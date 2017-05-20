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
/*     */ class SelectedDenseObjectMatrix1D
/*     */   extends ObjectMatrix1D
/*     */ {
/*     */   protected Object[] elements;
/*     */   protected int[] offsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedDenseObjectMatrix1D(Object[] paramArrayOfObject, int[] paramArrayOfInt)
/*     */   {
/*  65 */     this(paramArrayOfInt.length, paramArrayOfObject, 0, 1, paramArrayOfInt, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SelectedDenseObjectMatrix1D(int paramInt1, Object[] paramArrayOfObject, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4)
/*     */   {
/*  77 */     setUp(paramInt1, paramInt2, paramInt3);
/*     */     
/*  79 */     this.elements = paramArrayOfObject;
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
/*     */   public Object getQuick(int paramInt)
/*     */   {
/* 108 */     return this.elements[(this.offset + this.offsets[(this.zero + paramInt * this.stride)])];
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 114 */     if ((paramObjectMatrix1D instanceof SelectedDenseObjectMatrix1D)) {
/* 115 */       localObject = (SelectedDenseObjectMatrix1D)paramObjectMatrix1D;
/* 116 */       return this.elements == ((SelectedDenseObjectMatrix1D)localObject).elements;
/*     */     }
/* 118 */     if ((paramObjectMatrix1D instanceof DenseObjectMatrix1D)) {
/* 119 */       localObject = (DenseObjectMatrix1D)paramObjectMatrix1D;
/* 120 */       return this.elements == ((DenseObjectMatrix1D)localObject).elements;
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
/*     */   public ObjectMatrix1D like(int paramInt)
/*     */   {
/* 145 */     return new DenseObjectMatrix1D(paramInt);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ObjectMatrix2D like2D(int paramInt1, int paramInt2)
/*     */   {
/* 157 */     return new DenseObjectMatrix2D(paramInt1, paramInt2);
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
/*     */   public void setQuick(int paramInt, Object paramObject)
/*     */   {
/* 173 */     this.elements[(this.offset + this.offsets[(this.zero + paramInt * this.stride)])] = paramObject;
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
/*     */   protected ObjectMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 191 */     return new SelectedDenseObjectMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedDenseObjectMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */