package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.KTypeCursor;
import com.carrotsearch.hppc.predicates.KTypePredicate;
import com.carrotsearch.hppc.procedures.KTypeProcedure;
import java.util.Iterator;

public abstract interface KTypeContainer<KType>
  extends Iterable<KTypeCursor<KType>>
{
  public abstract Iterator<KTypeCursor<KType>> iterator();
  
  public abstract boolean contains(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract KType[] toArray(Class<? super KType> paramClass);
  
  public abstract Object[] toArray();
  
  public abstract <T extends KTypeProcedure<? super KType>> T forEach(T paramT);
  
  public abstract <T extends KTypePredicate<? super KType>> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */