package com.thoughtworks.xstream.io.naming;

public abstract interface NameCoder
{
  public abstract String encodeNode(String paramString);
  
  public abstract String encodeAttribute(String paramString);
  
  public abstract String decodeNode(String paramString);
  
  public abstract String decodeAttribute(String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/naming/NameCoder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */