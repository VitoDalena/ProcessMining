package org.apache.commons.math.analysis;

import org.apache.commons.math.MathException;

public abstract interface UnivariateRealInterpolator
{
  public abstract UnivariateRealFunction interpolate(double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
    throws MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */