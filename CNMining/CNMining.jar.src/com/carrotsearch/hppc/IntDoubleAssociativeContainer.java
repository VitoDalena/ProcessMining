package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntDoubleCursor;
import com.carrotsearch.hppc.predicates.IntPredicate;
import com.carrotsearch.hppc.procedures.IntDoubleProcedure;
import java.util.Iterator;

public abstract interface IntDoubleAssociativeContainer
  extends Iterable<IntDoubleCursor>
{
  public abstract Iterator<IntDoubleCursor> iterator();
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(IntContainer paramIntContainer);
  
  public abstract int removeAll(IntPredicate paramIntPredicate);
  
  public abstract <T extends IntDoubleProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract IntCollection keys();
  
  public abstract DoubleContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntDoubleAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */