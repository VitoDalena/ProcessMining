/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.DuplicateSampleAbscissaException;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PolynomialFunctionLagrangeForm
/*     */   implements UnivariateRealFunction, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -3965199246151093920L;
/*     */   private double[] coefficients;
/*     */   private double[] x;
/*     */   private double[] y;
/*     */   private boolean coefficientsComputed;
/*     */   
/*     */   PolynomialFunctionLagrangeForm(double[] x, double[] y)
/*     */     throws IllegalArgumentException
/*     */   {
/*  72 */     verifyInterpolationArray(x, y);
/*  73 */     this.x = new double[x.length];
/*  74 */     this.y = new double[y.length];
/*  75 */     System.arraycopy(x, 0, this.x, 0, x.length);
/*  76 */     System.arraycopy(y, 0, this.y, 0, y.length);
/*  77 */     this.coefficientsComputed = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double value(double z)
/*     */     throws FunctionEvaluationException
/*     */   {
/*     */     try
/*     */     {
/*  90 */       return evaluate(this.x, this.y, z);
/*     */     } catch (DuplicateSampleAbscissaException e) {
/*  92 */       throw new FunctionEvaluationException(z, e.getPattern(), e.getArguments(), e);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int degree()
/*     */   {
/* 102 */     return this.x.length - 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getInterpolatingPoints()
/*     */   {
/* 113 */     double[] out = new double[this.x.length];
/* 114 */     System.arraycopy(this.x, 0, out, 0, this.x.length);
/* 115 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getInterpolatingValues()
/*     */   {
/* 126 */     double[] out = new double[this.y.length];
/* 127 */     System.arraycopy(this.y, 0, out, 0, this.y.length);
/* 128 */     return out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getCoefficients()
/*     */   {
/* 139 */     if (!this.coefficientsComputed) {
/* 140 */       computeCoefficients();
/*     */     }
/* 142 */     double[] out = new double[this.coefficients.length];
/* 143 */     System.arraycopy(this.coefficients, 0, out, 0, this.coefficients.length);
/* 144 */     return out;
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
/*     */   public static double evaluate(double[] x, double[] y, double z)
/*     */     throws DuplicateSampleAbscissaException, IllegalArgumentException
/*     */   {
/* 165 */     int nearest = 0;
/*     */     
/*     */ 
/* 168 */     verifyInterpolationArray(x, y);
/*     */     
/* 170 */     int n = x.length;
/* 171 */     double[] c = new double[n];
/* 172 */     double[] d = new double[n];
/* 173 */     double min_dist = Double.POSITIVE_INFINITY;
/* 174 */     for (int i = 0; i < n; i++)
/*     */     {
/* 176 */       c[i] = y[i];
/* 177 */       d[i] = y[i];
/*     */       
/* 179 */       double dist = Math.abs(z - x[i]);
/* 180 */       if (dist < min_dist) {
/* 181 */         nearest = i;
/* 182 */         min_dist = dist;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 187 */     double value = y[nearest];
/*     */     
/* 189 */     for (i = 1; i < n; i++) {
/* 190 */       for (int j = 0; j < n - i; j++) {
/* 191 */         double tc = x[j] - z;
/* 192 */         double td = x[(i + j)] - z;
/* 193 */         double divider = x[j] - x[(i + j)];
/* 194 */         if (divider == 0.0D)
/*     */         {
/* 196 */           throw new DuplicateSampleAbscissaException(x[i], i, i + j);
/*     */         }
/*     */         
/* 199 */         double w = (c[(j + 1)] - d[j]) / divider;
/* 200 */         c[j] = (tc * w);
/* 201 */         d[j] = (td * w);
/*     */       }
/*     */       
/* 204 */       if (nearest < 0.5D * (n - i + 1)) {
/* 205 */         value += c[nearest];
/*     */       } else {
/* 207 */         nearest--;
/* 208 */         value += d[nearest];
/*     */       }
/*     */     }
/*     */     
/* 212 */     return value;
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
/*     */   protected void computeCoefficients()
/*     */     throws ArithmeticException
/*     */   {
/* 228 */     int n = degree() + 1;
/* 229 */     this.coefficients = new double[n];
/* 230 */     for (int i = 0; i < n; i++) {
/* 231 */       this.coefficients[i] = 0.0D;
/*     */     }
/*     */     
/*     */ 
/* 235 */     double[] c = new double[n + 1];
/* 236 */     c[0] = 1.0D;
/* 237 */     for (i = 0; i < n; i++) {
/* 238 */       for (int j = i; j > 0; j--) {
/* 239 */         c[j] = (c[(j - 1)] - c[j] * this.x[i]);
/*     */       }
/* 241 */       c[0] *= -this.x[i];
/* 242 */       c[(i + 1)] = 1.0D;
/*     */     }
/*     */     
/* 245 */     double[] tc = new double[n];
/* 246 */     for (i = 0; i < n; i++)
/*     */     {
/* 248 */       double d = 1.0D;
/* 249 */       for (int j = 0; j < n; j++) {
/* 250 */         if (i != j) {
/* 251 */           d *= (this.x[i] - this.x[j]);
/*     */         }
/*     */       }
/* 254 */       if (d == 0.0D)
/*     */       {
/* 256 */         throw new ArithmeticException("Identical abscissas cause division by zero.");
/*     */       }
/*     */       
/* 259 */       double t = this.y[i] / d;
/*     */       
/*     */ 
/*     */ 
/* 263 */       tc[(n - 1)] = c[n];
/* 264 */       this.coefficients[(n - 1)] += t * tc[(n - 1)];
/* 265 */       for (j = n - 2; j >= 0; j--) {
/* 266 */         tc[j] = (c[(j + 1)] + tc[(j + 1)] * this.x[i]);
/* 267 */         this.coefficients[j] += t * tc[j];
/*     */       }
/*     */     }
/*     */     
/* 271 */     this.coefficientsComputed = true;
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
/*     */   protected static void verifyInterpolationArray(double[] x, double[] y)
/*     */     throws IllegalArgumentException
/*     */   {
/* 289 */     if ((x.length < 2) || (y.length < 2)) {
/* 290 */       throw new IllegalArgumentException("Interpolation requires at least two points.");
/*     */     }
/*     */     
/* 293 */     if (x.length != y.length) {
/* 294 */       throw new IllegalArgumentException("Abscissa and value arrays must have the same length.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/PolynomialFunctionLagrangeForm.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */