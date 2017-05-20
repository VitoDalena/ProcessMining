/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
/*     */ import org.apache.commons.math.util.MathUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MullerSolver
/*     */   extends UnivariateRealSolverImpl
/*     */ {
/*     */   private static final long serialVersionUID = 6552227503458976920L;
/*     */   
/*     */   public MullerSolver(UnivariateRealFunction f)
/*     */   {
/*  47 */     super(f, 100, 1.0E-6D);
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
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  69 */     if (this.f.value(min) == 0.0D) return min;
/*  70 */     if (this.f.value(max) == 0.0D) return max;
/*  71 */     if (this.f.value(initial) == 0.0D) { return initial;
/*     */     }
/*  73 */     verifyBracketing(min, max, this.f);
/*  74 */     verifySequence(min, initial, max);
/*  75 */     if (isBracketing(min, initial, this.f)) {
/*  76 */       return solve(min, initial);
/*     */     }
/*  78 */     return solve(initial, max);
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
/*     */   public double solve(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 116 */     double x0 = min;double y0 = this.f.value(x0);
/* 117 */     double x2 = max;double y2 = this.f.value(x2);
/* 118 */     double x1 = 0.5D * (x0 + x2);double y1 = this.f.value(x1);
/*     */     
/*     */ 
/* 121 */     if (y0 == 0.0D) return min;
/* 122 */     if (y2 == 0.0D) return max;
/* 123 */     verifyBracketing(min, max, this.f);
/*     */     
/* 125 */     int i = 1;
/* 126 */     double oldx = Double.POSITIVE_INFINITY;
/* 127 */     while (i <= this.maximalIterationCount)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/* 132 */       double d01 = (y1 - y0) / (x1 - x0);
/* 133 */       double d12 = (y2 - y1) / (x2 - x1);
/* 134 */       double d012 = (d12 - d01) / (x2 - x0);
/* 135 */       double c1 = d01 + (x1 - x0) * d012;
/* 136 */       double delta = c1 * c1 - 4.0D * y1 * d012;
/* 137 */       double xplus = x1 + -2.0D * y1 / (c1 + Math.sqrt(delta));
/* 138 */       double xminus = x1 + -2.0D * y1 / (c1 - Math.sqrt(delta));
/*     */       
/*     */ 
/* 141 */       double x = isSequence(x0, xplus, x2) ? xplus : xminus;
/* 142 */       double y = this.f.value(x);
/*     */       
/*     */ 
/* 145 */       double tolerance = Math.max(this.relativeAccuracy * Math.abs(x), this.absoluteAccuracy);
/* 146 */       if (Math.abs(x - oldx) <= tolerance) {
/* 147 */         setResult(x, i);
/* 148 */         return this.result;
/*     */       }
/* 150 */       if (Math.abs(y) <= this.functionValueAccuracy) {
/* 151 */         setResult(x, i);
/* 152 */         return this.result;
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 159 */       boolean bisect = ((x < x1) && (x1 - x0 > 0.95D * (x2 - x0))) || ((x > x1) && (x2 - x1 > 0.95D * (x2 - x0))) || (x == x1);
/*     */       
/*     */ 
/*     */ 
/* 163 */       if (!bisect) {
/* 164 */         x0 = x < x1 ? x0 : x1;
/* 165 */         y0 = x < x1 ? y0 : y1;
/* 166 */         x2 = x > x1 ? x2 : x1;
/* 167 */         y2 = x > x1 ? y2 : y1;
/* 168 */         x1 = x;y1 = y;
/* 169 */         oldx = x;
/*     */       } else {
/* 171 */         double xm = 0.5D * (x0 + x2);
/* 172 */         double ym = this.f.value(xm);
/* 173 */         if (MathUtils.sign(y0) + MathUtils.sign(ym) == 0.0D) {
/* 174 */           x2 = xm;y2 = ym;
/*     */         } else {
/* 176 */           x0 = xm;y0 = ym;
/*     */         }
/* 178 */         x1 = 0.5D * (x0 + x2);
/* 179 */         y1 = this.f.value(x1);
/* 180 */         oldx = Double.POSITIVE_INFINITY;
/*     */       }
/* 182 */       i++;
/*     */     }
/* 184 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
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
/*     */   public double solve2(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/* 221 */     double x0 = min;double y0 = this.f.value(x0);
/* 222 */     double x1 = max;double y1 = this.f.value(x1);
/* 223 */     double x2 = 0.5D * (x0 + x1);double y2 = this.f.value(x2);
/*     */     
/*     */ 
/* 226 */     if (y0 == 0.0D) return min;
/* 227 */     if (y1 == 0.0D) return max;
/* 228 */     verifyBracketing(min, max, this.f);
/*     */     
/* 230 */     int i = 1;
/* 231 */     double oldx = Double.POSITIVE_INFINITY;
/* 232 */     while (i <= this.maximalIterationCount)
/*     */     {
/* 234 */       double q = (x2 - x1) / (x1 - x0);
/* 235 */       double A = q * (y2 - (1.0D + q) * y1 + q * y0);
/* 236 */       double B = (2.0D * q + 1.0D) * y2 - (1.0D + q) * (1.0D + q) * y1 + q * q * y0;
/* 237 */       double C = (1.0D + q) * y2;
/* 238 */       double delta = B * B - 4.0D * A * C;
/* 239 */       double denominator; double denominator; if (delta >= 0.0D)
/*     */       {
/* 241 */         double dplus = B + Math.sqrt(delta);
/* 242 */         double dminus = B - Math.sqrt(delta);
/* 243 */         denominator = Math.abs(dplus) > Math.abs(dminus) ? dplus : dminus;
/*     */       }
/*     */       else {
/* 246 */         denominator = Math.sqrt(B * B - delta);
/*     */       }
/* 248 */       if (denominator != 0.0D) {
/* 249 */         double x = x2 - 2.0D * C * (x2 - x1) / denominator;
/*     */         
/*     */ 
/* 252 */         while ((x == x1) || (x == x2)) {
/* 253 */           x += this.absoluteAccuracy;
/*     */         }
/*     */       }
/*     */       
/* 257 */       double x = min + Math.random() * (max - min);
/* 258 */       oldx = Double.POSITIVE_INFINITY;
/*     */       
/* 260 */       double y = this.f.value(x);
/*     */       
/*     */ 
/* 263 */       double tolerance = Math.max(this.relativeAccuracy * Math.abs(x), this.absoluteAccuracy);
/* 264 */       if (Math.abs(x - oldx) <= tolerance) {
/* 265 */         setResult(x, i);
/* 266 */         return this.result;
/*     */       }
/* 268 */       if (Math.abs(y) <= this.functionValueAccuracy) {
/* 269 */         setResult(x, i);
/* 270 */         return this.result;
/*     */       }
/*     */       
/*     */ 
/* 274 */       x0 = x1;y0 = y1;
/* 275 */       x1 = x2;y1 = y2;
/* 276 */       x2 = x;y2 = y;
/* 277 */       oldx = x;
/* 278 */       i++;
/*     */     }
/* 280 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/MullerSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */