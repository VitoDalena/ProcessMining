package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleFloatCursor;
import com.carrotsearch.hppc.predicates.DoublePredicate;
import com.carrotsearch.hppc.procedures.DoubleFloatProcedure;
import java.util.Iterator;

public abstract interface DoubleFloatAssociativeContainer
  extends Iterable<DoubleFloatCursor>
{
  public abstract Iterator<DoubleFloatCursor> iterator();
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(DoubleContainer paramDoubleContainer);
  
  public abstract int removeAll(DoublePredicate paramDoublePredicate);
  
  public abstract <T extends DoubleFloatProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract DoubleCollection keys();
  
  public abstract FloatContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleFloatAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */