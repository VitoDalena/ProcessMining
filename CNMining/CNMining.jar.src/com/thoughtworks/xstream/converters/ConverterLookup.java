package com.thoughtworks.xstream.converters;

public abstract interface ConverterLookup
{
  public abstract Converter lookupConverterForType(Class paramClass);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/ConverterLookup.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */