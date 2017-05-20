/*     */ package org.jfree.data;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Range
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -906333695431863380L;
/*     */   private double lower;
/*     */   private double upper;
/*     */   
/*     */   public strictfp Range(double lower, double upper)
/*     */   {
/*  87 */     if (lower > upper) {
/*  88 */       String msg = "Range(double, double): require lower (" + lower + ") <= upper (" + upper + ").";
/*     */       
/*  90 */       throw new IllegalArgumentException(msg);
/*     */     }
/*  92 */     this.lower = lower;
/*  93 */     this.upper = upper;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp double getLowerBound()
/*     */   {
/* 102 */     return this.lower;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp double getUpperBound()
/*     */   {
/* 111 */     return this.upper;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp double getLength()
/*     */   {
/* 120 */     return this.upper - this.lower;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp double getCentralValue()
/*     */   {
/* 129 */     return this.lower / 2.0D + this.upper / 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp boolean contains(double value)
/*     */   {
/* 141 */     return (value >= this.lower) && (value <= this.upper);
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
/*     */   public strictfp boolean intersects(double b0, double b1)
/*     */   {
/* 154 */     if (b0 <= this.lower) {
/* 155 */       return b1 > this.lower;
/*     */     }
/*     */     
/* 158 */     return (b0 < this.upper) && (b1 >= b0);
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
/*     */   public strictfp boolean intersects(Range range)
/*     */   {
/* 173 */     return intersects(range.getLowerBound(), range.getUpperBound());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp double constrain(double value)
/*     */   {
/* 185 */     double result = value;
/* 186 */     if (!contains(value)) {
/* 187 */       if (value > this.upper) {
/* 188 */         result = this.upper;
/*     */       }
/* 190 */       else if (value < this.lower) {
/* 191 */         result = this.lower;
/*     */       }
/*     */     }
/* 194 */     return result;
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
/*     */   public static strictfp Range combine(Range range1, Range range2)
/*     */   {
/* 214 */     if (range1 == null) {
/* 215 */       return range2;
/*     */     }
/*     */     
/* 218 */     if (range2 == null) {
/* 219 */       return range1;
/*     */     }
/*     */     
/* 222 */     double l = Math.min(range1.getLowerBound(), range2.getLowerBound());
/*     */     
/* 224 */     double u = Math.max(range1.getUpperBound(), range2.getUpperBound());
/*     */     
/* 226 */     return new Range(l, u);
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
/*     */   public static strictfp Range expandToInclude(Range range, double value)
/*     */   {
/* 243 */     if (range == null) {
/* 244 */       return new Range(value, value);
/*     */     }
/* 246 */     if (value < range.getLowerBound()) {
/* 247 */       return new Range(value, range.getUpperBound());
/*     */     }
/* 249 */     if (value > range.getUpperBound()) {
/* 250 */       return new Range(range.getLowerBound(), value);
/*     */     }
/*     */     
/* 253 */     return range;
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
/*     */   public static strictfp Range expand(Range range, double lowerMargin, double upperMargin)
/*     */   {
/* 270 */     if (range == null) {
/* 271 */       throw new IllegalArgumentException("Null 'range' argument.");
/*     */     }
/* 273 */     double length = range.getLength();
/* 274 */     double lower = range.getLowerBound() - length * lowerMargin;
/* 275 */     double upper = range.getUpperBound() + length * upperMargin;
/* 276 */     if (lower > upper) {
/* 277 */       lower = lower / 2.0D + upper / 2.0D;
/* 278 */       upper = lower;
/*     */     }
/* 280 */     return new Range(lower, upper);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static strictfp Range shift(Range base, double delta)
/*     */   {
/* 292 */     return shift(base, delta, false);
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
/*     */   public static strictfp Range shift(Range base, double delta, boolean allowZeroCrossing)
/*     */   {
/* 308 */     if (base == null) {
/* 309 */       throw new IllegalArgumentException("Null 'base' argument.");
/*     */     }
/* 311 */     if (allowZeroCrossing) {
/* 312 */       return new Range(base.getLowerBound() + delta, base.getUpperBound() + delta);
/*     */     }
/*     */     
/*     */ 
/* 316 */     return new Range(shiftWithNoZeroCrossing(base.getLowerBound(), delta), shiftWithNoZeroCrossing(base.getUpperBound(), delta));
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
/*     */   private static strictfp double shiftWithNoZeroCrossing(double value, double delta)
/*     */   {
/* 332 */     if (value > 0.0D) {
/* 333 */       return Math.max(value + delta, 0.0D);
/*     */     }
/* 335 */     if (value < 0.0D) {
/* 336 */       return Math.min(value + delta, 0.0D);
/*     */     }
/*     */     
/* 339 */     return value + delta;
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
/*     */   public static strictfp Range scale(Range base, double factor)
/*     */   {
/* 354 */     if (base == null) {
/* 355 */       throw new IllegalArgumentException("Null 'base' argument.");
/*     */     }
/* 357 */     if (factor < 0.0D) {
/* 358 */       throw new IllegalArgumentException("Negative 'factor' argument.");
/*     */     }
/* 360 */     return new Range(base.getLowerBound() * factor, base.getUpperBound() * factor);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp boolean equals(Object obj)
/*     */   {
/* 372 */     if (!(obj instanceof Range)) {
/* 373 */       return false;
/*     */     }
/* 375 */     Range range = (Range)obj;
/* 376 */     if (this.lower != range.lower) {
/* 377 */       return false;
/*     */     }
/* 379 */     if (this.upper != range.upper) {
/* 380 */       return false;
/*     */     }
/* 382 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp int hashCode()
/*     */   {
/* 393 */     long temp = Double.doubleToLongBits(this.lower);
/* 394 */     int result = (int)(temp ^ temp >>> 32);
/* 395 */     temp = Double.doubleToLongBits(this.upper);
/* 396 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 397 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public strictfp String toString()
/*     */   {
/* 407 */     return "Range[" + this.lower + "," + this.upper + "]";
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/data/Range.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */