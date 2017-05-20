package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteProcedure;
import java.util.Iterator;

public abstract interface ByteDeque
  extends ByteCollection
{
  public abstract int removeFirstOccurrence(byte paramByte);
  
  public abstract int removeLastOccurrence(byte paramByte);
  
  public abstract void addFirst(byte paramByte);
  
  public abstract void addLast(byte paramByte);
  
  public abstract byte removeFirst();
  
  public abstract byte removeLast();
  
  public abstract byte getFirst();
  
  public abstract byte getLast();
  
  public abstract Iterator<ByteCursor> descendingIterator();
  
  public abstract <T extends ByteProcedure> T descendingForEach(T paramT);
  
  public abstract <T extends BytePredicate> T descendingForEach(T paramT);
  
  public abstract boolean equals(Object paramObject);
  
  public abstract int hashCode();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteDeque.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */