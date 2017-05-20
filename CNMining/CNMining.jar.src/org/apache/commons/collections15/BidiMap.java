package org.apache.commons.collections15;

import java.util.Set;

public abstract interface BidiMap<K, V>
  extends IterableMap<K, V>
{
  public abstract MapIterator<K, V> mapIterator();
  
  public abstract V put(K paramK, V paramV);
  
  public abstract K getKey(Object paramObject);
  
  public abstract K removeValue(Object paramObject);
  
  public abstract BidiMap<V, K> inverseBidiMap();
  
  public abstract Set<V> values();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/BidiMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */