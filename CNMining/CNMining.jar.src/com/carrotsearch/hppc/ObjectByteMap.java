package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectByteCursor;

public abstract interface ObjectByteMap<KType>
  extends ObjectByteAssociativeContainer<KType>
{
  public abstract byte put(KType paramKType, byte paramByte);
  
  public abstract byte addTo(KType paramKType, byte paramByte);
  
  public abstract byte putOrAdd(KType paramKType, byte paramByte1, byte paramByte2);
  
  public abstract byte get(KType paramKType);
  
  public abstract byte getOrDefault(KType paramKType, byte paramByte);
  
  public abstract int putAll(ObjectByteAssociativeContainer<? extends KType> paramObjectByteAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ObjectByteCursor<? extends KType>> paramIterable);
  
  public abstract byte remove(KType paramKType);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectByteMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */