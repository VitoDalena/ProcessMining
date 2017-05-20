package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntLongCursor;
import com.carrotsearch.hppc.predicates.IntPredicate;
import com.carrotsearch.hppc.procedures.IntLongProcedure;
import java.util.Iterator;

public abstract interface IntLongAssociativeContainer
  extends Iterable<IntLongCursor>
{
  public abstract Iterator<IntLongCursor> iterator();
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(IntContainer paramIntContainer);
  
  public abstract int removeAll(IntPredicate paramIntPredicate);
  
  public abstract <T extends IntLongProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract IntCollection keys();
  
  public abstract LongContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntLongAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */