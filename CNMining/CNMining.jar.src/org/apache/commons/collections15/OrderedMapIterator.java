package org.apache.commons.collections15;

public abstract interface OrderedMapIterator<K, V>
  extends MapIterator<K, V>, OrderedIterator<K>
{
  public abstract boolean hasPrevious();
  
  public abstract K previous();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/OrderedMapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */