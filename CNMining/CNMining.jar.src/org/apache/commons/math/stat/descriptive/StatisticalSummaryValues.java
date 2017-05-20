/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StatisticalSummaryValues
/*     */   implements Serializable, StatisticalSummary
/*     */ {
/*     */   private static final long serialVersionUID = -5108854841843722536L;
/*     */   private final double mean;
/*     */   private final double variance;
/*     */   private final long n;
/*     */   private final double max;
/*     */   private final double min;
/*     */   private final double sum;
/*     */   
/*     */   public StatisticalSummaryValues(double mean, double variance, long n, double max, double min, double sum)
/*     */   {
/*  64 */     this.mean = mean;
/*  65 */     this.variance = variance;
/*  66 */     this.n = n;
/*  67 */     this.max = max;
/*  68 */     this.min = min;
/*  69 */     this.sum = sum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMax()
/*     */   {
/*  76 */     return this.max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/*  83 */     return this.mean;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getMin()
/*     */   {
/*  90 */     return this.min;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/*  97 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getSum()
/*     */   {
/* 104 */     return this.sum;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getStandardDeviation()
/*     */   {
/* 111 */     return Math.sqrt(this.variance);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getVariance()
/*     */   {
/* 118 */     return this.variance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 130 */     if (object == this) {
/* 131 */       return true;
/*     */     }
/* 133 */     if (!(object instanceof StatisticalSummaryValues)) {
/* 134 */       return false;
/*     */     }
/* 136 */     StatisticalSummaryValues stat = (StatisticalSummaryValues)object;
/* 137 */     return (MathUtils.equals(stat.getMax(), getMax())) && (MathUtils.equals(stat.getMean(), getMean())) && (MathUtils.equals(stat.getMin(), getMin())) && (MathUtils.equals(stat.getN(), getN())) && (MathUtils.equals(stat.getSum(), getSum())) && (MathUtils.equals(stat.getVariance(), getVariance()));
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
/*     */   public int hashCode()
/*     */   {
/* 151 */     int result = 31 + MathUtils.hash(getMax());
/* 152 */     result = result * 31 + MathUtils.hash(getMean());
/* 153 */     result = result * 31 + MathUtils.hash(getMin());
/* 154 */     result = result * 31 + MathUtils.hash(getN());
/* 155 */     result = result * 31 + MathUtils.hash(getSum());
/* 156 */     result = result * 31 + MathUtils.hash(getVariance());
/* 157 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/StatisticalSummaryValues.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */