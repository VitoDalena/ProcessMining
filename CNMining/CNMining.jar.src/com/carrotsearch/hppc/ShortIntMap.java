package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortIntCursor;

public abstract interface ShortIntMap
  extends ShortIntAssociativeContainer
{
  public abstract int put(short paramShort, int paramInt);
  
  public abstract int addTo(short paramShort, int paramInt);
  
  public abstract int putOrAdd(short paramShort, int paramInt1, int paramInt2);
  
  public abstract int get(short paramShort);
  
  public abstract int getOrDefault(short paramShort, int paramInt);
  
  public abstract int putAll(ShortIntAssociativeContainer paramShortIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortIntCursor> paramIterable);
  
  public abstract int remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */