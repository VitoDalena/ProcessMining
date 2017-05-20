package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectShortCursor;

public abstract interface ObjectShortMap<KType>
  extends ObjectShortAssociativeContainer<KType>
{
  public abstract short put(KType paramKType, short paramShort);
  
  public abstract short addTo(KType paramKType, short paramShort);
  
  public abstract short putOrAdd(KType paramKType, short paramShort1, short paramShort2);
  
  public abstract short get(KType paramKType);
  
  public abstract short getOrDefault(KType paramKType, short paramShort);
  
  public abstract int putAll(ObjectShortAssociativeContainer<? extends KType> paramObjectShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectShortCursor<? extends KType>> paramIterable);
  
  public abstract short remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */