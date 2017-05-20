/*     */ package cern.colt.matrix.bench;
/*     */ 
/*     */ import cern.colt.Timer;
/*     */ import cern.colt.function.Double9Function;
/*     */ import cern.colt.list.ObjectArrayList;
/*     */ import cern.colt.matrix.DoubleFactory2D;
/*     */ import cern.colt.matrix.DoubleFactory3D;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.DoubleMatrix3D;
/*     */ import cern.colt.matrix.doublealgo.Formatter;
/*     */ import cern.colt.matrix.doublealgo.Statistic;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.colt.matrix.impl.DenseDoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.FormerFactory;
/*     */ import cern.colt.matrix.linalg.Blas;
/*     */ import cern.colt.matrix.linalg.LUDecompositionQuick;
/*     */ import cern.colt.matrix.linalg.Property;
/*     */ import cern.colt.matrix.linalg.SeqBlas;
/*     */ import cern.colt.matrix.linalg.SmpBlas;
/*     */ import cern.jet.math.Functions;
/*     */ import hep.aida.bin.BinFunction1D;
/*     */ import hep.aida.bin.BinFunctions1D;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.io.PrintStream;
/*     */ import java.io.StreamTokenizer;
/*     */ 
/*     */ public class BenchmarkMatrix
/*     */ {
/*     */   protected static void bench_dgemm(String[] paramArrayOfString)
/*     */   {
/*     */     String[] arrayOfString;
/*     */     int i;
/*     */     double d;
/*     */     double[] arrayOfDouble;
/*     */     boolean bool1;
/*     */     boolean bool2;
/*     */     int[] arrayOfInt;
/*     */     try
/*     */     {
/*  43 */       int j = 1;
/*  44 */       arrayOfString = new String[] { paramArrayOfString[(j++)] };
/*  45 */       i = Integer.parseInt(paramArrayOfString[(j++)]);
/*  46 */       d = new Double(paramArrayOfString[(j++)]).doubleValue();
/*  47 */       arrayOfDouble = new double[] { new Double(paramArrayOfString[(j++)]).doubleValue() };
/*  48 */       bool1 = new Boolean(paramArrayOfString[(j++)]).booleanValue();
/*  49 */       bool2 = new Boolean(paramArrayOfString[(j++)]).booleanValue();
/*     */       
/*  51 */       arrayOfInt = new int[paramArrayOfString.length - j];
/*  52 */       for (int k = 0; j < paramArrayOfString.length; k++) { arrayOfInt[k] = Integer.parseInt(paramArrayOfString[j]);j++;
/*     */       }
/*     */     } catch (Exception localException) {
/*  55 */       System.out.println(usage(paramArrayOfString[0]));
/*  56 */       System.out.println("Ignoring command...\n");
/*  57 */       return;
/*     */     }
/*     */     
/*  60 */     SmpBlas.allocateBlas(i, SeqBlas.seqBlas);
/*  61 */     Double2DProcedure localDouble2DProcedure = fun_dgemm(bool1, bool2);
/*  62 */     String str1 = localDouble2DProcedure.toString();
/*  63 */     String str2 = bool1 + ", " + bool2 + ", 1, A, B, 0, C";
/*  64 */     str1 = str1 + " dgemm(" + str2 + ")";
/*  65 */     run(d, str1, localDouble2DProcedure, arrayOfString, arrayOfInt, arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void bench_dgemv(String[] paramArrayOfString)
/*     */   {
/*     */     String[] arrayOfString;
/*     */     int i;
/*     */     double d;
/*     */     double[] arrayOfDouble;
/*     */     boolean bool;
/*     */     int[] arrayOfInt;
/*     */     try
/*     */     {
/*  79 */       int j = 1;
/*  80 */       arrayOfString = new String[] { paramArrayOfString[(j++)] };
/*  81 */       i = Integer.parseInt(paramArrayOfString[(j++)]);
/*  82 */       d = new Double(paramArrayOfString[(j++)]).doubleValue();
/*  83 */       arrayOfDouble = new double[] { new Double(paramArrayOfString[(j++)]).doubleValue() };
/*  84 */       bool = new Boolean(paramArrayOfString[(j++)]).booleanValue();
/*     */       
/*  86 */       arrayOfInt = new int[paramArrayOfString.length - j];
/*  87 */       for (int k = 0; j < paramArrayOfString.length; k++) { arrayOfInt[k] = Integer.parseInt(paramArrayOfString[j]);j++;
/*     */       }
/*     */     } catch (Exception localException) {
/*  90 */       System.out.println(usage(paramArrayOfString[0]));
/*  91 */       System.out.println("Ignoring command...\n");
/*  92 */       return;
/*     */     }
/*     */     
/*  95 */     SmpBlas.allocateBlas(i, SeqBlas.seqBlas);
/*  96 */     Double2DProcedure localDouble2DProcedure = fun_dgemv(bool);
/*  97 */     String str1 = localDouble2DProcedure.toString();
/*  98 */     String str2 = bool + ", 1, A, B, 0, C";
/*  99 */     str1 = str1 + " dgemv(" + str2 + ")";
/* 100 */     run(d, str1, localDouble2DProcedure, arrayOfString, arrayOfInt, arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void bench_pow(String[] paramArrayOfString)
/*     */   {
/*     */     String[] arrayOfString;
/*     */     int i;
/*     */     double d;
/*     */     double[] arrayOfDouble;
/*     */     int j;
/*     */     int[] arrayOfInt;
/*     */     try
/*     */     {
/* 114 */       int k = 1;
/* 115 */       arrayOfString = new String[] { paramArrayOfString[(k++)] };
/* 116 */       i = Integer.parseInt(paramArrayOfString[(k++)]);
/* 117 */       d = new Double(paramArrayOfString[(k++)]).doubleValue();
/* 118 */       arrayOfDouble = new double[] { new Double(paramArrayOfString[(k++)]).doubleValue() };
/* 119 */       j = Integer.parseInt(paramArrayOfString[(k++)]);
/*     */       
/* 121 */       arrayOfInt = new int[paramArrayOfString.length - k];
/* 122 */       for (int m = 0; k < paramArrayOfString.length; m++) { arrayOfInt[m] = Integer.parseInt(paramArrayOfString[k]);k++;
/*     */       }
/*     */     } catch (Exception localException) {
/* 125 */       System.out.println(usage(paramArrayOfString[0]));
/* 126 */       System.out.println("Ignoring command...\n");
/* 127 */       return;
/*     */     }
/*     */     
/* 130 */     SmpBlas.allocateBlas(i, SeqBlas.seqBlas);
/* 131 */     Double2DProcedure localDouble2DProcedure = fun_pow(j);
/* 132 */     String str1 = localDouble2DProcedure.toString();
/* 133 */     String str2 = "A," + j;
/* 134 */     str1 = str1 + " pow(" + str2 + ")";
/* 135 */     run(d, str1, localDouble2DProcedure, arrayOfString, arrayOfInt, arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void benchGeneric(Double2DProcedure paramDouble2DProcedure, String[] paramArrayOfString)
/*     */   {
/*     */     String[] arrayOfString;
/*     */     int i;
/*     */     double d;
/*     */     double[] arrayOfDouble;
/*     */     int[] arrayOfInt;
/*     */     try
/*     */     {
/* 148 */       int j = 1;
/* 149 */       arrayOfString = new String[] { paramArrayOfString[(j++)] };
/* 150 */       i = Integer.parseInt(paramArrayOfString[(j++)]);
/* 151 */       d = new Double(paramArrayOfString[(j++)]).doubleValue();
/* 152 */       arrayOfDouble = new double[] { new Double(paramArrayOfString[(j++)]).doubleValue() };
/*     */       
/* 154 */       arrayOfInt = new int[paramArrayOfString.length - j];
/* 155 */       for (int k = 0; j < paramArrayOfString.length; k++) { arrayOfInt[k] = Integer.parseInt(paramArrayOfString[j]);j++;
/*     */       }
/*     */     } catch (Exception localException) {
/* 158 */       System.out.println(usage(paramArrayOfString[0]));
/* 159 */       System.out.println("Ignoring command...\n");
/* 160 */       return;
/*     */     }
/*     */     
/* 163 */     SmpBlas.allocateBlas(i, SeqBlas.seqBlas);
/* 164 */     String str = paramDouble2DProcedure.toString();
/* 165 */     run(d, str, paramDouble2DProcedure, arrayOfString, arrayOfInt, arrayOfDouble);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static String commands()
/*     */   {
/* 171 */     return "dgemm, dgemv, pow, assign, assignGetSet, assignGetSetQuick, assignLog, assignPlusMult, elementwiseMult, elementwiseMultB, SOR5, SOR8, LUDecompose, LUSolve";
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure fun_dgemm(boolean paramBoolean1, boolean paramBoolean2)
/*     */   {
/* 177 */     new Double2DProcedure() {
/* 178 */       public String toString() { return "Blas matrix-matrix mult"; }
/*     */       
/* 180 */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { super.setParameters(paramAnonymousDoubleMatrix2D1, paramAnonymousDoubleMatrix2D2);
/* 181 */         this.D = new DenseDoubleMatrix2D(this.A.rows(), this.A.columns()).assign(0.5D);
/* 182 */         this.C = this.D.copy();
/* 183 */         this.B = this.D.copy(); }
/*     */       
/* 185 */       public void init() { this.C.assign(this.D); }
/*     */       
/* 187 */       public void apply(Timer paramAnonymousTimer) { SmpBlas.smpBlas.dgemm(this.val$transposeA, this.val$transposeB, 1.0D, this.A, this.B, 0.0D, this.C); }
/*     */       
/*     */       public double operations() {
/* 190 */         double d1 = this.A.rows();
/* 191 */         double d2 = this.A.columns();
/* 192 */         double d3 = this.B.columns();
/* 193 */         return 2.0D * d1 * d2 * d3 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure fun_dgemv(boolean paramBoolean)
/*     */   {
/* 201 */     new Double2DProcedure() {
/* 202 */       public String toString() { return "Blas matrix-vector mult"; }
/*     */       
/* 204 */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { super.setParameters(paramAnonymousDoubleMatrix2D1, paramAnonymousDoubleMatrix2D2);
/* 205 */         this.D = new DenseDoubleMatrix2D(this.A.rows(), this.A.columns()).assign(0.5D);
/* 206 */         this.C = this.D.copy();
/* 207 */         this.B = this.D.copy(); }
/*     */       
/* 209 */       public void init() { this.C.viewRow(0).assign(this.D.viewRow(0)); }
/*     */       
/* 211 */       public void apply(Timer paramAnonymousTimer) { SmpBlas.smpBlas.dgemv(this.val$transposeA, 1.0D, this.A, this.B.viewRow(0), 0.0D, this.C.viewRow(0)); }
/*     */       
/*     */       public double operations() {
/* 214 */         double d1 = this.A.rows();
/* 215 */         double d2 = this.A.columns();
/*     */         
/* 217 */         return 2.0D * d1 * d2 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure fun_pow(int paramInt)
/*     */   {
/* 225 */     new Double2DProcedure() { public double dummy;
/*     */       
/* 227 */       public String toString() { return "matrix to the power of an exponent"; }
/*     */       
/* 229 */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { if (this.val$k < 0) {
/* 230 */           if ((!Property.ZERO.isDiagonallyDominantByRow(paramAnonymousDoubleMatrix2D1)) || (!Property.ZERO.isDiagonallyDominantByColumn(paramAnonymousDoubleMatrix2D1)))
/*     */           {
/* 232 */             Property.ZERO.generateNonSingular(paramAnonymousDoubleMatrix2D1);
/*     */           }
/* 234 */           super.setParameters(paramAnonymousDoubleMatrix2D1, paramAnonymousDoubleMatrix2D2);
/*     */         }
/*     */       }
/*     */       
/*     */       public void init() {}
/*     */       
/* 240 */       public void apply(Timer paramAnonymousTimer) { cern.colt.matrix.linalg.Algebra.DEFAULT.pow(this.A, this.val$k); }
/*     */       
/*     */       public double operations() {
/* 243 */         double d1 = this.A.rows();
/* 244 */         if (this.val$k == 0) return d1;
/* 245 */         double d2 = 0.0D;
/* 246 */         if (this.val$k < 0)
/*     */         {
/* 248 */           double d3 = Math.min(this.A.rows(), this.A.columns());
/* 249 */           d2 += 2.0D * d3 * d3 * d3 / 3.0D / 1000000.0D;
/*     */           
/*     */ 
/* 252 */           double d4 = this.A.columns();
/* 253 */           double d5 = this.B.columns();
/* 254 */           d2 += 2.0D * d5 * (d4 * d4 + d4) / 1000000.0D;
/*     */         }
/*     */         
/* 257 */         d2 += 2.0D * (Math.abs(this.val$k) - 1) * d1 * d1 * d1 / 1000000.0D;
/* 258 */         return d2;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funAssign()
/*     */   {
/* 266 */     new Double2DProcedure() {
/* 267 */       public String toString() { return "A.assign(B) [Mops/sec]"; }
/* 268 */       public void init() { this.A.assign(0.0D); }
/*     */       
/* 270 */       public void apply(Timer paramAnonymousTimer) { this.A.assign(this.B); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static Double2DProcedure funAssignGetSet()
/*     */   {
/* 278 */     new Double2DProcedure() {
/* 279 */       public String toString() { return "A.assign(B) via get and set [Mops/sec]"; }
/* 280 */       public void init() { this.A.assign(0.0D); }
/*     */       
/* 282 */       public void apply(Timer paramAnonymousTimer) { int i = this.B.rows();
/* 283 */         int j = this.B.columns();
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 291 */         for (int k = 0; k < i; k++) {
/* 292 */           for (int m = 0; m < j; m++) {
/* 293 */             this.A.set(k, m, this.B.get(k, m));
/*     */           }
/*     */         }
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funAssignGetSetQuick()
/*     */   {
/* 303 */     new Double2DProcedure() {
/* 304 */       public String toString() { return "A.assign(B) via getQuick and setQuick [Mops/sec]"; }
/* 305 */       public void init() { this.A.assign(0.0D); }
/*     */       
/* 307 */       public void apply(Timer paramAnonymousTimer) { int i = this.B.rows();
/* 308 */         int j = this.B.columns();
/*     */         
/*     */ 
/* 311 */         for (int k = 0; k < i; k++) {
/* 312 */           for (int m = 0; m < j; m++) {
/* 313 */             this.A.setQuick(k, m, this.B.getQuick(k, m));
/*     */           }
/*     */         }
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funAssignLog()
/*     */   {
/* 323 */     new Double2DProcedure() {
/* 324 */       public String toString() { return "A[i,j] = log(A[i,j]) via Blas.assign(fun) [Mflops/sec]"; }
/* 325 */       public void init() { this.A.assign(this.C); }
/*     */       
/* 327 */       public void apply(Timer paramAnonymousTimer) { SmpBlas.smpBlas.assign(this.A, Functions.log); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static Double2DProcedure funAssignPlusMult()
/*     */   {
/* 335 */     new Double2DProcedure() {
/* 336 */       public String toString() { return "A[i,j] = A[i,j] + s*B[i,j] via Blas.assign(fun) [Mflops/sec]"; }
/* 337 */       public void init() { this.A.assign(this.C); }
/*     */       
/* 339 */       public void apply(Timer paramAnonymousTimer) { SmpBlas.smpBlas.assign(this.A, this.B, Functions.plusMult(0.5D)); }
/*     */       
/*     */       public double operations() {
/* 342 */         double d1 = this.A.rows();
/* 343 */         double d2 = this.A.columns();
/* 344 */         return 2.0D * d1 * d2 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funCorrelation()
/*     */   {
/* 352 */     new Double2DProcedure() {
/* 353 */       public String toString() { return "xxxxxxx"; }
/*     */       
/*     */       public void init() {}
/* 356 */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { super.setParameters(paramAnonymousDoubleMatrix2D1.viewDice(), paramAnonymousDoubleMatrix2D2); }
/*     */       
/*     */       public void apply(Timer paramAnonymousTimer) {
/* 359 */         Statistic.correlation(Statistic.covariance(this.A));
/*     */       }
/*     */       
/*     */       public double operations() {
/* 363 */         double d1 = this.A.rows();
/* 364 */         double d2 = this.A.columns();
/* 365 */         return d1 * (d2 * d2 + d2) / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funElementwiseMult()
/*     */   {
/* 373 */     new Double2DProcedure() {
/* 374 */       public String toString() { return "A.assign(F.mult(0.5)) via Blas [Mflops/sec]"; }
/* 375 */       public void init() { this.A.assign(this.C); }
/*     */       
/* 377 */       public void apply(Timer paramAnonymousTimer) { SmpBlas.smpBlas.assign(this.A, Functions.mult(0.5D)); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static Double2DProcedure funElementwiseMultB()
/*     */   {
/* 385 */     new Double2DProcedure() {
/* 386 */       public String toString() { return "A.assign(B,F.mult) via Blas [Mflops/sec]"; }
/* 387 */       public void init() { this.A.assign(this.C); }
/*     */       
/* 389 */       public void apply(Timer paramAnonymousTimer) { SmpBlas.smpBlas.assign(this.A, this.B, Functions.mult); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static Double2DProcedure funGetQuick()
/*     */   {
/* 397 */     new Double2DProcedure() { public double dummy;
/*     */       
/* 399 */       public String toString() { return "xxxxxxx"; }
/*     */       
/*     */       public void init() {}
/* 402 */       public void apply(Timer paramAnonymousTimer) { int i = this.B.rows();
/* 403 */         int j = this.B.columns();
/* 404 */         double d = 0.0D;
/*     */         
/*     */ 
/* 407 */         for (int k = 0; k < i; k++) {
/* 408 */           for (int m = 0; m < j; m++) {
/* 409 */             d += this.A.getQuick(k, m);
/*     */           }
/*     */         }
/* 412 */         this.dummy = d;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funLUDecompose()
/*     */   {
/* 420 */     new Double2DProcedure() {
/* 421 */       LUDecompositionQuick lu = new LUDecompositionQuick(0.0D);
/* 422 */       public String toString() { return "LU.decompose(A) [Mflops/sec]"; }
/* 423 */       public void init() { this.A.assign(this.C); }
/*     */       
/* 425 */       public void apply(Timer paramAnonymousTimer) { this.lu.decompose(this.A); }
/*     */       
/*     */       public double operations() {
/* 428 */         double d = Math.min(this.A.rows(), this.A.columns());
/* 429 */         return 2.0D * d * d * d / 3.0D / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funLUSolve()
/*     */   {
/* 437 */     new Double2DProcedure() { LUDecompositionQuick lu;
/*     */       
/* 439 */       public String toString() { return "LU.solve(A) [Mflops/sec]"; }
/*     */       
/* 441 */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { this.lu = null;
/* 442 */         if ((!Property.ZERO.isDiagonallyDominantByRow(paramAnonymousDoubleMatrix2D1)) || (!Property.ZERO.isDiagonallyDominantByColumn(paramAnonymousDoubleMatrix2D1)))
/*     */         {
/* 444 */           Property.ZERO.generateNonSingular(paramAnonymousDoubleMatrix2D1);
/*     */         }
/* 446 */         super.setParameters(paramAnonymousDoubleMatrix2D1, paramAnonymousDoubleMatrix2D2);
/* 447 */         this.lu = new LUDecompositionQuick(0.0D);
/* 448 */         this.lu.decompose(paramAnonymousDoubleMatrix2D1); }
/*     */       
/* 450 */       public void init() { this.B.assign(this.D); }
/*     */       
/* 452 */       public void apply(Timer paramAnonymousTimer) { this.lu.solve(this.B); }
/*     */       
/*     */       public double operations() {
/* 455 */         double d1 = this.A.columns();
/* 456 */         double d2 = this.B.columns();
/* 457 */         return 2.0D * d2 * (d1 * d1 + d1) / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funMatMultLarge()
/*     */   {
/* 465 */     new Double2DProcedure() {
/* 466 */       public String toString() { return "xxxxxxx"; }
/*     */       
/*     */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) {
/* 469 */         this.A = paramAnonymousDoubleMatrix2D1;
/* 470 */         this.B = paramAnonymousDoubleMatrix2D2;
/* 471 */         this.C = paramAnonymousDoubleMatrix2D1.copy(); }
/*     */       
/* 473 */       public void init() { this.C.assign(0.0D); }
/* 474 */       public void apply(Timer paramAnonymousTimer) { this.A.zMult(this.B, this.C); }
/*     */       
/* 476 */       public double operations() { double d1 = this.A.rows();
/* 477 */         double d2 = this.A.columns();
/* 478 */         double d3 = this.B.columns();
/* 479 */         return 2.0D * d1 * d2 * d3 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funMatVectorMult()
/*     */   {
/* 487 */     new Double2DProcedure() {
/* 488 */       public String toString() { return "xxxxxxx"; }
/*     */       
/* 490 */       public void setParameters(DoubleMatrix2D paramAnonymousDoubleMatrix2D1, DoubleMatrix2D paramAnonymousDoubleMatrix2D2) { super.setParameters(paramAnonymousDoubleMatrix2D1, paramAnonymousDoubleMatrix2D2);
/* 491 */         this.D = new DenseDoubleMatrix2D(this.A.rows(), this.A.columns()).assign(0.5D);
/* 492 */         this.C = this.D.copy();
/* 493 */         this.B = this.D.copy(); }
/*     */       
/* 495 */       public void init() { this.C.viewRow(0).assign(this.D.viewRow(0)); }
/* 496 */       public void apply(Timer paramAnonymousTimer) { this.A.zMult(this.B.viewRow(0), this.C.viewRow(0)); }
/*     */       
/* 498 */       public double operations() { double d1 = this.A.rows();
/* 499 */         double d2 = this.A.columns();
/*     */         
/* 501 */         return 2.0D * d1 * d2 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funSetQuick()
/*     */   {
/* 509 */     new Double2DProcedure() { private int current;
/*     */       private double density;
/*     */       
/* 512 */       public String toString() { return "xxxxxxx"; }
/*     */       
/* 514 */       public void init() { this.A.assign(0.0D);
/* 515 */         int i = 123456;
/* 516 */         this.current = (4 * i + 1);
/* 517 */         this.density = (this.A.cardinality() / this.A.size());
/*     */       }
/*     */       
/* 520 */       public void apply(Timer paramAnonymousTimer) { int i = this.B.rows();
/* 521 */         int j = this.B.columns();
/*     */         
/*     */ 
/* 524 */         for (int k = 0; k < i; k++) {
/* 525 */           for (int m = 0; m < j; m++)
/*     */           {
/* 527 */             this.current *= 663608941;
/* 528 */             double d = (this.current & 0xFFFFFFFF) * 2.3283064365386963E-10D;
/*     */             
/* 530 */             if (d < this.density) {
/* 531 */               this.A.setQuick(k, m, d);
/*     */             } else {
/* 533 */               this.A.setQuick(k, m, 0.0D);
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */   protected static Double2DProcedure funSOR5()
/*     */   {
/* 543 */     new Double2DProcedure() {
/* 544 */       double value = 2.0D;
/* 545 */       double omega = 1.25D;
/* 546 */       final double alpha = this.omega * 0.25D;
/* 547 */       final double beta = 1.0D - this.omega;
/* 548 */       Double9Function function = new BenchmarkMatrix.19(this);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 556 */       public String toString() { return "A.zAssign8Neighbors(5 point function) [Mflops/sec]"; }
/* 557 */       public void init() { this.B.assign(this.D); }
/* 558 */       public void apply(Timer paramAnonymousTimer) { this.A.zAssign8Neighbors(this.B, this.function); }
/*     */       
/* 560 */       public double operations() { double d1 = this.A.columns();
/* 561 */         double d2 = this.A.rows();
/* 562 */         return 6.0D * d2 * d1 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funSOR8()
/*     */   {
/* 570 */     new Double2DProcedure() {
/* 571 */       double value = 2.0D;
/* 572 */       double omega = 1.25D;
/* 573 */       final double alpha = this.omega * 0.25D;
/* 574 */       final double beta = 1.0D - this.omega;
/* 575 */       Double9Function function = new BenchmarkMatrix.21(this);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 583 */       public String toString() { return "A.zAssign8Neighbors(9 point function) [Mflops/sec]"; }
/* 584 */       public void init() { this.B.assign(this.D); }
/* 585 */       public void apply(Timer paramAnonymousTimer) { this.A.zAssign8Neighbors(this.B, this.function); }
/*     */       
/* 587 */       public double operations() { double d1 = this.A.columns();
/* 588 */         double d2 = this.A.rows();
/* 589 */         return 10.0D * d2 * d1 / 1000000.0D;
/*     */       }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure funSort()
/*     */   {
/* 597 */     new Double2DProcedure() {
/* 598 */       public String toString() { return "xxxxxxx"; }
/* 599 */       public void init() { this.A.assign(this.C); }
/* 600 */       public void apply(Timer paramAnonymousTimer) { this.A.viewSorted(0); }
/*     */     };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected static DoubleFactory2D getFactory(String paramString)
/*     */   {
/* 608 */     if (paramString.equals("dense")) return DoubleFactory2D.dense;
/* 609 */     if (paramString.equals("sparse")) return DoubleFactory2D.sparse;
/* 610 */     if (paramString.equals("rowCompressed")) return DoubleFactory2D.rowCompressed;
/* 611 */     String str = "type=" + paramString + " is unknown. Use one of {dense,sparse,rowCompressed}";
/* 612 */     throw new IllegalArgumentException(str);
/*     */   }
/*     */   
/*     */ 
/*     */   protected static Double2DProcedure getGenericFunction(String paramString)
/*     */   {
/* 618 */     if (paramString.equals("dgemm")) return fun_dgemm(false, false);
/* 619 */     if (paramString.equals("dgemv")) return fun_dgemv(false);
/* 620 */     if (paramString.equals("pow")) return fun_pow(2);
/* 621 */     if (paramString.equals("assign")) return funAssign();
/* 622 */     if (paramString.equals("assignGetSet")) return funAssignGetSet();
/* 623 */     if (paramString.equals("assignGetSetQuick")) return funAssignGetSetQuick();
/* 624 */     if (paramString.equals("elementwiseMult")) return funElementwiseMult();
/* 625 */     if (paramString.equals("elementwiseMultB")) return funElementwiseMultB();
/* 626 */     if (paramString.equals("SOR5")) return funSOR5();
/* 627 */     if (paramString.equals("SOR8")) return funSOR8();
/* 628 */     if (paramString.equals("LUDecompose")) return funLUDecompose();
/* 629 */     if (paramString.equals("LUSolve")) return funLUSolve();
/* 630 */     if (paramString.equals("assignLog")) return funAssignLog();
/* 631 */     if (paramString.equals("assignPlusMult")) { return funAssignPlusMult();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 636 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static boolean handle(String[] paramArrayOfString)
/*     */   {
/* 642 */     boolean bool = true;
/* 643 */     String str1 = paramArrayOfString[0];
/* 644 */     if (str1.equals("dgemm")) { bench_dgemm(paramArrayOfString);
/* 645 */     } else if (str1.equals("dgemv")) { bench_dgemv(paramArrayOfString);
/* 646 */     } else if (str1.equals("pow")) { bench_pow(paramArrayOfString);
/*     */     } else {
/* 648 */       Double2DProcedure localDouble2DProcedure = getGenericFunction(str1);
/* 649 */       if (localDouble2DProcedure != null) {
/* 650 */         benchGeneric(localDouble2DProcedure, paramArrayOfString);
/*     */       }
/*     */       else {
/* 653 */         bool = false;
/* 654 */         String str2 = "Command=" + paramArrayOfString[0] + " is illegal or unknown. Should be one of " + commands() + "followed by appropriate parameters.\n" + usage() + "\nIgnoring this line.\n";
/* 655 */         System.out.println(str2);
/*     */       }
/*     */     }
/* 658 */     return bool;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static void main(String[] paramArrayOfString)
/*     */   {
/* 665 */     int i = paramArrayOfString.length;
/* 666 */     if ((i == 0) || ((i <= 1) && (paramArrayOfString[0].equals("-help")))) {
/* 667 */       System.out.println(usage());
/* 668 */       return;
/*     */     }
/* 670 */     if (paramArrayOfString[0].equals("-help")) {
/* 671 */       if (commands().indexOf(paramArrayOfString[1]) < 0) {
/* 672 */         System.out.println(paramArrayOfString[1] + ": no such command available.\n" + usage());
/*     */       }
/*     */       else {
/* 675 */         System.out.println(usage(paramArrayOfString[1]));
/*     */       }
/* 677 */       return;
/*     */     }
/*     */     
/* 680 */     System.out.println("Colt Matrix benchmark running on\n");
/* 681 */     System.out.println(BenchmarkKernel.systemInfo() + "\n");
/*     */     
/* 683 */     System.out.println("Colt Version is " + cern.colt.Version.asString() + "\n");
/*     */     
/* 685 */     Timer localTimer = new Timer().start();
/* 686 */     if (!paramArrayOfString[0].equals("-file")) {
/* 687 */       System.out.println("\n\nExecuting command = " + new ObjectArrayList(paramArrayOfString) + " ...");
/* 688 */       handle(paramArrayOfString);
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
/* 699 */       BufferedReader localBufferedReader = null;
/*     */       try {
/* 701 */         localBufferedReader = new BufferedReader(new FileReader(paramArrayOfString[1]));
/* 702 */       } catch (IOException localIOException1) { throw new RuntimeException(localIOException1.getMessage());
/*     */       }
/* 704 */       StreamTokenizer localStreamTokenizer = new StreamTokenizer(localBufferedReader);
/* 705 */       localStreamTokenizer.eolIsSignificant(true);
/* 706 */       localStreamTokenizer.slashSlashComments(true);
/* 707 */       localStreamTokenizer.slashStarComments(true);
/*     */       try {
/* 709 */         ObjectArrayList localObjectArrayList = new ObjectArrayList();
/*     */         int j;
/* 711 */         while ((j = localStreamTokenizer.nextToken()) != -1) { Object localObject;
/* 712 */           if (j == 10)
/*     */           {
/* 714 */             if (localObjectArrayList.size() > 0) {
/* 715 */               localObject = new String[localObjectArrayList.size()];
/* 716 */               for (int k = 0; k < localObjectArrayList.size(); k++) { localObject[k] = ((String)localObjectArrayList.get(k));
/*     */               }
/*     */               
/* 719 */               System.out.println("\n\nExecuting command = " + localObjectArrayList + " ...");
/* 720 */               handle((String[])localObject);
/*     */             }
/* 722 */             localObjectArrayList.clear();
/*     */           }
/*     */           else
/*     */           {
/* 726 */             cern.colt.matrix.impl.Former localFormer = new FormerFactory().create("%G");
/*     */             
/* 728 */             if (j == -2) {
/* 729 */               localObject = localFormer.form(localStreamTokenizer.nval);
/*     */             } else
/* 731 */               localObject = localStreamTokenizer.sval;
/* 732 */             if (localObject != null) localObjectArrayList.add(localObject);
/*     */           }
/*     */         }
/* 735 */         localBufferedReader.close();
/*     */         
/* 737 */         System.out.println("\nCommand file name used: " + paramArrayOfString[1] + "\nTo reproduce and compare results, here it's contents:");
/*     */         try {
/* 739 */           localBufferedReader = new BufferedReader(new FileReader(paramArrayOfString[1]));
/* 740 */         } catch (IOException localIOException3) { throw new RuntimeException(localIOException3.getMessage());
/*     */         }
/*     */         
/*     */ 
/*     */         String str;
/*     */         
/*     */ 
/* 747 */         while ((str = localBufferedReader.readLine()) != null) {
/* 748 */           System.out.println(localIOException3);
/*     */         }
/* 750 */         localBufferedReader.close();
/*     */       } catch (IOException localIOException2) {
/* 752 */         throw new RuntimeException(localIOException2.getMessage());
/*     */       }
/*     */     }
/* 755 */     System.out.println("\nProgram execution took a total of " + localTimer.minutes() + " minutes.");
/* 756 */     System.out.println("Good bye.");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private final int val$k;
/*     */   
/*     */   private final boolean val$transposeA;
/*     */   
/*     */   private final boolean val$transposeA;
/*     */   
/*     */   private final boolean val$transposeB;
/*     */   
/*     */   protected static void run(double paramDouble, String paramString, Double2DProcedure paramDouble2DProcedure, String[] paramArrayOfString, int[] paramArrayOfInt, double[] paramArrayOfDouble)
/*     */   {
/* 771 */     DoubleMatrix3D localDoubleMatrix3D = DoubleFactory3D.dense.make(paramArrayOfString.length, paramArrayOfInt.length, paramArrayOfDouble.length);
/* 772 */     Timer localTimer = new Timer().start();
/* 773 */     for (int i = 0; i < paramArrayOfString.length; i++)
/*     */     {
/*     */ 
/* 776 */       localObject1 = getFactory(paramArrayOfString[i]);
/* 777 */       System.out.print("\n@");
/*     */       
/* 779 */       for (int j = 0; j < paramArrayOfInt.length; j++) {
/* 780 */         int k = paramArrayOfInt[j];
/* 781 */         System.out.print("x");
/*     */         
/*     */ 
/* 784 */         for (int m = 0; m < paramArrayOfDouble.length; m++) {
/* 785 */           double d1 = paramArrayOfDouble[m];
/* 786 */           System.out.print(".");
/*     */           
/*     */ 
/*     */           float f;
/*     */           
/*     */ 
/* 792 */           if ((i <= 0) || (d1 < 0.1D) || (k < 500)) {
/* 793 */             double d2 = 0.5D;
/* 794 */             paramDouble2DProcedure.A = null;paramDouble2DProcedure.B = null;paramDouble2DProcedure.C = null;paramDouble2DProcedure.D = null;
/* 795 */             DoubleMatrix2D localDoubleMatrix2D = ((DoubleFactory2D)localObject1).sample(k, k, d2, d1);
/* 796 */             localObject6 = ((DoubleFactory2D)localObject1).sample(k, k, d2, d1);
/* 797 */             paramDouble2DProcedure.setParameters(localDoubleMatrix2D, (DoubleMatrix2D)localObject6);
/* 798 */             localDoubleMatrix2D = null;localObject6 = null;
/* 799 */             double d3 = paramDouble2DProcedure.operations();
/* 800 */             double d4 = BenchmarkKernel.run(paramDouble, paramDouble2DProcedure);
/* 801 */             f = (float)(d3 / d4);
/*     */           }
/*     */           else {
/* 804 */             f = NaN.0F;
/*     */           }
/* 806 */           localDoubleMatrix3D.set(i, j, m, f);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 812 */     localTimer.stop();
/*     */     
/* 814 */     Object localObject1 = "type";
/* 815 */     Object localObject2 = "size";
/* 816 */     Object localObject3 = "d";
/*     */     
/*     */ 
/* 819 */     String[] arrayOfString = paramArrayOfString;
/* 820 */     BinFunctions1D localBinFunctions1D = BinFunctions1D.functions;
/* 821 */     BinFunction1D[] arrayOfBinFunction1D = null;
/* 822 */     Object localObject4 = new String[paramArrayOfInt.length];
/* 823 */     Object localObject5 = new String[paramArrayOfDouble.length];
/* 824 */     int n = paramArrayOfInt.length; do { localObject4[n] = Integer.toString(paramArrayOfInt[n]);n--; } while (n >= 0);
/* 825 */     int i1 = paramArrayOfDouble.length; do { localObject5[i1] = Double.toString(paramArrayOfDouble[i1]);i1--; } while (i1 >= 0);
/* 826 */     System.out.println("*");
/*     */     
/* 828 */     Object localObject6 = localObject2;localObject2 = localObject3;localObject3 = localObject6;
/* 829 */     Object localObject7 = localObject4;localObject4 = localObject5;localObject5 = localObject7;
/* 830 */     localDoubleMatrix3D = localDoubleMatrix3D.viewDice(0, 2, 1);
/* 831 */     System.out.println(new Formatter("%1.3G").toTitleString(localDoubleMatrix3D, arrayOfString, (String[])localObject4, (String[])localObject5, (String)localObject1, (String)localObject2, (String)localObject3, "Performance of " + paramString, arrayOfBinFunction1D));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 837 */     System.out.println("Run took a total of " + localTimer + ". End of run.");
/*     */   }
/*     */   
/*     */ 
/*     */   protected static void runSpecial(double paramDouble, String paramString, Double2DProcedure paramDouble2DProcedure)
/*     */   {
/* 843 */     int[] arrayOfInt = { 10000 };
/* 844 */     double[] arrayOfDouble = { 1.0E-5D };
/* 845 */     boolean[] arrayOfBoolean = { true };
/*     */     
/* 847 */     DoubleMatrix2D localDoubleMatrix2D1 = DoubleFactory2D.dense.make(arrayOfInt.length, 4);
/* 848 */     Timer localTimer = new Timer().start();
/* 849 */     for (int i = 0; i < arrayOfInt.length; i++) {
/* 850 */       int j = arrayOfInt[i];
/* 851 */       double d1 = arrayOfDouble[i];
/* 852 */       int k = arrayOfBoolean[i];
/* 853 */       localDoubleFactory2D = k != 0 ? DoubleFactory2D.sparse : DoubleFactory2D.dense;
/* 854 */       System.out.print("\n@");
/*     */       
/* 856 */       System.out.print("x");
/* 857 */       double d2 = 0.5D;
/* 858 */       paramDouble2DProcedure.A = null;paramDouble2DProcedure.B = null;paramDouble2DProcedure.C = null;paramDouble2DProcedure.D = null;
/* 859 */       DoubleMatrix2D localDoubleMatrix2D2 = localDoubleFactory2D.sample(j, j, d2, d1);
/* 860 */       DoubleMatrix2D localDoubleMatrix2D3 = localDoubleFactory2D.sample(j, j, d2, d1);
/* 861 */       paramDouble2DProcedure.setParameters(localDoubleMatrix2D2, localDoubleMatrix2D3);
/* 862 */       localDoubleMatrix2D2 = null;localDoubleMatrix2D3 = null;
/* 863 */       float f1 = BenchmarkKernel.run(paramDouble, paramDouble2DProcedure);
/* 864 */       double d3 = paramDouble2DProcedure.operations();
/* 865 */       float f2 = (float)(d3 / f1);
/* 866 */       localDoubleMatrix2D1.viewRow(i).set(0, k != 0 ? 0.0D : 1.0D);
/* 867 */       localDoubleMatrix2D1.viewRow(i).set(1, j);
/* 868 */       localDoubleMatrix2D1.viewRow(i).set(2, d1);
/* 869 */       localDoubleMatrix2D1.viewRow(i).set(3, f2);
/*     */     }
/*     */     
/*     */ 
/* 873 */     localTimer.stop();
/*     */     
/* 875 */     BinFunctions1D localBinFunctions1D = BinFunctions1D.functions;
/* 876 */     BinFunction1D[] arrayOfBinFunction1D = null;
/* 877 */     String[] arrayOfString1 = null;
/* 878 */     String[] arrayOfString2 = { "dense (y=1,n=0)", "size", "density", "flops/sec" };
/* 879 */     DoubleFactory2D localDoubleFactory2D = null;
/* 880 */     String str = null;
/* 881 */     System.out.println("*");
/* 882 */     System.out.println(new Formatter("%1.3G").toTitleString(localDoubleMatrix2D1, arrayOfString1, arrayOfString2, localDoubleFactory2D, str, paramString, arrayOfBinFunction1D));
/*     */     
/* 884 */     System.out.println("Run took a total of " + localTimer + ". End of run.");
/*     */   }
/*     */   
/*     */ 
/*     */   protected static String usage()
/*     */   {
/* 890 */     String str = "\nUsage (help): To get this help, type java cern.colt.matrix.bench.BenchmarkMatrix -help\nTo get help on a command's args, omit args and type java cern.colt.matrix.bench.BenchmarkMatrix -help <command>\nAvailable commands: " + commands() + "\n\n" + "Usage (direct): java cern.colt.matrix.bench.BenchmarkMatrix command {args}\n" + "Example: dgemm dense 2 2.0 0.999 false true 5 10 25 50 100 250 500\n\n" + "Usage (batch mode): java cern.colt.matrix.bench.BenchmarkMatrix -file <file>\nwhere <file> is a text file with each line holding a command followed by appropriate args (comments and empty lines ignored).\n\n" + "Example file's content:\n" + "dgemm dense 1 2.0 0.999 false true 5 10 25 50 100 250 500\n" + "dgemm dense 2 2.0 0.999 false true 5 10 25 50 100 250 500\n\n" + "/*\n" + "Java like comments in file are ignored\n" + "dgemv dense 1 2.0 0.001 false 5 10 25 50 100 250 500 1000\n" + "dgemv sparse 1 2.0 0.001 false 5 10 25 50 100 250 500 1000\n" + "dgemv rowCompressed 1 2.0 0.001 false 5 10 25 50 100 250 500 1000\n" + "*/\n" + "// more comments ignored\n";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 909 */     return str;
/*     */   }
/*     */   
/*     */ 
/*     */   protected static String usage(String paramString)
/*     */   {
/* 915 */     String str = paramString + " description: " + getGenericFunction(paramString).toString() + "\nArguments to be supplied:\n" + "\t<operation> <type> <cpus> <minSecs> <density>";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 920 */     if (paramString.equals("dgemv")) str = str + " <transposeA>";
/* 921 */     if (paramString.equals("dgemm")) str = str + " <transposeA> <transposeB>";
/* 922 */     if (paramString.equals("pow")) str = str + " <exponent>";
/* 923 */     str = str + " {sizes}\n" + "where\n" + "\toperation = the operation to benchmark; in this case: " + paramString + "\n" + "\ttype = matrix type to be used; e.g. dense, sparse or rowCompressed\n" + "\tcpus = #cpus available; e.g. 1 or 2 or ...\n" + "\tminSecs = #seconds each operation shall at least run; e.g. 2.0 is a good number giving realistic timings\n" + "\tdensity = the density of the matrices to be benchmarked; e.g. 0.999 is very dense, 0.001 is very sparse\n";
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 932 */     if (paramString.equals("dgemv")) str = str + "\ttransposeA = false or true\n";
/* 933 */     if (paramString.equals("dgemm")) str = str + "\ttransposeA = false or true\n\ttransposeB = false or true\n";
/* 934 */     if (paramString.equals("pow")) str = str + "\texponent = the number of times to multiply; e.g. 1000\n";
/* 935 */     str = str + "\tsizes = a list of problem sizes; e.g. 100 200 benchmarks squared 100x100 and 200x200 matrices";
/*     */     
/* 937 */     return str;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/bench/BenchmarkMatrix.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */