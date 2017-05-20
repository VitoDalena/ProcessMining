package org.apache.commons.math.distribution;

public abstract interface GammaDistribution
  extends ContinuousDistribution
{
  public abstract void setAlpha(double paramDouble);
  
  public abstract double getAlpha();
  
  public abstract void setBeta(double paramDouble);
  
  public abstract double getBeta();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/GammaDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */