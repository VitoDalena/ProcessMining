package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.carrotsearch.hppc.predicates.ObjectPredicate;
import com.carrotsearch.hppc.procedures.ObjectProcedure;
import java.util.Iterator;

public abstract interface ObjectContainer<KType>
  extends Iterable<ObjectCursor<KType>>
{
  public abstract Iterator<ObjectCursor<KType>> iterator();
  
  public abstract boolean contains(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract KType[] toArray(Class<? super KType> paramClass);
  
  public abstract Object[] toArray();
  
  public abstract <T extends ObjectProcedure<? super KType>> T forEach(T paramT);
  
  public abstract <T extends ObjectPredicate<? super KType>> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */