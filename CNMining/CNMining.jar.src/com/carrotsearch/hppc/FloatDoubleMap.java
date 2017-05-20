package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatDoubleCursor;

public abstract interface FloatDoubleMap
  extends FloatDoubleAssociativeContainer
{
  public abstract double put(float paramFloat, double paramDouble);
  
  public abstract double addTo(float paramFloat, double paramDouble);
  
  public abstract double putOrAdd(float paramFloat, double paramDouble1, double paramDouble2);
  
  public abstract double get(float paramFloat);
  
  public abstract double getOrDefault(float paramFloat, double paramDouble);
  
  public abstract int putAll(FloatDoubleAssociativeContainer paramFloatDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatDoubleCursor> paramIterable);
  
  public abstract double remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */