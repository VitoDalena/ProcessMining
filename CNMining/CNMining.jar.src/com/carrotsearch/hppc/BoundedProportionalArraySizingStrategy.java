/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class BoundedProportionalArraySizingStrategy
/*    */   implements ArraySizingStrategy
/*    */ {
/*    */   public static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final int DEFAULT_MIN_GROW_COUNT = 10;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final int DEFAULT_MAX_GROW_COUNT = Integer.MAX_VALUE;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static final float DEFAULT_GROW_RATIO = 1.5F;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public final int minGrowCount;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public final int maxGrowCount;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public final float growRatio;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public BoundedProportionalArraySizingStrategy()
/*    */   {
/* 49 */     this(10, Integer.MAX_VALUE, 1.5F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public BoundedProportionalArraySizingStrategy(int minGrow, int maxGrow, float ratio)
/*    */   {
/* 57 */     assert (minGrow >= 1) : "Min grow must be >= 1.";
/* 58 */     assert (maxGrow >= minGrow) : "Max grow must be >= min grow.";
/* 59 */     assert (ratio >= 1.0F) : ("Growth ratio must be >= 1 (was " + ratio + ").");
/*    */     
/* 61 */     this.minGrowCount = minGrow;
/* 62 */     this.maxGrowCount = maxGrow;
/* 63 */     this.growRatio = (ratio - 1.0F);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int grow(int currentBufferLength, int elementsCount, int expectedAdditions)
/*    */   {
/* 71 */     long growBy = ((float)currentBufferLength * this.growRatio);
/* 72 */     growBy = Math.max(growBy, this.minGrowCount);
/* 73 */     growBy = Math.min(growBy, this.maxGrowCount);
/* 74 */     long growTo = Math.min(2147483647L, growBy + currentBufferLength);
/*    */     
/* 76 */     long newSize = Math.max(elementsCount + expectedAdditions, growTo);
/*    */     
/* 78 */     if (newSize > 2147483647L) {
/* 79 */       throw new AssertionError("Cannot resize beyond 2147483647 (" + (elementsCount + expectedAdditions) + ")");
/*    */     }
/*    */     
/*    */ 
/*    */ 
/* 84 */     return (int)newSize;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public int round(int capacity)
/*    */   {
/* 92 */     assert (capacity >= 0) : "Capacity must be a positive number.";
/* 93 */     return capacity;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/BoundedProportionalArraySizingStrategy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */