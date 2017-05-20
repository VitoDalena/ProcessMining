package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortFloatCursor;

public abstract interface ShortFloatMap
  extends ShortFloatAssociativeContainer
{
  public abstract float put(short paramShort, float paramFloat);
  
  public abstract float addTo(short paramShort, float paramFloat);
  
  public abstract float putOrAdd(short paramShort, float paramFloat1, float paramFloat2);
  
  public abstract float get(short paramShort);
  
  public abstract float getOrDefault(short paramShort, float paramFloat);
  
  public abstract int putAll(ShortFloatAssociativeContainer paramShortFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortFloatCursor> paramIterable);
  
  public abstract float remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */