/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.map.AbstractIntDoubleMap;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SelectedSparseDoubleMatrix2D
/*     */   extends DoubleMatrix2D
/*     */ {
/*     */   protected AbstractIntDoubleMap elements;
/*     */   protected int[] rowOffsets;
/*     */   protected int[] columnOffsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedSparseDoubleMatrix2D(int paramInt1, int paramInt2, AbstractIntDoubleMap paramAbstractIntDoubleMap, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt7)
/*     */   {
/*  76 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*     */     
/*  78 */     this.elements = paramAbstractIntDoubleMap;
/*  79 */     this.rowOffsets = paramArrayOfInt1;
/*  80 */     this.columnOffsets = paramArrayOfInt2;
/*  81 */     this.offset = paramInt7;
/*     */     
/*  83 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SelectedSparseDoubleMatrix2D(AbstractIntDoubleMap paramAbstractIntDoubleMap, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
/*     */   {
/*  93 */     this(paramArrayOfInt1.length, paramArrayOfInt2.length, paramAbstractIntDoubleMap, 0, 0, 1, 1, paramArrayOfInt1, paramArrayOfInt2, paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnOffset(int paramInt)
/*     */   {
/* 103 */     return this.columnOffsets[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowOffset(int paramInt)
/*     */   {
/* 113 */     return this.rowOffsets[paramInt];
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
/*     */   public double getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 130 */     return this.elements.get(this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 142 */     if ((paramDoubleMatrix2D instanceof SelectedSparseDoubleMatrix2D)) {
/* 143 */       localObject = (SelectedSparseDoubleMatrix2D)paramDoubleMatrix2D;
/* 144 */       return this.elements == ((SelectedSparseDoubleMatrix2D)localObject).elements;
/*     */     }
/* 146 */     if ((paramDoubleMatrix2D instanceof SparseDoubleMatrix2D)) {
/* 147 */       localObject = (SparseDoubleMatrix2D)paramDoubleMatrix2D;
/* 148 */       return this.elements == ((SparseDoubleMatrix2D)localObject).elements;
/*     */     }
/* 150 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt1, int paramInt2)
/*     */   {
/* 161 */     return this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)];
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
/*     */   public DoubleMatrix2D like(int paramInt1, int paramInt2)
/*     */   {
/* 174 */     return new SparseDoubleMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like1D(int paramInt)
/*     */   {
/* 185 */     return new SparseDoubleMatrix1D(paramInt);
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
/*     */   protected DoubleMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 198 */     throw new InternalError();
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
/*     */   public void setQuick(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 215 */     int i = this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)];
/*     */     
/* 217 */     if (paramDouble == 0.0D) {
/* 218 */       this.elements.removeKey(i);
/*     */     } else {
/* 220 */       this.elements.put(i, paramDouble);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2)
/*     */   {
/* 229 */     super.setUp(paramInt1, paramInt2);
/* 230 */     this.rowStride = 1;
/* 231 */     this.columnStride = 1;
/* 232 */     this.offset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix2D vDice()
/*     */   {
/* 238 */     super.vDice();
/*     */     
/* 240 */     int[] arrayOfInt = this.rowOffsets;this.rowOffsets = this.columnOffsets;this.columnOffsets = arrayOfInt;
/*     */     
/*     */ 
/*     */ 
/* 244 */     this.isNoView = false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D viewColumn(int paramInt)
/*     */   {
/* 270 */     checkColumn(paramInt);
/* 271 */     int i = this.rows;
/* 272 */     int j = this.rowZero;
/* 273 */     int k = this.rowStride;
/* 274 */     int[] arrayOfInt = this.rowOffsets;
/* 275 */     int m = this.offset + _columnOffset(_columnRank(paramInt));
/* 276 */     return new SelectedSparseDoubleMatrix1D(i, this.elements, j, k, arrayOfInt, m);
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
/*     */   public DoubleMatrix1D viewRow(int paramInt)
/*     */   {
/* 301 */     checkRow(paramInt);
/* 302 */     int i = this.columns;
/* 303 */     int j = this.columnZero;
/* 304 */     int k = this.columnStride;
/* 305 */     int[] arrayOfInt = this.columnOffsets;
/* 306 */     int m = this.offset + _rowOffset(_rowRank(paramInt));
/* 307 */     return new SelectedSparseDoubleMatrix1D(i, this.elements, j, k, arrayOfInt, m);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 317 */     return new SelectedSparseDoubleMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, this.offset);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedSparseDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */