/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CategoryPointerAnnotation
/*     */   extends CategoryTextAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4031161445009858551L;
/*     */   public static final double DEFAULT_TIP_RADIUS = 10.0D;
/*     */   public static final double DEFAULT_BASE_RADIUS = 30.0D;
/*     */   public static final double DEFAULT_LABEL_OFFSET = 3.0D;
/*     */   public static final double DEFAULT_ARROW_LENGTH = 5.0D;
/*     */   public static final double DEFAULT_ARROW_WIDTH = 3.0D;
/*     */   private double angle;
/*     */   private double tipRadius;
/*     */   private double baseRadius;
/*     */   private double arrowLength;
/*     */   private double arrowWidth;
/*     */   private transient Stroke arrowStroke;
/*     */   private transient Paint arrowPaint;
/*     */   private double labelOffset;
/*     */   
/*     */   public CategoryPointerAnnotation(String label, Comparable key, double value, double angle)
/*     */   {
/* 148 */     super(label, key, value);
/* 149 */     this.angle = angle;
/* 150 */     this.tipRadius = 10.0D;
/* 151 */     this.baseRadius = 30.0D;
/* 152 */     this.arrowLength = 5.0D;
/* 153 */     this.arrowWidth = 3.0D;
/* 154 */     this.labelOffset = 3.0D;
/* 155 */     this.arrowStroke = new BasicStroke(1.0F);
/* 156 */     this.arrowPaint = Color.black;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 168 */     return this.angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAngle(double angle)
/*     */   {
/* 179 */     this.angle = angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getTipRadius()
/*     */   {
/* 190 */     return this.tipRadius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTipRadius(double radius)
/*     */   {
/* 201 */     this.tipRadius = radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBaseRadius()
/*     */   {
/* 212 */     return this.baseRadius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBaseRadius(double radius)
/*     */   {
/* 223 */     this.baseRadius = radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getLabelOffset()
/*     */   {
/* 234 */     return this.labelOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelOffset(double offset)
/*     */   {
/* 246 */     this.labelOffset = offset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getArrowLength()
/*     */   {
/* 257 */     return this.arrowLength;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArrowLength(double length)
/*     */   {
/* 268 */     this.arrowLength = length;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getArrowWidth()
/*     */   {
/* 279 */     return this.arrowWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArrowWidth(double width)
/*     */   {
/* 290 */     this.arrowWidth = width;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getArrowStroke()
/*     */   {
/* 301 */     return this.arrowStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArrowStroke(Stroke stroke)
/*     */   {
/* 312 */     if (stroke == null) {
/* 313 */       throw new IllegalArgumentException("Null 'stroke' not permitted.");
/*     */     }
/* 315 */     this.arrowStroke = stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getArrowPaint()
/*     */   {
/* 326 */     return this.arrowPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArrowPaint(Paint paint)
/*     */   {
/* 337 */     if (paint == null) {
/* 338 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 340 */     this.arrowPaint = paint;
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
/*     */   public void draw(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, CategoryAxis domainAxis, ValueAxis rangeAxis)
/*     */   {
/* 355 */     PlotOrientation orientation = plot.getOrientation();
/* 356 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 358 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/* 360 */     CategoryDataset dataset = plot.getDataset();
/* 361 */     int catIndex = dataset.getColumnIndex(getCategory());
/* 362 */     int catCount = dataset.getColumnCount();
/* 363 */     double j2DX = domainAxis.getCategoryMiddle(catIndex, catCount, dataArea, domainEdge);
/*     */     
/* 365 */     double j2DY = rangeAxis.valueToJava2D(getValue(), dataArea, rangeEdge);
/* 366 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 367 */       double temp = j2DX;
/* 368 */       j2DX = j2DY;
/* 369 */       j2DY = temp;
/*     */     }
/* 371 */     double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
/* 372 */     double startY = j2DY + Math.sin(this.angle) * this.baseRadius;
/*     */     
/* 374 */     double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
/* 375 */     double endY = j2DY + Math.sin(this.angle) * this.tipRadius;
/*     */     
/* 377 */     double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
/* 378 */     double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;
/*     */     
/* 380 */     double arrowLeftX = arrowBaseX + Math.cos(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/* 382 */     double arrowLeftY = arrowBaseY + Math.sin(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/*     */ 
/* 385 */     double arrowRightX = arrowBaseX - Math.cos(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/* 387 */     double arrowRightY = arrowBaseY - Math.sin(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/*     */ 
/* 390 */     GeneralPath arrow = new GeneralPath();
/* 391 */     arrow.moveTo((float)endX, (float)endY);
/* 392 */     arrow.lineTo((float)arrowLeftX, (float)arrowLeftY);
/* 393 */     arrow.lineTo((float)arrowRightX, (float)arrowRightY);
/* 394 */     arrow.closePath();
/*     */     
/* 396 */     g2.setStroke(this.arrowStroke);
/* 397 */     g2.setPaint(this.arrowPaint);
/* 398 */     Line2D line = new Line2D.Double(startX, startY, endX, endY);
/* 399 */     g2.draw(line);
/* 400 */     g2.fill(arrow);
/*     */     
/*     */ 
/* 403 */     g2.setFont(getFont());
/* 404 */     g2.setPaint(getPaint());
/* 405 */     double labelX = j2DX + Math.cos(this.angle) * (this.baseRadius + this.labelOffset);
/*     */     
/* 407 */     double labelY = j2DY + Math.sin(this.angle) * (this.baseRadius + this.labelOffset);
/*     */     
/* 409 */     TextUtilities.drawAlignedString(getText(), g2, (float)labelX, (float)labelY, getTextAnchor());
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 424 */     if (obj == this) {
/* 425 */       return true;
/*     */     }
/* 427 */     if (!(obj instanceof CategoryPointerAnnotation)) {
/* 428 */       return false;
/*     */     }
/* 430 */     if (!super.equals(obj)) {
/* 431 */       return false;
/*     */     }
/* 433 */     CategoryPointerAnnotation that = (CategoryPointerAnnotation)obj;
/* 434 */     if (this.angle != that.angle) {
/* 435 */       return false;
/*     */     }
/* 437 */     if (this.tipRadius != that.tipRadius) {
/* 438 */       return false;
/*     */     }
/* 440 */     if (this.baseRadius != that.baseRadius) {
/* 441 */       return false;
/*     */     }
/* 443 */     if (this.arrowLength != that.arrowLength) {
/* 444 */       return false;
/*     */     }
/* 446 */     if (this.arrowWidth != that.arrowWidth) {
/* 447 */       return false;
/*     */     }
/* 449 */     if (!this.arrowPaint.equals(that.arrowPaint)) {
/* 450 */       return false;
/*     */     }
/* 452 */     if (!ObjectUtilities.equal(this.arrowStroke, that.arrowStroke)) {
/* 453 */       return false;
/*     */     }
/* 455 */     if (this.labelOffset != that.labelOffset) {
/* 456 */       return false;
/*     */     }
/* 458 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 467 */     int result = 193;
/* 468 */     long temp = Double.doubleToLongBits(this.angle);
/* 469 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 470 */     temp = Double.doubleToLongBits(this.tipRadius);
/* 471 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 472 */     temp = Double.doubleToLongBits(this.baseRadius);
/* 473 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 474 */     temp = Double.doubleToLongBits(this.arrowLength);
/* 475 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 476 */     temp = Double.doubleToLongBits(this.arrowWidth);
/* 477 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 478 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.arrowPaint);
/* 479 */     result = 37 * result + this.arrowStroke.hashCode();
/* 480 */     temp = Double.doubleToLongBits(this.labelOffset);
/* 481 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 482 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 493 */     return super.clone();
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
/* 504 */     stream.defaultWriteObject();
/* 505 */     SerialUtilities.writePaint(this.arrowPaint, stream);
/* 506 */     SerialUtilities.writeStroke(this.arrowStroke, stream);
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
/* 519 */     stream.defaultReadObject();
/* 520 */     this.arrowPaint = SerialUtilities.readPaint(stream);
/* 521 */     this.arrowStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/CategoryPointerAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */