package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteObjectCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteObjectProcedure;
import java.util.Iterator;

public abstract interface ByteObjectAssociativeContainer<VType>
  extends Iterable<ByteObjectCursor<VType>>
{
  public abstract Iterator<ByteObjectCursor<VType>> iterator();
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ByteContainer paramByteContainer);
  
  public abstract int removeAll(BytePredicate paramBytePredicate);
  
  public abstract <T extends ByteObjectProcedure<? super VType>> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ByteCollection keys();
  
  public abstract ObjectContainer<VType> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteObjectAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */