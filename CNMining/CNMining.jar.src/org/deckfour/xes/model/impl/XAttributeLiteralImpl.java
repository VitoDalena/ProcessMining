/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeLiteral;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeLiteralImpl
/*     */   extends XAttributeImpl
/*     */   implements XAttributeLiteral
/*     */ {
/*     */   private String value;
/*     */   
/*     */   public XAttributeLiteralImpl(String key, String value)
/*     */   {
/*  67 */     this(key, value, null);
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
/*     */   public XAttributeLiteralImpl(String key, String value, XExtension extension)
/*     */   {
/*  81 */     super(key, extension);
/*  82 */     setValue(value);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getValue()
/*     */   {
/*  91 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(String value)
/*     */   {
/* 101 */     if (value == null) {
/* 102 */       throw new NullPointerException("No null value allowed in literal attribute!");
/*     */     }
/*     */     
/* 105 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 115 */     return this.value;
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 119 */     XAttributeLiteralImpl clone = (XAttributeLiteralImpl)super.clone();
/* 120 */     clone.value = new String(this.value);
/* 121 */     return clone;
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 125 */     if ((obj instanceof XAttributeLiteral)) {
/* 126 */       XAttributeLiteral other = (XAttributeLiteral)obj;
/* 127 */       return (super.equals(other)) && (this.value.equals(other.getValue()));
/*     */     }
/*     */     
/* 130 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(XAttribute other)
/*     */   {
/* 136 */     if (!(other instanceof XAttributeLiteral)) {
/* 137 */       throw new ClassCastException();
/*     */     }
/* 139 */     int result = super.compareTo(other);
/* 140 */     if (result != 0) {
/* 141 */       return result;
/*     */     }
/* 143 */     return this.value.compareTo(((XAttributeLiteral)other).getValue());
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeLiteralImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */