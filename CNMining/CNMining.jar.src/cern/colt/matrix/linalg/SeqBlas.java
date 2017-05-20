/*     */ package cern.colt.matrix.linalg;
/*     */ 
/*     */ import cern.colt.function.DoubleDoubleFunction;
/*     */ import cern.colt.function.DoubleFunction;
/*     */ import cern.colt.matrix.DoubleMatrix1D;
/*     */ import cern.colt.matrix.DoubleMatrix2D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix1D;
/*     */ import cern.colt.matrix.impl.AbstractMatrix2D;
/*     */ import cern.jet.math.Functions;
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
/*     */ public class SeqBlas
/*     */   implements Blas
/*     */ {
/*  30 */   public static final Blas seqBlas = new SeqBlas();
/*     */   
/*  32 */   private static final Functions F = Functions.functions;
/*     */   
/*     */ 
/*     */ 
/*     */   public void assign(DoubleMatrix2D paramDoubleMatrix2D, DoubleFunction paramDoubleFunction)
/*     */   {
/*  38 */     paramDoubleMatrix2D.assign(paramDoubleFunction);
/*     */   }
/*     */   
/*  41 */   public void assign(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, DoubleDoubleFunction paramDoubleDoubleFunction) { paramDoubleMatrix2D1.assign(paramDoubleMatrix2D2, paramDoubleDoubleFunction); }
/*     */   
/*     */   public double dasum(DoubleMatrix1D paramDoubleMatrix1D) {
/*  44 */     return paramDoubleMatrix1D.aggregate(Functions.plus, Functions.abs);
/*     */   }
/*     */   
/*  47 */   public void daxpy(double paramDouble, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) { paramDoubleMatrix1D2.assign(paramDoubleMatrix1D1, Functions.plusMult(paramDouble)); }
/*     */   
/*     */   public void daxpy(double paramDouble, DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2) {
/*  50 */     paramDoubleMatrix2D2.assign(paramDoubleMatrix2D1, Functions.plusMult(paramDouble));
/*     */   }
/*     */   
/*  53 */   public void dcopy(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) { paramDoubleMatrix1D2.assign(paramDoubleMatrix1D1); }
/*     */   
/*     */   public void dcopy(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2) {
/*  56 */     paramDoubleMatrix2D2.assign(paramDoubleMatrix2D1);
/*     */   }
/*     */   
/*  59 */   public double ddot(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) { return paramDoubleMatrix1D1.zDotProduct(paramDoubleMatrix1D2); }
/*     */   
/*     */   public void dgemm(boolean paramBoolean1, boolean paramBoolean2, double paramDouble1, DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2, double paramDouble2, DoubleMatrix2D paramDoubleMatrix2D3) {
/*  62 */     paramDoubleMatrix2D1.zMult(paramDoubleMatrix2D2, paramDoubleMatrix2D3, paramDouble1, paramDouble2, paramBoolean1, paramBoolean2);
/*     */   }
/*     */   
/*  65 */   public void dgemv(boolean paramBoolean, double paramDouble1, DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D1, double paramDouble2, DoubleMatrix1D paramDoubleMatrix1D2) { paramDoubleMatrix2D.zMult(paramDoubleMatrix1D1, paramDoubleMatrix1D2, paramDouble1, paramDouble2, paramBoolean); }
/*     */   
/*     */   public void dger(double paramDouble, DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, DoubleMatrix2D paramDoubleMatrix2D) {
/*  68 */     PlusMult localPlusMult = PlusMult.plusMult(0.0D);
/*  69 */     int i = paramDoubleMatrix2D.rows();
/*  70 */     do { localPlusMult.multiplicator = (paramDouble * paramDoubleMatrix1D1.getQuick(i));
/*  71 */       paramDoubleMatrix2D.viewRow(i).assign(paramDoubleMatrix1D2, localPlusMult);i--;
/*  69 */     } while (i >= 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  76 */   public double dnrm2(DoubleMatrix1D paramDoubleMatrix1D) { return Math.sqrt(Algebra.DEFAULT.norm2(paramDoubleMatrix1D)); }
/*     */   
/*     */   public void drot(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2, double paramDouble1, double paramDouble2) {
/*  79 */     paramDoubleMatrix1D1.checkSize(paramDoubleMatrix1D2);
/*  80 */     DoubleMatrix1D localDoubleMatrix1D = paramDoubleMatrix1D1.copy();
/*     */     
/*  82 */     paramDoubleMatrix1D1.assign(Functions.mult(paramDouble1));
/*  83 */     paramDoubleMatrix1D1.assign(paramDoubleMatrix1D2, Functions.plusMult(paramDouble2));
/*     */     
/*  85 */     paramDoubleMatrix1D2.assign(Functions.mult(paramDouble1));
/*  86 */     paramDoubleMatrix1D2.assign(localDoubleMatrix1D, Functions.minusMult(paramDouble2));
/*     */   }
/*     */   
/*     */   public void drotg(double paramDouble1, double paramDouble2, double[] paramArrayOfDouble)
/*     */   {
/*  91 */     double d3 = paramDouble2;
/*     */     
/*  93 */     if (Math.abs(paramDouble1) > Math.abs(paramDouble2)) { d3 = paramDouble1;
/*     */     }
/*  95 */     double d4 = Math.abs(paramDouble1) + Math.abs(paramDouble2);
/*     */     double d5;
/*  97 */     double d1; double d2; double d6; if (d4 != 0.0D)
/*     */     {
/*  99 */       double d7 = paramDouble1 / d4;
/* 100 */       double d8 = paramDouble2 / d4;
/* 101 */       d5 = d4 * Math.sqrt(d7 * d7 + d8 * d8);
/* 102 */       d5 = sign(1.0D, d3) * d5;
/* 103 */       d1 = paramDouble1 / d5;
/* 104 */       d2 = paramDouble2 / d5;
/* 105 */       d6 = 1.0D;
/* 106 */       if (Math.abs(paramDouble1) > Math.abs(paramDouble2)) d6 = d2;
/* 107 */       if ((Math.abs(paramDouble2) >= Math.abs(paramDouble1)) && (d1 != 0.0D)) d6 = 1.0D / d1;
/*     */     }
/*     */     else
/*     */     {
/* 111 */       d1 = 1.0D;
/* 112 */       d2 = 0.0D;
/* 113 */       d5 = 0.0D;
/* 114 */       d6 = 0.0D;
/*     */     }
/*     */     
/*     */ 
/* 118 */     paramDouble1 = d5;
/* 119 */     paramDouble2 = d6;
/*     */     
/* 121 */     paramArrayOfDouble[0] = paramDouble1;
/* 122 */     paramArrayOfDouble[1] = paramDouble2;
/* 123 */     paramArrayOfDouble[2] = d1;
/* 124 */     paramArrayOfDouble[3] = d2;
/*     */   }
/*     */   
/*     */   public void dscal(double paramDouble, DoubleMatrix1D paramDoubleMatrix1D) {
/* 128 */     paramDoubleMatrix1D.assign(Functions.mult(paramDouble));
/*     */   }
/*     */   
/*     */   public void dscal(double paramDouble, DoubleMatrix2D paramDoubleMatrix2D) {
/* 132 */     paramDoubleMatrix2D.assign(Functions.mult(paramDouble));
/*     */   }
/*     */   
/*     */   public void dswap(DoubleMatrix1D paramDoubleMatrix1D1, DoubleMatrix1D paramDoubleMatrix1D2) {
/* 136 */     paramDoubleMatrix1D2.swap(paramDoubleMatrix1D1);
/*     */   }
/*     */   
/*     */   public void dswap(DoubleMatrix2D paramDoubleMatrix2D1, DoubleMatrix2D paramDoubleMatrix2D2) {
/* 140 */     paramDoubleMatrix2D1.checkShape(paramDoubleMatrix2D2);
/* 141 */     int i = paramDoubleMatrix2D1.rows(); do { paramDoubleMatrix2D1.viewRow(i).swap(paramDoubleMatrix2D2.viewRow(i));i--; } while (i >= 0);
/*     */   }
/*     */   
/* 144 */   public void dsymv(boolean paramBoolean, double paramDouble1, DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D1, double paramDouble2, DoubleMatrix1D paramDoubleMatrix1D2) { if (paramBoolean) paramDoubleMatrix2D = paramDoubleMatrix2D.viewDice();
/* 145 */     Property.DEFAULT.checkSquare(paramDoubleMatrix2D);
/* 146 */     int i = paramDoubleMatrix2D.rows();
/* 147 */     if ((i != paramDoubleMatrix1D1.size()) || (i != paramDoubleMatrix1D2.size())) {
/* 148 */       throw new IllegalArgumentException(paramDoubleMatrix2D.toStringShort() + ", " + paramDoubleMatrix1D1.toStringShort() + ", " + paramDoubleMatrix1D2.toStringShort());
/*     */     }
/* 150 */     DoubleMatrix1D localDoubleMatrix1D = paramDoubleMatrix1D1.like();
/* 151 */     for (int j = 0; j < i; j++) {
/* 152 */       double d = 0.0D;
/* 153 */       for (int k = 0; k <= j; k++) {
/* 154 */         d += paramDoubleMatrix2D.getQuick(j, k) * paramDoubleMatrix1D1.getQuick(k);
/*     */       }
/* 156 */       for (int m = j + 1; m < i; m++) {
/* 157 */         d += paramDoubleMatrix2D.getQuick(m, j) * paramDoubleMatrix1D1.getQuick(m);
/*     */       }
/* 159 */       localDoubleMatrix1D.setQuick(j, paramDouble1 * d + paramDouble2 * paramDoubleMatrix1D2.getQuick(j));
/*     */     }
/* 161 */     paramDoubleMatrix1D2.assign(localDoubleMatrix1D);
/*     */   }
/*     */   
/* 164 */   public void dtrmv(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, DoubleMatrix2D paramDoubleMatrix2D, DoubleMatrix1D paramDoubleMatrix1D) { if (paramBoolean2) {
/* 165 */       paramDoubleMatrix2D = paramDoubleMatrix2D.viewDice();
/* 166 */       paramBoolean1 = !paramBoolean1;
/*     */     }
/*     */     
/* 169 */     Property.DEFAULT.checkSquare(paramDoubleMatrix2D);
/* 170 */     int i = paramDoubleMatrix2D.rows();
/* 171 */     if (i != paramDoubleMatrix1D.size()) {
/* 172 */       throw new IllegalArgumentException(paramDoubleMatrix2D.toStringShort() + ", " + paramDoubleMatrix1D.toStringShort());
/*     */     }
/*     */     
/* 175 */     DoubleMatrix1D localDoubleMatrix1D1 = paramDoubleMatrix1D.like();
/* 176 */     DoubleMatrix1D localDoubleMatrix1D2 = paramDoubleMatrix1D.like();
/* 177 */     if (paramBoolean3) {
/* 178 */       localDoubleMatrix1D2.assign(1.0D);
/*     */     }
/*     */     else {
/* 181 */       for (j = 0; j < i; j++) {
/* 182 */         localDoubleMatrix1D2.setQuick(j, paramDoubleMatrix2D.getQuick(j, j));
/*     */       }
/*     */     }
/*     */     
/* 186 */     for (int j = 0; j < i; j++) {
/* 187 */       double d = 0.0D;
/* 188 */       int k; if (!paramBoolean1) {
/* 189 */         for (k = 0; k < j; k++) {
/* 190 */           d += paramDoubleMatrix2D.getQuick(j, k) * paramDoubleMatrix1D.getQuick(k);
/*     */         }
/* 192 */         d += localDoubleMatrix1D2.getQuick(j) * paramDoubleMatrix1D.getQuick(j);
/*     */       }
/*     */       else {
/* 195 */         d += localDoubleMatrix1D2.getQuick(j) * paramDoubleMatrix1D.getQuick(j);
/* 196 */         for (k = j + 1; k < i; k++)
/* 197 */           d += paramDoubleMatrix2D.getQuick(j, k) * paramDoubleMatrix1D.getQuick(k);
/*     */       }
/* 199 */       localDoubleMatrix1D1.setQuick(j, d);
/*     */     }
/* 201 */     paramDoubleMatrix1D.assign(localDoubleMatrix1D1);
/*     */   }
/*     */   
/* 204 */   public int idamax(DoubleMatrix1D paramDoubleMatrix1D) { int i = -1;
/* 205 */     double d1 = Double.MIN_VALUE;
/* 206 */     int j = paramDoubleMatrix1D.size();
/* 207 */     do { double d2 = Math.abs(paramDoubleMatrix1D.getQuick(j));
/* 208 */       if (d2 > d1) {
/* 209 */         d1 = d2;
/* 210 */         i = j;
/*     */       }
/* 206 */       j--; } while (j >= 0);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 213 */     return i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double sign(double paramDouble1, double paramDouble2)
/*     */   {
/* 222 */     if (paramDouble2 < 0.0D) {
/* 223 */       return -Math.abs(paramDouble1);
/*     */     }
/* 225 */     return Math.abs(paramDouble1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/cern/colt/matrix/linalg/SeqBlas.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       0.7.1
 */