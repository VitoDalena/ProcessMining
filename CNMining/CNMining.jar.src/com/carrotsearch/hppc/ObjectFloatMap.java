package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectFloatCursor;

public abstract interface ObjectFloatMap<KType>
  extends ObjectFloatAssociativeContainer<KType>
{
  public abstract float put(KType paramKType, float paramFloat);
  
  public abstract float addTo(KType paramKType, float paramFloat);
  
  public abstract float putOrAdd(KType paramKType, float paramFloat1, float paramFloat2);
  
  public abstract float get(KType paramKType);
  
  public abstract float getOrDefault(KType paramKType, float paramFloat);
  
  public abstract int putAll(ObjectFloatAssociativeContainer<? extends KType> paramObjectFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectFloatCursor<? extends KType>> paramIterable);
  
  public abstract float remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */