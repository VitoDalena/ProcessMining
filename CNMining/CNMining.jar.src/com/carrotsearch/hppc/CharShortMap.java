package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharShortCursor;

public abstract interface CharShortMap
  extends CharShortAssociativeContainer
{
  public abstract short put(char paramChar, short paramShort);
  
  public abstract short addTo(char paramChar, short paramShort);
  
  public abstract short putOrAdd(char paramChar, short paramShort1, short paramShort2);
  
  public abstract short get(char paramChar);
  
  public abstract short getOrDefault(char paramChar, short paramShort);
  
  public abstract int putAll(CharShortAssociativeContainer paramCharShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharShortCursor> paramIterable);
  
  public abstract short remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */