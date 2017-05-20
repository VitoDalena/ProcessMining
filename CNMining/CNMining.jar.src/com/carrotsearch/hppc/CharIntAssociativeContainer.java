package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharIntCursor;
import com.carrotsearch.hppc.predicates.CharPredicate;
import com.carrotsearch.hppc.procedures.CharIntProcedure;
import java.util.Iterator;

public abstract interface CharIntAssociativeContainer
  extends Iterable<CharIntCursor>
{
  public abstract Iterator<CharIntCursor> iterator();
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(CharContainer paramCharContainer);
  
  public abstract int removeAll(CharPredicate paramCharPredicate);
  
  public abstract <T extends CharIntProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract CharCollection keys();
  
  public abstract IntContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharIntAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */