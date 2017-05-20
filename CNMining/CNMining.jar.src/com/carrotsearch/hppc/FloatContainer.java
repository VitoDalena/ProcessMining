package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatCursor;
import com.carrotsearch.hppc.predicates.FloatPredicate;
import com.carrotsearch.hppc.procedures.FloatProcedure;
import java.util.Iterator;

public abstract interface FloatContainer
  extends Iterable<FloatCursor>
{
  public abstract Iterator<FloatCursor> iterator();
  
  public abstract boolean contains(float paramFloat);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract float[] toArray();
  
  public abstract <T extends FloatProcedure> T forEach(T paramT);
  
  public abstract <T extends FloatPredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */