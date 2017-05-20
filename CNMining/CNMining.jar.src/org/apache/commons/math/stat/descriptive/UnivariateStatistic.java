package org.apache.commons.math.stat.descriptive;

import java.io.Serializable;

public abstract interface UnivariateStatistic
  extends Serializable
{
  public abstract double evaluate(double[] paramArrayOfDouble);
  
  public abstract double evaluate(double[] paramArrayOfDouble, int paramInt1, int paramInt2);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/descriptive/UnivariateStatistic.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */