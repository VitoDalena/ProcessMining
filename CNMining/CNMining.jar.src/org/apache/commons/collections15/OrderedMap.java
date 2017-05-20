package org.apache.commons.collections15;

public abstract interface OrderedMap<K, V>
  extends IterableMap<K, V>
{
  public abstract OrderedMapIterator<K, V> orderedMapIterator();
  
  public abstract K firstKey();
  
  public abstract K lastKey();
  
  public abstract K nextKey(K paramK);
  
  public abstract K previousKey(K paramK);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/OrderedMap.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */