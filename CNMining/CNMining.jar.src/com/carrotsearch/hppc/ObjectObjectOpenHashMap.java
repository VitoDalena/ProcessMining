/*      */ package com.carrotsearch.hppc;
/*      */ 
/*      */ import com.carrotsearch.hppc.cursors.ObjectCursor;
/*      */ import com.carrotsearch.hppc.cursors.ObjectObjectCursor;
/*      */ import com.carrotsearch.hppc.predicates.ObjectPredicate;
/*      */ import com.carrotsearch.hppc.procedures.ObjectObjectProcedure;
/*      */ import com.carrotsearch.hppc.procedures.ObjectProcedure;
/*      */ import java.util.Arrays;
/*      */ import java.util.Iterator;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ObjectObjectOpenHashMap<KType, VType>
/*      */   implements ObjectObjectMap<KType, VType>, Cloneable
/*      */ {
/*      */   public static final int MIN_CAPACITY = 4;
/*      */   public static final int DEFAULT_CAPACITY = 16;
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   public KType[] keys;
/*      */   public VType[] values;
/*      */   public boolean[] allocated;
/*      */   public int assigned;
/*      */   public final float loadFactor;
/*      */   protected int resizeAt;
/*      */   protected int lastSlot;
/*      */   protected int perturbation;
/*      */   
/*      */   public ObjectObjectOpenHashMap()
/*      */   {
/*  159 */     this(16);
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
/*      */   public ObjectObjectOpenHashMap(int initialCapacity)
/*      */   {
/*  173 */     this(initialCapacity, 0.75F);
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
/*      */   public ObjectObjectOpenHashMap(int initialCapacity, float loadFactor)
/*      */   {
/*  189 */     initialCapacity = Math.max(initialCapacity, 4);
/*      */     
/*      */ 
/*  192 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*      */     
/*  194 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*      */     
/*  196 */     this.loadFactor = loadFactor;
/*  197 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectObjectOpenHashMap(ObjectObjectAssociativeContainer<KType, VType> container)
/*      */   {
/*  205 */     this((int)(container.size() * 1.75F));
/*  206 */     putAll(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType put(KType key, VType value)
/*      */   {
/*  215 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  217 */     int mask = this.allocated.length - 1;
/*  218 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  219 */     while (this.allocated[slot] != 0)
/*      */     {
/*  221 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  223 */         VType oldValue = this.values[slot];
/*  224 */         this.values[slot] = value;
/*  225 */         return oldValue;
/*      */       }
/*      */       
/*  228 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  233 */     if (this.assigned == this.resizeAt) {
/*  234 */       expandAndPut(key, value, slot);
/*      */     } else {
/*  236 */       this.assigned += 1;
/*  237 */       this.allocated[slot] = true;
/*  238 */       this.keys[slot] = key;
/*  239 */       this.values[slot] = value;
/*      */     }
/*  241 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(ObjectObjectAssociativeContainer<? extends KType, ? extends VType> container)
/*      */   {
/*  251 */     int count = this.assigned;
/*  252 */     for (ObjectObjectCursor<? extends KType, ? extends VType> c : container)
/*      */     {
/*  254 */       put(c.key, c.value);
/*      */     }
/*  256 */     return this.assigned - count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(Iterable<? extends ObjectObjectCursor<? extends KType, ? extends VType>> iterable)
/*      */   {
/*  266 */     int count = this.assigned;
/*  267 */     for (ObjectObjectCursor<? extends KType, ? extends VType> c : iterable)
/*      */     {
/*  269 */       put(c.key, c.value);
/*      */     }
/*  271 */     return this.assigned - count;
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
/*      */   public boolean putIfAbsent(KType key, VType value)
/*      */   {
/*  290 */     if (!containsKey(key))
/*      */     {
/*  292 */       put(key, value);
/*  293 */       return true;
/*      */     }
/*  295 */     return false;
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
/*      */   private void expandAndPut(KType pendingKey, VType pendingValue, int freeSlot)
/*      */   {
/*  309 */     assert (this.assigned == this.resizeAt);
/*  310 */     assert (this.allocated[freeSlot] == 0);
/*      */     
/*      */ 
/*      */ 
/*  314 */     KType[] oldKeys = this.keys;
/*  315 */     VType[] oldValues = this.values;
/*  316 */     boolean[] oldAllocated = this.allocated;
/*      */     
/*  318 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*      */     
/*      */ 
/*      */ 
/*  322 */     this.lastSlot = -1;
/*  323 */     this.assigned += 1;
/*  324 */     oldAllocated[freeSlot] = true;
/*  325 */     oldKeys[freeSlot] = pendingKey;
/*  326 */     oldValues[freeSlot] = pendingValue;
/*      */     
/*      */ 
/*  329 */     KType[] keys = this.keys;
/*  330 */     VType[] values = this.values;
/*  331 */     boolean[] allocated = this.allocated;
/*  332 */     int mask = allocated.length - 1;
/*  333 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*      */         break;
/*  335 */       if (oldAllocated[i] != 0)
/*      */       {
/*  337 */         KType k = oldKeys[i];
/*  338 */         VType v = oldValues[i];
/*      */         
/*  340 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/*  341 */         while (allocated[slot] != 0)
/*      */         {
/*  343 */           slot = slot + 1 & mask;
/*      */         }
/*      */         
/*  346 */         allocated[slot] = true;
/*  347 */         keys[slot] = k;
/*  348 */         values[slot] = v;
/*      */       }
/*      */     }
/*      */     
/*  352 */     Arrays.fill(oldKeys, null);
/*  353 */     Arrays.fill(oldValues, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void allocateBuffers(int capacity)
/*      */   {
/*  363 */     KType[] keys = (Object[])Internals.newArray(capacity);
/*  364 */     VType[] values = (Object[])Internals.newArray(capacity);
/*  365 */     boolean[] allocated = new boolean[capacity];
/*      */     
/*  367 */     this.keys = keys;
/*  368 */     this.values = values;
/*  369 */     this.allocated = allocated;
/*      */     
/*  371 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/*  372 */     this.perturbation = computePerturbationValue(capacity);
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
/*      */   protected int computePerturbationValue(int capacity)
/*      */   {
/*  390 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType remove(KType key)
/*      */   {
/*  399 */     int mask = this.allocated.length - 1;
/*  400 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  401 */     int wrappedAround = slot;
/*  402 */     while (this.allocated[slot] != 0)
/*      */     {
/*  404 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  406 */         this.assigned -= 1;
/*  407 */         VType v = this.values[slot];
/*  408 */         shiftConflictingKeys(slot);
/*  409 */         return v;
/*      */       }
/*  411 */       slot = slot + 1 & mask;
/*  412 */       if (slot == wrappedAround)
/*      */         break;
/*      */     }
/*  415 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void shiftConflictingKeys(int slotCurr)
/*      */   {
/*  424 */     int mask = this.allocated.length - 1;
/*      */     int slotPrev;
/*      */     for (;;)
/*      */     {
/*  428 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*      */       
/*  430 */       while (this.allocated[slotCurr] != 0)
/*      */       {
/*  432 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/*  433 */         if (slotPrev <= slotCurr ? 
/*      */         
/*      */ 
/*  436 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  442 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*      */           break;
/*      */         }
/*  445 */         slotCurr = slotCurr + 1 & mask;
/*      */       }
/*      */       
/*  448 */       if (this.allocated[slotCurr] == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  452 */       this.keys[slotPrev] = this.keys[slotCurr];
/*  453 */       this.values[slotPrev] = this.values[slotCurr];
/*      */     }
/*      */     
/*  456 */     this.allocated[slotPrev] = false;
/*      */     
/*      */ 
/*  459 */     this.keys[slotPrev] = null;
/*      */     
/*      */ 
/*  462 */     this.values[slotPrev] = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(ObjectContainer<? extends KType> container)
/*      */   {
/*  472 */     int before = this.assigned;
/*      */     
/*  474 */     for (ObjectCursor<? extends KType> cursor : container)
/*      */     {
/*  476 */       remove(cursor.value);
/*      */     }
/*      */     
/*  479 */     return before - this.assigned;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(ObjectPredicate<? super KType> predicate)
/*      */   {
/*  488 */     int before = this.assigned;
/*      */     
/*  490 */     KType[] keys = this.keys;
/*  491 */     boolean[] states = this.allocated;
/*      */     
/*  493 */     for (int i = 0; i < states.length;)
/*      */     {
/*  495 */       if (states[i] != 0)
/*      */       {
/*  497 */         if (predicate.apply(keys[i]))
/*      */         {
/*  499 */           this.assigned -= 1;
/*  500 */           shiftConflictingKeys(i);
/*      */           
/*  502 */           continue;
/*      */         }
/*      */       }
/*  505 */       i++;
/*      */     }
/*  507 */     return before - this.assigned;
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
/*      */   public VType get(KType key)
/*      */   {
/*  531 */     int mask = this.allocated.length - 1;
/*  532 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  533 */     int wrappedAround = slot;
/*  534 */     while (this.allocated[slot] != 0)
/*      */     {
/*  536 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  538 */         return (VType)this.values[slot];
/*      */       }
/*      */       
/*  541 */       slot = slot + 1 & mask;
/*  542 */       if (slot == wrappedAround) break;
/*      */     }
/*  544 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType getOrDefault(KType key, VType defaultValue)
/*      */   {
/*  553 */     int mask = this.allocated.length - 1;
/*  554 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  555 */     int wrappedAround = slot;
/*  556 */     while (this.allocated[slot] != 0)
/*      */     {
/*  558 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  560 */         return (VType)this.values[slot];
/*      */       }
/*      */       
/*  563 */       slot = slot + 1 & mask;
/*  564 */       if (slot == wrappedAround) break;
/*      */     }
/*  566 */     return defaultValue;
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
/*      */   public KType lkey()
/*      */   {
/*  589 */     return (KType)this.keys[lslot()];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType lget()
/*      */   {
/*  600 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  601 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  603 */     return (VType)this.values[this.lastSlot];
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
/*      */   public VType lset(VType key)
/*      */   {
/*  616 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  617 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  619 */     VType previous = this.values[this.lastSlot];
/*  620 */     this.values[this.lastSlot] = key;
/*  621 */     return previous;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int lslot()
/*      */   {
/*  632 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  633 */     return this.lastSlot;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean containsKey(KType key)
/*      */   {
/*  663 */     int mask = this.allocated.length - 1;
/*  664 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  665 */     int wrappedAround = slot;
/*  666 */     while (this.allocated[slot] != 0)
/*      */     {
/*  668 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  670 */         this.lastSlot = slot;
/*  671 */         return true;
/*      */       }
/*  673 */       slot = slot + 1 & mask;
/*  674 */       if (slot == wrappedAround) break;
/*      */     }
/*  676 */     this.lastSlot = -1;
/*  677 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clear()
/*      */   {
/*  688 */     this.assigned = 0;
/*      */     
/*      */ 
/*  691 */     Arrays.fill(this.allocated, false);
/*      */     
/*      */ 
/*  694 */     Arrays.fill(this.keys, null);
/*      */     
/*      */ 
/*      */ 
/*  698 */     Arrays.fill(this.values, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  708 */     return this.assigned;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEmpty()
/*      */   {
/*  719 */     return size() == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  728 */     int h = 0;
/*  729 */     for (ObjectObjectCursor<KType, VType> c : this)
/*      */     {
/*  731 */       h += Internals.rehash(c.key) + Internals.rehash(c.value);
/*      */     }
/*  733 */     return h;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  742 */     if (obj != null)
/*      */     {
/*  744 */       if (obj == this) { return true;
/*      */       }
/*  746 */       if ((obj instanceof ObjectObjectMap))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  751 */         ObjectObjectMap<KType, VType> other = (ObjectObjectMap)obj;
/*  752 */         if (other.size() == size())
/*      */         {
/*  754 */           for (ObjectObjectCursor<KType, VType> c : this)
/*      */           {
/*  756 */             if (other.containsKey(c.key))
/*      */             {
/*  758 */               VType v = other.get(c.key);
/*  759 */               if (c.value == null ? v == null : c.value.equals(v)) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */             else {
/*  764 */               return false;
/*      */             } }
/*  766 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  770 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   private final class EntryIterator
/*      */     extends AbstractIterator<ObjectObjectCursor<KType, VType>>
/*      */   {
/*      */     private final ObjectObjectCursor<KType, VType> cursor;
/*      */     
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  782 */       this.cursor = new ObjectObjectCursor();
/*  783 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ObjectObjectCursor<KType, VType> fetch()
/*      */     {
/*  789 */       int i = this.cursor.index + 1;
/*  790 */       int max = ObjectObjectOpenHashMap.this.keys.length;
/*  791 */       while ((i < max) && (ObjectObjectOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  793 */         i++;
/*      */       }
/*      */       
/*  796 */       if (i == max) {
/*  797 */         return (ObjectObjectCursor)done();
/*      */       }
/*  799 */       this.cursor.index = i;
/*  800 */       this.cursor.key = ObjectObjectOpenHashMap.this.keys[i];
/*  801 */       this.cursor.value = ObjectObjectOpenHashMap.this.values[i];
/*      */       
/*  803 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<ObjectObjectCursor<KType, VType>> iterator()
/*      */   {
/*  813 */     return new EntryIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <T extends ObjectObjectProcedure<? super KType, ? super VType>> T forEach(T procedure)
/*      */   {
/*  822 */     KType[] keys = this.keys;
/*  823 */     VType[] values = this.values;
/*  824 */     boolean[] states = this.allocated;
/*      */     
/*  826 */     for (int i = 0; i < states.length; i++)
/*      */     {
/*  828 */       if (states[i] != 0) {
/*  829 */         procedure.apply(keys[i], values[i]);
/*      */       }
/*      */     }
/*  832 */     return procedure;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectObjectOpenHashMap<KType, VType>.KeysContainer keys()
/*      */   {
/*  841 */     return new KeysContainer();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final class KeysContainer
/*      */     extends AbstractObjectCollection<KType>
/*      */     implements ObjectLookupContainer<KType>
/*      */   {
/*  850 */     private final ObjectObjectOpenHashMap<KType, VType> owner = ObjectObjectOpenHashMap.this;
/*      */     
/*      */     public KeysContainer() {}
/*      */     
/*      */     public boolean contains(KType e)
/*      */     {
/*  856 */       return ObjectObjectOpenHashMap.this.containsKey(e);
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectProcedure<? super KType>> T forEach(T procedure)
/*      */     {
/*  862 */       KType[] localKeys = this.owner.keys;
/*  863 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  865 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  867 */         if (localStates[i] != 0) {
/*  868 */           procedure.apply(localKeys[i]);
/*      */         }
/*      */       }
/*  871 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectPredicate<? super KType>> T forEach(T predicate)
/*      */     {
/*  877 */       KType[] localKeys = this.owner.keys;
/*  878 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  880 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  882 */         if ((localStates[i] != 0) && 
/*      */         
/*  884 */           (!predicate.apply(localKeys[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/*  889 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  895 */       return this.owner.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ObjectCursor<KType>> iterator()
/*      */     {
/*  901 */       return new ObjectObjectOpenHashMap.KeysIterator(ObjectObjectOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int size()
/*      */     {
/*  907 */       return this.owner.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/*  913 */       this.owner.clear();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(ObjectPredicate<? super KType> predicate)
/*      */     {
/*  919 */       return this.owner.removeAll(predicate);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(KType e)
/*      */     {
/*  925 */       boolean hasKey = this.owner.containsKey(e);
/*  926 */       int result = 0;
/*  927 */       if (hasKey)
/*      */       {
/*  929 */         this.owner.remove(e);
/*  930 */         result = 1;
/*      */       }
/*  932 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class KeysIterator
/*      */     extends AbstractIterator<ObjectCursor<KType>>
/*      */   {
/*      */     private final ObjectCursor<KType> cursor;
/*      */     
/*      */ 
/*      */     public KeysIterator()
/*      */     {
/*  945 */       this.cursor = new ObjectCursor();
/*  946 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ObjectCursor<KType> fetch()
/*      */     {
/*  952 */       int i = this.cursor.index + 1;
/*  953 */       int max = ObjectObjectOpenHashMap.this.keys.length;
/*  954 */       while ((i < max) && (ObjectObjectOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  956 */         i++;
/*      */       }
/*      */       
/*  959 */       if (i == max) {
/*  960 */         return (ObjectCursor)done();
/*      */       }
/*  962 */       this.cursor.index = i;
/*  963 */       this.cursor.value = ObjectObjectOpenHashMap.this.keys[i];
/*      */       
/*  965 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectContainer<VType> values()
/*      */   {
/*  975 */     return new ValuesContainer(null);
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesContainer
/*      */     extends AbstractObjectCollection<VType>
/*      */   {
/*      */     private ValuesContainer() {}
/*      */     
/*      */     public int size()
/*      */     {
/*  986 */       return ObjectObjectOpenHashMap.this.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  992 */       return ObjectObjectOpenHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean contains(VType value)
/*      */     {
/*  999 */       boolean[] allocated = ObjectObjectOpenHashMap.this.allocated;
/* 1000 */       VType[] values = ObjectObjectOpenHashMap.this.values;
/*      */       
/* 1002 */       for (int slot = 0; slot < allocated.length; slot++)
/*      */       {
/* 1004 */         if ((allocated[slot] != 0) && (value == null ? values[slot] == null : value.equals(values[slot])))
/*      */         {
/* 1006 */           return true;
/*      */         }
/*      */       }
/* 1009 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectProcedure<? super VType>> T forEach(T procedure)
/*      */     {
/* 1015 */       boolean[] allocated = ObjectObjectOpenHashMap.this.allocated;
/* 1016 */       VType[] values = ObjectObjectOpenHashMap.this.values;
/*      */       
/* 1018 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1020 */         if (allocated[i] != 0) {
/* 1021 */           procedure.apply(values[i]);
/*      */         }
/*      */       }
/* 1024 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectPredicate<? super VType>> T forEach(T predicate)
/*      */     {
/* 1030 */       boolean[] allocated = ObjectObjectOpenHashMap.this.allocated;
/* 1031 */       VType[] values = ObjectObjectOpenHashMap.this.values;
/*      */       
/* 1033 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1035 */         if ((allocated[i] != 0) && 
/*      */         
/* 1037 */           (!predicate.apply(values[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1042 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ObjectCursor<VType>> iterator()
/*      */     {
/* 1048 */       return new ObjectObjectOpenHashMap.ValuesIterator(ObjectObjectOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(VType e)
/*      */     {
/* 1054 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(ObjectPredicate<? super VType> predicate)
/*      */     {
/* 1060 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/* 1066 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesIterator
/*      */     extends AbstractIterator<ObjectCursor<VType>>
/*      */   {
/*      */     private final ObjectCursor<VType> cursor;
/*      */     
/*      */ 
/*      */     public ValuesIterator()
/*      */     {
/* 1079 */       this.cursor = new ObjectCursor();
/* 1080 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ObjectCursor<VType> fetch()
/*      */     {
/* 1086 */       int i = this.cursor.index + 1;
/* 1087 */       int max = ObjectObjectOpenHashMap.this.keys.length;
/* 1088 */       while ((i < max) && (ObjectObjectOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1090 */         i++;
/*      */       }
/*      */       
/* 1093 */       if (i == max) {
/* 1094 */         return (ObjectCursor)done();
/*      */       }
/* 1096 */       this.cursor.index = i;
/* 1097 */       this.cursor.value = ObjectObjectOpenHashMap.this.values[i];
/*      */       
/* 1099 */       return this.cursor;
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
/*      */   public ObjectObjectOpenHashMap<KType, VType> clone()
/*      */   {
/*      */     try
/*      */     {
/* 1114 */       ObjectObjectOpenHashMap<KType, VType> cloned = (ObjectObjectOpenHashMap)super.clone();
/*      */       
/*      */ 
/* 1117 */       cloned.keys = ((Object[])this.keys.clone());
/* 1118 */       cloned.values = ((Object[])this.values.clone());
/* 1119 */       cloned.allocated = ((boolean[])this.allocated.clone());
/*      */       
/* 1121 */       return cloned;
/*      */     }
/*      */     catch (CloneNotSupportedException e)
/*      */     {
/* 1125 */       throw new RuntimeException(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1135 */     StringBuilder buffer = new StringBuilder();
/* 1136 */     buffer.append("[");
/*      */     
/* 1138 */     boolean first = true;
/* 1139 */     for (ObjectObjectCursor<KType, VType> cursor : this)
/*      */     {
/* 1141 */       if (!first) buffer.append(", ");
/* 1142 */       buffer.append(cursor.key);
/* 1143 */       buffer.append("=>");
/* 1144 */       buffer.append(cursor.value);
/* 1145 */       first = false;
/*      */     }
/* 1147 */     buffer.append("]");
/* 1148 */     return buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> from(KType[] keys, VType[] values)
/*      */   {
/* 1156 */     if (keys.length != values.length) {
/* 1157 */       throw new IllegalArgumentException("Arrays of keys and values must have an identical length.");
/*      */     }
/* 1159 */     ObjectObjectOpenHashMap<KType, VType> map = new ObjectObjectOpenHashMap();
/* 1160 */     for (int i = 0; i < keys.length; i++)
/*      */     {
/* 1162 */       map.put(keys[i], values[i]);
/*      */     }
/* 1164 */     return map;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> from(ObjectObjectAssociativeContainer<KType, VType> container)
/*      */   {
/* 1172 */     return new ObjectObjectOpenHashMap(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> newInstance()
/*      */   {
/* 1181 */     return new ObjectObjectOpenHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> newInstanceWithoutPerturbations()
/*      */   {
/* 1191 */     new ObjectObjectOpenHashMap() {
/*      */       protected int computePerturbationValue(int capacity) {
/* 1193 */         return 0;
/*      */       }
/*      */     };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> newInstance(int initialCapacity, float loadFactor)
/*      */   {
/* 1203 */     return new ObjectObjectOpenHashMap(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> newInstanceWithExpectedSize(int expectedSize)
/*      */   {
/* 1213 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> ObjectObjectOpenHashMap<KType, VType> newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*      */   {
/* 1223 */     return newInstance((int)(expectedSize / loadFactor) + 1, loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectObjectOpenHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */