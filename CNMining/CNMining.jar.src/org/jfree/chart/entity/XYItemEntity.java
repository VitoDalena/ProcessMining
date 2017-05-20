/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYItemEntity
/*     */   extends ChartEntity
/*     */ {
/*     */   private static final long serialVersionUID = -3870862224880283771L;
/*     */   private transient XYDataset dataset;
/*     */   private int series;
/*     */   private int item;
/*     */   
/*     */   public XYItemEntity(Shape area, XYDataset dataset, int series, int item, String toolTipText, String urlText)
/*     */   {
/*  88 */     super(area, toolTipText, urlText);
/*  89 */     this.dataset = dataset;
/*  90 */     this.series = series;
/*  91 */     this.item = item;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYDataset getDataset()
/*     */   {
/* 100 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(XYDataset dataset)
/*     */   {
/* 109 */     this.dataset = dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSeriesIndex()
/*     */   {
/* 118 */     return this.series;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesIndex(int series)
/*     */   {
/* 127 */     this.series = series;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getItem()
/*     */   {
/* 136 */     return this.item;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setItem(int item)
/*     */   {
/* 145 */     this.item = item;
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
/* 156 */     if (obj == this) {
/* 157 */       return true;
/*     */     }
/* 159 */     if (((obj instanceof XYItemEntity)) && (super.equals(obj))) {
/* 160 */       XYItemEntity ie = (XYItemEntity)obj;
/* 161 */       if (this.series != ie.series) {
/* 162 */         return false;
/*     */       }
/* 164 */       if (this.item != ie.item) {
/* 165 */         return false;
/*     */       }
/* 167 */       return true;
/*     */     }
/* 169 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 179 */     return "XYItemEntity: series = " + getSeriesIndex() + ", item = " + getItem() + ", dataset = " + getDataset();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/XYItemEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */