/*      */ package org.apache.commons.math.stat.inference;
/*      */ 
/*      */ import org.apache.commons.math.MathException;
/*      */ import org.apache.commons.math.distribution.DistributionFactory;
/*      */ import org.apache.commons.math.distribution.TDistribution;
/*      */ import org.apache.commons.math.distribution.TDistributionImpl;
/*      */ import org.apache.commons.math.stat.StatUtils;
/*      */ import org.apache.commons.math.stat.descriptive.StatisticalSummary;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class TTestImpl
/*      */   implements TTest
/*      */ {
/*      */   private TDistribution distribution;
/*      */   
/*      */   public TTestImpl()
/*      */   {
/*   43 */     this(new TDistributionImpl(1.0D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TTestImpl(TDistribution t)
/*      */   {
/*   54 */     setDistribution(t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double pairedT(double[] sample1, double[] sample2)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*   79 */     if ((sample1 == null) || (sample2 == null) || (Math.min(sample1.length, sample2.length) < 2))
/*      */     {
/*   81 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*   83 */     double meanDifference = StatUtils.meanDifference(sample1, sample2);
/*   84 */     return t(meanDifference, 0.0D, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double pairedTTest(double[] sample1, double[] sample2)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  124 */     double meanDifference = StatUtils.meanDifference(sample1, sample2);
/*  125 */     return tTest(meanDifference, 0.0D, StatUtils.varianceDifference(sample1, sample2, meanDifference), sample1.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean pairedTTest(double[] sample1, double[] sample2, double alpha)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  164 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/*  165 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*      */     }
/*  167 */     return pairedTTest(sample1, sample2) < alpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double t(double mu, double[] observed)
/*      */     throws IllegalArgumentException
/*      */   {
/*  187 */     if ((observed == null) || (observed.length < 2)) {
/*  188 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  190 */     return t(StatUtils.mean(observed), mu, StatUtils.variance(observed), observed.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double t(double mu, StatisticalSummary sampleStats)
/*      */     throws IllegalArgumentException
/*      */   {
/*  212 */     if ((sampleStats == null) || (sampleStats.getN() < 2L)) {
/*  213 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  215 */     return t(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double homoscedasticT(double[] sample1, double[] sample2)
/*      */     throws IllegalArgumentException
/*      */   {
/*  254 */     if ((sample1 == null) || (sample2 == null) || (Math.min(sample1.length, sample2.length) < 2))
/*      */     {
/*  256 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  258 */     return homoscedasticT(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double t(double[] sample1, double[] sample2)
/*      */     throws IllegalArgumentException
/*      */   {
/*  293 */     if ((sample1 == null) || (sample2 == null) || (Math.min(sample1.length, sample2.length) < 2))
/*      */     {
/*  295 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  297 */     return t(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*      */     throws IllegalArgumentException
/*      */   {
/*  336 */     if ((sampleStats1 == null) || (sampleStats2 == null) || (Math.min(sampleStats1.getN(), sampleStats2.getN()) < 2L))
/*      */     {
/*      */ 
/*  339 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  341 */     return t(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*      */     throws IllegalArgumentException
/*      */   {
/*  384 */     if ((sampleStats1 == null) || (sampleStats2 == null) || (Math.min(sampleStats1.getN(), sampleStats2.getN()) < 2L))
/*      */     {
/*      */ 
/*  387 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  389 */     return homoscedasticT(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double tTest(double mu, double[] sample)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  422 */     if ((sample == null) || (sample.length < 2)) {
/*  423 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  425 */     return tTest(StatUtils.mean(sample), mu, StatUtils.variance(sample), sample.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean tTest(double mu, double[] sample, double alpha)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  466 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/*  467 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*      */     }
/*  469 */     return tTest(mu, sample) < alpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double tTest(double mu, StatisticalSummary sampleStats)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  502 */     if ((sampleStats == null) || (sampleStats.getN() < 2L)) {
/*  503 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  505 */     return tTest(sampleStats.getMean(), mu, sampleStats.getVariance(), sampleStats.getN());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean tTest(double mu, StatisticalSummary sampleStats, double alpha)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  548 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/*  549 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*      */     }
/*  551 */     return tTest(mu, sampleStats) < alpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double tTest(double[] sample1, double[] sample2)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  592 */     if ((sample1 == null) || (sample2 == null) || (Math.min(sample1.length, sample2.length) < 2))
/*      */     {
/*  594 */       throw new IllegalArgumentException("insufficient data");
/*      */     }
/*  596 */     return tTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double homoscedasticTTest(double[] sample1, double[] sample2)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  636 */     if ((sample1 == null) || (sample2 == null) || (Math.min(sample1.length, sample2.length) < 2))
/*      */     {
/*  638 */       throw new IllegalArgumentException("insufficient data");
/*      */     }
/*  640 */     return homoscedasticTTest(StatUtils.mean(sample1), StatUtils.mean(sample2), StatUtils.variance(sample1), StatUtils.variance(sample2), sample1.length, sample2.length);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean tTest(double[] sample1, double[] sample2, double alpha)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  701 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/*  702 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*      */     }
/*  704 */     return tTest(sample1, sample2) < alpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  761 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/*  762 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*      */     }
/*  764 */     return homoscedasticTTest(sample1, sample2) < alpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  803 */     if ((sampleStats1 == null) || (sampleStats2 == null) || (Math.min(sampleStats1.getN(), sampleStats2.getN()) < 2L))
/*      */     {
/*  805 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  807 */     return tTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  848 */     if ((sampleStats1 == null) || (sampleStats2 == null) || (Math.min(sampleStats1.getN(), sampleStats2.getN()) < 2L))
/*      */     {
/*  850 */       throw new IllegalArgumentException("insufficient data for t statistic");
/*      */     }
/*  852 */     return homoscedasticTTest(sampleStats1.getMean(), sampleStats2.getMean(), sampleStats1.getVariance(), sampleStats2.getVariance(), sampleStats1.getN(), sampleStats2.getN());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha)
/*      */     throws IllegalArgumentException, MathException
/*      */   {
/*  914 */     if ((alpha <= 0.0D) || (alpha > 0.5D)) {
/*  915 */       throw new IllegalArgumentException("bad significance level: " + alpha);
/*      */     }
/*  917 */     return tTest(sampleStats1, sampleStats2) < alpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected DistributionFactory getDistributionFactory()
/*      */   {
/*  928 */     return DistributionFactory.newInstance();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double df(double v1, double v2, double n1, double n2)
/*      */   {
/*  941 */     return (v1 / n1 + v2 / n2) * (v1 / n1 + v2 / n2) / (v1 * v1 / (n1 * n1 * (n1 - 1.0D)) + v2 * v2 / (n2 * n2 * (n2 - 1.0D)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double t(double m, double mu, double v, double n)
/*      */   {
/*  956 */     return (m - mu) / Math.sqrt(v / n);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double t(double m1, double m2, double v1, double v2, double n1, double n2)
/*      */   {
/*  974 */     return (m1 - m2) / Math.sqrt(v1 / n1 + v2 / n2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double homoscedasticT(double m1, double m2, double v1, double v2, double n1, double n2)
/*      */   {
/*  991 */     double pooledVariance = ((n1 - 1.0D) * v1 + (n2 - 1.0D) * v2) / (n1 + n2 - 2.0D);
/*  992 */     return (m1 - m2) / Math.sqrt(pooledVariance * (1.0D / n1 + 1.0D / n2));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double tTest(double m, double mu, double v, double n)
/*      */     throws MathException
/*      */   {
/* 1007 */     double t = Math.abs(t(m, mu, v, n));
/* 1008 */     this.distribution.setDegreesOfFreedom(n - 1.0D);
/* 1009 */     return 1.0D - this.distribution.cumulativeProbability(-t, t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double tTest(double m1, double m2, double v1, double v2, double n1, double n2)
/*      */     throws MathException
/*      */   {
/* 1030 */     double t = Math.abs(t(m1, m2, v1, v2, n1, n2));
/* 1031 */     double degreesOfFreedom = 0.0D;
/* 1032 */     degreesOfFreedom = df(v1, v2, n1, n2);
/* 1033 */     this.distribution.setDegreesOfFreedom(degreesOfFreedom);
/* 1034 */     return 1.0D - this.distribution.cumulativeProbability(-t, t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double homoscedasticTTest(double m1, double m2, double v1, double v2, double n1, double n2)
/*      */     throws MathException
/*      */   {
/* 1055 */     double t = Math.abs(homoscedasticT(m1, m2, v1, v2, n1, n2));
/* 1056 */     double degreesOfFreedom = n1 + n2 - 2.0D;
/* 1057 */     this.distribution.setDegreesOfFreedom(degreesOfFreedom);
/* 1058 */     return 1.0D - this.distribution.cumulativeProbability(-t, t);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDistribution(TDistribution value)
/*      */   {
/* 1067 */     this.distribution = value;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/TTestImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */