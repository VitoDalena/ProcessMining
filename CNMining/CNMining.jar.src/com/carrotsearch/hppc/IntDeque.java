package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntCursor;
import com.carrotsearch.hppc.predicates.IntPredicate;
import com.carrotsearch.hppc.procedures.IntProcedure;
import java.util.Iterator;

public abstract interface IntDeque
  extends IntCollection
{
  public abstract int removeFirstOccurrence(int paramInt);
  
  public abstract int removeLastOccurrence(int paramInt);
  
  public abstract void addFirst(int paramInt);
  
  public abstract void addLast(int paramInt);
  
  public abstract int removeFirst();
  
  public abstract int removeLast();
  
  public abstract int getFirst();
  
  public abstract int getLast();
  
  public abstract Iterator<IntCursor> descendingIterator();
  
  public abstract <T extends IntProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends IntPredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */