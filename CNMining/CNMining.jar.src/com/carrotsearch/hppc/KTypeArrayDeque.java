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
/*     */ public class KTypeArrayDeque<KType>
/*     */   extends AbstractKTypeCollection<KType>
/*     */   implements KTypeDeque<KType>, Cloneable
/*     */ {
/*     */   public static final int DEFAULT_CAPACITY = 5;
/*     */   public KType[] buffer;
/*     */   public int head;
/*     */   public int tail;
/*     */   protected final ArraySizingStrategy resizer;
/*     */   
/*     */   public KTypeArrayDeque()
/*     */   {
/*  98 */     this(5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeArrayDeque(int initialCapacity)
/*     */   {
/* 108 */     this(initialCapacity, new BoundedProportionalArraySizingStrategy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeArrayDeque(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/* 116 */     assert (initialCapacity >= 0) : ("initialCapacity must be >= 0: " + initialCapacity);
/* 117 */     assert (resizer != null);
/*     */     
/* 119 */     this.resizer = resizer;
/* 120 */     initialCapacity = resizer.round(initialCapacity);
/* 121 */     this.buffer = ((Object[])Intrinsics.newKTypeArray(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeArrayDeque(KTypeContainer<? extends KType> container)
/*     */   {
/* 130 */     this(container.size());
/* 131 */     addLast(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addFirst(KType e1)
/*     */   {
/* 140 */     int h = oneLeft(this.head, this.buffer.length);
/* 141 */     if (h == this.tail)
/*     */     {
/* 143 */       ensureBufferSpace(1);
/* 144 */       h = oneLeft(this.head, this.buffer.length);
/*     */     }
/* 146 */     this.buffer[(this.head = h)] = e1;
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
/* 157 */     ensureBufferSpace(elements.length);
/*     */     
/*     */ 
/* 160 */     for (int i = 0; i < elements.length; i++) {
/* 161 */       addFirst(elements[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addFirst(KTypeContainer<? extends KType> container)
/*     */   {
/* 172 */     int size = container.size();
/* 173 */     ensureBufferSpace(size);
/*     */     
/* 175 */     for (KTypeCursor<? extends KType> cursor : container)
/*     */     {
/* 177 */       addFirst(cursor.value);
/*     */     }
/*     */     
/* 180 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addFirst(Iterable<? extends KTypeCursor<? extends KType>> iterable)
/*     */   {
/* 190 */     int size = 0;
/* 191 */     for (KTypeCursor<? extends KType> cursor : iterable)
/*     */     {
/* 193 */       addFirst(cursor.value);
/* 194 */       size++;
/*     */     }
/* 196 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addLast(KType e1)
/*     */   {
/* 205 */     int t = oneRight(this.tail, this.buffer.length);
/* 206 */     if (this.head == t)
/*     */     {
/* 208 */       ensureBufferSpace(1);
/* 209 */       t = oneRight(this.tail, this.buffer.length);
/*     */     }
/* 211 */     this.buffer[this.tail] = e1;
/* 212 */     this.tail = t;
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
/* 223 */     ensureBufferSpace(1);
/*     */     
/*     */ 
/* 226 */     for (int i = 0; i < elements.length; i++) {
/* 227 */       addLast(elements[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addLast(KTypeContainer<? extends KType> container)
/*     */   {
/* 238 */     int size = container.size();
/* 239 */     ensureBufferSpace(size);
/*     */     
/* 241 */     for (KTypeCursor<? extends KType> cursor : container)
/*     */     {
/* 243 */       addLast(cursor.value);
/*     */     }
/*     */     
/* 246 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addLast(Iterable<? extends KTypeCursor<? extends KType>> iterable)
/*     */   {
/* 256 */     int size = 0;
/* 257 */     for (KTypeCursor<? extends KType> cursor : iterable)
/*     */     {
/* 259 */       addLast(cursor.value);
/* 260 */       size++;
/*     */     }
/* 262 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType removeFirst()
/*     */   {
/* 271 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 273 */     KType result = this.buffer[this.head];
/* 274 */     this.buffer[this.head] = Intrinsics.defaultKTypeValue();
/* 275 */     this.head = oneRight(this.head, this.buffer.length);
/* 276 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType removeLast()
/*     */   {
/* 285 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 287 */     this.tail = oneLeft(this.tail, this.buffer.length);
/* 288 */     KType result = this.buffer[this.tail];
/* 289 */     this.buffer[this.tail] = Intrinsics.defaultKTypeValue();
/* 290 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType getFirst()
/*     */   {
/* 299 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 301 */     return (KType)this.buffer[this.head];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType getLast()
/*     */   {
/* 310 */     assert (size() > 0) : "The deque is empty.";
/*     */     
/* 312 */     return (KType)this.buffer[oneLeft(this.tail, this.buffer.length)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeFirstOccurrence(KType e1)
/*     */   {
/* 321 */     int index = bufferIndexOf(e1);
/* 322 */     if (index >= 0) removeAtBufferIndex(index);
/* 323 */     return index;
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
/* 336 */     int last = this.tail;
/* 337 */     int bufLen = this.buffer.length;
/* 338 */     for (int i = this.head; i != last; i = oneRight(i, bufLen))
/*     */     {
/* 340 */       if (Intrinsics.equalsKType(e1, this.buffer[i])) {
/* 341 */         return i;
/*     */       }
/*     */     }
/* 344 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeLastOccurrence(KType e1)
/*     */   {
/* 353 */     int index = lastBufferIndexOf(e1);
/* 354 */     if (index >= 0) removeAtBufferIndex(index);
/* 355 */     return index;
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
/* 368 */     int bufLen = this.buffer.length;
/* 369 */     int last = oneLeft(this.head, bufLen);
/* 370 */     for (int i = oneLeft(this.tail, bufLen); i != last; i = oneLeft(i, bufLen))
/*     */     {
/* 372 */       if (Intrinsics.equalsKType(e1, this.buffer[i])) {
/* 373 */         return i;
/*     */       }
/*     */     }
/* 376 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(KType e1)
/*     */   {
/* 385 */     int removed = 0;
/* 386 */     int last = this.tail;
/* 387 */     int bufLen = this.buffer.length;
/*     */     int to;
/* 389 */     for (int from = to = this.head; from != last; from = oneRight(from, bufLen))
/*     */     {
/* 391 */       if (Intrinsics.equalsKType(e1, this.buffer[from]))
/*     */       {
/* 393 */         this.buffer[from] = Intrinsics.defaultKTypeValue();
/* 394 */         removed++;
/*     */       }
/*     */       else
/*     */       {
/* 398 */         if (to != from)
/*     */         {
/* 400 */           this.buffer[to] = this.buffer[from];
/* 401 */           this.buffer[from] = Intrinsics.defaultKTypeValue();
/*     */         }
/*     */         
/* 404 */         to = oneRight(to, bufLen);
/*     */       }
/*     */     }
/* 407 */     this.tail = to;
/* 408 */     return removed;
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
/* 422 */     assert (this.head <= this.tail ? (index >= this.head) || (index < this.tail) : (index >= this.head) || (index < this.tail)) : ("Index out of range (head=" + this.head + ", tail=" + this.tail + ", index=" + index + ").");
/*     */     
/*     */ 
/*     */ 
/* 426 */     KType[] b = this.buffer;
/* 427 */     int bufLen = b.length;
/* 428 */     int lastIndex = bufLen - 1;
/* 429 */     int head = this.head;
/* 430 */     int tail = this.tail;
/*     */     
/* 432 */     int leftChunk = Math.abs(index - head) % bufLen;
/* 433 */     int rightChunk = Math.abs(tail - index) % bufLen;
/*     */     
/* 435 */     if (leftChunk < rightChunk)
/*     */     {
/* 437 */       if (index >= head)
/*     */       {
/* 439 */         System.arraycopy(b, head, b, head + 1, leftChunk);
/*     */       }
/*     */       else
/*     */       {
/* 443 */         System.arraycopy(b, 0, b, 1, index);
/* 444 */         b[0] = b[lastIndex];
/* 445 */         System.arraycopy(b, head, b, head + 1, lastIndex - head);
/*     */       }
/* 447 */       b[head] = Intrinsics.defaultKTypeValue();
/* 448 */       this.head = oneRight(head, bufLen);
/*     */     }
/*     */     else
/*     */     {
/* 452 */       if (index < tail)
/*     */       {
/* 454 */         System.arraycopy(b, index + 1, b, index, rightChunk);
/*     */       }
/*     */       else
/*     */       {
/* 458 */         System.arraycopy(b, index + 1, b, index, lastIndex - index);
/* 459 */         b[lastIndex] = b[0];
/* 460 */         System.arraycopy(b, 1, b, 0, tail);
/*     */       }
/* 462 */       b[tail] = Intrinsics.defaultKTypeValue();
/* 463 */       this.tail = oneLeft(tail, bufLen);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 473 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 482 */     if (this.head <= this.tail) {
/* 483 */       return this.tail - this.head;
/*     */     }
/* 485 */     return this.tail - this.head + this.buffer.length;
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
/* 498 */     if (this.head < this.tail)
/*     */     {
/* 500 */       Arrays.fill(this.buffer, this.head, this.tail, Intrinsics.defaultKTypeValue());
/*     */     }
/*     */     else
/*     */     {
/* 504 */       Arrays.fill(this.buffer, 0, this.tail, Intrinsics.defaultKTypeValue());
/* 505 */       Arrays.fill(this.buffer, this.head, this.buffer.length, Intrinsics.defaultKTypeValue());
/*     */     }
/* 507 */     this.head = (this.tail = 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void release()
/*     */   {
/* 515 */     this.head = (this.tail = 0);
/* 516 */     this.buffer = ((Object[])Intrinsics.newKTypeArray(this.resizer.round(5)));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void ensureBufferSpace(int expectedAdditions)
/*     */   {
/* 525 */     int bufferLen = this.buffer == null ? 0 : this.buffer.length;
/* 526 */     int elementsCount = size();
/*     */     
/* 528 */     if (elementsCount >= bufferLen - expectedAdditions - 1)
/*     */     {
/* 530 */       int newSize = this.resizer.grow(bufferLen, elementsCount, expectedAdditions + 1);
/* 531 */       assert (newSize >= elementsCount + expectedAdditions + 1) : ("Resizer failed to return sensible new size: " + newSize + " <= " + (elementsCount + expectedAdditions));
/*     */       
/*     */ 
/*     */ 
/* 535 */       KType[] newBuffer = (Object[])Intrinsics.newKTypeArray(newSize);
/* 536 */       if (bufferLen > 0)
/*     */       {
/* 538 */         toArray(newBuffer);
/* 539 */         this.tail = elementsCount;
/* 540 */         this.head = 0;
/*     */       }
/* 542 */       this.buffer = newBuffer;
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
/*     */   public Object[] toArray()
/*     */   {
/* 556 */     int size = size();
/* 557 */     return toArray((Object[])Intrinsics.newKTypeArray(size));
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
/* 570 */     assert (target.length >= size()) : ("Target array must be >= " + size());
/*     */     
/* 572 */     if (this.head < this.tail)
/*     */     {
/*     */ 
/* 575 */       System.arraycopy(this.buffer, this.head, target, 0, size());
/*     */     }
/* 577 */     else if (this.head > this.tail)
/*     */     {
/*     */ 
/*     */ 
/* 581 */       int rightCount = this.buffer.length - this.head;
/* 582 */       System.arraycopy(this.buffer, this.head, target, 0, rightCount);
/* 583 */       System.arraycopy(this.buffer, 0, target, rightCount, this.tail);
/*     */     }
/*     */     
/* 586 */     return target;
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
/*     */   public KTypeArrayDeque<KType> clone()
/*     */   {
/*     */     try
/*     */     {
/* 601 */       KTypeArrayDeque<KType> cloned = (KTypeArrayDeque)super.clone();
/* 602 */       cloned.buffer = ((Object[])this.buffer.clone());
/* 603 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 607 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static int oneLeft(int index, int modulus)
/*     */   {
/* 616 */     if (index >= 1) return index - 1;
/* 617 */     return modulus - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected static int oneRight(int index, int modulus)
/*     */   {
/* 625 */     if (index + 1 == modulus) return 0;
/* 626 */     return index + 1;
/*     */   }
/*     */   
/*     */ 
/*     */   private final class ValueIterator
/*     */     extends AbstractIterator<KTypeCursor<KType>>
/*     */   {
/*     */     private final KTypeCursor<KType> cursor;
/*     */     
/*     */     private int remaining;
/*     */     
/*     */     public ValueIterator()
/*     */     {
/* 639 */       this.cursor = new KTypeCursor();
/* 640 */       this.cursor.index = KTypeArrayDeque.oneLeft(KTypeArrayDeque.this.head, KTypeArrayDeque.this.buffer.length);
/* 641 */       this.remaining = KTypeArrayDeque.this.size();
/*     */     }
/*     */     
/*     */ 
/*     */     protected KTypeCursor<KType> fetch()
/*     */     {
/* 647 */       if (this.remaining == 0) {
/* 648 */         return (KTypeCursor)done();
/*     */       }
/* 650 */       this.remaining -= 1;
/* 651 */       this.cursor.value = KTypeArrayDeque.this.buffer[(this.cursor.index = KTypeArrayDeque.oneRight(this.cursor.index, KTypeArrayDeque.this.buffer.length))];
/* 652 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   private final class DescendingValueIterator
/*     */     extends AbstractIterator<KTypeCursor<KType>>
/*     */   {
/*     */     private final KTypeCursor<KType> cursor;
/*     */     
/*     */     private int remaining;
/*     */     
/*     */     public DescendingValueIterator()
/*     */     {
/* 666 */       this.cursor = new KTypeCursor();
/* 667 */       this.cursor.index = KTypeArrayDeque.this.tail;
/* 668 */       this.remaining = KTypeArrayDeque.this.size();
/*     */     }
/*     */     
/*     */ 
/*     */     protected KTypeCursor<KType> fetch()
/*     */     {
/* 674 */       if (this.remaining == 0) {
/* 675 */         return (KTypeCursor)done();
/*     */       }
/* 677 */       this.remaining -= 1;
/* 678 */       this.cursor.value = KTypeArrayDeque.this.buffer[(this.cursor.index = KTypeArrayDeque.oneLeft(this.cursor.index, KTypeArrayDeque.this.buffer.length))];
/* 679 */       return this.cursor;
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
/*     */   public Iterator<KTypeCursor<KType>> iterator()
/*     */   {
/* 700 */     return new ValueIterator();
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
/*     */   public Iterator<KTypeCursor<KType>> descendingIterator()
/*     */   {
/* 721 */     return new DescendingValueIterator();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypeProcedure<? super KType>> T forEach(T procedure)
/*     */   {
/* 730 */     forEach(procedure, this.head, this.tail);
/* 731 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void forEach(KTypeProcedure<? super KType> procedure, int fromIndex, int toIndex)
/*     */   {
/* 741 */     KType[] buffer = this.buffer;
/* 742 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 744 */       procedure.apply(buffer[i]);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypePredicate<? super KType>> T forEach(T predicate)
/*     */   {
/* 754 */     int fromIndex = this.head;
/* 755 */     int toIndex = this.tail;
/*     */     
/* 757 */     KType[] buffer = this.buffer;
/* 758 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 760 */       if (!predicate.apply(buffer[i])) {
/*     */         break;
/*     */       }
/*     */     }
/* 764 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypeProcedure<? super KType>> T descendingForEach(T procedure)
/*     */   {
/* 773 */     descendingForEach(procedure, this.head, this.tail);
/* 774 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void descendingForEach(KTypeProcedure<? super KType> procedure, int fromIndex, int toIndex)
/*     */   {
/* 784 */     if (fromIndex == toIndex) {
/* 785 */       return;
/*     */     }
/* 787 */     KType[] buffer = this.buffer;
/* 788 */     int i = toIndex;
/*     */     do
/*     */     {
/* 791 */       i = oneLeft(i, buffer.length);
/* 792 */       procedure.apply(buffer[i]);
/* 793 */     } while (i != fromIndex);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypePredicate<? super KType>> T descendingForEach(T predicate)
/*     */   {
/* 802 */     descendingForEach(predicate, this.head, this.tail);
/* 803 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void descendingForEach(KTypePredicate<? super KType> predicate, int fromIndex, int toIndex)
/*     */   {
/* 814 */     if (fromIndex == toIndex) {
/* 815 */       return;
/*     */     }
/* 817 */     KType[] buffer = this.buffer;
/* 818 */     int i = toIndex;
/*     */     do
/*     */     {
/* 821 */       i = oneLeft(i, buffer.length);
/* 822 */     } while ((predicate.apply(buffer[i])) && 
/*     */     
/* 824 */       (i != fromIndex));
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int removeAll(KTypePredicate<? super KType> predicate)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: iconst_0
/*     */     //   1: istore_2
/*     */     //   2: aload_0
/*     */     //   3: getfield 25	com/carrotsearch/hppc/KTypeArrayDeque:tail	I
/*     */     //   6: istore_3
/*     */     //   7: aload_0
/*     */     //   8: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   11: arraylength
/*     */     //   12: istore 4
/*     */     //   14: aload_0
/*     */     //   15: getfield 23	com/carrotsearch/hppc/KTypeArrayDeque:head	I
/*     */     //   18: dup
/*     */     //   19: istore 6
/*     */     //   21: istore 5
/*     */     //   23: aload_0
/*     */     //   24: getfield 23	com/carrotsearch/hppc/KTypeArrayDeque:head	I
/*     */     //   27: dup
/*     */     //   28: istore 6
/*     */     //   30: istore 5
/*     */     //   32: iload 5
/*     */     //   34: iload_3
/*     */     //   35: if_icmpeq +87 -> 122
/*     */     //   38: aload_1
/*     */     //   39: aload_0
/*     */     //   40: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   43: iload 5
/*     */     //   45: aaload
/*     */     //   46: invokeinterface 67 2 0
/*     */     //   51: ifeq +19 -> 70
/*     */     //   54: aload_0
/*     */     //   55: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   58: iload 5
/*     */     //   60: invokestatic 38	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   63: aastore
/*     */     //   64: iinc 2 1
/*     */     //   67: goto +43 -> 110
/*     */     //   70: iload 6
/*     */     //   72: iload 5
/*     */     //   74: if_icmpeq +27 -> 101
/*     */     //   77: aload_0
/*     */     //   78: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   81: iload 6
/*     */     //   83: aload_0
/*     */     //   84: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   87: iload 5
/*     */     //   89: aaload
/*     */     //   90: aastore
/*     */     //   91: aload_0
/*     */     //   92: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   95: iload 5
/*     */     //   97: invokestatic 38	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   100: aastore
/*     */     //   101: iload 6
/*     */     //   103: iload 4
/*     */     //   105: invokestatic 34	com/carrotsearch/hppc/KTypeArrayDeque:oneRight	(II)I
/*     */     //   108: istore 6
/*     */     //   110: iload 5
/*     */     //   112: iload 4
/*     */     //   114: invokestatic 34	com/carrotsearch/hppc/KTypeArrayDeque:oneRight	(II)I
/*     */     //   117: istore 5
/*     */     //   119: goto -87 -> 32
/*     */     //   122: iload 5
/*     */     //   124: iload_3
/*     */     //   125: if_icmpeq +55 -> 180
/*     */     //   128: iload 6
/*     */     //   130: iload 5
/*     */     //   132: if_icmpeq +27 -> 159
/*     */     //   135: aload_0
/*     */     //   136: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   139: iload 6
/*     */     //   141: aload_0
/*     */     //   142: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   145: iload 5
/*     */     //   147: aaload
/*     */     //   148: aastore
/*     */     //   149: aload_0
/*     */     //   150: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   153: iload 5
/*     */     //   155: invokestatic 38	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   158: aastore
/*     */     //   159: iload 6
/*     */     //   161: iload 4
/*     */     //   163: invokestatic 34	com/carrotsearch/hppc/KTypeArrayDeque:oneRight	(II)I
/*     */     //   166: istore 6
/*     */     //   168: iload 5
/*     */     //   170: iload 4
/*     */     //   172: invokestatic 34	com/carrotsearch/hppc/KTypeArrayDeque:oneRight	(II)I
/*     */     //   175: istore 5
/*     */     //   177: goto -55 -> 122
/*     */     //   180: aload_0
/*     */     //   181: iload 6
/*     */     //   183: putfield 25	com/carrotsearch/hppc/KTypeArrayDeque:tail	I
/*     */     //   186: goto +72 -> 258
/*     */     //   189: astore 7
/*     */     //   191: iload 5
/*     */     //   193: iload_3
/*     */     //   194: if_icmpeq +55 -> 249
/*     */     //   197: iload 6
/*     */     //   199: iload 5
/*     */     //   201: if_icmpeq +27 -> 228
/*     */     //   204: aload_0
/*     */     //   205: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   208: iload 6
/*     */     //   210: aload_0
/*     */     //   211: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   214: iload 5
/*     */     //   216: aaload
/*     */     //   217: aastore
/*     */     //   218: aload_0
/*     */     //   219: getfield 20	com/carrotsearch/hppc/KTypeArrayDeque:buffer	[Ljava/lang/Object;
/*     */     //   222: iload 5
/*     */     //   224: invokestatic 38	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   227: aastore
/*     */     //   228: iload 6
/*     */     //   230: iload 4
/*     */     //   232: invokestatic 34	com/carrotsearch/hppc/KTypeArrayDeque:oneRight	(II)I
/*     */     //   235: istore 6
/*     */     //   237: iload 5
/*     */     //   239: iload 4
/*     */     //   241: invokestatic 34	com/carrotsearch/hppc/KTypeArrayDeque:oneRight	(II)I
/*     */     //   244: istore 5
/*     */     //   246: goto -55 -> 191
/*     */     //   249: aload_0
/*     */     //   250: iload 6
/*     */     //   252: putfield 25	com/carrotsearch/hppc/KTypeArrayDeque:tail	I
/*     */     //   255: aload 7
/*     */     //   257: athrow
/*     */     //   258: iload_2
/*     */     //   259: ireturn
/*     */     // Line number table:
/*     */     //   Java source line #833	-> byte code offset #0
/*     */     //   Java source line #834	-> byte code offset #2
/*     */     //   Java source line #835	-> byte code offset #7
/*     */     //   Java source line #837	-> byte code offset #14
/*     */     //   Java source line #840	-> byte code offset #23
/*     */     //   Java source line #842	-> byte code offset #38
/*     */     //   Java source line #844	-> byte code offset #54
/*     */     //   Java source line #845	-> byte code offset #64
/*     */     //   Java source line #846	-> byte code offset #67
/*     */     //   Java source line #849	-> byte code offset #70
/*     */     //   Java source line #851	-> byte code offset #77
/*     */     //   Java source line #852	-> byte code offset #91
/*     */     //   Java source line #855	-> byte code offset #101
/*     */     //   Java source line #840	-> byte code offset #110
/*     */     //   Java source line #861	-> byte code offset #122
/*     */     //   Java source line #863	-> byte code offset #128
/*     */     //   Java source line #865	-> byte code offset #135
/*     */     //   Java source line #866	-> byte code offset #149
/*     */     //   Java source line #869	-> byte code offset #159
/*     */     //   Java source line #861	-> byte code offset #168
/*     */     //   Java source line #871	-> byte code offset #180
/*     */     //   Java source line #872	-> byte code offset #186
/*     */     //   Java source line #861	-> byte code offset #189
/*     */     //   Java source line #863	-> byte code offset #197
/*     */     //   Java source line #865	-> byte code offset #204
/*     */     //   Java source line #866	-> byte code offset #218
/*     */     //   Java source line #869	-> byte code offset #228
/*     */     //   Java source line #861	-> byte code offset #237
/*     */     //   Java source line #871	-> byte code offset #249
/*     */     //   Java source line #874	-> byte code offset #258
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	260	0	this	KTypeArrayDeque<KType>
/*     */     //   0	260	1	predicate	KTypePredicate<? super KType>
/*     */     //   1	258	2	removed	int
/*     */     //   6	188	3	last	int
/*     */     //   12	228	4	bufLen	int
/*     */     //   21	224	5	from	int
/*     */     //   19	232	6	to	int
/*     */     //   189	67	7	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   23	122	189	finally
/*     */     //   189	191	189	finally
/*     */   }
/*     */   
/*     */   public boolean contains(KType e)
/*     */   {
/* 883 */     int fromIndex = this.head;
/* 884 */     int toIndex = this.tail;
/*     */     
/* 886 */     KType[] buffer = this.buffer;
/* 887 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 889 */       if (Intrinsics.equalsKType(e, buffer[i])) {
/* 890 */         return true;
/*     */       }
/*     */     }
/* 893 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 902 */     int h = 1;
/* 903 */     int fromIndex = this.head;
/* 904 */     int toIndex = this.tail;
/*     */     
/* 906 */     KType[] buffer = this.buffer;
/* 907 */     for (int i = fromIndex; i != toIndex; i = oneRight(i, buffer.length))
/*     */     {
/* 909 */       h = 31 * h + Internals.rehash(this.buffer[i]);
/*     */     }
/* 911 */     return h;
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
/* 923 */     if (obj != null)
/*     */     {
/* 925 */       if ((obj instanceof KTypeDeque))
/*     */       {
/* 927 */         KTypeDeque<Object> other = (KTypeDeque)obj;
/* 928 */         if (other.size() == size())
/*     */         {
/* 930 */           int fromIndex = this.head;
/* 931 */           KType[] buffer = this.buffer;
/* 932 */           int i = fromIndex;
/* 933 */           for (KTypeCursor<Object> c : other)
/*     */           {
/* 935 */             if (!Intrinsics.equalsKType(c.value, buffer[i]))
/* 936 */               return false;
/* 937 */             i = oneRight(i, buffer.length);
/*     */           }
/* 939 */           return true;
/*     */         }
/*     */       }
/*     */     }
/* 943 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayDeque<KType> newInstance()
/*     */   {
/* 953 */     return new KTypeArrayDeque();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayDeque<KType> newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 963 */     return new KTypeArrayDeque(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayDeque<KType> from(KType... elements)
/*     */   {
/* 972 */     KTypeArrayDeque<KType> coll = new KTypeArrayDeque(elements.length);
/* 973 */     coll.addLast(elements);
/* 974 */     return coll;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayDeque<KType> from(KTypeArrayDeque<KType> container)
/*     */   {
/* 983 */     return new KTypeArrayDeque(container);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeArrayDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */