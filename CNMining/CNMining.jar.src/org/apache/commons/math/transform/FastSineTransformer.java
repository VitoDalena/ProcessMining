/*     */ package org.apache.commons.math.transform;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.analysis.UnivariateRealFunction;
/*     */ import org.apache.commons.math.complex.Complex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FastSineTransformer
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -478002039949390854L;
/*     */   
/*     */   public double[] transform(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/*  66 */     return fst(f);
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
/*     */   public double[] transform(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/*  87 */     double[] data = FastFourierTransformer.sample(f, min, max, n);
/*  88 */     data[0] = 0.0D;
/*  89 */     return fst(data);
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
/*     */   public double[] transform2(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 106 */     double scaling_coefficient = Math.sqrt(2.0D / f.length);
/* 107 */     return FastFourierTransformer.scaleArray(fst(f), scaling_coefficient);
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
/*     */   public double[] transform2(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 128 */     double[] data = FastFourierTransformer.sample(f, min, max, n);
/* 129 */     data[0] = 0.0D;
/* 130 */     double scaling_coefficient = Math.sqrt(2.0D / n);
/* 131 */     return FastFourierTransformer.scaleArray(fst(data), scaling_coefficient);
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
/*     */   public double[] inversetransform(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 148 */     double scaling_coefficient = 2.0D / f.length;
/* 149 */     return FastFourierTransformer.scaleArray(fst(f), scaling_coefficient);
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
/*     */   public double[] inversetransform(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 170 */     double[] data = FastFourierTransformer.sample(f, min, max, n);
/* 171 */     data[0] = 0.0D;
/* 172 */     double scaling_coefficient = 2.0D / n;
/* 173 */     return FastFourierTransformer.scaleArray(fst(data), scaling_coefficient);
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
/*     */   public double[] inversetransform2(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 190 */     return transform2(f);
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
/*     */   public double[] inversetransform2(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 211 */     return transform2(f, min, max, n);
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
/*     */   protected double[] fst(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 225 */     double[] F = new double[f.length];
/*     */     
/* 227 */     FastFourierTransformer.verifyDataSet(f);
/* 228 */     if (f[0] != 0.0D) {
/* 229 */       throw new IllegalArgumentException("The first element is not zero: " + f[0]);
/*     */     }
/*     */     
/* 232 */     int N = f.length;
/* 233 */     if (N == 1) {
/* 234 */       F[0] = 0.0D;
/* 235 */       return F;
/*     */     }
/*     */     
/*     */ 
/* 239 */     double[] x = new double[N];
/* 240 */     x[0] = 0.0D;
/* 241 */     x[(N >> 1)] = (2.0D * f[(N >> 1)]);
/* 242 */     for (int i = 1; i < N >> 1; i++) {
/* 243 */       double A = Math.sin(i * 3.141592653589793D / N) * (f[i] + f[(N - i)]);
/* 244 */       double B = 0.5D * (f[i] - f[(N - i)]);
/* 245 */       x[i] = (A + B);
/* 246 */       x[(N - i)] = (A - B);
/*     */     }
/* 248 */     FastFourierTransformer transformer = new FastFourierTransformer();
/* 249 */     Complex[] y = transformer.transform(x);
/*     */     
/*     */ 
/* 252 */     F[0] = 0.0D;
/* 253 */     F[1] = (0.5D * y[0].getReal());
/* 254 */     for (int i = 1; i < N >> 1; i++) {
/* 255 */       F[(2 * i)] = (-y[i].getImaginary());
/* 256 */       F[(2 * i + 1)] = (y[i].getReal() + F[(2 * i - 1)]);
/*     */     }
/*     */     
/* 259 */     return F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/transform/FastSineTransformer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */