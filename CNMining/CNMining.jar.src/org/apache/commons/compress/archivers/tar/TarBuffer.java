/*     */ package org.apache.commons.compress.archivers.tar;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Arrays;
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
/*     */ class TarBuffer
/*     */ {
/*     */   public static final int DEFAULT_RCDSIZE = 512;
/*     */   public static final int DEFAULT_BLKSIZE = 10240;
/*     */   private InputStream inStream;
/*     */   private OutputStream outStream;
/*     */   private byte[] blockBuffer;
/*     */   private int currBlkIdx;
/*     */   private int currRecIdx;
/*     */   private int blockSize;
/*     */   private int recordSize;
/*     */   private int recsPerBlock;
/*     */   
/*     */   public TarBuffer(InputStream inStream)
/*     */   {
/*  61 */     this(inStream, 10240);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarBuffer(InputStream inStream, int blockSize)
/*     */   {
/*  70 */     this(inStream, blockSize, 512);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarBuffer(InputStream inStream, int blockSize, int recordSize)
/*     */   {
/*  80 */     this.inStream = inStream;
/*  81 */     this.outStream = null;
/*     */     
/*  83 */     initialize(blockSize, recordSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarBuffer(OutputStream outStream)
/*     */   {
/*  91 */     this(outStream, 10240);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarBuffer(OutputStream outStream, int blockSize)
/*     */   {
/* 100 */     this(outStream, blockSize, 512);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TarBuffer(OutputStream outStream, int blockSize, int recordSize)
/*     */   {
/* 110 */     this.inStream = null;
/* 111 */     this.outStream = outStream;
/*     */     
/* 113 */     initialize(blockSize, recordSize);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void initialize(int blockSize, int recordSize)
/*     */   {
/* 120 */     this.blockSize = blockSize;
/* 121 */     this.recordSize = recordSize;
/* 122 */     this.recsPerBlock = (this.blockSize / this.recordSize);
/* 123 */     this.blockBuffer = new byte[this.blockSize];
/*     */     
/* 125 */     if (this.inStream != null) {
/* 126 */       this.currBlkIdx = -1;
/* 127 */       this.currRecIdx = this.recsPerBlock;
/*     */     } else {
/* 129 */       this.currBlkIdx = 0;
/* 130 */       this.currRecIdx = 0;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getBlockSize()
/*     */   {
/* 139 */     return this.blockSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRecordSize()
/*     */   {
/* 147 */     return this.recordSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEOFRecord(byte[] record)
/*     */   {
/* 158 */     int i = 0; for (int sz = getRecordSize(); i < sz; i++) {
/* 159 */       if (record[i] != 0) {
/* 160 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 164 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void skipRecord()
/*     */     throws IOException
/*     */   {
/* 172 */     if (this.inStream == null) {
/* 173 */       throw new IOException("reading (via skip) from an output buffer");
/*     */     }
/*     */     
/* 176 */     if ((this.currRecIdx >= this.recsPerBlock) && 
/* 177 */       (!readBlock())) {
/* 178 */       return;
/*     */     }
/*     */     
/*     */ 
/* 182 */     this.currRecIdx += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public byte[] readRecord()
/*     */     throws IOException
/*     */   {
/* 192 */     if (this.inStream == null) {
/* 193 */       if (this.outStream == null) {
/* 194 */         throw new IOException("input buffer is closed");
/*     */       }
/* 196 */       throw new IOException("reading from an output buffer");
/*     */     }
/*     */     
/* 199 */     if ((this.currRecIdx >= this.recsPerBlock) && 
/* 200 */       (!readBlock())) {
/* 201 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 205 */     byte[] result = new byte[this.recordSize];
/*     */     
/* 207 */     System.arraycopy(this.blockBuffer, this.currRecIdx * this.recordSize, result, 0, this.recordSize);
/*     */     
/*     */ 
/*     */ 
/* 211 */     this.currRecIdx += 1;
/*     */     
/* 213 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   private boolean readBlock()
/*     */     throws IOException
/*     */   {
/* 220 */     if (this.inStream == null) {
/* 221 */       throw new IOException("reading from an output buffer");
/*     */     }
/*     */     
/* 224 */     this.currRecIdx = 0;
/*     */     
/* 226 */     int offset = 0;
/* 227 */     int bytesNeeded = this.blockSize;
/*     */     
/* 229 */     while (bytesNeeded > 0) {
/* 230 */       long numBytes = this.inStream.read(this.blockBuffer, offset, bytesNeeded);
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
/* 246 */       if (numBytes == -1L) {
/* 247 */         if (offset == 0)
/*     */         {
/*     */ 
/*     */ 
/* 251 */           return false;
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 259 */         Arrays.fill(this.blockBuffer, offset, offset + bytesNeeded, (byte)0);
/*     */         
/* 261 */         break;
/*     */       }
/*     */       
/* 264 */       offset = (int)(offset + numBytes);
/* 265 */       bytesNeeded = (int)(bytesNeeded - numBytes);
/*     */       
/* 267 */       if (numBytes == this.blockSize) {}
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 272 */     this.currBlkIdx += 1;
/*     */     
/* 274 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCurrentBlockNum()
/*     */   {
/* 283 */     return this.currBlkIdx;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getCurrentRecordNum()
/*     */   {
/* 293 */     return this.currRecIdx - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeRecord(byte[] record)
/*     */     throws IOException
/*     */   {
/* 303 */     if (this.outStream == null) {
/* 304 */       if (this.inStream == null) {
/* 305 */         throw new IOException("Output buffer is closed");
/*     */       }
/* 307 */       throw new IOException("writing to an input buffer");
/*     */     }
/*     */     
/* 310 */     if (record.length != this.recordSize) {
/* 311 */       throw new IOException("record to write has length '" + record.length + "' which is not the record size of '" + this.recordSize + "'");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 317 */     if (this.currRecIdx >= this.recsPerBlock) {
/* 318 */       writeBlock();
/*     */     }
/*     */     
/* 321 */     System.arraycopy(record, 0, this.blockBuffer, this.currRecIdx * this.recordSize, this.recordSize);
/*     */     
/*     */ 
/*     */ 
/* 325 */     this.currRecIdx += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void writeRecord(byte[] buf, int offset)
/*     */     throws IOException
/*     */   {
/* 338 */     if (this.outStream == null) {
/* 339 */       if (this.inStream == null) {
/* 340 */         throw new IOException("Output buffer is closed");
/*     */       }
/* 342 */       throw new IOException("writing to an input buffer");
/*     */     }
/*     */     
/* 345 */     if (offset + this.recordSize > buf.length) {
/* 346 */       throw new IOException("record has length '" + buf.length + "' with offset '" + offset + "' which is less than the record size of '" + this.recordSize + "'");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 352 */     if (this.currRecIdx >= this.recsPerBlock) {
/* 353 */       writeBlock();
/*     */     }
/*     */     
/* 356 */     System.arraycopy(buf, offset, this.blockBuffer, this.currRecIdx * this.recordSize, this.recordSize);
/*     */     
/*     */ 
/*     */ 
/* 360 */     this.currRecIdx += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   private void writeBlock()
/*     */     throws IOException
/*     */   {
/* 367 */     if (this.outStream == null) {
/* 368 */       throw new IOException("writing to an input buffer");
/*     */     }
/*     */     
/* 371 */     this.outStream.write(this.blockBuffer, 0, this.blockSize);
/* 372 */     this.outStream.flush();
/*     */     
/* 374 */     this.currRecIdx = 0;
/* 375 */     this.currBlkIdx += 1;
/*     */   }
/*     */   
/*     */ 
/*     */   private void flushBlock()
/*     */     throws IOException
/*     */   {
/* 382 */     if (this.outStream == null) {
/* 383 */       throw new IOException("writing to an input buffer");
/*     */     }
/*     */     
/* 386 */     if (this.currRecIdx > 0) {
/* 387 */       writeBlock();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void close()
/*     */     throws IOException
/*     */   {
/* 397 */     if (this.outStream != null) {
/* 398 */       flushBlock();
/*     */       
/* 400 */       if ((this.outStream != System.out) && (this.outStream != System.err))
/*     */       {
/* 402 */         this.outStream.close();
/*     */         
/* 404 */         this.outStream = null;
/*     */       }
/* 406 */     } else if ((this.inStream != null) && 
/* 407 */       (this.inStream != System.in)) {
/* 408 */       this.inStream.close();
/*     */       
/* 410 */       this.inStream = null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/compress/archivers/tar/TarBuffer.class
 * Java compiler version: 4 (48.0)
 * JD-Core Version:       0.7.1
 */