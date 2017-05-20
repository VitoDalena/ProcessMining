/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.Iterator;
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
/*    */ public class AbstractIteratorDecorator<E>
/*    */   implements Iterator<E>
/*    */ {
/*    */   protected final Iterator<E> iterator;
/*    */   
/*    */   public AbstractIteratorDecorator(Iterator<E> iterator)
/*    */   {
/* 47 */     if (iterator == null) {
/* 48 */       throw new IllegalArgumentException("Iterator must not be null");
/*    */     }
/* 50 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected Iterator<E> getIterator()
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
/*    */   public void remove() {
/* 72 */     this.iterator.remove();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/AbstractIteratorDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */