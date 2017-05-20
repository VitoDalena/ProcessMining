/*     */ package org.deckfour.xes.nikefs2;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NikeFS2Block
/*     */ {
/*     */   private final NikeFS2BlockProvider provider;
/*     */   private final int blockNumber;
/*     */   
/*     */   public NikeFS2Block(NikeFS2BlockProvider provider, int blockNumber)
/*     */   {
/*  69 */     this.provider = provider;
/*  70 */     this.blockNumber = blockNumber;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/*  77 */     return this.provider.blockSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int blockNumber()
/*     */   {
/*  85 */     return this.blockNumber;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void close()
/*     */   {
/*  92 */     this.provider.freeBlock(this);
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
/*     */   public synchronized int read(int blockOffset, byte[] buffer, int offset, int length)
/*     */     throws IOException
/*     */   {
/* 106 */     return this.provider.read(this.blockNumber, blockOffset, buffer, offset, length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int read(int blockOffset, byte[] buffer)
/*     */     throws IOException
/*     */   {
/* 117 */     return this.provider.read(this.blockNumber, blockOffset, buffer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized int read(int blockOffset)
/*     */     throws IOException
/*     */   {
/* 128 */     return this.provider.read(this.blockNumber, blockOffset);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(int blockOffset, byte[] buffer, int offset, int length)
/*     */     throws IOException
/*     */   {
/* 141 */     this.provider.write(this.blockNumber, blockOffset, buffer, offset, length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(int blockOffset, byte[] buffer)
/*     */     throws IOException
/*     */   {
/* 152 */     this.provider.write(this.blockNumber, blockOffset, buffer);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void write(int blockOffset, int value)
/*     */     throws IOException
/*     */   {
/* 163 */     this.provider.write(this.blockNumber, blockOffset, value);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/nikefs2/NikeFS2Block.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */