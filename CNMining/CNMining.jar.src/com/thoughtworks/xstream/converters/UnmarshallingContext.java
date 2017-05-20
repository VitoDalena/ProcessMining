package com.thoughtworks.xstream.converters;

public abstract interface UnmarshallingContext
  extends DataHolder
{
  public abstract Object convertAnother(Object paramObject, Class paramClass);
  
  public abstract Object convertAnother(Object paramObject, Class paramClass, Converter paramConverter);
  
  public abstract Object currentObject();
  
  public abstract Class getRequiredType();
  
  public abstract void addCompletionCallback(Runnable paramRunnable, int paramInt);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/UnmarshallingContext.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */