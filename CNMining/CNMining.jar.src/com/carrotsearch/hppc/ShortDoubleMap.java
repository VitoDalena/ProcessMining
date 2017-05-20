package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortDoubleCursor;

public abstract interface ShortDoubleMap
  extends ShortDoubleAssociativeContainer
{
  public abstract double put(short paramShort, double paramDouble);
  
  public abstract double addTo(short paramShort, double paramDouble);
  
  public abstract double putOrAdd(short paramShort, double paramDouble1, double paramDouble2);
  
  public abstract double get(short paramShort);
  
  public abstract double getOrDefault(short paramShort, double paramDouble);
  
  public abstract int putAll(ShortDoubleAssociativeContainer paramShortDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortDoubleCursor> paramIterable);
  
  public abstract double remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */