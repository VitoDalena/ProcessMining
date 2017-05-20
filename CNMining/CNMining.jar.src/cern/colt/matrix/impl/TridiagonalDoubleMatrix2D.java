/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntIntDoubleFunction;
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
/*     */ class TridiagonalDoubleMatrix2D
/*     */   extends WrapperDoubleMatrix2D
/*     */ {
/*     */   protected double[] values;
/*     */   protected int[] dims;
/*     */   protected static final int NONZERO = 4;
/*     */   
/*     */   public TridiagonalDoubleMatrix2D(double[][] paramArrayOfDouble)
/*     */   {
/*  59 */     this(paramArrayOfDouble.length, paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0].length);
/*  60 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TridiagonalDoubleMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/*  70 */     super(null);
/*  71 */     setUp(paramInt1, paramInt2);
/*     */     
/*  73 */     int i = Math.min(paramInt1, paramInt2);
/*  74 */     int j = i - 1;
/*  75 */     int k = i - 1;
/*  76 */     if (paramInt1 > paramInt2) k++;
/*  77 */     if (paramInt1 < paramInt2) { j++;
/*     */     }
/*  79 */     this.values = new double[k + i + j];
/*  80 */     int[] arrayOfInt = { 0, k, k + i, k + i + j, 0, 0, 0 };
/*  81 */     this.dims = arrayOfInt;
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
/*     */   public DoubleMatrix2D assign(double paramDouble)
/*     */   {
/*  98 */     if (paramDouble == 0.0D) {
/*  99 */       int i = this.values.length; do { this.values[i] = 0.0D;i--; } while (i >= 0);
/* 100 */       int j = this.dims.length; do { this.dims[j] = 0;j--; } while (j >= 4);
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 110 */       super.assign(paramDouble); }
/* 111 */     return this;
/*     */   }
/*     */   
/* 114 */   public DoubleMatrix2D assign(DoubleFunction paramDoubleFunction) { if ((paramDoubleFunction instanceof Mult)) {
/* 115 */       double d = ((Mult)paramDoubleFunction).multiplicator;
/* 116 */       if (d == 1.0D) return this;
/* 117 */       if (d == 0.0D) return assign(0.0D);
/* 118 */       if (d != d) { return assign(d);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 127 */       forEachNonZero(new IntIntDoubleFunction() {
/*     */         private final DoubleFunction val$function;
/*     */         
/* 130 */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { return this.val$function.apply(paramAnonymousDouble);
/*     */         }
/*     */       });
/*     */     }
/*     */     else
/*     */     {
/* 136 */       super.assign(paramDoubleFunction);
/*     */     }
/* 138 */     return this;
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
/* 151 */     if (paramDoubleMatrix2D == this) return this;
/* 152 */     checkShape(paramDoubleMatrix2D);
/*     */     
/* 154 */     if ((paramDoubleMatrix2D instanceof TridiagonalDoubleMatrix2D))
/*     */     {
/* 156 */       TridiagonalDoubleMatrix2D localTridiagonalDoubleMatrix2D = (TridiagonalDoubleMatrix2D)paramDoubleMatrix2D;
/*     */       
/* 158 */       System.arraycopy(localTridiagonalDoubleMatrix2D.values, 0, this.values, 0, this.values.length);
/* 159 */       System.arraycopy(localTridiagonalDoubleMatrix2D.dims, 0, this.dims, 0, this.dims.length);
/* 160 */       return this;
/*     */     }
/*     */     
/* 163 */     if (((paramDoubleMatrix2D instanceof RCDoubleMatrix2D)) || ((paramDoubleMatrix2D instanceof SparseDoubleMatrix2D))) {
/* 164 */       assign(0.0D);
/* 165 */       paramDoubleMatrix2D.forEachNonZero(new IntIntDoubleFunction()
/*     */       {
/*     */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) {
/* 168 */           TridiagonalDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousDouble);
/* 169 */           return paramAnonymousDouble;
/*     */         }
/*     */         
/* 172 */       });
/* 173 */       return this;
/*     */     }
/*     */     
/* 176 */     return super.assign(paramDoubleMatrix2D);
/*     */   }
/*     */   
/* 179 */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleDoubleFunction paramDoubleDoubleFunction) { checkShape(paramDoubleMatrix2D);
/*     */     
/* 181 */     if ((paramDoubleDoubleFunction instanceof PlusMult)) {
/* 182 */       double d = ((PlusMult)paramDoubleDoubleFunction).multiplicator;
/* 183 */       if (d == 0.0D) return this;
/* 184 */       paramDoubleMatrix2D.forEachNonZero(new IntIntDoubleFunction() {
/*     */         private final double val$alpha;
/*     */         
/* 187 */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { TridiagonalDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, TridiagonalDoubleMatrix2D.this.getQuick(paramAnonymousInt1, paramAnonymousInt2) + this.val$alpha * paramAnonymousDouble);
/* 188 */           return paramAnonymousDouble;
/*     */         }
/*     */         
/* 191 */       });
/* 192 */       return this;
/*     */     }
/*     */     
/* 195 */     if (paramDoubleDoubleFunction == Functions.mult) {
/* 196 */       forEachNonZero(new IntIntDoubleFunction() {
/*     */         private final DoubleMatrix2D val$y;
/*     */         
/* 199 */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { TridiagonalDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, TridiagonalDoubleMatrix2D.this.getQuick(paramAnonymousInt1, paramAnonymousInt2) * this.val$y.getQuick(paramAnonymousInt1, paramAnonymousInt2));
/* 200 */           return paramAnonymousDouble;
/*     */         }
/*     */         
/* 203 */       });
/* 204 */       return this;
/*     */     }
/*     */     
/* 207 */     if (paramDoubleDoubleFunction == Functions.div) {
/* 208 */       forEachNonZero(new IntIntDoubleFunction() {
/*     */         private final DoubleMatrix2D val$y;
/*     */         
/* 211 */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { TridiagonalDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, TridiagonalDoubleMatrix2D.this.getQuick(paramAnonymousInt1, paramAnonymousInt2) / this.val$y.getQuick(paramAnonymousInt1, paramAnonymousInt2));
/* 212 */           return paramAnonymousDouble;
/*     */         }
/*     */         
/* 215 */       });
/* 216 */       return this;
/*     */     }
/*     */     
/* 219 */     return super.assign(paramDoubleMatrix2D, paramDoubleDoubleFunction);
/*     */   }
/*     */   
/* 222 */   public DoubleMatrix2D forEachNonZero(IntIntDoubleFunction paramIntIntDoubleFunction) { for (int i = 0; i <= 2; i++) {
/* 223 */       int j = 0;int k = 0;
/* 224 */       switch (i) {
/* 225 */       case 0:  j = 1;
/*     */       case 2: 
/* 227 */         k = 1;
/*     */       }
/* 229 */       int m = this.dims[i];
/* 230 */       int n = this.dims[(i + 1)];
/*     */       
/* 232 */       for (int i1 = m; i1 < n; k++) {
/* 233 */         double d1 = this.values[i1];
/* 234 */         if (d1 != 0.0D) {
/* 235 */           double d2 = paramIntIntDoubleFunction.apply(j, k, d1);
/* 236 */           if (d2 != d1) {
/* 237 */             if (d2 == 0.0D) this.dims[(i + 4)] += 1;
/* 238 */             this.values[i1] = d2;
/*     */           }
/*     */         }
/* 232 */         i1++;j++;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 243 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D getContent()
/*     */   {
/* 250 */     return this;
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
/*     */   public double getQuick(int paramInt1, int paramInt2)
/*     */   {
/* 264 */     int i = paramInt1;
/* 265 */     int j = paramInt2;
/*     */     
/* 267 */     int k = j - i + 1;
/* 268 */     int m = i;
/* 269 */     if (k == 0) { m = j;
/*     */     }
/* 271 */     if ((k >= 0) && (k <= 2)) {
/* 272 */       return this.values[(this.dims[k] + m)];
/*     */     }
/* 274 */     return 0.0D;
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
/*     */ 
/*     */ 
/*     */ 
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
/* 311 */     return new TridiagonalDoubleMatrix2D(paramInt1, paramInt2);
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
/* 322 */     return new SparseDoubleMatrix1D(paramInt);
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
/*     */   public void setQuick(int paramInt1, int paramInt2, double paramDouble)
/*     */   {
/* 336 */     int i = paramInt1;
/* 337 */     int j = paramInt2;
/*     */     
/* 339 */     int k = paramDouble == 0.0D ? 1 : 0;
/*     */     
/* 341 */     int m = j - i + 1;
/* 342 */     int n = i;
/* 343 */     if (m == 0) { n = j;
/*     */     }
/* 345 */     if ((m >= 0) && (m <= 2)) {
/* 346 */       int i1 = this.dims[m] + n;
/* 347 */       if (this.values[i1] != 0.0D) {
/* 348 */         if (k != 0) { this.dims[(m + 4)] -= 1;
/*     */         }
/*     */       }
/* 351 */       else if (k == 0) { this.dims[(m + 4)] += 1;
/*     */       }
/* 353 */       this.values[i1] = paramDouble;
/* 354 */       return;
/*     */     }
/*     */     
/* 357 */     if (k == 0) { throw new IllegalArgumentException("Can't store non-zero value to non-tridiagonal coordinate: row=" + paramInt1 + ", column=" + paramInt2 + ", value=" + paramDouble);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix1D zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2, boolean paramBoolean)
/*     */   {
/* 421 */     int i = this.rows;
/* 422 */     int j = this.columns;
/* 423 */     if (paramBoolean) {
/* 424 */       i = this.columns;
/* 425 */       j = this.rows;
/*     */     }
/*     */     
/* 428 */     int k = paramDoubleMatrix1D2 == null ? 1 : 0;
/* 429 */     if (paramDoubleMatrix1D2 == null) { paramDoubleMatrix1D2 = new DenseDoubleMatrix1D(i);
/*     */     }
/* 431 */     if ((!this.isNoView) || (!(paramDoubleMatrix1D1 instanceof DenseDoubleMatrix1D)) || (!(paramDoubleMatrix1D2 instanceof DenseDoubleMatrix1D))) {
/* 432 */       return super.zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, paramBoolean);
/*     */     }
/*     */     
/* 435 */     if ((j != paramDoubleMatrix1D1.size()) || (i > paramDoubleMatrix1D2.size())) {
/* 436 */       throw new IllegalArgumentException("Incompatible args: " + (paramBoolean ? viewDice() : this).toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*     */     }
/* 438 */     if (k == 0) { paramDoubleMatrix1D2.assign(Functions.mult(paramDouble2 / paramDouble1));
/*     */     }
/* 440 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D1 = (DenseDoubleMatrix1D)paramDoubleMatrix1D2;
/* 441 */     double[] arrayOfDouble1 = localDenseDoubleMatrix1D1.elements;
/* 442 */     int m = localDenseDoubleMatrix1D1.stride;
/* 443 */     int n = paramDoubleMatrix1D2.index(0);
/*     */     
/* 445 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D2 = (DenseDoubleMatrix1D)paramDoubleMatrix1D1;
/* 446 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D2.elements;
/* 447 */     int i1 = localDenseDoubleMatrix1D2.stride;
/* 448 */     int i2 = paramDoubleMatrix1D1.index(0);
/*     */     
/* 450 */     if ((arrayOfDouble2 == null) || (arrayOfDouble1 == null)) { throw new InternalError();
/*     */     }
/* 452 */     forEachNonZero(new IntIntDoubleFunction() {
/*     */       private final boolean val$transposeA;
/*     */       
/* 455 */       public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { if (this.val$transposeA) { int i = paramAnonymousInt1;paramAnonymousInt1 = paramAnonymousInt2;paramAnonymousInt2 = i; }
/* 456 */         this.val$zElements[(this.val$zi + this.val$zStride * paramAnonymousInt1)] += paramAnonymousDouble * this.val$yElements[(this.val$yi + this.val$yStride * paramAnonymousInt2)];
/*     */         
/*     */ 
/* 459 */         return paramAnonymousDouble;
/*     */       }
/*     */     });
/*     */     
/*     */ 
/* 464 */     if (paramDouble1 != 1.0D) paramDoubleMatrix1D2.assign(Functions.mult(paramDouble1));
/* 465 */     return paramDoubleMatrix1D2;
/*     */   }
/*     */   
/* 468 */   public DoubleMatrix2D zMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2) { if (paramBoolean2) paramDoubleMatrix2D1 = paramDoubleMatrix2D1.viewDice();
/* 469 */     int i = this.rows;
/* 470 */     int j = this.columns;
/* 471 */     if (paramBoolean1) {
/* 472 */       i = this.columns;
/* 473 */       j = this.rows;
/*     */     }
/* 475 */     int k = paramDoubleMatrix2D1.columns;
/* 476 */     int m = paramDoubleMatrix2D2 == null ? 1 : 0;
/* 477 */     if (paramDoubleMatrix2D2 == null) { paramDoubleMatrix2D2 = new DenseDoubleMatrix2D(i, k);
/*     */     }
/* 479 */     if (paramDoubleMatrix2D1.rows != j)
/* 480 */       throw new IllegalArgumentException("Matrix2D inner dimensions must agree:" + toStringShort() + ", " + (paramBoolean2 ? paramDoubleMatrix2D1.viewDice() : paramDoubleMatrix2D1).toStringShort());
/* 481 */     if ((paramDoubleMatrix2D2.rows != i) || (paramDoubleMatrix2D2.columns != k))
/* 482 */       throw new IllegalArgumentException("Incompatibel result matrix: " + toStringShort() + ", " + (paramBoolean2 ? paramDoubleMatrix2D1.viewDice() : paramDoubleMatrix2D1).toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort());
/* 483 */     if ((this == paramDoubleMatrix2D2) || (paramDoubleMatrix2D1 == paramDoubleMatrix2D2)) {
/* 484 */       throw new IllegalArgumentException("Matrices must not be identical");
/*     */     }
/* 486 */     if (m == 0) { paramDoubleMatrix2D2.assign(Functions.mult(paramDouble2));
/*     */     }
/*     */     
/* 489 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D1 = new DoubleMatrix1D[j];
/* 490 */     int n = j; do { arrayOfDoubleMatrix1D1[n] = paramDoubleMatrix2D1.viewRow(n);n--; } while (n >= 0);
/* 491 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D2 = new DoubleMatrix1D[i];
/* 492 */     int i1 = i; do { arrayOfDoubleMatrix1D2[i1] = paramDoubleMatrix2D2.viewRow(i1);i1--; } while (i1 >= 0);
/*     */     
/* 494 */     PlusMult localPlusMult = PlusMult.plusMult(0.0D);
/*     */     
/* 496 */     forEachNonZero(new IntIntDoubleFunction() { private final PlusMult val$fun;
/*     */       private final double val$alpha;
/*     */       
/* 499 */       public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { this.val$fun.multiplicator = (paramAnonymousDouble * this.val$alpha);
/* 500 */         if (!this.val$transposeA) {
/* 501 */           this.val$Crows[paramAnonymousInt1].assign(this.val$Brows[paramAnonymousInt2], this.val$fun);
/*     */         } else
/* 503 */           this.val$Crows[paramAnonymousInt2].assign(this.val$Brows[paramAnonymousInt1], this.val$fun);
/* 504 */         return paramAnonymousDouble;
/*     */       }
/*     */       
/*     */ 
/* 508 */     });
/* 509 */     return paramDoubleMatrix2D2;
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


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/TridiagonalDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */