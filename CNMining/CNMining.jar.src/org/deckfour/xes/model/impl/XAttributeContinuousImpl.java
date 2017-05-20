/*     */ package org.deckfour.xes.model.impl;
/*     */ 
/*     */ import org.deckfour.xes.extension.XExtension;
/*     */ import org.deckfour.xes.model.XAttribute;
/*     */ import org.deckfour.xes.model.XAttributeContinuous;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XAttributeContinuousImpl
/*     */   extends XAttributeImpl
/*     */   implements XAttributeContinuous
/*     */ {
/*     */   private double value;
/*     */   
/*     */   public XAttributeContinuousImpl(String key, double value)
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
/*     */ 
/*     */   public XAttributeContinuousImpl(String key, double value, XExtension extension)
/*     */   {
/*  82 */     super(key, extension);
/*  83 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getValue()
/*     */   {
/*  92 */     return this.value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValue(double value)
/*     */   {
/* 101 */     this.value = value;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 111 */     return Double.toString(this.value);
/*     */   }
/*     */   
/*     */   public Object clone() {
/* 115 */     return super.clone();
/*     */   }
/*     */   
/*     */   public boolean equals(Object obj) {
/* 119 */     if ((obj instanceof XAttributeContinuous)) {
/* 120 */       XAttributeContinuous other = (XAttributeContinuous)obj;
/* 121 */       return (super.equals(other)) && (this.value == other.getValue());
/*     */     }
/*     */     
/* 124 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */   public int compareTo(XAttribute other)
/*     */   {
/* 130 */     if (!(other instanceof XAttributeContinuous)) {
/* 131 */       throw new ClassCastException();
/*     */     }
/* 133 */     int result = super.compareTo(other);
/* 134 */     if (result != 0) {
/* 135 */       return result;
/*     */     }
/* 137 */     return Double.valueOf(this.value).compareTo(Double.valueOf(((XAttributeContinuous)other).getValue()));
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/xes/model/impl/XAttributeContinuousImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */