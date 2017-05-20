package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteCharCursor;

public abstract interface ByteCharMap
  extends ByteCharAssociativeContainer
{
  public abstract char put(byte paramByte, char paramChar);
  
  public abstract char addTo(byte paramByte, char paramChar);
  
  public abstract char putOrAdd(byte paramByte, char paramChar1, char paramChar2);
  
  public abstract char get(byte paramByte);
  
  public abstract char getOrDefault(byte paramByte, char paramChar);
  
  public abstract int putAll(ByteCharAssociativeContainer paramByteCharAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteCharCursor> paramIterable);
  
  public abstract char remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteCharMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */