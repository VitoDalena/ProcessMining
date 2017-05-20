package org.apache.commons.math.distribution;

import org.apache.commons.math.MathException;

public abstract interface ContinuousDistribution
  extends Distribution
{
  public abstract double inverseCumulativeProbability(double paramDouble)
    throws MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/ContinuousDistribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */