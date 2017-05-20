/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.matrix.ObjectMatrix1D;
/*     */ import cern.colt.matrix.ObjectMatrix2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SelectedDenseObjectMatrix2D
/*     */   extends ObjectMatrix2D
/*     */ {
/*     */   protected Object[] elements;
/*     */   protected int[] rowOffsets;
/*     */   protected int[] columnOffsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedDenseObjectMatrix2D(Object[] paramArrayOfObject, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
/*     */   {
/*  68 */     this(paramArrayOfInt1.length, paramArrayOfInt2.length, paramArrayOfObject, 0, 0, 1, 1, paramArrayOfInt1, paramArrayOfInt2, paramInt);
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
/*     */   protected SelectedDenseObjectMatrix2D(int paramInt1, int paramInt2, Object[] paramArrayOfObject, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt7)
/*     */   {
/*  85 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*     */     
/*  87 */     this.elements = paramArrayOfObject;
/*  88 */     this.rowOffsets = paramArrayOfInt1;
/*  89 */     this.columnOffsets = paramArrayOfInt2;
/*  90 */     this.offset = paramInt7;
/*     */     
/*  92 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _columnOffset(int paramInt)
/*     */   {
/* 102 */     return this.columnOffsets[paramInt];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _rowOffset(int paramInt)
/*     */   {
/* 112 */     return this.rowOffsets[paramInt];
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
/*     */   public Object getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 129 */     return this.elements[(this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)])];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix2D paramObjectMatrix2D)
/*     */   {
/*     */     Object localObject;
/*     */     
/*     */ 
/*     */ 
/* 141 */     if ((paramObjectMatrix2D instanceof SelectedDenseObjectMatrix2D)) {
/* 142 */       localObject = (SelectedDenseObjectMatrix2D)paramObjectMatrix2D;
/* 143 */       return this.elements == ((SelectedDenseObjectMatrix2D)localObject).elements;
/*     */     }
/* 145 */     if ((paramObjectMatrix2D instanceof DenseObjectMatrix2D)) {
/* 146 */       localObject = (DenseObjectMatrix2D)paramObjectMatrix2D;
/* 147 */       return this.elements == ((DenseObjectMatrix2D)localObject).elements;
/*     */     }
/* 149 */     return false;
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
/* 160 */     return this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)];
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
/*     */   public ObjectMatrix2D like(int paramInt1, int paramInt2)
/*     */   {
/* 173 */     return new DenseObjectMatrix2D(paramInt1, paramInt2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix1D like1D(int paramInt)
/*     */   {
/* 184 */     return new DenseObjectMatrix1D(paramInt);
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
/*     */   protected ObjectMatrix1D like1D(int paramInt1, int paramInt2, int paramInt3)
/*     */   {
/* 197 */     throw new InternalError();
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
/*     */   public void setQuick(int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 214 */     this.elements[(this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)])] = paramObject;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt1, int paramInt2)
/*     */   {
/* 223 */     super.setUp(paramInt1, paramInt2);
/* 224 */     this.rowStride = 1;
/* 225 */     this.columnStride = 1;
/* 226 */     this.offset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */   protected AbstractMatrix2D vDice()
/*     */   {
/* 232 */     super.vDice();
/*     */     
/* 234 */     int[] arrayOfInt = this.rowOffsets;this.rowOffsets = this.columnOffsets;this.columnOffsets = arrayOfInt;
/*     */     
/*     */ 
/*     */ 
/* 238 */     this.isNoView = false;
/* 239 */     return this;
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
/* 264 */     checkColumn(paramInt);
/* 265 */     int i = this.rows;
/* 266 */     int j = this.rowZero;
/* 267 */     int k = this.rowStride;
/* 268 */     int[] arrayOfInt = this.rowOffsets;
/* 269 */     int m = this.offset + _columnOffset(_columnRank(paramInt));
/* 270 */     return new SelectedDenseObjectMatrix1D(i, this.elements, j, k, arrayOfInt, m);
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
/* 295 */     checkRow(paramInt);
/* 296 */     int i = this.columns;
/* 297 */     int j = this.columnZero;
/* 298 */     int k = this.columnStride;
/* 299 */     int[] arrayOfInt = this.columnOffsets;
/* 300 */     int m = this.offset + _rowOffset(_rowRank(paramInt));
/* 301 */     return new SelectedDenseObjectMatrix1D(i, this.elements, j, k, arrayOfInt, m);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 311 */     return new SelectedDenseObjectMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, this.offset);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedDenseObjectMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */