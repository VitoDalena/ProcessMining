/*     */ package org.apache.commons.math.stat.inference;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import org.apache.commons.math.MathException;
/*     */ import org.apache.commons.math.stat.descriptive.StatisticalSummary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TestUtils
/*     */ {
/*  39 */   private static TTest tTest = new TTestImpl();
/*     */   
/*     */ 
/*  42 */   private static ChiSquareTest chiSquareTest = new ChiSquareTestImpl();
/*     */   
/*     */ 
/*     */ 
/*  46 */   private static UnknownDistributionChiSquareTest unknownDistributionChiSquareTest = new ChiSquareTestImpl();
/*     */   
/*     */ 
/*     */ 
/*  50 */   private static OneWayAnova oneWayAnova = new OneWayAnovaImpl();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setChiSquareTest(TTest tTest)
/*     */   {
/*  60 */     tTest = tTest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static TTest getTTest()
/*     */   {
/*  69 */     return tTest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setChiSquareTest(ChiSquareTest chiSquareTest)
/*     */   {
/*  79 */     chiSquareTest = chiSquareTest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static ChiSquareTest getChiSquareTest()
/*     */   {
/*  88 */     return chiSquareTest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setUnknownDistributionChiSquareTest(UnknownDistributionChiSquareTest unknownDistributionChiSquareTest)
/*     */   {
/*  98 */     unknownDistributionChiSquareTest = unknownDistributionChiSquareTest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static UnknownDistributionChiSquareTest getUnknownDistributionChiSquareTest()
/*     */   {
/* 107 */     return unknownDistributionChiSquareTest;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static void setOneWayAnova(OneWayAnova oneWayAnova)
/*     */   {
/* 117 */     oneWayAnova = oneWayAnova;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static OneWayAnova getOneWayAnova()
/*     */   {
/* 127 */     return oneWayAnova;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double homoscedasticT(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 136 */     return tTest.homoscedasticT(sample1, sample2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double homoscedasticT(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 145 */     return tTest.homoscedasticT(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean homoscedasticTTest(double[] sample1, double[] sample2, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 154 */     return tTest.homoscedasticTTest(sample1, sample2, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double homoscedasticTTest(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 162 */     return tTest.homoscedasticTTest(sample1, sample2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double homoscedasticTTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 171 */     return tTest.homoscedasticTTest(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double pairedT(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 179 */     return tTest.pairedT(sample1, sample2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean pairedTTest(double[] sample1, double[] sample2, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 188 */     return tTest.pairedTTest(sample1, sample2, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double pairedTTest(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 196 */     return tTest.pairedTTest(sample1, sample2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double t(double mu, double[] observed)
/*     */     throws IllegalArgumentException
/*     */   {
/* 204 */     return tTest.t(mu, observed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double t(double mu, StatisticalSummary sampleStats)
/*     */     throws IllegalArgumentException
/*     */   {
/* 212 */     return tTest.t(mu, sampleStats);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double t(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 220 */     return tTest.t(sample1, sample2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double t(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 229 */     return tTest.t(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean tTest(double mu, double[] sample, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 237 */     return tTest.tTest(mu, sample, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double tTest(double mu, double[] sample)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 245 */     return tTest.tTest(mu, sample);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean tTest(double mu, StatisticalSummary sampleStats, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 254 */     return tTest.tTest(mu, sampleStats, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double tTest(double mu, StatisticalSummary sampleStats)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 262 */     return tTest.tTest(mu, sampleStats);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean tTest(double[] sample1, double[] sample2, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 270 */     return tTest.tTest(sample1, sample2, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double tTest(double[] sample1, double[] sample2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 278 */     return tTest.tTest(sample1, sample2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 287 */     return tTest.tTest(sampleStats1, sampleStats2, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double tTest(StatisticalSummary sampleStats1, StatisticalSummary sampleStats2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 296 */     return tTest.tTest(sampleStats1, sampleStats2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double chiSquare(double[] expected, long[] observed)
/*     */     throws IllegalArgumentException
/*     */   {
/* 304 */     return chiSquareTest.chiSquare(expected, observed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double chiSquare(long[][] counts)
/*     */     throws IllegalArgumentException
/*     */   {
/* 312 */     return chiSquareTest.chiSquare(counts);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean chiSquareTest(double[] expected, long[] observed, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 321 */     return chiSquareTest.chiSquareTest(expected, observed, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double chiSquareTest(double[] expected, long[] observed)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 329 */     return chiSquareTest.chiSquareTest(expected, observed);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static boolean chiSquareTest(long[][] counts, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 337 */     return chiSquareTest.chiSquareTest(counts, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public static double chiSquareTest(long[][] counts)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 345 */     return chiSquareTest.chiSquareTest(counts);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double chiSquareDataSetsComparison(long[] observed1, long[] observed2)
/*     */     throws IllegalArgumentException
/*     */   {
/* 355 */     return unknownDistributionChiSquareTest.chiSquareDataSetsComparison(observed1, observed2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double chiSquareTestDataSetsComparison(long[] observed1, long[] observed2)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 365 */     return unknownDistributionChiSquareTest.chiSquareTestDataSetsComparison(observed1, observed2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean chiSquareTestDataSetsComparison(long[] observed1, long[] observed2, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 377 */     return unknownDistributionChiSquareTest.chiSquareTestDataSetsComparison(observed1, observed2, alpha);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double oneWayAnovaFValue(Collection categoryData)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 387 */     return oneWayAnova.anovaFValue(categoryData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static double oneWayAnovaPValue(Collection categoryData)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 397 */     return oneWayAnova.anovaPValue(categoryData);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static boolean oneWayAnovaTest(Collection categoryData, double alpha)
/*     */     throws IllegalArgumentException, MathException
/*     */   {
/* 407 */     return oneWayAnova.anovaTest(categoryData, alpha);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/TestUtils.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */