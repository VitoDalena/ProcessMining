/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.LongProcedure;
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
/*     */ public class LongArrayList
/*     */   extends AbstractLongList
/*     */ {
/*     */   protected long[] elements;
/*     */   
/*     */   public LongArrayList()
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
/*     */   public LongArrayList(long[] paramArrayOfLong)
/*     */   {
/*  39 */     elements(paramArrayOfLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongArrayList(int paramInt)
/*     */   {
/*  47 */     this(new long[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(long paramLong)
/*     */   {
/*  57 */     if (this.size == this.elements.length) ensureCapacity(this.size + 1);
/*  58 */     this.elements[(this.size++)] = paramLong;
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
/*     */   public void beforeInsert(int paramInt, long paramLong)
/*     */   {
/*  71 */     if ((paramInt > this.size) || (paramInt < 0))
/*  72 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  73 */     ensureCapacity(this.size + 1);
/*  74 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  75 */     this.elements[paramInt] = paramLong;
/*  76 */     this.size += 1;
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
/*     */   public int binarySearchFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 102 */     return Sorting.binarySearchFromTo(this.elements, paramLong, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 111 */     LongArrayList localLongArrayList = new LongArrayList((long[])this.elements.clone());
/* 112 */     localLongArrayList.setSizeRaw(this.size);
/* 113 */     return localLongArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public LongArrayList copy()
/*     */   {
/* 121 */     return (LongArrayList)clone();
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
/*     */   protected void countSortFromTo(int paramInt1, int paramInt2, long paramLong1, long paramLong2)
/*     */   {
/* 138 */     if (this.size == 0) return;
/* 139 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 141 */     int i = (int)(paramLong2 - paramLong1 + 1L);
/*     */     
/* 143 */     int[] arrayOfInt = new int[i];
/* 144 */     long[] arrayOfLong = this.elements;
/* 145 */     for (int j = paramInt1; j <= paramInt2; arrayOfInt[((int)(arrayOfLong[(j++)] - paramLong1))] += 1) {}
/*     */     
/* 147 */     int k = paramInt1;
/* 148 */     long l = paramLong1;
/* 149 */     for (int m = 0; m < i; l += 1L) {
/* 150 */       int n = arrayOfInt[m];
/* 151 */       if (n > 0) {
/* 152 */         if (n == 1) { arrayOfLong[(k++)] = l;
/*     */         } else {
/* 154 */           int i1 = k + n - 1;
/* 155 */           fillFromToWith(k, i1, l);
/* 156 */           k = i1 + 1;
/*     */         }
/*     */       }
/* 149 */       m++;
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
/*     */   public long[] elements()
/*     */   {
/* 170 */     return this.elements;
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
/*     */   public AbstractLongList elements(long[] paramArrayOfLong)
/*     */   {
/* 183 */     this.elements = paramArrayOfLong;
/* 184 */     this.size = paramArrayOfLong.length;
/* 185 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 194 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/* 208 */     if (!(paramObject instanceof LongArrayList)) return super.equals(paramObject);
/* 209 */     if (this == paramObject) return true;
/* 210 */     if (paramObject == null) return false;
/* 211 */     LongArrayList localLongArrayList = (LongArrayList)paramObject;
/* 212 */     if (size() != localLongArrayList.size()) { return false;
/*     */     }
/* 214 */     long[] arrayOfLong1 = elements();
/* 215 */     long[] arrayOfLong2 = localLongArrayList.elements();
/* 216 */     int i = size();
/* 217 */     do { if (arrayOfLong1[i] != arrayOfLong2[i]) return false;
/* 216 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 219 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(LongProcedure paramLongProcedure)
/*     */   {
/* 229 */     long[] arrayOfLong = this.elements;
/* 230 */     int i = this.size;
/*     */     
/* 232 */     for (int j = 0; j < i;) if (!paramLongProcedure.apply(arrayOfLong[(j++)])) return false;
/* 233 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long get(int paramInt)
/*     */   {
/* 244 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 245 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 246 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getQuick(int paramInt)
/*     */   {
/* 257 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 273 */     if (this.size == 0) return -1;
/* 274 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 276 */     long[] arrayOfLong = this.elements;
/* 277 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 278 */       if (paramLong == arrayOfLong[i]) return i;
/*     */     }
/* 280 */     return -1;
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
/*     */   public int lastIndexOfFromTo(long paramLong, int paramInt1, int paramInt2)
/*     */   {
/* 296 */     if (this.size == 0) return -1;
/* 297 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 299 */     long[] arrayOfLong = this.elements;
/* 300 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 301 */       if (paramLong == arrayOfLong[i]) return i;
/*     */     }
/* 303 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractLongList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 313 */     if (this.size == 0) { return new LongArrayList(0);
/*     */     }
/* 315 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 317 */     long[] arrayOfLong = new long[paramInt2 - paramInt1 + 1];
/* 318 */     System.arraycopy(this.elements, paramInt1, arrayOfLong, 0, paramInt2 - paramInt1 + 1);
/* 319 */     return new LongArrayList(arrayOfLong);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractLongList paramAbstractLongList)
/*     */   {
/* 330 */     if (!(paramAbstractLongList instanceof LongArrayList)) { return super.removeAll(paramAbstractLongList);
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
/* 342 */     if (paramAbstractLongList.size() == 0) return false;
/* 343 */     int i = paramAbstractLongList.size() - 1;
/* 344 */     int j = 0;
/* 345 */     long[] arrayOfLong = this.elements;
/* 346 */     int k = size();
/*     */     
/* 348 */     double d1 = paramAbstractLongList.size();
/* 349 */     double d2 = k;
/* 350 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 352 */       LongArrayList localLongArrayList = (LongArrayList)paramAbstractLongList.clone();
/* 353 */       localLongArrayList.quickSort();
/*     */       
/* 355 */       for (int n = 0; n < k; n++) {
/* 356 */         if (localLongArrayList.binarySearchFromTo(arrayOfLong[n], 0, i) < 0) arrayOfLong[(j++)] = arrayOfLong[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 361 */       for (m = 0; m < k; m++) {
/* 362 */         if (paramAbstractLongList.indexOfFromTo(arrayOfLong[m], 0, i) < 0) { arrayOfLong[(j++)] = arrayOfLong[m];
/*     */         }
/*     */       }
/*     */     }
/* 366 */     int m = j != k ? 1 : 0;
/* 367 */     setSize(j);
/* 368 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractLongList paramAbstractLongList, int paramInt3)
/*     */   {
/* 382 */     if (!(paramAbstractLongList instanceof LongArrayList))
/*     */     {
/* 384 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractLongList, paramInt3);
/* 385 */       return;
/*     */     }
/* 387 */     int i = paramInt2 - paramInt1 + 1;
/* 388 */     if (i > 0) {
/* 389 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 390 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractLongList.size());
/* 391 */       System.arraycopy(((LongArrayList)paramAbstractLongList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractLongList paramAbstractLongList)
/*     */   {
/* 403 */     if (!(paramAbstractLongList instanceof LongArrayList)) { return super.retainAll(paramAbstractLongList);
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
/* 415 */     int i = paramAbstractLongList.size() - 1;
/* 416 */     int j = 0;
/* 417 */     long[] arrayOfLong = this.elements;
/* 418 */     int k = size();
/*     */     
/* 420 */     double d1 = paramAbstractLongList.size();
/* 421 */     double d2 = k;
/* 422 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 424 */       LongArrayList localLongArrayList = (LongArrayList)paramAbstractLongList.clone();
/* 425 */       localLongArrayList.quickSort();
/*     */       
/* 427 */       for (int n = 0; n < k; n++) {
/* 428 */         if (localLongArrayList.binarySearchFromTo(arrayOfLong[n], 0, i) >= 0) arrayOfLong[(j++)] = arrayOfLong[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 433 */       for (m = 0; m < k; m++) {
/* 434 */         if (paramAbstractLongList.indexOfFromTo(arrayOfLong[m], 0, i) >= 0) { arrayOfLong[(j++)] = arrayOfLong[m];
/*     */         }
/*     */       }
/*     */     }
/* 438 */     int m = j != k ? 1 : 0;
/* 439 */     setSize(j);
/* 440 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 449 */     int i = this.size / 2;
/* 450 */     int j = this.size - 1;
/*     */     
/* 452 */     long[] arrayOfLong = this.elements;
/* 453 */     for (int k = 0; k < i;) {
/* 454 */       long l = arrayOfLong[k];
/* 455 */       arrayOfLong[(k++)] = arrayOfLong[j];
/* 456 */       arrayOfLong[(j--)] = l;
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
/*     */   public void set(int paramInt, long paramLong)
/*     */   {
/* 469 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 470 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 471 */     this.elements[paramInt] = paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, long paramLong)
/*     */   {
/* 483 */     this.elements[paramInt] = paramLong;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 493 */     if (this.size == 0) return;
/* 494 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 496 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 498 */     long[] arrayOfLong = this.elements;
/*     */     
/* 500 */     for (int j = paramInt1; j < paramInt2; j++) {
/* 501 */       int i = localUniform.nextIntFromTo(j, paramInt2);
/*     */       
/*     */ 
/* 504 */       long l = arrayOfLong[i];
/* 505 */       arrayOfLong[i] = arrayOfLong[j];
/* 506 */       arrayOfLong[j] = l;
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
/* 532 */     if (this.size == 0) return;
/* 533 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/*     */ 
/* 536 */     long l1 = this.elements[paramInt1];
/* 537 */     long l2 = this.elements[paramInt1];
/*     */     
/* 539 */     long[] arrayOfLong = this.elements;
/* 540 */     for (int i = paramInt1 + 1; i <= paramInt2;) {
/* 541 */       long l3 = arrayOfLong[(i++)];
/* 542 */       if (l3 > l2) { l2 = l3;
/* 543 */       } else if (l3 < l1) { l1 = l3;
/*     */       }
/*     */     }
/*     */     
/* 547 */     double d1 = paramInt2 - paramInt1 + 1.0D;
/* 548 */     double d2 = d1 * Math.log(d1) / 0.6931471805599453D;
/*     */     
/* 550 */     double d3 = l2 - l1 + 1.0D;
/* 551 */     double d4 = Math.max(d3, d1);
/*     */     
/* 553 */     if ((d3 < 10000.0D) && (d4 < d2)) {
/* 554 */       countSortFromTo(paramInt1, paramInt2, l1, l2);
/*     */     }
/*     */     else {
/* 557 */       quickSortFromTo(paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 566 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/LongArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */