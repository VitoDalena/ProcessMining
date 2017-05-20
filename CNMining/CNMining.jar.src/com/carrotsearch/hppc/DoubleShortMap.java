package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleShortCursor;

public abstract interface DoubleShortMap
  extends DoubleShortAssociativeContainer
{
  public abstract short put(double paramDouble, short paramShort);
  
  public abstract short addTo(double paramDouble, short paramShort);
  
  public abstract short putOrAdd(double paramDouble, short paramShort1, short paramShort2);
  
  public abstract short get(double paramDouble);
  
  public abstract short getOrDefault(double paramDouble, short paramShort);
  
  public abstract int putAll(DoubleShortAssociativeContainer paramDoubleShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleShortCursor> paramIterable);
  
  public abstract short remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */