package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongCursor;
import com.carrotsearch.hppc.predicates.LongPredicate;
import com.carrotsearch.hppc.procedures.LongProcedure;
import java.util.Iterator;

public abstract interface LongContainer
  extends Iterable<LongCursor>
{
  public abstract Iterator<LongCursor> iterator();
  
  public abstract boolean contains(long paramLong);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract long[] toArray();
  
  public abstract <T extends LongProcedure> T forEach(T paramT);
  
  public abstract <T extends LongPredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */