/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class FloatHolder
/*    */ {
/*    */   public float value;
/*    */   
/*    */ 
/*    */ 
/*    */   public FloatHolder() {}
/*    */   
/*    */ 
/*    */   public FloatHolder(float value)
/*    */   {
/* 16 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 21 */     return Float.floatToIntBits(this.value);
/*    */   }
/*    */   
/*    */   public boolean equals(Object other)
/*    */   {
/* 26 */     return ((other instanceof FloatHolder)) && (Float.floatToIntBits(this.value) == Float.floatToIntBits(((FloatHolder)other).value));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/FloatHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */