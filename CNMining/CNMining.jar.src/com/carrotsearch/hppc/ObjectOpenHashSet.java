/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.ObjectCursor;
/*     */ import com.carrotsearch.hppc.predicates.ObjectPredicate;
/*     */ import com.carrotsearch.hppc.procedures.ObjectProcedure;
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
/*     */ public class ObjectOpenHashSet<KType>
/*     */   extends AbstractObjectCollection<KType>
/*     */   implements ObjectLookupContainer<KType>, ObjectSet<KType>, Cloneable
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
/*     */   public ObjectOpenHashSet()
/*     */   {
/* 136 */     this(16, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectOpenHashSet(int initialCapacity)
/*     */   {
/* 145 */     this(initialCapacity, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectOpenHashSet(int initialCapacity, float loadFactor)
/*     */   {
/* 153 */     initialCapacity = Math.max(initialCapacity, 4);
/*     */     
/*     */ 
/* 156 */     assert (initialCapacity > 0) : "Initial capacity must be between (0, 2147483647].";
/*     */     
/* 158 */     assert ((loadFactor > 0.0F) && (loadFactor <= 1.0F)) : "Load factor must be between (0, 1].";
/*     */     
/* 160 */     this.loadFactor = loadFactor;
/* 161 */     allocateBuffers(HashContainerUtils.roundCapacity(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectOpenHashSet(ObjectContainer<KType> container)
/*     */   {
/* 169 */     this((int)(container.size() * 1.75F));
/* 170 */     addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(KType e)
/*     */   {
/* 179 */     assert (this.assigned < this.allocated.length);
/*     */     
/* 181 */     int mask = this.allocated.length - 1;
/* 182 */     int slot = Internals.rehash(e, this.perturbation) & mask;
/* 183 */     while (this.allocated[slot] != 0)
/*     */     {
/* 185 */       if (e == null ? this.keys[slot] == null : e.equals(this.keys[slot]))
/*     */       {
/* 187 */         return false;
/*     */       }
/*     */       
/* 190 */       slot = slot + 1 & mask;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 195 */     if (this.assigned == this.resizeAt) {
/* 196 */       expandAndAdd(e, slot);
/*     */     } else {
/* 198 */       this.assigned += 1;
/* 199 */       this.allocated[slot] = true;
/* 200 */       this.keys[slot] = e;
/*     */     }
/* 202 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int add(KType e1, KType e2)
/*     */   {
/* 210 */     int count = 0;
/* 211 */     if (add(e1)) count++;
/* 212 */     if (add(e2)) count++;
/* 213 */     return count;
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
/* 226 */     int count = 0;
/* 227 */     for (KType e : elements)
/* 228 */       if (add(e)) count++;
/* 229 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(ObjectContainer<? extends KType> container)
/*     */   {
/* 240 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(Iterable<? extends ObjectCursor<? extends KType>> iterable)
/*     */   {
/* 251 */     int count = 0;
/* 252 */     for (ObjectCursor<? extends KType> cursor : iterable)
/*     */     {
/* 254 */       if (add(cursor.value)) count++;
/*     */     }
/* 256 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void expandAndAdd(KType pendingKey, int freeSlot)
/*     */   {
/* 265 */     assert (this.assigned == this.resizeAt);
/* 266 */     assert (this.allocated[freeSlot] == 0);
/*     */     
/*     */ 
/*     */ 
/* 270 */     KType[] oldKeys = this.keys;
/* 271 */     boolean[] oldAllocated = this.allocated;
/*     */     
/* 273 */     allocateBuffers(HashContainerUtils.nextCapacity(this.keys.length));
/*     */     
/*     */ 
/*     */ 
/* 277 */     this.lastSlot = -1;
/* 278 */     this.assigned += 1;
/* 279 */     oldAllocated[freeSlot] = true;
/* 280 */     oldKeys[freeSlot] = pendingKey;
/*     */     
/*     */ 
/* 283 */     KType[] keys = this.keys;
/* 284 */     boolean[] allocated = this.allocated;
/* 285 */     int mask = allocated.length - 1;
/* 286 */     int i = oldAllocated.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 288 */       if (oldAllocated[i] != 0)
/*     */       {
/* 290 */         KType k = oldKeys[i];
/*     */         
/* 292 */         int slot = Internals.rehash(k, this.perturbation) & mask;
/* 293 */         while (allocated[slot] != 0)
/*     */         {
/* 295 */           slot = slot + 1 & mask;
/*     */         }
/*     */         
/* 298 */         allocated[slot] = true;
/* 299 */         keys[slot] = k;
/*     */       }
/*     */     }
/*     */     
/* 303 */     Arrays.fill(oldKeys, null);
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
/* 314 */     KType[] keys = (Object[])Internals.newArray(capacity);
/* 315 */     boolean[] allocated = new boolean[capacity];
/*     */     
/* 317 */     this.keys = keys;
/* 318 */     this.allocated = allocated;
/*     */     
/* 320 */     this.resizeAt = (Math.max(2, (int)Math.ceil(capacity * this.loadFactor)) - 1);
/* 321 */     this.perturbation = computePerturbationValue(capacity);
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
/* 339 */     return HashContainerUtils.PERTURBATIONS[Integer.numberOfLeadingZeros(capacity)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(KType key)
/*     */   {
/* 348 */     return remove(key) ? 1 : 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(KType key)
/*     */   {
/* 356 */     int mask = this.allocated.length - 1;
/* 357 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/*     */     
/* 359 */     while (this.allocated[slot] != 0)
/*     */     {
/* 361 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*     */       {
/* 363 */         this.assigned -= 1;
/* 364 */         shiftConflictingKeys(slot);
/* 365 */         return true;
/*     */       }
/* 367 */       slot = slot + 1 & mask;
/*     */     }
/*     */     
/* 370 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void shiftConflictingKeys(int slotCurr)
/*     */   {
/* 379 */     int mask = this.allocated.length - 1;
/*     */     int slotPrev;
/*     */     for (;;)
/*     */     {
/* 383 */       slotCurr = (slotPrev = slotCurr) + 1 & mask;
/*     */       
/* 385 */       while (this.allocated[slotCurr] != 0)
/*     */       {
/* 387 */         int slotOther = Internals.rehash(this.keys[slotCurr], this.perturbation) & mask;
/* 388 */         if (slotPrev <= slotCurr ? 
/*     */         
/*     */ 
/* 391 */           (slotPrev < slotOther) && (slotOther <= slotCurr) : 
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 397 */           (slotPrev >= slotOther) && (slotOther > slotCurr)) {
/*     */           break;
/*     */         }
/* 400 */         slotCurr = slotCurr + 1 & mask;
/*     */       }
/*     */       
/* 403 */       if (this.allocated[slotCurr] == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 407 */       this.keys[slotPrev] = this.keys[slotCurr];
/*     */     }
/*     */     
/* 410 */     this.allocated[slotPrev] = false;
/*     */     
/*     */ 
/* 413 */     this.keys[slotPrev] = null;
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
/* 425 */     assert (this.lastSlot >= 0) : "Call contains() first.";
/* 426 */     assert (this.allocated[this.lastSlot] != 0) : "Last call to exists did not have any associated value.";
/*     */     
/* 428 */     return (KType)this.keys[this.lastSlot];
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
/* 440 */     assert (this.lastSlot >= 0) : "Call contains() first.";
/* 441 */     return this.lastSlot;
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
/*     */   public boolean contains(KType key)
/*     */   {
/* 455 */     int mask = this.allocated.length - 1;
/* 456 */     int slot = Internals.rehash(key, this.perturbation) & mask;
/* 457 */     while (this.allocated[slot] != 0)
/*     */     {
/* 459 */       if (key == null ? this.keys[slot] == null : key.equals(this.keys[slot]))
/*     */       {
/* 461 */         this.lastSlot = slot;
/* 462 */         return true;
/*     */       }
/* 464 */       slot = slot + 1 & mask;
/*     */     }
/* 466 */     this.lastSlot = -1;
/* 467 */     return false;
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
/* 478 */     this.assigned = 0;
/*     */     
/* 480 */     Arrays.fill(this.allocated, false);
/* 481 */     Arrays.fill(this.keys, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 490 */     return this.assigned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 499 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 508 */     int h = 0;
/*     */     
/* 510 */     KType[] keys = this.keys;
/* 511 */     boolean[] states = this.allocated;
/* 512 */     int i = states.length; for (;;) { i--; if (i < 0)
/*     */         break;
/* 514 */       if (states[i] != 0)
/*     */       {
/* 516 */         h += Internals.rehash(keys[i]);
/*     */       }
/*     */     }
/*     */     
/* 520 */     return h;
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
/* 532 */     if (obj != null)
/*     */     {
/* 534 */       if (obj == this) { return true;
/*     */       }
/* 536 */       if ((obj instanceof ObjectSet))
/*     */       {
/* 538 */         ObjectSet<Object> other = (ObjectSet)obj;
/* 539 */         if (other.size() == size())
/*     */         {
/* 541 */           for (ObjectCursor<KType> c : this)
/*     */           {
/* 543 */             if (!other.contains(c.value))
/*     */             {
/* 545 */               return false;
/*     */             }
/*     */           }
/* 548 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 552 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   private final class EntryIterator
/*     */     extends AbstractIterator<ObjectCursor<KType>>
/*     */   {
/*     */     private final ObjectCursor<KType> cursor;
/*     */     
/*     */ 
/*     */     public EntryIterator()
/*     */     {
/* 564 */       this.cursor = new ObjectCursor();
/* 565 */       this.cursor.index = -1;
/*     */     }
/*     */     
/*     */ 
/*     */     protected ObjectCursor<KType> fetch()
/*     */     {
/* 571 */       int max = ObjectOpenHashSet.this.keys.length;
/*     */       
/* 573 */       int i = this.cursor.index + 1;
/* 574 */       while ((i < ObjectOpenHashSet.this.keys.length) && (ObjectOpenHashSet.this.allocated[i] == 0))
/*     */       {
/* 576 */         i++;
/*     */       }
/*     */       
/* 579 */       if (i == max) {
/* 580 */         return (ObjectCursor)done();
/*     */       }
/* 582 */       this.cursor.index = i;
/* 583 */       this.cursor.value = ObjectOpenHashSet.this.keys[i];
/* 584 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<ObjectCursor<KType>> iterator()
/*     */   {
/* 594 */     return new EntryIterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectProcedure<? super KType>> T forEach(T procedure)
/*     */   {
/* 603 */     KType[] keys = this.keys;
/* 604 */     boolean[] states = this.allocated;
/*     */     
/* 606 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 608 */       if (states[i] != 0) {
/* 609 */         procedure.apply(keys[i]);
/*     */       }
/*     */     }
/* 612 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[] toArray()
/*     */   {
/* 623 */     KType[] cloned = (Object[])Internals.newArray(this.assigned);
/* 624 */     int i = 0; for (int j = 0; i < this.keys.length; i++)
/* 625 */       if (this.allocated[i] != 0)
/* 626 */         cloned[(j++)] = this.keys[i];
/* 627 */     return cloned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectOpenHashSet<KType> clone()
/*     */   {
/*     */     try
/*     */     {
/* 641 */       ObjectOpenHashSet<KType> cloned = (ObjectOpenHashSet)super.clone();
/* 642 */       cloned.keys = ((Object[])this.keys.clone());
/* 643 */       cloned.allocated = ((boolean[])this.allocated.clone());
/* 644 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 648 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectPredicate<? super KType>> T forEach(T predicate)
/*     */   {
/* 658 */     KType[] keys = this.keys;
/* 659 */     boolean[] states = this.allocated;
/*     */     
/* 661 */     for (int i = 0; i < states.length; i++)
/*     */     {
/* 663 */       if ((states[i] != 0) && 
/*     */       
/* 665 */         (!predicate.apply(keys[i]))) {
/*     */         break;
/*     */       }
/*     */     }
/*     */     
/* 670 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAll(ObjectPredicate<? super KType> predicate)
/*     */   {
/* 679 */     KType[] keys = this.keys;
/* 680 */     boolean[] allocated = this.allocated;
/*     */     
/* 682 */     int before = this.assigned;
/* 683 */     for (int i = 0; i < allocated.length;)
/*     */     {
/* 685 */       if (allocated[i] != 0)
/*     */       {
/* 687 */         if (predicate.apply(keys[i]))
/*     */         {
/* 689 */           this.assigned -= 1;
/* 690 */           shiftConflictingKeys(i);
/*     */           
/* 692 */           continue;
/*     */         }
/*     */       }
/* 695 */       i++;
/*     */     }
/*     */     
/* 698 */     return before - this.assigned;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> from(KType... elements)
/*     */   {
/* 707 */     ObjectOpenHashSet<KType> set = new ObjectOpenHashSet((int)(elements.length * 1.75F));
/*     */     
/* 709 */     set.add(elements);
/* 710 */     return set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> from(ObjectContainer<KType> container)
/*     */   {
/* 718 */     return new ObjectOpenHashSet(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> newInstance()
/*     */   {
/* 727 */     return new ObjectOpenHashSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> newInstanceWithoutPerturbations()
/*     */   {
/* 737 */     new ObjectOpenHashSet() {
/*     */       protected int computePerturbationValue(int capacity) {
/* 739 */         return 0;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> newInstanceWithCapacity(int initialCapacity, float loadFactor)
/*     */   {
/* 749 */     return new ObjectOpenHashSet(initialCapacity, loadFactor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> newInstanceWithExpectedSize(int expectedSize)
/*     */   {
/* 759 */     return newInstanceWithExpectedSize(expectedSize, 0.75F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectOpenHashSet<KType> newInstanceWithExpectedSize(int expectedSize, float loadFactor)
/*     */   {
/* 769 */     return newInstanceWithCapacity((int)(expectedSize / loadFactor) + 1, loadFactor);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectOpenHashSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */