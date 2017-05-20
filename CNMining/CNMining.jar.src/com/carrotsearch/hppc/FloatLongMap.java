package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatLongCursor;

public abstract interface FloatLongMap
  extends FloatLongAssociativeContainer
{
  public abstract long put(float paramFloat, long paramLong);
  
  public abstract long addTo(float paramFloat, long paramLong);
  
  public abstract long putOrAdd(float paramFloat, long paramLong1, long paramLong2);
  
  public abstract long get(float paramFloat);
  
  public abstract long getOrDefault(float paramFloat, long paramLong);
  
  public abstract int putAll(FloatLongAssociativeContainer paramFloatLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatLongCursor> paramIterable);
  
  public abstract long remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */