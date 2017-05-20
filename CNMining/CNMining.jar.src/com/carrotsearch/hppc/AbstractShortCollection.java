/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.ShortCursor;
/*    */ import com.carrotsearch.hppc.predicates.ShortPredicate;
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
/*    */ abstract class AbstractShortCollection
/*    */   implements ShortCollection
/*    */ {
/*    */   public int removeAll(ShortLookupContainer c)
/*    */   {
/* 22 */     final ShortContainer c2 = c;
/* 23 */     removeAll(new ShortPredicate()
/*    */     {
/*    */       public boolean apply(short k)
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
/*    */   public int retainAll(ShortLookupContainer c)
/*    */   {
/* 40 */     final ShortContainer c2 = c;
/* 41 */     removeAll(new ShortPredicate()
/*    */     {
/*    */       public boolean apply(short k)
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
/*    */   public int retainAll(final ShortPredicate predicate)
/*    */   {
/* 57 */     removeAll(new ShortPredicate()
/*    */     {
/*    */       public boolean apply(short value)
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
/*    */   public short[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     short[] array = new short[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (ShortCursor c : this)
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractShortCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */