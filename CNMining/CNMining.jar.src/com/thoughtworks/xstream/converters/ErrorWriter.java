package com.thoughtworks.xstream.converters;

import java.util.Iterator;

public abstract interface ErrorWriter
{
  public abstract void add(String paramString1, String paramString2);
  
  public abstract void set(String paramString1, String paramString2);
  
  public abstract String get(String paramString);
  
  public abstract Iterator keys();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/converters/ErrorWriter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */