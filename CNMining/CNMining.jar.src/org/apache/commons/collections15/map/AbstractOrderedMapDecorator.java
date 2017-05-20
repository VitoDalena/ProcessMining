/*    */ package org.apache.commons.collections15.map;
/*    */ 
/*    */ import org.apache.commons.collections15.MapIterator;
/*    */ import org.apache.commons.collections15.OrderedMap;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractOrderedMapDecorator<K, V>
/*    */   extends AbstractMapDecorator<K, V>
/*    */   implements OrderedMap<K, V>
/*    */ {
/*    */   protected AbstractOrderedMapDecorator() {}
/*    */   
/*    */   public AbstractOrderedMapDecorator(OrderedMap<K, V> map)
/*    */   {
/* 57 */     super(map);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected OrderedMap<K, V> getOrderedMap()
/*    */   {
/* 66 */     return (OrderedMap)this.map;
/*    */   }
/*    */   
/*    */   public K firstKey()
/*    */   {
/* 71 */     return (K)getOrderedMap().firstKey();
/*    */   }
/*    */   
/*    */   public K lastKey() {
/* 75 */     return (K)getOrderedMap().lastKey();
/*    */   }
/*    */   
/*    */   public K nextKey(K key) {
/* 79 */     return (K)getOrderedMap().nextKey(key);
/*    */   }
/*    */   
/*    */   public K previousKey(K key) {
/* 83 */     return (K)getOrderedMap().previousKey(key);
/*    */   }
/*    */   
/*    */   public MapIterator<K, V> mapIterator() {
/* 87 */     return getOrderedMap().mapIterator();
/*    */   }
/*    */   
/*    */   public OrderedMapIterator<K, V> orderedMapIterator() {
/* 91 */     return getOrderedMap().orderedMapIterator();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractOrderedMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */