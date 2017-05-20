/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.OrderedMapIterator;
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
/*    */ public class AbstractOrderedMapIteratorDecorator<K, V>
/*    */   implements OrderedMapIterator<K, V>
/*    */ {
/*    */   protected final OrderedMapIterator<K, V> iterator;
/*    */   
/*    */   public AbstractOrderedMapIteratorDecorator(OrderedMapIterator<K, V> iterator)
/*    */   {
/* 46 */     if (iterator == null) {
/* 47 */       throw new IllegalArgumentException("OrderedMapIterator must not be null");
/*    */     }
/* 49 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected OrderedMapIterator<K, V> getOrderedMapIterator()
/*    */   {
/* 58 */     return this.iterator;
/*    */   }
/*    */   
/*    */   public boolean hasNext()
/*    */   {
/* 63 */     return this.iterator.hasNext();
/*    */   }
/*    */   
/*    */   public K next() {
/* 67 */     return (K)this.iterator.next();
/*    */   }
/*    */   
/*    */   public boolean hasPrevious() {
/* 71 */     return this.iterator.hasPrevious();
/*    */   }
/*    */   
/*    */   public K previous() {
/* 75 */     return (K)this.iterator.previous();
/*    */   }
/*    */   
/*    */   public void remove() {
/* 79 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 83 */     return (K)this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 87 */     return (V)this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public V setValue(V obj) {
/* 91 */     return (V)this.iterator.setValue(obj);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/AbstractOrderedMapIteratorDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */