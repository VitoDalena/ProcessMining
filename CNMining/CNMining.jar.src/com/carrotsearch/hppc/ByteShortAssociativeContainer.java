package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteShortCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteShortProcedure;
import java.util.Iterator;

public abstract interface ByteShortAssociativeContainer
  extends Iterable<ByteShortCursor>
{
  public abstract Iterator<ByteShortCursor> iterator();
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ByteContainer paramByteContainer);
  
  public abstract int removeAll(BytePredicate paramBytePredicate);
  
  public abstract <T extends ByteShortProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ByteCollection keys();
  
  public abstract ShortContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteShortAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */