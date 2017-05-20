package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface FloatIndexedContainer
  extends FloatCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(float paramFloat);
  
  public abstract int removeLastOccurrence(float paramFloat);
  
  public abstract int indexOf(float paramFloat);
  
  public abstract int lastIndexOf(float paramFloat);
  
  public abstract void add(float paramFloat);
  
  public abstract void insert(int paramInt, float paramFloat);
  
  public abstract float set(int paramInt, float paramFloat);
  
  public abstract float get(int paramInt);
  
  public abstract float remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */