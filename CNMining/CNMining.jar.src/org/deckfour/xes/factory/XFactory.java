package org.deckfour.xes.factory;

import java.net.URI;
import java.util.Date;
import org.deckfour.xes.extension.XExtension;
import org.deckfour.xes.id.XID;
import org.deckfour.xes.model.XAttributeBoolean;
import org.deckfour.xes.model.XAttributeContinuous;
import org.deckfour.xes.model.XAttributeDiscrete;
import org.deckfour.xes.model.XAttributeID;
import org.deckfour.xes.model.XAttributeLiteral;
import org.deckfour.xes.model.XAttributeMap;
import org.deckfour.xes.model.XAttributeTimestamp;
import org.deckfour.xes.model.XEvent;
import org.deckfour.xes.model.XLog;
import org.deckfour.xes.model.XTrace;

public abstract interface XFactory
{
  public abstract String getName();
  
  public abstract String getAuthor();
  
  public abstract String getVendor();
  
  public abstract String getDescription();
  
  public abstract URI getUri();
  
  public abstract XLog createLog();
  
  public abstract XLog createLog(XAttributeMap paramXAttributeMap);
  
  public abstract XTrace createTrace();
  
  public abstract XTrace createTrace(XAttributeMap paramXAttributeMap);
  
  public abstract XEvent createEvent();
  
  public abstract XEvent createEvent(XAttributeMap paramXAttributeMap);
  
  public abstract XEvent createEvent(XID paramXID, XAttributeMap paramXAttributeMap);
  
  public abstract XAttributeMap createAttributeMap();
  
  public abstract XAttributeBoolean createAttributeBoolean(String paramString, boolean paramBoolean, XExtension paramXExtension);
  
  public abstract XAttributeContinuous createAttributeContinuous(String paramString, double paramDouble, XExtension paramXExtension);
  
  public abstract XAttributeDiscrete createAttributeDiscrete(String paramString, long paramLong, XExtension paramXExtension);
  
  public abstract XAttributeLiteral createAttributeLiteral(String paramString1, String paramString2, XExtension paramXExtension);
  
  public abstract XAttributeTimestamp createAttributeTimestamp(String paramString, Date paramDate, XExtension paramXExtension);
  
  public abstract XAttributeTimestamp createAttributeTimestamp(String paramString, long paramLong, XExtension paramXExtension);
  
  public abstract XAttributeID createAttributeID(String paramString, XID paramXID, XExtension paramXExtension);
}


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/factory/XFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */