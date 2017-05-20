/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class BitUtil
/*     */ {
/*     */   public static long pop_array(long[] arr, int wordOffset, int numWords)
/*     */   {
/*  41 */     long popCount = 0L;
/*  42 */     int i = wordOffset; for (int end = wordOffset + numWords; i < end; i++) {
/*  43 */       popCount += Long.bitCount(arr[i]);
/*     */     }
/*  45 */     return popCount;
/*     */   }
/*     */   
/*     */ 
/*     */   public static long pop_intersect(long[] arr1, long[] arr2, int wordOffset, int numWords)
/*     */   {
/*  51 */     long popCount = 0L;
/*  52 */     int i = wordOffset; for (int end = wordOffset + numWords; i < end; i++) {
/*  53 */       popCount += Long.bitCount(arr1[i] & arr2[i]);
/*     */     }
/*  55 */     return popCount;
/*     */   }
/*     */   
/*     */ 
/*     */   public static long pop_union(long[] arr1, long[] arr2, int wordOffset, int numWords)
/*     */   {
/*  61 */     long popCount = 0L;
/*  62 */     int i = wordOffset; for (int end = wordOffset + numWords; i < end; i++) {
/*  63 */       popCount += Long.bitCount(arr1[i] | arr2[i]);
/*     */     }
/*  65 */     return popCount;
/*     */   }
/*     */   
/*     */ 
/*     */   public static long pop_andnot(long[] arr1, long[] arr2, int wordOffset, int numWords)
/*     */   {
/*  71 */     long popCount = 0L;
/*  72 */     int i = wordOffset; for (int end = wordOffset + numWords; i < end; i++) {
/*  73 */       popCount += Long.bitCount(arr1[i] & (arr2[i] ^ 0xFFFFFFFFFFFFFFFF));
/*     */     }
/*  75 */     return popCount;
/*     */   }
/*     */   
/*     */ 
/*     */   public static long pop_xor(long[] arr1, long[] arr2, int wordOffset, int numWords)
/*     */   {
/*  81 */     long popCount = 0L;
/*  82 */     int i = wordOffset; for (int end = wordOffset + numWords; i < end; i++) {
/*  83 */       popCount += Long.bitCount(arr1[i] ^ arr2[i]);
/*     */     }
/*  85 */     return popCount;
/*     */   }
/*     */   
/*     */   public static int nextHighestPowerOfTwo(int v)
/*     */   {
/*  90 */     v--;
/*  91 */     v |= v >> 1;
/*  92 */     v |= v >> 2;
/*  93 */     v |= v >> 4;
/*  94 */     v |= v >> 8;
/*  95 */     v |= v >> 16;
/*  96 */     v++;
/*  97 */     return v;
/*     */   }
/*     */   
/*     */   public static long nextHighestPowerOfTwo(long v)
/*     */   {
/* 102 */     v -= 1L;
/* 103 */     v |= v >> 1;
/* 104 */     v |= v >> 2;
/* 105 */     v |= v >> 4;
/* 106 */     v |= v >> 8;
/* 107 */     v |= v >> 16;
/* 108 */     v |= v >> 32;
/* 109 */     v += 1L;
/* 110 */     return v;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/BitUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */