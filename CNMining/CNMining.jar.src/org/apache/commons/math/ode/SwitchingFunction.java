package org.apache.commons.math.ode;

import java.io.Serializable;
import org.apache.commons.math.FunctionEvaluationException;

public abstract interface SwitchingFunction
  extends Serializable
{
  public static final int STOP = 0;
  public static final int RESET_STATE = 1;
  public static final int RESET_DERIVATIVES = 2;
  public static final int CONTINUE = 3;
  
  public abstract double g(double paramDouble, double[] paramArrayOfDouble)
    throws FunctionEvaluationException;
  
  public abstract int eventOccurred(double paramDouble, double[] paramArrayOfDouble);
  
  public abstract void resetState(double paramDouble, double[] paramArrayOfDouble);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/ode/SwitchingFunction.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */