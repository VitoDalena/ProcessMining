/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.Sorting;
/*     */ import cern.colt.function.DoubleProcedure;
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
/*     */ public class DoubleArrayList
/*     */   extends AbstractDoubleList
/*     */ {
/*     */   protected double[] elements;
/*     */   
/*     */   public DoubleArrayList()
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
/*     */   public DoubleArrayList(double[] paramArrayOfDouble)
/*     */   {
/*  39 */     elements(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList(int paramInt)
/*     */   {
/*  47 */     this(new double[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(double paramDouble)
/*     */   {
/*  57 */     if (this.size == this.elements.length) ensureCapacity(this.size + 1);
/*  58 */     this.elements[(this.size++)] = paramDouble;
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
/*     */   public void beforeInsert(int paramInt, double paramDouble)
/*     */   {
/*  71 */     if (this.size == paramInt) {
/*  72 */       add(paramDouble);
/*  73 */       return;
/*     */     }
/*  75 */     if ((paramInt > this.size) || (paramInt < 0))
/*  76 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  77 */     ensureCapacity(this.size + 1);
/*  78 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  79 */     this.elements[paramInt] = paramDouble;
/*  80 */     this.size += 1;
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
/*     */   public int binarySearchFromTo(double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 106 */     return Sorting.binarySearchFromTo(this.elements, paramDouble, paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 115 */     DoubleArrayList localDoubleArrayList = new DoubleArrayList((double[])this.elements.clone());
/* 116 */     localDoubleArrayList.setSizeRaw(this.size);
/* 117 */     return localDoubleArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleArrayList copy()
/*     */   {
/* 125 */     return (DoubleArrayList)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] elements()
/*     */   {
/* 136 */     return this.elements;
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
/*     */   public AbstractDoubleList elements(double[] paramArrayOfDouble)
/*     */   {
/* 149 */     this.elements = paramArrayOfDouble;
/* 150 */     this.size = paramArrayOfDouble.length;
/* 151 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 160 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/* 174 */     if (!(paramObject instanceof DoubleArrayList)) return super.equals(paramObject);
/* 175 */     if (this == paramObject) return true;
/* 176 */     if (paramObject == null) return false;
/* 177 */     DoubleArrayList localDoubleArrayList = (DoubleArrayList)paramObject;
/* 178 */     if (size() != localDoubleArrayList.size()) { return false;
/*     */     }
/* 180 */     double[] arrayOfDouble1 = elements();
/* 181 */     double[] arrayOfDouble2 = localDoubleArrayList.elements();
/* 182 */     int i = size();
/* 183 */     do { if (arrayOfDouble1[i] != arrayOfDouble2[i]) return false;
/* 182 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 185 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 195 */     double[] arrayOfDouble = this.elements;
/* 196 */     int i = this.size;
/*     */     
/* 198 */     for (int j = 0; j < i;) if (!paramDoubleProcedure.apply(arrayOfDouble[(j++)])) return false;
/* 199 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double get(int paramInt)
/*     */   {
/* 210 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 211 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 212 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getQuick(int paramInt)
/*     */   {
/* 223 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 239 */     if (this.size == 0) return -1;
/* 240 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 242 */     double[] arrayOfDouble = this.elements;
/* 243 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 244 */       if (paramDouble == arrayOfDouble[i]) return i;
/*     */     }
/* 246 */     return -1;
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
/*     */   public int lastIndexOfFromTo(double paramDouble, int paramInt1, int paramInt2)
/*     */   {
/* 262 */     if (this.size == 0) return -1;
/* 263 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 265 */     double[] arrayOfDouble = this.elements;
/* 266 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 267 */       if (paramDouble == arrayOfDouble[i]) return i;
/*     */     }
/* 269 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractDoubleList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 279 */     if (this.size == 0) { return new DoubleArrayList(0);
/*     */     }
/* 281 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 283 */     double[] arrayOfDouble = new double[paramInt2 - paramInt1 + 1];
/* 284 */     System.arraycopy(this.elements, paramInt1, arrayOfDouble, 0, paramInt2 - paramInt1 + 1);
/* 285 */     return new DoubleArrayList(arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractDoubleList paramAbstractDoubleList)
/*     */   {
/* 296 */     if (!(paramAbstractDoubleList instanceof DoubleArrayList)) { return super.removeAll(paramAbstractDoubleList);
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
/* 308 */     if (paramAbstractDoubleList.size() == 0) return false;
/* 309 */     int i = paramAbstractDoubleList.size() - 1;
/* 310 */     int j = 0;
/* 311 */     double[] arrayOfDouble = this.elements;
/* 312 */     int k = size();
/*     */     
/* 314 */     double d1 = paramAbstractDoubleList.size();
/* 315 */     double d2 = k;
/* 316 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 318 */       DoubleArrayList localDoubleArrayList = (DoubleArrayList)paramAbstractDoubleList.clone();
/* 319 */       localDoubleArrayList.quickSort();
/*     */       
/* 321 */       for (int n = 0; n < k; n++) {
/* 322 */         if (localDoubleArrayList.binarySearchFromTo(arrayOfDouble[n], 0, i) < 0) arrayOfDouble[(j++)] = arrayOfDouble[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 327 */       for (m = 0; m < k; m++) {
/* 328 */         if (paramAbstractDoubleList.indexOfFromTo(arrayOfDouble[m], 0, i) < 0) { arrayOfDouble[(j++)] = arrayOfDouble[m];
/*     */         }
/*     */       }
/*     */     }
/* 332 */     int m = j != k ? 1 : 0;
/* 333 */     setSize(j);
/* 334 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractDoubleList paramAbstractDoubleList, int paramInt3)
/*     */   {
/* 348 */     if (!(paramAbstractDoubleList instanceof DoubleArrayList))
/*     */     {
/* 350 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractDoubleList, paramInt3);
/* 351 */       return;
/*     */     }
/* 353 */     int i = paramInt2 - paramInt1 + 1;
/* 354 */     if (i > 0) {
/* 355 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 356 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractDoubleList.size());
/* 357 */       System.arraycopy(((DoubleArrayList)paramAbstractDoubleList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractDoubleList paramAbstractDoubleList)
/*     */   {
/* 369 */     if (!(paramAbstractDoubleList instanceof DoubleArrayList)) { return super.retainAll(paramAbstractDoubleList);
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
/* 381 */     int i = paramAbstractDoubleList.size() - 1;
/* 382 */     int j = 0;
/* 383 */     double[] arrayOfDouble = this.elements;
/* 384 */     int k = size();
/*     */     
/* 386 */     double d1 = paramAbstractDoubleList.size();
/* 387 */     double d2 = k;
/* 388 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 390 */       DoubleArrayList localDoubleArrayList = (DoubleArrayList)paramAbstractDoubleList.clone();
/* 391 */       localDoubleArrayList.quickSort();
/*     */       
/* 393 */       for (int n = 0; n < k; n++) {
/* 394 */         if (localDoubleArrayList.binarySearchFromTo(arrayOfDouble[n], 0, i) >= 0) arrayOfDouble[(j++)] = arrayOfDouble[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 399 */       for (m = 0; m < k; m++) {
/* 400 */         if (paramAbstractDoubleList.indexOfFromTo(arrayOfDouble[m], 0, i) >= 0) { arrayOfDouble[(j++)] = arrayOfDouble[m];
/*     */         }
/*     */       }
/*     */     }
/* 404 */     int m = j != k ? 1 : 0;
/* 405 */     setSize(j);
/* 406 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 415 */     int i = this.size / 2;
/* 416 */     int j = this.size - 1;
/*     */     
/* 418 */     double[] arrayOfDouble = this.elements;
/* 419 */     for (int k = 0; k < i;) {
/* 420 */       double d = arrayOfDouble[k];
/* 421 */       arrayOfDouble[(k++)] = arrayOfDouble[j];
/* 422 */       arrayOfDouble[(j--)] = d;
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
/*     */   public void set(int paramInt, double paramDouble)
/*     */   {
/* 435 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 436 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 437 */     this.elements[paramInt] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, double paramDouble)
/*     */   {
/* 449 */     this.elements[paramInt] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 459 */     if (this.size == 0) return;
/* 460 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 462 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 464 */     double[] arrayOfDouble = this.elements;
/*     */     
/* 466 */     for (int j = paramInt1; j < paramInt2; j++) {
/* 467 */       int i = localUniform.nextIntFromTo(j, paramInt2);
/*     */       
/*     */ 
/* 470 */       double d = arrayOfDouble[i];
/* 471 */       arrayOfDouble[i] = arrayOfDouble[j];
/* 472 */       arrayOfDouble[j] = d;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 481 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/DoubleArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */