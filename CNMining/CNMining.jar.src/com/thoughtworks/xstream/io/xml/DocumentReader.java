package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.HierarchicalStreamReader;

public abstract interface DocumentReader
  extends HierarchicalStreamReader
{
  public abstract Object getCurrent();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/DocumentReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */