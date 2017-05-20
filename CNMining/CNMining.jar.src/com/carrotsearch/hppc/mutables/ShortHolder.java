/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ShortHolder
/*    */ {
/*    */   public short value;
/*    */   
/*    */ 
/*    */ 
/*    */   public ShortHolder() {}
/*    */   
/*    */ 
/*    */   public ShortHolder(short value)
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
/* 26 */     return ((other instanceof ShortHolder)) && (this.value == ((ShortHolder)other).value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/ShortHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */