package org.apache.commons.math.distribution;

public abstract interface CauchyDistribution
  extends ContinuousDistribution
{
  public abstract double getMedian();
  
  public abstract double getScale();
  
  public abstract void setMedian(double paramDouble);
  
  public abstract void setScale(double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/CauchyDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */