package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortFloatCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortFloatProcedure;
import java.util.Iterator;

public abstract interface ShortFloatAssociativeContainer
  extends Iterable<ShortFloatCursor>
{
  public abstract Iterator<ShortFloatCursor> iterator();
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ShortContainer paramShortContainer);
  
  public abstract int removeAll(ShortPredicate paramShortPredicate);
  
  public abstract <T extends ShortFloatProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ShortCollection keys();
  
  public abstract FloatContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortFloatAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */