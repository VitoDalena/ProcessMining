/*     */ package org.apache.commons.math.stat.inference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.distribution.FDistribution;
/*     */ import org.apache.commons.math.distribution.FDistributionImpl;
/*     */ import org.apache.commons.math.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OneWayAnovaImpl
/*     */   implements OneWayAnova
/*     */ {
/*     */   public double anovaFValue(Collection categoryData)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/*  70 */     AnovaStats a = anovaStats(categoryData);
/*  71 */     return a.F;
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
/*     */   public double anovaPValue(Collection categoryData)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/*  86 */     AnovaStats a = anovaStats(categoryData);
/*  87 */     FDistribution fdist = new FDistributionImpl(a.dfbg, a.dfwg);
/*  88 */     return 1.0D - fdist.cumulativeProbability(a.F);
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
/*     */   public boolean anovaTest(Collection categoryData, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 104 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/* 105 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*     */     }
/* 107 */     return anovaPValue(categoryData) < alpha;
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
/*     */   private AnovaStats anovaStats(Collection categoryData)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 125 */     if (categoryData.size() < 2) {
/* 126 */       throw new IllegalArgumentException("ANOVA: two or more categories required");
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 131 */     for (Iterator iterator = categoryData.iterator(); iterator.hasNext();) {
/*     */       double[] array;
/*     */       try {
/* 134 */         array = (double[])iterator.next();
/*     */       } catch (ClassCastException ex) {
/* 136 */         throw new IllegalArgumentException("ANOVA: categoryData contains non-double[] elements.");
/*     */       }
/*     */       
/* 139 */       if (array.length <= 1) {
/* 140 */         throw new IllegalArgumentException("ANOVA: one element of categoryData has fewer than 2 values.");
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 145 */     int dfwg = 0;
/* 146 */     double sswg = 0.0D;
/* 147 */     Sum totsum = new Sum();
/* 148 */     SumOfSquares totsumsq = new SumOfSquares();
/* 149 */     int totnum = 0;
/*     */     
/* 151 */     for (Iterator iterator = categoryData.iterator(); iterator.hasNext();) {
/* 152 */       double[] data = (double[])iterator.next();
/*     */       
/* 154 */       Sum sum = new Sum();
/* 155 */       SumOfSquares sumsq = new SumOfSquares();
/* 156 */       int num = 0;
/*     */       
/* 158 */       for (int i = 0; i < data.length; i++) {
/* 159 */         double val = data[i];
/*     */         
/*     */ 
/* 162 */         num++;
/* 163 */         sum.increment(val);
/* 164 */         sumsq.increment(val);
/*     */         
/*     */ 
/* 167 */         totnum++;
/* 168 */         totsum.increment(val);
/* 169 */         totsumsq.increment(val);
/*     */       }
/* 171 */       dfwg += num - 1;
/* 172 */       double ss = sumsq.getResult() - sum.getResult() * sum.getResult() / num;
/* 173 */       sswg += ss;
/*     */     }
/* 175 */     double sst = totsumsq.getResult() - totsum.getResult() * totsum.getResult() / totnum;
/*     */     
/* 177 */     double ssbg = sst - sswg;
/* 178 */     int dfbg = categoryData.size() - 1;
/* 179 */     double msbg = ssbg / dfbg;
/* 180 */     double mswg = sswg / dfwg;
/* 181 */     double F = msbg / mswg;
/*     */     
/* 183 */     return new AnovaStats(dfbg, dfwg, F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private static class AnovaStats
/*     */   {
/*     */     private int dfbg;
/*     */     
/*     */ 
/*     */     private int dfwg;
/*     */     
/*     */ 
/*     */     private double F;
/*     */     
/*     */ 
/*     */ 
/*     */     AnovaStats(int dfbg, int dfwg, double F)
/*     */     {
/* 202 */       this.dfbg = dfbg;
/* 203 */       this.dfwg = dfwg;
/* 204 */       this.F = F;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/OneWayAnovaImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */