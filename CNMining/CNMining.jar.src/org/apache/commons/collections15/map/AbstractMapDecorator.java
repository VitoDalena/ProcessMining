/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
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
/*     */ public abstract class AbstractMapDecorator<K, V>
/*     */   implements Map<K, V>
/*     */ {
/*     */   protected transient Map<K, V> map;
/*     */   
/*     */   protected AbstractMapDecorator() {}
/*     */   
/*     */   public AbstractMapDecorator(Map<K, V> map)
/*     */   {
/*  64 */     if (map == null) {
/*  65 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  67 */     this.map = map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Map<K, V> getMap()
/*     */   {
/*  76 */     return this.map;
/*     */   }
/*     */   
/*     */   public void clear()
/*     */   {
/*  81 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/*  85 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/*  89 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/*  93 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/*  97 */     return (V)this.map.get(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 101 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 105 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public V put(K key, V value) {
/* 109 */     return (V)this.map.put(key, value);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> mapToCopy) {
/* 113 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 117 */     return (V)this.map.remove(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 121 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 125 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 129 */     if (object == this) {
/* 130 */       return true;
/*     */     }
/* 132 */     return this.map.equals(object);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 136 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 140 */     return this.map.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractMapDecorator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */