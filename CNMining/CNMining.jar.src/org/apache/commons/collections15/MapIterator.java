package org.apache.commons.collections15;

import java.util.Iterator;

public abstract interface MapIterator<K, V>
  extends Iterator<K>
{
  public abstract boolean hasNext();
  
  public abstract K next();
  
  public abstract K getKey();
  
  public abstract V getValue();
  
  public abstract void remove();
  
  public abstract V setValue(V paramV);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/apache/commons/collections15/MapIterator.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */