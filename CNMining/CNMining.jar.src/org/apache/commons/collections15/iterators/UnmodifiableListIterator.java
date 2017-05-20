/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.ListIterator;
/*    */ import org.apache.commons.collections15.Unmodifiable;
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
/*    */ public final class UnmodifiableListIterator<E>
/*    */   implements ListIterator<E>, Unmodifiable
/*    */ {
/*    */   private ListIterator<E> iterator;
/*    */   
/*    */   public static <E> ListIterator<E> decorate(ListIterator<E> iterator)
/*    */   {
/* 45 */     if (iterator == null) {
/* 46 */       throw new IllegalArgumentException("ListIterator must not be null");
/*    */     }
/* 48 */     if ((iterator instanceof Unmodifiable)) {
/* 49 */       return iterator;
/*    */     }
/* 51 */     return new UnmodifiableListIterator(iterator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private UnmodifiableListIterator(ListIterator<E> iterator)
/*    */   {
/* 62 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 67 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public E next() {
/* 71 */     return (E)this.iterator.next();
/*    */   }
/*    */   
/*    */   public int nextIndex() {
/* 75 */     return this.iterator.nextIndex();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 79 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public E previous() {
/* 83 */     return (E)this.iterator.previous();
/*    */   }
/*    */   
/*    */   public int previousIndex() {
/* 87 */     return this.iterator.previousIndex();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 91 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */   
/*    */   public void set(E obj) {
/* 95 */     throw new UnsupportedOperationException("set() is not supported");
/*    */   }
/*    */   
/*    */   public void add(E obj) {
/* 99 */     throw new UnsupportedOperationException("add() is not supported");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/UnmodifiableListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */