/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.ObjectStreamException;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TickType
/*     */   implements Serializable
/*     */ {
/*  54 */   public static final TickType MAJOR = new TickType("MAJOR");
/*     */   
/*     */ 
/*  57 */   public static final TickType MINOR = new TickType("MINOR");
/*     */   
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private TickType(String name)
/*     */   {
/*  68 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  77 */     return this.name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  89 */     if (this == obj) {
/*  90 */       return true;
/*     */     }
/*  92 */     if (!(obj instanceof TickType)) {
/*  93 */       return false;
/*     */     }
/*     */     
/*  96 */     TickType that = (TickType)obj;
/*  97 */     if (!this.name.equals(that.name)) {
/*  98 */       return false;
/*     */     }
/* 100 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private Object readResolve()
/*     */     throws ObjectStreamException
/*     */   {
/* 111 */     Object result = null;
/* 112 */     if (equals(MAJOR)) {
/* 113 */       result = MAJOR;
/*     */     }
/* 115 */     else if (equals(MINOR)) {
/* 116 */       result = MINOR;
/*     */     }
/* 118 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/TickType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */