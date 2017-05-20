package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteShortCursor;

public abstract interface ByteShortMap
  extends ByteShortAssociativeContainer
{
  public abstract short put(byte paramByte, short paramShort);
  
  public abstract short addTo(byte paramByte, short paramShort);
  
  public abstract short putOrAdd(byte paramByte, short paramShort1, short paramShort2);
  
  public abstract short get(byte paramByte);
  
  public abstract short getOrDefault(byte paramByte, short paramShort);
  
  public abstract int putAll(ByteShortAssociativeContainer paramByteShortAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteShortCursor> paramIterable);
  
  public abstract short remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteShortMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */