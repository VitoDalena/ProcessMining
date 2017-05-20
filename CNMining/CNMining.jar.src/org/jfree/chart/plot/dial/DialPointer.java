/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
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
/*     */ public abstract class DialPointer
/*     */   extends AbstractDialLayer
/*     */   implements DialLayer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   double radius;
/*     */   int datasetIndex;
/*     */   
/*     */   protected DialPointer()
/*     */   {
/*  88 */     this(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected DialPointer(int datasetIndex)
/*     */   {
/*  97 */     this.radius = 0.9D;
/*  98 */     this.datasetIndex = datasetIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDatasetIndex()
/*     */   {
/* 109 */     return this.datasetIndex;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDatasetIndex(int index)
/*     */   {
/* 121 */     this.datasetIndex = index;
/* 122 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/* 134 */     return this.radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRadius(double radius)
/*     */   {
/* 146 */     this.radius = radius;
/* 147 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 157 */     return true;
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
/* 168 */     if (obj == this) {
/* 169 */       return true;
/*     */     }
/* 171 */     if (!(obj instanceof DialPointer)) {
/* 172 */       return false;
/*     */     }
/* 174 */     DialPointer that = (DialPointer)obj;
/* 175 */     if (this.datasetIndex != that.datasetIndex) {
/* 176 */       return false;
/*     */     }
/* 178 */     if (this.radius != that.radius) {
/* 179 */       return false;
/*     */     }
/* 181 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 190 */     int result = 23;
/* 191 */     result = HashUtilities.hashCode(result, this.radius);
/* 192 */     return result;
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
/* 204 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Pin
/*     */     extends DialPointer
/*     */   {
/*     */     static final long serialVersionUID = -8445860485367689750L;
/*     */     
/*     */ 
/*     */     private transient Paint paint;
/*     */     
/*     */ 
/*     */     private transient Stroke stroke;
/*     */     
/*     */ 
/*     */ 
/*     */     public Pin()
/*     */     {
/* 225 */       this(0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Pin(int datasetIndex)
/*     */     {
/* 234 */       super();
/* 235 */       this.paint = Color.red;
/* 236 */       this.stroke = new BasicStroke(3.0F, 1, 2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Paint getPaint()
/*     */     {
/* 248 */       return this.paint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setPaint(Paint paint)
/*     */     {
/* 260 */       if (paint == null) {
/* 261 */         throw new IllegalArgumentException("Null 'paint' argument.");
/*     */       }
/* 263 */       this.paint = paint;
/* 264 */       notifyListeners(new DialLayerChangeEvent(this));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Stroke getStroke()
/*     */     {
/* 275 */       return this.stroke;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setStroke(Stroke stroke)
/*     */     {
/* 287 */       if (stroke == null) {
/* 288 */         throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */       }
/* 290 */       this.stroke = stroke;
/* 291 */       notifyListeners(new DialLayerChangeEvent(this));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */     {
/* 305 */       g2.setPaint(this.paint);
/* 306 */       g2.setStroke(this.stroke);
/* 307 */       Rectangle2D arcRect = DialPlot.rectangleByRadius(frame, this.radius, this.radius);
/*     */       
/*     */ 
/* 310 */       double value = plot.getValue(this.datasetIndex);
/* 311 */       DialScale scale = plot.getScaleForDataset(this.datasetIndex);
/* 312 */       double angle = scale.valueToAngle(value);
/*     */       
/* 314 */       Arc2D arc = new Arc2D.Double(arcRect, angle, 0.0D, 0);
/* 315 */       Point2D pt = arc.getEndPoint();
/*     */       
/* 317 */       Line2D line = new Line2D.Double(frame.getCenterX(), frame.getCenterY(), pt.getX(), pt.getY());
/*     */       
/* 319 */       g2.draw(line);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 330 */       if (obj == this) {
/* 331 */         return true;
/*     */       }
/* 333 */       if (!(obj instanceof Pin)) {
/* 334 */         return false;
/*     */       }
/* 336 */       Pin that = (Pin)obj;
/* 337 */       if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 338 */         return false;
/*     */       }
/* 340 */       if (!this.stroke.equals(that.stroke)) {
/* 341 */         return false;
/*     */       }
/* 343 */       return super.equals(obj);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 352 */       int result = super.hashCode();
/* 353 */       result = HashUtilities.hashCode(result, this.paint);
/* 354 */       result = HashUtilities.hashCode(result, this.stroke);
/* 355 */       return result;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void writeObject(ObjectOutputStream stream)
/*     */       throws IOException
/*     */     {
/* 366 */       stream.defaultWriteObject();
/* 367 */       SerialUtilities.writePaint(this.paint, stream);
/* 368 */       SerialUtilities.writeStroke(this.stroke, stream);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void readObject(ObjectInputStream stream)
/*     */       throws IOException, ClassNotFoundException
/*     */     {
/* 381 */       stream.defaultReadObject();
/* 382 */       this.paint = SerialUtilities.readPaint(stream);
/* 383 */       this.stroke = SerialUtilities.readStroke(stream);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class Pointer
/*     */     extends DialPointer
/*     */   {
/*     */     static final long serialVersionUID = -4180500011963176960L;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private double widthRadius;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private transient Paint fillPaint;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */     private transient Paint outlinePaint;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Pointer()
/*     */     {
/* 419 */       this(0);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Pointer(int datasetIndex)
/*     */     {
/* 428 */       super();
/* 429 */       this.widthRadius = 0.05D;
/* 430 */       this.fillPaint = Color.gray;
/* 431 */       this.outlinePaint = Color.black;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public double getWidthRadius()
/*     */     {
/* 442 */       return this.widthRadius;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setWidthRadius(double radius)
/*     */     {
/* 454 */       this.widthRadius = radius;
/* 455 */       notifyListeners(new DialLayerChangeEvent(this));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Paint getFillPaint()
/*     */     {
/* 468 */       return this.fillPaint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setFillPaint(Paint paint)
/*     */     {
/* 482 */       if (paint == null) {
/* 483 */         throw new IllegalArgumentException("Null 'paint' argument.");
/*     */       }
/* 485 */       this.fillPaint = paint;
/* 486 */       notifyListeners(new DialLayerChangeEvent(this));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Paint getOutlinePaint()
/*     */     {
/* 499 */       return this.outlinePaint;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setOutlinePaint(Paint paint)
/*     */     {
/* 513 */       if (paint == null) {
/* 514 */         throw new IllegalArgumentException("Null 'paint' argument.");
/*     */       }
/* 516 */       this.outlinePaint = paint;
/* 517 */       notifyListeners(new DialLayerChangeEvent(this));
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */     {
/* 531 */       g2.setPaint(Color.blue);
/* 532 */       g2.setStroke(new BasicStroke(1.0F));
/* 533 */       Rectangle2D lengthRect = DialPlot.rectangleByRadius(frame, this.radius, this.radius);
/*     */       
/* 535 */       Rectangle2D widthRect = DialPlot.rectangleByRadius(frame, this.widthRadius, this.widthRadius);
/*     */       
/* 537 */       double value = plot.getValue(this.datasetIndex);
/* 538 */       DialScale scale = plot.getScaleForDataset(this.datasetIndex);
/* 539 */       double angle = scale.valueToAngle(value);
/*     */       
/* 541 */       Arc2D arc1 = new Arc2D.Double(lengthRect, angle, 0.0D, 0);
/* 542 */       Point2D pt1 = arc1.getEndPoint();
/* 543 */       Arc2D arc2 = new Arc2D.Double(widthRect, angle - 90.0D, 180.0D, 0);
/*     */       
/* 545 */       Point2D pt2 = arc2.getStartPoint();
/* 546 */       Point2D pt3 = arc2.getEndPoint();
/* 547 */       Arc2D arc3 = new Arc2D.Double(widthRect, angle - 180.0D, 0.0D, 0);
/*     */       
/* 549 */       Point2D pt4 = arc3.getStartPoint();
/*     */       
/* 551 */       GeneralPath gp = new GeneralPath();
/* 552 */       gp.moveTo((float)pt1.getX(), (float)pt1.getY());
/* 553 */       gp.lineTo((float)pt2.getX(), (float)pt2.getY());
/* 554 */       gp.lineTo((float)pt4.getX(), (float)pt4.getY());
/* 555 */       gp.lineTo((float)pt3.getX(), (float)pt3.getY());
/* 556 */       gp.closePath();
/* 557 */       g2.setPaint(this.fillPaint);
/* 558 */       g2.fill(gp);
/*     */       
/* 560 */       g2.setPaint(this.outlinePaint);
/* 561 */       Line2D line = new Line2D.Double(frame.getCenterX(), frame.getCenterY(), pt1.getX(), pt1.getY());
/*     */       
/* 563 */       g2.draw(line);
/*     */       
/* 565 */       line.setLine(pt2, pt3);
/* 566 */       g2.draw(line);
/*     */       
/* 568 */       line.setLine(pt3, pt1);
/* 569 */       g2.draw(line);
/*     */       
/* 571 */       line.setLine(pt2, pt1);
/* 572 */       g2.draw(line);
/*     */       
/* 574 */       line.setLine(pt2, pt4);
/* 575 */       g2.draw(line);
/*     */       
/* 577 */       line.setLine(pt3, pt4);
/* 578 */       g2.draw(line);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public boolean equals(Object obj)
/*     */     {
/* 589 */       if (obj == this) {
/* 590 */         return true;
/*     */       }
/* 592 */       if (!(obj instanceof Pointer)) {
/* 593 */         return false;
/*     */       }
/* 595 */       Pointer that = (Pointer)obj;
/*     */       
/* 597 */       if (this.widthRadius != that.widthRadius) {
/* 598 */         return false;
/*     */       }
/* 600 */       if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 601 */         return false;
/*     */       }
/* 603 */       if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 604 */         return false;
/*     */       }
/* 606 */       return super.equals(obj);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public int hashCode()
/*     */     {
/* 615 */       int result = super.hashCode();
/* 616 */       result = HashUtilities.hashCode(result, this.widthRadius);
/* 617 */       result = HashUtilities.hashCode(result, this.fillPaint);
/* 618 */       result = HashUtilities.hashCode(result, this.outlinePaint);
/* 619 */       return result;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void writeObject(ObjectOutputStream stream)
/*     */       throws IOException
/*     */     {
/* 630 */       stream.defaultWriteObject();
/* 631 */       SerialUtilities.writePaint(this.fillPaint, stream);
/* 632 */       SerialUtilities.writePaint(this.outlinePaint, stream);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     private void readObject(ObjectInputStream stream)
/*     */       throws IOException, ClassNotFoundException
/*     */     {
/* 645 */       stream.defaultReadObject();
/* 646 */       this.fillPaint = SerialUtilities.readPaint(stream);
/* 647 */       this.outlinePaint = SerialUtilities.readPaint(stream);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialPointer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */