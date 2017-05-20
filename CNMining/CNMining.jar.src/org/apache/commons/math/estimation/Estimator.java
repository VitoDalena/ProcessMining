package org.apache.commons.math.estimation;

public abstract interface Estimator
{
  public abstract void estimate(EstimationProblem paramEstimationProblem)
    throws EstimationException;
  
  public abstract double getRMS(EstimationProblem paramEstimationProblem);
  
  public abstract double[][] getCovariances(EstimationProblem paramEstimationProblem)
    throws EstimationException;
  
  public abstract double[] guessParametersErrors(EstimationProblem paramEstimationProblem)
    throws EstimationException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/estimation/Estimator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */