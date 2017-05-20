/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.KeyValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class StaticBucketMap<K, V>
/*     */   implements Map<K, V>
/*     */ {
/*     */   private static final int DEFAULT_BUCKETS = 255;
/*     */   private Node<K, V>[] buckets;
/*     */   private Lock[] locks;
/*     */   
/*     */   public StaticBucketMap()
/*     */   {
/* 114 */     this(255);
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
/*     */   public StaticBucketMap(int numBuckets)
/*     */   {
/* 128 */     int size = Math.max(17, numBuckets);
/*     */     
/*     */ 
/* 131 */     if (size % 2 == 0) {
/* 132 */       size--;
/*     */     }
/*     */     
/* 135 */     this.buckets = new Node[size];
/* 136 */     this.locks = new Lock[size];
/*     */     
/* 138 */     for (int i = 0; i < size; i++) {
/* 139 */       this.locks[i] = new Lock(null);
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
/*     */   private final int getHash(Object key)
/*     */   {
/* 158 */     if (key == null) {
/* 159 */       return 0;
/*     */     }
/* 161 */     int hash = key.hashCode();
/* 162 */     hash += (hash << 15 ^ 0xFFFFFFFF);
/* 163 */     hash ^= hash >>> 10;
/* 164 */     hash += (hash << 3);
/* 165 */     hash ^= hash >>> 6;
/* 166 */     hash += (hash << 11 ^ 0xFFFFFFFF);
/* 167 */     hash ^= hash >>> 16;
/* 168 */     hash %= this.buckets.length;
/* 169 */     return hash < 0 ? hash * -1 : hash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 179 */     int cnt = 0;
/*     */     
/* 181 */     for (int i = 0; i < this.buckets.length; i++) {
/* 182 */       cnt += this.locks[i].size;
/*     */     }
/* 184 */     return cnt;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 193 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V get(Object key)
/*     */   {
/* 203 */     int hash = getHash(key);
/*     */     
/* 205 */     synchronized (this.locks[hash]) {
/* 206 */       Node<K, V> n = this.buckets[hash];
/*     */       
/* 208 */       while (n != null) {
/* 209 */         if ((n.key == key) || ((n.key != null) && (n.key.equals(key)))) {
/* 210 */           return (V)n.value;
/*     */         }
/*     */         
/* 213 */         n = n.next;
/*     */       }
/*     */     }
/* 216 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(Object key)
/*     */   {
/* 226 */     int hash = getHash(key);
/*     */     
/* 228 */     synchronized (this.locks[hash]) {
/* 229 */       Node n = this.buckets[hash];
/*     */       
/* 231 */       while (n != null) {
/* 232 */         if ((n.key == null) || ((n.key != null) && (n.key.equals(key)))) {
/* 233 */           return true;
/*     */         }
/*     */         
/* 236 */         n = n.next;
/*     */       }
/*     */     }
/* 239 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object value)
/*     */   {
/* 249 */     for (int i = 0; i < this.buckets.length; i++) {
/* 250 */       synchronized (this.locks[i]) {
/* 251 */         Node n = this.buckets[i];
/*     */         
/* 253 */         while (n != null) {
/* 254 */           if ((n.value == value) || ((n.value != null) && (n.value.equals(value)))) {
/* 255 */             return true;
/*     */           }
/*     */           
/* 258 */           n = n.next;
/*     */         }
/*     */       }
/*     */     }
/* 262 */     return false;
/*     */   }
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
/* 274 */     int hash = getHash(key);
/*     */     
/* 276 */     synchronized (this.locks[hash]) {
/* 277 */       Node<K, V> n = this.buckets[hash];
/*     */       
/* 279 */       if (n == null) {
/* 280 */         n = new Node(null);
/* 281 */         n.key = key;
/* 282 */         n.value = value;
/* 283 */         this.buckets[hash] = n;
/* 284 */         this.locks[hash].size += 1;
/* 285 */         return null;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 291 */       for (Node<K, V> next = n; next != null; next = next.next) {
/* 292 */         n = next;
/*     */         
/* 294 */         if ((n.key == key) || ((n.key != null) && (n.key.equals(key)))) {
/* 295 */           V returnVal = n.value;
/* 296 */           n.value = value;
/* 297 */           return returnVal;
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 303 */       Node<K, V> newNode = new Node(null);
/* 304 */       newNode.key = key;
/* 305 */       newNode.value = value;
/* 306 */       n.next = newNode;
/* 307 */       this.locks[hash].size += 1;
/*     */     }
/* 309 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V remove(Object key)
/*     */   {
/* 319 */     int hash = getHash(key);
/*     */     
/* 321 */     synchronized (this.locks[hash]) {
/* 322 */       Node<K, V> n = this.buckets[hash];
/* 323 */       Node<K, V> prev = null;
/*     */       
/* 325 */       while (n != null) {
/* 326 */         if ((n.key == key) || ((n.key != null) && (n.key.equals(key))))
/*     */         {
/* 328 */           if (null == prev)
/*     */           {
/* 330 */             this.buckets[hash] = n.next;
/*     */           }
/*     */           else {
/* 333 */             prev.next = n.next;
/*     */           }
/* 335 */           this.locks[hash].size -= 1;
/* 336 */           return (V)n.value;
/*     */         }
/*     */         
/* 339 */         prev = n;
/* 340 */         n = n.next;
/*     */       }
/*     */     }
/* 343 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<K> keySet()
/*     */   {
/* 353 */     return new KeySet(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<V> values()
/*     */   {
/* 362 */     return new Values(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<Map.Entry<K, V>> entrySet()
/*     */   {
/* 371 */     return new EntrySet(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void putAll(Map<? extends K, ? extends V> map)
/*     */   {
/* 382 */     Iterator i = map.keySet().iterator();
/*     */     
/* 384 */     while (i.hasNext()) {
/* 385 */       K key = i.next();
/* 386 */       put(key, map.get(key));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 394 */     for (int i = 0; i < this.buckets.length; i++) {
/* 395 */       Lock lock = this.locks[i];
/* 396 */       synchronized (lock) {
/* 397 */         this.buckets[i] = null;
/* 398 */         lock.size = 0;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 410 */     if (obj == this) {
/* 411 */       return true;
/*     */     }
/* 413 */     if (!(obj instanceof Map)) {
/* 414 */       return false;
/*     */     }
/* 416 */     Map other = (Map)obj;
/* 417 */     return entrySet().equals(other.entrySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 426 */     int hashCode = 0;
/*     */     
/* 428 */     for (int i = 0; i < this.buckets.length; i++) {
/* 429 */       synchronized (this.locks[i]) {
/* 430 */         Node n = this.buckets[i];
/*     */         
/* 432 */         while (n != null) {
/* 433 */           hashCode += n.hashCode();
/* 434 */           n = n.next;
/*     */         }
/*     */       }
/*     */     }
/* 438 */     return hashCode;
/*     */   }
/*     */   
/*     */ 
/*     */   private static final class Node<K, V>
/*     */     implements Map.Entry<K, V>, KeyValue<K, V>
/*     */   {
/*     */     protected K key;
/*     */     protected V value;
/*     */     protected Node<K, V> next;
/*     */     
/*     */     public K getKey()
/*     */     {
/* 451 */       return (K)this.key;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 455 */       return (V)this.value;
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 459 */       return (this.key == null ? 0 : this.key.hashCode()) ^ (this.value == null ? 0 : this.value.hashCode());
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 463 */       if (obj == this) {
/* 464 */         return true;
/*     */       }
/* 466 */       if (!(obj instanceof Map.Entry)) {
/* 467 */         return false;
/*     */       }
/*     */       
/* 470 */       Map.Entry e2 = (Map.Entry)obj;
/* 471 */       return (this.key == null ? e2.getKey() == null : this.key.equals(e2.getKey())) && (this.value == null ? e2.getValue() == null : this.value.equals(e2.getValue()));
/*     */     }
/*     */     
/*     */     public V setValue(V obj) {
/* 475 */       V retVal = this.value;
/* 476 */       this.value = obj;
/* 477 */       return retVal;
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class Lock
/*     */   {
/*     */     public int size;
/*     */   }
/*     */   
/*     */   private class EntryIterator extends StaticBucketMap<K, V>.IteratorBase implements Iterator<Map.Entry<K, V>>
/*     */   {
/*     */     private EntryIterator() {
/* 489 */       super(null);
/*     */     }
/*     */     
/* 492 */     public Map.Entry<K, V> next() { return superNext(); }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private class IteratorBase
/*     */   {
/* 500 */     private ArrayList<Map.Entry<K, V>> current = new ArrayList();
/*     */     private int bucket;
/*     */     private Map.Entry<K, V> last;
/*     */     
/*     */     private IteratorBase() {}
/*     */     
/* 506 */     public boolean hasNext() { if (this.current.size() > 0) return true;
/* 507 */       while (this.bucket < StaticBucketMap.this.buckets.length) {
/* 508 */         synchronized (StaticBucketMap.this.locks[this.bucket]) {
/* 509 */           StaticBucketMap.Node<K, V> n = StaticBucketMap.this.buckets[this.bucket];
/* 510 */           while (n != null) {
/* 511 */             this.current.add(n);
/* 512 */             n = n.next;
/*     */           }
/* 514 */           this.bucket += 1;
/* 515 */           if (this.current.size() > 0) return true;
/*     */         }
/*     */       }
/* 518 */       return false;
/*     */     }
/*     */     
/*     */     protected Map.Entry<K, V> nextEntry() {
/* 522 */       if (!hasNext()) throw new NoSuchElementException();
/* 523 */       this.last = ((Map.Entry)this.current.remove(this.current.size() - 1));
/* 524 */       return this.last;
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> superNext() {
/* 528 */       return nextEntry();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 532 */       if (this.last == null) throw new IllegalStateException();
/* 533 */       StaticBucketMap.this.remove(this.last.getKey());
/* 534 */       this.last = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private class ValueIterator extends StaticBucketMap<K, V>.IteratorBase implements Iterator<V> {
/* 539 */     private ValueIterator() { super(null); }
/*     */     
/*     */     public V next() {
/* 542 */       return (V)nextEntry().getValue();
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeyIterator extends StaticBucketMap<K, V>.IteratorBase implements Iterator<K> {
/* 547 */     private KeyIterator() { super(null); }
/*     */     
/*     */     public K next() {
/* 550 */       return (K)nextEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */   private class EntrySet extends AbstractSet<Map.Entry<K, V>> {
/*     */     private EntrySet() {}
/*     */     
/*     */     public int size() {
/* 558 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 562 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 566 */       return new StaticBucketMap.EntryIterator(StaticBucketMap.this, null);
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 570 */       Map.Entry entry = (Map.Entry)obj;
/* 571 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 572 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 573 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 574 */           if (n.equals(entry)) return true;
/*     */         }
/*     */       }
/* 577 */       return false;
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 581 */       if (!(obj instanceof Map.Entry)) {
/* 582 */         return false;
/*     */       }
/* 584 */       Map.Entry entry = (Map.Entry)obj;
/* 585 */       int hash = StaticBucketMap.this.getHash(entry.getKey());
/* 586 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 587 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 588 */           if (n.equals(entry)) {
/* 589 */             StaticBucketMap.this.remove(n.getKey());
/* 590 */             return true;
/*     */           }
/*     */         }
/*     */       }
/* 594 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private class KeySet extends AbstractSet<K>
/*     */   {
/*     */     private KeySet() {}
/*     */     
/*     */     public int size() {
/* 603 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 607 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator<K> iterator() {
/* 611 */       return new StaticBucketMap.KeyIterator(StaticBucketMap.this, null);
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 615 */       return StaticBucketMap.this.containsKey(obj);
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 619 */       int hash = StaticBucketMap.this.getHash(obj);
/* 620 */       synchronized (StaticBucketMap.this.locks[hash]) {
/* 621 */         for (StaticBucketMap.Node n = StaticBucketMap.this.buckets[hash]; n != null; n = n.next) {
/* 622 */           Object k = n.getKey();
/* 623 */           if ((k == obj) || ((k != null) && (k.equals(obj)))) {
/* 624 */             StaticBucketMap.this.remove(k);
/* 625 */             return true;
/*     */           }
/*     */         }
/*     */       }
/* 629 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */   private class Values extends AbstractCollection<V>
/*     */   {
/*     */     private Values() {}
/*     */     
/*     */     public int size()
/*     */     {
/* 639 */       return StaticBucketMap.this.size();
/*     */     }
/*     */     
/*     */     public void clear() {
/* 643 */       StaticBucketMap.this.clear();
/*     */     }
/*     */     
/*     */     public Iterator<V> iterator() {
/* 647 */       return new StaticBucketMap.ValueIterator(StaticBucketMap.this, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void atomic(Runnable r)
/*     */   {
/* 688 */     if (r == null) throw new NullPointerException();
/* 689 */     atomic(r, 0);
/*     */   }
/*     */   
/*     */   private void atomic(Runnable r, int bucket) {
/* 693 */     if (bucket >= this.buckets.length) {
/* 694 */       r.run();
/* 695 */       return;
/*     */     }
/* 697 */     synchronized (this.locks[bucket]) {
/* 698 */       atomic(r, bucket + 1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/StaticBucketMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */