/*     */ package org.apache.commons.math.stat.descriptive.moment;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.math.stat.descriptive.AbstractStorelessUnivariateStatistic;
/*     */ import org.apache.commons.math.stat.descriptive.summary.Sum;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Mean
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1296043746617791564L;
/*     */   protected FirstMoment moment;
/*     */   protected boolean incMoment;
/*     */   
/*     */   public Mean()
/*     */   {
/*  77 */     this.incMoment = true;
/*  78 */     this.moment = new FirstMoment();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Mean(FirstMoment m1)
/*     */   {
/*  87 */     this.moment = m1;
/*  88 */     this.incMoment = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  95 */     if (this.incMoment) {
/*  96 */       this.moment.increment(d);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 104 */     if (this.incMoment) {
/* 105 */       this.moment.clear();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/* 113 */     return this.moment.m1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 120 */     return this.moment.getN();
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
/*     */   public double evaluate(double[] values, int begin, int length)
/*     */   {
/* 140 */     if (test(values, begin, length)) {
/* 141 */       Sum sum = new Sum();
/* 142 */       double sampleSize = length;
/*     */       
/*     */ 
/* 145 */       double xbar = sum.evaluate(values, begin, length) / sampleSize;
/*     */       
/*     */ 
/* 148 */       double correction = 0.0D;
/* 149 */       for (int i = begin; i < begin + length; i++) {
/* 150 */         correction += values[i] - xbar;
/*     */       }
/* 152 */       return xbar + correction / sampleSize;
/*     */     }
/* 154 */     return NaN.0D;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/moment/Mean.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */