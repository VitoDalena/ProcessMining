/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeBoolean;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeBooleanImpl
/*     */   extends XAttributeImpl
/*     */   implements XAttributeBoolean
/*     */ {
/*     */   private boolean value;
/*     */   
/*     */   public XAttributeBooleanImpl(String key, boolean value)
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
/*     */   public XAttributeBooleanImpl(String key, boolean value, XExtension extension)
/*     */   {
/*  81 */     super(key, extension);
/*  82 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getValue()
/*     */   {
/*  91 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(boolean value)
/*     */   {
/* 100 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 110 */     return this.value ? "true" : "false";
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */   {
/* 119 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 123 */     if ((obj instanceof XAttributeBoolean)) {
/* 124 */       XAttributeBoolean other = (XAttributeBoolean)obj;
/* 125 */       return (super.equals(other)) && (this.value == other.getValue());
/*     */     }
/*     */     
/* 128 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(XAttribute other)
/*     */   {
/* 134 */     if (!(other instanceof XAttributeBoolean)) {
/* 135 */       throw new ClassCastException();
/*     */     }
/* 137 */     int result = super.compareTo(other);
/* 138 */     if (result != 0) {
/* 139 */       return result;
/*     */     }
/* 141 */     return Boolean.valueOf(this.value).compareTo(Boolean.valueOf(((XAttributeBoolean)other).getValue()));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeBooleanImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */