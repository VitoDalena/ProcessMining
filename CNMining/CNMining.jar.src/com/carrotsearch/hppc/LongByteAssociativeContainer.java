package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongByteCursor;
import com.carrotsearch.hppc.predicates.LongPredicate;
import com.carrotsearch.hppc.procedures.LongByteProcedure;
import java.util.Iterator;

public abstract interface LongByteAssociativeContainer
  extends Iterable<LongByteCursor>
{
  public abstract Iterator<LongByteCursor> iterator();
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(LongContainer paramLongContainer);
  
  public abstract int removeAll(LongPredicate paramLongPredicate);
  
  public abstract <T extends LongByteProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract LongCollection keys();
  
  public abstract ByteContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongByteAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */