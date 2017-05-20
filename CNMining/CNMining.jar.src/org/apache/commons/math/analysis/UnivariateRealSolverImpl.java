/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ public abstract class UnivariateRealSolverImpl
/*     */   implements UnivariateRealSolver, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1112491292565386596L;
/*     */   protected double absoluteAccuracy;
/*     */   protected double relativeAccuracy;
/*     */   protected double functionValueAccuracy;
/*     */   protected int maximalIterationCount;
/*     */   protected double defaultAbsoluteAccuracy;
/*     */   protected double defaultRelativeAccuracy;
/*     */   protected double defaultFunctionValueAccuracy;
/*     */   protected int defaultMaximalIterationCount;
/*  61 */   protected boolean resultComputed = false;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double result;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int iterationCount;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected UnivariateRealFunction f;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected UnivariateRealSolverImpl(UnivariateRealFunction f, int defaultMaximalIterationCount, double defaultAbsoluteAccuracy)
/*     */   {
/*  89 */     if (f == null) {
/*  90 */       throw new IllegalArgumentException("function can not be null.");
/*     */     }
/*     */     
/*  93 */     this.f = f;
/*  94 */     this.defaultAbsoluteAccuracy = defaultAbsoluteAccuracy;
/*  95 */     this.defaultRelativeAccuracy = 1.0E-14D;
/*  96 */     this.defaultFunctionValueAccuracy = 1.0E-15D;
/*  97 */     this.absoluteAccuracy = defaultAbsoluteAccuracy;
/*  98 */     this.relativeAccuracy = this.defaultRelativeAccuracy;
/*  99 */     this.functionValueAccuracy = this.defaultFunctionValueAccuracy;
/* 100 */     this.defaultMaximalIterationCount = defaultMaximalIterationCount;
/* 101 */     this.maximalIterationCount = defaultMaximalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/* 111 */     if (this.resultComputed) {
/* 112 */       return this.result;
/*     */     }
/* 114 */     throw new IllegalStateException("No result available");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIterationCount()
/*     */   {
/* 126 */     if (this.resultComputed) {
/* 127 */       return this.iterationCount;
/*     */     }
/* 129 */     throw new IllegalStateException("No result available");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected final void setResult(double result, int iterationCount)
/*     */   {
/* 140 */     this.result = result;
/* 141 */     this.iterationCount = iterationCount;
/* 142 */     this.resultComputed = true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected final void clearResult()
/*     */   {
/* 149 */     this.resultComputed = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAbsoluteAccuracy(double accuracy)
/*     */   {
/* 160 */     this.absoluteAccuracy = accuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAbsoluteAccuracy()
/*     */   {
/* 169 */     return this.absoluteAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetAbsoluteAccuracy()
/*     */   {
/* 176 */     this.absoluteAccuracy = this.defaultAbsoluteAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaximalIterationCount(int count)
/*     */   {
/* 185 */     this.maximalIterationCount = count;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getMaximalIterationCount()
/*     */   {
/* 194 */     return this.maximalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetMaximalIterationCount()
/*     */   {
/* 201 */     this.maximalIterationCount = this.defaultMaximalIterationCount;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRelativeAccuracy(double accuracy)
/*     */   {
/* 212 */     this.relativeAccuracy = accuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRelativeAccuracy()
/*     */   {
/* 220 */     return this.relativeAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetRelativeAccuracy()
/*     */   {
/* 227 */     this.relativeAccuracy = this.defaultRelativeAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFunctionValueAccuracy(double accuracy)
/*     */   {
/* 238 */     this.functionValueAccuracy = accuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getFunctionValueAccuracy()
/*     */   {
/* 246 */     return this.functionValueAccuracy;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void resetFunctionValueAccuracy()
/*     */   {
/* 253 */     this.functionValueAccuracy = this.defaultFunctionValueAccuracy;
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
/*     */   protected boolean isBracketing(double lower, double upper, UnivariateRealFunction f)
/*     */     throws FunctionEvaluationException
/*     */   {
/* 269 */     double f1 = f.value(lower);
/* 270 */     double f2 = f.value(upper);
/* 271 */     return ((f1 > 0.0D) && (f2 < 0.0D)) || ((f1 < 0.0D) && (f2 > 0.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isSequence(double start, double mid, double end)
/*     */   {
/* 283 */     return (start < mid) && (mid < end);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyInterval(double lower, double upper)
/*     */   {
/* 295 */     if (lower >= upper) {
/* 296 */       throw new IllegalArgumentException("Endpoints do not specify an interval: [" + lower + "," + upper + "]");
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
/*     */ 
/*     */ 
/*     */   protected void verifySequence(double lower, double initial, double upper)
/*     */   {
/* 312 */     if (!isSequence(lower, initial, upper)) {
/* 313 */       throw new IllegalArgumentException("Invalid interval, initial value parameters:  lower=" + lower + " initial=" + initial + " upper=" + upper);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void verifyBracketing(double lower, double upper, UnivariateRealFunction f)
/*     */     throws FunctionEvaluationException
/*     */   {
/* 333 */     verifyInterval(lower, upper);
/* 334 */     if (!isBracketing(lower, upper, f)) {
/* 335 */       throw new IllegalArgumentException("Function values at endpoints do not have different signs.  Endpoints: [" + lower + "," + upper + "]" + "  Values: [" + f.value(lower) + "," + f.value(upper) + "]");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealSolverImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */