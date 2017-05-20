package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntFloatCursor;
import com.carrotsearch.hppc.predicates.IntPredicate;
import com.carrotsearch.hppc.procedures.IntFloatProcedure;
import java.util.Iterator;

public abstract interface IntFloatAssociativeContainer
  extends Iterable<IntFloatCursor>
{
  public abstract Iterator<IntFloatCursor> iterator();
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(IntContainer paramIntContainer);
  
  public abstract int removeAll(IntPredicate paramIntPredicate);
  
  public abstract <T extends IntFloatProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract IntCollection keys();
  
  public abstract FloatContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntFloatAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */