package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleIntCursor;

public abstract interface DoubleIntMap
  extends DoubleIntAssociativeContainer
{
  public abstract int put(double paramDouble, int paramInt);
  
  public abstract int addTo(double paramDouble, int paramInt);
  
  public abstract int putOrAdd(double paramDouble, int paramInt1, int paramInt2);
  
  public abstract int get(double paramDouble);
  
  public abstract int getOrDefault(double paramDouble, int paramInt);
  
  public abstract int putAll(DoubleIntAssociativeContainer paramDoubleIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleIntCursor> paramIterable);
  
  public abstract int remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */