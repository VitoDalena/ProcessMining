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
/*     */ class SelectedSparseObjectMatrix1D
/*     */   extends ObjectMatrix1D
/*     */ {
/*     */   protected AbstractIntObjectMap elements;
/*     */   protected int[] offsets;
/*     */   protected int offset;
/*     */   
/*     */   protected SelectedSparseObjectMatrix1D(int paramInt1, AbstractIntObjectMap paramAbstractIntObjectMap, int paramInt2, int paramInt3, int[] paramArrayOfInt, int paramInt4)
/*     */   {
/*  70 */     setUp(paramInt1, paramInt2, paramInt3);
/*     */     
/*  72 */     this.elements = paramAbstractIntObjectMap;
/*  73 */     this.offsets = paramArrayOfInt;
/*  74 */     this.offset = paramInt4;
/*  75 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SelectedSparseObjectMatrix1D(AbstractIntObjectMap paramAbstractIntObjectMap, int[] paramArrayOfInt)
/*     */   {
/*  83 */     this(paramArrayOfInt.length, paramAbstractIntObjectMap, 0, 1, paramArrayOfInt, 0);
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
/*     */   public Object getQuick(int paramInt)
/*     */   {
/* 109 */     return this.elements.get(this.offset + this.offsets[(this.zero + paramInt * this.stride)]);
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 115 */     if ((paramObjectMatrix1D instanceof SelectedSparseObjectMatrix1D)) {
/* 116 */       localObject = (SelectedSparseObjectMatrix1D)paramObjectMatrix1D;
/* 117 */       return this.elements == ((SelectedSparseObjectMatrix1D)localObject).elements;
/*     */     }
/* 119 */     if ((paramObjectMatrix1D instanceof SparseObjectMatrix1D)) {
/* 120 */       localObject = (SparseObjectMatrix1D)paramObjectMatrix1D;
/* 121 */       return this.elements == ((SparseObjectMatrix1D)localObject).elements;
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
/*     */   public ObjectMatrix1D like(int paramInt)
/*     */   {
/* 146 */     return new SparseObjectMatrix1D(paramInt);
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
/* 158 */     return new SparseObjectMatrix2D(paramInt1, paramInt2);
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
/* 174 */     int i = this.offset + this.offsets[(this.zero + paramInt * this.stride)];
/* 175 */     if (paramObject == null) {
/* 176 */       this.elements.removeKey(i);
/*     */     } else {
/* 178 */       this.elements.put(i, paramObject);
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
/*     */   protected ObjectMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 196 */     return new SelectedSparseObjectMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SelectedSparseObjectMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */