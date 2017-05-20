/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardDialRange
/*     */   extends AbstractDialLayer
/*     */   implements DialLayer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 345515648249364904L;
/*     */   private int scaleIndex;
/*     */   private double lowerBound;
/*     */   private double upperBound;
/*     */   private transient Paint paint;
/*     */   private double innerRadius;
/*     */   private double outerRadius;
/*     */   
/*     */   public StandardDialRange()
/*     */   {
/* 104 */     this(0.0D, 100.0D, Color.white);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StandardDialRange(double lower, double upper, Paint paint)
/*     */   {
/* 115 */     if (paint == null) {
/* 116 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 118 */     this.scaleIndex = 0;
/* 119 */     this.lowerBound = lower;
/* 120 */     this.upperBound = upper;
/* 121 */     this.innerRadius = 0.48D;
/* 122 */     this.outerRadius = 0.52D;
/* 123 */     this.paint = paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getScaleIndex()
/*     */   {
/* 134 */     return this.scaleIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setScaleIndex(int index)
/*     */   {
/* 146 */     this.scaleIndex = index;
/* 147 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLowerBound()
/*     */   {
/* 158 */     return this.lowerBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLowerBound(double bound)
/*     */   {
/* 170 */     if (bound >= this.upperBound) {
/* 171 */       throw new IllegalArgumentException("Lower bound must be less than upper bound.");
/*     */     }
/*     */     
/* 174 */     this.lowerBound = bound;
/* 175 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getUpperBound()
/*     */   {
/* 186 */     return this.upperBound;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUpperBound(double bound)
/*     */   {
/* 198 */     if (bound <= this.lowerBound) {
/* 199 */       throw new IllegalArgumentException("Lower bound must be less than upper bound.");
/*     */     }
/*     */     
/* 202 */     this.upperBound = bound;
/* 203 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBounds(double lower, double upper)
/*     */   {
/* 214 */     if (lower >= upper) {
/* 215 */       throw new IllegalArgumentException("Lower must be less than upper.");
/*     */     }
/*     */     
/* 218 */     this.lowerBound = lower;
/* 219 */     this.upperBound = upper;
/* 220 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 231 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 243 */     if (paint == null) {
/* 244 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 246 */     this.paint = paint;
/* 247 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getInnerRadius()
/*     */   {
/* 258 */     return this.innerRadius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInnerRadius(double radius)
/*     */   {
/* 270 */     this.innerRadius = radius;
/* 271 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getOuterRadius()
/*     */   {
/* 282 */     return this.outerRadius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOuterRadius(double radius)
/*     */   {
/* 294 */     this.outerRadius = radius;
/* 295 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 305 */     return true;
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
/*     */   public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */   {
/* 319 */     Rectangle2D arcRectInner = DialPlot.rectangleByRadius(frame, this.innerRadius, this.innerRadius);
/*     */     
/* 321 */     Rectangle2D arcRectOuter = DialPlot.rectangleByRadius(frame, this.outerRadius, this.outerRadius);
/*     */     
/*     */ 
/* 324 */     DialScale scale = plot.getScale(this.scaleIndex);
/* 325 */     if (scale == null) {
/* 326 */       throw new RuntimeException("No scale for scaleIndex = " + this.scaleIndex);
/*     */     }
/*     */     
/* 329 */     double angleMin = scale.valueToAngle(this.lowerBound);
/* 330 */     double angleMax = scale.valueToAngle(this.upperBound);
/*     */     
/* 332 */     Arc2D arcInner = new Arc2D.Double(arcRectInner, angleMin, angleMax - angleMin, 0);
/*     */     
/* 334 */     Arc2D arcOuter = new Arc2D.Double(arcRectOuter, angleMax, angleMin - angleMax, 0);
/*     */     
/*     */ 
/* 337 */     g2.setPaint(this.paint);
/* 338 */     g2.setStroke(new BasicStroke(2.0F));
/* 339 */     g2.draw(arcInner);
/* 340 */     g2.draw(arcOuter);
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
/* 351 */     if (obj == this) {
/* 352 */       return true;
/*     */     }
/* 354 */     if (!(obj instanceof StandardDialRange)) {
/* 355 */       return false;
/*     */     }
/* 357 */     StandardDialRange that = (StandardDialRange)obj;
/* 358 */     if (this.scaleIndex != that.scaleIndex) {
/* 359 */       return false;
/*     */     }
/* 361 */     if (this.lowerBound != that.lowerBound) {
/* 362 */       return false;
/*     */     }
/* 364 */     if (this.upperBound != that.upperBound) {
/* 365 */       return false;
/*     */     }
/* 367 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 368 */       return false;
/*     */     }
/* 370 */     if (this.innerRadius != that.innerRadius) {
/* 371 */       return false;
/*     */     }
/* 373 */     if (this.outerRadius != that.outerRadius) {
/* 374 */       return false;
/*     */     }
/* 376 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 385 */     int result = 193;
/* 386 */     long temp = Double.doubleToLongBits(this.lowerBound);
/* 387 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 388 */     temp = Double.doubleToLongBits(this.upperBound);
/* 389 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 390 */     temp = Double.doubleToLongBits(this.innerRadius);
/* 391 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 392 */     temp = Double.doubleToLongBits(this.outerRadius);
/* 393 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 394 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 395 */     return result;
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
/* 407 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 418 */     stream.defaultWriteObject();
/* 419 */     SerialUtilities.writePaint(this.paint, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 432 */     stream.defaultReadObject();
/* 433 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/StandardDialRange.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */