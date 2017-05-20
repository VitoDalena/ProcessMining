/*     */ package cern.colt.matrix.impl;
/*     */ 
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
/*     */ class SelectedDenseDoubleMatrix3D
/*     */   extends DoubleMatrix3D
/*     */ {
/*     */   protected double[] elements;
/*     */   protected int[] sliceOffsets;
/*     */   protected int[] rowOffsets;
/*     */   protected int[] columnOffsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedDenseDoubleMatrix3D(double[] paramArrayOfDouble, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int[] paramArrayOfInt3, int paramInt)
/*     */   {
/*  71 */     int i = paramArrayOfInt1.length;
/*  72 */     int j = paramArrayOfInt2.length;
/*  73 */     int k = paramArrayOfInt3.length;
/*  74 */     setUp(i, j, k);
/*     */     
/*  76 */     this.elements = paramArrayOfDouble;
/*     */     
/*  78 */     this.sliceOffsets = paramArrayOfInt1;
/*  79 */     this.rowOffsets = paramArrayOfInt2;
/*  80 */     this.columnOffsets = paramArrayOfInt3;
/*     */     
/*  82 */     this.offset = paramInt;
/*     */     
/*  84 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnOffset(int paramInt)
/*     */   {
/*  94 */     return this.columnOffsets[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowOffset(int paramInt)
/*     */   {
/* 104 */     return this.rowOffsets[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _sliceOffset(int paramInt)
/*     */   {
/* 114 */     return this.sliceOffsets[paramInt];
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
/* 132 */     return this.elements[(this.offset + this.sliceOffsets[(this.sliceZero + paramInt1 * this.sliceStride)] + this.rowOffsets[(this.rowZero + paramInt2 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt3 * this.columnStride)])];
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
/* 144 */     if ((paramDoubleMatrix3D instanceof SelectedDenseDoubleMatrix3D)) {
/* 145 */       localObject = (SelectedDenseDoubleMatrix3D)paramDoubleMatrix3D;
/* 146 */       return this.elements == ((SelectedDenseDoubleMatrix3D)localObject).elements;
/*     */     }
/* 148 */     if ((paramDoubleMatrix3D instanceof DenseDoubleMatrix3D)) {
/* 149 */       localObject = (DenseDoubleMatrix3D)paramDoubleMatrix3D;
/* 150 */       return this.elements == ((DenseDoubleMatrix3D)localObject).elements;
/*     */     }
/* 152 */     return false;
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
/* 164 */     return this.offset + this.sliceOffsets[(this.sliceZero + paramInt1 * this.sliceStride)] + this.rowOffsets[(this.rowZero + paramInt2 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt3 * this.columnStride)];
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
/* 178 */     return new DenseDoubleMatrix3D(paramInt1, paramInt2, paramInt3);
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
/* 194 */     throw new InternalError();
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
/* 212 */     this.elements[(this.offset + this.sliceOffsets[(this.sliceZero + paramInt1 * this.sliceStride)] + this.rowOffsets[(this.rowZero + paramInt2 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt3 * this.columnStride)])] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 222 */     super.setUp(paramInt1, paramInt2, paramInt3);
/* 223 */     this.sliceStride = 1;
/* 224 */     this.rowStride = 1;
/* 225 */     this.columnStride = 1;
/* 226 */     this.offset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected AbstractMatrix3D vDice(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 233 */     super.vDice(paramInt1, paramInt2, paramInt3);
/*     */     
/*     */ 
/* 236 */     int[][] arrayOfInt = new int[3][];
/* 237 */     arrayOfInt[0] = this.sliceOffsets;
/* 238 */     arrayOfInt[1] = this.rowOffsets;
/* 239 */     arrayOfInt[2] = this.columnOffsets;
/*     */     
/* 241 */     this.sliceOffsets = arrayOfInt[paramInt1];
/* 242 */     this.rowOffsets = arrayOfInt[paramInt2];
/* 243 */     this.columnOffsets = arrayOfInt[paramInt3];
/*     */     
/* 245 */     return this;
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
/* 262 */     checkColumn(paramInt);
/*     */     
/* 264 */     int i = this.slices;
/* 265 */     int j = this.rows;
/*     */     
/* 267 */     int k = this.sliceZero;
/* 268 */     int m = this.rowZero;
/* 269 */     int n = this.offset + _columnOffset(_columnRank(paramInt));
/*     */     
/* 271 */     int i1 = this.sliceStride;
/* 272 */     int i2 = this.rowStride;
/*     */     
/* 274 */     int[] arrayOfInt1 = this.sliceOffsets;
/* 275 */     int[] arrayOfInt2 = this.rowOffsets;
/*     */     
/* 277 */     return new SelectedDenseDoubleMatrix2D(i, j, this.elements, k, m, i1, i2, arrayOfInt1, arrayOfInt2, n);
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
/* 294 */     checkRow(paramInt);
/*     */     
/* 296 */     int i = this.slices;
/* 297 */     int j = this.columns;
/*     */     
/* 299 */     int k = this.sliceZero;
/* 300 */     int m = this.columnZero;
/* 301 */     int n = this.offset + _rowOffset(_rowRank(paramInt));
/*     */     
/* 303 */     int i1 = this.sliceStride;
/* 304 */     int i2 = this.columnStride;
/*     */     
/* 306 */     int[] arrayOfInt1 = this.sliceOffsets;
/* 307 */     int[] arrayOfInt2 = this.columnOffsets;
/*     */     
/* 309 */     return new SelectedDenseDoubleMatrix2D(i, j, this.elements, k, m, i1, i2, arrayOfInt1, arrayOfInt2, n);
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
/* 320 */     return new SelectedDenseDoubleMatrix3D(this.elements, paramArrayOfInt1, paramArrayOfInt2, paramArrayOfInt3, this.offset);
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
/* 337 */     checkSlice(paramInt);
/*     */     
/* 339 */     int i = this.rows;
/* 340 */     int j = this.columns;
/*     */     
/* 342 */     int k = this.rowZero;
/* 343 */     int m = this.columnZero;
/* 344 */     int n = this.offset + _sliceOffset(_sliceRank(paramInt));
/*     */     
/* 346 */     int i1 = this.rowStride;
/* 347 */     int i2 = this.columnStride;
/*     */     
/* 349 */     int[] arrayOfInt1 = this.rowOffsets;
/* 350 */     int[] arrayOfInt2 = this.columnOffsets;
/*     */     
/* 352 */     return new SelectedDenseDoubleMatrix2D(i, j, this.elements, k, m, i1, i2, arrayOfInt1, arrayOfInt2, n);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedDenseDoubleMatrix3D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */