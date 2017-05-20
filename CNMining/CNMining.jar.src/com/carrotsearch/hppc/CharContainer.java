package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharCursor;
import com.carrotsearch.hppc.predicates.CharPredicate;
import com.carrotsearch.hppc.procedures.CharProcedure;
import java.util.Iterator;

public abstract interface CharContainer
  extends Iterable<CharCursor>
{
  public abstract Iterator<CharCursor> iterator();
  
  public abstract boolean contains(char paramChar);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract char[] toArray();
  
  public abstract <T extends CharProcedure> T forEach(T paramT);
  
  public abstract <T extends CharPredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */