/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
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
/*     */ public class XYTextAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2946063342782506328L;
/*  94 */   public static final Font DEFAULT_FONT = new Font("SansSerif", 0, 10);
/*     */   
/*     */ 
/*     */ 
/*  98 */   public static final Paint DEFAULT_PAINT = Color.black;
/*     */   
/*     */ 
/* 101 */   public static final TextAnchor DEFAULT_TEXT_ANCHOR = TextAnchor.CENTER;
/*     */   
/*     */ 
/* 104 */   public static final TextAnchor DEFAULT_ROTATION_ANCHOR = TextAnchor.CENTER;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public static final double DEFAULT_ROTATION_ANGLE = 0.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private String text;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private Font font;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint paint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double x;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double y;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private TextAnchor textAnchor;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private TextAnchor rotationAnchor;
/*     */   
/*     */ 
/*     */ 
/*     */   private double rotationAngle;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint backgroundPaint;
/*     */   
/*     */ 
/*     */ 
/*     */   private boolean outlineVisible;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Paint outlinePaint;
/*     */   
/*     */ 
/*     */ 
/*     */   private transient Stroke outlineStroke;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYTextAnnotation(String text, double x, double y)
/*     */   {
/* 171 */     if (text == null) {
/* 172 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 174 */     this.text = text;
/* 175 */     this.font = DEFAULT_FONT;
/* 176 */     this.paint = DEFAULT_PAINT;
/* 177 */     this.x = x;
/* 178 */     this.y = y;
/* 179 */     this.textAnchor = DEFAULT_TEXT_ANCHOR;
/* 180 */     this.rotationAnchor = DEFAULT_ROTATION_ANCHOR;
/* 181 */     this.rotationAngle = 0.0D;
/*     */     
/*     */ 
/* 184 */     this.backgroundPaint = null;
/* 185 */     this.outlineVisible = false;
/* 186 */     this.outlinePaint = Color.black;
/* 187 */     this.outlineStroke = new BasicStroke(0.5F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getText()
/*     */   {
/* 198 */     return this.text;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setText(String text)
/*     */   {
/* 209 */     if (text == null) {
/* 210 */       throw new IllegalArgumentException("Null 'text' argument.");
/*     */     }
/* 212 */     this.text = text;
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
/* 223 */     return this.font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFont(Font font)
/*     */   {
/* 234 */     if (font == null) {
/* 235 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 237 */     this.font = font;
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
/* 248 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 259 */     if (paint == null) {
/* 260 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 262 */     this.paint = paint;
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
/* 273 */     return this.textAnchor;
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
/* 285 */     if (anchor == null) {
/* 286 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 288 */     this.textAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getRotationAnchor()
/*     */   {
/* 299 */     return this.rotationAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAnchor(TextAnchor anchor)
/*     */   {
/* 310 */     if (anchor == null) {
/* 311 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 313 */     this.rotationAnchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRotationAngle()
/*     */   {
/* 324 */     return this.rotationAngle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRotationAngle(double angle)
/*     */   {
/* 335 */     this.rotationAngle = angle;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/* 347 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setX(double x)
/*     */   {
/* 359 */     this.x = x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 371 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setY(double y)
/*     */   {
/* 383 */     this.y = y;
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
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 396 */     return this.backgroundPaint;
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
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 409 */     this.backgroundPaint = paint;
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
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 422 */     return this.outlinePaint;
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
/*     */   public void setOutlinePaint(Paint paint)
/*     */   {
/* 435 */     if (paint == null) {
/* 436 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 438 */     this.outlinePaint = paint;
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
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 451 */     return this.outlineStroke;
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
/*     */   public void setOutlineStroke(Stroke stroke)
/*     */   {
/* 464 */     if (stroke == null) {
/* 465 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 467 */     this.outlineStroke = stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isOutlineVisible()
/*     */   {
/* 478 */     return this.outlineVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlineVisible(boolean visible)
/*     */   {
/* 489 */     this.outlineVisible = visible;
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
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 509 */     PlotOrientation orientation = plot.getOrientation();
/* 510 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 512 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/*     */ 
/* 515 */     float anchorX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/*     */     
/* 517 */     float anchorY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/*     */     
/*     */ 
/* 520 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 521 */       float tempAnchor = anchorX;
/* 522 */       anchorX = anchorY;
/* 523 */       anchorY = tempAnchor;
/*     */     }
/*     */     
/* 526 */     g2.setFont(getFont());
/* 527 */     Shape hotspot = TextUtilities.calculateRotatedStringBounds(getText(), g2, anchorX, anchorY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/*     */     
/*     */ 
/* 530 */     if (this.backgroundPaint != null) {
/* 531 */       g2.setPaint(this.backgroundPaint);
/* 532 */       g2.fill(hotspot);
/*     */     }
/* 534 */     g2.setPaint(getPaint());
/* 535 */     TextUtilities.drawRotatedString(getText(), g2, anchorX, anchorY, getTextAnchor(), getRotationAngle(), getRotationAnchor());
/*     */     
/* 537 */     if (this.outlineVisible) {
/* 538 */       g2.setStroke(this.outlineStroke);
/* 539 */       g2.setPaint(this.outlinePaint);
/* 540 */       g2.draw(hotspot);
/*     */     }
/*     */     
/* 543 */     String toolTip = getToolTipText();
/* 544 */     String url = getURL();
/* 545 */     if ((toolTip != null) || (url != null)) {
/* 546 */       addEntity(info, hotspot, rendererIndex, toolTip, url);
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
/* 559 */     if (obj == this) {
/* 560 */       return true;
/*     */     }
/* 562 */     if (!(obj instanceof XYTextAnnotation)) {
/* 563 */       return false;
/*     */     }
/* 565 */     XYTextAnnotation that = (XYTextAnnotation)obj;
/* 566 */     if (!this.text.equals(that.text)) {
/* 567 */       return false;
/*     */     }
/* 569 */     if (this.x != that.x) {
/* 570 */       return false;
/*     */     }
/* 572 */     if (this.y != that.y) {
/* 573 */       return false;
/*     */     }
/* 575 */     if (!this.font.equals(that.font)) {
/* 576 */       return false;
/*     */     }
/* 578 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 579 */       return false;
/*     */     }
/* 581 */     if (!this.rotationAnchor.equals(that.rotationAnchor)) {
/* 582 */       return false;
/*     */     }
/* 584 */     if (this.rotationAngle != that.rotationAngle) {
/* 585 */       return false;
/*     */     }
/* 587 */     if (!this.textAnchor.equals(that.textAnchor)) {
/* 588 */       return false;
/*     */     }
/* 590 */     if (this.outlineVisible != that.outlineVisible) {
/* 591 */       return false;
/*     */     }
/* 593 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 594 */       return false;
/*     */     }
/* 596 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 597 */       return false;
/*     */     }
/* 599 */     if (!this.outlineStroke.equals(that.outlineStroke)) {
/* 600 */       return false;
/*     */     }
/* 602 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 611 */     int result = 193;
/* 612 */     result = 37 * this.text.hashCode();
/* 613 */     result = 37 * this.font.hashCode();
/* 614 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 615 */     long temp = Double.doubleToLongBits(this.x);
/* 616 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 617 */     temp = Double.doubleToLongBits(this.y);
/* 618 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 619 */     result = 37 * result + this.textAnchor.hashCode();
/* 620 */     result = 37 * result + this.rotationAnchor.hashCode();
/* 621 */     temp = Double.doubleToLongBits(this.rotationAngle);
/* 622 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 623 */     return result;
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
/* 634 */     return super.clone();
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
/* 645 */     stream.defaultWriteObject();
/* 646 */     SerialUtilities.writePaint(this.paint, stream);
/* 647 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 648 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 649 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
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
/* 662 */     stream.defaultReadObject();
/* 663 */     this.paint = SerialUtilities.readPaint(stream);
/* 664 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 665 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 666 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYTextAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */