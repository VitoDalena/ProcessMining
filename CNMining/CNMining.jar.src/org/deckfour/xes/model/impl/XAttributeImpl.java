/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttributable;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class XAttributeImpl
/*     */   implements XAttribute
/*     */ {
/*     */   private final String key;
/*     */   private final XExtension extension;
/*     */   private XAttributeMap attributes;
/*     */   
/*     */   protected XAttributeImpl(String key)
/*     */   {
/*  81 */     this(key, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected XAttributeImpl(String key, XExtension extension)
/*     */   {
/*  93 */     this.key = key;
/*  94 */     this.extension = extension;
/*  95 */     this.attributes = new XAttributeMapLazyImpl(XAttributeMapImpl.class);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getKey()
/*     */   {
/* 105 */     return this.key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XExtension getExtension()
/*     */   {
/* 114 */     return this.extension;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XAttributeMap getAttributes()
/*     */   {
/* 123 */     return this.attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAttributes(XAttributeMap attributes)
/*     */   {
/* 134 */     this.attributes = attributes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Set<XExtension> getExtensions()
/*     */   {
/* 143 */     return XAttributeUtils.extractExtensions(this.attributes);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 152 */     XAttributeImpl clone = null;
/*     */     try {
/* 154 */       clone = (XAttributeImpl)super.clone();
/*     */     } catch (CloneNotSupportedException e) {
/* 156 */       e.printStackTrace();
/* 157 */       return null;
/*     */     }
/* 159 */     clone.attributes = ((XAttributeMap)this.attributes.clone());
/* 160 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 170 */     if ((obj instanceof XAttribute)) {
/* 171 */       XAttribute other = (XAttribute)obj;
/* 172 */       return other.getKey().equals(this.key);
/*     */     }
/* 174 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 185 */     return this.key.hashCode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int compareTo(XAttribute o)
/*     */   {
/* 194 */     return this.key.compareTo(o.getKey());
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
/*     */   public void accept(XVisitor visitor, XAttributable parent)
/*     */   {
/* 207 */     visitor.visitAttributePre(this, parent);
/*     */     
/*     */ 
/*     */ 
/* 211 */     for (XAttribute attribute : this.attributes.values()) {
/* 212 */       attribute.accept(visitor, this);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 217 */     visitor.visitAttributePost(this, parent);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */