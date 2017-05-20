package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.ShortPredicate;

public abstract interface ShortCollection
  extends ShortContainer
{
  public abstract int removeAllOccurrences(short paramShort);
  
  public abstract int removeAll(ShortLookupContainer paramShortLookupContainer);
  
  public abstract int removeAll(ShortPredicate paramShortPredicate);
  
  public abstract int retainAll(ShortLookupContainer paramShortLookupContainer);
  
  public abstract int retainAll(ShortPredicate paramShortPredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */