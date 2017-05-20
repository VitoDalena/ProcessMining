/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class XorShiftRandom
/*    */   extends Random
/*    */ {
/*    */   private long x;
/*    */   
/*    */   public XorShiftRandom()
/*    */   {
/* 20 */     this(System.nanoTime());
/*    */   }
/*    */   
/*    */   public XorShiftRandom(long seed)
/*    */   {
/* 25 */     setSeed(seed);
/*    */   }
/*    */   
/*    */ 
/*    */   public long nextLong()
/*    */   {
/* 31 */     this.x ^= this.x << 21;
/* 32 */     this.x ^= this.x >>> 35;
/* 33 */     this.x ^= this.x << 4;
/* 34 */     return this.x;
/*    */   }
/*    */   
/*    */ 
/*    */   protected int next(int bits)
/*    */   {
/* 40 */     return (int)(nextLong() & (1L << bits) - 1L);
/*    */   }
/*    */   
/*    */ 
/*    */   public void setSeed(long seed)
/*    */   {
/* 46 */     this.x = seed;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/XorShiftRandom.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */