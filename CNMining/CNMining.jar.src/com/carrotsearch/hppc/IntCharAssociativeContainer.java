package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntCharCursor;
import com.carrotsearch.hppc.predicates.IntPredicate;
import com.carrotsearch.hppc.procedures.IntCharProcedure;
import java.util.Iterator;

public abstract interface IntCharAssociativeContainer
  extends Iterable<IntCharCursor>
{
  public abstract Iterator<IntCharCursor> iterator();
  
  public abstract boolean containsKey(int paramInt);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(IntContainer paramIntContainer);
  
  public abstract int removeAll(IntPredicate paramIntPredicate);
  
  public abstract <T extends IntCharProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract IntCollection keys();
  
  public abstract CharContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntCharAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */