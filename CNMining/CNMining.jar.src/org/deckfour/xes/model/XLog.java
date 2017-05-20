package org.deckfour.xes.model;

import java.util.List;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.info.XLogInfo;

public abstract interface XLog
  extends XElement, List<XTrace>
{
  public abstract List<XEventClassifier> getClassifiers();
  
  public abstract List<XAttribute> getGlobalTraceAttributes();
  
  public abstract List<XAttribute> getGlobalEventAttributes();
  
  public abstract boolean accept(XVisitor paramXVisitor);
  
  public abstract XLogInfo getInfo(XEventClassifier paramXEventClassifier);
  
  public abstract void setInfo(XEventClassifier paramXEventClassifier, XLogInfo paramXLogInfo);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/XLog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */