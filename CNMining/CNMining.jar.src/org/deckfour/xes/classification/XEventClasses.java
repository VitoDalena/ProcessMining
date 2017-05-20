/*     */ package org.deckfour.xes.classification;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XEventClasses
/*     */ {
/*     */   protected XEventClassifier classifier;
/*     */   protected HashMap<String, XEventClass> classMap;
/*     */   
/*     */   public static synchronized XEventClasses deriveEventClasses(XEventClassifier classifier, XLog log)
/*     */   {
/*  77 */     XEventClasses nClasses = new XEventClasses(classifier);
/*  78 */     nClasses.register(log);
/*  79 */     nClasses.harmonizeIndices();
/*  80 */     return nClasses;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventClasses(XEventClassifier classifier)
/*     */   {
/*  99 */     this.classifier = classifier;
/* 100 */     this.classMap = new HashMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventClassifier getClassifier()
/*     */   {
/* 109 */     return this.classifier;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Collection<XEventClass> getClasses()
/*     */   {
/* 118 */     return this.classMap.values();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int size()
/*     */   {
/* 127 */     return this.classMap.size();
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
/*     */   public XEventClass getClassOf(XEvent event)
/*     */   {
/* 141 */     return (XEventClass)this.classMap.get(this.classifier.getClassIdentity(event));
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
/*     */   public XEventClass getByIdentity(String classIdentity)
/*     */   {
/* 154 */     return (XEventClass)this.classMap.get(classIdentity);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventClass getByIndex(int index)
/*     */   {
/* 166 */     for (XEventClass eventClass : this.classMap.values()) {
/* 167 */       if (eventClass.getIndex() == index) {
/* 168 */         return eventClass;
/*     */       }
/*     */     }
/* 171 */     return null;
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
/*     */   public void register(XLog log)
/*     */   {
/* 185 */     for (XTrace trace : log) {
/* 186 */       register(trace);
/*     */     }
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
/*     */   public void register(XTrace trace)
/*     */   {
/* 201 */     for (XEvent event : trace) {
/* 202 */       register(event);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void register(XEvent event)
/*     */   {
/* 215 */     String classId = this.classifier.getClassIdentity(event);
/* 216 */     XEventClass eventClass = (XEventClass)this.classMap.get(classId);
/* 217 */     if ((eventClass == null) && (classId != null)) {
/* 218 */       eventClass = new XEventClass(classId, this.classMap.size());
/* 219 */       this.classMap.put(classId, eventClass);
/*     */     }
/* 221 */     if (eventClass != null) {
/* 222 */       eventClass.incrementSize();
/*     */     }
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
/*     */   public synchronized void harmonizeIndices()
/*     */   {
/* 236 */     ArrayList<XEventClass> classList = new ArrayList(this.classMap.values());
/*     */     
/* 238 */     Collections.sort(classList);
/* 239 */     this.classMap.clear();
/* 240 */     for (int i = 0; i < classList.size(); i++) {
/* 241 */       XEventClass original = (XEventClass)classList.get(i);
/* 242 */       XEventClass harmonized = new XEventClass(original.getId(), i);
/* 243 */       harmonized.setSize(original.size());
/* 244 */       this.classMap.put(harmonized.getId(), harmonized);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 254 */     if ((o instanceof XEventClasses)) {
/* 255 */       return ((XEventClasses)o).getClassifier().equals(this.classifier);
/*     */     }
/* 257 */     return false;
/*     */   }
/*     */   
/*     */   public String toString()
/*     */   {
/* 262 */     return "Event classes defined by " + this.classifier.name();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/classification/XEventClasses.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */