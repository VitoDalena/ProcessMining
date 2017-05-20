/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.map.AbstractIntObjectMap;
/*     */ import cern.colt.map.AbstractMap;
/*     */ import cern.colt.map.OpenIntObjectHashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SparseObjectMatrix2D
/*     */   extends ObjectMatrix2D
/*     */ {
/*     */   protected AbstractIntObjectMap elements;
/*     */   
/*     */   public SparseObjectMatrix2D(Object[][] paramArrayOfObject)
/*     */   {
/*  88 */     this(paramArrayOfObject.length, paramArrayOfObject.length == 0 ? 0 : paramArrayOfObject[0].length);
/*  89 */     assign(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SparseObjectMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/*  99 */     this(paramInt1, paramInt2, paramInt1 * (paramInt2 / 1000), 0.2D, 0.5D);
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
/*     */   public SparseObjectMatrix2D(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2)
/*     */   {
/* 116 */     setUp(paramInt1, paramInt2);
/* 117 */     this.elements = new OpenIntObjectHashMap(paramInt3, paramDouble1, paramDouble2);
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
/*     */   protected SparseObjectMatrix2D(int paramInt1, int paramInt2, AbstractIntObjectMap paramAbstractIntObjectMap, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 131 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 132 */     this.elements = paramAbstractIntObjectMap;
/* 133 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 139 */     if (this.isNoView) return this.elements.size();
/* 140 */     return super.cardinality();
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
/*     */   public void ensureCapacity(int paramInt)
/*     */   {
/* 153 */     this.elements.ensureCapacity(paramInt);
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
/* 170 */     return this.elements.get(this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride);
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
/* 182 */     if ((paramObjectMatrix2D instanceof SelectedSparseObjectMatrix2D)) {
/* 183 */       localObject = (SelectedSparseObjectMatrix2D)paramObjectMatrix2D;
/* 184 */       return this.elements == ((SelectedSparseObjectMatrix2D)localObject).elements;
/*     */     }
/* 186 */     if ((paramObjectMatrix2D instanceof SparseObjectMatrix2D)) {
/* 187 */       localObject = (SparseObjectMatrix2D)paramObjectMatrix2D;
/* 188 */       return this.elements == ((SparseObjectMatrix2D)localObject).elements;
/*     */     }
/* 190 */     return false;
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
/* 201 */     return this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride;
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
/* 214 */     return new SparseObjectMatrix2D(paramInt1, paramInt2);
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
/* 225 */     return new SparseObjectMatrix1D(paramInt);
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
/* 238 */     return new SparseObjectMatrix1D(paramInt1, this.elements, paramInt2, paramInt3);
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
/* 255 */     int i = this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride;
/*     */     
/* 257 */     if (paramObject == null) {
/* 258 */       this.elements.removeKey(i);
/*     */     } else {
/* 260 */       this.elements.put(i, paramObject);
/*     */     }
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
/*     */   public void trimToSize()
/*     */   {
/* 280 */     this.elements.trimToSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
/*     */   {
/* 290 */     return new SelectedSparseObjectMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, 0);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SparseObjectMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */