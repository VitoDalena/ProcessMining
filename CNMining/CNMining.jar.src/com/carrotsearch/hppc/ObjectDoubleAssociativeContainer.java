package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectDoubleCursor;
import com.carrotsearch.hppc.predicates.ObjectPredicate;
import com.carrotsearch.hppc.procedures.ObjectDoubleProcedure;
import java.util.Iterator;

public abstract interface ObjectDoubleAssociativeContainer<KType>
  extends Iterable<ObjectDoubleCursor<KType>>
{
  public abstract Iterator<ObjectDoubleCursor<KType>> iterator();
  
  public abstract boolean containsKey(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ObjectContainer<? extends KType> paramObjectContainer);
  
  public abstract int removeAll(ObjectPredicate<? super KType> paramObjectPredicate);
  
  public abstract <T extends ObjectDoubleProcedure<? super KType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ObjectCollection<KType> keys();
  
  public abstract DoubleContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectDoubleAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */