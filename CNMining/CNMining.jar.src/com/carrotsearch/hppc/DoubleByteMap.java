package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleByteCursor;

public abstract interface DoubleByteMap
  extends DoubleByteAssociativeContainer
{
  public abstract byte put(double paramDouble, byte paramByte);
  
  public abstract byte addTo(double paramDouble, byte paramByte);
  
  public abstract byte putOrAdd(double paramDouble, byte paramByte1, byte paramByte2);
  
  public abstract byte get(double paramDouble);
  
  public abstract byte getOrDefault(double paramDouble, byte paramByte);
  
  public abstract int putAll(DoubleByteAssociativeContainer paramDoubleByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends DoubleByteCursor> paramIterable);
  
  public abstract byte remove(double paramDouble);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */