package org.apache.commons.collections15;

import java.util.Iterator;

public abstract interface ResettableIterator<E>
  extends Iterator<E>
{
  public abstract void reset();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/ResettableIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */