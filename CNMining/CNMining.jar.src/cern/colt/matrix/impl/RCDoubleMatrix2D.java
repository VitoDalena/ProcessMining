/*     */ package cern.colt.matrix.impl;
/*     */ 
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.function.IntIntDoubleFunction;
/*     */ import cern.colt.list.AbstractDoubleList;
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.DoubleArrayList;
/*     */ import cern.colt.list.IntArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RCDoubleMatrix2D
/*     */   extends WrapperDoubleMatrix2D
/*     */ {
/*     */   protected IntArrayList indexes;
/*     */   protected DoubleArrayList values;
/*     */   protected int[] starts;
/*     */   
/*     */   public RCDoubleMatrix2D(double[][] paramArrayOfDouble)
/*     */   {
/* 136 */     this(paramArrayOfDouble.length, paramArrayOfDouble.length == 0 ? 0 : paramArrayOfDouble[0].length);
/* 137 */     assign(paramArrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RCDoubleMatrix2D(int paramInt1, int paramInt2)
/*     */   {
/* 147 */     super(null);
/*     */     try {
/* 149 */       setUp(paramInt1, paramInt2);
/*     */     }
/*     */     catch (IllegalArgumentException localIllegalArgumentException) {
/* 152 */       if (!"matrix too large".equals(localIllegalArgumentException.getMessage())) throw localIllegalArgumentException;
/*     */     }
/* 154 */     this.indexes = new IntArrayList();
/* 155 */     this.values = new DoubleArrayList();
/* 156 */     this.starts = new int[paramInt1 + 1];
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D assign(double paramDouble)
/*     */   {
/* 165 */     if (paramDouble == 0.0D) {
/* 166 */       this.indexes.clear();
/* 167 */       this.values.clear();
/* 168 */       int i = this.starts.length; do { this.starts[i] = 0;i--; } while (i >= 0);
/*     */     } else {
/* 170 */       super.assign(paramDouble); }
/* 171 */     return this;
/*     */   }
/*     */   
/* 174 */   public DoubleMatrix2D assign(DoubleFunction paramDoubleFunction) { if ((paramDoubleFunction instanceof Mult)) {
/* 175 */       double d = ((Mult)paramDoubleFunction).multiplicator;
/* 176 */       if (d == 1.0D) return this;
/* 177 */       if (d == 0.0D) return assign(0.0D);
/* 178 */       if (d != d) { return assign(d);
/*     */       }
/* 180 */       double[] arrayOfDouble = this.values.elements();
/* 181 */       int i = this.values.size();
/* 182 */       do { arrayOfDouble[i] *= d;i--;
/* 181 */       } while (i >= 0);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 196 */       super.assign(paramDoubleFunction);
/*     */     }
/* 198 */     return this;
/*     */   }
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
/* 210 */     if (paramDoubleMatrix2D == this) return this;
/* 211 */     checkShape(paramDoubleMatrix2D);
/*     */     
/* 213 */     if (!(paramDoubleMatrix2D instanceof RCDoubleMatrix2D))
/*     */     {
/*     */ 
/* 216 */       assign(0.0D);
/* 217 */       paramDoubleMatrix2D.forEachNonZero(new IntIntDoubleFunction()
/*     */       {
/*     */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) {
/* 220 */           RCDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, paramAnonymousDouble);
/* 221 */           return paramAnonymousDouble;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 241 */       });
/* 242 */       return this;
/*     */     }
/*     */     
/*     */ 
/* 246 */     RCDoubleMatrix2D localRCDoubleMatrix2D = (RCDoubleMatrix2D)paramDoubleMatrix2D;
/*     */     
/* 248 */     System.arraycopy(localRCDoubleMatrix2D.starts, 0, this.starts, 0, this.starts.length);
/* 249 */     int i = localRCDoubleMatrix2D.indexes.size();
/* 250 */     this.indexes.setSize(i);
/* 251 */     this.values.setSize(i);
/* 252 */     this.indexes.replaceFromToWithFrom(0, i - 1, localRCDoubleMatrix2D.indexes, 0);
/* 253 */     this.values.replaceFromToWithFrom(0, i - 1, localRCDoubleMatrix2D.values, 0);
/*     */     
/* 255 */     return this;
/*     */   }
/*     */   
/* 258 */   public DoubleMatrix2D assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleDoubleFunction paramDoubleDoubleFunction) { checkShape(paramDoubleMatrix2D);
/*     */     
/* 260 */     if ((paramDoubleDoubleFunction instanceof PlusMult)) {
/* 261 */       double d = ((PlusMult)paramDoubleDoubleFunction).multiplicator;
/* 262 */       if (d == 0.0D) return this;
/* 263 */       paramDoubleMatrix2D.forEachNonZero(new IntIntDoubleFunction() {
/*     */         private final double val$alpha;
/*     */         
/* 266 */         public double apply(int paramAnonymousInt1, int paramAnonymousInt2, double paramAnonymousDouble) { RCDoubleMatrix2D.this.setQuick(paramAnonymousInt1, paramAnonymousInt2, RCDoubleMatrix2D.this.getQuick(paramAnonymousInt1, paramAnonymousInt2) + this.val$alpha * paramAnonymousDouble);
/* 267 */           return paramAnonymousDouble;
/*     */         }
/*     */         
/* 270 */       });
/* 271 */       return this; }
/*     */     int[] arrayOfInt;
/*     */     double[] arrayOfDouble;
/* 274 */     int i; int j; int k; int m; if (paramDoubleDoubleFunction == Functions.mult) {
/* 275 */       arrayOfInt = this.indexes.elements();
/* 276 */       arrayOfDouble = this.values.elements();
/*     */       
/* 278 */       i = this.starts.length - 1;
/* 279 */       do { j = this.starts[i];
/* 280 */         k = this.starts[(i + 1)];
/* 281 */         do { m = arrayOfInt[k];
/* 282 */           arrayOfDouble[k] *= paramDoubleMatrix2D.getQuick(i, m);
/* 283 */           if (arrayOfDouble[k] == 0.0D) remove(i, m);
/* 280 */           k--; } while (k >= j);
/* 278 */         i--; } while (i >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 286 */       return this;
/*     */     }
/*     */     
/* 289 */     if (paramDoubleDoubleFunction == Functions.div) {
/* 290 */       arrayOfInt = this.indexes.elements();
/* 291 */       arrayOfDouble = this.values.elements();
/*     */       
/* 293 */       i = this.starts.length - 1;
/* 294 */       do { j = this.starts[i];
/* 295 */         k = this.starts[(i + 1)];
/* 296 */         do { m = arrayOfInt[k];
/* 297 */           arrayOfDouble[k] /= paramDoubleMatrix2D.getQuick(i, m);
/* 298 */           if (arrayOfDouble[k] == 0.0D) remove(i, m);
/* 295 */           k--; } while (k >= j);
/* 293 */         i--; } while (i >= 0);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 301 */       return this;
/*     */     }
/*     */     
/* 304 */     return super.assign(paramDoubleMatrix2D, paramDoubleDoubleFunction);
/*     */   }
/*     */   
/* 307 */   public DoubleMatrix2D forEachNonZero(IntIntDoubleFunction paramIntIntDoubleFunction) { int[] arrayOfInt = this.indexes.elements();
/* 308 */     double[] arrayOfDouble = this.values.elements();
/*     */     
/* 310 */     int i = this.starts.length - 1;
/* 311 */     do { int j = this.starts[i];
/* 312 */       int k = this.starts[(i + 1)];
/* 313 */       do { int m = arrayOfInt[k];
/* 314 */         double d1 = arrayOfDouble[k];
/* 315 */         double d2 = paramIntIntDoubleFunction.apply(i, m, d1);
/* 316 */         if (d2 != d1) arrayOfDouble[k] = d2;
/* 312 */         k--; } while (k >= j);
/* 310 */       i--; } while (i >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 319 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D getContent()
/*     */   {
/* 326 */     return this;
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
/* 340 */     int i = this.indexes.binarySearchFromTo(paramInt2, this.starts[paramInt1], this.starts[(paramInt1 + 1)] - 1);
/* 341 */     double d = 0.0D;
/* 342 */     if (i >= 0) d = this.values.getQuick(i);
/* 343 */     return d;
/*     */   }
/*     */   
/* 346 */   protected void insert(int paramInt1, int paramInt2, int paramInt3, double paramDouble) { this.indexes.beforeInsert(paramInt3, paramInt2);
/* 347 */     this.values.beforeInsert(paramInt3, paramDouble);
/* 348 */     int i = this.starts.length; do { this.starts[i] += 1;i--; } while (i > paramInt1);
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
/* 361 */     return new RCDoubleMatrix2D(paramInt1, paramInt2);
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
/* 372 */   public DoubleMatrix1D like1D(int paramInt) { return new SparseDoubleMatrix1D(paramInt); }
/*     */   
/*     */   protected void remove(int paramInt1, int paramInt2) {
/* 375 */     this.indexes.remove(paramInt2);
/* 376 */     this.values.remove(paramInt2);
/* 377 */     int i = this.starts.length; do { this.starts[i] -= 1;i--; } while (i > paramInt1);
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
/* 391 */     int i = this.indexes.binarySearchFromTo(paramInt2, this.starts[paramInt1], this.starts[(paramInt1 + 1)] - 1);
/* 392 */     if (i >= 0) {
/* 393 */       if (paramDouble == 0.0D) {
/* 394 */         remove(paramInt1, i);
/*     */       } else
/* 396 */         this.values.setQuick(i, paramDouble);
/* 397 */       return;
/*     */     }
/*     */     
/* 400 */     if (paramDouble != 0.0D) {
/* 401 */       i = -i - 1;
/* 402 */       insert(paramInt1, paramInt2, i, paramDouble);
/*     */     }
/*     */   }
/*     */   
/* 406 */   public void trimToSize() { this.indexes.trimToSize();
/* 407 */     this.values.trimToSize();
/*     */   }
/*     */   
/* 410 */   public DoubleMatrix1D zMult(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2, boolean paramBoolean) { int i = this.rows;
/* 411 */     int j = this.columns;
/* 412 */     if (paramBoolean) {
/* 413 */       i = this.columns;
/* 414 */       j = this.rows;
/*     */     }
/*     */     
/* 417 */     int k = (paramDoubleMatrix1D2 == null) || (!paramBoolean) ? 1 : 0;
/* 418 */     if (paramDoubleMatrix1D2 == null) { paramDoubleMatrix1D2 = new DenseDoubleMatrix1D(i);
/*     */     }
/* 420 */     if ((!(paramDoubleMatrix1D1 instanceof DenseDoubleMatrix1D)) || (!(paramDoubleMatrix1D2 instanceof DenseDoubleMatrix1D))) {
/* 421 */       return super.zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, paramBoolean);
/*     */     }
/*     */     
/* 424 */     if ((j != paramDoubleMatrix1D1.size()) || (i > paramDoubleMatrix1D2.size())) {
/* 425 */       throw new IllegalArgumentException("Incompatible args: " + (paramBoolean ? viewDice() : this).toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*     */     }
/* 427 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D1 = (DenseDoubleMatrix1D)paramDoubleMatrix1D2;
/* 428 */     double[] arrayOfDouble1 = localDenseDoubleMatrix1D1.elements;
/* 429 */     int m = localDenseDoubleMatrix1D1.stride;
/* 430 */     int n = paramDoubleMatrix1D2.index(0);
/*     */     
/* 432 */     DenseDoubleMatrix1D localDenseDoubleMatrix1D2 = (DenseDoubleMatrix1D)paramDoubleMatrix1D1;
/* 433 */     double[] arrayOfDouble2 = localDenseDoubleMatrix1D2.elements;
/* 434 */     int i1 = localDenseDoubleMatrix1D2.stride;
/* 435 */     int i2 = paramDoubleMatrix1D1.index(0);
/*     */     
/* 437 */     if ((arrayOfDouble2 == null) || (arrayOfDouble1 == null)) { throw new InternalError();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 453 */     int[] arrayOfInt = this.indexes.elements();
/* 454 */     double[] arrayOfDouble3 = this.values.elements();
/* 455 */     int i3 = this.starts.length - 1;
/* 456 */     int i4; int i5; double d; int i6; int i7; if (!paramBoolean) {
/* 457 */       for (i4 = 0; i4 < i3; i4++) {
/* 458 */         i5 = this.starts[(i4 + 1)];
/* 459 */         d = 0.0D;
/* 460 */         for (i6 = this.starts[i4]; i6 < i5; i6++) {
/* 461 */           i7 = arrayOfInt[i6];
/* 462 */           d += arrayOfDouble3[i6] * arrayOfDouble2[(i2 + i1 * i7)];
/*     */         }
/* 464 */         arrayOfDouble1[n] = (paramDouble1 * d + paramDouble2 * arrayOfDouble1[n]);
/* 465 */         n += m;
/*     */       }
/*     */     }
/*     */     else {
/* 469 */       if (k == 0) paramDoubleMatrix1D2.assign(Functions.mult(paramDouble2));
/* 470 */       for (i4 = 0; i4 < i3; i4++) {
/* 471 */         i5 = this.starts[(i4 + 1)];
/* 472 */         d = paramDouble1 * arrayOfDouble2[(i2 + i1 * i4)];
/* 473 */         for (i6 = this.starts[i4]; i6 < i5; i6++) {
/* 474 */           i7 = arrayOfInt[i6];
/* 475 */           arrayOfDouble1[(n + m * i7)] += arrayOfDouble3[i6] * d;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 480 */     return paramDoubleMatrix1D2;
/*     */   }
/*     */   
/* 483 */   public DoubleMatrix2D zMult(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble1, double paramDouble2, boolean paramBoolean1, boolean paramBoolean2) { if (paramBoolean2) paramDoubleMatrix2D1 = paramDoubleMatrix2D1.viewDice();
/* 484 */     int i = this.rows;
/* 485 */     int j = this.columns;
/* 486 */     if (paramBoolean1) {
/* 487 */       i = this.columns;
/* 488 */       j = this.rows;
/*     */     }
/* 490 */     int k = paramDoubleMatrix2D1.columns;
/* 491 */     int m = paramDoubleMatrix2D2 == null ? 1 : 0;
/* 492 */     if (paramDoubleMatrix2D2 == null) { paramDoubleMatrix2D2 = new DenseDoubleMatrix2D(i, k);
/*     */     }
/* 494 */     if (paramDoubleMatrix2D1.rows != j)
/* 495 */       throw new IllegalArgumentException("Matrix2D inner dimensions must agree:" + toStringShort() + ", " + (paramBoolean2 ? paramDoubleMatrix2D1.viewDice() : paramDoubleMatrix2D1).toStringShort());
/* 496 */     if ((paramDoubleMatrix2D2.rows != i) || (paramDoubleMatrix2D2.columns != k))
/* 497 */       throw new IllegalArgumentException("Incompatibel result matrix: " + toStringShort() + ", " + (paramBoolean2 ? paramDoubleMatrix2D1.viewDice() : paramDoubleMatrix2D1).toStringShort() + ", " + paramDoubleMatrix2D2.toStringShort());
/* 498 */     if ((this == paramDoubleMatrix2D2) || (paramDoubleMatrix2D1 == paramDoubleMatrix2D2)) {
/* 499 */       throw new IllegalArgumentException("Matrices must not be identical");
/*     */     }
/* 501 */     if (m == 0) { paramDoubleMatrix2D2.assign(Functions.mult(paramDouble2));
/*     */     }
/*     */     
/* 504 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D1 = new DoubleMatrix1D[j];
/* 505 */     int n = j; do { arrayOfDoubleMatrix1D1[n] = paramDoubleMatrix2D1.viewRow(n);n--; } while (n >= 0);
/* 506 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D2 = new DoubleMatrix1D[i];
/* 507 */     int i1 = i; do { arrayOfDoubleMatrix1D2[i1] = paramDoubleMatrix2D2.viewRow(i1);i1--; } while (i1 >= 0);
/*     */     
/* 509 */     PlusMult localPlusMult = PlusMult.plusMult(0.0D);
/*     */     
/* 511 */     int[] arrayOfInt = this.indexes.elements();
/* 512 */     double[] arrayOfDouble = this.values.elements();
/* 513 */     int i2 = this.starts.length - 1;
/* 514 */     do { int i3 = this.starts[i2];
/* 515 */       int i4 = this.starts[(i2 + 1)];
/* 516 */       do { int i5 = arrayOfInt[i4];
/* 517 */         localPlusMult.multiplicator = (arrayOfDouble[i4] * paramDouble1);
/* 518 */         if (!paramBoolean1) {
/* 519 */           arrayOfDoubleMatrix1D2[i2].assign(arrayOfDoubleMatrix1D1[i5], localPlusMult);
/*     */         } else {
/* 521 */           arrayOfDoubleMatrix1D2[i5].assign(arrayOfDoubleMatrix1D1[i2], localPlusMult);
/*     */         }
/* 515 */         i4--; } while (i4 >= i3);
/* 513 */       i2--; } while (i2 >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 525 */     return paramDoubleMatrix2D2;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/impl/RCDoubleMatrix2D.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */