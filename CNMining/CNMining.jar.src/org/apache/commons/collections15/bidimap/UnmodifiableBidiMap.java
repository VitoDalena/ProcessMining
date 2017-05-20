/*     */ package org.apache.commons.collections15.bidimap;
/*     */ 
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.Unmodifiable;
/*     */ import org.apache.commons.collections15.iterators.UnmodifiableMapIterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class UnmodifiableBidiMap<K, V>
/*     */   extends AbstractBidiMapDecorator<K, V>
/*     */   implements Unmodifiable
/*     */ {
/*     */   private UnmodifiableBidiMap<V, K> inverse;
/*     */   
/*     */   public static <K, V> BidiMap<K, V> decorate(BidiMap<K, V> map)
/*     */   {
/*  53 */     if ((map instanceof Unmodifiable)) {
/*  54 */       return map;
/*     */     }
/*  56 */     return new UnmodifiableBidiMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private UnmodifiableBidiMap(BidiMap<K, V> map)
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
/* 108 */     MapIterator<K, V> it = getBidiMap().mapIterator();
/* 109 */     return UnmodifiableMapIterator.decorate(it);
/*     */   }
/*     */   
/*     */   public BidiMap<V, K> inverseBidiMap() {
/* 113 */     if (this.inverse == null) {
/* 114 */       this.inverse = new UnmodifiableBidiMap(getBidiMap().inverseBidiMap());
/* 115 */       this.inverse.inverse = this;
/*     */     }
/* 117 */     return this.inverse;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/UnmodifiableBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */