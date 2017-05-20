/*      */ package com.carrotsearch.hppc;
/*      */ 
/*      */ import com.carrotsearch.hppc.cursors.ByteCursor;
/*      */ import com.carrotsearch.hppc.cursors.ByteObjectCursor;
/*      */ import com.carrotsearch.hppc.cursors.ObjectCursor;
/*      */ import com.carrotsearch.hppc.predicates.BytePredicate;
/*      */ import com.carrotsearch.hppc.predicates.ObjectPredicate;
/*      */ import com.carrotsearch.hppc.procedures.ByteObjectProcedure;
/*      */ import com.carrotsearch.hppc.procedures.ByteProcedure;
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
/*      */ public class ByteObjectOpenHashMap<VType>
/*      */   implements ByteObjectMap<VType>, Cloneable
/*      */ {
/*      */   public static final int MIN_CAPACITY = 4;
/*      */   public static final int DEFAULT_CAPACITY = 16;
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   public byte[] keys;
/*      */   public VType[] values;
/*      */   public boolean[] allocated;
/*      */   public int assigned;
/*      */   public final float loadFactor;
/*      */   protected int resizeAt;
/*      */   protected int lastSlot;
/*      */   protected int perturbation;
/*      */   
/*      */   public ByteObjectOpenHashMap()
/*      */   {
/*  121 */     this(16);
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
/*      */   public ByteObjectOpenHashMap(int initialCapacity)
/*      */   {
/*  135 */     this(initialCapacity, 0.75F);
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
/*      */   public ByteObjectOpenHashMap(int initialCapacity, float loadFactor)
/*      */   {
/*  151 */     initialCapacity = Math.max(initialCapacity, 4);
/*      */     
/*      */ 
/*  154 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*      */     
/*  156 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*      */     
/*  158 */     this.loadFactor = loadFactor;
/*  159 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ByteObjectOpenHashMap(ByteObjectAssociativeContainer<VType> container)
/*      */   {
/*  167 */     this((int)(container.size() * 1.75F));
/*  168 */     putAll(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType put(byte key, VType value)
/*      */   {
/*  177 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  179 */     int mask = this.allocated.length - 1;
/*  180 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  181 */     while (this.allocated[slot] != 0)
/*      */     {
/*  183 */       if (key == this.keys[slot])
/*      */       {
/*  185 */         VType oldValue = this.values[slot];
/*  186 */         this.values[slot] = value;
/*  187 */         return oldValue;
/*      */       }
/*      */       
/*  190 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  195 */     if (this.assigned == this.resizeAt) {
/*  196 */       expandAndPut(key, value, slot);
/*      */     } else {
/*  198 */       this.assigned += 1;
/*  199 */       this.allocated[slot] = true;
/*  200 */       this.keys[slot] = key;
/*  201 */       this.values[slot] = value;
/*      */     }
/*  203 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(ByteObjectAssociativeContainer<? extends VType> container)
/*      */   {
/*  213 */     int count = this.assigned;
/*  214 */     for (ByteObjectCursor<? extends VType> c : container)
/*      */     {
/*  216 */       put(c.key, c.value);
/*      */     }
/*  218 */     return this.assigned - count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(Iterable<? extends ByteObjectCursor<? extends VType>> iterable)
/*      */   {
/*  228 */     int count = this.assigned;
/*  229 */     for (ByteObjectCursor<? extends VType> c : iterable)
/*      */     {
/*  231 */       put(c.key, c.value);
/*      */     }
/*  233 */     return this.assigned - count;
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
/*      */   public boolean putIfAbsent(byte key, VType value)
/*      */   {
/*  252 */     if (!containsKey(key))
/*      */     {
/*  254 */       put(key, value);
/*  255 */       return true;
/*      */     }
/*  257 */     return false;
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
/*      */   private void expandAndPut(byte pendingKey, VType pendingValue, int freeSlot)
/*      */   {
/*  271 */     assert (this.assigned == this.resizeAt);
/*  272 */     assert (this.allocated[freeSlot] == 0);
/*      */     
/*      */ 
/*      */ 
/*  276 */     byte[] oldKeys = this.keys;
/*  277 */     VType[] oldValues = this.values;
/*  278 */     boolean[] oldAllocated = this.allocated;
/*      */     
/*  280 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*      */     
/*      */ 
/*      */ 
/*  284 */     this.lastSlot = -1;
/*  285 */     this.assigned += 1;
/*  286 */     oldAllocated[freeSlot] = true;
/*  287 */     oldKeys[freeSlot] = pendingKey;
/*  288 */     oldValues[freeSlot] = pendingValue;
/*      */     
/*      */ 
/*  291 */     byte[] keys = this.keys;
/*  292 */     VType[] values = this.values;
/*  293 */     boolean[] allocated = this.allocated;
/*  294 */     int mask = allocated.length - 1;
/*  295 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*      */         break;
/*  297 */       if (oldAllocated[i] != 0)
/*      */       {
/*  299 */         byte k = oldKeys[i];
/*  300 */         VType v = oldValues[i];
/*      */         
/*  302 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/*  303 */         while (allocated[slot] != 0)
/*      */         {
/*  305 */           slot = slot + 1 & mask;
/*      */         }
/*      */         
/*  308 */         allocated[slot] = true;
/*  309 */         keys[slot] = k;
/*  310 */         values[slot] = v;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  315 */     Arrays.fill(oldValues, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void allocateBuffers(int capacity)
/*      */   {
/*  325 */     byte[] keys = new byte[capacity];
/*  326 */     VType[] values = (Object[])Internals.newArray(capacity);
/*  327 */     boolean[] allocated = new boolean[capacity];
/*      */     
/*  329 */     this.keys = keys;
/*  330 */     this.values = values;
/*  331 */     this.allocated = allocated;
/*      */     
/*  333 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/*  334 */     this.perturbation = computePerturbationValue(capacity);
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
/*  352 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType remove(byte key)
/*      */   {
/*  361 */     int mask = this.allocated.length - 1;
/*  362 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  363 */     int wrappedAround = slot;
/*  364 */     while (this.allocated[slot] != 0)
/*      */     {
/*  366 */       if (key == this.keys[slot])
/*      */       {
/*  368 */         this.assigned -= 1;
/*  369 */         VType v = this.values[slot];
/*  370 */         shiftConflictingKeys(slot);
/*  371 */         return v;
/*      */       }
/*  373 */       slot = slot + 1 & mask;
/*  374 */       if (slot == wrappedAround)
/*      */         break;
/*      */     }
/*  377 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void shiftConflictingKeys(int slotCurr)
/*      */   {
/*  386 */     int mask = this.allocated.length - 1;
/*      */     int slotPrev;
/*      */     for (;;)
/*      */     {
/*  390 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*      */       
/*  392 */       while (this.allocated[slotCurr] != 0)
/*      */       {
/*  394 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/*  395 */         if (slotPrev <= slotCurr ? 
/*      */         
/*      */ 
/*  398 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  404 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*      */           break;
/*      */         }
/*  407 */         slotCurr = slotCurr + 1 & mask;
/*      */       }
/*      */       
/*  410 */       if (this.allocated[slotCurr] == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  414 */       this.keys[slotPrev] = this.keys[slotCurr];
/*  415 */       this.values[slotPrev] = this.values[slotCurr];
/*      */     }
/*      */     
/*  418 */     this.allocated[slotPrev] = false;
/*      */     
/*      */ 
/*      */ 
/*  422 */     this.values[slotPrev] = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(ByteContainer container)
/*      */   {
/*  432 */     int before = this.assigned;
/*      */     
/*  434 */     for (ByteCursor cursor : container)
/*      */     {
/*  436 */       remove(cursor.value);
/*      */     }
/*      */     
/*  439 */     return before - this.assigned;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(BytePredicate predicate)
/*      */   {
/*  448 */     int before = this.assigned;
/*      */     
/*  450 */     byte[] keys = this.keys;
/*  451 */     boolean[] states = this.allocated;
/*      */     
/*  453 */     for (int i = 0; i < states.length;)
/*      */     {
/*  455 */       if (states[i] != 0)
/*      */       {
/*  457 */         if (predicate.apply(keys[i]))
/*      */         {
/*  459 */           this.assigned -= 1;
/*  460 */           shiftConflictingKeys(i);
/*      */           
/*  462 */           continue;
/*      */         }
/*      */       }
/*  465 */       i++;
/*      */     }
/*  467 */     return before - this.assigned;
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
/*      */   public VType get(byte key)
/*      */   {
/*  491 */     int mask = this.allocated.length - 1;
/*  492 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  493 */     int wrappedAround = slot;
/*  494 */     while (this.allocated[slot] != 0)
/*      */     {
/*  496 */       if (key == this.keys[slot])
/*      */       {
/*  498 */         return (VType)this.values[slot];
/*      */       }
/*      */       
/*  501 */       slot = slot + 1 & mask;
/*  502 */       if (slot == wrappedAround) break;
/*      */     }
/*  504 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType getOrDefault(byte key, VType defaultValue)
/*      */   {
/*  513 */     int mask = this.allocated.length - 1;
/*  514 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  515 */     int wrappedAround = slot;
/*  516 */     while (this.allocated[slot] != 0)
/*      */     {
/*  518 */       if (key == this.keys[slot])
/*      */       {
/*  520 */         return (VType)this.values[slot];
/*      */       }
/*      */       
/*  523 */       slot = slot + 1 & mask;
/*  524 */       if (slot == wrappedAround) break;
/*      */     }
/*  526 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType lget()
/*      */   {
/*  538 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  539 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  541 */     return (VType)this.values[this.lastSlot];
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
/*  554 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  555 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  557 */     VType previous = this.values[this.lastSlot];
/*  558 */     this.values[this.lastSlot] = key;
/*  559 */     return previous;
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
/*  570 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  571 */     return this.lastSlot;
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
/*      */   public boolean containsKey(byte key)
/*      */   {
/*  597 */     int mask = this.allocated.length - 1;
/*  598 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  599 */     int wrappedAround = slot;
/*  600 */     while (this.allocated[slot] != 0)
/*      */     {
/*  602 */       if (key == this.keys[slot])
/*      */       {
/*  604 */         this.lastSlot = slot;
/*  605 */         return true;
/*      */       }
/*  607 */       slot = slot + 1 & mask;
/*  608 */       if (slot == wrappedAround) break;
/*      */     }
/*  610 */     this.lastSlot = -1;
/*  611 */     return false;
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
/*  622 */     this.assigned = 0;
/*      */     
/*      */ 
/*  625 */     Arrays.fill(this.allocated, false);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  630 */     Arrays.fill(this.values, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  640 */     return this.assigned;
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
/*  651 */     return size() == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  660 */     int h = 0;
/*  661 */     for (ByteObjectCursor<VType> c : this)
/*      */     {
/*  663 */       h += Internals.rehash(c.key) + Internals.rehash(c.value);
/*      */     }
/*  665 */     return h;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  674 */     if (obj != null)
/*      */     {
/*  676 */       if (obj == this) { return true;
/*      */       }
/*  678 */       if ((obj instanceof ByteObjectMap))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  683 */         ByteObjectMap<VType> other = (ByteObjectMap)obj;
/*  684 */         if (other.size() == size())
/*      */         {
/*  686 */           for (ByteObjectCursor<VType> c : this)
/*      */           {
/*  688 */             if (other.containsKey(c.key))
/*      */             {
/*  690 */               VType v = other.get(c.key);
/*  691 */               if (c.value == null ? v == null : c.value.equals(v)) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */             else {
/*  696 */               return false;
/*      */             } }
/*  698 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  702 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   private final class EntryIterator
/*      */     extends AbstractIterator<ByteObjectCursor<VType>>
/*      */   {
/*      */     private final ByteObjectCursor<VType> cursor;
/*      */     
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  714 */       this.cursor = new ByteObjectCursor();
/*  715 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ByteObjectCursor<VType> fetch()
/*      */     {
/*  721 */       int i = this.cursor.index + 1;
/*  722 */       int max = ByteObjectOpenHashMap.this.keys.length;
/*  723 */       while ((i < max) && (ByteObjectOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  725 */         i++;
/*      */       }
/*      */       
/*  728 */       if (i == max) {
/*  729 */         return (ByteObjectCursor)done();
/*      */       }
/*  731 */       this.cursor.index = i;
/*  732 */       this.cursor.key = ByteObjectOpenHashMap.this.keys[i];
/*  733 */       this.cursor.value = ByteObjectOpenHashMap.this.values[i];
/*      */       
/*  735 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<ByteObjectCursor<VType>> iterator()
/*      */   {
/*  745 */     return new EntryIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <T extends ByteObjectProcedure<? super VType>> T forEach(T procedure)
/*      */   {
/*  754 */     byte[] keys = this.keys;
/*  755 */     VType[] values = this.values;
/*  756 */     boolean[] states = this.allocated;
/*      */     
/*  758 */     for (int i = 0; i < states.length; i++)
/*      */     {
/*  760 */       if (states[i] != 0) {
/*  761 */         procedure.apply(keys[i], values[i]);
/*      */       }
/*      */     }
/*  764 */     return procedure;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ByteObjectOpenHashMap<VType>.KeysContainer keys()
/*      */   {
/*  773 */     return new KeysContainer();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final class KeysContainer
/*      */     extends AbstractByteCollection
/*      */     implements ByteLookupContainer
/*      */   {
/*  782 */     private final ByteObjectOpenHashMap<VType> owner = ByteObjectOpenHashMap.this;
/*      */     
/*      */     public KeysContainer() {}
/*      */     
/*      */     public boolean contains(byte e)
/*      */     {
/*  788 */       return ByteObjectOpenHashMap.this.containsKey(e);
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ByteProcedure> T forEach(T procedure)
/*      */     {
/*  794 */       byte[] localKeys = this.owner.keys;
/*  795 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  797 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  799 */         if (localStates[i] != 0) {
/*  800 */           procedure.apply(localKeys[i]);
/*      */         }
/*      */       }
/*  803 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends BytePredicate> T forEach(T predicate)
/*      */     {
/*  809 */       byte[] localKeys = this.owner.keys;
/*  810 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  812 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  814 */         if ((localStates[i] != 0) && 
/*      */         
/*  816 */           (!predicate.apply(localKeys[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/*  821 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  827 */       return this.owner.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ByteCursor> iterator()
/*      */     {
/*  833 */       return new ByteObjectOpenHashMap.KeysIterator(ByteObjectOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int size()
/*      */     {
/*  839 */       return this.owner.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/*  845 */       this.owner.clear();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(BytePredicate predicate)
/*      */     {
/*  851 */       return this.owner.removeAll(predicate);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(byte e)
/*      */     {
/*  857 */       boolean hasKey = this.owner.containsKey(e);
/*  858 */       int result = 0;
/*  859 */       if (hasKey)
/*      */       {
/*  861 */         this.owner.remove(e);
/*  862 */         result = 1;
/*      */       }
/*  864 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class KeysIterator
/*      */     extends AbstractIterator<ByteCursor>
/*      */   {
/*      */     private final ByteCursor cursor;
/*      */     
/*      */ 
/*      */     public KeysIterator()
/*      */     {
/*  877 */       this.cursor = new ByteCursor();
/*  878 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ByteCursor fetch()
/*      */     {
/*  884 */       int i = this.cursor.index + 1;
/*  885 */       int max = ByteObjectOpenHashMap.this.keys.length;
/*  886 */       while ((i < max) && (ByteObjectOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  888 */         i++;
/*      */       }
/*      */       
/*  891 */       if (i == max) {
/*  892 */         return (ByteCursor)done();
/*      */       }
/*  894 */       this.cursor.index = i;
/*  895 */       this.cursor.value = ByteObjectOpenHashMap.this.keys[i];
/*      */       
/*  897 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectContainer<VType> values()
/*      */   {
/*  907 */     return new ValuesContainer(null);
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
/*  918 */       return ByteObjectOpenHashMap.this.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  924 */       return ByteObjectOpenHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean contains(VType value)
/*      */     {
/*  931 */       boolean[] allocated = ByteObjectOpenHashMap.this.allocated;
/*  932 */       VType[] values = ByteObjectOpenHashMap.this.values;
/*      */       
/*  934 */       for (int slot = 0; slot < allocated.length; slot++)
/*      */       {
/*  936 */         if ((allocated[slot] != 0) && (value == null ? values[slot] == null : value.equals(values[slot])))
/*      */         {
/*  938 */           return true;
/*      */         }
/*      */       }
/*  941 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectProcedure<? super VType>> T forEach(T procedure)
/*      */     {
/*  947 */       boolean[] allocated = ByteObjectOpenHashMap.this.allocated;
/*  948 */       VType[] values = ByteObjectOpenHashMap.this.values;
/*      */       
/*  950 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/*  952 */         if (allocated[i] != 0) {
/*  953 */           procedure.apply(values[i]);
/*      */         }
/*      */       }
/*  956 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectPredicate<? super VType>> T forEach(T predicate)
/*      */     {
/*  962 */       boolean[] allocated = ByteObjectOpenHashMap.this.allocated;
/*  963 */       VType[] values = ByteObjectOpenHashMap.this.values;
/*      */       
/*  965 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/*  967 */         if ((allocated[i] != 0) && 
/*      */         
/*  969 */           (!predicate.apply(values[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/*  974 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ObjectCursor<VType>> iterator()
/*      */     {
/*  980 */       return new ByteObjectOpenHashMap.ValuesIterator(ByteObjectOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(VType e)
/*      */     {
/*  986 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(ObjectPredicate<? super VType> predicate)
/*      */     {
/*  992 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/*  998 */       throw new UnsupportedOperationException();
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
/* 1011 */       this.cursor = new ObjectCursor();
/* 1012 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ObjectCursor<VType> fetch()
/*      */     {
/* 1018 */       int i = this.cursor.index + 1;
/* 1019 */       int max = ByteObjectOpenHashMap.this.keys.length;
/* 1020 */       while ((i < max) && (ByteObjectOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1022 */         i++;
/*      */       }
/*      */       
/* 1025 */       if (i == max) {
/* 1026 */         return (ObjectCursor)done();
/*      */       }
/* 1028 */       this.cursor.index = i;
/* 1029 */       this.cursor.value = ByteObjectOpenHashMap.this.values[i];
/*      */       
/* 1031 */       return this.cursor;
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
/*      */   public ByteObjectOpenHashMap<VType> clone()
/*      */   {
/*      */     try
/*      */     {
/* 1046 */       ByteObjectOpenHashMap<VType> cloned = (ByteObjectOpenHashMap)super.clone();
/*      */       
/*      */ 
/* 1049 */       cloned.keys = ((byte[])this.keys.clone());
/* 1050 */       cloned.values = ((Object[])this.values.clone());
/* 1051 */       cloned.allocated = ((boolean[])this.allocated.clone());
/*      */       
/* 1053 */       return cloned;
/*      */     }
/*      */     catch (CloneNotSupportedException e)
/*      */     {
/* 1057 */       throw new RuntimeException(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1067 */     StringBuilder buffer = new StringBuilder();
/* 1068 */     buffer.append("[");
/*      */     
/* 1070 */     boolean first = true;
/* 1071 */     for (ByteObjectCursor<VType> cursor : this)
/*      */     {
/* 1073 */       if (!first) buffer.append(", ");
/* 1074 */       buffer.append(cursor.key);
/* 1075 */       buffer.append("=>");
/* 1076 */       buffer.append(cursor.value);
/* 1077 */       first = false;
/*      */     }
/* 1079 */     buffer.append("]");
/* 1080 */     return buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> from(byte[] keys, VType[] values)
/*      */   {
/* 1088 */     if (keys.length != values.length) {
/* 1089 */       throw new IllegalArgumentException("Arrays of keys and values must have an identical length.");
/*      */     }
/* 1091 */     ByteObjectOpenHashMap<VType> map = new ByteObjectOpenHashMap();
/* 1092 */     for (int i = 0; i < keys.length; i++)
/*      */     {
/* 1094 */       map.put(keys[i], values[i]);
/*      */     }
/* 1096 */     return map;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> from(ByteObjectAssociativeContainer<VType> container)
/*      */   {
/* 1104 */     return new ByteObjectOpenHashMap(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> newInstance()
/*      */   {
/* 1113 */     return new ByteObjectOpenHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> newInstanceWithoutPerturbations()
/*      */   {
/* 1123 */     new ByteObjectOpenHashMap() {
/*      */       protected int computePerturbationValue(int capacity) {
/* 1125 */         return 0;
/*      */       }
/*      */     };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> newInstance(int initialCapacity, float loadFactor)
/*      */   {
/* 1135 */     return new ByteObjectOpenHashMap(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> newInstanceWithExpectedSize(int expectedSize)
/*      */   {
/* 1145 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <VType> ByteObjectOpenHashMap<VType> newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*      */   {
/* 1155 */     return newInstance((int)(expectedSize / loadFactor) + 1, loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteObjectOpenHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */