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
/*     */ public class CauchyDistributionImpl
/*     */   extends AbstractContinuousDistribution
/*     */   implements CauchyDistribution, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8589540077390120676L;
/*  36 */   private double median = 0.0D;
/*     */   
/*     */ 
/*  39 */   private double scale = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public CauchyDistributionImpl()
/*     */   {
/*  46 */     this(0.0D, 1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CauchyDistributionImpl(double median, double s)
/*     */   {
/*  56 */     setMedian(median);
/*  57 */     setScale(s);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double cumulativeProbability(double x)
/*     */   {
/*  66 */     return 0.5D + Math.atan((x - this.median) / this.scale) / 3.141592653589793D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMedian()
/*     */   {
/*  74 */     return this.median;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getScale()
/*     */   {
/*  82 */     return this.scale;
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
/*  99 */     if ((p < 0.0D) || (p > 1.0D))
/* 100 */       throw new IllegalArgumentException("probability argument must be between 0 and 1 (inclusive)");
/*     */     double ret;
/* 102 */     double ret; if (p == 0.0D) {
/* 103 */       ret = Double.NEGATIVE_INFINITY; } else { double ret;
/* 104 */       if (p == 1.0D) {
/* 105 */         ret = Double.POSITIVE_INFINITY;
/*     */       } else
/* 107 */         ret = this.median + this.scale * Math.tan(3.141592653589793D * (p - 0.5D));
/*     */     }
/* 109 */     return ret;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMedian(double median)
/*     */   {
/* 117 */     this.median = median;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScale(double s)
/*     */   {
/* 126 */     if (s <= 0.0D) {
/* 127 */       throw new IllegalArgumentException("Scale must be positive.");
/*     */     }
/*     */     
/* 130 */     this.scale = s;
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
/* 145 */     if (p < 0.5D) {
/* 146 */       ret = -1.7976931348623157E308D;
/*     */     } else {
/* 148 */       ret = getMedian();
/*     */     }
/*     */     
/* 151 */     return ret;
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
/* 166 */     if (p < 0.5D) {
/* 167 */       ret = getMedian();
/*     */     } else {
/* 169 */       ret = Double.MAX_VALUE;
/*     */     }
/*     */     
/* 172 */     return ret;
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
/* 186 */     if (p < 0.5D) {
/* 187 */       ret = getMedian() - getScale(); } else { double ret;
/* 188 */       if (p > 0.5D) {
/* 189 */         ret = getMedian() + getScale();
/*     */       } else {
/* 191 */         ret = getMedian();
/*     */       }
/*     */     }
/* 194 */     return ret;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/CauchyDistributionImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */