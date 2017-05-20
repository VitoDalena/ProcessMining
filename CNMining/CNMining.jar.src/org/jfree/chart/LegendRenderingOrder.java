/*     */ package org.jfree.chart;
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
/*     */ public final class LegendRenderingOrder
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3832486612685808616L;
/*  55 */   public static final LegendRenderingOrder STANDARD = new LegendRenderingOrder("LegendRenderingOrder.STANDARD");
/*     */   
/*     */ 
/*     */ 
/*  59 */   public static final LegendRenderingOrder REVERSE = new LegendRenderingOrder("LegendRenderingOrder.REVERSE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private LegendRenderingOrder(String name)
/*     */   {
/*  71 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  80 */     return this.name;
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
/*  92 */     if (this == obj) {
/*  93 */       return true;
/*     */     }
/*  95 */     if (!(obj instanceof LegendRenderingOrder)) {
/*  96 */       return false;
/*     */     }
/*  98 */     LegendRenderingOrder order = (LegendRenderingOrder)obj;
/*  99 */     if (!this.name.equals(order.toString())) {
/* 100 */       return false;
/*     */     }
/* 102 */     return true;
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
/* 113 */     if (equals(STANDARD)) {
/* 114 */       return STANDARD;
/*     */     }
/* 116 */     if (equals(REVERSE)) {
/* 117 */       return REVERSE;
/*     */     }
/* 119 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/LegendRenderingOrder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */