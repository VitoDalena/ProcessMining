package com.carrotsearch.hppc;

import java.util.RandomAccess;

public abstract interface ObjectIndexedContainer<KType>
  extends ObjectCollection<KType>, RandomAccess
{
  public abstract int removeFirstOccurrence(KType paramKType);
  
  public abstract int removeLastOccurrence(KType paramKType);
  
  public abstract int indexOf(KType paramKType);
  
  public abstract int lastIndexOf(KType paramKType);
  
  public abstract void add(KType paramKType);
  
  public abstract void insert(int paramInt, KType paramKType);
  
  public abstract KType set(int paramInt, KType paramKType);
  
  public abstract KType get(int paramInt);
  
  public abstract KType remove(int paramInt);
  
  public abstract void removeRange(int paramInt1, int paramInt2);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectIndexedContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */