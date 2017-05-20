/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.map.AbstractIntDoubleMap;
/*     */ import cern.colt.map.AbstractMap;
/*     */ import cern.colt.map.OpenIntDoubleHashMap;
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
/*     */ public class SparseDoubleMatrix1D
/*     */   extends DoubleMatrix1D
/*     */ {
/*     */   protected AbstractIntDoubleMap elements;
/*     */   
/*     */   public SparseDoubleMatrix1D(double[] paramArrayOfDouble)
/*     */   {
/*  62 */     this(paramArrayOfDouble.length);
/*  63 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SparseDoubleMatrix1D(int paramInt)
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
/*     */   public SparseDoubleMatrix1D(int paramInt1, int paramInt2, double paramDouble1, double paramDouble2)
/*     */   {
/*  88 */     setUp(paramInt1);
/*  89 */     this.elements = new OpenIntDoubleHashMap(paramInt2, paramDouble1, paramDouble2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected SparseDoubleMatrix1D(int paramInt1, AbstractIntDoubleMap paramAbstractIntDoubleMap, int paramInt2, int paramInt3)
/*     */   {
/* 101 */     setUp(paramInt1, paramInt2, paramInt3);
/* 102 */     this.elements = paramAbstractIntDoubleMap;
/* 103 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D assign(double paramDouble)
/*     */   {
/* 112 */     if ((this.isNoView) && (paramDouble == 0.0D)) this.elements.clear(); else
/* 113 */       super.assign(paramDouble);
/* 114 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 120 */     if (this.isNoView) return this.elements.size();
/* 121 */     return super.cardinality();
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
/* 134 */     this.elements.ensureCapacity(paramInt);
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
/* 150 */     return this.elements.get(this.zero + paramInt * this.stride);
/*     */   }
/*     */   
/*     */   protected boolean haveSharedCellsRaw(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/*     */     Object localObject;
/* 156 */     if ((paramDoubleMatrix1D instanceof SelectedSparseDoubleMatrix1D)) {
/* 157 */       localObject = (SelectedSparseDoubleMatrix1D)paramDoubleMatrix1D;
/* 158 */       return this.elements == ((SelectedSparseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 160 */     if ((paramDoubleMatrix1D instanceof SparseDoubleMatrix1D)) {
/* 161 */       localObject = (SparseDoubleMatrix1D)paramDoubleMatrix1D;
/* 162 */       return this.elements == ((SparseDoubleMatrix1D)localObject).elements;
/*     */     }
/* 164 */     return false;
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
/* 175 */     return this.zero + paramInt * this.stride;
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
/* 187 */     return new SparseDoubleMatrix1D(paramInt);
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
/* 199 */     return new SparseDoubleMatrix2D(paramInt1, paramInt2);
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
/* 215 */     int i = this.zero + paramInt * this.stride;
/* 216 */     if (paramDouble == 0.0D) {
/* 217 */       this.elements.removeKey(i);
/*     */     } else {
/* 219 */       this.elements.put(i, paramDouble);
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
/* 239 */     this.elements.trimToSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix1D viewSelectionLike(int[] paramArrayOfInt)
/*     */   {
/* 248 */     return new SelectedSparseDoubleMatrix1D(this.elements, paramArrayOfInt);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SparseDoubleMatrix1D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */