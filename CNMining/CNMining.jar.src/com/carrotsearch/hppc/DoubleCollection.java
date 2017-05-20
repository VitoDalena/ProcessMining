package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.DoublePredicate;

public abstract interface DoubleCollection
  extends DoubleContainer
{
  public abstract int removeAllOccurrences(double paramDouble);
  
  public abstract int removeAll(DoubleLookupContainer paramDoubleLookupContainer);
  
  public abstract int removeAll(DoublePredicate paramDoublePredicate);
  
  public abstract int retainAll(DoubleLookupContainer paramDoubleLookupContainer);
  
  public abstract int retainAll(DoublePredicate paramDoublePredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */