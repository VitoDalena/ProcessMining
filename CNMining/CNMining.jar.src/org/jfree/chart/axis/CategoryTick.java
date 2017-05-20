/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import org.jfree.text.TextBlock;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ public class CategoryTick
/*     */   extends Tick
/*     */ {
/*     */   private Comparable category;
/*     */   private TextBlock label;
/*     */   private TextBlockAnchor labelAnchor;
/*     */   
/*     */   public CategoryTick(Comparable category, TextBlock label, TextBlockAnchor labelAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/*  78 */     super("", TextAnchor.CENTER, rotationAnchor, angle);
/*  79 */     this.category = category;
/*  80 */     this.label = label;
/*  81 */     this.labelAnchor = labelAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Comparable getCategory()
/*     */   {
/*  91 */     return this.category;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextBlock getLabel()
/*     */   {
/* 100 */     return this.label;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextBlockAnchor getLabelAnchor()
/*     */   {
/* 109 */     return this.labelAnchor;
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
/* 120 */     if (this == obj) {
/* 121 */       return true;
/*     */     }
/* 123 */     if (((obj instanceof CategoryTick)) && (super.equals(obj))) {
/* 124 */       CategoryTick that = (CategoryTick)obj;
/* 125 */       if (!ObjectUtilities.equal(this.category, that.category)) {
/* 126 */         return false;
/*     */       }
/* 128 */       if (!ObjectUtilities.equal(this.label, that.label)) {
/* 129 */         return false;
/*     */       }
/* 131 */       if (!ObjectUtilities.equal(this.labelAnchor, that.labelAnchor)) {
/* 132 */         return false;
/*     */       }
/* 134 */       return true;
/*     */     }
/* 136 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 145 */     int result = 41;
/* 146 */     result = 37 * result + this.category.hashCode();
/* 147 */     result = 37 * result + this.label.hashCode();
/* 148 */     result = 37 * result + this.labelAnchor.hashCode();
/* 149 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryTick.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */