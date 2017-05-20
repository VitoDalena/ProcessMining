package org.apache.commons.math.analysis;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;

public abstract interface UnivariateRealIntegrator
{
  public abstract void setMaximalIterationCount(int paramInt);
  
  public abstract int getMaximalIterationCount();
  
  public abstract void resetMaximalIterationCount();
  
  public abstract void setMinimalIterationCount(int paramInt);
  
  public abstract int getMinimalIterationCount();
  
  public abstract void resetMinimalIterationCount();
  
  public abstract void setRelativeAccuracy(double paramDouble);
  
  public abstract double getRelativeAccuracy();
  
  public abstract void resetRelativeAccuracy();
  
  public abstract double integrate(double paramDouble1, double paramDouble2)
    throws ConvergenceException, FunctionEvaluationException, IllegalArgumentException;
  
  public abstract double getResult()
    throws IllegalStateException;
  
  public abstract int getIterationCount()
    throws IllegalStateException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */