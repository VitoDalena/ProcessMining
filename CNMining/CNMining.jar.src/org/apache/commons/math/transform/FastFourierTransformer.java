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
/*     */ public class FastFourierTransformer
/*     */   implements Serializable
/*     */ {
/*     */   static final long serialVersionUID = 5138259215438106000L;
/*  49 */   private Complex[] omega = new Complex[0];
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  55 */   private int omegaCount = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Complex[] transform(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/*  78 */     return fft(f, false);
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
/*     */   public Complex[] transform(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/*  99 */     double[] data = sample(f, min, max, n);
/* 100 */     return fft(data, false);
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
/*     */   public Complex[] transform(Complex[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 117 */     computeOmega(f.length);
/* 118 */     return fft(f);
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
/*     */   public Complex[] transform2(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 135 */     double scaling_coefficient = 1.0D / Math.sqrt(f.length);
/* 136 */     return scaleArray(fft(f, false), scaling_coefficient);
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
/*     */   public Complex[] transform2(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 157 */     double[] data = sample(f, min, max, n);
/* 158 */     double scaling_coefficient = 1.0D / Math.sqrt(n);
/* 159 */     return scaleArray(fft(data, false), scaling_coefficient);
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
/*     */   public Complex[] transform2(Complex[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 176 */     computeOmega(f.length);
/* 177 */     double scaling_coefficient = 1.0D / Math.sqrt(f.length);
/* 178 */     return scaleArray(fft(f), scaling_coefficient);
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
/*     */   public Complex[] inversetransform(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 195 */     double scaling_coefficient = 1.0D / f.length;
/* 196 */     return scaleArray(fft(f, true), scaling_coefficient);
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
/*     */   public Complex[] inversetransform(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 217 */     double[] data = sample(f, min, max, n);
/* 218 */     double scaling_coefficient = 1.0D / n;
/* 219 */     return scaleArray(fft(data, true), scaling_coefficient);
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
/*     */   public Complex[] inversetransform(Complex[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 236 */     computeOmega(-f.length);
/* 237 */     double scaling_coefficient = 1.0D / f.length;
/* 238 */     return scaleArray(fft(f), scaling_coefficient);
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
/*     */   public Complex[] inversetransform2(double[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 255 */     double scaling_coefficient = 1.0D / Math.sqrt(f.length);
/* 256 */     return scaleArray(fft(f, true), scaling_coefficient);
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
/*     */   public Complex[] inversetransform2(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 277 */     double[] data = sample(f, min, max, n);
/* 278 */     double scaling_coefficient = 1.0D / Math.sqrt(n);
/* 279 */     return scaleArray(fft(data, true), scaling_coefficient);
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
/*     */   public Complex[] inversetransform2(Complex[] f)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 296 */     computeOmega(-f.length);
/* 297 */     double scaling_coefficient = 1.0D / Math.sqrt(f.length);
/* 298 */     return scaleArray(fft(f), scaling_coefficient);
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
/*     */   protected Complex[] fft(double[] f, boolean isInverse)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 313 */     verifyDataSet(f);
/* 314 */     Complex[] F = new Complex[f.length];
/* 315 */     if (f.length == 1) {
/* 316 */       F[0] = new Complex(f[0], 0.0D);
/* 317 */       return F;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 322 */     int N = f.length >> 1;
/* 323 */     Complex[] c = new Complex[N];
/* 324 */     for (int i = 0; i < N; i++) {
/* 325 */       c[i] = new Complex(f[(2 * i)], f[(2 * i + 1)]);
/*     */     }
/* 327 */     computeOmega(isInverse ? -N : N);
/* 328 */     Complex[] z = fft(c);
/*     */     
/*     */ 
/* 331 */     computeOmega(isInverse ? -2 * N : 2 * N);
/* 332 */     F[0] = new Complex(2.0D * (z[0].getReal() + z[0].getImaginary()), 0.0D);
/* 333 */     F[N] = new Complex(2.0D * (z[0].getReal() - z[0].getImaginary()), 0.0D);
/* 334 */     for (int i = 1; i < N; i++) {
/* 335 */       Complex A = z[(N - i)].conjugate();
/* 336 */       Complex B = z[i].add(A);
/* 337 */       Complex C = z[i].subtract(A);
/* 338 */       Complex D = this.omega[i].multiply(Complex.I);
/* 339 */       F[i] = B.subtract(C.multiply(D));
/* 340 */       F[(2 * N - i)] = F[i].conjugate();
/*     */     }
/*     */     
/* 343 */     return scaleArray(F, 0.5D);
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
/*     */   protected Complex[] fft(Complex[] data)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 357 */     int N = data.length;
/* 358 */     Complex[] f = new Complex[N];
/*     */     
/*     */ 
/* 361 */     verifyDataSet(data);
/* 362 */     if (N == 1) {
/* 363 */       f[0] = data[0];
/* 364 */       return f;
/*     */     }
/* 366 */     if (N == 2) {
/* 367 */       f[0] = data[0].add(data[1]);
/* 368 */       f[1] = data[0].subtract(data[1]);
/* 369 */       return f;
/*     */     }
/*     */     
/*     */ 
/* 373 */     int j = 0;
/* 374 */     for (int i = 0; i < N; i++) {
/* 375 */       f[i] = data[j];
/* 376 */       int k = N >> 1;
/* 377 */       for (; (j >= k) && (k > 0); 
/* 378 */           k >>= 1) { j -= k;
/*     */       }
/* 380 */       j += k;
/*     */     }
/*     */     
/*     */ 
/* 384 */     for (i = 0; i < N; i += 4) {
/* 385 */       Complex A = f[i].add(f[(i + 1)]);
/* 386 */       Complex B = f[(i + 2)].add(f[(i + 3)]);
/* 387 */       Complex C = f[i].subtract(f[(i + 1)]);
/* 388 */       Complex D = f[(i + 2)].subtract(f[(i + 3)]);
/* 389 */       Complex E = C.add(D.multiply(Complex.I));
/* 390 */       Complex F = C.subtract(D.multiply(Complex.I));
/* 391 */       f[i] = A.add(B);
/* 392 */       f[(i + 2)] = A.subtract(B);
/*     */       
/* 394 */       f[(i + 1)] = (this.omegaCount < 0 ? E : F);
/* 395 */       f[(i + 3)] = (this.omegaCount > 0 ? E : F);
/*     */     }
/*     */     
/*     */ 
/* 399 */     for (i = 4; i < N; i <<= 1) {
/* 400 */       int m = N / (i << 1);
/* 401 */       for (j = 0; j < N; j += (i << 1)) {
/* 402 */         for (int k = 0; k < i; k++) {
/* 403 */           Complex z = f[(i + j + k)].multiply(this.omega[(k * m)]);
/* 404 */           f[(i + j + k)] = f[(j + k)].subtract(z);
/* 405 */           f[(j + k)] = f[(j + k)].add(z);
/*     */         }
/*     */       }
/*     */     }
/* 409 */     return f;
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
/*     */   protected void computeOmega(int n)
/*     */     throws IllegalArgumentException
/*     */   {
/* 423 */     if (n == 0) {
/* 424 */       throw new IllegalArgumentException("Cannot compute 0-th root of unity, indefinite result.");
/*     */     }
/*     */     
/*     */ 
/* 428 */     if (n == this.omegaCount) return;
/* 429 */     if (n + this.omegaCount == 0) {
/* 430 */       for (int i = 0; i < Math.abs(this.omegaCount); i++) {
/* 431 */         this.omega[i] = this.omega[i].conjugate();
/*     */       }
/* 433 */       this.omegaCount = n;
/* 434 */       return;
/*     */     }
/*     */     
/* 437 */     this.omega = new Complex[Math.abs(n)];
/* 438 */     double t = 6.283185307179586D / n;
/* 439 */     double cost = Math.cos(t);
/* 440 */     double sint = Math.sin(t);
/* 441 */     this.omega[0] = new Complex(1.0D, 0.0D);
/* 442 */     for (int i = 1; i < Math.abs(n); i++) {
/* 443 */       this.omega[i] = new Complex(this.omega[(i - 1)].getReal() * cost + this.omega[(i - 1)].getImaginary() * sint, this.omega[(i - 1)].getImaginary() * cost - this.omega[(i - 1)].getReal() * sint);
/*     */     }
/*     */     
/*     */ 
/* 447 */     this.omegaCount = n;
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
/*     */   public static double[] sample(UnivariateRealFunction f, double min, double max, int n)
/*     */     throws MathException, IllegalArgumentException
/*     */   {
/* 470 */     if (n <= 0) {
/* 471 */       throw new IllegalArgumentException("Number of samples not positive.");
/*     */     }
/* 473 */     verifyInterval(min, max);
/*     */     
/* 475 */     double[] s = new double[n];
/* 476 */     double h = (max - min) / n;
/* 477 */     for (int i = 0; i < n; i++) {
/* 478 */       s[i] = f.value(min + i * h);
/*     */     }
/* 480 */     return s;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double[] scaleArray(double[] f, double d)
/*     */   {
/* 492 */     for (int i = 0; i < f.length; i++) {
/* 493 */       f[i] *= d;
/*     */     }
/* 495 */     return f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static Complex[] scaleArray(Complex[] f, double d)
/*     */   {
/* 507 */     for (int i = 0; i < f.length; i++) {
/* 508 */       f[i] = new Complex(d * f[i].getReal(), d * f[i].getImaginary());
/*     */     }
/* 510 */     return f;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean isPowerOf2(long n)
/*     */   {
/* 520 */     return (n > 0L) && ((n & n - 1L) == 0L);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void verifyDataSet(double[] d)
/*     */     throws IllegalArgumentException
/*     */   {
/* 530 */     if (!isPowerOf2(d.length)) {
/* 531 */       throw new IllegalArgumentException("Number of samples not power of 2, consider padding for fix.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void verifyDataSet(Object[] o)
/*     */     throws IllegalArgumentException
/*     */   {
/* 543 */     if (!isPowerOf2(o.length)) {
/* 544 */       throw new IllegalArgumentException("Number of samples not power of 2, consider padding for fix.");
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
/*     */   public static void verifyInterval(double lower, double upper)
/*     */     throws IllegalArgumentException
/*     */   {
/* 559 */     if (lower >= upper) {
/* 560 */       throw new IllegalArgumentException("Endpoints do not specify an interval: [" + lower + ", " + upper + "]");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/transform/FastFourierTransformer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */