/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.DoubleCursor;
/*    */ import com.carrotsearch.hppc.predicates.DoublePredicate;
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
/*    */ abstract class AbstractDoubleCollection
/*    */   implements DoubleCollection
/*    */ {
/*    */   public int removeAll(DoubleLookupContainer c)
/*    */   {
/* 22 */     final DoubleContainer c2 = c;
/* 23 */     removeAll(new DoublePredicate()
/*    */     {
/*    */       public boolean apply(double k)
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
/*    */   public int retainAll(DoubleLookupContainer c)
/*    */   {
/* 40 */     final DoubleContainer c2 = c;
/* 41 */     removeAll(new DoublePredicate()
/*    */     {
/*    */       public boolean apply(double k)
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
/*    */   public int retainAll(final DoublePredicate predicate)
/*    */   {
/* 57 */     removeAll(new DoublePredicate()
/*    */     {
/*    */       public boolean apply(double value)
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
/*    */   public double[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     double[] array = new double[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (DoubleCursor c : this)
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractDoubleCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */