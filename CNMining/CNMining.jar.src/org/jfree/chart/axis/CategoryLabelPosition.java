/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.ui.RectangleAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryLabelPosition
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5168681143844183864L;
/*     */   private RectangleAnchor categoryAnchor;
/*     */   private TextBlockAnchor labelAnchor;
/*     */   private TextAnchor rotationAnchor;
/*     */   private double angle;
/*     */   private CategoryLabelWidthType widthType;
/*     */   private float widthRatio;
/*     */   
/*     */   public CategoryLabelPosition()
/*     */   {
/*  89 */     this(RectangleAnchor.CENTER, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.CENTER, 0.0D, CategoryLabelWidthType.CATEGORY, 0.95F);
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
/*     */   public CategoryLabelPosition(RectangleAnchor categoryAnchor, TextBlockAnchor labelAnchor)
/*     */   {
/* 103 */     this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0D, CategoryLabelWidthType.CATEGORY, 0.95F);
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
/*     */   public CategoryLabelPosition(RectangleAnchor categoryAnchor, TextBlockAnchor labelAnchor, CategoryLabelWidthType widthType, float widthRatio)
/*     */   {
/* 122 */     this(categoryAnchor, labelAnchor, TextAnchor.CENTER, 0.0D, widthType, widthRatio);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryLabelPosition(RectangleAnchor categoryAnchor, TextBlockAnchor labelAnchor, TextAnchor rotationAnchor, double angle, CategoryLabelWidthType widthType, float widthRatio)
/*     */   {
/* 149 */     if (categoryAnchor == null) {
/* 150 */       throw new IllegalArgumentException("Null 'categoryAnchor' argument.");
/*     */     }
/*     */     
/* 153 */     if (labelAnchor == null) {
/* 154 */       throw new IllegalArgumentException("Null 'labelAnchor' argument.");
/*     */     }
/*     */     
/* 157 */     if (rotationAnchor == null) {
/* 158 */       throw new IllegalArgumentException("Null 'rotationAnchor' argument.");
/*     */     }
/*     */     
/* 161 */     if (widthType == null) {
/* 162 */       throw new IllegalArgumentException("Null 'widthType' argument.");
/*     */     }
/*     */     
/* 165 */     this.categoryAnchor = categoryAnchor;
/* 166 */     this.labelAnchor = labelAnchor;
/* 167 */     this.rotationAnchor = rotationAnchor;
/* 168 */     this.angle = angle;
/* 169 */     this.widthType = widthType;
/* 170 */     this.widthRatio = widthRatio;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getCategoryAnchor()
/*     */   {
/* 180 */     return this.categoryAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextBlockAnchor getLabelAnchor()
/*     */   {
/* 189 */     return this.labelAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getRotationAnchor()
/*     */   {
/* 198 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 207 */     return this.angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryLabelWidthType getWidthType()
/*     */   {
/* 216 */     return this.widthType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public float getWidthRatio()
/*     */   {
/* 225 */     return this.widthRatio;
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
/* 236 */     if (obj == this) {
/* 237 */       return true;
/*     */     }
/* 239 */     if (!(obj instanceof CategoryLabelPosition)) {
/* 240 */       return false;
/*     */     }
/* 242 */     CategoryLabelPosition that = (CategoryLabelPosition)obj;
/* 243 */     if (!this.categoryAnchor.equals(that.categoryAnchor)) {
/* 244 */       return false;
/*     */     }
/* 246 */     if (!this.labelAnchor.equals(that.labelAnchor)) {
/* 247 */       return false;
/*     */     }
/* 249 */     if (!this.rotationAnchor.equals(that.rotationAnchor)) {
/* 250 */       return false;
/*     */     }
/* 252 */     if (this.angle != that.angle) {
/* 253 */       return false;
/*     */     }
/* 255 */     if (this.widthType != that.widthType) {
/* 256 */       return false;
/*     */     }
/* 258 */     if (this.widthRatio != that.widthRatio) {
/* 259 */       return false;
/*     */     }
/* 261 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 270 */     int result = 19;
/* 271 */     result = 37 * result + this.categoryAnchor.hashCode();
/* 272 */     result = 37 * result + this.labelAnchor.hashCode();
/* 273 */     result = 37 * result + this.rotationAnchor.hashCode();
/* 274 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryLabelPosition.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */