/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class AbstractListIteratorDecorator<E>
/*    */   implements ListIterator<E>
/*    */ {
/*    */   protected final ListIterator<E> iterator;
/*    */   
/*    */   public AbstractListIteratorDecorator(ListIterator<E> iterator)
/*    */   {
/* 47 */     if (iterator == null) {
/* 48 */       throw new IllegalArgumentException("ListIterator must not be null");
/*    */     }
/* 50 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected ListIterator<E> getListIterator()
/*    */   {
/* 59 */     return this.iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 64 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public E next() {
/* 68 */     return (E)this.iterator.next();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 72 */     return this.iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 76 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public E previous() {
/* 80 */     return (E)this.iterator.previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 84 */     return this.iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 88 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public void set(E obj) {
/* 92 */     this.iterator.set(obj);
/*    */   }
/*    */   
/*    */   public void add(E obj) {
/* 96 */     this.iterator.add(obj);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/AbstractListIteratorDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */