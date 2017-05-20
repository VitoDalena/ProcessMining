package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectIntCursor;

public abstract interface ObjectIntMap<KType>
  extends ObjectIntAssociativeContainer<KType>
{
  public abstract int put(KType paramKType, int paramInt);
  
  public abstract int addTo(KType paramKType, int paramInt);
  
  public abstract int putOrAdd(KType paramKType, int paramInt1, int paramInt2);
  
  public abstract int get(KType paramKType);
  
  public abstract int getOrDefault(KType paramKType, int paramInt);
  
  public abstract int putAll(ObjectIntAssociativeContainer<? extends KType> paramObjectIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectIntCursor<? extends KType>> paramIterable);
  
  public abstract int remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */