package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongFloatCursor;

public abstract interface LongFloatMap
  extends LongFloatAssociativeContainer
{
  public abstract float put(long paramLong, float paramFloat);
  
  public abstract float addTo(long paramLong, float paramFloat);
  
  public abstract float putOrAdd(long paramLong, float paramFloat1, float paramFloat2);
  
  public abstract float get(long paramLong);
  
  public abstract float getOrDefault(long paramLong, float paramFloat);
  
  public abstract int putAll(LongFloatAssociativeContainer paramLongFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongFloatCursor> paramIterable);
  
  public abstract float remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */