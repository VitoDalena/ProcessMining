package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteCharCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteCharProcedure;
import java.util.Iterator;

public abstract interface ByteCharAssociativeContainer
  extends Iterable<ByteCharCursor>
{
  public abstract Iterator<ByteCharCursor> iterator();
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ByteContainer paramByteContainer);
  
  public abstract int removeAll(BytePredicate paramBytePredicate);
  
  public abstract <T extends ByteCharProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ByteCollection keys();
  
  public abstract CharContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteCharAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */