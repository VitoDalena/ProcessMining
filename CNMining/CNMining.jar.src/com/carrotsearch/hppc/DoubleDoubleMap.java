package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleDoubleCursor;

public abstract interface DoubleDoubleMap
  extends DoubleDoubleAssociativeContainer
{
  public abstract double put(double paramDouble1, double paramDouble2);
  
  public abstract double addTo(double paramDouble1, double paramDouble2);
  
  public abstract double putOrAdd(double paramDouble1, double paramDouble2, double paramDouble3);
  
  public abstract double get(double paramDouble);
  
  public abstract double getOrDefault(double paramDouble1, double paramDouble2);
  
  public abstract int putAll(DoubleDoubleAssociativeContainer paramDoubleDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleDoubleCursor> paramIterable);
  
  public abstract double remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */