package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectFloatCursor;
import com.carrotsearch.hppc.predicates.ObjectPredicate;
import com.carrotsearch.hppc.procedures.ObjectFloatProcedure;
import java.util.Iterator;

public abstract interface ObjectFloatAssociativeContainer<KType>
  extends Iterable<ObjectFloatCursor<KType>>
{
  public abstract Iterator<ObjectFloatCursor<KType>> iterator();
  
  public abstract boolean containsKey(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ObjectContainer<? extends KType> paramObjectContainer);
  
  public abstract int removeAll(ObjectPredicate<? super KType> paramObjectPredicate);
  
  public abstract <T extends ObjectFloatProcedure<? super KType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ObjectCollection<KType> keys();
  
  public abstract FloatContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectFloatAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */