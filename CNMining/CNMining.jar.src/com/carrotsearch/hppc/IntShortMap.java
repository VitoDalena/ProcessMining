package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntShortCursor;

public abstract interface IntShortMap
  extends IntShortAssociativeContainer
{
  public abstract short put(int paramInt, short paramShort);
  
  public abstract short addTo(int paramInt, short paramShort);
  
  public abstract short putOrAdd(int paramInt, short paramShort1, short paramShort2);
  
  public abstract short get(int paramInt);
  
  public abstract short getOrDefault(int paramInt, short paramShort);
  
  public abstract int putAll(IntShortAssociativeContainer paramIntShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntShortCursor> paramIterable);
  
  public abstract short remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */