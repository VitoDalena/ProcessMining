package org.jfree.util;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Iterator;

public abstract interface Configuration
  extends Serializable, Cloneable
{
  public abstract String getConfigProperty(String paramString);
  
  public abstract String getConfigProperty(String paramString1, String paramString2);
  
  public abstract Iterator findPropertyKeys(String paramString);
  
  public abstract Enumeration getConfigProperties();
  
  public abstract Object clone()
    throws CloneNotSupportedException;
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/util/Configuration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */