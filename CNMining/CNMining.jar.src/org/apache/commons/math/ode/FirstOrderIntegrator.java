package org.apache.commons.math.ode;

public abstract interface FirstOrderIntegrator
{
  public abstract String getName();
  
  public abstract void setStepHandler(StepHandler paramStepHandler);
  
  public abstract StepHandler getStepHandler();
  
  public abstract void addSwitchingFunction(SwitchingFunction paramSwitchingFunction, double paramDouble1, double paramDouble2, int paramInt);
  
  public abstract void integrate(FirstOrderDifferentialEquations paramFirstOrderDifferentialEquations, double paramDouble1, double[] paramArrayOfDouble1, double paramDouble2, double[] paramArrayOfDouble2)
    throws DerivativeException, IntegratorException;
  
  public abstract double getCurrentStepStart();
  
  public abstract double getCurrentSignedStepsize();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/FirstOrderIntegrator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */