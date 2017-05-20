/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.data.general.PieDataset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PieSectionEntity
/*     */   extends ChartEntity
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 9199892576531984162L;
/*     */   private PieDataset dataset;
/*     */   private int pieIndex;
/*     */   private int sectionIndex;
/*     */   private Comparable sectionKey;
/*     */   
/*     */   public PieSectionEntity(Shape area, PieDataset dataset, int pieIndex, int sectionIndex, Comparable sectionKey, String toolTipText, String urlText)
/*     */   {
/* 102 */     super(area, toolTipText, urlText);
/* 103 */     this.dataset = dataset;
/* 104 */     this.pieIndex = pieIndex;
/* 105 */     this.sectionIndex = sectionIndex;
/* 106 */     this.sectionKey = sectionKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PieDataset getDataset()
/*     */   {
/* 118 */     return this.dataset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDataset(PieDataset dataset)
/*     */   {
/* 129 */     this.dataset = dataset;
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
/*     */   public int getPieIndex()
/*     */   {
/* 142 */     return this.pieIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPieIndex(int index)
/*     */   {
/* 153 */     this.pieIndex = index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSectionIndex()
/*     */   {
/* 164 */     return this.sectionIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSectionIndex(int index)
/*     */   {
/* 175 */     this.sectionIndex = index;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getSectionKey()
/*     */   {
/* 186 */     return this.sectionKey;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSectionKey(Comparable key)
/*     */   {
/* 197 */     this.sectionKey = key;
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
/* 208 */     if (obj == this) {
/* 209 */       return true;
/*     */     }
/* 211 */     if (!(obj instanceof PieSectionEntity)) {
/* 212 */       return false;
/*     */     }
/* 214 */     PieSectionEntity that = (PieSectionEntity)obj;
/* 215 */     if (!ObjectUtilities.equal(this.dataset, that.dataset)) {
/* 216 */       return false;
/*     */     }
/* 218 */     if (this.pieIndex != that.pieIndex) {
/* 219 */       return false;
/*     */     }
/* 221 */     if (this.sectionIndex != that.sectionIndex) {
/* 222 */       return false;
/*     */     }
/* 224 */     if (!ObjectUtilities.equal(this.sectionKey, that.sectionKey)) {
/* 225 */       return false;
/*     */     }
/* 227 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 236 */     int result = super.hashCode();
/* 237 */     result = HashUtilities.hashCode(result, this.pieIndex);
/* 238 */     result = HashUtilities.hashCode(result, this.sectionIndex);
/* 239 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 248 */     return "PieSection: " + this.pieIndex + ", " + this.sectionIndex + "(" + this.sectionKey.toString() + ")";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/PieSectionEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */