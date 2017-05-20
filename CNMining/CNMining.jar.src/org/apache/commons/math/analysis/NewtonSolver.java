/*     */ package org.apache.commons.math.analysis;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
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
/*     */ public class NewtonSolver
/*     */   extends UnivariateRealSolverImpl
/*     */ {
/*     */   private static final long serialVersionUID = 2067325783137941016L;
/*     */   private transient UnivariateRealFunction derivative;
/*     */   
/*     */   public NewtonSolver(DifferentiableUnivariateRealFunction f)
/*     */   {
/*  45 */     super(f, 100, 1.0E-6D);
/*  46 */     this.derivative = f.derivative();
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
/*     */   public double solve(double min, double max)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  62 */     return solve(min, max, UnivariateRealSolverUtils.midpoint(min, max));
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
/*     */   public double solve(double min, double max, double startValue)
/*     */     throws MaxIterationsExceededException, FunctionEvaluationException
/*     */   {
/*  80 */     clearResult();
/*  81 */     verifySequence(min, startValue, max);
/*     */     
/*  83 */     double x0 = startValue;
/*     */     
/*     */ 
/*  86 */     int i = 0;
/*  87 */     while (i < this.maximalIterationCount) {
/*  88 */       double x1 = x0 - this.f.value(x0) / this.derivative.value(x0);
/*  89 */       if (Math.abs(x1 - x0) <= this.absoluteAccuracy)
/*     */       {
/*  91 */         setResult(x1, i);
/*  92 */         return x1;
/*     */       }
/*     */       
/*  95 */       x0 = x1;
/*  96 */       i++;
/*     */     }
/*     */     
/*  99 */     throw new MaxIterationsExceededException(this.maximalIterationCount);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream in)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 111 */     in.defaultReadObject();
/* 112 */     this.derivative = ((DifferentiableUnivariateRealFunction)this.f).derivative();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/NewtonSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */