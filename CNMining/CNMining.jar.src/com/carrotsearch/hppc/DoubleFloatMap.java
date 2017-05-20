package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleFloatCursor;

public abstract interface DoubleFloatMap
  extends DoubleFloatAssociativeContainer
{
  public abstract float put(double paramDouble, float paramFloat);
  
  public abstract float addTo(double paramDouble, float paramFloat);
  
  public abstract float putOrAdd(double paramDouble, float paramFloat1, float paramFloat2);
  
  public abstract float get(double paramDouble);
  
  public abstract float getOrDefault(double paramDouble, float paramFloat);
  
  public abstract int putAll(DoubleFloatAssociativeContainer paramDoubleFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleFloatCursor> paramIterable);
  
  public abstract float remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */