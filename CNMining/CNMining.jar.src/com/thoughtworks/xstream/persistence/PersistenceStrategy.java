package com.thoughtworks.xstream.persistence;

import java.util.Iterator;

public abstract interface PersistenceStrategy
{
  public abstract Iterator iterator();
  
  public abstract int size();
  
  public abstract Object get(Object paramObject);
  
  public abstract Object put(Object paramObject1, Object paramObject2);
  
  public abstract Object remove(Object paramObject);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/persistence/PersistenceStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */