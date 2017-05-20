/*     */ package org.jfree.chart.plot;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Arrays;
/*     */ import java.util.ResourceBundle;
/*     */ import org.jfree.chart.LegendItemCollection;
/*     */ import org.jfree.chart.needle.ArrowNeedle;
/*     */ import org.jfree.chart.needle.LineNeedle;
/*     */ import org.jfree.chart.needle.LongNeedle;
/*     */ import org.jfree.chart.needle.MeterNeedle;
/*     */ import org.jfree.chart.needle.MiddlePinNeedle;
/*     */ import org.jfree.chart.needle.PinNeedle;
/*     */ import org.jfree.chart.needle.PlumNeedle;
/*     */ import org.jfree.chart.needle.PointerNeedle;
/*     */ import org.jfree.chart.needle.ShipNeedle;
/*     */ import org.jfree.chart.needle.WindNeedle;
/*     */ import org.jfree.chart.util.ResourceBundleWrapper;
/*     */ import org.jfree.data.general.DefaultValueDataset;
/*     */ import org.jfree.data.general.ValueDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompassPlot
/*     */   extends Plot
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6924382802125527395L;
/* 115 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 1, 10);
/*     */   
/*     */ 
/*     */ 
/*     */   public static final int NO_LABELS = 0;
/*     */   
/*     */ 
/*     */   public static final int VALUE_LABELS = 1;
/*     */   
/*     */ 
/*     */   private int labelType;
/*     */   
/*     */ 
/*     */   private Font labelFont;
/*     */   
/*     */ 
/* 131 */   private boolean drawBorder = false;
/*     */   
/*     */ 
/* 134 */   private transient Paint roseHighlightPaint = Color.black;
/*     */   
/*     */ 
/* 137 */   private transient Paint rosePaint = Color.yellow;
/*     */   
/*     */ 
/* 140 */   private transient Paint roseCenterPaint = Color.white;
/*     */   
/*     */ 
/* 143 */   private Font compassFont = new Font("Arial", 0, 10);
/*     */   
/*     */ 
/*     */   private transient Ellipse2D circle1;
/*     */   
/*     */ 
/*     */   private transient Ellipse2D circle2;
/*     */   
/*     */ 
/*     */   private transient Area a1;
/*     */   
/*     */ 
/*     */   private transient Area a2;
/*     */   
/*     */ 
/*     */   private transient Rectangle2D rect1;
/*     */   
/*     */ 
/* 161 */   private ValueDataset[] datasets = new ValueDataset[1];
/*     */   
/*     */ 
/* 164 */   private MeterNeedle[] seriesNeedle = new MeterNeedle[1];
/*     */   
/*     */ 
/* 167 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 175 */   protected double revolutionDistance = 360.0D;
/*     */   
/*     */ 
/*     */ 
/*     */   public CompassPlot()
/*     */   {
/* 181 */     this(new DefaultValueDataset());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CompassPlot(ValueDataset dataset)
/*     */   {
/* 191 */     if (dataset != null) {
/* 192 */       this.datasets[0] = dataset;
/* 193 */       dataset.addChangeListener(this);
/*     */     }
/* 195 */     this.circle1 = new Ellipse2D.Double();
/* 196 */     this.circle2 = new Ellipse2D.Double();
/* 197 */     this.rect1 = new Rectangle2D.Double();
/* 198 */     setSeriesNeedle(0);
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
/*     */   public int getLabelType()
/*     */   {
/* 211 */     return this.labelType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabelType(int type)
/*     */   {
/* 223 */     if ((type != 0) && (type != 1)) {
/* 224 */       throw new IllegalArgumentException("MeterPlot.setLabelType(int): unrecognised type.");
/*     */     }
/*     */     
/* 227 */     if (this.labelType != type) {
/* 228 */       this.labelType = type;
/* 229 */       fireChangeEvent();
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
/*     */   public Font getLabelFont()
/*     */   {
/* 242 */     return this.labelFont;
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
/*     */   public void setLabelFont(Font font)
/*     */   {
/* 255 */     if (font == null) {
/* 256 */       throw new IllegalArgumentException("Null 'font' not allowed.");
/*     */     }
/* 258 */     this.labelFont = font;
/* 259 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getRosePaint()
/*     */   {
/* 270 */     return this.rosePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRosePaint(Paint paint)
/*     */   {
/* 282 */     if (paint == null) {
/* 283 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 285 */     this.rosePaint = paint;
/* 286 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getRoseCenterPaint()
/*     */   {
/* 298 */     return this.roseCenterPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRoseCenterPaint(Paint paint)
/*     */   {
/* 310 */     if (paint == null) {
/* 311 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 313 */     this.roseCenterPaint = paint;
/* 314 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getRoseHighlightPaint()
/*     */   {
/* 326 */     return this.roseHighlightPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRoseHighlightPaint(Paint paint)
/*     */   {
/* 338 */     if (paint == null) {
/* 339 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 341 */     this.roseHighlightPaint = paint;
/* 342 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawBorder()
/*     */   {
/* 353 */     return this.drawBorder;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDrawBorder(boolean status)
/*     */   {
/* 364 */     this.drawBorder = status;
/* 365 */     fireChangeEvent();
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
/*     */   public void setSeriesPaint(int series, Paint paint)
/*     */   {
/* 378 */     if ((series >= 0) && (series < this.seriesNeedle.length)) {
/* 379 */       this.seriesNeedle[series].setFillPaint(paint);
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
/*     */   public void setSeriesOutlinePaint(int series, Paint p)
/*     */   {
/* 393 */     if ((series >= 0) && (series < this.seriesNeedle.length)) {
/* 394 */       this.seriesNeedle[series].setOutlinePaint(p);
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
/*     */   public void setSeriesOutlineStroke(int series, Stroke stroke)
/*     */   {
/* 409 */     if ((series >= 0) && (series < this.seriesNeedle.length)) {
/* 410 */       this.seriesNeedle[series].setOutlineStroke(stroke);
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
/*     */   public void setSeriesNeedle(int type)
/*     */   {
/* 423 */     setSeriesNeedle(0, type);
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
/*     */ 
/*     */ 
/*     */   public void setSeriesNeedle(int index, int type)
/*     */   {
/* 446 */     switch (type) {
/*     */     case 0: 
/* 448 */       setSeriesNeedle(index, new ArrowNeedle(true));
/* 449 */       setSeriesPaint(index, Color.red);
/* 450 */       this.seriesNeedle[index].setHighlightPaint(Color.white);
/* 451 */       break;
/*     */     case 1: 
/* 453 */       setSeriesNeedle(index, new LineNeedle());
/* 454 */       break;
/*     */     case 2: 
/* 456 */       MeterNeedle longNeedle = new LongNeedle();
/* 457 */       longNeedle.setRotateY(0.5D);
/* 458 */       setSeriesNeedle(index, longNeedle);
/* 459 */       break;
/*     */     case 3: 
/* 461 */       setSeriesNeedle(index, new PinNeedle());
/* 462 */       break;
/*     */     case 4: 
/* 464 */       setSeriesNeedle(index, new PlumNeedle());
/* 465 */       break;
/*     */     case 5: 
/* 467 */       setSeriesNeedle(index, new PointerNeedle());
/* 468 */       break;
/*     */     case 6: 
/* 470 */       setSeriesPaint(index, null);
/* 471 */       setSeriesOutlineStroke(index, new BasicStroke(3.0F));
/* 472 */       setSeriesNeedle(index, new ShipNeedle());
/* 473 */       break;
/*     */     case 7: 
/* 475 */       setSeriesPaint(index, Color.blue);
/* 476 */       setSeriesNeedle(index, new WindNeedle());
/* 477 */       break;
/*     */     case 8: 
/* 479 */       setSeriesNeedle(index, new ArrowNeedle(true));
/* 480 */       break;
/*     */     case 9: 
/* 482 */       setSeriesNeedle(index, new MiddlePinNeedle());
/* 483 */       break;
/*     */     
/*     */     default: 
/* 486 */       throw new IllegalArgumentException("Unrecognised type.");
/*     */     }
/*     */     
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesNeedle(int index, MeterNeedle needle)
/*     */   {
/* 499 */     if ((needle != null) && (index < this.seriesNeedle.length)) {
/* 500 */       this.seriesNeedle[index] = needle;
/*     */     }
/* 502 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ValueDataset[] getDatasets()
/*     */   {
/* 513 */     return this.datasets;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDataset(ValueDataset dataset)
/*     */   {
/* 524 */     addDataset(dataset, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void addDataset(ValueDataset dataset, MeterNeedle needle)
/*     */   {
/* 535 */     if (dataset != null) {
/* 536 */       int i = this.datasets.length + 1;
/* 537 */       ValueDataset[] t = new ValueDataset[i];
/* 538 */       MeterNeedle[] p = new MeterNeedle[i];
/* 539 */       i -= 2;
/* 540 */       for (; i >= 0; i--) {
/* 541 */         t[i] = this.datasets[i];
/* 542 */         p[i] = this.seriesNeedle[i];
/*     */       }
/* 544 */       i = this.datasets.length;
/* 545 */       t[i] = dataset;
/* 546 */       p[i] = (needle != null ? needle : p[(i - 1)]);
/*     */       
/* 548 */       ValueDataset[] a = this.datasets;
/* 549 */       MeterNeedle[] b = this.seriesNeedle;
/* 550 */       this.datasets = t;
/* 551 */       this.seriesNeedle = p;
/*     */       
/* 553 */       for (i--; i >= 0; i--) {
/* 554 */         a[i] = null;
/* 555 */         b[i] = null;
/*     */       }
/* 557 */       dataset.addChangeListener(this);
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
/*     */ 
/*     */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*     */   {
/* 575 */     int outerRadius = 0;
/* 576 */     int innerRadius = 0;
/*     */     
/*     */ 
/*     */ 
/* 580 */     if (info != null) {
/* 581 */       info.setPlotArea(area);
/*     */     }
/*     */     
/*     */ 
/* 585 */     RectangleInsets insets = getInsets();
/* 586 */     insets.trim(area);
/*     */     
/*     */ 
/* 589 */     if (this.drawBorder) {
/* 590 */       drawBackground(g2, area);
/*     */     }
/*     */     
/* 593 */     int midX = (int)(area.getWidth() / 2.0D);
/* 594 */     int midY = (int)(area.getHeight() / 2.0D);
/* 595 */     int radius = midX;
/* 596 */     if (midY < midX) {
/* 597 */       radius = midY;
/*     */     }
/* 599 */     radius--;
/* 600 */     int diameter = 2 * radius;
/*     */     
/* 602 */     midX += (int)area.getMinX();
/* 603 */     midY += (int)area.getMinY();
/*     */     
/* 605 */     this.circle1.setFrame(midX - radius, midY - radius, diameter, diameter);
/* 606 */     this.circle2.setFrame(midX - radius + 15, midY - radius + 15, diameter - 30, diameter - 30);
/*     */     
/*     */ 
/*     */ 
/* 610 */     g2.setPaint(this.rosePaint);
/* 611 */     this.a1 = new Area(this.circle1);
/* 612 */     this.a2 = new Area(this.circle2);
/* 613 */     this.a1.subtract(this.a2);
/* 614 */     g2.fill(this.a1);
/*     */     
/* 616 */     g2.setPaint(this.roseCenterPaint);
/* 617 */     int x1 = diameter - 30;
/* 618 */     g2.fillOval(midX - radius + 15, midY - radius + 15, x1, x1);
/* 619 */     g2.setPaint(this.roseHighlightPaint);
/* 620 */     g2.drawOval(midX - radius, midY - radius, diameter, diameter);
/* 621 */     x1 = diameter - 20;
/* 622 */     g2.drawOval(midX - radius + 10, midY - radius + 10, x1, x1);
/* 623 */     x1 = diameter - 30;
/* 624 */     g2.drawOval(midX - radius + 15, midY - radius + 15, x1, x1);
/* 625 */     x1 = diameter - 80;
/* 626 */     g2.drawOval(midX - radius + 40, midY - radius + 40, x1, x1);
/*     */     
/* 628 */     outerRadius = radius - 20;
/* 629 */     innerRadius = radius - 32;
/* 630 */     for (int w = 0; w < 360; w += 15) {
/* 631 */       double a = Math.toRadians(w);
/* 632 */       x1 = midX - (int)(Math.sin(a) * innerRadius);
/* 633 */       int x2 = midX - (int)(Math.sin(a) * outerRadius);
/* 634 */       int y1 = midY - (int)(Math.cos(a) * innerRadius);
/* 635 */       int y2 = midY - (int)(Math.cos(a) * outerRadius);
/* 636 */       g2.drawLine(x1, y1, x2, y2);
/*     */     }
/*     */     
/* 639 */     g2.setPaint(this.roseHighlightPaint);
/* 640 */     innerRadius = radius - 26;
/* 641 */     outerRadius = 7;
/* 642 */     for (int w = 45; w < 360; w += 90) {
/* 643 */       double a = Math.toRadians(w);
/* 644 */       x1 = midX - (int)(Math.sin(a) * innerRadius);
/* 645 */       int y1 = midY - (int)(Math.cos(a) * innerRadius);
/* 646 */       g2.fillOval(x1 - outerRadius, y1 - outerRadius, 2 * outerRadius, 2 * outerRadius);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 651 */     for (int w = 0; w < 360; w += 90) {
/* 652 */       double a = Math.toRadians(w);
/* 653 */       x1 = midX - (int)(Math.sin(a) * innerRadius);
/* 654 */       int y1 = midY - (int)(Math.cos(a) * innerRadius);
/*     */       
/* 656 */       Polygon p = new Polygon();
/* 657 */       p.addPoint(x1 - outerRadius, y1);
/* 658 */       p.addPoint(x1, y1 + outerRadius);
/* 659 */       p.addPoint(x1 + outerRadius, y1);
/* 660 */       p.addPoint(x1, y1 - outerRadius);
/* 661 */       g2.fillPolygon(p);
/*     */     }
/*     */     
/*     */ 
/* 665 */     innerRadius = radius - 42;
/* 666 */     Font f = getCompassFont(radius);
/* 667 */     g2.setFont(f);
/* 668 */     g2.drawString("N", midX - 5, midY - innerRadius + f.getSize());
/* 669 */     g2.drawString("S", midX - 5, midY + innerRadius - 5);
/* 670 */     g2.drawString("W", midX - innerRadius + 5, midY + 5);
/* 671 */     g2.drawString("E", midX + innerRadius - f.getSize(), midY + 5);
/*     */     
/*     */ 
/* 674 */     int y1 = radius / 2;
/* 675 */     x1 = radius / 6;
/* 676 */     Rectangle2D needleArea = new Rectangle2D.Double(midX - x1, midY - y1, 2 * x1, 2 * y1);
/*     */     
/*     */ 
/* 679 */     int x = this.seriesNeedle.length;
/* 680 */     int current = 0;
/* 681 */     double value = 0.0D;
/* 682 */     for (int i = this.datasets.length - 1; 
/* 683 */         i >= 0; i--) {
/* 684 */       ValueDataset data = this.datasets[i];
/*     */       
/* 686 */       if ((data != null) && (data.getValue() != null)) {
/* 687 */         value = data.getValue().doubleValue() % this.revolutionDistance;
/*     */         
/* 689 */         value = value / this.revolutionDistance * 360.0D;
/* 690 */         current = i % x;
/* 691 */         this.seriesNeedle[current].draw(g2, needleArea, value);
/*     */       }
/*     */     }
/*     */     
/* 695 */     if (this.drawBorder) {
/* 696 */       drawOutline(g2, area);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getPlotType()
/*     */   {
/* 707 */     return localizationResources.getString("Compass_Plot");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LegendItemCollection getLegendItems()
/*     */   {
/* 717 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void zoom(double percent) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Font getCompassFont(int radius)
/*     */   {
/* 737 */     float fontSize = radius / 10.0F;
/* 738 */     if (fontSize < 8.0F) {
/* 739 */       fontSize = 8.0F;
/*     */     }
/* 741 */     Font newFont = this.compassFont.deriveFont(fontSize);
/* 742 */     return newFont;
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
/* 753 */     if (obj == this) {
/* 754 */       return true;
/*     */     }
/* 756 */     if (!(obj instanceof CompassPlot)) {
/* 757 */       return false;
/*     */     }
/* 759 */     if (!super.equals(obj)) {
/* 760 */       return false;
/*     */     }
/* 762 */     CompassPlot that = (CompassPlot)obj;
/* 763 */     if (this.labelType != that.labelType) {
/* 764 */       return false;
/*     */     }
/* 766 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont)) {
/* 767 */       return false;
/*     */     }
/* 769 */     if (this.drawBorder != that.drawBorder) {
/* 770 */       return false;
/*     */     }
/* 772 */     if (!PaintUtilities.equal(this.roseHighlightPaint, that.roseHighlightPaint))
/*     */     {
/* 774 */       return false;
/*     */     }
/* 776 */     if (!PaintUtilities.equal(this.rosePaint, that.rosePaint)) {
/* 777 */       return false;
/*     */     }
/* 779 */     if (!PaintUtilities.equal(this.roseCenterPaint, that.roseCenterPaint))
/*     */     {
/* 781 */       return false;
/*     */     }
/* 783 */     if (!ObjectUtilities.equal(this.compassFont, that.compassFont)) {
/* 784 */       return false;
/*     */     }
/* 786 */     if (!Arrays.equals(this.seriesNeedle, that.seriesNeedle)) {
/* 787 */       return false;
/*     */     }
/* 789 */     if (getRevolutionDistance() != that.getRevolutionDistance()) {
/* 790 */       return false;
/*     */     }
/* 792 */     return true;
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
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 806 */     CompassPlot clone = (CompassPlot)super.clone();
/* 807 */     if (this.circle1 != null) {
/* 808 */       clone.circle1 = ((Ellipse2D)this.circle1.clone());
/*     */     }
/* 810 */     if (this.circle2 != null) {
/* 811 */       clone.circle2 = ((Ellipse2D)this.circle2.clone());
/*     */     }
/* 813 */     if (this.a1 != null) {
/* 814 */       clone.a1 = ((Area)this.a1.clone());
/*     */     }
/* 816 */     if (this.a2 != null) {
/* 817 */       clone.a2 = ((Area)this.a2.clone());
/*     */     }
/* 819 */     if (this.rect1 != null) {
/* 820 */       clone.rect1 = ((Rectangle2D)this.rect1.clone());
/*     */     }
/* 822 */     clone.datasets = ((ValueDataset[])this.datasets.clone());
/* 823 */     clone.seriesNeedle = ((MeterNeedle[])this.seriesNeedle.clone());
/*     */     
/*     */ 
/* 826 */     for (int i = 0; i < this.datasets.length; i++) {
/* 827 */       if (clone.datasets[i] != null) {
/* 828 */         clone.datasets[i].addChangeListener(clone);
/*     */       }
/*     */     }
/* 831 */     return clone;
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
/*     */   public void setRevolutionDistance(double size)
/*     */   {
/* 844 */     if (size > 0.0D) {
/* 845 */       this.revolutionDistance = size;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRevolutionDistance()
/*     */   {
/* 857 */     return this.revolutionDistance;
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
/* 868 */     stream.defaultWriteObject();
/* 869 */     SerialUtilities.writePaint(this.rosePaint, stream);
/* 870 */     SerialUtilities.writePaint(this.roseCenterPaint, stream);
/* 871 */     SerialUtilities.writePaint(this.roseHighlightPaint, stream);
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
/* 884 */     stream.defaultReadObject();
/* 885 */     this.rosePaint = SerialUtilities.readPaint(stream);
/* 886 */     this.roseCenterPaint = SerialUtilities.readPaint(stream);
/* 887 */     this.roseHighlightPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CompassPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */