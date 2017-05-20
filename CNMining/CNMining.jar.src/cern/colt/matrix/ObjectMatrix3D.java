/*     */ package cern.colt.matrix;
/*     */ 
/*     */ import cern.colt.PersistentObject;
/*     */ import cern.colt.function.IntIntIntProcedure;
/*     */ import cern.colt.function.ObjectFunction;
/*     */ import cern.colt.function.ObjectObjectFunction;
/*     */ import cern.colt.function.ObjectProcedure;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.impl.AbstractMatrix3D;
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
/*     */ 
/*     */ 
/*     */ public abstract class ObjectMatrix3D
/*     */   extends AbstractMatrix3D
/*     */ {
/*     */   public Object aggregate(ObjectObjectFunction paramObjectObjectFunction, ObjectFunction paramObjectFunction)
/*     */   {
/*  59 */     if (size() == 0) return null;
/*  60 */     Object localObject = paramObjectFunction.apply(getQuick(this.slices - 1, this.rows - 1, this.columns - 1));
/*  61 */     int i = 1;
/*  62 */     int j = this.slices;
/*  63 */     do { int k = this.rows;
/*  64 */       do { int m = this.columns - i;
/*  65 */         do { localObject = paramObjectObjectFunction.apply(localObject, paramObjectFunction.apply(getQuick(j, k, m)));m--;
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
/*  70 */     return localObject;
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
/*     */   public Object aggregate(ObjectMatrix3D paramObjectMatrix3D, ObjectObjectFunction paramObjectObjectFunction1, ObjectObjectFunction paramObjectObjectFunction2)
/*     */   {
/* 110 */     checkShape(paramObjectMatrix3D);
/* 111 */     if (size() == 0) return null;
/* 112 */     Object localObject = paramObjectObjectFunction2.apply(getQuick(this.slices - 1, this.rows - 1, this.columns - 1), paramObjectMatrix3D.getQuick(this.slices - 1, this.rows - 1, this.columns - 1));
/* 113 */     int i = 1;
/* 114 */     int j = this.slices;
/* 115 */     do { int k = this.rows;
/* 116 */       do { int m = this.columns - i;
/* 117 */         do { localObject = paramObjectObjectFunction1.apply(localObject, paramObjectObjectFunction2.apply(getQuick(j, k, m), paramObjectMatrix3D.getQuick(j, k, m)));m--;
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
/* 122 */     return localObject;
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
/*     */   public ObjectMatrix3D assign(Object[][][] paramArrayOfObject)
/*     */   {
/* 137 */     if (paramArrayOfObject.length != this.slices) throw new IllegalArgumentException("Must have same number of slices: slices=" + paramArrayOfObject.length + "slices()=" + slices());
/* 138 */     int i = this.slices;
/* 139 */     do { Object[][] arrayOfObject = paramArrayOfObject[i];
/* 140 */       if (arrayOfObject.length != this.rows) throw new IllegalArgumentException("Must have same number of rows in every slice: rows=" + arrayOfObject.length + "rows()=" + rows());
/* 141 */       int j = this.rows;
/* 142 */       do { Object[] arrayOfObject1 = arrayOfObject[j];
/* 143 */         if (arrayOfObject1.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfObject1.length + "columns()=" + columns());
/* 144 */         int k = this.columns;
/* 145 */         do { setQuick(i, j, k, arrayOfObject1[k]);k--;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D assign(ObjectFunction paramObjectFunction)
/*     */   {
/* 174 */     int i = this.slices;
/* 175 */     do { int j = this.rows;
/* 176 */       do { int k = this.columns;
/* 177 */         do { setQuick(i, j, k, paramObjectFunction.apply(getQuick(i, j, k)));k--;
/* 176 */         } while (k >= 0);
/* 175 */         j--; } while (j >= 0);
/* 174 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 181 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D assign(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/* 193 */     if (paramObjectMatrix3D == this) return this;
/* 194 */     checkShape(paramObjectMatrix3D);
/* 195 */     if (haveSharedCells(paramObjectMatrix3D)) { paramObjectMatrix3D = paramObjectMatrix3D.copy();
/*     */     }
/* 197 */     int i = this.slices;
/* 198 */     do { int j = this.rows;
/* 199 */       do { int k = this.columns;
/* 200 */         do { setQuick(i, j, k, paramObjectMatrix3D.getQuick(i, j, k));k--;
/* 199 */         } while (k >= 0);
/* 198 */         j--; } while (j >= 0);
/* 197 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 204 */     return this;
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
/*     */   public ObjectMatrix3D assign(ObjectMatrix3D paramObjectMatrix3D, ObjectObjectFunction paramObjectObjectFunction)
/*     */   {
/* 236 */     checkShape(paramObjectMatrix3D);
/* 237 */     int i = this.slices;
/* 238 */     do { int j = this.rows;
/* 239 */       do { int k = this.columns;
/* 240 */         do { setQuick(i, j, k, paramObjectObjectFunction.apply(getQuick(i, j, k), paramObjectMatrix3D.getQuick(i, j, k)));k--;
/* 239 */         } while (k >= 0);
/* 238 */         j--; } while (j >= 0);
/* 237 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 244 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D assign(Object paramObject)
/*     */   {
/* 252 */     int i = this.slices;
/* 253 */     do { int j = this.rows;
/* 254 */       do { int k = this.columns;
/* 255 */         do { setQuick(i, j, k, paramObject);k--;
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
/* 269 */         do { if (getQuick(j, k, m) != null) i++;
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
/*     */   public ObjectMatrix3D copy()
/*     */   {
/* 284 */     return like().assign(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object paramObject)
/*     */   {
/* 294 */     return equals(paramObject, true);
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
/* 312 */     if (!(paramObject instanceof ObjectMatrix3D)) return false;
/* 313 */     if (this == paramObject) return true;
/* 314 */     if (paramObject == null) return false;
/* 315 */     ObjectMatrix3D localObjectMatrix3D = (ObjectMatrix3D)paramObject;
/* 316 */     if (this.rows != localObjectMatrix3D.rows()) return false;
/* 317 */     if (this.columns != localObjectMatrix3D.columns()) return false;
/*     */     int i;
/* 319 */     int j; int k; if (!paramBoolean) {
/* 320 */       i = this.slices;
/* 321 */       do { j = this.rows;
/* 322 */         do { k = this.columns;
/* 323 */           do { if (getQuick(i, j, k) != localObjectMatrix3D.getQuick(i, j, k)) return false;
/* 322 */             k--; } while (k >= 0);
/* 321 */           j--; } while (j >= 0);
/* 320 */         i--; } while (i >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/* 329 */       i = this.slices;
/* 330 */       do { j = this.rows;
/* 331 */         do { k = this.columns;
/* 332 */           do { if (!(getQuick(i, j, k) == null ? false : localObjectMatrix3D.getQuick(i, j, k) == null ? true : getQuick(i, j, k).equals(localObjectMatrix3D.getQuick(i, j, k)))) return false;
/* 331 */             k--; } while (k >= 0);
/* 330 */           j--; } while (j >= 0);
/* 329 */         i--; } while (i >= 0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 338 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object get(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 350 */     if ((paramInt1 < 0) || (paramInt1 >= this.slices) || (paramInt2 < 0) || (paramInt2 >= this.rows) || (paramInt3 < 0) || (paramInt3 >= this.columns)) throw new IndexOutOfBoundsException("slice:" + paramInt1 + ", row:" + paramInt2 + ", column:" + paramInt3);
/* 351 */     return getQuick(paramInt1, paramInt2, paramInt3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix3D getContent()
/*     */   {
/* 358 */     return this;
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
/*     */   public void getNonZeros(IntArrayList paramIntArrayList1, IntArrayList paramIntArrayList2, IntArrayList paramIntArrayList3, ObjectArrayList paramObjectArrayList)
/*     */   {
/* 377 */     paramIntArrayList1.clear();
/* 378 */     paramIntArrayList2.clear();
/* 379 */     paramIntArrayList3.clear();
/* 380 */     paramObjectArrayList.clear();
/* 381 */     int i = this.slices;
/* 382 */     int j = this.rows;
/* 383 */     int k = this.columns;
/* 384 */     for (int m = 0; m < i; m++) {
/* 385 */       for (int n = 0; n < j; n++) {
/* 386 */         for (int i1 = 0; i1 < k; i1++) {
/* 387 */           Object localObject = getQuick(m, n, i1);
/* 388 */           if (localObject != null) {
/* 389 */             paramIntArrayList1.add(m);
/* 390 */             paramIntArrayList2.add(n);
/* 391 */             paramIntArrayList3.add(i1);
/* 392 */             paramObjectArrayList.add(localObject);
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
/*     */   public abstract Object getQuick(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCells(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/* 415 */     if (paramObjectMatrix3D == null) return false;
/* 416 */     if (this == paramObjectMatrix3D) return true;
/* 417 */     return getContent().haveSharedCellsRaw(paramObjectMatrix3D.getContent());
/*     */   }
/*     */   
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/* 423 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D like()
/*     */   {
/* 434 */     return like(this.slices, this.rows, this.columns);
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
/*     */   public abstract ObjectMatrix3D like(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract ObjectMatrix2D like2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void set(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
/*     */   {
/* 472 */     if ((paramInt1 < 0) || (paramInt1 >= this.slices) || (paramInt2 < 0) || (paramInt2 >= this.rows) || (paramInt3 < 0) || (paramInt3 >= this.columns)) throw new IndexOutOfBoundsException("slice:" + paramInt1 + ", row:" + paramInt2 + ", column:" + paramInt3);
/* 473 */     setQuick(paramInt1, paramInt2, paramInt3, paramObject);
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
/*     */   public abstract void setQuick(int paramInt1, int paramInt2, int paramInt3, Object paramObject);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object[][][] toArray()
/*     */   {
/* 498 */     Object[][][] arrayOfObject = new Object[this.slices][this.rows][this.columns];
/* 499 */     int i = this.slices;
/* 500 */     do { Object[][] arrayOfObject1 = arrayOfObject[i];
/* 501 */       int j = this.rows;
/* 502 */       do { Object[] arrayOfObject2 = arrayOfObject1[j];
/* 503 */         int k = this.columns;
/* 504 */         do { arrayOfObject2[k] = getQuick(i, j, k);k--;
/* 503 */         } while (k >= 0);
/* 501 */         j--; } while (j >= 0);
/* 499 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 508 */     return arrayOfObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 515 */     return new Formatter().toString(this);
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
/*     */   protected ObjectMatrix3D view()
/*     */   {
/* 529 */     return (ObjectMatrix3D)clone();
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
/*     */   public ObjectMatrix2D viewColumn(int paramInt)
/*     */   {
/* 546 */     checkColumn(paramInt);
/* 547 */     int i = this.slices;
/* 548 */     int j = this.rows;
/*     */     
/*     */ 
/* 551 */     int k = this.sliceZero;
/* 552 */     int m = this.rowZero + _columnOffset(_columnRank(paramInt));
/*     */     
/* 554 */     int n = this.sliceStride;
/* 555 */     int i1 = this.rowStride;
/* 556 */     return like2D(i, j, k, m, n, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D viewColumnFlip()
/*     */   {
/* 568 */     return (ObjectMatrix3D)view().vColumnFlip();
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
/*     */   public ObjectMatrix3D viewDice(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 582 */     return (ObjectMatrix3D)view().vDice(paramInt1, paramInt2, paramInt3);
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
/*     */   public ObjectMatrix3D viewPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 600 */     return (ObjectMatrix3D)view().vPart(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
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
/*     */   public ObjectMatrix2D viewRow(int paramInt)
/*     */   {
/* 617 */     checkRow(paramInt);
/* 618 */     int i = this.slices;
/* 619 */     int j = this.columns;
/*     */     
/*     */ 
/* 622 */     int k = this.sliceZero;
/* 623 */     int m = this.columnZero + _rowOffset(_rowRank(paramInt));
/*     */     
/* 625 */     int n = this.sliceStride;
/* 626 */     int i1 = this.columnStride;
/* 627 */     return like2D(i, j, k, m, n, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D viewRowFlip()
/*     */   {
/* 639 */     return (ObjectMatrix3D)view().vRowFlip();
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
/*     */   public ObjectMatrix3D viewSelection(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
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
/* 661 */     if (paramArrayOfInt1 == null) {
/* 662 */       paramArrayOfInt1 = new int[this.slices];
/* 663 */       i = this.slices; do { paramArrayOfInt1[i] = i;i--; } while (i >= 0);
/*     */     }
/* 665 */     if (paramArrayOfInt2 == null) {
/* 666 */       paramArrayOfInt2 = new int[this.rows];
/* 667 */       i = this.rows; do { paramArrayOfInt2[i] = i;i--; } while (i >= 0);
/*     */     }
/* 669 */     if (paramArrayOfInt3 == null) {
/* 670 */       paramArrayOfInt3 = new int[this.columns];
/* 671 */       i = this.columns; do { paramArrayOfInt3[i] = i;i--; } while (i >= 0);
/*     */     }
/*     */     
/* 674 */     checkSliceIndexes(paramArrayOfInt1);
/* 675 */     checkRowIndexes(paramArrayOfInt2);
/* 676 */     checkColumnIndexes(paramArrayOfInt3);
/*     */     
/* 678 */     int[] arrayOfInt1 = new int[paramArrayOfInt1.length];
/* 679 */     int[] arrayOfInt2 = new int[paramArrayOfInt2.length];
/* 680 */     int[] arrayOfInt3 = new int[paramArrayOfInt3.length];
/*     */     
/* 682 */     int j = paramArrayOfInt1.length;
/* 683 */     do { arrayOfInt1[j] = _sliceOffset(_sliceRank(paramArrayOfInt1[j]));j--;
/* 682 */     } while (j >= 0);
/*     */     
/*     */ 
/* 685 */     int k = paramArrayOfInt2.length;
/* 686 */     do { arrayOfInt2[k] = _rowOffset(_rowRank(paramArrayOfInt2[k]));k--;
/* 685 */     } while (k >= 0);
/*     */     
/*     */ 
/* 688 */     int m = paramArrayOfInt3.length;
/* 689 */     do { arrayOfInt3[m] = _columnOffset(_columnRank(paramArrayOfInt3[m]));m--;
/* 688 */     } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/* 692 */     return viewSelectionLike(arrayOfInt1, arrayOfInt2, arrayOfInt3);
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
/*     */   public ObjectMatrix3D viewSelection(ObjectMatrix2DProcedure paramObjectMatrix2DProcedure)
/*     */   {
/* 716 */     IntArrayList localIntArrayList = new IntArrayList();
/* 717 */     for (int i = 0; i < this.slices; i++) {
/* 718 */       if (paramObjectMatrix2DProcedure.apply(viewSlice(i))) { localIntArrayList.add(i);
/*     */       }
/*     */     }
/* 721 */     localIntArrayList.trimToSize();
/* 722 */     return viewSelection(localIntArrayList.elements(), null, null);
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
/*     */   protected abstract ObjectMatrix3D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D viewSlice(int paramInt)
/*     */   {
/* 748 */     checkSlice(paramInt);
/* 749 */     int i = this.rows;
/* 750 */     int j = this.columns;
/*     */     
/*     */ 
/* 753 */     int k = this.rowZero;
/* 754 */     int m = this.columnZero + _sliceOffset(_sliceRank(paramInt));
/*     */     
/* 756 */     int n = this.rowStride;
/* 757 */     int i1 = this.columnStride;
/* 758 */     return like2D(i, j, k, m, n, i1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D viewSliceFlip()
/*     */   {
/* 770 */     return (ObjectMatrix3D)view().vSliceFlip();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix3D viewSorted(int paramInt1, int paramInt2)
/*     */   {
/* 781 */     return Sorting.mergeSort.sort(this, paramInt1, paramInt2);
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
/*     */   public ObjectMatrix3D viewStrides(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 796 */     return (ObjectMatrix3D)view().vStrides(paramInt1, paramInt2, paramInt3);
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
/*     */   private boolean xforEach(ObjectProcedure paramObjectProcedure)
/*     */   {
/* 818 */     int i = this.slices;
/* 819 */     do { int j = this.rows;
/* 820 */       do { int k = this.columns;
/* 821 */         do { if (!paramObjectProcedure.apply(getQuick(i, j, k))) return false;
/* 820 */           k--; } while (k >= 0);
/* 819 */         j--; } while (j >= 0);
/* 818 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 825 */     return true;
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
/* 847 */     int i = this.columns;
/* 848 */     do { int j = this.slices;
/* 849 */       do { int k = this.rows;
/* 850 */         do { if (!paramIntIntIntProcedure.apply(j, k, i)) return false;
/* 849 */           k--; } while (k >= 0);
/* 848 */         j--; } while (j >= 0);
/* 847 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 854 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/ObjectMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */