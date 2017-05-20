package org.apache.commons.math.ode;

public abstract interface SecondOrderDifferentialEquations
{
  public abstract int getDimension();
  
  public abstract void computeSecondDerivatives(double paramDouble, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3)
    throws DerivativeException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/SecondOrderDifferentialEquations.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */