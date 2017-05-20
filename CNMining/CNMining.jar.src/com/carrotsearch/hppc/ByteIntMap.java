package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteIntCursor;

public abstract interface ByteIntMap
  extends ByteIntAssociativeContainer
{
  public abstract int put(byte paramByte, int paramInt);
  
  public abstract int addTo(byte paramByte, int paramInt);
  
  public abstract int putOrAdd(byte paramByte, int paramInt1, int paramInt2);
  
  public abstract int get(byte paramByte);
  
  public abstract int getOrDefault(byte paramByte, int paramInt);
  
  public abstract int putAll(ByteIntAssociativeContainer paramByteIntAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteIntCursor> paramIterable);
  
  public abstract int remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */