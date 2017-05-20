/*      */ package com.carrotsearch.hppc;
/*      */ 
/*      */ import com.carrotsearch.hppc.cursors.DoubleCursor;
/*      */ import com.carrotsearch.hppc.cursors.DoubleIntCursor;
/*      */ import com.carrotsearch.hppc.cursors.IntCursor;
/*      */ import com.carrotsearch.hppc.predicates.DoublePredicate;
/*      */ import com.carrotsearch.hppc.predicates.IntPredicate;
/*      */ import com.carrotsearch.hppc.procedures.DoubleIntProcedure;
/*      */ import com.carrotsearch.hppc.procedures.DoubleProcedure;
/*      */ import com.carrotsearch.hppc.procedures.IntProcedure;
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
/*      */ public class DoubleIntOpenHashMap
/*      */   implements DoubleIntMap, Cloneable
/*      */ {
/*      */   public static final int MIN_CAPACITY = 4;
/*      */   public static final int DEFAULT_CAPACITY = 16;
/*      */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*      */   public double[] keys;
/*      */   public int[] values;
/*      */   public boolean[] allocated;
/*      */   public int assigned;
/*      */   public final float loadFactor;
/*      */   protected int resizeAt;
/*      */   protected int lastSlot;
/*      */   protected int perturbation;
/*      */   
/*      */   public DoubleIntOpenHashMap()
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
/*      */   public DoubleIntOpenHashMap(int initialCapacity)
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
/*      */   public DoubleIntOpenHashMap(int initialCapacity, float loadFactor)
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
/*      */   public DoubleIntOpenHashMap(DoubleIntAssociativeContainer container)
/*      */   {
/*  166 */     this((int)(container.size() * 1.75F));
/*  167 */     putAll(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int put(double key, int value)
/*      */   {
/*  176 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  178 */     int mask = this.allocated.length - 1;
/*  179 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  180 */     while (this.allocated[slot] != 0)
/*      */     {
/*  182 */       if (Double.doubleToLongBits(key) == Double.doubleToLongBits(this.keys[slot]))
/*      */       {
/*  184 */         int oldValue = this.values[slot];
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
/*  202 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int putAll(DoubleIntAssociativeContainer container)
/*      */   {
/*  212 */     int count = this.assigned;
/*  213 */     for (DoubleIntCursor c : container)
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
/*      */   public int putAll(Iterable<? extends DoubleIntCursor> iterable)
/*      */   {
/*  227 */     int count = this.assigned;
/*  228 */     for (DoubleIntCursor c : iterable)
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
/*      */   public boolean putIfAbsent(double key, int value)
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
/*      */   public int putOrAdd(double key, int putValue, int additionValue)
/*      */   {
/*  285 */     assert (this.assigned < this.allocated.length);
/*      */     
/*  287 */     int mask = this.allocated.length - 1;
/*  288 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  289 */     while (this.allocated[slot] != 0)
/*      */     {
/*  291 */       if (Double.doubleToLongBits(key) == Double.doubleToLongBits(this.keys[slot]))
/*      */       {
/*  293 */         return this.values[slot] += additionValue;
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
/*      */   public int addTo(double key, int additionValue)
/*      */   {
/*  335 */     return putOrAdd(key, additionValue, additionValue);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void expandAndPut(double pendingKey, int pendingValue, int freeSlot)
/*      */   {
/*  344 */     assert (this.assigned == this.resizeAt);
/*  345 */     assert (this.allocated[freeSlot] == 0);
/*      */     
/*      */ 
/*      */ 
/*  349 */     double[] oldKeys = this.keys;
/*  350 */     int[] oldValues = this.values;
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
/*  364 */     double[] keys = this.keys;
/*  365 */     int[] values = this.values;
/*  366 */     boolean[] allocated = this.allocated;
/*  367 */     int mask = allocated.length - 1;
/*  368 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*      */         break;
/*  370 */       if (oldAllocated[i] != 0)
/*      */       {
/*  372 */         double k = oldKeys[i];
/*  373 */         int v = oldValues[i];
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
/*  398 */     double[] keys = new double[capacity];
/*  399 */     int[] values = new int[capacity];
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
/*      */   public int remove(double key)
/*      */   {
/*  434 */     int mask = this.allocated.length - 1;
/*  435 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  436 */     int wrappedAround = slot;
/*  437 */     while (this.allocated[slot] != 0)
/*      */     {
/*  439 */       if (Double.doubleToLongBits(key) == Double.doubleToLongBits(this.keys[slot]))
/*      */       {
/*  441 */         this.assigned -= 1;
/*  442 */         int v = this.values[slot];
/*  443 */         shiftConflictingKeys(slot);
/*  444 */         return v;
/*      */       }
/*  446 */       slot = slot + 1 & mask;
/*  447 */       if (slot == wrappedAround)
/*      */         break;
/*      */     }
/*  450 */     return 0;
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
/*      */   public int removeAll(DoubleContainer container)
/*      */   {
/*  503 */     int before = this.assigned;
/*      */     
/*  505 */     for (DoubleCursor cursor : container)
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
/*      */   public int removeAll(DoublePredicate predicate)
/*      */   {
/*  519 */     int before = this.assigned;
/*      */     
/*  521 */     double[] keys = this.keys;
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
/*      */   public int get(double key)
/*      */   {
/*  562 */     int mask = this.allocated.length - 1;
/*  563 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  564 */     int wrappedAround = slot;
/*  565 */     while (this.allocated[slot] != 0)
/*      */     {
/*  567 */       if (Double.doubleToLongBits(key) == Double.doubleToLongBits(this.keys[slot]))
/*      */       {
/*  569 */         return this.values[slot];
/*      */       }
/*      */       
/*  572 */       slot = slot + 1 & mask;
/*  573 */       if (slot == wrappedAround) break;
/*      */     }
/*  575 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getOrDefault(double key, int defaultValue)
/*      */   {
/*  584 */     int mask = this.allocated.length - 1;
/*  585 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  586 */     int wrappedAround = slot;
/*  587 */     while (this.allocated[slot] != 0)
/*      */     {
/*  589 */       if (Double.doubleToLongBits(key) == Double.doubleToLongBits(this.keys[slot]))
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
/*      */   public int lget()
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
/*      */   public int lset(int key)
/*      */   {
/*  625 */     assert (this.lastSlot >= 0) : "Call containsKey() first.";
/*  626 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*      */     
/*  628 */     int previous = this.values[this.lastSlot];
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
/*      */   public boolean containsKey(double key)
/*      */   {
/*  668 */     int mask = this.allocated.length - 1;
/*  669 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*  670 */     int wrappedAround = slot;
/*  671 */     while (this.allocated[slot] != 0)
/*      */     {
/*  673 */       if (Double.doubleToLongBits(key) == Double.doubleToLongBits(this.keys[slot]))
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
/*  730 */     for (DoubleIntCursor c : this)
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
/*  747 */       if ((obj instanceof DoubleIntMap))
/*      */       {
/*      */ 
/*  750 */         DoubleIntMap other = (DoubleIntMap)obj;
/*  751 */         if (other.size() == size())
/*      */         {
/*  753 */           for (DoubleIntCursor c : this)
/*      */           {
/*  755 */             if (other.containsKey(c.key))
/*      */             {
/*  757 */               int v = other.get(c.key);
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
/*      */     extends AbstractIterator<DoubleIntCursor>
/*      */   {
/*      */     private final DoubleIntCursor cursor;
/*      */     
/*      */ 
/*      */     public EntryIterator()
/*      */     {
/*  781 */       this.cursor = new DoubleIntCursor();
/*  782 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected DoubleIntCursor fetch()
/*      */     {
/*  788 */       int i = this.cursor.index + 1;
/*  789 */       int max = DoubleIntOpenHashMap.this.keys.length;
/*  790 */       while ((i < max) && (DoubleIntOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  792 */         i++;
/*      */       }
/*      */       
/*  795 */       if (i == max) {
/*  796 */         return (DoubleIntCursor)done();
/*      */       }
/*  798 */       this.cursor.index = i;
/*  799 */       this.cursor.key = DoubleIntOpenHashMap.this.keys[i];
/*  800 */       this.cursor.value = DoubleIntOpenHashMap.this.values[i];
/*      */       
/*  802 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Iterator<DoubleIntCursor> iterator()
/*      */   {
/*  812 */     return new EntryIterator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public <T extends DoubleIntProcedure> T forEach(T procedure)
/*      */   {
/*  821 */     double[] keys = this.keys;
/*  822 */     int[] values = this.values;
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
/*      */     extends AbstractDoubleCollection
/*      */     implements DoubleLookupContainer
/*      */   {
/*  849 */     private final DoubleIntOpenHashMap owner = DoubleIntOpenHashMap.this;
/*      */     
/*      */     public KeysContainer() {}
/*      */     
/*      */     public boolean contains(double e)
/*      */     {
/*  855 */       return DoubleIntOpenHashMap.this.containsKey(e);
/*      */     }
/*      */     
/*      */ 
/*      */     public <T extends DoubleProcedure> T forEach(T procedure)
/*      */     {
/*  861 */       double[] localKeys = this.owner.keys;
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
/*      */     public <T extends DoublePredicate> T forEach(T predicate)
/*      */     {
/*  876 */       double[] localKeys = this.owner.keys;
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
/*      */     public Iterator<DoubleCursor> iterator()
/*      */     {
/*  900 */       return new DoubleIntOpenHashMap.KeysIterator(DoubleIntOpenHashMap.this);
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
/*      */     public int removeAll(DoublePredicate predicate)
/*      */     {
/*  918 */       return this.owner.removeAll(predicate);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(double e)
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
/*      */     extends AbstractIterator<DoubleCursor>
/*      */   {
/*      */     private final DoubleCursor cursor;
/*      */     
/*      */ 
/*      */     public KeysIterator()
/*      */     {
/*  944 */       this.cursor = new DoubleCursor();
/*  945 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected DoubleCursor fetch()
/*      */     {
/*  951 */       int i = this.cursor.index + 1;
/*  952 */       int max = DoubleIntOpenHashMap.this.keys.length;
/*  953 */       while ((i < max) && (DoubleIntOpenHashMap.this.allocated[i] == 0))
/*      */       {
/*  955 */         i++;
/*      */       }
/*      */       
/*  958 */       if (i == max) {
/*  959 */         return (DoubleCursor)done();
/*      */       }
/*  961 */       this.cursor.index = i;
/*  962 */       this.cursor.value = DoubleIntOpenHashMap.this.keys[i];
/*      */       
/*  964 */       return this.cursor;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public IntContainer values()
/*      */   {
/*  974 */     return new ValuesContainer(null);
/*      */   }
/*      */   
/*      */ 
/*      */   private final class ValuesContainer
/*      */     extends AbstractIntCollection
/*      */   {
/*      */     private ValuesContainer() {}
/*      */     
/*      */     public int size()
/*      */     {
/*  985 */       return DoubleIntOpenHashMap.this.size();
/*      */     }
/*      */     
/*      */ 
/*      */     public boolean isEmpty()
/*      */     {
/*  991 */       return DoubleIntOpenHashMap.this.isEmpty();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */     public boolean contains(int value)
/*      */     {
/*  998 */       boolean[] allocated = DoubleIntOpenHashMap.this.allocated;
/*  999 */       int[] values = DoubleIntOpenHashMap.this.values;
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
/*      */     public <T extends IntProcedure> T forEach(T procedure)
/*      */     {
/* 1014 */       boolean[] allocated = DoubleIntOpenHashMap.this.allocated;
/* 1015 */       int[] values = DoubleIntOpenHashMap.this.values;
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
/*      */     public <T extends IntPredicate> T forEach(T predicate)
/*      */     {
/* 1029 */       boolean[] allocated = DoubleIntOpenHashMap.this.allocated;
/* 1030 */       int[] values = DoubleIntOpenHashMap.this.values;
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
/*      */     public Iterator<IntCursor> iterator()
/*      */     {
/* 1047 */       return new DoubleIntOpenHashMap.ValuesIterator(DoubleIntOpenHashMap.this);
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAllOccurrences(int e)
/*      */     {
/* 1053 */       throw new UnsupportedOperationException();
/*      */     }
/*      */     
/*      */ 
/*      */     public int removeAll(IntPredicate predicate)
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
/*      */     extends AbstractIterator<IntCursor>
/*      */   {
/*      */     private final IntCursor cursor;
/*      */     
/*      */ 
/*      */     public ValuesIterator()
/*      */     {
/* 1078 */       this.cursor = new IntCursor();
/* 1079 */       this.cursor.index = -1;
/*      */     }
/*      */     
/*      */ 
/*      */     protected IntCursor fetch()
/*      */     {
/* 1085 */       int i = this.cursor.index + 1;
/* 1086 */       int max = DoubleIntOpenHashMap.this.keys.length;
/* 1087 */       while ((i < max) && (DoubleIntOpenHashMap.this.allocated[i] == 0))
/*      */       {
/* 1089 */         i++;
/*      */       }
/*      */       
/* 1092 */       if (i == max) {
/* 1093 */         return (IntCursor)done();
/*      */       }
/* 1095 */       this.cursor.index = i;
/* 1096 */       this.cursor.value = DoubleIntOpenHashMap.this.values[i];
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
/*      */   public DoubleIntOpenHashMap clone()
/*      */   {
/*      */     try
/*      */     {
/* 1111 */       DoubleIntOpenHashMap cloned = (DoubleIntOpenHashMap)super.clone();
/*      */       
/*      */ 
/* 1114 */       cloned.keys = ((double[])this.keys.clone());
/* 1115 */       cloned.values = ((int[])this.values.clone());
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
/* 1136 */     for (DoubleIntCursor cursor : this)
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
/*      */   public static DoubleIntOpenHashMap from(double[] keys, int[] values)
/*      */   {
/* 1153 */     if (keys.length != values.length) {
/* 1154 */       throw new IllegalArgumentException("Arrays of keys and values must have an identical length.");
/*      */     }
/* 1156 */     DoubleIntOpenHashMap map = new DoubleIntOpenHashMap();
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
/*      */   public static DoubleIntOpenHashMap from(DoubleIntAssociativeContainer container)
/*      */   {
/* 1169 */     return new DoubleIntOpenHashMap(container);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DoubleIntOpenHashMap newInstance()
/*      */   {
/* 1178 */     return new DoubleIntOpenHashMap();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DoubleIntOpenHashMap newInstanceWithoutPerturbations()
/*      */   {
/* 1188 */     new DoubleIntOpenHashMap() {
/*      */       protected int computePerturbationValue(int capacity) {
/* 1190 */         return 0;
/*      */       }
/*      */     };
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DoubleIntOpenHashMap newInstance(int initialCapacity, float loadFactor)
/*      */   {
/* 1200 */     return new DoubleIntOpenHashMap(initialCapacity, loadFactor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DoubleIntOpenHashMap newInstanceWithExpectedSize(int expectedSize)
/*      */   {
/* 1210 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static DoubleIntOpenHashMap newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*      */   {
/* 1220 */     return newInstance((int)(expectedSize / loadFactor) + 1, loadFactor);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleIntOpenHashMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */