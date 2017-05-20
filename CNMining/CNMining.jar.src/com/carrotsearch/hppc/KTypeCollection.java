package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.KTypePredicate;

public abstract interface KTypeCollection<KType>
  extends KTypeContainer<KType>
{
  public abstract int removeAllOccurrences(KType paramKType);
  
  public abstract int removeAll(KTypeLookupContainer<? extends KType> paramKTypeLookupContainer);
  
  public abstract int removeAll(KTypePredicate<? super KType> paramKTypePredicate);
  
  public abstract int retainAll(KTypeLookupContainer<? extends KType> paramKTypeLookupContainer);
  
  public abstract int retainAll(KTypePredicate<? super KType> paramKTypePredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */