/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import org.apache.commons.math.ConvergenceException;
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
/*     */ public class UnivariateRealSolverUtils
/*     */ {
/*  36 */   private static UnivariateRealSolverFactory factory = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double solve(UnivariateRealFunction f, double x0, double x1)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/*  54 */     setup(f);
/*  55 */     return factory.newDefaultSolver(f).solve(x0, x1);
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
/*     */   public static double solve(UnivariateRealFunction f, double x0, double x1, double absoluteAccuracy)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/*  78 */     setup(f);
/*  79 */     UnivariateRealSolver solver = factory.newDefaultSolver(f);
/*  80 */     solver.setAbsoluteAccuracy(absoluteAccuracy);
/*  81 */     return solver.solve(x0, x1);
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
/*     */   public static double[] bracket(UnivariateRealFunction function, double initial, double lowerBound, double upperBound)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/* 128 */     return bracket(function, initial, lowerBound, upperBound, Integer.MAX_VALUE);
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
/*     */   public static double[] bracket(UnivariateRealFunction function, double initial, double lowerBound, double upperBound, int maximumIterations)
/*     */     throws ConvergenceException, FunctionEvaluationException
/*     */   {
/* 171 */     if (function == null) {
/* 172 */       throw new IllegalArgumentException("function is null.");
/*     */     }
/* 174 */     if (maximumIterations <= 0) {
/* 175 */       throw new IllegalArgumentException("bad value for maximumIterations: " + maximumIterations);
/*     */     }
/*     */     
/* 178 */     if ((initial < lowerBound) || (initial > upperBound) || (lowerBound >= upperBound)) {
/* 179 */       throw new IllegalArgumentException("Invalid endpoint parameters:  lowerBound=" + lowerBound + " initial=" + initial + " upperBound=" + upperBound);
/*     */     }
/*     */     
/*     */ 
/* 183 */     double a = initial;
/* 184 */     double b = initial;
/*     */     
/*     */ 
/* 187 */     int numIterations = 0;
/*     */     double fa;
/*     */     double fb;
/* 190 */     do { a = Math.max(a - 1.0D, lowerBound);
/* 191 */       b = Math.min(b + 1.0D, upperBound);
/* 192 */       fa = function.value(a);
/*     */       
/* 194 */       fb = function.value(b);
/* 195 */       numIterations++;
/* 196 */     } while ((fa * fb > 0.0D) && (numIterations < maximumIterations) && ((a > lowerBound) || (b < upperBound)));
/*     */     
/*     */ 
/* 199 */     if (fa * fb >= 0.0D) {
/* 200 */       throw new ConvergenceException("Number of iterations={0}, maximum iterations={1}, initial={2}, lower bound={3}, upper bound={4}, final a value={5}, final b value={6}, f(a)={7}, f(b)={8}", new Object[] { new Integer(numIterations), new Integer(maximumIterations), new Double(initial), new Double(lowerBound), new Double(upperBound), new Double(a), new Double(b), new Double(fa), new Double(fb) });
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 207 */     return new double[] { a, b };
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double midpoint(double a, double b)
/*     */   {
/* 218 */     return (a + b) * 0.5D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private static void setup(UnivariateRealFunction f)
/*     */   {
/* 230 */     if (f == null) {
/* 231 */       throw new IllegalArgumentException("function can not be null.");
/*     */     }
/*     */     
/* 234 */     if (factory == null) {
/* 235 */       factory = UnivariateRealSolverFactory.newInstance();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealSolverUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */