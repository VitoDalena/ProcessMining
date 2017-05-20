/*      */ package com.carrotsearch.hppc;
/*      */ 
/*      */ import com.carrotsearch.hppc.cursors.KTypeCursor;
/*      */ import com.carrotsearch.hppc.cursors.KTypeVTypeCursor;
/*      */ import com.carrotsearch.hppc.predicates.KTypePredicate;
/*      */ import com.carrotsearch.hppc.procedures.KTypeProcedure;
/*      */ import com.carrotsearch.hppc.procedures.KTypeVTypeProcedure;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class KTypeVTypeOpenHashMap<KType, VType>
/*      */   implements KTypeVTypeMap<KType, VType>, Cloneable
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
/*      */   public KTypeVTypeOpenHashMap()
/*      */   {
/*  172 */     this(16);
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
/*      */   public KTypeVTypeOpenHashMap(int initialCapacity)
/*      */   {
/*  186 */     this(initialCapacity, 0.75F);
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
/*      */   public KTypeVTypeOpenHashMap(int initialCapacity, float loadFactor)
/*      */   {
/*  202 */     initialCapacity = Math.max(initialCapacity, 4);
/*      */     
/*      */ 
/*  205 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*      */     
/*  207 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*      */     
/*  209 */     this.loadFactor = loadFactor;
/*  210 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public KTypeVTypeOpenHashMap(KTypeVTypeAssociativeContainer<KType, VType> container)
/*      */   {
/*  218 */     this((int)(container.size() * 1.75F));
/*  219 */     putAll(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType put(KType key, VType value)
/*      */   {
/*  228 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  230 */     int mask = this.allocated.length - 1;
/*  231 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  232 */     while (this.allocated[slot] != 0)
/*      */     {
/*  234 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*      */       {
/*  236 */         VType oldValue = this.values[slot];
/*  237 */         this.values[slot] = value;
/*  238 */         return oldValue;
/*      */       }
/*      */       
/*  241 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  246 */     if (this.assigned == this.resizeAt) {
/*  247 */       expandAndPut(key, value, slot);
/*      */     } else {
/*  249 */       this.assigned += 1;
/*  250 */       this.allocated[slot] = true;
/*  251 */       this.keys[slot] = key;
/*  252 */       this.values[slot] = value;
/*      */     }
/*  254 */     return (VType)Intrinsics.defaultVTypeValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(KTypeVTypeAssociativeContainer<? extends KType, ? extends VType> container)
/*      */   {
/*  264 */     int count = this.assigned;
/*  265 */     for (KTypeVTypeCursor<? extends KType, ? extends VType> c : container)
/*      */     {
/*  267 */       put(c.key, c.value);
/*      */     }
/*  269 */     return this.assigned - count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(Iterable<? extends KTypeVTypeCursor<? extends KType, ? extends VType>> iterable)
/*      */   {
/*  279 */     int count = this.assigned;
/*  280 */     for (KTypeVTypeCursor<? extends KType, ? extends VType> c : iterable)
/*      */     {
/*  282 */       put(c.key, c.value);
/*      */     }
/*  284 */     return this.assigned - count;
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
/*  303 */     if (!containsKey(key))
/*      */     {
/*  305 */       put(key, value);
/*  306 */       return true;
/*      */     }
/*  308 */     return false;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  398 */     assert (this.assigned == this.resizeAt);
/*  399 */     assert (this.allocated[freeSlot] == 0);
/*      */     
/*      */ 
/*      */ 
/*  403 */     KType[] oldKeys = this.keys;
/*  404 */     VType[] oldValues = this.values;
/*  405 */     boolean[] oldAllocated = this.allocated;
/*      */     
/*  407 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*      */     
/*      */ 
/*      */ 
/*  411 */     this.lastSlot = -1;
/*  412 */     this.assigned += 1;
/*  413 */     oldAllocated[freeSlot] = true;
/*  414 */     oldKeys[freeSlot] = pendingKey;
/*  415 */     oldValues[freeSlot] = pendingValue;
/*      */     
/*      */ 
/*  418 */     KType[] keys = this.keys;
/*  419 */     VType[] values = this.values;
/*  420 */     boolean[] allocated = this.allocated;
/*  421 */     int mask = allocated.length - 1;
/*  422 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*      */         break;
/*  424 */       if (oldAllocated[i] != 0)
/*      */       {
/*  426 */         KType k = oldKeys[i];
/*  427 */         VType v = oldValues[i];
/*      */         
/*  429 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/*  430 */         while (allocated[slot] != 0)
/*      */         {
/*  432 */           slot = slot + 1 & mask;
/*      */         }
/*      */         
/*  435 */         allocated[slot] = true;
/*  436 */         keys[slot] = k;
/*  437 */         values[slot] = v;
/*      */       }
/*      */     }
/*      */     
/*  441 */     Arrays.fill(oldKeys, null);
/*  442 */     Arrays.fill(oldValues, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void allocateBuffers(int capacity)
/*      */   {
/*  452 */     KType[] keys = (Object[])Intrinsics.newKTypeArray(capacity);
/*  453 */     VType[] values = (Object[])Intrinsics.newVTypeArray(capacity);
/*  454 */     boolean[] allocated = new boolean[capacity];
/*      */     
/*  456 */     this.keys = keys;
/*  457 */     this.values = values;
/*  458 */     this.allocated = allocated;
/*      */     
/*  460 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/*  461 */     this.perturbation = computePerturbationValue(capacity);
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
/*  479 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType remove(KType key)
/*      */   {
/*  488 */     int mask = this.allocated.length - 1;
/*  489 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  490 */     int wrappedAround = slot;
/*  491 */     while (this.allocated[slot] != 0)
/*      */     {
/*  493 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*      */       {
/*  495 */         this.assigned -= 1;
/*  496 */         VType v = this.values[slot];
/*  497 */         shiftConflictingKeys(slot);
/*  498 */         return v;
/*      */       }
/*  500 */       slot = slot + 1 & mask;
/*  501 */       if (slot == wrappedAround)
/*      */         break;
/*      */     }
/*  504 */     return (VType)Intrinsics.defaultVTypeValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void shiftConflictingKeys(int slotCurr)
/*      */   {
/*  513 */     int mask = this.allocated.length - 1;
/*      */     int slotPrev;
/*      */     for (;;)
/*      */     {
/*  517 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*      */       
/*  519 */       while (this.allocated[slotCurr] != 0)
/*      */       {
/*  521 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/*  522 */         if (slotPrev <= slotCurr ? 
/*      */         
/*      */ 
/*  525 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  531 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*      */           break;
/*      */         }
/*  534 */         slotCurr = slotCurr + 1 & mask;
/*      */       }
/*      */       
/*  537 */       if (this.allocated[slotCurr] == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  541 */       this.keys[slotPrev] = this.keys[slotCurr];
/*  542 */       this.values[slotPrev] = this.values[slotCurr];
/*      */     }
/*      */     
/*  545 */     this.allocated[slotPrev] = false;
/*      */     
/*      */ 
/*  548 */     this.keys[slotPrev] = Intrinsics.defaultKTypeValue();
/*      */     
/*      */ 
/*  551 */     this.values[slotPrev] = Intrinsics.defaultVTypeValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(KTypeContainer<? extends KType> container)
/*      */   {
/*  561 */     int before = this.assigned;
/*      */     
/*  563 */     for (KTypeCursor<? extends KType> cursor : container)
/*      */     {
/*  565 */       remove(cursor.value);
/*      */     }
/*      */     
/*  568 */     return before - this.assigned;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(KTypePredicate<? super KType> predicate)
/*      */   {
/*  577 */     int before = this.assigned;
/*      */     
/*  579 */     KType[] keys = this.keys;
/*  580 */     boolean[] states = this.allocated;
/*      */     
/*  582 */     for (int i = 0; i < states.length;)
/*      */     {
/*  584 */       if (states[i] != 0)
/*      */       {
/*  586 */         if (predicate.apply(keys[i]))
/*      */         {
/*  588 */           this.assigned -= 1;
/*  589 */           shiftConflictingKeys(i);
/*      */           
/*  591 */           continue;
/*      */         }
/*      */       }
/*  594 */       i++;
/*      */     }
/*  596 */     return before - this.assigned;
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
/*  620 */     int mask = this.allocated.length - 1;
/*  621 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  622 */     int wrappedAround = slot;
/*  623 */     while (this.allocated[slot] != 0)
/*      */     {
/*  625 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*      */       {
/*  627 */         return (VType)this.values[slot];
/*      */       }
/*      */       
/*  630 */       slot = slot + 1 & mask;
/*  631 */       if (slot == wrappedAround) break;
/*      */     }
/*  633 */     return (VType)Intrinsics.defaultVTypeValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public VType getOrDefault(KType key, VType defaultValue)
/*      */   {
/*  642 */     int mask = this.allocated.length - 1;
/*  643 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  644 */     int wrappedAround = slot;
/*  645 */     while (this.allocated[slot] != 0)
/*      */     {
/*  647 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*      */       {
/*  649 */         return (VType)this.values[slot];
/*      */       }
/*      */       
/*  652 */       slot = slot + 1 & mask;
/*  653 */       if (slot == wrappedAround) break;
/*      */     }
/*  655 */     return defaultValue;
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
/*  678 */     return (KType)this.keys[lslot()];
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
/*  689 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  690 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  692 */     return (VType)this.values[this.lastSlot];
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
/*  705 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  706 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  708 */     VType previous = this.values[this.lastSlot];
/*  709 */     this.values[this.lastSlot] = key;
/*  710 */     return previous;
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
/*  721 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  722 */     return this.lastSlot;
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
/*      */ 
/*      */   public boolean containsKey(KType key)
/*      */   {
/*  753 */     int mask = this.allocated.length - 1;
/*  754 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  755 */     int wrappedAround = slot;
/*  756 */     while (this.allocated[slot] != 0)
/*      */     {
/*  758 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*      */       {
/*  760 */         this.lastSlot = slot;
/*  761 */         return true;
/*      */       }
/*  763 */       slot = slot + 1 & mask;
/*  764 */       if (slot == wrappedAround) break;
/*      */     }
/*  766 */     this.lastSlot = -1;
/*  767 */     return false;
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
/*  778 */     this.assigned = 0;
/*      */     
/*      */ 
/*  781 */     Arrays.fill(this.allocated, false);
/*      */     
/*      */ 
/*  784 */     Arrays.fill(this.keys, null);
/*      */     
/*      */ 
/*      */ 
/*  788 */     Arrays.fill(this.values, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  798 */     return this.assigned;
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
/*  809 */     return size() == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  818 */     int h = 0;
/*  819 */     for (KTypeVTypeCursor<KType, VType> c : this)
/*      */     {
/*  821 */       h += Internals.rehash(c.key) + Internals.rehash(c.value);
/*      */     }
/*  823 */     return h;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  832 */     if (obj != null)
/*      */     {
/*  834 */       if (obj == this) { return true;
/*      */       }
/*  836 */       if ((obj instanceof KTypeVTypeMap))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  841 */         KTypeVTypeMap<KType, VType> other = (KTypeVTypeMap)obj;
/*  842 */         if (other.size() == size())
/*      */         {
/*  844 */           for (KTypeVTypeCursor<KType, VType> c : this)
/*      */           {
/*  846 */             if (other.containsKey(c.key))
/*      */             {
/*  848 */               VType v = other.get(c.key);
/*  849 */               if (Intrinsics.equalsVType(c.value, v)) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */             else {
/*  854 */               return false;
/*      */             } }
/*  856 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  860 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   private final class EntryIterator
/*      */     extends AbstractIterator<KTypeVTypeCursor<KType, VType>>
/*      */   {
/*      */     private final KTypeVTypeCursor<KType, VType> cursor;
/*      */     
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  872 */       this.cursor = new KTypeVTypeCursor();
/*  873 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected KTypeVTypeCursor<KType, VType> fetch()
/*      */     {
/*  879 */       int i = this.cursor.index + 1;
/*  880 */       int max = KTypeVTypeOpenHashMap.this.keys.length;
/*  881 */       while ((i < max) && (KTypeVTypeOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  883 */         i++;
/*      */       }
/*      */       
/*  886 */       if (i == max) {
/*  887 */         return (KTypeVTypeCursor)done();
/*      */       }
/*  889 */       this.cursor.index = i;
/*  890 */       this.cursor.key = KTypeVTypeOpenHashMap.this.keys[i];
/*  891 */       this.cursor.value = KTypeVTypeOpenHashMap.this.values[i];
/*      */       
/*  893 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<KTypeVTypeCursor<KType, VType>> iterator()
/*      */   {
/*  903 */     return new EntryIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <T extends KTypeVTypeProcedure<? super KType, ? super VType>> T forEach(T procedure)
/*      */   {
/*  912 */     KType[] keys = this.keys;
/*  913 */     VType[] values = this.values;
/*  914 */     boolean[] states = this.allocated;
/*      */     
/*  916 */     for (int i = 0; i < states.length; i++)
/*      */     {
/*  918 */       if (states[i] != 0) {
/*  919 */         procedure.apply(keys[i], values[i]);
/*      */       }
/*      */     }
/*  922 */     return procedure;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public KTypeVTypeOpenHashMap<KType, VType>.KeysContainer keys()
/*      */   {
/*  931 */     return new KeysContainer();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final class KeysContainer
/*      */     extends AbstractKTypeCollection<KType>
/*      */     implements KTypeLookupContainer<KType>
/*      */   {
/*  940 */     private final KTypeVTypeOpenHashMap<KType, VType> owner = KTypeVTypeOpenHashMap.this;
/*      */     
/*      */     public KeysContainer() {}
/*      */     
/*      */     public boolean contains(KType e)
/*      */     {
/*  946 */       return KTypeVTypeOpenHashMap.this.containsKey(e);
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends KTypeProcedure<? super KType>> T forEach(T procedure)
/*      */     {
/*  952 */       KType[] localKeys = this.owner.keys;
/*  953 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  955 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  957 */         if (localStates[i] != 0) {
/*  958 */           procedure.apply(localKeys[i]);
/*      */         }
/*      */       }
/*  961 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends KTypePredicate<? super KType>> T forEach(T predicate)
/*      */     {
/*  967 */       KType[] localKeys = this.owner.keys;
/*  968 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  970 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  972 */         if ((localStates[i] != 0) && 
/*      */         
/*  974 */           (!predicate.apply(localKeys[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/*  979 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  985 */       return this.owner.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<KTypeCursor<KType>> iterator()
/*      */     {
/*  991 */       return new KTypeVTypeOpenHashMap.KeysIterator(KTypeVTypeOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int size()
/*      */     {
/*  997 */       return this.owner.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/* 1003 */       this.owner.clear();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(KTypePredicate<? super KType> predicate)
/*      */     {
/* 1009 */       return this.owner.removeAll(predicate);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(KType e)
/*      */     {
/* 1015 */       boolean hasKey = this.owner.containsKey(e);
/* 1016 */       int result = 0;
/* 1017 */       if (hasKey)
/*      */       {
/* 1019 */         this.owner.remove(e);
/* 1020 */         result = 1;
/*      */       }
/* 1022 */       return result;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class KeysIterator
/*      */     extends AbstractIterator<KTypeCursor<KType>>
/*      */   {
/*      */     private final KTypeCursor<KType> cursor;
/*      */     
/*      */ 
/*      */     public KeysIterator()
/*      */     {
/* 1035 */       this.cursor = new KTypeCursor();
/* 1036 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected KTypeCursor<KType> fetch()
/*      */     {
/* 1042 */       int i = this.cursor.index + 1;
/* 1043 */       int max = KTypeVTypeOpenHashMap.this.keys.length;
/* 1044 */       while ((i < max) && (KTypeVTypeOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1046 */         i++;
/*      */       }
/*      */       
/* 1049 */       if (i == max) {
/* 1050 */         return (KTypeCursor)done();
/*      */       }
/* 1052 */       this.cursor.index = i;
/* 1053 */       this.cursor.value = KTypeVTypeOpenHashMap.this.keys[i];
/*      */       
/* 1055 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public KTypeContainer<VType> values()
/*      */   {
/* 1065 */     return new ValuesContainer(null);
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesContainer
/*      */     extends AbstractKTypeCollection<VType>
/*      */   {
/*      */     private ValuesContainer() {}
/*      */     
/*      */     public int size()
/*      */     {
/* 1076 */       return KTypeVTypeOpenHashMap.this.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/* 1082 */       return KTypeVTypeOpenHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean contains(VType value)
/*      */     {
/* 1089 */       boolean[] allocated = KTypeVTypeOpenHashMap.this.allocated;
/* 1090 */       VType[] values = KTypeVTypeOpenHashMap.this.values;
/*      */       
/* 1092 */       for (int slot = 0; slot < allocated.length; slot++)
/*      */       {
/* 1094 */         if ((allocated[slot] != 0) && (Intrinsics.equalsVType(value, values[slot])))
/*      */         {
/* 1096 */           return true;
/*      */         }
/*      */       }
/* 1099 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends KTypeProcedure<? super VType>> T forEach(T procedure)
/*      */     {
/* 1105 */       boolean[] allocated = KTypeVTypeOpenHashMap.this.allocated;
/* 1106 */       VType[] values = KTypeVTypeOpenHashMap.this.values;
/*      */       
/* 1108 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1110 */         if (allocated[i] != 0) {
/* 1111 */           procedure.apply(values[i]);
/*      */         }
/*      */       }
/* 1114 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends KTypePredicate<? super VType>> T forEach(T predicate)
/*      */     {
/* 1120 */       boolean[] allocated = KTypeVTypeOpenHashMap.this.allocated;
/* 1121 */       VType[] values = KTypeVTypeOpenHashMap.this.values;
/*      */       
/* 1123 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1125 */         if ((allocated[i] != 0) && 
/*      */         
/* 1127 */           (!predicate.apply(values[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1132 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<KTypeCursor<VType>> iterator()
/*      */     {
/* 1138 */       return new KTypeVTypeOpenHashMap.ValuesIterator(KTypeVTypeOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(VType e)
/*      */     {
/* 1144 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(KTypePredicate<? super VType> predicate)
/*      */     {
/* 1150 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/* 1156 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesIterator
/*      */     extends AbstractIterator<KTypeCursor<VType>>
/*      */   {
/*      */     private final KTypeCursor<VType> cursor;
/*      */     
/*      */ 
/*      */     public ValuesIterator()
/*      */     {
/* 1169 */       this.cursor = new KTypeCursor();
/* 1170 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected KTypeCursor<VType> fetch()
/*      */     {
/* 1176 */       int i = this.cursor.index + 1;
/* 1177 */       int max = KTypeVTypeOpenHashMap.this.keys.length;
/* 1178 */       while ((i < max) && (KTypeVTypeOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1180 */         i++;
/*      */       }
/*      */       
/* 1183 */       if (i == max) {
/* 1184 */         return (KTypeCursor)done();
/*      */       }
/* 1186 */       this.cursor.index = i;
/* 1187 */       this.cursor.value = KTypeVTypeOpenHashMap.this.values[i];
/*      */       
/* 1189 */       return this.cursor;
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
/*      */   public KTypeVTypeOpenHashMap<KType, VType> clone()
/*      */   {
/*      */     try
/*      */     {
/* 1204 */       KTypeVTypeOpenHashMap<KType, VType> cloned = (KTypeVTypeOpenHashMap)super.clone();
/*      */       
/*      */ 
/* 1207 */       cloned.keys = ((Object[])this.keys.clone());
/* 1208 */       cloned.values = ((Object[])this.values.clone());
/* 1209 */       cloned.allocated = ((boolean[])this.allocated.clone());
/*      */       
/* 1211 */       return cloned;
/*      */     }
/*      */     catch (CloneNotSupportedException e)
/*      */     {
/* 1215 */       throw new RuntimeException(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1225 */     StringBuilder buffer = new StringBuilder();
/* 1226 */     buffer.append("[");
/*      */     
/* 1228 */     boolean first = true;
/* 1229 */     for (KTypeVTypeCursor<KType, VType> cursor : this)
/*      */     {
/* 1231 */       if (!first) buffer.append(", ");
/* 1232 */       buffer.append(cursor.key);
/* 1233 */       buffer.append("=>");
/* 1234 */       buffer.append(cursor.value);
/* 1235 */       first = false;
/*      */     }
/* 1237 */     buffer.append("]");
/* 1238 */     return buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> from(KType[] keys, VType[] values)
/*      */   {
/* 1246 */     if (keys.length != values.length) {
/* 1247 */       throw new IllegalArgumentException("Arrays of keys and values must have an identical length.");
/*      */     }
/* 1249 */     KTypeVTypeOpenHashMap<KType, VType> map = new KTypeVTypeOpenHashMap();
/* 1250 */     for (int i = 0; i < keys.length; i++)
/*      */     {
/* 1252 */       map.put(keys[i], values[i]);
/*      */     }
/* 1254 */     return map;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> from(KTypeVTypeAssociativeContainer<KType, VType> container)
/*      */   {
/* 1262 */     return new KTypeVTypeOpenHashMap(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> newInstance()
/*      */   {
/* 1271 */     return new KTypeVTypeOpenHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> newInstanceWithoutPerturbations()
/*      */   {
/* 1281 */     new KTypeVTypeOpenHashMap() {
/*      */       protected int computePerturbationValue(int capacity) {
/* 1283 */         return 0;
/*      */       }
/*      */     };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> newInstance(int initialCapacity, float loadFactor)
/*      */   {
/* 1293 */     return new KTypeVTypeOpenHashMap(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> newInstanceWithExpectedSize(int expectedSize)
/*      */   {
/* 1303 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType, VType> KTypeVTypeOpenHashMap<KType, VType> newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*      */   {
/* 1313 */     return newInstance((int)(expectedSize / loadFactor) + 1, loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeVTypeOpenHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */