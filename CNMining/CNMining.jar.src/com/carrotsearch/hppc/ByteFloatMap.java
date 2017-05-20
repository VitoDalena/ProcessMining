package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteFloatCursor;

public abstract interface ByteFloatMap
  extends ByteFloatAssociativeContainer
{
  public abstract float put(byte paramByte, float paramFloat);
  
  public abstract float addTo(byte paramByte, float paramFloat);
  
  public abstract float putOrAdd(byte paramByte, float paramFloat1, float paramFloat2);
  
  public abstract float get(byte paramByte);
  
  public abstract float getOrDefault(byte paramByte, float paramFloat);
  
  public abstract int putAll(ByteFloatAssociativeContainer paramByteFloatAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteFloatCursor> paramIterable);
  
  public abstract float remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteFloatMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */