package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.CharCursor;
import com.carrotsearch.hppc.predicates.CharPredicate;
import com.carrotsearch.hppc.procedures.CharProcedure;
import java.util.Iterator;

public abstract interface CharDeque
  extends CharCollection
{
  public abstract int removeFirstOccurrence(char paramChar);
  
  public abstract int removeLastOccurrence(char paramChar);
  
  public abstract void addFirst(char paramChar);
  
  public abstract void addLast(char paramChar);
  
  public abstract char removeFirst();
  
  public abstract char removeLast();
  
  public abstract char getFirst();
  
  public abstract char getLast();
  
  public abstract Iterator<CharCursor> descendingIterator();
  
  public abstract <T extends CharProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends CharPredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/CharDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */