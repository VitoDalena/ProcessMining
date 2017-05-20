/*      */ package org.apache.commons.collections15.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.lang.ref.Reference;
/*      */ import java.lang.ref.ReferenceQueue;
/*      */ import java.lang.ref.SoftReference;
/*      */ import java.lang.ref.WeakReference;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.ConcurrentModificationException;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections15.MapIterator;
/*      */ import org.apache.commons.collections15.keyvalue.DefaultMapEntry;
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
/*      */ public abstract class AbstractReferenceMap<K, V>
/*      */   extends AbstractHashedMap<K, V>
/*      */ {
/*      */   public static final int HARD = 0;
/*      */   public static final int SOFT = 1;
/*      */   public static final int WEAK = 2;
/*      */   protected int keyType;
/*      */   protected int valueType;
/*      */   protected boolean purgeValues;
/*      */   private transient ReferenceQueue queue;
/*      */   
/*      */   protected AbstractReferenceMap() {}
/*      */   
/*      */   protected AbstractReferenceMap(int keyType, int valueType, int capacity, float loadFactor, boolean purgeValues)
/*      */   {
/*  146 */     super(capacity, loadFactor);
/*  147 */     verify("keyType", keyType);
/*  148 */     verify("valueType", valueType);
/*  149 */     this.keyType = keyType;
/*  150 */     this.valueType = valueType;
/*  151 */     this.purgeValues = purgeValues;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void init()
/*      */   {
/*  158 */     this.queue = new ReferenceQueue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static void verify(String name, int type)
/*      */   {
/*  170 */     if ((type < 0) || (type > 2)) {
/*  171 */       throw new IllegalArgumentException(name + " must be HARD, SOFT, WEAK.");
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  182 */     purgeBeforeRead();
/*  183 */     return super.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  192 */     purgeBeforeRead();
/*  193 */     return super.isEmpty();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsKey(Object key)
/*      */   {
/*  203 */     purgeBeforeRead();
/*  204 */     Map.Entry entry = getEntry(key);
/*  205 */     if (entry == null) {
/*  206 */       return false;
/*      */     }
/*  208 */     return entry.getValue() != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsValue(Object value)
/*      */   {
/*  218 */     purgeBeforeRead();
/*  219 */     if (value == null) {
/*  220 */       return false;
/*      */     }
/*  222 */     return super.containsValue(value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V get(Object key)
/*      */   {
/*  232 */     purgeBeforeRead();
/*  233 */     Map.Entry<K, V> entry = getEntry(key);
/*  234 */     if (entry == null) {
/*  235 */       return null;
/*      */     }
/*  237 */     return (V)entry.getValue();
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
/*      */   public V put(K key, V value)
/*      */   {
/*  251 */     if (key == null) {
/*  252 */       throw new NullPointerException("null keys not allowed");
/*      */     }
/*  254 */     if (value == null) {
/*  255 */       throw new NullPointerException("null values not allowed");
/*      */     }
/*      */     
/*  258 */     purgeBeforeWrite();
/*  259 */     return (V)super.put(key, value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V remove(Object key)
/*      */   {
/*  269 */     if (key == null) {
/*  270 */       return null;
/*      */     }
/*  272 */     purgeBeforeWrite();
/*  273 */     return (V)super.remove(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  280 */     super.clear();
/*  281 */     while (this.queue.poll() != null) {}
/*      */   }
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
/*  293 */     return new ReferenceMapIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Set<Map.Entry<K, V>> entrySet()
/*      */   {
/*  304 */     if (this.entrySet == null) {
/*  305 */       this.entrySet = new ReferenceEntrySet(this);
/*      */     }
/*  307 */     return this.entrySet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Set<K> keySet()
/*      */   {
/*  316 */     if (this.keySet == null) {
/*  317 */       this.keySet = new ReferenceKeySet(this);
/*      */     }
/*  319 */     return this.keySet;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection<V> values()
/*      */   {
/*  328 */     if (this.values == null) {
/*  329 */       this.values = new ReferenceValues(this);
/*      */     }
/*  331 */     return this.values;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void purgeBeforeRead()
/*      */   {
/*  341 */     purge();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void purgeBeforeWrite()
/*      */   {
/*  350 */     purge();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void purge()
/*      */   {
/*  362 */     Reference ref = this.queue.poll();
/*  363 */     while (ref != null) {
/*  364 */       purge(ref);
/*  365 */       ref = this.queue.poll();
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
/*      */   protected void purge(Reference ref)
/*      */   {
/*  378 */     int hash = ref.hashCode();
/*  379 */     int index = hashIndex(hash, this.data.length);
/*  380 */     AbstractHashedMap.HashEntry<K, V> previous = null;
/*  381 */     AbstractHashedMap.HashEntry<K, V> entry = this.data[index];
/*  382 */     while (entry != null) {
/*  383 */       if (((ReferenceEntry)entry).purge(ref)) {
/*  384 */         if (previous == null) {
/*  385 */           this.data[index] = entry.next;
/*      */         } else {
/*  387 */           previous.next = entry.next;
/*      */         }
/*  389 */         this.size -= 1;
/*  390 */         return;
/*      */       }
/*  392 */       previous = entry;
/*  393 */       entry = entry.next;
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
/*      */   protected AbstractHashedMap.HashEntry<K, V> getEntry(Object key)
/*      */   {
/*  406 */     if (key == null) {
/*  407 */       return null;
/*      */     }
/*  409 */     return super.getEntry(key);
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
/*      */   protected int hashEntry(Object key, Object value)
/*      */   {
/*  422 */     return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
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
/*      */   protected boolean isEqualKey(Object key1, Object key2)
/*      */   {
/*  441 */     return (key1 == key2) || (key1.equals(key2));
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
/*      */   public AbstractHashedMap.HashEntry<K, V> createEntry(AbstractHashedMap.HashEntry<K, V> next, int hashCode, K key, V value)
/*      */   {
/*  454 */     return new ReferenceEntry(this, (ReferenceEntry)next, hashCode, key, value);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<Map.Entry<K, V>> createEntrySetIterator()
/*      */   {
/*  463 */     return new ReferenceEntrySetIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<K> createKeySetIterator()
/*      */   {
/*  472 */     return new ReferenceKeySetIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Iterator<V> createValuesIterator()
/*      */   {
/*  481 */     return new ReferenceValuesIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class ReferenceEntrySet<K, V>
/*      */     extends AbstractHashedMap.EntrySet<K, V>
/*      */   {
/*      */     protected ReferenceEntrySet(AbstractHashedMap<K, V> parent)
/*      */     {
/*  491 */       super();
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  495 */       return toArray(new Object[0]);
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] arr)
/*      */     {
/*  500 */       ArrayList<Map.Entry<K, V>> list = new ArrayList();
/*  501 */       Iterator<Map.Entry<K, V>> iterator = iterator();
/*  502 */       while (iterator.hasNext()) {
/*  503 */         Map.Entry<K, V> e = (Map.Entry)iterator.next();
/*  504 */         list.add(new DefaultMapEntry(e.getKey(), e.getValue()));
/*      */       }
/*  506 */       return list.toArray(arr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class ReferenceKeySet<K, V>
/*      */     extends AbstractHashedMap.KeySet<K, V>
/*      */   {
/*      */     protected ReferenceKeySet(AbstractHashedMap<K, V> parent)
/*      */     {
/*  517 */       super();
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  521 */       return toArray(new Object[0]);
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] arr)
/*      */     {
/*  526 */       List<K> list = new ArrayList(this.parent.size());
/*  527 */       for (Iterator<K> it = iterator(); it.hasNext();) {
/*  528 */         list.add(it.next());
/*      */       }
/*  530 */       return list.toArray(arr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class ReferenceValues<K, V>
/*      */     extends AbstractHashedMap.Values<K, V>
/*      */   {
/*      */     protected ReferenceValues(AbstractHashedMap<K, V> parent)
/*      */     {
/*  541 */       super();
/*      */     }
/*      */     
/*      */     public Object[] toArray() {
/*  545 */       return toArray(new Object[0]);
/*      */     }
/*      */     
/*      */     public <T> T[] toArray(T[] arr)
/*      */     {
/*  550 */       List<V> list = new ArrayList(this.parent.size());
/*  551 */       for (Iterator<V> it = iterator(); it.hasNext();) {
/*  552 */         list.add(it.next());
/*      */       }
/*  554 */       return list.toArray(arr);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static class ReferenceEntry<K, V>
/*      */     extends AbstractHashedMap.HashEntry<K, V>
/*      */   {
/*      */     protected final AbstractReferenceMap<K, V> parent;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Reference<K> refKey;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected Reference<V> refValue;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public ReferenceEntry(AbstractReferenceMap<K, V> parent, ReferenceEntry<K, V> next, int hashCode, K key, V value)
/*      */     {
/*  586 */       super(hashCode, null, null);
/*  587 */       this.parent = parent;
/*  588 */       if (parent.keyType != 0) {
/*  589 */         this.refKey = toReference(parent.keyType, key, hashCode);
/*      */       } else {
/*  591 */         setKey(key);
/*      */       }
/*  593 */       if (parent.valueType != 0) {
/*  594 */         this.refValue = toReference(parent.valueType, value, hashCode);
/*      */       } else {
/*  596 */         setValue(value);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public K getKey()
/*      */     {
/*  607 */       return (K)(this.parent.keyType > 0 ? this.refKey.get() : super.getKey());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public V getValue()
/*      */     {
/*  617 */       return (V)(this.parent.valueType > 0 ? this.refValue.get() : super.getValue());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public V setValue(V obj)
/*      */     {
/*  627 */       V old = getValue();
/*  628 */       if (this.parent.valueType > 0) {
/*  629 */         this.refValue.clear();
/*  630 */         this.refValue = toReference(this.parent.valueType, obj, this.hashCode);
/*      */       } else {
/*  632 */         super.setValue(obj);
/*      */       }
/*  634 */       return old;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public boolean equals(Object obj)
/*      */     {
/*  647 */       if (obj == this) {
/*  648 */         return true;
/*      */       }
/*  650 */       if (!(obj instanceof Map.Entry)) {
/*  651 */         return false;
/*      */       }
/*      */       
/*  654 */       Map.Entry entry = (Map.Entry)obj;
/*  655 */       Object entryKey = entry.getKey();
/*  656 */       Object entryValue = entry.getValue();
/*  657 */       if ((entryKey == null) || (entryValue == null)) {
/*  658 */         return false;
/*      */       }
/*      */       
/*      */ 
/*  662 */       return (this.parent.isEqualKey(entryKey, getKey())) && (this.parent.isEqualValue(entryValue, getValue()));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public int hashCode()
/*      */     {
/*  673 */       return this.parent.hashEntry(getKey(), getValue());
/*      */     }
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
/*      */     protected <T> Reference<T> toReference(int type, T referent, int hash)
/*      */     {
/*  687 */       switch (type) {
/*      */       case 1: 
/*  689 */         return new AbstractReferenceMap.SoftRef(hash, referent, this.parent.queue);
/*      */       case 2: 
/*  691 */         return new AbstractReferenceMap.WeakRef(hash, referent, this.parent.queue);
/*      */       }
/*  693 */       throw new Error("Attempt to create hard reference in ReferenceMap!");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     boolean purge(Reference ref)
/*      */     {
/*  704 */       boolean r = (this.parent.keyType > 0) && (this.refKey == ref);
/*  705 */       r = (r) || ((this.parent.valueType > 0) && (this.refValue == ref));
/*  706 */       if (r) {
/*  707 */         if (this.parent.keyType > 0) {
/*  708 */           this.refKey.clear();
/*      */         }
/*  710 */         if (this.parent.valueType > 0) {
/*  711 */           this.refValue.clear();
/*  712 */         } else if (this.parent.purgeValues) {
/*  713 */           setValue(null);
/*      */         }
/*      */       }
/*  716 */       return r;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     protected ReferenceEntry<K, V> next()
/*      */     {
/*  725 */       return (ReferenceEntry)this.next;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class ReferenceIteratorBase<K, V>
/*      */   {
/*      */     final AbstractReferenceMap<K, V> parent;
/*      */     
/*      */ 
/*      */     int index;
/*      */     
/*      */ 
/*      */     AbstractReferenceMap.ReferenceEntry<K, V> entry;
/*      */     
/*      */     AbstractReferenceMap.ReferenceEntry<K, V> previous;
/*      */     
/*      */     K nextKey;
/*      */     
/*      */     V nextValue;
/*      */     
/*      */     K currentKey;
/*      */     
/*      */     V currentValue;
/*      */     
/*      */     int expectedModCount;
/*      */     
/*      */ 
/*      */     public ReferenceIteratorBase(AbstractReferenceMap<K, V> parent)
/*      */     {
/*  756 */       this.parent = parent;
/*  757 */       this.index = (parent.size() != 0 ? parent.data.length : 0);
/*      */       
/*      */ 
/*  760 */       this.expectedModCount = parent.modCount;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  764 */       checkMod();
/*  765 */       while (nextNull()) {
/*  766 */         AbstractReferenceMap.ReferenceEntry<K, V> e = this.entry;
/*  767 */         int i = this.index;
/*  768 */         while ((e == null) && (i > 0)) {
/*  769 */           i--;
/*  770 */           e = (AbstractReferenceMap.ReferenceEntry)this.parent.data[i];
/*      */         }
/*  772 */         this.entry = e;
/*  773 */         this.index = i;
/*  774 */         if (e == null) {
/*  775 */           this.currentKey = null;
/*  776 */           this.currentValue = null;
/*  777 */           return false;
/*      */         }
/*  779 */         this.nextKey = e.getKey();
/*  780 */         this.nextValue = e.getValue();
/*  781 */         if (nextNull()) {
/*  782 */           this.entry = this.entry.next();
/*      */         }
/*      */       }
/*  785 */       return true;
/*      */     }
/*      */     
/*      */     private void checkMod() {
/*  789 */       if (this.parent.modCount != this.expectedModCount) {
/*  790 */         throw new ConcurrentModificationException();
/*      */       }
/*      */     }
/*      */     
/*      */     private boolean nextNull() {
/*  795 */       return (this.nextKey == null) || (this.nextValue == null);
/*      */     }
/*      */     
/*      */     protected AbstractReferenceMap.ReferenceEntry<K, V> nextEntry() {
/*  799 */       checkMod();
/*  800 */       if ((nextNull()) && (!hasNext())) {
/*  801 */         throw new NoSuchElementException();
/*      */       }
/*  803 */       this.previous = this.entry;
/*  804 */       this.entry = this.entry.next();
/*  805 */       this.currentKey = this.nextKey;
/*  806 */       this.currentValue = this.nextValue;
/*  807 */       this.nextKey = null;
/*  808 */       this.nextValue = null;
/*  809 */       return this.previous;
/*      */     }
/*      */     
/*      */     protected AbstractReferenceMap.ReferenceEntry<K, V> currentEntry() {
/*  813 */       checkMod();
/*  814 */       return this.previous;
/*      */     }
/*      */     
/*      */     public AbstractReferenceMap.ReferenceEntry<K, V> superNext() {
/*  818 */       return nextEntry();
/*      */     }
/*      */     
/*      */     public void remove() {
/*  822 */       checkMod();
/*  823 */       if (this.previous == null) {
/*  824 */         throw new IllegalStateException();
/*      */       }
/*  826 */       this.parent.remove(this.currentKey);
/*  827 */       this.previous = null;
/*  828 */       this.currentKey = null;
/*  829 */       this.currentValue = null;
/*  830 */       this.expectedModCount = this.parent.modCount;
/*      */     }
/*      */   }
/*      */   
/*      */   static class ReferenceEntrySetIterator<K, V>
/*      */     extends AbstractReferenceMap.ReferenceIteratorBase<K, V>
/*      */     implements Iterator<Map.Entry<K, V>>
/*      */   {
/*      */     public ReferenceEntrySetIterator(AbstractReferenceMap<K, V> abstractReferenceMap)
/*      */     {
/*  840 */       super();
/*      */     }
/*      */     
/*      */     public AbstractReferenceMap.ReferenceEntry<K, V> next() {
/*  844 */       return superNext();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class ReferenceKeySetIterator<K, V>
/*      */     extends AbstractReferenceMap.ReferenceIteratorBase<K, V>
/*      */     implements Iterator<K>
/*      */   {
/*      */     ReferenceKeySetIterator(AbstractReferenceMap<K, V> parent)
/*      */     {
/*  855 */       super();
/*      */     }
/*      */     
/*      */     public K next() {
/*  859 */       return (K)nextEntry().getKey();
/*      */     }
/*      */   }
/*      */   
/*      */   static class ReferenceValuesIterator<K, V>
/*      */     extends AbstractReferenceMap.ReferenceIteratorBase<K, V>
/*      */     implements Iterator<V>
/*      */   {
/*      */     ReferenceValuesIterator(AbstractReferenceMap<K, V> parent)
/*      */     {
/*  869 */       super();
/*      */     }
/*      */     
/*      */     public V next() {
/*  873 */       return (V)nextEntry().getValue();
/*      */     }
/*      */   }
/*      */   
/*      */   static class ReferenceMapIterator<K, V>
/*      */     extends AbstractReferenceMap.ReferenceIteratorBase<K, V>
/*      */     implements MapIterator<K, V>
/*      */   {
/*      */     protected ReferenceMapIterator(AbstractReferenceMap<K, V> parent)
/*      */     {
/*  883 */       super();
/*      */     }
/*      */     
/*      */     public K next() {
/*  887 */       return (K)nextEntry().getKey();
/*      */     }
/*      */     
/*      */     public K getKey() {
/*  891 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/*  892 */       if (current == null) {
/*  893 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  895 */       return (K)current.getKey();
/*      */     }
/*      */     
/*      */     public V getValue() {
/*  899 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/*  900 */       if (current == null) {
/*  901 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  903 */       return (V)current.getValue();
/*      */     }
/*      */     
/*      */     public V setValue(V value) {
/*  907 */       AbstractHashedMap.HashEntry<K, V> current = currentEntry();
/*  908 */       if (current == null) {
/*  909 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  911 */       return (V)current.setValue(value);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   static class SoftRef<T>
/*      */     extends SoftReference<T>
/*      */   {
/*      */     private int hash;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public SoftRef(int hash, T r, ReferenceQueue q)
/*      */     {
/*  930 */       super(q);
/*  931 */       this.hash = hash;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  935 */       return this.hash;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   static class WeakRef<T>
/*      */     extends WeakReference<T>
/*      */   {
/*      */     private int hash;
/*      */     
/*      */ 
/*      */     public WeakRef(int hash, T r, ReferenceQueue q)
/*      */     {
/*  949 */       super(q);
/*  950 */       this.hash = hash;
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  954 */       return this.hash;
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
/*      */   protected void doWriteObject(ObjectOutputStream out)
/*      */     throws IOException
/*      */   {
/*  978 */     out.writeInt(this.keyType);
/*  979 */     out.writeInt(this.valueType);
/*  980 */     out.writeBoolean(this.purgeValues);
/*  981 */     out.writeFloat(this.loadFactor);
/*  982 */     out.writeInt(this.data.length);
/*  983 */     for (MapIterator it = mapIterator(); it.hasNext();) {
/*  984 */       out.writeObject(it.next());
/*  985 */       out.writeObject(it.getValue());
/*      */     }
/*  987 */     out.writeObject(null);
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
/* 1009 */     this.keyType = in.readInt();
/* 1010 */     this.valueType = in.readInt();
/* 1011 */     this.purgeValues = in.readBoolean();
/* 1012 */     this.loadFactor = in.readFloat();
/* 1013 */     int capacity = in.readInt();
/* 1014 */     init();
/* 1015 */     this.data = new AbstractHashedMap.HashEntry[capacity];
/*      */     for (;;) {
/* 1017 */       K key = in.readObject();
/* 1018 */       if (key == null) {
/*      */         break;
/*      */       }
/* 1021 */       V value = in.readObject();
/* 1022 */       put(key, value);
/*      */     }
/* 1024 */     this.threshold = calculateThreshold(this.data.length, this.loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/AbstractReferenceMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */