package com.carrotsearch.hppc;

import com.carrotsearch.hppc.predicates.ObjectPredicate;

public abstract interface ObjectCollection<KType>
  extends ObjectContainer<KType>
{
  public abstract int removeAllOccurrences(KType paramKType);
  
  public abstract int removeAll(ObjectLookupContainer<? extends KType> paramObjectLookupContainer);
  
  public abstract int removeAll(ObjectPredicate<? super KType> paramObjectPredicate);
  
  public abstract int retainAll(ObjectLookupContainer<? extends KType> paramObjectLookupContainer);
  
  public abstract int retainAll(ObjectPredicate<? super KType> paramObjectPredicate);
  
  public abstract void clear();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectCollection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */