package org.deckfour.xes.model;

import org.deckfour.xes.id.XID;

public abstract interface XEvent
  extends XElement
{
  public abstract XID getID();
  
  public abstract void accept(XVisitor paramXVisitor, XTrace paramXTrace);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */