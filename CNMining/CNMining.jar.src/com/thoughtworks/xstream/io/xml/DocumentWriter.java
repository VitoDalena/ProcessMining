package com.thoughtworks.xstream.io.xml;

import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.List;

public abstract interface DocumentWriter
  extends HierarchicalStreamWriter
{
  public abstract List getTopLevelNodes();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/xml/DocumentWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */