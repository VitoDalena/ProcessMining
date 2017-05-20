/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.matrix.ObjectMatrix2D;
/*     */ import cern.colt.matrix.ObjectMatrix3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DenseObjectMatrix3D
/*     */   extends ObjectMatrix3D
/*     */ {
/*     */   protected Object[] elements;
/*     */   
/*     */   public DenseObjectMatrix3D(Object[][][] paramArrayOfObject)
/*     */   {
/*  78 */     this(paramArrayOfObject.length, paramArrayOfObject.length == 0 ? 0 : paramArrayOfObject[0].length, paramArrayOfObject[0].length == 0 ? 0 : paramArrayOfObject.length == 0 ? 0 : paramArrayOfObject[0][0].length);
/*  79 */     assign(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DenseObjectMatrix3D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/*  91 */     setUp(paramInt1, paramInt2, paramInt3);
/*  92 */     this.elements = new Object[paramInt1 * paramInt2 * paramInt3];
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
/*     */   protected DenseObjectMatrix3D(int paramInt1, int paramInt2, int paramInt3, Object[] paramArrayOfObject, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9)
/*     */   {
/* 110 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7, paramInt8, paramInt9);
/* 111 */     this.elements = paramArrayOfObject;
/* 112 */     this.isNoView = false;
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
/* 127 */     if (this.isNoView) {
/* 128 */       if (paramArrayOfObject.length != this.slices) throw new IllegalArgumentException("Must have same number of slices: slices=" + paramArrayOfObject.length + "slices()=" + slices());
/* 129 */       int i = this.slices * this.rows * this.columns - this.columns;
/* 130 */       int j = this.slices;
/* 131 */       do { Object[][] arrayOfObject = paramArrayOfObject[j];
/* 132 */         if (arrayOfObject.length != this.rows) throw new IllegalArgumentException("Must have same number of rows in every slice: rows=" + arrayOfObject.length + "rows()=" + rows());
/* 133 */         int k = this.rows;
/* 134 */         do { Object[] arrayOfObject1 = arrayOfObject[k];
/* 135 */           if (arrayOfObject1.length != this.columns) throw new IllegalArgumentException("Must have same number of columns in every row: columns=" + arrayOfObject1.length + "columns()=" + columns());
/* 136 */           System.arraycopy(arrayOfObject1, 0, this.elements, i, this.columns);
/* 137 */           i -= this.columns;k--;
/* 133 */         } while (k >= 0);
/* 130 */         j--; } while (j >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 142 */       super.assign(paramArrayOfObject);
/*     */     }
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
/*     */ 
/*     */   public ObjectMatrix3D assign(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/* 157 */     if (!(paramObjectMatrix3D instanceof DenseObjectMatrix3D)) {
/* 158 */       return super.assign(paramObjectMatrix3D);
/*     */     }
/* 160 */     DenseObjectMatrix3D localDenseObjectMatrix3D = (DenseObjectMatrix3D)paramObjectMatrix3D;
/* 161 */     if (localDenseObjectMatrix3D == this) return this;
/* 162 */     checkShape(localDenseObjectMatrix3D);
/* 163 */     if (haveSharedCells(localDenseObjectMatrix3D)) {
/* 164 */       ObjectMatrix3D localObjectMatrix3D = localDenseObjectMatrix3D.copy();
/* 165 */       if (!(localObjectMatrix3D instanceof DenseObjectMatrix3D)) {
/* 166 */         return super.assign(paramObjectMatrix3D);
/*     */       }
/* 168 */       localDenseObjectMatrix3D = (DenseObjectMatrix3D)localObjectMatrix3D;
/*     */     }
/*     */     
/* 171 */     if ((this.isNoView) && (localDenseObjectMatrix3D.isNoView)) {
/* 172 */       System.arraycopy(localDenseObjectMatrix3D.elements, 0, this.elements, 0, this.elements.length);
/* 173 */       return this;
/*     */     }
/* 175 */     return super.assign(localDenseObjectMatrix3D);
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
/*     */   public Object getQuick(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 193 */     return this.elements[(this.sliceZero + paramInt1 * this.sliceStride + this.rowZero + paramInt2 * this.rowStride + this.columnZero + paramInt3 * this.columnStride)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix3D paramObjectMatrix3D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 205 */     if ((paramObjectMatrix3D instanceof SelectedDenseObjectMatrix3D)) {
/* 206 */       localObject = (SelectedDenseObjectMatrix3D)paramObjectMatrix3D;
/* 207 */       return this.elements == ((SelectedDenseObjectMatrix3D)localObject).elements;
/*     */     }
/* 209 */     if ((paramObjectMatrix3D instanceof DenseObjectMatrix3D)) {
/* 210 */       localObject = (DenseObjectMatrix3D)paramObjectMatrix3D;
/* 211 */       return this.elements == ((DenseObjectMatrix3D)localObject).elements;
/*     */     }
/* 213 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 225 */     return this.sliceZero + paramInt1 * this.sliceStride + this.rowZero + paramInt2 * this.rowStride + this.columnZero + paramInt3 * this.columnStride;
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
/*     */   public ObjectMatrix3D like(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 239 */     return new DenseObjectMatrix3D(paramInt1, paramInt2, paramInt3);
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
/*     */   protected ObjectMatrix2D like2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 255 */     return new DenseObjectMatrix2D(paramInt1, paramInt2, this.elements, paramInt3, paramInt4, paramInt5, paramInt6);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, int paramInt3, Object paramObject)
/*     */   {
/* 273 */     this.elements[(this.sliceZero + paramInt1 * this.sliceStride + this.rowZero + paramInt2 * this.rowStride + this.columnZero + paramInt3 * this.columnStride)] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix3D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/* 284 */     return new SelectedDenseObjectMatrix3D(this.elements, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, 0);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/DenseObjectMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */