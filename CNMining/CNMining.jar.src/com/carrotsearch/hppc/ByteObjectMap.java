package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteObjectCursor;

public abstract interface ByteObjectMap<VType>
  extends ByteObjectAssociativeContainer<VType>
{
  public abstract VType put(byte paramByte, VType paramVType);
  
  public abstract VType get(byte paramByte);
  
  public abstract VType getOrDefault(byte paramByte, VType paramVType);
  
  public abstract int putAll(ByteObjectAssociativeContainer<? extends VType> paramByteObjectAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteObjectCursor<? extends VType>> paramIterable);
  
  public abstract VType remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteObjectMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */