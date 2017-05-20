package org.apache.commons.math.optimization;

public abstract interface CostFunction
{
  public abstract double cost(double[] paramArrayOfDouble)
    throws CostException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/CostFunction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */