/*    */ package com.carrotsearch.hppc;
/*    */ 
/*    */ import com.carrotsearch.hppc.cursors.CharCursor;
/*    */ import com.carrotsearch.hppc.predicates.CharPredicate;
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
/*    */ abstract class AbstractCharCollection
/*    */   implements CharCollection
/*    */ {
/*    */   public int removeAll(CharLookupContainer c)
/*    */   {
/* 22 */     final CharContainer c2 = c;
/* 23 */     removeAll(new CharPredicate()
/*    */     {
/*    */       public boolean apply(char k)
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
/*    */   public int retainAll(CharLookupContainer c)
/*    */   {
/* 40 */     final CharContainer c2 = c;
/* 41 */     removeAll(new CharPredicate()
/*    */     {
/*    */       public boolean apply(char k)
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
/*    */   public int retainAll(final CharPredicate predicate)
/*    */   {
/* 57 */     removeAll(new CharPredicate()
/*    */     {
/*    */       public boolean apply(char value)
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
/*    */   public char[] toArray()
/*    */   {
/* 73 */     int size = size();
/* 74 */     char[] array = new char[size];
/*    */     
/*    */ 
/*    */ 
/* 78 */     int i = 0;
/* 79 */     for (CharCursor c : this)
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/AbstractCharCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */