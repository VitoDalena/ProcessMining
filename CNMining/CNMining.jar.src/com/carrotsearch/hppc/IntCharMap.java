package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntCharCursor;

public abstract interface IntCharMap
  extends IntCharAssociativeContainer
{
  public abstract char put(int paramInt, char paramChar);
  
  public abstract char addTo(int paramInt, char paramChar);
  
  public abstract char putOrAdd(int paramInt, char paramChar1, char paramChar2);
  
  public abstract char get(int paramInt);
  
  public abstract char getOrDefault(int paramInt, char paramChar);
  
  public abstract int putAll(IntCharAssociativeContainer paramIntCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntCharCursor> paramIterable);
  
  public abstract char remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */