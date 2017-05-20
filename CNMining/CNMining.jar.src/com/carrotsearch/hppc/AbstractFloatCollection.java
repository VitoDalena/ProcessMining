/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.FloatCursor;
/*    */ import com.carrotsearch.hppc.predicates.FloatPredicate;
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
/*    */ abstract class AbstractFloatCollection
/*    */   implements FloatCollection
/*    */ {
/*    */   public int removeAll(FloatLookupContainer c)
/*    */   {
/* 22 */     final FloatContainer c2 = c;
/* 23 */     removeAll(new FloatPredicate()
/*    */     {
/*    */       public boolean apply(float k)
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
/*    */   public int retainAll(FloatLookupContainer c)
/*    */   {
/* 40 */     final FloatContainer c2 = c;
/* 41 */     removeAll(new FloatPredicate()
/*    */     {
/*    */       public boolean apply(float k)
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
/*    */   public int retainAll(final FloatPredicate predicate)
/*    */   {
/* 57 */     removeAll(new FloatPredicate()
/*    */     {
/*    */       public boolean apply(float value)
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
/*    */   public float[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     float[] array = new float[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (FloatCursor c : this)
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractFloatCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */