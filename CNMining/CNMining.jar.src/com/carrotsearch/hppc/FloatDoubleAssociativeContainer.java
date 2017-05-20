package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.FloatDoubleCursor;
import com.carrotsearch.hppc.predicates.FloatPredicate;
import com.carrotsearch.hppc.procedures.FloatDoubleProcedure;
import java.util.Iterator;

public abstract interface FloatDoubleAssociativeContainer
  extends Iterable<FloatDoubleCursor>
{
  public abstract Iterator<FloatDoubleCursor> iterator();
  
  public abstract boolean containsKey(float paramFloat);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(FloatContainer paramFloatContainer);
  
  public abstract int removeAll(FloatPredicate paramFloatPredicate);
  
  public abstract <T extends FloatDoubleProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract FloatCollection keys();
  
  public abstract DoubleContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/FloatDoubleAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */