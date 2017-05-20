/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.KTypeCursor;
/*     */ import com.carrotsearch.hppc.predicates.KTypePredicate;
/*     */ import com.carrotsearch.hppc.procedures.KTypeProcedure;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
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
/*     */ public class KTypeOpenHashSet<KType>
/*     */   extends AbstractKTypeCollection<KType>
/*     */   implements KTypeLookupContainer<KType>, KTypeSet<KType>, Cloneable
/*     */ {
/*     */   public static final int MIN_CAPACITY = 4;
/*     */   public static final int DEFAULT_CAPACITY = 16;
/*     */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*     */   public KType[] keys;
/*     */   public boolean[] allocated;
/*     */   public int assigned;
/*     */   public final float loadFactor;
/*     */   protected int resizeAt;
/*     */   protected int lastSlot;
/*     */   protected int perturbation;
/*     */   
/*     */   public KTypeOpenHashSet()
/*     */   {
/* 143 */     this(16, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeOpenHashSet(int initialCapacity)
/*     */   {
/* 152 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeOpenHashSet(int initialCapacity, float loadFactor)
/*     */   {
/* 160 */     initialCapacity = Math.max(initialCapacity, 4);
/*     */     
/*     */ 
/* 163 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*     */     
/* 165 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*     */     
/* 167 */     this.loadFactor = loadFactor;
/* 168 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeOpenHashSet(KTypeContainer<KType> container)
/*     */   {
/* 176 */     this((int)(container.size() * 1.75F));
/* 177 */     addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(KType e)
/*     */   {
/* 186 */     assert (this.assigned < this.allocated.length);
/*     */     
/* 188 */     int mask = this.allocated.length - 1;
/* 189 */     int slot = Internals.rehash(e, this.perturbation) & mask;
/* 190 */     while (this.allocated[slot] != 0)
/*     */     {
/* 192 */       if (Intrinsics.equalsKType(e, this.keys[slot]))
/*     */       {
/* 194 */         return false;
/*     */       }
/*     */       
/* 197 */       slot = slot + 1 & mask;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 202 */     if (this.assigned == this.resizeAt) {
/* 203 */       expandAndAdd(e, slot);
/*     */     } else {
/* 205 */       this.assigned += 1;
/* 206 */       this.allocated[slot] = true;
/* 207 */       this.keys[slot] = e;
/*     */     }
/* 209 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int add(KType e1, KType e2)
/*     */   {
/* 217 */     int count = 0;
/* 218 */     if (add(e1)) count++;
/* 219 */     if (add(e2)) count++;
/* 220 */     return count;
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
/*     */   public int add(KType... elements)
/*     */   {
/* 233 */     int count = 0;
/* 234 */     for (KType e : elements)
/* 235 */       if (add(e)) count++;
/* 236 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(KTypeContainer<? extends KType> container)
/*     */   {
/* 247 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(Iterable<? extends KTypeCursor<? extends KType>> iterable)
/*     */   {
/* 258 */     int count = 0;
/* 259 */     for (KTypeCursor<? extends KType> cursor : iterable)
/*     */     {
/* 261 */       if (add(cursor.value)) count++;
/*     */     }
/* 263 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void expandAndAdd(KType pendingKey, int freeSlot)
/*     */   {
/* 272 */     assert (this.assigned == this.resizeAt);
/* 273 */     assert (this.allocated[freeSlot] == 0);
/*     */     
/*     */ 
/*     */ 
/* 277 */     KType[] oldKeys = this.keys;
/* 278 */     boolean[] oldAllocated = this.allocated;
/*     */     
/* 280 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*     */     
/*     */ 
/*     */ 
/* 284 */     this.lastSlot = -1;
/* 285 */     this.assigned += 1;
/* 286 */     oldAllocated[freeSlot] = true;
/* 287 */     oldKeys[freeSlot] = pendingKey;
/*     */     
/*     */ 
/* 290 */     KType[] keys = this.keys;
/* 291 */     boolean[] allocated = this.allocated;
/* 292 */     int mask = allocated.length - 1;
/* 293 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 295 */       if (oldAllocated[i] != 0)
/*     */       {
/* 297 */         KType k = oldKeys[i];
/*     */         
/* 299 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/* 300 */         while (allocated[slot] != 0)
/*     */         {
/* 302 */           slot = slot + 1 & mask;
/*     */         }
/*     */         
/* 305 */         allocated[slot] = true;
/* 306 */         keys[slot] = k;
/*     */       }
/*     */     }
/*     */     
/* 310 */     Arrays.fill(oldKeys, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void allocateBuffers(int capacity)
/*     */   {
/* 321 */     KType[] keys = (Object[])Intrinsics.newKTypeArray(capacity);
/* 322 */     boolean[] allocated = new boolean[capacity];
/*     */     
/* 324 */     this.keys = keys;
/* 325 */     this.allocated = allocated;
/*     */     
/* 327 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/* 328 */     this.perturbation = computePerturbationValue(capacity);
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
/*     */   protected int computePerturbationValue(int capacity)
/*     */   {
/* 346 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(KType key)
/*     */   {
/* 355 */     return remove(key) ? 1 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(KType key)
/*     */   {
/* 363 */     int mask = this.allocated.length - 1;
/* 364 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*     */     
/* 366 */     while (this.allocated[slot] != 0)
/*     */     {
/* 368 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*     */       {
/* 370 */         this.assigned -= 1;
/* 371 */         shiftConflictingKeys(slot);
/* 372 */         return true;
/*     */       }
/* 374 */       slot = slot + 1 & mask;
/*     */     }
/*     */     
/* 377 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void shiftConflictingKeys(int slotCurr)
/*     */   {
/* 386 */     int mask = this.allocated.length - 1;
/*     */     int slotPrev;
/*     */     for (;;)
/*     */     {
/* 390 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*     */       
/* 392 */       while (this.allocated[slotCurr] != 0)
/*     */       {
/* 394 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/* 395 */         if (slotPrev <= slotCurr ? 
/*     */         
/*     */ 
/* 398 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 404 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*     */           break;
/*     */         }
/* 407 */         slotCurr = slotCurr + 1 & mask;
/*     */       }
/*     */       
/* 410 */       if (this.allocated[slotCurr] == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 414 */       this.keys[slotPrev] = this.keys[slotCurr];
/*     */     }
/*     */     
/* 417 */     this.allocated[slotPrev] = false;
/*     */     
/*     */ 
/* 420 */     this.keys[slotPrev] = Intrinsics.defaultKTypeValue();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType lkey()
/*     */   {
/* 432 */     assert (this.lastSlot >= 0) : "Call contains() first.";
/* 433 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*     */     
/* 435 */     return (KType)this.keys[this.lastSlot];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lslot()
/*     */   {
/* 447 */     assert (this.lastSlot >= 0) : "Call contains() first.";
/* 448 */     return this.lastSlot;
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
/*     */   public boolean contains(KType key)
/*     */   {
/* 463 */     int mask = this.allocated.length - 1;
/* 464 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/* 465 */     while (this.allocated[slot] != 0)
/*     */     {
/* 467 */       if (Intrinsics.equalsKType(key, this.keys[slot]))
/*     */       {
/* 469 */         this.lastSlot = slot;
/* 470 */         return true;
/*     */       }
/* 472 */       slot = slot + 1 & mask;
/*     */     }
/* 474 */     this.lastSlot = -1;
/* 475 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 486 */     this.assigned = 0;
/*     */     
/* 488 */     Arrays.fill(this.allocated, false);
/* 489 */     Arrays.fill(this.keys, Intrinsics.defaultKTypeValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 498 */     return this.assigned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 507 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 516 */     int h = 0;
/*     */     
/* 518 */     KType[] keys = this.keys;
/* 519 */     boolean[] states = this.allocated;
/* 520 */     int i = states.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 522 */       if (states[i] != 0)
/*     */       {
/* 524 */         h += Internals.rehash(keys[i]);
/*     */       }
/*     */     }
/*     */     
/* 528 */     return h;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 540 */     if (obj != null)
/*     */     {
/* 542 */       if (obj == this) { return true;
/*     */       }
/* 544 */       if ((obj instanceof KTypeSet))
/*     */       {
/* 546 */         KTypeSet<Object> other = (KTypeSet)obj;
/* 547 */         if (other.size() == size())
/*     */         {
/* 549 */           for (KTypeCursor<KType> c : this)
/*     */           {
/* 551 */             if (!other.contains(c.value))
/*     */             {
/* 553 */               return false;
/*     */             }
/*     */           }
/* 556 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 560 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   private final class EntryIterator
/*     */     extends AbstractIterator<KTypeCursor<KType>>
/*     */   {
/*     */     private final KTypeCursor<KType> cursor;
/*     */     
/*     */ 
/*     */     public EntryIterator()
/*     */     {
/* 572 */       this.cursor = new KTypeCursor();
/* 573 */       this.cursor.index = -1;
/*     */     }
/*     */     
/*     */ 
/*     */     protected KTypeCursor<KType> fetch()
/*     */     {
/* 579 */       int max = KTypeOpenHashSet.this.keys.length;
/*     */       
/* 581 */       int i = this.cursor.index + 1;
/* 582 */       while ((i < KTypeOpenHashSet.this.keys.length) && (KTypeOpenHashSet.this.allocated[i] == 0))
/*     */       {
/* 584 */         i++;
/*     */       }
/*     */       
/* 587 */       if (i == max) {
/* 588 */         return (KTypeCursor)done();
/*     */       }
/* 590 */       this.cursor.index = i;
/* 591 */       this.cursor.value = KTypeOpenHashSet.this.keys[i];
/* 592 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<KTypeCursor<KType>> iterator()
/*     */   {
/* 602 */     return new EntryIterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypeProcedure<? super KType>> T forEach(T procedure)
/*     */   {
/* 611 */     KType[] keys = this.keys;
/* 612 */     boolean[] states = this.allocated;
/*     */     
/* 614 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 616 */       if (states[i] != 0) {
/* 617 */         procedure.apply(keys[i]);
/*     */       }
/*     */     }
/* 620 */     return procedure;
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
/*     */   public Object[] toArray()
/*     */   {
/* 633 */     KType[] cloned = (Object[])Intrinsics.newKTypeArray(this.assigned);
/* 634 */     int i = 0; for (int j = 0; i < this.keys.length; i++)
/* 635 */       if (this.allocated[i] != 0)
/* 636 */         cloned[(j++)] = this.keys[i];
/* 637 */     return cloned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeOpenHashSet<KType> clone()
/*     */   {
/*     */     try
/*     */     {
/* 651 */       KTypeOpenHashSet<KType> cloned = (KTypeOpenHashSet)super.clone();
/* 652 */       cloned.keys = ((Object[])this.keys.clone());
/* 653 */       cloned.allocated = ((boolean[])this.allocated.clone());
/* 654 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 658 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypePredicate<? super KType>> T forEach(T predicate)
/*     */   {
/* 668 */     KType[] keys = this.keys;
/* 669 */     boolean[] states = this.allocated;
/*     */     
/* 671 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 673 */       if ((states[i] != 0) && 
/*     */       
/* 675 */         (!predicate.apply(keys[i]))) {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/* 680 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAll(KTypePredicate<? super KType> predicate)
/*     */   {
/* 689 */     KType[] keys = this.keys;
/* 690 */     boolean[] allocated = this.allocated;
/*     */     
/* 692 */     int before = this.assigned;
/* 693 */     for (int i = 0; i < allocated.length;)
/*     */     {
/* 695 */       if (allocated[i] != 0)
/*     */       {
/* 697 */         if (predicate.apply(keys[i]))
/*     */         {
/* 699 */           this.assigned -= 1;
/* 700 */           shiftConflictingKeys(i);
/*     */           
/* 702 */           continue;
/*     */         }
/*     */       }
/* 705 */       i++;
/*     */     }
/*     */     
/* 708 */     return before - this.assigned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> from(KType... elements)
/*     */   {
/* 717 */     KTypeOpenHashSet<KType> set = new KTypeOpenHashSet((int)(elements.length * 1.75F));
/*     */     
/* 719 */     set.add(elements);
/* 720 */     return set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> from(KTypeContainer<KType> container)
/*     */   {
/* 728 */     return new KTypeOpenHashSet(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> newInstance()
/*     */   {
/* 737 */     return new KTypeOpenHashSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> newInstanceWithoutPerturbations()
/*     */   {
/* 747 */     new KTypeOpenHashSet() {
/*     */       protected int computePerturbationValue(int capacity) {
/* 749 */         return 0;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> newInstanceWithCapacity(int initialCapacity, float loadFactor)
/*     */   {
/* 759 */     return new KTypeOpenHashSet(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> newInstanceWithExpectedSize(int expectedSize)
/*     */   {
/* 769 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeOpenHashSet<KType> newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*     */   {
/* 779 */     return newInstanceWithCapacity((int)(expectedSize / loadFactor) + 1, loadFactor);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeOpenHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */