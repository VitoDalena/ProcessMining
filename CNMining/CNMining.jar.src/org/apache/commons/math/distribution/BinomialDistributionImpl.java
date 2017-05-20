/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.special.Beta;
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
/*     */ public class BinomialDistributionImpl
/*     */   extends AbstractIntegerDistribution
/*     */   implements BinomialDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6751309484392813623L;
/*     */   private int numberOfTrials;
/*     */   private double probabilityOfSuccess;
/*     */   
/*     */   public BinomialDistributionImpl(int trials, double p)
/*     */   {
/*  51 */     setNumberOfTrials(trials);
/*  52 */     setProbabilityOfSuccess(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNumberOfTrials()
/*     */   {
/*  60 */     return this.numberOfTrials;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getProbabilityOfSuccess()
/*     */   {
/*  68 */     return this.probabilityOfSuccess;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberOfTrials(int trials)
/*     */   {
/*  78 */     if (trials < 0) {
/*  79 */       throw new IllegalArgumentException("number of trials must be non-negative.");
/*     */     }
/*  81 */     this.numberOfTrials = trials;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setProbabilityOfSuccess(double p)
/*     */   {
/*  91 */     if ((p < 0.0D) || (p > 1.0D)) {
/*  92 */       throw new IllegalArgumentException("probability of success must be between 0.0 and 1.0, inclusive.");
/*     */     }
/*  94 */     this.probabilityOfSuccess = p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getDomainLowerBound(double p)
/*     */   {
/* 106 */     return -1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected int getDomainUpperBound(double p)
/*     */   {
/* 118 */     return getNumberOfTrials();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(int x)
/*     */     throws MathException
/*     */   {
/*     */     double ret;
/*     */     
/*     */     double ret;
/*     */     
/* 130 */     if (x < 0) {
/* 131 */       ret = 0.0D; } else { double ret;
/* 132 */       if (x >= getNumberOfTrials()) {
/* 133 */         ret = 1.0D;
/*     */       } else {
/* 135 */         ret = 1.0D - Beta.regularizedBeta(getProbabilityOfSuccess(), x + 1.0D, getNumberOfTrials() - x);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 141 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double probability(int x)
/*     */   {
/*     */     double ret;
/*     */     
/*     */     double ret;
/*     */     
/* 152 */     if ((x < 0) || (x > getNumberOfTrials())) {
/* 153 */       ret = 0.0D;
/*     */     } else {
/* 155 */       ret = MathUtils.binomialCoefficientDouble(getNumberOfTrials(), x) * Math.pow(getProbabilityOfSuccess(), x) * Math.pow(1.0D - getProbabilityOfSuccess(), getNumberOfTrials() - x);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 161 */     return ret;
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
/*     */   public int inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/* 179 */     if (p == 0.0D) {
/* 180 */       return -1;
/*     */     }
/* 182 */     if (p == 1.0D) {
/* 183 */       return Integer.MAX_VALUE;
/*     */     }
/*     */     
/*     */ 
/* 187 */     return super.inverseCumulativeProbability(p);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/BinomialDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */