/*     */ package org.apache.commons.collections15.map;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.AbstractSet;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.collections15.BoundedMap;
/*     */ import org.apache.commons.collections15.KeyValue;
/*     */ import org.apache.commons.collections15.MapIterator;
/*     */ import org.apache.commons.collections15.OrderedMap;
/*     */ import org.apache.commons.collections15.OrderedMapIterator;
/*     */ import org.apache.commons.collections15.ResettableIterator;
/*     */ import org.apache.commons.collections15.iterators.SingletonIterator;
/*     */ import org.apache.commons.collections15.keyvalue.TiedMapEntry;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SingletonMap<K, V>
/*     */   implements OrderedMap<K, V>, BoundedMap<K, V>, KeyValue<K, V>, Serializable, Cloneable
/*     */ {
/*     */   private static final long serialVersionUID = -8931271118676803261L;
/*     */   private final K key;
/*     */   private V value;
/*     */   
/*     */   public SingletonMap()
/*     */   {
/*  71 */     this.key = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SingletonMap(K key, V value)
/*     */   {
/*  82 */     this.key = key;
/*  83 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SingletonMap(KeyValue<K, V> keyValue)
/*     */   {
/*  93 */     this.key = keyValue.getKey();
/*  94 */     this.value = keyValue.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SingletonMap(Map.Entry<K, V> entry)
/*     */   {
/* 104 */     this.key = entry.getKey();
/* 105 */     this.value = entry.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SingletonMap(Map<? extends K, ? extends V> map)
/*     */   {
/* 117 */     if (map.size() != 1) {
/* 118 */       throw new IllegalArgumentException("The map size must be 1");
/*     */     }
/* 120 */     Map.Entry<? extends K, ? extends V> entry = (Map.Entry)map.entrySet().iterator().next();
/* 121 */     this.key = entry.getKey();
/* 122 */     this.value = entry.getValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K getKey()
/*     */   {
/* 133 */     return (K)this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V getValue()
/*     */   {
/* 142 */     return (V)this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public V setValue(V value)
/*     */   {
/* 152 */     V old = this.value;
/* 153 */     this.value = value;
/* 154 */     return old;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isFull()
/*     */   {
/* 165 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int maxSize()
/*     */   {
/* 174 */     return 1;
/*     */   }
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
/* 186 */     if (isEqualKey(key)) {
/* 187 */       return (V)this.value;
/*     */     }
/* 189 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 198 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 207 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsKey(Object key)
/*     */   {
/* 218 */     return isEqualKey(key);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean containsValue(Object value)
/*     */   {
/* 228 */     return isEqualValue(value);
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
/* 244 */     if (isEqualKey(key)) {
/* 245 */       return (V)setValue(value);
/*     */     }
/* 247 */     throw new IllegalArgumentException("Cannot put new key/value pair - Map is fixed size singleton");
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
/*     */   public void putAll(Map<? extends K, ? extends V> map)
/*     */   {
/* 262 */     switch (map.size()) {
/*     */     case 0: 
/* 264 */       return;
/*     */     
/*     */     case 1: 
/* 267 */       Map.Entry<? extends K, ? extends V> entry = (Map.Entry)map.entrySet().iterator().next();
/* 268 */       put(entry.getKey(), entry.getValue());
/* 269 */       return;
/*     */     }
/*     */     
/* 272 */     throw new IllegalArgumentException("The map size must be 0 or 1");
/*     */   }
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
/* 284 */     throw new UnsupportedOperationException();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 291 */     throw new UnsupportedOperationException();
/*     */   }
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
/* 303 */     Map.Entry<K, V> entry = new TiedMapEntry(this, getKey());
/* 304 */     return Collections.singleton(entry);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<K> keySet()
/*     */   {
/* 315 */     return Collections.singleton(this.key);
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
/* 326 */     return new SingletonValues(this);
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
/*     */   public MapIterator<K, V> mapIterator()
/*     */   {
/* 342 */     return new SingletonMapIterator(this);
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
/* 356 */     return new SingletonMapIterator(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K firstKey()
/*     */   {
/* 365 */     return (K)getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K lastKey()
/*     */   {
/* 374 */     return (K)getKey();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K nextKey(K key)
/*     */   {
/* 384 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public K previousKey(K key)
/*     */   {
/* 394 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isEqualKey(Object key)
/*     */   {
/* 405 */     return key == null ? false : getKey() == null ? true : key.equals(getKey());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isEqualValue(Object value)
/*     */   {
/* 415 */     return value == null ? false : getValue() == null ? true : value.equals(getValue());
/*     */   }
/*     */   
/*     */ 
/*     */   static class SingletonMapIterator<K, V>
/*     */     implements OrderedMapIterator<K, V>, ResettableIterator<K>
/*     */   {
/*     */     private final SingletonMap<K, V> parent;
/*     */     
/* 424 */     private boolean hasNext = true;
/* 425 */     private boolean canGetSet = false;
/*     */     
/*     */     SingletonMapIterator(SingletonMap<K, V> parent)
/*     */     {
/* 429 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public boolean hasNext() {
/* 433 */       return this.hasNext;
/*     */     }
/*     */     
/*     */     public K next() {
/* 437 */       if (!this.hasNext) {
/* 438 */         throw new NoSuchElementException("No next() entry in the iteration");
/*     */       }
/* 440 */       this.hasNext = false;
/* 441 */       this.canGetSet = true;
/* 442 */       return (K)this.parent.getKey();
/*     */     }
/*     */     
/*     */     public boolean hasPrevious() {
/* 446 */       return !this.hasNext;
/*     */     }
/*     */     
/*     */     public K previous() {
/* 450 */       if (this.hasNext == true) {
/* 451 */         throw new NoSuchElementException("No previous() entry in the iteration");
/*     */       }
/* 453 */       this.hasNext = true;
/* 454 */       return (K)this.parent.getKey();
/*     */     }
/*     */     
/*     */     public void remove() {
/* 458 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public K getKey() {
/* 462 */       if (!this.canGetSet) {
/* 463 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*     */       }
/* 465 */       return (K)this.parent.getKey();
/*     */     }
/*     */     
/*     */     public V getValue() {
/* 469 */       if (!this.canGetSet) {
/* 470 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*     */       }
/* 472 */       return (V)this.parent.getValue();
/*     */     }
/*     */     
/*     */     public V setValue(V value) {
/* 476 */       if (!this.canGetSet) {
/* 477 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*     */       }
/* 479 */       return (V)this.parent.setValue(value);
/*     */     }
/*     */     
/*     */     public void reset() {
/* 483 */       this.hasNext = true;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 487 */       if (this.hasNext) {
/* 488 */         return "Iterator[]";
/*     */       }
/* 490 */       return "Iterator[" + getKey() + "=" + getValue() + "]";
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   static class SingletonValues<K, V>
/*     */     extends AbstractSet<V>
/*     */     implements Serializable
/*     */   {
/*     */     private static final long serialVersionUID = -3689524741863047872L;
/*     */     
/*     */     private final SingletonMap<K, V> parent;
/*     */     
/*     */     SingletonValues(SingletonMap<K, V> parent)
/*     */     {
/* 505 */       this.parent = parent;
/*     */     }
/*     */     
/*     */     public int size() {
/* 509 */       return 1;
/*     */     }
/*     */     
/*     */     public boolean isEmpty() {
/* 513 */       return false;
/*     */     }
/*     */     
/*     */     public boolean contains(Object object) {
/* 517 */       return this.parent.containsValue(object);
/*     */     }
/*     */     
/*     */     public void clear() {
/* 521 */       throw new UnsupportedOperationException();
/*     */     }
/*     */     
/*     */     public Iterator<V> iterator() {
/* 525 */       return new SingletonIterator(this.parent.getValue(), false);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     try
/*     */     {
/* 537 */       return (SingletonMap)super.clone();
/*     */     }
/*     */     catch (CloneNotSupportedException ex) {
/* 540 */       throw new InternalError();
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
/* 551 */     if (obj == this) {
/* 552 */       return true;
/*     */     }
/* 554 */     if (!(obj instanceof Map)) {
/* 555 */       return false;
/*     */     }
/* 557 */     Map other = (Map)obj;
/* 558 */     if (other.size() != 1) {
/* 559 */       return false;
/*     */     }
/* 561 */     Map.Entry entry = (Map.Entry)other.entrySet().iterator().next();
/* 562 */     return (isEqualKey(entry.getKey())) && (isEqualValue(entry.getValue()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 571 */     return (getKey() == null ? 0 : getKey().hashCode()) ^ (getValue() == null ? 0 : getValue().hashCode());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 580 */     return 128 + '{' + getKey() + '=' + getValue() + '}';
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/SingletonMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */