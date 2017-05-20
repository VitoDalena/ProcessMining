/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ModuloAxis
/*     */   extends NumberAxis
/*     */ {
/*     */   private Range fixedRange;
/*     */   private double displayStart;
/*     */   private double displayEnd;
/*     */   
/*     */   public ModuloAxis(String label, Range fixedRange)
/*     */   {
/*  80 */     super(label);
/*  81 */     this.fixedRange = fixedRange;
/*  82 */     this.displayStart = 270.0D;
/*  83 */     this.displayEnd = 90.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDisplayStart()
/*     */   {
/*  92 */     return this.displayStart;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getDisplayEnd()
/*     */   {
/* 101 */     return this.displayEnd;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDisplayRange(double start, double end)
/*     */   {
/* 112 */     this.displayStart = mapValueToFixedRange(start);
/* 113 */     this.displayEnd = mapValueToFixedRange(end);
/* 114 */     if (this.displayStart < this.displayEnd) {
/* 115 */       setRange(this.displayStart, this.displayEnd);
/*     */     }
/*     */     else {
/* 118 */       setRange(this.displayStart, this.fixedRange.getUpperBound() + (this.displayEnd - this.fixedRange.getLowerBound()));
/*     */     }
/*     */     
/* 121 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void autoAdjustRange()
/*     */   {
/* 129 */     setRange(this.fixedRange, false, false);
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
/*     */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 143 */     double result = 0.0D;
/* 144 */     double v = mapValueToFixedRange(value);
/* 145 */     if (this.displayStart < this.displayEnd) {
/* 146 */       result = trans(v, area, edge);
/*     */     }
/*     */     else {
/* 149 */       double cutoff = (this.displayStart + this.displayEnd) / 2.0D;
/* 150 */       double length1 = this.fixedRange.getUpperBound() - this.displayStart;
/*     */       
/* 152 */       double length2 = this.displayEnd - this.fixedRange.getLowerBound();
/* 153 */       if (v > cutoff) {
/* 154 */         result = transStart(v, area, edge, length1, length2);
/*     */       }
/*     */       else {
/* 157 */         result = transEnd(v, area, edge, length1, length2);
/*     */       }
/*     */     }
/* 160 */     return result;
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
/*     */   private double trans(double value, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 173 */     double min = 0.0D;
/* 174 */     double max = 0.0D;
/* 175 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 176 */       min = area.getX();
/* 177 */       max = area.getX() + area.getWidth();
/*     */     }
/* 179 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 180 */       min = area.getMaxY();
/* 181 */       max = area.getMaxY() - area.getHeight();
/*     */     }
/* 183 */     if (isInverted()) {
/* 184 */       return max - (value - this.displayStart) / (this.displayEnd - this.displayStart) * (max - min);
/*     */     }
/*     */     
/*     */ 
/* 188 */     return min + (value - this.displayStart) / (this.displayEnd - this.displayStart) * (max - min);
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
/*     */   private double transStart(double value, Rectangle2D area, RectangleEdge edge, double length1, double length2)
/*     */   {
/* 209 */     double min = 0.0D;
/* 210 */     double max = 0.0D;
/* 211 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 212 */       min = area.getX();
/* 213 */       max = area.getX() + area.getWidth() * length1 / (length1 + length2);
/*     */     }
/* 215 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 216 */       min = area.getMaxY();
/* 217 */       max = area.getMaxY() - area.getHeight() * length1 / (length1 + length2);
/*     */     }
/*     */     
/* 220 */     if (isInverted()) {
/* 221 */       return max - (value - this.displayStart) / (this.fixedRange.getUpperBound() - this.displayStart) * (max - min);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 226 */     return min + (value - this.displayStart) / (this.fixedRange.getUpperBound() - this.displayStart) * (max - min);
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
/*     */   private double transEnd(double value, Rectangle2D area, RectangleEdge edge, double length1, double length2)
/*     */   {
/* 247 */     double min = 0.0D;
/* 248 */     double max = 0.0D;
/* 249 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 250 */       max = area.getMaxX();
/* 251 */       min = area.getMaxX() - area.getWidth() * length2 / (length1 + length2);
/*     */ 
/*     */     }
/* 254 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 255 */       max = area.getMinY();
/* 256 */       min = area.getMinY() + area.getHeight() * length2 / (length1 + length2);
/*     */     }
/*     */     
/* 259 */     if (isInverted()) {
/* 260 */       return max - (value - this.fixedRange.getLowerBound()) / (this.displayEnd - this.fixedRange.getLowerBound()) * (max - min);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 265 */     return min + (value - this.fixedRange.getLowerBound()) / (this.displayEnd - this.fixedRange.getLowerBound()) * (max - min);
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
/*     */   private double mapValueToFixedRange(double value)
/*     */   {
/* 280 */     double lower = this.fixedRange.getLowerBound();
/* 281 */     double length = this.fixedRange.getLength();
/* 282 */     if (value < lower) {
/* 283 */       return lower + length + (value - lower) % length;
/*     */     }
/*     */     
/* 286 */     return lower + (value - lower) % length;
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
/*     */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 301 */     double result = 0.0D;
/* 302 */     if (this.displayStart < this.displayEnd) {
/* 303 */       result = super.java2DToValue(java2DValue, area, edge);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 308 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getDisplayLength()
/*     */   {
/* 317 */     if (this.displayStart < this.displayEnd) {
/* 318 */       return this.displayEnd - this.displayStart;
/*     */     }
/*     */     
/* 321 */     return this.fixedRange.getUpperBound() - this.displayStart + (this.displayEnd - this.fixedRange.getLowerBound());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double getDisplayCentralValue()
/*     */   {
/* 332 */     return mapValueToFixedRange(this.displayStart + getDisplayLength() / 2.0D);
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
/*     */   public void resizeRange(double percent)
/*     */   {
/* 348 */     resizeRange(percent, getDisplayCentralValue());
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
/*     */   public void resizeRange(double percent, double anchorValue)
/*     */   {
/* 364 */     if (percent > 0.0D) {
/* 365 */       double halfLength = getDisplayLength() * percent / 2.0D;
/* 366 */       setDisplayRange(anchorValue - halfLength, anchorValue + halfLength);
/*     */     }
/*     */     else {
/* 369 */       setAutoRange(true);
/*     */     }
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
/*     */   public double lengthToJava2D(double length, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 386 */     double axisLength = 0.0D;
/* 387 */     if (this.displayEnd > this.displayStart) {
/* 388 */       axisLength = this.displayEnd - this.displayStart;
/*     */     }
/*     */     else {
/* 391 */       axisLength = this.fixedRange.getUpperBound() - this.displayStart + (this.displayEnd - this.fixedRange.getLowerBound());
/*     */     }
/*     */     
/* 394 */     double areaLength = 0.0D;
/* 395 */     if (RectangleEdge.isLeftOrRight(edge)) {
/* 396 */       areaLength = area.getHeight();
/*     */     }
/*     */     else {
/* 399 */       areaLength = area.getWidth();
/*     */     }
/* 401 */     return length / axisLength * areaLength;
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
/* 412 */     if (obj == this) {
/* 413 */       return true;
/*     */     }
/* 415 */     if (!(obj instanceof ModuloAxis)) {
/* 416 */       return false;
/*     */     }
/* 418 */     ModuloAxis that = (ModuloAxis)obj;
/* 419 */     if (this.displayStart != that.displayStart) {
/* 420 */       return false;
/*     */     }
/* 422 */     if (this.displayEnd != that.displayEnd) {
/* 423 */       return false;
/*     */     }
/* 425 */     if (!this.fixedRange.equals(that.fixedRange)) {
/* 426 */       return false;
/*     */     }
/* 428 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/ModuloAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */