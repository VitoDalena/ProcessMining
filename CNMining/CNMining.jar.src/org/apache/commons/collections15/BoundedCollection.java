package org.apache.commons.collections15;

import java.util.Collection;

public abstract interface BoundedCollection<E>
  extends Collection<E>
{
  public abstract boolean isFull();
  
  public abstract int maxSize();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BoundedCollection.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */