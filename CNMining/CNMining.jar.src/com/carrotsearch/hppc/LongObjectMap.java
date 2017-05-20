package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongObjectCursor;

public abstract interface LongObjectMap<VType>
  extends LongObjectAssociativeContainer<VType>
{
  public abstract VType put(long paramLong, VType paramVType);
  
  public abstract VType get(long paramLong);
  
  public abstract VType getOrDefault(long paramLong, VType paramVType);
  
  public abstract int putAll(LongObjectAssociativeContainer<? extends VType> paramLongObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */