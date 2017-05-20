/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.map.AbstractIntObjectMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class SelectedSparseObjectMatrix2D
/*     */   extends ObjectMatrix2D
/*     */ {
/*     */   protected AbstractIntObjectMap elements;
/*     */   protected int[] rowOffsets;
/*     */   protected int[] columnOffsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedSparseObjectMatrix2D(int paramInt1, int paramInt2, AbstractIntObjectMap paramAbstractIntObjectMap, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt7)
/*     */   {
/*  76 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/*     */     
/*  78 */     this.elements = paramAbstractIntObjectMap;
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
/*     */   protected SelectedSparseObjectMatrix2D(AbstractIntObjectMap paramAbstractIntObjectMap, int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
/*     */   {
/*  93 */     this(paramArrayOfInt1.length, paramArrayOfInt2.length, paramAbstractIntObjectMap, 0, 0, 1, 1, paramArrayOfInt1, paramArrayOfInt2, paramInt);
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
/*     */   public Object getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 130 */     return this.elements.get(this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)]);
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
/* 142 */     if ((paramObjectMatrix2D instanceof SelectedSparseObjectMatrix2D)) {
/* 143 */       localObject = (SelectedSparseObjectMatrix2D)paramObjectMatrix2D;
/* 144 */       return this.elements == ((SelectedSparseObjectMatrix2D)localObject).elements;
/*     */     }
/* 146 */     if ((paramObjectMatrix2D instanceof SparseObjectMatrix2D)) {
/* 147 */       localObject = (SparseObjectMatrix2D)paramObjectMatrix2D;
/* 148 */       return this.elements == ((SparseObjectMatrix2D)localObject).elements;
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
/*     */   public ObjectMatrix2D like(int paramInt1, int paramInt2)
/*     */   {
/* 174 */     return new SparseObjectMatrix2D(paramInt1, paramInt2);
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
/* 185 */     return new SparseObjectMatrix1D(paramInt);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, Object paramObject)
/*     */   {
/* 215 */     int i = this.offset + this.rowOffsets[(this.rowZero + paramInt1 * this.rowStride)] + this.columnOffsets[(this.columnZero + paramInt2 * this.columnStride)];
/*     */     
/* 217 */     if (paramObject == null) {
/* 218 */       this.elements.removeKey(i);
/*     */     } else {
/* 220 */       this.elements.put(i, paramObject);
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
/*     */   public ObjectMatrix1D viewColumn(int paramInt)
/*     */   {
/* 270 */     checkColumn(paramInt);
/* 271 */     int i = this.rows;
/* 272 */     int j = this.rowZero;
/* 273 */     int k = this.rowStride;
/* 274 */     int[] arrayOfInt = this.rowOffsets;
/* 275 */     int m = this.offset + _columnOffset(_columnRank(paramInt));
/* 276 */     return new SelectedSparseObjectMatrix1D(i, this.elements, j, k, arrayOfInt, m);
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
/* 301 */     checkRow(paramInt);
/* 302 */     int i = this.columns;
/* 303 */     int j = this.columnZero;
/* 304 */     int k = this.columnStride;
/* 305 */     int[] arrayOfInt = this.columnOffsets;
/* 306 */     int m = this.offset + _rowOffset(_rowRank(paramInt));
/* 307 */     return new SelectedSparseObjectMatrix1D(i, this.elements, j, k, arrayOfInt, m);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 317 */     return new SelectedSparseObjectMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, this.offset);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedSparseObjectMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */