package org.deckfour.xes.model;

import java.util.List;

public abstract interface XTrace
  extends XElement, List<XEvent>
{
  public abstract int insertOrdered(XEvent paramXEvent);
  
  public abstract void accept(XVisitor paramXVisitor, XLog paramXLog);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XTrace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */