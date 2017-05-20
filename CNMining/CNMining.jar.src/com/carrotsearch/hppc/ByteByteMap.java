package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteByteCursor;

public abstract interface ByteByteMap
  extends ByteByteAssociativeContainer
{
  public abstract byte put(byte paramByte1, byte paramByte2);
  
  public abstract byte addTo(byte paramByte1, byte paramByte2);
  
  public abstract byte putOrAdd(byte paramByte1, byte paramByte2, byte paramByte3);
  
  public abstract byte get(byte paramByte);
  
  public abstract byte getOrDefault(byte paramByte1, byte paramByte2);
  
  public abstract int putAll(ByteByteAssociativeContainer paramByteByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteByteCursor> paramIterable);
  
  public abstract byte remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */