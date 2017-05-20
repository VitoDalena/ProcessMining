package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatObjectCursor;
import com.carrotsearch.hppc.predicates.FloatPredicate;
import com.carrotsearch.hppc.procedures.FloatObjectProcedure;
import java.util.Iterator;

public abstract interface FloatObjectAssociativeContainer<VType>
  extends Iterable<FloatObjectCursor<VType>>
{
  public abstract Iterator<FloatObjectCursor<VType>> iterator();
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(FloatContainer paramFloatContainer);
  
  public abstract int removeAll(FloatPredicate paramFloatPredicate);
  
  public abstract <T extends FloatObjectProcedure<? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract FloatCollection keys();
  
  public abstract ObjectContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatObjectAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */