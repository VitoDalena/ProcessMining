/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.util.ConcurrentModificationException;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.OrderedIterator;
/*     */ import org.apache.commons.collections15.OrderedMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyOrderedIterator;
/*     */ import org.apache.commons.collections15.iterators.EmptyOrderedMapIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AbstractLinkedMap<K, V>
/*     */   extends AbstractHashedMap<K, V>
/*     */   implements OrderedMap<K, V>
/*     */ {
/*     */   protected transient LinkEntry<K, V> header;
/*     */   
/*     */   protected AbstractLinkedMap() {}
/*     */   
/*     */   protected AbstractLinkedMap(int initialCapacity, float loadFactor, int threshold)
/*     */   {
/*  83 */     super(initialCapacity, loadFactor, threshold);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractLinkedMap(int initialCapacity)
/*     */   {
/*  93 */     super(initialCapacity);
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
/*     */   protected AbstractLinkedMap(int initialCapacity, float loadFactor)
/*     */   {
/* 106 */     super(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected AbstractLinkedMap(Map<? extends K, ? extends V> map)
/*     */   {
/* 116 */     super(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void init()
/*     */   {
/* 123 */     this.header = new LinkEntry(null, -1, null, null);
/* 124 */     this.header.before = (this.header.after = this.header);
/*     */   }
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
/* 136 */     if (value == null) {
/* 137 */       for (LinkEntry entry = this.header.after; entry != this.header; entry = entry.after) {
/* 138 */         if (entry.getValue() == null) {
/* 139 */           return true;
/*     */         }
/*     */       }
/*     */     } else {
/* 143 */       for (LinkEntry entry = this.header.after; entry != this.header; entry = entry.after) {
/* 144 */         if (isEqualValue(value, entry.getValue())) {
/* 145 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 149 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 158 */     super.clear();
/* 159 */     this.header.before = (this.header.after = this.header);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K firstKey()
/*     */   {
/* 169 */     if (this.size == 0) {
/* 170 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 172 */     return (K)this.header.after.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K lastKey()
/*     */   {
/* 181 */     if (this.size == 0) {
/* 182 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 184 */     return (K)this.header.before.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K nextKey(K key)
/*     */   {
/* 194 */     LinkEntry<K, V> entry = (LinkEntry)getEntry(key);
/* 195 */     return (entry == null) || (entry.after == this.header) ? null : entry.after.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K previousKey(K key)
/*     */   {
/* 205 */     LinkEntry<K, V> entry = (LinkEntry)getEntry(key);
/* 206 */     return (entry == null) || (entry.before == this.header) ? null : entry.before.getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected LinkEntry<K, V> getEntry(int index)
/*     */   {
/* 218 */     if (index < 0) {
/* 219 */       throw new IndexOutOfBoundsException("Index " + index + " is less than zero");
/*     */     }
/* 221 */     if (index >= this.size) {
/* 222 */       throw new IndexOutOfBoundsException("Index " + index + " is invalid for size " + this.size);
/*     */     }
/*     */     LinkEntry<K, V> entry;
/* 225 */     if (index < this.size / 2)
/*     */     {
/* 227 */       LinkEntry<K, V> entry = this.header.after;
/* 228 */       for (int currentIndex = 0; currentIndex < index; currentIndex++) {
/* 229 */         entry = entry.after;
/*     */       }
/*     */     }
/*     */     else {
/* 233 */       entry = this.header;
/* 234 */       for (int currentIndex = this.size; currentIndex > index; currentIndex--) {
/* 235 */         entry = entry.before;
/*     */       }
/*     */     }
/* 238 */     return entry;
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
/*     */   protected void addEntry(AbstractHashedMap.HashEntry<K, V> entry, int hashIndex)
/*     */   {
/* 251 */     LinkEntry<K, V> link = (LinkEntry)entry;
/* 252 */     link.after = this.header;
/* 253 */     link.before = this.header.before;
/* 254 */     this.header.before.after = link;
/* 255 */     this.header.before = link;
/* 256 */     this.data[hashIndex] = entry;
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
/*     */   protected AbstractHashedMap.HashEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value)
/*     */   {
/* 271 */     return new LinkEntry(next, hashCode, key, value);
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
/*     */   protected void removeEntry(AbstractHashedMap.HashEntry<K, V> entry, int hashIndex, AbstractHashedMap.HashEntry<K, V> previous)
/*     */   {
/* 285 */     LinkEntry<K, V> link = (LinkEntry)entry;
/* 286 */     link.before.after = link.after;
/* 287 */     link.after.before = link.before;
/* 288 */     link.after = null;
/* 289 */     link.before = null;
/* 290 */     super.removeEntry(entry, hashIndex, previous);
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
/*     */   protected LinkEntry<K, V> entryBefore(LinkEntry<K, V> entry)
/*     */   {
/* 304 */     return entry.before;
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
/*     */   protected LinkEntry<K, V> entryAfter(LinkEntry<K, V> entry)
/*     */   {
/* 317 */     return entry.after;
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
/*     */   public MapIterator<K, V> mapIterator()
/*     */   {
/* 332 */     if (this.size == 0) {
/* 333 */       return EmptyOrderedMapIterator.INSTANCE;
/*     */     }
/* 335 */     return new LinkMapIterator(this);
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
/*     */   public OrderedMapIterator<K, V> orderedMapIterator()
/*     */   {
/* 349 */     if (this.size == 0) {
/* 350 */       return EmptyOrderedMapIterator.INSTANCE;
/*     */     }
/* 352 */     return new LinkMapIterator(this);
/*     */   }
/*     */   
/*     */   protected static class LinkMapIterator<K, V>
/*     */     extends AbstractLinkedMap.LinkIterator<K, V>
/*     */     implements OrderedMapIterator<K, V>, OrderedIterator<K>, ResettableIterator<K>
/*     */   {
/*     */     protected LinkMapIterator(AbstractLinkedMap<K, V> parent)
/*     */     {
/* 361 */       super();
/*     */     }
/*     */     
/*     */     public K next() {
/* 365 */       return (K)super.nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public K previous() {
/* 369 */       return (K)super.previousEntry().getKey();
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 373 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/* 374 */       if (current == null) {
/* 375 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 377 */       return (K)current.getKey();
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 381 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/* 382 */       if (current == null) {
/* 383 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 385 */       return (V)current.getValue();
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 389 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/* 390 */       if (current == null) {
/* 391 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 393 */       return (V)current.setValue(value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator<Map.Entry<K, V>> createEntrySetIterator()
/*     */   {
/* 405 */     if (size() == 0) {
/* 406 */       return EmptyOrderedIterator.INSTANCE;
/*     */     }
/* 408 */     return new EntrySetIterator(this);
/*     */   }
/*     */   
/*     */   protected static class EntrySetIterator<K, V>
/*     */     extends AbstractLinkedMap.LinkIterator<K, V>
/*     */     implements OrderedIterator<Map.Entry<K, V>>, ResettableIterator<Map.Entry<K, V>>
/*     */   {
/*     */     protected EntrySetIterator(AbstractLinkedMap<K, V> parent)
/*     */     {
/* 417 */       super();
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 421 */       return super.nextEntry();
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> previous() {
/* 425 */       return super.previousEntry();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator createKeySetIterator()
/*     */   {
/* 437 */     if (size() == 0) {
/* 438 */       return EmptyOrderedIterator.INSTANCE;
/*     */     }
/* 440 */     return new KeySetIterator(this);
/*     */   }
/*     */   
/*     */   protected static class KeySetIterator<K, V>
/*     */     extends AbstractLinkedMap.LinkIterator<K, V>
/*     */     implements OrderedIterator<K>, ResettableIterator<K>
/*     */   {
/*     */     protected KeySetIterator(AbstractLinkedMap<K, V> parent)
/*     */     {
/* 449 */       super();
/*     */     }
/*     */     
/*     */     public K next() {
/* 453 */       return (K)super.nextEntry().getKey();
/*     */     }
/*     */     
/*     */     public K previous() {
/* 457 */       return (K)super.previousEntry().getKey();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator<V> createValuesIterator()
/*     */   {
/* 469 */     if (size() == 0) {
/* 470 */       return EmptyOrderedIterator.INSTANCE;
/*     */     }
/* 472 */     return new ValuesIterator(this);
/*     */   }
/*     */   
/*     */   protected static class ValuesIterator<K, V>
/*     */     extends AbstractLinkedMap.LinkIterator<K, V>
/*     */     implements OrderedIterator<V>, ResettableIterator<V>
/*     */   {
/*     */     protected ValuesIterator(AbstractLinkedMap<K, V> parent)
/*     */     {
/* 481 */       super();
/*     */     }
/*     */     
/*     */     public V next() {
/* 485 */       return (V)super.nextEntry().getValue();
/*     */     }
/*     */     
/*     */     public V previous() {
/* 489 */       return (V)super.previousEntry().getValue();
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
/*     */   protected static class LinkEntry<K, V>
/*     */     extends AbstractHashedMap.HashEntry<K, V>
/*     */   {
/*     */     protected LinkEntry<K, V> before;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected LinkEntry<K, V> after;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected LinkEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value)
/*     */     {
/* 521 */       super(hashCode, key, value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static abstract class LinkIterator<K, V>
/*     */   {
/*     */     protected final AbstractLinkedMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */     protected AbstractLinkedMap.LinkEntry<K, V> last;
/*     */     
/*     */ 
/*     */ 
/*     */     protected AbstractLinkedMap.LinkEntry<K, V> next;
/*     */     
/*     */ 
/*     */ 
/*     */     protected int expectedModCount;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected LinkIterator(AbstractLinkedMap<K, V> parent)
/*     */     {
/* 549 */       this.parent = parent;
/* 550 */       this.next = parent.header.after;
/* 551 */       this.expectedModCount = parent.modCount;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 555 */       return this.next != this.parent.header;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 559 */       return this.next.before != this.parent.header;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry<K, V> nextEntry() {
/* 563 */       if (this.parent.modCount != this.expectedModCount) {
/* 564 */         throw new ConcurrentModificationException();
/*     */       }
/* 566 */       if (this.next == this.parent.header) {
/* 567 */         throw new NoSuchElementException("No next() entry in the iteration");
/*     */       }
/* 569 */       this.last = this.next;
/* 570 */       this.next = this.next.after;
/* 571 */       return this.last;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry<K, V> previousEntry() {
/* 575 */       if (this.parent.modCount != this.expectedModCount) {
/* 576 */         throw new ConcurrentModificationException();
/*     */       }
/* 578 */       AbstractLinkedMap.LinkEntry<K, V> previous = this.next.before;
/* 579 */       if (previous == this.parent.header) {
/* 580 */         throw new NoSuchElementException("No previous() entry in the iteration");
/*     */       }
/* 582 */       this.next = previous;
/* 583 */       this.last = previous;
/* 584 */       return this.last;
/*     */     }
/*     */     
/*     */     protected AbstractLinkedMap.LinkEntry<K, V> currentEntry() {
/* 588 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 592 */       if (this.last == null) {
/* 593 */         throw new IllegalStateException("remove() can only be called once after next()");
/*     */       }
/* 595 */       if (this.parent.modCount != this.expectedModCount) {
/* 596 */         throw new ConcurrentModificationException();
/*     */       }
/* 598 */       this.parent.remove(this.last.getKey());
/* 599 */       this.last = null;
/* 600 */       this.expectedModCount = this.parent.modCount;
/*     */     }
/*     */     
/*     */     public void reset() {
/* 604 */       this.last = null;
/* 605 */       this.next = this.parent.header.after;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 609 */       if (this.last != null) {
/* 610 */         return "Iterator[" + this.last.getKey() + "=" + this.last.getValue() + "]";
/*     */       }
/* 612 */       return "Iterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractLinkedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */