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
/*     */ 
/*     */ 
/*     */ public final class DatasetRenderingOrder
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -600593412366385072L;
/*  62 */   public static final DatasetRenderingOrder FORWARD = new DatasetRenderingOrder("DatasetRenderingOrder.FORWARD");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  69 */   public static final DatasetRenderingOrder REVERSE = new DatasetRenderingOrder("DatasetRenderingOrder.REVERSE");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private DatasetRenderingOrder(String name)
/*     */   {
/*  81 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  90 */     return this.name;
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
/* 102 */     if (this == obj) {
/* 103 */       return true;
/*     */     }
/* 105 */     if (!(obj instanceof DatasetRenderingOrder)) {
/* 106 */       return false;
/*     */     }
/* 108 */     DatasetRenderingOrder order = (DatasetRenderingOrder)obj;
/* 109 */     if (!this.name.equals(order.toString())) {
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 121 */     return this.name.hashCode();
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
/* 132 */     if (equals(FORWARD)) {
/* 133 */       return FORWARD;
/*     */     }
/* 135 */     if (equals(REVERSE)) {
/* 136 */       return REVERSE;
/*     */     }
/* 138 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/DatasetRenderingOrder.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */