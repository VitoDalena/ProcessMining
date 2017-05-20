package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectIntCursor;
import com.carrotsearch.hppc.predicates.ObjectPredicate;
import com.carrotsearch.hppc.procedures.ObjectIntProcedure;
import java.util.Iterator;

public abstract interface ObjectIntAssociativeContainer<KType>
  extends Iterable<ObjectIntCursor<KType>>
{
  public abstract Iterator<ObjectIntCursor<KType>> iterator();
  
  public abstract boolean containsKey(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ObjectContainer<? extends KType> paramObjectContainer);
  
  public abstract int removeAll(ObjectPredicate<? super KType> paramObjectPredicate);
  
  public abstract <T extends ObjectIntProcedure<? super KType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ObjectCollection<KType> keys();
  
  public abstract IntContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectIntAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */