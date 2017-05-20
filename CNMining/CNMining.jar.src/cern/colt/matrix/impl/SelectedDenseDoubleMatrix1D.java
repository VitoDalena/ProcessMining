/*     */ package cern.colt.matrix.impl;
/*     */ 
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
/*     */ class SelectedDenseDoubleMatrix1D
/*     */   extends DoubleMatrix1D
/*     */ {
/*     */   protected double[] elements;
/*     */   protected int[] offsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedDenseDoubleMatrix1D(double[] paramArrayOfDouble, int[] paramArrayOfInt)
/*     */   {
/*  65 */     this(paramArrayOfInt.length, paramArrayOfDouble, 0, 1, paramArrayOfInt, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SelectedDenseDoubleMatrix1D(int paramInt1, double[] paramArrayOfDouble, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4)
/*     */   {
/*  77 */     setUp(paramInt1, paramInt2, paramInt3);
/*     */     
/*  79 */     this.elements = paramArrayOfDouble;
/*  80 */     this.offsets = paramArrayOfInt;
/*  81 */     this.offset = paramInt4;
/*  82 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int _offset(int paramInt)
/*     */   {
/*  92 */     return this.offsets[paramInt];
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
/* 108 */     return this.elements[(this.offset + this.offsets[(this.zero + paramInt * this.stride)])];
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 114 */     if ((paramDoubleMatrix1D instanceof SelectedDenseDoubleMatrix1D)) {
/* 115 */       localObject = (SelectedDenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 116 */       return this.elements == ((SelectedDenseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 118 */     if ((paramDoubleMatrix1D instanceof DenseDoubleMatrix1D)) {
/* 119 */       localObject = (DenseDoubleMatrix1D)paramDoubleMatrix1D;
/* 120 */       return this.elements == ((DenseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 122 */     return false;
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
/* 133 */     return this.offset + this.offsets[(this.zero + paramInt * this.stride)];
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
/* 145 */     return new DenseDoubleMatrix1D(paramInt);
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
/* 157 */     return new DenseDoubleMatrix2D(paramInt1, paramInt2);
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
/* 173 */     this.elements[(this.offset + this.offsets[(this.zero + paramInt * this.stride)])] = paramDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setUp(int paramInt)
/*     */   {
/* 180 */     super.setUp(paramInt);
/* 181 */     this.stride = 1;
/* 182 */     this.offset = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 191 */     return new SelectedDenseDoubleMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedDenseDoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */