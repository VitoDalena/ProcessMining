package org.apache.commons.math.stat.descriptive;

public abstract interface StatisticalSummary
{
  public abstract double getMean();
  
  public abstract double getVariance();
  
  public abstract double getStandardDeviation();
  
  public abstract double getMax();
  
  public abstract double getMin();
  
  public abstract long getN();
  
  public abstract double getSum();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/StatisticalSummary.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */