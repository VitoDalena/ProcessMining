/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.ConvergenceException;
/*     */ import org.apache.commons.math.FunctionEvaluationException;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.analysis.UnivariateRealFunction;
/*     */ import org.apache.commons.math.analysis.UnivariateRealSolverUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractContinuousDistribution
/*     */   extends AbstractDistribution
/*     */   implements ContinuousDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -38038050983108802L;
/*     */   
/*     */   public double inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/*  61 */     if ((p < 0.0D) || (p > 1.0D)) {
/*  62 */       throw new IllegalArgumentException("p must be between 0.0 and 1.0, inclusive.");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*  67 */     UnivariateRealFunction rootFindingFunction = new UnivariateRealFunction() {
/*     */       private final double val$p;
/*     */       
/*     */       public double value(double x) throws FunctionEvaluationException {
/*     */         try {
/*  72 */           return AbstractContinuousDistribution.this.cumulativeProbability(x) - this.val$p;
/*     */         } catch (MathException ex) {
/*  74 */           throw new FunctionEvaluationException(x, ex.getPattern(), ex.getArguments(), ex);
/*     */         }
/*     */         
/*     */       }
/*     */       
/*  79 */     };
/*  80 */     double lowerBound = getDomainLowerBound(p);
/*  81 */     double upperBound = getDomainUpperBound(p);
/*  82 */     double[] bracket = null;
/*     */     try {
/*  84 */       bracket = UnivariateRealSolverUtils.bracket(rootFindingFunction, getInitialDomain(p), lowerBound, upperBound);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     }
/*     */     catch (ConvergenceException ex)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  96 */       if (Math.abs(rootFindingFunction.value(lowerBound)) < 1.0E-6D) {
/*  97 */         return lowerBound;
/*     */       }
/*  99 */       if (Math.abs(rootFindingFunction.value(upperBound)) < 1.0E-6D) {
/* 100 */         return upperBound;
/*     */       }
/*     */       
/* 103 */       throw new MathException(ex);
/*     */     }
/*     */     
/*     */ 
/* 107 */     double root = UnivariateRealSolverUtils.solve(rootFindingFunction, bracket[0], bracket[1]);
/*     */     
/* 109 */     return root;
/*     */   }
/*     */   
/*     */   protected abstract double getInitialDomain(double paramDouble);
/*     */   
/*     */   protected abstract double getDomainLowerBound(double paramDouble);
/*     */   
/*     */   protected abstract double getDomainUpperBound(double paramDouble);
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/AbstractContinuousDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */