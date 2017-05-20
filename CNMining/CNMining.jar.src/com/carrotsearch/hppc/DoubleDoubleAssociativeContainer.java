package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleDoubleCursor;
import com.carrotsearch.hppc.predicates.DoublePredicate;
import com.carrotsearch.hppc.procedures.DoubleDoubleProcedure;
import java.util.Iterator;

public abstract interface DoubleDoubleAssociativeContainer
  extends Iterable<DoubleDoubleCursor>
{
  public abstract Iterator<DoubleDoubleCursor> iterator();
  
  public abstract boolean containsKey(double paramDouble);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(DoubleContainer paramDoubleContainer);
  
  public abstract int removeAll(DoublePredicate paramDoublePredicate);
  
  public abstract <T extends DoubleDoubleProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract DoubleCollection keys();
  
  public abstract DoubleContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleDoubleAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */