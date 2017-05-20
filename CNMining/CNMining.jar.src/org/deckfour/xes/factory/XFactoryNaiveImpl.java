/*     */ package org.deckfour.xes.factory;
/*     */ 
/*     */ import java.net.URI;
/*     */ import java.util.Date;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.model.XAttributeBoolean;
/*     */ import org.deckfour.xes.model.XAttributeContinuous;
/*     */ import org.deckfour.xes.model.XAttributeDiscrete;
/*     */ import org.deckfour.xes.model.XAttributeID;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.model.impl.XAttributeBooleanImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeContinuousImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeDiscreteImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeIDImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeLiteralImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeMapLazyImpl;
/*     */ import org.deckfour.xes.model.impl.XAttributeTimestampImpl;
/*     */ import org.deckfour.xes.model.impl.XEventImpl;
/*     */ import org.deckfour.xes.model.impl.XLogImpl;
/*     */ import org.deckfour.xes.model.impl.XTraceImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XFactoryNaiveImpl
/*     */   implements XFactory
/*     */ {
/*     */   public String getAuthor()
/*     */   {
/*  82 */     return "Christian W. Günther";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getDescription()
/*     */   {
/*  89 */     return "Creates naive implementations for all available model hierarchy elements, i.e., no optimizations will be employed.";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getName()
/*     */   {
/*  98 */     return "Standard / naive";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public URI getUri()
/*     */   {
/* 105 */     return URI.create("http://www.xes-standard.org/");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public String getVendor()
/*     */   {
/* 112 */     return "xes-standard.org";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XLog createLog()
/*     */   {
/* 119 */     return new XLogImpl(new XAttributeMapLazyImpl(XAttributeMapImpl.class));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XLog createLog(XAttributeMap attributes)
/*     */   {
/* 126 */     return new XLogImpl(attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XTrace createTrace()
/*     */   {
/* 133 */     return new XTraceImpl(new XAttributeMapLazyImpl(XAttributeMapImpl.class));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XTrace createTrace(XAttributeMap attributes)
/*     */   {
/* 140 */     return new XTraceImpl(attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEvent createEvent()
/*     */   {
/* 147 */     return new XEventImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEvent createEvent(XAttributeMap attributes)
/*     */   {
/* 154 */     return new XEventImpl(attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEvent createEvent(XID id, XAttributeMap attributes)
/*     */   {
/* 162 */     return new XEventImpl(id, attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XAttributeMap createAttributeMap()
/*     */   {
/* 169 */     return new XAttributeMapImpl();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeBoolean createAttributeBoolean(String key, boolean value, XExtension extension)
/*     */   {
/* 177 */     return new XAttributeBooleanImpl(key, value, extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeContinuous createAttributeContinuous(String key, double value, XExtension extension)
/*     */   {
/* 185 */     return new XAttributeContinuousImpl(key, value, extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeDiscrete createAttributeDiscrete(String key, long value, XExtension extension)
/*     */   {
/* 193 */     return new XAttributeDiscreteImpl(key, value, extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeLiteral createAttributeLiteral(String key, String value, XExtension extension)
/*     */   {
/* 201 */     return new XAttributeLiteralImpl(key, value, extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeTimestamp createAttributeTimestamp(String key, Date value, XExtension extension)
/*     */   {
/* 209 */     return new XAttributeTimestampImpl(key, value, extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeTimestamp createAttributeTimestamp(String key, long millis, XExtension extension)
/*     */   {
/* 217 */     return new XAttributeTimestampImpl(key, millis, extension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeID createAttributeID(String key, XID value, XExtension extension)
/*     */   {
/* 225 */     return new XAttributeIDImpl(key, value, extension);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/factory/XFactoryNaiveImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */