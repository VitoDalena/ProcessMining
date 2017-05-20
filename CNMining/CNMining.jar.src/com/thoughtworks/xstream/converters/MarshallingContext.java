package com.thoughtworks.xstream.converters;

public abstract interface MarshallingContext
  extends DataHolder
{
  public abstract void convertAnother(Object paramObject);
  
  public abstract void convertAnother(Object paramObject, Converter paramConverter);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/MarshallingContext.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */