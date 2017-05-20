/*     */ package org.apache.commons.compress.archivers.cpio;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FilterOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.HashMap;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveOutputStream;
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
/*     */ public class CpioArchiveOutputStream
/*     */   extends ArchiveOutputStream
/*     */   implements CpioConstants
/*     */ {
/*     */   private CpioArchiveEntry entry;
/*  67 */   private boolean closed = false;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean finished;
/*     */   
/*     */ 
/*     */   private final short entryFormat;
/*     */   
/*     */ 
/*  77 */   private final HashMap names = new HashMap();
/*     */   
/*  79 */   private long crc = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */   private long written;
/*     */   
/*     */ 
/*     */ 
/*     */   private final OutputStream out;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CpioArchiveOutputStream(OutputStream out, short format)
/*     */   {
/*  94 */     this.out = new FilterOutputStream(out);
/*  95 */     switch (format) {
/*     */     case 1: case 2: 
/*     */     case 4: case 8: 
/*     */       break;
/*     */     case 3: case 5: 
/*     */     case 6: case 7: 
/*     */     default: 
/* 102 */       throw new IllegalArgumentException("Unknown format: " + format);
/*     */     }
/*     */     
/* 105 */     this.entryFormat = format;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CpioArchiveOutputStream(OutputStream out)
/*     */   {
/* 116 */     this(out, (short)1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void ensureOpen()
/*     */     throws IOException
/*     */   {
/* 126 */     if (this.closed) {
/* 127 */       throw new IOException("Stream closed");
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
/*     */   public void putArchiveEntry(ArchiveEntry entry)
/*     */     throws IOException
/*     */   {
/* 146 */     if (this.finished) {
/* 147 */       throw new IOException("Stream has already been finished");
/*     */     }
/*     */     
/* 150 */     CpioArchiveEntry e = (CpioArchiveEntry)entry;
/* 151 */     ensureOpen();
/* 152 */     if (this.entry != null) {
/* 153 */       closeArchiveEntry();
/*     */     }
/* 155 */     if (e.getTime() == -1L) {
/* 156 */       e.setTime(System.currentTimeMillis());
/*     */     }
/*     */     
/* 159 */     short format = e.getFormat();
/* 160 */     if (format != this.entryFormat) {
/* 161 */       throw new IOException("Header format: " + format + " does not match existing format: " + this.entryFormat);
/*     */     }
/*     */     
/* 164 */     if (this.names.put(e.getName(), e) != null) {
/* 165 */       throw new IOException("duplicate entry: " + e.getName());
/*     */     }
/*     */     
/* 168 */     writeHeader(e);
/* 169 */     this.entry = e;
/* 170 */     this.written = 0L;
/*     */   }
/*     */   
/*     */   private void writeHeader(CpioArchiveEntry e) throws IOException {
/* 174 */     switch (e.getFormat()) {
/*     */     case 1: 
/* 176 */       this.out.write(ArchiveUtils.toAsciiBytes("070701"));
/* 177 */       writeNewEntry(e);
/* 178 */       break;
/*     */     case 2: 
/* 180 */       this.out.write(ArchiveUtils.toAsciiBytes("070702"));
/* 181 */       writeNewEntry(e);
/* 182 */       break;
/*     */     case 4: 
/* 184 */       this.out.write(ArchiveUtils.toAsciiBytes("070707"));
/* 185 */       writeOldAsciiEntry(e);
/* 186 */       break;
/*     */     case 8: 
/* 188 */       boolean swapHalfWord = true;
/* 189 */       writeBinaryLong(29127L, 2, swapHalfWord);
/* 190 */       writeOldBinaryEntry(e, swapHalfWord);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeNewEntry(CpioArchiveEntry entry) throws IOException
/*     */   {
/* 196 */     writeAsciiLong(entry.getInode(), 8, 16);
/* 197 */     writeAsciiLong(entry.getMode(), 8, 16);
/* 198 */     writeAsciiLong(entry.getUID(), 8, 16);
/* 199 */     writeAsciiLong(entry.getGID(), 8, 16);
/* 200 */     writeAsciiLong(entry.getNumberOfLinks(), 8, 16);
/* 201 */     writeAsciiLong(entry.getTime(), 8, 16);
/* 202 */     writeAsciiLong(entry.getSize(), 8, 16);
/* 203 */     writeAsciiLong(entry.getDeviceMaj(), 8, 16);
/* 204 */     writeAsciiLong(entry.getDeviceMin(), 8, 16);
/* 205 */     writeAsciiLong(entry.getRemoteDeviceMaj(), 8, 16);
/* 206 */     writeAsciiLong(entry.getRemoteDeviceMin(), 8, 16);
/* 207 */     writeAsciiLong(entry.getName().length() + 1, 8, 16);
/* 208 */     writeAsciiLong(entry.getChksum(), 8, 16);
/* 209 */     writeCString(entry.getName());
/* 210 */     pad(entry.getHeaderPadCount());
/*     */   }
/*     */   
/*     */   private void writeOldAsciiEntry(CpioArchiveEntry entry) throws IOException
/*     */   {
/* 215 */     writeAsciiLong(entry.getDevice(), 6, 8);
/* 216 */     writeAsciiLong(entry.getInode(), 6, 8);
/* 217 */     writeAsciiLong(entry.getMode(), 6, 8);
/* 218 */     writeAsciiLong(entry.getUID(), 6, 8);
/* 219 */     writeAsciiLong(entry.getGID(), 6, 8);
/* 220 */     writeAsciiLong(entry.getNumberOfLinks(), 6, 8);
/* 221 */     writeAsciiLong(entry.getRemoteDevice(), 6, 8);
/* 222 */     writeAsciiLong(entry.getTime(), 11, 8);
/* 223 */     writeAsciiLong(entry.getName().length() + 1, 6, 8);
/* 224 */     writeAsciiLong(entry.getSize(), 11, 8);
/* 225 */     writeCString(entry.getName());
/*     */   }
/*     */   
/*     */   private void writeOldBinaryEntry(CpioArchiveEntry entry, boolean swapHalfWord) throws IOException
/*     */   {
/* 230 */     writeBinaryLong(entry.getDevice(), 2, swapHalfWord);
/* 231 */     writeBinaryLong(entry.getInode(), 2, swapHalfWord);
/* 232 */     writeBinaryLong(entry.getMode(), 2, swapHalfWord);
/* 233 */     writeBinaryLong(entry.getUID(), 2, swapHalfWord);
/* 234 */     writeBinaryLong(entry.getGID(), 2, swapHalfWord);
/* 235 */     writeBinaryLong(entry.getNumberOfLinks(), 2, swapHalfWord);
/* 236 */     writeBinaryLong(entry.getRemoteDevice(), 2, swapHalfWord);
/* 237 */     writeBinaryLong(entry.getTime(), 4, swapHalfWord);
/* 238 */     writeBinaryLong(entry.getName().length() + 1, 2, swapHalfWord);
/* 239 */     writeBinaryLong(entry.getSize(), 4, swapHalfWord);
/* 240 */     writeCString(entry.getName());
/* 241 */     pad(entry.getHeaderPadCount());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void closeArchiveEntry()
/*     */     throws IOException
/*     */   {
/* 251 */     if (this.finished) {
/* 252 */       throw new IOException("Stream has already been finished");
/*     */     }
/*     */     
/* 255 */     ensureOpen();
/*     */     
/* 257 */     if (this.entry == null) {
/* 258 */       throw new IOException("Trying to close non-existent entry");
/*     */     }
/*     */     
/* 261 */     if (this.entry.getSize() != this.written) {
/* 262 */       throw new IOException("invalid entry size (expected " + this.entry.getSize() + " but got " + this.written + " bytes)");
/*     */     }
/*     */     
/*     */ 
/* 266 */     pad(this.entry.getDataPadCount());
/* 267 */     if ((this.entry.getFormat() == 2) && 
/* 268 */       (this.crc != this.entry.getChksum())) {
/* 269 */       throw new IOException("CRC Error");
/*     */     }
/*     */     
/* 272 */     this.entry = null;
/* 273 */     this.crc = 0L;
/* 274 */     this.written = 0L;
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
/*     */   public void write(byte[] b, int off, int len)
/*     */     throws IOException
/*     */   {
/* 293 */     ensureOpen();
/* 294 */     if ((off < 0) || (len < 0) || (off > b.length - len))
/* 295 */       throw new IndexOutOfBoundsException();
/* 296 */     if (len == 0) {
/* 297 */       return;
/*     */     }
/*     */     
/* 300 */     if (this.entry == null) {
/* 301 */       throw new IOException("no current CPIO entry");
/*     */     }
/* 303 */     if (this.written + len > this.entry.getSize()) {
/* 304 */       throw new IOException("attempt to write past end of STORED entry");
/*     */     }
/* 306 */     this.out.write(b, off, len);
/* 307 */     this.written += len;
/* 308 */     if (this.entry.getFormat() == 2) {
/* 309 */       for (int pos = 0; pos < len; pos++) {
/* 310 */         this.crc += (b[pos] & 0xFF);
/*     */       }
/*     */     }
/* 313 */     count(len);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void finish()
/*     */     throws IOException
/*     */   {
/* 326 */     ensureOpen();
/* 327 */     if (this.finished) {
/* 328 */       throw new IOException("This archive has already been finished");
/*     */     }
/*     */     
/* 331 */     if (this.entry != null) {
/* 332 */       throw new IOException("This archive contains unclosed entries.");
/*     */     }
/* 334 */     this.entry = new CpioArchiveEntry(this.entryFormat);
/* 335 */     this.entry.setName("TRAILER!!!");
/* 336 */     this.entry.setNumberOfLinks(1L);
/* 337 */     writeHeader(this.entry);
/* 338 */     closeArchiveEntry();
/*     */     
/* 340 */     this.finished = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 351 */     if (!this.finished) {
/* 352 */       finish();
/*     */     }
/*     */     
/* 355 */     if (!this.closed) {
/* 356 */       this.out.close();
/* 357 */       this.closed = true;
/*     */     }
/*     */   }
/*     */   
/*     */   private void pad(int count) throws IOException {
/* 362 */     if (count > 0) {
/* 363 */       byte[] buff = new byte[count];
/* 364 */       this.out.write(buff);
/*     */     }
/*     */   }
/*     */   
/*     */   private void writeBinaryLong(long number, int length, boolean swapHalfWord) throws IOException
/*     */   {
/* 370 */     byte[] tmp = CpioUtil.long2byteArray(number, length, swapHalfWord);
/* 371 */     this.out.write(tmp);
/*     */   }
/*     */   
/*     */   private void writeAsciiLong(long number, int length, int radix) throws IOException
/*     */   {
/* 376 */     StringBuffer tmp = new StringBuffer();
/*     */     
/* 378 */     if (radix == 16) {
/* 379 */       tmp.append(Long.toHexString(number));
/* 380 */     } else if (radix == 8) {
/* 381 */       tmp.append(Long.toOctalString(number));
/*     */     } else
/* 383 */       tmp.append(Long.toString(number));
/*     */     String tmpStr;
/*     */     String tmpStr;
/* 386 */     if (tmp.length() <= length) {
/* 387 */       long insertLength = length - tmp.length();
/* 388 */       for (int pos = 0; pos < insertLength; pos++) {
/* 389 */         tmp.insert(0, "0");
/*     */       }
/* 391 */       tmpStr = tmp.toString();
/*     */     } else {
/* 393 */       tmpStr = tmp.substring(tmp.length() - length);
/*     */     }
/* 395 */     this.out.write(ArchiveUtils.toAsciiBytes(tmpStr));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeCString(String str)
/*     */     throws IOException
/*     */   {
/* 404 */     this.out.write(ArchiveUtils.toAsciiBytes(str));
/* 405 */     this.out.write(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName)
/*     */     throws IOException
/*     */   {
/* 415 */     if (this.finished) {
/* 416 */       throw new IOException("Stream has already been finished");
/*     */     }
/* 418 */     return new CpioArchiveEntry(inputFile, entryName);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/cpio/CpioArchiveOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */