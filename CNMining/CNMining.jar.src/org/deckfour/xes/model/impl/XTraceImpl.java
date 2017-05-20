/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XAttributeTimestamp;
/*     */ import org.deckfour.xes.model.XEvent;
/*     */ import org.deckfour.xes.model.XLog;
/*     */ import org.deckfour.xes.model.XTrace;
/*     */ import org.deckfour.xes.model.XVisitor;
/*     */ import org.deckfour.xes.util.XAttributeUtils;
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
/*     */ public class XTraceImpl
/*     */   extends ArrayList<XEvent>
/*     */   implements XTrace
/*     */ {
/*     */   private static final long serialVersionUID = 843122019760036963L;
/*     */   private XAttributeMap attributes;
/*     */   
/*     */   public XTraceImpl(XAttributeMap attributeMap)
/*     */   {
/*  81 */     this.attributes = attributeMap;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeMap getAttributes()
/*     */   {
/*  90 */     return this.attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<XExtension> getExtensions()
/*     */   {
/*  99 */     return XAttributeUtils.extractExtensions(this.attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAttributes(XAttributeMap attributes)
/*     */   {
/* 108 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 115 */     XTraceImpl clone = (XTraceImpl)super.clone();
/* 116 */     clone.attributes = ((XAttributeMap)this.attributes.clone());
/* 117 */     clone.clear();
/* 118 */     for (XEvent event : this) {
/* 119 */       clone.add((XEvent)event.clone());
/*     */     }
/* 121 */     return clone;
/*     */   }
/*     */   
/*     */   public synchronized int insertOrdered(XEvent event) {
/* 125 */     if (size() == 0)
/*     */     {
/* 127 */       add(event);
/* 128 */       return 0;
/*     */     }
/* 130 */     XAttribute insTsAttr = (XAttribute)event.getAttributes().get("time:timestamp");
/*     */     
/* 132 */     if (insTsAttr == null)
/*     */     {
/* 134 */       add(event);
/* 135 */       return size() - 1;
/*     */     }
/* 137 */     Date insTs = ((XAttributeTimestamp)insTsAttr).getValue();
/* 138 */     for (int i = size() - 1; i >= 0; i--) {
/* 139 */       XAttribute refTsAttr = (XAttribute)((XEvent)get(i)).getAttributes().get("time:timestamp");
/*     */       
/* 141 */       if (refTsAttr == null)
/*     */       {
/* 143 */         add(event);
/* 144 */         return size() - 1;
/*     */       }
/* 146 */       Date refTs = ((XAttributeTimestamp)refTsAttr).getValue();
/* 147 */       if (!insTs.before(refTs))
/*     */       {
/* 149 */         add(i + 1, event);
/* 150 */         return i + 1;
/*     */       }
/*     */     }
/*     */     
/* 154 */     add(0, event);
/* 155 */     return 0;
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
/*     */   public void accept(XVisitor visitor, XLog log)
/*     */   {
/* 168 */     visitor.visitTracePre(this, log);
/*     */     
/*     */ 
/*     */ 
/* 172 */     for (XAttribute attribute : this.attributes.values()) {
/* 173 */       attribute.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 178 */     for (XEvent event : this) {
/* 179 */       event.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 184 */     visitor.visitTracePost(this, log);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XTraceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */