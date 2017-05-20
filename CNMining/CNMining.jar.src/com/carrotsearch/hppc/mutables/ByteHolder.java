/*    */ package com.carrotsearch.hppc.mutables;
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ByteHolder
/*    */ {
/*    */   public byte value;
/*    */   
/*    */ 
/*    */ 
/*    */   public ByteHolder() {}
/*    */   
/*    */ 
/*    */   public ByteHolder(byte value)
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
/* 26 */     return ((other instanceof ByteHolder)) && (this.value == ((ByteHolder)other).value);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/mutables/ByteHolder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */