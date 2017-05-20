package org.apache.commons.math.estimation;

public abstract interface EstimationProblem
{
  public abstract WeightedMeasurement[] getMeasurements();
  
  public abstract EstimatedParameter[] getUnboundParameters();
  
  public abstract EstimatedParameter[] getAllParameters();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/EstimationProblem.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */