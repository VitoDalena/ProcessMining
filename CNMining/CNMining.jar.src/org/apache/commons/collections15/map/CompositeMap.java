/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.CollectionUtils;
/*     */ import org.apache.commons.collections15.collection.CompositeCollection;
/*     */ import org.apache.commons.collections15.set.CompositeSet;
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
/*     */ public class CompositeMap<K, V>
/*     */   implements Map<K, V>
/*     */ {
/*     */   private Map[] composite;
/*     */   private MapMutator<K, V> mutator;
/*     */   
/*     */   public CompositeMap()
/*     */   {
/*  55 */     this(new Map[0], null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeMap(Map<? extends K, ? extends V> one, Map<? extends K, ? extends V> two)
/*     */   {
/*  66 */     this(new Map[] { one, two }, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeMap(Map<? extends K, ? extends V> one, Map<? extends K, ? extends V> two, MapMutator<K, V> mutator)
/*     */   {
/*  77 */     this(new Map[] { one, two }, mutator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeMap(Map[] composite)
/*     */   {
/*  88 */     this(composite, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompositeMap(Map[] composite, MapMutator<K, V> mutator)
/*     */   {
/*  99 */     this.mutator = mutator;
/* 100 */     this.composite = new Map[0];
/* 101 */     for (int i = composite.length - 1; i >= 0; i--) {
/* 102 */       addComposited(composite[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMutator(MapMutator<K, V> mutator)
/*     */   {
/* 113 */     this.mutator = mutator;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void addComposited(Map<? extends K, ? extends V> map)
/*     */     throws IllegalArgumentException
/*     */   {
/* 124 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 125 */       Collection<K> intersect = CollectionUtils.intersection(this.composite[i].keySet(), map.keySet());
/* 126 */       if (intersect.size() != 0) {
/* 127 */         if (this.mutator == null) {
/* 128 */           throw new IllegalArgumentException("Key collision adding Map to CompositeMap");
/*     */         }
/* 130 */         this.mutator.resolveCollision(this, this.composite[i], map, intersect);
/*     */       }
/*     */     }
/*     */     
/* 134 */     Map[] temp = new Map[this.composite.length + 1];
/* 135 */     System.arraycopy(this.composite, 0, temp, 0, this.composite.length);
/* 136 */     temp[(temp.length - 1)] = map;
/* 137 */     this.composite = temp;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized Map<? extends K, ? extends V> removeComposited(Map<? extends K, ? extends V> map)
/*     */   {
/* 147 */     int size = this.composite.length;
/* 148 */     for (int i = 0; i < size; i++) {
/* 149 */       if (this.composite[i].equals(map)) {
/* 150 */         Map[] temp = new Map[size - 1];
/* 151 */         System.arraycopy(this.composite, 0, temp, 0, i);
/* 152 */         System.arraycopy(this.composite, i + 1, temp, i, size - i - 1);
/* 153 */         this.composite = temp;
/* 154 */         return map;
/*     */       }
/*     */     }
/* 157 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 167 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 168 */       this.composite[i].clear();
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(Object key)
/*     */   {
/* 188 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 189 */       if (this.composite[i].containsKey(key)) {
/* 190 */         return true;
/*     */       }
/*     */     }
/* 193 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object value)
/*     */   {
/* 213 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 214 */       if (this.composite[i].containsValue(value)) {
/* 215 */         return true;
/*     */       }
/*     */     }
/* 218 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set entrySet()
/*     */   {
/* 239 */     CompositeSet<Map.Entry<K, V>> entries = new CompositeSet();
/* 240 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 241 */       entries.addComposited(this.composite[i].entrySet());
/*     */     }
/* 243 */     return entries;
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
/*     */   public V get(Object key)
/*     */   {
/* 269 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 270 */       if (this.composite[i].containsKey(key)) {
/* 271 */         return (V)this.composite[i].get(key);
/*     */       }
/*     */     }
/* 274 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 283 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 284 */       if (!this.composite[i].isEmpty()) {
/* 285 */         return false;
/*     */       }
/*     */     }
/* 288 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set keySet()
/*     */   {
/* 307 */     CompositeSet<K> keys = new CompositeSet();
/* 308 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 309 */       keys.addComposited(this.composite[i].keySet());
/*     */     }
/* 311 */     return keys;
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
/*     */   public V put(K key, V value)
/*     */   {
/* 339 */     if (this.mutator == null) {
/* 340 */       throw new UnsupportedOperationException("No mutator specified");
/*     */     }
/* 342 */     return (V)this.mutator.put(this, this.composite, key, value);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends V> map)
/*     */   {
/* 365 */     if (this.mutator == null) {
/* 366 */       throw new UnsupportedOperationException("No mutator specified");
/*     */     }
/* 368 */     this.mutator.putAll(this, this.composite, map);
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
/*     */   public V remove(Object key)
/*     */   {
/* 396 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 397 */       if (this.composite[i].containsKey(key)) {
/* 398 */         return (V)this.composite[i].remove(key);
/*     */       }
/*     */     }
/* 401 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 412 */     int size = 0;
/* 413 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 414 */       size += this.composite[i].size();
/*     */     }
/* 416 */     return size;
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
/*     */ 
/*     */   public Collection<V> values()
/*     */   {
/* 433 */     CompositeCollection<V> keys = new CompositeCollection();
/* 434 */     for (int i = this.composite.length - 1; i >= 0; i--) {
/* 435 */       keys.addComposited(this.composite[i].values());
/*     */     }
/* 437 */     return keys;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 447 */     if ((obj instanceof Map)) {
/* 448 */       Map map = (Map)obj;
/* 449 */       return entrySet().equals(map.entrySet());
/*     */     }
/* 451 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 458 */     int code = 0;
/* 459 */     for (Iterator i = entrySet().iterator(); i.hasNext();) {
/* 460 */       code += i.next().hashCode();
/*     */     }
/* 462 */     return code;
/*     */   }
/*     */   
/*     */   public static abstract interface MapMutator<K, V>
/*     */   {
/*     */     public abstract void resolveCollision(CompositeMap<K, V> paramCompositeMap, Map<? extends K, ? extends V> paramMap1, Map<? extends K, ? extends V> paramMap2, Collection<K> paramCollection);
/*     */     
/*     */     public abstract V put(CompositeMap<K, V> paramCompositeMap, Map[] paramArrayOfMap, K paramK, V paramV);
/*     */     
/*     */     public abstract void putAll(CompositeMap<K, V> paramCompositeMap, Map[] paramArrayOfMap, Map<? extends K, ? extends V> paramMap);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/CompositeMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */