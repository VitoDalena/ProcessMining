/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.ByteCursor;
/*    */ import com.carrotsearch.hppc.predicates.BytePredicate;
/*    */ import java.util.Arrays;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class AbstractByteCollection
/*    */   implements ByteCollection
/*    */ {
/*    */   public int removeAll(ByteLookupContainer c)
/*    */   {
/* 22 */     final ByteContainer c2 = c;
/* 23 */     removeAll(new BytePredicate()
/*    */     {
/*    */       public boolean apply(byte k)
/*    */       {
/* 27 */         return c2.contains(k);
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int retainAll(ByteLookupContainer c)
/*    */   {
/* 40 */     final ByteContainer c2 = c;
/* 41 */     removeAll(new BytePredicate()
/*    */     {
/*    */       public boolean apply(byte k)
/*    */       {
/* 45 */         return !c2.contains(k);
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public int retainAll(final BytePredicate predicate)
/*    */   {
/* 57 */     removeAll(new BytePredicate()
/*    */     {
/*    */       public boolean apply(byte value)
/*    */       {
/* 61 */         return !predicate.apply(value);
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public byte[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     byte[] array = new byte[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (ByteCursor c : this)
/*    */     {
/* 81 */       array[(i++)] = c.value;
/*    */     }
/* 83 */     return array;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   public String toString()
/*    */   {
/* 94 */     return Arrays.toString(toArray());
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractByteCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */