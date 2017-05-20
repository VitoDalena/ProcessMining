/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntDoubleProcedure;
/*     */ import cern.colt.function.IntIntDoubleFunction;
/*     */ import cern.colt.map.AbstractIntDoubleMap;
/*     */ import cern.colt.map.AbstractMap;
/*     */ import cern.colt.map.OpenIntDoubleHashMap;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.jet.math.Functions;
/*     */ import cern.jet.math.Mult;
/*     */ import cern.jet.math.PlusMult;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SparseDoubleMatrix2D
/*     */   extends DoubleMatrix2D
/*     */ {
/*     */   protected AbstractIntDoubleMap elements;
/*     */   protected int dummy;
/*     */   
/*     */   public SparseDoubleMatrix2D(double[][] paramArrayOfDouble)
/*     */   {
/*  89 */     this(paramArrayOfDouble.length, paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0].length);
/*  90 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SparseDoubleMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/* 100 */     this(paramInt1, paramInt2, paramInt1 * (paramInt2 / 1000), 0.2D, 0.5D);
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
/*     */   public SparseDoubleMatrix2D(int paramInt1, int paramInt2, int paramInt3, double paramDouble1, double paramDouble2)
/*     */   {
/* 117 */     setUp(paramInt1, paramInt2);
/* 118 */     this.elements = new OpenIntDoubleHashMap(paramInt3, paramDouble1, paramDouble2);
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
/*     */   protected SparseDoubleMatrix2D(int paramInt1, int paramInt2, AbstractIntDoubleMap paramAbstractIntDoubleMap, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
/*     */   {
/* 132 */     setUp(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6);
/* 133 */     this.elements = paramAbstractIntDoubleMap;
/* 134 */     this.isNoView = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D assign(double paramDouble)
/*     */   {
/* 143 */     if ((this.isNoView) && (paramDouble == 0.0D)) this.elements.clear(); else
/* 144 */       super.assign(paramDouble);
/* 145 */     return this;
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
/*     */   public DoubleMatrix2D assign(DoubleFunction paramDoubleFunction)
/*     */   {
/* 170 */     if ((this.isNoView) && ((paramDoubleFunction instanceof Mult))) {
/* 171 */       this.elements.assign(paramDoubleFunction);
/*     */     }
/*     */     else {
/* 174 */       super.assign(paramDoubleFunction);
/*     */     }
/* 176 */     return this;
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
/*     */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 189 */     if (!(paramDoubleMatrix2D instanceof SparseDoubleMatrix2D)) {
/* 190 */       return super.assign(paramDoubleMatrix2D);
/*     */     }
/* 192 */     SparseDoubleMatrix2D localSparseDoubleMatrix2D = (SparseDoubleMatrix2D)paramDoubleMatrix2D;
/* 193 */     if (localSparseDoubleMatrix2D == this) return this;
/* 194 */     checkShape(localSparseDoubleMatrix2D);
/*     */     
/* 196 */     if ((this.isNoView) && (localSparseDoubleMatrix2D.isNoView)) {
/* 197 */       this.elements.assign(localSparseDoubleMatrix2D.elements);
/* 198 */       return this;
/*     */     }
/* 200 */     return super.assign(paramDoubleMatrix2D);
/*     */   }
/*     */   
/* 203 */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleDoubleFunction paramDoubleDoubleFunction) { if (!this.isNoView) { return super.assign(paramDoubleMatrix2D, paramDoubleDoubleFunction);
/*     */     }
/* 205 */     checkShape(paramDoubleMatrix2D);
/*     */     
/* 207 */     if ((paramDoubleDoubleFunction instanceof PlusMult)) {
/* 208 */       double d = ((PlusMult)paramDoubleDoubleFunction).multiplicator;
/* 209 */       if (d == 0.0D) return this;
/* 210 */       paramDoubleMatrix2D.forEachNonZero(new IntIntDoubleFunction() {
/*     */         private final double val$alpha;
/*     */         
/* 213 */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { SparseDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, SparseDoubleMatrix2D.this.getQuick(paramAnonymousInt1, paramAnonymousInt2) + this.val$alpha * paramAnonymousDouble);
/* 214 */           return paramAnonymousDouble;
/*     */         }
/*     */         
/* 217 */       });
/* 218 */       return this;
/*     */     }
/*     */     
/* 221 */     if (paramDoubleDoubleFunction == Functions.mult) {
/* 222 */       this.elements.forEachPair(new IntDoubleProcedure() {
/*     */         private final DoubleMatrix2D val$y;
/*     */         
/* 225 */         public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { int i = paramAnonymousInt / SparseDoubleMatrix2D.this.columns;
/* 226 */           int j = paramAnonymousInt % SparseDoubleMatrix2D.this.columns;
/* 227 */           double d = paramAnonymousDouble * this.val$y.getQuick(i, j);
/* 228 */           if (d != paramAnonymousDouble) SparseDoubleMatrix2D.this.elements.put(paramAnonymousInt, d);
/* 229 */           return true;
/*     */         }
/*     */       });
/*     */     }
/*     */     
/*     */ 
/* 235 */     if (paramDoubleDoubleFunction == Functions.div) {
/* 236 */       this.elements.forEachPair(new IntDoubleProcedure() {
/*     */         private final DoubleMatrix2D val$y;
/*     */         
/* 239 */         public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { int i = paramAnonymousInt / SparseDoubleMatrix2D.this.columns;
/* 240 */           int j = paramAnonymousInt % SparseDoubleMatrix2D.this.columns;
/* 241 */           double d = paramAnonymousDouble / this.val$y.getQuick(i, j);
/* 242 */           if (d != paramAnonymousDouble) SparseDoubleMatrix2D.this.elements.put(paramAnonymousInt, d);
/* 243 */           return true;
/*     */         }
/*     */       });
/*     */     }
/*     */     
/*     */ 
/* 249 */     return super.assign(paramDoubleMatrix2D, paramDoubleDoubleFunction);
/*     */   }
/*     */   
/*     */ 
/*     */   public int cardinality()
/*     */   {
/* 255 */     if (this.isNoView) return this.elements.size();
/* 256 */     return super.cardinality();
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
/* 269 */   public void ensureCapacity(int paramInt) { this.elements.ensureCapacity(paramInt); }
/*     */   
/*     */   public DoubleMatrix2D forEachNonZero(IntIntDoubleFunction paramIntIntDoubleFunction) {
/* 272 */     if (this.isNoView) {
/* 273 */       this.elements.forEachPair(new IntDoubleProcedure() {
/*     */         private final IntIntDoubleFunction val$function;
/*     */         
/* 276 */         public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { int i = paramAnonymousInt / SparseDoubleMatrix2D.this.columns;
/* 277 */           int j = paramAnonymousInt % SparseDoubleMatrix2D.this.columns;
/* 278 */           double d = this.val$function.apply(i, j, paramAnonymousDouble);
/* 279 */           if (d != paramAnonymousDouble) SparseDoubleMatrix2D.this.elements.put(paramAnonymousInt, d);
/* 280 */           return true;
/*     */         }
/*     */         
/*     */ 
/*     */       });
/*     */     } else {
/* 286 */       super.forEachNonZero(paramIntIntDoubleFunction);
/*     */     }
/* 288 */     return this;
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
/* 305 */     return this.elements.get(this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride);
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
/* 317 */     if ((paramDoubleMatrix2D instanceof SelectedSparseDoubleMatrix2D)) {
/* 318 */       localObject = (SelectedSparseDoubleMatrix2D)paramDoubleMatrix2D;
/* 319 */       return this.elements == ((SelectedSparseDoubleMatrix2D)localObject).elements;
/*     */     }
/* 321 */     if ((paramDoubleMatrix2D instanceof SparseDoubleMatrix2D)) {
/* 322 */       localObject = (SparseDoubleMatrix2D)paramDoubleMatrix2D;
/* 323 */       return this.elements == ((SparseDoubleMatrix2D)localObject).elements;
/*     */     }
/* 325 */     return false;
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
/* 336 */     return this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride;
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
/* 349 */     return new SparseDoubleMatrix2D(paramInt1, paramInt2);
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
/* 360 */     return new SparseDoubleMatrix1D(paramInt);
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
/* 373 */     return new SparseDoubleMatrix1D(paramInt1, this.elements, paramInt2, paramInt3);
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
/* 390 */     int i = this.rowZero + paramInt1 * this.rowStride + this.columnZero + paramInt2 * this.columnStride;
/*     */     
/*     */ 
/* 393 */     if (paramDouble == 0.0D) {
/* 394 */       this.elements.removeKey(i);
/*     */     } else {
/* 396 */       this.elements.put(i, paramDouble);
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
/* 416 */     this.elements.trimToSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 426 */   protected DoubleMatrix2D viewSelectionLike(int[] paramArrayOfInt1, int[] paramArrayOfInt2) { return new SelectedSparseDoubleMatrix2D(this.elements, paramArrayOfInt1, paramArrayOfInt2, 0); }
/*     */   
/*     */   public DoubleMatrix1D zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2, boolean paramBoolean) {
/* 429 */     int i = this.rows;
/* 430 */     int j = this.columns;
/* 431 */     if (paramBoolean) {
/* 432 */       i = this.columns;
/* 433 */       j = this.rows;
/*     */     }
/*     */     
/* 436 */     int k = paramDoubleMatrix1D2 == null ? 1 : 0;
/* 437 */     if (paramDoubleMatrix1D2 == null) { paramDoubleMatrix1D2 = new DenseDoubleMatrix1D(i);
/*     */     }
/* 439 */     if ((!this.isNoView) || (!(paramDoubleMatrix1D1 instanceof DenseDoubleMatrix1D)) || (!(paramDoubleMatrix1D2 instanceof DenseDoubleMatrix1D))) {
/* 440 */       return super.zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, paramBoolean);
/*     */     }
/*     */     
/* 443 */     if ((j != paramDoubleMatrix1D1.size()) || (i > paramDoubleMatrix1D2.size())) {
/* 444 */       throw new IllegalArgumentException("Incompatible args: " + (paramBoolean ? viewDice() : this).toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*     */     }
/* 446 */     if (k == 0) { paramDoubleMatrix1D2.assign(Functions.mult(paramDouble2 / paramDouble1));
/*     */     }
/* 448 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D1 = (DenseDoubleMatrix1D)paramDoubleMatrix1D2;
/* 449 */     double[] arrayOfDouble1 = localDenseDoubleMatrix1D1.elements;
/* 450 */     int m = localDenseDoubleMatrix1D1.stride;
/* 451 */     int n = paramDoubleMatrix1D2.index(0);
/*     */     
/* 453 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D2 = (DenseDoubleMatrix1D)paramDoubleMatrix1D1;
/* 454 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D2.elements;
/* 455 */     int i1 = localDenseDoubleMatrix1D2.stride;
/* 456 */     int i2 = paramDoubleMatrix1D1.index(0);
/*     */     
/* 458 */     if ((arrayOfDouble2 == null) || (arrayOfDouble1 == null)) { throw new InternalError();
/*     */     }
/* 460 */     this.elements.forEachPair(new IntDoubleProcedure() {
/*     */       private final boolean val$transposeA;
/*     */       
/* 463 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { int i = paramAnonymousInt / SparseDoubleMatrix2D.this.columns;
/* 464 */         int j = paramAnonymousInt % SparseDoubleMatrix2D.this.columns;
/* 465 */         if (this.val$transposeA) { int k = i;i = j;j = k; }
/* 466 */         this.val$zElements[(this.val$zi + this.val$zStride * i)] += paramAnonymousDouble * this.val$yElements[(this.val$yi + this.val$yStride * j)];
/*     */         
/* 468 */         return true;
/*     */       }
/*     */     });
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 487 */     if (paramDouble1 != 1.0D) paramDoubleMatrix1D2.assign(Functions.mult(paramDouble1));
/* 488 */     return paramDoubleMatrix1D2;
/*     */   }
/*     */   
/* 491 */   public DoubleMatrix2D zMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2) { if (!this.isNoView) {
/* 492 */       return super.zMult(paramDoubleMatrix2D1, paramDoubleMatrix2D2, paramDouble1, paramDouble2, paramBoolean1, paramBoolean2);
/*     */     }
/* 494 */     if (paramBoolean2) paramDoubleMatrix2D1 = paramDoubleMatrix2D1.viewDice();
/* 495 */     int i = this.rows;
/* 496 */     int j = this.columns;
/* 497 */     if (paramBoolean1) {
/* 498 */       i = this.columns;
/* 499 */       j = this.rows;
/*     */     }
/* 501 */     int k = paramDoubleMatrix2D1.columns;
/* 502 */     int m = paramDoubleMatrix2D2 == null ? 1 : 0;
/* 503 */     if (paramDoubleMatrix2D2 == null) { paramDoubleMatrix2D2 = new DenseDoubleMatrix2D(i, k);
/*     */     }
/* 505 */     if (paramDoubleMatrix2D1.rows != j)
/* 506 */       throw new IllegalArgumentException("Matrix2D inner dimensions must agree:" + toStringShort() + ", " + (paramBoolean2 ? paramDoubleMatrix2D1.viewDice() : paramDoubleMatrix2D1).toStringShort());
/* 507 */     if ((paramDoubleMatrix2D2.rows != i) || (paramDoubleMatrix2D2.columns != k))
/* 508 */       throw new IllegalArgumentException("Incompatibel result matrix: " + toStringShort() + ", " + (paramBoolean2 ? paramDoubleMatrix2D1.viewDice() : paramDoubleMatrix2D1).toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort());
/* 509 */     if ((this == paramDoubleMatrix2D2) || (paramDoubleMatrix2D1 == paramDoubleMatrix2D2)) {
/* 510 */       throw new IllegalArgumentException("Matrices must not be identical");
/*     */     }
/* 512 */     if (m == 0) { paramDoubleMatrix2D2.assign(Functions.mult(paramDouble2));
/*     */     }
/*     */     
/* 515 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D1 = new DoubleMatrix1D[j];
/* 516 */     int n = j; do { arrayOfDoubleMatrix1D1[n] = paramDoubleMatrix2D1.viewRow(n);n--; } while (n >= 0);
/* 517 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D2 = new DoubleMatrix1D[i];
/* 518 */     int i1 = i; do { arrayOfDoubleMatrix1D2[i1] = paramDoubleMatrix2D2.viewRow(i1);i1--; } while (i1 >= 0);
/*     */     
/* 520 */     PlusMult localPlusMult = PlusMult.plusMult(0.0D);
/*     */     
/* 522 */     this.elements.forEachPair(new IntDoubleProcedure() { private final PlusMult val$fun;
/*     */       private final double val$alpha;
/*     */       
/* 525 */       public boolean apply(int paramAnonymousInt, double paramAnonymousDouble) { int i = paramAnonymousInt / SparseDoubleMatrix2D.this.columns;
/* 526 */         int j = paramAnonymousInt % SparseDoubleMatrix2D.this.columns;
/* 527 */         this.val$fun.multiplicator = (paramAnonymousDouble * this.val$alpha);
/* 528 */         if (!this.val$transposeA) {
/* 529 */           this.val$Crows[i].assign(this.val$Brows[j], this.val$fun);
/*     */         } else
/* 531 */           this.val$Crows[j].assign(this.val$Brows[i], this.val$fun);
/* 532 */         return true;
/*     */       }
/*     */       
/*     */ 
/* 536 */     });
/* 537 */     return paramDoubleMatrix2D2;
/*     */   }
/*     */   
/*     */   private final double[] val$zElements;
/*     */   private final int val$zi;
/*     */   private final int val$zStride;
/*     */   private final double[] val$yElements;
/*     */   private final boolean val$transposeA;
/*     */   private final DoubleMatrix1D[] val$Crows;
/*     */   private final DoubleMatrix1D[] val$Brows;
/*     */   private final int val$yi;
/*     */   private final int val$yStride;
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/SparseDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */