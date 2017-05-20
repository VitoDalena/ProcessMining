/*     */ package org.deckfour.xes.info.impl;
/*     */ 
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import org.deckfour.xes.classification.XEventAttributeClassifier;
/*     */ import org.deckfour.xes.classification.XEventClasses;
/*     */ import org.deckfour.xes.classification.XEventClassifier;
/*     */ import org.deckfour.xes.classification.XEventLifeTransClassifier;
/*     */ import org.deckfour.xes.classification.XEventNameClassifier;
/*     */ import org.deckfour.xes.classification.XEventResourceClassifier;
/*     */ import org.deckfour.xes.info.XAttributeInfo;
/*     */ import org.deckfour.xes.info.XLogInfo;
/*     */ import org.deckfour.xes.info.XTimeBounds;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XLogInfoImpl
/*     */   implements XLogInfo
/*     */ {
/*  81 */   public static final XEventClassifier STANDARD_CLASSIFIER = new XEventAttributeClassifier("MXML Legacy Classifier", new String[] { "concept:name", "lifecycle:transition" });
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
/*  92 */   public static final XEventClassifier NAME_CLASSIFIER = new XEventNameClassifier();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 100 */   public static final XEventClassifier RESOURCE_CLASSIFIER = new XEventResourceClassifier();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 108 */   public static final XEventClassifier LIFECYCLE_TRANSITION_CLASSIFIER = new XEventLifeTransClassifier();
/*     */   
/*     */   protected XLog log;
/*     */   
/*     */   protected int numberOfEvents;
/*     */   
/*     */   protected int numberOfTraces;
/*     */   protected Map<XEventClassifier, XEventClasses> eventClasses;
/*     */   protected XEventClassifier defaultClassifier;
/*     */   
/*     */   public static XLogInfo create(XLog log)
/*     */   {
/* 120 */     return create(log, STANDARD_CLASSIFIER);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static XLogInfo create(XLog log, XEventClassifier defaultClassifier)
/*     */   {
/* 131 */     return create(log, defaultClassifier, null);
/*     */   }
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
/*     */   public static XLogInfo create(XLog log, XEventClassifier defaultClassifier, Collection<XEventClassifier> classifiers)
/*     */   {
/* 146 */     return new XLogInfoImpl(log, defaultClassifier, classifiers);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XTimeBoundsImpl logBoundaries;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected HashMap<XTrace, XTimeBoundsImpl> traceBoundaries;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAttributeInfoImpl logAttributeInfo;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAttributeInfoImpl traceAttributeInfo;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAttributeInfoImpl eventAttributeInfo;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAttributeInfoImpl metaAttributeInfo;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XLogInfoImpl(XLog log, XEventClassifier defaultClassifier, Collection<XEventClassifier> classifiers)
/*     */   {
/* 204 */     this.log = log;
/* 205 */     this.defaultClassifier = defaultClassifier;
/* 206 */     if (classifiers == null) {
/* 207 */       classifiers = Collections.emptyList();
/*     */     }
/* 209 */     this.eventClasses = new HashMap(classifiers.size() + 4);
/* 210 */     for (XEventClassifier classifier : classifiers) {
/* 211 */       this.eventClasses.put(classifier, new XEventClasses(classifier));
/*     */     }
/* 213 */     this.eventClasses.put(this.defaultClassifier, new XEventClasses(this.defaultClassifier));
/* 214 */     this.eventClasses.put(NAME_CLASSIFIER, new XEventClasses(NAME_CLASSIFIER));
/* 215 */     this.eventClasses.put(RESOURCE_CLASSIFIER, new XEventClasses(RESOURCE_CLASSIFIER));
/* 216 */     this.eventClasses.put(LIFECYCLE_TRANSITION_CLASSIFIER, new XEventClasses(LIFECYCLE_TRANSITION_CLASSIFIER));
/* 217 */     this.numberOfEvents = 0;
/* 218 */     this.numberOfTraces = 0;
/* 219 */     this.logBoundaries = new XTimeBoundsImpl();
/* 220 */     this.traceBoundaries = new HashMap();
/* 221 */     this.logAttributeInfo = new XAttributeInfoImpl();
/* 222 */     this.traceAttributeInfo = new XAttributeInfoImpl();
/* 223 */     this.eventAttributeInfo = new XAttributeInfoImpl();
/* 224 */     this.metaAttributeInfo = new XAttributeInfoImpl();
/* 225 */     setup();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void setup()
/*     */   {
/* 233 */     registerAttributes(this.logAttributeInfo, this.log);
/* 234 */     for (XTrace trace : this.log) {
/* 235 */       this.numberOfTraces += 1;
/* 236 */       registerAttributes(this.traceAttributeInfo, trace);
/* 237 */       XTimeBoundsImpl traceBounds = new XTimeBoundsImpl();
/* 238 */       for (XEvent event : trace) {
/* 239 */         registerAttributes(this.eventAttributeInfo, event);
/* 240 */         for (XEventClasses classes : this.eventClasses.values()) {
/* 241 */           classes.register(event);
/*     */         }
/* 243 */         traceBounds.register(event);
/* 244 */         this.numberOfEvents += 1;
/*     */       }
/* 246 */       this.traceBoundaries.put(trace, traceBounds);
/* 247 */       this.logBoundaries.register(traceBounds);
/*     */     }
/*     */     
/* 250 */     for (XEventClasses classes : this.eventClasses.values()) {
/* 251 */       classes.harmonizeIndices();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void registerAttributes(XAttributeInfoImpl attributeInfo, XAttributable attributable)
/*     */   {
/* 263 */     for (XAttribute attribute : attributable.getAttributes().values())
/*     */     {
/* 265 */       attributeInfo.register(attribute);
/*     */       
/* 267 */       registerAttributes(this.metaAttributeInfo, attribute);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XLog getLog()
/*     */   {
/* 275 */     return this.log;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getNumberOfEvents()
/*     */   {
/* 282 */     return this.numberOfEvents;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public int getNumberOfTraces()
/*     */   {
/* 289 */     return this.numberOfTraces;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEventClasses getEventClasses(XEventClassifier classifier)
/*     */   {
/* 296 */     return (XEventClasses)this.eventClasses.get(classifier);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Collection<XEventClassifier> getEventClassifiers()
/*     */   {
/* 303 */     return Collections.unmodifiableCollection(this.eventClasses.keySet());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEventClasses getEventClasses()
/*     */   {
/* 310 */     return getEventClasses(this.defaultClassifier);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEventClasses getResourceClasses()
/*     */   {
/* 317 */     return getEventClasses(RESOURCE_CLASSIFIER);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEventClasses getNameClasses()
/*     */   {
/* 324 */     return getEventClasses(NAME_CLASSIFIER);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XEventClasses getTransitionClasses()
/*     */   {
/* 331 */     return getEventClasses(LIFECYCLE_TRANSITION_CLASSIFIER);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XTimeBounds getLogTimeBoundaries()
/*     */   {
/* 338 */     return this.logBoundaries;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XTimeBounds getTraceTimeBoundaries(XTrace trace)
/*     */   {
/* 345 */     return (XTimeBounds)this.traceBoundaries.get(trace);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XAttributeInfo getLogAttributeInfo()
/*     */   {
/* 352 */     return this.logAttributeInfo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XAttributeInfo getTraceAttributeInfo()
/*     */   {
/* 359 */     return this.traceAttributeInfo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XAttributeInfo getEventAttributeInfo()
/*     */   {
/* 366 */     return this.eventAttributeInfo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XAttributeInfo getMetaAttributeInfo()
/*     */   {
/* 373 */     return this.metaAttributeInfo;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/info/impl/XLogInfoImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */