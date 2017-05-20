package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleCharCursor;

public abstract interface DoubleCharMap
  extends DoubleCharAssociativeContainer
{
  public abstract char put(double paramDouble, char paramChar);
  
  public abstract char addTo(double paramDouble, char paramChar);
  
  public abstract char putOrAdd(double paramDouble, char paramChar1, char paramChar2);
  
  public abstract char get(double paramDouble);
  
  public abstract char getOrDefault(double paramDouble, char paramChar);
  
  public abstract int putAll(DoubleCharAssociativeContainer paramDoubleCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleCharCursor> paramIterable);
  
  public abstract char remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */