package org.apache.commons.math.analysis;

import org.apache.commons.math.ConvergenceException;
import org.apache.commons.math.FunctionEvaluationException;

public abstract interface UnivariateRealSolver
{
  public abstract void setMaximalIterationCount(int paramInt);
  
  public abstract int getMaximalIterationCount();
  
  public abstract void resetMaximalIterationCount();
  
  public abstract void setAbsoluteAccuracy(double paramDouble);
  
  public abstract double getAbsoluteAccuracy();
  
  public abstract void resetAbsoluteAccuracy();
  
  public abstract void setRelativeAccuracy(double paramDouble);
  
  public abstract double getRelativeAccuracy();
  
  public abstract void resetRelativeAccuracy();
  
  public abstract void setFunctionValueAccuracy(double paramDouble);
  
  public abstract double getFunctionValueAccuracy();
  
  public abstract void resetFunctionValueAccuracy();
  
  public abstract double solve(double paramDouble1, double paramDouble2)
    throws ConvergenceException, FunctionEvaluationException;
  
  public abstract double solve(double paramDouble1, double paramDouble2, double paramDouble3)
    throws ConvergenceException, FunctionEvaluationException;
  
  public abstract double getResult();
  
  public abstract int getIterationCount();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealSolver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */