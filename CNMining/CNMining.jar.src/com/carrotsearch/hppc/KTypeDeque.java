package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.KTypeCursor;
import com.carrotsearch.hppc.predicates.KTypePredicate;
import com.carrotsearch.hppc.procedures.KTypeProcedure;
import java.util.Iterator;

public abstract interface KTypeDeque<KType>
  extends KTypeCollection<KType>
{
  public abstract int removeFirstOccurrence(KType paramKType);
  
  public abstract int removeLastOccurrence(KType paramKType);
  
  public abstract void addFirst(KType paramKType);
  
  public abstract void addLast(KType paramKType);
  
  public abstract KType removeFirst();
  
  public abstract KType removeLast();
  
  public abstract KType getFirst();
  
  public abstract KType getLast();
  
  public abstract Iterator<KTypeCursor<KType>> descendingIterator();
  
  public abstract <T extends KTypeProcedure<? super KType>> T descendingForEach(T paramT);
  
  public abstract <T extends KTypePredicate<? super KType>> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/KTypeDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */