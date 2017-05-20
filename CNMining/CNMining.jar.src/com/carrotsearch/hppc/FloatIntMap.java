package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatIntCursor;

public abstract interface FloatIntMap
  extends FloatIntAssociativeContainer
{
  public abstract int put(float paramFloat, int paramInt);
  
  public abstract int addTo(float paramFloat, int paramInt);
  
  public abstract int putOrAdd(float paramFloat, int paramInt1, int paramInt2);
  
  public abstract int get(float paramFloat);
  
  public abstract int getOrDefault(float paramFloat, int paramInt);
  
  public abstract int putAll(FloatIntAssociativeContainer paramFloatIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatIntCursor> paramIterable);
  
  public abstract int remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */