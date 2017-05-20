/*    */ package com.carrotsearch.hppc;
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
/*    */ final class Intrinsics
/*    */ {
/*    */   public static <T> T newKTypeArray(int arraySize)
/*    */   {
/* 27 */     return new Object[arraySize];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> T newVTypeArray(int arraySize)
/*    */   {
/* 39 */     return new Object[arraySize];
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> T defaultKTypeValue()
/*    */   {
/* 50 */     return (T)null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static <T> T defaultVTypeValue()
/*    */   {
/* 61 */     return (T)null;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean equalsKType(Object e1, Object e2)
/*    */   {
/* 72 */     return e1 == null ? false : e2 == null ? true : e1.equals(e2);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public static boolean equalsVType(Object e1, Object e2)
/*    */   {
/* 81 */     return e1 == null ? false : e2 == null ? true : e1.equals(e2);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/Intrinsics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */