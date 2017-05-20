package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongCharCursor;

public abstract interface LongCharMap
  extends LongCharAssociativeContainer
{
  public abstract char put(long paramLong, char paramChar);
  
  public abstract char addTo(long paramLong, char paramChar);
  
  public abstract char putOrAdd(long paramLong, char paramChar1, char paramChar2);
  
  public abstract char get(long paramLong);
  
  public abstract char getOrDefault(long paramLong, char paramChar);
  
  public abstract int putAll(LongCharAssociativeContainer paramLongCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongCharCursor> paramIterable);
  
  public abstract char remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */