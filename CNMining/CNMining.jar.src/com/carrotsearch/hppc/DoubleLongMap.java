package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleLongCursor;

public abstract interface DoubleLongMap
  extends DoubleLongAssociativeContainer
{
  public abstract long put(double paramDouble, long paramLong);
  
  public abstract long addTo(double paramDouble, long paramLong);
  
  public abstract long putOrAdd(double paramDouble, long paramLong1, long paramLong2);
  
  public abstract long get(double paramDouble);
  
  public abstract long getOrDefault(double paramDouble, long paramLong);
  
  public abstract int putAll(DoubleLongAssociativeContainer paramDoubleLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleLongCursor> paramIterable);
  
  public abstract long remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */