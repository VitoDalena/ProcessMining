package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface LongIndexedContainer
  extends LongCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(long paramLong);
  
  public abstract int removeLastOccurrence(long paramLong);
  
  public abstract int indexOf(long paramLong);
  
  public abstract int lastIndexOf(long paramLong);
  
  public abstract void add(long paramLong);
  
  public abstract void insert(int paramInt, long paramLong);
  
  public abstract long set(int paramInt, long paramLong);
  
  public abstract long get(int paramInt);
  
  public abstract long remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */