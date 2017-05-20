package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortCharCursor;

public abstract interface ShortCharMap
  extends ShortCharAssociativeContainer
{
  public abstract char put(short paramShort, char paramChar);
  
  public abstract char addTo(short paramShort, char paramChar);
  
  public abstract char putOrAdd(short paramShort, char paramChar1, char paramChar2);
  
  public abstract char get(short paramShort);
  
  public abstract char getOrDefault(short paramShort, char paramChar);
  
  public abstract int putAll(ShortCharAssociativeContainer paramShortCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortCharCursor> paramIterable);
  
  public abstract char remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */