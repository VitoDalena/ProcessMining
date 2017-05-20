/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.EventListener;
/*      */ import java.util.List;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.entity.AxisEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.event.AxisChangeListener;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Axis
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 7719289504573298271L;
/*      */   public static final boolean DEFAULT_AXIS_VISIBLE = true;
/*  139 */   public static final Font DEFAULT_AXIS_LABEL_FONT = new Font("SansSerif", 0, 12);
/*      */   
/*      */ 
/*      */ 
/*  143 */   public static final Paint DEFAULT_AXIS_LABEL_PAINT = Color.black;
/*      */   
/*      */ 
/*  146 */   public static final RectangleInsets DEFAULT_AXIS_LABEL_INSETS = new RectangleInsets(3.0D, 3.0D, 3.0D, 3.0D);
/*      */   
/*      */ 
/*      */ 
/*  150 */   public static final Paint DEFAULT_AXIS_LINE_PAINT = Color.gray;
/*      */   
/*      */ 
/*  153 */   public static final Stroke DEFAULT_AXIS_LINE_STROKE = new BasicStroke(1.0F);
/*      */   
/*      */ 
/*      */   public static final boolean DEFAULT_TICK_LABELS_VISIBLE = true;
/*      */   
/*      */ 
/*  159 */   public static final Font DEFAULT_TICK_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*      */ 
/*      */ 
/*  163 */   public static final Paint DEFAULT_TICK_LABEL_PAINT = Color.black;
/*      */   
/*      */ 
/*  166 */   public static final RectangleInsets DEFAULT_TICK_LABEL_INSETS = new RectangleInsets(2.0D, 4.0D, 2.0D, 4.0D);
/*      */   
/*      */ 
/*      */ 
/*      */   public static final boolean DEFAULT_TICK_MARKS_VISIBLE = true;
/*      */   
/*      */ 
/*  173 */   public static final Stroke DEFAULT_TICK_MARK_STROKE = new BasicStroke(1.0F);
/*      */   
/*      */ 
/*  176 */   public static final Paint DEFAULT_TICK_MARK_PAINT = Color.gray;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final float DEFAULT_TICK_MARK_INSIDE_LENGTH = 0.0F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final float DEFAULT_TICK_MARK_OUTSIDE_LENGTH = 2.0F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean visible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private String label;
/*      */   
/*      */ 
/*      */ 
/*      */   private Font labelFont;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint labelPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private RectangleInsets labelInsets;
/*      */   
/*      */ 
/*      */ 
/*      */   private double labelAngle;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean axisLineVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke axisLineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint axisLinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean tickLabelsVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private Font tickLabelFont;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint tickLabelPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private RectangleInsets tickLabelInsets;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean tickMarksVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private float tickMarkInsideLength;
/*      */   
/*      */ 
/*      */ 
/*      */   private float tickMarkOutsideLength;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean minorTickMarksVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private float minorTickMarkInsideLength;
/*      */   
/*      */ 
/*      */ 
/*      */   private float minorTickMarkOutsideLength;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke tickMarkStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint tickMarkPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private double fixedDimension;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Plot plot;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient EventListenerList listenerList;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Axis(String label)
/*      */   {
/*  291 */     this.label = label;
/*  292 */     this.visible = true;
/*  293 */     this.labelFont = DEFAULT_AXIS_LABEL_FONT;
/*  294 */     this.labelPaint = DEFAULT_AXIS_LABEL_PAINT;
/*  295 */     this.labelInsets = DEFAULT_AXIS_LABEL_INSETS;
/*  296 */     this.labelAngle = 0.0D;
/*      */     
/*  298 */     this.axisLineVisible = true;
/*  299 */     this.axisLinePaint = DEFAULT_AXIS_LINE_PAINT;
/*  300 */     this.axisLineStroke = DEFAULT_AXIS_LINE_STROKE;
/*      */     
/*  302 */     this.tickLabelsVisible = true;
/*  303 */     this.tickLabelFont = DEFAULT_TICK_LABEL_FONT;
/*  304 */     this.tickLabelPaint = DEFAULT_TICK_LABEL_PAINT;
/*  305 */     this.tickLabelInsets = DEFAULT_TICK_LABEL_INSETS;
/*      */     
/*  307 */     this.tickMarksVisible = true;
/*  308 */     this.tickMarkStroke = DEFAULT_TICK_MARK_STROKE;
/*  309 */     this.tickMarkPaint = DEFAULT_TICK_MARK_PAINT;
/*  310 */     this.tickMarkInsideLength = 0.0F;
/*  311 */     this.tickMarkOutsideLength = 2.0F;
/*      */     
/*  313 */     this.minorTickMarksVisible = false;
/*  314 */     this.minorTickMarkInsideLength = 0.0F;
/*  315 */     this.minorTickMarkOutsideLength = 2.0F;
/*      */     
/*  317 */     this.plot = null;
/*      */     
/*  319 */     this.listenerList = new EventListenerList();
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
/*      */   public boolean isVisible()
/*      */   {
/*  332 */     return this.visible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setVisible(boolean flag)
/*      */   {
/*  344 */     if (flag != this.visible) {
/*  345 */       this.visible = flag;
/*  346 */       fireChangeEvent();
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
/*      */   public String getLabel()
/*      */   {
/*  360 */     return this.label;
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
/*      */   public void setLabel(String label)
/*      */   {
/*  375 */     String existing = this.label;
/*  376 */     if (existing != null) {
/*  377 */       if (!existing.equals(label)) {
/*  378 */         this.label = label;
/*  379 */         fireChangeEvent();
/*      */       }
/*      */       
/*      */     }
/*  383 */     else if (label != null) {
/*  384 */       this.label = label;
/*  385 */       fireChangeEvent();
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
/*      */   public Font getLabelFont()
/*      */   {
/*  399 */     return this.labelFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelFont(Font font)
/*      */   {
/*  411 */     if (font == null) {
/*  412 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  414 */     if (!this.labelFont.equals(font)) {
/*  415 */       this.labelFont = font;
/*  416 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelPaint()
/*      */   {
/*  428 */     return this.labelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelPaint(Paint paint)
/*      */   {
/*  440 */     if (paint == null) {
/*  441 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  443 */     this.labelPaint = paint;
/*  444 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getLabelInsets()
/*      */   {
/*  456 */     return this.labelInsets;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelInsets(RectangleInsets insets)
/*      */   {
/*  468 */     setLabelInsets(insets, true);
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
/*      */   public void setLabelInsets(RectangleInsets insets, boolean notify)
/*      */   {
/*  481 */     if (insets == null) {
/*  482 */       throw new IllegalArgumentException("Null 'insets' argument.");
/*      */     }
/*  484 */     if (!insets.equals(this.labelInsets)) {
/*  485 */       this.labelInsets = insets;
/*  486 */       if (notify) {
/*  487 */         fireChangeEvent();
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
/*      */   public double getLabelAngle()
/*      */   {
/*  500 */     return this.labelAngle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelAngle(double angle)
/*      */   {
/*  512 */     this.labelAngle = angle;
/*  513 */     fireChangeEvent();
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
/*      */   public boolean isAxisLineVisible()
/*      */   {
/*  526 */     return this.axisLineVisible;
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
/*      */   public void setAxisLineVisible(boolean visible)
/*      */   {
/*  540 */     this.axisLineVisible = visible;
/*  541 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getAxisLinePaint()
/*      */   {
/*  552 */     return this.axisLinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxisLinePaint(Paint paint)
/*      */   {
/*  564 */     if (paint == null) {
/*  565 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  567 */     this.axisLinePaint = paint;
/*  568 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getAxisLineStroke()
/*      */   {
/*  579 */     return this.axisLineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxisLineStroke(Stroke stroke)
/*      */   {
/*  591 */     if (stroke == null) {
/*  592 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  594 */     this.axisLineStroke = stroke;
/*  595 */     fireChangeEvent();
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
/*      */   public boolean isTickLabelsVisible()
/*      */   {
/*  608 */     return this.tickLabelsVisible;
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
/*      */   public void setTickLabelsVisible(boolean flag)
/*      */   {
/*  624 */     if (flag != this.tickLabelsVisible) {
/*  625 */       this.tickLabelsVisible = flag;
/*  626 */       fireChangeEvent();
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
/*      */   public boolean isMinorTickMarksVisible()
/*      */   {
/*  643 */     return this.minorTickMarksVisible;
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
/*      */   public void setMinorTickMarksVisible(boolean flag)
/*      */   {
/*  657 */     if (flag != this.minorTickMarksVisible) {
/*  658 */       this.minorTickMarksVisible = flag;
/*  659 */       fireChangeEvent();
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
/*  671 */     return this.tickLabelFont;
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
/*      */   public void setTickLabelFont(Font font)
/*      */   {
/*  684 */     if (font == null) {
/*  685 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*      */     
/*  688 */     if (!this.tickLabelFont.equals(font)) {
/*  689 */       this.tickLabelFont = font;
/*  690 */       fireChangeEvent();
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
/*      */   public Paint getTickLabelPaint()
/*      */   {
/*  703 */     return this.tickLabelPaint;
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
/*  715 */     if (paint == null) {
/*  716 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  718 */     this.tickLabelPaint = paint;
/*  719 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getTickLabelInsets()
/*      */   {
/*  730 */     return this.tickLabelInsets;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelInsets(RectangleInsets insets)
/*      */   {
/*  742 */     if (insets == null) {
/*  743 */       throw new IllegalArgumentException("Null 'insets' argument.");
/*      */     }
/*  745 */     if (!this.tickLabelInsets.equals(insets)) {
/*  746 */       this.tickLabelInsets = insets;
/*  747 */       fireChangeEvent();
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
/*      */   public boolean isTickMarksVisible()
/*      */   {
/*  761 */     return this.tickMarksVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickMarksVisible(boolean flag)
/*      */   {
/*  773 */     if (flag != this.tickMarksVisible) {
/*  774 */       this.tickMarksVisible = flag;
/*  775 */       fireChangeEvent();
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
/*      */   public float getTickMarkInsideLength()
/*      */   {
/*  788 */     return this.tickMarkInsideLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickMarkInsideLength(float length)
/*      */   {
/*  800 */     this.tickMarkInsideLength = length;
/*  801 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getTickMarkOutsideLength()
/*      */   {
/*  813 */     return this.tickMarkOutsideLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickMarkOutsideLength(float length)
/*      */   {
/*  825 */     this.tickMarkOutsideLength = length;
/*  826 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getTickMarkStroke()
/*      */   {
/*  837 */     return this.tickMarkStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickMarkStroke(Stroke stroke)
/*      */   {
/*  849 */     if (stroke == null) {
/*  850 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  852 */     if (!this.tickMarkStroke.equals(stroke)) {
/*  853 */       this.tickMarkStroke = stroke;
/*  854 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getTickMarkPaint()
/*      */   {
/*  866 */     return this.tickMarkPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickMarkPaint(Paint paint)
/*      */   {
/*  878 */     if (paint == null) {
/*  879 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  881 */     this.tickMarkPaint = paint;
/*  882 */     fireChangeEvent();
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
/*      */   public float getMinorTickMarkInsideLength()
/*      */   {
/*  896 */     return this.minorTickMarkInsideLength;
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
/*      */   public void setMinorTickMarkInsideLength(float length)
/*      */   {
/*  910 */     this.minorTickMarkInsideLength = length;
/*  911 */     fireChangeEvent();
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
/*      */   public float getMinorTickMarkOutsideLength()
/*      */   {
/*  925 */     return this.minorTickMarkOutsideLength;
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
/*      */   public void setMinorTickMarkOutsideLength(float length)
/*      */   {
/*  939 */     this.minorTickMarkOutsideLength = length;
/*  940 */     fireChangeEvent();
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
/*      */   public Plot getPlot()
/*      */   {
/*  953 */     return this.plot;
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
/*      */   public void setPlot(Plot plot)
/*      */   {
/*  966 */     this.plot = plot;
/*  967 */     configure();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getFixedDimension()
/*      */   {
/*  978 */     return this.fixedDimension;
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
/*      */   public void setFixedDimension(double dimension)
/*      */   {
/*  995 */     this.fixedDimension = dimension;
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
/*      */   public abstract void configure();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract AxisSpace reserveSpace(Graphics2D paramGraphics2D, Plot paramPlot, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge, AxisSpace paramAxisSpace);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract AxisState draw(Graphics2D paramGraphics2D, double paramDouble, Rectangle2D paramRectangle2D1, Rectangle2D paramRectangle2D2, RectangleEdge paramRectangleEdge, PlotRenderingInfo paramPlotRenderingInfo);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract List refreshTicks(Graphics2D paramGraphics2D, AxisState paramAxisState, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void createAndAddEntity(double cursor, AxisState state, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*      */   {
/* 1074 */     if ((plotState == null) || (plotState.getOwner() == null)) {
/* 1075 */       return;
/*      */     }
/* 1077 */     Rectangle2D hotspot = null;
/* 1078 */     if (edge.equals(RectangleEdge.TOP)) {
/* 1079 */       hotspot = new Rectangle2D.Double(dataArea.getX(), state.getCursor(), dataArea.getWidth(), cursor - state.getCursor());
/*      */ 
/*      */ 
/*      */     }
/* 1083 */     else if (edge.equals(RectangleEdge.BOTTOM)) {
/* 1084 */       hotspot = new Rectangle2D.Double(dataArea.getX(), cursor, dataArea.getWidth(), state.getCursor() - cursor);
/*      */ 
/*      */     }
/* 1087 */     else if (edge.equals(RectangleEdge.LEFT)) {
/* 1088 */       hotspot = new Rectangle2D.Double(state.getCursor(), dataArea.getY(), cursor - state.getCursor(), dataArea.getHeight());
/*      */ 
/*      */ 
/*      */     }
/* 1092 */     else if (edge.equals(RectangleEdge.RIGHT)) {
/* 1093 */       hotspot = new Rectangle2D.Double(cursor, dataArea.getY(), state.getCursor() - cursor, dataArea.getHeight());
/*      */     }
/*      */     
/* 1096 */     EntityCollection e = plotState.getOwner().getEntityCollection();
/* 1097 */     if (e != null) {
/* 1098 */       e.add(new AxisEntity(hotspot, this));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addChangeListener(AxisChangeListener listener)
/*      */   {
/* 1110 */     this.listenerList.add(AxisChangeListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeChangeListener(AxisChangeListener listener)
/*      */   {
/* 1121 */     this.listenerList.remove(AxisChangeListener.class, listener);
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
/*      */   public boolean hasListener(EventListener listener)
/*      */   {
/* 1134 */     List list = Arrays.asList(this.listenerList.getListenerList());
/* 1135 */     return list.contains(listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void notifyListeners(AxisChangeEvent event)
/*      */   {
/* 1145 */     Object[] listeners = this.listenerList.getListenerList();
/* 1146 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 1147 */       if (listeners[i] == AxisChangeListener.class) {
/* 1148 */         ((AxisChangeListener)listeners[(i + 1)]).axisChanged(event);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void fireChangeEvent()
/*      */   {
/* 1159 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   protected Rectangle2D getLabelEnclosure(Graphics2D g2, RectangleEdge edge)
/*      */   {
/* 1173 */     Rectangle2D result = new Rectangle2D.Double();
/* 1174 */     String axisLabel = getLabel();
/* 1175 */     if ((axisLabel != null) && (!axisLabel.equals(""))) {
/* 1176 */       FontMetrics fm = g2.getFontMetrics(getLabelFont());
/* 1177 */       Rectangle2D bounds = TextUtilities.getTextBounds(axisLabel, g2, fm);
/* 1178 */       RectangleInsets insets = getLabelInsets();
/* 1179 */       bounds = insets.createOutsetRectangle(bounds);
/* 1180 */       double angle = getLabelAngle();
/* 1181 */       if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT)) {
/* 1182 */         angle -= 1.5707963267948966D;
/*      */       }
/* 1184 */       double x = bounds.getCenterX();
/* 1185 */       double y = bounds.getCenterY();
/* 1186 */       AffineTransform transformer = AffineTransform.getRotateInstance(angle, x, y);
/*      */       
/* 1188 */       Shape labelBounds = transformer.createTransformedShape(bounds);
/* 1189 */       result = labelBounds.getBounds2D();
/*      */     }
/*      */     
/* 1192 */     return result;
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
/*      */ 
/*      */ 
/*      */   protected AxisState drawLabel(String label, Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisState state)
/*      */   {
/* 1213 */     if (state == null) {
/* 1214 */       throw new IllegalArgumentException("Null 'state' argument.");
/*      */     }
/*      */     
/* 1217 */     if ((label == null) || (label.equals(""))) {
/* 1218 */       return state;
/*      */     }
/*      */     
/* 1221 */     Font font = getLabelFont();
/* 1222 */     RectangleInsets insets = getLabelInsets();
/* 1223 */     g2.setFont(font);
/* 1224 */     g2.setPaint(getLabelPaint());
/* 1225 */     FontMetrics fm = g2.getFontMetrics();
/* 1226 */     Rectangle2D labelBounds = TextUtilities.getTextBounds(label, g2, fm);
/*      */     
/* 1228 */     if (edge == RectangleEdge.TOP) {
/* 1229 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
/*      */       
/*      */ 
/* 1232 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/* 1233 */       labelBounds = rotatedLabelBounds.getBounds2D();
/* 1234 */       double labelx = dataArea.getCenterX();
/* 1235 */       double labely = state.getCursor() - insets.getBottom() - labelBounds.getHeight() / 2.0D;
/*      */       
/* 1237 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle(), TextAnchor.CENTER);
/*      */       
/*      */ 
/* 1240 */       state.cursorUp(insets.getTop() + labelBounds.getHeight() + insets.getBottom());
/*      */ 
/*      */     }
/* 1243 */     else if (edge == RectangleEdge.BOTTOM) {
/* 1244 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle(), labelBounds.getCenterX(), labelBounds.getCenterY());
/*      */       
/*      */ 
/* 1247 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/* 1248 */       labelBounds = rotatedLabelBounds.getBounds2D();
/* 1249 */       double labelx = dataArea.getCenterX();
/* 1250 */       double labely = state.getCursor() + insets.getTop() + labelBounds.getHeight() / 2.0D;
/*      */       
/* 1252 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle(), TextAnchor.CENTER);
/*      */       
/*      */ 
/* 1255 */       state.cursorDown(insets.getTop() + labelBounds.getHeight() + insets.getBottom());
/*      */ 
/*      */     }
/* 1258 */     else if (edge == RectangleEdge.LEFT) {
/* 1259 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle() - 1.5707963267948966D, labelBounds.getCenterX(), labelBounds.getCenterY());
/*      */       
/*      */ 
/* 1262 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/* 1263 */       labelBounds = rotatedLabelBounds.getBounds2D();
/* 1264 */       double labelx = state.getCursor() - insets.getRight() - labelBounds.getWidth() / 2.0D;
/*      */       
/* 1266 */       double labely = dataArea.getCenterY();
/* 1267 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle() - 1.5707963267948966D, TextAnchor.CENTER);
/*      */       
/*      */ 
/* 1270 */       state.cursorLeft(insets.getLeft() + labelBounds.getWidth() + insets.getRight());
/*      */ 
/*      */     }
/* 1273 */     else if (edge == RectangleEdge.RIGHT)
/*      */     {
/* 1275 */       AffineTransform t = AffineTransform.getRotateInstance(getLabelAngle() + 1.5707963267948966D, labelBounds.getCenterX(), labelBounds.getCenterY());
/*      */       
/*      */ 
/* 1278 */       Shape rotatedLabelBounds = t.createTransformedShape(labelBounds);
/* 1279 */       labelBounds = rotatedLabelBounds.getBounds2D();
/* 1280 */       double labelx = state.getCursor() + insets.getLeft() + labelBounds.getWidth() / 2.0D;
/*      */       
/* 1282 */       double labely = dataArea.getY() + dataArea.getHeight() / 2.0D;
/* 1283 */       TextUtilities.drawRotatedString(label, g2, (float)labelx, (float)labely, TextAnchor.CENTER, getLabelAngle() + 1.5707963267948966D, TextAnchor.CENTER);
/*      */       
/*      */ 
/* 1286 */       state.cursorRight(insets.getLeft() + labelBounds.getWidth() + insets.getRight());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1291 */     return state;
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
/*      */   protected void drawAxisLine(Graphics2D g2, double cursor, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1306 */     Line2D axisLine = null;
/* 1307 */     if (edge == RectangleEdge.TOP) {
/* 1308 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/*      */ 
/*      */     }
/* 1311 */     else if (edge == RectangleEdge.BOTTOM) {
/* 1312 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/*      */ 
/*      */     }
/* 1315 */     else if (edge == RectangleEdge.LEFT) {
/* 1316 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*      */ 
/*      */     }
/* 1319 */     else if (edge == RectangleEdge.RIGHT) {
/* 1320 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*      */     }
/*      */     
/* 1323 */     g2.setPaint(this.axisLinePaint);
/* 1324 */     g2.setStroke(this.axisLineStroke);
/* 1325 */     g2.draw(axisLine);
/*      */   }
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
/* 1338 */     Axis clone = (Axis)super.clone();
/*      */     
/* 1340 */     clone.plot = null;
/* 1341 */     clone.listenerList = new EventListenerList();
/* 1342 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1353 */     if (obj == this) {
/* 1354 */       return true;
/*      */     }
/* 1356 */     if (!(obj instanceof Axis)) {
/* 1357 */       return false;
/*      */     }
/* 1359 */     Axis that = (Axis)obj;
/* 1360 */     if (this.visible != that.visible) {
/* 1361 */       return false;
/*      */     }
/* 1363 */     if (!ObjectUtilities.equal(this.label, that.label)) {
/* 1364 */       return false;
/*      */     }
/* 1366 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont)) {
/* 1367 */       return false;
/*      */     }
/* 1369 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint)) {
/* 1370 */       return false;
/*      */     }
/* 1372 */     if (!ObjectUtilities.equal(this.labelInsets, that.labelInsets)) {
/* 1373 */       return false;
/*      */     }
/* 1375 */     if (this.labelAngle != that.labelAngle) {
/* 1376 */       return false;
/*      */     }
/* 1378 */     if (this.axisLineVisible != that.axisLineVisible) {
/* 1379 */       return false;
/*      */     }
/* 1381 */     if (!ObjectUtilities.equal(this.axisLineStroke, that.axisLineStroke)) {
/* 1382 */       return false;
/*      */     }
/* 1384 */     if (!PaintUtilities.equal(this.axisLinePaint, that.axisLinePaint)) {
/* 1385 */       return false;
/*      */     }
/* 1387 */     if (this.tickLabelsVisible != that.tickLabelsVisible) {
/* 1388 */       return false;
/*      */     }
/* 1390 */     if (!ObjectUtilities.equal(this.tickLabelFont, that.tickLabelFont)) {
/* 1391 */       return false;
/*      */     }
/* 1393 */     if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) {
/* 1394 */       return false;
/*      */     }
/* 1396 */     if (!ObjectUtilities.equal(this.tickLabelInsets, that.tickLabelInsets))
/*      */     {
/*      */ 
/* 1399 */       return false;
/*      */     }
/* 1401 */     if (this.tickMarksVisible != that.tickMarksVisible) {
/* 1402 */       return false;
/*      */     }
/* 1404 */     if (this.tickMarkInsideLength != that.tickMarkInsideLength) {
/* 1405 */       return false;
/*      */     }
/* 1407 */     if (this.tickMarkOutsideLength != that.tickMarkOutsideLength) {
/* 1408 */       return false;
/*      */     }
/* 1410 */     if (!PaintUtilities.equal(this.tickMarkPaint, that.tickMarkPaint)) {
/* 1411 */       return false;
/*      */     }
/* 1413 */     if (!ObjectUtilities.equal(this.tickMarkStroke, that.tickMarkStroke)) {
/* 1414 */       return false;
/*      */     }
/* 1416 */     if (this.minorTickMarksVisible != that.minorTickMarksVisible) {
/* 1417 */       return false;
/*      */     }
/* 1419 */     if (this.minorTickMarkInsideLength != that.minorTickMarkInsideLength) {
/* 1420 */       return false;
/*      */     }
/* 1422 */     if (this.minorTickMarkOutsideLength != that.minorTickMarkOutsideLength) {
/* 1423 */       return false;
/*      */     }
/* 1425 */     if (this.fixedDimension != that.fixedDimension) {
/* 1426 */       return false;
/*      */     }
/* 1428 */     return true;
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
/* 1439 */     stream.defaultWriteObject();
/* 1440 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 1441 */     SerialUtilities.writePaint(this.tickLabelPaint, stream);
/* 1442 */     SerialUtilities.writeStroke(this.axisLineStroke, stream);
/* 1443 */     SerialUtilities.writePaint(this.axisLinePaint, stream);
/* 1444 */     SerialUtilities.writeStroke(this.tickMarkStroke, stream);
/* 1445 */     SerialUtilities.writePaint(this.tickMarkPaint, stream);
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
/* 1458 */     stream.defaultReadObject();
/* 1459 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 1460 */     this.tickLabelPaint = SerialUtilities.readPaint(stream);
/* 1461 */     this.axisLineStroke = SerialUtilities.readStroke(stream);
/* 1462 */     this.axisLinePaint = SerialUtilities.readPaint(stream);
/* 1463 */     this.tickMarkStroke = SerialUtilities.readStroke(stream);
/* 1464 */     this.tickMarkPaint = SerialUtilities.readPaint(stream);
/* 1465 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/Axis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */