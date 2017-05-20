package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongShortCursor;

public abstract interface LongShortMap
  extends LongShortAssociativeContainer
{
  public abstract short put(long paramLong, short paramShort);
  
  public abstract short addTo(long paramLong, short paramShort);
  
  public abstract short putOrAdd(long paramLong, short paramShort1, short paramShort2);
  
  public abstract short get(long paramLong);
  
  public abstract short getOrDefault(long paramLong, short paramShort);
  
  public abstract int putAll(LongShortAssociativeContainer paramLongShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongShortCursor> paramIterable);
  
  public abstract short remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */