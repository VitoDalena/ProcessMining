/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.IntCursor;
/*    */ import com.carrotsearch.hppc.predicates.IntPredicate;
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
/*    */ abstract class AbstractIntCollection
/*    */   implements IntCollection
/*    */ {
/*    */   public int removeAll(IntLookupContainer c)
/*    */   {
/* 22 */     final IntContainer c2 = c;
/* 23 */     removeAll(new IntPredicate()
/*    */     {
/*    */       public boolean apply(int k)
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
/*    */   public int retainAll(IntLookupContainer c)
/*    */   {
/* 40 */     final IntContainer c2 = c;
/* 41 */     removeAll(new IntPredicate()
/*    */     {
/*    */       public boolean apply(int k)
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
/*    */   public int retainAll(final IntPredicate predicate)
/*    */   {
/* 57 */     removeAll(new IntPredicate()
/*    */     {
/*    */       public boolean apply(int value)
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
/*    */   public int[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     int[] array = new int[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (IntCursor c : this)
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractIntCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */