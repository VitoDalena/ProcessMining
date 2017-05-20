package com.thoughtworks.xstream.io;

import com.thoughtworks.xstream.converters.ErrorWriter;
import java.util.Iterator;

public abstract interface HierarchicalStreamReader
{
  public abstract boolean hasMoreChildren();
  
  public abstract void moveDown();
  
  public abstract void moveUp();
  
  public abstract String getNodeName();
  
  public abstract String getValue();
  
  public abstract String getAttribute(String paramString);
  
  public abstract String getAttribute(int paramInt);
  
  public abstract int getAttributeCount();
  
  public abstract String getAttributeName(int paramInt);
  
  public abstract Iterator getAttributeNames();
  
  public abstract void appendErrors(ErrorWriter paramErrorWriter);
  
  public abstract void close();
  
  public abstract HierarchicalStreamReader underlyingReader();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/HierarchicalStreamReader.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */