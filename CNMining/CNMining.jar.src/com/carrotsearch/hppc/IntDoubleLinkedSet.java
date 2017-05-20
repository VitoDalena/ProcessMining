/*     */ package com.carrotsearch.hppc;
/*     */ 
/*     */ import com.carrotsearch.hppc.cursors.IntCursor;
/*     */ import com.carrotsearch.hppc.predicates.IntPredicate;
/*     */ import com.carrotsearch.hppc.procedures.IntProcedure;
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
/*     */ public class IntDoubleLinkedSet
/*     */   implements IntLookupContainer, IntSet, Cloneable
/*     */ {
/*     */   public static final int DEFAULT_CAPACITY = 5;
/*  29 */   public int[] dense = EmptyArrays.EMPTY_INT_ARRAY;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  34 */   public int[] sparse = EmptyArrays.EMPTY_INT_ARRAY;
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
/*     */   protected final ArraySizingStrategy resizer;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntDoubleLinkedSet()
/*     */   {
/*  55 */     this(5, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntDoubleLinkedSet(int denseCapacity, int sparseCapacity)
/*     */   {
/*  65 */     this(denseCapacity, sparseCapacity, new BoundedProportionalArraySizingStrategy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntDoubleLinkedSet(int denseCapacity, int sparseCapacity, ArraySizingStrategy resizer)
/*     */   {
/*  73 */     assert (denseCapacity >= 0) : ("denseCapacity must be >= 0: " + denseCapacity);
/*  74 */     assert (sparseCapacity >= 0) : ("sparseCapacity must be >= 0: " + sparseCapacity);
/*  75 */     assert (resizer != null);
/*     */     
/*  77 */     this.resizer = resizer;
/*  78 */     ensureDenseCapacity(resizer.round(denseCapacity));
/*  79 */     ensureSparseCapacity(sparseCapacity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntDoubleLinkedSet(IntContainer container)
/*     */   {
/*  87 */     this(container.size(), 1 + maxElement(container));
/*  88 */     for (IntCursor cursor : container)
/*     */     {
/*  90 */       addNoChecks(cursor.value);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void ensureDenseCapacity(int expectedAdditions)
/*     */   {
/* 100 */     int bufferLen = this.dense == null ? 0 : this.dense.length;
/* 101 */     int elementsCount = size();
/* 102 */     if (elementsCount > bufferLen - expectedAdditions)
/*     */     {
/* 104 */       int newSize = this.resizer.grow(bufferLen, elementsCount, expectedAdditions);
/* 105 */       assert (newSize >= elementsCount + expectedAdditions) : ("Resizer failed to return sensible new size: " + newSize + " <= " + (elementsCount + expectedAdditions));
/*     */       
/*     */ 
/*     */ 
/* 109 */       int[] newBuffer = new int[newSize];
/* 110 */       if (bufferLen > 0)
/*     */       {
/* 112 */         System.arraycopy(this.dense, 0, newBuffer, 0, elementsCount);
/*     */       }
/* 114 */       this.dense = newBuffer;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void ensureSparseCapacity(int value)
/*     */   {
/* 124 */     assert (value >= 0) : ("value must be >= 0: " + value);
/*     */     
/* 126 */     if (value >= this.sparse.length)
/*     */     {
/* 128 */       int[] newBuffer = new int[value + 1];
/* 129 */       if (this.sparse.length > 0)
/*     */       {
/* 131 */         System.arraycopy(this.sparse, 0, newBuffer, 0, this.sparse.length);
/*     */       }
/* 133 */       this.sparse = newBuffer;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   public int size()
/*     */   {
/* 140 */     return this.elementsCount;
/*     */   }
/*     */   
/*     */ 
/*     */   public int[] toArray()
/*     */   {
/* 146 */     int[] result = new int[size()];
/* 147 */     System.arraycopy(this.dense, 0, result, 0, size());
/* 148 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean isEmpty()
/*     */   {
/* 154 */     return size() == 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public void clear()
/*     */   {
/* 160 */     this.elementsCount = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   public boolean contains(int value)
/*     */   {
/*     */     int index;
/* 167 */     return (value >= 0) && (value < this.sparse.length) && ((index = this.sparse[value]) < this.elementsCount) && (this.dense[index] == value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean add(int value)
/*     */   {
/* 176 */     assert (value >= 0) : "Double linked set supports values >= 0 only.";
/*     */     
/* 178 */     boolean containsAlready = contains(value);
/* 179 */     if (!containsAlready)
/*     */     {
/*     */ 
/* 182 */       ensureDenseCapacity(1);
/* 183 */       ensureSparseCapacity(value);
/*     */       
/* 185 */       this.sparse[value] = this.elementsCount;
/* 186 */       this.dense[(this.elementsCount++)] = value;
/*     */     }
/* 188 */     return !containsAlready;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void addNoChecks(int value)
/*     */   {
/* 197 */     assert (value >= 0) : "Double linked set supports values >= 0 only.";
/*     */     
/* 199 */     boolean containsAlready = contains(value);
/* 200 */     if (!containsAlready)
/*     */     {
/* 202 */       assert (size() + 1 < this.dense.length) : "Dense array too small.";
/* 203 */       assert (value < this.sparse.length) : "Value too large for sparse.";
/*     */       
/* 205 */       this.sparse[value] = this.elementsCount;
/* 206 */       this.dense[(this.elementsCount++)] = value;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int add(int e1, int e2)
/*     */   {
/* 215 */     int count = 0;
/* 216 */     if (add(e1)) count++;
/* 217 */     if (add(e2)) count++;
/* 218 */     return count;
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
/*     */   public int add(int... elements)
/*     */   {
/* 231 */     int count = 0;
/* 232 */     for (int e : elements)
/* 233 */       if (add(e)) count++;
/* 234 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(IntContainer container)
/*     */   {
/* 245 */     return addAll(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int addAll(Iterable<? extends IntCursor> iterable)
/*     */   {
/* 256 */     int count = 0;
/* 257 */     for (IntCursor cursor : iterable)
/*     */     {
/* 259 */       if (add(cursor.value)) { count++;
/*     */       }
/*     */     }
/* 262 */     return count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int removeAllOccurrences(int value)
/*     */   {
/* 271 */     if ((value >= 0) && (value < this.sparse.length))
/*     */     {
/* 273 */       int slot = this.sparse[value];
/* 274 */       int n = this.elementsCount - 1;
/* 275 */       if ((slot <= n) && (this.dense[slot] == value))
/*     */       {
/*     */ 
/* 278 */         int lastValue = this.dense[n];
/* 279 */         this.elementsCount -= 1;
/* 280 */         this.dense[slot] = lastValue;
/* 281 */         this.sparse[lastValue] = slot;
/* 282 */         return 1;
/*     */       }
/*     */     }
/* 285 */     return 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean remove(int key)
/*     */   {
/* 293 */     return removeAllOccurrences(key) == 1;
/*     */   }
/*     */   
/*     */ 
/*     */   public Iterator<IntCursor> iterator()
/*     */   {
/* 299 */     return new IntArrayList.ValueIterator(this.dense, size());
/*     */   }
/*     */   
/*     */ 
/*     */   public <T extends IntProcedure> T forEach(T procedure)
/*     */   {
/* 305 */     int max = size();
/* 306 */     int[] dense = this.dense;
/* 307 */     for (int i = 0; i < max; i++)
/*     */     {
/* 309 */       procedure.apply(dense[i]);
/*     */     }
/*     */     
/* 312 */     return procedure;
/*     */   }
/*     */   
/*     */ 
/*     */   public <T extends IntPredicate> T forEach(T predicate)
/*     */   {
/* 318 */     int max = size();
/* 319 */     int[] dense = this.dense;
/* 320 */     for (int i = 0; i < max; i++)
/*     */     {
/* 322 */       if (predicate.apply(dense[i])) {
/*     */         break;
/*     */       }
/*     */     }
/* 326 */     return predicate;
/*     */   }
/*     */   
/*     */ 
/*     */   public int removeAll(IntLookupContainer c)
/*     */   {
/* 332 */     int max = size();int removed = 0;
/* 333 */     int[] dense = this.dense;
/* 334 */     for (int i = 0; i < max;)
/*     */     {
/* 336 */       if (c.contains(dense[i]))
/*     */       {
/* 338 */         int lastValue = dense[(--max)];
/* 339 */         dense[i] = lastValue;
/* 340 */         this.sparse[lastValue] = i;
/* 341 */         removed++;
/*     */       } else {
/* 343 */         i++;
/*     */       }
/*     */     }
/* 346 */     this.elementsCount = max;
/* 347 */     return removed;
/*     */   }
/*     */   
/*     */ 
/*     */   public int removeAll(IntPredicate predicate)
/*     */   {
/* 353 */     int max = size();int removed = 0;
/* 354 */     int[] dense = this.dense;
/* 355 */     for (int i = 0; i < max;)
/*     */     {
/* 357 */       if (predicate.apply(dense[i]))
/*     */       {
/* 359 */         int lastValue = dense[(--max)];
/* 360 */         dense[i] = lastValue;
/* 361 */         this.sparse[lastValue] = i;
/* 362 */         removed++;
/*     */       } else {
/* 364 */         i++;
/*     */       }
/*     */     }
/* 367 */     this.elementsCount = max;
/* 368 */     return removed;
/*     */   }
/*     */   
/*     */ 
/*     */   public int retainAll(IntLookupContainer c)
/*     */   {
/* 374 */     int max = size();int removed = 0;
/* 375 */     int[] dense = this.dense;
/* 376 */     for (int i = 0; i < max;)
/*     */     {
/* 378 */       if (!c.contains(dense[i]))
/*     */       {
/* 380 */         int lastValue = dense[(--max)];
/* 381 */         dense[i] = lastValue;
/* 382 */         this.sparse[lastValue] = i;
/* 383 */         removed++;
/*     */       } else {
/* 385 */         i++;
/*     */       }
/*     */     }
/* 388 */     this.elementsCount = max;
/* 389 */     return removed;
/*     */   }
/*     */   
/*     */ 
/*     */   public int retainAll(final IntPredicate predicate)
/*     */   {
/* 395 */     removeAll(new IntPredicate()
/*     */     {
/*     */       public boolean apply(int value)
/*     */       {
/* 399 */         return !predicate.apply(value);
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IntDoubleLinkedSet from(int... elements)
/*     */   {
/* 410 */     IntDoubleLinkedSet set = new IntDoubleLinkedSet(elements.length, 1 + maxElement(elements));
/*     */     
/* 412 */     for (int i : elements)
/* 413 */       set.addNoChecks(i);
/* 414 */     return set;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IntDoubleLinkedSet from(IntContainer container)
/*     */   {
/* 422 */     return new IntDoubleLinkedSet(container);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static IntDoubleLinkedSet newInstance()
/*     */   {
/* 430 */     return new IntDoubleLinkedSet();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int maxElement(IntContainer container)
/*     */   {
/* 438 */     int max = 0;
/* 439 */     for (IntCursor c : container)
/* 440 */       max = Math.max(max, c.value);
/* 441 */     return max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private static int maxElement(int... elements)
/*     */   {
/* 449 */     int max = 0;
/* 450 */     for (int c : elements)
/* 451 */       max = Math.max(max, c);
/* 452 */     return max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntDoubleLinkedSet clone()
/*     */   {
/*     */     try
/*     */     {
/* 463 */       IntDoubleLinkedSet cloned = (IntDoubleLinkedSet)super.clone();
/* 464 */       cloned.dense = ((int[])this.dense.clone());
/* 465 */       cloned.sparse = ((int[])this.sparse.clone());
/* 466 */       return cloned;
/*     */     }
/*     */     catch (CloneNotSupportedException e)
/*     */     {
/* 470 */       throw new RuntimeException(e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 480 */     return Arrays.toString(toArray());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntDoubleLinkedSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */