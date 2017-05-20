package org.apache.commons.collections15;

import java.util.Iterator;

public abstract interface OrderedIterator<E>
  extends Iterator<E>
{
  public abstract boolean hasPrevious();
  
  public abstract E previous();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/OrderedIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */