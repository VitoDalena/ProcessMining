/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.LongCursor;
/*    */ import com.carrotsearch.hppc.predicates.LongPredicate;
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
/*    */ abstract class AbstractLongCollection
/*    */   implements LongCollection
/*    */ {
/*    */   public int removeAll(LongLookupContainer c)
/*    */   {
/* 22 */     final LongContainer c2 = c;
/* 23 */     removeAll(new LongPredicate()
/*    */     {
/*    */       public boolean apply(long k)
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
/*    */   public int retainAll(LongLookupContainer c)
/*    */   {
/* 40 */     final LongContainer c2 = c;
/* 41 */     removeAll(new LongPredicate()
/*    */     {
/*    */       public boolean apply(long k)
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
/*    */   public int retainAll(final LongPredicate predicate)
/*    */   {
/* 57 */     removeAll(new LongPredicate()
/*    */     {
/*    */       public boolean apply(long value)
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
/*    */   public long[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     long[] array = new long[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (LongCursor c : this)
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractLongCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */