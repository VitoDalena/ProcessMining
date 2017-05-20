package org.apache.commons.math.distribution;

public abstract interface BinomialDistribution
  extends IntegerDistribution
{
  public abstract int getNumberOfTrials();
  
  public abstract double getProbabilityOfSuccess();
  
  public abstract void setNumberOfTrials(int paramInt);
  
  public abstract void setProbabilityOfSuccess(double paramDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/BinomialDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */