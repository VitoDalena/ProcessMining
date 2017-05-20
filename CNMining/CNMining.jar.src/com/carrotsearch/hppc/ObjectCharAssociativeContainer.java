package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectCharCursor;
import com.carrotsearch.hppc.predicates.ObjectPredicate;
import com.carrotsearch.hppc.procedures.ObjectCharProcedure;
import java.util.Iterator;

public abstract interface ObjectCharAssociativeContainer<KType>
  extends Iterable<ObjectCharCursor<KType>>
{
  public abstract Iterator<ObjectCharCursor<KType>> iterator();
  
  public abstract boolean containsKey(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ObjectContainer<? extends KType> paramObjectContainer);
  
  public abstract int removeAll(ObjectPredicate<? super KType> paramObjectPredicate);
  
  public abstract <T extends ObjectCharProcedure<? super KType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ObjectCollection<KType> keys();
  
  public abstract CharContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectCharAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */