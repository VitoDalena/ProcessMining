package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharIntCursor;

public abstract interface CharIntMap
  extends CharIntAssociativeContainer
{
  public abstract int put(char paramChar, int paramInt);
  
  public abstract int addTo(char paramChar, int paramInt);
  
  public abstract int putOrAdd(char paramChar, int paramInt1, int paramInt2);
  
  public abstract int get(char paramChar);
  
  public abstract int getOrDefault(char paramChar, int paramInt);
  
  public abstract int putAll(CharIntAssociativeContainer paramCharIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharIntCursor> paramIterable);
  
  public abstract int remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */