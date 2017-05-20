package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface IntIndexedContainer
  extends IntCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(int paramInt);
  
  public abstract int removeLastOccurrence(int paramInt);
  
  public abstract int indexOf(int paramInt);
  
  public abstract int lastIndexOf(int paramInt);
  
  public abstract void add(int paramInt);
  
  public abstract void insert(int paramInt1, int paramInt2);
  
  public abstract int set(int paramInt1, int paramInt2);
  
  public abstract int get(int paramInt);
  
  public abstract int remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */