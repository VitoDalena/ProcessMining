package org.apache.commons.math.distribution;

public abstract interface HypergeometricDistribution
  extends IntegerDistribution
{
  public abstract int getNumberOfSuccesses();
  
  public abstract int getPopulationSize();
  
  public abstract int getSampleSize();
  
  public abstract void setNumberOfSuccesses(int paramInt);
  
  public abstract void setPopulationSize(int paramInt);
  
  public abstract void setSampleSize(int paramInt);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/HypergeometricDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */