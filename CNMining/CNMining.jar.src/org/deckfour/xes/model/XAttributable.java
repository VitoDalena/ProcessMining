package org.deckfour.xes.model;

import java.util.Set;
import org.deckfour.xes.extension.XExtension;

public abstract interface XAttributable
{
  public abstract XAttributeMap getAttributes();
  
  public abstract void setAttributes(XAttributeMap paramXAttributeMap);
  
  public abstract Set<XExtension> getExtensions();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XAttributable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */