package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.CharPredicate;

public abstract interface CharCollection
  extends CharContainer
{
  public abstract int removeAllOccurrences(char paramChar);
  
  public abstract int removeAll(CharLookupContainer paramCharLookupContainer);
  
  public abstract int removeAll(CharPredicate paramCharPredicate);
  
  public abstract int retainAll(CharLookupContainer paramCharLookupContainer);
  
  public abstract int retainAll(CharPredicate paramCharPredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */