/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.classification.XEventClassifier;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.info.XLogInfo;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.model.XVisitor;
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
/*     */ public class XLogImpl
/*     */   extends ArrayList<XTrace>
/*     */   implements XLog
/*     */ {
/*     */   private static final long serialVersionUID = -9192919845877466525L;
/*     */   private XAttributeMap attributes;
/*     */   private Set<XExtension> extensions;
/*     */   private List<XEventClassifier> classifiers;
/*     */   private List<XAttribute> globalTraceAttributes;
/*     */   private List<XAttribute> globalEventAttributes;
/*     */   private XEventClassifier cachedClassifier;
/*     */   private XLogInfo cachedInfo;
/*     */   
/*     */   public XLogImpl(XAttributeMap attributeMap)
/*     */   {
/* 105 */     this.attributes = attributeMap;
/* 106 */     this.extensions = new HashSet();
/* 107 */     this.classifiers = new ArrayList();
/* 108 */     this.globalTraceAttributes = new ArrayList();
/* 109 */     this.globalEventAttributes = new ArrayList();
/* 110 */     this.cachedClassifier = null;
/* 111 */     this.cachedInfo = null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public XAttributeMap getAttributes()
/*     */   {
/* 118 */     return this.attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setAttributes(XAttributeMap attributes)
/*     */   {
/* 125 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Set<XExtension> getExtensions()
/*     */   {
/* 132 */     return this.extensions;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 139 */     XLogImpl clone = (XLogImpl)super.clone();
/* 140 */     clone.attributes = ((XAttributeMap)this.attributes.clone());
/* 141 */     clone.extensions = new HashSet(this.extensions);
/* 142 */     clone.classifiers = new ArrayList(this.classifiers);
/* 143 */     clone.globalTraceAttributes = new ArrayList(this.globalTraceAttributes);
/* 144 */     clone.globalEventAttributes = new ArrayList(this.globalEventAttributes);
/* 145 */     clone.clear();
/* 146 */     for (XTrace trace : this) {
/* 147 */       clone.add((XTrace)trace.clone());
/*     */     }
/* 149 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<XEventClassifier> getClassifiers()
/*     */   {
/* 156 */     return this.classifiers;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<XAttribute> getGlobalEventAttributes()
/*     */   {
/* 163 */     return this.globalEventAttributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public List<XAttribute> getGlobalTraceAttributes()
/*     */   {
/* 170 */     return this.globalTraceAttributes;
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
/*     */   public boolean accept(XVisitor visitor)
/*     */   {
/* 183 */     if (visitor.precondition())
/*     */     {
/*     */ 
/*     */ 
/* 187 */       visitor.init(this);
/*     */       
/*     */ 
/*     */ 
/* 191 */       visitor.visitLogPre(this);
/*     */       
/*     */ 
/*     */ 
/* 195 */       for (XExtension extension : this.extensions) {
/* 196 */         extension.accept(visitor, this);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 201 */       for (XEventClassifier classifier : this.classifiers) {
/* 202 */         classifier.accept(visitor, this);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 207 */       for (XAttribute attribute : this.attributes.values()) {
/* 208 */         attribute.accept(visitor, this);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 213 */       for (XTrace trace : this) {
/* 214 */         trace.accept(visitor, this);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 219 */       visitor.visitLogPost(this);
/* 220 */       return true;
/*     */     }
/* 222 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XLogInfo getInfo(XEventClassifier classifier)
/*     */   {
/* 230 */     return classifier.equals(this.cachedClassifier) ? this.cachedInfo : null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setInfo(XEventClassifier classifier, XLogInfo info)
/*     */   {
/* 237 */     this.cachedClassifier = classifier;
/* 238 */     this.cachedInfo = info;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XLogImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */