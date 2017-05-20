/*     */ package org.apache.commons.math.stat.descriptive;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import org.apache.commons.discovery.tools.DiscoverClass;
/*     */ import org.apache.commons.math.stat.descriptive.moment.GeometricMean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Kurtosis;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Mean;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Skewness;
/*     */ import org.apache.commons.math.stat.descriptive.moment.Variance;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Max;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Min;
/*     */ import org.apache.commons.math.stat.descriptive.rank.Percentile;
/*     */ import org.apache.commons.math.stat.descriptive.summary.Sum;
/*     */ import org.apache.commons.math.stat.descriptive.summary.SumOfSquares;
/*     */ import org.apache.commons.math.util.ResizableDoubleArray;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DescriptiveStatistics
/*     */   implements StatisticalSummary, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2734185686570407433L;
/*  62 */   protected int windowSize = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  67 */   protected ResizableDoubleArray eDA = new ResizableDoubleArray();
/*     */   
/*     */ 
/*  70 */   private UnivariateStatistic meanImpl = new Mean();
/*     */   
/*     */ 
/*  73 */   private UnivariateStatistic geometricMeanImpl = new GeometricMean();
/*     */   
/*     */ 
/*  76 */   private UnivariateStatistic kurtosisImpl = new Kurtosis();
/*     */   
/*     */ 
/*  79 */   private UnivariateStatistic maxImpl = new Max();
/*     */   
/*     */ 
/*  82 */   private UnivariateStatistic minImpl = new Min();
/*     */   
/*     */ 
/*  85 */   private UnivariateStatistic percentileImpl = new Percentile();
/*     */   
/*     */ 
/*  88 */   private UnivariateStatistic skewnessImpl = new Skewness();
/*     */   
/*     */ 
/*  91 */   private UnivariateStatistic varianceImpl = new Variance();
/*     */   
/*     */ 
/*  94 */   private UnivariateStatistic sumsqImpl = new SumOfSquares();
/*     */   
/*     */ 
/*  97 */   private UnivariateStatistic sumImpl = new Sum();
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int INFINITE_WINDOW = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public DescriptiveStatistics() {}
/*     */   
/*     */ 
/*     */ 
/*     */   public DescriptiveStatistics(int window)
/*     */   {
/* 112 */     setWindowSize(window);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static DescriptiveStatistics newInstance(Class cls)
/*     */     throws InstantiationException, IllegalAccessException
/*     */   {
/* 127 */     return (DescriptiveStatistics)cls.newInstance();
/*     */   }
/*     */   
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public static DescriptiveStatistics newInstance()
/*     */   {
/* 136 */     DescriptiveStatistics factory = null;
/*     */     try {
/* 138 */       DiscoverClass dc = new DiscoverClass();
/* 139 */       factory = (DescriptiveStatistics)dc.newInstance(DescriptiveStatistics.class, "org.apache.commons.math.stat.descriptive.DescriptiveStatisticsImpl");
/*     */     }
/*     */     catch (Throwable t)
/*     */     {
/* 143 */       return new DescriptiveStatisticsImpl();
/*     */     }
/* 145 */     return factory;
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
/*     */   public void addValue(double v)
/*     */   {
/* 164 */     if (this.windowSize != -1) {
/* 165 */       if (getN() == this.windowSize) {
/* 166 */         this.eDA.addElementRolling(v);
/* 167 */       } else if (getN() < this.windowSize) {
/* 168 */         this.eDA.addElement(v);
/*     */       }
/*     */     } else {
/* 171 */       this.eDA.addElement(v);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMean()
/*     */   {
/* 181 */     return apply(this.meanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getGeometricMean()
/*     */   {
/* 191 */     return apply(this.geometricMeanImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getVariance()
/*     */   {
/* 200 */     return apply(this.varianceImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStandardDeviation()
/*     */   {
/* 209 */     double stdDev = NaN.0D;
/* 210 */     if (getN() > 0L) {
/* 211 */       if (getN() > 1L) {
/* 212 */         stdDev = Math.sqrt(getVariance());
/*     */       } else {
/* 214 */         stdDev = 0.0D;
/*     */       }
/*     */     }
/* 217 */     return stdDev;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSkewness()
/*     */   {
/* 227 */     return apply(this.skewnessImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getKurtosis()
/*     */   {
/* 237 */     return apply(this.kurtosisImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMax()
/*     */   {
/* 245 */     return apply(this.maxImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMin()
/*     */   {
/* 253 */     return apply(this.minImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getN()
/*     */   {
/* 261 */     return this.eDA.getNumElements();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSum()
/*     */   {
/* 269 */     return apply(this.sumImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSumsq()
/*     */   {
/* 278 */     return apply(this.sumsqImpl);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void clear()
/*     */   {
/* 285 */     this.eDA.clear();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getWindowSize()
/*     */   {
/* 296 */     return this.windowSize;
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
/*     */   public void setWindowSize(int windowSize)
/*     */   {
/* 309 */     if ((windowSize < 1) && 
/* 310 */       (windowSize != -1)) {
/* 311 */       throw new IllegalArgumentException("window size must be positive.");
/*     */     }
/*     */     
/*     */ 
/* 315 */     this.windowSize = windowSize;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 320 */     if ((windowSize != -1) && (windowSize < this.eDA.getNumElements())) {
/* 321 */       this.eDA.discardFrontElements(this.eDA.getNumElements() - windowSize);
/*     */     }
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
/*     */   public double[] getValues()
/*     */   {
/* 335 */     double[] copiedArray = new double[this.eDA.getNumElements()];
/* 336 */     System.arraycopy(this.eDA.getElements(), 0, copiedArray, 0, this.eDA.getNumElements());
/*     */     
/* 338 */     return copiedArray;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double[] getSortedValues()
/*     */   {
/* 350 */     double[] sort = getValues();
/* 351 */     Arrays.sort(sort);
/* 352 */     return sort;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getElement(int index)
/*     */   {
/* 361 */     return this.eDA.getElement(index);
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
/*     */   public double getPercentile(double p)
/*     */   {
/* 384 */     if ((this.percentileImpl instanceof Percentile)) {
/* 385 */       ((Percentile)this.percentileImpl).setQuantile(p);
/*     */     } else {
/*     */       try {
/* 388 */         this.percentileImpl.getClass().getMethod("setQuantile", new Class[] { Double.TYPE }).invoke(this.percentileImpl, new Object[] { new Double(p) });
/*     */       }
/*     */       catch (NoSuchMethodException e1)
/*     */       {
/* 392 */         throw new IllegalArgumentException("Percentile implementation does not support setQuantile");
/*     */       }
/*     */       catch (IllegalAccessException e2) {
/* 395 */         throw new IllegalArgumentException("IllegalAccessException setting quantile");
/*     */       }
/*     */       catch (InvocationTargetException e3) {
/* 398 */         throw new IllegalArgumentException("Error setting quantile" + e3.toString());
/*     */       }
/*     */     }
/*     */     
/* 402 */     return apply(this.percentileImpl);
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
/* 413 */     StringBuffer outBuffer = new StringBuffer();
/* 414 */     outBuffer.append("DescriptiveStatistics:\n");
/* 415 */     outBuffer.append("n: " + getN() + "\n");
/* 416 */     outBuffer.append("min: " + getMin() + "\n");
/* 417 */     outBuffer.append("max: " + getMax() + "\n");
/* 418 */     outBuffer.append("mean: " + getMean() + "\n");
/* 419 */     outBuffer.append("std dev: " + getStandardDeviation() + "\n");
/* 420 */     outBuffer.append("median: " + getPercentile(50.0D) + "\n");
/* 421 */     outBuffer.append("skewness: " + getSkewness() + "\n");
/* 422 */     outBuffer.append("kurtosis: " + getKurtosis() + "\n");
/* 423 */     return outBuffer.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double apply(UnivariateStatistic stat)
/*     */   {
/* 432 */     return stat.evaluate(this.eDA.getValues(), this.eDA.start(), this.eDA.getNumElements());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getMeanImpl()
/*     */   {
/* 444 */     return this.meanImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setMeanImpl(UnivariateStatistic meanImpl)
/*     */   {
/* 455 */     this.meanImpl = meanImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getGeometricMeanImpl()
/*     */   {
/* 465 */     return this.geometricMeanImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setGeometricMeanImpl(UnivariateStatistic geometricMeanImpl)
/*     */   {
/* 477 */     this.geometricMeanImpl = geometricMeanImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getKurtosisImpl()
/*     */   {
/* 487 */     return this.kurtosisImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setKurtosisImpl(UnivariateStatistic kurtosisImpl)
/*     */   {
/* 498 */     this.kurtosisImpl = kurtosisImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getMaxImpl()
/*     */   {
/* 508 */     return this.maxImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setMaxImpl(UnivariateStatistic maxImpl)
/*     */   {
/* 519 */     this.maxImpl = maxImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getMinImpl()
/*     */   {
/* 529 */     return this.minImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setMinImpl(UnivariateStatistic minImpl)
/*     */   {
/* 540 */     this.minImpl = minImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getPercentileImpl()
/*     */   {
/* 550 */     return this.percentileImpl;
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
/*     */   public synchronized void setPercentileImpl(UnivariateStatistic percentileImpl)
/*     */   {
/*     */     try
/*     */     {
/* 567 */       percentileImpl.getClass().getMethod("setQuantile", new Class[] { Double.TYPE }).invoke(percentileImpl, new Object[] { new Double(50.0D) });
/*     */     }
/*     */     catch (NoSuchMethodException e1)
/*     */     {
/* 571 */       throw new IllegalArgumentException("Percentile implementation does not support setQuantile");
/*     */     }
/*     */     catch (IllegalAccessException e2) {
/* 574 */       throw new IllegalArgumentException("IllegalAccessException setting quantile");
/*     */     }
/*     */     catch (InvocationTargetException e3) {
/* 577 */       throw new IllegalArgumentException("Error setting quantile" + e3.toString());
/*     */     }
/*     */     
/* 580 */     this.percentileImpl = percentileImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getSkewnessImpl()
/*     */   {
/* 590 */     return this.skewnessImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setSkewnessImpl(UnivariateStatistic skewnessImpl)
/*     */   {
/* 602 */     this.skewnessImpl = skewnessImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getVarianceImpl()
/*     */   {
/* 612 */     return this.varianceImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setVarianceImpl(UnivariateStatistic varianceImpl)
/*     */   {
/* 624 */     this.varianceImpl = varianceImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getSumsqImpl()
/*     */   {
/* 634 */     return this.sumsqImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setSumsqImpl(UnivariateStatistic sumsqImpl)
/*     */   {
/* 645 */     this.sumsqImpl = sumsqImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized UnivariateStatistic getSumImpl()
/*     */   {
/* 655 */     return this.sumImpl;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void setSumImpl(UnivariateStatistic sumImpl)
/*     */   {
/* 666 */     this.sumImpl = sumImpl;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/DescriptiveStatistics.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */