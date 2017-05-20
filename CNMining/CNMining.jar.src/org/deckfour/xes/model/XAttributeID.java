package org.deckfour.xes.model;

import org.deckfour.xes.id.XID;

public abstract interface XAttributeID
  extends XAttribute
{
  public abstract void setValue(XID paramXID);
  
  public abstract XID getValue();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XAttributeID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */