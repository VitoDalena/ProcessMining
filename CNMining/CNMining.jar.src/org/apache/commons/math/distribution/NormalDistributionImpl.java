/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.MaxIterationsExceededException;
/*     */ import org.apache.commons.math.special.Erf;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NormalDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements NormalDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*  39 */   private double mean = 0.0D;
/*     */   
/*     */ 
/*  42 */   private double standardDeviation = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NormalDistributionImpl(double mean, double sd)
/*     */   {
/*  51 */     setMean(mean);
/*  52 */     setStandardDeviation(sd);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public NormalDistributionImpl()
/*     */   {
/*  60 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/*  68 */     return this.mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMean(double mean)
/*     */   {
/*  76 */     this.mean = mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStandardDeviation()
/*     */   {
/*  84 */     return this.standardDeviation;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStandardDeviation(double sd)
/*     */   {
/*  93 */     if (sd <= 0.0D) {
/*  94 */       throw new IllegalArgumentException("Standard deviation must be positive.");
/*     */     }
/*     */     
/*  97 */     this.standardDeviation = sd;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(double x)
/*     */     throws MathException
/*     */   {
/*     */     try
/*     */     {
/* 110 */       return 0.5D * (1.0D + Erf.erf((x - this.mean) / (this.standardDeviation * Math.sqrt(2.0D))));
/*     */     }
/*     */     catch (MaxIterationsExceededException ex) {
/* 113 */       if (x < this.mean - 20.0D * this.standardDeviation)
/* 114 */         return 0.0D;
/* 115 */       if (x > this.mean + 20.0D * this.standardDeviation) {
/* 116 */         return 1.0D;
/*     */       }
/* 118 */       throw ex;
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
/*     */ 
/*     */   public double inverseCumulativeProbability(double p)
/*     */     throws MathException
/*     */   {
/* 139 */     if (p == 0.0D) {
/* 140 */       return Double.NEGATIVE_INFINITY;
/*     */     }
/* 142 */     if (p == 1.0D) {
/* 143 */       return Double.POSITIVE_INFINITY;
/*     */     }
/* 145 */     return super.inverseCumulativeProbability(p);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getDomainLowerBound(double p)
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/* 160 */     if (p < 0.5D) {
/* 161 */       ret = -1.7976931348623157E308D;
/*     */     } else {
/* 163 */       ret = getMean();
/*     */     }
/*     */     
/* 166 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getDomainUpperBound(double p)
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/* 181 */     if (p < 0.5D) {
/* 182 */       ret = getMean();
/*     */     } else {
/* 184 */       ret = Double.MAX_VALUE;
/*     */     }
/*     */     
/* 187 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected double getInitialDomain(double p)
/*     */   {
/*     */     double ret;
/*     */     
/*     */ 
/*     */     double ret;
/*     */     
/*     */ 
/* 201 */     if (p < 0.5D) {
/* 202 */       ret = getMean() - getStandardDeviation(); } else { double ret;
/* 203 */       if (p > 0.5D) {
/* 204 */         ret = getMean() + getStandardDeviation();
/*     */       } else {
/* 206 */         ret = getMean();
/*     */       }
/*     */     }
/* 209 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/NormalDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */