/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Area;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.awt.geom.RoundRectangle2D;
/*      */ import java.awt.geom.RoundRectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Arrays;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DefaultValueDataset;
/*      */ import org.jfree.data.general.ValueDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.UnitType;
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
/*      */ public class ThermometerPlot
/*      */   extends Plot
/*      */   implements ValueAxisPlot, Zoomable, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 4087093313147984390L;
/*      */   public static final int UNITS_NONE = 0;
/*      */   public static final int UNITS_FAHRENHEIT = 1;
/*      */   public static final int UNITS_CELCIUS = 2;
/*      */   public static final int UNITS_KELVIN = 3;
/*      */   public static final int NONE = 0;
/*      */   public static final int RIGHT = 1;
/*      */   public static final int LEFT = 2;
/*      */   public static final int BULB = 3;
/*      */   public static final int NORMAL = 0;
/*      */   public static final int WARNING = 1;
/*      */   public static final int CRITICAL = 2;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected static final int BULB_RADIUS = 40;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected static final int BULB_DIAMETER = 80;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected static final int COLUMN_RADIUS = 20;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected static final int COLUMN_DIAMETER = 40;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected static final int GAP_RADIUS = 5;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected static final int GAP_DIAMETER = 10;
/*      */   protected static final int AXIS_GAP = 10;
/*  236 */   protected static final String[] UNITS = { "", "°F", "°C", "°K" };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static final int RANGE_LOW = 0;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final int RANGE_HIGH = 1;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final int DISPLAY_LOW = 2;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final int DISPLAY_HIGH = 3;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final double DEFAULT_LOWER_BOUND = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final double DEFAULT_UPPER_BOUND = 100.0D;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final int DEFAULT_BULB_RADIUS = 40;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final int DEFAULT_COLUMN_RADIUS = 20;
/*      */   
/*      */ 
/*      */ 
/*      */   protected static final int DEFAULT_GAP = 5;
/*      */   
/*      */ 
/*      */ 
/*      */   private ValueDataset dataset;
/*      */   
/*      */ 
/*      */ 
/*      */   private ValueAxis rangeAxis;
/*      */   
/*      */ 
/*      */ 
/*  285 */   private double lowerBound = 0.0D;
/*      */   
/*      */ 
/*  288 */   private double upperBound = 100.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  295 */   private int bulbRadius = 40;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  302 */   private int columnRadius = 20;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  309 */   private int gap = 5;
/*      */   
/*      */ 
/*      */ 
/*      */   private RectangleInsets padding;
/*      */   
/*      */ 
/*      */ 
/*  317 */   private transient Stroke thermometerStroke = new BasicStroke(1.0F);
/*      */   
/*      */ 
/*  320 */   private transient Paint thermometerPaint = Color.black;
/*      */   
/*      */ 
/*  323 */   private int units = 2;
/*      */   
/*      */ 
/*  326 */   private int valueLocation = 3;
/*      */   
/*      */ 
/*  329 */   private int axisLocation = 2;
/*      */   
/*      */ 
/*  332 */   private Font valueFont = new Font("SansSerif", 1, 16);
/*      */   
/*      */ 
/*  335 */   private transient Paint valuePaint = Color.white;
/*      */   
/*      */ 
/*  338 */   private NumberFormat valueFormat = new DecimalFormat();
/*      */   
/*      */ 
/*  341 */   private transient Paint mercuryPaint = Color.lightGray;
/*      */   
/*      */ 
/*  344 */   private boolean showValueLines = false;
/*      */   
/*      */ 
/*  347 */   private int subrange = -1;
/*      */   
/*      */ 
/*  350 */   private double[][] subrangeInfo = { { 0.0D, 50.0D, 0.0D, 50.0D }, { 50.0D, 75.0D, 50.0D, 75.0D }, { 75.0D, 100.0D, 75.0D, 100.0D } };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  360 */   private boolean followDataInSubranges = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  366 */   private boolean useSubrangePaint = true;
/*      */   
/*      */ 
/*  369 */   private transient Paint[] subrangePaint = { Color.green, Color.orange, Color.red };
/*      */   
/*      */ 
/*      */ 
/*  373 */   private boolean subrangeIndicatorsVisible = true;
/*      */   
/*      */ 
/*  376 */   private transient Stroke subrangeIndicatorStroke = new BasicStroke(2.0F);
/*      */   
/*      */ 
/*  379 */   private transient Stroke rangeIndicatorStroke = new BasicStroke(3.0F);
/*      */   
/*      */ 
/*  382 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ThermometerPlot()
/*      */   {
/*  390 */     this(new DefaultValueDataset());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ThermometerPlot(ValueDataset dataset)
/*      */   {
/*  402 */     this.padding = new RectangleInsets(UnitType.RELATIVE, 0.05D, 0.05D, 0.05D, 0.05D);
/*      */     
/*  404 */     this.dataset = dataset;
/*  405 */     if (dataset != null) {
/*  406 */       dataset.addChangeListener(this);
/*      */     }
/*  408 */     NumberAxis axis = new NumberAxis(null);
/*  409 */     axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/*  410 */     axis.setAxisLineVisible(false);
/*  411 */     axis.setPlot(this);
/*  412 */     axis.addChangeListener(this);
/*  413 */     this.rangeAxis = axis;
/*  414 */     setAxisRange();
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
/*  425 */     return this.dataset;
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
/*  440 */     ValueDataset existing = this.dataset;
/*  441 */     if (existing != null) {
/*  442 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  446 */     this.dataset = dataset;
/*  447 */     if (dataset != null) {
/*  448 */       setDatasetGroup(dataset.getGroup());
/*  449 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  453 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/*  454 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getRangeAxis()
/*      */   {
/*  466 */     return this.rangeAxis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxis(ValueAxis axis)
/*      */   {
/*  478 */     if (axis == null) {
/*  479 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*      */     }
/*      */     
/*  482 */     this.rangeAxis.removeChangeListener(this);
/*      */     
/*  484 */     axis.setPlot(this);
/*  485 */     axis.addChangeListener(this);
/*  486 */     this.rangeAxis = axis;
/*  487 */     fireChangeEvent();
/*      */   }
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
/*  499 */     return this.lowerBound;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLowerBound(double lower)
/*      */   {
/*  510 */     this.lowerBound = lower;
/*  511 */     setAxisRange();
/*      */   }
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
/*  523 */     return this.upperBound;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUpperBound(double upper)
/*      */   {
/*  534 */     this.upperBound = upper;
/*  535 */     setAxisRange();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRange(double lower, double upper)
/*      */   {
/*  545 */     this.lowerBound = lower;
/*  546 */     this.upperBound = upper;
/*  547 */     setAxisRange();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getPadding()
/*      */   {
/*  559 */     return this.padding;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPadding(RectangleInsets padding)
/*      */   {
/*  571 */     if (padding == null) {
/*  572 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*      */     }
/*  574 */     this.padding = padding;
/*  575 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getThermometerStroke()
/*      */   {
/*  587 */     return this.thermometerStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setThermometerStroke(Stroke s)
/*      */   {
/*  599 */     if (s != null) {
/*  600 */       this.thermometerStroke = s;
/*  601 */       fireChangeEvent();
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
/*      */   public Paint getThermometerPaint()
/*      */   {
/*  614 */     return this.thermometerPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setThermometerPaint(Paint paint)
/*      */   {
/*  626 */     if (paint != null) {
/*  627 */       this.thermometerPaint = paint;
/*  628 */       fireChangeEvent();
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
/*      */   public int getUnits()
/*      */   {
/*  642 */     return this.units;
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
/*      */ 
/*      */   public void setUnits(int u)
/*      */   {
/*  661 */     if ((u >= 0) && (u < UNITS.length) && 
/*  662 */       (this.units != u)) {
/*  663 */       this.units = u;
/*  664 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setUnits(String u)
/*      */   {
/*  678 */     if (u == null) {
/*  679 */       return;
/*      */     }
/*      */     
/*  682 */     u = u.toUpperCase().trim();
/*  683 */     for (int i = 0; i < UNITS.length; i++) {
/*  684 */       if (u.equals(UNITS[i].toUpperCase().trim())) {
/*  685 */         setUnits(i);
/*  686 */         i = UNITS.length;
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
/*      */   public int getValueLocation()
/*      */   {
/*  699 */     return this.valueLocation;
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
/*      */   public void setValueLocation(int location)
/*      */   {
/*  715 */     if ((location >= 0) && (location < 4)) {
/*  716 */       this.valueLocation = location;
/*  717 */       fireChangeEvent();
/*      */     }
/*      */     else {
/*  720 */       throw new IllegalArgumentException("Location not recognised.");
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
/*      */   public int getAxisLocation()
/*      */   {
/*  733 */     return this.axisLocation;
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
/*      */   public void setAxisLocation(int location)
/*      */   {
/*  747 */     if ((location >= 0) && (location < 3)) {
/*  748 */       this.axisLocation = location;
/*  749 */       fireChangeEvent();
/*      */     }
/*      */     else {
/*  752 */       throw new IllegalArgumentException("Location not recognised.");
/*      */     }
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
/*  764 */     return this.valueFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setValueFont(Font f)
/*      */   {
/*  775 */     if (f == null) {
/*  776 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  778 */     if (!this.valueFont.equals(f)) {
/*  779 */       this.valueFont = f;
/*  780 */       fireChangeEvent();
/*      */     }
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
/*  792 */     return this.valuePaint;
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
/*  804 */     if (paint == null) {
/*  805 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  807 */     if (!this.valuePaint.equals(paint)) {
/*  808 */       this.valuePaint = paint;
/*  809 */       fireChangeEvent();
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
/*      */   public void setValueFormat(NumberFormat formatter)
/*      */   {
/*  822 */     if (formatter == null) {
/*  823 */       throw new IllegalArgumentException("Null 'formatter' argument.");
/*      */     }
/*  825 */     this.valueFormat = formatter;
/*  826 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getMercuryPaint()
/*      */   {
/*  837 */     return this.mercuryPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMercuryPaint(Paint paint)
/*      */   {
/*  849 */     if (paint == null) {
/*  850 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  852 */     this.mercuryPaint = paint;
/*  853 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public boolean getShowValueLines()
/*      */   {
/*  867 */     return this.showValueLines;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setShowValueLines(boolean b)
/*      */   {
/*  881 */     this.showValueLines = b;
/*  882 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSubrangeInfo(int range, double low, double hi)
/*      */   {
/*  893 */     setSubrangeInfo(range, low, hi, low, hi);
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
/*      */   public void setSubrangeInfo(int range, double rangeLow, double rangeHigh, double displayLow, double displayHigh)
/*      */   {
/*  909 */     if ((range >= 0) && (range < 3)) {
/*  910 */       setSubrange(range, rangeLow, rangeHigh);
/*  911 */       setDisplayRange(range, displayLow, displayHigh);
/*  912 */       setAxisRange();
/*  913 */       fireChangeEvent();
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
/*      */   public void setSubrange(int range, double low, double high)
/*      */   {
/*  926 */     if ((range >= 0) && (range < 3)) {
/*  927 */       this.subrangeInfo[range][1] = high;
/*  928 */       this.subrangeInfo[range][0] = low;
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
/*      */   public void setDisplayRange(int range, double low, double high)
/*      */   {
/*  941 */     if ((range >= 0) && (range < this.subrangeInfo.length) && (isValidNumber(high)) && (isValidNumber(low)))
/*      */     {
/*      */ 
/*  944 */       if (high > low) {
/*  945 */         this.subrangeInfo[range][3] = high;
/*  946 */         this.subrangeInfo[range][2] = low;
/*      */       }
/*      */       else {
/*  949 */         this.subrangeInfo[range][3] = low;
/*  950 */         this.subrangeInfo[range][2] = high;
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
/*      */   public Paint getSubrangePaint(int range)
/*      */   {
/*  967 */     if ((range >= 0) && (range < this.subrangePaint.length)) {
/*  968 */       return this.subrangePaint[range];
/*      */     }
/*      */     
/*  971 */     return this.mercuryPaint;
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
/*      */   public void setSubrangePaint(int range, Paint paint)
/*      */   {
/*  985 */     if ((range >= 0) && (range < this.subrangePaint.length) && (paint != null))
/*      */     {
/*  987 */       this.subrangePaint[range] = paint;
/*  988 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getFollowDataInSubranges()
/*      */   {
/*  999 */     return this.followDataInSubranges;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFollowDataInSubranges(boolean flag)
/*      */   {
/* 1009 */     this.followDataInSubranges = flag;
/* 1010 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getUseSubrangePaint()
/*      */   {
/* 1022 */     return this.useSubrangePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUseSubrangePaint(boolean flag)
/*      */   {
/* 1033 */     this.useSubrangePaint = flag;
/* 1034 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getBulbRadius()
/*      */   {
/* 1045 */     return this.bulbRadius;
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
/*      */   public void setBulbRadius(int r)
/*      */   {
/* 1059 */     this.bulbRadius = r;
/* 1060 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getBulbDiameter()
/*      */   {
/* 1072 */     return getBulbRadius() * 2;
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
/*      */   public int getColumnRadius()
/*      */   {
/* 1085 */     return this.columnRadius;
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
/*      */   public void setColumnRadius(int r)
/*      */   {
/* 1099 */     this.columnRadius = r;
/* 1100 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getColumnDiameter()
/*      */   {
/* 1112 */     return getColumnRadius() * 2;
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
/*      */   public int getGap()
/*      */   {
/* 1126 */     return this.gap;
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
/*      */   public void setGap(int gap)
/*      */   {
/* 1141 */     this.gap = gap;
/* 1142 */     fireChangeEvent();
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
/* 1159 */     RoundRectangle2D outerStem = new RoundRectangle2D.Double();
/* 1160 */     RoundRectangle2D innerStem = new RoundRectangle2D.Double();
/* 1161 */     RoundRectangle2D mercuryStem = new RoundRectangle2D.Double();
/* 1162 */     Ellipse2D outerBulb = new Ellipse2D.Double();
/* 1163 */     Ellipse2D innerBulb = new Ellipse2D.Double();
/* 1164 */     String temp = null;
/* 1165 */     FontMetrics metrics = null;
/* 1166 */     if (info != null) {
/* 1167 */       info.setPlotArea(area);
/*      */     }
/*      */     
/*      */ 
/* 1171 */     RectangleInsets insets = getInsets();
/* 1172 */     insets.trim(area);
/* 1173 */     drawBackground(g2, area);
/*      */     
/*      */ 
/* 1176 */     Rectangle2D interior = (Rectangle2D)area.clone();
/* 1177 */     this.padding.trim(interior);
/* 1178 */     int midX = (int)(interior.getX() + interior.getWidth() / 2.0D);
/* 1179 */     int midY = (int)(interior.getY() + interior.getHeight() / 2.0D);
/* 1180 */     int stemTop = (int)(interior.getMinY() + getBulbRadius());
/* 1181 */     int stemBottom = (int)(interior.getMaxY() - getBulbDiameter());
/* 1182 */     Rectangle2D dataArea = new Rectangle2D.Double(midX - getColumnRadius(), stemTop, getColumnRadius(), stemBottom - stemTop);
/*      */     
/*      */ 
/* 1185 */     outerBulb.setFrame(midX - getBulbRadius(), stemBottom, getBulbDiameter(), getBulbDiameter());
/*      */     
/*      */ 
/* 1188 */     outerStem.setRoundRect(midX - getColumnRadius(), interior.getMinY(), getColumnDiameter(), stemBottom + getBulbDiameter() - stemTop, getColumnDiameter(), getColumnDiameter());
/*      */     
/*      */ 
/*      */ 
/* 1192 */     Area outerThermometer = new Area(outerBulb);
/* 1193 */     Area tempArea = new Area(outerStem);
/* 1194 */     outerThermometer.add(tempArea);
/*      */     
/* 1196 */     innerBulb.setFrame(midX - getBulbRadius() + getGap(), stemBottom + getGap(), getBulbDiameter() - getGap() * 2, getBulbDiameter() - getGap() * 2);
/*      */     
/*      */ 
/*      */ 
/* 1200 */     innerStem.setRoundRect(midX - getColumnRadius() + getGap(), interior.getMinY() + getGap(), getColumnDiameter() - getGap() * 2, stemBottom + getBulbDiameter() - getGap() * 2 - stemTop, getColumnDiameter() - getGap() * 2, getColumnDiameter() - getGap() * 2);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1206 */     Area innerThermometer = new Area(innerBulb);
/* 1207 */     tempArea = new Area(innerStem);
/* 1208 */     innerThermometer.add(tempArea);
/*      */     
/* 1210 */     if ((this.dataset != null) && (this.dataset.getValue() != null)) {
/* 1211 */       double current = this.dataset.getValue().doubleValue();
/* 1212 */       double ds = this.rangeAxis.valueToJava2D(current, dataArea, RectangleEdge.LEFT);
/*      */       
/*      */ 
/* 1215 */       int i = getColumnDiameter() - getGap() * 2;
/* 1216 */       int j = getColumnRadius() - getGap();
/* 1217 */       int l = i / 2;
/* 1218 */       int k = (int)Math.round(ds);
/* 1219 */       if (k < getGap() + interior.getMinY()) {
/* 1220 */         k = (int)(getGap() + interior.getMinY());
/* 1221 */         l = getBulbRadius();
/*      */       }
/*      */       
/* 1224 */       Area mercury = new Area(innerBulb);
/*      */       
/* 1226 */       if (k < stemBottom + getBulbRadius()) {
/* 1227 */         mercuryStem.setRoundRect(midX - j, k, i, stemBottom + getBulbRadius() - k, l, l);
/*      */         
/* 1229 */         tempArea = new Area(mercuryStem);
/* 1230 */         mercury.add(tempArea);
/*      */       }
/*      */       
/* 1233 */       g2.setPaint(getCurrentPaint());
/* 1234 */       g2.fill(mercury);
/*      */       
/*      */ 
/* 1237 */       if (this.subrangeIndicatorsVisible) {
/* 1238 */         g2.setStroke(this.subrangeIndicatorStroke);
/* 1239 */         Range range = this.rangeAxis.getRange();
/*      */         
/*      */ 
/* 1242 */         double value = this.subrangeInfo[0][0];
/* 1243 */         if (range.contains(value)) {
/* 1244 */           double x = midX + getColumnRadius() + 2;
/* 1245 */           double y = this.rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */           
/* 1247 */           Line2D line = new Line2D.Double(x, y, x + 10.0D, y);
/* 1248 */           g2.setPaint(this.subrangePaint[0]);
/* 1249 */           g2.draw(line);
/*      */         }
/*      */         
/*      */ 
/* 1253 */         value = this.subrangeInfo[1][0];
/* 1254 */         if (range.contains(value)) {
/* 1255 */           double x = midX + getColumnRadius() + 2;
/* 1256 */           double y = this.rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */           
/* 1258 */           Line2D line = new Line2D.Double(x, y, x + 10.0D, y);
/* 1259 */           g2.setPaint(this.subrangePaint[1]);
/* 1260 */           g2.draw(line);
/*      */         }
/*      */         
/*      */ 
/* 1264 */         value = this.subrangeInfo[2][0];
/* 1265 */         if (range.contains(value)) {
/* 1266 */           double x = midX + getColumnRadius() + 2;
/* 1267 */           double y = this.rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */           
/* 1269 */           Line2D line = new Line2D.Double(x, y, x + 10.0D, y);
/* 1270 */           g2.setPaint(this.subrangePaint[2]);
/* 1271 */           g2.draw(line);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1276 */       if ((this.rangeAxis != null) && (this.axisLocation != 0)) {
/* 1277 */         int drawWidth = 10;
/* 1278 */         if (this.showValueLines) {
/* 1279 */           drawWidth += getColumnDiameter();
/*      */         }
/*      */         
/* 1282 */         double cursor = 0.0D;
/*      */         Rectangle2D drawArea;
/* 1284 */         switch (this.axisLocation) {
/*      */         case 1: 
/* 1286 */           cursor = midX + getColumnRadius();
/* 1287 */           drawArea = new Rectangle2D.Double(cursor, stemTop, drawWidth, stemBottom - stemTop + 1);
/*      */           
/* 1289 */           this.rangeAxis.draw(g2, cursor, area, drawArea, RectangleEdge.RIGHT, null);
/*      */           
/* 1291 */           break;
/*      */         
/*      */ 
/*      */         case 2: 
/*      */         default: 
/* 1296 */           cursor = midX - getColumnRadius();
/* 1297 */           drawArea = new Rectangle2D.Double(cursor, stemTop, drawWidth, stemBottom - stemTop + 1);
/*      */           
/* 1299 */           this.rangeAxis.draw(g2, cursor, area, drawArea, RectangleEdge.LEFT, null);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1307 */       g2.setFont(this.valueFont);
/* 1308 */       g2.setPaint(this.valuePaint);
/* 1309 */       metrics = g2.getFontMetrics();
/* 1310 */       switch (this.valueLocation) {
/*      */       case 1: 
/* 1312 */         g2.drawString(this.valueFormat.format(current), midX + getColumnRadius() + getGap(), midY);
/*      */         
/* 1314 */         break;
/*      */       case 2: 
/* 1316 */         String valueString = this.valueFormat.format(current);
/* 1317 */         int stringWidth = metrics.stringWidth(valueString);
/* 1318 */         g2.drawString(valueString, midX - getColumnRadius() - getGap() - stringWidth, midY);
/*      */         
/* 1320 */         break;
/*      */       case 3: 
/* 1322 */         temp = this.valueFormat.format(current);
/* 1323 */         i = metrics.stringWidth(temp) / 2;
/* 1324 */         g2.drawString(temp, midX - i, stemBottom + getBulbRadius() + getGap());
/*      */         
/* 1326 */         break;
/*      */       }
/*      */       
/*      */     }
/*      */     
/*      */ 
/* 1332 */     g2.setPaint(this.thermometerPaint);
/* 1333 */     g2.setFont(this.valueFont);
/*      */     
/*      */ 
/* 1336 */     metrics = g2.getFontMetrics();
/* 1337 */     int tickX1 = midX - getColumnRadius() - getGap() * 2 - metrics.stringWidth(UNITS[this.units]);
/*      */     
/* 1339 */     if (tickX1 > area.getMinX()) {
/* 1340 */       g2.drawString(UNITS[this.units], tickX1, (int)(area.getMinY() + 20.0D));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1345 */     g2.setStroke(this.thermometerStroke);
/* 1346 */     g2.draw(outerThermometer);
/* 1347 */     g2.draw(innerThermometer);
/*      */     
/* 1349 */     drawOutline(g2, area);
/*      */   }
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
/*      */   public String getPlotType()
/*      */   {
/* 1369 */     return localizationResources.getString("Thermometer_Plot");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void datasetChanged(DatasetChangeEvent event)
/*      */   {
/* 1378 */     if (this.dataset != null) {
/* 1379 */       Number vn = this.dataset.getValue();
/* 1380 */       if (vn != null) {
/* 1381 */         double value = vn.doubleValue();
/* 1382 */         if (inSubrange(0, value)) {
/* 1383 */           this.subrange = 0;
/*      */         }
/* 1385 */         else if (inSubrange(1, value)) {
/* 1386 */           this.subrange = 1;
/*      */         }
/* 1388 */         else if (inSubrange(2, value)) {
/* 1389 */           this.subrange = 2;
/*      */         }
/*      */         else {
/* 1392 */           this.subrange = -1;
/*      */         }
/* 1394 */         setAxisRange();
/*      */       }
/*      */     }
/* 1397 */     super.datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Number getMinimumVerticalDataValue()
/*      */   {
/* 1411 */     return new Double(this.lowerBound);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Number getMaximumVerticalDataValue()
/*      */   {
/* 1425 */     return new Double(this.upperBound);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range getDataRange(ValueAxis axis)
/*      */   {
/* 1436 */     return new Range(this.lowerBound, this.upperBound);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   protected void setAxisRange()
/*      */   {
/* 1443 */     if ((this.subrange >= 0) && (this.followDataInSubranges)) {
/* 1444 */       this.rangeAxis.setRange(new Range(this.subrangeInfo[this.subrange][2], this.subrangeInfo[this.subrange][3]));
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 1449 */       this.rangeAxis.setRange(this.lowerBound, this.upperBound);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItemCollection getLegendItems()
/*      */   {
/* 1459 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PlotOrientation getOrientation()
/*      */   {
/* 1468 */     return PlotOrientation.VERTICAL;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected static boolean isValidNumber(double d)
/*      */   {
/* 1480 */     return (!Double.isNaN(d)) && (!Double.isInfinite(d));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean inSubrange(int subrange, double value)
/*      */   {
/* 1492 */     return (value > this.subrangeInfo[subrange][0]) && (value <= this.subrangeInfo[subrange][1]);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Paint getCurrentPaint()
/*      */   {
/* 1504 */     Paint result = this.mercuryPaint;
/* 1505 */     if (this.useSubrangePaint) {
/* 1506 */       double value = this.dataset.getValue().doubleValue();
/* 1507 */       if (inSubrange(0, value)) {
/* 1508 */         result = this.subrangePaint[0];
/*      */       }
/* 1510 */       else if (inSubrange(1, value)) {
/* 1511 */         result = this.subrangePaint[1];
/*      */       }
/* 1513 */       else if (inSubrange(2, value)) {
/* 1514 */         result = this.subrangePaint[2];
/*      */       }
/*      */     }
/* 1517 */     return result;
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
/* 1529 */     if (obj == this) {
/* 1530 */       return true;
/*      */     }
/* 1532 */     if (!(obj instanceof ThermometerPlot)) {
/* 1533 */       return false;
/*      */     }
/* 1535 */     ThermometerPlot that = (ThermometerPlot)obj;
/* 1536 */     if (!super.equals(obj)) {
/* 1537 */       return false;
/*      */     }
/* 1539 */     if (!ObjectUtilities.equal(this.rangeAxis, that.rangeAxis)) {
/* 1540 */       return false;
/*      */     }
/* 1542 */     if (this.axisLocation != that.axisLocation) {
/* 1543 */       return false;
/*      */     }
/* 1545 */     if (this.lowerBound != that.lowerBound) {
/* 1546 */       return false;
/*      */     }
/* 1548 */     if (this.upperBound != that.upperBound) {
/* 1549 */       return false;
/*      */     }
/* 1551 */     if (!ObjectUtilities.equal(this.padding, that.padding)) {
/* 1552 */       return false;
/*      */     }
/* 1554 */     if (!ObjectUtilities.equal(this.thermometerStroke, that.thermometerStroke))
/*      */     {
/* 1556 */       return false;
/*      */     }
/* 1558 */     if (!PaintUtilities.equal(this.thermometerPaint, that.thermometerPaint))
/*      */     {
/* 1560 */       return false;
/*      */     }
/* 1562 */     if (this.units != that.units) {
/* 1563 */       return false;
/*      */     }
/* 1565 */     if (this.valueLocation != that.valueLocation) {
/* 1566 */       return false;
/*      */     }
/* 1568 */     if (!ObjectUtilities.equal(this.valueFont, that.valueFont)) {
/* 1569 */       return false;
/*      */     }
/* 1571 */     if (!PaintUtilities.equal(this.valuePaint, that.valuePaint)) {
/* 1572 */       return false;
/*      */     }
/* 1574 */     if (!ObjectUtilities.equal(this.valueFormat, that.valueFormat)) {
/* 1575 */       return false;
/*      */     }
/* 1577 */     if (!PaintUtilities.equal(this.mercuryPaint, that.mercuryPaint)) {
/* 1578 */       return false;
/*      */     }
/* 1580 */     if (this.showValueLines != that.showValueLines) {
/* 1581 */       return false;
/*      */     }
/* 1583 */     if (this.subrange != that.subrange) {
/* 1584 */       return false;
/*      */     }
/* 1586 */     if (this.followDataInSubranges != that.followDataInSubranges) {
/* 1587 */       return false;
/*      */     }
/* 1589 */     if (!equal(this.subrangeInfo, that.subrangeInfo)) {
/* 1590 */       return false;
/*      */     }
/* 1592 */     if (this.useSubrangePaint != that.useSubrangePaint) {
/* 1593 */       return false;
/*      */     }
/* 1595 */     if (this.bulbRadius != that.bulbRadius) {
/* 1596 */       return false;
/*      */     }
/* 1598 */     if (this.columnRadius != that.columnRadius) {
/* 1599 */       return false;
/*      */     }
/* 1601 */     if (this.gap != that.gap) {
/* 1602 */       return false;
/*      */     }
/* 1604 */     for (int i = 0; i < this.subrangePaint.length; i++) {
/* 1605 */       if (!PaintUtilities.equal(this.subrangePaint[i], that.subrangePaint[i]))
/*      */       {
/* 1607 */         return false;
/*      */       }
/*      */     }
/* 1610 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private static boolean equal(double[][] array1, double[][] array2)
/*      */   {
/* 1622 */     if (array1 == null) {
/* 1623 */       return array2 == null;
/*      */     }
/* 1625 */     if (array2 == null) {
/* 1626 */       return false;
/*      */     }
/* 1628 */     if (array1.length != array2.length) {
/* 1629 */       return false;
/*      */     }
/* 1631 */     for (int i = 0; i < array1.length; i++) {
/* 1632 */       if (!Arrays.equals(array1[i], array2[i])) {
/* 1633 */         return false;
/*      */       }
/*      */     }
/* 1636 */     return true;
/*      */   }
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
/* 1648 */     ThermometerPlot clone = (ThermometerPlot)super.clone();
/*      */     
/* 1650 */     if (clone.dataset != null) {
/* 1651 */       clone.dataset.addChangeListener(clone);
/*      */     }
/* 1653 */     clone.rangeAxis = ((ValueAxis)ObjectUtilities.clone(this.rangeAxis));
/* 1654 */     if (clone.rangeAxis != null) {
/* 1655 */       clone.rangeAxis.setPlot(clone);
/* 1656 */       clone.rangeAxis.addChangeListener(clone);
/*      */     }
/* 1658 */     clone.valueFormat = ((NumberFormat)this.valueFormat.clone());
/* 1659 */     clone.subrangePaint = ((Paint[])this.subrangePaint.clone());
/*      */     
/* 1661 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void writeObject(ObjectOutputStream stream)
/*      */     throws IOException
/*      */   {
/* 1673 */     stream.defaultWriteObject();
/* 1674 */     SerialUtilities.writeStroke(this.thermometerStroke, stream);
/* 1675 */     SerialUtilities.writePaint(this.thermometerPaint, stream);
/* 1676 */     SerialUtilities.writePaint(this.valuePaint, stream);
/* 1677 */     SerialUtilities.writePaint(this.mercuryPaint, stream);
/* 1678 */     SerialUtilities.writeStroke(this.subrangeIndicatorStroke, stream);
/* 1679 */     SerialUtilities.writeStroke(this.rangeIndicatorStroke, stream);
/* 1680 */     for (int i = 0; i < 3; i++) {
/* 1681 */       SerialUtilities.writePaint(this.subrangePaint[i], stream);
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
/*      */   private void readObject(ObjectInputStream stream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1695 */     stream.defaultReadObject();
/* 1696 */     this.thermometerStroke = SerialUtilities.readStroke(stream);
/* 1697 */     this.thermometerPaint = SerialUtilities.readPaint(stream);
/* 1698 */     this.valuePaint = SerialUtilities.readPaint(stream);
/* 1699 */     this.mercuryPaint = SerialUtilities.readPaint(stream);
/* 1700 */     this.subrangeIndicatorStroke = SerialUtilities.readStroke(stream);
/* 1701 */     this.rangeIndicatorStroke = SerialUtilities.readStroke(stream);
/* 1702 */     this.subrangePaint = new Paint[3];
/* 1703 */     for (int i = 0; i < 3; i++) {
/* 1704 */       this.subrangePaint[i] = SerialUtilities.readPaint(stream);
/*      */     }
/* 1706 */     if (this.rangeAxis != null) {
/* 1707 */       this.rangeAxis.addChangeListener(this);
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
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source) {}
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
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source, boolean useAnchor) {}
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
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source)
/*      */   {
/* 1748 */     this.rangeAxis.resizeRange(factor);
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
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source, boolean useAnchor)
/*      */   {
/* 1764 */     double anchorY = getRangeAxis().java2DToValue(source.getY(), state.getDataArea(), RectangleEdge.LEFT);
/*      */     
/* 1766 */     this.rangeAxis.resizeRange(factor, anchorY);
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
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {}
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
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source)
/*      */   {
/* 1792 */     this.rangeAxis.zoomRange(lowerPercent, upperPercent);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainZoomable()
/*      */   {
/* 1801 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/* 1810 */     return true;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/ThermometerPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */