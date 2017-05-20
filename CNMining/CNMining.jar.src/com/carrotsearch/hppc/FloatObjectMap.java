package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatObjectCursor;

public abstract interface FloatObjectMap<VType>
  extends FloatObjectAssociativeContainer<VType>
{
  public abstract VType put(float paramFloat, VType paramVType);
  
  public abstract VType get(float paramFloat);
  
  public abstract VType getOrDefault(float paramFloat, VType paramVType);
  
  public abstract int putAll(FloatObjectAssociativeContainer<? extends VType> paramFloatObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */