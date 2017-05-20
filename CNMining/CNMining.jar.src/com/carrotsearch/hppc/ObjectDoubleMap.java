package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectDoubleCursor;

public abstract interface ObjectDoubleMap<KType>
  extends ObjectDoubleAssociativeContainer<KType>
{
  public abstract double put(KType paramKType, double paramDouble);
  
  public abstract double addTo(KType paramKType, double paramDouble);
  
  public abstract double putOrAdd(KType paramKType, double paramDouble1, double paramDouble2);
  
  public abstract double get(KType paramKType);
  
  public abstract double getOrDefault(KType paramKType, double paramDouble);
  
  public abstract int putAll(ObjectDoubleAssociativeContainer<? extends KType> paramObjectDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectDoubleCursor<? extends KType>> paramIterable);
  
  public abstract double remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */