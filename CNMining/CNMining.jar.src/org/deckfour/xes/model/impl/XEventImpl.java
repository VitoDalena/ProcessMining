/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.id.XIDFactory;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
/*     */ import org.deckfour.xes.model.XEvent;
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
/*     */ public class XEventImpl
/*     */   implements XEvent
/*     */ {
/*     */   private XID id;
/*     */   private XAttributeMap attributes;
/*     */   
/*     */   public XEventImpl()
/*     */   {
/*  75 */     this(XIDFactory.instance().createId(), new XAttributeMapImpl());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventImpl(XID id)
/*     */   {
/*  85 */     this(id, new XAttributeMapImpl());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventImpl(XAttributeMap attributes)
/*     */   {
/*  95 */     this(XIDFactory.instance().createId(), attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XEventImpl(XID id, XAttributeMap attributes)
/*     */   {
/* 107 */     this.id = id;
/* 108 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeMap getAttributes()
/*     */   {
/* 117 */     return this.attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAttributes(XAttributeMap attributes)
/*     */   {
/* 126 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<XExtension> getExtensions()
/*     */   {
/* 135 */     return XAttributeUtils.extractExtensions(this.attributes);
/*     */   }
/*     */   
/*     */ 
/*     */   public Object clone()
/*     */   {
/*     */     XEventImpl clone;
/*     */     
/*     */     try
/*     */     {
/* 145 */       clone = (XEventImpl)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 147 */       e.printStackTrace();
/* 148 */       return null;
/*     */     }
/* 150 */     clone.id = XIDFactory.instance().createId();
/* 151 */     clone.attributes = ((XAttributeMap)this.attributes.clone());
/* 152 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean equals(Object o)
/*     */   {
/* 159 */     if ((o instanceof XEventImpl)) {
/* 160 */       return ((XEventImpl)o).id.equals(this.id);
/*     */     }
/* 162 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 170 */     return this.id.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XID getID()
/*     */   {
/* 178 */     return this.id;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setID(XID id)
/*     */   {
/* 188 */     this.id = id;
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
/*     */   public void accept(XVisitor visitor, XTrace trace)
/*     */   {
/* 201 */     visitor.visitEventPre(this, trace);
/*     */     
/*     */ 
/*     */ 
/* 205 */     for (XAttribute attribute : this.attributes.values()) {
/* 206 */       attribute.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 211 */     visitor.visitEventPost(this, trace);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XEventImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */