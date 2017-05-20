/*     */ package org.jfree.chart.event;
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
/*     */ public final class ChartChangeEventType
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5481917022435735602L;
/*  55 */   public static final ChartChangeEventType GENERAL = new ChartChangeEventType("ChartChangeEventType.GENERAL");
/*     */   
/*     */ 
/*     */ 
/*  59 */   public static final ChartChangeEventType NEW_DATASET = new ChartChangeEventType("ChartChangeEventType.NEW_DATASET");
/*     */   
/*     */ 
/*     */ 
/*  63 */   public static final ChartChangeEventType DATASET_UPDATED = new ChartChangeEventType("ChartChangeEventType.DATASET_UPDATED");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private ChartChangeEventType(String name)
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
/*  99 */     if (!(obj instanceof ChartChangeEventType)) {
/* 100 */       return false;
/*     */     }
/* 102 */     ChartChangeEventType that = (ChartChangeEventType)obj;
/* 103 */     if (!this.name.equals(that.toString())) {
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
/* 126 */     if (equals(GENERAL)) {
/* 127 */       return GENERAL;
/*     */     }
/* 129 */     if (equals(NEW_DATASET)) {
/* 130 */       return NEW_DATASET;
/*     */     }
/* 132 */     if (equals(DATASET_UPDATED)) {
/* 133 */       return DATASET_UPDATED;
/*     */     }
/* 135 */     return null;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/event/ChartChangeEventType.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */