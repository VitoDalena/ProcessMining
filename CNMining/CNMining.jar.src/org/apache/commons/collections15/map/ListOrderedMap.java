/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractCollection;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.OrderedMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ import org.apache.commons.collections15.iterators.AbstractIteratorDecorator;
/*     */ import org.apache.commons.collections15.keyvalue.AbstractMapEntry;
/*     */ import org.apache.commons.collections15.list.UnmodifiableList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ListOrderedMap<K, V>
/*     */   extends AbstractMapDecorator<K, V>
/*     */   implements OrderedMap<K, V>, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2728177751851003750L;
/*  63 */   protected final List<K> insertOrder = new ArrayList();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <K, V> OrderedMap<K, V> decorate(Map<K, V> map)
/*     */   {
/*  74 */     return new ListOrderedMap(map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ListOrderedMap()
/*     */   {
/*  85 */     this(new HashMap());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ListOrderedMap(Map<K, V> map)
/*     */   {
/*  95 */     super(map);
/*  96 */     this.insertOrder.addAll(getMap().keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream out)
/*     */     throws IOException
/*     */   {
/* 108 */     out.defaultWriteObject();
/* 109 */     out.writeObject(this.map);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 121 */     in.defaultReadObject();
/* 122 */     this.map = ((Map)in.readObject());
/*     */   }
/*     */   
/*     */ 
/*     */   public MapIterator<K, V> mapIterator()
/*     */   {
/* 128 */     return orderedMapIterator();
/*     */   }
/*     */   
/*     */   public OrderedMapIterator<K, V> orderedMapIterator() {
/* 132 */     return new ListOrderedMapIterator(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K firstKey()
/*     */   {
/* 142 */     if (size() == 0) {
/* 143 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 145 */     return (K)this.insertOrder.get(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K lastKey()
/*     */   {
/* 155 */     if (size() == 0) {
/* 156 */       throw new NoSuchElementException("Map is empty");
/*     */     }
/* 158 */     return (K)this.insertOrder.get(size() - 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K nextKey(K key)
/*     */   {
/* 169 */     int index = this.insertOrder.indexOf(key);
/* 170 */     if ((index >= 0) && (index < size() - 1)) {
/* 171 */       return (K)this.insertOrder.get(index + 1);
/*     */     }
/* 173 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K previousKey(K key)
/*     */   {
/* 184 */     int index = this.insertOrder.indexOf(key);
/* 185 */     if (index > 0) {
/* 186 */       return (K)this.insertOrder.get(index - 1);
/*     */     }
/* 188 */     return null;
/*     */   }
/*     */   
/*     */   public V put(K key, V value)
/*     */   {
/* 193 */     if (getMap().containsKey(key))
/*     */     {
/* 195 */       return (V)getMap().put(key, value);
/*     */     }
/*     */     
/* 198 */     V result = getMap().put(key, value);
/* 199 */     this.insertOrder.add(key);
/* 200 */     return result;
/*     */   }
/*     */   
/*     */   public void putAll(Map<? extends K, ? extends V> map)
/*     */   {
/* 205 */     for (Iterator it = map.entrySet().iterator(); it.hasNext();) {
/* 206 */       Map.Entry entry = (Map.Entry)it.next();
/* 207 */       put(entry.getKey(), entry.getValue());
/*     */     }
/*     */   }
/*     */   
/*     */   public V remove(Object key) {
/* 212 */     V result = getMap().remove(key);
/* 213 */     this.insertOrder.remove(key);
/* 214 */     return result;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 218 */     getMap().clear();
/* 219 */     this.insertOrder.clear();
/*     */   }
/*     */   
/*     */   public Set<K> keySet()
/*     */   {
/* 224 */     return new KeySetView(this);
/*     */   }
/*     */   
/*     */   public Collection<V> values() {
/* 228 */     return new ValuesView(this);
/*     */   }
/*     */   
/*     */   public Set<Map.Entry<K, V>> entrySet() {
/* 232 */     return new EntrySetView(this, this.insertOrder);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 242 */     if (isEmpty()) {
/* 243 */       return "{}";
/*     */     }
/* 245 */     StringBuffer buf = new StringBuffer();
/* 246 */     buf.append('{');
/* 247 */     boolean first = true;
/* 248 */     Iterator it = entrySet().iterator();
/* 249 */     while (it.hasNext()) {
/* 250 */       Map.Entry entry = (Map.Entry)it.next();
/* 251 */       Object key = entry.getKey();
/* 252 */       Object value = entry.getValue();
/* 253 */       if (first) {
/* 254 */         first = false;
/*     */       } else {
/* 256 */         buf.append(", ");
/*     */       }
/* 258 */       buf.append(key == this ? "(this Map)" : key);
/* 259 */       buf.append('=');
/* 260 */       buf.append(value == this ? "(this Map)" : value);
/*     */     }
/* 262 */     buf.append('}');
/* 263 */     return buf.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K get(int index)
/*     */   {
/* 275 */     return (K)this.insertOrder.get(index);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getValue(int index)
/*     */   {
/* 286 */     return (V)get(this.insertOrder.get(index));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(Object key)
/*     */   {
/* 296 */     return this.insertOrder.indexOf(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object remove(int index)
/*     */   {
/* 308 */     return remove(get(index));
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
/*     */   public List<K> asList()
/*     */   {
/* 327 */     return UnmodifiableList.decorate(this.insertOrder);
/*     */   }
/*     */   
/*     */   static class ValuesView<K, V> extends AbstractCollection<V>
/*     */   {
/*     */     private final ListOrderedMap<K, V> parent;
/*     */     
/*     */     ValuesView(ListOrderedMap<K, V> parent)
/*     */     {
/* 336 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 340 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 344 */       return this.parent.containsValue(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 348 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public Iterator<V> iterator() {
/* 352 */       new AbstractIteratorDecorator(this.parent.entrySet().iterator()) {
/*     */         public Object next() {
/* 354 */           return ((Map.Entry)this.iterator.next()).getValue();
/*     */         }
/*     */       };
/*     */     }
/*     */   }
/*     */   
/*     */   static class KeySetView<K, V> extends AbstractSet<K>
/*     */   {
/*     */     private final ListOrderedMap<K, V> parent;
/*     */     
/*     */     KeySetView(ListOrderedMap<K, V> parent)
/*     */     {
/* 366 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 370 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean contains(Object value) {
/* 374 */       return this.parent.containsKey(value);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 378 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public Iterator<K> iterator() {
/* 382 */       final Iterator<Map.Entry<K, V>> entryIterator = this.parent.entrySet().iterator();
/* 383 */       new Iterator() {
/*     */         public K next() {
/* 385 */           return (K)((Map.Entry)entryIterator.next()).getKey();
/*     */         }
/*     */         
/*     */         public boolean hasNext() {
/* 389 */           return entryIterator.hasNext();
/*     */         }
/*     */         
/*     */         public void remove() {
/* 393 */           entryIterator.remove();
/*     */         }
/*     */       };
/*     */     }
/*     */   }
/*     */   
/*     */   static class EntrySetView<K, V> extends AbstractSet<Map.Entry<K, V>>
/*     */   {
/*     */     private final ListOrderedMap<K, V> parent;
/*     */     private final List<K> insertOrder;
/*     */     private Set<Map.Entry<K, V>> entrySet;
/*     */     
/*     */     public EntrySetView(ListOrderedMap<K, V> parent, List<K> insertOrder)
/*     */     {
/* 407 */       this.parent = parent;
/* 408 */       this.insertOrder = insertOrder;
/*     */     }
/*     */     
/*     */     private Set<Map.Entry<K, V>> getEntrySet() {
/* 412 */       if (this.entrySet == null) {
/* 413 */         this.entrySet = this.parent.getMap().entrySet();
/*     */       }
/* 415 */       return this.entrySet;
/*     */     }
/*     */     
/*     */     public int size() {
/* 419 */       return this.parent.size();
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 423 */       return this.parent.isEmpty();
/*     */     }
/*     */     
/*     */     public boolean contains(Object obj) {
/* 427 */       return getEntrySet().contains(obj);
/*     */     }
/*     */     
/*     */     public boolean containsAll(Collection<?> coll) {
/* 431 */       return getEntrySet().containsAll(coll);
/*     */     }
/*     */     
/*     */     public boolean remove(Object obj) {
/* 435 */       if (!(obj instanceof Map.Entry)) {
/* 436 */         return false;
/*     */       }
/* 438 */       if (getEntrySet().contains(obj)) {
/* 439 */         Object key = ((Map.Entry)obj).getKey();
/* 440 */         this.parent.remove(key);
/* 441 */         return true;
/*     */       }
/* 443 */       return false;
/*     */     }
/*     */     
/*     */     public void clear() {
/* 447 */       this.parent.clear();
/*     */     }
/*     */     
/*     */     public boolean equals(Object obj) {
/* 451 */       if (obj == this) {
/* 452 */         return true;
/*     */       }
/* 454 */       return getEntrySet().equals(obj);
/*     */     }
/*     */     
/*     */     public int hashCode() {
/* 458 */       return getEntrySet().hashCode();
/*     */     }
/*     */     
/*     */     public String toString() {
/* 462 */       return getEntrySet().toString();
/*     */     }
/*     */     
/*     */     public Iterator<Map.Entry<K, V>> iterator() {
/* 466 */       return new ListOrderedMap.ListOrderedIterator(this.parent, this.insertOrder);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedIterator<K, V> implements Iterator<Map.Entry<K, V>>
/*     */   {
/*     */     private final ListOrderedMap<K, V> parent;
/* 473 */     private K last = null;
/*     */     private Iterator<K> listIterator;
/*     */     
/*     */     ListOrderedIterator(ListOrderedMap<K, V> parent, List<K> insertOrder) {
/* 477 */       this.listIterator = insertOrder.iterator();
/* 478 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public Map.Entry<K, V> next() {
/* 482 */       this.last = this.listIterator.next();
/* 483 */       return new ListOrderedMap.ListOrderedMapEntry(this.parent, this.last);
/*     */     }
/*     */     
/*     */     public void remove() {
/* 487 */       this.listIterator.remove();
/* 488 */       this.parent.getMap().remove(this.last);
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 492 */       return this.listIterator.hasNext();
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedMapEntry<K, V> extends AbstractMapEntry<K, V>
/*     */   {
/*     */     private final ListOrderedMap<K, V> parent;
/*     */     
/*     */     ListOrderedMapEntry(ListOrderedMap<K, V> parent, K key) {
/* 501 */       super(null);
/* 502 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 506 */       return (V)this.parent.get(this.key);
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 510 */       return (V)this.parent.getMap().put(this.key, value);
/*     */     }
/*     */   }
/*     */   
/*     */   static class ListOrderedMapIterator<K, V> implements OrderedMapIterator<K, V>, ResettableIterator<K>
/*     */   {
/*     */     private final ListOrderedMap<K, V> parent;
/*     */     private ListIterator<K> iterator;
/* 518 */     private K last = null;
/* 519 */     private boolean readable = false;
/*     */     
/*     */     ListOrderedMapIterator(ListOrderedMap<K, V> parent)
/*     */     {
/* 523 */       this.parent = parent;
/* 524 */       this.iterator = parent.insertOrder.listIterator();
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 528 */       return this.iterator.hasNext();
/*     */     }
/*     */     
/*     */     public K next() {
/* 532 */       this.last = this.iterator.next();
/* 533 */       this.readable = true;
/* 534 */       return (K)this.last;
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 538 */       return this.iterator.hasPrevious();
/*     */     }
/*     */     
/*     */     public K previous() {
/* 542 */       this.last = this.iterator.previous();
/* 543 */       this.readable = true;
/* 544 */       return (K)this.last;
/*     */     }
/*     */     
/*     */     public void remove() {
/* 548 */       if (!this.readable) {
/* 549 */         throw new IllegalStateException("remove() can only be called once after next()");
/*     */       }
/* 551 */       this.iterator.remove();
/* 552 */       this.parent.map.remove(this.last);
/* 553 */       this.readable = false;
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 557 */       if (!this.readable) {
/* 558 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 560 */       return (K)this.last;
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 564 */       if (!this.readable) {
/* 565 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 567 */       return (V)this.parent.get(this.last);
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 571 */       if (!this.readable) {
/* 572 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 574 */       return (V)this.parent.map.put(this.last, value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 578 */       this.iterator = this.parent.insertOrder.listIterator();
/* 579 */       this.last = null;
/* 580 */       this.readable = false;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 584 */       if (this.readable == true) {
/* 585 */         return "Iterator[" + getKey() + "=" + getValue() + "]";
/*     */       }
/* 587 */       return "Iterator[]";
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/ListOrderedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */