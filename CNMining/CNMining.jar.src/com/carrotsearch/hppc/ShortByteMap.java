package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortByteCursor;

public abstract interface ShortByteMap
  extends ShortByteAssociativeContainer
{
  public abstract byte put(short paramShort, byte paramByte);
  
  public abstract byte addTo(short paramShort, byte paramByte);
  
  public abstract byte putOrAdd(short paramShort, byte paramByte1, byte paramByte2);
  
  public abstract byte get(short paramShort);
  
  public abstract byte getOrDefault(short paramShort, byte paramByte);
  
  public abstract int putAll(ShortByteAssociativeContainer paramShortByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ShortByteCursor> paramIterable);
  
  public abstract byte remove(short paramShort);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */