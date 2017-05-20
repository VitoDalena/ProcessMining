package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharCharCursor;

public abstract interface CharCharMap
  extends CharCharAssociativeContainer
{
  public abstract char put(char paramChar1, char paramChar2);
  
  public abstract char addTo(char paramChar1, char paramChar2);
  
  public abstract char putOrAdd(char paramChar1, char paramChar2, char paramChar3);
  
  public abstract char get(char paramChar);
  
  public abstract char getOrDefault(char paramChar1, char paramChar2);
  
  public abstract int putAll(CharCharAssociativeContainer paramCharCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharCharCursor> paramIterable);
  
  public abstract char remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */