package org.apache.commons.math.stat.descriptive;

import org.apache.commons.math.linear.RealMatrix;

public abstract interface StatisticalMultivariateSummary
{
  public abstract int getDimension();
  
  public abstract double[] getMean();
  
  public abstract RealMatrix getCovariance();
  
  public abstract double[] getStandardDeviation();
  
  public abstract double[] getMax();
  
  public abstract double[] getMin();
  
  public abstract long getN();
  
  public abstract double[] getGeometricMean();
  
  public abstract double[] getSum();
  
  public abstract double[] getSumSq();
  
  public abstract double[] getSumLog();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/StatisticalMultivariateSummary.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */