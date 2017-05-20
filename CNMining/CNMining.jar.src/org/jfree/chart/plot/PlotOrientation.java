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
/*     */ public final class PlotOrientation
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2508771828190337782L;
/*  57 */   public static final PlotOrientation HORIZONTAL = new PlotOrientation("PlotOrientation.HORIZONTAL");
/*     */   
/*     */ 
/*     */ 
/*  61 */   public static final PlotOrientation VERTICAL = new PlotOrientation("PlotOrientation.VERTICAL");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String name;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private PlotOrientation(String name)
/*     */   {
/*  73 */     this.name = name;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/*  82 */     return this.name;
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
/*  94 */     if (this == obj) {
/*  95 */       return true;
/*     */     }
/*  97 */     if (!(obj instanceof PlotOrientation)) {
/*  98 */       return false;
/*     */     }
/* 100 */     PlotOrientation orientation = (PlotOrientation)obj;
/* 101 */     if (!this.name.equals(orientation.toString())) {
/* 102 */       return false;
/*     */     }
/* 104 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 113 */     return this.name.hashCode();
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
/* 124 */     Object result = null;
/* 125 */     if (equals(HORIZONTAL)) {
/* 126 */       result = HORIZONTAL;
/*     */     }
/* 128 */     else if (equals(VERTICAL)) {
/* 129 */       result = VERTICAL;
/*     */     }
/* 131 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PlotOrientation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */