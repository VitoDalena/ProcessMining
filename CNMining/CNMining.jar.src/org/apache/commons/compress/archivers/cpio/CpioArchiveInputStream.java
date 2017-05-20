/*     */ package org.apache.commons.compress.archivers.cpio;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
/*     */ import org.apache.commons.compress.utils.ArchiveUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CpioArchiveInputStream
/*     */   extends ArchiveInputStream
/*     */   implements CpioConstants
/*     */ {
/*  66 */   private boolean closed = false;
/*     */   
/*     */   private CpioArchiveEntry entry;
/*     */   
/*  70 */   private long entryBytesRead = 0L;
/*     */   
/*  72 */   private boolean entryEOF = false;
/*     */   
/*  74 */   private final byte[] tmpbuf = new byte['က'];
/*     */   
/*  76 */   private long crc = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */   private final InputStream in;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CpioArchiveInputStream(InputStream in)
/*     */   {
/*  87 */     this.in = in;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int available()
/*     */     throws IOException
/*     */   {
/* 103 */     ensureOpen();
/* 104 */     if (this.entryEOF) {
/* 105 */       return 0;
/*     */     }
/* 107 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 117 */     if (!this.closed) {
/* 118 */       this.in.close();
/* 119 */       this.closed = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void closeEntry()
/*     */     throws IOException
/*     */   {
/* 132 */     ensureOpen();
/* 133 */     while (read(this.tmpbuf, 0, this.tmpbuf.length) != -1) {}
/*     */     
/*     */ 
/*     */ 
/* 137 */     this.entryEOF = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void ensureOpen()
/*     */     throws IOException
/*     */   {
/* 147 */     if (this.closed) {
/* 148 */       throw new IOException("Stream closed");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CpioArchiveEntry getNextCPIOEntry()
/*     */     throws IOException
/*     */   {
/* 162 */     ensureOpen();
/* 163 */     if (this.entry != null) {
/* 164 */       closeEntry();
/*     */     }
/* 166 */     byte[] magic = new byte[2];
/* 167 */     readFully(magic, 0, magic.length);
/* 168 */     if (CpioUtil.byteArray2long(magic, false) == 29127L) {
/* 169 */       this.entry = readOldBinaryEntry(false);
/* 170 */     } else if (CpioUtil.byteArray2long(magic, true) == 29127L) {
/* 171 */       this.entry = readOldBinaryEntry(true);
/*     */     } else {
/* 173 */       byte[] more_magic = new byte[4];
/* 174 */       readFully(more_magic, 0, more_magic.length);
/* 175 */       byte[] tmp = new byte[6];
/* 176 */       System.arraycopy(magic, 0, tmp, 0, magic.length);
/* 177 */       System.arraycopy(more_magic, 0, tmp, magic.length, more_magic.length);
/*     */       
/* 179 */       String magicString = ArchiveUtils.toAsciiString(tmp);
/* 180 */       if (magicString.equals("070701")) {
/* 181 */         this.entry = readNewEntry(false);
/* 182 */       } else if (magicString.equals("070702")) {
/* 183 */         this.entry = readNewEntry(true);
/* 184 */       } else if (magicString.equals("070707")) {
/* 185 */         this.entry = readOldAsciiEntry();
/*     */       } else {
/* 187 */         throw new IOException("Unknown magic [" + magicString + "]. Occured at byte: " + getCount());
/*     */       }
/*     */     }
/*     */     
/* 191 */     this.entryBytesRead = 0L;
/* 192 */     this.entryEOF = false;
/* 193 */     this.crc = 0L;
/*     */     
/* 195 */     if (this.entry.getName().equals("TRAILER!!!")) {
/* 196 */       this.entryEOF = true;
/* 197 */       return null;
/*     */     }
/* 199 */     return this.entry;
/*     */   }
/*     */   
/*     */   private void skip(int bytes) throws IOException {
/* 203 */     byte[] buff = new byte[4];
/* 204 */     if (bytes > 0) {
/* 205 */       readFully(buff, 0, bytes);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int read(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 227 */     ensureOpen();
/* 228 */     if ((off < 0) || (len < 0) || (off > b.length - len))
/* 229 */       throw new IndexOutOfBoundsException();
/* 230 */     if (len == 0) {
/* 231 */       return 0;
/*     */     }
/*     */     
/* 234 */     if ((this.entry == null) || (this.entryEOF)) {
/* 235 */       return -1;
/*     */     }
/* 237 */     if (this.entryBytesRead == this.entry.getSize()) {
/* 238 */       skip(this.entry.getDataPadCount());
/* 239 */       this.entryEOF = true;
/* 240 */       if ((this.entry.getFormat() == 2) && 
/* 241 */         (this.crc != this.entry.getChksum())) {
/* 242 */         throw new IOException("CRC Error. Occured at byte: " + getCount());
/*     */       }
/*     */       
/* 245 */       return -1;
/*     */     }
/* 247 */     int tmplength = (int)Math.min(len, this.entry.getSize() - this.entryBytesRead);
/*     */     
/* 249 */     if (tmplength < 0) {
/* 250 */       return -1;
/*     */     }
/*     */     
/* 253 */     int tmpread = readFully(b, off, tmplength);
/* 254 */     if (this.entry.getFormat() == 2) {
/* 255 */       for (int pos = 0; pos < tmpread; pos++) {
/* 256 */         this.crc += (b[pos] & 0xFF);
/*     */       }
/*     */     }
/* 259 */     this.entryBytesRead += tmpread;
/*     */     
/* 261 */     return tmpread;
/*     */   }
/*     */   
/*     */   private final int readFully(byte[] b, int off, int len) throws IOException
/*     */   {
/* 266 */     if (len < 0) {
/* 267 */       throw new IndexOutOfBoundsException();
/*     */     }
/* 269 */     int n = 0;
/* 270 */     while (n < len) {
/* 271 */       int count = this.in.read(b, off + n, len - n);
/* 272 */       count(count);
/* 273 */       if (count < 0) {
/* 274 */         throw new EOFException();
/*     */       }
/* 276 */       n += count;
/*     */     }
/* 278 */     return n;
/*     */   }
/*     */   
/*     */   private long readBinaryLong(int length, boolean swapHalfWord) throws IOException
/*     */   {
/* 283 */     byte[] tmp = new byte[length];
/* 284 */     readFully(tmp, 0, tmp.length);
/* 285 */     return CpioUtil.byteArray2long(tmp, swapHalfWord);
/*     */   }
/*     */   
/*     */   private long readAsciiLong(int length, int radix) throws IOException
/*     */   {
/* 290 */     byte[] tmpBuffer = new byte[length];
/* 291 */     readFully(tmpBuffer, 0, tmpBuffer.length);
/* 292 */     return Long.parseLong(ArchiveUtils.toAsciiString(tmpBuffer), radix);
/*     */   }
/*     */   
/*     */   private CpioArchiveEntry readNewEntry(boolean hasCrc) throws IOException {
/*     */     CpioArchiveEntry ret;
/*     */     CpioArchiveEntry ret;
/* 298 */     if (hasCrc) {
/* 299 */       ret = new CpioArchiveEntry((short)2);
/*     */     } else {
/* 301 */       ret = new CpioArchiveEntry((short)1);
/*     */     }
/*     */     
/* 304 */     ret.setInode(readAsciiLong(8, 16));
/* 305 */     long mode = readAsciiLong(8, 16);
/* 306 */     if (mode != 0L) {
/* 307 */       ret.setMode(mode);
/*     */     }
/* 309 */     ret.setUID(readAsciiLong(8, 16));
/* 310 */     ret.setGID(readAsciiLong(8, 16));
/* 311 */     ret.setNumberOfLinks(readAsciiLong(8, 16));
/* 312 */     ret.setTime(readAsciiLong(8, 16));
/* 313 */     ret.setSize(readAsciiLong(8, 16));
/* 314 */     ret.setDeviceMaj(readAsciiLong(8, 16));
/* 315 */     ret.setDeviceMin(readAsciiLong(8, 16));
/* 316 */     ret.setRemoteDeviceMaj(readAsciiLong(8, 16));
/* 317 */     ret.setRemoteDeviceMin(readAsciiLong(8, 16));
/* 318 */     long namesize = readAsciiLong(8, 16);
/* 319 */     ret.setChksum(readAsciiLong(8, 16));
/* 320 */     String name = readCString((int)namesize);
/* 321 */     ret.setName(name);
/* 322 */     if ((mode == 0L) && (!name.equals("TRAILER!!!"))) {
/* 323 */       throw new IOException("Mode 0 only allowed in the trailer. Found entry name: " + name + " Occured at byte: " + getCount());
/*     */     }
/* 325 */     skip(ret.getHeaderPadCount());
/*     */     
/* 327 */     return ret;
/*     */   }
/*     */   
/*     */   private CpioArchiveEntry readOldAsciiEntry() throws IOException {
/* 331 */     CpioArchiveEntry ret = new CpioArchiveEntry((short)4);
/*     */     
/* 333 */     ret.setDevice(readAsciiLong(6, 8));
/* 334 */     ret.setInode(readAsciiLong(6, 8));
/* 335 */     long mode = readAsciiLong(6, 8);
/* 336 */     if (mode != 0L) {
/* 337 */       ret.setMode(mode);
/*     */     }
/* 339 */     ret.setUID(readAsciiLong(6, 8));
/* 340 */     ret.setGID(readAsciiLong(6, 8));
/* 341 */     ret.setNumberOfLinks(readAsciiLong(6, 8));
/* 342 */     ret.setRemoteDevice(readAsciiLong(6, 8));
/* 343 */     ret.setTime(readAsciiLong(11, 8));
/* 344 */     long namesize = readAsciiLong(6, 8);
/* 345 */     ret.setSize(readAsciiLong(11, 8));
/* 346 */     String name = readCString((int)namesize);
/* 347 */     ret.setName(name);
/* 348 */     if ((mode == 0L) && (!name.equals("TRAILER!!!"))) {
/* 349 */       throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + name + " Occured at byte: " + getCount());
/*     */     }
/*     */     
/* 352 */     return ret;
/*     */   }
/*     */   
/*     */   private CpioArchiveEntry readOldBinaryEntry(boolean swapHalfWord) throws IOException
/*     */   {
/* 357 */     CpioArchiveEntry ret = new CpioArchiveEntry((short)8);
/*     */     
/* 359 */     ret.setDevice(readBinaryLong(2, swapHalfWord));
/* 360 */     ret.setInode(readBinaryLong(2, swapHalfWord));
/* 361 */     long mode = readBinaryLong(2, swapHalfWord);
/* 362 */     if (mode != 0L) {
/* 363 */       ret.setMode(mode);
/*     */     }
/* 365 */     ret.setUID(readBinaryLong(2, swapHalfWord));
/* 366 */     ret.setGID(readBinaryLong(2, swapHalfWord));
/* 367 */     ret.setNumberOfLinks(readBinaryLong(2, swapHalfWord));
/* 368 */     ret.setRemoteDevice(readBinaryLong(2, swapHalfWord));
/* 369 */     ret.setTime(readBinaryLong(4, swapHalfWord));
/* 370 */     long namesize = readBinaryLong(2, swapHalfWord);
/* 371 */     ret.setSize(readBinaryLong(4, swapHalfWord));
/* 372 */     String name = readCString((int)namesize);
/* 373 */     ret.setName(name);
/* 374 */     if ((mode == 0L) && (!name.equals("TRAILER!!!"))) {
/* 375 */       throw new IOException("Mode 0 only allowed in the trailer. Found entry: " + name + "Occured at byte: " + getCount());
/*     */     }
/* 377 */     skip(ret.getHeaderPadCount());
/*     */     
/* 379 */     return ret;
/*     */   }
/*     */   
/*     */   private String readCString(int length) throws IOException {
/* 383 */     byte[] tmpBuffer = new byte[length];
/* 384 */     readFully(tmpBuffer, 0, tmpBuffer.length);
/* 385 */     return new String(tmpBuffer, 0, tmpBuffer.length - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long skip(long n)
/*     */     throws IOException
/*     */   {
/* 400 */     if (n < 0L) {
/* 401 */       throw new IllegalArgumentException("negative skip length");
/*     */     }
/* 403 */     ensureOpen();
/* 404 */     int max = (int)Math.min(n, 2147483647L);
/* 405 */     int total = 0;
/*     */     
/* 407 */     while (total < max) {
/* 408 */       int len = max - total;
/* 409 */       if (len > this.tmpbuf.length) {
/* 410 */         len = this.tmpbuf.length;
/*     */       }
/* 412 */       len = read(this.tmpbuf, 0, len);
/* 413 */       if (len == -1) {
/* 414 */         this.entryEOF = true;
/* 415 */         break;
/*     */       }
/* 417 */       total += len;
/*     */     }
/* 419 */     return total;
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 423 */     return getNextCPIOEntry();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean matches(byte[] signature, int length)
/*     */   {
/* 440 */     if (length < 6) {
/* 441 */       return false;
/*     */     }
/*     */     
/*     */ 
/* 445 */     if ((signature[0] == 113) && ((signature[1] & 0xFF) == 199)) {
/* 446 */       return true;
/*     */     }
/* 448 */     if ((signature[1] == 113) && ((signature[0] & 0xFF) == 199)) {
/* 449 */       return true;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 454 */     if (signature[0] != 48) {
/* 455 */       return false;
/*     */     }
/* 457 */     if (signature[1] != 55) {
/* 458 */       return false;
/*     */     }
/* 460 */     if (signature[2] != 48) {
/* 461 */       return false;
/*     */     }
/* 463 */     if (signature[3] != 55) {
/* 464 */       return false;
/*     */     }
/* 466 */     if (signature[4] != 48) {
/* 467 */       return false;
/*     */     }
/*     */     
/* 470 */     if (signature[5] == 49) {
/* 471 */       return true;
/*     */     }
/* 473 */     if (signature[5] == 50) {
/* 474 */       return true;
/*     */     }
/* 476 */     if (signature[5] == 55) {
/* 477 */       return true;
/*     */     }
/*     */     
/* 480 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/cpio/CpioArchiveInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */