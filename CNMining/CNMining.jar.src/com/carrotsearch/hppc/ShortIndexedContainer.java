package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface ShortIndexedContainer
  extends ShortCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(short paramShort);
  
  public abstract int removeLastOccurrence(short paramShort);
  
  public abstract int indexOf(short paramShort);
  
  public abstract int lastIndexOf(short paramShort);
  
  public abstract void add(short paramShort);
  
  public abstract void insert(int paramInt, short paramShort);
  
  public abstract short set(int paramInt, short paramShort);
  
  public abstract short get(int paramInt);
  
  public abstract short remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */