package org.apache.commons.math.ode;

public abstract interface StepHandler
{
  public abstract boolean requiresDenseOutput();
  
  public abstract void reset();
  
  public abstract void handleStep(StepInterpolator paramStepInterpolator, boolean paramBoolean)
    throws DerivativeException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/StepHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */