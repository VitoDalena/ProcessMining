/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ArcDialFrame
/*     */   extends AbstractDialLayer
/*     */   implements DialFrame, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -4089176959553523499L;
/*     */   private transient Paint backgroundPaint;
/*     */   private transient Paint foregroundPaint;
/*     */   private transient Stroke stroke;
/*     */   private double startAngle;
/*     */   private double extent;
/*     */   private double innerRadius;
/*     */   private double outerRadius;
/*     */   
/*     */   public ArcDialFrame()
/*     */   {
/* 118 */     this(0.0D, 180.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ArcDialFrame(double startAngle, double extent)
/*     */   {
/* 129 */     this.backgroundPaint = Color.gray;
/* 130 */     this.foregroundPaint = new Color(100, 100, 150);
/* 131 */     this.stroke = new BasicStroke(2.0F);
/* 132 */     this.innerRadius = 0.25D;
/* 133 */     this.outerRadius = 0.75D;
/* 134 */     this.startAngle = startAngle;
/* 135 */     this.extent = extent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 146 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 158 */     if (paint == null) {
/* 159 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 161 */     this.backgroundPaint = paint;
/* 162 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getForegroundPaint()
/*     */   {
/* 173 */     return this.foregroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setForegroundPaint(Paint paint)
/*     */   {
/* 185 */     if (paint == null) {
/* 186 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 188 */     this.foregroundPaint = paint;
/* 189 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 200 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStroke(Stroke stroke)
/*     */   {
/* 212 */     if (stroke == null) {
/* 213 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 215 */     this.stroke = stroke;
/* 216 */     notifyListeners(new DialLayerChangeEvent(this));
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
/* 227 */     return this.innerRadius;
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
/* 239 */     if (radius < 0.0D) {
/* 240 */       throw new IllegalArgumentException("Negative 'radius' argument.");
/*     */     }
/* 242 */     this.innerRadius = radius;
/* 243 */     notifyListeners(new DialLayerChangeEvent(this));
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
/* 254 */     return this.outerRadius;
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
/* 266 */     if (radius < 0.0D) {
/* 267 */       throw new IllegalArgumentException("Negative 'radius' argument.");
/*     */     }
/* 269 */     this.outerRadius = radius;
/* 270 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStartAngle()
/*     */   {
/* 281 */     return this.startAngle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStartAngle(double angle)
/*     */   {
/* 293 */     this.startAngle = angle;
/* 294 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getExtent()
/*     */   {
/* 305 */     return this.extent;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setExtent(double extent)
/*     */   {
/* 317 */     this.extent = extent;
/* 318 */     notifyListeners(new DialLayerChangeEvent(this));
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
/*     */   public Shape getWindow(Rectangle2D frame)
/*     */   {
/* 331 */     Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame, this.innerRadius, this.innerRadius);
/*     */     
/* 333 */     Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame, this.outerRadius, this.outerRadius);
/*     */     
/* 335 */     Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle, this.extent, 0);
/*     */     
/* 337 */     Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle + this.extent, -this.extent, 0);
/*     */     
/* 339 */     GeneralPath p = new GeneralPath();
/* 340 */     Point2D point1 = inner.getStartPoint();
/* 341 */     p.moveTo((float)point1.getX(), (float)point1.getY());
/* 342 */     p.append(inner, true);
/* 343 */     p.append(outer, true);
/* 344 */     p.closePath();
/* 345 */     return p;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Shape getOuterWindow(Rectangle2D frame)
/*     */   {
/* 357 */     double radiusMargin = 0.02D;
/* 358 */     double angleMargin = 1.5D;
/* 359 */     Rectangle2D innerFrame = DialPlot.rectangleByRadius(frame, this.innerRadius - radiusMargin, this.innerRadius - radiusMargin);
/*     */     
/*     */ 
/* 362 */     Rectangle2D outerFrame = DialPlot.rectangleByRadius(frame, this.outerRadius + radiusMargin, this.outerRadius + radiusMargin);
/*     */     
/*     */ 
/* 365 */     Arc2D inner = new Arc2D.Double(innerFrame, this.startAngle - angleMargin, this.extent + 2.0D * angleMargin, 0);
/*     */     
/* 367 */     Arc2D outer = new Arc2D.Double(outerFrame, this.startAngle + angleMargin + this.extent, -this.extent - 2.0D * angleMargin, 0);
/*     */     
/*     */ 
/* 370 */     GeneralPath p = new GeneralPath();
/* 371 */     Point2D point1 = inner.getStartPoint();
/* 372 */     p.moveTo((float)point1.getX(), (float)point1.getY());
/* 373 */     p.append(inner, true);
/* 374 */     p.append(outer, true);
/* 375 */     p.closePath();
/* 376 */     return p;
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
/* 390 */     Shape window = getWindow(frame);
/* 391 */     Shape outerWindow = getOuterWindow(frame);
/*     */     
/* 393 */     Area area1 = new Area(outerWindow);
/* 394 */     Area area2 = new Area(window);
/* 395 */     area1.subtract(area2);
/* 396 */     g2.setPaint(Color.lightGray);
/* 397 */     g2.fill(area1);
/*     */     
/* 399 */     g2.setStroke(this.stroke);
/* 400 */     g2.setPaint(this.foregroundPaint);
/* 401 */     g2.draw(window);
/* 402 */     g2.draw(outerWindow);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 413 */     return false;
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
/* 424 */     if (obj == this) {
/* 425 */       return true;
/*     */     }
/* 427 */     if (!(obj instanceof ArcDialFrame)) {
/* 428 */       return false;
/*     */     }
/* 430 */     ArcDialFrame that = (ArcDialFrame)obj;
/* 431 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 432 */       return false;
/*     */     }
/* 434 */     if (!PaintUtilities.equal(this.foregroundPaint, that.foregroundPaint)) {
/* 435 */       return false;
/*     */     }
/* 437 */     if (this.startAngle != that.startAngle) {
/* 438 */       return false;
/*     */     }
/* 440 */     if (this.extent != that.extent) {
/* 441 */       return false;
/*     */     }
/* 443 */     if (this.innerRadius != that.innerRadius) {
/* 444 */       return false;
/*     */     }
/* 446 */     if (this.outerRadius != that.outerRadius) {
/* 447 */       return false;
/*     */     }
/* 449 */     if (!this.stroke.equals(that.stroke)) {
/* 450 */       return false;
/*     */     }
/* 452 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 461 */     int result = 193;
/* 462 */     long temp = Double.doubleToLongBits(this.startAngle);
/* 463 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 464 */     temp = Double.doubleToLongBits(this.extent);
/* 465 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 466 */     temp = Double.doubleToLongBits(this.innerRadius);
/* 467 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 468 */     temp = Double.doubleToLongBits(this.outerRadius);
/* 469 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 470 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.backgroundPaint);
/*     */     
/* 472 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.foregroundPaint);
/*     */     
/* 474 */     result = 37 * result + this.stroke.hashCode();
/* 475 */     return result;
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
/* 487 */     return super.clone();
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
/* 498 */     stream.defaultWriteObject();
/* 499 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 500 */     SerialUtilities.writePaint(this.foregroundPaint, stream);
/* 501 */     SerialUtilities.writeStroke(this.stroke, stream);
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
/* 514 */     stream.defaultReadObject();
/* 515 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 516 */     this.foregroundPaint = SerialUtilities.readPaint(stream);
/* 517 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/ArcDialFrame.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */