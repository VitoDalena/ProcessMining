package org.apache.commons.math.ode;

public abstract interface SecondOrderIntegrator
{
  public abstract String getName();
  
  public abstract void setStepHandler(StepHandler paramStepHandler);
  
  public abstract StepHandler getStepHandler();
  
  public abstract void integrate(SecondOrderDifferentialEquations paramSecondOrderDifferentialEquations, double paramDouble1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double paramDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4)
    throws DerivativeException, IntegratorException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/SecondOrderIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */