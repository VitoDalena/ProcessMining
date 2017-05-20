/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.hash.MurmurHash3;
/*    */ import java.util.concurrent.Callable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ final class HashContainerUtils
/*    */ {
/*    */   static final int MAX_CAPACITY = 1073741824;
/*    */   static final int MIN_CAPACITY = 4;
/*    */   static final int DEFAULT_CAPACITY = 16;
/*    */   static final float DEFAULT_LOAD_FACTOR = 0.75F;
/* 33 */   static final int[] PERTURBATIONS = new Callable() {
/*    */     public int[] call() {
/* 35 */       int[] result = new int[32];
/* 36 */       for (int i = 0; i < result.length; i++) {
/* 37 */         result[i] = MurmurHash3.hash(17 + i);
/*    */       }
/* 39 */       return result;
/*    */     }
/* 33 */   }.call();
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   static int roundCapacity(int requestedCapacity)
/*    */   {
/* 48 */     if (requestedCapacity > 1073741824) {
/* 49 */       return 1073741824;
/*    */     }
/* 51 */     return Math.max(4, BitUtil.nextHighestPowerOfTwo(requestedCapacity));
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   static int nextCapacity(int current)
/*    */   {
/* 60 */     assert ((current > 0) && (Long.bitCount(current) == 1)) : "Capacity must be a power of two.";
/*    */     
/* 62 */     if (current < 2)
/*    */     {
/* 64 */       current = 2;
/*    */     }
/*    */     
/* 67 */     current <<= 1;
/* 68 */     if (current < 0)
/*    */     {
/* 70 */       throw new RuntimeException("Maximum capacity exceeded.");
/*    */     }
/*    */     
/* 73 */     return current;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/HashContainerUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */