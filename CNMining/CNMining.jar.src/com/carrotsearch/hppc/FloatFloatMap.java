package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatFloatCursor;

public abstract interface FloatFloatMap
  extends FloatFloatAssociativeContainer
{
  public abstract float put(float paramFloat1, float paramFloat2);
  
  public abstract float addTo(float paramFloat1, float paramFloat2);
  
  public abstract float putOrAdd(float paramFloat1, float paramFloat2, float paramFloat3);
  
  public abstract float get(float paramFloat);
  
  public abstract float getOrDefault(float paramFloat1, float paramFloat2);
  
  public abstract int putAll(FloatFloatAssociativeContainer paramFloatFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatFloatCursor> paramIterable);
  
  public abstract float remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */