package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharDoubleCursor;

public abstract interface CharDoubleMap
  extends CharDoubleAssociativeContainer
{
  public abstract double put(char paramChar, double paramDouble);
  
  public abstract double addTo(char paramChar, double paramDouble);
  
  public abstract double putOrAdd(char paramChar, double paramDouble1, double paramDouble2);
  
  public abstract double get(char paramChar);
  
  public abstract double getOrDefault(char paramChar, double paramDouble);
  
  public abstract int putAll(CharDoubleAssociativeContainer paramCharDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharDoubleCursor> paramIterable);
  
  public abstract double remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */