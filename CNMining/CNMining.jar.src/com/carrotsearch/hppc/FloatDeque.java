package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatCursor;
import com.carrotsearch.hppc.predicates.FloatPredicate;
import com.carrotsearch.hppc.procedures.FloatProcedure;
import java.util.Iterator;

public abstract interface FloatDeque
  extends FloatCollection
{
  public abstract int removeFirstOccurrence(float paramFloat);
  
  public abstract int removeLastOccurrence(float paramFloat);
  
  public abstract void addFirst(float paramFloat);
  
  public abstract void addLast(float paramFloat);
  
  public abstract float removeFirst();
  
  public abstract float removeLast();
  
  public abstract float getFirst();
  
  public abstract float getLast();
  
  public abstract Iterator<FloatCursor> descendingIterator();
  
  public abstract <T extends FloatProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends FloatPredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */