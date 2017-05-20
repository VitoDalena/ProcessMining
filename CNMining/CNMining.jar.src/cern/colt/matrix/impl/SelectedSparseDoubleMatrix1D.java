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
/*     */ class SelectedSparseDoubleMatrix1D
/*     */   extends DoubleMatrix1D
/*     */ {
/*     */   protected AbstractIntDoubleMap elements;
/*     */   protected int[] offsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedSparseDoubleMatrix1D(int paramInt1, AbstractIntDoubleMap paramAbstractIntDoubleMap, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4)
/*     */   {
/*  70 */     setUp(paramInt1, paramInt2, paramInt3);
/*     */     
/*  72 */     this.elements = paramAbstractIntDoubleMap;
/*  73 */     this.offsets = paramArrayOfInt;
/*  74 */     this.offset = paramInt4;
/*  75 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SelectedSparseDoubleMatrix1D(AbstractIntDoubleMap paramAbstractIntDoubleMap, int[] paramArrayOfInt)
/*     */   {
/*  83 */     this(paramArrayOfInt.length, paramAbstractIntDoubleMap, 0, 1, paramArrayOfInt, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _offset(int paramInt)
/*     */   {
/*  93 */     return this.offsets[paramInt];
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
/*     */   public double getQuick(int paramInt)
/*     */   {
/* 109 */     return this.elements.get(this.offset + this.offsets[(this.zero + paramInt * this.stride)]);
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 115 */     if ((paramDoubleMatrix1D instanceof SelectedSparseDoubleMatrix1D)) {
/* 116 */       localObject = (SelectedSparseDoubleMatrix1D)paramDoubleMatrix1D;
/* 117 */       return this.elements == ((SelectedSparseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 119 */     if ((paramDoubleMatrix1D instanceof SparseDoubleMatrix1D)) {
/* 120 */       localObject = (SparseDoubleMatrix1D)paramDoubleMatrix1D;
/* 121 */       return this.elements == ((SparseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 123 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int index(int paramInt)
/*     */   {
/* 134 */     return this.offset + this.offsets[(this.zero + paramInt * this.stride)];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D like(int paramInt)
/*     */   {
/* 146 */     return new SparseDoubleMatrix1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D like2D(int paramInt1, int paramInt2)
/*     */   {
/* 158 */     return new SparseDoubleMatrix2D(paramInt1, paramInt2);
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
/*     */   public void setQuick(int paramInt, double paramDouble)
/*     */   {
/* 174 */     int i = this.offset + this.offsets[(this.zero + paramInt * this.stride)];
/* 175 */     if (paramDouble == 0.0D) {
/* 176 */       this.elements.removeKey(i);
/*     */     } else {
/* 178 */       this.elements.put(i, paramDouble);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setUp(int paramInt)
/*     */   {
/* 185 */     super.setUp(paramInt);
/* 186 */     this.stride = 1;
/* 187 */     this.offset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 196 */     return new SelectedSparseDoubleMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedSparseDoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */