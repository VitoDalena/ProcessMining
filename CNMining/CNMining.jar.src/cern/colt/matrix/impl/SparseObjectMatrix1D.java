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
/*     */ public class SparseObjectMatrix1D
/*     */   extends ObjectMatrix1D
/*     */ {
/*     */   protected AbstractIntObjectMap elements;
/*     */   
/*     */   public SparseObjectMatrix1D(Object[] paramArrayOfObject)
/*     */   {
/*  62 */     this(paramArrayOfObject.length);
/*  63 */     assign(paramArrayOfObject);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SparseObjectMatrix1D(int paramInt)
/*     */   {
/*  72 */     this(paramInt, paramInt / 1000, 0.2D, 0.5D);
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
/*     */   public SparseObjectMatrix1D(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
/*     */   {
/*  88 */     setUp(paramInt1);
/*  89 */     this.elements = new OpenIntObjectHashMap(paramInt2, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SparseObjectMatrix1D(int paramInt1, AbstractIntObjectMap paramAbstractIntObjectMap, int paramInt2, int paramInt3)
/*     */   {
/* 101 */     setUp(paramInt1, paramInt2, paramInt3);
/* 102 */     this.elements = paramAbstractIntObjectMap;
/* 103 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 109 */     if (this.isNoView) return this.elements.size();
/* 110 */     return super.cardinality();
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
/* 123 */     this.elements.ensureCapacity(paramInt);
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
/* 139 */     return this.elements.get(this.zero + paramInt * this.stride);
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(ObjectMatrix1D paramObjectMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 145 */     if ((paramObjectMatrix1D instanceof SelectedSparseObjectMatrix1D)) {
/* 146 */       localObject = (SelectedSparseObjectMatrix1D)paramObjectMatrix1D;
/* 147 */       return this.elements == ((SelectedSparseObjectMatrix1D)localObject).elements;
/*     */     }
/* 149 */     if ((paramObjectMatrix1D instanceof SparseObjectMatrix1D)) {
/* 150 */       localObject = (SparseObjectMatrix1D)paramObjectMatrix1D;
/* 151 */       return this.elements == ((SparseObjectMatrix1D)localObject).elements;
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
/*     */   protected int index(int paramInt)
/*     */   {
/* 164 */     return this.zero + paramInt * this.stride;
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
/* 176 */     return new SparseObjectMatrix1D(paramInt);
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
/* 188 */     return new SparseObjectMatrix2D(paramInt1, paramInt2);
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
/* 204 */     int i = this.zero + paramInt * this.stride;
/* 205 */     if (paramObject == null) {
/* 206 */       this.elements.removeKey(i);
/*     */     } else {
/* 208 */       this.elements.put(i, paramObject);
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
/* 228 */     this.elements.trimToSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected ObjectMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 237 */     return new SelectedSparseObjectMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SparseObjectMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */