/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WeibullDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements WeibullDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*     */   private double alpha;
/*     */   private double beta;
/*     */   
/*     */   public WeibullDistributionImpl(double alpha, double beta)
/*     */   {
/*  49 */     setShape(alpha);
/*  50 */     setScale(beta);
/*     */   }
/*     */   
/*     */ 
/*     */   public double cumulativeProbability(double x)
/*     */   {
/*     */     double ret;
/*     */     
/*     */     double ret;
/*     */     
/*  60 */     if (x <= 0.0D) {
/*  61 */       ret = 0.0D;
/*     */     } else {
/*  63 */       ret = 1.0D - Math.exp(-Math.pow(x / getScale(), getShape()));
/*     */     }
/*  65 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getShape()
/*     */   {
/*  73 */     return this.alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getScale()
/*     */   {
/*  81 */     return this.beta;
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
/*     */   public double inverseCumulativeProbability(double p)
/*     */   {
/*  98 */     if ((p < 0.0D) || (p > 1.0D))
/*  99 */       throw new IllegalArgumentException("probability argument must be between 0 and 1 (inclusive)");
/*     */     double ret;
/* 101 */     double ret; if (p == 0.0D) {
/* 102 */       ret = 0.0D; } else { double ret;
/* 103 */       if (p == 1.0D) {
/* 104 */         ret = Double.POSITIVE_INFINITY;
/*     */       } else
/* 106 */         ret = getScale() * Math.pow(-Math.log(1.0D - p), 1.0D / getShape());
/*     */     }
/* 108 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShape(double alpha)
/*     */   {
/* 116 */     if (alpha <= 0.0D) {
/* 117 */       throw new IllegalArgumentException("Shape must be positive.");
/*     */     }
/*     */     
/* 120 */     this.alpha = alpha;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScale(double beta)
/*     */   {
/* 128 */     if (beta <= 0.0D) {
/* 129 */       throw new IllegalArgumentException("Scale must be positive.");
/*     */     }
/*     */     
/* 132 */     this.beta = beta;
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
/*     */   protected double getDomainLowerBound(double p)
/*     */   {
/* 145 */     return 0.0D;
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
/*     */   protected double getDomainUpperBound(double p)
/*     */   {
/* 158 */     return Double.MAX_VALUE;
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
/*     */   protected double getInitialDomain(double p)
/*     */   {
/* 171 */     return Math.pow(getScale() * Math.log(2.0D), 1.0D / getShape());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/WeibullDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */