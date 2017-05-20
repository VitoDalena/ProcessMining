package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharByteCursor;

public abstract interface CharByteMap
  extends CharByteAssociativeContainer
{
  public abstract byte put(char paramChar, byte paramByte);
  
  public abstract byte addTo(char paramChar, byte paramByte);
  
  public abstract byte putOrAdd(char paramChar, byte paramByte1, byte paramByte2);
  
  public abstract byte get(char paramChar);
  
  public abstract byte getOrDefault(char paramChar, byte paramByte);
  
  public abstract int putAll(CharByteAssociativeContainer paramCharByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends CharByteCursor> paramIterable);
  
  public abstract byte remove(char paramChar);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */