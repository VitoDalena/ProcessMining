package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortObjectCursor;

public abstract interface ShortObjectMap<VType>
  extends ShortObjectAssociativeContainer<VType>
{
  public abstract VType put(short paramShort, VType paramVType);
  
  public abstract VType get(short paramShort);
  
  public abstract VType getOrDefault(short paramShort, VType paramVType);
  
  public abstract int putAll(ShortObjectAssociativeContainer<? extends VType> paramShortObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */