/*     */ package org.apache.commons.math.stat.descriptive.moment;
/*     */ 
/*     */ import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math.stat.descriptive.StorelessUnivariateStatistic;
/*     */ import org.apache.commons.math.stat.descriptive.summary.SumOfLogs;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GeometricMean
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */ {
/*     */   private static final long serialVersionUID = -8178734905303459453L;
/*     */   private StorelessUnivariateStatistic sumOfLogs;
/*     */   
/*     */   public GeometricMean()
/*     */   {
/*  59 */     this.sumOfLogs = new SumOfLogs();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public GeometricMean(SumOfLogs sumOfLogs)
/*     */   {
/*  66 */     this.sumOfLogs = sumOfLogs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  73 */     this.sumOfLogs.increment(d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  80 */     if (this.sumOfLogs.getN() > 0L) {
/*  81 */       return Math.exp(this.sumOfLogs.getResult() / this.sumOfLogs.getN());
/*     */     }
/*  83 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  91 */     this.sumOfLogs.clear();
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
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 112 */     return Math.exp(this.sumOfLogs.evaluate(values, begin, length) / length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 120 */     return this.sumOfLogs.getN();
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
/*     */   public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl)
/*     */   {
/* 136 */     checkEmpty();
/* 137 */     this.sumOfLogs = sumLogImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getSumLogImpl()
/*     */   {
/* 146 */     return this.sumOfLogs;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkEmpty()
/*     */   {
/* 153 */     if (getN() > 0L) {
/* 154 */       throw new IllegalStateException("Implementation must be configured before values are added.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/GeometricMean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */