/*    */ package com.carrotsearch.hppc.cursors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ObjectDoubleCursor<KType>
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
/*    */   public double value;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/cursors/ObjectDoubleCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */