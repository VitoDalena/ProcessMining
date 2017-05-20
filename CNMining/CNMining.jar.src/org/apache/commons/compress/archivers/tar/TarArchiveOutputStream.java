/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
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
/*     */ public class TarArchiveOutputStream
/*     */   extends ArchiveOutputStream
/*     */ {
/*     */   public static final int LONGFILE_ERROR = 0;
/*     */   public static final int LONGFILE_TRUNCATE = 1;
/*     */   public static final int LONGFILE_GNU = 2;
/*     */   private long currSize;
/*     */   private String currName;
/*     */   private long currBytes;
/*     */   private final byte[] recordBuf;
/*     */   private int assemLen;
/*     */   private final byte[] assemBuf;
/*     */   protected final TarBuffer buffer;
/*  51 */   private int longFileMode = 0;
/*     */   
/*  53 */   private boolean closed = false;
/*     */   
/*     */ 
/*  56 */   private boolean haveUnclosedEntry = false;
/*     */   
/*     */ 
/*  59 */   private boolean finished = false;
/*     */   
/*     */ 
/*     */   private final OutputStream out;
/*     */   
/*     */ 
/*     */ 
/*     */   public TarArchiveOutputStream(OutputStream os)
/*     */   {
/*  68 */     this(os, 10240, 512);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveOutputStream(OutputStream os, int blockSize)
/*     */   {
/*  77 */     this(os, blockSize, 512);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize)
/*     */   {
/*  87 */     this.out = os;
/*     */     
/*  89 */     this.buffer = new TarBuffer(os, blockSize, recordSize);
/*  90 */     this.assemLen = 0;
/*  91 */     this.assemBuf = new byte[recordSize];
/*  92 */     this.recordBuf = new byte[recordSize];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLongFileMode(int longFileMode)
/*     */   {
/* 103 */     this.longFileMode = longFileMode;
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
/*     */   public void finish()
/*     */     throws IOException
/*     */   {
/* 117 */     if (this.finished) {
/* 118 */       throw new IOException("This archive has already been finished");
/*     */     }
/*     */     
/* 121 */     if (this.haveUnclosedEntry) {
/* 122 */       throw new IOException("This archives contains unclosed entries.");
/*     */     }
/* 124 */     writeEOFRecord();
/* 125 */     writeEOFRecord();
/* 126 */     this.finished = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 134 */     if (!this.finished) {
/* 135 */       finish();
/*     */     }
/*     */     
/* 138 */     if (!this.closed) {
/* 139 */       this.buffer.close();
/* 140 */       this.out.close();
/* 141 */       this.closed = true;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRecordSize()
/*     */   {
/* 151 */     return this.buffer.getRecordSize();
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
/*     */   public void putArchiveEntry(ArchiveEntry archiveEntry)
/*     */     throws IOException
/*     */   {
/* 168 */     if (this.finished) {
/* 169 */       throw new IOException("Stream has already been finished");
/*     */     }
/* 171 */     TarArchiveEntry entry = (TarArchiveEntry)archiveEntry;
/* 172 */     if (entry.getName().length() >= 100)
/*     */     {
/* 174 */       if (this.longFileMode == 2)
/*     */       {
/*     */ 
/* 177 */         TarArchiveEntry longLinkEntry = new TarArchiveEntry("././@LongLink", (byte)76);
/*     */         
/*     */ 
/* 180 */         byte[] nameBytes = ArchiveUtils.toAsciiBytes(entry.getName());
/* 181 */         longLinkEntry.setSize(nameBytes.length + 1);
/* 182 */         putArchiveEntry(longLinkEntry);
/* 183 */         write(nameBytes);
/* 184 */         write(0);
/* 185 */         closeArchiveEntry();
/* 186 */       } else if (this.longFileMode != 1) {
/* 187 */         throw new RuntimeException("file name '" + entry.getName() + "' is too long ( > " + 100 + " bytes)");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 193 */     entry.writeEntryHeader(this.recordBuf);
/* 194 */     this.buffer.writeRecord(this.recordBuf);
/*     */     
/* 196 */     this.currBytes = 0L;
/*     */     
/* 198 */     if (entry.isDirectory()) {
/* 199 */       this.currSize = 0L;
/*     */     } else {
/* 201 */       this.currSize = entry.getSize();
/*     */     }
/* 203 */     this.currName = entry.getName();
/* 204 */     this.haveUnclosedEntry = true;
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
/*     */   public void closeArchiveEntry()
/*     */     throws IOException
/*     */   {
/* 218 */     if (this.finished) {
/* 219 */       throw new IOException("Stream has already been finished");
/*     */     }
/* 221 */     if (!this.haveUnclosedEntry) {
/* 222 */       throw new IOException("No current entry to close");
/*     */     }
/* 224 */     if (this.assemLen > 0) {
/* 225 */       for (int i = this.assemLen; i < this.assemBuf.length; i++) {
/* 226 */         this.assemBuf[i] = 0;
/*     */       }
/*     */       
/* 229 */       this.buffer.writeRecord(this.assemBuf);
/*     */       
/* 231 */       this.currBytes += this.assemLen;
/* 232 */       this.assemLen = 0;
/*     */     }
/*     */     
/* 235 */     if (this.currBytes < this.currSize) {
/* 236 */       throw new IOException("entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 241 */     this.haveUnclosedEntry = false;
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
/*     */   public void write(byte[] wBuf, int wOffset, int numToWrite)
/*     */     throws IOException
/*     */   {
/* 259 */     if (this.currBytes + numToWrite > this.currSize) {
/* 260 */       throw new IOException("request to write '" + numToWrite + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
/*     */     }
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
/* 274 */     if (this.assemLen > 0) {
/* 275 */       if (this.assemLen + numToWrite >= this.recordBuf.length) {
/* 276 */         int aLen = this.recordBuf.length - this.assemLen;
/*     */         
/* 278 */         System.arraycopy(this.assemBuf, 0, this.recordBuf, 0, this.assemLen);
/*     */         
/* 280 */         System.arraycopy(wBuf, wOffset, this.recordBuf, this.assemLen, aLen);
/*     */         
/* 282 */         this.buffer.writeRecord(this.recordBuf);
/*     */         
/* 284 */         this.currBytes += this.recordBuf.length;
/* 285 */         wOffset += aLen;
/* 286 */         numToWrite -= aLen;
/* 287 */         this.assemLen = 0;
/*     */       } else {
/* 289 */         System.arraycopy(wBuf, wOffset, this.assemBuf, this.assemLen, numToWrite);
/*     */         
/*     */ 
/* 292 */         wOffset += numToWrite;
/* 293 */         this.assemLen += numToWrite;
/* 294 */         numToWrite = 0;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 303 */     while (numToWrite > 0) {
/* 304 */       if (numToWrite < this.recordBuf.length) {
/* 305 */         System.arraycopy(wBuf, wOffset, this.assemBuf, this.assemLen, numToWrite);
/*     */         
/*     */ 
/* 308 */         this.assemLen += numToWrite;
/*     */         
/* 310 */         break;
/*     */       }
/*     */       
/* 313 */       this.buffer.writeRecord(wBuf, wOffset);
/*     */       
/* 315 */       int num = this.recordBuf.length;
/*     */       
/* 317 */       this.currBytes += num;
/* 318 */       numToWrite -= num;
/* 319 */       wOffset += num;
/*     */     }
/*     */     
/* 322 */     count(numToWrite);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void writeEOFRecord()
/*     */     throws IOException
/*     */   {
/* 330 */     for (int i = 0; i < this.recordBuf.length; i++) {
/* 331 */       this.recordBuf[i] = 0;
/*     */     }
/*     */     
/* 334 */     this.buffer.writeRecord(this.recordBuf);
/*     */   }
/*     */   
/*     */   public void flush() throws IOException
/*     */   {
/* 339 */     this.out.flush();
/*     */   }
/*     */   
/*     */   public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException
/*     */   {
/* 344 */     if (this.finished) {
/* 345 */       throw new IOException("Stream has already been finished");
/*     */     }
/* 347 */     return new TarArchiveEntry(inputFile, entryName);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/tar/TarArchiveOutputStream.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */