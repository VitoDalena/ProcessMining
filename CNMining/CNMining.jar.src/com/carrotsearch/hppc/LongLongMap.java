package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongLongCursor;

public abstract interface LongLongMap
  extends LongLongAssociativeContainer
{
  public abstract long put(long paramLong1, long paramLong2);
  
  public abstract long addTo(long paramLong1, long paramLong2);
  
  public abstract long putOrAdd(long paramLong1, long paramLong2, long paramLong3);
  
  public abstract long get(long paramLong);
  
  public abstract long getOrDefault(long paramLong1, long paramLong2);
  
  public abstract int putAll(LongLongAssociativeContainer paramLongLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongLongCursor> paramIterable);
  
  public abstract long remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */