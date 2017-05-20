/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class IntHolder
/*    */ {
/*    */   public int value;
/*    */   
/*    */ 
/*    */ 
/*    */   public IntHolder() {}
/*    */   
/*    */ 
/*    */   public IntHolder(int value)
/*    */   {
/* 16 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 21 */     return this.value;
/*    */   }
/*    */   
/*    */   public boolean equals(Object other)
/*    */   {
/* 26 */     return ((other instanceof IntHolder)) && (this.value == ((IntHolder)other).value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/IntHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */