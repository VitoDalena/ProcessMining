package org.deckfour.xes.info;

import org.deckfour.xes.model.XAttribute;

public abstract interface XAttributeNameMap
{
  public abstract String getMappingName();
  
  public abstract String map(XAttribute paramXAttribute);
  
  public abstract String map(String paramString);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/XAttributeNameMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */