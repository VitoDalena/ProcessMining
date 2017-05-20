package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleCursor;
import com.carrotsearch.hppc.predicates.DoublePredicate;
import com.carrotsearch.hppc.procedures.DoubleProcedure;
import java.util.Iterator;

public abstract interface DoubleDeque
  extends DoubleCollection
{
  public abstract int removeFirstOccurrence(double paramDouble);
  
  public abstract int removeLastOccurrence(double paramDouble);
  
  public abstract void addFirst(double paramDouble);
  
  public abstract void addLast(double paramDouble);
  
  public abstract double removeFirst();
  
  public abstract double removeLast();
  
  public abstract double getFirst();
  
  public abstract double getLast();
  
  public abstract Iterator<DoubleCursor> descendingIterator();
  
  public abstract <T extends DoubleProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends DoublePredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */