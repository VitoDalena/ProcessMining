/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class DoubleHolder
/*    */ {
/*    */   public double value;
/*    */   
/*    */ 
/*    */ 
/*    */   public DoubleHolder() {}
/*    */   
/*    */ 
/*    */   public DoubleHolder(double value)
/*    */   {
/* 16 */     this.value = value;
/*    */   }
/*    */   
/*    */   public int hashCode()
/*    */   {
/* 21 */     long bits = Double.doubleToLongBits(this.value);
/* 22 */     return (int)(bits ^ bits >>> 32);
/*    */   }
/*    */   
/*    */   public boolean equals(Object other)
/*    */   {
/* 27 */     return ((other instanceof DoubleHolder)) && (Double.doubleToLongBits(this.value) == Double.doubleToLongBits(((DoubleHolder)other).value));
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/DoubleHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */