/*     */ package org.jfree.chart.labels;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemLabelPosition
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5845390630157034499L;
/*     */   private ItemLabelAnchor itemLabelAnchor;
/*     */   private TextAnchor textAnchor;
/*     */   private TextAnchor rotationAnchor;
/*     */   private double angle;
/*     */   
/*     */   public ItemLabelPosition()
/*     */   {
/*  75 */     this(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0D);
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
/*     */   public ItemLabelPosition(ItemLabelAnchor itemLabelAnchor, TextAnchor textAnchor)
/*     */   {
/*  88 */     this(itemLabelAnchor, textAnchor, TextAnchor.CENTER, 0.0D);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemLabelPosition(ItemLabelAnchor itemLabelAnchor, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*     */   {
/* 109 */     if (itemLabelAnchor == null) {
/* 110 */       throw new IllegalArgumentException("Null 'itemLabelAnchor' argument.");
/*     */     }
/*     */     
/* 113 */     if (textAnchor == null) {
/* 114 */       throw new IllegalArgumentException("Null 'textAnchor' argument.");
/*     */     }
/* 116 */     if (rotationAnchor == null) {
/* 117 */       throw new IllegalArgumentException("Null 'rotationAnchor' argument.");
/*     */     }
/*     */     
/*     */ 
/* 121 */     this.itemLabelAnchor = itemLabelAnchor;
/* 122 */     this.textAnchor = textAnchor;
/* 123 */     this.rotationAnchor = rotationAnchor;
/* 124 */     this.angle = angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemLabelAnchor getItemLabelAnchor()
/*     */   {
/* 134 */     return this.itemLabelAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getTextAnchor()
/*     */   {
/* 143 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getRotationAnchor()
/*     */   {
/* 152 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 161 */     return this.angle;
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
/* 172 */     if (obj == this) {
/* 173 */       return true;
/*     */     }
/* 175 */     if (!(obj instanceof ItemLabelPosition)) {
/* 176 */       return false;
/*     */     }
/* 178 */     ItemLabelPosition that = (ItemLabelPosition)obj;
/* 179 */     if (!this.itemLabelAnchor.equals(that.itemLabelAnchor)) {
/* 180 */       return false;
/*     */     }
/* 182 */     if (!this.textAnchor.equals(that.textAnchor)) {
/* 183 */       return false;
/*     */     }
/* 185 */     if (!this.rotationAnchor.equals(that.rotationAnchor)) {
/* 186 */       return false;
/*     */     }
/* 188 */     if (this.angle != that.angle) {
/* 189 */       return false;
/*     */     }
/* 191 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/labels/ItemLabelPosition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */