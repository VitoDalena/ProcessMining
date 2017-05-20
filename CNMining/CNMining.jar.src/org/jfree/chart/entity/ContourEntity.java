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
/*     */ /**
/*     */  * @deprecated
/*     */  */
/*     */ public class ContourEntity
/*     */   extends ChartEntity
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 1249570520505992847L;
/*  67 */   private int index = -1;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ContourEntity(Shape area, String toolTipText)
/*     */   {
/*  76 */     super(area, toolTipText);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ContourEntity(Shape area, String toolTipText, String urlText)
/*     */   {
/*  87 */     super(area, toolTipText, urlText);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getIndex()
/*     */   {
/*  96 */     return this.index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIndex(int index)
/*     */   {
/* 105 */     this.index = index;
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
/* 116 */     if (obj == this) {
/* 117 */       return true;
/*     */     }
/* 119 */     if (((obj instanceof ContourEntity)) && (super.equals(obj))) {
/* 120 */       ContourEntity ce = (ContourEntity)obj;
/* 121 */       if (this.index != ce.index) {
/* 122 */         return false;
/*     */       }
/* 124 */       return true;
/*     */     }
/* 126 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 137 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/ContourEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */