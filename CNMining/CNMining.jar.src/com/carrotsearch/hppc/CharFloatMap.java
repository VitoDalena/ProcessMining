package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharFloatCursor;

public abstract interface CharFloatMap
  extends CharFloatAssociativeContainer
{
  public abstract float put(char paramChar, float paramFloat);
  
  public abstract float addTo(char paramChar, float paramFloat);
  
  public abstract float putOrAdd(char paramChar, float paramFloat1, float paramFloat2);
  
  public abstract float get(char paramChar);
  
  public abstract float getOrDefault(char paramChar, float paramFloat);
  
  public abstract int putAll(CharFloatAssociativeContainer paramCharFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharFloatCursor> paramIterable);
  
  public abstract float remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */