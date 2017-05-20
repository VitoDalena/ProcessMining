package com.carrotsearch.hppc;

import com.carrotsearch.hppc.cursors.ShortByteCursor;
import com.carrotsearch.hppc.predicates.ShortPredicate;
import com.carrotsearch.hppc.procedures.ShortByteProcedure;
import java.util.Iterator;

public abstract interface ShortByteAssociativeContainer
  extends Iterable<ShortByteCursor>
{
  public abstract Iterator<ShortByteCursor> iterator();
  
  public abstract boolean containsKey(short paramShort);
  
  public abstract int size();
  
  public abstract boolean isEmpty();
  
  public abstract int removeAll(ShortContainer paramShortContainer);
  
  public abstract int removeAll(ShortPredicate paramShortPredicate);
  
  public abstract <T extends ShortByteProcedure> T forEach(T paramT);
  
  public abstract void clear();
  
  public abstract ShortCollection keys();
  
  public abstract ByteContainer values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/carrotsearch/hppc/ShortByteAssociativeContainer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */