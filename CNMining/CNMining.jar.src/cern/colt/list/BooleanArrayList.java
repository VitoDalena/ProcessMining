/*     */ package cern.colt.list;
/*     */ 
/*     */ import cern.colt.Arrays;
/*     */ import cern.colt.function.BooleanProcedure;
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
/*     */ 
/*     */ public class BooleanArrayList
/*     */   extends AbstractBooleanList
/*     */ {
/*     */   protected boolean[] elements;
/*     */   
/*     */   public BooleanArrayList()
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
/*     */   public BooleanArrayList(boolean[] paramArrayOfBoolean)
/*     */   {
/*  39 */     elements(paramArrayOfBoolean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BooleanArrayList(int paramInt)
/*     */   {
/*  47 */     this(new boolean[paramInt]);
/*  48 */     setSizeRaw(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void add(boolean paramBoolean)
/*     */   {
/*  57 */     if (this.size == this.elements.length) {
/*  58 */       ensureCapacity(this.size + 1);
/*     */     }
/*  60 */     this.elements[(this.size++)] = paramBoolean;
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
/*     */   public void beforeInsert(int paramInt, boolean paramBoolean)
/*     */   {
/*  73 */     if ((paramInt > this.size) || (paramInt < 0))
/*  74 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/*  75 */     ensureCapacity(this.size + 1);
/*  76 */     System.arraycopy(this.elements, paramInt, this.elements, paramInt + 1, this.size - paramInt);
/*  77 */     this.elements[paramInt] = paramBoolean;
/*  78 */     this.size += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/*  87 */     BooleanArrayList localBooleanArrayList = new BooleanArrayList((boolean[])this.elements.clone());
/*  88 */     localBooleanArrayList.setSizeRaw(this.size);
/*  89 */     return localBooleanArrayList;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public BooleanArrayList copy()
/*     */   {
/*  97 */     return (BooleanArrayList)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void countSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 109 */     if (this.size == 0) return;
/* 110 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 112 */     boolean[] arrayOfBoolean = this.elements;
/* 113 */     int i = 0;
/* 114 */     for (int j = paramInt1; j <= paramInt2;) if (arrayOfBoolean[(j++)] != 0) { i++;
/*     */       }
/* 116 */     int k = paramInt2 - paramInt1 + 1 - i;
/* 117 */     if (k > 0) fillFromToWith(paramInt1, paramInt1 + k - 1, false);
/* 118 */     if (i > 0) { fillFromToWith(paramInt1 + k, paramInt1 + k - 1 + i, true);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean[] elements()
/*     */   {
/* 129 */     return this.elements;
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
/*     */   public AbstractBooleanList elements(boolean[] paramArrayOfBoolean)
/*     */   {
/* 142 */     this.elements = paramArrayOfBoolean;
/* 143 */     this.size = paramArrayOfBoolean.length;
/* 144 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 153 */     this.elements = Arrays.ensureCapacity(this.elements, paramInt);
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
/* 167 */     if (!(paramObject instanceof BooleanArrayList)) return super.equals(paramObject);
/* 168 */     if (this == paramObject) return true;
/* 169 */     if (paramObject == null) return false;
/* 170 */     BooleanArrayList localBooleanArrayList = (BooleanArrayList)paramObject;
/* 171 */     if (size() != localBooleanArrayList.size()) { return false;
/*     */     }
/* 173 */     boolean[] arrayOfBoolean1 = elements();
/* 174 */     boolean[] arrayOfBoolean2 = localBooleanArrayList.elements();
/* 175 */     int i = size();
/* 176 */     do { if (arrayOfBoolean1[i] != arrayOfBoolean2[i]) return false;
/* 175 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 178 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean forEach(BooleanProcedure paramBooleanProcedure)
/*     */   {
/* 188 */     boolean[] arrayOfBoolean = this.elements;
/* 189 */     int i = this.size;
/*     */     
/* 191 */     for (int j = 0; j < i;) if (!paramBooleanProcedure.apply(arrayOfBoolean[(j++)])) return false;
/* 192 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean get(int paramInt)
/*     */   {
/* 203 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 204 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 205 */     return this.elements[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getQuick(int paramInt)
/*     */   {
/* 216 */     return this.elements[paramInt];
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
/*     */   public int indexOfFromTo(boolean paramBoolean, int paramInt1, int paramInt2)
/*     */   {
/* 232 */     if (this.size == 0) return -1;
/* 233 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 235 */     boolean[] arrayOfBoolean = this.elements;
/* 236 */     for (int i = paramInt1; i <= paramInt2; i++) {
/* 237 */       if (paramBoolean == arrayOfBoolean[i]) return i;
/*     */     }
/* 239 */     return -1;
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
/*     */   public int lastIndexOfFromTo(boolean paramBoolean, int paramInt1, int paramInt2)
/*     */   {
/* 255 */     if (this.size == 0) return -1;
/* 256 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 258 */     boolean[] arrayOfBoolean = this.elements;
/* 259 */     for (int i = paramInt2; i >= paramInt1; i--) {
/* 260 */       if (paramBoolean == arrayOfBoolean[i]) return i;
/*     */     }
/* 262 */     return -1;
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
/*     */   public void mergeSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 275 */     countSortFromTo(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AbstractBooleanList partFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 285 */     if (this.size == 0) { return new BooleanArrayList(0);
/*     */     }
/* 287 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 289 */     boolean[] arrayOfBoolean = new boolean[paramInt2 - paramInt1 + 1];
/* 290 */     System.arraycopy(this.elements, paramInt1, arrayOfBoolean, 0, paramInt2 - paramInt1 + 1);
/* 291 */     return new BooleanArrayList(arrayOfBoolean);
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
/*     */   public void quickSortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 304 */     countSortFromTo(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean removeAll(AbstractBooleanList paramAbstractBooleanList)
/*     */   {
/* 315 */     if (!(paramAbstractBooleanList instanceof BooleanArrayList)) { return super.removeAll(paramAbstractBooleanList);
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
/* 327 */     if (paramAbstractBooleanList.size() == 0) return false;
/* 328 */     int i = paramAbstractBooleanList.size() - 1;
/* 329 */     int j = 0;
/* 330 */     boolean[] arrayOfBoolean = this.elements;
/* 331 */     int k = size();
/*     */     
/* 333 */     double d1 = paramAbstractBooleanList.size();
/* 334 */     double d2 = k;
/* 335 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 337 */       BooleanArrayList localBooleanArrayList = (BooleanArrayList)paramAbstractBooleanList.clone();
/* 338 */       localBooleanArrayList.quickSort();
/*     */       
/* 340 */       for (int n = 0; n < k; n++) {
/* 341 */         if (localBooleanArrayList.binarySearchFromTo(arrayOfBoolean[n], 0, i) < 0) arrayOfBoolean[(j++)] = arrayOfBoolean[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 346 */       for (m = 0; m < k; m++) {
/* 347 */         if (paramAbstractBooleanList.indexOfFromTo(arrayOfBoolean[m], 0, i) < 0) { arrayOfBoolean[(j++)] = arrayOfBoolean[m];
/*     */         }
/*     */       }
/*     */     }
/* 351 */     int m = j != k ? 1 : 0;
/* 352 */     setSize(j);
/* 353 */     return m;
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
/*     */   public void replaceFromToWithFrom(int paramInt1, int paramInt2, AbstractBooleanList paramAbstractBooleanList, int paramInt3)
/*     */   {
/* 367 */     if (!(paramAbstractBooleanList instanceof BooleanArrayList))
/*     */     {
/* 369 */       super.replaceFromToWithFrom(paramInt1, paramInt2, paramAbstractBooleanList, paramInt3);
/* 370 */       return;
/*     */     }
/* 372 */     int i = paramInt2 - paramInt1 + 1;
/* 373 */     if (i > 0) {
/* 374 */       AbstractList.checkRangeFromTo(paramInt1, paramInt2, size());
/* 375 */       AbstractList.checkRangeFromTo(paramInt3, paramInt3 + i - 1, paramAbstractBooleanList.size());
/* 376 */       System.arraycopy(((BooleanArrayList)paramAbstractBooleanList).elements, paramInt3, this.elements, paramInt1, i);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean retainAll(AbstractBooleanList paramAbstractBooleanList)
/*     */   {
/* 388 */     if (!(paramAbstractBooleanList instanceof BooleanArrayList)) { return super.retainAll(paramAbstractBooleanList);
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
/* 400 */     int i = paramAbstractBooleanList.size() - 1;
/* 401 */     int j = 0;
/* 402 */     boolean[] arrayOfBoolean = this.elements;
/* 403 */     int k = size();
/*     */     
/* 405 */     double d1 = paramAbstractBooleanList.size();
/* 406 */     double d2 = k;
/* 407 */     if ((d1 + d2) * Arithmetic.log2(d1) < d2 * d1)
/*     */     {
/* 409 */       BooleanArrayList localBooleanArrayList = (BooleanArrayList)paramAbstractBooleanList.clone();
/* 410 */       localBooleanArrayList.quickSort();
/*     */       
/* 412 */       for (int n = 0; n < k; n++) {
/* 413 */         if (localBooleanArrayList.binarySearchFromTo(arrayOfBoolean[n], 0, i) >= 0) arrayOfBoolean[(j++)] = arrayOfBoolean[n];
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 418 */       for (m = 0; m < k; m++) {
/* 419 */         if (paramAbstractBooleanList.indexOfFromTo(arrayOfBoolean[m], 0, i) >= 0) { arrayOfBoolean[(j++)] = arrayOfBoolean[m];
/*     */         }
/*     */       }
/*     */     }
/* 423 */     int m = j != k ? 1 : 0;
/* 424 */     setSize(j);
/* 425 */     return m;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void reverse()
/*     */   {
/* 434 */     int j = this.size / 2;
/* 435 */     int k = this.size - 1;
/*     */     
/* 437 */     boolean[] arrayOfBoolean = this.elements;
/* 438 */     for (int m = 0; m < j;) {
/* 439 */       int i = arrayOfBoolean[m];
/* 440 */       arrayOfBoolean[(m++)] = arrayOfBoolean[k];
/* 441 */       arrayOfBoolean[(k--)] = i;
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
/*     */   public void set(int paramInt, boolean paramBoolean)
/*     */   {
/* 454 */     if ((paramInt >= this.size) || (paramInt < 0))
/* 455 */       throw new IndexOutOfBoundsException("Index: " + paramInt + ", Size: " + this.size);
/* 456 */     this.elements[paramInt] = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuick(int paramInt, boolean paramBoolean)
/*     */   {
/* 468 */     this.elements[paramInt] = paramBoolean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void shuffleFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 478 */     if (this.size == 0) return;
/* 479 */     AbstractList.checkRangeFromTo(paramInt1, paramInt2, this.size);
/*     */     
/* 481 */     Uniform localUniform = new Uniform(new DRand(new Date()));
/*     */     
/* 483 */     boolean[] arrayOfBoolean = this.elements;
/*     */     
/* 485 */     for (int k = paramInt1; k < paramInt2; k++) {
/* 486 */       int j = localUniform.nextIntFromTo(k, paramInt2);
/*     */       
/*     */ 
/* 489 */       int i = arrayOfBoolean[j];
/* 490 */       arrayOfBoolean[j] = arrayOfBoolean[k];
/* 491 */       arrayOfBoolean[k] = i;
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
/*     */   public void sortFromTo(int paramInt1, int paramInt2)
/*     */   {
/* 504 */     countSortFromTo(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void trimToSize()
/*     */   {
/* 512 */     this.elements = Arrays.trimToCapacity(this.elements, size());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/list/BooleanArrayList.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */