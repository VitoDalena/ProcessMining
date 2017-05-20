package org.apache.commons.math.distribution;

public abstract interface PascalDistribution
  extends IntegerDistribution
{
  public abstract int getNumberOfSuccesses();
  
  public abstract double getProbabilityOfSuccess();
  
  public abstract void setNumberOfSuccesses(int paramInt);
  
  public abstract void setProbabilityOfSuccess(double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/PascalDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */