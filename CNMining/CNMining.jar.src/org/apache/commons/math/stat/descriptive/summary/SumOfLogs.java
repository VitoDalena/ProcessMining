/*     */ package org.apache.commons.math.stat.descriptive.summary;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SumOfLogs
/*     */   extends AbstractStorelessUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -370076995648386763L;
/*     */   private int n;
/*     */   private double value;
/*     */   
/*     */   public SumOfLogs()
/*     */   {
/*  61 */     this.value = 0.0D;
/*  62 */     this.n = 0;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void increment(double d)
/*     */   {
/*  69 */     this.value += Math.log(d);
/*  70 */     this.n += 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public double getResult()
/*     */   {
/*  77 */     if (this.n > 0) {
/*  78 */       return this.value;
/*     */     }
/*  80 */     return NaN.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/*  88 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/*  95 */     this.value = 0.0D;
/*  96 */     this.n = 0;
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
/* 117 */     double sumLog = NaN.0D;
/* 118 */     if (test(values, begin, length)) {
/* 119 */       sumLog = 0.0D;
/* 120 */       for (int i = begin; i < begin + length; i++) {
/* 121 */         sumLog += Math.log(values[i]);
/*     */       }
/*     */     }
/* 124 */     return sumLog;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/summary/SumOfLogs.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */