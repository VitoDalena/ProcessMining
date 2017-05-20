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
/*     */ public class KTypeArrayList<KType>
/*     */   extends AbstractKTypeCollection<KType>
/*     */   implements KTypeIndexedContainer<KType>, Cloneable
/*     */ {
/*     */   public static final int DEFAULT_CAPACITY = 5;
/*  61 */   private static final Object EMPTY = new Object[0];
/*     */   
/*     */ 
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
/*     */ 
/*     */   public KTypeArrayList()
/*     */   {
/* 112 */     this(5);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeArrayList(int initialCapacity)
/*     */   {
/* 122 */     this(initialCapacity, new BoundedProportionalArraySizingStrategy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeArrayList(int initialCapacity, ArraySizingStrategy resizer)
/*     */   {
/* 130 */     assert (initialCapacity >= 0) : ("initialCapacity must be >= 0: " + initialCapacity);
/* 131 */     assert (resizer != null);
/*     */     
/* 133 */     this.resizer = resizer;
/* 134 */     ensureBufferSpace(resizer.round(initialCapacity));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public KTypeArrayList(KTypeContainer<? extends KType> container)
/*     */   {
/* 142 */     this(container.size());
/* 143 */     addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType e1)
/*     */   {
/* 152 */     ensureBufferSpace(1);
/* 153 */     this.buffer[(this.elementsCount++)] = e1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType e1, KType e2)
/*     */   {
/* 163 */     ensureBufferSpace(2);
/* 164 */     this.buffer[(this.elementsCount++)] = e1;
/* 165 */     this.buffer[(this.elementsCount++)] = e2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType[] elements, int start, int length)
/*     */   {
/* 173 */     assert (length >= 0) : "Length must be >= 0";
/*     */     
/* 175 */     ensureBufferSpace(length);
/* 176 */     System.arraycopy(elements, start, this.buffer, this.elementsCount, length);
/* 177 */     this.elementsCount += length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(KType... elements)
/*     */   {
/* 187 */     add(elements, 0, elements.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(KTypeContainer<? extends KType> container)
/*     */   {
/* 195 */     int size = container.size();
/* 196 */     ensureBufferSpace(size);
/*     */     
/* 198 */     for (KTypeCursor<? extends KType> cursor : container)
/*     */     {
/* 200 */       add(cursor.value);
/*     */     }
/*     */     
/* 203 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(Iterable<? extends KTypeCursor<? extends KType>> iterable)
/*     */   {
/* 211 */     int size = 0;
/* 212 */     for (KTypeCursor<? extends KType> cursor : iterable)
/*     */     {
/* 214 */       add(cursor.value);
/* 215 */       size++;
/*     */     }
/* 217 */     return size;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void insert(int index, KType e1)
/*     */   {
/* 227 */     assert ((index >= 0) && (index <= size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 229 */     ensureBufferSpace(1);
/* 230 */     System.arraycopy(this.buffer, index, this.buffer, index + 1, this.elementsCount - index);
/* 231 */     this.buffer[index] = e1;
/* 232 */     this.elementsCount += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType get(int index)
/*     */   {
/* 242 */     assert ((index >= 0) && (index < size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/* 244 */     return (KType)this.buffer[index];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType set(int index, KType e1)
/*     */   {
/* 254 */     assert ((index >= 0) && (index < size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/* 256 */     KType v = this.buffer[index];
/* 257 */     this.buffer[index] = e1;
/* 258 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public KType remove(int index)
/*     */   {
/* 268 */     assert ((index >= 0) && (index < size())) : ("Index " + index + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/* 270 */     KType v = this.buffer[index];
/* 271 */     if (index + 1 < this.elementsCount)
/* 272 */       System.arraycopy(this.buffer, index + 1, this.buffer, index, this.elementsCount - index - 1);
/* 273 */     this.elementsCount -= 1;
/* 274 */     this.buffer[this.elementsCount] = Intrinsics.defaultKTypeValue();
/* 275 */     return v;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void removeRange(int fromIndex, int toIndex)
/*     */   {
/* 285 */     assert ((fromIndex >= 0) && (fromIndex <= size())) : ("Index " + fromIndex + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/*     */ 
/* 288 */     assert ((toIndex >= 0) && (toIndex <= size())) : ("Index " + toIndex + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 290 */     assert (fromIndex <= toIndex) : ("fromIndex must be <= toIndex: " + fromIndex + ", " + toIndex);
/*     */     
/*     */ 
/* 293 */     System.arraycopy(this.buffer, toIndex, this.buffer, fromIndex, this.elementsCount - toIndex);
/*     */     
/* 295 */     int count = toIndex - fromIndex;
/* 296 */     this.elementsCount -= count;
/* 297 */     Arrays.fill(this.buffer, this.elementsCount, this.elementsCount + count, Intrinsics.defaultKTypeValue());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeFirstOccurrence(KType e1)
/*     */   {
/* 307 */     int index = indexOf(e1);
/* 308 */     if (index >= 0) remove(index);
/* 309 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeLastOccurrence(KType e1)
/*     */   {
/* 318 */     int index = lastIndexOf(e1);
/* 319 */     if (index >= 0) remove(index);
/* 320 */     return index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(KType e1)
/*     */   {
/* 329 */     int to = 0;
/* 330 */     for (int from = 0; from < this.elementsCount; from++)
/*     */     {
/* 332 */       if (Intrinsics.equalsKType(e1, this.buffer[from]))
/*     */       {
/* 334 */         this.buffer[from] = Intrinsics.defaultKTypeValue();
/*     */       }
/*     */       else
/*     */       {
/* 338 */         if (to != from)
/*     */         {
/* 340 */           this.buffer[to] = this.buffer[from];
/* 341 */           this.buffer[from] = Intrinsics.defaultKTypeValue();
/*     */         }
/* 343 */         to++;
/*     */       }
/*     */     }
/* 346 */     int deleted = this.elementsCount - to;
/* 347 */     this.elementsCount = to;
/* 348 */     return deleted;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean contains(KType e1)
/*     */   {
/* 357 */     return indexOf(e1) >= 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int indexOf(KType e1)
/*     */   {
/* 366 */     for (int i = 0; i < this.elementsCount; i++) {
/* 367 */       if (Intrinsics.equalsKType(e1, this.buffer[i]))
/* 368 */         return i;
/*     */     }
/* 370 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int lastIndexOf(KType e1)
/*     */   {
/* 379 */     for (int i = this.elementsCount - 1; i >= 0; i--) {
/* 380 */       if (Intrinsics.equalsKType(e1, this.buffer[i]))
/* 381 */         return i;
/*     */     }
/* 383 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 392 */     return this.elementsCount == 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int minCapacity)
/*     */   {
/* 402 */     if (minCapacity > this.buffer.length) {
/* 403 */       ensureBufferSpace(minCapacity - size());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void ensureBufferSpace(int expectedAdditions)
/*     */   {
/* 412 */     int bufferLen = this.buffer == null ? 0 : this.buffer.length;
/* 413 */     if (this.elementsCount >= bufferLen - expectedAdditions)
/*     */     {
/* 415 */       int newSize = this.resizer.grow(bufferLen, this.elementsCount, expectedAdditions);
/* 416 */       assert (newSize >= this.elementsCount + expectedAdditions) : ("Resizer failed to return sensible new size: " + newSize + " <= " + (this.elementsCount + expectedAdditions));
/*     */       
/*     */ 
/*     */ 
/* 420 */       KType[] newBuffer = (Object[])Intrinsics.newKTypeArray(newSize);
/* 421 */       if (bufferLen > 0)
/*     */       {
/* 423 */         System.arraycopy(this.buffer, 0, newBuffer, 0, this.buffer.length);
/*     */       }
/* 425 */       this.buffer = newBuffer;
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
/* 438 */     if (newSize <= this.buffer.length)
/*     */     {
/* 440 */       if (newSize < this.elementsCount)
/*     */       {
/* 442 */         Arrays.fill(this.buffer, newSize, this.elementsCount, Intrinsics.defaultKTypeValue());
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 447 */         Arrays.fill(this.buffer, this.elementsCount, newSize, Intrinsics.defaultKTypeValue());
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else {
/* 453 */       ensureCapacity(newSize);
/*     */     }
/* 455 */     this.elementsCount = newSize;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 464 */     return this.elementsCount;
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
/* 475 */     if (size() != this.buffer.length) {
/* 476 */       this.buffer = ((Object[])toArray());
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
/* 487 */     Arrays.fill(this.buffer, 0, this.elementsCount, Intrinsics.defaultKTypeValue());
/* 488 */     this.elementsCount = 0;
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
/* 499 */     this.buffer = ((Object[])EMPTY);
/* 500 */     this.elementsCount = 0;
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
/*     */   public Object[] toArray()
/*     */   {
/* 516 */     return Arrays.copyOf(this.buffer, this.elementsCount);
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
/*     */   public KTypeArrayList<KType> clone()
/*     */   {
/*     */     try
/*     */     {
/* 531 */       KTypeArrayList<KType> cloned = (KTypeArrayList)super.clone();
/* 532 */       cloned.buffer = ((Object[])this.buffer.clone());
/* 533 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 537 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 547 */     int h = 1;int max = this.elementsCount;
/* 548 */     for (int i = 0; i < max; i++)
/*     */     {
/* 550 */       h = 31 * h + Internals.rehash(this.buffer[i]);
/*     */     }
/* 552 */     return h;
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
/* 564 */     if (obj != null)
/*     */     {
/* 566 */       if ((obj instanceof KTypeArrayList))
/*     */       {
/* 568 */         KTypeArrayList<?> other = (KTypeArrayList)obj;
/* 569 */         return (other.size() == size()) && (rangeEquals(other.buffer, this.buffer, size()));
/*     */       }
/*     */       
/* 572 */       if ((obj instanceof KTypeIndexedContainer))
/*     */       {
/* 574 */         KTypeIndexedContainer<?> other = (KTypeIndexedContainer)obj;
/* 575 */         return (other.size() == size()) && (allIndexesEqual(this, other, size()));
/*     */       }
/*     */     }
/*     */     
/* 579 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean rangeEquals(Object[] b1, Object[] b2, int length)
/*     */   {
/* 591 */     for (int i = 0; i < length; i++)
/*     */     {
/* 593 */       if (!Intrinsics.equalsKType(b1[i], b2[i]))
/*     */       {
/* 595 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 599 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean allIndexesEqual(KTypeIndexedContainer<KType> b1, KTypeIndexedContainer<KType> b2, int length)
/*     */   {
/* 609 */     for (int i = 0; i < length; i++)
/*     */     {
/* 611 */       KType o1 = b1.get(i);
/* 612 */       KType o2 = b2.get(i);
/*     */       
/* 614 */       if (!Intrinsics.equalsKType(o1, o2))
/*     */       {
/* 616 */         return false;
/*     */       }
/*     */     }
/*     */     
/* 620 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */   static final class ValueIterator<KType>
/*     */     extends AbstractIterator<KTypeCursor<KType>>
/*     */   {
/*     */     private final KTypeCursor<KType> cursor;
/*     */     
/*     */     private final KType[] buffer;
/*     */     
/*     */     private final int size;
/*     */     
/*     */     public ValueIterator(KType[] buffer, int size)
/*     */     {
/* 635 */       this.cursor = new KTypeCursor();
/* 636 */       this.cursor.index = -1;
/* 637 */       this.size = size;
/* 638 */       this.buffer = buffer;
/*     */     }
/*     */     
/*     */ 
/*     */     protected KTypeCursor<KType> fetch()
/*     */     {
/* 644 */       if (this.cursor.index + 1 == this.size) {
/* 645 */         return (KTypeCursor)done();
/*     */       }
/* 647 */       this.cursor.value = this.buffer[(++this.cursor.index)];
/* 648 */       return this.cursor;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Iterator<KTypeCursor<KType>> iterator()
/*     */   {
/* 658 */     return new ValueIterator(this.buffer, size());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypeProcedure<? super KType>> T forEach(T procedure)
/*     */   {
/* 667 */     return forEach(procedure, 0, size());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypeProcedure<? super KType>> T forEach(T procedure, int fromIndex, int toIndex)
/*     */   {
/* 679 */     assert ((fromIndex >= 0) && (fromIndex <= size())) : ("Index " + fromIndex + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/*     */ 
/* 682 */     assert ((toIndex >= 0) && (toIndex <= size())) : ("Index " + toIndex + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 684 */     assert (fromIndex <= toIndex) : ("fromIndex must be <= toIndex: " + fromIndex + ", " + toIndex);
/*     */     
/*     */ 
/* 687 */     KType[] buffer = this.buffer;
/* 688 */     for (int i = fromIndex; i < toIndex; i++)
/*     */     {
/* 690 */       procedure.apply(buffer[i]);
/*     */     }
/*     */     
/* 693 */     return procedure;
/*     */   }
/*     */   
/*     */   /* Error */
/*     */   public int removeAll(KTypePredicate<? super KType> predicate)
/*     */   {
/*     */     // Byte code:
/*     */     //   0: aload_0
/*     */     //   1: getfield 22	com/carrotsearch/hppc/KTypeArrayList:elementsCount	I
/*     */     //   4: istore_2
/*     */     //   5: iconst_0
/*     */     //   6: istore_3
/*     */     //   7: iconst_0
/*     */     //   8: istore 4
/*     */     //   10: iload 4
/*     */     //   12: iload_2
/*     */     //   13: if_icmpge +70 -> 83
/*     */     //   16: aload_1
/*     */     //   17: aload_0
/*     */     //   18: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   21: iload 4
/*     */     //   23: aaload
/*     */     //   24: invokeinterface 71 2 0
/*     */     //   29: ifeq +16 -> 45
/*     */     //   32: aload_0
/*     */     //   33: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   36: iload 4
/*     */     //   38: invokestatic 39	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   41: aastore
/*     */     //   42: goto +35 -> 77
/*     */     //   45: iload_3
/*     */     //   46: iload 4
/*     */     //   48: if_icmpeq +26 -> 74
/*     */     //   51: aload_0
/*     */     //   52: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   55: iload_3
/*     */     //   56: aload_0
/*     */     //   57: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   60: iload 4
/*     */     //   62: aaload
/*     */     //   63: aastore
/*     */     //   64: aload_0
/*     */     //   65: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   68: iload 4
/*     */     //   70: invokestatic 39	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   73: aastore
/*     */     //   74: iinc 3 1
/*     */     //   77: iinc 4 1
/*     */     //   80: goto -70 -> 10
/*     */     //   83: iload 4
/*     */     //   85: iload_2
/*     */     //   86: if_icmpge +41 -> 127
/*     */     //   89: iload_3
/*     */     //   90: iload 4
/*     */     //   92: if_icmpeq +26 -> 118
/*     */     //   95: aload_0
/*     */     //   96: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   99: iload_3
/*     */     //   100: aload_0
/*     */     //   101: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   104: iload 4
/*     */     //   106: aaload
/*     */     //   107: aastore
/*     */     //   108: aload_0
/*     */     //   109: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   112: iload 4
/*     */     //   114: invokestatic 39	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   117: aastore
/*     */     //   118: iinc 3 1
/*     */     //   121: iinc 4 1
/*     */     //   124: goto -41 -> 83
/*     */     //   127: aload_0
/*     */     //   128: iload_3
/*     */     //   129: putfield 22	com/carrotsearch/hppc/KTypeArrayList:elementsCount	I
/*     */     //   132: goto +57 -> 189
/*     */     //   135: astore 5
/*     */     //   137: iload 4
/*     */     //   139: iload_2
/*     */     //   140: if_icmpge +41 -> 181
/*     */     //   143: iload_3
/*     */     //   144: iload 4
/*     */     //   146: if_icmpeq +26 -> 172
/*     */     //   149: aload_0
/*     */     //   150: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   153: iload_3
/*     */     //   154: aload_0
/*     */     //   155: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   158: iload 4
/*     */     //   160: aaload
/*     */     //   161: aastore
/*     */     //   162: aload_0
/*     */     //   163: getfield 21	com/carrotsearch/hppc/KTypeArrayList:buffer	[Ljava/lang/Object;
/*     */     //   166: iload 4
/*     */     //   168: invokestatic 39	com/carrotsearch/hppc/Intrinsics:defaultKTypeValue	()Ljava/lang/Object;
/*     */     //   171: aastore
/*     */     //   172: iinc 3 1
/*     */     //   175: iinc 4 1
/*     */     //   178: goto -41 -> 137
/*     */     //   181: aload_0
/*     */     //   182: iload_3
/*     */     //   183: putfield 22	com/carrotsearch/hppc/KTypeArrayList:elementsCount	I
/*     */     //   186: aload 5
/*     */     //   188: athrow
/*     */     //   189: iload_2
/*     */     //   190: iload_3
/*     */     //   191: isub
/*     */     //   192: ireturn
/*     */     // Line number table:
/*     */     //   Java source line #702	-> byte code offset #0
/*     */     //   Java source line #703	-> byte code offset #5
/*     */     //   Java source line #704	-> byte code offset #7
/*     */     //   Java source line #707	-> byte code offset #10
/*     */     //   Java source line #709	-> byte code offset #16
/*     */     //   Java source line #711	-> byte code offset #32
/*     */     //   Java source line #712	-> byte code offset #42
/*     */     //   Java source line #715	-> byte code offset #45
/*     */     //   Java source line #717	-> byte code offset #51
/*     */     //   Java source line #718	-> byte code offset #64
/*     */     //   Java source line #720	-> byte code offset #74
/*     */     //   Java source line #707	-> byte code offset #77
/*     */     //   Java source line #726	-> byte code offset #83
/*     */     //   Java source line #728	-> byte code offset #89
/*     */     //   Java source line #730	-> byte code offset #95
/*     */     //   Java source line #731	-> byte code offset #108
/*     */     //   Java source line #733	-> byte code offset #118
/*     */     //   Java source line #726	-> byte code offset #121
/*     */     //   Java source line #736	-> byte code offset #127
/*     */     //   Java source line #737	-> byte code offset #132
/*     */     //   Java source line #726	-> byte code offset #135
/*     */     //   Java source line #728	-> byte code offset #143
/*     */     //   Java source line #730	-> byte code offset #149
/*     */     //   Java source line #731	-> byte code offset #162
/*     */     //   Java source line #733	-> byte code offset #172
/*     */     //   Java source line #726	-> byte code offset #175
/*     */     //   Java source line #736	-> byte code offset #181
/*     */     //   Java source line #739	-> byte code offset #189
/*     */     // Local variable table:
/*     */     //   start	length	slot	name	signature
/*     */     //   0	193	0	this	KTypeArrayList<KType>
/*     */     //   0	193	1	predicate	KTypePredicate<? super KType>
/*     */     //   4	186	2	elementsCount	int
/*     */     //   6	185	3	to	int
/*     */     //   8	168	4	from	int
/*     */     //   135	52	5	localObject	Object
/*     */     // Exception table:
/*     */     //   from	to	target	type
/*     */     //   10	83	135	finally
/*     */     //   135	137	135	finally
/*     */   }
/*     */   
/*     */   public <T extends KTypePredicate<? super KType>> T forEach(T predicate)
/*     */   {
/* 748 */     return forEach(predicate, 0, size());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public <T extends KTypePredicate<? super KType>> T forEach(T predicate, int fromIndex, int toIndex)
/*     */   {
/* 760 */     assert ((fromIndex >= 0) && (fromIndex <= size())) : ("Index " + fromIndex + " out of bounds [" + 0 + ", " + size() + ").");
/*     */     
/*     */ 
/* 763 */     assert ((toIndex >= 0) && (toIndex <= size())) : ("Index " + toIndex + " out of bounds [" + 0 + ", " + size() + "].");
/*     */     
/* 765 */     assert (fromIndex <= toIndex) : ("fromIndex must be <= toIndex: " + fromIndex + ", " + toIndex);
/*     */     
/*     */ 
/* 768 */     KType[] buffer = this.buffer;
/* 769 */     for (int i = fromIndex; i < toIndex; i++)
/*     */     {
/* 771 */       if (!predicate.apply(buffer[i])) {
/*     */         break;
/*     */       }
/*     */     }
/* 775 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayList<KType> newInstance()
/*     */   {
/* 785 */     return new KTypeArrayList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayList<KType> newInstanceWithCapacity(int initialCapacity)
/*     */   {
/* 795 */     return new KTypeArrayList(initialCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayList<KType> from(KType... elements)
/*     */   {
/* 805 */     KTypeArrayList<KType> list = new KTypeArrayList(elements.length);
/* 806 */     list.add(elements);
/* 807 */     return list;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static <KType> KTypeArrayList<KType> from(KTypeContainer<KType> container)
/*     */   {
/* 816 */     return new KTypeArrayList(container);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */