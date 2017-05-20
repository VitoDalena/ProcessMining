package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatShortCursor;

public abstract interface FloatShortMap
  extends FloatShortAssociativeContainer
{
  public abstract short put(float paramFloat, short paramShort);
  
  public abstract short addTo(float paramFloat, short paramShort);
  
  public abstract short putOrAdd(float paramFloat, short paramShort1, short paramShort2);
  
  public abstract short get(float paramFloat);
  
  public abstract short getOrDefault(float paramFloat, short paramShort);
  
  public abstract int putAll(FloatShortAssociativeContainer paramFloatShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatShortCursor> paramIterable);
  
  public abstract short remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */