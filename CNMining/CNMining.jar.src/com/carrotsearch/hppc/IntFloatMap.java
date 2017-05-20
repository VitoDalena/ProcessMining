package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntFloatCursor;

public abstract interface IntFloatMap
  extends IntFloatAssociativeContainer
{
  public abstract float put(int paramInt, float paramFloat);
  
  public abstract float addTo(int paramInt, float paramFloat);
  
  public abstract float putOrAdd(int paramInt, float paramFloat1, float paramFloat2);
  
  public abstract float get(int paramInt);
  
  public abstract float getOrDefault(int paramInt, float paramFloat);
  
  public abstract int putAll(IntFloatAssociativeContainer paramIntFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntFloatCursor> paramIterable);
  
  public abstract float remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */