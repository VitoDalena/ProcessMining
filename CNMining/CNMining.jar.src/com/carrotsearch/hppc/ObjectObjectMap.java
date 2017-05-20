package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectObjectCursor;

public abstract interface ObjectObjectMap<KType, VType>
  extends ObjectObjectAssociativeContainer<KType, VType>
{
  public abstract VType put(KType paramKType, VType paramVType);
  
  public abstract VType get(KType paramKType);
  
  public abstract VType getOrDefault(KType paramKType, VType paramVType);
  
  public abstract int putAll(ObjectObjectAssociativeContainer<? extends KType, ? extends VType> paramObjectObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectObjectCursor<? extends KType, ? extends VType>> paramIterable);
  
  public abstract VType remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */