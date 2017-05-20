/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.special.Gamma;
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
/*     */ 
/*     */ 
/*     */ public class PoissonDistributionImpl
/*     */   extends AbstractIntegerDistribution
/*     */   implements PoissonDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3349935121172596109L;
/*     */   private NormalDistribution normal;
/*     */   private double mean;
/*     */   
/*     */   public PoissonDistributionImpl(double p)
/*     */   {
/*  53 */     this(p, new NormalDistributionImpl());
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
/*     */   public PoissonDistributionImpl(double p, NormalDistribution z)
/*     */   {
/*  68 */     setNormal(z);
/*  69 */     setMean(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/*  78 */     return this.mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMean(double p)
/*     */   {
/*  90 */     if (p <= 0.0D) {
/*  91 */       throw new IllegalArgumentException("The Poisson mean must be positive");
/*     */     }
/*     */     
/*  94 */     this.mean = p;
/*  95 */     this.normal.setMean(p);
/*  96 */     this.normal.setStandardDeviation(Math.sqrt(p));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double probability(int x)
/*     */   {
/* 106 */     if ((x < 0) || (x == Integer.MAX_VALUE)) {
/* 107 */       return 0.0D;
/*     */     }
/* 109 */     return Math.pow(getMean(), x) / MathUtils.factorialDouble(x) * Math.exp(-this.mean);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(int x)
/*     */     throws MathException
/*     */   {
/* 122 */     if (x < 0) {
/* 123 */       return 0.0D;
/*     */     }
/* 125 */     if (x == Integer.MAX_VALUE) {
/* 126 */       return 1.0D;
/*     */     }
/* 128 */     return Gamma.regularizedGammaQ(x + 1.0D, this.mean, 1.0E-12D, Integer.MAX_VALUE);
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
/*     */   public double normalApproximateProbability(int x)
/*     */     throws MathException
/*     */   {
/* 146 */     return this.normal.cumulativeProbability(x + 0.5D);
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
/* 158 */     return 0;
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
/* 170 */     return Integer.MAX_VALUE;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNormal(NormalDistribution value)
/*     */   {
/* 181 */     this.normal = value;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/PoissonDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */