package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.IntPredicate;

public abstract interface IntCollection
  extends IntContainer
{
  public abstract int removeAllOccurrences(int paramInt);
  
  public abstract int removeAll(IntLookupContainer paramIntLookupContainer);
  
  public abstract int removeAll(IntPredicate paramIntPredicate);
  
  public abstract int retainAll(IntLookupContainer paramIntLookupContainer);
  
  public abstract int retainAll(IntPredicate paramIntPredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */