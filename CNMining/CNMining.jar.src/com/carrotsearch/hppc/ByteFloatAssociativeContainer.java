package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteFloatCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteFloatProcedure;
import java.util.Iterator;

public abstract interface ByteFloatAssociativeContainer
  extends Iterable<ByteFloatCursor>
{
  public abstract Iterator<ByteFloatCursor> iterator();
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ByteContainer paramByteContainer);
  
  public abstract int removeAll(BytePredicate paramBytePredicate);
  
  public abstract <T extends ByteFloatProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ByteCollection keys();
  
  public abstract FloatContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteFloatAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */