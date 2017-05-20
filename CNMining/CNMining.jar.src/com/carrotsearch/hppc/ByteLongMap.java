package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteLongCursor;

public abstract interface ByteLongMap
  extends ByteLongAssociativeContainer
{
  public abstract long put(byte paramByte, long paramLong);
  
  public abstract long addTo(byte paramByte, long paramLong);
  
  public abstract long putOrAdd(byte paramByte, long paramLong1, long paramLong2);
  
  public abstract long get(byte paramByte);
  
  public abstract long getOrDefault(byte paramByte, long paramLong);
  
  public abstract int putAll(ByteLongAssociativeContainer paramByteLongAssociativeContainer);
  
  public abstract int putAll(Iterable<? extends ByteLongCursor> paramIterable);
  
  public abstract long remove(byte paramByte);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteLongMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */