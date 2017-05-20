/*      */ package org.apache.commons.collections15.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractMap;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections15.IterableMap;
/*      */ import org.apache.commons.collections15.KeyValue;
/*      */ import org.apache.commons.collections15.MapIterator;
/*      */ import org.apache.commons.collections15.iterators.EmptyIterator;
/*      */ import org.apache.commons.collections15.iterators.EmptyMapIterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class AbstractHashedMap<K, V>
/*      */   extends AbstractMap<K, V>
/*      */   implements IterableMap<K, V>
/*      */ {
/*      */   protected static final String NO_NEXT_ENTRY = "No next() entry in the iteration";
/*      */   protected static final String NO_PREVIOUS_ENTRY = "No previous() entry in the iteration";
/*      */   protected static final String REMOVE_INVALID = "remove() can only be called once after next()";
/*      */   protected static final String GETKEY_INVALID = "getKey() can only be called after next() and before remove()";
/*      */   protected static final String GETVALUE_INVALID = "getValue() can only be called after next() and before remove()";
/*      */   protected static final String SETVALUE_INVALID = "setValue() can only be called after next() and before remove()";
/*      */   protected static final int DEFAULT_CAPACITY = 16;
/*      */   protected static final int DEFAULT_THRESHOLD = 12;
/*      */   protected static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   protected static final int MAXIMUM_CAPACITY = 1073741824;
/*   80 */   protected static final Object NULL = new Object();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient float loadFactor;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient int size;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient HashEntry<K, V>[] data;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient int threshold;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient int modCount;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient EntrySet<K, V> entrySet;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected transient KeySet<K, V> keySet;
/*      */   
/*      */ 
/*      */ 
/*      */   protected transient Values<K, V> values;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AbstractHashedMap() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AbstractHashedMap(int initialCapacity, float loadFactor, int threshold)
/*      */   {
/*  131 */     this.loadFactor = loadFactor;
/*  132 */     this.data = new HashEntry[initialCapacity];
/*  133 */     this.threshold = threshold;
/*  134 */     init();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AbstractHashedMap(int initialCapacity)
/*      */   {
/*  145 */     this(initialCapacity, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AbstractHashedMap(int initialCapacity, float loadFactor)
/*      */   {
/*  159 */     if (initialCapacity < 1) {
/*  160 */       throw new IllegalArgumentException("Initial capacity must be greater than 0");
/*      */     }
/*  162 */     if ((loadFactor <= 0.0F) || (Float.isNaN(loadFactor))) {
/*  163 */       throw new IllegalArgumentException("Load factor must be greater than 0");
/*      */     }
/*  165 */     this.loadFactor = loadFactor;
/*  166 */     this.threshold = calculateThreshold(initialCapacity, loadFactor);
/*  167 */     initialCapacity = calculateNewCapacity(initialCapacity);
/*  168 */     this.data = new HashEntry[initialCapacity];
/*  169 */     init();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AbstractHashedMap(Map<? extends K, ? extends V> map)
/*      */   {
/*  179 */     this(Math.max(2 * map.size(), 16), 0.75F);
/*  180 */     putAll(map);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void init() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V get(Object key)
/*      */   {
/*  197 */     int hashCode = hash(key == null ? NULL : key);
/*  198 */     HashEntry<K, V> entry = this.data[hashIndex(hashCode, this.data.length)];
/*  199 */     while (entry != null) {
/*  200 */       if ((entry.hashCode == hashCode) && (isEqualKey(key, entry.key))) {
/*  201 */         return (V)entry.getValue();
/*      */       }
/*  203 */       entry = entry.next;
/*      */     }
/*  205 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  214 */     return this.size;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  223 */     return this.size == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsKey(Object key)
/*      */   {
/*  234 */     int hashCode = hash(key == null ? NULL : key);
/*  235 */     HashEntry entry = this.data[hashIndex(hashCode, this.data.length)];
/*  236 */     while (entry != null) {
/*  237 */       if ((entry.hashCode == hashCode) && (isEqualKey(key, entry.getKey()))) {
/*  238 */         return true;
/*      */       }
/*  240 */       entry = entry.next;
/*      */     }
/*  242 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsValue(Object value)
/*      */   {
/*  252 */     if (value == null) {
/*  253 */       int i = 0; for (int isize = this.data.length; i < isize; i++) {
/*  254 */         HashEntry entry = this.data[i];
/*  255 */         while (entry != null) {
/*  256 */           if (entry.getValue() == null) {
/*  257 */             return true;
/*      */           }
/*  259 */           entry = entry.next;
/*      */         }
/*      */       }
/*      */     } else {
/*  263 */       int i = 0; for (int isize = this.data.length; i < isize; i++) {
/*  264 */         HashEntry entry = this.data[i];
/*  265 */         while (entry != null) {
/*  266 */           if (isEqualValue(value, entry.getValue())) {
/*  267 */             return true;
/*      */           }
/*  269 */           entry = entry.next;
/*      */         }
/*      */       }
/*      */     }
/*  273 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V put(K key, V value)
/*      */   {
/*  285 */     int hashCode = hash(key == null ? NULL : key);
/*  286 */     int index = hashIndex(hashCode, this.data.length);
/*  287 */     HashEntry<K, V> entry = this.data[index];
/*  288 */     while (entry != null) {
/*  289 */       if ((entry.hashCode == hashCode) && (isEqualKey(key, entry.getKey()))) {
/*  290 */         V oldValue = entry.getValue();
/*  291 */         updateEntry(entry, value);
/*  292 */         return oldValue;
/*      */       }
/*  294 */       entry = entry.next;
/*      */     }
/*  296 */     addMapping(index, hashCode, key, value);
/*  297 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void putAll(Map<? extends K, ? extends V> map)
/*      */   {
/*  310 */     int mapSize = map.size();
/*  311 */     if (mapSize == 0) {
/*  312 */       return;
/*      */     }
/*  314 */     int newSize = (int)((this.size + mapSize) / this.loadFactor + 1.0F);
/*  315 */     ensureCapacity(calculateNewCapacity(newSize));
/*      */     
/*  317 */     for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
/*  318 */       Map.Entry<? extends K, ? extends V> entry = (Map.Entry)it.next();
/*  319 */       put(entry.getKey(), entry.getValue());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V remove(Object key)
/*      */   {
/*  330 */     int hashCode = hash(key == null ? NULL : key);
/*  331 */     int index = hashIndex(hashCode, this.data.length);
/*  332 */     HashEntry<K, V> entry = this.data[index];
/*  333 */     HashEntry<K, V> previous = null;
/*  334 */     while (entry != null) {
/*  335 */       if ((entry.hashCode == hashCode) && (isEqualKey(key, entry.getKey()))) {
/*  336 */         V oldValue = entry.getValue();
/*  337 */         removeMapping(entry, index, previous);
/*  338 */         return oldValue;
/*      */       }
/*  340 */       previous = entry;
/*  341 */       entry = entry.next;
/*      */     }
/*  343 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  351 */     this.modCount += 1;
/*  352 */     HashEntry[] data = this.data;
/*  353 */     for (int i = data.length - 1; i >= 0; i--) {
/*  354 */       data[i] = null;
/*      */     }
/*  356 */     this.size = 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int hash(Object key)
/*      */   {
/*  369 */     int h = key.hashCode();
/*  370 */     h += (h << 9 ^ 0xFFFFFFFF);
/*  371 */     h ^= h >>> 14;
/*  372 */     h += (h << 4);
/*  373 */     h ^= h >>> 10;
/*  374 */     return h;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean isEqualKey(Object key1, Object key2)
/*      */   {
/*  387 */     return (key1 == key2) || ((key1 != null) && (key1.equals(key2)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean isEqualValue(Object value1, Object value2)
/*      */   {
/*  400 */     return (value1 == value2) || (value1.equals(value2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int hashIndex(int hashCode, int dataSize)
/*      */   {
/*  413 */     return hashCode & dataSize - 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected HashEntry<K, V> getEntry(Object key)
/*      */   {
/*  428 */     int hashCode = hash(key == null ? NULL : key);
/*  429 */     HashEntry<K, V> entry = this.data[hashIndex(hashCode, this.data.length)];
/*  430 */     while (entry != null) {
/*  431 */       if ((entry.hashCode == hashCode) && (isEqualKey(key, entry.getKey()))) {
/*  432 */         return entry;
/*      */       }
/*  434 */       entry = entry.next;
/*      */     }
/*  436 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void updateEntry(HashEntry<K, V> entry, V newValue)
/*      */   {
/*  450 */     entry.setValue(newValue);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void reuseEntry(HashEntry<K, V> entry, int hashIndex, int hashCode, K key, V value)
/*      */   {
/*  466 */     entry.next = this.data[hashIndex];
/*  467 */     entry.hashCode = hashCode;
/*  468 */     entry.key = key;
/*  469 */     entry.value = value;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void addMapping(int hashIndex, int hashCode, K key, V value)
/*      */   {
/*  487 */     this.modCount += 1;
/*  488 */     HashEntry<K, V> entry = createEntry(this.data[hashIndex], hashCode, key, value);
/*  489 */     addEntry(entry, hashIndex);
/*  490 */     this.size += 1;
/*  491 */     checkCapacity();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected HashEntry<K, V> createEntry(HashEntry<K, V> next, int hashCode, K key, V value)
/*      */   {
/*  508 */     return new HashEntry(next, hashCode, key, value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void addEntry(HashEntry<K, V> entry, int hashIndex)
/*      */   {
/*  521 */     this.data[hashIndex] = entry;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void removeMapping(HashEntry<K, V> entry, int hashIndex, HashEntry<K, V> previous)
/*      */   {
/*  537 */     this.modCount += 1;
/*  538 */     removeEntry(entry, hashIndex, previous);
/*  539 */     this.size -= 1;
/*  540 */     destroyEntry(entry);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void removeEntry(HashEntry<K, V> entry, int hashIndex, HashEntry<K, V> previous)
/*      */   {
/*  555 */     if (previous == null) {
/*  556 */       this.data[hashIndex] = entry.next;
/*      */     } else {
/*  558 */       previous.next = entry.next;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void destroyEntry(HashEntry<K, V> entry)
/*      */   {
/*  571 */     entry.next = null;
/*  572 */     entry.key = null;
/*  573 */     entry.value = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void checkCapacity()
/*      */   {
/*  583 */     if (this.size >= this.threshold) {
/*  584 */       int newCapacity = this.data.length * 2;
/*  585 */       if (newCapacity <= 1073741824) {
/*  586 */         ensureCapacity(newCapacity);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void ensureCapacity(int newCapacity)
/*      */   {
/*  597 */     int oldCapacity = this.data.length;
/*  598 */     if (newCapacity <= oldCapacity) {
/*  599 */       return;
/*      */     }
/*  601 */     if (this.size == 0) {
/*  602 */       this.threshold = calculateThreshold(newCapacity, this.loadFactor);
/*  603 */       this.data = new HashEntry[newCapacity];
/*      */     } else {
/*  605 */       HashEntry<K, V>[] oldEntries = this.data;
/*  606 */       HashEntry<K, V>[] newEntries = new HashEntry[newCapacity];
/*      */       
/*  608 */       this.modCount += 1;
/*  609 */       for (int i = oldCapacity - 1; i >= 0; i--) {
/*  610 */         HashEntry<K, V> entry = oldEntries[i];
/*  611 */         if (entry != null) {
/*  612 */           oldEntries[i] = null;
/*      */           do {
/*  614 */             HashEntry<K, V> next = entry.next;
/*  615 */             int index = hashIndex(entry.hashCode, newCapacity);
/*  616 */             entry.next = newEntries[index];
/*  617 */             newEntries[index] = entry;
/*  618 */             entry = next;
/*  619 */           } while (entry != null);
/*      */         }
/*      */       }
/*  622 */       this.threshold = calculateThreshold(newCapacity, this.loadFactor);
/*  623 */       this.data = newEntries;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int calculateNewCapacity(int proposedCapacity)
/*      */   {
/*  635 */     int newCapacity = 1;
/*  636 */     if (proposedCapacity > 1073741824) {
/*  637 */       newCapacity = 1073741824;
/*      */     } else {
/*  639 */       while (newCapacity < proposedCapacity) {
/*  640 */         newCapacity <<= 1;
/*      */       }
/*  642 */       if (newCapacity > 1073741824) {
/*  643 */         newCapacity = 1073741824;
/*      */       }
/*      */     }
/*  646 */     return newCapacity;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int calculateThreshold(int newCapacity, float factor)
/*      */   {
/*  658 */     return (int)(newCapacity * factor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected HashEntry<K, V> entryNext(HashEntry<K, V> entry)
/*      */   {
/*  672 */     return entry.next;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int entryHashCode(HashEntry<K, V> entry)
/*      */   {
/*  685 */     return entry.hashCode;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected K entryKey(HashEntry<K, V> entry)
/*      */   {
/*  698 */     return (K)entry.key;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected V entryValue(HashEntry<K, V> entry)
/*      */   {
/*  711 */     return (V)entry.value;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MapIterator<K, V> mapIterator()
/*      */   {
/*  727 */     if (this.size == 0) {
/*  728 */       return EmptyMapIterator.INSTANCE;
/*      */     }
/*  730 */     return new HashMapIterator(this);
/*      */   }
/*      */   
/*      */   protected static class HashMapIterator<K, V>
/*      */     extends AbstractHashedMap.HashIterator<K, V>
/*      */     implements MapIterator<K, V>
/*      */   {
/*      */     protected HashMapIterator(AbstractHashedMap<K, V> parent)
/*      */     {
/*  739 */       super();
/*      */     }
/*      */     
/*      */     public K next() {
/*  743 */       return (K)super.nextEntry().getKey();
/*      */     }
/*      */     
/*      */     public K getKey() {
/*  747 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/*  748 */       if (current == null) {
/*  749 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  751 */       return (K)current.getKey();
/*      */     }
/*      */     
/*      */     public V getValue() {
/*  755 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/*  756 */       if (current == null) {
/*  757 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  759 */       return (V)current.getValue();
/*      */     }
/*      */     
/*      */     public V setValue(V value) {
/*  763 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/*  764 */       if (current == null) {
/*  765 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  767 */       return (V)current.setValue(value);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Set<Map.Entry<K, V>> entrySet()
/*      */   {
/*  780 */     if (this.entrySet == null) {
/*  781 */       this.entrySet = new EntrySet(this);
/*      */     }
/*  783 */     return this.entrySet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<Map.Entry<K, V>> createEntrySetIterator()
/*      */   {
/*  793 */     if (size() == 0) {
/*  794 */       return EmptyIterator.INSTANCE;
/*      */     }
/*  796 */     return new EntrySetIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected static class EntrySet<K, V>
/*      */     extends AbstractSet<Map.Entry<K, V>>
/*      */   {
/*      */     protected final AbstractHashedMap<K, V> parent;
/*      */     
/*      */ 
/*      */ 
/*      */     protected EntrySet(AbstractHashedMap<K, V> parent)
/*      */     {
/*  810 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  814 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  818 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Map.Entry<K, V> entry) {
/*  822 */       Map.Entry<K, V> e = entry;
/*  823 */       Map.Entry<K, V> match = this.parent.getEntry(e.getKey());
/*  824 */       return (match != null) && (match.equals(e));
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/*  828 */       if (!(obj instanceof Map.Entry)) {
/*  829 */         return false;
/*      */       }
/*  831 */       if (!contains(obj)) {
/*  832 */         return false;
/*      */       }
/*  834 */       Map.Entry<K, V> entry = (Map.Entry)obj;
/*  835 */       K key = entry.getKey();
/*  836 */       this.parent.remove(key);
/*  837 */       return true;
/*      */     }
/*      */     
/*      */     public Iterator<Map.Entry<K, V>> iterator() {
/*  841 */       return this.parent.createEntrySetIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class EntrySetIterator<K, V>
/*      */     extends AbstractHashedMap.HashIterator<K, V>
/*      */     implements Iterator<Map.Entry<K, V>>
/*      */   {
/*      */     protected EntrySetIterator(AbstractHashedMap<K, V> parent)
/*      */     {
/*  851 */       super();
/*      */     }
/*      */     
/*      */     public AbstractHashedMap.HashEntry<K, V> next() {
/*  855 */       return super.nextEntry();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Set<K> keySet()
/*      */   {
/*  868 */     if (this.keySet == null) {
/*  869 */       this.keySet = new KeySet(this);
/*      */     }
/*  871 */     return this.keySet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<K> createKeySetIterator()
/*      */   {
/*  881 */     if (size() == 0) {
/*  882 */       return EmptyIterator.INSTANCE;
/*      */     }
/*  884 */     return new KeySetIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected static class KeySet<K, V>
/*      */     extends AbstractSet<K>
/*      */   {
/*      */     protected final AbstractHashedMap<K, V> parent;
/*      */     
/*      */ 
/*      */ 
/*      */     protected KeySet(AbstractHashedMap<K, V> parent)
/*      */     {
/*  898 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  902 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  906 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object key) {
/*  910 */       return this.parent.containsKey(key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key) {
/*  914 */       boolean result = this.parent.containsKey(key);
/*  915 */       this.parent.remove(key);
/*  916 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator<K> iterator() {
/*  920 */       return this.parent.createKeySetIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class KeySetIterator<K, V>
/*      */     extends AbstractHashedMap.HashIterator<K, V>
/*      */     implements Iterator<K>
/*      */   {
/*      */     protected KeySetIterator(AbstractHashedMap<K, V> parent)
/*      */     {
/*  930 */       super();
/*      */     }
/*      */     
/*      */     public K next() {
/*  934 */       return (K)super.nextEntry().getKey();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection<V> values()
/*      */   {
/*  947 */     if (this.values == null) {
/*  948 */       this.values = new Values(this);
/*      */     }
/*  950 */     return this.values;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<V> createValuesIterator()
/*      */   {
/*  960 */     if (size() == 0) {
/*  961 */       return EmptyIterator.INSTANCE;
/*      */     }
/*  963 */     return new ValuesIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected static class Values<K, V>
/*      */     extends AbstractCollection<V>
/*      */   {
/*      */     protected final AbstractHashedMap<K, V> parent;
/*      */     
/*      */ 
/*      */ 
/*      */     protected Values(AbstractHashedMap<K, V> parent)
/*      */     {
/*  977 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  981 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  985 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object value) {
/*  989 */       return this.parent.containsValue(value);
/*      */     }
/*      */     
/*      */     public Iterator<V> iterator() {
/*  993 */       return this.parent.createValuesIterator();
/*      */     }
/*      */   }
/*      */   
/*      */   protected static class ValuesIterator<K, V>
/*      */     extends AbstractHashedMap.HashIterator<K, V>
/*      */     implements Iterator<V>
/*      */   {
/*      */     protected ValuesIterator(AbstractHashedMap<K, V> parent)
/*      */     {
/* 1003 */       super();
/*      */     }
/*      */     
/*      */     public V next() {
/* 1007 */       return (V)super.nextEntry().getValue();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class HashEntry<K, V>
/*      */     implements Map.Entry<K, V>, KeyValue<K, V>
/*      */   {
/*      */     protected HashEntry<K, V> next;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected int hashCode;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private K key;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     private V value;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected HashEntry(HashEntry<K, V> next, int hashCode, K key, V value)
/*      */     {
/* 1040 */       this.next = next;
/* 1041 */       this.hashCode = hashCode;
/* 1042 */       this.key = key;
/* 1043 */       this.value = value;
/*      */     }
/*      */     
/*      */     public K getKey() {
/* 1047 */       return (K)this.key;
/*      */     }
/*      */     
/*      */     public void setKey(K key) {
/* 1051 */       this.key = key;
/*      */     }
/*      */     
/*      */     public V getValue() {
/* 1055 */       return (V)this.value;
/*      */     }
/*      */     
/*      */     public V setValue(V value) {
/* 1059 */       V old = this.value;
/* 1060 */       this.value = value;
/* 1061 */       return old;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/* 1065 */       if (obj == this) {
/* 1066 */         return true;
/*      */       }
/* 1068 */       if (!(obj instanceof Map.Entry)) {
/* 1069 */         return false;
/*      */       }
/* 1071 */       Map.Entry other = (Map.Entry)obj;
/* 1072 */       return (getKey() == null ? other.getKey() == null : getKey().equals(other.getKey())) && (getValue() == null ? other.getValue() == null : getValue().equals(other.getValue()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/* 1076 */       return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1080 */       return getKey() + '=' + getValue();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static abstract class HashIterator<K, V>
/*      */   {
/*      */     protected final AbstractHashedMap parent;
/*      */     
/*      */ 
/*      */ 
/*      */     protected int hashIndex;
/*      */     
/*      */ 
/*      */ 
/*      */     protected AbstractHashedMap.HashEntry<K, V> last;
/*      */     
/*      */ 
/*      */ 
/*      */     protected AbstractHashedMap.HashEntry<K, V> next;
/*      */     
/*      */ 
/*      */ 
/*      */     protected int expectedModCount;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     protected HashIterator(AbstractHashedMap<K, V> parent)
/*      */     {
/* 1112 */       this.parent = parent;
/* 1113 */       AbstractHashedMap.HashEntry<K, V>[] data = parent.data;
/* 1114 */       int i = data.length;
/* 1115 */       AbstractHashedMap.HashEntry<K, V> next = null;
/* 1116 */       while ((i > 0) && (next == null)) {
/* 1117 */         next = data[(--i)];
/*      */       }
/* 1119 */       this.next = next;
/* 1120 */       this.hashIndex = i;
/* 1121 */       this.expectedModCount = parent.modCount;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/* 1125 */       return this.next != null;
/*      */     }
/*      */     
/*      */     protected AbstractHashedMap.HashEntry<K, V> nextEntry() {
/* 1129 */       if (this.parent.modCount != this.expectedModCount) {
/* 1130 */         throw new ConcurrentModificationException();
/*      */       }
/* 1132 */       AbstractHashedMap.HashEntry<K, V> newCurrent = this.next;
/* 1133 */       if (newCurrent == null) {
/* 1134 */         throw new NoSuchElementException("No next() entry in the iteration");
/*      */       }
/* 1136 */       AbstractHashedMap.HashEntry<K, V>[] data = this.parent.data;
/* 1137 */       int i = this.hashIndex;
/* 1138 */       AbstractHashedMap.HashEntry<K, V> n = newCurrent.next;
/* 1139 */       while ((n == null) && (i > 0)) {
/* 1140 */         n = data[(--i)];
/*      */       }
/* 1142 */       this.next = n;
/* 1143 */       this.hashIndex = i;
/* 1144 */       this.last = newCurrent;
/* 1145 */       return newCurrent;
/*      */     }
/*      */     
/*      */     protected AbstractHashedMap.HashEntry<K, V> currentEntry() {
/* 1149 */       return this.last;
/*      */     }
/*      */     
/*      */     public void remove() {
/* 1153 */       if (this.last == null) {
/* 1154 */         throw new IllegalStateException("remove() can only be called once after next()");
/*      */       }
/* 1156 */       if (this.parent.modCount != this.expectedModCount) {
/* 1157 */         throw new ConcurrentModificationException();
/*      */       }
/* 1159 */       this.parent.remove(this.last.getKey());
/* 1160 */       this.last = null;
/* 1161 */       this.expectedModCount = this.parent.modCount;
/*      */     }
/*      */     
/*      */     public String toString() {
/* 1165 */       if (this.last != null) {
/* 1166 */         return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
/*      */       }
/* 1168 */       return "Iterator[]";
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doWriteObject(ObjectOutputStream out)
/*      */     throws IOException
/*      */   {
/* 1194 */     out.writeFloat(this.loadFactor);
/* 1195 */     out.writeInt(this.data.length);
/* 1196 */     out.writeInt(this.size);
/* 1197 */     for (MapIterator it = mapIterator(); it.hasNext();) {
/* 1198 */       out.writeObject(it.next());
/* 1199 */       out.writeObject(it.getValue());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void doReadObject(ObjectInputStream in)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1222 */     this.loadFactor = in.readFloat();
/* 1223 */     int capacity = in.readInt();
/* 1224 */     int size = in.readInt();
/* 1225 */     init();
/* 1226 */     this.data = new HashEntry[capacity];
/* 1227 */     for (int i = 0; i < size; i++) {
/* 1228 */       K key = in.readObject();
/* 1229 */       V value = in.readObject();
/* 1230 */       put(key, value);
/*      */     }
/* 1232 */     this.threshold = calculateThreshold(this.data.length, this.loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object clone()
/*      */   {
/*      */     try
/*      */     {
/* 1246 */       AbstractHashedMap cloned = (AbstractHashedMap)super.clone();
/* 1247 */       cloned.data = new HashEntry[this.data.length];
/* 1248 */       cloned.entrySet = null;
/* 1249 */       cloned.keySet = null;
/* 1250 */       cloned.values = null;
/* 1251 */       cloned.modCount = 0;
/* 1252 */       cloned.size = 0;
/* 1253 */       cloned.init();
/* 1254 */       cloned.putAll(this);
/* 1255 */       return cloned;
/*      */     }
/*      */     catch (CloneNotSupportedException ex) {}
/* 1258 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1269 */     if (obj == this) {
/* 1270 */       return true;
/*      */     }
/* 1272 */     if (!(obj instanceof Map)) {
/* 1273 */       return false;
/*      */     }
/* 1275 */     Map map = (Map)obj;
/* 1276 */     if (map.size() != size()) {
/* 1277 */       return false;
/*      */     }
/* 1279 */     MapIterator it = mapIterator();
/*      */     try {
/* 1281 */       while (it.hasNext()) {
/* 1282 */         Object key = it.next();
/* 1283 */         Object value = it.getValue();
/* 1284 */         if (value == null) {
/* 1285 */           if ((map.get(key) != null) || (!map.containsKey(key))) {
/* 1286 */             return false;
/*      */           }
/*      */         }
/* 1289 */         else if (!value.equals(map.get(key))) {
/* 1290 */           return false;
/*      */         }
/*      */       }
/*      */     }
/*      */     catch (ClassCastException ignored) {
/* 1295 */       return false;
/*      */     } catch (NullPointerException ignored) {
/* 1297 */       return false;
/*      */     }
/* 1299 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1308 */     int total = 0;
/* 1309 */     Iterator it = createEntrySetIterator();
/* 1310 */     while (it.hasNext()) {
/* 1311 */       total += it.next().hashCode();
/*      */     }
/* 1313 */     return total;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1322 */     if (size() == 0) {
/* 1323 */       return "{}";
/*      */     }
/* 1325 */     StringBuffer buf = new StringBuffer(32 * size());
/* 1326 */     buf.append('{');
/*      */     
/* 1328 */     MapIterator it = mapIterator();
/* 1329 */     boolean hasNext = it.hasNext();
/* 1330 */     while (hasNext) {
/* 1331 */       Object key = it.next();
/* 1332 */       Object value = it.getValue();
/* 1333 */       buf.append(key == this ? "(this Map)" : key).append('=').append(value == this ? "(this Map)" : value);
/*      */       
/* 1335 */       hasNext = it.hasNext();
/* 1336 */       if (hasNext) {
/* 1337 */         buf.append(',').append(' ');
/*      */       }
/*      */     }
/*      */     
/* 1341 */     buf.append('}');
/* 1342 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractHashedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */