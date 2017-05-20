package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortProcedure;
import java.util.Iterator;

public abstract interface ShortDeque
  extends ShortCollection
{
  public abstract int removeFirstOccurrence(short paramShort);
  
  public abstract int removeLastOccurrence(short paramShort);
  
  public abstract void addFirst(short paramShort);
  
  public abstract void addLast(short paramShort);
  
  public abstract short removeFirst();
  
  public abstract short removeLast();
  
  public abstract short getFirst();
  
  public abstract short getLast();
  
  public abstract Iterator<ShortCursor> descendingIterator();
  
  public abstract <T extends ShortProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends ShortPredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */