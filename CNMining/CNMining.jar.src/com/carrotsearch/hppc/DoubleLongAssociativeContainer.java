package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleLongCursor;
import com.carrotsearch.hppc.predicates.DoublePredicate;
import com.carrotsearch.hppc.procedures.DoubleLongProcedure;
import java.util.Iterator;

public abstract interface DoubleLongAssociativeContainer
  extends Iterable<DoubleLongCursor>
{
  public abstract Iterator<DoubleLongCursor> iterator();
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(DoubleContainer paramDoubleContainer);
  
  public abstract int removeAll(DoublePredicate paramDoublePredicate);
  
  public abstract <T extends DoubleLongProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract DoubleCollection keys();
  
  public abstract LongContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleLongAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */