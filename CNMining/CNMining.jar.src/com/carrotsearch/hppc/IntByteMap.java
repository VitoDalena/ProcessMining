package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntByteCursor;

public abstract interface IntByteMap
  extends IntByteAssociativeContainer
{
  public abstract byte put(int paramInt, byte paramByte);
  
  public abstract byte addTo(int paramInt, byte paramByte);
  
  public abstract byte putOrAdd(int paramInt, byte paramByte1, byte paramByte2);
  
  public abstract byte get(int paramInt);
  
  public abstract byte getOrDefault(int paramInt, byte paramByte);
  
  public abstract int putAll(IntByteAssociativeContainer paramIntByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends IntByteCursor> paramIterable);
  
  public abstract byte remove(int paramInt);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */