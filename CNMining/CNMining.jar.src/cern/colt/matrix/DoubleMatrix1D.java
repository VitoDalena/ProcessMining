/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.matrix.doublealgo.Formatter;
/*     */ import cern.colt.matrix.doublealgo.Sorting;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.linalg.Property;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.math.PlusMult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DoubleMatrix1D
/*     */   extends AbstractMatrix1D
/*     */ {
/*     */   public double aggregate(DoubleDoubleFunction paramDoubleDoubleFunction, DoubleFunction paramDoubleFunction)
/*     */   {
/*  52 */     if (this.size == 0) return NaN.0D;
/*  53 */     double d = paramDoubleFunction.apply(getQuick(this.size - 1));
/*  54 */     int i = this.size - 1;
/*  55 */     do { d = paramDoubleDoubleFunction.apply(d, paramDoubleFunction.apply(getQuick(i)));i--;
/*  54 */     } while (i >= 0);
/*     */     
/*     */ 
/*  57 */     return d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public double aggregate(DoubleMatrix1D paramDoubleMatrix1D, DoubleDoubleFunction paramDoubleDoubleFunction1, DoubleDoubleFunction paramDoubleDoubleFunction2)
/*     */   {
/*  86 */     checkSize(paramDoubleMatrix1D);
/*  87 */     if (this.size == 0) return NaN.0D;
/*  88 */     double d = paramDoubleDoubleFunction2.apply(getQuick(this.size - 1), paramDoubleMatrix1D.getQuick(this.size - 1));
/*  89 */     int i = this.size - 1;
/*  90 */     do { d = paramDoubleDoubleFunction1.apply(d, paramDoubleDoubleFunction2.apply(getQuick(i), paramDoubleMatrix1D.getQuick(i)));i--;
/*  89 */     } while (i >= 0);
/*     */     
/*     */ 
/*  92 */     return d;
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
/*     */   public DoubleMatrix1D assign(double[] paramArrayOfDouble)
/*     */   {
/* 105 */     if (paramArrayOfDouble.length != this.size) throw new IllegalArgumentException("Must have same number of cells: length=" + paramArrayOfDouble.length + "size()=" + size());
/* 106 */     int i = this.size;
/* 107 */     do { setQuick(i, paramArrayOfDouble[i]);i--;
/* 106 */     } while (i >= 0);
/*     */     
/*     */ 
/* 109 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D assign(double paramDouble)
/*     */   {
/* 117 */     int i = this.size;
/* 118 */     do { setQuick(i, paramDouble);i--;
/* 117 */     } while (i >= 0);
/*     */     
/*     */ 
/* 120 */     return this;
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
/*     */   public DoubleMatrix1D assign(DoubleFunction paramDoubleFunction)
/*     */   {
/* 141 */     int i = this.size;
/* 142 */     do { setQuick(i, paramDoubleFunction.apply(getQuick(i)));i--;
/* 141 */     } while (i >= 0);
/*     */     
/*     */ 
/* 144 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D assign(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 156 */     if (paramDoubleMatrix1D == this) return this;
/* 157 */     checkSize(paramDoubleMatrix1D);
/* 158 */     if (haveSharedCells(paramDoubleMatrix1D)) { paramDoubleMatrix1D = paramDoubleMatrix1D.copy();
/*     */     }
/* 160 */     int i = this.size;
/* 161 */     do { setQuick(i, paramDoubleMatrix1D.getQuick(i));i--;
/* 160 */     } while (i >= 0);
/*     */     
/*     */ 
/* 163 */     return this;
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
/*     */   public DoubleMatrix1D assign(DoubleMatrix1D paramDoubleMatrix1D, DoubleDoubleFunction paramDoubleDoubleFunction)
/*     */   {
/* 187 */     checkSize(paramDoubleMatrix1D);
/* 188 */     int i = this.size;
/* 189 */     do { setQuick(i, paramDoubleDoubleFunction.apply(getQuick(i), paramDoubleMatrix1D.getQuick(i)));i--;
/* 188 */     } while (i >= 0);
/*     */     
/*     */ 
/* 191 */     return this;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D assign(DoubleMatrix1D paramDoubleMatrix1D, DoubleDoubleFunction paramDoubleDoubleFunction, IntArrayList paramIntArrayList)
/*     */   {
/* 223 */     checkSize(paramDoubleMatrix1D);
/* 224 */     int[] arrayOfInt = paramIntArrayList.elements();
/*     */     
/*     */     int k;
/* 227 */     if (paramDoubleDoubleFunction == Functions.mult) {
/* 228 */       int i = 0;
/* 229 */       int j = paramIntArrayList.size();
/* 230 */       do { k = arrayOfInt[j];
/* 231 */         for (; i < k; i++) setQuick(i, 0.0D);
/* 232 */         setQuick(k, getQuick(k) * paramDoubleMatrix1D.getQuick(k));
/* 233 */         i++;j--;
/* 229 */       } while (j >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/* 236 */     else if ((paramDoubleDoubleFunction instanceof PlusMult)) {
/* 237 */       double d = ((PlusMult)paramDoubleDoubleFunction).multiplicator;
/* 238 */       if (d == 0.0D)
/* 239 */         return this;
/*     */       int m;
/* 241 */       if (d == 1.0D) {
/* 242 */         k = paramIntArrayList.size();
/* 243 */         do { m = arrayOfInt[k];
/* 244 */           setQuick(m, getQuick(m) + paramDoubleMatrix1D.getQuick(m));k--;
/* 242 */         } while (k >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */       }
/* 247 */       else if (d == -1.0D) {
/* 248 */         k = paramIntArrayList.size();
/* 249 */         do { m = arrayOfInt[k];
/* 250 */           setQuick(m, getQuick(m) - paramDoubleMatrix1D.getQuick(m));k--;
/* 248 */         } while (k >= 0);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/*     */ 
/* 254 */         k = paramIntArrayList.size();
/* 255 */         do { m = arrayOfInt[k];
/* 256 */           setQuick(m, getQuick(m) + d * paramDoubleMatrix1D.getQuick(m));k--;
/* 254 */         } while (k >= 0);
/*     */       }
/*     */       
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 261 */       return assign(paramDoubleMatrix1D, paramDoubleDoubleFunction);
/*     */     }
/* 263 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 269 */     int i = 0;
/* 270 */     int j = this.size;
/* 271 */     do { if (getQuick(j) != 0.0D) i++;
/* 270 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/* 273 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int cardinality(int paramInt)
/*     */   {
/* 279 */     int i = 0;
/* 280 */     int j = this.size;
/*     */     do {
/* 282 */       if (getQuick(j) != 0.0D) i++;
/* 281 */       j--; } while ((j >= 0) && (i < paramInt));
/*     */     
/*     */ 
/* 284 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D copy()
/*     */   {
/* 295 */     DoubleMatrix1D localDoubleMatrix1D = like();
/* 296 */     localDoubleMatrix1D.assign(this);
/* 297 */     return localDoubleMatrix1D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(double paramDouble)
/*     */   {
/* 306 */     return Property.DEFAULT.equals(this, paramDouble);
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
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 319 */     if (this == paramObject) return true;
/* 320 */     if (paramObject == null) return false;
/* 321 */     if (!(paramObject instanceof DoubleMatrix1D)) { return false;
/*     */     }
/* 323 */     return Property.DEFAULT.equals(this, (DoubleMatrix1D)paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double get(int paramInt)
/*     */   {
/* 333 */     if ((paramInt < 0) || (paramInt >= this.size)) checkIndex(paramInt);
/* 334 */     return getQuick(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D getContent()
/*     */   {
/* 341 */     return this;
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
/*     */   public void getNonZeros(IntArrayList paramIntArrayList, DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 367 */     int i = paramIntArrayList != null ? 1 : 0;
/* 368 */     int j = paramDoubleArrayList != null ? 1 : 0;
/* 369 */     if (i != 0) paramIntArrayList.clear();
/* 370 */     if (j != 0) paramDoubleArrayList.clear();
/* 371 */     int k = this.size;
/* 372 */     for (int m = 0; m < k; m++) {
/* 373 */       double d = getQuick(m);
/* 374 */       if (d != 0.0D) {
/* 375 */         if (i != 0) paramIntArrayList.add(m);
/* 376 */         if (j != 0) { paramDoubleArrayList.add(d);
/*     */         }
/*     */       }
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
/*     */   public void getNonZeros(IntArrayList paramIntArrayList, DoubleArrayList paramDoubleArrayList, int paramInt)
/*     */   {
/* 404 */     int i = paramIntArrayList != null ? 1 : 0;
/* 405 */     int j = paramDoubleArrayList != null ? 1 : 0;
/* 406 */     int k = cardinality(paramInt);
/* 407 */     if (i != 0) paramIntArrayList.setSize(k);
/* 408 */     if (j != 0) paramDoubleArrayList.setSize(k);
/* 409 */     if (k >= paramInt) { return;
/*     */     }
/* 411 */     if (i != 0) paramIntArrayList.setSize(0);
/* 412 */     if (j != 0) paramDoubleArrayList.setSize(0);
/* 413 */     int m = this.size;
/* 414 */     for (int n = 0; n < m; n++) {
/* 415 */       double d = getQuick(n);
/* 416 */       if (d != 0.0D) {
/* 417 */         if (i != 0) paramIntArrayList.add(n);
/* 418 */         if (j != 0) { paramDoubleArrayList.add(d);
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract double getQuick(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCells(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 437 */     if (paramDoubleMatrix1D == null) return false;
/* 438 */     if (this == paramDoubleMatrix1D) return true;
/* 439 */     return getContent().haveSharedCellsRaw(paramDoubleMatrix1D.getContent());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 445 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like()
/*     */   {
/* 456 */     return like(this.size);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DoubleMatrix1D like(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract DoubleMatrix2D like2D(int paramInt1, int paramInt2);
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
/* 486 */     if ((paramInt < 0) || (paramInt >= this.size)) checkIndex(paramInt);
/* 487 */     setQuick(paramInt, paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void setQuick(int paramInt, double paramDouble);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void swap(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 505 */     checkSize(paramDoubleMatrix1D);
/* 506 */     int i = this.size;
/* 507 */     do { double d = getQuick(i);
/* 508 */       setQuick(i, paramDoubleMatrix1D.getQuick(i));
/* 509 */       paramDoubleMatrix1D.setQuick(i, d);i--;
/* 506 */     } while (i >= 0);
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
/*     */   public double[] toArray()
/*     */   {
/* 523 */     double[] arrayOfDouble = new double[this.size];
/* 524 */     toArray(arrayOfDouble);
/* 525 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void toArray(double[] paramArrayOfDouble)
/*     */   {
/* 537 */     if (paramArrayOfDouble.length < this.size) throw new IllegalArgumentException("values too small");
/* 538 */     int i = this.size;
/* 539 */     do { paramArrayOfDouble[i] = getQuick(i);i--;
/* 538 */     } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 547 */     return new Formatter().toString(this);
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
/*     */   protected DoubleMatrix1D view()
/*     */   {
/* 561 */     return (DoubleMatrix1D)clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D viewFlip()
/*     */   {
/* 571 */     return (DoubleMatrix1D)view().vFlip();
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
/*     */   public DoubleMatrix1D viewPart(int paramInt1, int paramInt2)
/*     */   {
/* 595 */     return (DoubleMatrix1D)view().vPart(paramInt1, paramInt2);
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
/*     */   public DoubleMatrix1D viewSelection(int[] paramArrayOfInt)
/*     */   {
/* 619 */     if (paramArrayOfInt == null) {
/* 620 */       paramArrayOfInt = new int[this.size];
/* 621 */       int i = this.size; do { paramArrayOfInt[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 624 */     checkIndexes(paramArrayOfInt);
/* 625 */     int[] arrayOfInt = new int[paramArrayOfInt.length];
/* 626 */     int j = paramArrayOfInt.length;
/* 627 */     do { arrayOfInt[j] = index(paramArrayOfInt[j]);j--;
/* 626 */     } while (j >= 0);
/*     */     
/*     */ 
/* 629 */     return viewSelectionLike(arrayOfInt);
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
/*     */   public DoubleMatrix1D viewSelection(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 655 */     IntArrayList localIntArrayList = new IntArrayList();
/* 656 */     for (int i = 0; i < this.size; i++) {
/* 657 */       if (paramDoubleProcedure.apply(getQuick(i))) localIntArrayList.add(i);
/*     */     }
/* 659 */     localIntArrayList.trimToSize();
/* 660 */     return viewSelection(localIntArrayList.elements());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract DoubleMatrix1D viewSelectionLike(int[] paramArrayOfInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D viewSorted()
/*     */   {
/* 677 */     return Sorting.mergeSort.sort(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D viewStrides(int paramInt)
/*     */   {
/* 689 */     return (DoubleMatrix1D)view().vStrides(paramInt);
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
/*     */   private boolean xforEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 707 */     int i = this.size;
/* 708 */     do { if (!paramDoubleProcedure.apply(getQuick(i))) return false;
/* 707 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/* 710 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double zDotProduct(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 720 */     return zDotProduct(paramDoubleMatrix1D, 0, this.size);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double zDotProduct(DoubleMatrix1D paramDoubleMatrix1D, int paramInt1, int paramInt2)
/*     */   {
/* 732 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) { return 0.0D;
/*     */     }
/* 734 */     int i = paramInt1 + paramInt2;
/* 735 */     if (this.size < i) i = this.size;
/* 736 */     if (paramDoubleMatrix1D.size < i) i = paramDoubleMatrix1D.size;
/* 737 */     paramInt2 = i - paramInt1;
/*     */     
/* 739 */     double d = 0.0D;
/* 740 */     int j = i - 1;
/* 741 */     int k = paramInt2;
/* 742 */     do { d += getQuick(j) * paramDoubleMatrix1D.getQuick(j);j--;k--;
/* 741 */     } while (k >= 0);
/*     */     
/*     */ 
/* 744 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double zDotProduct(DoubleMatrix1D paramDoubleMatrix1D, int paramInt1, int paramInt2, IntArrayList paramIntArrayList)
/*     */   {
/* 755 */     if ((paramInt1 < 0) || (paramInt2 <= 0)) { return 0.0D;
/*     */     }
/* 757 */     int i = paramInt1 + paramInt2;
/* 758 */     if (this.size < i) i = this.size;
/* 759 */     if (paramDoubleMatrix1D.size < i) i = paramDoubleMatrix1D.size;
/* 760 */     paramInt2 = i - paramInt1;
/* 761 */     if (paramInt2 <= 0) { return 0.0D;
/*     */     }
/*     */     
/* 764 */     int[] arrayOfInt = paramIntArrayList.elements();
/* 765 */     int j = 0;
/* 766 */     int k = paramIntArrayList.size();
/*     */     
/*     */ 
/* 769 */     while ((j < k) && (arrayOfInt[j] < paramInt1)) { j++;
/*     */     }
/*     */     
/*     */ 
/* 773 */     double d = 0.0D;
/*     */     int m;
/* 775 */     do { d += getQuick(m) * paramDoubleMatrix1D.getQuick(m);
/* 776 */       j++;paramInt2--;
/* 774 */     } while ((paramInt2 >= 0) && (j < k) && ((m = arrayOfInt[j]) < i));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 779 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double zDotProduct(DoubleMatrix1D paramDoubleMatrix1D, IntArrayList paramIntArrayList)
/*     */   {
/* 789 */     return zDotProduct(paramDoubleMatrix1D, 0, this.size, paramIntArrayList);
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
/*     */   public double zSum()
/*     */   {
/* 805 */     if (size() == 0) return 0.0D;
/* 806 */     return aggregate(Functions.plus, Functions.identity);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/DoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */