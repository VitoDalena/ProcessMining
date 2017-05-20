package org.apache.commons.collections15;

import java.util.ListIterator;

public abstract interface ResettableListIterator<E>
  extends ListIterator<E>, ResettableIterator<E>
{
  public abstract void reset();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ResettableListIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */