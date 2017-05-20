/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SplineInterpolator
/*     */   implements UnivariateRealInterpolator
/*     */ {
/*     */   public UnivariateRealFunction interpolate(double[] x, double[] y)
/*     */   {
/*  55 */     if (x.length != y.length) {
/*  56 */       throw new IllegalArgumentException("Dataset arrays must have same length.");
/*     */     }
/*     */     
/*  59 */     if (x.length < 3) {
/*  60 */       throw new IllegalArgumentException("At least 3 datapoints are required to compute a spline interpolant");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  65 */     int n = x.length - 1;
/*     */     
/*  67 */     for (int i = 0; i < n; i++) {
/*  68 */       if (x[i] >= x[(i + 1)]) {
/*  69 */         throw new IllegalArgumentException("Dataset x values must be strictly increasing.");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*  74 */     double[] h = new double[n];
/*  75 */     for (int i = 0; i < n; i++) {
/*  76 */       h[i] = (x[(i + 1)] - x[i]);
/*     */     }
/*     */     
/*  79 */     double[] mu = new double[n];
/*  80 */     double[] z = new double[n + 1];
/*  81 */     mu[0] = 0.0D;
/*  82 */     z[0] = 0.0D;
/*  83 */     double g = 0.0D;
/*  84 */     for (int i = 1; i < n; i++) {
/*  85 */       g = 2.0D * (x[(i + 1)] - x[(i - 1)]) - h[(i - 1)] * mu[(i - 1)];
/*  86 */       h[i] /= g;
/*  87 */       z[i] = ((3.0D * (y[(i + 1)] * h[(i - 1)] - y[i] * (x[(i + 1)] - x[(i - 1)]) + y[(i - 1)] * h[i]) / (h[(i - 1)] * h[i]) - h[(i - 1)] * z[(i - 1)]) / g);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  92 */     double[] b = new double[n];
/*  93 */     double[] c = new double[n + 1];
/*  94 */     double[] d = new double[n];
/*     */     
/*  96 */     z[n] = 0.0D;
/*  97 */     c[n] = 0.0D;
/*     */     
/*  99 */     for (int j = n - 1; j >= 0; j--) {
/* 100 */       z[j] -= mu[j] * c[(j + 1)];
/* 101 */       b[j] = ((y[(j + 1)] - y[j]) / h[j] - h[j] * (c[(j + 1)] + 2.0D * c[j]) / 3.0D);
/* 102 */       d[j] = ((c[(j + 1)] - c[j]) / (3.0D * h[j]));
/*     */     }
/*     */     
/* 105 */     PolynomialFunction[] polynomials = new PolynomialFunction[n];
/* 106 */     double[] coefficients = new double[4];
/* 107 */     for (int i = 0; i < n; i++) {
/* 108 */       coefficients[0] = y[i];
/* 109 */       coefficients[1] = b[i];
/* 110 */       coefficients[2] = c[i];
/* 111 */       coefficients[3] = d[i];
/* 112 */       polynomials[i] = new PolynomialFunction(coefficients);
/*     */     }
/*     */     
/* 115 */     return new PolynomialSplineFunction(x, polynomials);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/SplineInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */