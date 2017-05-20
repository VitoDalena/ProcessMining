/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.RandomAccessFile;
/*     */ import java.nio.MappedByteBuffer;
/*     */ import java.util.BitSet;
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
/*     */ public class NikeFS2BlockProvider
/*     */ {
/*     */   protected final boolean mapped;
/*     */   protected final File file;
/*     */   protected final RandomAccessFile rafile;
/*     */   protected final int size;
/*     */   protected final int blockSize;
/*     */   protected final int numberOfBlocks;
/*     */   protected final BitSet blockAllocationMap;
/*     */   
/*     */   public NikeFS2BlockProvider(File storage, int size, int blockSize, boolean mapped)
/*     */     throws IOException
/*     */   {
/* 110 */     synchronized (this)
/*     */     {
/* 112 */       this.mapped = mapped;
/* 113 */       this.size = size;
/* 114 */       this.blockSize = blockSize;
/*     */       
/* 116 */       if (!storage.exists()) {
/* 117 */         storage.createNewFile();
/*     */       }
/*     */       
/* 120 */       this.file = storage;
/* 121 */       if (this.mapped) {
/* 122 */         this.rafile = new RandomAccessFile(this.file, "rw");
/*     */       } else {
/* 124 */         this.rafile = null;
/*     */       }
/*     */       
/* 127 */       this.numberOfBlocks = (size / blockSize);
/*     */       
/* 129 */       this.blockAllocationMap = new BitSet(this.numberOfBlocks);
/*     */       
/* 131 */       this.blockAllocationMap.set(0, this.numberOfBlocks);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public File getFile()
/*     */   {
/* 141 */     return this.file;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 150 */     return this.size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int numberOfBlocks()
/*     */   {
/* 159 */     return this.numberOfBlocks;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int numberOfFreeBlocks()
/*     */   {
/* 170 */     return this.blockAllocationMap.cardinality();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int blockSize()
/*     */   {
/* 179 */     return this.blockSize;
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
/*     */   public synchronized NikeFS2Block allocateBlock()
/*     */   {
/* 192 */     int freeBlockIndex = this.blockAllocationMap.nextSetBit(0);
/* 193 */     if (freeBlockIndex < 0)
/*     */     {
/* 195 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 199 */     this.blockAllocationMap.set(freeBlockIndex, false);
/* 200 */     return new NikeFS2Block(this, freeBlockIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void freeBlock(NikeFS2Block block)
/*     */   {
/* 211 */     this.blockAllocationMap.set(block.blockNumber(), true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getBlockOffset(int blockNumber)
/*     */   {
/* 222 */     return blockNumber * this.blockSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int read(int blockNumber, int blockOffset, byte[] buffer)
/*     */     throws IOException
/*     */   {
/* 235 */     return read(blockNumber, blockOffset, buffer, 0, buffer.length);
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
/*     */   public synchronized int read(int blockNumber, int blockOffset, byte[] buffer, int bufferOffset, int length)
/*     */     throws IOException
/*     */   {
/* 250 */     long pointer = getBlockOffset(blockNumber) + blockOffset;
/* 251 */     int readable = this.blockSize - blockOffset;
/* 252 */     int readLength = length;
/* 253 */     if (readable < length) {
/* 254 */       readLength = readable;
/*     */     }
/* 256 */     if (this.mapped == true) {
/* 257 */       MappedByteBuffer map = NikeFS2FileAccessMonitor.instance().requestMap(this);
/* 258 */       map.position((int)pointer);
/* 259 */       map.get(buffer, bufferOffset, readLength);
/* 260 */       return readLength;
/*     */     }
/* 262 */     this.rafile.seek(pointer);
/* 263 */     return this.rafile.read(buffer, bufferOffset, readLength);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int read(int blockNumber, int blockOffset)
/*     */     throws IOException
/*     */   {
/* 276 */     long pointer = getBlockOffset(blockNumber) + blockOffset;
/* 277 */     if (this.mapped == true) {
/* 278 */       MappedByteBuffer map = NikeFS2FileAccessMonitor.instance().requestMap(this);
/* 279 */       map.position((int)pointer);
/* 280 */       int result = map.get();
/* 281 */       return result + 128;
/*     */     }
/* 283 */     this.rafile.seek(pointer);
/* 284 */     return this.rafile.read();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(int blockNumber, int blockOffset, byte[] buffer)
/*     */     throws IOException
/*     */   {
/* 297 */     write(blockNumber, blockOffset, buffer, 0, buffer.length);
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
/*     */   public synchronized void write(int blockNumber, int blockOffset, byte[] buffer, int bufferOffset, int length)
/*     */     throws IOException
/*     */   {
/* 311 */     long pointer = getBlockOffset(blockNumber) + blockOffset;
/* 312 */     int writable = this.blockSize - blockOffset;
/* 313 */     int writeLength = length;
/* 314 */     if (writable < length) {
/* 315 */       writeLength = writable;
/*     */     }
/* 317 */     if (this.mapped == true) {
/* 318 */       MappedByteBuffer map = NikeFS2FileAccessMonitor.instance().requestMap(this);
/* 319 */       map.position((int)pointer);
/* 320 */       map.put(buffer, bufferOffset, writeLength);
/*     */     } else {
/* 322 */       this.rafile.seek(pointer);
/* 323 */       this.rafile.write(buffer, bufferOffset, writeLength);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(int blockNumber, int blockOffset, int value)
/*     */     throws IOException
/*     */   {
/* 336 */     long pointer = getBlockOffset(blockNumber) + blockOffset;
/* 337 */     if (this.mapped == true) {
/* 338 */       MappedByteBuffer map = NikeFS2FileAccessMonitor.instance().requestMap(this);
/* 339 */       map.position((int)pointer);
/* 340 */       map.put((byte)(value - 128));
/*     */     } else {
/* 342 */       this.rafile.seek(pointer);
/* 343 */       this.rafile.write(value);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2BlockProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */