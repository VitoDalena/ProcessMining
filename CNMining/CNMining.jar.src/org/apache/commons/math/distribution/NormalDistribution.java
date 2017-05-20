package org.apache.commons.math.distribution;

public abstract interface NormalDistribution
  extends ContinuousDistribution
{
  public abstract double getMean();
  
  public abstract void setMean(double paramDouble);
  
  public abstract double getStandardDeviation();
  
  public abstract void setStandardDeviation(double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/NormalDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */