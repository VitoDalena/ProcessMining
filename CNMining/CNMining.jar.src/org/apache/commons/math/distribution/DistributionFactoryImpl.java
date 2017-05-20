/*     */ package org.apache.commons.math.distribution;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class DistributionFactoryImpl
/*     */   extends DistributionFactory
/*     */ {
/*     */   public ChiSquaredDistribution createChiSquareDistribution(double degreesOfFreedom)
/*     */   {
/*  45 */     return new ChiSquaredDistributionImpl(degreesOfFreedom);
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
/*     */   public GammaDistribution createGammaDistribution(double alpha, double beta)
/*     */   {
/*  58 */     return new GammaDistributionImpl(alpha, beta);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TDistribution createTDistribution(double degreesOfFreedom)
/*     */   {
/*  68 */     return new TDistributionImpl(degreesOfFreedom);
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
/*     */   public FDistribution createFDistribution(double numeratorDegreesOfFreedom, double denominatorDegreesOfFreedom)
/*     */   {
/*  81 */     return new FDistributionImpl(numeratorDegreesOfFreedom, denominatorDegreesOfFreedom);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ExponentialDistribution createExponentialDistribution(double mean)
/*     */   {
/*  92 */     return new ExponentialDistributionImpl(mean);
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
/*     */   public BinomialDistribution createBinomialDistribution(int numberOfTrials, double probabilityOfSuccess)
/*     */   {
/* 105 */     return new BinomialDistributionImpl(numberOfTrials, probabilityOfSuccess);
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
/*     */   public HypergeometricDistribution createHypergeometricDistribution(int populationSize, int numberOfSuccesses, int sampleSize)
/*     */   {
/* 120 */     return new HypergeometricDistributionImpl(populationSize, numberOfSuccesses, sampleSize);
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
/*     */   public NormalDistribution createNormalDistribution(double mean, double sd)
/*     */   {
/* 133 */     return new NormalDistributionImpl(mean, sd);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NormalDistribution createNormalDistribution()
/*     */   {
/* 143 */     return new NormalDistributionImpl();
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
/*     */   public PoissonDistribution createPoissonDistribution(double lambda)
/*     */   {
/* 157 */     return new PoissonDistributionImpl(lambda);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/DistributionFactoryImpl.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */