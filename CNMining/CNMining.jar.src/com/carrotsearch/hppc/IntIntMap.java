package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntIntCursor;

public abstract interface IntIntMap
  extends IntIntAssociativeContainer
{
  public abstract int put(int paramInt1, int paramInt2);
  
  public abstract int addTo(int paramInt1, int paramInt2);
  
  public abstract int putOrAdd(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract int get(int paramInt);
  
  public abstract int getOrDefault(int paramInt1, int paramInt2);
  
  public abstract int putAll(IntIntAssociativeContainer paramIntIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntIntCursor> paramIterable);
  
  public abstract int remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */