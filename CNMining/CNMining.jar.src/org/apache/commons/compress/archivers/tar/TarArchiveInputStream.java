/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
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
/*     */ public class TarArchiveInputStream
/*     */   extends ArchiveInputStream
/*     */ {
/*     */   private static final int SMALL_BUFFER_SIZE = 256;
/*     */   private static final int BUFFER_SIZE = 8192;
/*     */   private boolean hasHitEOF;
/*     */   private long entrySize;
/*     */   private long entryOffset;
/*     */   private byte[] readBuf;
/*     */   protected final TarBuffer buffer;
/*     */   private TarArchiveEntry currEntry;
/*     */   
/*     */   public TarArchiveInputStream(InputStream is)
/*     */   {
/*  55 */     this(is, 10240, 512);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveInputStream(InputStream is, int blockSize)
/*     */   {
/*  64 */     this(is, blockSize, 512);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveInputStream(InputStream is, int blockSize, int recordSize)
/*     */   {
/*  74 */     this.buffer = new TarBuffer(is, blockSize, recordSize);
/*  75 */     this.readBuf = null;
/*  76 */     this.hasHitEOF = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/*  84 */     this.buffer.close();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRecordSize()
/*     */   {
/*  93 */     return this.buffer.getRecordSize();
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
/* 109 */     if (this.entrySize - this.entryOffset > 2147483647L) {
/* 110 */       return Integer.MAX_VALUE;
/*     */     }
/* 112 */     return (int)(this.entrySize - this.entryOffset);
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
/*     */   public long skip(long numToSkip)
/*     */     throws IOException
/*     */   {
/* 130 */     byte[] skipBuf = new byte[' '];
/* 131 */     long skip = numToSkip;
/* 132 */     while (skip > 0L) {
/* 133 */       int realSkip = (int)(skip > skipBuf.length ? skipBuf.length : skip);
/* 134 */       int numRead = read(skipBuf, 0, realSkip);
/* 135 */       if (numRead == -1) {
/*     */         break;
/*     */       }
/* 138 */       skip -= numRead;
/*     */     }
/* 140 */     return numToSkip - skip;
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
/*     */   public void reset() {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveEntry getNextTarEntry()
/*     */     throws IOException
/*     */   {
/* 163 */     if (this.hasHitEOF) {
/* 164 */       return null;
/*     */     }
/*     */     
/* 167 */     if (this.currEntry != null) {
/* 168 */       long numToSkip = this.entrySize - this.entryOffset;
/*     */       
/* 170 */       while (numToSkip > 0L) {
/* 171 */         long skipped = skip(numToSkip);
/* 172 */         if (skipped <= 0L) {
/* 173 */           throw new RuntimeException("failed to skip current tar entry");
/*     */         }
/* 175 */         numToSkip -= skipped;
/*     */       }
/*     */       
/* 178 */       this.readBuf = null;
/*     */     }
/*     */     
/* 181 */     byte[] headerBuf = this.buffer.readRecord();
/*     */     
/* 183 */     if (headerBuf == null) {
/* 184 */       this.hasHitEOF = true;
/* 185 */     } else if (this.buffer.isEOFRecord(headerBuf)) {
/* 186 */       this.hasHitEOF = true;
/*     */     }
/*     */     
/* 189 */     if (this.hasHitEOF) {
/* 190 */       this.currEntry = null;
/*     */     } else {
/* 192 */       this.currEntry = new TarArchiveEntry(headerBuf);
/* 193 */       this.entryOffset = 0L;
/* 194 */       this.entrySize = this.currEntry.getSize();
/*     */     }
/*     */     
/* 197 */     if ((this.currEntry != null) && (this.currEntry.isGNULongNameEntry()))
/*     */     {
/* 199 */       StringBuffer longName = new StringBuffer();
/* 200 */       byte[] buf = new byte['Ā'];
/* 201 */       int length = 0;
/* 202 */       while ((length = read(buf)) >= 0) {
/* 203 */         longName.append(new String(buf, 0, length));
/*     */       }
/* 205 */       getNextEntry();
/* 206 */       if (this.currEntry == null)
/*     */       {
/*     */ 
/* 209 */         return null;
/*     */       }
/*     */       
/* 212 */       if ((longName.length() > 0) && (longName.charAt(longName.length() - 1) == 0))
/*     */       {
/* 214 */         longName.deleteCharAt(longName.length() - 1);
/*     */       }
/* 216 */       this.currEntry.setName(longName.toString());
/*     */     }
/*     */     
/* 219 */     return this.currEntry;
/*     */   }
/*     */   
/*     */   public ArchiveEntry getNextEntry() throws IOException {
/* 223 */     return getNextTarEntry();
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
/*     */   public int read(byte[] buf, int offset, int numToRead)
/*     */     throws IOException
/*     */   {
/* 240 */     int totalRead = 0;
/*     */     
/* 242 */     if (this.entryOffset >= this.entrySize) {
/* 243 */       return -1;
/*     */     }
/*     */     
/* 246 */     if (numToRead + this.entryOffset > this.entrySize) {
/* 247 */       numToRead = (int)(this.entrySize - this.entryOffset);
/*     */     }
/*     */     
/* 250 */     if (this.readBuf != null) {
/* 251 */       int sz = numToRead > this.readBuf.length ? this.readBuf.length : numToRead;
/*     */       
/*     */ 
/* 254 */       System.arraycopy(this.readBuf, 0, buf, offset, sz);
/*     */       
/* 256 */       if (sz >= this.readBuf.length) {
/* 257 */         this.readBuf = null;
/*     */       } else {
/* 259 */         int newLen = this.readBuf.length - sz;
/* 260 */         byte[] newBuf = new byte[newLen];
/*     */         
/* 262 */         System.arraycopy(this.readBuf, sz, newBuf, 0, newLen);
/*     */         
/* 264 */         this.readBuf = newBuf;
/*     */       }
/*     */       
/* 267 */       totalRead += sz;
/* 268 */       numToRead -= sz;
/* 269 */       offset += sz;
/*     */     }
/*     */     
/* 272 */     while (numToRead > 0) {
/* 273 */       byte[] rec = this.buffer.readRecord();
/*     */       
/* 275 */       if (rec == null)
/*     */       {
/* 277 */         throw new IOException("unexpected EOF with " + numToRead + " bytes unread. Occured at byte: " + getCount());
/*     */       }
/*     */       
/* 280 */       count(rec.length);
/* 281 */       int sz = numToRead;
/* 282 */       int recLen = rec.length;
/*     */       
/* 284 */       if (recLen > sz) {
/* 285 */         System.arraycopy(rec, 0, buf, offset, sz);
/*     */         
/* 287 */         this.readBuf = new byte[recLen - sz];
/*     */         
/* 289 */         System.arraycopy(rec, sz, this.readBuf, 0, recLen - sz);
/*     */       } else {
/* 291 */         sz = recLen;
/*     */         
/* 293 */         System.arraycopy(rec, 0, buf, offset, recLen);
/*     */       }
/*     */       
/* 296 */       totalRead += sz;
/* 297 */       numToRead -= sz;
/* 298 */       offset += sz;
/*     */     }
/*     */     
/* 301 */     this.entryOffset += totalRead;
/*     */     
/* 303 */     return totalRead;
/*     */   }
/*     */   
/*     */   protected final TarArchiveEntry getCurrentEntry() {
/* 307 */     return this.currEntry;
/*     */   }
/*     */   
/*     */   protected final void setCurrentEntry(TarArchiveEntry e) {
/* 311 */     this.currEntry = e;
/*     */   }
/*     */   
/*     */   protected final boolean isAtEOF() {
/* 315 */     return this.hasHitEOF;
/*     */   }
/*     */   
/*     */   protected final void setAtEOF(boolean b) {
/* 319 */     this.hasHitEOF = b;
/*     */   }
/*     */   
/*     */ 
/*     */   public static boolean matches(byte[] signature, int length)
/*     */   {
/* 325 */     if (length < 265) {
/* 326 */       return false;
/*     */     }
/*     */     
/* 329 */     if ((ArchiveUtils.matchAsciiBuffer("ustar\000", signature, 257, 6)) && (ArchiveUtils.matchAsciiBuffer("00", signature, 263, 2)))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 335 */       return true;
/*     */     }
/* 337 */     if ((ArchiveUtils.matchAsciiBuffer("ustar ", signature, 257, 6)) && ((ArchiveUtils.matchAsciiBuffer(" \000", signature, 263, 2)) || (ArchiveUtils.matchAsciiBuffer("0\000", signature, 263, 2))))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 348 */       return true;
/*     */     }
/* 350 */     return false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/tar/TarArchiveInputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */