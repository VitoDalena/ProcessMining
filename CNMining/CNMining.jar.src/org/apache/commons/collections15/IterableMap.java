package org.apache.commons.collections15;

import java.util.Map;

public abstract interface IterableMap<K, V>
  extends Map<K, V>
{
  public abstract MapIterator<K, V> mapIterator();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/IterableMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */