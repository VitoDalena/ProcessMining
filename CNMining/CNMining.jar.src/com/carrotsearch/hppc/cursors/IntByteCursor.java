/*    */ package com.carrotsearch.hppc.cursors;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class IntByteCursor
/*    */ {
/*    */   public int index;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int key;
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public byte value;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/cursors/IntByteCursor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */