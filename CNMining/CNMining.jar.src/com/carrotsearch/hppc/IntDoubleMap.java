package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntDoubleCursor;

public abstract interface IntDoubleMap
  extends IntDoubleAssociativeContainer
{
  public abstract double put(int paramInt, double paramDouble);
  
  public abstract double addTo(int paramInt, double paramDouble);
  
  public abstract double putOrAdd(int paramInt, double paramDouble1, double paramDouble2);
  
  public abstract double get(int paramInt);
  
  public abstract double getOrDefault(int paramInt, double paramDouble);
  
  public abstract int putAll(IntDoubleAssociativeContainer paramIntDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntDoubleCursor> paramIterable);
  
  public abstract double remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */