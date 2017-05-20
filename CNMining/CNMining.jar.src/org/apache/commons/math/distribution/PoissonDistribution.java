package org.apache.commons.math.distribution;

import org.apache.commons.math.MathException;

public abstract interface PoissonDistribution
  extends IntegerDistribution
{
  public abstract double getMean();
  
  public abstract void setMean(double paramDouble);
  
  public abstract double normalApproximateProbability(int paramInt)
    throws MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/PoissonDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */