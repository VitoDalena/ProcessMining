package org.deckfour.xes.model;

import org.deckfour.xes.extension.XExtension;

public abstract interface XAttribute
  extends XAttributable, Cloneable, Comparable<XAttribute>
{
  public abstract String getKey();
  
  public abstract XExtension getExtension();
  
  public abstract Object clone();
  
  public abstract void accept(XVisitor paramXVisitor, XAttributable paramXAttributable);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XAttribute.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */