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
/*     */ public abstract class AbstractStorelessUnivariateStatistic
/*     */   extends AbstractUnivariateStatistic
/*     */   implements StorelessUnivariateStatistic, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -44915725420072521L;
/*     */   
/*     */   public double evaluate(double[] values)
/*     */   {
/*  58 */     if (values == null) {
/*  59 */       throw new IllegalArgumentException("input value array is null");
/*     */     }
/*  61 */     return evaluate(values, 0, values.length);
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
/*  83 */     if (test(values, begin, length)) {
/*  84 */       clear();
/*  85 */       incrementAll(values, begin, length);
/*     */     }
/*  87 */     return getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void clear();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract double getResult();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public abstract void increment(double paramDouble);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void incrementAll(double[] values)
/*     */   {
/* 116 */     if (values == null) {
/* 117 */       throw new IllegalArgumentException("input values array is null");
/*     */     }
/* 119 */     incrementAll(values, 0, values.length);
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
/*     */   public void incrementAll(double[] values, int begin, int length)
/*     */   {
/* 135 */     if (test(values, begin, length)) {
/* 136 */       int k = begin + length;
/* 137 */       for (int i = begin; i < k; i++) {
/* 138 */         increment(values[i]);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 151 */     if (object == this) {
/* 152 */       return true;
/*     */     }
/* 154 */     if (!(object instanceof AbstractStorelessUnivariateStatistic)) {
/* 155 */       return false;
/*     */     }
/* 157 */     AbstractStorelessUnivariateStatistic stat = (AbstractStorelessUnivariateStatistic)object;
/* 158 */     return (MathUtils.equals(stat.getResult(), getResult())) && (MathUtils.equals(stat.getN(), getN()));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 168 */     return 31 * (31 + MathUtils.hash(getResult())) + MathUtils.hash(getN());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/AbstractStorelessUnivariateStatistic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */