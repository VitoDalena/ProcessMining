package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface CharIndexedContainer
  extends CharCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(char paramChar);
  
  public abstract int removeLastOccurrence(char paramChar);
  
  public abstract int indexOf(char paramChar);
  
  public abstract int lastIndexOf(char paramChar);
  
  public abstract void add(char paramChar);
  
  public abstract void insert(int paramInt, char paramChar);
  
  public abstract char set(int paramInt, char paramChar);
  
  public abstract char get(int paramInt);
  
  public abstract char remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */