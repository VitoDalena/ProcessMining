/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.math.DimensionMismatchException;
/*     */ import org.apache.commons.math.linear.RealMatrix;
/*     */ import org.apache.commons.math.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.VectorialCovariance;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math.stat.descriptive.summary.SumOfLogs;
/*     */ import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultivariateSummaryStatistics
/*     */   implements StatisticalMultivariateSummary, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2271900808994826718L;
/*     */   private int k;
/*     */   
/*     */   public MultivariateSummaryStatistics(int k, boolean isCovarianceBiasCorrected)
/*     */   {
/*  81 */     this.k = k;
/*     */     
/*  83 */     this.sumImpl = new StorelessUnivariateStatistic[k];
/*  84 */     this.sumSqImpl = new StorelessUnivariateStatistic[k];
/*  85 */     this.minImpl = new StorelessUnivariateStatistic[k];
/*  86 */     this.maxImpl = new StorelessUnivariateStatistic[k];
/*  87 */     this.sumLogImpl = new StorelessUnivariateStatistic[k];
/*  88 */     this.geoMeanImpl = new StorelessUnivariateStatistic[k];
/*  89 */     this.meanImpl = new StorelessUnivariateStatistic[k];
/*     */     
/*  91 */     for (int i = 0; i < k; i++) {
/*  92 */       this.sumImpl[i] = new Sum();
/*  93 */       this.sumSqImpl[i] = new SumOfSquares();
/*  94 */       this.minImpl[i] = new Min();
/*  95 */       this.maxImpl[i] = new Max();
/*  96 */       this.sumLogImpl[i] = new SumOfLogs();
/*  97 */       this.geoMeanImpl[i] = new GeometricMean();
/*  98 */       this.meanImpl[i] = new Mean();
/*     */     }
/*     */     
/* 101 */     this.covarianceImpl = new VectorialCovariance(k, isCovarianceBiasCorrected);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 110 */   private long n = 0L;
/*     */   
/*     */ 
/*     */ 
/*     */   private StorelessUnivariateStatistic[] sumImpl;
/*     */   
/*     */ 
/*     */ 
/*     */   private StorelessUnivariateStatistic[] sumSqImpl;
/*     */   
/*     */ 
/*     */ 
/*     */   private StorelessUnivariateStatistic[] minImpl;
/*     */   
/*     */ 
/*     */ 
/*     */   private StorelessUnivariateStatistic[] maxImpl;
/*     */   
/*     */ 
/*     */   private StorelessUnivariateStatistic[] sumLogImpl;
/*     */   
/*     */ 
/*     */   private StorelessUnivariateStatistic[] geoMeanImpl;
/*     */   
/*     */ 
/*     */   private StorelessUnivariateStatistic[] meanImpl;
/*     */   
/*     */ 
/*     */   private VectorialCovariance covarianceImpl;
/*     */   
/*     */ 
/*     */ 
/*     */   public void addValue(double[] value)
/*     */     throws DimensionMismatchException
/*     */   {
/* 145 */     checkDimension(value.length);
/* 146 */     for (int i = 0; i < this.k; i++) {
/* 147 */       double v = value[i];
/* 148 */       this.sumImpl[i].increment(v);
/* 149 */       this.sumSqImpl[i].increment(v);
/* 150 */       this.minImpl[i].increment(v);
/* 151 */       this.maxImpl[i].increment(v);
/* 152 */       this.sumLogImpl[i].increment(v);
/* 153 */       this.geoMeanImpl[i].increment(v);
/* 154 */       this.meanImpl[i].increment(v);
/*     */     }
/* 156 */     this.covarianceImpl.increment(value);
/* 157 */     this.n += 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDimension()
/*     */   {
/* 165 */     return this.k;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 173 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double[] getResults(StorelessUnivariateStatistic[] stats)
/*     */   {
/* 182 */     double[] results = new double[stats.length];
/* 183 */     for (int i = 0; i < results.length; i++) {
/* 184 */       results[i] = stats[i].getResult();
/*     */     }
/* 186 */     return results;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getSum()
/*     */   {
/* 197 */     return getResults(this.sumImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getSumSq()
/*     */   {
/* 208 */     return getResults(this.sumSqImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getSumLog()
/*     */   {
/* 219 */     return getResults(this.sumLogImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getMean()
/*     */   {
/* 230 */     return getResults(this.meanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getStandardDeviation()
/*     */   {
/* 241 */     double[] stdDev = new double[this.k];
/* 242 */     if (getN() < 1L) {
/* 243 */       Arrays.fill(stdDev, NaN.0D);
/* 244 */     } else if (getN() < 2L) {
/* 245 */       Arrays.fill(stdDev, 0.0D);
/*     */     } else {
/* 247 */       RealMatrix matrix = this.covarianceImpl.getResult();
/* 248 */       for (int i = 0; i < this.k; i++) {
/* 249 */         stdDev[i] = Math.sqrt(matrix.getEntry(i, i));
/*     */       }
/*     */     }
/* 252 */     return stdDev;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RealMatrix getCovariance()
/*     */   {
/* 261 */     return this.covarianceImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getMax()
/*     */   {
/* 272 */     return getResults(this.maxImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getMin()
/*     */   {
/* 283 */     return getResults(this.minImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getGeometricMean()
/*     */   {
/* 294 */     return getResults(this.geoMeanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 304 */     StringBuffer outBuffer = new StringBuffer();
/* 305 */     outBuffer.append("MultivariateSummaryStatistics:\n");
/* 306 */     outBuffer.append("n: " + getN() + "\n");
/* 307 */     append(outBuffer, getMin(), "min: ", ", ", "\n");
/* 308 */     append(outBuffer, getMax(), "max: ", ", ", "\n");
/* 309 */     append(outBuffer, getMean(), "mean: ", ", ", "\n");
/* 310 */     append(outBuffer, getGeometricMean(), "geometric mean: ", ", ", "\n");
/* 311 */     append(outBuffer, getSumSq(), "sum of squares: ", ", ", "\n");
/* 312 */     append(outBuffer, getSumLog(), "sum of logarithms: ", ", ", "\n");
/* 313 */     append(outBuffer, getStandardDeviation(), "standard deviation: ", ", ", "\n");
/* 314 */     outBuffer.append("covariance: " + getCovariance().toString() + "\n");
/* 315 */     return outBuffer.toString();
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
/*     */   private void append(StringBuffer buffer, double[] data, String prefix, String separator, String suffix)
/*     */   {
/* 328 */     buffer.append(prefix);
/* 329 */     for (int i = 0; i < data.length; i++) {
/* 330 */       if (i > 0) {
/* 331 */         buffer.append(separator);
/*     */       }
/* 333 */       buffer.append(data[i]);
/*     */     }
/* 335 */     buffer.append(suffix);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 342 */     this.n = 0L;
/* 343 */     for (int i = 0; i < this.k; i++) {
/* 344 */       this.minImpl[i].clear();
/* 345 */       this.maxImpl[i].clear();
/* 346 */       this.sumImpl[i].clear();
/* 347 */       this.sumLogImpl[i].clear();
/* 348 */       this.sumSqImpl[i].clear();
/* 349 */       this.geoMeanImpl[i].clear();
/* 350 */       this.meanImpl[i].clear();
/*     */     }
/* 352 */     this.covarianceImpl.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 362 */     if (object == this) {
/* 363 */       return true;
/*     */     }
/* 365 */     if (!(object instanceof MultivariateSummaryStatistics)) {
/* 366 */       return false;
/*     */     }
/* 368 */     MultivariateSummaryStatistics stat = (MultivariateSummaryStatistics)object;
/* 369 */     return (MathUtils.equals(stat.getGeometricMean(), getGeometricMean())) && (MathUtils.equals(stat.getMax(), getMax())) && (MathUtils.equals(stat.getMean(), getMean())) && (MathUtils.equals(stat.getMin(), getMin())) && (MathUtils.equals(stat.getN(), getN())) && (MathUtils.equals(stat.getSum(), getSum())) && (MathUtils.equals(stat.getSumSq(), getSumSq())) && (MathUtils.equals(stat.getSumLog(), getSumLog())) && (stat.getCovariance().equals(getCovariance()));
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
/*     */   public int hashCode()
/*     */   {
/* 387 */     int result = 31 + MathUtils.hash(getGeometricMean());
/* 388 */     result = result * 31 + MathUtils.hash(getGeometricMean());
/* 389 */     result = result * 31 + MathUtils.hash(getMax());
/* 390 */     result = result * 31 + MathUtils.hash(getMean());
/* 391 */     result = result * 31 + MathUtils.hash(getMin());
/* 392 */     result = result * 31 + MathUtils.hash(getN());
/* 393 */     result = result * 31 + MathUtils.hash(getSum());
/* 394 */     result = result * 31 + MathUtils.hash(getSumSq());
/* 395 */     result = result * 31 + MathUtils.hash(getSumLog());
/* 396 */     result = result * 31 + getCovariance().hashCode();
/* 397 */     return result;
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
/*     */   private void setImpl(StorelessUnivariateStatistic[] newImpl, StorelessUnivariateStatistic[] oldImpl)
/*     */     throws DimensionMismatchException, IllegalStateException
/*     */   {
/* 413 */     checkEmpty();
/* 414 */     checkDimension(newImpl.length);
/* 415 */     System.arraycopy(newImpl, 0, oldImpl, 0, newImpl.length);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getSumImpl()
/*     */   {
/* 424 */     return (StorelessUnivariateStatistic[])this.sumImpl.clone();
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
/*     */   public void setSumImpl(StorelessUnivariateStatistic[] sumImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 442 */     setImpl(sumImpl, this.sumImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getSumsqImpl()
/*     */   {
/* 451 */     return (StorelessUnivariateStatistic[])this.sumSqImpl.clone();
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
/*     */   public void setSumsqImpl(StorelessUnivariateStatistic[] sumsqImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 469 */     setImpl(sumsqImpl, this.sumSqImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getMinImpl()
/*     */   {
/* 478 */     return (StorelessUnivariateStatistic[])this.minImpl.clone();
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
/*     */   public void setMinImpl(StorelessUnivariateStatistic[] minImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 496 */     setImpl(minImpl, this.minImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getMaxImpl()
/*     */   {
/* 505 */     return (StorelessUnivariateStatistic[])this.maxImpl.clone();
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
/*     */   public void setMaxImpl(StorelessUnivariateStatistic[] maxImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 523 */     setImpl(maxImpl, this.maxImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getSumLogImpl()
/*     */   {
/* 532 */     return (StorelessUnivariateStatistic[])this.sumLogImpl.clone();
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
/*     */   public void setSumLogImpl(StorelessUnivariateStatistic[] sumLogImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 550 */     setImpl(sumLogImpl, this.sumLogImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getGeoMeanImpl()
/*     */   {
/* 559 */     return (StorelessUnivariateStatistic[])this.geoMeanImpl.clone();
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
/*     */   public void setGeoMeanImpl(StorelessUnivariateStatistic[] geoMeanImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 577 */     setImpl(geoMeanImpl, this.geoMeanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic[] getMeanImpl()
/*     */   {
/* 586 */     return (StorelessUnivariateStatistic[])this.meanImpl.clone();
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
/*     */   public void setMeanImpl(StorelessUnivariateStatistic[] meanImpl)
/*     */     throws DimensionMismatchException
/*     */   {
/* 604 */     setImpl(meanImpl, this.meanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkEmpty()
/*     */   {
/* 611 */     if (this.n > 0L) {
/* 612 */       throw new IllegalStateException("Implementations must be configured before values are added.");
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void checkDimension(int dimension)
/*     */     throws DimensionMismatchException
/*     */   {
/* 624 */     if (dimension != this.k) {
/* 625 */       throw new DimensionMismatchException(dimension, this.k);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/MultivariateSummaryStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */