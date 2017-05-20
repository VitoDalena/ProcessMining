package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.FloatPredicate;

public abstract interface FloatCollection
  extends FloatContainer
{
  public abstract int removeAllOccurrences(float paramFloat);
  
  public abstract int removeAll(FloatLookupContainer paramFloatLookupContainer);
  
  public abstract int removeAll(FloatPredicate paramFloatPredicate);
  
  public abstract int retainAll(FloatLookupContainer paramFloatLookupContainer);
  
  public abstract int retainAll(FloatPredicate paramFloatPredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */