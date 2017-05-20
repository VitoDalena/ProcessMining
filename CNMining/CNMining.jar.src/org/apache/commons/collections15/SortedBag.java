package org.apache.commons.collections15;

import java.util.Comparator;

public abstract interface SortedBag<E>
  extends Bag<E>
{
  public abstract Comparator<? super E> comparator();
  
  public abstract E first();
  
  public abstract E last();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/SortedBag.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */