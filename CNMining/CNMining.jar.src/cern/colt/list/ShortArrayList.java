/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.ShortProcedure;
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
/*     */ public class ShortArrayList
/*     */   extends AbstractShortList
/*     */ {
/*     */   protected short[] elements;
/*     */   
/*     */   public ShortArrayList()
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
/*     */   public ShortArrayList(short[] paramArrayOfShort)
/*     */   {
/*  39 */     elements(paramArrayOfShort);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShortArrayList(int paramInt)
/*     */   {
/*  47 */     this(new short[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(short paramShort)
/*     */   {
/*  57 */     if (this.size == this.elements.length) {
/*  58 */       ensureCapacity(this.size + 1);
/*     */     }
/*  60 */     this.elements[(this.size++)] = paramShort;
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
/*     */   public void beforeInsert(int paramInt, short paramShort)
/*     */   {
/*  73 */     if ((paramInt > this.size) || (paramInt < 0))
/*  74 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  75 */     ensureCapacity(this.size + 1);
/*  76 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  77 */     this.elements[paramInt] = paramShort;
/*  78 */     this.size += 1;
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
/*     */   public int binarySearchFromTo(short paramShort, int paramInt1, int paramInt2)
/*     */   {
/* 104 */     return Sorting.binarySearchFromTo(this.elements, paramShort, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 113 */     ShortArrayList localShortArrayList = new ShortArrayList((short[])this.elements.clone());
/* 114 */     localShortArrayList.setSizeRaw(this.size);
/* 115 */     return localShortArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ShortArrayList copy()
/*     */   {
/* 123 */     return (ShortArrayList)clone();
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
/*     */   protected void countSortFromTo(int paramInt1, int paramInt2, short paramShort1, short paramShort2)
/*     */   {
/* 140 */     if (this.size == 0) return;
/* 141 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 143 */     int i = paramShort2 - paramShort1 + 1;
/*     */     
/* 145 */     int[] arrayOfInt = new int[i];
/* 146 */     short[] arrayOfShort = this.elements;
/* 147 */     for (int j = paramInt1; j <= paramInt2; arrayOfInt[(arrayOfShort[(j++)] - paramShort1)] += 1) {}
/*     */     
/* 149 */     int k = paramInt1;
/* 150 */     short s = paramShort1;
/* 151 */     for (int m = 0; m < i; s = (short)(s + 1)) {
/* 152 */       int n = arrayOfInt[m];
/* 153 */       if (n > 0) {
/* 154 */         if (n == 1) { arrayOfShort[(k++)] = s;
/*     */         } else {
/* 156 */           int i1 = k + n - 1;
/* 157 */           fillFromToWith(k, i1, s);
/* 158 */           k = i1 + 1;
/*     */         }
/*     */       }
/* 151 */       m++;
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
/*     */   public short[] elements()
/*     */   {
/* 172 */     return this.elements;
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
/*     */   public AbstractShortList elements(short[] paramArrayOfShort)
/*     */   {
/* 185 */     this.elements = paramArrayOfShort;
/* 186 */     this.size = paramArrayOfShort.length;
/* 187 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 196 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/* 210 */     if (!(paramObject instanceof ShortArrayList)) return super.equals(paramObject);
/* 211 */     if (this == paramObject) return true;
/* 212 */     if (paramObject == null) return false;
/* 213 */     ShortArrayList localShortArrayList = (ShortArrayList)paramObject;
/* 214 */     if (size() != localShortArrayList.size()) { return false;
/*     */     }
/* 216 */     short[] arrayOfShort1 = elements();
/* 217 */     short[] arrayOfShort2 = localShortArrayList.elements();
/* 218 */     int i = size();
/* 219 */     do { if (arrayOfShort1[i] != arrayOfShort2[i]) return false;
/* 218 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 221 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(ShortProcedure paramShortProcedure)
/*     */   {
/* 231 */     short[] arrayOfShort = this.elements;
/* 232 */     int i = this.size;
/*     */     
/* 234 */     for (int j = 0; j < i;) if (!paramShortProcedure.apply(arrayOfShort[(j++)])) return false;
/* 235 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short get(int paramInt)
/*     */   {
/* 246 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 247 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 248 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public short getQuick(int paramInt)
/*     */   {
/* 259 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(short paramShort, int paramInt1, int paramInt2)
/*     */   {
/* 275 */     if (this.size == 0) return -1;
/* 276 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 278 */     short[] arrayOfShort = this.elements;
/* 279 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 280 */       if (paramShort == arrayOfShort[i]) return i;
/*     */     }
/* 282 */     return -1;
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
/*     */   public int lastIndexOfFromTo(short paramShort, int paramInt1, int paramInt2)
/*     */   {
/* 298 */     if (this.size == 0) return -1;
/* 299 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 301 */     short[] arrayOfShort = this.elements;
/* 302 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 303 */       if (paramShort == arrayOfShort[i]) return i;
/*     */     }
/* 305 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractShortList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 315 */     if (this.size == 0) { return new ShortArrayList(0);
/*     */     }
/* 317 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 319 */     short[] arrayOfShort = new short[paramInt2 - paramInt1 + 1];
/* 320 */     System.arraycopy(this.elements, paramInt1, arrayOfShort, 0, paramInt2 - paramInt1 + 1);
/* 321 */     return new ShortArrayList(arrayOfShort);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractShortList paramAbstractShortList)
/*     */   {
/* 332 */     if (!(paramAbstractShortList instanceof ShortArrayList)) { return super.removeAll(paramAbstractShortList);
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
/* 344 */     if (paramAbstractShortList.size() == 0) return false;
/* 345 */     int i = paramAbstractShortList.size() - 1;
/* 346 */     int j = 0;
/* 347 */     short[] arrayOfShort = this.elements;
/* 348 */     int k = size();
/*     */     
/* 350 */     double d1 = paramAbstractShortList.size();
/* 351 */     double d2 = k;
/* 352 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 354 */       ShortArrayList localShortArrayList = (ShortArrayList)paramAbstractShortList.clone();
/* 355 */       localShortArrayList.quickSort();
/*     */       
/* 357 */       for (int n = 0; n < k; n++) {
/* 358 */         if (localShortArrayList.binarySearchFromTo(arrayOfShort[n], 0, i) < 0) arrayOfShort[(j++)] = arrayOfShort[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 363 */       for (m = 0; m < k; m++) {
/* 364 */         if (paramAbstractShortList.indexOfFromTo(arrayOfShort[m], 0, i) < 0) { arrayOfShort[(j++)] = arrayOfShort[m];
/*     */         }
/*     */       }
/*     */     }
/* 368 */     int m = j != k ? 1 : 0;
/* 369 */     setSize(j);
/* 370 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractShortList paramAbstractShortList, int paramInt3)
/*     */   {
/* 384 */     if (!(paramAbstractShortList instanceof ShortArrayList))
/*     */     {
/* 386 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractShortList, paramInt3);
/* 387 */       return;
/*     */     }
/* 389 */     int i = paramInt2 - paramInt1 + 1;
/* 390 */     if (i > 0) {
/* 391 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 392 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractShortList.size());
/* 393 */       System.arraycopy(((ShortArrayList)paramAbstractShortList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractShortList paramAbstractShortList)
/*     */   {
/* 405 */     if (!(paramAbstractShortList instanceof ShortArrayList)) { return super.retainAll(paramAbstractShortList);
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
/* 417 */     int i = paramAbstractShortList.size() - 1;
/* 418 */     int j = 0;
/* 419 */     short[] arrayOfShort = this.elements;
/* 420 */     int k = size();
/*     */     
/* 422 */     double d1 = paramAbstractShortList.size();
/* 423 */     double d2 = k;
/* 424 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 426 */       ShortArrayList localShortArrayList = (ShortArrayList)paramAbstractShortList.clone();
/* 427 */       localShortArrayList.quickSort();
/*     */       
/* 429 */       for (int n = 0; n < k; n++) {
/* 430 */         if (localShortArrayList.binarySearchFromTo(arrayOfShort[n], 0, i) >= 0) arrayOfShort[(j++)] = arrayOfShort[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 435 */       for (m = 0; m < k; m++) {
/* 436 */         if (paramAbstractShortList.indexOfFromTo(arrayOfShort[m], 0, i) >= 0) { arrayOfShort[(j++)] = arrayOfShort[m];
/*     */         }
/*     */       }
/*     */     }
/* 440 */     int m = j != k ? 1 : 0;
/* 441 */     setSize(j);
/* 442 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 451 */     int j = this.size / 2;
/* 452 */     int k = this.size - 1;
/*     */     
/* 454 */     short[] arrayOfShort = this.elements;
/* 455 */     for (int m = 0; m < j;) {
/* 456 */       int i = arrayOfShort[m];
/* 457 */       arrayOfShort[(m++)] = arrayOfShort[k];
/* 458 */       arrayOfShort[(k--)] = i;
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
/*     */   public void set(int paramInt, short paramShort)
/*     */   {
/* 471 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 472 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 473 */     this.elements[paramInt] = paramShort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, short paramShort)
/*     */   {
/* 485 */     this.elements[paramInt] = paramShort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 495 */     if (this.size == 0) return;
/* 496 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 498 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 500 */     short[] arrayOfShort = this.elements;
/*     */     
/* 502 */     for (int k = paramInt1; k < paramInt2; k++) {
/* 503 */       int j = localUniform.nextIntFromTo(k, paramInt2);
/*     */       
/*     */ 
/* 506 */       int i = arrayOfShort[j];
/* 507 */       arrayOfShort[j] = arrayOfShort[k];
/* 508 */       arrayOfShort[k] = i;
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
/* 534 */     if (this.size == 0) return;
/* 535 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/*     */ 
/* 538 */     short s1 = this.elements[paramInt1];
/* 539 */     short s2 = this.elements[paramInt1];
/*     */     
/* 541 */     short[] arrayOfShort = this.elements;
/* 542 */     for (int i = paramInt1 + 1; i <= paramInt2;) {
/* 543 */       short s3 = arrayOfShort[(i++)];
/* 544 */       if (s3 > s2) { s2 = s3;
/* 545 */       } else if (s3 < s1) { s1 = s3;
/*     */       }
/*     */     }
/*     */     
/* 549 */     double d1 = paramInt2 - paramInt1 + 1.0D;
/* 550 */     double d2 = d1 * Math.log(d1) / 0.6931471805599453D;
/*     */     
/* 552 */     double d3 = s2 - s1 + 1.0D;
/* 553 */     double d4 = Math.max(d3, d1);
/*     */     
/* 555 */     if ((d3 < 10000.0D) && (d4 < d2)) {
/* 556 */       countSortFromTo(paramInt1, paramInt2, s1, s2);
/*     */     }
/*     */     else {
/* 559 */       quickSortFromTo(paramInt1, paramInt2);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 568 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/ShortArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */