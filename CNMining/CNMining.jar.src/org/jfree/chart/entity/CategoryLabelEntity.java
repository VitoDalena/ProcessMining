/*     */ package org.jfree.chart.entity;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import org.jfree.chart.HashUtilities;
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
/*     */ public class CategoryLabelEntity
/*     */   extends TickLabelEntity
/*     */ {
/*     */   private Comparable key;
/*     */   
/*     */   public CategoryLabelEntity(Comparable key, Shape area, String toolTipText, String urlText)
/*     */   {
/*  70 */     super(area, toolTipText, urlText);
/*  71 */     this.key = key;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getKey()
/*     */   {
/*  80 */     return this.key;
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
/*  91 */     if (obj == this) {
/*  92 */       return true;
/*     */     }
/*  94 */     if (!(obj instanceof CategoryLabelEntity)) {
/*  95 */       return false;
/*     */     }
/*  97 */     CategoryLabelEntity that = (CategoryLabelEntity)obj;
/*  98 */     if (!ObjectUtilities.equal(this.key, that.key)) {
/*  99 */       return false;
/*     */     }
/* 101 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 110 */     int result = super.hashCode();
/* 111 */     result = HashUtilities.hashCode(result, this.key);
/* 112 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 122 */     StringBuffer buf = new StringBuffer("CategoryLabelEntity: ");
/* 123 */     buf.append("category=");
/* 124 */     buf.append(this.key);
/* 125 */     buf.append(", tooltip=" + getToolTipText());
/* 126 */     buf.append(", url=" + getURLText());
/* 127 */     return buf.toString();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/entity/CategoryLabelEntity.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */