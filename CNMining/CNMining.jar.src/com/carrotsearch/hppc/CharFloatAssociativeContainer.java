package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharFloatCursor;
import com.carrotsearch.hppc.predicates.CharPredicate;
import com.carrotsearch.hppc.procedures.CharFloatProcedure;
import java.util.Iterator;

public abstract interface CharFloatAssociativeContainer
  extends Iterable<CharFloatCursor>
{
  public abstract Iterator<CharFloatCursor> iterator();
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(CharContainer paramCharContainer);
  
  public abstract int removeAll(CharPredicate paramCharPredicate);
  
  public abstract <T extends CharFloatProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract CharCollection keys();
  
  public abstract FloatContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharFloatAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */