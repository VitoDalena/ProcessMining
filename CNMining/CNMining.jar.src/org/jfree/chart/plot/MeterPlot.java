/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Arc2D.Double;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.ValueDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
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
/*      */ public class MeterPlot
/*      */   extends Plot
/*      */   implements Serializable, Cloneable
/*      */ {
/*      */   private static final long serialVersionUID = 2987472457734470962L;
/*  140 */   static final Paint DEFAULT_DIAL_BACKGROUND_PAINT = Color.black;
/*      */   
/*      */ 
/*  143 */   static final Paint DEFAULT_NEEDLE_PAINT = Color.green;
/*      */   
/*      */ 
/*  146 */   static final Font DEFAULT_VALUE_FONT = new Font("SansSerif", 1, 12);
/*      */   
/*      */ 
/*  149 */   static final Paint DEFAULT_VALUE_PAINT = Color.yellow;
/*      */   
/*      */ 
/*      */   public static final int DEFAULT_METER_ANGLE = 270;
/*      */   
/*      */ 
/*      */   public static final float DEFAULT_BORDER_SIZE = 3.0F;
/*      */   
/*      */ 
/*      */   public static final float DEFAULT_CIRCLE_SIZE = 10.0F;
/*      */   
/*      */ 
/*  161 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 1, 10);
/*      */   
/*      */ 
/*      */ 
/*      */   private ValueDataset dataset;
/*      */   
/*      */ 
/*      */   private DialShape shape;
/*      */   
/*      */ 
/*      */   private int meterAngle;
/*      */   
/*      */ 
/*      */   private Range range;
/*      */   
/*      */ 
/*      */   private double tickSize;
/*      */   
/*      */ 
/*      */   private transient Paint tickPaint;
/*      */   
/*      */ 
/*      */   private String units;
/*      */   
/*      */ 
/*      */   private Font valueFont;
/*      */   
/*      */ 
/*      */   private transient Paint valuePaint;
/*      */   
/*      */ 
/*      */   private boolean drawBorder;
/*      */   
/*      */ 
/*      */   private transient Paint dialOutlinePaint;
/*      */   
/*      */ 
/*      */   private transient Paint dialBackgroundPaint;
/*      */   
/*      */ 
/*      */   private transient Paint needlePaint;
/*      */   
/*      */ 
/*      */   private boolean tickLabelsVisible;
/*      */   
/*      */ 
/*      */   private Font tickLabelFont;
/*      */   
/*      */ 
/*      */   private transient Paint tickLabelPaint;
/*      */   
/*      */ 
/*      */   private NumberFormat tickLabelFormat;
/*      */   
/*      */ 
/*  216 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private List intervals;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MeterPlot()
/*      */   {
/*  231 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MeterPlot(ValueDataset dataset)
/*      */   {
/*  241 */     this.shape = DialShape.CIRCLE;
/*  242 */     this.meterAngle = 270;
/*  243 */     this.range = new Range(0.0D, 100.0D);
/*  244 */     this.tickSize = 10.0D;
/*  245 */     this.tickPaint = Color.white;
/*  246 */     this.units = "Units";
/*  247 */     this.needlePaint = DEFAULT_NEEDLE_PAINT;
/*  248 */     this.tickLabelsVisible = true;
/*  249 */     this.tickLabelFont = DEFAULT_LABEL_FONT;
/*  250 */     this.tickLabelPaint = Color.black;
/*  251 */     this.tickLabelFormat = NumberFormat.getInstance();
/*  252 */     this.valueFont = DEFAULT_VALUE_FONT;
/*  253 */     this.valuePaint = DEFAULT_VALUE_PAINT;
/*  254 */     this.dialBackgroundPaint = DEFAULT_DIAL_BACKGROUND_PAINT;
/*  255 */     this.intervals = new ArrayList();
/*  256 */     setDataset(dataset);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DialShape getDialShape()
/*      */   {
/*  267 */     return this.shape;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDialShape(DialShape shape)
/*      */   {
/*  279 */     if (shape == null) {
/*  280 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*      */     }
/*  282 */     this.shape = shape;
/*  283 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMeterAngle()
/*      */   {
/*  295 */     return this.meterAngle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMeterAngle(int angle)
/*      */   {
/*  307 */     if ((angle < 1) || (angle > 360)) {
/*  308 */       throw new IllegalArgumentException("Invalid 'angle' (" + angle + ")");
/*      */     }
/*      */     
/*  311 */     this.meterAngle = angle;
/*  312 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range getRange()
/*      */   {
/*  323 */     return this.range;
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
/*      */   public void setRange(Range range)
/*      */   {
/*  336 */     if (range == null) {
/*  337 */       throw new IllegalArgumentException("Null 'range' argument.");
/*      */     }
/*  339 */     if (range.getLength() <= 0.0D) {
/*  340 */       throw new IllegalArgumentException("Range length must be positive.");
/*      */     }
/*      */     
/*  343 */     this.range = range;
/*  344 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getTickSize()
/*      */   {
/*  355 */     return this.tickSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickSize(double size)
/*      */   {
/*  367 */     if (size <= 0.0D) {
/*  368 */       throw new IllegalArgumentException("Requires 'size' > 0.");
/*      */     }
/*  370 */     this.tickSize = size;
/*  371 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getTickPaint()
/*      */   {
/*  383 */     return this.tickPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickPaint(Paint paint)
/*      */   {
/*  395 */     if (paint == null) {
/*  396 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  398 */     this.tickPaint = paint;
/*  399 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getUnits()
/*      */   {
/*  410 */     return this.units;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUnits(String units)
/*      */   {
/*  422 */     this.units = units;
/*  423 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getNeedlePaint()
/*      */   {
/*  434 */     return this.needlePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNeedlePaint(Paint paint)
/*      */   {
/*  446 */     if (paint == null) {
/*  447 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  449 */     this.needlePaint = paint;
/*  450 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getTickLabelsVisible()
/*      */   {
/*  461 */     return this.tickLabelsVisible;
/*      */   }
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
/*  473 */     if (this.tickLabelsVisible != visible) {
/*  474 */       this.tickLabelsVisible = visible;
/*  475 */       fireChangeEvent();
/*      */     }
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
/*  487 */     return this.tickLabelFont;
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
/*  499 */     if (font == null) {
/*  500 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  502 */     if (!this.tickLabelFont.equals(font)) {
/*  503 */       this.tickLabelFont = font;
/*  504 */       fireChangeEvent();
/*      */     }
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
/*  516 */     return this.tickLabelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelPaint(Paint paint)
/*      */   {
/*  528 */     if (paint == null) {
/*  529 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  531 */     if (!this.tickLabelPaint.equals(paint)) {
/*  532 */       this.tickLabelPaint = paint;
/*  533 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NumberFormat getTickLabelFormat()
/*      */   {
/*  545 */     return this.tickLabelFormat;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelFormat(NumberFormat format)
/*      */   {
/*  557 */     if (format == null) {
/*  558 */       throw new IllegalArgumentException("Null 'format' argument.");
/*      */     }
/*  560 */     this.tickLabelFormat = format;
/*  561 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getValueFont()
/*      */   {
/*  572 */     return this.valueFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setValueFont(Font font)
/*      */   {
/*  584 */     if (font == null) {
/*  585 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  587 */     this.valueFont = font;
/*  588 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getValuePaint()
/*      */   {
/*  599 */     return this.valuePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setValuePaint(Paint paint)
/*      */   {
/*  611 */     if (paint == null) {
/*  612 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  614 */     this.valuePaint = paint;
/*  615 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDialBackgroundPaint()
/*      */   {
/*  626 */     return this.dialBackgroundPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDialBackgroundPaint(Paint paint)
/*      */   {
/*  638 */     this.dialBackgroundPaint = paint;
/*  639 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getDrawBorder()
/*      */   {
/*  651 */     return this.drawBorder;
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
/*      */   public void setDrawBorder(boolean draw)
/*      */   {
/*  665 */     this.drawBorder = draw;
/*  666 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDialOutlinePaint()
/*      */   {
/*  677 */     return this.dialOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDialOutlinePaint(Paint paint)
/*      */   {
/*  689 */     this.dialOutlinePaint = paint;
/*  690 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueDataset getDataset()
/*      */   {
/*  701 */     return this.dataset;
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
/*      */   public void setDataset(ValueDataset dataset)
/*      */   {
/*  716 */     ValueDataset existing = this.dataset;
/*  717 */     if (existing != null) {
/*  718 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  722 */     this.dataset = dataset;
/*  723 */     if (dataset != null) {
/*  724 */       setDatasetGroup(dataset.getGroup());
/*  725 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  729 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/*  730 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getIntervals()
/*      */   {
/*  742 */     return Collections.unmodifiableList(this.intervals);
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
/*      */   public void addInterval(MeterInterval interval)
/*      */   {
/*  755 */     if (interval == null) {
/*  756 */       throw new IllegalArgumentException("Null 'interval' argument.");
/*      */     }
/*  758 */     this.intervals.add(interval);
/*  759 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearIntervals()
/*      */   {
/*  769 */     this.intervals.clear();
/*  770 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItemCollection getLegendItems()
/*      */   {
/*  779 */     LegendItemCollection result = new LegendItemCollection();
/*  780 */     Iterator iterator = this.intervals.iterator();
/*  781 */     while (iterator.hasNext()) {
/*  782 */       MeterInterval mi = (MeterInterval)iterator.next();
/*  783 */       Paint color = mi.getBackgroundPaint();
/*  784 */       if (color == null) {
/*  785 */         color = mi.getOutlinePaint();
/*      */       }
/*  787 */       LegendItem item = new LegendItem(mi.getLabel(), mi.getLabel(), null, null, new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D), color);
/*      */       
/*      */ 
/*  790 */       item.setDataset(getDataset());
/*  791 */       result.add(item);
/*      */     }
/*  793 */     return result;
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
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*      */   {
/*  810 */     if (info != null) {
/*  811 */       info.setPlotArea(area);
/*      */     }
/*      */     
/*      */ 
/*  815 */     RectangleInsets insets = getInsets();
/*  816 */     insets.trim(area);
/*      */     
/*  818 */     area.setRect(area.getX() + 4.0D, area.getY() + 4.0D, area.getWidth() - 8.0D, area.getHeight() - 8.0D);
/*      */     
/*      */ 
/*      */ 
/*  822 */     if (this.drawBorder) {
/*  823 */       drawBackground(g2, area);
/*      */     }
/*      */     
/*      */ 
/*  827 */     double gapHorizontal = 6.0D;
/*  828 */     double gapVertical = 6.0D;
/*  829 */     double meterX = area.getX() + gapHorizontal / 2.0D;
/*  830 */     double meterY = area.getY() + gapVertical / 2.0D;
/*  831 */     double meterW = area.getWidth() - gapHorizontal;
/*  832 */     double meterH = area.getHeight() - gapVertical + ((this.meterAngle <= 180) && (this.shape != DialShape.CIRCLE) ? area.getHeight() / 1.25D : 0.0D);
/*      */     
/*      */ 
/*      */ 
/*  836 */     double min = Math.min(meterW, meterH) / 2.0D;
/*  837 */     meterX = (meterX + meterX + meterW) / 2.0D - min;
/*  838 */     meterY = (meterY + meterY + meterH) / 2.0D - min;
/*  839 */     meterW = 2.0D * min;
/*  840 */     meterH = 2.0D * min;
/*      */     
/*  842 */     Rectangle2D meterArea = new Rectangle2D.Double(meterX, meterY, meterW, meterH);
/*      */     
/*      */ 
/*  845 */     Rectangle2D.Double originalArea = new Rectangle2D.Double(meterArea.getX() - 4.0D, meterArea.getY() - 4.0D, meterArea.getWidth() + 8.0D, meterArea.getHeight() + 8.0D);
/*      */     
/*      */ 
/*      */ 
/*  849 */     double meterMiddleX = meterArea.getCenterX();
/*  850 */     double meterMiddleY = meterArea.getCenterY();
/*      */     
/*      */ 
/*  853 */     ValueDataset data = getDataset();
/*  854 */     if (data != null) {
/*  855 */       double dataMin = this.range.getLowerBound();
/*  856 */       double dataMax = this.range.getUpperBound();
/*      */       
/*  858 */       Shape savedClip = g2.getClip();
/*  859 */       g2.clip(originalArea);
/*  860 */       Composite originalComposite = g2.getComposite();
/*  861 */       g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */       
/*      */ 
/*  864 */       if (this.dialBackgroundPaint != null) {
/*  865 */         fillArc(g2, originalArea, dataMin, dataMax, this.dialBackgroundPaint, true);
/*      */       }
/*      */       
/*  868 */       drawTicks(g2, meterArea, dataMin, dataMax);
/*  869 */       drawArcForInterval(g2, meterArea, new MeterInterval("", this.range, this.dialOutlinePaint, new BasicStroke(1.0F), null));
/*      */       
/*      */ 
/*  872 */       Iterator iterator = this.intervals.iterator();
/*  873 */       while (iterator.hasNext()) {
/*  874 */         MeterInterval interval = (MeterInterval)iterator.next();
/*  875 */         drawArcForInterval(g2, meterArea, interval);
/*      */       }
/*      */       
/*  878 */       Number n = data.getValue();
/*  879 */       if (n != null) {
/*  880 */         double value = n.doubleValue();
/*  881 */         drawValueLabel(g2, meterArea);
/*      */         
/*  883 */         if (this.range.contains(value)) {
/*  884 */           g2.setPaint(this.needlePaint);
/*  885 */           g2.setStroke(new BasicStroke(2.0F));
/*      */           
/*  887 */           double radius = meterArea.getWidth() / 2.0D + 3.0D + 15.0D;
/*      */           
/*  889 */           double valueAngle = valueToAngle(value);
/*  890 */           double valueP1 = meterMiddleX + radius * Math.cos(3.141592653589793D * (valueAngle / 180.0D));
/*      */           
/*  892 */           double valueP2 = meterMiddleY - radius * Math.sin(3.141592653589793D * (valueAngle / 180.0D));
/*      */           
/*      */ 
/*  895 */           Polygon arrow = new Polygon();
/*  896 */           if (((valueAngle > 135.0D) && (valueAngle < 225.0D)) || ((valueAngle < 45.0D) && (valueAngle > -45.0D)))
/*      */           {
/*      */ 
/*  899 */             double valueP3 = meterMiddleY - 2.5D;
/*      */             
/*  901 */             double valueP4 = meterMiddleY + 2.5D;
/*      */             
/*  903 */             arrow.addPoint((int)meterMiddleX, (int)valueP3);
/*  904 */             arrow.addPoint((int)meterMiddleX, (int)valueP4);
/*      */           }
/*      */           else
/*      */           {
/*  908 */             arrow.addPoint((int)(meterMiddleX - 2.5D), (int)meterMiddleY);
/*      */             
/*  910 */             arrow.addPoint((int)(meterMiddleX + 2.5D), (int)meterMiddleY);
/*      */           }
/*      */           
/*  913 */           arrow.addPoint((int)valueP1, (int)valueP2);
/*  914 */           g2.fill(arrow);
/*      */           
/*  916 */           Ellipse2D circle = new Ellipse2D.Double(meterMiddleX - 5.0D, meterMiddleY - 5.0D, 10.0D, 10.0D);
/*      */           
/*      */ 
/*      */ 
/*  920 */           g2.fill(circle);
/*      */         }
/*      */       }
/*      */       
/*  924 */       g2.setClip(savedClip);
/*  925 */       g2.setComposite(originalComposite);
/*      */     }
/*      */     
/*  928 */     if (this.drawBorder) {
/*  929 */       drawOutline(g2, area);
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
/*      */   protected void drawArcForInterval(Graphics2D g2, Rectangle2D meterArea, MeterInterval interval)
/*      */   {
/*  944 */     double minValue = interval.getRange().getLowerBound();
/*  945 */     double maxValue = interval.getRange().getUpperBound();
/*  946 */     Paint outlinePaint = interval.getOutlinePaint();
/*  947 */     Stroke outlineStroke = interval.getOutlineStroke();
/*  948 */     Paint backgroundPaint = interval.getBackgroundPaint();
/*      */     
/*  950 */     if (backgroundPaint != null) {
/*  951 */       fillArc(g2, meterArea, minValue, maxValue, backgroundPaint, false);
/*      */     }
/*  953 */     if (outlinePaint != null) {
/*  954 */       if (outlineStroke != null) {
/*  955 */         drawArc(g2, meterArea, minValue, maxValue, outlinePaint, outlineStroke);
/*      */       }
/*      */       
/*  958 */       drawTick(g2, meterArea, minValue, true);
/*  959 */       drawTick(g2, meterArea, maxValue, true);
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
/*      */ 
/*      */   protected void drawArc(Graphics2D g2, Rectangle2D area, double minValue, double maxValue, Paint paint, Stroke stroke)
/*      */   {
/*  976 */     double startAngle = valueToAngle(maxValue);
/*  977 */     double endAngle = valueToAngle(minValue);
/*  978 */     double extent = endAngle - startAngle;
/*      */     
/*  980 */     double x = area.getX();
/*  981 */     double y = area.getY();
/*  982 */     double w = area.getWidth();
/*  983 */     double h = area.getHeight();
/*  984 */     g2.setPaint(paint);
/*  985 */     g2.setStroke(stroke);
/*      */     
/*  987 */     if ((paint != null) && (stroke != null)) {
/*  988 */       Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, 0);
/*      */       
/*  990 */       g2.setPaint(paint);
/*  991 */       g2.setStroke(stroke);
/*  992 */       g2.draw(arc);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void fillArc(Graphics2D g2, Rectangle2D area, double minValue, double maxValue, Paint paint, boolean dial)
/*      */   {
/* 1011 */     if (paint == null) {
/* 1012 */       throw new IllegalArgumentException("Null 'paint' argument");
/*      */     }
/* 1014 */     double startAngle = valueToAngle(maxValue);
/* 1015 */     double endAngle = valueToAngle(minValue);
/* 1016 */     double extent = endAngle - startAngle;
/*      */     
/* 1018 */     double x = area.getX();
/* 1019 */     double y = area.getY();
/* 1020 */     double w = area.getWidth();
/* 1021 */     double h = area.getHeight();
/* 1022 */     int joinType = 0;
/* 1023 */     if (this.shape == DialShape.PIE) {
/* 1024 */       joinType = 2;
/*      */     }
/* 1026 */     else if (this.shape == DialShape.CHORD) {
/* 1027 */       if ((dial) && (this.meterAngle > 180)) {
/* 1028 */         joinType = 1;
/*      */       }
/*      */       else {
/* 1031 */         joinType = 2;
/*      */       }
/*      */     }
/* 1034 */     else if (this.shape == DialShape.CIRCLE) {
/* 1035 */       joinType = 2;
/* 1036 */       if (dial) {
/* 1037 */         extent = 360.0D;
/*      */       }
/*      */     }
/*      */     else {
/* 1041 */       throw new IllegalStateException("DialShape not recognised.");
/*      */     }
/*      */     
/* 1044 */     g2.setPaint(paint);
/* 1045 */     Arc2D.Double arc = new Arc2D.Double(x, y, w, h, startAngle, extent, joinType);
/*      */     
/* 1047 */     g2.fill(arc);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double valueToAngle(double value)
/*      */   {
/* 1058 */     value -= this.range.getLowerBound();
/* 1059 */     double baseAngle = 180 + (this.meterAngle - 180) / 2;
/* 1060 */     return baseAngle - value / this.range.getLength() * this.meterAngle;
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
/*      */   protected void drawTicks(Graphics2D g2, Rectangle2D meterArea, double minValue, double maxValue)
/*      */   {
/* 1073 */     for (double v = minValue; v <= maxValue; v += this.tickSize) {
/* 1074 */       drawTick(g2, meterArea, v);
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
/*      */   protected void drawTick(Graphics2D g2, Rectangle2D meterArea, double value)
/*      */   {
/* 1087 */     drawTick(g2, meterArea, value, false);
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
/*      */   protected void drawTick(Graphics2D g2, Rectangle2D meterArea, double value, boolean label)
/*      */   {
/* 1101 */     double valueAngle = valueToAngle(value);
/*      */     
/* 1103 */     double meterMiddleX = meterArea.getCenterX();
/* 1104 */     double meterMiddleY = meterArea.getCenterY();
/*      */     
/* 1106 */     g2.setPaint(this.tickPaint);
/* 1107 */     g2.setStroke(new BasicStroke(2.0F));
/*      */     
/* 1109 */     double valueP2X = 0.0D;
/* 1110 */     double valueP2Y = 0.0D;
/*      */     
/* 1112 */     double radius = meterArea.getWidth() / 2.0D + 3.0D;
/* 1113 */     double radius1 = radius - 15.0D;
/*      */     
/* 1115 */     double valueP1X = meterMiddleX + radius * Math.cos(3.141592653589793D * (valueAngle / 180.0D));
/*      */     
/* 1117 */     double valueP1Y = meterMiddleY - radius * Math.sin(3.141592653589793D * (valueAngle / 180.0D));
/*      */     
/*      */ 
/* 1120 */     valueP2X = meterMiddleX + radius1 * Math.cos(3.141592653589793D * (valueAngle / 180.0D));
/*      */     
/* 1122 */     valueP2Y = meterMiddleY - radius1 * Math.sin(3.141592653589793D * (valueAngle / 180.0D));
/*      */     
/*      */ 
/* 1125 */     Line2D.Double line = new Line2D.Double(valueP1X, valueP1Y, valueP2X, valueP2Y);
/*      */     
/* 1127 */     g2.draw(line);
/*      */     
/* 1129 */     if ((this.tickLabelsVisible) && (label))
/*      */     {
/* 1131 */       String tickLabel = this.tickLabelFormat.format(value);
/* 1132 */       g2.setFont(this.tickLabelFont);
/* 1133 */       g2.setPaint(this.tickLabelPaint);
/*      */       
/* 1135 */       FontMetrics fm = g2.getFontMetrics();
/* 1136 */       Rectangle2D tickLabelBounds = TextUtilities.getTextBounds(tickLabel, g2, fm);
/*      */       
/*      */ 
/* 1139 */       double x = valueP2X;
/* 1140 */       double y = valueP2Y;
/* 1141 */       if ((valueAngle == 90.0D) || (valueAngle == 270.0D)) {
/* 1142 */         x -= tickLabelBounds.getWidth() / 2.0D;
/*      */       }
/* 1144 */       else if ((valueAngle < 90.0D) || (valueAngle > 270.0D)) {
/* 1145 */         x -= tickLabelBounds.getWidth();
/*      */       }
/* 1147 */       if (((valueAngle > 135.0D) && (valueAngle < 225.0D)) || (valueAngle > 315.0D) || (valueAngle < 45.0D))
/*      */       {
/* 1149 */         y -= tickLabelBounds.getHeight() / 2.0D;
/*      */       }
/*      */       else {
/* 1152 */         y += tickLabelBounds.getHeight() / 2.0D;
/*      */       }
/* 1154 */       g2.drawString(tickLabel, (float)x, (float)y);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawValueLabel(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 1165 */     g2.setFont(this.valueFont);
/* 1166 */     g2.setPaint(this.valuePaint);
/* 1167 */     String valueStr = "No value";
/* 1168 */     if (this.dataset != null) {
/* 1169 */       Number n = this.dataset.getValue();
/* 1170 */       if (n != null) {
/* 1171 */         valueStr = this.tickLabelFormat.format(n.doubleValue()) + " " + this.units;
/*      */       }
/*      */     }
/*      */     
/* 1175 */     float x = (float)area.getCenterX();
/* 1176 */     float y = (float)area.getCenterY() + 10.0F;
/* 1177 */     TextUtilities.drawAlignedString(valueStr, g2, x, y, TextAnchor.TOP_CENTER);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/* 1187 */     return localizationResources.getString("Meter_Plot");
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
/*      */   public void zoom(double percent) {}
/*      */   
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
/* 1210 */     if (obj == this) {
/* 1211 */       return true;
/*      */     }
/* 1213 */     if (!(obj instanceof MeterPlot)) {
/* 1214 */       return false;
/*      */     }
/* 1216 */     if (!super.equals(obj)) {
/* 1217 */       return false;
/*      */     }
/* 1219 */     MeterPlot that = (MeterPlot)obj;
/* 1220 */     if (!ObjectUtilities.equal(this.units, that.units)) {
/* 1221 */       return false;
/*      */     }
/* 1223 */     if (!ObjectUtilities.equal(this.range, that.range)) {
/* 1224 */       return false;
/*      */     }
/* 1226 */     if (!ObjectUtilities.equal(this.intervals, that.intervals)) {
/* 1227 */       return false;
/*      */     }
/* 1229 */     if (!PaintUtilities.equal(this.dialOutlinePaint, that.dialOutlinePaint))
/*      */     {
/* 1231 */       return false;
/*      */     }
/* 1233 */     if (this.shape != that.shape) {
/* 1234 */       return false;
/*      */     }
/* 1236 */     if (!PaintUtilities.equal(this.dialBackgroundPaint, that.dialBackgroundPaint))
/*      */     {
/* 1238 */       return false;
/*      */     }
/* 1240 */     if (!PaintUtilities.equal(this.needlePaint, that.needlePaint)) {
/* 1241 */       return false;
/*      */     }
/* 1243 */     if (!ObjectUtilities.equal(this.valueFont, that.valueFont)) {
/* 1244 */       return false;
/*      */     }
/* 1246 */     if (!PaintUtilities.equal(this.valuePaint, that.valuePaint)) {
/* 1247 */       return false;
/*      */     }
/* 1249 */     if (!PaintUtilities.equal(this.tickPaint, that.tickPaint)) {
/* 1250 */       return false;
/*      */     }
/* 1252 */     if (this.tickSize != that.tickSize) {
/* 1253 */       return false;
/*      */     }
/* 1255 */     if (this.tickLabelsVisible != that.tickLabelsVisible) {
/* 1256 */       return false;
/*      */     }
/* 1258 */     if (!ObjectUtilities.equal(this.tickLabelFont, that.tickLabelFont)) {
/* 1259 */       return false;
/*      */     }
/* 1261 */     if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) {
/* 1262 */       return false;
/*      */     }
/* 1264 */     if (!ObjectUtilities.equal(this.tickLabelFormat, that.tickLabelFormat))
/*      */     {
/* 1266 */       return false;
/*      */     }
/* 1268 */     if (this.drawBorder != that.drawBorder) {
/* 1269 */       return false;
/*      */     }
/* 1271 */     if (this.meterAngle != that.meterAngle) {
/* 1272 */       return false;
/*      */     }
/* 1274 */     return true;
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
/* 1285 */     stream.defaultWriteObject();
/* 1286 */     SerialUtilities.writePaint(this.dialBackgroundPaint, stream);
/* 1287 */     SerialUtilities.writePaint(this.dialOutlinePaint, stream);
/* 1288 */     SerialUtilities.writePaint(this.needlePaint, stream);
/* 1289 */     SerialUtilities.writePaint(this.valuePaint, stream);
/* 1290 */     SerialUtilities.writePaint(this.tickPaint, stream);
/* 1291 */     SerialUtilities.writePaint(this.tickLabelPaint, stream);
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
/* 1304 */     stream.defaultReadObject();
/* 1305 */     this.dialBackgroundPaint = SerialUtilities.readPaint(stream);
/* 1306 */     this.dialOutlinePaint = SerialUtilities.readPaint(stream);
/* 1307 */     this.needlePaint = SerialUtilities.readPaint(stream);
/* 1308 */     this.valuePaint = SerialUtilities.readPaint(stream);
/* 1309 */     this.tickPaint = SerialUtilities.readPaint(stream);
/* 1310 */     this.tickLabelPaint = SerialUtilities.readPaint(stream);
/* 1311 */     if (this.dataset != null) {
/* 1312 */       this.dataset.addChangeListener(this);
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
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1327 */     MeterPlot clone = (MeterPlot)super.clone();
/* 1328 */     clone.tickLabelFormat = ((NumberFormat)this.tickLabelFormat.clone());
/*      */     
/* 1330 */     clone.intervals = new ArrayList(this.intervals);
/* 1331 */     if (clone.dataset != null) {
/* 1332 */       clone.dataset.addChangeListener(clone);
/*      */     }
/* 1334 */     return clone;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/MeterPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */