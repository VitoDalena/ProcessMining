package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ByteLongCursor;
import com.carrotsearch.hppc.predicates.BytePredicate;
import com.carrotsearch.hppc.procedures.ByteLongProcedure;
import java.util.Iterator;

public abstract interface ByteLongAssociativeContainer
  extends Iterable<ByteLongCursor>
{
  public abstract Iterator<ByteLongCursor> iterator();
  
  public abstract boolean containsKey(byte paramByte);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ByteContainer paramByteContainer);
  
  public abstract int removeAll(BytePredicate paramBytePredicate);
  
  public abstract <T extends ByteLongProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ByteCollection keys();
  
  public abstract LongContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ByteLongAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */