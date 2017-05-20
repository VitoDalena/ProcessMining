package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleObjectCursor;
import com.carrotsearch.hppc.predicates.DoublePredicate;
import com.carrotsearch.hppc.procedures.DoubleObjectProcedure;
import java.util.Iterator;

public abstract interface DoubleObjectAssociativeContainer<VType>
  extends Iterable<DoubleObjectCursor<VType>>
{
  public abstract Iterator<DoubleObjectCursor<VType>> iterator();
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(DoubleContainer paramDoubleContainer);
  
  public abstract int removeAll(DoublePredicate paramDoublePredicate);
  
  public abstract <T extends DoubleObjectProcedure<? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract DoubleCollection keys();
  
  public abstract ObjectContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleObjectAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */