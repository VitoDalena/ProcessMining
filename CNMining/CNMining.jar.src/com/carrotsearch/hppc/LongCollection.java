package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.LongPredicate;

public abstract interface LongCollection
  extends LongContainer
{
  public abstract int removeAllOccurrences(long paramLong);
  
  public abstract int removeAll(LongLookupContainer paramLongLookupContainer);
  
  public abstract int removeAll(LongPredicate paramLongPredicate);
  
  public abstract int retainAll(LongLookupContainer paramLongLookupContainer);
  
  public abstract int retainAll(LongPredicate paramLongPredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */