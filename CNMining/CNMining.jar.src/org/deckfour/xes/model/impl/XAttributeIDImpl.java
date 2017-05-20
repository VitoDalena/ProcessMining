/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.id.XID;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeID;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeIDImpl
/*     */   extends XAttributeImpl
/*     */   implements XAttributeID
/*     */ {
/*     */   private XID value;
/*     */   
/*     */   public XAttributeIDImpl(String key, XID value)
/*     */   {
/*  66 */     this(key, value, null);
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
/*     */   public XAttributeIDImpl(String key, XID value, XExtension extension)
/*     */   {
/*  80 */     super(key, extension);
/*  81 */     setValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XID getValue()
/*     */   {
/*  90 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(XID value)
/*     */   {
/*  99 */     if (value == null) {
/* 100 */       throw new NullPointerException("No null value allowed in ID attribute!");
/*     */     }
/*     */     
/* 103 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 113 */     return this.value.toString();
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 117 */     XAttributeIDImpl clone = (XAttributeIDImpl)super.clone();
/* 118 */     clone.value = ((XID)this.value.clone());
/* 119 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 123 */     if ((obj instanceof XAttributeID)) {
/* 124 */       XAttributeID other = (XAttributeID)obj;
/* 125 */       return (super.equals(other)) && (this.value.equals(other.getValue()));
/*     */     }
/*     */     
/* 128 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(XAttribute other)
/*     */   {
/* 134 */     if (!(other instanceof XAttributeID)) {
/* 135 */       throw new ClassCastException();
/*     */     }
/* 137 */     int result = super.compareTo(other);
/* 138 */     if (result != 0) {
/* 139 */       return result;
/*     */     }
/* 141 */     return this.value.compareTo(((XAttributeID)other).getValue());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeIDImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */