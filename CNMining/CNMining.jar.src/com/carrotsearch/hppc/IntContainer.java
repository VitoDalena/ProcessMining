package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.IntCursor;
import com.carrotsearch.hppc.predicates.IntPredicate;
import com.carrotsearch.hppc.procedures.IntProcedure;
import java.util.Iterator;

public abstract interface IntContainer
  extends Iterable<IntCursor>
{
  public abstract Iterator<IntCursor> iterator();
  
  public abstract boolean contains(int paramInt);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int[] toArray();
  
  public abstract <T extends IntProcedure> T forEach(T paramT);
  
  public abstract <T extends IntPredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/IntContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */