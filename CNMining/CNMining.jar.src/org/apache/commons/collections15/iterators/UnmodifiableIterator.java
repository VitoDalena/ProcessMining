/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import java.util.Iterator;
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
/*    */ 
/*    */ 
/*    */ public final class UnmodifiableIterator<E>
/*    */   implements Iterator<E>, Unmodifiable
/*    */ {
/*    */   private Iterator<E> iterator;
/*    */   
/*    */   public static <E> Iterator<E> decorate(Iterator<E> iterator)
/*    */   {
/* 47 */     if (iterator == null) {
/* 48 */       throw new IllegalArgumentException("Iterator must not be null");
/*    */     }
/* 50 */     if ((iterator instanceof Unmodifiable)) {
/* 51 */       return iterator;
/*    */     }
/* 53 */     return new UnmodifiableIterator(iterator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private UnmodifiableIterator(Iterator<E> iterator)
/*    */   {
/* 64 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 69 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public E next() {
/* 73 */     return (E)this.iterator.next();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 77 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/UnmodifiableIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */