package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongIntCursor;

public abstract interface LongIntMap
  extends LongIntAssociativeContainer
{
  public abstract int put(long paramLong, int paramInt);
  
  public abstract int addTo(long paramLong, int paramInt);
  
  public abstract int putOrAdd(long paramLong, int paramInt1, int paramInt2);
  
  public abstract int get(long paramLong);
  
  public abstract int getOrDefault(long paramLong, int paramInt);
  
  public abstract int putAll(LongIntAssociativeContainer paramLongIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongIntCursor> paramIterable);
  
  public abstract int remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */