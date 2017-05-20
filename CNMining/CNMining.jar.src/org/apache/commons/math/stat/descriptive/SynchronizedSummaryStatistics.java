/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SynchronizedSummaryStatistics
/*     */   extends SummaryStatistics
/*     */ {
/*     */   private static final long serialVersionUID = 1909861009042253704L;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized StatisticalSummary getSummary()
/*     */   {
/*  48 */     return super.getSummary();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void addValue(double value)
/*     */   {
/*  55 */     super.addValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized long getN()
/*     */   {
/*  62 */     return super.getN();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getSum()
/*     */   {
/*  69 */     return super.getSum();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getSumsq()
/*     */   {
/*  76 */     return super.getSumsq();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getMean()
/*     */   {
/*  83 */     return super.getMean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getStandardDeviation()
/*     */   {
/*  90 */     return super.getStandardDeviation();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getVariance()
/*     */   {
/*  97 */     return super.getVariance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getMax()
/*     */   {
/* 104 */     return super.getMax();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getMin()
/*     */   {
/* 111 */     return super.getMin();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized double getGeometricMean()
/*     */   {
/* 118 */     return super.getGeometricMean();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized String toString()
/*     */   {
/* 125 */     return super.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void clear()
/*     */   {
/* 132 */     super.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized boolean equals(Object object)
/*     */   {
/* 139 */     return super.equals(object);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized int hashCode()
/*     */   {
/* 146 */     return super.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getSumImpl()
/*     */   {
/* 153 */     return super.getSumImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setSumImpl(StorelessUnivariateStatistic sumImpl)
/*     */   {
/* 160 */     super.setSumImpl(sumImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getSumsqImpl()
/*     */   {
/* 167 */     return super.getSumsqImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl)
/*     */   {
/* 174 */     super.setSumsqImpl(sumsqImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getMinImpl()
/*     */   {
/* 181 */     return super.getMinImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setMinImpl(StorelessUnivariateStatistic minImpl)
/*     */   {
/* 188 */     super.setMinImpl(minImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getMaxImpl()
/*     */   {
/* 195 */     return super.getMaxImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setMaxImpl(StorelessUnivariateStatistic maxImpl)
/*     */   {
/* 202 */     super.setMaxImpl(maxImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getSumLogImpl()
/*     */   {
/* 209 */     return super.getSumLogImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl)
/*     */   {
/* 216 */     super.setSumLogImpl(sumLogImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getGeoMeanImpl()
/*     */   {
/* 223 */     return super.getGeoMeanImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl)
/*     */   {
/* 230 */     super.setGeoMeanImpl(geoMeanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getMeanImpl()
/*     */   {
/* 237 */     return super.getMeanImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setMeanImpl(StorelessUnivariateStatistic meanImpl)
/*     */   {
/* 244 */     super.setMeanImpl(meanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized StorelessUnivariateStatistic getVarianceImpl()
/*     */   {
/* 251 */     return super.getVarianceImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public synchronized void setVarianceImpl(StorelessUnivariateStatistic varianceImpl)
/*     */   {
/* 258 */     super.setVarianceImpl(varianceImpl);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/SynchronizedSummaryStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */