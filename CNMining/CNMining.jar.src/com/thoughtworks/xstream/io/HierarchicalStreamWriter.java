package com.thoughtworks.xstream.io;

public abstract interface HierarchicalStreamWriter
{
  public abstract void startNode(String paramString);
  
  public abstract void addAttribute(String paramString1, String paramString2);
  
  public abstract void setValue(String paramString);
  
  public abstract void endNode();
  
  public abstract void flush();
  
  public abstract void close();
  
  public abstract HierarchicalStreamWriter underlyingWriter();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/HierarchicalStreamWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */