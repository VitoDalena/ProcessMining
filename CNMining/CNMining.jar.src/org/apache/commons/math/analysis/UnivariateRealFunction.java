package org.apache.commons.math.analysis;

import org.apache.commons.math.FunctionEvaluationException;

public abstract interface UnivariateRealFunction
{
  public abstract double value(double paramDouble)
    throws FunctionEvaluationException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/analysis/UnivariateRealFunction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */