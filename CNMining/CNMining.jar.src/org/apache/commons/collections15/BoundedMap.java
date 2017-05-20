package org.apache.commons.collections15;

import java.util.Map;

public abstract interface BoundedMap<K, V>
  extends Map<K, V>
{
  public abstract boolean isFull();
  
  public abstract int maxSize();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BoundedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */