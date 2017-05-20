package com.thoughtworks.xstream.converters;

public abstract interface SingleValueConverter
  extends ConverterMatcher
{
  public abstract String toString(Object paramObject);
  
  public abstract Object fromString(String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/SingleValueConverter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */