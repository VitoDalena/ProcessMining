package org.apache.commons.math.ode;

import java.io.Externalizable;

public abstract interface StepInterpolator
  extends Externalizable
{
  public abstract double getPreviousTime();
  
  public abstract double getCurrentTime();
  
  public abstract double getInterpolatedTime();
  
  public abstract void setInterpolatedTime(double paramDouble)
    throws DerivativeException;
  
  public abstract double[] getInterpolatedState();
  
  public abstract boolean isForward();
  
  public abstract StepInterpolator copy()
    throws DerivativeException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/StepInterpolator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */