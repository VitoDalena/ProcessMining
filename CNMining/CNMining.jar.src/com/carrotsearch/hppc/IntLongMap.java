package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntLongCursor;

public abstract interface IntLongMap
  extends IntLongAssociativeContainer
{
  public abstract long put(int paramInt, long paramLong);
  
  public abstract long addTo(int paramInt, long paramLong);
  
  public abstract long putOrAdd(int paramInt, long paramLong1, long paramLong2);
  
  public abstract long get(int paramInt);
  
  public abstract long getOrDefault(int paramInt, long paramLong);
  
  public abstract int putAll(IntLongAssociativeContainer paramIntLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntLongCursor> paramIterable);
  
  public abstract long remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */