package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortShortCursor;

public abstract interface ShortShortMap
  extends ShortShortAssociativeContainer
{
  public abstract short put(short paramShort1, short paramShort2);
  
  public abstract short addTo(short paramShort1, short paramShort2);
  
  public abstract short putOrAdd(short paramShort1, short paramShort2, short paramShort3);
  
  public abstract short get(short paramShort);
  
  public abstract short getOrDefault(short paramShort1, short paramShort2);
  
  public abstract int putAll(ShortShortAssociativeContainer paramShortShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortShortCursor> paramIterable);
  
  public abstract short remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */