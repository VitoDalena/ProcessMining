package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ObjectCursor;
import com.carrotsearch.hppc.predicates.ObjectPredicate;
import com.carrotsearch.hppc.procedures.ObjectProcedure;
import java.util.Iterator;

public abstract interface ObjectDeque<KType>
  extends ObjectCollection<KType>
{
  public abstract int removeFirstOccurrence(KType paramKType);
  
  public abstract int removeLastOccurrence(KType paramKType);
  
  public abstract void addFirst(KType paramKType);
  
  public abstract void addLast(KType paramKType);
  
  public abstract KType removeFirst();
  
  public abstract KType removeLast();
  
  public abstract KType getFirst();
  
  public abstract KType getLast();
  
  public abstract Iterator<ObjectCursor<KType>> descendingIterator();
  
  public abstract <T extends ObjectProcedure<? super KType>> T descendingForEach(T paramT);
  
  public abstract <T extends ObjectPredicate<? super KType>> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ObjectDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */