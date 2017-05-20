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
/*     */ public final class PieLabelLinkStyle
/*     */   implements Serializable
/*     */ {
/*  55 */   public static final PieLabelLinkStyle STANDARD = new PieLabelLinkStyle("PieLabelLinkStyle.STANDARD");
/*     */   
/*     */ 
/*     */ 
/*  59 */   public static final PieLabelLinkStyle QUAD_CURVE = new PieLabelLinkStyle("PieLabelLinkStyle.QUAD_CURVE");
/*     */   
/*     */ 
/*     */ 
/*  63 */   public static final PieLabelLinkStyle CUBIC_CURVE = new PieLabelLinkStyle("PieLabelLinkStyle.CUBIC_CURVE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private PieLabelLinkStyle(String name)
/*     */   {
/*  75 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  84 */     return this.name;
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
/*  96 */     if (this == obj) {
/*  97 */       return true;
/*     */     }
/*  99 */     if (!(obj instanceof PieLabelLinkStyle)) {
/* 100 */       return false;
/*     */     }
/* 102 */     PieLabelLinkStyle style = (PieLabelLinkStyle)obj;
/* 103 */     if (!this.name.equals(style.toString())) {
/* 104 */       return false;
/*     */     }
/* 106 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 115 */     return this.name.hashCode();
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
/* 126 */     Object result = null;
/* 127 */     if (equals(STANDARD)) {
/* 128 */       result = STANDARD;
/*     */     }
/* 130 */     else if (equals(QUAD_CURVE)) {
/* 131 */       result = QUAD_CURVE;
/*     */     }
/* 133 */     else if (equals(CUBIC_CURVE)) {
/* 134 */       result = CUBIC_CURVE;
/*     */     }
/* 136 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PieLabelLinkStyle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */