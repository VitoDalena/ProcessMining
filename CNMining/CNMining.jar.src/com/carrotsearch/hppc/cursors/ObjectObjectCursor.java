/*    */ package com.carrotsearch.hppc.cursors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ObjectObjectCursor<KType, VType>
/*    */ {
/*    */   public int index;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public KType key;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public VType value;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 30 */     return "[cursor, index: " + this.index + ", key: " + this.key + ", value: " + this.value + "]";
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/cursors/ObjectObjectCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */