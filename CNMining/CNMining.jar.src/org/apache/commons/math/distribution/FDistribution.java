package org.apache.commons.math.distribution;

public abstract interface FDistribution
  extends ContinuousDistribution
{
  public abstract void setNumeratorDegreesOfFreedom(double paramDouble);
  
  public abstract double getNumeratorDegreesOfFreedom();
  
  public abstract void setDenominatorDegreesOfFreedom(double paramDouble);
  
  public abstract double getDenominatorDegreesOfFreedom();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/FDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */