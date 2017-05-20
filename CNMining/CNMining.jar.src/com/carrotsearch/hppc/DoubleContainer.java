package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.DoubleCursor;
import com.carrotsearch.hppc.predicates.DoublePredicate;
import com.carrotsearch.hppc.procedures.DoubleProcedure;
import java.util.Iterator;

public abstract interface DoubleContainer
  extends Iterable<DoubleCursor>
{
  public abstract Iterator<DoubleCursor> iterator();
  
  public abstract boolean contains(double paramDouble);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract double[] toArray();
  
  public abstract <T extends DoubleProcedure> T forEach(T paramT);
  
  public abstract <T extends DoublePredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/DoubleContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */