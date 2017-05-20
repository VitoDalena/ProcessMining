package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntObjectCursor;

public abstract interface IntObjectMap<VType>
  extends IntObjectAssociativeContainer<VType>
{
  public abstract VType put(int paramInt, VType paramVType);
  
  public abstract VType get(int paramInt);
  
  public abstract VType getOrDefault(int paramInt, VType paramVType);
  
  public abstract int putAll(IntObjectAssociativeContainer<? extends VType> paramIntObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */