/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class CharHolder
/*    */ {
/*    */   public char value;
/*    */   
/*    */ 
/*    */ 
/*    */   public CharHolder() {}
/*    */   
/*    */ 
/*    */   public CharHolder(char value)
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
/* 26 */     return ((other instanceof CharHolder)) && (this.value == ((CharHolder)other).value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/CharHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */