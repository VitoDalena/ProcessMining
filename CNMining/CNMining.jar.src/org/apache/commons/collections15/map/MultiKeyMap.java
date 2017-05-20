/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.IterableMap;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.keyvalue.MultiKey;
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
/*     */ public class MultiKeyMap<K, V>
/*     */   implements IterableMap<MultiKey<K>, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1788199231038721040L;
/*     */   protected final AbstractHashedMap<MultiKey<K>, V> map;
/*     */   
/*     */   public static <K, V> MultiKeyMap<K, V> decorate(AbstractHashedMap<MultiKey<K>, V> map)
/*     */   {
/*  95 */     if (map == null) {
/*  96 */       throw new IllegalArgumentException("Map must not be null");
/*     */     }
/*  98 */     if (map.size() > 0) {
/*  99 */       throw new IllegalArgumentException("Map must be empty");
/*     */     }
/* 101 */     return new MultiKeyMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiKeyMap()
/*     */   {
/* 110 */     this.map = new HashedMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected MultiKeyMap(AbstractHashedMap<MultiKey<K>, V> map)
/*     */   {
/* 123 */     this.map = map;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V get(K... keys)
/*     */   {
/* 134 */     int hashCode = hash(keys);
/* 135 */     AbstractHashedMap.HashEntry<MultiKey<K>, V> entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 136 */     while (entry != null) {
/* 137 */       if ((entry.hashCode == hashCode) && (isEqualKey(entry, keys))) {
/* 138 */         return (V)entry.getValue();
/*     */       }
/* 140 */       entry = entry.next;
/*     */     }
/* 142 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(K... keys)
/*     */   {
/* 152 */     int hashCode = hash(keys);
/* 153 */     AbstractHashedMap.HashEntry<MultiKey<K>, V> entry = this.map.data[this.map.hashIndex(hashCode, this.map.data.length)];
/* 154 */     while (entry != null) {
/* 155 */       if ((entry.hashCode == hashCode) && (isEqualKey(entry, keys))) {
/* 156 */         return true;
/*     */       }
/* 158 */       entry = entry.next;
/*     */     }
/* 160 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V put(K key1, K key2, V value)
/*     */   {
/* 167 */     return (V)putMultiKey(value, new Object[] { key1, key2 });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V put(K key1, K key2, K key3, V value)
/*     */   {
/* 174 */     return (V)putMultiKey(value, new Object[] { key1, key2, key3 });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V put(K key1, K key2, K key3, K key4, V value)
/*     */   {
/* 181 */     return (V)putMultiKey(value, new Object[] { key1, key2, key3, key4 });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public V put(K key1, K key2, K key3, K key4, K key5, V value)
/*     */   {
/* 188 */     return (V)putMultiKey(value, new Object[] { key1, key2, key3, key4, key5 });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V putMultiKey(V value, K... keys)
/*     */   {
/* 199 */     int hashCode = hash(keys);
/* 200 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 201 */     AbstractHashedMap.HashEntry<MultiKey<K>, V> entry = this.map.data[index];
/* 202 */     while (entry != null) {
/* 203 */       if ((entry.hashCode == hashCode) && (isEqualKey(entry, keys))) {
/* 204 */         V oldValue = entry.getValue();
/* 205 */         this.map.updateEntry(entry, value);
/* 206 */         return oldValue;
/*     */       }
/* 208 */       entry = entry.next;
/*     */     }
/*     */     
/* 211 */     this.map.addMapping(index, hashCode, new MultiKey(keys), value);
/* 212 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object remove(K... keys)
/*     */   {
/* 222 */     int hashCode = hash(keys);
/* 223 */     int index = this.map.hashIndex(hashCode, this.map.data.length);
/* 224 */     AbstractHashedMap.HashEntry<MultiKey<K>, V> entry = this.map.data[index];
/* 225 */     AbstractHashedMap.HashEntry<MultiKey<K>, V> previous = null;
/* 226 */     while (entry != null) {
/* 227 */       if ((entry.hashCode == hashCode) && (isEqualKey(entry, keys))) {
/* 228 */         Object oldValue = entry.getValue();
/* 229 */         this.map.removeMapping(entry, index, previous);
/* 230 */         return oldValue;
/*     */       }
/* 232 */       previous = entry;
/* 233 */       entry = entry.next;
/*     */     }
/* 235 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int hash(K... keys)
/*     */   {
/* 245 */     int h = 0;
/* 246 */     for (int i = 0; i < keys.length; i++) {
/* 247 */       K key = keys[i];
/* 248 */       if (key != null) {
/* 249 */         h ^= key.hashCode();
/*     */       }
/*     */     }
/* 252 */     h += (h << 9 ^ 0xFFFFFFFF);
/* 253 */     h ^= h >>> 14;
/* 254 */     h += (h << 4);
/* 255 */     h ^= h >>> 10;
/* 256 */     return h;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isEqualKey(AbstractHashedMap.HashEntry<MultiKey<K>, V> entry, K... keys)
/*     */   {
/* 267 */     MultiKey multi = (MultiKey)entry.getKey();
/* 268 */     if (multi.size() != keys.length) {
/* 269 */       return false;
/*     */     }
/* 271 */     for (int i = 0; i < keys.length; i++) {
/* 272 */       K key = keys[i];
/* 273 */       if (key == null ? multi.getKey(i) != null : !key.equals(multi.getKey(i))) {
/* 274 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 278 */     return true;
/*     */   }
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
/*     */   public boolean removeAll(Object key1)
/*     */   {
/* 292 */     boolean modified = false;
/* 293 */     MapIterator it = mapIterator();
/* 294 */     while (it.hasNext()) {
/* 295 */       MultiKey multi = (MultiKey)it.next();
/* 296 */       if ((multi.size() >= 1) && (key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0)))) {
/* 297 */         it.remove();
/* 298 */         modified = true;
/*     */       }
/*     */     }
/* 301 */     return modified;
/*     */   }
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
/*     */   public boolean removeAll(Object key1, Object key2)
/*     */   {
/* 315 */     boolean modified = false;
/* 316 */     MapIterator it = mapIterator();
/* 317 */     while (it.hasNext()) {
/* 318 */       MultiKey multi = (MultiKey)it.next();
/* 319 */       if ((multi.size() >= 2) && (key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0))) && (key2 == null ? multi.getKey(1) == null : key2.equals(multi.getKey(1)))) {
/* 320 */         it.remove();
/* 321 */         modified = true;
/*     */       }
/*     */     }
/* 324 */     return modified;
/*     */   }
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
/*     */   public boolean removeAll(Object key1, Object key2, Object key3)
/*     */   {
/* 339 */     boolean modified = false;
/* 340 */     MapIterator it = mapIterator();
/* 341 */     while (it.hasNext()) {
/* 342 */       MultiKey multi = (MultiKey)it.next();
/* 343 */       if ((multi.size() >= 3) && (key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0))) && (key2 == null ? multi.getKey(1) == null : key2.equals(multi.getKey(1))) && (key3 == null ? multi.getKey(2) == null : key3.equals(multi.getKey(2)))) {
/* 344 */         it.remove();
/* 345 */         modified = true;
/*     */       }
/*     */     }
/* 348 */     return modified;
/*     */   }
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
/*     */   public boolean removeAll(Object key1, Object key2, Object key3, Object key4)
/*     */   {
/* 364 */     boolean modified = false;
/* 365 */     MapIterator it = mapIterator();
/* 366 */     while (it.hasNext()) {
/* 367 */       MultiKey multi = (MultiKey)it.next();
/* 368 */       if ((multi.size() >= 4) && (key1 == null ? multi.getKey(0) == null : key1.equals(multi.getKey(0))) && (key2 == null ? multi.getKey(1) == null : key2.equals(multi.getKey(1))) && (key3 == null ? multi.getKey(2) == null : key3.equals(multi.getKey(2))) && (key4 == null ? multi.getKey(3) == null : key4.equals(multi.getKey(3)))) {
/* 369 */         it.remove();
/* 370 */         modified = true;
/*     */       }
/*     */     }
/* 373 */     return modified;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void checkKey(Object key)
/*     */   {
/* 383 */     if (key == null) {
/* 384 */       throw new NullPointerException("Key must not be null");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 394 */     return new MultiKeyMap((AbstractHashedMap)this.map.clone());
/*     */   }
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
/*     */   public V put(MultiKey<K> key, V value)
/*     */   {
/* 408 */     checkKey(key);
/* 409 */     return (V)this.map.put(key, value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void putAll(Map<? extends MultiKey<K>, ? extends V> mapToCopy)
/*     */   {
/* 421 */     for (Iterator it = mapToCopy.keySet().iterator(); it.hasNext();) {
/* 422 */       Object key = it.next();
/* 423 */       checkKey(key);
/*     */     }
/* 425 */     this.map.putAll(mapToCopy);
/*     */   }
/*     */   
/*     */   public MapIterator mapIterator()
/*     */   {
/* 430 */     return this.map.mapIterator();
/*     */   }
/*     */   
/*     */   public int size() {
/* 434 */     return this.map.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 438 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 442 */     return this.map.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 446 */     return this.map.containsValue(value);
/*     */   }
/*     */   
/*     */   public V get(Object key) {
/* 450 */     return (V)this.map.get(key);
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 454 */     return (V)this.map.remove(key);
/*     */   }
/*     */   
/*     */   public void clear() {
/* 458 */     this.map.clear();
/*     */   }
/*     */   
/*     */   public Set keySet() {
/* 462 */     return this.map.keySet();
/*     */   }
/*     */   
/*     */   public Collection values() {
/* 466 */     return this.map.values();
/*     */   }
/*     */   
/*     */   public Set entrySet() {
/* 470 */     return this.map.entrySet();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 474 */     if (obj == this) {
/* 475 */       return true;
/*     */     }
/* 477 */     return this.map.equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 481 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 485 */     return this.map.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/MultiKeyMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */