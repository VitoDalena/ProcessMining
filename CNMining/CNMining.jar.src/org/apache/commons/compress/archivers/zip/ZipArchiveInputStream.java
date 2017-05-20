/*     */ package org.apache.commons.compress.archivers.zip;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.DataFormatException;
/*     */ import java.util.zip.Inflater;
/*     */ import java.util.zip.ZipException;
/*     */ import org.apache.commons.compress.archivers.ArchiveEntry;
/*     */ import org.apache.commons.compress.archivers.ArchiveInputStream;
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
/*     */ public class ZipArchiveInputStream
/*     */   extends ArchiveInputStream
/*     */ {
/*     */   private static final int SHORT = 2;
/*     */   private static final int WORD = 4;
/*     */   private final ZipEncoding zipEncoding;
/*     */   private final boolean useUnicodeExtraFields;
/*     */   private final InputStream in;
/*  61 */   private final Inflater inf = new Inflater(true);
/*  62 */   private final CRC32 crc = new CRC32();
/*     */   
/*  64 */   private final byte[] buf = new byte['Ȁ'];
/*     */   
/*  66 */   private ZipArchiveEntry current = null;
/*  67 */   private boolean closed = false;
/*  68 */   private boolean hitCentralDirectory = false;
/*  69 */   private int readBytesOfEntry = 0; private int offsetInBuffer = 0;
/*  70 */   private int bytesReadFromStream = 0;
/*  71 */   private int lengthOfLastRead = 0;
/*  72 */   private boolean hasDataDescriptor = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static final int LFH_LEN = 30;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveInputStream(InputStream inputStream)
/*     */   {
/*  90 */     this(inputStream, "UTF8", true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields)
/*     */   {
/* 102 */     this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
/* 103 */     this.useUnicodeExtraFields = useUnicodeExtraFields;
/* 104 */     this.in = new PushbackInputStream(inputStream, this.buf.length);
/*     */   }
/*     */   
/*     */   public ZipArchiveEntry getNextZipEntry() throws IOException {
/* 108 */     if ((this.closed) || (this.hitCentralDirectory)) {
/* 109 */       return null;
/*     */     }
/* 111 */     if (this.current != null) {
/* 112 */       closeEntry();
/*     */     }
/* 114 */     byte[] lfh = new byte[30];
/*     */     try {
/* 116 */       readFully(lfh);
/*     */     } catch (EOFException e) {
/* 118 */       return null;
/*     */     }
/* 120 */     ZipLong sig = new ZipLong(lfh);
/* 121 */     if (sig.equals(ZipLong.CFH_SIG)) {
/* 122 */       this.hitCentralDirectory = true;
/* 123 */       return null;
/*     */     }
/* 125 */     if (!sig.equals(ZipLong.LFH_SIG)) {
/* 126 */       return null;
/*     */     }
/*     */     
/* 129 */     int off = 4;
/* 130 */     this.current = new ZipArchiveEntry();
/*     */     
/* 132 */     int versionMadeBy = ZipShort.getValue(lfh, off);
/* 133 */     off += 2;
/* 134 */     this.current.setPlatform(versionMadeBy >> 8 & 0xF);
/*     */     
/*     */ 
/* 137 */     int generalPurposeFlag = ZipShort.getValue(lfh, off);
/* 138 */     boolean hasEFS = (generalPurposeFlag & 0x800) != 0;
/*     */     
/* 140 */     ZipEncoding entryEncoding = hasEFS ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
/*     */     
/* 142 */     this.hasDataDescriptor = ((generalPurposeFlag & 0x8) != 0);
/*     */     
/* 144 */     off += 2;
/*     */     
/* 146 */     this.current.setMethod(ZipShort.getValue(lfh, off));
/* 147 */     off += 2;
/*     */     
/* 149 */     long time = ZipUtil.dosToJavaTime(ZipLong.getValue(lfh, off));
/* 150 */     this.current.setTime(time);
/* 151 */     off += 4;
/*     */     
/* 153 */     if (!this.hasDataDescriptor) {
/* 154 */       this.current.setCrc(ZipLong.getValue(lfh, off));
/* 155 */       off += 4;
/*     */       
/* 157 */       this.current.setCompressedSize(ZipLong.getValue(lfh, off));
/* 158 */       off += 4;
/*     */       
/* 160 */       this.current.setSize(ZipLong.getValue(lfh, off));
/* 161 */       off += 4;
/*     */     } else {
/* 163 */       off += 12;
/*     */     }
/*     */     
/* 166 */     int fileNameLen = ZipShort.getValue(lfh, off);
/*     */     
/* 168 */     off += 2;
/*     */     
/* 170 */     int extraLen = ZipShort.getValue(lfh, off);
/* 171 */     off += 2;
/*     */     
/* 173 */     byte[] fileName = new byte[fileNameLen];
/* 174 */     readFully(fileName);
/* 175 */     this.current.setName(entryEncoding.decode(fileName));
/*     */     
/* 177 */     byte[] extraData = new byte[extraLen];
/* 178 */     readFully(extraData);
/* 179 */     this.current.setExtra(extraData);
/*     */     
/* 181 */     if ((!hasEFS) && (this.useUnicodeExtraFields)) {
/* 182 */       ZipUtil.setNameAndCommentFromExtraFields(this.current, fileName, null);
/*     */     }
/* 184 */     return this.current;
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 188 */     return getNextZipEntry();
/*     */   }
/*     */   
/*     */   public int read(byte[] buffer, int start, int length) throws IOException {
/* 192 */     if (this.closed) {
/* 193 */       throw new IOException("The stream is closed");
/*     */     }
/* 195 */     if ((this.inf.finished()) || (this.current == null)) {
/* 196 */       return -1;
/*     */     }
/*     */     
/*     */ 
/* 200 */     if ((start <= buffer.length) && (length >= 0) && (start >= 0) && (buffer.length - start >= length))
/*     */     {
/* 202 */       if (this.current.getMethod() == 0) {
/* 203 */         int csize = (int)this.current.getSize();
/* 204 */         if (this.readBytesOfEntry >= csize) {
/* 205 */           return -1;
/*     */         }
/* 207 */         if (this.offsetInBuffer >= this.lengthOfLastRead) {
/* 208 */           this.offsetInBuffer = 0;
/* 209 */           if ((this.lengthOfLastRead = this.in.read(this.buf)) == -1) {
/* 210 */             return -1;
/*     */           }
/* 212 */           count(this.lengthOfLastRead);
/* 213 */           this.bytesReadFromStream += this.lengthOfLastRead;
/*     */         }
/* 215 */         int toRead = length > this.lengthOfLastRead ? this.lengthOfLastRead - this.offsetInBuffer : length;
/*     */         
/*     */ 
/* 218 */         if (csize - this.readBytesOfEntry < toRead) {
/* 219 */           toRead = csize - this.readBytesOfEntry;
/*     */         }
/* 221 */         System.arraycopy(this.buf, this.offsetInBuffer, buffer, start, toRead);
/* 222 */         this.offsetInBuffer += toRead;
/* 223 */         this.readBytesOfEntry += toRead;
/* 224 */         this.crc.update(buffer, start, toRead);
/* 225 */         return toRead;
/*     */       }
/* 227 */       if (this.inf.needsInput()) {
/* 228 */         fill();
/* 229 */         if (this.lengthOfLastRead > 0) {
/* 230 */           this.bytesReadFromStream += this.lengthOfLastRead;
/*     */         }
/*     */       }
/* 233 */       int read = 0;
/*     */       try {
/* 235 */         read = this.inf.inflate(buffer, start, length);
/*     */       } catch (DataFormatException e) {
/* 237 */         throw new ZipException(e.getMessage());
/*     */       }
/* 239 */       if ((read == 0) && (this.inf.finished())) {
/* 240 */         return -1;
/*     */       }
/* 242 */       this.crc.update(buffer, start, read);
/* 243 */       return read;
/*     */     }
/* 245 */     throw new ArrayIndexOutOfBoundsException();
/*     */   }
/*     */   
/*     */   public void close() throws IOException {
/* 249 */     if (!this.closed) {
/* 250 */       this.closed = true;
/* 251 */       this.in.close();
/*     */     }
/*     */   }
/*     */   
/*     */   public long skip(long value) throws IOException {
/* 256 */     if (value >= 0L) {
/* 257 */       long skipped = 0L;
/* 258 */       byte[] b = new byte['Ѐ'];
/* 259 */       while (skipped != value) {
/* 260 */         long rem = value - skipped;
/* 261 */         int x = read(b, 0, (int)(b.length > rem ? rem : b.length));
/* 262 */         if (x == -1) {
/* 263 */           return skipped;
/*     */         }
/* 265 */         skipped += x;
/*     */       }
/* 267 */       return skipped;
/*     */     }
/* 269 */     throw new IllegalArgumentException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean matches(byte[] signature, int length)
/*     */   {
/* 277 */     if (length < ZipArchiveOutputStream.LFH_SIG.length) {
/* 278 */       return false;
/*     */     }
/*     */     
/* 281 */     return (checksig(signature, ZipArchiveOutputStream.LFH_SIG)) || (checksig(signature, ZipArchiveOutputStream.EOCD_SIG));
/*     */   }
/*     */   
/*     */   private static boolean checksig(byte[] signature, byte[] expected)
/*     */   {
/* 286 */     for (int i = 0; i < expected.length; i++) {
/* 287 */       if (signature[i] != expected[i]) {
/* 288 */         return false;
/*     */       }
/*     */     }
/* 291 */     return true;
/*     */   }
/*     */   
/*     */   private void closeEntry() throws IOException {
/* 295 */     if (this.closed) {
/* 296 */       throw new IOException("The stream is closed");
/*     */     }
/* 298 */     if (this.current == null) {
/* 299 */       return;
/*     */     }
/*     */     
/* 302 */     skip(Long.MAX_VALUE);
/*     */     int inB;
/* 304 */     int inB; if (this.current.getMethod() == 8) {
/* 305 */       inB = this.inf.getTotalIn();
/*     */     } else {
/* 307 */       inB = this.readBytesOfEntry;
/*     */     }
/* 309 */     int diff = 0;
/*     */     
/*     */ 
/* 312 */     if ((diff = this.bytesReadFromStream - inB) != 0) {
/* 313 */       ((PushbackInputStream)this.in).unread(this.buf, this.lengthOfLastRead - diff, diff);
/*     */     }
/*     */     
/*     */ 
/* 317 */     if (this.hasDataDescriptor) {
/* 318 */       readFully(new byte[16]);
/*     */     }
/*     */     
/* 321 */     this.inf.reset();
/* 322 */     this.readBytesOfEntry = (this.offsetInBuffer = this.bytesReadFromStream = this.lengthOfLastRead = 0);
/*     */     
/* 324 */     this.crc.reset();
/* 325 */     this.current = null;
/*     */   }
/*     */   
/*     */   private void fill() throws IOException {
/* 329 */     if (this.closed) {
/* 330 */       throw new IOException("The stream is closed");
/*     */     }
/* 332 */     if ((this.lengthOfLastRead = this.in.read(this.buf)) > 0) {
/* 333 */       this.inf.setInput(this.buf, 0, this.lengthOfLastRead);
/*     */     }
/*     */   }
/*     */   
/*     */   private void readFully(byte[] b) throws IOException {
/* 338 */     int count = 0;int x = 0;
/* 339 */     while (count != b.length) {
/* 340 */       count += (x = this.in.read(b, count, b.length - count));
/* 341 */       if (x == -1) {
/* 342 */         throw new EOFException();
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/zip/ZipArchiveInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */