/*    */ package org.apache.commons.collections15.bidimap;
/*    */ 
/*    */ import org.apache.commons.collections15.OrderedBidiMap;
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
/*    */ 
/*    */ public abstract class AbstractOrderedBidiMapDecorator<K, V>
/*    */   extends AbstractBidiMapDecorator<K, V>
/*    */   implements OrderedBidiMap<K, V>
/*    */ {
/*    */   protected AbstractOrderedBidiMapDecorator(OrderedBidiMap<K, V> map)
/*    */   {
/* 47 */     super(map);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected OrderedBidiMap<K, V> getOrderedBidiMap()
/*    */   {
/* 56 */     return (OrderedBidiMap)this.map;
/*    */   }
/*    */   
/*    */   public OrderedMapIterator<K, V> orderedMapIterator()
/*    */   {
/* 61 */     return getOrderedBidiMap().orderedMapIterator();
/*    */   }
/*    */   
/*    */   public K firstKey() {
/* 65 */     return (K)getOrderedBidiMap().firstKey();
/*    */   }
/*    */   
/*    */   public K lastKey() {
/* 69 */     return (K)getOrderedBidiMap().lastKey();
/*    */   }
/*    */   
/*    */   public K nextKey(K key) {
/* 73 */     return (K)getOrderedBidiMap().nextKey(key);
/*    */   }
/*    */   
/*    */   public K previousKey(K key) {
/* 77 */     return (K)getOrderedBidiMap().previousKey(key);
/*    */   }
/*    */   
/*    */   public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
/* 81 */     return getOrderedBidiMap().inverseOrderedBidiMap();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/AbstractOrderedBidiMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */