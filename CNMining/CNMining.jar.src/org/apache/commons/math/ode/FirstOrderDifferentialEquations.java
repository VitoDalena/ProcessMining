package org.apache.commons.math.ode;

public abstract interface FirstOrderDifferentialEquations
{
  public abstract int getDimension();
  
  public abstract void computeDerivatives(double paramDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
    throws DerivativeException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/FirstOrderDifferentialEquations.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */