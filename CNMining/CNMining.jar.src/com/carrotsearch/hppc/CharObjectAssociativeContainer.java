package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharObjectCursor;
import com.carrotsearch.hppc.predicates.CharPredicate;
import com.carrotsearch.hppc.procedures.CharObjectProcedure;
import java.util.Iterator;

public abstract interface CharObjectAssociativeContainer<VType>
  extends Iterable<CharObjectCursor<VType>>
{
  public abstract Iterator<CharObjectCursor<VType>> iterator();
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(CharContainer paramCharContainer);
  
  public abstract int removeAll(CharPredicate paramCharPredicate);
  
  public abstract <T extends CharObjectProcedure<? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract CharCollection keys();
  
  public abstract ObjectContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharObjectAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */