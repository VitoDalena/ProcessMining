/*     */ package org.apache.commons.collections15.bidimap;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import java.util.SortedMap;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.OrderedBidiMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.SortedBidiMap;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections15.map.UnmodifiableEntrySet;
/*     */ import org.apache.commons.collections15.map.UnmodifiableSortedMap;
/*     */ import org.apache.commons.collections15.set.UnmodifiableSet;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableSortedBidiMap<K, V>
/*     */   extends AbstractSortedBidiMapDecorator<K, V>
/*     */   implements Unmodifiable
/*     */ {
/*     */   private UnmodifiableSortedBidiMap<V, K> inverse;
/*     */   
/*     */   public static <K, V> SortedBidiMap<K, V> decorate(SortedBidiMap<K, V> map)
/*     */   {
/*  53 */     if ((map instanceof Unmodifiable)) {
/*  54 */       return map;
/*     */     }
/*  56 */     return new UnmodifiableSortedBidiMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableSortedBidiMap(SortedBidiMap<K, V> map)
/*     */   {
/*  67 */     super(map);
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/*  72 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/*  76 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/*  80 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/*  84 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/*  88 */     Set<Map.Entry<K, V>> set = super.entrySet();
/*  89 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/*  93 */     Set<K> set = super.keySet();
/*  94 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<V> values() {
/*  98 */     Set<V> coll = super.values();
/*  99 */     return UnmodifiableSet.decorate(coll);
/*     */   }
/*     */   
/*     */   public K removeValue(Object value)
/*     */   {
/* 104 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator<K, V> mapIterator() {
/* 108 */     return orderedMapIterator();
/*     */   }
/*     */   
/*     */   public BidiMap<V, K> inverseBidiMap() {
/* 112 */     return inverseSortedBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator<K, V> orderedMapIterator()
/*     */   {
/* 117 */     OrderedMapIterator<K, V> it = getSortedBidiMap().orderedMapIterator();
/* 118 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
/* 122 */     return inverseSortedBidiMap();
/*     */   }
/*     */   
/*     */   public SortedBidiMap<V, K> inverseSortedBidiMap()
/*     */   {
/* 127 */     if (this.inverse == null) {
/* 128 */       this.inverse = new UnmodifiableSortedBidiMap(getSortedBidiMap().inverseSortedBidiMap());
/* 129 */       this.inverse.inverse = this;
/*     */     }
/* 131 */     return this.inverse;
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> subMap(K fromKey, K toKey) {
/* 135 */     SortedMap<K, V> sm = getSortedBidiMap().subMap(fromKey, toKey);
/* 136 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> headMap(K toKey) {
/* 140 */     SortedMap<K, V> sm = getSortedBidiMap().headMap(toKey);
/* 141 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */   
/*     */   public SortedMap<K, V> tailMap(K fromKey) {
/* 145 */     SortedMap<K, V> sm = getSortedBidiMap().tailMap(fromKey);
/* 146 */     return UnmodifiableSortedMap.decorate(sm);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/UnmodifiableSortedBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */