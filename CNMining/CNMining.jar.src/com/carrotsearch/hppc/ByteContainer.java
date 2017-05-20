package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteProcedure;
import java.util.Iterator;

public abstract interface ByteContainer
  extends Iterable<ByteCursor>
{
  public abstract Iterator<ByteCursor> iterator();
  
  public abstract boolean contains(byte paramByte);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract byte[] toArray();
  
  public abstract <T extends ByteProcedure> T forEach(T paramT);
  
  public abstract <T extends BytePredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */