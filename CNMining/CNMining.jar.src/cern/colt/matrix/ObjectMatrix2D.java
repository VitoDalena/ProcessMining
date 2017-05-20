/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.ObjectFunction;
/*     */ import cern.colt.function.ObjectObjectFunction;
/*     */ import cern.colt.function.ObjectProcedure;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.objectalgo.Formatter;
/*     */ import cern.colt.matrix.objectalgo.Sorting;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ObjectMatrix2D
/*     */   extends AbstractMatrix2D
/*     */ {
/*     */   public Object aggregate(ObjectObjectFunction paramObjectObjectFunction, ObjectFunction paramObjectFunction)
/*     */   {
/*  56 */     if (size() == 0) return null;
/*  57 */     Object localObject = paramObjectFunction.apply(getQuick(this.rows - 1, this.columns - 1));
/*  58 */     int i = 1;
/*  59 */     int j = this.rows;
/*  60 */     do { int k = this.columns - i;
/*  61 */       do { localObject = paramObjectObjectFunction.apply(localObject, paramObjectFunction.apply(getQuick(j, k)));k--;
/*  60 */       } while (k >= 0);
/*     */       
/*     */ 
/*  63 */       i = 0;j--;
/*  59 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  65 */     return localObject;
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
/*     */   public Object aggregate(ObjectMatrix2D paramObjectMatrix2D, ObjectObjectFunction paramObjectObjectFunction1, ObjectObjectFunction paramObjectObjectFunction2)
/*     */   {
/*  99 */     checkShape(paramObjectMatrix2D);
/* 100 */     if (size() == 0) return null;
/* 101 */     Object localObject = paramObjectObjectFunction2.apply(getQuick(this.rows - 1, this.columns - 1), paramObjectMatrix2D.getQuick(this.rows - 1, this.columns - 1));
/* 102 */     int i = 1;
/* 103 */     int j = this.rows;
/* 104 */     do { int k = this.columns - i;
/* 105 */       do { localObject = paramObjectObjectFunction1.apply(localObject, paramObjectObjectFunction2.apply(getQuick(j, k), paramObjectMatrix2D.getQuick(j, k)));k--;
/* 104 */       } while (k >= 0);
/*     */       
/*     */ 
/* 107 */       i = 0;j--;
/* 103 */     } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 109 */     return localObject;
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
/*     */   public ObjectMatrix2D assign(Object[][] paramArrayOfObject)
/*     */   {
/* 123 */     if (paramArrayOfObject.length != this.rows) throw new IllegalArgumentException("Must have same number of rows: rows=" + paramArrayOfObject.length + "rows()=" + rows());
/* 124 */     int i = this.rows;
/* 125 */     do { Object[] arrayOfObject = paramArrayOfObject[i];
/* 126 */       if (arrayOfObject.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfObject.length + "columns()=" + columns());
/* 127 */       int j = this.columns;
/* 128 */       do { setQuick(i, j, arrayOfObject[j]);j--;
/* 127 */       } while (j >= 0);
/* 124 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */     return this;
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
/*     */   public ObjectMatrix2D assign(ObjectFunction paramObjectFunction)
/*     */   {
/* 156 */     int i = this.rows;
/* 157 */     do { int j = this.columns;
/* 158 */       do { setQuick(i, j, paramObjectFunction.apply(getQuick(i, j)));j--;
/* 157 */       } while (j >= 0);
/* 156 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 161 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D assign(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 173 */     if (paramObjectMatrix2D == this) return this;
/* 174 */     checkShape(paramObjectMatrix2D);
/* 175 */     if (haveSharedCells(paramObjectMatrix2D)) { paramObjectMatrix2D = paramObjectMatrix2D.copy();
/*     */     }
/* 177 */     int i = this.rows;
/* 178 */     do { int j = this.columns;
/* 179 */       do { setQuick(i, j, paramObjectMatrix2D.getQuick(i, j));j--;
/* 178 */       } while (j >= 0);
/* 177 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 182 */     return this;
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
/*     */   public ObjectMatrix2D assign(ObjectMatrix2D paramObjectMatrix2D, ObjectObjectFunction paramObjectObjectFunction)
/*     */   {
/* 214 */     checkShape(paramObjectMatrix2D);
/* 215 */     int i = this.rows;
/* 216 */     do { int j = this.columns;
/* 217 */       do { setQuick(i, j, paramObjectObjectFunction.apply(getQuick(i, j), paramObjectMatrix2D.getQuick(i, j)));j--;
/* 216 */       } while (j >= 0);
/* 215 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 220 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D assign(Object paramObject)
/*     */   {
/* 228 */     int i = this.rows;
/* 229 */     do { int j = this.columns;
/* 230 */       do { setQuick(i, j, paramObject);j--;
/* 229 */       } while (j >= 0);
/* 228 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 233 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 239 */     int i = 0;
/* 240 */     int j = this.rows;
/* 241 */     do { int k = this.columns;
/* 242 */       do { if (getQuick(j, k) != null) i++;
/* 241 */         k--; } while (k >= 0);
/* 240 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 245 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D copy()
/*     */   {
/* 256 */     return like().assign(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 266 */     return equals(paramObject, true);
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
/*     */   public boolean equals(Object paramObject, boolean paramBoolean)
/*     */   {
/* 284 */     if (!(paramObject instanceof ObjectMatrix2D)) return false;
/* 285 */     if (this == paramObject) return true;
/* 286 */     if (paramObject == null) return false;
/* 287 */     ObjectMatrix2D localObjectMatrix2D = (ObjectMatrix2D)paramObject;
/* 288 */     if (this.rows != localObjectMatrix2D.rows()) return false;
/* 289 */     if (this.columns != localObjectMatrix2D.columns()) return false;
/*     */     int i;
/* 291 */     int j; if (!paramBoolean) {
/* 292 */       i = this.rows;
/* 293 */       do { j = this.columns;
/* 294 */         do { if (getQuick(i, j) != localObjectMatrix2D.getQuick(i, j)) return false;
/* 293 */           j--; } while (j >= 0);
/* 292 */         i--; } while (i >= 0);
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 299 */       i = this.rows;
/* 300 */       do { j = this.columns;
/* 301 */         do { if (!(getQuick(i, j) == null ? false : localObjectMatrix2D.getQuick(i, j) == null ? true : getQuick(i, j).equals(localObjectMatrix2D.getQuick(i, j)))) return false;
/* 300 */           j--; } while (j >= 0);
/* 299 */         i--; } while (i >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 306 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(int paramInt1, int paramInt2)
/*     */   {
/* 318 */     if ((paramInt2 < 0) || (paramInt2 >= this.columns) || (paramInt1 < 0) || (paramInt1 >= this.rows)) throw new IndexOutOfBoundsException("row:" + paramInt1 + ", column:" + paramInt2);
/* 319 */     return getQuick(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix2D getContent()
/*     */   {
/* 326 */     return this;
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
/*     */   public void getNonZeros(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 356 */     paramIntArrayList1.clear();
/* 357 */     paramIntArrayList2.clear();
/* 358 */     paramObjectArrayList.clear();
/* 359 */     int i = this.rows;
/* 360 */     int j = this.columns;
/* 361 */     for (int k = 0; k < i; k++) {
/* 362 */       for (int m = 0; m < j; m++) {
/* 363 */         Object localObject = getQuick(k, m);
/* 364 */         if (localObject != null) {
/* 365 */           paramIntArrayList1.add(k);
/* 366 */           paramIntArrayList2.add(m);
/* 367 */           paramObjectArrayList.add(localObject);
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
/*     */   public abstract Object getQuick(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCells(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 388 */     if (paramObjectMatrix2D == null) return false;
/* 389 */     if (this == paramObjectMatrix2D) return true;
/* 390 */     return getContent().haveSharedCellsRaw(paramObjectMatrix2D.getContent());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/* 396 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D like()
/*     */   {
/* 407 */     return like(this.rows, this.columns);
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
/*     */   public abstract ObjectMatrix2D like(int paramInt1, int paramInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract ObjectMatrix1D like1D(int paramInt);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract ObjectMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 449 */     if ((paramInt2 < 0) || (paramInt2 >= this.columns) || (paramInt1 < 0) || (paramInt1 >= this.rows)) throw new IndexOutOfBoundsException("row:" + paramInt1 + ", column:" + paramInt2);
/* 450 */     setQuick(paramInt1, paramInt2, paramObject);
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
/*     */   public abstract void setQuick(int paramInt1, int paramInt2, Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[][] toArray()
/*     */   {
/* 474 */     Object[][] arrayOfObject = new Object[this.rows][this.columns];
/* 475 */     int i = this.rows;
/* 476 */     do { Object[] arrayOfObject1 = arrayOfObject[i];
/* 477 */       int j = this.columns;
/* 478 */       do { arrayOfObject1[j] = getQuick(i, j);j--;
/* 477 */       } while (j >= 0);
/* 475 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 481 */     return arrayOfObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 488 */     return new Formatter().toString(this);
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
/*     */   protected ObjectMatrix2D view()
/*     */   {
/* 502 */     return (ObjectMatrix2D)clone();
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
/*     */   public ObjectMatrix1D viewColumn(int paramInt)
/*     */   {
/* 527 */     checkColumn(paramInt);
/* 528 */     int i = this.rows;
/* 529 */     int j = index(0, paramInt);
/* 530 */     int k = this.rowStride;
/* 531 */     return like1D(i, j, k);
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
/*     */   public ObjectMatrix2D viewColumnFlip()
/*     */   {
/* 559 */     return (ObjectMatrix2D)view().vColumnFlip();
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
/*     */   public ObjectMatrix2D viewDice()
/*     */   {
/* 590 */     return (ObjectMatrix2D)view().vDice();
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
/*     */   public ObjectMatrix2D viewPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
/*     */   {
/* 616 */     return (ObjectMatrix2D)view().vPart(paramInt1, paramInt2, paramInt3, paramInt4);
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
/*     */   public ObjectMatrix1D viewRow(int paramInt)
/*     */   {
/* 641 */     checkRow(paramInt);
/* 642 */     int i = this.columns;
/* 643 */     int j = index(paramInt, 0);
/* 644 */     int k = this.columnStride;
/* 645 */     return like1D(i, j, k);
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
/*     */   public ObjectMatrix2D viewRowFlip()
/*     */   {
/* 673 */     return (ObjectMatrix2D)view().vRowFlip();
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
/*     */   public ObjectMatrix2D viewSelection(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 704 */     if (paramArrayOfInt1 == null) {
/* 705 */       paramArrayOfInt1 = new int[this.rows];
/* 706 */       i = this.rows; do { paramArrayOfInt1[i] = i;i--; } while (i >= 0);
/*     */     }
/* 708 */     if (paramArrayOfInt2 == null) {
/* 709 */       paramArrayOfInt2 = new int[this.columns];
/* 710 */       i = this.columns; do { paramArrayOfInt2[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 713 */     checkRowIndexes(paramArrayOfInt1);
/* 714 */     checkColumnIndexes(paramArrayOfInt2);
/* 715 */     int[] arrayOfInt1 = new int[paramArrayOfInt1.length];
/* 716 */     int[] arrayOfInt2 = new int[paramArrayOfInt2.length];
/* 717 */     int j = paramArrayOfInt1.length;
/* 718 */     do { arrayOfInt1[j] = _rowOffset(_rowRank(paramArrayOfInt1[j]));j--;
/* 717 */     } while (j >= 0);
/*     */     
/*     */ 
/* 720 */     int k = paramArrayOfInt2.length;
/* 721 */     do { arrayOfInt2[k] = _columnOffset(_columnRank(paramArrayOfInt2[k]));k--;
/* 720 */     } while (k >= 0);
/*     */     
/*     */ 
/* 723 */     return viewSelectionLike(arrayOfInt1, arrayOfInt2);
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
/*     */   public ObjectMatrix2D viewSelection(ObjectMatrix1DProcedure paramObjectMatrix1DProcedure)
/*     */   {
/* 758 */     IntArrayList localIntArrayList = new IntArrayList();
/* 759 */     for (int i = 0; i < this.rows; i++) {
/* 760 */       if (paramObjectMatrix1DProcedure.apply(viewRow(i))) { localIntArrayList.add(i);
/*     */       }
/*     */     }
/* 763 */     localIntArrayList.trimToSize();
/* 764 */     return viewSelection(localIntArrayList.elements(), null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract ObjectMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D viewSorted(int paramInt)
/*     */   {
/* 783 */     return Sorting.mergeSort.sort(this, paramInt);
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
/*     */   public ObjectMatrix2D viewStrides(int paramInt1, int paramInt2)
/*     */   {
/* 796 */     return (ObjectMatrix2D)view().vStrides(paramInt1, paramInt2);
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
/*     */   private boolean xforEach(ObjectProcedure paramObjectProcedure)
/*     */   {
/* 816 */     int i = this.rows;
/* 817 */     do { int j = this.columns;
/* 818 */       do { if (!paramObjectProcedure.apply(getQuick(i, j))) return false;
/* 817 */         j--; } while (j >= 0);
/* 816 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 821 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/ObjectMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */