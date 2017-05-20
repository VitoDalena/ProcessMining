/*      */ package cern.colt.matrix;
/*      */ 
/*      */ import cern.colt.PersistentObject;
/*      */ import cern.colt.function.Double9Function;
/*      */ import cern.colt.function.DoubleDoubleFunction;
/*      */ import cern.colt.function.DoubleFunction;
/*      */ import cern.colt.function.DoubleProcedure;
/*      */ import cern.colt.function.IntIntDoubleFunction;
/*      */ import cern.colt.list.AbstractList;
/*      */ import cern.colt.list.DoubleArrayList;
/*      */ import cern.colt.list.IntArrayList;
/*      */ import cern.colt.matrix.doublealgo.Formatter;
/*      */ import cern.colt.matrix.doublealgo.Sorting;
/*      */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*      */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*      */ import cern.colt.matrix.impl.DenseDoubleMatrix1D;
/*      */ import cern.colt.matrix.impl.DenseDoubleMatrix2D;
/*      */ import cern.colt.matrix.linalg.Property;
/*      */ import cern.jet.math.Functions;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class DoubleMatrix2D
/*      */   extends AbstractMatrix2D
/*      */ {
/*      */   public double aggregate(DoubleDoubleFunction paramDoubleDoubleFunction, DoubleFunction paramDoubleFunction)
/*      */   {
/*   56 */     if (size() == 0) return NaN.0D;
/*   57 */     double d = paramDoubleFunction.apply(getQuick(this.rows - 1, this.columns - 1));
/*   58 */     int i = 1;
/*   59 */     int j = this.rows;
/*   60 */     do { int k = this.columns - i;
/*   61 */       do { d = paramDoubleDoubleFunction.apply(d, paramDoubleFunction.apply(getQuick(j, k)));k--;
/*   60 */       } while (k >= 0);
/*      */       
/*      */ 
/*   63 */       i = 0;j--;
/*   59 */     } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*   65 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double aggregate(DoubleMatrix2D paramDoubleMatrix2D, DoubleDoubleFunction paramDoubleDoubleFunction1, DoubleDoubleFunction paramDoubleDoubleFunction2)
/*      */   {
/*   99 */     checkShape(paramDoubleMatrix2D);
/*  100 */     if (size() == 0) return NaN.0D;
/*  101 */     double d = paramDoubleDoubleFunction2.apply(getQuick(this.rows - 1, this.columns - 1), paramDoubleMatrix2D.getQuick(this.rows - 1, this.columns - 1));
/*  102 */     int i = 1;
/*  103 */     int j = this.rows;
/*  104 */     do { int k = this.columns - i;
/*  105 */       do { d = paramDoubleDoubleFunction1.apply(d, paramDoubleDoubleFunction2.apply(getQuick(j, k), paramDoubleMatrix2D.getQuick(j, k)));k--;
/*  104 */       } while (k >= 0);
/*      */       
/*      */ 
/*  107 */       i = 0;j--;
/*  103 */     } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  109 */     return d;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D assign(double[][] paramArrayOfDouble)
/*      */   {
/*  123 */     if (paramArrayOfDouble.length != this.rows) throw new IllegalArgumentException("Must have same number of rows: rows=" + paramArrayOfDouble.length + "rows()=" + rows());
/*  124 */     int i = this.rows;
/*  125 */     do { double[] arrayOfDouble = paramArrayOfDouble[i];
/*  126 */       if (arrayOfDouble.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfDouble.length + "columns()=" + columns());
/*  127 */       int j = this.columns;
/*  128 */       do { setQuick(i, j, arrayOfDouble[j]);j--;
/*  127 */       } while (j >= 0);
/*  124 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  131 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D assign(double paramDouble)
/*      */   {
/*  139 */     int i = this.rows;
/*  140 */     int j = this.columns;
/*      */     
/*      */ 
/*  143 */     for (int k = 0; k < i; k++) {
/*  144 */       for (int m = 0; m < j; m++) {
/*  145 */         setQuick(k, m, paramDouble);
/*      */       }
/*      */     }
/*  148 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D assign(DoubleFunction paramDoubleFunction)
/*      */   {
/*  173 */     int i = this.rows;
/*  174 */     do { int j = this.columns;
/*  175 */       do { setQuick(i, j, paramDoubleFunction.apply(getQuick(i, j)));j--;
/*  174 */       } while (j >= 0);
/*  173 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  178 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  190 */     if (paramDoubleMatrix2D == this) return this;
/*  191 */     checkShape(paramDoubleMatrix2D);
/*  192 */     if (haveSharedCells(paramDoubleMatrix2D)) { paramDoubleMatrix2D = paramDoubleMatrix2D.copy();
/*      */     }
/*      */     
/*      */ 
/*  196 */     int i = this.rows;
/*  197 */     do { int j = this.columns;
/*  198 */       do { setQuick(i, j, paramDoubleMatrix2D.getQuick(i, j));j--;
/*  197 */       } while (j >= 0);
/*  196 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  201 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleDoubleFunction paramDoubleDoubleFunction)
/*      */   {
/*  233 */     checkShape(paramDoubleMatrix2D);
/*  234 */     int i = this.rows;
/*  235 */     do { int j = this.columns;
/*  236 */       do { setQuick(i, j, paramDoubleDoubleFunction.apply(getQuick(i, j), paramDoubleMatrix2D.getQuick(i, j)));j--;
/*  235 */       } while (j >= 0);
/*  234 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  239 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */   public int cardinality()
/*      */   {
/*  245 */     int i = 0;
/*  246 */     int j = this.rows;
/*  247 */     do { int k = this.columns;
/*  248 */       do { if (getQuick(j, k) != 0.0D) i++;
/*  247 */         k--; } while (k >= 0);
/*  246 */       j--; } while (j >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  251 */     return i;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D copy()
/*      */   {
/*  262 */     return like().assign(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(double paramDouble)
/*      */   {
/*  271 */     return Property.DEFAULT.equals(this, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object paramObject)
/*      */   {
/*  284 */     if (this == paramObject) return true;
/*  285 */     if (paramObject == null) return false;
/*  286 */     if (!(paramObject instanceof DoubleMatrix2D)) { return false;
/*      */     }
/*  288 */     return Property.DEFAULT.equals(this, (DoubleMatrix2D)paramObject);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D forEachNonZero(IntIntDoubleFunction paramIntIntDoubleFunction)
/*      */   {
/*  301 */     int i = this.rows;
/*  302 */     do { int j = this.columns;
/*  303 */       do { double d1 = getQuick(i, j);
/*  304 */         if (d1 != 0.0D) {
/*  305 */           double d2 = paramIntIntDoubleFunction.apply(i, j, d1);
/*  306 */           if (d2 != d1) setQuick(i, j, d2);
/*      */         }
/*  302 */         j--; } while (j >= 0);
/*  301 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  310 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double get(int paramInt1, int paramInt2)
/*      */   {
/*  321 */     if ((paramInt2 < 0) || (paramInt2 >= this.columns) || (paramInt1 < 0) || (paramInt1 >= this.rows)) throw new IndexOutOfBoundsException("row:" + paramInt1 + ", column:" + paramInt2);
/*  322 */     return getQuick(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected DoubleMatrix2D getContent()
/*      */   {
/*  329 */     return this;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void getNonZeros(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2, DoubleArrayList paramDoubleArrayList)
/*      */   {
/*  359 */     paramIntArrayList1.clear();
/*  360 */     paramIntArrayList2.clear();
/*  361 */     paramDoubleArrayList.clear();
/*  362 */     int i = this.rows;
/*  363 */     int j = this.columns;
/*  364 */     for (int k = 0; k < i; k++) {
/*  365 */       for (int m = 0; m < j; m++) {
/*  366 */         double d = getQuick(k, m);
/*  367 */         if (d != 0.0D) {
/*  368 */           paramIntArrayList1.add(k);
/*  369 */           paramIntArrayList2.add(m);
/*  370 */           paramDoubleArrayList.add(d);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract double getQuick(int paramInt1, int paramInt2);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected boolean haveSharedCells(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  391 */     if (paramDoubleMatrix2D == null) return false;
/*  392 */     if (this == paramDoubleMatrix2D) return true;
/*  393 */     return getContent().haveSharedCellsRaw(paramDoubleMatrix2D.getContent());
/*      */   }
/*      */   
/*      */ 
/*      */   protected boolean haveSharedCellsRaw(DoubleMatrix2D paramDoubleMatrix2D)
/*      */   {
/*  399 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D like()
/*      */   {
/*  410 */     return like(this.rows, this.columns);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract DoubleMatrix2D like(int paramInt1, int paramInt2);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract DoubleMatrix1D like1D(int paramInt);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected abstract DoubleMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void set(int paramInt1, int paramInt2, double paramDouble)
/*      */   {
/*  452 */     if ((paramInt2 < 0) || (paramInt2 >= this.columns) || (paramInt1 < 0) || (paramInt1 >= this.rows)) throw new IndexOutOfBoundsException("row:" + paramInt1 + ", column:" + paramInt2);
/*  453 */     setQuick(paramInt1, paramInt2, paramDouble);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void setQuick(int paramInt1, int paramInt2, double paramDouble);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double[][] toArray()
/*      */   {
/*  477 */     double[][] arrayOfDouble = new double[this.rows][this.columns];
/*  478 */     int i = this.rows;
/*  479 */     do { double[] arrayOfDouble1 = arrayOfDouble[i];
/*  480 */       int j = this.columns;
/*  481 */       do { arrayOfDouble1[j] = getQuick(i, j);j--;
/*  480 */       } while (j >= 0);
/*  478 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  484 */     return arrayOfDouble;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public String toString()
/*      */   {
/*  491 */     return new Formatter().toString(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected DoubleMatrix2D view()
/*      */   {
/*  505 */     return (DoubleMatrix2D)clone();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix1D viewColumn(int paramInt)
/*      */   {
/*  530 */     checkColumn(paramInt);
/*  531 */     int i = this.rows;
/*  532 */     int j = index(0, paramInt);
/*  533 */     int k = this.rowStride;
/*  534 */     return like1D(i, j, k);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewColumnFlip()
/*      */   {
/*  562 */     return (DoubleMatrix2D)view().vColumnFlip();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewDice()
/*      */   {
/*  593 */     return (DoubleMatrix2D)view().vDice();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*      */   {
/*  619 */     return (DoubleMatrix2D)view().vPart(paramInt1, paramInt2, paramInt3, paramInt4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix1D viewRow(int paramInt)
/*      */   {
/*  644 */     checkRow(paramInt);
/*  645 */     int i = this.columns;
/*  646 */     int j = index(paramInt, 0);
/*  647 */     int k = this.columnStride;
/*  648 */     return like1D(i, j, k);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewRowFlip()
/*      */   {
/*  676 */     return (DoubleMatrix2D)view().vRowFlip();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewSelection(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*      */   {
/*      */     int i;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  707 */     if (paramArrayOfInt1 == null) {
/*  708 */       paramArrayOfInt1 = new int[this.rows];
/*  709 */       i = this.rows; do { paramArrayOfInt1[i] = i;i--; } while (i >= 0);
/*      */     }
/*  711 */     if (paramArrayOfInt2 == null) {
/*  712 */       paramArrayOfInt2 = new int[this.columns];
/*  713 */       i = this.columns; do { paramArrayOfInt2[i] = i;i--; } while (i >= 0);
/*      */     }
/*      */     
/*  716 */     checkRowIndexes(paramArrayOfInt1);
/*  717 */     checkColumnIndexes(paramArrayOfInt2);
/*  718 */     int[] arrayOfInt1 = new int[paramArrayOfInt1.length];
/*  719 */     int[] arrayOfInt2 = new int[paramArrayOfInt2.length];
/*  720 */     int j = paramArrayOfInt1.length;
/*  721 */     do { arrayOfInt1[j] = _rowOffset(_rowRank(paramArrayOfInt1[j]));j--;
/*  720 */     } while (j >= 0);
/*      */     
/*      */ 
/*  723 */     int k = paramArrayOfInt2.length;
/*  724 */     do { arrayOfInt2[k] = _columnOffset(_columnRank(paramArrayOfInt2[k]));k--;
/*  723 */     } while (k >= 0);
/*      */     
/*      */ 
/*  726 */     return viewSelectionLike(arrayOfInt1, arrayOfInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewSelection(DoubleMatrix1DProcedure paramDoubleMatrix1DProcedure)
/*      */   {
/*  761 */     IntArrayList localIntArrayList = new IntArrayList();
/*  762 */     for (int i = 0; i < this.rows; i++) {
/*  763 */       if (paramDoubleMatrix1DProcedure.apply(viewRow(i))) { localIntArrayList.add(i);
/*      */       }
/*      */     }
/*  766 */     localIntArrayList.trimToSize();
/*  767 */     return viewSelection(localIntArrayList.elements(), null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected abstract DoubleMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewSorted(int paramInt)
/*      */   {
/*  786 */     return Sorting.mergeSort.sort(this, paramInt);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D viewStrides(int paramInt1, int paramInt2)
/*      */   {
/*  799 */     return (DoubleMatrix2D)view().vStrides(paramInt1, paramInt2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean xforEach(DoubleProcedure paramDoubleProcedure)
/*      */   {
/*  819 */     int i = this.rows;
/*  820 */     do { int j = this.columns;
/*  821 */       do { if (!paramDoubleProcedure.apply(getQuick(i, j))) return false;
/*  820 */         j--; } while (j >= 0);
/*  819 */       i--; } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  824 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zAssign8Neighbors(DoubleMatrix2D paramDoubleMatrix2D, Double9Function paramDouble9Function)
/*      */   {
/*  881 */     if (paramDouble9Function == null) throw new NullPointerException("function must not be null.");
/*  882 */     checkShape(paramDoubleMatrix2D);
/*  883 */     if ((this.rows < 3) || (this.columns < 3)) return;
/*  884 */     int i = this.rows - 1;
/*  885 */     int j = this.columns - 1;
/*      */     
/*      */ 
/*      */ 
/*  889 */     for (int k = 1; k < i; k++) {
/*  890 */       double d1 = getQuick(k - 1, 0);double d2 = getQuick(k - 1, 1);
/*  891 */       double d4 = getQuick(k, 0);double d5 = getQuick(k, 1);
/*  892 */       double d7 = getQuick(k + 1, 0);double d8 = getQuick(k + 1, 1);
/*      */       
/*  894 */       for (int m = 1; m < j; m++)
/*      */       {
/*      */ 
/*  897 */         double d3 = getQuick(k - 1, m + 1);
/*  898 */         double d6 = getQuick(k, m + 1);
/*  899 */         double d9 = getQuick(k + 1, m + 1);
/*      */         
/*  901 */         paramDoubleMatrix2D.setQuick(k, m, paramDouble9Function.apply(d1, d2, d3, d4, d5, d6, d7, d8, d9));
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*  906 */         d1 = d2;
/*  907 */         d4 = d5;
/*  908 */         d7 = d8;
/*      */         
/*  910 */         d2 = d3;
/*  911 */         d5 = d6;
/*  912 */         d8 = d9;
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DoubleMatrix1D zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2)
/*      */   {
/*  921 */     return zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, 1.0D, paramDoubleMatrix1D2 == null ? 1.0D : 0.0D, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix1D zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2, boolean paramBoolean)
/*      */   {
/*  937 */     if (paramBoolean) { return viewDice().zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, false);
/*      */     }
/*  939 */     if (paramDoubleMatrix1D2 == null) paramDoubleMatrix1D2 = new DenseDoubleMatrix1D(this.rows);
/*  940 */     if ((this.columns != paramDoubleMatrix1D1.size()) || (this.rows > paramDoubleMatrix1D2.size())) {
/*  941 */       throw new IllegalArgumentException("Incompatible args: " + toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*      */     }
/*  943 */     int i = this.rows;
/*  944 */     do { double d = 0.0D;
/*  945 */       int j = this.columns;
/*  946 */       do { d += getQuick(i, j) * paramDoubleMatrix1D1.getQuick(j);j--;
/*  945 */       } while (j >= 0);
/*      */       
/*      */ 
/*  948 */       paramDoubleMatrix1D2.setQuick(i, paramDouble1 * d + paramDouble2 * paramDoubleMatrix1D2.getQuick(i));i--;
/*  943 */     } while (i >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  950 */     return paramDoubleMatrix1D2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D zMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2)
/*      */   {
/*  957 */     return zMult(paramDoubleMatrix2D1, paramDoubleMatrix2D2, 1.0D, paramDoubleMatrix2D2 == null ? 1.0D : 0.0D, false, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DoubleMatrix2D zMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2)
/*      */   {
/*  976 */     if (paramBoolean1) return viewDice().zMult(paramDoubleMatrix2D1, paramDoubleMatrix2D2, paramDouble1, paramDouble2, false, paramBoolean2);
/*  977 */     if (paramBoolean2) { return zMult(paramDoubleMatrix2D1.viewDice(), paramDoubleMatrix2D2, paramDouble1, paramDouble2, paramBoolean1, false);
/*      */     }
/*  979 */     int i = this.rows;
/*  980 */     int j = this.columns;
/*  981 */     int k = paramDoubleMatrix2D1.columns;
/*      */     
/*  983 */     if (paramDoubleMatrix2D2 == null) paramDoubleMatrix2D2 = new DenseDoubleMatrix2D(i, k);
/*  984 */     if (paramDoubleMatrix2D1.rows != j)
/*  985 */       throw new IllegalArgumentException("Matrix2D inner dimensions must agree:" + toStringShort() + ", " + paramDoubleMatrix2D1.toStringShort());
/*  986 */     if ((paramDoubleMatrix2D2.rows != i) || (paramDoubleMatrix2D2.columns != k))
/*  987 */       throw new IllegalArgumentException("Incompatibel result matrix: " + toStringShort() + ", " + paramDoubleMatrix2D1.toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort());
/*  988 */     if ((this == paramDoubleMatrix2D2) || (paramDoubleMatrix2D1 == paramDoubleMatrix2D2)) {
/*  989 */       throw new IllegalArgumentException("Matrices must not be identical");
/*      */     }
/*  991 */     int m = k;
/*  992 */     do { int n = i;
/*  993 */       do { double d = 0.0D;
/*  994 */         int i1 = j;
/*  995 */         do { d += getQuick(n, i1) * paramDoubleMatrix2D1.getQuick(i1, m);i1--;
/*  994 */         } while (i1 >= 0);
/*      */         
/*      */ 
/*  997 */         paramDoubleMatrix2D2.setQuick(n, m, paramDouble1 * d + paramDouble2 * paramDoubleMatrix2D2.getQuick(n, m));n--;
/*  992 */       } while (n >= 0);
/*  991 */       m--; } while (m >= 0);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1000 */     return paramDoubleMatrix2D2;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public double zSum()
/*      */   {
/* 1007 */     if (size() == 0) return 0.0D;
/* 1008 */     return aggregate(Functions.plus, Functions.identity);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/DoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */