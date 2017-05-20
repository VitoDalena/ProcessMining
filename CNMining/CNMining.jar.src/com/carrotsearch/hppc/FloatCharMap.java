package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatCharCursor;

public abstract interface FloatCharMap
  extends FloatCharAssociativeContainer
{
  public abstract char put(float paramFloat, char paramChar);
  
  public abstract char addTo(float paramFloat, char paramChar);
  
  public abstract char putOrAdd(float paramFloat, char paramChar1, char paramChar2);
  
  public abstract char get(float paramFloat);
  
  public abstract char getOrDefault(float paramFloat, char paramChar);
  
  public abstract int putAll(FloatCharAssociativeContainer paramFloatCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatCharCursor> paramIterable);
  
  public abstract char remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */