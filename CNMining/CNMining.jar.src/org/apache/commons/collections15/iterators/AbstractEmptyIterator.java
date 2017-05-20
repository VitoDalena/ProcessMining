/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.NoSuchElementException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ abstract class AbstractEmptyIterator<E>
/*    */ {
/*    */   public boolean hasNext()
/*    */   {
/* 38 */     return false;
/*    */   }
/*    */   
/*    */   public E next() {
/* 42 */     throw new NoSuchElementException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 46 */     return false;
/*    */   }
/*    */   
/*    */   public E previous() {
/* 50 */     throw new NoSuchElementException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 54 */     return 0;
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 58 */     return -1;
/*    */   }
/*    */   
/*    */   public void add(E obj) {
/* 62 */     throw new UnsupportedOperationException("add() not supported for empty Iterator");
/*    */   }
/*    */   
/*    */   public void set(E obj) {
/* 66 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 70 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public E getKey() {
/* 74 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public E getValue() {
/* 78 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public E setValue(E value) {
/* 82 */     throw new IllegalStateException("Iterator contains no elements");
/*    */   }
/*    */   
/*    */   public void reset() {}
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/AbstractEmptyIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */