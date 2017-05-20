/*      */ package com.carrotsearch.hppc;
/*      */ 
/*      */ import com.carrotsearch.hppc.cursors.ByteCharCursor;
/*      */ import com.carrotsearch.hppc.cursors.ByteCursor;
/*      */ import com.carrotsearch.hppc.cursors.CharCursor;
/*      */ import com.carrotsearch.hppc.predicates.BytePredicate;
/*      */ import com.carrotsearch.hppc.predicates.CharPredicate;
/*      */ import com.carrotsearch.hppc.procedures.ByteCharProcedure;
/*      */ import com.carrotsearch.hppc.procedures.ByteProcedure;
/*      */ import com.carrotsearch.hppc.procedures.CharProcedure;
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
/*      */ public class ByteCharOpenHashMap
/*      */   implements ByteCharMap, Cloneable
/*      */ {
/*      */   public static final int MIN_CAPACITY = 4;
/*      */   public static final int DEFAULT_CAPACITY = 16;
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   public byte[] keys;
/*      */   public char[] values;
/*      */   public boolean[] allocated;
/*      */   public int assigned;
/*      */   public final float loadFactor;
/*      */   protected int resizeAt;
/*      */   protected int lastSlot;
/*      */   protected int perturbation;
/*      */   
/*      */   public ByteCharOpenHashMap()
/*      */   {
/*  120 */     this(16);
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
/*      */   public ByteCharOpenHashMap(int initialCapacity)
/*      */   {
/*  134 */     this(initialCapacity, 0.75F);
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
/*      */   public ByteCharOpenHashMap(int initialCapacity, float loadFactor)
/*      */   {
/*  150 */     initialCapacity = Math.max(initialCapacity, 4);
/*      */     
/*      */ 
/*  153 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*      */     
/*  155 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*      */     
/*  157 */     this.loadFactor = loadFactor;
/*  158 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ByteCharOpenHashMap(ByteCharAssociativeContainer container)
/*      */   {
/*  166 */     this((int)(container.size() * 1.75F));
/*  167 */     putAll(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char put(byte key, char value)
/*      */   {
/*  176 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  178 */     int mask = this.allocated.length - 1;
/*  179 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  180 */     while (this.allocated[slot] != 0)
/*      */     {
/*  182 */       if (key == this.keys[slot])
/*      */       {
/*  184 */         char oldValue = this.values[slot];
/*  185 */         this.values[slot] = value;
/*  186 */         return oldValue;
/*      */       }
/*      */       
/*  189 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  194 */     if (this.assigned == this.resizeAt) {
/*  195 */       expandAndPut(key, value, slot);
/*      */     } else {
/*  197 */       this.assigned += 1;
/*  198 */       this.allocated[slot] = true;
/*  199 */       this.keys[slot] = key;
/*  200 */       this.values[slot] = value;
/*      */     }
/*  202 */     return '\000';
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(ByteCharAssociativeContainer container)
/*      */   {
/*  212 */     int count = this.assigned;
/*  213 */     for (ByteCharCursor c : container)
/*      */     {
/*  215 */       put(c.key, c.value);
/*      */     }
/*  217 */     return this.assigned - count;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(Iterable<? extends ByteCharCursor> iterable)
/*      */   {
/*  227 */     int count = this.assigned;
/*  228 */     for (ByteCharCursor c : iterable)
/*      */     {
/*  230 */       put(c.key, c.value);
/*      */     }
/*  232 */     return this.assigned - count;
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
/*      */   public boolean putIfAbsent(byte key, char value)
/*      */   {
/*  251 */     if (!containsKey(key))
/*      */     {
/*  253 */       put(key, value);
/*  254 */       return true;
/*      */     }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char putOrAdd(byte key, char putValue, char additionValue)
/*      */   {
/*  285 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  287 */     int mask = this.allocated.length - 1;
/*  288 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  289 */     while (this.allocated[slot] != 0)
/*      */     {
/*  291 */       if (key == this.keys[slot])
/*      */       {
/*  293 */         return this.values[slot] = (char)(this.values[slot] + additionValue);
/*      */       }
/*      */       
/*  296 */       slot = slot + 1 & mask;
/*      */     }
/*      */     
/*  299 */     if (this.assigned == this.resizeAt) {
/*  300 */       expandAndPut(key, putValue, slot);
/*      */     } else {
/*  302 */       this.assigned += 1;
/*  303 */       this.allocated[slot] = true;
/*  304 */       this.keys[slot] = key;
/*  305 */       this.values[slot] = putValue;
/*      */     }
/*  307 */     return putValue;
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
/*      */   public char addTo(byte key, char additionValue)
/*      */   {
/*  335 */     return putOrAdd(key, additionValue, additionValue);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void expandAndPut(byte pendingKey, char pendingValue, int freeSlot)
/*      */   {
/*  344 */     assert (this.assigned == this.resizeAt);
/*  345 */     assert (this.allocated[freeSlot] == 0);
/*      */     
/*      */ 
/*      */ 
/*  349 */     byte[] oldKeys = this.keys;
/*  350 */     char[] oldValues = this.values;
/*  351 */     boolean[] oldAllocated = this.allocated;
/*      */     
/*  353 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*      */     
/*      */ 
/*      */ 
/*  357 */     this.lastSlot = -1;
/*  358 */     this.assigned += 1;
/*  359 */     oldAllocated[freeSlot] = true;
/*  360 */     oldKeys[freeSlot] = pendingKey;
/*  361 */     oldValues[freeSlot] = pendingValue;
/*      */     
/*      */ 
/*  364 */     byte[] keys = this.keys;
/*  365 */     char[] values = this.values;
/*  366 */     boolean[] allocated = this.allocated;
/*  367 */     int mask = allocated.length - 1;
/*  368 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*      */         break;
/*  370 */       if (oldAllocated[i] != 0)
/*      */       {
/*  372 */         byte k = oldKeys[i];
/*  373 */         char v = oldValues[i];
/*      */         
/*  375 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/*  376 */         while (allocated[slot] != 0)
/*      */         {
/*  378 */           slot = slot + 1 & mask;
/*      */         }
/*      */         
/*  381 */         allocated[slot] = true;
/*  382 */         keys[slot] = k;
/*  383 */         values[slot] = v;
/*      */       }
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
/*      */   private void allocateBuffers(int capacity)
/*      */   {
/*  398 */     byte[] keys = new byte[capacity];
/*  399 */     char[] values = new char[capacity];
/*  400 */     boolean[] allocated = new boolean[capacity];
/*      */     
/*  402 */     this.keys = keys;
/*  403 */     this.values = values;
/*  404 */     this.allocated = allocated;
/*      */     
/*  406 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/*  407 */     this.perturbation = computePerturbationValue(capacity);
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
/*  425 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char remove(byte key)
/*      */   {
/*  434 */     int mask = this.allocated.length - 1;
/*  435 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  436 */     int wrappedAround = slot;
/*  437 */     while (this.allocated[slot] != 0)
/*      */     {
/*  439 */       if (key == this.keys[slot])
/*      */       {
/*  441 */         this.assigned -= 1;
/*  442 */         char v = this.values[slot];
/*  443 */         shiftConflictingKeys(slot);
/*  444 */         return v;
/*      */       }
/*  446 */       slot = slot + 1 & mask;
/*  447 */       if (slot == wrappedAround)
/*      */         break;
/*      */     }
/*  450 */     return '\000';
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void shiftConflictingKeys(int slotCurr)
/*      */   {
/*  459 */     int mask = this.allocated.length - 1;
/*      */     int slotPrev;
/*      */     for (;;)
/*      */     {
/*  463 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*      */       
/*  465 */       while (this.allocated[slotCurr] != 0)
/*      */       {
/*  467 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/*  468 */         if (slotPrev <= slotCurr ? 
/*      */         
/*      */ 
/*  471 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  477 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*      */           break;
/*      */         }
/*  480 */         slotCurr = slotCurr + 1 & mask;
/*      */       }
/*      */       
/*  483 */       if (this.allocated[slotCurr] == 0) {
/*      */         break;
/*      */       }
/*      */       
/*  487 */       this.keys[slotPrev] = this.keys[slotCurr];
/*  488 */       this.values[slotPrev] = this.values[slotCurr];
/*      */     }
/*      */     
/*  491 */     this.allocated[slotPrev] = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(ByteContainer container)
/*      */   {
/*  503 */     int before = this.assigned;
/*      */     
/*  505 */     for (ByteCursor cursor : container)
/*      */     {
/*  507 */       remove(cursor.value);
/*      */     }
/*      */     
/*  510 */     return before - this.assigned;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int removeAll(BytePredicate predicate)
/*      */   {
/*  519 */     int before = this.assigned;
/*      */     
/*  521 */     byte[] keys = this.keys;
/*  522 */     boolean[] states = this.allocated;
/*      */     
/*  524 */     for (int i = 0; i < states.length;)
/*      */     {
/*  526 */       if (states[i] != 0)
/*      */       {
/*  528 */         if (predicate.apply(keys[i]))
/*      */         {
/*  530 */           this.assigned -= 1;
/*  531 */           shiftConflictingKeys(i);
/*      */           
/*  533 */           continue;
/*      */         }
/*      */       }
/*  536 */       i++;
/*      */     }
/*  538 */     return before - this.assigned;
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
/*      */   public char get(byte key)
/*      */   {
/*  562 */     int mask = this.allocated.length - 1;
/*  563 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  564 */     int wrappedAround = slot;
/*  565 */     while (this.allocated[slot] != 0)
/*      */     {
/*  567 */       if (key == this.keys[slot])
/*      */       {
/*  569 */         return this.values[slot];
/*      */       }
/*      */       
/*  572 */       slot = slot + 1 & mask;
/*  573 */       if (slot == wrappedAround) break;
/*      */     }
/*  575 */     return '\000';
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char getOrDefault(byte key, char defaultValue)
/*      */   {
/*  584 */     int mask = this.allocated.length - 1;
/*  585 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  586 */     int wrappedAround = slot;
/*  587 */     while (this.allocated[slot] != 0)
/*      */     {
/*  589 */       if (key == this.keys[slot])
/*      */       {
/*  591 */         return this.values[slot];
/*      */       }
/*      */       
/*  594 */       slot = slot + 1 & mask;
/*  595 */       if (slot == wrappedAround) break;
/*      */     }
/*  597 */     return defaultValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public char lget()
/*      */   {
/*  609 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  610 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  612 */     return this.values[this.lastSlot];
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
/*      */   public char lset(char key)
/*      */   {
/*  625 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  626 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  628 */     char previous = this.values[this.lastSlot];
/*  629 */     this.values[this.lastSlot] = key;
/*  630 */     return previous;
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
/*  641 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  642 */     return this.lastSlot;
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
/*  668 */     int mask = this.allocated.length - 1;
/*  669 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  670 */     int wrappedAround = slot;
/*  671 */     while (this.allocated[slot] != 0)
/*      */     {
/*  673 */       if (key == this.keys[slot])
/*      */       {
/*  675 */         this.lastSlot = slot;
/*  676 */         return true;
/*      */       }
/*  678 */       slot = slot + 1 & mask;
/*  679 */       if (slot == wrappedAround) break;
/*      */     }
/*  681 */     this.lastSlot = -1;
/*  682 */     return false;
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
/*  693 */     this.assigned = 0;
/*      */     
/*      */ 
/*  696 */     Arrays.fill(this.allocated, false);
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
/*      */   public int size()
/*      */   {
/*  709 */     return this.assigned;
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
/*  720 */     return size() == 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  729 */     int h = 0;
/*  730 */     for (ByteCharCursor c : this)
/*      */     {
/*  732 */       h += Internals.rehash(c.key) + Internals.rehash(c.value);
/*      */     }
/*  734 */     return h;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  743 */     if (obj != null)
/*      */     {
/*  745 */       if (obj == this) { return true;
/*      */       }
/*  747 */       if ((obj instanceof ByteCharMap))
/*      */       {
/*      */ 
/*  750 */         ByteCharMap other = (ByteCharMap)obj;
/*  751 */         if (other.size() == size())
/*      */         {
/*  753 */           for (ByteCharCursor c : this)
/*      */           {
/*  755 */             if (other.containsKey(c.key))
/*      */             {
/*  757 */               char v = other.get(c.key);
/*  758 */               if (c.value == v) {
/*      */                 break;
/*      */               }
/*      */             }
/*      */             else {
/*  763 */               return false;
/*      */             } }
/*  765 */           return true;
/*      */         }
/*      */       }
/*      */     }
/*  769 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */   private final class EntryIterator
/*      */     extends AbstractIterator<ByteCharCursor>
/*      */   {
/*      */     private final ByteCharCursor cursor;
/*      */     
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  781 */       this.cursor = new ByteCharCursor();
/*  782 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ByteCharCursor fetch()
/*      */     {
/*  788 */       int i = this.cursor.index + 1;
/*  789 */       int max = ByteCharOpenHashMap.this.keys.length;
/*  790 */       while ((i < max) && (ByteCharOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  792 */         i++;
/*      */       }
/*      */       
/*  795 */       if (i == max) {
/*  796 */         return (ByteCharCursor)done();
/*      */       }
/*  798 */       this.cursor.index = i;
/*  799 */       this.cursor.key = ByteCharOpenHashMap.this.keys[i];
/*  800 */       this.cursor.value = ByteCharOpenHashMap.this.values[i];
/*      */       
/*  802 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<ByteCharCursor> iterator()
/*      */   {
/*  812 */     return new EntryIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <T extends ByteCharProcedure> T forEach(T procedure)
/*      */   {
/*  821 */     byte[] keys = this.keys;
/*  822 */     char[] values = this.values;
/*  823 */     boolean[] states = this.allocated;
/*      */     
/*  825 */     for (int i = 0; i < states.length; i++)
/*      */     {
/*  827 */       if (states[i] != 0) {
/*  828 */         procedure.apply(keys[i], values[i]);
/*      */       }
/*      */     }
/*  831 */     return procedure;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public KeysContainer keys()
/*      */   {
/*  840 */     return new KeysContainer();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public final class KeysContainer
/*      */     extends AbstractByteCollection
/*      */     implements ByteLookupContainer
/*      */   {
/*  849 */     private final ByteCharOpenHashMap owner = ByteCharOpenHashMap.this;
/*      */     
/*      */     public KeysContainer() {}
/*      */     
/*      */     public boolean contains(byte e)
/*      */     {
/*  855 */       return ByteCharOpenHashMap.this.containsKey(e);
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends ByteProcedure> T forEach(T procedure)
/*      */     {
/*  861 */       byte[] localKeys = this.owner.keys;
/*  862 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  864 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  866 */         if (localStates[i] != 0) {
/*  867 */           procedure.apply(localKeys[i]);
/*      */         }
/*      */       }
/*  870 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends BytePredicate> T forEach(T predicate)
/*      */     {
/*  876 */       byte[] localKeys = this.owner.keys;
/*  877 */       boolean[] localStates = this.owner.allocated;
/*      */       
/*  879 */       for (int i = 0; i < localStates.length; i++)
/*      */       {
/*  881 */         if ((localStates[i] != 0) && 
/*      */         
/*  883 */           (!predicate.apply(localKeys[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/*  888 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  894 */       return this.owner.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<ByteCursor> iterator()
/*      */     {
/*  900 */       return new ByteCharOpenHashMap.KeysIterator(ByteCharOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int size()
/*      */     {
/*  906 */       return this.owner.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/*  912 */       this.owner.clear();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(BytePredicate predicate)
/*      */     {
/*  918 */       return this.owner.removeAll(predicate);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(byte e)
/*      */     {
/*  924 */       boolean hasKey = this.owner.containsKey(e);
/*  925 */       int result = 0;
/*  926 */       if (hasKey)
/*      */       {
/*  928 */         this.owner.remove(e);
/*  929 */         result = 1;
/*      */       }
/*  931 */       return result;
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
/*  944 */       this.cursor = new ByteCursor();
/*  945 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected ByteCursor fetch()
/*      */     {
/*  951 */       int i = this.cursor.index + 1;
/*  952 */       int max = ByteCharOpenHashMap.this.keys.length;
/*  953 */       while ((i < max) && (ByteCharOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  955 */         i++;
/*      */       }
/*      */       
/*  958 */       if (i == max) {
/*  959 */         return (ByteCursor)done();
/*      */       }
/*  961 */       this.cursor.index = i;
/*  962 */       this.cursor.value = ByteCharOpenHashMap.this.keys[i];
/*      */       
/*  964 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CharContainer values()
/*      */   {
/*  974 */     return new ValuesContainer(null);
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesContainer
/*      */     extends AbstractCharCollection
/*      */   {
/*      */     private ValuesContainer() {}
/*      */     
/*      */     public int size()
/*      */     {
/*  985 */       return ByteCharOpenHashMap.this.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  991 */       return ByteCharOpenHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean contains(char value)
/*      */     {
/*  998 */       boolean[] allocated = ByteCharOpenHashMap.this.allocated;
/*  999 */       char[] values = ByteCharOpenHashMap.this.values;
/*      */       
/* 1001 */       for (int slot = 0; slot < allocated.length; slot++)
/*      */       {
/* 1003 */         if ((allocated[slot] != 0) && (value == values[slot]))
/*      */         {
/* 1005 */           return true;
/*      */         }
/*      */       }
/* 1008 */       return false;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends CharProcedure> T forEach(T procedure)
/*      */     {
/* 1014 */       boolean[] allocated = ByteCharOpenHashMap.this.allocated;
/* 1015 */       char[] values = ByteCharOpenHashMap.this.values;
/*      */       
/* 1017 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1019 */         if (allocated[i] != 0) {
/* 1020 */           procedure.apply(values[i]);
/*      */         }
/*      */       }
/* 1023 */       return procedure;
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends CharPredicate> T forEach(T predicate)
/*      */     {
/* 1029 */       boolean[] allocated = ByteCharOpenHashMap.this.allocated;
/* 1030 */       char[] values = ByteCharOpenHashMap.this.values;
/*      */       
/* 1032 */       for (int i = 0; i < allocated.length; i++)
/*      */       {
/* 1034 */         if ((allocated[i] != 0) && 
/*      */         
/* 1036 */           (!predicate.apply(values[i]))) {
/*      */           break;
/*      */         }
/*      */       }
/*      */       
/* 1041 */       return predicate;
/*      */     }
/*      */     
/*      */ 
/*      */     public Iterator<CharCursor> iterator()
/*      */     {
/* 1047 */       return new ByteCharOpenHashMap.ValuesIterator(ByteCharOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(char e)
/*      */     {
/* 1053 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(CharPredicate predicate)
/*      */     {
/* 1059 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public void clear()
/*      */     {
/* 1065 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesIterator
/*      */     extends AbstractIterator<CharCursor>
/*      */   {
/*      */     private final CharCursor cursor;
/*      */     
/*      */ 
/*      */     public ValuesIterator()
/*      */     {
/* 1078 */       this.cursor = new CharCursor();
/* 1079 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected CharCursor fetch()
/*      */     {
/* 1085 */       int i = this.cursor.index + 1;
/* 1086 */       int max = ByteCharOpenHashMap.this.keys.length;
/* 1087 */       while ((i < max) && (ByteCharOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1089 */         i++;
/*      */       }
/*      */       
/* 1092 */       if (i == max) {
/* 1093 */         return (CharCursor)done();
/*      */       }
/* 1095 */       this.cursor.index = i;
/* 1096 */       this.cursor.value = ByteCharOpenHashMap.this.values[i];
/*      */       
/* 1098 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ByteCharOpenHashMap clone()
/*      */   {
/*      */     try
/*      */     {
/* 1111 */       ByteCharOpenHashMap cloned = (ByteCharOpenHashMap)super.clone();
/*      */       
/*      */ 
/* 1114 */       cloned.keys = ((byte[])this.keys.clone());
/* 1115 */       cloned.values = ((char[])this.values.clone());
/* 1116 */       cloned.allocated = ((boolean[])this.allocated.clone());
/*      */       
/* 1118 */       return cloned;
/*      */     }
/*      */     catch (CloneNotSupportedException e)
/*      */     {
/* 1122 */       throw new RuntimeException(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/* 1132 */     StringBuilder buffer = new StringBuilder();
/* 1133 */     buffer.append("[");
/*      */     
/* 1135 */     boolean first = true;
/* 1136 */     for (ByteCharCursor cursor : this)
/*      */     {
/* 1138 */       if (!first) buffer.append(", ");
/* 1139 */       buffer.append(cursor.key);
/* 1140 */       buffer.append("=>");
/* 1141 */       buffer.append(cursor.value);
/* 1142 */       first = false;
/*      */     }
/* 1144 */     buffer.append("]");
/* 1145 */     return buffer.toString();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap from(byte[] keys, char[] values)
/*      */   {
/* 1153 */     if (keys.length != values.length) {
/* 1154 */       throw new IllegalArgumentException("Arrays of keys and values must have an identical length.");
/*      */     }
/* 1156 */     ByteCharOpenHashMap map = new ByteCharOpenHashMap();
/* 1157 */     for (int i = 0; i < keys.length; i++)
/*      */     {
/* 1159 */       map.put(keys[i], values[i]);
/*      */     }
/* 1161 */     return map;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap from(ByteCharAssociativeContainer container)
/*      */   {
/* 1169 */     return new ByteCharOpenHashMap(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap newInstance()
/*      */   {
/* 1178 */     return new ByteCharOpenHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap newInstanceWithoutPerturbations()
/*      */   {
/* 1188 */     new ByteCharOpenHashMap() {
/*      */       protected int computePerturbationValue(int capacity) {
/* 1190 */         return 0;
/*      */       }
/*      */     };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap newInstance(int initialCapacity, float loadFactor)
/*      */   {
/* 1200 */     return new ByteCharOpenHashMap(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap newInstanceWithExpectedSize(int expectedSize)
/*      */   {
/* 1210 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ByteCharOpenHashMap newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*      */   {
/* 1220 */     return newInstance((int)(expectedSize / loadFactor) + 1, loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteCharOpenHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */