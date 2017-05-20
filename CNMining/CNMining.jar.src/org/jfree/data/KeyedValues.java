package org.jfree.data;

import java.util.List;

public abstract interface KeyedValues
  extends Values
{
  public abstract Comparable getKey(int paramInt);
  
  public abstract int getIndex(Comparable paramComparable);
  
  public abstract List getKeys();
  
  public abstract Number getValue(Comparable paramComparable);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/KeyedValues.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */