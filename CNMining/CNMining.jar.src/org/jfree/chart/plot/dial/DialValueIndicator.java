/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ public class DialValueIndicator
/*     */   extends AbstractDialLayer
/*     */   implements DialLayer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 803094354130942585L;
/*     */   private int datasetIndex;
/*     */   private double angle;
/*     */   private double radius;
/*     */   private RectangleAnchor frameAnchor;
/*     */   private Number templateValue;
/*     */   private NumberFormat formatter;
/*     */   private Font font;
/*     */   private transient Paint paint;
/*     */   private transient Paint backgroundPaint;
/*     */   private transient Stroke outlineStroke;
/*     */   private transient Paint outlinePaint;
/*     */   private RectangleInsets insets;
/*     */   private RectangleAnchor valueAnchor;
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */   public DialValueIndicator()
/*     */   {
/* 129 */     this(0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialValueIndicator(int datasetIndex)
/*     */   {
/* 138 */     this.datasetIndex = datasetIndex;
/* 139 */     this.angle = -90.0D;
/* 140 */     this.radius = 0.3D;
/* 141 */     this.frameAnchor = RectangleAnchor.CENTER;
/* 142 */     this.templateValue = new Double(100.0D);
/* 143 */     this.formatter = new DecimalFormat("0.0");
/* 144 */     this.font = new Font("Dialog", 1, 14);
/* 145 */     this.paint = Color.black;
/* 146 */     this.backgroundPaint = Color.white;
/* 147 */     this.outlineStroke = new BasicStroke(1.0F);
/* 148 */     this.outlinePaint = Color.blue;
/* 149 */     this.insets = new RectangleInsets(4.0D, 4.0D, 4.0D, 4.0D);
/* 150 */     this.valueAnchor = RectangleAnchor.RIGHT;
/* 151 */     this.textAnchor = TextAnchor.CENTER_RIGHT;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getDatasetIndex()
/*     */   {
/* 163 */     return this.datasetIndex;
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
/* 175 */     this.datasetIndex = index;
/* 176 */     notifyListeners(new DialLayerChangeEvent(this));
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
/* 188 */     return this.angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAngle(double angle)
/*     */   {
/* 200 */     this.angle = angle;
/* 201 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/* 212 */     return this.radius;
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
/* 224 */     this.radius = radius;
/* 225 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getFrameAnchor()
/*     */   {
/* 236 */     return this.frameAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFrameAnchor(RectangleAnchor anchor)
/*     */   {
/* 248 */     if (anchor == null) {
/* 249 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 251 */     this.frameAnchor = anchor;
/* 252 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Number getTemplateValue()
/*     */   {
/* 263 */     return this.templateValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTemplateValue(Number value)
/*     */   {
/* 275 */     if (value == null) {
/* 276 */       throw new IllegalArgumentException("Null 'value' argument.");
/*     */     }
/* 278 */     this.templateValue = value;
/* 279 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberFormat getNumberFormat()
/*     */   {
/* 290 */     return this.formatter;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberFormat(NumberFormat formatter)
/*     */   {
/* 302 */     if (formatter == null) {
/* 303 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*     */     }
/* 305 */     this.formatter = formatter;
/* 306 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getFont()
/*     */   {
/* 317 */     return this.font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFont(Font font)
/*     */   {
/* 327 */     if (font == null) {
/* 328 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 330 */     this.font = font;
/* 331 */     notifyListeners(new DialLayerChangeEvent(this));
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
/* 342 */     return this.paint;
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
/* 354 */     if (paint == null) {
/* 355 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 357 */     this.paint = paint;
/* 358 */     notifyListeners(new DialLayerChangeEvent(this));
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
/* 369 */     return this.backgroundPaint;
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
/* 381 */     if (paint == null) {
/* 382 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 384 */     this.backgroundPaint = paint;
/* 385 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 396 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlineStroke(Stroke stroke)
/*     */   {
/* 408 */     if (stroke == null) {
/* 409 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 411 */     this.outlineStroke = stroke;
/* 412 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 423 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlinePaint(Paint paint)
/*     */   {
/* 435 */     if (paint == null) {
/* 436 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 438 */     this.outlinePaint = paint;
/* 439 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleInsets getInsets()
/*     */   {
/* 450 */     return this.insets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setInsets(RectangleInsets insets)
/*     */   {
/* 462 */     if (insets == null) {
/* 463 */       throw new IllegalArgumentException("Null 'insets' argument.");
/*     */     }
/* 465 */     this.insets = insets;
/* 466 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getValueAnchor()
/*     */   {
/* 477 */     return this.valueAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setValueAnchor(RectangleAnchor anchor)
/*     */   {
/* 489 */     if (anchor == null) {
/* 490 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 492 */     this.valueAnchor = anchor;
/* 493 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getTextAnchor()
/*     */   {
/* 504 */     return this.textAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setTextAnchor(TextAnchor anchor)
/*     */   {
/* 516 */     if (anchor == null) {
/* 517 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 519 */     this.textAnchor = anchor;
/* 520 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 530 */     return true;
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
/*     */   public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */   {
/* 547 */     Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, this.radius);
/*     */     
/* 549 */     Arc2D arc = new Arc2D.Double(f, this.angle, 0.0D, 0);
/* 550 */     Point2D pt = arc.getStartPoint();
/*     */     
/*     */ 
/* 553 */     FontMetrics fm = g2.getFontMetrics(this.font);
/* 554 */     String s = this.formatter.format(this.templateValue);
/* 555 */     Rectangle2D tb = TextUtilities.getTextBounds(s, g2, fm);
/*     */     
/*     */ 
/* 558 */     Rectangle2D bounds = RectangleAnchor.createRectangle(new Size2D(tb.getWidth(), tb.getHeight()), pt.getX(), pt.getY(), this.frameAnchor);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 563 */     Rectangle2D fb = this.insets.createOutsetRectangle(bounds);
/*     */     
/*     */ 
/* 566 */     g2.setPaint(this.backgroundPaint);
/* 567 */     g2.fill(fb);
/*     */     
/*     */ 
/* 570 */     g2.setStroke(this.outlineStroke);
/* 571 */     g2.setPaint(this.outlinePaint);
/* 572 */     g2.draw(fb);
/*     */     
/*     */ 
/*     */ 
/* 576 */     double value = plot.getValue(this.datasetIndex);
/* 577 */     String valueStr = this.formatter.format(value);
/* 578 */     Point2D pt2 = RectangleAnchor.coordinates(bounds, this.valueAnchor);
/* 579 */     g2.setPaint(this.paint);
/* 580 */     g2.setFont(this.font);
/* 581 */     TextUtilities.drawAlignedString(valueStr, g2, (float)pt2.getX(), (float)pt2.getY(), this.textAnchor);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 594 */     if (obj == this) {
/* 595 */       return true;
/*     */     }
/* 597 */     if (!(obj instanceof DialValueIndicator)) {
/* 598 */       return false;
/*     */     }
/* 600 */     DialValueIndicator that = (DialValueIndicator)obj;
/* 601 */     if (this.datasetIndex != that.datasetIndex) {
/* 602 */       return false;
/*     */     }
/* 604 */     if (this.angle != that.angle) {
/* 605 */       return false;
/*     */     }
/* 607 */     if (this.radius != that.radius) {
/* 608 */       return false;
/*     */     }
/* 610 */     if (!this.frameAnchor.equals(that.frameAnchor)) {
/* 611 */       return false;
/*     */     }
/* 613 */     if (!this.templateValue.equals(that.templateValue)) {
/* 614 */       return false;
/*     */     }
/* 616 */     if (!this.font.equals(that.font)) {
/* 617 */       return false;
/*     */     }
/* 619 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 620 */       return false;
/*     */     }
/* 622 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 623 */       return false;
/*     */     }
/* 625 */     if (!this.outlineStroke.equals(that.outlineStroke)) {
/* 626 */       return false;
/*     */     }
/* 628 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 629 */       return false;
/*     */     }
/* 631 */     if (!this.insets.equals(that.insets)) {
/* 632 */       return false;
/*     */     }
/* 634 */     if (!this.valueAnchor.equals(that.valueAnchor)) {
/* 635 */       return false;
/*     */     }
/* 637 */     if (!this.textAnchor.equals(that.textAnchor)) {
/* 638 */       return false;
/*     */     }
/*     */     
/* 641 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 650 */     int result = 193;
/* 651 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 652 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.backgroundPaint);
/*     */     
/* 654 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.outlinePaint);
/*     */     
/* 656 */     result = 37 * result + this.outlineStroke.hashCode();
/* 657 */     return result;
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
/* 669 */     return super.clone();
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
/* 680 */     stream.defaultWriteObject();
/* 681 */     SerialUtilities.writePaint(this.paint, stream);
/* 682 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 683 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 684 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
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
/* 697 */     stream.defaultReadObject();
/* 698 */     this.paint = SerialUtilities.readPaint(stream);
/* 699 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 700 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 701 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialValueIndicator.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */