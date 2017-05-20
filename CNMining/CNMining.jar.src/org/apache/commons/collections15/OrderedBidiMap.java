package org.apache.commons.collections15;

public abstract interface OrderedBidiMap<K, V>
  extends BidiMap<K, V>, OrderedMap<K, V>
{
  public abstract BidiMap<V, K> inverseBidiMap();
  
  public abstract OrderedBidiMap<V, K> inverseOrderedBidiMap();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/OrderedBidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */