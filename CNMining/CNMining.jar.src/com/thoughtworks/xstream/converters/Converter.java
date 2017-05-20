package com.thoughtworks.xstream.converters;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public abstract interface Converter
  extends ConverterMatcher
{
  public abstract void marshal(Object paramObject, HierarchicalStreamWriter paramHierarchicalStreamWriter, MarshallingContext paramMarshallingContext);
  
  public abstract Object unmarshal(HierarchicalStreamReader paramHierarchicalStreamReader, UnmarshallingContext paramUnmarshallingContext);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/Converter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */