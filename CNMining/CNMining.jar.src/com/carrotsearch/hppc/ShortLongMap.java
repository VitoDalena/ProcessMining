package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortLongCursor;

public abstract interface ShortLongMap
  extends ShortLongAssociativeContainer
{
  public abstract long put(short paramShort, long paramLong);
  
  public abstract long addTo(short paramShort, long paramLong);
  
  public abstract long putOrAdd(short paramShort, long paramLong1, long paramLong2);
  
  public abstract long get(short paramShort);
  
  public abstract long getOrDefault(short paramShort, long paramLong);
  
  public abstract int putAll(ShortLongAssociativeContainer paramShortLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortLongCursor> paramIterable);
  
  public abstract long remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */