/*     */ package org.apache.commons.math.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardDeviation
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5728716329662425188L;
/*  47 */   private Variance variance = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardDeviation()
/*     */   {
/*  54 */     this.variance = new Variance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardDeviation(SecondMoment m2)
/*     */   {
/*  63 */     this.variance = new Variance(m2);
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
/*     */   public StandardDeviation(boolean isBiasCorrected)
/*     */   {
/*  77 */     this.variance = new Variance(isBiasCorrected);
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
/*     */   public StandardDeviation(boolean isBiasCorrected, SecondMoment m2)
/*     */   {
/*  92 */     this.variance = new Variance(isBiasCorrected, m2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  99 */     this.variance.increment(d);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 106 */     return this.variance.getN();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/* 113 */     return Math.sqrt(this.variance.getResult());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 120 */     this.variance.clear();
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
/*     */   public double evaluate(double[] values)
/*     */   {
/* 138 */     return Math.sqrt(this.variance.evaluate(values));
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
/*     */ 
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 160 */     return Math.sqrt(this.variance.evaluate(values, begin, length));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, double mean, int begin, int length)
/*     */   {
/* 189 */     return Math.sqrt(this.variance.evaluate(values, mean, begin, length));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, double mean)
/*     */   {
/* 214 */     return Math.sqrt(this.variance.evaluate(values, mean));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean isBiasCorrected()
/*     */   {
/* 221 */     return this.variance.isBiasCorrected();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setBiasCorrected(boolean isBiasCorrected)
/*     */   {
/* 228 */     this.variance.setBiasCorrected(isBiasCorrected);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/StandardDeviation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */