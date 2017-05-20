package com.thoughtworks.xstream.converters;

import java.util.Iterator;

public abstract interface DataHolder
{
  public abstract Object get(Object paramObject);
  
  public abstract void put(Object paramObject1, Object paramObject2);
  
  public abstract Iterator keys();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/DataHolder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */