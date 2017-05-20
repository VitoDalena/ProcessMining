/*    */ package com.carrotsearch.hppc.hash;
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
/*    */ public final class MurmurHash3
/*    */ {
/*    */   public static int hash(int k)
/*    */   {
/* 21 */     k ^= k >>> 16;
/* 22 */     k *= -2048144789;
/* 23 */     k ^= k >>> 13;
/* 24 */     k *= -1028477387;
/* 25 */     k ^= k >>> 16;
/* 26 */     return k;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public static long hash(long k)
/*    */   {
/* 34 */     k ^= k >>> 33;
/* 35 */     k *= -49064778989728563L;
/* 36 */     k ^= k >>> 33;
/* 37 */     k *= -4265267296055464877L;
/* 38 */     k ^= k >>> 33;
/*    */     
/* 40 */     return k;
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/hash/MurmurHash3.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */