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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class SeriesRenderingOrder
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 209336477448807735L;
/*  60 */   public static final SeriesRenderingOrder FORWARD = new SeriesRenderingOrder("SeriesRenderingOrder.FORWARD");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  67 */   public static final SeriesRenderingOrder REVERSE = new SeriesRenderingOrder("SeriesRenderingOrder.REVERSE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private SeriesRenderingOrder(String name)
/*     */   {
/*  79 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  88 */     return this.name;
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
/* 100 */     if (this == obj) {
/* 101 */       return true;
/*     */     }
/* 103 */     if (!(obj instanceof SeriesRenderingOrder)) {
/* 104 */       return false;
/*     */     }
/* 106 */     SeriesRenderingOrder order = (SeriesRenderingOrder)obj;
/* 107 */     if (!this.name.equals(order.toString())) {
/* 108 */       return false;
/*     */     }
/* 110 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 119 */     return this.name.hashCode();
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
/* 130 */     if (equals(FORWARD)) {
/* 131 */       return FORWARD;
/*     */     }
/* 133 */     if (equals(REVERSE)) {
/* 134 */       return REVERSE;
/*     */     }
/* 136 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/SeriesRenderingOrder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */