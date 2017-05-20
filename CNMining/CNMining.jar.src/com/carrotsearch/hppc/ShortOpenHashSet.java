/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.ShortCursor;
/*     */ import com.carrotsearch.hppc.predicates.ShortPredicate;
/*     */ import com.carrotsearch.hppc.procedures.ShortProcedure;
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
/*     */ public class ShortOpenHashSet
/*     */   extends AbstractShortCollection
/*     */   implements ShortLookupContainer, ShortSet, Cloneable
/*     */ {
/*     */   public static final int MIN_CAPACITY = 4;
/*     */   public static final int DEFAULT_CAPACITY = 16;
/*     */   public static final float DEFAULT_LOAD_FACTOR = 0.75F;
/*     */   public short[] keys;
/*     */   public boolean[] allocated;
/*     */   public int assigned;
/*     */   public final float loadFactor;
/*     */   protected int resizeAt;
/*     */   protected int lastSlot;
/*     */   protected int perturbation;
/*     */   
/*     */   public ShortOpenHashSet()
/*     */   {
/* 107 */     this(16, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShortOpenHashSet(int initialCapacity)
/*     */   {
/* 116 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShortOpenHashSet(int initialCapacity, float loadFactor)
/*     */   {
/* 124 */     initialCapacity = Math.max(initialCapacity, 4);
/*     */     
/*     */ 
/* 127 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*     */     
/* 129 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*     */     
/* 131 */     this.loadFactor = loadFactor;
/* 132 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShortOpenHashSet(ShortContainer container)
/*     */   {
/* 140 */     this((int)(container.size() * 1.75F));
/* 141 */     addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(short e)
/*     */   {
/* 150 */     assert (this.assigned < this.allocated.length);
/*     */     
/* 152 */     int mask = this.allocated.length - 1;
/* 153 */     int slot = Internals.rehash(e, this.perturbation) & mask;
/* 154 */     while (this.allocated[slot] != 0)
/*     */     {
/* 156 */       if (e == this.keys[slot])
/*     */       {
/* 158 */         return false;
/*     */       }
/*     */       
/* 161 */       slot = slot + 1 & mask;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 166 */     if (this.assigned == this.resizeAt) {
/* 167 */       expandAndAdd(e, slot);
/*     */     } else {
/* 169 */       this.assigned += 1;
/* 170 */       this.allocated[slot] = true;
/* 171 */       this.keys[slot] = e;
/*     */     }
/* 173 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int add(short e1, short e2)
/*     */   {
/* 181 */     int count = 0;
/* 182 */     if (add(e1)) count++;
/* 183 */     if (add(e2)) count++;
/* 184 */     return count;
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
/*     */   public int add(short... elements)
/*     */   {
/* 197 */     int count = 0;
/* 198 */     for (short e : elements)
/* 199 */       if (add(e)) count++;
/* 200 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(ShortContainer container)
/*     */   {
/* 211 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(Iterable<? extends ShortCursor> iterable)
/*     */   {
/* 222 */     int count = 0;
/* 223 */     for (ShortCursor cursor : iterable)
/*     */     {
/* 225 */       if (add(cursor.value)) count++;
/*     */     }
/* 227 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void expandAndAdd(short pendingKey, int freeSlot)
/*     */   {
/* 236 */     assert (this.assigned == this.resizeAt);
/* 237 */     assert (this.allocated[freeSlot] == 0);
/*     */     
/*     */ 
/*     */ 
/* 241 */     short[] oldKeys = this.keys;
/* 242 */     boolean[] oldAllocated = this.allocated;
/*     */     
/* 244 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*     */     
/*     */ 
/*     */ 
/* 248 */     this.lastSlot = -1;
/* 249 */     this.assigned += 1;
/* 250 */     oldAllocated[freeSlot] = true;
/* 251 */     oldKeys[freeSlot] = pendingKey;
/*     */     
/*     */ 
/* 254 */     short[] keys = this.keys;
/* 255 */     boolean[] allocated = this.allocated;
/* 256 */     int mask = allocated.length - 1;
/* 257 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 259 */       if (oldAllocated[i] != 0)
/*     */       {
/* 261 */         short k = oldKeys[i];
/*     */         
/* 263 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/* 264 */         while (allocated[slot] != 0)
/*     */         {
/* 266 */           slot = slot + 1 & mask;
/*     */         }
/*     */         
/* 269 */         allocated[slot] = true;
/* 270 */         keys[slot] = k;
/*     */       }
/*     */     }
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
/*     */   private void allocateBuffers(int capacity)
/*     */   {
/* 285 */     short[] keys = new short[capacity];
/* 286 */     boolean[] allocated = new boolean[capacity];
/*     */     
/* 288 */     this.keys = keys;
/* 289 */     this.allocated = allocated;
/*     */     
/* 291 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/* 292 */     this.perturbation = computePerturbationValue(capacity);
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
/* 310 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(short key)
/*     */   {
/* 319 */     return remove(key) ? 1 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(short key)
/*     */   {
/* 327 */     int mask = this.allocated.length - 1;
/* 328 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*     */     
/* 330 */     while (this.allocated[slot] != 0)
/*     */     {
/* 332 */       if (key == this.keys[slot])
/*     */       {
/* 334 */         this.assigned -= 1;
/* 335 */         shiftConflictingKeys(slot);
/* 336 */         return true;
/*     */       }
/* 338 */       slot = slot + 1 & mask;
/*     */     }
/*     */     
/* 341 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void shiftConflictingKeys(int slotCurr)
/*     */   {
/* 350 */     int mask = this.allocated.length - 1;
/*     */     int slotPrev;
/*     */     for (;;)
/*     */     {
/* 354 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*     */       
/* 356 */       while (this.allocated[slotCurr] != 0)
/*     */       {
/* 358 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/* 359 */         if (slotPrev <= slotCurr ? 
/*     */         
/*     */ 
/* 362 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 368 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*     */           break;
/*     */         }
/* 371 */         slotCurr = slotCurr + 1 & mask;
/*     */       }
/*     */       
/* 374 */       if (this.allocated[slotCurr] == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 378 */       this.keys[slotPrev] = this.keys[slotCurr];
/*     */     }
/*     */     
/* 381 */     this.allocated[slotPrev] = false;
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
/*     */   public int lslot()
/*     */   {
/* 396 */     assert (this.lastSlot >= 0) : "Call contains() first.";
/* 397 */     return this.lastSlot;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(short key)
/*     */   {
/* 407 */     int mask = this.allocated.length - 1;
/* 408 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/* 409 */     while (this.allocated[slot] != 0)
/*     */     {
/* 411 */       if (key == this.keys[slot])
/*     */       {
/* 413 */         this.lastSlot = slot;
/* 414 */         return true;
/*     */       }
/* 416 */       slot = slot + 1 & mask;
/*     */     }
/* 418 */     this.lastSlot = -1;
/* 419 */     return false;
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
/* 430 */     this.assigned = 0;
/*     */     
/* 432 */     Arrays.fill(this.allocated, false);
/* 433 */     Arrays.fill(this.keys, (short)0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 442 */     return this.assigned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 451 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 460 */     int h = 0;
/*     */     
/* 462 */     short[] keys = this.keys;
/* 463 */     boolean[] states = this.allocated;
/* 464 */     int i = states.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 466 */       if (states[i] != 0)
/*     */       {
/* 468 */         h += Internals.rehash(keys[i]);
/*     */       }
/*     */     }
/*     */     
/* 472 */     return h;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 482 */     if (obj != null)
/*     */     {
/* 484 */       if (obj == this) { return true;
/*     */       }
/* 486 */       if ((obj instanceof ShortSet))
/*     */       {
/* 488 */         ShortSet other = (ShortSet)obj;
/* 489 */         if (other.size() == size())
/*     */         {
/* 491 */           for (ShortCursor c : this)
/*     */           {
/* 493 */             if (!other.contains(c.value))
/*     */             {
/* 495 */               return false;
/*     */             }
/*     */           }
/* 498 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 502 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   private final class EntryIterator
/*     */     extends AbstractIterator<ShortCursor>
/*     */   {
/*     */     private final ShortCursor cursor;
/*     */     
/*     */ 
/*     */     public EntryIterator()
/*     */     {
/* 514 */       this.cursor = new ShortCursor();
/* 515 */       this.cursor.index = -1;
/*     */     }
/*     */     
/*     */ 
/*     */     protected ShortCursor fetch()
/*     */     {
/* 521 */       int max = ShortOpenHashSet.this.keys.length;
/*     */       
/* 523 */       int i = this.cursor.index + 1;
/* 524 */       while ((i < ShortOpenHashSet.this.keys.length) && (ShortOpenHashSet.this.allocated[i] == 0))
/*     */       {
/* 526 */         i++;
/*     */       }
/*     */       
/* 529 */       if (i == max) {
/* 530 */         return (ShortCursor)done();
/*     */       }
/* 532 */       this.cursor.index = i;
/* 533 */       this.cursor.value = ShortOpenHashSet.this.keys[i];
/* 534 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<ShortCursor> iterator()
/*     */   {
/* 544 */     return new EntryIterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ShortProcedure> T forEach(T procedure)
/*     */   {
/* 553 */     short[] keys = this.keys;
/* 554 */     boolean[] states = this.allocated;
/*     */     
/* 556 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 558 */       if (states[i] != 0) {
/* 559 */         procedure.apply(keys[i]);
/*     */       }
/*     */     }
/* 562 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short[] toArray()
/*     */   {
/* 572 */     short[] cloned = new short[this.assigned];
/* 573 */     int i = 0; for (int j = 0; i < this.keys.length; i++)
/* 574 */       if (this.allocated[i] != 0)
/* 575 */         cloned[(j++)] = this.keys[i];
/* 576 */     return cloned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShortOpenHashSet clone()
/*     */   {
/*     */     try
/*     */     {
/* 588 */       ShortOpenHashSet cloned = (ShortOpenHashSet)super.clone();
/* 589 */       cloned.keys = ((short[])this.keys.clone());
/* 590 */       cloned.allocated = ((boolean[])this.allocated.clone());
/* 591 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 595 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ShortPredicate> T forEach(T predicate)
/*     */   {
/* 605 */     short[] keys = this.keys;
/* 606 */     boolean[] states = this.allocated;
/*     */     
/* 608 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 610 */       if ((states[i] != 0) && 
/*     */       
/* 612 */         (!predicate.apply(keys[i]))) {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/* 617 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAll(ShortPredicate predicate)
/*     */   {
/* 626 */     short[] keys = this.keys;
/* 627 */     boolean[] allocated = this.allocated;
/*     */     
/* 629 */     int before = this.assigned;
/* 630 */     for (int i = 0; i < allocated.length;)
/*     */     {
/* 632 */       if (allocated[i] != 0)
/*     */       {
/* 634 */         if (predicate.apply(keys[i]))
/*     */         {
/* 636 */           this.assigned -= 1;
/* 637 */           shiftConflictingKeys(i);
/*     */           
/* 639 */           continue;
/*     */         }
/*     */       }
/* 642 */       i++;
/*     */     }
/*     */     
/* 645 */     return before - this.assigned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet from(short... elements)
/*     */   {
/* 654 */     ShortOpenHashSet set = new ShortOpenHashSet((int)(elements.length * 1.75F));
/*     */     
/* 656 */     set.add(elements);
/* 657 */     return set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet from(ShortContainer container)
/*     */   {
/* 665 */     return new ShortOpenHashSet(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet newInstance()
/*     */   {
/* 674 */     return new ShortOpenHashSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet newInstanceWithoutPerturbations()
/*     */   {
/* 684 */     new ShortOpenHashSet() {
/*     */       protected int computePerturbationValue(int capacity) {
/* 686 */         return 0;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet newInstanceWithCapacity(int initialCapacity, float loadFactor)
/*     */   {
/* 696 */     return new ShortOpenHashSet(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet newInstanceWithExpectedSize(int expectedSize)
/*     */   {
/* 706 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ShortOpenHashSet newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*     */   {
/* 716 */     return newInstanceWithCapacity((int)(expectedSize / loadFactor) + 1, loadFactor);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortOpenHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */