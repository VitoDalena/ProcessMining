/*     */ package org.apache.commons.math.stat;
/*     */ 
/*     */ import org.apache.commons.math.stat.descriptive.UnivariateStatistic;
/*     */ import org.apache.commons.math.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Percentile;
/*     */ import org.apache.commons.math.stat.descriptive.summary.Product;
/*     */ import org.apache.commons.math.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math.stat.descriptive.summary.SumOfLogs;
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
/*     */ public final class StatUtils
/*     */ {
/*  40 */   private static UnivariateStatistic sum = new Sum();
/*     */   
/*     */ 
/*  43 */   private static UnivariateStatistic sumSq = new SumOfSquares();
/*     */   
/*     */ 
/*  46 */   private static UnivariateStatistic prod = new Product();
/*     */   
/*     */ 
/*  49 */   private static UnivariateStatistic sumLog = new SumOfLogs();
/*     */   
/*     */ 
/*  52 */   private static UnivariateStatistic min = new Min();
/*     */   
/*     */ 
/*  55 */   private static UnivariateStatistic max = new Max();
/*     */   
/*     */ 
/*  58 */   private static UnivariateStatistic mean = new Mean();
/*     */   
/*     */ 
/*  61 */   private static Variance variance = new Variance();
/*     */   
/*     */ 
/*  64 */   private static Percentile percentile = new Percentile();
/*     */   
/*     */ 
/*  67 */   private static GeometricMean geometricMean = new GeometricMean();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double sum(double[] values)
/*     */   {
/*  88 */     return sum.evaluate(values);
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
/*     */   public static double sum(double[] values, int begin, int length)
/*     */   {
/* 107 */     return sum.evaluate(values, begin, length);
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
/*     */   public static double sumSq(double[] values)
/*     */   {
/* 122 */     return sumSq.evaluate(values);
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
/*     */   public static double sumSq(double[] values, int begin, int length)
/*     */   {
/* 141 */     return sumSq.evaluate(values, begin, length);
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
/*     */   public static double product(double[] values)
/*     */   {
/* 155 */     return prod.evaluate(values);
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
/*     */   public static double product(double[] values, int begin, int length)
/*     */   {
/* 174 */     return prod.evaluate(values, begin, length);
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
/*     */   public static double sumLog(double[] values)
/*     */   {
/* 192 */     return sumLog.evaluate(values);
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
/*     */   public static double sumLog(double[] values, int begin, int length)
/*     */   {
/* 215 */     return sumLog.evaluate(values, begin, length);
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
/*     */   public static double mean(double[] values)
/*     */   {
/* 232 */     return mean.evaluate(values);
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
/*     */   public static double mean(double[] values, int begin, int length)
/*     */   {
/* 254 */     return mean.evaluate(values, begin, length);
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
/*     */   public static double geometricMean(double[] values)
/*     */   {
/* 271 */     return geometricMean.evaluate(values);
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
/*     */   public static double geometricMean(double[] values, int begin, int length)
/*     */   {
/* 293 */     return geometricMean.evaluate(values, begin, length);
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
/*     */   public static double variance(double[] values)
/*     */   {
/* 313 */     return variance.evaluate(values);
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
/*     */   public static double variance(double[] values, int begin, int length)
/*     */   {
/* 338 */     return variance.evaluate(values, begin, length);
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
/*     */   public static double variance(double[] values, double mean, int begin, int length)
/*     */   {
/* 369 */     return variance.evaluate(values, mean, begin, length);
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
/*     */   public static double variance(double[] values, double mean)
/*     */   {
/* 395 */     return variance.evaluate(values, mean);
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
/*     */   public static double max(double[] values)
/*     */   {
/* 416 */     return max.evaluate(values);
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
/*     */   public static double max(double[] values, int begin, int length)
/*     */   {
/* 443 */     return max.evaluate(values, begin, length);
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
/*     */   public static double min(double[] values)
/*     */   {
/* 464 */     return min.evaluate(values);
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
/*     */   public static double min(double[] values, int begin, int length)
/*     */   {
/* 491 */     return min.evaluate(values, begin, length);
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
/*     */   public static double percentile(double[] values, double p)
/*     */   {
/* 518 */     return percentile.evaluate(values, p);
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
/*     */   public static double percentile(double[] values, int begin, int length, double p)
/*     */   {
/* 550 */     return percentile.evaluate(values, begin, length, p);
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
/*     */   public static double sumDifference(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 565 */     int n = sample1.length;
/* 566 */     if ((n != sample2.length) || (n < 1)) {
/* 567 */       throw new IllegalArgumentException("Input arrays must have the same (positive) length.");
/*     */     }
/*     */     
/* 570 */     double result = 0.0D;
/* 571 */     for (int i = 0; i < n; i++) {
/* 572 */       result += sample1[i] - sample2[i];
/*     */     }
/* 574 */     return result;
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
/*     */   public static double meanDifference(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 589 */     return sumDifference(sample1, sample2) / sample1.length;
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
/*     */   public static double varianceDifference(double[] sample1, double[] sample2, double meanDifference)
/*     */     throws IllegalArgumentException
/*     */   {
/* 606 */     double sum1 = 0.0D;
/* 607 */     double sum2 = 0.0D;
/* 608 */     double diff = 0.0D;
/* 609 */     int n = sample1.length;
/* 610 */     if ((n < 2) || (n != sample2.length)) {
/* 611 */       throw new IllegalArgumentException("Input array lengths must be equal and at least 2.");
/*     */     }
/* 613 */     for (int i = 0; i < n; i++) {
/* 614 */       diff = sample1[i] - sample2[i];
/* 615 */       sum1 += (diff - meanDifference) * (diff - meanDifference);
/* 616 */       sum2 += diff - meanDifference;
/*     */     }
/* 618 */     return (sum1 - sum2 * sum2 / n) / (n - 1);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/StatUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */