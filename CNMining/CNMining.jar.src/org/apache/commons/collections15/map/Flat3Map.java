/*      */ package org.apache.commons.collections15.map;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.AbstractCollection;
/*      */ import java.util.AbstractSet;
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.Set;
/*      */ import org.apache.commons.collections15.IterableMap;
/*      */ import org.apache.commons.collections15.MapIterator;
/*      */ import org.apache.commons.collections15.ResettableIterator;
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
/*      */ public class Flat3Map<K, V>
/*      */   implements IterableMap<K, V>, Serializable, Cloneable
/*      */ {
/*      */   private static final long serialVersionUID = -6701087419741928296L;
/*      */   private transient int size;
/*      */   private transient int hash1;
/*      */   private transient int hash2;
/*      */   private transient int hash3;
/*      */   private transient K key1;
/*      */   private transient K key2;
/*      */   private transient K key3;
/*      */   private transient V value1;
/*      */   private transient V value2;
/*      */   private transient V value3;
/*      */   private transient AbstractHashedMap<K, V> delegateMap;
/*      */   
/*      */   public Flat3Map() {}
/*      */   
/*      */   public Flat3Map(Map<K, V> map)
/*      */   {
/*  129 */     putAll(map);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V get(Object key)
/*      */   {
/*  140 */     if (this.delegateMap != null) {
/*  141 */       return (V)this.delegateMap.get(key);
/*      */     }
/*  143 */     if (key == null) {
/*  144 */       switch (this.size)
/*      */       {
/*      */       case 3: 
/*  147 */         if (this.key3 == null) return (V)this.value3;
/*      */       case 2: 
/*  149 */         if (this.key2 == null) return (V)this.value2;
/*      */       case 1: 
/*  151 */         if (this.key1 == null) return (V)this.value1;
/*      */         break;
/*      */       }
/*  154 */     } else if (this.size > 0) {
/*  155 */       int hashCode = key.hashCode();
/*  156 */       switch (this.size)
/*      */       {
/*      */       case 3: 
/*  159 */         if ((this.hash3 == hashCode) && (key.equals(this.key3))) return (V)this.value3;
/*      */       case 2: 
/*  161 */         if ((this.hash2 == hashCode) && (key.equals(this.key2))) return (V)this.value2;
/*      */       case 1: 
/*  163 */         if ((this.hash1 == hashCode) && (key.equals(this.key1))) return (V)this.value1;
/*      */         break;
/*      */       }
/*      */     }
/*  167 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  176 */     if (this.delegateMap != null) {
/*  177 */       return this.delegateMap.size();
/*      */     }
/*  179 */     return this.size;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  188 */     return size() == 0;
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
/*  199 */     if (this.delegateMap != null) {
/*  200 */       return this.delegateMap.containsKey(key);
/*      */     }
/*  202 */     if (key == null) {
/*  203 */       switch (this.size) {
/*      */       case 3: 
/*  205 */         if (this.key3 == null) return true;
/*      */       case 2: 
/*  207 */         if (this.key2 == null) return true;
/*      */       case 1: 
/*  209 */         if (this.key1 == null) return true;
/*      */         break;
/*      */       }
/*  212 */     } else if (this.size > 0) {
/*  213 */       int hashCode = key.hashCode();
/*  214 */       switch (this.size) {
/*      */       case 3: 
/*  216 */         if ((this.hash3 == hashCode) && (key.equals(this.key3))) return true;
/*      */       case 2: 
/*  218 */         if ((this.hash2 == hashCode) && (key.equals(this.key2))) return true;
/*      */       case 1: 
/*  220 */         if ((this.hash1 == hashCode) && (key.equals(this.key1))) return true;
/*      */         break;
/*      */       }
/*      */     }
/*  224 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsValue(Object value)
/*      */   {
/*  234 */     if (this.delegateMap != null) {
/*  235 */       return this.delegateMap.containsValue(value);
/*      */     }
/*  237 */     if (value == null)
/*  238 */       switch (this.size) {
/*      */       case 3: 
/*  240 */         if (this.value3 == null) return true;
/*      */       case 2: 
/*  242 */         if (this.value2 == null) return true;
/*      */       case 1: 
/*  244 */         if (this.value1 == null) return true;
/*      */         break;
/*      */       } else
/*  247 */       switch (this.size) {
/*      */       case 3: 
/*  249 */         if (value.equals(this.value3)) return true;
/*      */       case 2: 
/*  251 */         if (value.equals(this.value2)) return true;
/*      */       case 1: 
/*  253 */         if (value.equals(this.value1)) return true;
/*      */         break;
/*      */       }
/*  256 */     return false;
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
/*  268 */     if (this.delegateMap != null) {
/*  269 */       return (V)this.delegateMap.put(key, value);
/*      */     }
/*      */     
/*  272 */     if (key == null) {
/*  273 */       switch (this.size) {
/*      */       case 3: 
/*  275 */         if (this.key3 == null) {
/*  276 */           V old = this.value3;
/*  277 */           this.value3 = value;
/*  278 */           return old;
/*      */         }
/*      */       case 2: 
/*  281 */         if (this.key2 == null) {
/*  282 */           V old = this.value2;
/*  283 */           this.value2 = value;
/*  284 */           return old;
/*      */         }
/*      */       case 1: 
/*  287 */         if (this.key1 == null) {
/*  288 */           V old = this.value1;
/*  289 */           this.value1 = value;
/*  290 */           return old;
/*      */         }
/*      */         break;
/*      */       }
/*  294 */     } else if (this.size > 0) {
/*  295 */       int hashCode = key.hashCode();
/*  296 */       switch (this.size) {
/*      */       case 3: 
/*  298 */         if ((this.hash3 == hashCode) && (key.equals(this.key3))) {
/*  299 */           V old = this.value3;
/*  300 */           this.value3 = value;
/*  301 */           return old;
/*      */         }
/*      */       case 2: 
/*  304 */         if ((this.hash2 == hashCode) && (key.equals(this.key2))) {
/*  305 */           V old = this.value2;
/*  306 */           this.value2 = value;
/*  307 */           return old;
/*      */         }
/*      */       case 1: 
/*  310 */         if ((this.hash1 == hashCode) && (key.equals(this.key1))) {
/*  311 */           V old = this.value1;
/*  312 */           this.value1 = value;
/*  313 */           return old;
/*      */         }
/*      */         
/*      */         break;
/*      */       }
/*      */       
/*      */     }
/*  320 */     switch (this.size) {
/*      */     default: 
/*  322 */       convertToMap();
/*  323 */       this.delegateMap.put(key, value);
/*  324 */       return null;
/*      */     case 2: 
/*  326 */       this.hash3 = (key == null ? 0 : key.hashCode());
/*  327 */       this.key3 = key;
/*  328 */       this.value3 = value;
/*  329 */       break;
/*      */     case 1: 
/*  331 */       this.hash2 = (key == null ? 0 : key.hashCode());
/*  332 */       this.key2 = key;
/*  333 */       this.value2 = value;
/*  334 */       break;
/*      */     case 0: 
/*  336 */       this.hash1 = (key == null ? 0 : key.hashCode());
/*  337 */       this.key1 = key;
/*  338 */       this.value1 = value;
/*      */     }
/*      */     
/*  341 */     this.size += 1;
/*  342 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void putAll(Map<? extends K, ? extends V> map)
/*      */   {
/*  352 */     int size = map.size();
/*  353 */     if (size == 0) {
/*  354 */       return;
/*      */     }
/*  356 */     if (this.delegateMap != null) {
/*  357 */       this.delegateMap.putAll(map); return;
/*      */     }
/*      */     Iterator it;
/*  360 */     if (size < 4) {
/*  361 */       for (it = map.entrySet().iterator(); it.hasNext();) {
/*  362 */         Map.Entry<? extends K, ? extends V> entry = (Map.Entry)it.next();
/*  363 */         put(entry.getKey(), entry.getValue());
/*      */       }
/*      */     } else {
/*  366 */       convertToMap();
/*  367 */       this.delegateMap.putAll(map);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void convertToMap()
/*      */   {
/*  375 */     this.delegateMap = createDelegateMap();
/*  376 */     switch (this.size) {
/*      */     case 3: 
/*  378 */       this.delegateMap.put(this.key3, this.value3);
/*      */     case 2: 
/*  380 */       this.delegateMap.put(this.key2, this.value2);
/*      */     case 1: 
/*  382 */       this.delegateMap.put(this.key1, this.value1);
/*      */     }
/*      */     
/*  385 */     this.size = 0;
/*  386 */     this.hash1 = (this.hash2 = this.hash3 = 0);
/*  387 */     this.key1 = (this.key2 = this.key3 = null);
/*  388 */     this.value1 = (this.value2 = this.value3 = null);
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
/*      */   protected AbstractHashedMap<K, V> createDelegateMap()
/*      */   {
/*  402 */     return new HashedMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public V remove(Object key)
/*      */   {
/*  412 */     if (this.delegateMap != null) {
/*  413 */       return (V)this.delegateMap.remove(key);
/*      */     }
/*  415 */     if (this.size == 0) {
/*  416 */       return null;
/*      */     }
/*  418 */     if (key == null) {
/*  419 */       switch (this.size) {
/*      */       case 3: 
/*  421 */         if (this.key3 == null) {
/*  422 */           V old = this.value3;
/*  423 */           this.hash3 = 0;
/*  424 */           this.key3 = null;
/*  425 */           this.value3 = null;
/*  426 */           this.size = 2;
/*  427 */           return old;
/*      */         }
/*  429 */         if (this.key2 == null) {
/*  430 */           V old = this.value3;
/*  431 */           this.hash2 = this.hash3;
/*  432 */           this.key2 = this.key3;
/*  433 */           this.value2 = this.value3;
/*  434 */           this.hash3 = 0;
/*  435 */           this.key3 = null;
/*  436 */           this.value3 = null;
/*  437 */           this.size = 2;
/*  438 */           return old;
/*      */         }
/*  440 */         if (this.key1 == null) {
/*  441 */           V old = this.value3;
/*  442 */           this.hash1 = this.hash3;
/*  443 */           this.key1 = this.key3;
/*  444 */           this.value1 = this.value3;
/*  445 */           this.hash3 = 0;
/*  446 */           this.key3 = null;
/*  447 */           this.value3 = null;
/*  448 */           this.size = 2;
/*  449 */           return old;
/*      */         }
/*  451 */         return null;
/*      */       case 2: 
/*  453 */         if (this.key2 == null) {
/*  454 */           V old = this.value2;
/*  455 */           this.hash2 = 0;
/*  456 */           this.key2 = null;
/*  457 */           this.value2 = null;
/*  458 */           this.size = 1;
/*  459 */           return old;
/*      */         }
/*  461 */         if (this.key1 == null) {
/*  462 */           V old = this.value2;
/*  463 */           this.hash1 = this.hash2;
/*  464 */           this.key1 = this.key2;
/*  465 */           this.value1 = this.value2;
/*  466 */           this.hash2 = 0;
/*  467 */           this.key2 = null;
/*  468 */           this.value2 = null;
/*  469 */           this.size = 1;
/*  470 */           return old;
/*      */         }
/*  472 */         return null;
/*      */       case 1: 
/*  474 */         if (this.key1 == null) {
/*  475 */           V old = this.value1;
/*  476 */           this.hash1 = 0;
/*  477 */           this.key1 = null;
/*  478 */           this.value1 = null;
/*  479 */           this.size = 0;
/*  480 */           return old;
/*      */         }
/*      */         break;
/*      */       }
/*  484 */     } else if (this.size > 0) {
/*  485 */       int hashCode = key.hashCode();
/*  486 */       switch (this.size) {
/*      */       case 3: 
/*  488 */         if ((this.hash3 == hashCode) && (key.equals(this.key3))) {
/*  489 */           V old = this.value3;
/*  490 */           this.hash3 = 0;
/*  491 */           this.key3 = null;
/*  492 */           this.value3 = null;
/*  493 */           this.size = 2;
/*  494 */           return old;
/*      */         }
/*  496 */         if ((this.hash2 == hashCode) && (key.equals(this.key2))) {
/*  497 */           V old = this.value3;
/*  498 */           this.hash2 = this.hash3;
/*  499 */           this.key2 = this.key3;
/*  500 */           this.value2 = this.value3;
/*  501 */           this.hash3 = 0;
/*  502 */           this.key3 = null;
/*  503 */           this.value3 = null;
/*  504 */           this.size = 2;
/*  505 */           return old;
/*      */         }
/*  507 */         if ((this.hash1 == hashCode) && (key.equals(this.key1))) {
/*  508 */           V old = this.value3;
/*  509 */           this.hash1 = this.hash3;
/*  510 */           this.key1 = this.key3;
/*  511 */           this.value1 = this.value3;
/*  512 */           this.hash3 = 0;
/*  513 */           this.key3 = null;
/*  514 */           this.value3 = null;
/*  515 */           this.size = 2;
/*  516 */           return old;
/*      */         }
/*  518 */         return null;
/*      */       case 2: 
/*  520 */         if ((this.hash2 == hashCode) && (key.equals(this.key2))) {
/*  521 */           V old = this.value2;
/*  522 */           this.hash2 = 0;
/*  523 */           this.key2 = null;
/*  524 */           this.value2 = null;
/*  525 */           this.size = 1;
/*  526 */           return old;
/*      */         }
/*  528 */         if ((this.hash1 == hashCode) && (key.equals(this.key1))) {
/*  529 */           V old = this.value2;
/*  530 */           this.hash1 = this.hash2;
/*  531 */           this.key1 = this.key2;
/*  532 */           this.value1 = this.value2;
/*  533 */           this.hash2 = 0;
/*  534 */           this.key2 = null;
/*  535 */           this.value2 = null;
/*  536 */           this.size = 1;
/*  537 */           return old;
/*      */         }
/*  539 */         return null;
/*      */       case 1: 
/*  541 */         if ((this.hash1 == hashCode) && (key.equals(this.key1))) {
/*  542 */           V old = this.value1;
/*  543 */           this.hash1 = 0;
/*  544 */           this.key1 = null;
/*  545 */           this.value1 = null;
/*  546 */           this.size = 0;
/*  547 */           return old;
/*      */         }
/*      */         break;
/*      */       }
/*      */     }
/*  552 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  560 */     if (this.delegateMap != null) {
/*  561 */       this.delegateMap.clear();
/*  562 */       this.delegateMap = null;
/*      */     } else {
/*  564 */       this.size = 0;
/*  565 */       this.hash1 = (this.hash2 = this.hash3 = 0);
/*  566 */       this.key1 = (this.key2 = this.key3 = null);
/*  567 */       this.value1 = (this.value2 = this.value3 = null);
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
/*      */   public MapIterator<K, V> mapIterator()
/*      */   {
/*  584 */     if (this.delegateMap != null) {
/*  585 */       return this.delegateMap.mapIterator();
/*      */     }
/*  587 */     if (this.size == 0) {
/*  588 */       return EmptyMapIterator.INSTANCE;
/*      */     }
/*  590 */     return new FlatMapIterator(this);
/*      */   }
/*      */   
/*      */ 
/*      */   static class FlatMapIterator<K, V>
/*      */     implements MapIterator<K, V>, ResettableIterator<K>
/*      */   {
/*      */     private final Flat3Map<K, V> parent;
/*  598 */     private int nextIndex = 0;
/*  599 */     private boolean canRemove = false;
/*      */     
/*      */     FlatMapIterator(Flat3Map<K, V> parent)
/*      */     {
/*  603 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  607 */       return this.nextIndex < this.parent.size;
/*      */     }
/*      */     
/*      */     public K next() {
/*  611 */       if (!hasNext()) {
/*  612 */         throw new NoSuchElementException("No next() entry in the iteration");
/*      */       }
/*  614 */       this.canRemove = true;
/*  615 */       this.nextIndex += 1;
/*  616 */       return (K)getKey();
/*      */     }
/*      */     
/*      */     public void remove() {
/*  620 */       if (!this.canRemove) {
/*  621 */         throw new IllegalStateException("remove() can only be called once after next()");
/*      */       }
/*  623 */       this.parent.remove(getKey());
/*  624 */       this.nextIndex -= 1;
/*  625 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public K getKey() {
/*  629 */       if (!this.canRemove) {
/*  630 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  632 */       switch (this.nextIndex) {
/*      */       case 3: 
/*  634 */         return (K)this.parent.key3;
/*      */       case 2: 
/*  636 */         return (K)this.parent.key2;
/*      */       case 1: 
/*  638 */         return (K)this.parent.key1;
/*      */       }
/*  640 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public V getValue() {
/*  644 */       if (!this.canRemove) {
/*  645 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  647 */       switch (this.nextIndex) {
/*      */       case 3: 
/*  649 */         return (V)this.parent.value3;
/*      */       case 2: 
/*  651 */         return (V)this.parent.value2;
/*      */       case 1: 
/*  653 */         return (V)this.parent.value1;
/*      */       }
/*  655 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public V setValue(V value) {
/*  659 */       if (!this.canRemove) {
/*  660 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  662 */       V old = getValue();
/*  663 */       switch (this.nextIndex) {
/*      */       case 3: 
/*  665 */         this.parent.value3 = value;
/*      */       case 2: 
/*  667 */         this.parent.value2 = value;
/*      */       case 1: 
/*  669 */         this.parent.value1 = value;
/*      */       }
/*  671 */       return old;
/*      */     }
/*      */     
/*      */     public void reset() {
/*  675 */       this.nextIndex = 0;
/*  676 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public String toString() {
/*  680 */       if (this.canRemove) {
/*  681 */         return "Iterator[" + getKey() + "=" + getValue() + "]";
/*      */       }
/*  683 */       return "Iterator[]";
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
/*      */   public Set<Map.Entry<K, V>> entrySet()
/*      */   {
/*  698 */     if (this.delegateMap != null) {
/*  699 */       return this.delegateMap.entrySet();
/*      */     }
/*  701 */     return new EntrySet(this);
/*      */   }
/*      */   
/*      */ 
/*      */   static class EntrySet<K, V>
/*      */     extends AbstractSet<Map.Entry<K, V>>
/*      */   {
/*      */     private final Flat3Map<K, V> parent;
/*      */     
/*      */     EntrySet(Flat3Map<K, V> parent)
/*      */     {
/*  712 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  716 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  720 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean remove(Object obj) {
/*  724 */       if (!(obj instanceof Map.Entry)) {
/*  725 */         return false;
/*      */       }
/*  727 */       Map.Entry entry = (Map.Entry)obj;
/*  728 */       Object key = entry.getKey();
/*  729 */       boolean result = this.parent.containsKey(key);
/*  730 */       this.parent.remove(key);
/*  731 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator<Map.Entry<K, V>> iterator() {
/*  735 */       if (this.parent.delegateMap != null) {
/*  736 */         return this.parent.delegateMap.entrySet().iterator();
/*      */       }
/*  738 */       if (this.parent.size() == 0) {
/*  739 */         return EmptyIterator.INSTANCE;
/*      */       }
/*  741 */       return new Flat3Map.EntrySetIterator(this.parent);
/*      */     }
/*      */   }
/*      */   
/*      */   static class EntrySetIterator<K, V> extends Flat3Map.IteratorBase<K, V> implements Iterator<Map.Entry<K, V>>
/*      */   {
/*      */     public EntrySetIterator(Flat3Map<K, V> flat3Map) {
/*  748 */       super();
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> next() {
/*  752 */       return superNext();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   static class IteratorBase<K, V>
/*      */     implements Map.Entry<K, V>
/*      */   {
/*      */     private final Flat3Map<K, V> parent;
/*      */     
/*  762 */     private int nextIndex = 0;
/*  763 */     private boolean canRemove = false;
/*      */     
/*      */     IteratorBase(Flat3Map<K, V> parent)
/*      */     {
/*  767 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public boolean hasNext() {
/*  771 */       return this.nextIndex < this.parent.size;
/*      */     }
/*      */     
/*      */     public void remove() {
/*  775 */       if (!this.canRemove) {
/*  776 */         throw new IllegalStateException("remove() can only be called once after next()");
/*      */       }
/*  778 */       this.parent.remove(getKey());
/*  779 */       this.nextIndex -= 1;
/*  780 */       this.canRemove = false;
/*      */     }
/*      */     
/*      */     public K getKey() {
/*  784 */       if (!this.canRemove) {
/*  785 */         throw new IllegalStateException("getKey() can only be called after next() and before remove()");
/*      */       }
/*  787 */       switch (this.nextIndex) {
/*      */       case 3: 
/*  789 */         return (K)this.parent.key3;
/*      */       case 2: 
/*  791 */         return (K)this.parent.key2;
/*      */       case 1: 
/*  793 */         return (K)this.parent.key1;
/*      */       }
/*  795 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public Map.Entry<K, V> superNext() {
/*  799 */       if (!hasNext()) {
/*  800 */         throw new NoSuchElementException("No next() entry in the iteration");
/*      */       }
/*  802 */       this.canRemove = true;
/*  803 */       this.nextIndex += 1;
/*  804 */       return this;
/*      */     }
/*      */     
/*      */     public V getValue() {
/*  808 */       if (!this.canRemove) {
/*  809 */         throw new IllegalStateException("getValue() can only be called after next() and before remove()");
/*      */       }
/*  811 */       switch (this.nextIndex) {
/*      */       case 3: 
/*  813 */         return (V)this.parent.value3;
/*      */       case 2: 
/*  815 */         return (V)this.parent.value2;
/*      */       case 1: 
/*  817 */         return (V)this.parent.value1;
/*      */       }
/*  819 */       throw new IllegalStateException("Invalid map index");
/*      */     }
/*      */     
/*      */     public V setValue(V value) {
/*  823 */       if (!this.canRemove) {
/*  824 */         throw new IllegalStateException("setValue() can only be called after next() and before remove()");
/*      */       }
/*  826 */       V old = getValue();
/*  827 */       switch (this.nextIndex) {
/*      */       case 3: 
/*  829 */         this.parent.value3 = value;
/*      */       case 2: 
/*  831 */         this.parent.value2 = value;
/*      */       case 1: 
/*  833 */         this.parent.value1 = value;
/*      */       }
/*  835 */       return old;
/*      */     }
/*      */     
/*      */     public boolean equals(Object obj) {
/*  839 */       if (!this.canRemove) {
/*  840 */         return false;
/*      */       }
/*  842 */       if (!(obj instanceof Map.Entry)) {
/*  843 */         return false;
/*      */       }
/*  845 */       Map.Entry other = (Map.Entry)obj;
/*  846 */       Object key = getKey();
/*  847 */       Object value = getValue();
/*  848 */       return (key == null ? other.getKey() == null : key.equals(other.getKey())) && (value == null ? other.getValue() == null : value.equals(other.getValue()));
/*      */     }
/*      */     
/*      */     public int hashCode() {
/*  852 */       if (!this.canRemove) {
/*  853 */         return 0;
/*      */       }
/*  855 */       Object key = getKey();
/*  856 */       Object value = getValue();
/*  857 */       return (key == null ? 0 : key.hashCode()) ^ (value == null ? 0 : value.hashCode());
/*      */     }
/*      */     
/*      */     public String toString() {
/*  861 */       if (this.canRemove) {
/*  862 */         return getKey() + "=" + getValue();
/*      */       }
/*  864 */       return "";
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
/*      */   public Set keySet()
/*      */   {
/*  877 */     if (this.delegateMap != null) {
/*  878 */       return this.delegateMap.keySet();
/*      */     }
/*  880 */     return new KeySet(this);
/*      */   }
/*      */   
/*      */ 
/*      */   static class KeySet<K, V>
/*      */     extends AbstractSet<K>
/*      */   {
/*      */     private final Flat3Map<K, V> parent;
/*      */     
/*      */     KeySet(Flat3Map<K, V> parent)
/*      */     {
/*  891 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  895 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  899 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object key) {
/*  903 */       return this.parent.containsKey(key);
/*      */     }
/*      */     
/*      */     public boolean remove(Object key) {
/*  907 */       boolean result = this.parent.containsKey(key);
/*  908 */       this.parent.remove(key);
/*  909 */       return result;
/*      */     }
/*      */     
/*      */     public Iterator<K> iterator() {
/*  913 */       if (this.parent.delegateMap != null) {
/*  914 */         return this.parent.delegateMap.keySet().iterator();
/*      */       }
/*  916 */       if (this.parent.size() == 0) {
/*  917 */         return EmptyIterator.INSTANCE;
/*      */       }
/*  919 */       return new Flat3Map.KeySetIterator(this.parent);
/*      */     }
/*      */   }
/*      */   
/*      */   static class KeySetIterator<K, V>
/*      */     extends Flat3Map.IteratorBase<K, V>
/*      */     implements Iterator<K>
/*      */   {
/*      */     KeySetIterator(Flat3Map<K, V> parent)
/*      */     {
/*  929 */       super();
/*      */     }
/*      */     
/*      */     public K next() {
/*  933 */       superNext();
/*  934 */       return (K)getKey();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection<V> values()
/*      */   {
/*  946 */     if (this.delegateMap != null) {
/*  947 */       return this.delegateMap.values();
/*      */     }
/*  949 */     return new Values(this);
/*      */   }
/*      */   
/*      */ 
/*      */   static class Values<K, V>
/*      */     extends AbstractCollection<V>
/*      */   {
/*      */     private final Flat3Map<K, V> parent;
/*      */     
/*      */     Values(Flat3Map<K, V> parent)
/*      */     {
/*  960 */       this.parent = parent;
/*      */     }
/*      */     
/*      */     public int size() {
/*  964 */       return this.parent.size();
/*      */     }
/*      */     
/*      */     public void clear() {
/*  968 */       this.parent.clear();
/*      */     }
/*      */     
/*      */     public boolean contains(Object value) {
/*  972 */       return this.parent.containsValue(value);
/*      */     }
/*      */     
/*      */     public Iterator<V> iterator() {
/*  976 */       if (this.parent.delegateMap != null) {
/*  977 */         return this.parent.delegateMap.values().iterator();
/*      */       }
/*  979 */       if (this.parent.size() == 0) {
/*  980 */         return EmptyIterator.INSTANCE;
/*      */       }
/*  982 */       return new Flat3Map.ValuesIterator(this.parent);
/*      */     }
/*      */   }
/*      */   
/*      */   static class ValuesIterator<K, V>
/*      */     extends Flat3Map.IteratorBase<K, V>
/*      */     implements Iterator<V>
/*      */   {
/*      */     ValuesIterator(Flat3Map<K, V> parent)
/*      */     {
/*  992 */       super();
/*      */     }
/*      */     
/*      */     public V next() {
/*  996 */       superNext();
/*  997 */       return (V)getValue();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream out)
/*      */     throws IOException
/*      */   {
/* 1006 */     out.defaultWriteObject();
/* 1007 */     out.writeInt(size());
/* 1008 */     for (MapIterator it = mapIterator(); it.hasNext();) {
/* 1009 */       out.writeObject(it.next());
/* 1010 */       out.writeObject(it.getValue());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private void readObject(ObjectInputStream in)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1018 */     in.defaultReadObject();
/* 1019 */     int count = in.readInt();
/* 1020 */     if (count > 3) {
/* 1021 */       this.delegateMap = createDelegateMap();
/*      */     }
/* 1023 */     for (int i = count; i > 0; i--) {
/* 1024 */       put(in.readObject(), in.readObject());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */   {
/*      */     try
/*      */     {
/* 1037 */       Flat3Map cloned = (Flat3Map)super.clone();
/* 1038 */       if (cloned.delegateMap != null) {
/* 1039 */         cloned.delegateMap = ((HashedMap)cloned.delegateMap.clone());
/*      */       }
/* 1041 */       return cloned;
/*      */     } catch (CloneNotSupportedException ex) {
/* 1043 */       throw new InternalError();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1054 */     if (obj == this) {
/* 1055 */       return true;
/*      */     }
/* 1057 */     if (this.delegateMap != null) {
/* 1058 */       return this.delegateMap.equals(obj);
/*      */     }
/* 1060 */     if (!(obj instanceof Map)) {
/* 1061 */       return false;
/*      */     }
/* 1063 */     Map other = (Map)obj;
/* 1064 */     if (this.size != other.size()) {
/* 1065 */       return false;
/*      */     }
/* 1067 */     if (this.size > 0) {
/* 1068 */       Object otherValue = null;
/* 1069 */       switch (this.size) {
/*      */       case 3: 
/* 1071 */         if (!other.containsKey(this.key3)) {
/* 1072 */           otherValue = other.get(this.key3);
/* 1073 */           if (this.value3 == null ? otherValue != null : !this.value3.equals(otherValue)) {
/* 1074 */             return false;
/*      */           }
/*      */         }
/*      */       case 2: 
/* 1078 */         if (!other.containsKey(this.key2)) {
/* 1079 */           otherValue = other.get(this.key2);
/* 1080 */           if (this.value2 == null ? otherValue != null : !this.value2.equals(otherValue)) {
/* 1081 */             return false;
/*      */           }
/*      */         }
/*      */       case 1: 
/* 1085 */         if (!other.containsKey(this.key1)) {
/* 1086 */           otherValue = other.get(this.key1);
/* 1087 */           if (this.value1 == null ? otherValue != null : !this.value1.equals(otherValue))
/* 1088 */             return false;
/*      */         }
/*      */         break;
/*      */       }
/*      */     }
/* 1093 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1102 */     if (this.delegateMap != null) {
/* 1103 */       return this.delegateMap.hashCode();
/*      */     }
/* 1105 */     int total = 0;
/* 1106 */     switch (this.size) {
/*      */     case 3: 
/* 1108 */       total += (this.hash3 ^ (this.value3 == null ? 0 : this.value3.hashCode()));
/*      */     case 2: 
/* 1110 */       total += (this.hash2 ^ (this.value2 == null ? 0 : this.value2.hashCode()));
/*      */     case 1: 
/* 1112 */       total += (this.hash1 ^ (this.value1 == null ? 0 : this.value1.hashCode()));
/*      */     }
/* 1114 */     return total;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1123 */     if (this.delegateMap != null) {
/* 1124 */       return this.delegateMap.toString();
/*      */     }
/* 1126 */     if (this.size == 0) {
/* 1127 */       return "{}";
/*      */     }
/* 1129 */     StringBuffer buf = new StringBuffer(128);
/* 1130 */     buf.append('{');
/* 1131 */     switch (this.size) {
/*      */     case 3: 
/* 1133 */       buf.append(this.key3);
/* 1134 */       buf.append('=');
/* 1135 */       buf.append(this.value3);
/* 1136 */       buf.append(',');
/*      */     case 2: 
/* 1138 */       buf.append(this.key2);
/* 1139 */       buf.append('=');
/* 1140 */       buf.append(this.value2);
/* 1141 */       buf.append(',');
/*      */     case 1: 
/* 1143 */       buf.append(this.key1);
/* 1144 */       buf.append('=');
/* 1145 */       buf.append(this.value1);
/*      */     }
/* 1147 */     buf.append('}');
/* 1148 */     return buf.toString();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/map/Flat3Map.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */