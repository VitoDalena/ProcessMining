/*     */ package org.apache.commons.math.stat.descriptive.rank;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math.stat.descriptive.AbstractUnivariateStatistic;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Percentile
/*     */   extends AbstractUnivariateStatistic
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8091216485095130416L;
/*  73 */   private double quantile = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Percentile()
/*     */   {
/*  80 */     this(50.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Percentile(double p)
/*     */   {
/*  90 */     setQuantile(p);
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
/*     */ 
/*     */   public double evaluate(double[] values, double p)
/*     */   {
/* 120 */     test(values, 0, 0);
/* 121 */     return evaluate(values, 0, values.length, p);
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
/*     */   public double evaluate(double[] values, int start, int length)
/*     */   {
/* 149 */     return evaluate(values, start, length, this.quantile);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double evaluate(double[] values, int begin, int length, double p)
/*     */   {
/* 185 */     test(values, begin, length);
/*     */     
/* 187 */     if ((p > 100.0D) || (p <= 0.0D)) {
/* 188 */       throw new IllegalArgumentException("invalid quantile value: " + p);
/*     */     }
/* 190 */     if (length == 0) {
/* 191 */       return NaN.0D;
/*     */     }
/* 193 */     if (length == 1) {
/* 194 */       return values[begin];
/*     */     }
/* 196 */     double n = length;
/* 197 */     double pos = p * (n + 1.0D) / 100.0D;
/* 198 */     double fpos = Math.floor(pos);
/* 199 */     int intPos = (int)fpos;
/* 200 */     double dif = pos - fpos;
/* 201 */     double[] sorted = new double[length];
/* 202 */     System.arraycopy(values, begin, sorted, 0, length);
/* 203 */     Arrays.sort(sorted);
/*     */     
/* 205 */     if (pos < 1.0D) {
/* 206 */       return sorted[0];
/*     */     }
/* 208 */     if (pos >= n) {
/* 209 */       return sorted[(length - 1)];
/*     */     }
/* 211 */     double lower = sorted[(intPos - 1)];
/* 212 */     double upper = sorted[intPos];
/* 213 */     return lower + dif * (upper - lower);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getQuantile()
/*     */   {
/* 223 */     return this.quantile;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setQuantile(double p)
/*     */   {
/* 235 */     if ((p <= 0.0D) || (p > 100.0D)) {
/* 236 */       throw new IllegalArgumentException("Illegal quantile value: " + p);
/*     */     }
/* 238 */     this.quantile = p;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/rank/Percentile.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */