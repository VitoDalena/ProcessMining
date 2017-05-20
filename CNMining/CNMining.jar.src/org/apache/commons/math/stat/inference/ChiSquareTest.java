package org.apache.commons.math.stat.inference;

import org.apache.commons.math.MathException;

public abstract interface ChiSquareTest
{
  public abstract double chiSquare(double[] paramArrayOfDouble, long[] paramArrayOfLong)
    throws IllegalArgumentException;
  
  public abstract double chiSquareTest(double[] paramArrayOfDouble, long[] paramArrayOfLong)
    throws IllegalArgumentException, MathException;
  
  public abstract boolean chiSquareTest(double[] paramArrayOfDouble, long[] paramArrayOfLong, double paramDouble)
    throws IllegalArgumentException, MathException;
  
  public abstract double chiSquare(long[][] paramArrayOfLong)
    throws IllegalArgumentException;
  
  public abstract double chiSquareTest(long[][] paramArrayOfLong)
    throws IllegalArgumentException, MathException;
  
  public abstract boolean chiSquareTest(long[][] paramArrayOfLong, double paramDouble)
    throws IllegalArgumentException, MathException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/stat/inference/ChiSquareTest.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */