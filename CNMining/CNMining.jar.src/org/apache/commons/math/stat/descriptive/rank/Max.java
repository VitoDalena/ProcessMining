/*     */ package org.apache.commons.math.stat.descriptive.rank;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Max
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */ {
/*     */   private static final long serialVersionUID = -5593383832225844641L;
/*     */   private long n;
/*     */   private double value;
/*     */   
/*     */   public Max()
/*     */   {
/*  53 */     this.n = 0L;
/*  54 */     this.value = NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  61 */     if ((d > this.value) || (Double.isNaN(this.value))) {
/*  62 */       this.value = d;
/*     */     }
/*  64 */     this.n += 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  71 */     this.value = NaN.0D;
/*  72 */     this.n = 0L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  79 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/*  86 */     return this.n;
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
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 112 */     double max = NaN.0D;
/* 113 */     if (test(values, begin, length)) {
/* 114 */       max = values[begin];
/* 115 */       for (int i = begin; i < begin + length; i++) {
/* 116 */         if (!Double.isNaN(values[i])) {
/* 117 */           max = max > values[i] ? max : values[i];
/*     */         }
/*     */       }
/*     */     }
/* 121 */     return max;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/rank/Max.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */