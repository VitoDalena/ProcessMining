/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ import org.apache.commons.math.DimensionMismatchException;
/*     */ import org.apache.commons.math.linear.RealMatrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedMultivariateSummaryStatistics
/*     */   extends MultivariateSummaryStatistics
/*     */ {
/*     */   private static final long serialVersionUID = 7099834153347155363L;
/*     */   
/*     */   public SynchronizedMultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected)
/*     */   {
/*  48 */     super(k, isCovarianceBiasCorrected);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void addValue(double[] value)
/*     */     throws DimensionMismatchException
/*     */   {
/*  56 */     super.addValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized int getDimension()
/*     */   {
/*  63 */     return super.getDimension();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized long getN()
/*     */   {
/*  70 */     return super.getN();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getSum()
/*     */   {
/*  77 */     return super.getSum();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getSumSq()
/*     */   {
/*  84 */     return super.getSumSq();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getSumLog()
/*     */   {
/*  91 */     return super.getSumLog();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getMean()
/*     */   {
/*  98 */     return super.getMean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getStandardDeviation()
/*     */   {
/* 105 */     return super.getStandardDeviation();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized RealMatrix getCovariance()
/*     */   {
/* 112 */     return super.getCovariance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getMax()
/*     */   {
/* 119 */     return super.getMax();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getMin()
/*     */   {
/* 126 */     return super.getMin();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double[] getGeometricMean()
/*     */   {
/* 133 */     return super.getGeometricMean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 140 */     return super.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 147 */     super.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean equals(Object object)
/*     */   {
/* 154 */     return super.equals(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized int hashCode()
/*     */   {
/* 161 */     return super.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getSumImpl()
/*     */   {
/* 168 */     return super.getSumImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setSumImpl(StorelessUnivariateStatistic[] sumImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 176 */     super.setSumImpl(sumImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getSumsqImpl()
/*     */   {
/* 183 */     return super.getSumsqImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 191 */     super.setSumsqImpl(sumsqImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getMinImpl()
/*     */   {
/* 198 */     return super.getMinImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setMinImpl(StorelessUnivariateStatistic[] minImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 206 */     super.setMinImpl(minImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getMaxImpl()
/*     */   {
/* 213 */     return super.getMaxImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setMaxImpl(StorelessUnivariateStatistic[] maxImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 221 */     super.setMaxImpl(maxImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getSumLogImpl()
/*     */   {
/* 228 */     return super.getSumLogImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 236 */     super.setSumLogImpl(sumLogImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getGeoMeanImpl()
/*     */   {
/* 243 */     return super.getGeoMeanImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 251 */     super.setGeoMeanImpl(geoMeanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic[] getMeanImpl()
/*     */   {
/* 258 */     return super.getMeanImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setMeanImpl(StorelessUnivariateStatistic[] meanImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 266 */     super.setMeanImpl(meanImpl);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/SynchronizedMultivariateSummaryStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */