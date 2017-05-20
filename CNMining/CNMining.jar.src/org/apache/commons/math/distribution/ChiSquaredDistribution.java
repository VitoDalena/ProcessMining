package org.apache.commons.math.distribution;

public abstract interface ChiSquaredDistribution
  extends ContinuousDistribution
{
  public abstract void setDegreesOfFreedom(double paramDouble);
  
  public abstract double getDegreesOfFreedom();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/ChiSquaredDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */