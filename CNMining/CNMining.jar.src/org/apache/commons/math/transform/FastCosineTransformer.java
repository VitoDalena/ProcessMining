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
/*     */ 
/*     */ public class FastCosineTransformer
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = -7673941545134707766L;
/*     */   
/*     */   public double[] transform(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/*  67 */     return fct(f);
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
/*     */   public double[] transform(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/*  89 */     double[] data = FastFourierTransformer.sample(f, min, max, n);
/*  90 */     return fct(data);
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
/*     */   public double[] transform2(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 108 */     double scaling_coefficient = Math.sqrt(2.0D / (f.length - 1));
/* 109 */     return FastFourierTransformer.scaleArray(fct(f), scaling_coefficient);
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
/*     */   public double[] transform2(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 132 */     double[] data = FastFourierTransformer.sample(f, min, max, n);
/* 133 */     double scaling_coefficient = Math.sqrt(2.0D / (n - 1));
/* 134 */     return FastFourierTransformer.scaleArray(fct(data), scaling_coefficient);
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
/*     */   public double[] inversetransform(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 152 */     double scaling_coefficient = 2.0D / (f.length - 1);
/* 153 */     return FastFourierTransformer.scaleArray(fct(f), scaling_coefficient);
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
/*     */   public double[] inversetransform(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 175 */     double[] data = FastFourierTransformer.sample(f, min, max, n);
/* 176 */     double scaling_coefficient = 2.0D / (n - 1);
/* 177 */     return FastFourierTransformer.scaleArray(fct(data), scaling_coefficient);
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
/*     */   public double[] inversetransform2(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 195 */     return transform2(f);
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
/*     */   public double[] inversetransform2(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 217 */     return transform2(f, min, max, n);
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
/*     */   protected double[] fct(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 231 */     double[] F = new double[f.length];
/*     */     
/* 233 */     int N = f.length - 1;
/* 234 */     if (!FastFourierTransformer.isPowerOf2(N)) {
/* 235 */       throw new IllegalArgumentException("Number of samples not power of 2 plus one: " + f.length);
/*     */     }
/*     */     
/* 238 */     if (N == 1) {
/* 239 */       F[0] = (0.5D * (f[0] + f[1]));
/* 240 */       F[1] = (0.5D * (f[0] - f[1]));
/* 241 */       return F;
/*     */     }
/*     */     
/*     */ 
/* 245 */     double[] x = new double[N];
/* 246 */     x[0] = (0.5D * (f[0] + f[N]));
/* 247 */     x[(N >> 1)] = f[(N >> 1)];
/* 248 */     double F1 = 0.5D * (f[0] - f[N]);
/* 249 */     for (int i = 1; i < N >> 1; i++) {
/* 250 */       double A = 0.5D * (f[i] + f[(N - i)]);
/* 251 */       double B = Math.sin(i * 3.141592653589793D / N) * (f[i] - f[(N - i)]);
/* 252 */       double C = Math.cos(i * 3.141592653589793D / N) * (f[i] - f[(N - i)]);
/* 253 */       x[i] = (A - B);
/* 254 */       x[(N - i)] = (A + B);
/* 255 */       F1 += C;
/*     */     }
/* 257 */     FastFourierTransformer transformer = new FastFourierTransformer();
/* 258 */     Complex[] y = transformer.transform(x);
/*     */     
/*     */ 
/* 261 */     F[0] = y[0].getReal();
/* 262 */     F[1] = F1;
/* 263 */     for (int i = 1; i < N >> 1; i++) {
/* 264 */       F[(2 * i)] = y[i].getReal();
/* 265 */       F[(2 * i + 1)] = (F[(2 * i - 1)] - y[i].getImaginary());
/*     */     }
/* 267 */     F[N] = y[(N >> 1)].getReal();
/*     */     
/* 269 */     return F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/transform/FastCosineTransformer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */