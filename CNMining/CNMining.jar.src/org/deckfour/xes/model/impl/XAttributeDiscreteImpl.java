/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeDiscrete;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeDiscreteImpl
/*     */   extends XAttributeImpl
/*     */   implements XAttributeDiscrete
/*     */ {
/*     */   private long value;
/*     */   
/*     */   public XAttributeDiscreteImpl(String key, long value)
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
/*     */   public XAttributeDiscreteImpl(String key, long value, XExtension extension)
/*     */   {
/*  81 */     super(key, extension);
/*  82 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public long getValue()
/*     */   {
/*  91 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(long value)
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
/* 110 */     return Long.toString(this.value);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 114 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 118 */     if ((obj instanceof XAttributeDiscrete)) {
/* 119 */       XAttributeDiscrete other = (XAttributeDiscrete)obj;
/* 120 */       return (super.equals(other)) && (this.value == other.getValue());
/*     */     }
/*     */     
/* 123 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(XAttribute other)
/*     */   {
/* 129 */     if (!(other instanceof XAttributeDiscrete)) {
/* 130 */       throw new ClassCastException();
/*     */     }
/* 132 */     int result = super.compareTo(other);
/* 133 */     if (result != 0) {
/* 134 */       return result;
/*     */     }
/* 136 */     return Long.valueOf(this.value).compareTo(Long.valueOf(((XAttributeDiscrete)other).getValue()));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeDiscreteImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */