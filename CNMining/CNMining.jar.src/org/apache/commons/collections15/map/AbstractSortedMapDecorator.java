/*    */ package org.apache.commons.collections15.map;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSortedMapDecorator<K, V>
/*    */   extends AbstractMapDecorator<K, V>
/*    */   implements SortedMap<K, V>
/*    */ {
/*    */   protected AbstractSortedMapDecorator() {}
/*    */   
/*    */   public AbstractSortedMapDecorator(SortedMap<K, V> map)
/*    */   {
/* 56 */     super(map);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SortedMap<K, V> getSortedMap()
/*    */   {
/* 65 */     return (SortedMap)this.map;
/*    */   }
/*    */   
/*    */   public Comparator<? super K> comparator()
/*    */   {
/* 70 */     return getSortedMap().comparator();
/*    */   }
/*    */   
/*    */   public K firstKey() {
/* 74 */     return (K)getSortedMap().firstKey();
/*    */   }
/*    */   
/*    */   public SortedMap<K, V> headMap(K toKey) {
/* 78 */     return getSortedMap().headMap(toKey);
/*    */   }
/*    */   
/*    */   public K lastKey() {
/* 82 */     return (K)getSortedMap().lastKey();
/*    */   }
/*    */   
/*    */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 86 */     return getSortedMap().subMap(fromKey, toKey);
/*    */   }
/*    */   
/*    */   public SortedMap<K, V> tailMap(K fromKey) {
/* 90 */     return getSortedMap().tailMap(fromKey);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractSortedMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */