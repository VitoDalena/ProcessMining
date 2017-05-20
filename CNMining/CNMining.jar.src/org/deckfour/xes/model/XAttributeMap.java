package org.deckfour.xes.model;

import java.util.Map;

public abstract interface XAttributeMap
  extends Map<String, XAttribute>, Cloneable
{
  public abstract Object clone();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XAttributeMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */