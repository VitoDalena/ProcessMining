/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.Double27Function;
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.DoubleProcedure;
/*     */ import cern.colt.function.IntIntIntProcedure;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.matrix.doublealgo.Formatter;
/*     */ import cern.colt.matrix.doublealgo.Sorting;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
/*     */ import cern.colt.matrix.linalg.Property;
/*     */ import cern.jet.math.Functions;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class DoubleMatrix3D
/*     */   extends AbstractMatrix3D
/*     */ {
/*     */   public double aggregate(DoubleDoubleFunction paramDoubleDoubleFunction, DoubleFunction paramDoubleFunction)
/*     */   {
/*  59 */     if (size() == 0) return NaN.0D;
/*  60 */     double d = paramDoubleFunction.apply(getQuick(this.slices - 1, this.rows - 1, this.columns - 1));
/*  61 */     int i = 1;
/*  62 */     int j = this.slices;
/*  63 */     do { int k = this.rows;
/*  64 */       do { int m = this.columns - i;
/*  65 */         do { d = paramDoubleDoubleFunction.apply(d, paramDoubleFunction.apply(getQuick(j, k, m)));m--;
/*  64 */         } while (m >= 0);
/*     */         
/*     */ 
/*  67 */         i = 0;k--;
/*  63 */       } while (k >= 0);
/*  62 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  70 */     return d;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double aggregate(DoubleMatrix3D paramDoubleMatrix3D, DoubleDoubleFunction paramDoubleDoubleFunction1, DoubleDoubleFunction paramDoubleDoubleFunction2)
/*     */   {
/* 110 */     checkShape(paramDoubleMatrix3D);
/* 111 */     if (size() == 0) return NaN.0D;
/* 112 */     double d = paramDoubleDoubleFunction2.apply(getQuick(this.slices - 1, this.rows - 1, this.columns - 1), paramDoubleMatrix3D.getQuick(this.slices - 1, this.rows - 1, this.columns - 1));
/* 113 */     int i = 1;
/* 114 */     int j = this.slices;
/* 115 */     do { int k = this.rows;
/* 116 */       do { int m = this.columns - i;
/* 117 */         do { d = paramDoubleDoubleFunction1.apply(d, paramDoubleDoubleFunction2.apply(getQuick(j, k, m), paramDoubleMatrix3D.getQuick(j, k, m)));m--;
/* 116 */         } while (m >= 0);
/*     */         
/*     */ 
/* 119 */         i = 0;k--;
/* 115 */       } while (k >= 0);
/* 114 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 122 */     return d;
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
/*     */   public DoubleMatrix3D assign(double[][][] paramArrayOfDouble)
/*     */   {
/* 137 */     if (paramArrayOfDouble.length != this.slices) throw new IllegalArgumentException("Must have same number of slices: slices=" + paramArrayOfDouble.length + "slices()=" + slices());
/* 138 */     int i = this.slices;
/* 139 */     do { double[][] arrayOfDouble = paramArrayOfDouble[i];
/* 140 */       if (arrayOfDouble.length != this.rows) throw new IllegalArgumentException("Must have same number of rows in every slice: rows=" + arrayOfDouble.length + "rows()=" + rows());
/* 141 */       int j = this.rows;
/* 142 */       do { double[] arrayOfDouble1 = arrayOfDouble[j];
/* 143 */         if (arrayOfDouble1.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfDouble1.length + "columns()=" + columns());
/* 144 */         int k = this.columns;
/* 145 */         do { setQuick(i, j, k, arrayOfDouble1[k]);k--;
/* 144 */         } while (k >= 0);
/* 141 */         j--; } while (j >= 0);
/* 138 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 149 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D assign(double paramDouble)
/*     */   {
/* 157 */     int i = this.slices;
/* 158 */     do { int j = this.rows;
/* 159 */       do { int k = this.columns;
/* 160 */         do { setQuick(i, j, k, paramDouble);k--;
/* 159 */         } while (k >= 0);
/* 158 */         j--; } while (j >= 0);
/* 157 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 164 */     return this;
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
/*     */   public DoubleMatrix3D assign(DoubleFunction paramDoubleFunction)
/*     */   {
/* 189 */     int i = this.slices;
/* 190 */     do { int j = this.rows;
/* 191 */       do { int k = this.columns;
/* 192 */         do { setQuick(i, j, k, paramDoubleFunction.apply(getQuick(i, j, k)));k--;
/* 191 */         } while (k >= 0);
/* 190 */         j--; } while (j >= 0);
/* 189 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 196 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D assign(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/* 208 */     if (paramDoubleMatrix3D == this) return this;
/* 209 */     checkShape(paramDoubleMatrix3D);
/* 210 */     if (haveSharedCells(paramDoubleMatrix3D)) { paramDoubleMatrix3D = paramDoubleMatrix3D.copy();
/*     */     }
/* 212 */     int i = this.slices;
/* 213 */     do { int j = this.rows;
/* 214 */       do { int k = this.columns;
/* 215 */         do { setQuick(i, j, k, paramDoubleMatrix3D.getQuick(i, j, k));k--;
/* 214 */         } while (k >= 0);
/* 213 */         j--; } while (j >= 0);
/* 212 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 219 */     return this;
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
/*     */   public DoubleMatrix3D assign(DoubleMatrix3D paramDoubleMatrix3D, DoubleDoubleFunction paramDoubleDoubleFunction)
/*     */   {
/* 251 */     checkShape(paramDoubleMatrix3D);
/* 252 */     int i = this.slices;
/* 253 */     do { int j = this.rows;
/* 254 */       do { int k = this.columns;
/* 255 */         do { setQuick(i, j, k, paramDoubleDoubleFunction.apply(getQuick(i, j, k), paramDoubleMatrix3D.getQuick(i, j, k)));k--;
/* 254 */         } while (k >= 0);
/* 253 */         j--; } while (j >= 0);
/* 252 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 259 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 265 */     int i = 0;
/* 266 */     int j = this.slices;
/* 267 */     do { int k = this.rows;
/* 268 */       do { int m = this.columns;
/* 269 */         do { if (getQuick(j, k, m) != 0.0D) i++;
/* 268 */           m--; } while (m >= 0);
/* 267 */         k--; } while (k >= 0);
/* 266 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 273 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D copy()
/*     */   {
/* 284 */     return like().assign(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(double paramDouble)
/*     */   {
/* 293 */     return Property.DEFAULT.equals(this, paramDouble);
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
/* 306 */     if (this == paramObject) return true;
/* 307 */     if (paramObject == null) return false;
/* 308 */     if (!(paramObject instanceof DoubleMatrix3D)) { return false;
/*     */     }
/* 310 */     return Property.DEFAULT.equals(this, (DoubleMatrix3D)paramObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double get(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 322 */     if ((paramInt1 < 0) || (paramInt1 >= this.slices) || (paramInt2 < 0) || (paramInt2 >= this.rows) || (paramInt3 < 0) || (paramInt3 >= this.columns)) throw new IndexOutOfBoundsException("slice:" + paramInt1 + ", row:" + paramInt2 + ", column:" + paramInt3);
/* 323 */     return getQuick(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix3D getContent()
/*     */   {
/* 330 */     return this;
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
/*     */   public void getNonZeros(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2, IntArrayList paramIntArrayList3, DoubleArrayList paramDoubleArrayList)
/*     */   {
/* 349 */     paramIntArrayList1.clear();
/* 350 */     paramIntArrayList2.clear();
/* 351 */     paramIntArrayList3.clear();
/* 352 */     paramDoubleArrayList.clear();
/* 353 */     int i = this.slices;
/* 354 */     int j = this.rows;
/* 355 */     int k = this.columns;
/* 356 */     for (int m = 0; m < i; m++) {
/* 357 */       for (int n = 0; n < j; n++) {
/* 358 */         for (int i1 = 0; i1 < k; i1++) {
/* 359 */           double d = getQuick(m, n, i1);
/* 360 */           if (d != 0.0D) {
/* 361 */             paramIntArrayList1.add(m);
/* 362 */             paramIntArrayList2.add(n);
/* 363 */             paramIntArrayList3.add(i1);
/* 364 */             paramDoubleArrayList.add(d);
/*     */           }
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
/*     */   public abstract double getQuick(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCells(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/* 387 */     if (paramDoubleMatrix3D == null) return false;
/* 388 */     if (this == paramDoubleMatrix3D) return true;
/* 389 */     return getContent().haveSharedCellsRaw(paramDoubleMatrix3D.getContent());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/* 395 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D like()
/*     */   {
/* 406 */     return like(this.slices, this.rows, this.columns);
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
/*     */   public abstract DoubleMatrix3D like(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract DoubleMatrix2D like2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt1, int paramInt2, int paramInt3, double paramDouble)
/*     */   {
/* 444 */     if ((paramInt1 < 0) || (paramInt1 >= this.slices) || (paramInt2 < 0) || (paramInt2 >= this.rows) || (paramInt3 < 0) || (paramInt3 >= this.columns)) throw new IndexOutOfBoundsException("slice:" + paramInt1 + ", row:" + paramInt2 + ", column:" + paramInt3);
/* 445 */     setQuick(paramInt1, paramInt2, paramInt3, paramDouble);
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
/*     */   public abstract void setQuick(int paramInt1, int paramInt2, int paramInt3, double paramDouble);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[][][] toArray()
/*     */   {
/* 470 */     double[][][] arrayOfDouble = new double[this.slices][this.rows][this.columns];
/* 471 */     int i = this.slices;
/* 472 */     do { double[][] arrayOfDouble1 = arrayOfDouble[i];
/* 473 */       int j = this.rows;
/* 474 */       do { double[] arrayOfDouble2 = arrayOfDouble1[j];
/* 475 */         int k = this.columns;
/* 476 */         do { arrayOfDouble2[k] = getQuick(i, j, k);k--;
/* 475 */         } while (k >= 0);
/* 473 */         j--; } while (j >= 0);
/* 471 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 480 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 487 */     return new Formatter().toString(this);
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
/*     */   protected DoubleMatrix3D view()
/*     */   {
/* 501 */     return (DoubleMatrix3D)clone();
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
/*     */   public DoubleMatrix2D viewColumn(int paramInt)
/*     */   {
/* 518 */     checkColumn(paramInt);
/* 519 */     int i = this.slices;
/* 520 */     int j = this.rows;
/*     */     
/*     */ 
/* 523 */     int k = this.sliceZero;
/* 524 */     int m = this.rowZero + _columnOffset(_columnRank(paramInt));
/*     */     
/* 526 */     int n = this.sliceStride;
/* 527 */     int i1 = this.rowStride;
/* 528 */     return like2D(i, j, k, m, n, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D viewColumnFlip()
/*     */   {
/* 540 */     return (DoubleMatrix3D)view().vColumnFlip();
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
/*     */   public DoubleMatrix3D viewDice(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 554 */     return (DoubleMatrix3D)view().vDice(paramInt1, paramInt2, paramInt3);
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
/*     */   public DoubleMatrix3D viewPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 572 */     return (DoubleMatrix3D)view().vPart(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
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
/*     */   public DoubleMatrix2D viewRow(int paramInt)
/*     */   {
/* 589 */     checkRow(paramInt);
/* 590 */     int i = this.slices;
/* 591 */     int j = this.columns;
/*     */     
/*     */ 
/* 594 */     int k = this.sliceZero;
/* 595 */     int m = this.columnZero + _rowOffset(_rowRank(paramInt));
/*     */     
/* 597 */     int n = this.sliceStride;
/* 598 */     int i1 = this.columnStride;
/* 599 */     return like2D(i, j, k, m, n, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D viewRowFlip()
/*     */   {
/* 611 */     return (DoubleMatrix3D)view().vRowFlip();
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
/*     */   public DoubleMatrix3D viewSelection(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/*     */     int i;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 633 */     if (paramArrayOfInt1 == null) {
/* 634 */       paramArrayOfInt1 = new int[this.slices];
/* 635 */       i = this.slices; do { paramArrayOfInt1[i] = i;i--; } while (i >= 0);
/*     */     }
/* 637 */     if (paramArrayOfInt2 == null) {
/* 638 */       paramArrayOfInt2 = new int[this.rows];
/* 639 */       i = this.rows; do { paramArrayOfInt2[i] = i;i--; } while (i >= 0);
/*     */     }
/* 641 */     if (paramArrayOfInt3 == null) {
/* 642 */       paramArrayOfInt3 = new int[this.columns];
/* 643 */       i = this.columns; do { paramArrayOfInt3[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 646 */     checkSliceIndexes(paramArrayOfInt1);
/* 647 */     checkRowIndexes(paramArrayOfInt2);
/* 648 */     checkColumnIndexes(paramArrayOfInt3);
/*     */     
/* 650 */     int[] arrayOfInt1 = new int[paramArrayOfInt1.length];
/* 651 */     int[] arrayOfInt2 = new int[paramArrayOfInt2.length];
/* 652 */     int[] arrayOfInt3 = new int[paramArrayOfInt3.length];
/*     */     
/* 654 */     int j = paramArrayOfInt1.length;
/* 655 */     do { arrayOfInt1[j] = _sliceOffset(_sliceRank(paramArrayOfInt1[j]));j--;
/* 654 */     } while (j >= 0);
/*     */     
/*     */ 
/* 657 */     int k = paramArrayOfInt2.length;
/* 658 */     do { arrayOfInt2[k] = _rowOffset(_rowRank(paramArrayOfInt2[k]));k--;
/* 657 */     } while (k >= 0);
/*     */     
/*     */ 
/* 660 */     int m = paramArrayOfInt3.length;
/* 661 */     do { arrayOfInt3[m] = _columnOffset(_columnRank(paramArrayOfInt3[m]));m--;
/* 660 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/* 664 */     return viewSelectionLike(arrayOfInt1, arrayOfInt2, arrayOfInt3);
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
/*     */   public DoubleMatrix3D viewSelection(DoubleMatrix2DProcedure paramDoubleMatrix2DProcedure)
/*     */   {
/* 688 */     IntArrayList localIntArrayList = new IntArrayList();
/* 689 */     for (int i = 0; i < this.slices; i++) {
/* 690 */       if (paramDoubleMatrix2DProcedure.apply(viewSlice(i))) { localIntArrayList.add(i);
/*     */       }
/*     */     }
/* 693 */     localIntArrayList.trimToSize();
/* 694 */     return viewSelection(localIntArrayList.elements(), null, null);
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
/*     */   protected abstract DoubleMatrix3D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D viewSlice(int paramInt)
/*     */   {
/* 720 */     checkSlice(paramInt);
/* 721 */     int i = this.rows;
/* 722 */     int j = this.columns;
/*     */     
/*     */ 
/* 725 */     int k = this.rowZero;
/* 726 */     int m = this.columnZero + _sliceOffset(_sliceRank(paramInt));
/*     */     
/* 728 */     int n = this.rowStride;
/* 729 */     int i1 = this.columnStride;
/* 730 */     return like2D(i, j, k, m, n, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D viewSliceFlip()
/*     */   {
/* 742 */     return (DoubleMatrix3D)view().vSliceFlip();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix3D viewSorted(int paramInt1, int paramInt2)
/*     */   {
/* 753 */     return Sorting.mergeSort.sort(this, paramInt1, paramInt2);
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
/*     */   public DoubleMatrix3D viewStrides(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 768 */     return (DoubleMatrix3D)view().vStrides(paramInt1, paramInt2, paramInt3);
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
/*     */   private boolean xforEach(DoubleProcedure paramDoubleProcedure)
/*     */   {
/* 790 */     int i = this.slices;
/* 791 */     do { int j = this.rows;
/* 792 */       do { int k = this.columns;
/* 793 */         do { if (!paramDoubleProcedure.apply(getQuick(i, j, k))) return false;
/* 792 */           k--; } while (k >= 0);
/* 791 */         j--; } while (j >= 0);
/* 790 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 797 */     return true;
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
/*     */   private boolean xforEachCoordinate(IntIntIntProcedure paramIntIntIntProcedure)
/*     */   {
/* 819 */     int i = this.columns;
/* 820 */     do { int j = this.slices;
/* 821 */       do { int k = this.rows;
/* 822 */         do { if (!paramIntIntIntProcedure.apply(j, k, i)) return false;
/* 821 */           k--; } while (k >= 0);
/* 820 */         j--; } while (j >= 0);
/* 819 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 826 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void zAssign27Neighbors(DoubleMatrix3D paramDoubleMatrix3D, Double27Function paramDouble27Function)
/*     */   {
/* 887 */     if (paramDouble27Function == null) throw new NullPointerException("function must not be null.");
/* 888 */     checkShape(paramDoubleMatrix3D);
/* 889 */     if ((this.rows < 3) || (this.columns < 3) || (this.slices < 3)) return;
/* 890 */     int i = this.rows - 1;
/* 891 */     int j = this.columns - 1;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 904 */     for (int k = 1; k < this.slices - 1; k++) {
/* 905 */       for (int m = 1; m < i; m++) {
/* 906 */         double d1 = getQuick(k - 1, m - 1, 0);double d2 = getQuick(k - 1, m - 1, 1);
/* 907 */         double d4 = getQuick(k - 1, m, 0);double d5 = getQuick(k - 1, m, 1);
/* 908 */         double d7 = getQuick(k - 1, m + 1, 0);double d8 = getQuick(k - 1, m + 1, 1);
/*     */         
/* 910 */         double d10 = getQuick(k - 1, m - 1, 0);double d11 = getQuick(k, m - 1, 1);
/* 911 */         double d13 = getQuick(k, m, 0);double d14 = getQuick(k, m, 1);
/* 912 */         double d16 = getQuick(k, m + 1, 0);double d17 = getQuick(k, m + 1, 1);
/*     */         
/* 914 */         double d19 = getQuick(k + 1, m - 1, 0);double d20 = getQuick(k + 1, m - 1, 1);
/* 915 */         double d22 = getQuick(k + 1, m, 0);double d23 = getQuick(k + 1, m, 1);
/* 916 */         double d25 = getQuick(k + 1, m + 1, 0);double d26 = getQuick(k + 1, m + 1, 1);
/*     */         
/* 918 */         for (int n = 1; n < j; n++)
/*     */         {
/*     */ 
/* 921 */           double d3 = getQuick(k - 1, m - 1, n + 1);
/* 922 */           double d6 = getQuick(k - 1, m, n + 1);
/* 923 */           double d9 = getQuick(k - 1, m + 1, n + 1);
/*     */           
/* 925 */           double d12 = getQuick(k, m - 1, n + 1);
/* 926 */           double d15 = getQuick(k, m, n + 1);
/* 927 */           double d18 = getQuick(k, m + 1, n + 1);
/*     */           
/* 929 */           double d21 = getQuick(k + 1, m - 1, n + 1);
/* 930 */           double d24 = getQuick(k + 1, m, n + 1);
/* 931 */           double d27 = getQuick(k + 1, m + 1, n + 1);
/*     */           
/* 933 */           paramDoubleMatrix3D.setQuick(k, m, n, paramDouble27Function.apply(d1, d2, d3, d4, d5, d6, d7, d8, d9, d10, d11, d12, d13, d14, d15, d16, d17, d18, d19, d20, d21, d22, d23, d24, d25, d26, d27));
/*     */           
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 946 */           d1 = d2;d2 = d3;
/* 947 */           d4 = d5;d5 = d6;
/* 948 */           d7 = d8;d8 = d9;
/*     */           
/* 950 */           d10 = d11;d11 = d12;
/* 951 */           d13 = d14;d14 = d15;
/* 952 */           d16 = d17;d17 = d18;
/*     */           
/* 954 */           d19 = d20;d20 = d21;
/* 955 */           d22 = d23;d23 = d24;
/* 956 */           d25 = d26;d26 = d27;
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double zSum()
/*     */   {
/* 966 */     if (size() == 0) return 0.0D;
/* 967 */     return aggregate(Functions.plus, Functions.identity);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/DoubleMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */