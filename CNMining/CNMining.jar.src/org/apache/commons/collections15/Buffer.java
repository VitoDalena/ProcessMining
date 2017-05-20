package org.apache.commons.collections15;

import java.util.Collection;

public abstract interface Buffer<E>
  extends Collection<E>
{
  public abstract E remove();
  
  public abstract E get();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/Buffer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */