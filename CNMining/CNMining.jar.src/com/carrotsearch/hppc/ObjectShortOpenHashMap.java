/*      */ package com.carrotsearch.hppc;
/*      */ 
/*      */ import com.carrotsearch.hppc.cursors.ObjectCursor;
/*      */ import com.carrotsearch.hppc.cursors.ObjectShortCursor;
/*      */ import com.carrotsearch.hppc.cursors.ShortCursor;
/*      */ import com.carrotsearch.hppc.predicates.ObjectPredicate;
/*      */ import com.carrotsearch.hppc.predicates.ShortPredicate;
/*      */ import com.carrotsearch.hppc.procedures.ObjectProcedure;
/*      */ import com.carrotsearch.hppc.procedures.ObjectShortProcedure;
/*      */ import com.carrotsearch.hppc.procedures.ShortProcedure;
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
/*      */ public class ObjectShortOpenHashMap<KType>
/*      */   implements ObjectShortMap<KType>, Cloneable
/*      */ {
/*      */   public static final int MIN_CAPACITY = 4;
/*      */   public static final int DEFAULT_CAPACITY = 16;
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   public KType[] keys;
/*      */   public short[] values;
/*      */   public boolean[] allocated;
/*      */   public int assigned;
/*      */   public final float loadFactor;
/*      */   protected int resizeAt;
/*      */   protected int lastSlot;
/*      */   protected int perturbation;
/*      */   
/*      */   public ObjectShortOpenHashMap()
/*      */   {
/*  137 */     this(16);
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
/*      */   public ObjectShortOpenHashMap(int initialCapacity)
/*      */   {
/*  151 */     this(initialCapacity, 0.75F);
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
/*      */   public ObjectShortOpenHashMap(int initialCapacity, float loadFactor)
/*      */   {
/*  167 */     initialCapacity = Math.max(initialCapacity, 4);
/*      */     
/*      */ 
/*  170 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*      */     
/*  172 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*      */     
/*  174 */     this.loadFactor = loadFactor;
/*  175 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectShortOpenHashMap(ObjectShortAssociativeContainer<KType> container)
/*      */   {
/*  183 */     this((int)(container.size() * 1.75F));
/*  184 */     putAll(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short put(KType key, short value)
/*      */   {
/*  193 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  195 */     int mask = this.allocated.length - 1;
/*  196 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  197 */     while (this.allocated[slot] != 0)
/*      */     {
/*  199 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  201 */         short oldValue = this.values[slot];
/*  202 */         this.values[slot] = value;
/*  203 */         return oldValue;
/*      */       }
/*      */       
/*  206 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  211 */     if (this.assigned == this.resizeAt) {
/*  212 */       expandAndPut(key, value, slot);
/*      */     } else {
/*  214 */       this.assigned += 1;
/*  215 */       this.allocated[slot] = true;
/*  216 */       this.keys[slot] = key;
/*  217 */       this.values[slot] = value;
/*      */     }
/*  219 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(ObjectShortAssociativeContainer<? extends KType> container)
/*      */   {
/*  229 */     int count = this.assigned;
/*  230 */     for (ObjectShortCursor<? extends KType> c : container)
/*      */     {
/*  232 */       put(c.key, c.value);
/*      */     }
/*  234 */     return this.assigned - count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(Iterable<? extends ObjectShortCursor<? extends KType>> iterable)
/*      */   {
/*  244 */     int count = this.assigned;
/*  245 */     for (ObjectShortCursor<? extends KType> c : iterable)
/*      */     {
/*  247 */       put(c.key, c.value);
/*      */     }
/*  249 */     return this.assigned - count;
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
/*      */   public boolean putIfAbsent(KType key, short value)
/*      */   {
/*  268 */     if (!containsKey(key))
/*      */     {
/*  270 */       put(key, value);
/*  271 */       return true;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short putOrAdd(KType key, short putValue, short additionValue)
/*      */   {
/*  302 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  304 */     int mask = this.allocated.length - 1;
/*  305 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  306 */     while (this.allocated[slot] != 0)
/*      */     {
/*  308 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  310 */         return this.values[slot] = (short)(this.values[slot] + additionValue);
/*      */       }
/*      */       
/*  313 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*  316 */     if (this.assigned == this.resizeAt) {
/*  317 */       expandAndPut(key, putValue, slot);
/*      */     } else {
/*  319 */       this.assigned += 1;
/*  320 */       this.allocated[slot] = true;
/*  321 */       this.keys[slot] = key;
/*  322 */       this.values[slot] = putValue;
/*      */     }
/*  324 */     return putValue;
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
/*      */   public short addTo(KType key, short additionValue)
/*      */   {
/*  352 */     return putOrAdd(key, additionValue, additionValue);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void expandAndPut(KType pendingKey, short pendingValue, int freeSlot)
/*      */   {
/*  361 */     assert (this.assigned == this.resizeAt);
/*  362 */     assert (this.allocated[freeSlot] == 0);
/*      */     
/*      */ 
/*      */ 
/*  366 */     KType[] oldKeys = this.keys;
/*  367 */     short[] oldValues = this.values;
/*  368 */     boolean[] oldAllocated = this.allocated;
/*      */     
/*  370 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*      */     
/*      */ 
/*      */ 
/*  374 */     this.lastSlot = -1;
/*  375 */     this.assigned += 1;
/*  376 */     oldAllocated[freeSlot] = true;
/*  377 */     oldKeys[freeSlot] = pendingKey;
/*  378 */     oldValues[freeSlot] = pendingValue;
/*      */     
/*      */ 
/*  381 */     KType[] keys = this.keys;
/*  382 */     short[] values = this.values;
/*  383 */     boolean[] allocated = this.allocated;
/*  384 */     int mask = allocated.length - 1;
/*  385 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*      */         break;
/*  387 */       if (oldAllocated[i] != 0)
/*      */       {
/*  389 */         KType k = oldKeys[i];
/*  390 */         short v = oldValues[i];
/*      */         
/*  392 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/*  393 */         while (allocated[slot] != 0)
/*      */         {
/*  395 */           slot = slot + 1 & mask;
/*      */         }
/*      */         
/*  398 */         allocated[slot] = true;
/*  399 */         keys[slot] = k;
/*  400 */         values[slot] = v;
/*      */       }
/*      */     }
/*      */     
/*  404 */     Arrays.fill(oldKeys, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void allocateBuffers(int capacity)
/*      */   {
/*  415 */     KType[] keys = (Object[])Internals.newArray(capacity);
/*  416 */     short[] values = new short[capacity];
/*  417 */     boolean[] allocated = new boolean[capacity];
/*      */     
/*  419 */     this.keys = keys;
/*  420 */     this.values = values;
/*  421 */     this.allocated = allocated;
/*      */     
/*  423 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/*  424 */     this.perturbation = computePerturbationValue(capacity);
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
/*  442 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short remove(KType key)
/*      */   {
/*  451 */     int mask = this.allocated.length - 1;
/*  452 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  453 */     int wrappedAround = slot;
/*  454 */     while (this.allocated[slot] != 0)
/*      */     {
/*  456 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  458 */         this.assigned -= 1;
/*  459 */         short v = this.values[slot];
/*  460 */         shiftConflictingKeys(slot);
/*  461 */         return v;
/*      */       }
/*  463 */       slot = slot + 1 & mask;
/*  464 */       if (slot == wrappedAround)
/*      */         break;
/*      */     }
/*  467 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void shiftConflictingKeys(int slotCurr)
/*      */   {
/*  476 */     int mask = this.allocated.length - 1;
/*      */     int slotPrev;
/*      */     for (;;)
/*      */     {
/*  480 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*      */       
/*  482 */       while (this.allocated[slotCurr] != 0)
/*      */       {
/*  484 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/*  485 */         if (slotPrev <= slotCurr ? 
/*      */         
/*      */ 
/*  488 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  494 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*      */           break;
/*      */         }
/*  497 */         slotCurr = slotCurr + 1 & mask;
/*      */       }
/*      */       
/*  500 */       if (this.allocated[slotCurr] == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  504 */       this.keys[slotPrev] = this.keys[slotCurr];
/*  505 */       this.values[slotPrev] = this.values[slotCurr];
/*      */     }
/*      */     
/*  508 */     this.allocated[slotPrev] = false;
/*      */     
/*      */ 
/*  511 */     this.keys[slotPrev] = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(ObjectContainer<? extends KType> container)
/*      */   {
/*  522 */     int before = this.assigned;
/*      */     
/*  524 */     for (ObjectCursor<? extends KType> cursor : container)
/*      */     {
/*  526 */       remove(cursor.value);
/*      */     }
/*      */     
/*  529 */     return before - this.assigned;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(ObjectPredicate<? super KType> predicate)
/*      */   {
/*  538 */     int before = this.assigned;
/*      */     
/*  540 */     KType[] keys = this.keys;
/*  541 */     boolean[] states = this.allocated;
/*      */     
/*  543 */     for (int i = 0; i < states.length;)
/*      */     {
/*  545 */       if (states[i] != 0)
/*      */       {
/*  547 */         if (predicate.apply(keys[i]))
/*      */         {
/*  549 */           this.assigned -= 1;
/*  550 */           shiftConflictingKeys(i);
/*      */           
/*  552 */           continue;
/*      */         }
/*      */       }
/*  555 */       i++;
/*      */     }
/*  557 */     return before - this.assigned;
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
/*      */   public short get(KType key)
/*      */   {
/*  581 */     int mask = this.allocated.length - 1;
/*  582 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  583 */     int wrappedAround = slot;
/*  584 */     while (this.allocated[slot] != 0)
/*      */     {
/*  586 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  588 */         return this.values[slot];
/*      */       }
/*      */       
/*  591 */       slot = slot + 1 & mask;
/*  592 */       if (slot == wrappedAround) break;
/*      */     }
/*  594 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short getOrDefault(KType key, short defaultValue)
/*      */   {
/*  603 */     int mask = this.allocated.length - 1;
/*  604 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  605 */     int wrappedAround = slot;
/*  606 */     while (this.allocated[slot] != 0)
/*      */     {
/*  608 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  610 */         return this.values[slot];
/*      */       }
/*      */       
/*  613 */       slot = slot + 1 & mask;
/*  614 */       if (slot == wrappedAround) break;
/*      */     }
/*  616 */     return defaultValue;
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
/*  639 */     return (KType)this.keys[lslot()];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public short lget()
/*      */   {
/*  650 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  651 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  653 */     return this.values[this.lastSlot];
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
/*      */   public short lset(short key)
/*      */   {
/*  666 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  667 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  669 */     short previous = this.values[this.lastSlot];
/*  670 */     this.values[this.lastSlot] = key;
/*  671 */     return previous;
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
/*  682 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  683 */     return this.lastSlot;
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
/*  713 */     int mask = this.allocated.length - 1;
/*  714 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  715 */     int wrappedAround = slot;
/*  716 */     while (this.allocated[slot] != 0)
/*      */     {
/*  718 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*      */       {
/*  720 */         this.lastSlot = slot;
/*  721 */         return true;
/*      */       }
/*  723 */       slot = slot + 1 & mask;
/*  724 */       if (slot == wrappedAround) break;
/*      */     }
/*  726 */     this.lastSlot = -1;
/*  727 */     return false;
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
/*  738 */     this.assigned = 0;
/*      */     
/*      */ 
/*  741 */     Arrays.fill(this.allocated, false);
/*      */     
/*      */ 
/*  744 */     Arrays.fill(this.keys, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int size()
/*      */   {
/*  756 */     return this.assigned;
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
/*  767 */     return size() == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  776 */     int h = 0;
/*  777 */     for (ObjectShortCursor<KType> c : this)
/*      */     {
/*  779 */       h += Internals.rehash(c.key) + Internals.rehash(c.value);
/*      */     }
/*  781 */     return h;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  790 */     if (obj != null)
/*      */     {
/*  792 */       if (obj == this) { return true;
/*      */       }
/*  794 */       if ((obj instanceof ObjectShortMap))
/*      */       {
/*      */ 
/*      */ 
/*      */ 
/*  799 */         ObjectShortMap<KType> other = (ObjectShortMap)obj;
/*  800 */         if (other.size() == size())
/*      */         {
/*  802 */           for (ObjectShortCursor<KType> c : this)
/*      */           {
/*  804 */             if (other.containsKey(c.key))
/*      */             {
/*  806 */               short v = other.get(c.key);
/*  807 */               if (c.value == v) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */             else {
/*  812 */               return false;
/*      */             } }
/*  814 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  818 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   private final class EntryIterator
/*      */     extends AbstractIterator<ObjectShortCursor<KType>>
/*      */   {
/*      */     private final ObjectShortCursor<KType> cursor;
/*      */     
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  830 */       this.cursor = new ObjectShortCursor();
/*  831 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ObjectShortCursor<KType> fetch()
/*      */     {
/*  837 */       int i = this.cursor.index + 1;
/*  838 */       int max = ObjectShortOpenHashMap.this.keys.length;
/*  839 */       while ((i < max) && (ObjectShortOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  841 */         i++;
/*      */       }
/*      */       
/*  844 */       if (i == max) {
/*  845 */         return (ObjectShortCursor)done();
/*      */       }
/*  847 */       this.cursor.index = i;
/*  848 */       this.cursor.key = ObjectShortOpenHashMap.this.keys[i];
/*  849 */       this.cursor.value = ObjectShortOpenHashMap.this.values[i];
/*      */       
/*  851 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<ObjectShortCursor<KType>> iterator()
/*      */   {
/*  861 */     return new EntryIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <T extends ObjectShortProcedure<? super KType>> T forEach(T procedure)
/*      */   {
/*  870 */     KType[] keys = this.keys;
/*  871 */     short[] values = this.values;
/*  872 */     boolean[] states = this.allocated;
/*      */     
/*  874 */     for (int i = 0; i < states.length; i++)
/*      */     {
/*  876 */       if (states[i] != 0) {
/*  877 */         procedure.apply(keys[i], values[i]);
/*      */       }
/*      */     }
/*  880 */     return procedure;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ObjectShortOpenHashMap<KType>.KeysContainer keys()
/*      */   {
/*  889 */     return new KeysContainer();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final class KeysContainer
/*      */     extends AbstractObjectCollection<KType>
/*      */     implements ObjectLookupContainer<KType>
/*      */   {
/*  898 */     private final ObjectShortOpenHashMap<KType> owner = ObjectShortOpenHashMap.this;
/*      */     
/*      */     public KeysContainer() {}
/*      */     
/*      */     public boolean contains(KType e)
/*      */     {
/*  904 */       return ObjectShortOpenHashMap.this.containsKey(e);
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectProcedure<? super KType>> T forEach(T procedure)
/*      */     {
/*  910 */       KType[] localKeys = this.owner.keys;
/*  911 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  913 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  915 */         if (localStates[i] != 0) {
/*  916 */           procedure.apply(localKeys[i]);
/*      */         }
/*      */       }
/*  919 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ObjectPredicate<? super KType>> T forEach(T predicate)
/*      */     {
/*  925 */       KType[] localKeys = this.owner.keys;
/*  926 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  928 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  930 */         if ((localStates[i] != 0) && 
/*      */         
/*  932 */           (!predicate.apply(localKeys[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/*  937 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  943 */       return this.owner.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ObjectCursor<KType>> iterator()
/*      */     {
/*  949 */       return new ObjectShortOpenHashMap.KeysIterator(ObjectShortOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int size()
/*      */     {
/*  955 */       return this.owner.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/*  961 */       this.owner.clear();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(ObjectPredicate<? super KType> predicate)
/*      */     {
/*  967 */       return this.owner.removeAll(predicate);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(KType e)
/*      */     {
/*  973 */       boolean hasKey = this.owner.containsKey(e);
/*  974 */       int result = 0;
/*  975 */       if (hasKey)
/*      */       {
/*  977 */         this.owner.remove(e);
/*  978 */         result = 1;
/*      */       }
/*  980 */       return result;
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
/*  993 */       this.cursor = new ObjectCursor();
/*  994 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ObjectCursor<KType> fetch()
/*      */     {
/* 1000 */       int i = this.cursor.index + 1;
/* 1001 */       int max = ObjectShortOpenHashMap.this.keys.length;
/* 1002 */       while ((i < max) && (ObjectShortOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1004 */         i++;
/*      */       }
/*      */       
/* 1007 */       if (i == max) {
/* 1008 */         return (ObjectCursor)done();
/*      */       }
/* 1010 */       this.cursor.index = i;
/* 1011 */       this.cursor.value = ObjectShortOpenHashMap.this.keys[i];
/*      */       
/* 1013 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ShortContainer values()
/*      */   {
/* 1023 */     return new ValuesContainer(null);
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesContainer
/*      */     extends AbstractShortCollection
/*      */   {
/*      */     private ValuesContainer() {}
/*      */     
/*      */     public int size()
/*      */     {
/* 1034 */       return ObjectShortOpenHashMap.this.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/* 1040 */       return ObjectShortOpenHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean contains(short value)
/*      */     {
/* 1047 */       boolean[] allocated = ObjectShortOpenHashMap.this.allocated;
/* 1048 */       short[] values = ObjectShortOpenHashMap.this.values;
/*      */       
/* 1050 */       for (int slot = 0; slot < allocated.length; slot++)
/*      */       {
/* 1052 */         if ((allocated[slot] != 0) && (value == values[slot]))
/*      */         {
/* 1054 */           return true;
/*      */         }
/*      */       }
/* 1057 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ShortProcedure> T forEach(T procedure)
/*      */     {
/* 1063 */       boolean[] allocated = ObjectShortOpenHashMap.this.allocated;
/* 1064 */       short[] values = ObjectShortOpenHashMap.this.values;
/*      */       
/* 1066 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1068 */         if (allocated[i] != 0) {
/* 1069 */           procedure.apply(values[i]);
/*      */         }
/*      */       }
/* 1072 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ShortPredicate> T forEach(T predicate)
/*      */     {
/* 1078 */       boolean[] allocated = ObjectShortOpenHashMap.this.allocated;
/* 1079 */       short[] values = ObjectShortOpenHashMap.this.values;
/*      */       
/* 1081 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1083 */         if ((allocated[i] != 0) && 
/*      */         
/* 1085 */           (!predicate.apply(values[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1090 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ShortCursor> iterator()
/*      */     {
/* 1096 */       return new ObjectShortOpenHashMap.ValuesIterator(ObjectShortOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(short e)
/*      */     {
/* 1102 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(ShortPredicate predicate)
/*      */     {
/* 1108 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/* 1114 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesIterator
/*      */     extends AbstractIterator<ShortCursor>
/*      */   {
/*      */     private final ShortCursor cursor;
/*      */     
/*      */ 
/*      */     public ValuesIterator()
/*      */     {
/* 1127 */       this.cursor = new ShortCursor();
/* 1128 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ShortCursor fetch()
/*      */     {
/* 1134 */       int i = this.cursor.index + 1;
/* 1135 */       int max = ObjectShortOpenHashMap.this.keys.length;
/* 1136 */       while ((i < max) && (ObjectShortOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1138 */         i++;
/*      */       }
/*      */       
/* 1141 */       if (i == max) {
/* 1142 */         return (ShortCursor)done();
/*      */       }
/* 1144 */       this.cursor.index = i;
/* 1145 */       this.cursor.value = ObjectShortOpenHashMap.this.values[i];
/*      */       
/* 1147 */       return this.cursor;
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
/*      */   public ObjectShortOpenHashMap<KType> clone()
/*      */   {
/*      */     try
/*      */     {
/* 1162 */       ObjectShortOpenHashMap<KType> cloned = (ObjectShortOpenHashMap)super.clone();
/*      */       
/*      */ 
/* 1165 */       cloned.keys = ((Object[])this.keys.clone());
/* 1166 */       cloned.values = ((short[])this.values.clone());
/* 1167 */       cloned.allocated = ((boolean[])this.allocated.clone());
/*      */       
/* 1169 */       return cloned;
/*      */     }
/*      */     catch (CloneNotSupportedException e)
/*      */     {
/* 1173 */       throw new RuntimeException(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1183 */     StringBuilder buffer = new StringBuilder();
/* 1184 */     buffer.append("[");
/*      */     
/* 1186 */     boolean first = true;
/* 1187 */     for (ObjectShortCursor<KType> cursor : this)
/*      */     {
/* 1189 */       if (!first) buffer.append(", ");
/* 1190 */       buffer.append(cursor.key);
/* 1191 */       buffer.append("=>");
/* 1192 */       buffer.append(cursor.value);
/* 1193 */       first = false;
/*      */     }
/* 1195 */     buffer.append("]");
/* 1196 */     return buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> from(KType[] keys, short[] values)
/*      */   {
/* 1204 */     if (keys.length != values.length) {
/* 1205 */       throw new IllegalArgumentException("Arrays of keys and values must have an identical length.");
/*      */     }
/* 1207 */     ObjectShortOpenHashMap<KType> map = new ObjectShortOpenHashMap();
/* 1208 */     for (int i = 0; i < keys.length; i++)
/*      */     {
/* 1210 */       map.put(keys[i], values[i]);
/*      */     }
/* 1212 */     return map;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> from(ObjectShortAssociativeContainer<KType> container)
/*      */   {
/* 1220 */     return new ObjectShortOpenHashMap(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> newInstance()
/*      */   {
/* 1229 */     return new ObjectShortOpenHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> newInstanceWithoutPerturbations()
/*      */   {
/* 1239 */     new ObjectShortOpenHashMap() {
/*      */       protected int computePerturbationValue(int capacity) {
/* 1241 */         return 0;
/*      */       }
/*      */     };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> newInstance(int initialCapacity, float loadFactor)
/*      */   {
/* 1251 */     return new ObjectShortOpenHashMap(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> newInstanceWithExpectedSize(int expectedSize)
/*      */   {
/* 1261 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static <KType> ObjectShortOpenHashMap<KType> newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*      */   {
/* 1271 */     return newInstance((int)(expectedSize / loadFactor) + 1, loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectShortOpenHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */