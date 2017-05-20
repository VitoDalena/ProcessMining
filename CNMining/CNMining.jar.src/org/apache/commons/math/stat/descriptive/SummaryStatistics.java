/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.apache.commons.discovery.tools.DiscoverClass;
/*     */ import org.apache.commons.math.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.SecondMoment;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Variance;
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
/*     */ public class SummaryStatistics
/*     */   implements StatisticalSummary, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3346512372447011854L;
/*     */   
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static SummaryStatistics newInstance(Class cls)
/*     */     throws InstantiationException, IllegalAccessException
/*     */   {
/*  73 */     return (SummaryStatistics)cls.newInstance();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static SummaryStatistics newInstance()
/*     */   {
/*  83 */     SummaryStatistics instance = null;
/*     */     try {
/*  85 */       DiscoverClass dc = new DiscoverClass();
/*  86 */       instance = (SummaryStatistics)dc.newInstance(SummaryStatistics.class, "org.apache.commons.math.stat.descriptive.SummaryStatisticsImpl");
/*     */     }
/*     */     catch (Throwable t)
/*     */     {
/*  90 */       return new SummaryStatisticsImpl();
/*     */     }
/*  92 */     return instance;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 102 */   protected long n = 0L;
/*     */   
/*     */ 
/* 105 */   protected SecondMoment secondMoment = new SecondMoment();
/*     */   
/*     */ 
/* 108 */   protected Sum sum = new Sum();
/*     */   
/*     */ 
/* 111 */   protected SumOfSquares sumsq = new SumOfSquares();
/*     */   
/*     */ 
/* 114 */   protected Min min = new Min();
/*     */   
/*     */ 
/* 117 */   protected Max max = new Max();
/*     */   
/*     */ 
/* 120 */   protected SumOfLogs sumLog = new SumOfLogs();
/*     */   
/*     */ 
/* 123 */   protected GeometricMean geoMean = new GeometricMean(this.sumLog);
/*     */   
/*     */ 
/* 126 */   protected Mean mean = new Mean();
/*     */   
/*     */ 
/* 129 */   protected Variance variance = new Variance();
/*     */   
/*     */ 
/* 132 */   private StorelessUnivariateStatistic sumImpl = this.sum;
/*     */   
/*     */ 
/* 135 */   private StorelessUnivariateStatistic sumsqImpl = this.sumsq;
/*     */   
/*     */ 
/* 138 */   private StorelessUnivariateStatistic minImpl = this.min;
/*     */   
/*     */ 
/* 141 */   private StorelessUnivariateStatistic maxImpl = this.max;
/*     */   
/*     */ 
/* 144 */   private StorelessUnivariateStatistic sumLogImpl = this.sumLog;
/*     */   
/*     */ 
/* 147 */   private StorelessUnivariateStatistic geoMeanImpl = this.geoMean;
/*     */   
/*     */ 
/* 150 */   private StorelessUnivariateStatistic meanImpl = this.mean;
/*     */   
/*     */ 
/* 153 */   private StorelessUnivariateStatistic varianceImpl = this.variance;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StatisticalSummary getSummary()
/*     */   {
/* 162 */     return new StatisticalSummaryValues(getMean(), getVariance(), getN(), getMax(), getMin(), getSum());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addValue(double value)
/*     */   {
/* 172 */     this.sumImpl.increment(value);
/* 173 */     this.sumsqImpl.increment(value);
/* 174 */     this.minImpl.increment(value);
/* 175 */     this.maxImpl.increment(value);
/* 176 */     this.sumLogImpl.increment(value);
/* 177 */     this.secondMoment.increment(value);
/*     */     
/*     */ 
/* 180 */     if (!(this.meanImpl instanceof Mean)) {
/* 181 */       this.meanImpl.increment(value);
/*     */     }
/* 183 */     if (!(this.varianceImpl instanceof Variance)) {
/* 184 */       this.varianceImpl.increment(value);
/*     */     }
/* 186 */     if (!(this.geoMeanImpl instanceof GeometricMean)) {
/* 187 */       this.geoMeanImpl.increment(value);
/*     */     }
/* 189 */     this.n += 1L;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 197 */     return this.n;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSum()
/*     */   {
/* 205 */     return this.sumImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSumsq()
/*     */   {
/* 216 */     return this.sumsqImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/* 227 */     if (this.mean == this.meanImpl) {
/* 228 */       return new Mean(this.secondMoment).getResult();
/*     */     }
/* 230 */     return this.meanImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStandardDeviation()
/*     */   {
/* 242 */     double stdDev = NaN.0D;
/* 243 */     if (getN() > 0L) {
/* 244 */       if (getN() > 1L) {
/* 245 */         stdDev = Math.sqrt(getVariance());
/*     */       } else {
/* 247 */         stdDev = 0.0D;
/*     */       }
/*     */     }
/* 250 */     return stdDev;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVariance()
/*     */   {
/* 261 */     if (this.varianceImpl == this.variance) {
/* 262 */       return new Variance(this.secondMoment).getResult();
/*     */     }
/* 264 */     return this.varianceImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMax()
/*     */   {
/* 276 */     return this.maxImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMin()
/*     */   {
/* 287 */     return this.minImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGeometricMean()
/*     */   {
/* 298 */     return this.geoMeanImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSumOfLogs()
/*     */   {
/* 310 */     return this.sumLogImpl.getResult();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 321 */     StringBuffer outBuffer = new StringBuffer();
/* 322 */     outBuffer.append("SummaryStatistics:\n");
/* 323 */     outBuffer.append("n: " + getN() + "\n");
/* 324 */     outBuffer.append("min: " + getMin() + "\n");
/* 325 */     outBuffer.append("max: " + getMax() + "\n");
/* 326 */     outBuffer.append("mean: " + getMean() + "\n");
/* 327 */     outBuffer.append("geometric mean: " + getGeometricMean() + "\n");
/* 328 */     outBuffer.append("variance: " + getVariance() + "\n");
/* 329 */     outBuffer.append("sum of squares: " + getSumsq() + "\n");
/* 330 */     outBuffer.append("standard deviation: " + getStandardDeviation() + "\n");
/* 331 */     outBuffer.append("sum of logs: " + getSumOfLogs() + "\n");
/* 332 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 339 */     this.n = 0L;
/* 340 */     this.minImpl.clear();
/* 341 */     this.maxImpl.clear();
/* 342 */     this.sumImpl.clear();
/* 343 */     this.sumLogImpl.clear();
/* 344 */     this.sumsqImpl.clear();
/* 345 */     this.geoMeanImpl.clear();
/* 346 */     this.secondMoment.clear();
/* 347 */     if (this.meanImpl != this.mean) {
/* 348 */       this.meanImpl.clear();
/*     */     }
/* 350 */     if (this.varianceImpl != this.variance) {
/* 351 */       this.varianceImpl.clear();
/*     */     }
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
/* 365 */     if (!(object instanceof SummaryStatistics)) {
/* 366 */       return false;
/*     */     }
/* 368 */     SummaryStatistics stat = (SummaryStatistics)object;
/* 369 */     return (MathUtils.equals(stat.getGeometricMean(), getGeometricMean())) && (MathUtils.equals(stat.getMax(), getMax())) && (MathUtils.equals(stat.getMean(), getMean())) && (MathUtils.equals(stat.getMin(), getMin())) && (MathUtils.equals(stat.getN(), getN())) && (MathUtils.equals(stat.getSum(), getSum())) && (MathUtils.equals(stat.getSumsq(), getSumsq())) && (MathUtils.equals(stat.getVariance(), getVariance()));
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
/*     */   public int hashCode()
/*     */   {
/* 386 */     int result = 31 + MathUtils.hash(getGeometricMean());
/* 387 */     result = result * 31 + MathUtils.hash(getGeometricMean());
/* 388 */     result = result * 31 + MathUtils.hash(getMax());
/* 389 */     result = result * 31 + MathUtils.hash(getMean());
/* 390 */     result = result * 31 + MathUtils.hash(getMin());
/* 391 */     result = result * 31 + MathUtils.hash(getN());
/* 392 */     result = result * 31 + MathUtils.hash(getSum());
/* 393 */     result = result * 31 + MathUtils.hash(getSumsq());
/* 394 */     result = result * 31 + MathUtils.hash(getVariance());
/* 395 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getSumImpl()
/*     */   {
/* 406 */     return this.sumImpl;
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
/*     */   public void setSumImpl(StorelessUnivariateStatistic sumImpl)
/*     */   {
/* 422 */     checkEmpty();
/* 423 */     this.sumImpl = sumImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getSumsqImpl()
/*     */   {
/* 433 */     return this.sumsqImpl;
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
/*     */   public void setSumsqImpl(StorelessUnivariateStatistic sumsqImpl)
/*     */   {
/* 450 */     checkEmpty();
/* 451 */     this.sumsqImpl = sumsqImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getMinImpl()
/*     */   {
/* 461 */     return this.minImpl;
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
/*     */   public void setMinImpl(StorelessUnivariateStatistic minImpl)
/*     */   {
/* 477 */     checkEmpty();
/* 478 */     this.minImpl = minImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getMaxImpl()
/*     */   {
/* 488 */     return this.maxImpl;
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
/*     */   public void setMaxImpl(StorelessUnivariateStatistic maxImpl)
/*     */   {
/* 504 */     checkEmpty();
/* 505 */     this.maxImpl = maxImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getSumLogImpl()
/*     */   {
/* 515 */     return this.sumLogImpl;
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
/*     */   public void setSumLogImpl(StorelessUnivariateStatistic sumLogImpl)
/*     */   {
/* 532 */     checkEmpty();
/* 533 */     this.sumLogImpl = sumLogImpl;
/* 534 */     this.geoMean.setSumLogImpl(sumLogImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getGeoMeanImpl()
/*     */   {
/* 544 */     return this.geoMeanImpl;
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
/*     */   public void setGeoMeanImpl(StorelessUnivariateStatistic geoMeanImpl)
/*     */   {
/* 561 */     checkEmpty();
/* 562 */     this.geoMeanImpl = geoMeanImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getMeanImpl()
/*     */   {
/* 572 */     return this.meanImpl;
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
/*     */   public void setMeanImpl(StorelessUnivariateStatistic meanImpl)
/*     */   {
/* 589 */     checkEmpty();
/* 590 */     this.meanImpl = meanImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StorelessUnivariateStatistic getVarianceImpl()
/*     */   {
/* 600 */     return this.varianceImpl;
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
/*     */   public void setVarianceImpl(StorelessUnivariateStatistic varianceImpl)
/*     */   {
/* 617 */     checkEmpty();
/* 618 */     this.varianceImpl = varianceImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   private void checkEmpty()
/*     */   {
/* 625 */     if (this.n > 0L) {
/* 626 */       throw new IllegalStateException("Implementations must be configured before values are added.");
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/SummaryStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */