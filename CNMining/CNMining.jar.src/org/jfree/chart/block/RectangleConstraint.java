/*     */ package org.jfree.chart.block;
/*     */ 
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.Size2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RectangleConstraint
/*     */ {
/*  59 */   public static final RectangleConstraint NONE = new RectangleConstraint(0.0D, null, LengthConstraintType.NONE, 0.0D, null, LengthConstraintType.NONE);
/*     */   
/*     */ 
/*     */ 
/*     */   private double width;
/*     */   
/*     */ 
/*     */ 
/*     */   private Range widthRange;
/*     */   
/*     */ 
/*     */ 
/*     */   private LengthConstraintType widthConstraintType;
/*     */   
/*     */ 
/*     */ 
/*     */   private double height;
/*     */   
/*     */ 
/*     */   private Range heightRange;
/*     */   
/*     */ 
/*     */   private LengthConstraintType heightConstraintType;
/*     */   
/*     */ 
/*     */ 
/*     */   public RectangleConstraint(double w, double h)
/*     */   {
/*  87 */     this(w, null, LengthConstraintType.FIXED, h, null, LengthConstraintType.FIXED);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleConstraint(Range w, Range h)
/*     */   {
/*  98 */     this(0.0D, w, LengthConstraintType.RANGE, 0.0D, h, LengthConstraintType.RANGE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleConstraint(Range w, double h)
/*     */   {
/* 110 */     this(0.0D, w, LengthConstraintType.RANGE, h, null, LengthConstraintType.FIXED);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleConstraint(double w, Range h)
/*     */   {
/* 122 */     this(w, null, LengthConstraintType.FIXED, 0.0D, h, LengthConstraintType.RANGE);
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
/*     */   public RectangleConstraint(double w, Range widthRange, LengthConstraintType widthConstraintType, double h, Range heightRange, LengthConstraintType heightConstraintType)
/*     */   {
/* 140 */     if (widthConstraintType == null) {
/* 141 */       throw new IllegalArgumentException("Null 'widthType' argument.");
/*     */     }
/* 143 */     if (heightConstraintType == null) {
/* 144 */       throw new IllegalArgumentException("Null 'heightType' argument.");
/*     */     }
/* 146 */     this.width = w;
/* 147 */     this.widthRange = widthRange;
/* 148 */     this.widthConstraintType = widthConstraintType;
/* 149 */     this.height = h;
/* 150 */     this.heightRange = heightRange;
/* 151 */     this.heightConstraintType = heightConstraintType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getWidth()
/*     */   {
/* 160 */     return this.width;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getWidthRange()
/*     */   {
/* 169 */     return this.widthRange;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LengthConstraintType getWidthConstraintType()
/*     */   {
/* 178 */     return this.widthConstraintType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getHeight()
/*     */   {
/* 187 */     return this.height;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getHeightRange()
/*     */   {
/* 196 */     return this.heightRange;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LengthConstraintType getHeightConstraintType()
/*     */   {
/* 205 */     return this.heightConstraintType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleConstraint toUnconstrainedWidth()
/*     */   {
/* 215 */     if (this.widthConstraintType == LengthConstraintType.NONE) {
/* 216 */       return this;
/*     */     }
/*     */     
/* 219 */     return new RectangleConstraint(this.width, this.widthRange, LengthConstraintType.NONE, this.height, this.heightRange, this.heightConstraintType);
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
/*     */   public RectangleConstraint toUnconstrainedHeight()
/*     */   {
/* 232 */     if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 233 */       return this;
/*     */     }
/*     */     
/* 236 */     return new RectangleConstraint(this.width, this.widthRange, this.widthConstraintType, 0.0D, this.heightRange, LengthConstraintType.NONE);
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
/*     */   public RectangleConstraint toFixedWidth(double width)
/*     */   {
/* 251 */     return new RectangleConstraint(width, this.widthRange, LengthConstraintType.FIXED, this.height, this.heightRange, this.heightConstraintType);
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
/*     */   public RectangleConstraint toFixedHeight(double height)
/*     */   {
/* 265 */     return new RectangleConstraint(this.width, this.widthRange, this.widthConstraintType, height, this.heightRange, LengthConstraintType.FIXED);
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
/*     */   public RectangleConstraint toRangeWidth(Range range)
/*     */   {
/* 279 */     if (range == null) {
/* 280 */       throw new IllegalArgumentException("Null 'range' argument.");
/*     */     }
/* 282 */     return new RectangleConstraint(range.getUpperBound(), range, LengthConstraintType.RANGE, this.height, this.heightRange, this.heightConstraintType);
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
/*     */   public RectangleConstraint toRangeHeight(Range range)
/*     */   {
/* 296 */     if (range == null) {
/* 297 */       throw new IllegalArgumentException("Null 'range' argument.");
/*     */     }
/* 299 */     return new RectangleConstraint(this.width, this.widthRange, this.widthConstraintType, range.getUpperBound(), range, LengthConstraintType.RANGE);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String toString()
/*     */   {
/* 311 */     return "RectangleConstraint[" + this.widthConstraintType.toString() + ": width=" + this.width + ", height=" + this.height + "]";
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
/*     */   public Size2D calculateConstrainedSize(Size2D base)
/*     */   {
/* 325 */     Size2D result = new Size2D();
/* 326 */     if (this.widthConstraintType == LengthConstraintType.NONE) {
/* 327 */       result.width = base.width;
/* 328 */       if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 329 */         result.height = base.height;
/*     */       }
/* 331 */       else if (this.heightConstraintType == LengthConstraintType.RANGE) {
/* 332 */         result.height = this.heightRange.constrain(base.height);
/*     */       }
/* 334 */       else if (this.heightConstraintType == LengthConstraintType.FIXED) {
/* 335 */         result.height = this.height;
/*     */       }
/*     */     }
/* 338 */     else if (this.widthConstraintType == LengthConstraintType.RANGE) {
/* 339 */       result.width = this.widthRange.constrain(base.width);
/* 340 */       if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 341 */         result.height = base.height;
/*     */       }
/* 343 */       else if (this.heightConstraintType == LengthConstraintType.RANGE) {
/* 344 */         result.height = this.heightRange.constrain(base.height);
/*     */       }
/* 346 */       else if (this.heightConstraintType == LengthConstraintType.FIXED) {
/* 347 */         result.height = this.height;
/*     */       }
/*     */     }
/* 350 */     else if (this.widthConstraintType == LengthConstraintType.FIXED) {
/* 351 */       result.width = this.width;
/* 352 */       if (this.heightConstraintType == LengthConstraintType.NONE) {
/* 353 */         result.height = base.height;
/*     */       }
/* 355 */       else if (this.heightConstraintType == LengthConstraintType.RANGE) {
/* 356 */         result.height = this.heightRange.constrain(base.height);
/*     */       }
/* 358 */       else if (this.heightConstraintType == LengthConstraintType.FIXED) {
/* 359 */         result.height = this.height;
/*     */       }
/*     */     }
/* 362 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/block/RectangleConstraint.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */