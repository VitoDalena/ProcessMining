package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortShortCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortShortProcedure;
import java.util.Iterator;

public abstract interface ShortShortAssociativeContainer
  extends Iterable<ShortShortCursor>
{
  public abstract Iterator<ShortShortCursor> iterator();
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ShortContainer paramShortContainer);
  
  public abstract int removeAll(ShortPredicate paramShortPredicate);
  
  public abstract <T extends ShortShortProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ShortCollection keys();
  
  public abstract ShortContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortShortAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */