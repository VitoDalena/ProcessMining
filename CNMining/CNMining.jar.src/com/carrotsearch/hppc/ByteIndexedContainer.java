package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface ByteIndexedContainer
  extends ByteCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(byte paramByte);
  
  public abstract int removeLastOccurrence(byte paramByte);
  
  public abstract int indexOf(byte paramByte);
  
  public abstract int lastIndexOf(byte paramByte);
  
  public abstract void add(byte paramByte);
  
  public abstract void insert(int paramInt, byte paramByte);
  
  public abstract byte set(int paramInt, byte paramByte);
  
  public abstract byte get(int paramInt);
  
  public abstract byte remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */