/*    */ package org.apache.commons.collections15.bidimap;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import java.util.SortedMap;
/*    */ import org.apache.commons.collections15.SortedBidiMap;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractSortedBidiMapDecorator<K, V>
/*    */   extends AbstractOrderedBidiMapDecorator<K, V>
/*    */   implements SortedBidiMap<K, V>
/*    */ {
/*    */   public AbstractSortedBidiMapDecorator(SortedBidiMap<K, V> map)
/*    */   {
/* 49 */     super(map);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   protected SortedBidiMap<K, V> getSortedBidiMap()
/*    */   {
/* 58 */     return (SortedBidiMap)this.map;
/*    */   }
/*    */   
/*    */   public SortedBidiMap<V, K> inverseSortedBidiMap()
/*    */   {
/* 63 */     return getSortedBidiMap().inverseSortedBidiMap();
/*    */   }
/*    */   
/*    */   public Comparator<? super K> comparator() {
/* 67 */     return getSortedBidiMap().comparator();
/*    */   }
/*    */   
/*    */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 71 */     return getSortedBidiMap().subMap(fromKey, toKey);
/*    */   }
/*    */   
/*    */   public SortedMap<K, V> headMap(K toKey) {
/* 75 */     return getSortedBidiMap().headMap(toKey);
/*    */   }
/*    */   
/*    */   public SortedMap<K, V> tailMap(K fromKey) {
/* 79 */     return getSortedBidiMap().tailMap(fromKey);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/AbstractSortedBidiMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */