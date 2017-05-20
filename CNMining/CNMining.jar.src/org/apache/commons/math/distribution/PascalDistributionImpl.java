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
/*     */ public class PascalDistributionImpl
/*     */   extends AbstractIntegerDistribution
/*     */   implements PascalDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6751309484392813623L;
/*     */   private int numberOfSuccesses;
/*     */   private double probabilityOfSuccess;
/*     */   
/*     */   public PascalDistributionImpl(int r, double p)
/*     */   {
/*  50 */     setNumberOfSuccesses(r);
/*  51 */     setProbabilityOfSuccess(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getNumberOfSuccesses()
/*     */   {
/*  59 */     return this.numberOfSuccesses;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getProbabilityOfSuccess()
/*     */   {
/*  67 */     return this.probabilityOfSuccess;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberOfSuccesses(int successes)
/*     */   {
/*  77 */     if (successes < 0) {
/*  78 */       throw new IllegalArgumentException("number of successes must be non-negative.");
/*     */     }
/*     */     
/*  81 */     this.numberOfSuccesses = successes;
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
/*     */     
/*  95 */     this.probabilityOfSuccess = p;
/*     */   }
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
/* 118 */     return 2147483646;
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
/* 131 */       ret = 0.0D;
/*     */     } else {
/* 133 */       ret = Beta.regularizedBeta(getProbabilityOfSuccess(), getNumberOfSuccesses(), x + 1);
/*     */     }
/*     */     
/* 136 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */   public double probability(int x)
/*     */   {
/*     */     double ret;
/*     */     
/*     */     double ret;
/*     */     
/* 146 */     if (x < 0) {
/* 147 */       ret = 0.0D;
/*     */     } else {
/* 149 */       ret = MathUtils.binomialCoefficientDouble(x + getNumberOfSuccesses() - 1, getNumberOfSuccesses() - 1) * Math.pow(getProbabilityOfSuccess(), getNumberOfSuccesses()) * Math.pow(1.0D - getProbabilityOfSuccess(), x);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 154 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/*     */     int ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     int ret;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 174 */     if (p == 0.0D) {
/* 175 */       ret = -1; } else { int ret;
/* 176 */       if (p == 1.0D) {
/* 177 */         ret = Integer.MAX_VALUE;
/*     */       } else {
/* 179 */         ret = super.inverseCumulativeProbability(p);
/*     */       }
/*     */     }
/* 182 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/PascalDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */