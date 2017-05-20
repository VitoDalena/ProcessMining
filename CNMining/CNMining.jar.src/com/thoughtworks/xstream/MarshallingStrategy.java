package com.thoughtworks.xstream;

import com.thoughtworks.xstream.converters.ConverterLookup;
import com.thoughtworks.xstream.converters.DataHolder;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;

public abstract interface MarshallingStrategy
{
  public abstract Object unmarshal(Object paramObject, HierarchicalStreamReader paramHierarchicalStreamReader, DataHolder paramDataHolder, ConverterLookup paramConverterLookup, Mapper paramMapper);
  
  public abstract void marshal(HierarchicalStreamWriter paramHierarchicalStreamWriter, Object paramObject, ConverterLookup paramConverterLookup, Mapper paramMapper, DataHolder paramDataHolder);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/MarshallingStrategy.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */