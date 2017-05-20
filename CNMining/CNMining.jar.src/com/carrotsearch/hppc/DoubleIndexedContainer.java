package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface DoubleIndexedContainer
  extends DoubleCollection, RandomAccess
{
  public abstract int removeFirstOccurrence(double paramDouble);
  
  public abstract int removeLastOccurrence(double paramDouble);
  
  public abstract int indexOf(double paramDouble);
  
  public abstract int lastIndexOf(double paramDouble);
  
  public abstract void add(double paramDouble);
  
  public abstract void insert(int paramInt, double paramDouble);
  
  public abstract double set(int paramInt, double paramDouble);
  
  public abstract double get(int paramInt);
  
  public abstract double remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */