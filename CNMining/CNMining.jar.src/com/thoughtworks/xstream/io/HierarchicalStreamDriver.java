package com.thoughtworks.xstream.io;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

public abstract interface HierarchicalStreamDriver
{
  public abstract HierarchicalStreamReader createReader(Reader paramReader);
  
  public abstract HierarchicalStreamReader createReader(InputStream paramInputStream);
  
  public abstract HierarchicalStreamWriter createWriter(Writer paramWriter);
  
  public abstract HierarchicalStreamWriter createWriter(OutputStream paramOutputStream);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/thoughtworks/xstream/io/HierarchicalStreamDriver.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */