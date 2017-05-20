package org.apache.commons.math.distribution;

import org.apache.commons.math.MathException;

public abstract interface Distribution
{
  public abstract double cumulativeProbability(double paramDouble)
    throws MathException;
  
  public abstract double cumulativeProbability(double paramDouble1, double paramDouble2)
    throws MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/distribution/Distribution.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */