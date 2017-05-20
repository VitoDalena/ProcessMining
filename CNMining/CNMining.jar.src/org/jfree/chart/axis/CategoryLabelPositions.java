/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.io.Serializable;
/*     */ import org.jfree.text.TextBlockAnchor;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class CategoryLabelPositions
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8999557901920364580L;
/*  63 */   public static final CategoryLabelPositions STANDARD = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_CENTER), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_CENTER), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.CENTER_RIGHT, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.CENTER_LEFT, CategoryLabelWidthType.RANGE, 0.3F));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  82 */   public static final CategoryLabelPositions UP_90 = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, -1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, -1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, -1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, -1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 107 */   public static final CategoryLabelPositions DOWN_90 = new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.CENTER_RIGHT, TextAnchor.CENTER_RIGHT, 1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.CENTER_LEFT, TextAnchor.CENTER_LEFT, 1.5707963267948966D, CategoryLabelWidthType.RANGE, 0.3F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.TOP_CENTER, TextAnchor.TOP_CENTER, 1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_CENTER, TextAnchor.BOTTOM_CENTER, 1.5707963267948966D, CategoryLabelWidthType.CATEGORY, 0.9F));
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 131 */   public static final CategoryLabelPositions UP_45 = createUpRotationLabelPositions(0.7853981633974483D);
/*     */   
/*     */ 
/*     */ 
/* 135 */   public static final CategoryLabelPositions DOWN_45 = createDownRotationLabelPositions(0.7853981633974483D);
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtTop;
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtBottom;
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtLeft;
/*     */   
/*     */   private CategoryLabelPosition positionForAxisAtRight;
/*     */   
/*     */ 
/*     */   public static CategoryLabelPositions createUpRotationLabelPositions(double angle)
/*     */   {
/* 148 */     return new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, -angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_RIGHT, TextAnchor.TOP_RIGHT, -angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.BOTTOM_RIGHT, TextAnchor.BOTTOM_RIGHT, -angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, -angle, CategoryLabelWidthType.RANGE, 0.5F));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static CategoryLabelPositions createDownRotationLabelPositions(double angle)
/*     */   {
/* 182 */     return new CategoryLabelPositions(new CategoryLabelPosition(RectangleAnchor.BOTTOM, TextBlockAnchor.BOTTOM_RIGHT, TextAnchor.BOTTOM_RIGHT, angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.TOP, TextBlockAnchor.TOP_LEFT, TextAnchor.TOP_LEFT, angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.RIGHT, TextBlockAnchor.TOP_RIGHT, TextAnchor.TOP_RIGHT, angle, CategoryLabelWidthType.RANGE, 0.5F), new CategoryLabelPosition(RectangleAnchor.LEFT, TextBlockAnchor.BOTTOM_LEFT, TextAnchor.BOTTOM_LEFT, angle, CategoryLabelWidthType.RANGE, 0.5F));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryLabelPositions()
/*     */   {
/* 234 */     this.positionForAxisAtTop = new CategoryLabelPosition();
/* 235 */     this.positionForAxisAtBottom = new CategoryLabelPosition();
/* 236 */     this.positionForAxisAtLeft = new CategoryLabelPosition();
/* 237 */     this.positionForAxisAtRight = new CategoryLabelPosition();
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
/*     */   public CategoryLabelPositions(CategoryLabelPosition top, CategoryLabelPosition bottom, CategoryLabelPosition left, CategoryLabelPosition right)
/*     */   {
/* 257 */     if (top == null) {
/* 258 */       throw new IllegalArgumentException("Null 'top' argument.");
/*     */     }
/* 260 */     if (bottom == null) {
/* 261 */       throw new IllegalArgumentException("Null 'bottom' argument.");
/*     */     }
/* 263 */     if (left == null) {
/* 264 */       throw new IllegalArgumentException("Null 'left' argument.");
/*     */     }
/* 266 */     if (right == null) {
/* 267 */       throw new IllegalArgumentException("Null 'right' argument.");
/*     */     }
/*     */     
/* 270 */     this.positionForAxisAtTop = top;
/* 271 */     this.positionForAxisAtBottom = bottom;
/* 272 */     this.positionForAxisAtLeft = left;
/* 273 */     this.positionForAxisAtRight = right;
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
/*     */   public CategoryLabelPosition getLabelPosition(RectangleEdge edge)
/*     */   {
/* 286 */     CategoryLabelPosition result = null;
/* 287 */     if (edge == RectangleEdge.TOP) {
/* 288 */       result = this.positionForAxisAtTop;
/*     */     }
/* 290 */     else if (edge == RectangleEdge.BOTTOM) {
/* 291 */       result = this.positionForAxisAtBottom;
/*     */     }
/* 293 */     else if (edge == RectangleEdge.LEFT) {
/* 294 */       result = this.positionForAxisAtLeft;
/*     */     }
/* 296 */     else if (edge == RectangleEdge.RIGHT) {
/* 297 */       result = this.positionForAxisAtRight;
/*     */     }
/* 299 */     return result;
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
/*     */   public static CategoryLabelPositions replaceTopPosition(CategoryLabelPositions base, CategoryLabelPosition top)
/*     */   {
/* 314 */     if (base == null) {
/* 315 */       throw new IllegalArgumentException("Null 'base' argument.");
/*     */     }
/* 317 */     if (top == null) {
/* 318 */       throw new IllegalArgumentException("Null 'top' argument.");
/*     */     }
/*     */     
/* 321 */     return new CategoryLabelPositions(top, base.getLabelPosition(RectangleEdge.BOTTOM), base.getLabelPosition(RectangleEdge.LEFT), base.getLabelPosition(RectangleEdge.RIGHT));
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
/*     */   public static CategoryLabelPositions replaceBottomPosition(CategoryLabelPositions base, CategoryLabelPosition bottom)
/*     */   {
/* 341 */     if (base == null) {
/* 342 */       throw new IllegalArgumentException("Null 'base' argument.");
/*     */     }
/* 344 */     if (bottom == null) {
/* 345 */       throw new IllegalArgumentException("Null 'bottom' argument.");
/*     */     }
/*     */     
/* 348 */     return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), bottom, base.getLabelPosition(RectangleEdge.LEFT), base.getLabelPosition(RectangleEdge.RIGHT));
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
/*     */   public static CategoryLabelPositions replaceLeftPosition(CategoryLabelPositions base, CategoryLabelPosition left)
/*     */   {
/* 368 */     if (base == null) {
/* 369 */       throw new IllegalArgumentException("Null 'base' argument.");
/*     */     }
/* 371 */     if (left == null) {
/* 372 */       throw new IllegalArgumentException("Null 'left' argument.");
/*     */     }
/*     */     
/* 375 */     return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), base.getLabelPosition(RectangleEdge.BOTTOM), left, base.getLabelPosition(RectangleEdge.RIGHT));
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
/*     */   public static CategoryLabelPositions replaceRightPosition(CategoryLabelPositions base, CategoryLabelPosition right)
/*     */   {
/* 395 */     if (base == null) {
/* 396 */       throw new IllegalArgumentException("Null 'base' argument.");
/*     */     }
/* 398 */     if (right == null) {
/* 399 */       throw new IllegalArgumentException("Null 'right' argument.");
/*     */     }
/*     */     
/* 402 */     return new CategoryLabelPositions(base.getLabelPosition(RectangleEdge.TOP), base.getLabelPosition(RectangleEdge.BOTTOM), base.getLabelPosition(RectangleEdge.LEFT), right);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 420 */     if (this == obj) {
/* 421 */       return true;
/*     */     }
/* 423 */     if (!(obj instanceof CategoryLabelPositions)) {
/* 424 */       return false;
/*     */     }
/*     */     
/* 427 */     CategoryLabelPositions that = (CategoryLabelPositions)obj;
/* 428 */     if (!this.positionForAxisAtTop.equals(that.positionForAxisAtTop)) {
/* 429 */       return false;
/*     */     }
/* 431 */     if (!this.positionForAxisAtBottom.equals(that.positionForAxisAtBottom))
/*     */     {
/* 433 */       return false;
/*     */     }
/* 435 */     if (!this.positionForAxisAtLeft.equals(that.positionForAxisAtLeft)) {
/* 436 */       return false;
/*     */     }
/* 438 */     if (!this.positionForAxisAtRight.equals(that.positionForAxisAtRight)) {
/* 439 */       return false;
/*     */     }
/*     */     
/* 442 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 452 */     int result = 19;
/* 453 */     result = 37 * result + this.positionForAxisAtTop.hashCode();
/* 454 */     result = 37 * result + this.positionForAxisAtBottom.hashCode();
/* 455 */     result = 37 * result + this.positionForAxisAtLeft.hashCode();
/* 456 */     result = 37 * result + this.positionForAxisAtRight.hashCode();
/* 457 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryLabelPositions.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */