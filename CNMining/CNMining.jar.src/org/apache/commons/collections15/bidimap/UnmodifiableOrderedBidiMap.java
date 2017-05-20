/*     */ package org.apache.commons.collections15.bidimap;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.OrderedBidiMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableOrderedMapIterator;
/*     */ import org.apache.commons.collections15.map.UnmodifiableEntrySet;
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
/*     */ 
/*     */ public final class UnmodifiableOrderedBidiMap<K, V>
/*     */   extends AbstractOrderedBidiMapDecorator<K, V>
/*     */   implements Unmodifiable
/*     */ {
/*     */   private UnmodifiableOrderedBidiMap<V, K> inverse;
/*     */   
/*     */   public static <K, V> OrderedBidiMap<K, V> decorate(OrderedBidiMap<K, V> map)
/*     */   {
/*  51 */     if ((map instanceof Unmodifiable)) {
/*  52 */       return map;
/*     */     }
/*  54 */     return new UnmodifiableOrderedBidiMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableOrderedBidiMap(OrderedBidiMap<K, V> map)
/*     */   {
/*  65 */     super(map);
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/*  70 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/*  74 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/*  82 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/*  86 */     Set<Map.Entry<K, V>> set = super.entrySet();
/*  87 */     return UnmodifiableEntrySet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/*  91 */     Set<K> set = super.keySet();
/*  92 */     return UnmodifiableSet.decorate(set);
/*     */   }
/*     */   
/*     */   public Set<V> values() {
/*  96 */     Set<V> coll = super.values();
/*  97 */     return UnmodifiableSet.decorate(coll);
/*     */   }
/*     */   
/*     */   public K removeValue(Object value)
/*     */   {
/* 102 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */   public MapIterator<K, V> mapIterator() {
/* 106 */     return orderedMapIterator();
/*     */   }
/*     */   
/*     */   public BidiMap<V, K> inverseBidiMap() {
/* 110 */     return inverseOrderedBidiMap();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator<K, V> orderedMapIterator()
/*     */   {
/* 115 */     OrderedMapIterator<K, V> it = getOrderedBidiMap().orderedMapIterator();
/* 116 */     return UnmodifiableOrderedMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public OrderedBidiMap<V, K> inverseOrderedBidiMap() {
/* 120 */     if (this.inverse == null) {
/* 121 */       this.inverse = new UnmodifiableOrderedBidiMap(getOrderedBidiMap().inverseOrderedBidiMap());
/* 122 */       this.inverse.inverse = this;
/*     */     }
/* 124 */     return this.inverse;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/UnmodifiableOrderedBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */