package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatByteCursor;

public abstract interface FloatByteMap
  extends FloatByteAssociativeContainer
{
  public abstract byte put(float paramFloat, byte paramByte);
  
  public abstract byte addTo(float paramFloat, byte paramByte);
  
  public abstract byte putOrAdd(float paramFloat, byte paramByte1, byte paramByte2);
  
  public abstract byte get(float paramFloat);
  
  public abstract byte getOrDefault(float paramFloat, byte paramByte);
  
  public abstract int putAll(FloatByteAssociativeContainer paramFloatByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends FloatByteCursor> paramIterable);
  
  public abstract byte remove(float paramFloat);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */