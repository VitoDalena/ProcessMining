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
/*     */ public class ObjectArrayDeque<KType>
/*     */   extends AbstractObjectCollection<KType>
/*     */   implements ObjectDeque<KType>, Cloneable
/*     */ {
/*     */   public static final int DEFAULT_CAPACITY = 5;
/*     */   public KType[] buffer;
/*     */   public int head;
/*     */   public int tail;
/*     */   protected final ArraySizingStrategy resizer;
/*     */   
/*     */   public ObjectArrayDeque()
/*     */   {
/*  91 */     this(5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayDeque(int initialCapacity)
/*     */   {
/* 101 */     this(initialCapacity, new BoundedProportionalArraySizingStrategy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayDeque(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/* 109 */     assert (initialCapacity >= 0) : ("initialCapacity must be >= 0: " + initialCapacity);
/* 110 */     assert (resizer != null);
/*     */     
/* 112 */     this.resizer = resizer;
/* 113 */     initialCapacity = resizer.round(initialCapacity);
/* 114 */     this.buffer = ((Object[])Internals.newArray(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayDeque(ObjectContainer<? extends KType> container)
/*     */   {
/* 123 */     this(container.size());
/* 124 */     addLast(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addFirst(KType e1)
/*     */   {
/* 133 */     int h = oneLeft(this.head, this.buffer.length);
/* 134 */     if (h == this.tail)
/*     */     {
/* 136 */       ensureBufferSpace(1);
/* 137 */       h = oneLeft(this.head, this.buffer.length);
/*     */     }
/* 139 */     this.buffer[(this.head = h)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addFirst(KType... elements)
/*     */   {
/* 150 */     ensureBufferSpace(elements.length);
/*     */     
/*     */ 
/* 153 */     for (int i = 0; i < elements.length; i++) {
/* 154 */       addFirst(elements[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addFirst(ObjectContainer<? extends KType> container)
/*     */   {
/* 165 */     int size = container.size();
/* 166 */     ensureBufferSpace(size);
/*     */     
/* 168 */     for (ObjectCursor<? extends KType> cursor : container)
/*     */     {
/* 170 */       addFirst(cursor.value);
/*     */     }
/*     */     
/* 173 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addFirst(Iterable<? extends ObjectCursor<? extends KType>> iterable)
/*     */   {
/* 183 */     int size = 0;
/* 184 */     for (ObjectCursor<? extends KType> cursor : iterable)
/*     */     {
/* 186 */       addFirst(cursor.value);
/* 187 */       size++;
/*     */     }
/* 189 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addLast(KType e1)
/*     */   {
/* 198 */     int t = oneRight(this.tail, this.buffer.length);
/* 199 */     if (this.head == t)
/*     */     {
/* 201 */       ensureBufferSpace(1);
/* 202 */       t = oneRight(this.tail, this.buffer.length);
/*     */     }
/* 204 */     this.buffer[this.tail] = e1;
/* 205 */     this.tail = t;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addLast(KType... elements)
/*     */   {
/* 216 */     ensureBufferSpace(1);
/*     */     
/*     */ 
/* 219 */     for (int i = 0; i < elements.length; i++) {
/* 220 */       addLast(elements[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addLast(ObjectContainer<? extends KType> container)
/*     */   {
/* 231 */     int size = container.size();
/* 232 */     ensureBufferSpace(size);
/*     */     
/* 234 */     for (ObjectCursor<? extends KType> cursor : container)
/*     */     {
/* 236 */       addLast(cursor.value);
/*     */     }
/*     */     
/* 239 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addLast(Iterable<? extends ObjectCursor<? extends KType>> iterable)
/*     */   {
/* 249 */     int size = 0;
/* 250 */     for (ObjectCursor<? extends KType> cursor : iterable)
/*     */     {
/* 252 */       addLast(cursor.value);
/* 253 */       size++;
/*     */     }
/* 255 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType removeFirst()
/*     */   {
/* 264 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 266 */     KType result = this.buffer[this.head];
/* 267 */     this.buffer[this.head] = null;
/* 268 */     this.head = oneRight(this.head, this.buffer.length);
/* 269 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType removeLast()
/*     */   {
/* 278 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 280 */     this.tail = oneLeft(this.tail, this.buffer.length);
/* 281 */     KType result = this.buffer[this.tail];
/* 282 */     this.buffer[this.tail] = null;
/* 283 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType getFirst()
/*     */   {
/* 292 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 294 */     return (KType)this.buffer[this.head];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType getLast()
/*     */   {
/* 303 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 305 */     return (KType)this.buffer[oneLeft(this.tail, this.buffer.length)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeFirstOccurrence(KType e1)
/*     */   {
/* 314 */     int index = bufferIndexOf(e1);
/* 315 */     if (index >= 0) removeAtBufferIndex(index);
/* 316 */     return index;
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
/*     */   public int bufferIndexOf(KType e1)
/*     */   {
/* 329 */     int last = this.tail;
/* 330 */     int bufLen = this.buffer.length;
/* 331 */     for (int i = this.head; i != last; i = oneRight(i, bufLen))
/*     */     {
/* 333 */       if (e1 == null ? this.buffer[i] == null : e1.equals(this.buffer[i])) {
/* 334 */         return i;
/*     */       }
/*     */     }
/* 337 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeLastOccurrence(KType e1)
/*     */   {
/* 346 */     int index = lastBufferIndexOf(e1);
/* 347 */     if (index >= 0) removeAtBufferIndex(index);
/* 348 */     return index;
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
/*     */   public int lastBufferIndexOf(KType e1)
/*     */   {
/* 361 */     int bufLen = this.buffer.length;
/* 362 */     int last = oneLeft(this.head, bufLen);
/* 363 */     for (int i = oneLeft(this.tail, bufLen); i != last; i = oneLeft(i, bufLen))
/*     */     {
/* 365 */       if (e1 == null ? this.buffer[i] == null : e1.equals(this.buffer[i])) {
/* 366 */         return i;
/*     */       }
/*     */     }
/* 369 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(KType e1)
/*     */   {
/* 378 */     int removed = 0;
/* 379 */     int last = this.tail;
/* 380 */     int bufLen = this.buffer.length;
/*     */     int to;
/* 382 */     for (int from = to = this.head; from != last; from = oneRight(from, bufLen))
/*     */     {
/* 384 */       if (e1 == null ? this.buffer[from] == null : e1.equals(this.buffer[from]))
/*     */       {
/* 386 */         this.buffer[from] = null;
/* 387 */         removed++;
/*     */       }
/*     */       else
/*     */       {
/* 391 */         if (to != from)
/*     */         {
/* 393 */           this.buffer[to] = this.buffer[from];
/* 394 */           this.buffer[from] = null;
/*     */         }
/*     */         
/* 397 */         to = oneRight(to, bufLen);
/*     */       }
/*     */     }
/* 400 */     this.tail = to;
/* 401 */     return removed;
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
/*     */   public void removeAtBufferIndex(int index)
/*     */   {
/* 415 */     assert (this.head <= this.tail ? (index >= this.head) || (index < this.tail) : (index >= this.head) || (index < this.tail)) : ("Index out of range (head=" + this.head + ", tail=" + this.tail + ", index=" + index + ").");
/*     */     
/*     */ 
/*     */ 
/* 419 */     KType[] b = this.buffer;
/* 420 */     int bufLen = b.length;
/* 421 */     int lastIndex = bufLen - 1;
/* 422 */     int head = this.head;
/* 423 */     int tail = this.tail;
/*     */     
/* 425 */     int leftChunk = Math.abs(index - head) % bufLen;
/* 426 */     int rightChunk = Math.abs(tail - index) % bufLen;
/*     */     
/* 428 */     if (leftChunk < rightChunk)
/*     */     {
/* 430 */       if (index >= head)
/*     */       {
/* 432 */         System.arraycopy(b, head, b, head + 1, leftChunk);
/*     */       }
/*     */       else
/*     */       {
/* 436 */         System.arraycopy(b, 0, b, 1, index);
/* 437 */         b[0] = b[lastIndex];
/* 438 */         System.arraycopy(b, head, b, head + 1, lastIndex - head);
/*     */       }
/* 440 */       b[head] = null;
/* 441 */       this.head = oneRight(head, bufLen);
/*     */     }
/*     */     else
/*     */     {
/* 445 */       if (index < tail)
/*     */       {
/* 447 */         System.arraycopy(b, index + 1, b, index, rightChunk);
/*     */       }
/*     */       else
/*     */       {
/* 451 */         System.arraycopy(b, index + 1, b, index, lastIndex - index);
/* 452 */         b[lastIndex] = b[0];
/* 453 */         System.arraycopy(b, 1, b, 0, tail);
/*     */       }
/* 455 */       b[tail] = null;
/* 456 */       this.tail = oneLeft(tail, bufLen);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 466 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 475 */     if (this.head <= this.tail) {
/* 476 */       return this.tail - this.head;
/*     */     }
/* 478 */     return this.tail - this.head + this.buffer.length;
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
/*     */   public void clear()
/*     */   {
/* 491 */     if (this.head < this.tail)
/*     */     {
/* 493 */       Arrays.fill(this.buffer, this.head, this.tail, null);
/*     */     }
/*     */     else
/*     */     {
/* 497 */       Arrays.fill(this.buffer, 0, this.tail, null);
/* 498 */       Arrays.fill(this.buffer, this.head, this.buffer.length, null);
/*     */     }
/* 500 */     this.head = (this.tail = 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void release()
/*     */   {
/* 508 */     this.head = (this.tail = 0);
/* 509 */     this.buffer = ((Object[])Internals.newArray(this.resizer.round(5)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void ensureBufferSpace(int expectedAdditions)
/*     */   {
/* 518 */     int bufferLen = this.buffer == null ? 0 : this.buffer.length;
/* 519 */     int elementsCount = size();
/*     */     
/* 521 */     if (elementsCount >= bufferLen - expectedAdditions - 1)
/*     */     {
/* 523 */       int newSize = this.resizer.grow(bufferLen, elementsCount, expectedAdditions + 1);
/* 524 */       assert (newSize >= elementsCount + expectedAdditions + 1) : ("Resizer failed to return sensible new size: " + newSize + " <= " + (elementsCount + expectedAdditions));
/*     */       
/*     */ 
/*     */ 
/* 528 */       KType[] newBuffer = (Object[])Internals.newArray(newSize);
/* 529 */       if (bufferLen > 0)
/*     */       {
/* 531 */         toArray(newBuffer);
/* 532 */         this.tail = elementsCount;
/* 533 */         this.head = 0;
/*     */       }
/* 535 */       this.buffer = newBuffer;
/*     */     }
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
/* 547 */     int size = size();
/* 548 */     return toArray((Object[])Internals.newArray(size));
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
/*     */   public KType[] toArray(KType[] target)
/*     */   {
/* 561 */     assert (target.length >= size()) : ("Target array must be >= " + size());
/*     */     
/* 563 */     if (this.head < this.tail)
/*     */     {
/*     */ 
/* 566 */       System.arraycopy(this.buffer, this.head, target, 0, size());
/*     */     }
/* 568 */     else if (this.head > this.tail)
/*     */     {
/*     */ 
/*     */ 
/* 572 */       int rightCount = this.buffer.length - this.head;
/* 573 */       System.arraycopy(this.buffer, this.head, target, 0, rightCount);
/* 574 */       System.arraycopy(this.buffer, 0, target, rightCount, this.tail);
/*     */     }
/*     */     
/* 577 */     return target;
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
/*     */   public ObjectArrayDeque<KType> clone()
/*     */   {
/*     */     try
/*     */     {
/* 592 */       ObjectArrayDeque<KType> cloned = (ObjectArrayDeque)super.clone();
/* 593 */       cloned.buffer = ((Object[])this.buffer.clone());
/* 594 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 598 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static int oneLeft(int index, int modulus)
/*     */   {
/* 607 */     if (index >= 1) return index - 1;
/* 608 */     return modulus - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static int oneRight(int index, int modulus)
/*     */   {
/* 616 */     if (index + 1 == modulus) return 0;
/* 617 */     return index + 1;
/*     */   }
/*     */   
/*     */ 
/*     */   private final class ValueIterator
/*     */     extends AbstractIterator<ObjectCursor<KType>>
/*     */   {
/*     */     private final ObjectCursor<KType> cursor;
/*     */     
/*     */     private int remaining;
/*     */     
/*     */     public ValueIterator()
/*     */     {
/* 630 */       this.cursor = new ObjectCursor();
/* 631 */       this.cursor.index = ObjectArrayDeque.oneLeft(ObjectArrayDeque.this.head, ObjectArrayDeque.this.buffer.length);
/* 632 */       this.remaining = ObjectArrayDeque.this.size();
/*     */     }
/*     */     
/*     */ 
/*     */     protected ObjectCursor<KType> fetch()
/*     */     {
/* 638 */       if (this.remaining == 0) {
/* 639 */         return (ObjectCursor)done();
/*     */       }
/* 641 */       this.remaining -= 1;
/* 642 */       this.cursor.value = ObjectArrayDeque.this.buffer[(this.cursor.index = ObjectArrayDeque.oneRight(this.cursor.index, ObjectArrayDeque.this.buffer.length))];
/* 643 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private final class DescendingValueIterator
/*     */     extends AbstractIterator<ObjectCursor<KType>>
/*     */   {
/*     */     private final ObjectCursor<KType> cursor;
/*     */     
/*     */     private int remaining;
/*     */     
/*     */     public DescendingValueIterator()
/*     */     {
/* 657 */       this.cursor = new ObjectCursor();
/* 658 */       this.cursor.index = ObjectArrayDeque.this.tail;
/* 659 */       this.remaining = ObjectArrayDeque.this.size();
/*     */     }
/*     */     
/*     */ 
/*     */     protected ObjectCursor<KType> fetch()
/*     */     {
/* 665 */       if (this.remaining == 0) {
/* 666 */         return (ObjectCursor)done();
/*     */       }
/* 668 */       this.remaining -= 1;
/* 669 */       this.cursor.value = ObjectArrayDeque.this.buffer[(this.cursor.index = ObjectArrayDeque.oneLeft(this.cursor.index, ObjectArrayDeque.this.buffer.length))];
/* 670 */       return this.cursor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<ObjectCursor<KType>> iterator()
/*     */   {
/* 691 */     return new ValueIterator();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<ObjectCursor<KType>> descendingIterator()
/*     */   {
/* 712 */     return new DescendingValueIterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectProcedure<? super KType>> T forEach(T procedure)
/*     */   {
/* 721 */     forEach(procedure, this.head, this.tail);
/* 722 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void forEach(ObjectProcedure<? super KType> procedure, int fromIndex, int toIndex)
/*     */   {
/* 732 */     KType[] buffer = this.buffer;
/* 733 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 735 */       procedure.apply(buffer[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectPredicate<? super KType>> T forEach(T predicate)
/*     */   {
/* 745 */     int fromIndex = this.head;
/* 746 */     int toIndex = this.tail;
/*     */     
/* 748 */     KType[] buffer = this.buffer;
/* 749 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 751 */       if (!predicate.apply(buffer[i])) {
/*     */         break;
/*     */       }
/*     */     }
/* 755 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectProcedure<? super KType>> T descendingForEach(T procedure)
/*     */   {
/* 764 */     descendingForEach(procedure, this.head, this.tail);
/* 765 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void descendingForEach(ObjectProcedure<? super KType> procedure, int fromIndex, int toIndex)
/*     */   {
/* 775 */     if (fromIndex == toIndex) {
/* 776 */       return;
/*     */     }
/* 778 */     KType[] buffer = this.buffer;
/* 779 */     int i = toIndex;
/*     */     do
/*     */     {
/* 782 */       i = oneLeft(i, buffer.length);
/* 783 */       procedure.apply(buffer[i]);
/* 784 */     } while (i != fromIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectPredicate<? super KType>> T descendingForEach(T predicate)
/*     */   {
/* 793 */     descendingForEach(predicate, this.head, this.tail);
/* 794 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void descendingForEach(ObjectPredicate<? super KType> predicate, int fromIndex, int toIndex)
/*     */   {
/* 805 */     if (fromIndex == toIndex) {
/* 806 */       return;
/*     */     }
/* 808 */     KType[] buffer = this.buffer;
/* 809 */     int i = toIndex;
/*     */     do
/*     */     {
/* 812 */       i = oneLeft(i, buffer.length);
/* 813 */     } while ((predicate.apply(buffer[i])) && 
/*     */     
/* 815 */       (i != fromIndex));
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int removeAll(ObjectPredicate<? super KType> predicate)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_2
/*     */     //   2: aload_0
/*     */     //   3: getfield 25	com/carrotsearch/hppc/ObjectArrayDeque:tail	I
/*     */     //   6: istore_3
/*     */     //   7: aload_0
/*     */     //   8: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   11: arraylength
/*     */     //   12: istore 4
/*     */     //   14: aload_0
/*     */     //   15: getfield 23	com/carrotsearch/hppc/ObjectArrayDeque:head	I
/*     */     //   18: dup
/*     */     //   19: istore 6
/*     */     //   21: istore 5
/*     */     //   23: aload_0
/*     */     //   24: getfield 23	com/carrotsearch/hppc/ObjectArrayDeque:head	I
/*     */     //   27: dup
/*     */     //   28: istore 6
/*     */     //   30: istore 5
/*     */     //   32: iload 5
/*     */     //   34: iload_3
/*     */     //   35: if_icmpeq +83 -> 118
/*     */     //   38: aload_1
/*     */     //   39: aload_0
/*     */     //   40: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   43: iload 5
/*     */     //   45: aaload
/*     */     //   46: invokeinterface 66 2 0
/*     */     //   51: ifeq +17 -> 68
/*     */     //   54: aload_0
/*     */     //   55: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   58: iload 5
/*     */     //   60: aconst_null
/*     */     //   61: aastore
/*     */     //   62: iinc 2 1
/*     */     //   65: goto +41 -> 106
/*     */     //   68: iload 6
/*     */     //   70: iload 5
/*     */     //   72: if_icmpeq +25 -> 97
/*     */     //   75: aload_0
/*     */     //   76: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   79: iload 6
/*     */     //   81: aload_0
/*     */     //   82: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   85: iload 5
/*     */     //   87: aaload
/*     */     //   88: aastore
/*     */     //   89: aload_0
/*     */     //   90: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   93: iload 5
/*     */     //   95: aconst_null
/*     */     //   96: aastore
/*     */     //   97: iload 6
/*     */     //   99: iload 4
/*     */     //   101: invokestatic 34	com/carrotsearch/hppc/ObjectArrayDeque:oneRight	(II)I
/*     */     //   104: istore 6
/*     */     //   106: iload 5
/*     */     //   108: iload 4
/*     */     //   110: invokestatic 34	com/carrotsearch/hppc/ObjectArrayDeque:oneRight	(II)I
/*     */     //   113: istore 5
/*     */     //   115: goto -83 -> 32
/*     */     //   118: iload 5
/*     */     //   120: iload_3
/*     */     //   121: if_icmpeq +53 -> 174
/*     */     //   124: iload 6
/*     */     //   126: iload 5
/*     */     //   128: if_icmpeq +25 -> 153
/*     */     //   131: aload_0
/*     */     //   132: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   135: iload 6
/*     */     //   137: aload_0
/*     */     //   138: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   141: iload 5
/*     */     //   143: aaload
/*     */     //   144: aastore
/*     */     //   145: aload_0
/*     */     //   146: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   149: iload 5
/*     */     //   151: aconst_null
/*     */     //   152: aastore
/*     */     //   153: iload 6
/*     */     //   155: iload 4
/*     */     //   157: invokestatic 34	com/carrotsearch/hppc/ObjectArrayDeque:oneRight	(II)I
/*     */     //   160: istore 6
/*     */     //   162: iload 5
/*     */     //   164: iload 4
/*     */     //   166: invokestatic 34	com/carrotsearch/hppc/ObjectArrayDeque:oneRight	(II)I
/*     */     //   169: istore 5
/*     */     //   171: goto -53 -> 118
/*     */     //   174: aload_0
/*     */     //   175: iload 6
/*     */     //   177: putfield 25	com/carrotsearch/hppc/ObjectArrayDeque:tail	I
/*     */     //   180: goto +70 -> 250
/*     */     //   183: astore 7
/*     */     //   185: iload 5
/*     */     //   187: iload_3
/*     */     //   188: if_icmpeq +53 -> 241
/*     */     //   191: iload 6
/*     */     //   193: iload 5
/*     */     //   195: if_icmpeq +25 -> 220
/*     */     //   198: aload_0
/*     */     //   199: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   202: iload 6
/*     */     //   204: aload_0
/*     */     //   205: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   208: iload 5
/*     */     //   210: aaload
/*     */     //   211: aastore
/*     */     //   212: aload_0
/*     */     //   213: getfield 20	com/carrotsearch/hppc/ObjectArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   216: iload 5
/*     */     //   218: aconst_null
/*     */     //   219: aastore
/*     */     //   220: iload 6
/*     */     //   222: iload 4
/*     */     //   224: invokestatic 34	com/carrotsearch/hppc/ObjectArrayDeque:oneRight	(II)I
/*     */     //   227: istore 6
/*     */     //   229: iload 5
/*     */     //   231: iload 4
/*     */     //   233: invokestatic 34	com/carrotsearch/hppc/ObjectArrayDeque:oneRight	(II)I
/*     */     //   236: istore 5
/*     */     //   238: goto -53 -> 185
/*     */     //   241: aload_0
/*     */     //   242: iload 6
/*     */     //   244: putfield 25	com/carrotsearch/hppc/ObjectArrayDeque:tail	I
/*     */     //   247: aload 7
/*     */     //   249: athrow
/*     */     //   250: iload_2
/*     */     //   251: ireturn
/*     */     // Line number table:
/*     */     //   Java source line #824	-> byte code offset #0
/*     */     //   Java source line #825	-> byte code offset #2
/*     */     //   Java source line #826	-> byte code offset #7
/*     */     //   Java source line #828	-> byte code offset #14
/*     */     //   Java source line #831	-> byte code offset #23
/*     */     //   Java source line #833	-> byte code offset #38
/*     */     //   Java source line #835	-> byte code offset #54
/*     */     //   Java source line #836	-> byte code offset #62
/*     */     //   Java source line #837	-> byte code offset #65
/*     */     //   Java source line #840	-> byte code offset #68
/*     */     //   Java source line #842	-> byte code offset #75
/*     */     //   Java source line #843	-> byte code offset #89
/*     */     //   Java source line #846	-> byte code offset #97
/*     */     //   Java source line #831	-> byte code offset #106
/*     */     //   Java source line #852	-> byte code offset #118
/*     */     //   Java source line #854	-> byte code offset #124
/*     */     //   Java source line #856	-> byte code offset #131
/*     */     //   Java source line #857	-> byte code offset #145
/*     */     //   Java source line #860	-> byte code offset #153
/*     */     //   Java source line #852	-> byte code offset #162
/*     */     //   Java source line #862	-> byte code offset #174
/*     */     //   Java source line #863	-> byte code offset #180
/*     */     //   Java source line #852	-> byte code offset #183
/*     */     //   Java source line #854	-> byte code offset #191
/*     */     //   Java source line #856	-> byte code offset #198
/*     */     //   Java source line #857	-> byte code offset #212
/*     */     //   Java source line #860	-> byte code offset #220
/*     */     //   Java source line #852	-> byte code offset #229
/*     */     //   Java source line #862	-> byte code offset #241
/*     */     //   Java source line #865	-> byte code offset #250
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	252	0	this	ObjectArrayDeque<KType>
/*     */     //   0	252	1	predicate	ObjectPredicate<? super KType>
/*     */     //   1	250	2	removed	int
/*     */     //   6	182	3	last	int
/*     */     //   12	220	4	bufLen	int
/*     */     //   21	216	5	from	int
/*     */     //   19	224	6	to	int
/*     */     //   183	65	7	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	118	183	finally
/*     */     //   183	185	183	finally
/*     */   }
/*     */   
/*     */   public boolean contains(KType e)
/*     */   {
/* 874 */     int fromIndex = this.head;
/* 875 */     int toIndex = this.tail;
/*     */     
/* 877 */     KType[] buffer = this.buffer;
/* 878 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 880 */       if (e == null ? buffer[i] == null : e.equals(buffer[i])) {
/* 881 */         return true;
/*     */       }
/*     */     }
/* 884 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 893 */     int h = 1;
/* 894 */     int fromIndex = this.head;
/* 895 */     int toIndex = this.tail;
/*     */     
/* 897 */     KType[] buffer = this.buffer;
/* 898 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 900 */       h = 31 * h + Internals.rehash(this.buffer[i]);
/*     */     }
/* 902 */     return h;
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
/* 914 */     if (obj != null)
/*     */     {
/* 916 */       if ((obj instanceof ObjectDeque))
/*     */       {
/* 918 */         ObjectDeque<Object> other = (ObjectDeque)obj;
/* 919 */         if (other.size() == size())
/*     */         {
/* 921 */           int fromIndex = this.head;
/* 922 */           KType[] buffer = this.buffer;
/* 923 */           int i = fromIndex;
/* 924 */           for (ObjectCursor<Object> c : other)
/*     */           {
/* 926 */             if (c.value == null ? buffer[i] != null : !c.value.equals(buffer[i]))
/* 927 */               return false;
/* 928 */             i = oneRight(i, buffer.length);
/*     */           }
/* 930 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 934 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayDeque<KType> newInstance()
/*     */   {
/* 944 */     return new ObjectArrayDeque();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayDeque<KType> newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 954 */     return new ObjectArrayDeque(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayDeque<KType> from(KType... elements)
/*     */   {
/* 963 */     ObjectArrayDeque<KType> coll = new ObjectArrayDeque(elements.length);
/* 964 */     coll.addLast(elements);
/* 965 */     return coll;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayDeque<KType> from(ObjectArrayDeque<KType> container)
/*     */   {
/* 974 */     return new ObjectArrayDeque(container);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectArrayDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */