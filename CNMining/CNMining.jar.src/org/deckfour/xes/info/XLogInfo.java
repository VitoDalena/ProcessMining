package org.deckfour.xes.info;

import java.util.Collection;
import org.deckfour.xes.classification.XEventClasses;
import org.deckfour.xes.classification.XEventClassifier;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

public abstract interface XLogInfo
{
  public abstract XLog getLog();
  
  public abstract int getNumberOfEvents();
  
  public abstract int getNumberOfTraces();
  
  public abstract Collection<XEventClassifier> getEventClassifiers();
  
  public abstract XEventClasses getEventClasses(XEventClassifier paramXEventClassifier);
  
  public abstract XEventClasses getEventClasses();
  
  public abstract XEventClasses getResourceClasses();
  
  public abstract XEventClasses getNameClasses();
  
  public abstract XEventClasses getTransitionClasses();
  
  public abstract XTimeBounds getLogTimeBoundaries();
  
  public abstract XTimeBounds getTraceTimeBoundaries(XTrace paramXTrace);
  
  public abstract XAttributeInfo getLogAttributeInfo();
  
  public abstract XAttributeInfo getTraceAttributeInfo();
  
  public abstract XAttributeInfo getEventAttributeInfo();
  
  public abstract XAttributeInfo getMetaAttributeInfo();
  
  public abstract String toString();
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/XLogInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */