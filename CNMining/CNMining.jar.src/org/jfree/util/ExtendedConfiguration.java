package org.jfree.util;

public abstract interface ExtendedConfiguration
  extends Configuration
{
  public abstract boolean isPropertySet(String paramString);
  
  public abstract int getIntProperty(String paramString);
  
  public abstract int getIntProperty(String paramString, int paramInt);
  
  public abstract boolean getBoolProperty(String paramString);
  
  public abstract boolean getBoolProperty(String paramString, boolean paramBoolean);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/util/ExtendedConfiguration.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       0.7.1
 */