/*     */ package org.jfree.chart.plot;
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
/*     */ 
/*     */ 
/*     */ public final class DialShape
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3471933055190251131L;
/*  57 */   public static final DialShape CIRCLE = new DialShape("DialShape.CIRCLE");
/*     */   
/*     */ 
/*  60 */   public static final DialShape CHORD = new DialShape("DialShape.CHORD");
/*     */   
/*     */ 
/*  63 */   public static final DialShape PIE = new DialShape("DialShape.PIE");
/*     */   
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private DialShape(String name)
/*     */   {
/*  74 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  83 */     return this.name;
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
/*  95 */     if (this == obj) {
/*  96 */       return true;
/*     */     }
/*  98 */     if (!(obj instanceof DialShape)) {
/*  99 */       return false;
/*     */     }
/* 101 */     DialShape shape = (DialShape)obj;
/* 102 */     if (!this.name.equals(shape.toString())) {
/* 103 */       return false;
/*     */     }
/* 105 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 114 */     return this.name.hashCode();
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
/* 125 */     if (equals(CIRCLE)) {
/* 126 */       return CIRCLE;
/*     */     }
/* 128 */     if (equals(CHORD)) {
/* 129 */       return CHORD;
/*     */     }
/* 131 */     if (equals(PIE)) {
/* 132 */       return PIE;
/*     */     }
/* 134 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/DialShape.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */