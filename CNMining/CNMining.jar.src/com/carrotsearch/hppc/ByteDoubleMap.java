package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteDoubleCursor;

public abstract interface ByteDoubleMap
  extends ByteDoubleAssociativeContainer
{
  public abstract double put(byte paramByte, double paramDouble);
  
  public abstract double addTo(byte paramByte, double paramDouble);
  
  public abstract double putOrAdd(byte paramByte, double paramDouble1, double paramDouble2);
  
  public abstract double get(byte paramByte);
  
  public abstract double getOrDefault(byte paramByte, double paramDouble);
  
  public abstract int putAll(ByteDoubleAssociativeContainer paramByteDoubleAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteDoubleCursor> paramIterable);
  
  public abstract double remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteDoubleMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */