package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharLongCursor;
import com.carrotsearch.hppc.predicates.CharPredicate;
import com.carrotsearch.hppc.procedures.CharLongProcedure;
import java.util.Iterator;

public abstract interface CharLongAssociativeContainer
  extends Iterable<CharLongCursor>
{
  public abstract Iterator<CharLongCursor> iterator();
  
  public abstract boolean containsKey(char paramChar);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(CharContainer paramCharContainer);
  
  public abstract int removeAll(CharPredicate paramCharPredicate);
  
  public abstract <T extends CharLongProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract CharCollection keys();
  
  public abstract LongContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharLongAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */