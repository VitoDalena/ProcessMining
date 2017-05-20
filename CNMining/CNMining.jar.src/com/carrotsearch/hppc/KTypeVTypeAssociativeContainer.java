package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.KTypeVTypeCursor;
import com.carrotsearch.hppc.predicates.KTypePredicate;
import com.carrotsearch.hppc.procedures.KTypeVTypeProcedure;
import java.util.Iterator;

public abstract interface KTypeVTypeAssociativeContainer<KType, VType>
  extends Iterable<KTypeVTypeCursor<KType, VType>>
{
  public abstract Iterator<KTypeVTypeCursor<KType, VType>> iterator();
  
  public abstract boolean containsKey(KType paramKType);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(KTypeContainer<? extends KType> paramKTypeContainer);
  
  public abstract int removeAll(KTypePredicate<? super KType> paramKTypePredicate);
  
  public abstract <T extends KTypeVTypeProcedure<? super KType, ? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract KTypeCollection<KType> keys();
  
  public abstract KTypeContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeVTypeAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */