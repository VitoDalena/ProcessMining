/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math.ArgumentOutsideDomainException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PolynomialSplineFunction
/*     */   implements DifferentiableUnivariateRealFunction, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1619940313389547244L;
/*     */   private double[] knots;
/*  74 */   private PolynomialFunction[] polynomials = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  80 */   private int n = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolynomialSplineFunction(double[] knots, PolynomialFunction[] polynomials)
/*     */   {
/*  99 */     if (knots.length < 2) {
/* 100 */       throw new IllegalArgumentException("Not enough knot values -- spline partition must have at least 2 points.");
/*     */     }
/*     */     
/* 103 */     if (knots.length - 1 != polynomials.length) {
/* 104 */       throw new IllegalArgumentException("Number of polynomial interpolants must match the number of segments.");
/*     */     }
/*     */     
/* 107 */     if (!isStrictlyIncreasing(knots)) {
/* 108 */       throw new IllegalArgumentException("Knot values must be strictly increasing.");
/*     */     }
/*     */     
/*     */ 
/* 112 */     this.n = (knots.length - 1);
/* 113 */     this.knots = new double[this.n + 1];
/* 114 */     System.arraycopy(knots, 0, this.knots, 0, this.n + 1);
/* 115 */     this.polynomials = new PolynomialFunction[this.n];
/* 116 */     System.arraycopy(polynomials, 0, this.polynomials, 0, this.n);
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
/*     */   public double value(double v)
/*     */     throws ArgumentOutsideDomainException
/*     */   {
/* 135 */     if ((v < this.knots[0]) || (v > this.knots[this.n])) {
/* 136 */       throw new ArgumentOutsideDomainException(v, this.knots[0], this.knots[this.n]);
/*     */     }
/* 138 */     int i = Arrays.binarySearch(this.knots, v);
/* 139 */     if (i < 0) {
/* 140 */       i = -i - 2;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 145 */     if (i >= this.polynomials.length) {
/* 146 */       i--;
/*     */     }
/* 148 */     return this.polynomials[i].value(v - this.knots[i]);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public UnivariateRealFunction derivative()
/*     */   {
/* 156 */     return polynomialSplineDerivative();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolynomialSplineFunction polynomialSplineDerivative()
/*     */   {
/* 165 */     PolynomialFunction[] derivativePolynomials = new PolynomialFunction[this.n];
/* 166 */     for (int i = 0; i < this.n; i++) {
/* 167 */       derivativePolynomials[i] = this.polynomials[i].polynomialDerivative();
/*     */     }
/* 169 */     return new PolynomialSplineFunction(this.knots, derivativePolynomials);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getN()
/*     */   {
/* 179 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolynomialFunction[] getPolynomials()
/*     */   {
/* 191 */     PolynomialFunction[] p = new PolynomialFunction[this.n];
/* 192 */     System.arraycopy(this.polynomials, 0, p, 0, this.n);
/* 193 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getKnots()
/*     */   {
/* 205 */     double[] out = new double[this.n + 1];
/* 206 */     System.arraycopy(this.knots, 0, out, 0, this.n + 1);
/* 207 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static boolean isStrictlyIncreasing(double[] x)
/*     */   {
/* 219 */     for (int i = 1; i < x.length; i++) {
/* 220 */       if (x[(i - 1)] >= x[i]) {
/* 221 */         return false;
/*     */       }
/*     */     }
/* 224 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/PolynomialSplineFunction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */