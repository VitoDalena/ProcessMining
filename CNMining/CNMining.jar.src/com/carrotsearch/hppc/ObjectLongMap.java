package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectLongCursor;

public abstract interface ObjectLongMap<KType>
  extends ObjectLongAssociativeContainer<KType>
{
  public abstract long put(KType paramKType, long paramLong);
  
  public abstract long addTo(KType paramKType, long paramLong);
  
  public abstract long putOrAdd(KType paramKType, long paramLong1, long paramLong2);
  
  public abstract long get(KType paramKType);
  
  public abstract long getOrDefault(KType paramKType, long paramLong);
  
  public abstract int putAll(ObjectLongAssociativeContainer<? extends KType> paramObjectLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectLongCursor<? extends KType>> paramIterable);
  
  public abstract long remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */