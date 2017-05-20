package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.LongDoubleCursor;
import com.carrotsearch.hppc.predicates.LongPredicate;
import com.carrotsearch.hppc.procedures.LongDoubleProcedure;
import java.util.Iterator;

public abstract interface LongDoubleAssociativeContainer
  extends Iterable<LongDoubleCursor>
{
  public abstract Iterator<LongDoubleCursor> iterator();
  
  public abstract boolean containsKey(long paramLong);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(LongContainer paramLongContainer);
  
  public abstract int removeAll(LongPredicate paramLongPredicate);
  
  public abstract <T extends LongDoubleProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract LongCollection keys();
  
  public abstract DoubleContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/LongDoubleAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */