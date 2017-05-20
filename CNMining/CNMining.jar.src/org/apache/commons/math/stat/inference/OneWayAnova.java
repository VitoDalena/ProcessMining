package org.apache.commons.math.stat.inference;

import java.util.Collection;
import org.apache.commons.math.MathException;

public abstract interface OneWayAnova
{
  public abstract double anovaFValue(Collection paramCollection)
    throws IllegalArgumentException, MathException;
  
  public abstract double anovaPValue(Collection paramCollection)
    throws IllegalArgumentException, MathException;
  
  public abstract boolean anovaTest(Collection paramCollection, double paramDouble)
    throws IllegalArgumentException, MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/OneWayAnova.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */