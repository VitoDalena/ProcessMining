/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.map.AbstractIntDoubleMap;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SelectedSparseDoubleMatrix3D
/*     */   extends DoubleMatrix3D
/*     */ {
/*     */   protected AbstractIntDoubleMap elements;
/*     */   protected int[] sliceOffsets;
/*     */   protected int[] rowOffsets;
/*     */   protected int[] columnOffsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedSparseDoubleMatrix3D(AbstractIntDoubleMap paramAbstractIntDoubleMap, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt)
/*     */   {
/*  72 */     int i = paramArrayOfInt1.length;
/*  73 */     int j = paramArrayOfInt2.length;
/*  74 */     int k = paramArrayOfInt3.length;
/*  75 */     setUp(i, j, k);
/*     */     
/*  77 */     this.elements = paramAbstractIntDoubleMap;
/*     */     
/*  79 */     this.sliceOffsets = paramArrayOfInt1;
/*  80 */     this.rowOffsets = paramArrayOfInt2;
/*  81 */     this.columnOffsets = paramArrayOfInt3;
/*     */     
/*  83 */     this.offset = paramInt;
/*     */     
/*  85 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnOffset(int paramInt)
/*     */   {
/*  95 */     return this.columnOffsets[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowOffset(int paramInt)
/*     */   {
/* 105 */     return this.rowOffsets[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _sliceOffset(int paramInt)
/*     */   {
/* 115 */     return this.sliceOffsets[paramInt];
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
/*     */   public double getQuick(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 133 */     return this.elements.get(this.offset + this.sliceOffsets[(this.sliceZero + paramInt1 * this.sliceStride)] + this.rowOffsets[(this.rowZero + paramInt2 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt3 * this.columnStride)]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix3D paramDoubleMatrix3D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 145 */     if ((paramDoubleMatrix3D instanceof SelectedSparseDoubleMatrix3D)) {
/* 146 */       localObject = (SelectedSparseDoubleMatrix3D)paramDoubleMatrix3D;
/* 147 */       return this.elements == ((SelectedSparseDoubleMatrix3D)localObject).elements;
/*     */     }
/* 149 */     if ((paramDoubleMatrix3D instanceof SparseDoubleMatrix3D)) {
/* 150 */       localObject = (SparseDoubleMatrix3D)paramDoubleMatrix3D;
/* 151 */       return this.elements == ((SparseDoubleMatrix3D)localObject).elements;
/*     */     }
/* 153 */     return false;
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
/* 165 */     return this.offset + this.sliceOffsets[(this.sliceZero + paramInt1 * this.sliceStride)] + this.rowOffsets[(this.rowZero + paramInt2 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt3 * this.columnStride)];
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
/*     */   public DoubleMatrix3D like(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 179 */     return new SparseDoubleMatrix3D(paramInt1, paramInt2, paramInt3);
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
/*     */   protected DoubleMatrix2D like2D(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 195 */     throw new InternalError();
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
/*     */   public void setQuick(int paramInt1, int paramInt2, int paramInt3, double paramDouble)
/*     */   {
/* 213 */     int i = this.offset + this.sliceOffsets[(this.sliceZero + paramInt1 * this.sliceStride)] + this.rowOffsets[(this.rowZero + paramInt2 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt3 * this.columnStride)];
/* 214 */     if (paramDouble == 0.0D) {
/* 215 */       this.elements.removeKey(i);
/*     */     } else {
/* 217 */       this.elements.put(i, paramDouble);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 227 */     super.setUp(paramInt1, paramInt2, paramInt3);
/* 228 */     this.sliceStride = 1;
/* 229 */     this.rowStride = 1;
/* 230 */     this.columnStride = 1;
/* 231 */     this.offset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix3D vDice(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 238 */     super.vDice(paramInt1, paramInt2, paramInt3);
/*     */     
/*     */ 
/* 241 */     int[][] arrayOfInt = new int[3][];
/* 242 */     arrayOfInt[0] = this.sliceOffsets;
/* 243 */     arrayOfInt[1] = this.rowOffsets;
/* 244 */     arrayOfInt[2] = this.columnOffsets;
/*     */     
/* 246 */     this.sliceOffsets = arrayOfInt[paramInt1];
/* 247 */     this.rowOffsets = arrayOfInt[paramInt2];
/* 248 */     this.columnOffsets = arrayOfInt[paramInt3];
/*     */     
/* 250 */     return this;
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
/* 267 */     checkColumn(paramInt);
/*     */     
/* 269 */     int i = this.slices;
/* 270 */     int j = this.rows;
/*     */     
/* 272 */     int k = this.sliceZero;
/* 273 */     int m = this.rowZero;
/* 274 */     int n = this.offset + _columnOffset(_columnRank(paramInt));
/*     */     
/* 276 */     int i1 = this.sliceStride;
/* 277 */     int i2 = this.rowStride;
/*     */     
/* 279 */     int[] arrayOfInt1 = this.sliceOffsets;
/* 280 */     int[] arrayOfInt2 = this.rowOffsets;
/*     */     
/* 282 */     return new SelectedSparseDoubleMatrix2D(i, j, this.elements, k, m, i1, i2, arrayOfInt1, arrayOfInt2, n);
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
/* 299 */     checkRow(paramInt);
/*     */     
/* 301 */     int i = this.slices;
/* 302 */     int j = this.columns;
/*     */     
/* 304 */     int k = this.sliceZero;
/* 305 */     int m = this.columnZero;
/* 306 */     int n = this.offset + _rowOffset(_rowRank(paramInt));
/*     */     
/* 308 */     int i1 = this.sliceStride;
/* 309 */     int i2 = this.columnStride;
/*     */     
/* 311 */     int[] arrayOfInt1 = this.sliceOffsets;
/* 312 */     int[] arrayOfInt2 = this.columnOffsets;
/*     */     
/* 314 */     return new SelectedSparseDoubleMatrix2D(i, j, this.elements, k, m, i1, i2, arrayOfInt1, arrayOfInt2, n);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix3D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3)
/*     */   {
/* 325 */     return new SelectedSparseDoubleMatrix3D(this.elements, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, this.offset);
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
/*     */   public DoubleMatrix2D viewSlice(int paramInt)
/*     */   {
/* 342 */     checkSlice(paramInt);
/*     */     
/* 344 */     int i = this.rows;
/* 345 */     int j = this.columns;
/*     */     
/* 347 */     int k = this.rowZero;
/* 348 */     int m = this.columnZero;
/* 349 */     int n = this.offset + _sliceOffset(_sliceRank(paramInt));
/*     */     
/* 351 */     int i1 = this.rowStride;
/* 352 */     int i2 = this.columnStride;
/*     */     
/* 354 */     int[] arrayOfInt1 = this.rowOffsets;
/* 355 */     int[] arrayOfInt2 = this.columnOffsets;
/*     */     
/* 357 */     return new SelectedSparseDoubleMatrix2D(i, j, this.elements, k, m, i1, i2, arrayOfInt1, arrayOfInt2, n);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedSparseDoubleMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */