package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharLongCursor;

public abstract interface CharLongMap
  extends CharLongAssociativeContainer
{
  public abstract long put(char paramChar, long paramLong);
  
  public abstract long addTo(char paramChar, long paramLong);
  
  public abstract long putOrAdd(char paramChar, long paramLong1, long paramLong2);
  
  public abstract long get(char paramChar);
  
  public abstract long getOrDefault(char paramChar, long paramLong);
  
  public abstract int putAll(CharLongAssociativeContainer paramCharLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharLongCursor> paramIterable);
  
  public abstract long remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */