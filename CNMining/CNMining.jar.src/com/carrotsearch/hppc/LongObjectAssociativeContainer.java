package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongObjectCursor;
import com.carrotsearch.hppc.predicates.LongPredicate;
import com.carrotsearch.hppc.procedures.LongObjectProcedure;
import java.util.Iterator;

public abstract interface LongObjectAssociativeContainer<VType>
  extends Iterable<LongObjectCursor<VType>>
{
  public abstract Iterator<LongObjectCursor<VType>> iterator();
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(LongContainer paramLongContainer);
  
  public abstract int removeAll(LongPredicate paramLongPredicate);
  
  public abstract <T extends LongObjectProcedure<? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract LongCollection keys();
  
  public abstract ObjectContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongObjectAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */