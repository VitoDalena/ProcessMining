/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.data.general.Dataset;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LegendItemEntity
/*     */   extends ChartEntity
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7435683933545666702L;
/*     */   private Dataset dataset;
/*     */   private Comparable seriesKey;
/*     */   private int seriesIndex;
/*     */   
/*     */   public LegendItemEntity(Shape area)
/*     */   {
/*  85 */     super(area);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dataset getDataset()
/*     */   {
/*  99 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(Dataset dataset)
/*     */   {
/* 110 */     this.dataset = dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSeriesKey()
/*     */   {
/* 123 */     return this.seriesKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesKey(Comparable key)
/*     */   {
/* 136 */     this.seriesKey = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public int getSeriesIndex()
/*     */   {
/* 149 */     return this.seriesIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setSeriesIndex(int index)
/*     */   {
/* 163 */     this.seriesIndex = index;
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
/* 174 */     if (obj == this) {
/* 175 */       return true;
/*     */     }
/* 177 */     if (!(obj instanceof LegendItemEntity)) {
/* 178 */       return false;
/*     */     }
/* 180 */     LegendItemEntity that = (LegendItemEntity)obj;
/* 181 */     if (!ObjectUtilities.equal(this.seriesKey, that.seriesKey)) {
/* 182 */       return false;
/*     */     }
/* 184 */     if (this.seriesIndex != that.seriesIndex) {
/* 185 */       return false;
/*     */     }
/* 187 */     if (!ObjectUtilities.equal(this.dataset, that.dataset)) {
/* 188 */       return false;
/*     */     }
/* 190 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 202 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 212 */     return "LegendItemEntity: seriesKey=" + this.seriesKey + ", dataset=" + this.dataset;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/LegendItemEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */