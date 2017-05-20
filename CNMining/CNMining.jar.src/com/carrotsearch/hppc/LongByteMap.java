package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongByteCursor;

public abstract interface LongByteMap
  extends LongByteAssociativeContainer
{
  public abstract byte put(long paramLong, byte paramByte);
  
  public abstract byte addTo(long paramLong, byte paramByte);
  
  public abstract byte putOrAdd(long paramLong, byte paramByte1, byte paramByte2);
  
  public abstract byte get(long paramLong);
  
  public abstract byte getOrDefault(long paramLong, byte paramByte);
  
  public abstract int putAll(LongByteAssociativeContainer paramLongByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends LongByteCursor> paramIterable);
  
  public abstract byte remove(long paramLong);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */