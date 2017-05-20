/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
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
/*     */ public class LaguerreSolver
/*     */   extends UnivariateRealSolverImpl
/*     */ {
/*     */   private static final long serialVersionUID = -3775334783473775723L;
/*     */   private PolynomialFunction p;
/*     */   
/*     */   public LaguerreSolver(UnivariateRealFunction f)
/*     */     throws IllegalArgumentException
/*     */   {
/*  53 */     super(f, 100, 1.0E-6D);
/*  54 */     if ((f instanceof PolynomialFunction)) {
/*  55 */       this.p = ((PolynomialFunction)f);
/*     */     } else {
/*  57 */       throw new IllegalArgumentException("Function is not polynomial.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PolynomialFunction getPolynomialFunction()
/*     */   {
/*  67 */     return new PolynomialFunction(this.p.getCoefficients());
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
/*     */   public double solve(double min, double max, double initial)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/*  89 */     if (this.p.value(min) == 0.0D) return min;
/*  90 */     if (this.p.value(max) == 0.0D) return max;
/*  91 */     if (this.p.value(initial) == 0.0D) { return initial;
/*     */     }
/*  93 */     verifyBracketing(min, max, this.p);
/*  94 */     verifySequence(min, initial, max);
/*  95 */     if (isBracketing(min, initial, this.p)) {
/*  96 */       return solve(min, initial);
/*     */     }
/*  98 */     return solve(initial, max);
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
/*     */   public double solve(double min, double max)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/* 124 */     if (this.p.value(min) == 0.0D) return min;
/* 125 */     if (this.p.value(max) == 0.0D) return max;
/* 126 */     verifyBracketing(min, max, this.p);
/*     */     
/* 128 */     double[] coefficients = this.p.getCoefficients();
/* 129 */     Complex[] c = new Complex[coefficients.length];
/* 130 */     for (int i = 0; i < coefficients.length; i++) {
/* 131 */       c[i] = new Complex(coefficients[i], 0.0D);
/*     */     }
/* 133 */     Complex initial = new Complex(0.5D * (min + max), 0.0D);
/* 134 */     Complex z = solve(c, initial);
/* 135 */     if (isRootOK(min, max, z)) {
/* 136 */       setResult(z.getReal(), this.iterationCount);
/* 137 */       return this.result;
/*     */     }
/*     */     
/*     */ 
/* 141 */     Complex[] root = solveAll(c, initial);
/* 142 */     for (int i = 0; i < root.length; i++) {
/* 143 */       if (isRootOK(min, max, root[i])) {
/* 144 */         setResult(root[i].getReal(), this.iterationCount);
/* 145 */         return this.result;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 150 */     throw new ConvergenceException();
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
/*     */   protected boolean isRootOK(double min, double max, Complex z)
/*     */   {
/* 163 */     double tolerance = Math.max(this.relativeAccuracy * z.abs(), this.absoluteAccuracy);
/* 164 */     return (isSequence(min, z.getReal(), max)) && ((Math.abs(z.getImaginary()) <= tolerance) || (z.abs() <= this.functionValueAccuracy));
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
/*     */   public Complex[] solveAll(double[] coefficients, double initial)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/* 185 */     Complex[] c = new Complex[coefficients.length];
/* 186 */     Complex z = new Complex(initial, 0.0D);
/* 187 */     for (int i = 0; i < c.length; i++) {
/* 188 */       c[i] = new Complex(coefficients[i], 0.0D);
/*     */     }
/* 190 */     return solveAll(c, z);
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
/*     */   public Complex[] solveAll(Complex[] coefficients, Complex initial)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 209 */     int n = coefficients.length - 1;
/* 210 */     int iterationCount = 0;
/* 211 */     if (n < 1) {
/* 212 */       throw new IllegalArgumentException("Polynomial degree must be positive: degree=" + n);
/*     */     }
/*     */     
/* 215 */     Complex[] c = new Complex[n + 1];
/* 216 */     for (int i = 0; i <= n; i++) {
/* 217 */       c[i] = coefficients[i];
/*     */     }
/*     */     
/*     */ 
/* 221 */     Complex[] root = new Complex[n];
/* 222 */     for (int i = 0; i < n; i++) {
/* 223 */       Complex[] subarray = new Complex[n - i + 1];
/* 224 */       System.arraycopy(c, 0, subarray, 0, subarray.length);
/* 225 */       root[i] = solve(subarray, initial);
/*     */       
/* 227 */       Complex newc = c[(n - i)];
/* 228 */       Complex oldc = null;
/* 229 */       for (int j = n - i - 1; j >= 0; j--) {
/* 230 */         oldc = c[j];
/* 231 */         c[j] = newc;
/* 232 */         newc = oldc.add(newc.multiply(root[i]));
/*     */       }
/* 234 */       iterationCount += this.iterationCount;
/*     */     }
/*     */     
/* 237 */     this.resultComputed = true;
/* 238 */     this.iterationCount = iterationCount;
/* 239 */     return root;
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
/*     */   public Complex solve(Complex[] coefficients, Complex initial)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 258 */     int n = coefficients.length - 1;
/* 259 */     if (n < 1) {
/* 260 */       throw new IllegalArgumentException("Polynomial degree must be positive: degree=" + n);
/*     */     }
/*     */     
/* 263 */     Complex N = new Complex(n, 0.0D);
/* 264 */     Complex N1 = new Complex(n - 1, 0.0D);
/*     */     
/* 266 */     int i = 1;
/* 267 */     Complex pv = null;
/* 268 */     Complex dv = null;
/* 269 */     Complex d2v = null;
/* 270 */     Complex G = null;
/* 271 */     Complex G2 = null;
/* 272 */     Complex H = null;
/* 273 */     Complex delta = null;
/* 274 */     Complex denominator = null;
/* 275 */     Complex z = initial;
/* 276 */     Complex oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/* 277 */     while (i <= this.maximalIterationCount)
/*     */     {
/*     */ 
/* 280 */       pv = coefficients[n];
/* 281 */       dv = Complex.ZERO;
/* 282 */       d2v = Complex.ZERO;
/* 283 */       for (int j = n - 1; j >= 0; j--) {
/* 284 */         d2v = dv.add(z.multiply(d2v));
/* 285 */         dv = pv.add(z.multiply(dv));
/* 286 */         pv = coefficients[j].add(z.multiply(pv));
/*     */       }
/* 288 */       d2v = d2v.multiply(new Complex(2.0D, 0.0D));
/*     */       
/*     */ 
/* 291 */       double tolerance = Math.max(this.relativeAccuracy * z.abs(), this.absoluteAccuracy);
/*     */       
/* 293 */       if (z.subtract(oldz).abs() <= tolerance) {
/* 294 */         this.resultComputed = true;
/* 295 */         this.iterationCount = i;
/* 296 */         return z;
/*     */       }
/* 298 */       if (pv.abs() <= this.functionValueAccuracy) {
/* 299 */         this.resultComputed = true;
/* 300 */         this.iterationCount = i;
/* 301 */         return z;
/*     */       }
/*     */       
/*     */ 
/* 305 */       G = dv.divide(pv);
/* 306 */       G2 = G.multiply(G);
/* 307 */       H = G2.subtract(d2v.divide(pv));
/* 308 */       delta = N1.multiply(N.multiply(H).subtract(G2));
/*     */       
/* 310 */       Complex deltaSqrt = delta.sqrt();
/* 311 */       Complex dplus = G.add(deltaSqrt);
/* 312 */       Complex dminus = G.subtract(deltaSqrt);
/* 313 */       denominator = dplus.abs() > dminus.abs() ? dplus : dminus;
/*     */       
/*     */ 
/* 316 */       if (denominator.equals(new Complex(0.0D, 0.0D))) {
/* 317 */         z = z.add(new Complex(this.absoluteAccuracy, this.absoluteAccuracy));
/* 318 */         oldz = new Complex(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
/*     */       }
/*     */       else {
/* 321 */         oldz = z;
/* 322 */         z = z.subtract(N.divide(denominator));
/*     */       }
/* 324 */       i++;
/*     */     }
/* 326 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/LaguerreSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */