/*      */ package org.jfree.chart.plot.dial;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Arc2D.Double;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class StandardDialScale
/*      */   extends AbstractDialLayer
/*      */   implements DialScale, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   static final long serialVersionUID = 3715644629665918516L;
/*      */   private double lowerBound;
/*      */   private double upperBound;
/*      */   private double startAngle;
/*      */   private double extent;
/*      */   private double tickRadius;
/*      */   private double majorTickIncrement;
/*      */   private double majorTickLength;
/*      */   private transient Paint majorTickPaint;
/*      */   private transient Stroke majorTickStroke;
/*      */   private int minorTickCount;
/*      */   private double minorTickLength;
/*      */   private transient Paint minorTickPaint;
/*      */   private transient Stroke minorTickStroke;
/*      */   private double tickLabelOffset;
/*      */   private Font tickLabelFont;
/*      */   private boolean tickLabelsVisible;
/*      */   private NumberFormat tickLabelFormatter;
/*      */   private boolean firstTickLabelVisible;
/*      */   private transient Paint tickLabelPaint;
/*      */   
/*      */   public StandardDialScale()
/*      */   {
/*  185 */     this(0.0D, 100.0D, 175.0D, -170.0D, 10.0D, 4);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public StandardDialScale(double lowerBound, double upperBound, double startAngle, double extent, double majorTickIncrement, int minorTickCount)
/*      */   {
/*  203 */     this.startAngle = startAngle;
/*  204 */     this.extent = extent;
/*  205 */     this.lowerBound = lowerBound;
/*  206 */     this.upperBound = upperBound;
/*  207 */     this.tickRadius = 0.7D;
/*  208 */     this.tickLabelsVisible = true;
/*  209 */     this.tickLabelFormatter = new DecimalFormat("0.0");
/*  210 */     this.firstTickLabelVisible = true;
/*  211 */     this.tickLabelFont = new Font("Dialog", 1, 16);
/*  212 */     this.tickLabelPaint = Color.blue;
/*  213 */     this.tickLabelOffset = 0.1D;
/*  214 */     this.majorTickIncrement = majorTickIncrement;
/*  215 */     this.majorTickLength = 0.04D;
/*  216 */     this.majorTickPaint = Color.black;
/*  217 */     this.majorTickStroke = new BasicStroke(3.0F);
/*  218 */     this.minorTickCount = minorTickCount;
/*  219 */     this.minorTickLength = 0.02D;
/*  220 */     this.minorTickPaint = Color.black;
/*  221 */     this.minorTickStroke = new BasicStroke(1.0F);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getLowerBound()
/*      */   {
/*  234 */     return this.lowerBound;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLowerBound(double lower)
/*      */   {
/*  248 */     this.lowerBound = lower;
/*  249 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getUpperBound()
/*      */   {
/*  262 */     return this.upperBound;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUpperBound(double upper)
/*      */   {
/*  276 */     this.upperBound = upper;
/*  277 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getStartAngle()
/*      */   {
/*  289 */     return this.startAngle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setStartAngle(double angle)
/*      */   {
/*  301 */     this.startAngle = angle;
/*  302 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getExtent()
/*      */   {
/*  313 */     return this.extent;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExtent(double extent)
/*      */   {
/*  325 */     this.extent = extent;
/*  326 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getTickRadius()
/*      */   {
/*  338 */     return this.tickRadius;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickRadius(double radius)
/*      */   {
/*  350 */     if (radius <= 0.0D) {
/*  351 */       throw new IllegalArgumentException("The 'radius' must be positive.");
/*      */     }
/*      */     
/*  354 */     this.tickRadius = radius;
/*  355 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMajorTickIncrement()
/*      */   {
/*  366 */     return this.majorTickIncrement;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMajorTickIncrement(double increment)
/*      */   {
/*  378 */     if (increment <= 0.0D) {
/*  379 */       throw new IllegalArgumentException("The 'increment' must be positive.");
/*      */     }
/*      */     
/*  382 */     this.majorTickIncrement = increment;
/*  383 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMajorTickLength()
/*      */   {
/*  396 */     return this.majorTickLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMajorTickLength(double length)
/*      */   {
/*  408 */     if (length < 0.0D) {
/*  409 */       throw new IllegalArgumentException("Negative 'length' argument.");
/*      */     }
/*  411 */     this.majorTickLength = length;
/*  412 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getMajorTickPaint()
/*      */   {
/*  423 */     return this.majorTickPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMajorTickPaint(Paint paint)
/*      */   {
/*  435 */     if (paint == null) {
/*  436 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  438 */     this.majorTickPaint = paint;
/*  439 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getMajorTickStroke()
/*      */   {
/*  450 */     return this.majorTickStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMajorTickStroke(Stroke stroke)
/*      */   {
/*  462 */     if (stroke == null) {
/*  463 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  465 */     this.majorTickStroke = stroke;
/*  466 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinorTickCount()
/*      */   {
/*  477 */     return this.minorTickCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickCount(int count)
/*      */   {
/*  489 */     if (count < 0) {
/*  490 */       throw new IllegalArgumentException("The 'count' cannot be negative.");
/*      */     }
/*      */     
/*  493 */     this.minorTickCount = count;
/*  494 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMinorTickLength()
/*      */   {
/*  507 */     return this.minorTickLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickLength(double length)
/*      */   {
/*  519 */     if (length < 0.0D) {
/*  520 */       throw new IllegalArgumentException("Negative 'length' argument.");
/*      */     }
/*  522 */     this.minorTickLength = length;
/*  523 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getMinorTickPaint()
/*      */   {
/*  534 */     return this.minorTickPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickPaint(Paint paint)
/*      */   {
/*  546 */     if (paint == null) {
/*  547 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  549 */     this.minorTickPaint = paint;
/*  550 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getMinorTickStroke()
/*      */   {
/*  563 */     return this.minorTickStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickStroke(Stroke stroke)
/*      */   {
/*  577 */     if (stroke == null) {
/*  578 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  580 */     this.minorTickStroke = stroke;
/*  581 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getTickLabelOffset()
/*      */   {
/*  592 */     return this.tickLabelOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelOffset(double offset)
/*      */   {
/*  604 */     this.tickLabelOffset = offset;
/*  605 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getTickLabelFont()
/*      */   {
/*  616 */     return this.tickLabelFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelFont(Font font)
/*      */   {
/*  628 */     if (font == null) {
/*  629 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  631 */     this.tickLabelFont = font;
/*  632 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getTickLabelPaint()
/*      */   {
/*  643 */     return this.tickLabelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelPaint(Paint paint)
/*      */   {
/*  653 */     if (paint == null) {
/*  654 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  656 */     this.tickLabelPaint = paint;
/*  657 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getTickLabelsVisible()
/*      */   {
/*  669 */     return this.tickLabelsVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelsVisible(boolean visible)
/*      */   {
/*  682 */     this.tickLabelsVisible = visible;
/*  683 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NumberFormat getTickLabelFormatter()
/*      */   {
/*  695 */     return this.tickLabelFormatter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelFormatter(NumberFormat formatter)
/*      */   {
/*  708 */     if (formatter == null) {
/*  709 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*      */     }
/*  711 */     this.tickLabelFormatter = formatter;
/*  712 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getFirstTickLabelVisible()
/*      */   {
/*  724 */     return this.firstTickLabelVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFirstTickLabelVisible(boolean visible)
/*      */   {
/*  737 */     this.firstTickLabelVisible = visible;
/*  738 */     notifyListeners(new DialLayerChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isClippedToWindow()
/*      */   {
/*  748 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*      */   {
/*  764 */     Rectangle2D arcRect = DialPlot.rectangleByRadius(frame, this.tickRadius, this.tickRadius);
/*      */     
/*  766 */     Rectangle2D arcRectMajor = DialPlot.rectangleByRadius(frame, this.tickRadius - this.majorTickLength, this.tickRadius - this.majorTickLength);
/*      */     
/*      */ 
/*  769 */     Rectangle2D arcRectMinor = arcRect;
/*  770 */     if ((this.minorTickCount > 0) && (this.minorTickLength > 0.0D)) {
/*  771 */       arcRectMinor = DialPlot.rectangleByRadius(frame, this.tickRadius - this.minorTickLength, this.tickRadius - this.minorTickLength);
/*      */     }
/*      */     
/*      */ 
/*  775 */     Rectangle2D arcRectForLabels = DialPlot.rectangleByRadius(frame, this.tickRadius - this.tickLabelOffset, this.tickRadius - this.tickLabelOffset);
/*      */     
/*      */ 
/*      */ 
/*  779 */     boolean firstLabel = true;
/*      */     
/*  781 */     Arc2D arc = new Arc2D.Double();
/*  782 */     Line2D workingLine = new Line2D.Double();
/*  783 */     for (double v = this.lowerBound; v <= this.upperBound; 
/*  784 */         v += this.majorTickIncrement) {
/*  785 */       arc.setArc(arcRect, this.startAngle, valueToAngle(v) - this.startAngle, 0);
/*      */       
/*  787 */       Point2D pt0 = arc.getEndPoint();
/*  788 */       arc.setArc(arcRectMajor, this.startAngle, valueToAngle(v) - this.startAngle, 0);
/*      */       
/*  790 */       Point2D pt1 = arc.getEndPoint();
/*  791 */       g2.setPaint(this.majorTickPaint);
/*  792 */       g2.setStroke(this.majorTickStroke);
/*  793 */       workingLine.setLine(pt0, pt1);
/*  794 */       g2.draw(workingLine);
/*  795 */       arc.setArc(arcRectForLabels, this.startAngle, valueToAngle(v) - this.startAngle, 0);
/*      */       
/*  797 */       Point2D pt2 = arc.getEndPoint();
/*      */       
/*  799 */       if ((this.tickLabelsVisible) && (
/*  800 */         (!firstLabel) || (this.firstTickLabelVisible))) {
/*  801 */         g2.setFont(this.tickLabelFont);
/*  802 */         g2.setPaint(this.tickLabelPaint);
/*  803 */         TextUtilities.drawAlignedString(this.tickLabelFormatter.format(v), g2, (float)pt2.getX(), (float)pt2.getY(), TextAnchor.CENTER);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  809 */       firstLabel = false;
/*      */       
/*      */ 
/*  812 */       if ((this.minorTickCount > 0) && (this.minorTickLength > 0.0D)) {
/*  813 */         double minorTickIncrement = this.majorTickIncrement / (this.minorTickCount + 1);
/*      */         
/*  815 */         for (int i = 0; i < this.minorTickCount; i++) {
/*  816 */           double vv = v + (i + 1) * minorTickIncrement;
/*  817 */           if (vv >= this.upperBound) {
/*      */             break;
/*      */           }
/*  820 */           double angle = valueToAngle(vv);
/*      */           
/*  822 */           arc.setArc(arcRect, this.startAngle, angle - this.startAngle, 0);
/*      */           
/*  824 */           pt0 = arc.getEndPoint();
/*  825 */           arc.setArc(arcRectMinor, this.startAngle, angle - this.startAngle, 0);
/*      */           
/*  827 */           Point2D pt3 = arc.getEndPoint();
/*  828 */           g2.setStroke(this.minorTickStroke);
/*  829 */           g2.setPaint(this.minorTickPaint);
/*  830 */           workingLine.setLine(pt0, pt3);
/*  831 */           g2.draw(workingLine);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double valueToAngle(double value)
/*      */   {
/*  849 */     double range = this.upperBound - this.lowerBound;
/*  850 */     double unit = this.extent / range;
/*  851 */     return this.startAngle + unit * (value - this.lowerBound);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double angleToValue(double angle)
/*      */   {
/*  864 */     return NaN.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/*  876 */     if (obj == this) {
/*  877 */       return true;
/*      */     }
/*  879 */     if (!(obj instanceof StandardDialScale)) {
/*  880 */       return false;
/*      */     }
/*  882 */     StandardDialScale that = (StandardDialScale)obj;
/*  883 */     if (this.lowerBound != that.lowerBound) {
/*  884 */       return false;
/*      */     }
/*  886 */     if (this.upperBound != that.upperBound) {
/*  887 */       return false;
/*      */     }
/*  889 */     if (this.startAngle != that.startAngle) {
/*  890 */       return false;
/*      */     }
/*  892 */     if (this.extent != that.extent) {
/*  893 */       return false;
/*      */     }
/*  895 */     if (this.tickRadius != that.tickRadius) {
/*  896 */       return false;
/*      */     }
/*  898 */     if (this.majorTickIncrement != that.majorTickIncrement) {
/*  899 */       return false;
/*      */     }
/*  901 */     if (this.majorTickLength != that.majorTickLength) {
/*  902 */       return false;
/*      */     }
/*  904 */     if (!PaintUtilities.equal(this.majorTickPaint, that.majorTickPaint)) {
/*  905 */       return false;
/*      */     }
/*  907 */     if (!this.majorTickStroke.equals(that.majorTickStroke)) {
/*  908 */       return false;
/*      */     }
/*  910 */     if (this.minorTickCount != that.minorTickCount) {
/*  911 */       return false;
/*      */     }
/*  913 */     if (this.minorTickLength != that.minorTickLength) {
/*  914 */       return false;
/*      */     }
/*  916 */     if (!PaintUtilities.equal(this.minorTickPaint, that.minorTickPaint)) {
/*  917 */       return false;
/*      */     }
/*  919 */     if (!this.minorTickStroke.equals(that.minorTickStroke)) {
/*  920 */       return false;
/*      */     }
/*  922 */     if (this.tickLabelsVisible != that.tickLabelsVisible) {
/*  923 */       return false;
/*      */     }
/*  925 */     if (this.tickLabelOffset != that.tickLabelOffset) {
/*  926 */       return false;
/*      */     }
/*  928 */     if (!this.tickLabelFont.equals(that.tickLabelFont)) {
/*  929 */       return false;
/*      */     }
/*  931 */     if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) {
/*  932 */       return false;
/*      */     }
/*  934 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/*  943 */     int result = 193;
/*      */     
/*  945 */     long temp = Double.doubleToLongBits(this.lowerBound);
/*  946 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/*      */     
/*  948 */     temp = Double.doubleToLongBits(this.upperBound);
/*  949 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/*      */     
/*  951 */     temp = Double.doubleToLongBits(this.startAngle);
/*  952 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/*      */     
/*  954 */     temp = Double.doubleToLongBits(this.extent);
/*  955 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/*      */     
/*  957 */     temp = Double.doubleToLongBits(this.tickRadius);
/*  958 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  972 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/*  983 */     return super.clone();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream stream)
/*      */     throws IOException
/*      */   {
/*  994 */     stream.defaultWriteObject();
/*  995 */     SerialUtilities.writePaint(this.majorTickPaint, stream);
/*  996 */     SerialUtilities.writeStroke(this.majorTickStroke, stream);
/*  997 */     SerialUtilities.writePaint(this.minorTickPaint, stream);
/*  998 */     SerialUtilities.writeStroke(this.minorTickStroke, stream);
/*  999 */     SerialUtilities.writePaint(this.tickLabelPaint, stream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void readObject(ObjectInputStream stream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1012 */     stream.defaultReadObject();
/* 1013 */     this.majorTickPaint = SerialUtilities.readPaint(stream);
/* 1014 */     this.majorTickStroke = SerialUtilities.readStroke(stream);
/* 1015 */     this.minorTickPaint = SerialUtilities.readPaint(stream);
/* 1016 */     this.minorTickStroke = SerialUtilities.readStroke(stream);
/* 1017 */     this.tickLabelPaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/StandardDialScale.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */