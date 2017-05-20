package org.apache.commons.collections15;

import java.util.SortedMap;

public abstract interface SortedBidiMap<K, V>
  extends OrderedBidiMap<K, V>, SortedMap<K, V>
{
  public abstract BidiMap<V, K> inverseBidiMap();
  
  public abstract SortedBidiMap<V, K> inverseSortedBidiMap();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/SortedBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */