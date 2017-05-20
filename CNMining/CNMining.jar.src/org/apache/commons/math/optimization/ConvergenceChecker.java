package org.apache.commons.math.optimization;

public abstract interface ConvergenceChecker
{
  public abstract boolean converged(PointCostPair[] paramArrayOfPointCostPair);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/optimization/ConvergenceChecker.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */