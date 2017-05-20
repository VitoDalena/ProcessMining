package org.deckfour.xes.info;

import java.util.Collection;
import org.deckfour.xes.extension.XExtension;
import org.deckfour.xes.model.XAttribute;

public abstract interface XAttributeInfo
{
  public abstract Collection<XAttribute> getAttributes();
  
  public abstract Collection<String> getAttributeKeys();
  
  public abstract int getFrequency(String paramString);
  
  public abstract int getFrequency(XAttribute paramXAttribute);
  
  public abstract double getRelativeFrequency(String paramString);
  
  public abstract double getRelativeFrequency(XAttribute paramXAttribute);
  
  public abstract Collection<XAttribute> getAttributesForType(Class<? extends XAttribute> paramClass);
  
  public abstract Collection<String> getKeysForType(Class<? extends XAttribute> paramClass);
  
  public abstract Collection<XAttribute> getAttributesForExtension(XExtension paramXExtension);
  
  public abstract Collection<String> getKeysForExtension(XExtension paramXExtension);
  
  public abstract Collection<XAttribute> getAttributesWithoutExtension();
  
  public abstract Collection<String> getKeysWithoutExtension();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/XAttributeInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */