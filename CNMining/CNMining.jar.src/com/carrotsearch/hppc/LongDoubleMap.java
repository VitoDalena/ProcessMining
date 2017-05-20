package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongDoubleCursor;

public abstract interface LongDoubleMap
  extends LongDoubleAssociativeContainer
{
  public abstract double put(long paramLong, double paramDouble);
  
  public abstract double addTo(long paramLong, double paramDouble);
  
  public abstract double putOrAdd(long paramLong, double paramDouble1, double paramDouble2);
  
  public abstract double get(long paramLong);
  
  public abstract double getOrDefault(long paramLong, double paramDouble);
  
  public abstract int putAll(LongDoubleAssociativeContainer paramLongDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongDoubleCursor> paramIterable);
  
  public abstract double remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */