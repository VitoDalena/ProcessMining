/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
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
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYPointerAnnotation
/*     */   extends XYTextAnnotation
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
/*     */   public XYPointerAnnotation(String label, double x, double y, double angle)
/*     */   {
/* 155 */     super(label, x, y);
/* 156 */     this.angle = angle;
/* 157 */     this.tipRadius = 10.0D;
/* 158 */     this.baseRadius = 30.0D;
/* 159 */     this.arrowLength = 5.0D;
/* 160 */     this.arrowWidth = 3.0D;
/* 161 */     this.labelOffset = 3.0D;
/* 162 */     this.arrowStroke = new BasicStroke(1.0F);
/* 163 */     this.arrowPaint = Color.black;
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
/* 175 */     return this.angle;
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
/* 186 */     this.angle = angle;
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
/* 197 */     return this.tipRadius;
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
/* 208 */     this.tipRadius = radius;
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
/* 219 */     return this.baseRadius;
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
/* 230 */     this.baseRadius = radius;
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
/* 241 */     return this.labelOffset;
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
/* 253 */     this.labelOffset = offset;
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
/* 264 */     return this.arrowLength;
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
/* 275 */     this.arrowLength = length;
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
/* 286 */     return this.arrowWidth;
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
/* 297 */     this.arrowWidth = width;
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
/* 308 */     return this.arrowStroke;
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
/* 319 */     if (stroke == null) {
/* 320 */       throw new IllegalArgumentException("Null 'stroke' not permitted.");
/*     */     }
/* 322 */     this.arrowStroke = stroke;
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
/* 333 */     return this.arrowPaint;
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
/* 344 */     if (paint == null) {
/* 345 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 347 */     this.arrowPaint = paint;
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
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 366 */     PlotOrientation orientation = plot.getOrientation();
/* 367 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 369 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/* 371 */     double j2DX = domainAxis.valueToJava2D(getX(), dataArea, domainEdge);
/* 372 */     double j2DY = rangeAxis.valueToJava2D(getY(), dataArea, rangeEdge);
/* 373 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 374 */       double temp = j2DX;
/* 375 */       j2DX = j2DY;
/* 376 */       j2DY = temp;
/*     */     }
/* 378 */     double startX = j2DX + Math.cos(this.angle) * this.baseRadius;
/* 379 */     double startY = j2DY + Math.sin(this.angle) * this.baseRadius;
/*     */     
/* 381 */     double endX = j2DX + Math.cos(this.angle) * this.tipRadius;
/* 382 */     double endY = j2DY + Math.sin(this.angle) * this.tipRadius;
/*     */     
/* 384 */     double arrowBaseX = endX + Math.cos(this.angle) * this.arrowLength;
/* 385 */     double arrowBaseY = endY + Math.sin(this.angle) * this.arrowLength;
/*     */     
/* 387 */     double arrowLeftX = arrowBaseX + Math.cos(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/* 389 */     double arrowLeftY = arrowBaseY + Math.sin(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/*     */ 
/* 392 */     double arrowRightX = arrowBaseX - Math.cos(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/* 394 */     double arrowRightY = arrowBaseY - Math.sin(this.angle + 1.5707963267948966D) * this.arrowWidth;
/*     */     
/*     */ 
/* 397 */     GeneralPath arrow = new GeneralPath();
/* 398 */     arrow.moveTo((float)endX, (float)endY);
/* 399 */     arrow.lineTo((float)arrowLeftX, (float)arrowLeftY);
/* 400 */     arrow.lineTo((float)arrowRightX, (float)arrowRightY);
/* 401 */     arrow.closePath();
/*     */     
/* 403 */     g2.setStroke(this.arrowStroke);
/* 404 */     g2.setPaint(this.arrowPaint);
/* 405 */     Line2D line = new Line2D.Double(startX, startY, endX, endY);
/* 406 */     g2.draw(line);
/* 407 */     g2.fill(arrow);
/*     */     
/*     */ 
/* 410 */     double labelX = j2DX + Math.cos(this.angle) * (this.baseRadius + this.labelOffset);
/*     */     
/* 412 */     double labelY = j2DY + Math.sin(this.angle) * (this.baseRadius + this.labelOffset);
/*     */     
/* 414 */     g2.setFont(getFont());
/* 415 */     Shape hotspot = TextUtilities.calculateRotatedStringBounds(getText(), g2, (float)labelX, (float)labelY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/*     */     
/*     */ 
/* 418 */     if (getBackgroundPaint() != null) {
/* 419 */       g2.setPaint(getBackgroundPaint());
/* 420 */       g2.fill(hotspot);
/*     */     }
/* 422 */     g2.setPaint(getPaint());
/* 423 */     TextUtilities.drawRotatedString(getText(), g2, (float)labelX, (float)labelY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/*     */     
/*     */ 
/* 426 */     if (isOutlineVisible()) {
/* 427 */       g2.setStroke(getOutlineStroke());
/* 428 */       g2.setPaint(getOutlinePaint());
/* 429 */       g2.draw(hotspot);
/*     */     }
/*     */     
/* 432 */     String toolTip = getToolTipText();
/* 433 */     String url = getURL();
/* 434 */     if ((toolTip != null) || (url != null)) {
/* 435 */       addEntity(info, hotspot, rendererIndex, toolTip, url);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 448 */     if (obj == this) {
/* 449 */       return true;
/*     */     }
/* 451 */     if (!(obj instanceof XYPointerAnnotation)) {
/* 452 */       return false;
/*     */     }
/* 454 */     XYPointerAnnotation that = (XYPointerAnnotation)obj;
/* 455 */     if (this.angle != that.angle) {
/* 456 */       return false;
/*     */     }
/* 458 */     if (this.tipRadius != that.tipRadius) {
/* 459 */       return false;
/*     */     }
/* 461 */     if (this.baseRadius != that.baseRadius) {
/* 462 */       return false;
/*     */     }
/* 464 */     if (this.arrowLength != that.arrowLength) {
/* 465 */       return false;
/*     */     }
/* 467 */     if (this.arrowWidth != that.arrowWidth) {
/* 468 */       return false;
/*     */     }
/* 470 */     if (!this.arrowPaint.equals(that.arrowPaint)) {
/* 471 */       return false;
/*     */     }
/* 473 */     if (!ObjectUtilities.equal(this.arrowStroke, that.arrowStroke)) {
/* 474 */       return false;
/*     */     }
/* 476 */     if (this.labelOffset != that.labelOffset) {
/* 477 */       return false;
/*     */     }
/* 479 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 488 */     int result = super.hashCode();
/* 489 */     long temp = Double.doubleToLongBits(this.angle);
/* 490 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 491 */     temp = Double.doubleToLongBits(this.tipRadius);
/* 492 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 493 */     temp = Double.doubleToLongBits(this.baseRadius);
/* 494 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 495 */     temp = Double.doubleToLongBits(this.arrowLength);
/* 496 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 497 */     temp = Double.doubleToLongBits(this.arrowWidth);
/* 498 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 499 */     result = result * 37 + HashUtilities.hashCodeForPaint(this.arrowPaint);
/* 500 */     result = result * 37 + this.arrowStroke.hashCode();
/* 501 */     temp = Double.doubleToLongBits(this.labelOffset);
/* 502 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 503 */     return super.hashCode();
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
/* 514 */     return super.clone();
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
/* 525 */     stream.defaultWriteObject();
/* 526 */     SerialUtilities.writePaint(this.arrowPaint, stream);
/* 527 */     SerialUtilities.writeStroke(this.arrowStroke, stream);
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
/* 540 */     stream.defaultReadObject();
/* 541 */     this.arrowPaint = SerialUtilities.readPaint(stream);
/* 542 */     this.arrowStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYPointerAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */