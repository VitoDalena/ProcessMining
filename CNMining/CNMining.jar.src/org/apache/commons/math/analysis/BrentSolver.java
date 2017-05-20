/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BrentSolver
/*     */   extends UnivariateRealSolverImpl
/*     */ {
/*     */   private static final long serialVersionUID = -2136672307739067002L;
/*     */   
/*     */   public BrentSolver(UnivariateRealFunction f)
/*     */   {
/*  42 */     super(f, 100, 1.0E-6D);
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
/*     */   public double solve(double min, double max, double initial)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  67 */     if ((initial - min) * (max - initial) < 0.0D) {
/*  68 */       throw new IllegalArgumentException("Initial guess is not in search interval.  Initial: " + initial + "  Endpoints: [" + min + "," + max + "]");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  74 */     double yInitial = this.f.value(initial);
/*  75 */     if (Math.abs(yInitial) <= this.functionValueAccuracy) {
/*  76 */       setResult(initial, 0);
/*  77 */       return this.result;
/*     */     }
/*     */     
/*     */ 
/*  81 */     double yMin = this.f.value(min);
/*  82 */     if (Math.abs(yMin) <= this.functionValueAccuracy) {
/*  83 */       setResult(yMin, 0);
/*  84 */       return this.result;
/*     */     }
/*     */     
/*     */ 
/*  88 */     if (yInitial * yMin < 0.0D) {
/*  89 */       return solve(min, yMin, initial, yInitial, min, yMin);
/*     */     }
/*     */     
/*     */ 
/*  93 */     double yMax = this.f.value(max);
/*  94 */     if (Math.abs(yMax) <= this.functionValueAccuracy) {
/*  95 */       setResult(yMax, 0);
/*  96 */       return this.result;
/*     */     }
/*     */     
/*     */ 
/* 100 */     if (yInitial * yMax < 0.0D) {
/* 101 */       return solve(initial, yInitial, max, yMax, initial, yInitial);
/*     */     }
/*     */     
/*     */ 
/* 105 */     return solve(min, yMin, max, yMax, initial, yInitial);
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
/*     */   public double solve(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 128 */     clearResult();
/* 129 */     verifyInterval(min, max);
/*     */     
/* 131 */     double yMin = this.f.value(min);
/* 132 */     double yMax = this.f.value(max);
/*     */     
/*     */ 
/* 135 */     if (yMin * yMax >= 0.0D) {
/* 136 */       throw new IllegalArgumentException("Function values at endpoints do not have different signs.  Endpoints: [" + min + "," + max + "]" + "  Values: [" + yMin + "," + yMax + "]");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 143 */     return solve(min, yMin, max, yMax, min, yMin);
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
/*     */   private double solve(double x0, double y0, double x1, double y1, double x2, double y2)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 168 */     double delta = x1 - x0;
/* 169 */     double oldDelta = delta;
/*     */     
/* 171 */     int i = 0;
/* 172 */     while (i < this.maximalIterationCount) {
/* 173 */       if (Math.abs(y2) < Math.abs(y1))
/*     */       {
/* 175 */         x0 = x1;
/* 176 */         x1 = x2;
/* 177 */         x2 = x0;
/* 178 */         y0 = y1;
/* 179 */         y1 = y2;
/* 180 */         y2 = y0;
/*     */       }
/* 182 */       if (Math.abs(y1) <= this.functionValueAccuracy)
/*     */       {
/*     */ 
/*     */ 
/* 186 */         setResult(x1, i);
/* 187 */         return this.result;
/*     */       }
/* 189 */       double dx = x2 - x1;
/* 190 */       double tolerance = Math.max(this.relativeAccuracy * Math.abs(x1), this.absoluteAccuracy);
/*     */       
/* 192 */       if (Math.abs(dx) <= tolerance) {
/* 193 */         setResult(x1, i);
/* 194 */         return this.result;
/*     */       }
/* 196 */       if ((Math.abs(oldDelta) < tolerance) || (Math.abs(y0) <= Math.abs(y1)))
/*     */       {
/*     */ 
/* 199 */         delta = 0.5D * dx;
/* 200 */         oldDelta = delta;
/*     */       } else {
/* 202 */         double r3 = y1 / y0;
/*     */         
/*     */         double p1;
/*     */         
/*     */         double p;
/*     */         double p1;
/* 208 */         if (x0 == x2)
/*     */         {
/* 210 */           double p = dx * r3;
/* 211 */           p1 = 1.0D - r3;
/*     */         }
/*     */         else {
/* 214 */           double r1 = y0 / y2;
/* 215 */           double r2 = y1 / y2;
/* 216 */           p = r3 * (dx * r1 * (r1 - r2) - (x1 - x0) * (r2 - 1.0D));
/* 217 */           p1 = (r1 - 1.0D) * (r2 - 1.0D) * (r3 - 1.0D);
/*     */         }
/* 219 */         if (p > 0.0D) {
/* 220 */           p1 = -p1;
/*     */         } else {
/* 222 */           p = -p;
/*     */         }
/* 224 */         if ((2.0D * p >= 1.5D * dx * p1 - Math.abs(tolerance * p1)) || (p >= Math.abs(0.5D * oldDelta * p1)))
/*     */         {
/*     */ 
/*     */ 
/*     */ 
/* 229 */           delta = 0.5D * dx;
/* 230 */           oldDelta = delta;
/*     */         } else {
/* 232 */           oldDelta = delta;
/* 233 */           delta = p / p1;
/*     */         }
/*     */       }
/*     */       
/* 237 */       x0 = x1;
/* 238 */       y0 = y1;
/*     */       
/* 240 */       if (Math.abs(delta) > tolerance) {
/* 241 */         x1 += delta;
/* 242 */       } else if (dx > 0.0D) {
/* 243 */         x1 += 0.5D * tolerance;
/* 244 */       } else if (dx <= 0.0D) {
/* 245 */         x1 -= 0.5D * tolerance;
/*     */       }
/* 247 */       y1 = this.f.value(x1);
/* 248 */       if ((y1 > 0.0D ? 1 : 0) == (y2 > 0.0D ? 1 : 0)) {
/* 249 */         x2 = x0;
/* 250 */         y2 = y0;
/* 251 */         delta = x1 - x0;
/* 252 */         oldDelta = delta;
/*     */       }
/* 254 */       i++;
/*     */     }
/* 256 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/BrentSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */