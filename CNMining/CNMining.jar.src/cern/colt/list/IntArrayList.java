/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.IntProcedure;
/*     */ import cern.jet.math.Arithmetic;
/*     */ import cern.jet.random.Uniform;
/*     */ import cern.jet.random.engine.DRand;
/*     */ import java.util.Date;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntArrayList
/*     */   extends AbstractIntList
/*     */ {
/*     */   protected int[] elements;
/*     */   
/*     */   public IntArrayList()
/*     */   {
/*  27 */     this(10);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntArrayList(int[] paramArrayOfInt)
/*     */   {
/*  39 */     elements(paramArrayOfInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntArrayList(int paramInt)
/*     */   {
/*  47 */     this(new int[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(int paramInt)
/*     */   {
/*  57 */     if (this.size == this.elements.length) {
/*  58 */       ensureCapacity(this.size + 1);
/*     */     }
/*  60 */     this.elements[(this.size++)] = paramInt;
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
/*     */   public void beforeInsert(int paramInt1, int paramInt2)
/*     */   {
/*  73 */     if (this.size == paramInt1) {
/*  74 */       add(paramInt2);
/*  75 */       return;
/*     */     }
/*  77 */     if ((paramInt1 > this.size) || (paramInt1 < 0))
/*  78 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/*  79 */     ensureCapacity(this.size + 1);
/*  80 */     System.arraycopy(this.elements, paramInt1, this.elements, paramInt1 + 1, this.size - paramInt1);
/*  81 */     this.elements[paramInt1] = paramInt2;
/*  82 */     this.size += 1;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int binarySearchFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 108 */     return Sorting.binarySearchFromTo(this.elements, paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 117 */     IntArrayList localIntArrayList = new IntArrayList((int[])this.elements.clone());
/* 118 */     localIntArrayList.setSizeRaw(this.size);
/* 119 */     return localIntArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public IntArrayList copy()
/*     */   {
/* 127 */     return (IntArrayList)clone();
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
/*     */   protected void countSortFromTo(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 144 */     if (this.size == 0) return;
/* 145 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 147 */     int i = paramInt4 - paramInt3 + 1;
/*     */     
/* 149 */     int[] arrayOfInt1 = new int[i];
/* 150 */     int[] arrayOfInt2 = this.elements;
/* 151 */     for (int j = paramInt1; j <= paramInt2; arrayOfInt1[(arrayOfInt2[(j++)] - paramInt3)] += 1) {}
/*     */     
/* 153 */     int k = paramInt1;
/* 154 */     int m = paramInt3;
/* 155 */     for (int n = 0; n < i; m++) {
/* 156 */       int i1 = arrayOfInt1[n];
/* 157 */       if (i1 > 0) {
/* 158 */         if (i1 == 1) { arrayOfInt2[(k++)] = m;
/*     */         } else {
/* 160 */           int i2 = k + i1 - 1;
/* 161 */           fillFromToWith(k, i2, m);
/* 162 */           k = i2 + 1;
/*     */         }
/*     */       }
/* 155 */       n++;
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
/*     */   public int[] elements()
/*     */   {
/* 176 */     return this.elements;
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
/*     */   public AbstractIntList elements(int[] paramArrayOfInt)
/*     */   {
/* 189 */     this.elements = paramArrayOfInt;
/* 190 */     this.size = paramArrayOfInt.length;
/* 191 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 200 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 214 */     if (!(paramObject instanceof IntArrayList)) return super.equals(paramObject);
/* 215 */     if (this == paramObject) return true;
/* 216 */     if (paramObject == null) return false;
/* 217 */     IntArrayList localIntArrayList = (IntArrayList)paramObject;
/* 218 */     if (size() != localIntArrayList.size()) { return false;
/*     */     }
/* 220 */     int[] arrayOfInt1 = elements();
/* 221 */     int[] arrayOfInt2 = localIntArrayList.elements();
/* 222 */     int i = size();
/* 223 */     do { if (arrayOfInt1[i] != arrayOfInt2[i]) return false;
/* 222 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 225 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(IntProcedure paramIntProcedure)
/*     */   {
/* 235 */     int[] arrayOfInt = this.elements;
/* 236 */     int i = this.size;
/*     */     
/* 238 */     for (int j = 0; j < i;) if (!paramIntProcedure.apply(arrayOfInt[(j++)])) return false;
/* 239 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int get(int paramInt)
/*     */   {
/* 250 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 251 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 252 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getQuick(int paramInt)
/*     */   {
/* 263 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 279 */     if (this.size == 0) return -1;
/* 280 */     AbstractList.checkRangeFromTo(paramInt2, paramInt3, this.size);
/*     */     
/* 282 */     int[] arrayOfInt = this.elements;
/* 283 */     for (int i = paramInt2; i <= paramInt3; i++) {
/* 284 */       if (paramInt1 == arrayOfInt[i]) return i;
/*     */     }
/* 286 */     return -1;
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
/*     */   public int lastIndexOfFromTo(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 302 */     if (this.size == 0) return -1;
/* 303 */     AbstractList.checkRangeFromTo(paramInt2, paramInt3, this.size);
/*     */     
/* 305 */     int[] arrayOfInt = this.elements;
/* 306 */     for (int i = paramInt3; i >= paramInt2; i--) {
/* 307 */       if (paramInt1 == arrayOfInt[i]) return i;
/*     */     }
/* 309 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractIntList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 319 */     if (this.size == 0) { return new IntArrayList(0);
/*     */     }
/* 321 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 323 */     int[] arrayOfInt = new int[paramInt2 - paramInt1 + 1];
/* 324 */     System.arraycopy(this.elements, paramInt1, arrayOfInt, 0, paramInt2 - paramInt1 + 1);
/* 325 */     return new IntArrayList(arrayOfInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractIntList paramAbstractIntList)
/*     */   {
/* 336 */     if (!(paramAbstractIntList instanceof IntArrayList)) { return super.removeAll(paramAbstractIntList);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 348 */     if (paramAbstractIntList.size() == 0) return false;
/* 349 */     int i = paramAbstractIntList.size() - 1;
/* 350 */     int j = 0;
/* 351 */     int[] arrayOfInt = this.elements;
/* 352 */     int k = size();
/*     */     
/* 354 */     double d1 = paramAbstractIntList.size();
/* 355 */     double d2 = k;
/* 356 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 358 */       IntArrayList localIntArrayList = (IntArrayList)paramAbstractIntList.clone();
/* 359 */       localIntArrayList.quickSort();
/*     */       
/* 361 */       for (int n = 0; n < k; n++) {
/* 362 */         if (localIntArrayList.binarySearchFromTo(arrayOfInt[n], 0, i) < 0) arrayOfInt[(j++)] = arrayOfInt[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 367 */       for (m = 0; m < k; m++) {
/* 368 */         if (paramAbstractIntList.indexOfFromTo(arrayOfInt[m], 0, i) < 0) { arrayOfInt[(j++)] = arrayOfInt[m];
/*     */         }
/*     */       }
/*     */     }
/* 372 */     int m = j != k ? 1 : 0;
/* 373 */     setSize(j);
/* 374 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractIntList paramAbstractIntList, int paramInt3)
/*     */   {
/* 388 */     if (!(paramAbstractIntList instanceof IntArrayList))
/*     */     {
/* 390 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractIntList, paramInt3);
/* 391 */       return;
/*     */     }
/* 393 */     int i = paramInt2 - paramInt1 + 1;
/* 394 */     if (i > 0) {
/* 395 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 396 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractIntList.size());
/* 397 */       System.arraycopy(((IntArrayList)paramAbstractIntList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractIntList paramAbstractIntList)
/*     */   {
/* 409 */     if (!(paramAbstractIntList instanceof IntArrayList)) { return super.retainAll(paramAbstractIntList);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 421 */     int i = paramAbstractIntList.size() - 1;
/* 422 */     int j = 0;
/* 423 */     int[] arrayOfInt = this.elements;
/* 424 */     int k = size();
/*     */     
/* 426 */     double d1 = paramAbstractIntList.size();
/* 427 */     double d2 = k;
/* 428 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 430 */       IntArrayList localIntArrayList = (IntArrayList)paramAbstractIntList.clone();
/* 431 */       localIntArrayList.quickSort();
/*     */       
/* 433 */       for (int n = 0; n < k; n++) {
/* 434 */         if (localIntArrayList.binarySearchFromTo(arrayOfInt[n], 0, i) >= 0) arrayOfInt[(j++)] = arrayOfInt[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 439 */       for (m = 0; m < k; m++) {
/* 440 */         if (paramAbstractIntList.indexOfFromTo(arrayOfInt[m], 0, i) >= 0) { arrayOfInt[(j++)] = arrayOfInt[m];
/*     */         }
/*     */       }
/*     */     }
/* 444 */     int m = j != k ? 1 : 0;
/* 445 */     setSize(j);
/* 446 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 455 */     int j = this.size / 2;
/* 456 */     int k = this.size - 1;
/*     */     
/* 458 */     int[] arrayOfInt = this.elements;
/* 459 */     for (int m = 0; m < j;) {
/* 460 */       int i = arrayOfInt[m];
/* 461 */       arrayOfInt[(m++)] = arrayOfInt[k];
/* 462 */       arrayOfInt[(k--)] = i;
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
/*     */   public void set(int paramInt1, int paramInt2)
/*     */   {
/* 475 */     if ((paramInt1 >= this.size) || (paramInt1 < 0))
/* 476 */       throw new IndexOutOfBoundsException("Index: " + paramInt1 + ", Size: " + this.size);
/* 477 */     this.elements[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt1, int paramInt2)
/*     */   {
/* 489 */     this.elements[paramInt1] = paramInt2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 499 */     if (this.size == 0) return;
/* 500 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 502 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 504 */     int[] arrayOfInt = this.elements;
/*     */     
/* 506 */     for (int k = paramInt1; k < paramInt2; k++) {
/* 507 */       int j = localUniform.nextIntFromTo(k, paramInt2);
/*     */       
/*     */ 
/* 510 */       int i = arrayOfInt[j];
/* 511 */       arrayOfInt[j] = arrayOfInt[k];
/* 512 */       arrayOfInt[k] = i;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void sortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 538 */     if (this.size == 0) return;
/* 539 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/*     */ 
/* 542 */     int i = this.elements[paramInt1];
/* 543 */     int j = this.elements[paramInt1];
/*     */     
/* 545 */     int[] arrayOfInt = this.elements;
/* 546 */     for (int k = paramInt1 + 1; k <= paramInt2;) {
/* 547 */       int m = arrayOfInt[(k++)];
/* 548 */       if (m > j) { j = m;
/* 549 */       } else if (m < i) { i = m;
/*     */       }
/*     */     }
/*     */     
/* 553 */     double d1 = paramInt2 - paramInt1 + 1.0D;
/* 554 */     double d2 = d1 * Math.log(d1) / 0.6931471805599453D;
/*     */     
/* 556 */     double d3 = j - i + 1.0D;
/* 557 */     double d4 = Math.max(d3, d1);
/*     */     
/* 559 */     if ((d3 < 10000.0D) && (d4 < d2)) {
/* 560 */       countSortFromTo(paramInt1, paramInt2, i, j);
/*     */     }
/*     */     else {
/* 563 */       quickSortFromTo(paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 572 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/IntArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */