/*    */ package org.apache.commons.collections15.iterators;
/*    */ 
/*    */ import org.apache.commons.collections15.MapIterator;
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
/*    */ public class AbstractMapIteratorDecorator<K, V>
/*    */   implements MapIterator<K, V>
/*    */ {
/*    */   protected final MapIterator<K, V> iterator;
/*    */   
/*    */   public AbstractMapIteratorDecorator(MapIterator<K, V> iterator)
/*    */   {
/* 46 */     if (iterator == null) {
/* 47 */       throw new IllegalArgumentException("MapIterator must not be null");
/*    */     }
/* 49 */     this.iterator = iterator;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected MapIterator<K, V> getMapIterator()
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
/*    */   public void remove() {
/* 71 */     this.iterator.remove();
/*    */   }
/*    */   
/*    */   public K getKey() {
/* 75 */     return (K)this.iterator.getKey();
/*    */   }
/*    */   
/*    */   public V getValue() {
/* 79 */     return (V)this.iterator.getValue();
/*    */   }
/*    */   
/*    */   public V setValue(V obj) {
/* 83 */     return (V)this.iterator.setValue(obj);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/iterators/AbstractMapIteratorDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */