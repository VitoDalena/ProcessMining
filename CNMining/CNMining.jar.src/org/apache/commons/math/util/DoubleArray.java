package org.apache.commons.math.util;

public abstract interface DoubleArray
{
  public abstract int getNumElements();
  
  public abstract double getElement(int paramInt);
  
  public abstract void setElement(int paramInt, double paramDouble);
  
  public abstract void addElement(double paramDouble);
  
  public abstract double addElementRolling(double paramDouble);
  
  public abstract double[] getElements();
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/math/util/DoubleArray.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */