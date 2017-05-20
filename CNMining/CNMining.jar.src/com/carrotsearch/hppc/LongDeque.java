package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongCursor;
import com.carrotsearch.hppc.predicates.LongPredicate;
import com.carrotsearch.hppc.procedures.LongProcedure;
import java.util.Iterator;

public abstract interface LongDeque
  extends LongCollection
{
  public abstract int removeFirstOccurrence(long paramLong);
  
  public abstract int removeLastOccurrence(long paramLong);
  
  public abstract void addFirst(long paramLong);
  
  public abstract void addLast(long paramLong);
  
  public abstract long removeFirst();
  
  public abstract long removeLast();
  
  public abstract long getFirst();
  
  public abstract long getLast();
  
  public abstract Iterator<LongCursor> descendingIterator();
  
  public abstract <T extends LongProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends LongPredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */