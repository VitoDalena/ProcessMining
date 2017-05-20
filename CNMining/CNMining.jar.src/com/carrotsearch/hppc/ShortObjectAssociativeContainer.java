package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortObjectCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortObjectProcedure;
import java.util.Iterator;

public abstract interface ShortObjectAssociativeContainer<VType>
  extends Iterable<ShortObjectCursor<VType>>
{
  public abstract Iterator<ShortObjectCursor<VType>> iterator();
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ShortContainer paramShortContainer);
  
  public abstract int removeAll(ShortPredicate paramShortPredicate);
  
  public abstract <T extends ShortObjectProcedure<? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ShortCollection keys();
  
  public abstract ObjectContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortObjectAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */