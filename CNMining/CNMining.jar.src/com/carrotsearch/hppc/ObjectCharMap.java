package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectCharCursor;

public abstract interface ObjectCharMap<KType>
  extends ObjectCharAssociativeContainer<KType>
{
  public abstract char put(KType paramKType, char paramChar);
  
  public abstract char addTo(KType paramKType, char paramChar);
  
  public abstract char putOrAdd(KType paramKType, char paramChar1, char paramChar2);
  
  public abstract char get(KType paramKType);
  
  public abstract char getOrDefault(KType paramKType, char paramChar);
  
  public abstract int putAll(ObjectCharAssociativeContainer<? extends KType> paramObjectCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectCharCursor<? extends KType>> paramIterable);
  
  public abstract char remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */