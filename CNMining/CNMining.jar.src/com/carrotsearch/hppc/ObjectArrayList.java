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
/*     */ public class ObjectArrayList<KType>
/*     */   extends AbstractObjectCollection<KType>
/*     */   implements ObjectIndexedContainer<KType>, Cloneable
/*     */ {
/*     */   public static final int DEFAULT_CAPACITY = 5;
/*  56 */   private static final Object EMPTY = new Object[0];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType[] buffer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int elementsCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final ArraySizingStrategy resizer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList()
/*     */   {
/* 103 */     this(5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList(int initialCapacity)
/*     */   {
/* 113 */     this(initialCapacity, new BoundedProportionalArraySizingStrategy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/* 121 */     assert (initialCapacity >= 0) : ("initialCapacity must be >= 0: " + initialCapacity);
/* 122 */     assert (resizer != null);
/*     */     
/* 124 */     this.resizer = resizer;
/* 125 */     ensureBufferSpace(resizer.round(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectArrayList(ObjectContainer<? extends KType> container)
/*     */   {
/* 133 */     this(container.size());
/* 134 */     addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType e1)
/*     */   {
/* 143 */     ensureBufferSpace(1);
/* 144 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType e1, KType e2)
/*     */   {
/* 154 */     ensureBufferSpace(2);
/* 155 */     this.buffer[(this.elementsCount++)] = e1;
/* 156 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType[] elements, int start, int length)
/*     */   {
/* 164 */     assert (length >= 0) : "Length must be >= 0";
/*     */     
/* 166 */     ensureBufferSpace(length);
/* 167 */     System.arraycopy(elements, start, this.buffer, this.elementsCount, length);
/* 168 */     this.elementsCount += length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType... elements)
/*     */   {
/* 178 */     add(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(ObjectContainer<? extends KType> container)
/*     */   {
/* 186 */     int size = container.size();
/* 187 */     ensureBufferSpace(size);
/*     */     
/* 189 */     for (ObjectCursor<? extends KType> cursor : container)
/*     */     {
/* 191 */       add(cursor.value);
/*     */     }
/*     */     
/* 194 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(Iterable<? extends ObjectCursor<? extends KType>> iterable)
/*     */   {
/* 202 */     int size = 0;
/* 203 */     for (ObjectCursor<? extends KType> cursor : iterable)
/*     */     {
/* 205 */       add(cursor.value);
/* 206 */       size++;
/*     */     }
/* 208 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void insert(int index, KType e1)
/*     */   {
/* 218 */     assert ((index >= 0) && (index <= size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 220 */     ensureBufferSpace(1);
/* 221 */     System.arraycopy(this.buffer, index, this.buffer, index + 1, this.elementsCount - index);
/* 222 */     this.buffer[index] = e1;
/* 223 */     this.elementsCount += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType get(int index)
/*     */   {
/* 233 */     assert ((index >= 0) && (index < size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/* 235 */     return (KType)this.buffer[index];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType set(int index, KType e1)
/*     */   {
/* 245 */     assert ((index >= 0) && (index < size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/* 247 */     KType v = this.buffer[index];
/* 248 */     this.buffer[index] = e1;
/* 249 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType remove(int index)
/*     */   {
/* 259 */     assert ((index >= 0) && (index < size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/* 261 */     KType v = this.buffer[index];
/* 262 */     if (index + 1 < this.elementsCount)
/* 263 */       System.arraycopy(this.buffer, index + 1, this.buffer, index, this.elementsCount - index - 1);
/* 264 */     this.elementsCount -= 1;
/* 265 */     this.buffer[this.elementsCount] = null;
/* 266 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeRange(int fromIndex, int toIndex)
/*     */   {
/* 276 */     assert ((fromIndex >= 0) && (fromIndex <= size())) : ("Index " + fromIndex + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/*     */ 
/* 279 */     assert ((toIndex >= 0) && (toIndex <= size())) : ("Index " + toIndex + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 281 */     assert (fromIndex <= toIndex) : ("fromIndex must be <= toIndex: " + fromIndex + ", " + toIndex);
/*     */     
/*     */ 
/* 284 */     System.arraycopy(this.buffer, toIndex, this.buffer, fromIndex, this.elementsCount - toIndex);
/*     */     
/* 286 */     int count = toIndex - fromIndex;
/* 287 */     this.elementsCount -= count;
/* 288 */     Arrays.fill(this.buffer, this.elementsCount, this.elementsCount + count, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeFirstOccurrence(KType e1)
/*     */   {
/* 298 */     int index = indexOf(e1);
/* 299 */     if (index >= 0) remove(index);
/* 300 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeLastOccurrence(KType e1)
/*     */   {
/* 309 */     int index = lastIndexOf(e1);
/* 310 */     if (index >= 0) remove(index);
/* 311 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(KType e1)
/*     */   {
/* 320 */     int to = 0;
/* 321 */     for (int from = 0; from < this.elementsCount; from++)
/*     */     {
/* 323 */       if (e1 == null ? this.buffer[from] == null : e1.equals(this.buffer[from]))
/*     */       {
/* 325 */         this.buffer[from] = null;
/*     */       }
/*     */       else
/*     */       {
/* 329 */         if (to != from)
/*     */         {
/* 331 */           this.buffer[to] = this.buffer[from];
/* 332 */           this.buffer[from] = null;
/*     */         }
/* 334 */         to++;
/*     */       }
/*     */     }
/* 337 */     int deleted = this.elementsCount - to;
/* 338 */     this.elementsCount = to;
/* 339 */     return deleted;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(KType e1)
/*     */   {
/* 348 */     return indexOf(e1) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(KType e1)
/*     */   {
/* 357 */     for (int i = 0; i < this.elementsCount; i++) {
/* 358 */       if (e1 == null ? this.buffer[i] == null : e1.equals(this.buffer[i]))
/* 359 */         return i;
/*     */     }
/* 361 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(KType e1)
/*     */   {
/* 370 */     for (int i = this.elementsCount - 1; i >= 0; i--) {
/* 371 */       if (e1 == null ? this.buffer[i] == null : e1.equals(this.buffer[i]))
/* 372 */         return i;
/*     */     }
/* 374 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 383 */     return this.elementsCount == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int minCapacity)
/*     */   {
/* 393 */     if (minCapacity > this.buffer.length) {
/* 394 */       ensureBufferSpace(minCapacity - size());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void ensureBufferSpace(int expectedAdditions)
/*     */   {
/* 403 */     int bufferLen = this.buffer == null ? 0 : this.buffer.length;
/* 404 */     if (this.elementsCount >= bufferLen - expectedAdditions)
/*     */     {
/* 406 */       int newSize = this.resizer.grow(bufferLen, this.elementsCount, expectedAdditions);
/* 407 */       assert (newSize >= this.elementsCount + expectedAdditions) : ("Resizer failed to return sensible new size: " + newSize + " <= " + (this.elementsCount + expectedAdditions));
/*     */       
/*     */ 
/*     */ 
/* 411 */       KType[] newBuffer = (Object[])Internals.newArray(newSize);
/* 412 */       if (bufferLen > 0)
/*     */       {
/* 414 */         System.arraycopy(this.buffer, 0, newBuffer, 0, this.buffer.length);
/*     */       }
/* 416 */       this.buffer = newBuffer;
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
/*     */   public void resize(int newSize)
/*     */   {
/* 429 */     if (newSize <= this.buffer.length)
/*     */     {
/* 431 */       if (newSize < this.elementsCount)
/*     */       {
/* 433 */         Arrays.fill(this.buffer, newSize, this.elementsCount, null);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 438 */         Arrays.fill(this.buffer, this.elementsCount, newSize, null);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else {
/* 444 */       ensureCapacity(newSize);
/*     */     }
/* 446 */     this.elementsCount = newSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 455 */     return this.elementsCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 466 */     if (size() != this.buffer.length) {
/* 467 */       this.buffer = ((Object[])toArray());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 478 */     Arrays.fill(this.buffer, 0, this.elementsCount, null);
/* 479 */     this.elementsCount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void release()
/*     */   {
/* 490 */     this.buffer = ((Object[])EMPTY);
/* 491 */     this.elementsCount = 0;
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
/*     */   public Object[] toArray()
/*     */   {
/* 505 */     return Arrays.copyOf(this.buffer, this.elementsCount);
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
/*     */   public ObjectArrayList<KType> clone()
/*     */   {
/*     */     try
/*     */     {
/* 520 */       ObjectArrayList<KType> cloned = (ObjectArrayList)super.clone();
/* 521 */       cloned.buffer = ((Object[])this.buffer.clone());
/* 522 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 526 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 536 */     int h = 1;int max = this.elementsCount;
/* 537 */     for (int i = 0; i < max; i++)
/*     */     {
/* 539 */       h = 31 * h + Internals.rehash(this.buffer[i]);
/*     */     }
/* 541 */     return h;
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
/* 553 */     if (obj != null)
/*     */     {
/* 555 */       if ((obj instanceof ObjectArrayList))
/*     */       {
/* 557 */         ObjectArrayList<?> other = (ObjectArrayList)obj;
/* 558 */         return (other.size() == size()) && (rangeEquals(other.buffer, this.buffer, size()));
/*     */       }
/*     */       
/* 561 */       if ((obj instanceof ObjectIndexedContainer))
/*     */       {
/* 563 */         ObjectIndexedContainer<?> other = (ObjectIndexedContainer)obj;
/* 564 */         return (other.size() == size()) && (allIndexesEqual(this, other, size()));
/*     */       }
/*     */     }
/*     */     
/* 568 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean rangeEquals(Object[] b1, Object[] b2, int length)
/*     */   {
/* 578 */     for (int i = 0; i < length; i++)
/*     */     {
/* 580 */       if (b1[i] == null ? b2[i] != null : !b1[i].equals(b2[i]))
/*     */       {
/* 582 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 586 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean allIndexesEqual(ObjectIndexedContainer<KType> b1, ObjectIndexedContainer<KType> b2, int length)
/*     */   {
/* 596 */     for (int i = 0; i < length; i++)
/*     */     {
/* 598 */       KType o1 = b1.get(i);
/* 599 */       KType o2 = b2.get(i);
/*     */       
/* 601 */       if (o1 == null ? o2 != null : !o1.equals(o2))
/*     */       {
/* 603 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 607 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   static final class ValueIterator<KType>
/*     */     extends AbstractIterator<ObjectCursor<KType>>
/*     */   {
/*     */     private final ObjectCursor<KType> cursor;
/*     */     
/*     */     private final KType[] buffer;
/*     */     
/*     */     private final int size;
/*     */     
/*     */     public ValueIterator(KType[] buffer, int size)
/*     */     {
/* 622 */       this.cursor = new ObjectCursor();
/* 623 */       this.cursor.index = -1;
/* 624 */       this.size = size;
/* 625 */       this.buffer = buffer;
/*     */     }
/*     */     
/*     */ 
/*     */     protected ObjectCursor<KType> fetch()
/*     */     {
/* 631 */       if (this.cursor.index + 1 == this.size) {
/* 632 */         return (ObjectCursor)done();
/*     */       }
/* 634 */       this.cursor.value = this.buffer[(++this.cursor.index)];
/* 635 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<ObjectCursor<KType>> iterator()
/*     */   {
/* 645 */     return new ValueIterator(this.buffer, size());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectProcedure<? super KType>> T forEach(T procedure)
/*     */   {
/* 654 */     return forEach(procedure, 0, size());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectProcedure<? super KType>> T forEach(T procedure, int fromIndex, int toIndex)
/*     */   {
/* 666 */     assert ((fromIndex >= 0) && (fromIndex <= size())) : ("Index " + fromIndex + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/*     */ 
/* 669 */     assert ((toIndex >= 0) && (toIndex <= size())) : ("Index " + toIndex + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 671 */     assert (fromIndex <= toIndex) : ("fromIndex must be <= toIndex: " + fromIndex + ", " + toIndex);
/*     */     
/*     */ 
/* 674 */     KType[] buffer = this.buffer;
/* 675 */     for (int i = fromIndex; i < toIndex; i++)
/*     */     {
/* 677 */       procedure.apply(buffer[i]);
/*     */     }
/*     */     
/* 680 */     return procedure;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int removeAll(ObjectPredicate<? super KType> predicate)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 22	com/carrotsearch/hppc/ObjectArrayList:elementsCount	I
/*     */     //   4: istore_2
/*     */     //   5: iconst_0
/*     */     //   6: istore_3
/*     */     //   7: iconst_0
/*     */     //   8: istore 4
/*     */     //   10: iload 4
/*     */     //   12: iload_2
/*     */     //   13: if_icmpge +66 -> 79
/*     */     //   16: aload_1
/*     */     //   17: aload_0
/*     */     //   18: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   21: iload 4
/*     */     //   23: aaload
/*     */     //   24: invokeinterface 70 2 0
/*     */     //   29: ifeq +14 -> 43
/*     */     //   32: aload_0
/*     */     //   33: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   36: iload 4
/*     */     //   38: aconst_null
/*     */     //   39: aastore
/*     */     //   40: goto +33 -> 73
/*     */     //   43: iload_3
/*     */     //   44: iload 4
/*     */     //   46: if_icmpeq +24 -> 70
/*     */     //   49: aload_0
/*     */     //   50: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   53: iload_3
/*     */     //   54: aload_0
/*     */     //   55: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   58: iload 4
/*     */     //   60: aaload
/*     */     //   61: aastore
/*     */     //   62: aload_0
/*     */     //   63: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   66: iload 4
/*     */     //   68: aconst_null
/*     */     //   69: aastore
/*     */     //   70: iinc 3 1
/*     */     //   73: iinc 4 1
/*     */     //   76: goto -66 -> 10
/*     */     //   79: iload 4
/*     */     //   81: iload_2
/*     */     //   82: if_icmpge +39 -> 121
/*     */     //   85: iload_3
/*     */     //   86: iload 4
/*     */     //   88: if_icmpeq +24 -> 112
/*     */     //   91: aload_0
/*     */     //   92: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   95: iload_3
/*     */     //   96: aload_0
/*     */     //   97: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   100: iload 4
/*     */     //   102: aaload
/*     */     //   103: aastore
/*     */     //   104: aload_0
/*     */     //   105: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   108: iload 4
/*     */     //   110: aconst_null
/*     */     //   111: aastore
/*     */     //   112: iinc 3 1
/*     */     //   115: iinc 4 1
/*     */     //   118: goto -39 -> 79
/*     */     //   121: aload_0
/*     */     //   122: iload_3
/*     */     //   123: putfield 22	com/carrotsearch/hppc/ObjectArrayList:elementsCount	I
/*     */     //   126: goto +55 -> 181
/*     */     //   129: astore 5
/*     */     //   131: iload 4
/*     */     //   133: iload_2
/*     */     //   134: if_icmpge +39 -> 173
/*     */     //   137: iload_3
/*     */     //   138: iload 4
/*     */     //   140: if_icmpeq +24 -> 164
/*     */     //   143: aload_0
/*     */     //   144: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   147: iload_3
/*     */     //   148: aload_0
/*     */     //   149: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   152: iload 4
/*     */     //   154: aaload
/*     */     //   155: aastore
/*     */     //   156: aload_0
/*     */     //   157: getfield 21	com/carrotsearch/hppc/ObjectArrayList:buffer	[Ljava/lang/Object;
/*     */     //   160: iload 4
/*     */     //   162: aconst_null
/*     */     //   163: aastore
/*     */     //   164: iinc 3 1
/*     */     //   167: iinc 4 1
/*     */     //   170: goto -39 -> 131
/*     */     //   173: aload_0
/*     */     //   174: iload_3
/*     */     //   175: putfield 22	com/carrotsearch/hppc/ObjectArrayList:elementsCount	I
/*     */     //   178: aload 5
/*     */     //   180: athrow
/*     */     //   181: iload_2
/*     */     //   182: iload_3
/*     */     //   183: isub
/*     */     //   184: ireturn
/*     */     // Line number table:
/*     */     //   Java source line #689	-> byte code offset #0
/*     */     //   Java source line #690	-> byte code offset #5
/*     */     //   Java source line #691	-> byte code offset #7
/*     */     //   Java source line #694	-> byte code offset #10
/*     */     //   Java source line #696	-> byte code offset #16
/*     */     //   Java source line #698	-> byte code offset #32
/*     */     //   Java source line #699	-> byte code offset #40
/*     */     //   Java source line #702	-> byte code offset #43
/*     */     //   Java source line #704	-> byte code offset #49
/*     */     //   Java source line #705	-> byte code offset #62
/*     */     //   Java source line #707	-> byte code offset #70
/*     */     //   Java source line #694	-> byte code offset #73
/*     */     //   Java source line #713	-> byte code offset #79
/*     */     //   Java source line #715	-> byte code offset #85
/*     */     //   Java source line #717	-> byte code offset #91
/*     */     //   Java source line #718	-> byte code offset #104
/*     */     //   Java source line #720	-> byte code offset #112
/*     */     //   Java source line #713	-> byte code offset #115
/*     */     //   Java source line #723	-> byte code offset #121
/*     */     //   Java source line #724	-> byte code offset #126
/*     */     //   Java source line #713	-> byte code offset #129
/*     */     //   Java source line #715	-> byte code offset #137
/*     */     //   Java source line #717	-> byte code offset #143
/*     */     //   Java source line #718	-> byte code offset #156
/*     */     //   Java source line #720	-> byte code offset #164
/*     */     //   Java source line #713	-> byte code offset #167
/*     */     //   Java source line #723	-> byte code offset #173
/*     */     //   Java source line #726	-> byte code offset #181
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	185	0	this	ObjectArrayList<KType>
/*     */     //   0	185	1	predicate	ObjectPredicate<? super KType>
/*     */     //   4	178	2	elementsCount	int
/*     */     //   6	177	3	to	int
/*     */     //   8	160	4	from	int
/*     */     //   129	50	5	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   10	79	129	finally
/*     */     //   129	131	129	finally
/*     */   }
/*     */   
/*     */   public <T extends ObjectPredicate<? super KType>> T forEach(T predicate)
/*     */   {
/* 735 */     return forEach(predicate, 0, size());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends ObjectPredicate<? super KType>> T forEach(T predicate, int fromIndex, int toIndex)
/*     */   {
/* 747 */     assert ((fromIndex >= 0) && (fromIndex <= size())) : ("Index " + fromIndex + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/*     */ 
/* 750 */     assert ((toIndex >= 0) && (toIndex <= size())) : ("Index " + toIndex + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 752 */     assert (fromIndex <= toIndex) : ("fromIndex must be <= toIndex: " + fromIndex + ", " + toIndex);
/*     */     
/*     */ 
/* 755 */     KType[] buffer = this.buffer;
/* 756 */     for (int i = fromIndex; i < toIndex; i++)
/*     */     {
/* 758 */       if (!predicate.apply(buffer[i])) {
/*     */         break;
/*     */       }
/*     */     }
/* 762 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayList<KType> newInstance()
/*     */   {
/* 772 */     return new ObjectArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayList<KType> newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 782 */     return new ObjectArrayList(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayList<KType> from(KType... elements)
/*     */   {
/* 792 */     ObjectArrayList<KType> list = new ObjectArrayList(elements.length);
/* 793 */     list.add(elements);
/* 794 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> ObjectArrayList<KType> from(ObjectContainer<KType> container)
/*     */   {
/* 803 */     return new ObjectArrayList(container);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */