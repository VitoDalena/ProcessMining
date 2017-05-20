/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
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
/*     */ 
/*     */ 
/*     */ public class XYAnnotationEntity
/*     */   extends ChartEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 2340334068383660799L;
/*     */   private int rendererIndex;
/*     */   
/*     */   public XYAnnotationEntity(Shape hotspot, int rendererIndex, String toolTipText, String urlText)
/*     */   {
/*  69 */     super(hotspot, toolTipText, urlText);
/*  70 */     this.rendererIndex = rendererIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getRendererIndex()
/*     */   {
/*  79 */     return this.rendererIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRendererIndex(int index)
/*     */   {
/*  88 */     this.rendererIndex = index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/*  99 */     if (obj == this) {
/* 100 */       return true;
/*     */     }
/* 102 */     if (!super.equals(obj)) {
/* 103 */       return false;
/*     */     }
/* 105 */     if (!(obj instanceof XYAnnotationEntity)) {
/* 106 */       return false;
/*     */     }
/* 108 */     XYAnnotationEntity that = (XYAnnotationEntity)obj;
/* 109 */     if (this.rendererIndex != that.rendererIndex) {
/* 110 */       return false;
/*     */     }
/* 112 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/XYAnnotationEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */