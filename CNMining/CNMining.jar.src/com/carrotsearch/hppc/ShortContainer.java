package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortProcedure;
import java.util.Iterator;

public abstract interface ShortContainer
  extends Iterable<ShortCursor>
{
  public abstract Iterator<ShortCursor> iterator();
  
  public abstract boolean contains(short paramShort);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract short[] toArray();
  
  public abstract <T extends ShortProcedure> T forEach(T paramT);
  
  public abstract <T extends ShortPredicate> T forEach(T paramT);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */