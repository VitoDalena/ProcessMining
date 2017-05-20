/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class LongHolder
/*    */ {
/*    */   public long value;
/*    */   
/*    */ 
/*    */ 
/*    */   public LongHolder() {}
/*    */   
/*    */ 
/*    */   public LongHolder(long value)
/*    */   {
/* 16 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 21 */     return (int)(this.value ^ this.value >>> 32);
/*    */   }
/*    */   
/*    */   public boolean equals(Object other)
/*    */   {
/* 26 */     return ((other instanceof LongHolder)) && (this.value == ((LongHolder)other).value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/LongHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */