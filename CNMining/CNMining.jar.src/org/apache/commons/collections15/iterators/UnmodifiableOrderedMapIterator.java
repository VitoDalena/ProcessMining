/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.OrderedMapIterator;
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
/*    */ public final class UnmodifiableOrderedMapIterator<K, V>
/*    */   implements OrderedMapIterator<K, V>, Unmodifiable
/*    */ {
/*    */   private OrderedMapIterator<K, V> iterator;
/*    */   
/*    */   public static <K, V> OrderedMapIterator<K, V> decorate(OrderedMapIterator<K, V> iterator)
/*    */   {
/* 44 */     if (iterator == null) {
/* 45 */       throw new IllegalArgumentException("OrderedMapIterator must not be null");
/*    */     }
/* 47 */     if ((iterator instanceof Unmodifiable)) {
/* 48 */       return iterator;
/*    */     }
/* 50 */     return new UnmodifiableOrderedMapIterator(iterator);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   private UnmodifiableOrderedMapIterator(OrderedMapIterator<K, V> iterator)
/*    */   {
/* 61 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 66 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public K next() {
/* 70 */     return (K)this.iterator.next();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 74 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public K previous() {
/* 78 */     return (K)this.iterator.previous();
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 82 */     return (K)this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 86 */     return (V)this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public V setValue(V value) {
/* 90 */     throw new UnsupportedOperationException("setValue() is not supported");
/*    */   }
/*    */   
/*    */   public void remove() {
/* 94 */     throw new UnsupportedOperationException("remove() is not supported");
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/UnmodifiableOrderedMapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */