/*     */ package org.apache.commons.collections15.multimap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.MultiMap;
/*     */ import org.apache.commons.collections15.iterators.EmptyIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiHashMap<K, V>
/*     */   implements MultiMap<K, V>, Serializable, Cloneable
/*     */ {
/*  63 */   private transient Collection values = null;
/*     */   
/*     */ 
/*     */   private static final long serialVersionUID = 1943563828307035349L;
/*     */   
/*     */ 
/*     */   private HashMap<K, Collection<V>> internalMap;
/*     */   
/*     */ 
/*     */   public MultiHashMap()
/*     */   {
/*  74 */     this.internalMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiHashMap(int initialCapacity)
/*     */   {
/*  83 */     this.internalMap = new HashMap(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public MultiHashMap(int initialCapacity, float loadFactor)
/*     */   {
/*  93 */     this.internalMap = new HashMap(initialCapacity, loadFactor);
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
/*     */   public MultiHashMap(Map<K, V> mapToCopy)
/*     */   {
/* 106 */     this.internalMap = new HashMap((int)(mapToCopy.size() * 1.4F));
/* 107 */     putAll(mapToCopy);
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
/*     */   public MultiHashMap(MultiMap<K, V> mapToCopy)
/*     */   {
/* 121 */     this.internalMap = new HashMap((int)(mapToCopy.size() * 1.4F));
/* 122 */     for (Iterator<Map.Entry<K, Collection<V>>> it = mapToCopy.entrySet().iterator(); it.hasNext();) {
/* 123 */       Map.Entry<K, Collection<V>> entry = (Map.Entry)it.next();
/* 124 */       Collection<V> coll = (Collection)entry.getValue();
/* 125 */       Collection<V> newColl = createCollection(coll);
/* 126 */       this.internalMap.put(entry.getKey(), newColl);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream s)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 139 */     s.defaultReadObject();
/*     */     
/*     */ 
/* 142 */     String version = "1.2";
/*     */     try {
/* 144 */       version = System.getProperty("java.version");
/*     */     }
/*     */     catch (SecurityException ex) {}
/*     */     
/*     */     Iterator<Map.Entry<K, Collection<V>>> iterator;
/* 149 */     if ((version.startsWith("1.2")) || (version.startsWith("1.3"))) {
/* 150 */       for (iterator = entrySet().iterator(); iterator.hasNext();) {
/* 151 */         Map.Entry<K, Collection<V>> entry = (Map.Entry)iterator.next();
/*     */         
/* 153 */         this.internalMap.put(entry.getKey(), entry.getValue());
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int totalSize()
/*     */   {
/* 166 */     int total = 0;
/* 167 */     Collection<Collection<V>> values = this.internalMap.values();
/* 168 */     for (Iterator<Collection<V>> it = values.iterator(); it.hasNext();) {
/* 169 */       Collection<V> coll = (Collection)it.next();
/* 170 */       total += coll.size();
/*     */     }
/* 172 */     return total;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> getCollection(Object key)
/*     */   {
/* 184 */     return (Collection)this.internalMap.get(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size(Object key)
/*     */   {
/* 195 */     Collection<V> coll = getCollection(key);
/* 196 */     if (coll == null) {
/* 197 */       return 0;
/*     */     }
/* 199 */     return coll.size();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<V> iterator(Object key)
/*     */   {
/* 210 */     Collection<V> coll = getCollection(key);
/* 211 */     if (coll == null) {
/* 212 */       return EmptyIterator.INSTANCE;
/*     */     }
/* 214 */     return coll.iterator();
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
/*     */   public V put(K key, V value)
/*     */   {
/* 230 */     Collection<V> coll = getCollection(key);
/* 231 */     if (coll == null) {
/* 232 */       coll = createCollection(null);
/* 233 */       this.internalMap.put(key, coll);
/*     */     }
/* 235 */     boolean results = coll.add(value);
/* 236 */     return results ? value : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean putAll(K key, Collection<? extends V> values)
/*     */   {
/* 248 */     if ((values == null) || (values.size() == 0)) {
/* 249 */       return false;
/*     */     }
/* 251 */     Collection<V> coll = getCollection(key);
/* 252 */     if (coll == null) {
/* 253 */       coll = createCollection(values);
/* 254 */       if (coll.size() == 0) {
/* 255 */         return false;
/*     */       }
/* 257 */       this.internalMap.put(key, coll);
/* 258 */       return true;
/*     */     }
/* 260 */     return coll.addAll(values);
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
/*     */   public boolean containsValue(Object value)
/*     */   {
/* 273 */     Set<Map.Entry<K, Collection<V>>> pairs = this.internalMap.entrySet();
/*     */     
/* 275 */     if (pairs == null) {
/* 276 */       return false;
/*     */     }
/* 278 */     Iterator<Map.Entry<K, Collection<V>>> pairsIterator = pairs.iterator();
/* 279 */     while (pairsIterator.hasNext()) {
/* 280 */       Map.Entry<K, Collection<V>> keyValuePair = (Map.Entry)pairsIterator.next();
/* 281 */       Collection<V> coll = (Collection)keyValuePair.getValue();
/* 282 */       if (coll.contains(value)) {
/* 283 */         return true;
/*     */       }
/*     */     }
/* 286 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object key, Object value)
/*     */   {
/* 297 */     Collection<V> coll = getCollection(key);
/* 298 */     if (coll == null) {
/* 299 */       return false;
/*     */     }
/* 301 */     return coll.contains(value);
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
/*     */   public V remove(Object key, Object item)
/*     */   {
/* 318 */     Collection valuesForKey = getCollection(key);
/* 319 */     if (valuesForKey == null) {
/* 320 */       return null;
/*     */     }
/* 322 */     valuesForKey.remove(item);
/*     */     
/*     */ 
/*     */ 
/* 326 */     if (valuesForKey.isEmpty()) {
/* 327 */       remove(key);
/*     */     }
/* 329 */     return (V)item;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 339 */     Set<Map.Entry<K, Collection<V>>> pairs = this.internalMap.entrySet();
/* 340 */     Iterator<Map.Entry<K, Collection<V>>> pairsIterator = pairs.iterator();
/* 341 */     while (pairsIterator.hasNext()) {
/* 342 */       Map.Entry<K, Collection<V>> keyValuePair = (Map.Entry)pairsIterator.next();
/* 343 */       Collection<V> coll = (Collection)keyValuePair.getValue();
/* 344 */       coll.clear();
/*     */     }
/* 346 */     this.internalMap.clear();
/*     */   }
/*     */   
/*     */   public int size() {
/* 350 */     return this.internalMap.size();
/*     */   }
/*     */   
/*     */   public Collection<V> get(Object key) {
/* 354 */     return (Collection)this.internalMap.get(key);
/*     */   }
/*     */   
/*     */   public Collection<V> remove(Object key) {
/* 358 */     return (Collection)this.internalMap.remove(key);
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 362 */     return this.internalMap.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 366 */     return this.internalMap.containsKey(key);
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> map) {
/* 370 */     for (K key : map.keySet()) {
/* 371 */       put(key, map.get(key));
/*     */     }
/*     */   }
/*     */   
/*     */   public void putAll(MultiMap<? extends K, ? extends V> map) {
/* 376 */     for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
/* 377 */       entry = (Map.Entry)it.next();
/* 378 */       for (V v : (Collection)entry.getValue())
/* 379 */         put(entry.getKey(), v);
/*     */     }
/*     */     Map.Entry<? extends K, Collection<? extends V>> entry;
/*     */   }
/*     */   
/*     */   public Set<K> keySet() {
/* 385 */     return this.internalMap.keySet();
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, Collection<V>>> entrySet() {
/* 389 */     return this.internalMap.entrySet();
/*     */   }
/*     */   
/*     */   public Map<K, Collection<V>> map() {
/* 393 */     return this.internalMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> values()
/*     */   {
/* 404 */     Collection vs = this.values;
/* 405 */     return vs != null ? vs : (this.values = new Values(null));
/*     */   }
/*     */   
/*     */   private class Values<T>
/*     */     extends AbstractCollection<V>
/*     */   {
/*     */     private Values() {}
/*     */     
/*     */     public Iterator<V> iterator()
/*     */     {
/* 415 */       return new MultiHashMap.ValueIterator(MultiHashMap.this, null);
/*     */     }
/*     */     
/*     */     public int size() {
/* 419 */       int compt = 0;
/* 420 */       Iterator it = iterator();
/* 421 */       while (it.hasNext()) {
/* 422 */         it.next();
/* 423 */         compt++;
/*     */       }
/* 425 */       return compt;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 429 */       MultiHashMap.this.clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private class ValueIterator<T>
/*     */     implements Iterator<V>
/*     */   {
/*     */     private Iterator<Collection<V>> backedIterator;
/*     */     private Iterator<V> tempIterator;
/*     */     
/*     */     private ValueIterator()
/*     */     {
/* 442 */       this.backedIterator = MultiHashMap.this.internalMap.values().iterator();
/*     */     }
/*     */     
/*     */     private boolean searchNextIterator() {
/* 446 */       while ((this.tempIterator == null) || (!this.tempIterator.hasNext())) {
/* 447 */         if (!this.backedIterator.hasNext()) {
/* 448 */           return false;
/*     */         }
/* 450 */         this.tempIterator = ((Collection)this.backedIterator.next()).iterator();
/*     */       }
/* 452 */       return true;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 456 */       return searchNextIterator();
/*     */     }
/*     */     
/*     */     public V next() {
/* 460 */       if (!searchNextIterator()) {
/* 461 */         throw new NoSuchElementException();
/*     */       }
/* 463 */       return (V)this.tempIterator.next();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 467 */       if (this.tempIterator == null) {
/* 468 */         throw new IllegalStateException();
/*     */       }
/* 470 */       this.tempIterator.remove();
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
/*     */   public Object clone()
/*     */   {
/* 484 */     MultiHashMap<K, V> cloned = new MultiHashMap();
/* 485 */     for (Iterator<Map.Entry<K, Collection<V>>> it = this.internalMap.entrySet().iterator(); it.hasNext();) {
/* 486 */       entry = (Map.Entry)it.next();
/* 487 */       for (V v : (Collection)entry.getValue())
/* 488 */         cloned.put(entry.getKey(), v);
/*     */     }
/*     */     Map.Entry<K, Collection<V>> entry;
/* 491 */     return cloned;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 495 */     if ((obj instanceof MultiHashMap))
/* 496 */       return this.internalMap.equals(((MultiHashMap)obj).map());
/* 497 */     return false;
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 501 */     return this.internalMap.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Collection<V> createCollection(Collection<? extends V> coll)
/*     */   {
/* 513 */     if (coll == null) {
/* 514 */       return new ArrayList();
/*     */     }
/* 516 */     return new ArrayList(coll);
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 521 */     return this.internalMap.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/multimap/MultiHashMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */