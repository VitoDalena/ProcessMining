/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.list.AbstractIntList;
/*     */ import cern.colt.list.AbstractList;
/*     */ import cern.colt.list.IntArrayList;
/*     */ import cern.colt.matrix.DoubleFactory1D;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.jet.math.Mult;
/*     */ import cern.jet.math.PlusMult;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LUDecompositionQuick
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1020L;
/*     */   protected DoubleMatrix2D LU;
/*     */   protected int pivsign;
/*     */   protected int[] piv;
/*     */   protected boolean isNonSingular;
/*     */   protected Algebra algebra;
/*     */   protected transient double[] workDouble;
/*     */   protected transient int[] work1;
/*     */   protected transient int[] work2;
/*     */   
/*     */   public LUDecompositionQuick()
/*     */   {
/*  72 */     this(Property.DEFAULT.tolerance());
/*     */   }
/*     */   
/*     */ 
/*     */   public LUDecompositionQuick(double paramDouble)
/*     */   {
/*  78 */     this.algebra = new Algebra(paramDouble);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void decompose(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/*  89 */     this.LU = paramDoubleMatrix2D;
/*  90 */     int i = paramDoubleMatrix2D.rows();
/*  91 */     int j = paramDoubleMatrix2D.columns();
/*     */     
/*     */ 
/*  94 */     if ((this.piv == null) || (this.piv.length != i)) this.piv = new int[i];
/*  95 */     int k = i; do { this.piv[k] = k;k--; } while (k >= 0);
/*  96 */     this.pivsign = 1;
/*     */     
/*  98 */     if (i * j == 0) {
/*  99 */       setLU(this.LU);
/* 100 */       return;
/*     */     }
/*     */     
/*     */ 
/* 104 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[i];
/* 105 */     for (int m = 0; m < i; m++) { arrayOfDoubleMatrix1D[m] = this.LU.viewRow(m);
/*     */     }
/* 107 */     IntArrayList localIntArrayList = new IntArrayList();
/* 108 */     DoubleMatrix1D localDoubleMatrix1D = this.LU.viewColumn(0).like();
/* 109 */     Mult localMult = Mult.mult(0.0D);
/*     */     
/*     */ 
/* 112 */     for (int n = 0; n < j; n++)
/*     */     {
/* 114 */       localDoubleMatrix1D.assign(this.LU.viewColumn(n));
/*     */       
/*     */ 
/* 117 */       int i1 = i / 10;
/* 118 */       localDoubleMatrix1D.getNonZeros(localIntArrayList, null, i1);
/* 119 */       int i2 = localIntArrayList.size();
/* 120 */       int i3 = i2 < i1 ? 1 : 0;
/*     */       
/*     */       double d1;
/* 123 */       for (int i4 = 0; i4 < i; i4++) {
/* 124 */         i5 = Math.min(i4, n);
/*     */         
/* 126 */         if (i3 != 0) {
/* 127 */           d1 = arrayOfDoubleMatrix1D[i4].zDotProduct(localDoubleMatrix1D, 0, i5, localIntArrayList);
/*     */         }
/*     */         else {
/* 130 */           d1 = arrayOfDoubleMatrix1D[i4].zDotProduct(localDoubleMatrix1D, 0, i5);
/*     */         }
/* 132 */         double d3 = localDoubleMatrix1D.getQuick(i4);
/* 133 */         double d5 = d3 - d1;
/* 134 */         localDoubleMatrix1D.setQuick(i4, d5);
/* 135 */         this.LU.setQuick(i4, n, d5);
/* 136 */         if (i3 != 0) {
/* 137 */           if ((d3 == 0.0D) && (d5 != 0.0D)) {
/* 138 */             int i8 = localIntArrayList.binarySearch(i4);
/* 139 */             i8 = -i8 - 1;
/* 140 */             localIntArrayList.beforeInsert(i8, i4);
/*     */           }
/* 142 */           if ((d3 != 0.0D) && (d5 == 0.0D)) {
/* 143 */             localIntArrayList.remove(localIntArrayList.binarySearch(i4));
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 149 */       int i5 = n;
/* 150 */       if (i5 < i) {
/* 151 */         d1 = Math.abs(localDoubleMatrix1D.getQuick(i5));
/* 152 */         for (int i7 = n + 1; i7 < i; i7++) {
/* 153 */           double d4 = Math.abs(localDoubleMatrix1D.getQuick(i7));
/* 154 */           if (d4 > d1) {
/* 155 */             i5 = i7;
/* 156 */             d1 = d4;
/*     */           }
/*     */         }
/*     */       }
/* 160 */       if (i5 != n) {
/* 161 */         arrayOfDoubleMatrix1D[i5].swap(arrayOfDoubleMatrix1D[n]);
/* 162 */         int i6 = this.piv[i5];this.piv[i5] = this.piv[n];this.piv[n] = i6;
/* 163 */         this.pivsign = (-this.pivsign);
/*     */       }
/*     */       
/*     */       double d2;
/*     */       
/* 168 */       if ((n < i) && ((d2 = this.LU.getQuick(n, n)) != 0.0D)) {
/* 169 */         localMult.multiplicator = (1.0D / d2);
/* 170 */         this.LU.viewColumn(n).viewPart(n + 1, i - (n + 1)).assign(localMult);
/*     */       }
/*     */     }
/*     */     
/* 174 */     setLU(this.LU);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void decompose(DoubleMatrix2D paramDoubleMatrix2D, int paramInt)
/*     */   {
/* 184 */     if ((!this.algebra.property().isSquare(paramDoubleMatrix2D)) || (paramInt < 0) || (paramInt > 2)) {
/* 185 */       decompose(paramDoubleMatrix2D);
/* 186 */       return;
/*     */     }
/*     */     
/* 189 */     this.LU = paramDoubleMatrix2D;
/* 190 */     int i = paramDoubleMatrix2D.rows();
/* 191 */     int j = paramDoubleMatrix2D.columns();
/*     */     
/*     */ 
/* 194 */     if ((this.piv == null) || (this.piv.length != i)) this.piv = new int[i];
/* 195 */     int k = i; do { this.piv[k] = k;k--; } while (k >= 0);
/* 196 */     this.pivsign = 1;
/*     */     
/* 198 */     if (i * j == 0) {
/* 199 */       setLU(paramDoubleMatrix2D);
/* 200 */       return;
/*     */     }
/*     */     
/*     */ 
/* 204 */     if (paramInt == 2)
/*     */     {
/* 206 */       if (j > 1) { paramDoubleMatrix2D.setQuick(1, 0, paramDoubleMatrix2D.getQuick(1, 0) / paramDoubleMatrix2D.getQuick(0, 0));
/*     */       }
/* 208 */       for (int m = 1; m < j; m++) {
/* 209 */         double d = paramDoubleMatrix2D.getQuick(m, m) - paramDoubleMatrix2D.getQuick(m, m - 1) * paramDoubleMatrix2D.getQuick(m - 1, m);
/* 210 */         paramDoubleMatrix2D.setQuick(m, m, d);
/* 211 */         if (m < j - 1) paramDoubleMatrix2D.setQuick(m + 1, m, paramDoubleMatrix2D.getQuick(m + 1, m) / d);
/*     */       }
/*     */     }
/* 214 */     setLU(paramDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double det()
/*     */   {
/* 221 */     int i = m();
/* 222 */     int j = n();
/* 223 */     if (i != j) { throw new IllegalArgumentException("Matrix must be square.");
/*     */     }
/* 225 */     if (!isNonsingular()) { return 0.0D;
/*     */     }
/* 227 */     double d = this.pivsign;
/* 228 */     for (int k = 0; k < j; k++) {
/* 229 */       d *= this.LU.getQuick(k, k);
/*     */     }
/* 231 */     return d;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected double[] getDoublePivot()
/*     */   {
/* 238 */     int i = m();
/* 239 */     double[] arrayOfDouble = new double[i];
/* 240 */     for (int j = 0; j < i; j++) {
/* 241 */       arrayOfDouble[j] = this.piv[j];
/*     */     }
/* 243 */     return arrayOfDouble;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getL()
/*     */   {
/* 250 */     return lowerTriangular(this.LU.copy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getLU()
/*     */   {
/* 257 */     return this.LU.copy();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int[] getPivot()
/*     */   {
/* 264 */     return this.piv;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public DoubleMatrix2D getU()
/*     */   {
/* 271 */     return upperTriangular(this.LU.copy());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isNonsingular()
/*     */   {
/* 278 */     return this.isNonSingular;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected boolean isNonsingular(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 285 */     int i = paramDoubleMatrix2D.rows();
/* 286 */     int j = paramDoubleMatrix2D.columns();
/* 287 */     double d = this.algebra.property().tolerance();
/* 288 */     int k = Math.min(j, i);
/*     */     do {
/* 290 */       if (Math.abs(paramDoubleMatrix2D.getQuick(k, k)) <= d) return false;
/* 288 */       k--; } while (k >= 0);
/*     */     
/*     */ 
/*     */ 
/* 292 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D lowerTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 361 */     int i = paramDoubleMatrix2D.rows();
/* 362 */     int j = paramDoubleMatrix2D.columns();
/* 363 */     int k = Math.min(i, j);
/* 364 */     int m = k;
/* 365 */     do { int n = k;
/* 366 */       do { if (m < n) { paramDoubleMatrix2D.setQuick(m, n, 0.0D);
/* 367 */         } else if (m == n) paramDoubleMatrix2D.setQuick(m, n, 1.0D);
/* 365 */         n--; } while (n >= 0);
/* 364 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 370 */     if (j > i) { paramDoubleMatrix2D.viewPart(0, k, i, j - k).assign(0.0D);
/*     */     }
/* 372 */     return paramDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */   protected int m()
/*     */   {
/* 378 */     return this.LU.rows();
/*     */   }
/*     */   
/*     */ 
/*     */   protected int n()
/*     */   {
/* 384 */     return this.LU.columns();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setLU(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 391 */     this.LU = paramDoubleMatrix2D;
/* 392 */     this.isNonSingular = isNonsingular(paramDoubleMatrix2D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void solve(DoubleMatrix1D paramDoubleMatrix1D)
/*     */   {
/* 403 */     this.algebra.property().checkRectangular(this.LU);
/* 404 */     int i = m();
/* 405 */     int j = n();
/* 406 */     if (paramDoubleMatrix1D.size() != i) throw new IllegalArgumentException("Matrix dimensions must agree.");
/* 407 */     if (!isNonsingular()) { throw new IllegalArgumentException("Matrix is singular.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 412 */     if ((this.workDouble == null) || (this.workDouble.length < i)) this.workDouble = new double[i];
/* 413 */     this.algebra.permute(paramDoubleMatrix1D, this.piv, this.workDouble);
/*     */     
/* 415 */     if (i * j == 0) { return;
/*     */     }
/*     */     
/* 418 */     for (int k = 0; k < j; k++) {
/* 419 */       double d1 = paramDoubleMatrix1D.getQuick(k);
/* 420 */       if (d1 != 0.0D) {
/* 421 */         for (int n = k + 1; n < j; n++)
/*     */         {
/* 423 */           double d3 = this.LU.getQuick(n, k);
/* 424 */           if (d3 != 0.0D) { paramDoubleMatrix1D.setQuick(n, paramDoubleMatrix1D.getQuick(n) - d1 * d3);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 430 */     for (int m = j - 1; m >= 0; m--)
/*     */     {
/* 432 */       paramDoubleMatrix1D.setQuick(m, paramDoubleMatrix1D.getQuick(m) / this.LU.getQuick(m, m));
/* 433 */       double d2 = paramDoubleMatrix1D.getQuick(m);
/* 434 */       if (d2 != 0.0D) {
/* 435 */         for (int i1 = 0; i1 < m; i1++)
/*     */         {
/* 437 */           double d4 = this.LU.getQuick(i1, m);
/* 438 */           if (d4 != 0.0D) { paramDoubleMatrix1D.setQuick(i1, paramDoubleMatrix1D.getQuick(i1) - d2 * d4);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void solve(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 453 */     this.algebra.property().checkRectangular(this.LU);
/* 454 */     int i = m();
/* 455 */     int j = n();
/* 456 */     if (paramDoubleMatrix2D.rows() != i) throw new IllegalArgumentException("Matrix row dimensions must agree.");
/* 457 */     if (!isNonsingular()) { throw new IllegalArgumentException("Matrix is singular.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 462 */     if ((this.work1 == null) || (this.work1.length < i)) { this.work1 = new int[i];
/*     */     }
/* 464 */     this.algebra.permuteRows(paramDoubleMatrix2D, this.piv, this.work1);
/*     */     
/* 466 */     if (i * j == 0) return;
/* 467 */     int k = paramDoubleMatrix2D.columns();
/*     */     
/*     */ 
/* 470 */     DoubleMatrix1D[] arrayOfDoubleMatrix1D = new DoubleMatrix1D[j];
/* 471 */     for (int m = 0; m < j; m++) { arrayOfDoubleMatrix1D[m] = paramDoubleMatrix2D.viewRow(m);
/*     */     }
/*     */     
/* 474 */     Mult localMult = Mult.div(0.0D);
/* 475 */     PlusMult localPlusMult = PlusMult.minusMult(0.0D);
/*     */     
/* 477 */     IntArrayList localIntArrayList = new IntArrayList();
/* 478 */     DoubleMatrix1D localDoubleMatrix1D = DoubleFactory1D.dense.make(k);
/*     */     int i2;
/*     */     int i3;
/* 481 */     int i4; for (int n = 0; n < j; n++)
/*     */     {
/* 483 */       localDoubleMatrix1D.assign(arrayOfDoubleMatrix1D[n]);
/*     */       
/*     */ 
/* 486 */       i1 = k / 10;
/* 487 */       localDoubleMatrix1D.getNonZeros(localIntArrayList, null, i1);
/* 488 */       i2 = localIntArrayList.size();
/* 489 */       i3 = i2 < i1 ? 1 : 0;
/*     */       
/* 491 */       for (i4 = n + 1; i4 < j; i4++)
/*     */       {
/*     */ 
/*     */ 
/* 495 */         localPlusMult.multiplicator = (-this.LU.getQuick(i4, n));
/* 496 */         if (localPlusMult.multiplicator != 0.0D) {
/* 497 */           if (i3 != 0) {
/* 498 */             arrayOfDoubleMatrix1D[i4].assign(localDoubleMatrix1D, localPlusMult, localIntArrayList);
/*     */           }
/*     */           else {
/* 501 */             arrayOfDoubleMatrix1D[i4].assign(localDoubleMatrix1D, localPlusMult);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 508 */     for (int i1 = j - 1; i1 >= 0; i1--)
/*     */     {
/*     */ 
/* 511 */       localMult.multiplicator = (1.0D / this.LU.getQuick(i1, i1));
/* 512 */       arrayOfDoubleMatrix1D[i1].assign(localMult);
/*     */       
/*     */ 
/* 515 */       if (localDoubleMatrix1D == null) localDoubleMatrix1D = DoubleFactory1D.dense.make(paramDoubleMatrix2D.columns());
/* 516 */       localDoubleMatrix1D.assign(arrayOfDoubleMatrix1D[i1]);
/*     */       
/*     */ 
/* 519 */       i2 = k / 10;
/* 520 */       localDoubleMatrix1D.getNonZeros(localIntArrayList, null, i2);
/* 521 */       i3 = localIntArrayList.size();
/* 522 */       i4 = i3 < i2 ? 1 : 0;
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 527 */       for (int i5 = 0; i5 < i1; i5++)
/*     */       {
/*     */ 
/*     */ 
/* 531 */         localPlusMult.multiplicator = (-this.LU.getQuick(i5, i1));
/* 532 */         if (localPlusMult.multiplicator != 0.0D) {
/* 533 */           if (i4 != 0) {
/* 534 */             arrayOfDoubleMatrix1D[i5].assign(localDoubleMatrix1D, localPlusMult, localIntArrayList);
/*     */           }
/*     */           else {
/* 537 */             arrayOfDoubleMatrix1D[i5].assign(localDoubleMatrix1D, localPlusMult);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void solveOld(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 552 */     this.algebra.property().checkRectangular(this.LU);
/* 553 */     int i = m();
/* 554 */     int j = n();
/* 555 */     if (paramDoubleMatrix2D.rows() != i) throw new IllegalArgumentException("Matrix row dimensions must agree.");
/* 556 */     if (!isNonsingular()) { throw new IllegalArgumentException("Matrix is singular.");
/*     */     }
/*     */     
/* 559 */     int k = paramDoubleMatrix2D.columns();
/*     */     
/* 561 */     if ((this.work1 == null) || (this.work1.length < i)) { this.work1 = new int[i];
/*     */     }
/* 563 */     this.algebra.permuteRows(paramDoubleMatrix2D, this.piv, this.work1);
/*     */     double d;
/*     */     int i1;
/* 566 */     for (int m = 0; m < j; m++) {
/* 567 */       for (n = m + 1; n < j; n++) {
/* 568 */         d = this.LU.getQuick(n, m);
/* 569 */         if (d != 0.0D) {
/* 570 */           for (i1 = 0; i1 < k; i1++)
/*     */           {
/* 572 */             paramDoubleMatrix2D.setQuick(n, i1, paramDoubleMatrix2D.getQuick(n, i1) - paramDoubleMatrix2D.getQuick(m, i1) * d);
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 578 */     for (int n = j - 1; n >= 0; n--) {
/* 579 */       d = 1.0D / this.LU.getQuick(n, n);
/* 580 */       if (d != 1.0D) {
/* 581 */         for (i1 = 0; i1 < k; i1++)
/*     */         {
/* 583 */           paramDoubleMatrix2D.setQuick(n, i1, paramDoubleMatrix2D.getQuick(n, i1) * d);
/*     */         }
/*     */       }
/* 586 */       for (i1 = 0; i1 < n; i1++) {
/* 587 */         d = this.LU.getQuick(i1, n);
/* 588 */         if (d != 0.0D) {
/* 589 */           for (int i2 = 0; i2 < k; i2++)
/*     */           {
/* 591 */             paramDoubleMatrix2D.setQuick(i1, i2, paramDoubleMatrix2D.getQuick(i1, i2) - paramDoubleMatrix2D.getQuick(n, i2) * d);
/*     */           }
/*     */         }
/*     */       }
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
/*     */   public String toString()
/*     */   {
/* 607 */     StringBuffer localStringBuffer = new StringBuffer();
/* 608 */     String str = "Illegal operation or error: ";
/*     */     
/* 610 */     localStringBuffer.append("-----------------------------------------------------------------------------\n");
/* 611 */     localStringBuffer.append("LUDecompositionQuick(A) --> isNonSingular(A), det(A), pivot, L, U, inverse(A)\n");
/* 612 */     localStringBuffer.append("-----------------------------------------------------------------------------\n");
/*     */     
/* 614 */     localStringBuffer.append("isNonSingular = ");
/* 615 */     try { localStringBuffer.append(String.valueOf(isNonsingular()));
/* 616 */     } catch (IllegalArgumentException localIllegalArgumentException1) { localStringBuffer.append(str + localIllegalArgumentException1.getMessage());
/*     */     }
/* 618 */     localStringBuffer.append("\ndet = ");
/* 619 */     try { localStringBuffer.append(String.valueOf(det()));
/* 620 */     } catch (IllegalArgumentException localIllegalArgumentException2) { localStringBuffer.append(str + localIllegalArgumentException2.getMessage());
/*     */     }
/* 622 */     localStringBuffer.append("\npivot = ");
/* 623 */     try { localStringBuffer.append(String.valueOf(new IntArrayList(getPivot())));
/* 624 */     } catch (IllegalArgumentException localIllegalArgumentException3) { localStringBuffer.append(str + localIllegalArgumentException3.getMessage());
/*     */     }
/* 626 */     localStringBuffer.append("\n\nL = ");
/* 627 */     try { localStringBuffer.append(String.valueOf(getL()));
/* 628 */     } catch (IllegalArgumentException localIllegalArgumentException4) { localStringBuffer.append(str + localIllegalArgumentException4.getMessage());
/*     */     }
/* 630 */     localStringBuffer.append("\n\nU = ");
/* 631 */     try { localStringBuffer.append(String.valueOf(getU()));
/* 632 */     } catch (IllegalArgumentException localIllegalArgumentException5) { localStringBuffer.append(str + localIllegalArgumentException5.getMessage());
/*     */     }
/* 634 */     localStringBuffer.append("\n\ninverse(A) = ");
/* 635 */     DoubleMatrix2D localDoubleMatrix2D = DoubleFactory2D.dense.identity(this.LU.rows());
/* 636 */     try { solve(localDoubleMatrix2D);localStringBuffer.append(String.valueOf(localDoubleMatrix2D));
/* 637 */     } catch (IllegalArgumentException localIllegalArgumentException6) { localStringBuffer.append(str + localIllegalArgumentException6.getMessage());
/*     */     }
/* 639 */     return localStringBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DoubleMatrix2D upperTriangular(DoubleMatrix2D paramDoubleMatrix2D)
/*     */   {
/* 647 */     int i = paramDoubleMatrix2D.rows();
/* 648 */     int j = paramDoubleMatrix2D.columns();
/* 649 */     int k = Math.min(i, j);
/* 650 */     int m = k;
/* 651 */     do { int n = k;
/* 652 */       do { if (m > n) paramDoubleMatrix2D.setQuick(m, n, 0.0D);
/* 651 */         n--; } while (n >= 0);
/* 650 */       m--; } while (m >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 655 */     if (j < i) { paramDoubleMatrix2D.viewPart(k, 0, i - k, j).assign(0.0D);
/*     */     }
/* 657 */     return paramDoubleMatrix2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private double[] xgetDoublePivot()
/*     */   {
/* 664 */     int i = m();
/* 665 */     double[] arrayOfDouble = new double[i];
/* 666 */     for (int j = 0; j < i; j++) {
/* 667 */       arrayOfDouble[j] = this.piv[j];
/*     */     }
/* 669 */     return arrayOfDouble;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/LUDecompositionQuick.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */