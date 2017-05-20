package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharObjectCursor;

public abstract interface CharObjectMap<VType>
  extends CharObjectAssociativeContainer<VType>
{
  public abstract VType put(char paramChar, VType paramVType);
  
  public abstract VType get(char paramChar);
  
  public abstract VType getOrDefault(char paramChar, VType paramVType);
  
  public abstract int putAll(CharObjectAssociativeContainer<? extends VType> paramCharObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */