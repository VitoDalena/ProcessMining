package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortDoubleCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortDoubleProcedure;
import java.util.Iterator;

public abstract interface ShortDoubleAssociativeContainer
  extends Iterable<ShortDoubleCursor>
{
  public abstract Iterator<ShortDoubleCursor> iterator();
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ShortContainer paramShortContainer);
  
  public abstract int removeAll(ShortPredicate paramShortPredicate);
  
  public abstract <T extends ShortDoubleProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ShortCollection keys();
  
  public abstract DoubleContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortDoubleAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */