package org.deckfour.xes.classification;

import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XVisitor;

public abstract interface XEventClassifier
{
  public abstract String name();
  
  public abstract void setName(String paramString);
  
  public abstract boolean sameEventClass(XEvent paramXEvent1, XEvent paramXEvent2);
  
  public abstract String getClassIdentity(XEvent paramXEvent);
  
  public abstract String[] getDefiningAttributeKeys();
  
  public abstract void accept(XVisitor paramXVisitor, XLog paramXLog);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/classification/XEventClassifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */