package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleObjectCursor;

public abstract interface DoubleObjectMap<VType>
  extends DoubleObjectAssociativeContainer<VType>
{
  public abstract VType put(double paramDouble, VType paramVType);
  
  public abstract VType get(double paramDouble);
  
  public abstract VType getOrDefault(double paramDouble, VType paramVType);
  
  public abstract int putAll(DoubleObjectAssociativeContainer<? extends VType> paramDoubleObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */