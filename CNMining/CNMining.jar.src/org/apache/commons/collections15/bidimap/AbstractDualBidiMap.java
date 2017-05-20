/*     */ package org.apache.commons.collections15.bidimap;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.BidiMap;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ import org.apache.commons.collections15.collection.AbstractCollectionDecorator;
/*     */ import org.apache.commons.collections15.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections15.keyvalue.AbstractMapEntryDecorator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractDualBidiMap<K, V>
/*     */   implements BidiMap<K, V>
/*     */ {
/*     */   protected transient Map<K, V> forwardMap;
/*     */   protected transient Map<V, K> inverseMap;
/*  56 */   protected transient BidiMap<V, K> inverseBidiMap = null;
/*     */   
/*     */ 
/*     */ 
/*  60 */   protected transient Set<K> keySet = null;
/*     */   
/*     */ 
/*     */ 
/*  64 */   protected transient Set<V> values = null;
/*     */   
/*     */ 
/*     */ 
/*  68 */   protected transient Set<Map.Entry<K, V>> entrySet = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected AbstractDualBidiMap()
/*     */   {
/*  81 */     this.forwardMap = createMap();
/*  82 */     this.inverseMap = createMap();
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
/*     */   protected AbstractDualBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap)
/*     */   {
/* 101 */     this.forwardMap = normalMap;
/* 102 */     this.inverseMap = reverseMap;
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
/*     */   protected AbstractDualBidiMap(Map<K, V> normalMap, Map<V, K> reverseMap, BidiMap<V, K> inverseBidiMap)
/*     */   {
/* 115 */     this.forwardMap = normalMap;
/* 116 */     this.inverseMap = reverseMap;
/* 117 */     this.inverseBidiMap = inverseBidiMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   protected Map createMap()
/*     */   {
/* 131 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract <K, V> BidiMap<K, V> createBidiMap(Map<K, V> paramMap, Map<V, K> paramMap1, BidiMap<V, K> paramBidiMap);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V get(Object key)
/*     */   {
/* 147 */     return (V)this.forwardMap.get(key);
/*     */   }
/*     */   
/*     */   public int size() {
/* 151 */     return this.forwardMap.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 155 */     return this.forwardMap.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean containsKey(Object key) {
/* 159 */     return this.forwardMap.containsKey(key);
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 163 */     return this.forwardMap.equals(obj);
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 167 */     return this.forwardMap.hashCode();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 171 */     return this.forwardMap.toString();
/*     */   }
/*     */   
/*     */ 
/*     */   public V put(K key, V value)
/*     */   {
/* 177 */     if (this.forwardMap.containsKey(key)) {
/* 178 */       this.inverseMap.remove(this.forwardMap.get(key));
/*     */     }
/* 180 */     if (this.inverseMap.containsKey(value)) {
/* 181 */       this.forwardMap.remove(this.inverseMap.get(value));
/*     */     }
/* 183 */     V obj = this.forwardMap.put(key, value);
/* 184 */     this.inverseMap.put(value, key);
/* 185 */     return obj;
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> map) {
/* 189 */     for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
/* 190 */       Map.Entry entry = (Map.Entry)it.next();
/* 191 */       put(entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 196 */     V value = null;
/* 197 */     if (this.forwardMap.containsKey(key)) {
/* 198 */       value = this.forwardMap.remove(key);
/* 199 */       this.inverseMap.remove(value);
/*     */     }
/* 201 */     return value;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 205 */     this.forwardMap.clear();
/* 206 */     this.inverseMap.clear();
/*     */   }
/*     */   
/*     */   public boolean containsValue(Object value) {
/* 210 */     return this.inverseMap.containsKey(value);
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
/*     */   public MapIterator<K, V> mapIterator()
/*     */   {
/* 227 */     return new BidiMapIterator(this);
/*     */   }
/*     */   
/*     */   public K getKey(Object value) {
/* 231 */     return (K)this.inverseMap.get(value);
/*     */   }
/*     */   
/*     */   public K removeValue(Object value) {
/* 235 */     K key = null;
/* 236 */     if (this.inverseMap.containsKey(value)) {
/* 237 */       key = this.inverseMap.remove(value);
/* 238 */       this.forwardMap.remove(key);
/*     */     }
/* 240 */     return key;
/*     */   }
/*     */   
/*     */   public BidiMap<V, K> inverseBidiMap() {
/* 244 */     if (this.inverseBidiMap == null) {
/* 245 */       this.inverseBidiMap = createBidiMap(this.inverseMap, this.forwardMap, this);
/*     */     }
/* 247 */     return this.inverseBidiMap;
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
/*     */   public Set<K> keySet()
/*     */   {
/* 260 */     if (this.keySet == null) {
/* 261 */       this.keySet = new KeySet(this);
/*     */     }
/* 263 */     return this.keySet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator<K> createKeySetIterator(Iterator<K> iterator)
/*     */   {
/* 274 */     return new KeySetIterator(iterator, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<V> values()
/*     */   {
/* 285 */     if (this.values == null) {
/* 286 */       this.values = new Values(this);
/*     */     }
/* 288 */     return this.values;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator<V> createValuesIterator(Iterator<V> iterator)
/*     */   {
/* 299 */     return new ValuesIterator(iterator, this);
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
/*     */   public Set<Map.Entry<K, V>> entrySet()
/*     */   {
/* 314 */     if (this.entrySet == null) {
/* 315 */       this.entrySet = new EntrySet(this);
/*     */     }
/* 317 */     return this.entrySet;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Iterator<Map.Entry<K, V>> createEntrySetIterator(Iterator<Map.Entry<K, V>> iterator)
/*     */   {
/* 328 */     return new EntrySetIterator(iterator, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static abstract class View<K, V, E>
/*     */     extends AbstractCollectionDecorator<E>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected View(Collection<E> coll, AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 349 */       super();
/* 350 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public boolean removeAll(Collection<?> coll) {
/* 354 */       if ((this.parent.isEmpty()) || (coll.isEmpty())) {
/* 355 */         return false;
/*     */       }
/* 357 */       boolean modified = false;
/* 358 */       Iterator it = iterator();
/* 359 */       while (it.hasNext()) {
/* 360 */         if (coll.contains(it.next())) {
/* 361 */           it.remove();
/* 362 */           modified = true;
/*     */         }
/*     */       }
/* 365 */       return modified;
/*     */     }
/*     */     
/*     */     public boolean retainAll(Collection<?> coll) {
/* 369 */       if (this.parent.isEmpty()) {
/* 370 */         return false;
/*     */       }
/* 372 */       if (coll.isEmpty()) {
/* 373 */         this.parent.clear();
/* 374 */         return true;
/*     */       }
/* 376 */       boolean modified = false;
/* 377 */       Iterator it = iterator();
/* 378 */       while (it.hasNext()) {
/* 379 */         if (!coll.contains(it.next())) {
/* 380 */           it.remove();
/* 381 */           modified = true;
/*     */         }
/*     */       }
/* 384 */       return modified;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 388 */       this.parent.clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class KeySet<K, V>
/*     */     extends AbstractDualBidiMap.View<K, V, K>
/*     */     implements Set<K>
/*     */   {
/*     */     protected KeySet(AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 404 */       super(parent);
/*     */     }
/*     */     
/*     */     public Iterator<K> iterator() {
/* 408 */       return this.parent.createKeySetIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean contains(Object key) {
/* 412 */       return this.parent.forwardMap.containsKey(key);
/*     */     }
/*     */     
/*     */     public boolean remove(Object key) {
/* 416 */       if (this.parent.forwardMap.containsKey(key)) {
/* 417 */         Object value = this.parent.forwardMap.remove(key);
/* 418 */         this.parent.inverseMap.remove(value);
/* 419 */         return true;
/*     */       }
/* 421 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class KeySetIterator<K, V>
/*     */     extends AbstractIteratorDecorator<K>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 437 */     protected K lastKey = null;
/*     */     
/*     */ 
/*     */ 
/* 441 */     protected boolean canRemove = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected KeySetIterator(Iterator<K> iterator, AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 450 */       super();
/* 451 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public K next() {
/* 455 */       this.lastKey = super.next();
/* 456 */       this.canRemove = true;
/* 457 */       return (K)this.lastKey;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 461 */       if (!this.canRemove) {
/* 462 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/* 464 */       Object value = this.parent.forwardMap.get(this.lastKey);
/* 465 */       super.remove();
/* 466 */       this.parent.inverseMap.remove(value);
/* 467 */       this.lastKey = null;
/* 468 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class Values<K, V>
/*     */     extends AbstractDualBidiMap.View<K, V, V>
/*     */     implements Set<V>
/*     */   {
/*     */     protected Values(AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 484 */       super(parent);
/*     */     }
/*     */     
/*     */     public Iterator<V> iterator() {
/* 488 */       return this.parent.createValuesIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 492 */       return this.parent.inverseMap.containsKey(value);
/*     */     }
/*     */     
/*     */     public boolean remove(Object value) {
/* 496 */       if (this.parent.inverseMap.containsKey(value)) {
/* 497 */         Object key = this.parent.inverseMap.remove(value);
/* 498 */         this.parent.forwardMap.remove(key);
/* 499 */         return true;
/*     */       }
/* 501 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class ValuesIterator<K, V>
/*     */     extends AbstractIteratorDecorator<V>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 517 */     protected V lastValue = null;
/*     */     
/*     */ 
/*     */ 
/* 521 */     protected boolean canRemove = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected ValuesIterator(Iterator<V> iterator, AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 530 */       super();
/* 531 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public V next() {
/* 535 */       this.lastValue = super.next();
/* 536 */       this.canRemove = true;
/* 537 */       return (V)this.lastValue;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 541 */       if (!this.canRemove) {
/* 542 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/* 544 */       super.remove();
/* 545 */       this.parent.inverseMap.remove(this.lastValue);
/* 546 */       this.lastValue = null;
/* 547 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class EntrySet<K, V>
/*     */     extends AbstractDualBidiMap.View<K, V, Map.Entry<K, V>>
/*     */     implements Set<Map.Entry<K, V>>
/*     */   {
/*     */     protected EntrySet(AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 563 */       super(parent);
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 567 */       return this.parent.createEntrySetIterator(super.iterator());
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 571 */       if (!(obj instanceof Map.Entry)) {
/* 572 */         return false;
/*     */       }
/* 574 */       Map.Entry entry = (Map.Entry)obj;
/* 575 */       Object key = entry.getKey();
/* 576 */       if (this.parent.containsKey(key)) {
/* 577 */         Object value = this.parent.forwardMap.get(key);
/* 578 */         if (value == null ? entry.getValue() == null : value.equals(entry.getValue())) {
/* 579 */           this.parent.forwardMap.remove(key);
/* 580 */           this.parent.inverseMap.remove(value);
/* 581 */           return true;
/*     */         }
/*     */       }
/* 584 */       return false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class EntrySetIterator<K, V>
/*     */     extends AbstractIteratorDecorator<Map.Entry<K, V>>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 600 */     protected Map.Entry<K, V> last = null;
/*     */     
/*     */ 
/*     */ 
/* 604 */     protected boolean canRemove = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected EntrySetIterator(Iterator<Map.Entry<K, V>> iterator, AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 613 */       super();
/* 614 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 618 */       this.last = new AbstractDualBidiMap.MapEntry((Map.Entry)super.next(), this.parent);
/* 619 */       this.canRemove = true;
/* 620 */       return this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 624 */       if (!this.canRemove) {
/* 625 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/*     */       
/* 628 */       Object value = this.last.getValue();
/* 629 */       super.remove();
/* 630 */       this.parent.inverseMap.remove(value);
/* 631 */       this.last = null;
/* 632 */       this.canRemove = false;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class MapEntry<K, V>
/*     */     extends AbstractMapEntryDecorator<K, V>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected MapEntry(Map.Entry<K, V> entry, AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 653 */       super();
/* 654 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 658 */       K key = getKey();
/* 659 */       if ((this.parent.inverseMap.containsKey(value)) && (this.parent.inverseMap.get(value) != key)) {
/* 660 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
/*     */       }
/* 662 */       this.parent.put(key, value);
/* 663 */       V oldValue = super.setValue(value);
/* 664 */       return oldValue;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static class BidiMapIterator<K, V>
/*     */     implements MapIterator<K, V>, ResettableIterator<K>
/*     */   {
/*     */     protected final AbstractDualBidiMap<K, V> parent;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     protected Iterator<Map.Entry<K, V>> iterator;
/*     */     
/*     */ 
/*     */ 
/* 684 */     protected Map.Entry<K, V> last = null;
/*     */     
/*     */ 
/*     */ 
/* 688 */     protected boolean canRemove = false;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     protected BidiMapIterator(AbstractDualBidiMap<K, V> parent)
/*     */     {
/* 697 */       this.parent = parent;
/* 698 */       this.iterator = parent.forwardMap.entrySet().iterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 702 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public K next() {
/* 706 */       this.last = ((Map.Entry)this.iterator.next());
/* 707 */       this.canRemove = true;
/* 708 */       return (K)this.last.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 712 */       if (!this.canRemove) {
/* 713 */         throw new IllegalStateException("Iterator remove() can only be called once after next()");
/*     */       }
/*     */       
/* 716 */       V value = this.last.getValue();
/* 717 */       this.iterator.remove();
/* 718 */       this.parent.inverseMap.remove(value);
/* 719 */       this.last = null;
/* 720 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 724 */       if (this.last == null) {
/* 725 */         throw new IllegalStateException("Iterator getKey() can only be called after next() and before remove()");
/*     */       }
/* 727 */       return (K)this.last.getKey();
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 731 */       if (this.last == null) {
/* 732 */         throw new IllegalStateException("Iterator getValue() can only be called after next() and before remove()");
/*     */       }
/* 734 */       return (V)this.last.getValue();
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 738 */       if (this.last == null) {
/* 739 */         throw new IllegalStateException("Iterator setValue() can only be called after next() and before remove()");
/*     */       }
/* 741 */       if ((this.parent.inverseMap.containsKey(value)) && (this.parent.inverseMap.get(value) != this.last.getKey())) {
/* 742 */         throw new IllegalArgumentException("Cannot use setValue() when the object being set is already in the map");
/*     */       }
/* 744 */       return (V)this.parent.put(this.last.getKey(), value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 748 */       this.iterator = this.parent.forwardMap.entrySet().iterator();
/* 749 */       this.last = null;
/* 750 */       this.canRemove = false;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 754 */       if (this.last != null) {
/* 755 */         return "MapIterator[" + getKey() + "=" + getValue() + "]";
/*     */       }
/* 757 */       return "MapIterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/bidimap/AbstractDualBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */