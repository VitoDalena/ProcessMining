/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Line2D.Float;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.text.DateFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collections;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.TimeZone;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.time.Day;
/*      */ import org.jfree.data.time.Month;
/*      */ import org.jfree.data.time.RegularTimePeriod;
/*      */ import org.jfree.data.time.Year;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
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
/*      */ public class PeriodAxis
/*      */   extends ValueAxis
/*      */   implements Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 8353295532075872069L;
/*      */   private RegularTimePeriod first;
/*      */   private RegularTimePeriod last;
/*      */   private TimeZone timeZone;
/*      */   private Locale locale;
/*      */   private Calendar calendar;
/*      */   private Class autoRangeTimePeriodClass;
/*      */   private Class majorTickTimePeriodClass;
/*      */   private boolean minorTickMarksVisible;
/*      */   private Class minorTickTimePeriodClass;
/*  164 */   private float minorTickMarkInsideLength = 0.0F;
/*      */   
/*      */ 
/*  167 */   private float minorTickMarkOutsideLength = 2.0F;
/*      */   
/*      */ 
/*  170 */   private transient Stroke minorTickMarkStroke = new BasicStroke(0.5F);
/*      */   
/*      */ 
/*  173 */   private transient Paint minorTickMarkPaint = Color.black;
/*      */   
/*      */ 
/*      */ 
/*      */   private PeriodAxisLabelInfo[] labelInfo;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodAxis(String label)
/*      */   {
/*  184 */     this(label, new Day(), new Day());
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
/*      */   public PeriodAxis(String label, RegularTimePeriod first, RegularTimePeriod last)
/*      */   {
/*  198 */     this(label, first, last, TimeZone.getDefault(), Locale.getDefault());
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public PeriodAxis(String label, RegularTimePeriod first, RegularTimePeriod last, TimeZone timeZone)
/*      */   {
/*  217 */     this(label, first, last, timeZone, Locale.getDefault());
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
/*      */   public PeriodAxis(String label, RegularTimePeriod first, RegularTimePeriod last, TimeZone timeZone, Locale locale)
/*      */   {
/*  235 */     super(label, null);
/*  236 */     if (timeZone == null) {
/*  237 */       throw new IllegalArgumentException("Null 'timeZone' argument.");
/*      */     }
/*  239 */     if (locale == null) {
/*  240 */       throw new IllegalArgumentException("Null 'locale' argument.");
/*      */     }
/*  242 */     this.first = first;
/*  243 */     this.last = last;
/*  244 */     this.timeZone = timeZone;
/*  245 */     this.locale = locale;
/*  246 */     this.calendar = Calendar.getInstance(timeZone, locale);
/*  247 */     this.first.peg(this.calendar);
/*  248 */     this.last.peg(this.calendar);
/*  249 */     this.autoRangeTimePeriodClass = first.getClass();
/*  250 */     this.majorTickTimePeriodClass = first.getClass();
/*  251 */     this.minorTickMarksVisible = false;
/*  252 */     this.minorTickTimePeriodClass = RegularTimePeriod.downsize(this.majorTickTimePeriodClass);
/*      */     
/*  254 */     setAutoRange(true);
/*  255 */     this.labelInfo = new PeriodAxisLabelInfo[2];
/*  256 */     this.labelInfo[0] = new PeriodAxisLabelInfo(Month.class, new SimpleDateFormat("MMM", locale));
/*      */     
/*  258 */     this.labelInfo[1] = new PeriodAxisLabelInfo(Year.class, new SimpleDateFormat("yyyy", locale));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RegularTimePeriod getFirst()
/*      */   {
/*  268 */     return this.first;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFirst(RegularTimePeriod first)
/*      */   {
/*  278 */     if (first == null) {
/*  279 */       throw new IllegalArgumentException("Null 'first' argument.");
/*      */     }
/*  281 */     this.first = first;
/*  282 */     this.first.peg(this.calendar);
/*  283 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RegularTimePeriod getLast()
/*      */   {
/*  292 */     return this.last;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLast(RegularTimePeriod last)
/*      */   {
/*  302 */     if (last == null) {
/*  303 */       throw new IllegalArgumentException("Null 'last' argument.");
/*      */     }
/*  305 */     this.last = last;
/*  306 */     this.last.peg(this.calendar);
/*  307 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TimeZone getTimeZone()
/*      */   {
/*  317 */     return this.timeZone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTimeZone(TimeZone zone)
/*      */   {
/*  327 */     if (zone == null) {
/*  328 */       throw new IllegalArgumentException("Null 'zone' argument.");
/*      */     }
/*  330 */     this.timeZone = zone;
/*  331 */     this.calendar = Calendar.getInstance(zone, this.locale);
/*  332 */     this.first.peg(this.calendar);
/*  333 */     this.last.peg(this.calendar);
/*  334 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Locale getLocale()
/*      */   {
/*  345 */     return this.locale;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Class getAutoRangeTimePeriodClass()
/*      */   {
/*  355 */     return this.autoRangeTimePeriodClass;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoRangeTimePeriodClass(Class c)
/*      */   {
/*  366 */     if (c == null) {
/*  367 */       throw new IllegalArgumentException("Null 'c' argument.");
/*      */     }
/*  369 */     this.autoRangeTimePeriodClass = c;
/*  370 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Class getMajorTickTimePeriodClass()
/*      */   {
/*  379 */     return this.majorTickTimePeriodClass;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMajorTickTimePeriodClass(Class c)
/*      */   {
/*  390 */     if (c == null) {
/*  391 */       throw new IllegalArgumentException("Null 'c' argument.");
/*      */     }
/*  393 */     this.majorTickTimePeriodClass = c;
/*  394 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isMinorTickMarksVisible()
/*      */   {
/*  404 */     return this.minorTickMarksVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickMarksVisible(boolean visible)
/*      */   {
/*  415 */     this.minorTickMarksVisible = visible;
/*  416 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Class getMinorTickTimePeriodClass()
/*      */   {
/*  425 */     return this.minorTickTimePeriodClass;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickTimePeriodClass(Class c)
/*      */   {
/*  436 */     if (c == null) {
/*  437 */       throw new IllegalArgumentException("Null 'c' argument.");
/*      */     }
/*  439 */     this.minorTickTimePeriodClass = c;
/*  440 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getMinorTickMarkStroke()
/*      */   {
/*  450 */     return this.minorTickMarkStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickMarkStroke(Stroke stroke)
/*      */   {
/*  461 */     if (stroke == null) {
/*  462 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  464 */     this.minorTickMarkStroke = stroke;
/*  465 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getMinorTickMarkPaint()
/*      */   {
/*  475 */     return this.minorTickMarkPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickMarkPaint(Paint paint)
/*      */   {
/*  486 */     if (paint == null) {
/*  487 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  489 */     this.minorTickMarkPaint = paint;
/*  490 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getMinorTickMarkInsideLength()
/*      */   {
/*  499 */     return this.minorTickMarkInsideLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickMarkInsideLength(float length)
/*      */   {
/*  509 */     this.minorTickMarkInsideLength = length;
/*  510 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getMinorTickMarkOutsideLength()
/*      */   {
/*  519 */     return this.minorTickMarkOutsideLength;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinorTickMarkOutsideLength(float length)
/*      */   {
/*  529 */     this.minorTickMarkOutsideLength = length;
/*  530 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PeriodAxisLabelInfo[] getLabelInfo()
/*      */   {
/*  539 */     return this.labelInfo;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelInfo(PeriodAxisLabelInfo[] info)
/*      */   {
/*  549 */     this.labelInfo = info;
/*  550 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify)
/*      */   {
/*  566 */     long upper = Math.round(range.getUpperBound());
/*  567 */     long lower = Math.round(range.getLowerBound());
/*  568 */     this.first = createInstance(this.autoRangeTimePeriodClass, new Date(lower), this.timeZone, this.locale);
/*      */     
/*  570 */     this.last = createInstance(this.autoRangeTimePeriodClass, new Date(upper), this.timeZone, this.locale);
/*      */     
/*  572 */     super.setRange(new Range(this.first.getFirstMillisecond(), this.last.getLastMillisecond() + 1.0D), turnOffAutoRange, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configure()
/*      */   {
/*  582 */     if (isAutoRange()) {
/*  583 */       autoAdjustRange();
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
/*      */ 
/*      */ 
/*      */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space)
/*      */   {
/*  604 */     if (space == null) {
/*  605 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/*  609 */     if (!isVisible()) {
/*  610 */       return space;
/*      */     }
/*      */     
/*      */ 
/*  614 */     double dimension = getFixedDimension();
/*  615 */     if (dimension > 0.0D) {
/*  616 */       space.ensureAtLeast(dimension, edge);
/*      */     }
/*      */     
/*      */ 
/*  620 */     Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
/*  621 */     double labelHeight = 0.0D;
/*  622 */     double labelWidth = 0.0D;
/*  623 */     double tickLabelBandsDimension = 0.0D;
/*      */     
/*  625 */     for (int i = 0; i < this.labelInfo.length; i++) {
/*  626 */       PeriodAxisLabelInfo info = this.labelInfo[i];
/*  627 */       FontMetrics fm = g2.getFontMetrics(info.getLabelFont());
/*  628 */       tickLabelBandsDimension += info.getPadding().extendHeight(fm.getHeight());
/*      */     }
/*      */     
/*      */ 
/*  632 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  633 */       labelHeight = labelEnclosure.getHeight();
/*  634 */       space.add(labelHeight + tickLabelBandsDimension, edge);
/*      */     }
/*  636 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  637 */       labelWidth = labelEnclosure.getWidth();
/*  638 */       space.add(labelWidth + tickLabelBandsDimension, edge);
/*      */     }
/*      */     
/*      */ 
/*  642 */     double tickMarkSpace = 0.0D;
/*  643 */     if (isTickMarksVisible()) {
/*  644 */       tickMarkSpace = getTickMarkOutsideLength();
/*      */     }
/*  646 */     if (this.minorTickMarksVisible) {
/*  647 */       tickMarkSpace = Math.max(tickMarkSpace, this.minorTickMarkOutsideLength);
/*      */     }
/*      */     
/*  650 */     space.add(tickMarkSpace, edge);
/*  651 */     return space;
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
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*      */   {
/*  672 */     AxisState axisState = new AxisState(cursor);
/*  673 */     if (isAxisLineVisible()) {
/*  674 */       drawAxisLine(g2, cursor, dataArea, edge);
/*      */     }
/*  676 */     if (isTickMarksVisible()) {
/*  677 */       drawTickMarks(g2, axisState, dataArea, edge);
/*      */     }
/*  679 */     if (isTickLabelsVisible()) {
/*  680 */       for (int band = 0; band < this.labelInfo.length; band++) {
/*  681 */         axisState = drawTickLabels(band, g2, axisState, dataArea, edge);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  687 */     axisState = drawLabel(getLabel(), g2, plotArea, dataArea, edge, axisState);
/*      */     
/*  689 */     return axisState;
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
/*      */   protected void drawTickMarks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  704 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  705 */       drawTickMarksHorizontal(g2, state, dataArea, edge);
/*      */     }
/*  707 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  708 */       drawTickMarksVertical(g2, state, dataArea, edge);
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
/*      */   protected void drawTickMarksHorizontal(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  724 */     List ticks = new ArrayList();
/*  725 */     double x0 = dataArea.getX();
/*  726 */     double y0 = state.getCursor();
/*  727 */     double insideLength = getTickMarkInsideLength();
/*  728 */     double outsideLength = getTickMarkOutsideLength();
/*  729 */     RegularTimePeriod t = createInstance(this.majorTickTimePeriodClass, this.first.getStart(), getTimeZone(), this.locale);
/*      */     
/*  731 */     long t0 = t.getFirstMillisecond();
/*  732 */     Line2D inside = null;
/*  733 */     Line2D outside = null;
/*  734 */     long firstOnAxis = getFirst().getFirstMillisecond();
/*  735 */     long lastOnAxis = getLast().getLastMillisecond() + 1L;
/*  736 */     while (t0 <= lastOnAxis) {
/*  737 */       ticks.add(new NumberTick(new Double(t0), "", TextAnchor.CENTER, TextAnchor.CENTER, 0.0D));
/*      */       
/*  739 */       x0 = valueToJava2D(t0, dataArea, edge);
/*  740 */       if (edge == RectangleEdge.TOP) {
/*  741 */         inside = new Line2D.Double(x0, y0, x0, y0 + insideLength);
/*  742 */         outside = new Line2D.Double(x0, y0, x0, y0 - outsideLength);
/*      */       }
/*  744 */       else if (edge == RectangleEdge.BOTTOM) {
/*  745 */         inside = new Line2D.Double(x0, y0, x0, y0 - insideLength);
/*  746 */         outside = new Line2D.Double(x0, y0, x0, y0 + outsideLength);
/*      */       }
/*  748 */       if (t0 >= firstOnAxis) {
/*  749 */         g2.setPaint(getTickMarkPaint());
/*  750 */         g2.setStroke(getTickMarkStroke());
/*  751 */         g2.draw(inside);
/*  752 */         g2.draw(outside);
/*      */       }
/*      */       
/*  755 */       if (this.minorTickMarksVisible) {
/*  756 */         RegularTimePeriod tminor = createInstance(this.minorTickTimePeriodClass, new Date(t0), getTimeZone(), this.locale);
/*      */         
/*      */ 
/*  759 */         long tt0 = tminor.getFirstMillisecond();
/*      */         
/*  761 */         while ((tt0 < t.getLastMillisecond()) && (tt0 < lastOnAxis)) {
/*  762 */           double xx0 = valueToJava2D(tt0, dataArea, edge);
/*  763 */           if (edge == RectangleEdge.TOP) {
/*  764 */             inside = new Line2D.Double(xx0, y0, xx0, y0 + this.minorTickMarkInsideLength);
/*      */             
/*  766 */             outside = new Line2D.Double(xx0, y0, xx0, y0 - this.minorTickMarkOutsideLength);
/*      */ 
/*      */           }
/*  769 */           else if (edge == RectangleEdge.BOTTOM) {
/*  770 */             inside = new Line2D.Double(xx0, y0, xx0, y0 - this.minorTickMarkInsideLength);
/*      */             
/*  772 */             outside = new Line2D.Double(xx0, y0, xx0, y0 + this.minorTickMarkOutsideLength);
/*      */           }
/*      */           
/*  775 */           if (tt0 >= firstOnAxis) {
/*  776 */             g2.setPaint(this.minorTickMarkPaint);
/*  777 */             g2.setStroke(this.minorTickMarkStroke);
/*  778 */             g2.draw(inside);
/*  779 */             g2.draw(outside);
/*      */           }
/*  781 */           tminor = tminor.next();
/*  782 */           tminor.peg(this.calendar);
/*  783 */           tt0 = tminor.getFirstMillisecond();
/*      */         }
/*      */       }
/*  786 */       t = t.next();
/*  787 */       t.peg(this.calendar);
/*  788 */       t0 = t.getFirstMillisecond();
/*      */     }
/*  790 */     if (edge == RectangleEdge.TOP) {
/*  791 */       state.cursorUp(Math.max(outsideLength, this.minorTickMarkOutsideLength));
/*      */ 
/*      */     }
/*  794 */     else if (edge == RectangleEdge.BOTTOM) {
/*  795 */       state.cursorDown(Math.max(outsideLength, this.minorTickMarkOutsideLength));
/*      */     }
/*      */     
/*  798 */     state.setTicks(ticks);
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
/*      */   protected void drawTickMarksVertical(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AxisState drawTickLabels(int band, Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  831 */     double delta1 = 0.0D;
/*  832 */     FontMetrics fm = g2.getFontMetrics(this.labelInfo[band].getLabelFont());
/*  833 */     if (edge == RectangleEdge.BOTTOM) {
/*  834 */       delta1 = this.labelInfo[band].getPadding().calculateTopOutset(fm.getHeight());
/*      */ 
/*      */     }
/*  837 */     else if (edge == RectangleEdge.TOP) {
/*  838 */       delta1 = this.labelInfo[band].getPadding().calculateBottomOutset(fm.getHeight());
/*      */     }
/*      */     
/*  841 */     state.moveCursor(delta1, edge);
/*  842 */     long axisMin = this.first.getFirstMillisecond();
/*  843 */     long axisMax = this.last.getLastMillisecond();
/*  844 */     g2.setFont(this.labelInfo[band].getLabelFont());
/*  845 */     g2.setPaint(this.labelInfo[band].getLabelPaint());
/*      */     
/*      */ 
/*  848 */     RegularTimePeriod p1 = this.labelInfo[band].createInstance(new Date(axisMin), this.timeZone, this.locale);
/*      */     
/*  850 */     RegularTimePeriod p2 = this.labelInfo[band].createInstance(new Date(axisMax), this.timeZone, this.locale);
/*      */     
/*  852 */     String label1 = this.labelInfo[band].getDateFormat().format(new Date(p1.getMiddleMillisecond()));
/*      */     
/*  854 */     String label2 = this.labelInfo[band].getDateFormat().format(new Date(p2.getMiddleMillisecond()));
/*      */     
/*  856 */     Rectangle2D b1 = TextUtilities.getTextBounds(label1, g2, g2.getFontMetrics());
/*      */     
/*  858 */     Rectangle2D b2 = TextUtilities.getTextBounds(label2, g2, g2.getFontMetrics());
/*      */     
/*  860 */     double w = Math.max(b1.getWidth(), b2.getWidth());
/*  861 */     long ww = Math.round(java2DToValue(dataArea.getX() + w + 5.0D, dataArea, edge));
/*      */     
/*  863 */     if (isInverted()) {
/*  864 */       ww = axisMax - ww;
/*      */     }
/*      */     else {
/*  867 */       ww -= axisMin;
/*      */     }
/*  869 */     long length = p1.getLastMillisecond() - p1.getFirstMillisecond();
/*      */     
/*  871 */     int periods = (int)(ww / length) + 1;
/*      */     
/*  873 */     RegularTimePeriod p = this.labelInfo[band].createInstance(new Date(axisMin), this.timeZone, this.locale);
/*      */     
/*  875 */     Rectangle2D b = null;
/*  876 */     long lastXX = 0L;
/*  877 */     float y = (float)state.getCursor();
/*  878 */     TextAnchor anchor = TextAnchor.TOP_CENTER;
/*  879 */     float yDelta = (float)b1.getHeight();
/*  880 */     if (edge == RectangleEdge.TOP) {
/*  881 */       anchor = TextAnchor.BOTTOM_CENTER;
/*  882 */       yDelta = -yDelta;
/*      */     }
/*  884 */     while (p.getFirstMillisecond() <= axisMax) {
/*  885 */       float x = (float)valueToJava2D(p.getMiddleMillisecond(), dataArea, edge);
/*      */       
/*  887 */       DateFormat df = this.labelInfo[band].getDateFormat();
/*  888 */       String label = df.format(new Date(p.getMiddleMillisecond()));
/*  889 */       long first = p.getFirstMillisecond();
/*  890 */       long last = p.getLastMillisecond();
/*  891 */       if (last > axisMax)
/*      */       {
/*      */ 
/*  894 */         Rectangle2D bb = TextUtilities.getTextBounds(label, g2, g2.getFontMetrics());
/*      */         
/*  896 */         if (x + bb.getWidth() / 2.0D > dataArea.getMaxX()) {
/*  897 */           float xstart = (float)valueToJava2D(Math.max(first, axisMin), dataArea, edge);
/*      */           
/*  899 */           if (bb.getWidth() < dataArea.getMaxX() - xstart) {
/*  900 */             x = ((float)dataArea.getMaxX() + xstart) / 2.0F;
/*      */           }
/*      */           else {
/*  903 */             label = null;
/*      */           }
/*      */         }
/*      */       }
/*  907 */       if (first < axisMin)
/*      */       {
/*      */ 
/*  910 */         Rectangle2D bb = TextUtilities.getTextBounds(label, g2, g2.getFontMetrics());
/*      */         
/*  912 */         if (x - bb.getWidth() / 2.0D < dataArea.getX()) {
/*  913 */           float xlast = (float)valueToJava2D(Math.min(last, axisMax), dataArea, edge);
/*      */           
/*  915 */           if (bb.getWidth() < xlast - dataArea.getX()) {
/*  916 */             x = (xlast + (float)dataArea.getX()) / 2.0F;
/*      */           }
/*      */           else {
/*  919 */             label = null;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  924 */       if (label != null) {
/*  925 */         g2.setPaint(this.labelInfo[band].getLabelPaint());
/*  926 */         b = TextUtilities.drawAlignedString(label, g2, x, y, anchor);
/*      */       }
/*  928 */       if ((lastXX > 0L) && 
/*  929 */         (this.labelInfo[band].getDrawDividers())) {
/*  930 */         long nextXX = p.getFirstMillisecond();
/*  931 */         long mid = (lastXX + nextXX) / 2L;
/*  932 */         float mid2d = (float)valueToJava2D(mid, dataArea, edge);
/*  933 */         g2.setStroke(this.labelInfo[band].getDividerStroke());
/*  934 */         g2.setPaint(this.labelInfo[band].getDividerPaint());
/*  935 */         g2.draw(new Line2D.Float(mid2d, y, mid2d, y + yDelta));
/*      */       }
/*      */       
/*  938 */       lastXX = last;
/*  939 */       for (int i = 0; i < periods; i++) {
/*  940 */         p = p.next();
/*      */       }
/*  942 */       p.peg(this.calendar);
/*      */     }
/*  944 */     double used = 0.0D;
/*  945 */     if (b != null) {
/*  946 */       used = b.getHeight();
/*      */       
/*  948 */       if (edge == RectangleEdge.BOTTOM) {
/*  949 */         used += this.labelInfo[band].getPadding().calculateBottomOutset(fm.getHeight());
/*      */ 
/*      */       }
/*  952 */       else if (edge == RectangleEdge.TOP) {
/*  953 */         used += this.labelInfo[band].getPadding().calculateTopOutset(fm.getHeight());
/*      */       }
/*      */     }
/*      */     
/*  957 */     state.moveCursor(used, edge);
/*  958 */     return state;
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
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  974 */     return Collections.EMPTY_LIST;
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
/*      */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  992 */     double result = NaN.0D;
/*  993 */     double axisMin = this.first.getFirstMillisecond();
/*  994 */     double axisMax = this.last.getLastMillisecond();
/*  995 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  996 */       double minX = area.getX();
/*  997 */       double maxX = area.getMaxX();
/*  998 */       if (isInverted()) {
/*  999 */         result = maxX + (value - axisMin) / (axisMax - axisMin) * (minX - maxX);
/*      */       }
/*      */       else
/*      */       {
/* 1003 */         result = minX + (value - axisMin) / (axisMax - axisMin) * (maxX - minX);
/*      */       }
/*      */       
/*      */     }
/* 1007 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1008 */       double minY = area.getMinY();
/* 1009 */       double maxY = area.getMaxY();
/* 1010 */       if (isInverted()) {
/* 1011 */         result = minY + (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       }
/*      */       else
/*      */       {
/* 1015 */         result = maxY - (value - axisMin) / (axisMax - axisMin) * (maxY - minY);
/*      */       }
/*      */     }
/*      */     
/* 1019 */     return result;
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
/*      */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge)
/*      */   {
/* 1036 */     double result = NaN.0D;
/* 1037 */     double min = 0.0D;
/* 1038 */     double max = 0.0D;
/* 1039 */     double axisMin = this.first.getFirstMillisecond();
/* 1040 */     double axisMax = this.last.getLastMillisecond();
/* 1041 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1042 */       min = area.getX();
/* 1043 */       max = area.getMaxX();
/*      */     }
/* 1045 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1046 */       min = area.getMaxY();
/* 1047 */       max = area.getY();
/*      */     }
/* 1049 */     if (isInverted()) {
/* 1050 */       result = axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     }
/*      */     else
/*      */     {
/* 1054 */       result = axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     }
/*      */     
/* 1057 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void autoAdjustRange()
/*      */   {
/* 1065 */     Plot plot = getPlot();
/* 1066 */     if (plot == null) {
/* 1067 */       return;
/*      */     }
/*      */     
/* 1070 */     if ((plot instanceof ValueAxisPlot)) {
/* 1071 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*      */       
/* 1073 */       Range r = vap.getDataRange(this);
/* 1074 */       if (r == null) {
/* 1075 */         r = getDefaultAutoRange();
/*      */       }
/*      */       
/* 1078 */       long upper = Math.round(r.getUpperBound());
/* 1079 */       long lower = Math.round(r.getLowerBound());
/* 1080 */       this.first = createInstance(this.autoRangeTimePeriodClass, new Date(lower), this.timeZone, this.locale);
/*      */       
/* 1082 */       this.last = createInstance(this.autoRangeTimePeriodClass, new Date(upper), this.timeZone, this.locale);
/*      */       
/* 1084 */       setRange(r, false, false);
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
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1097 */     if (obj == this) {
/* 1098 */       return true;
/*      */     }
/* 1100 */     if (!(obj instanceof PeriodAxis)) {
/* 1101 */       return false;
/*      */     }
/* 1103 */     PeriodAxis that = (PeriodAxis)obj;
/* 1104 */     if (!this.first.equals(that.first)) {
/* 1105 */       return false;
/*      */     }
/* 1107 */     if (!this.last.equals(that.last)) {
/* 1108 */       return false;
/*      */     }
/* 1110 */     if (!this.timeZone.equals(that.timeZone)) {
/* 1111 */       return false;
/*      */     }
/* 1113 */     if (!this.locale.equals(that.locale)) {
/* 1114 */       return false;
/*      */     }
/* 1116 */     if (!this.autoRangeTimePeriodClass.equals(that.autoRangeTimePeriodClass))
/*      */     {
/* 1118 */       return false;
/*      */     }
/* 1120 */     if (isMinorTickMarksVisible() != that.isMinorTickMarksVisible()) {
/* 1121 */       return false;
/*      */     }
/* 1123 */     if (!this.majorTickTimePeriodClass.equals(that.majorTickTimePeriodClass))
/*      */     {
/* 1125 */       return false;
/*      */     }
/* 1127 */     if (!this.minorTickTimePeriodClass.equals(that.minorTickTimePeriodClass))
/*      */     {
/* 1129 */       return false;
/*      */     }
/* 1131 */     if (!this.minorTickMarkPaint.equals(that.minorTickMarkPaint)) {
/* 1132 */       return false;
/*      */     }
/* 1134 */     if (!this.minorTickMarkStroke.equals(that.minorTickMarkStroke)) {
/* 1135 */       return false;
/*      */     }
/* 1137 */     if (!Arrays.equals(this.labelInfo, that.labelInfo)) {
/* 1138 */       return false;
/*      */     }
/* 1140 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1149 */     if (getLabel() != null) {
/* 1150 */       return getLabel().hashCode();
/*      */     }
/*      */     
/* 1153 */     return 0;
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
/* 1166 */     PeriodAxis clone = (PeriodAxis)super.clone();
/* 1167 */     clone.timeZone = ((TimeZone)this.timeZone.clone());
/* 1168 */     clone.labelInfo = new PeriodAxisLabelInfo[this.labelInfo.length];
/* 1169 */     for (int i = 0; i < this.labelInfo.length; i++) {
/* 1170 */       clone.labelInfo[i] = this.labelInfo[i];
/*      */     }
/*      */     
/* 1173 */     return clone;
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
/*      */   private RegularTimePeriod createInstance(Class periodClass, Date millisecond, TimeZone zone, Locale locale)
/*      */   {
/* 1190 */     RegularTimePeriod result = null;
/*      */     try {
/* 1192 */       Constructor c = periodClass.getDeclaredConstructor(new Class[] { Date.class, TimeZone.class, Locale.class });
/*      */       
/* 1194 */       result = (RegularTimePeriod)c.newInstance(new Object[] { millisecond, zone, locale });
/*      */     }
/*      */     catch (Exception e)
/*      */     {
/*      */       try {
/* 1199 */         Constructor c = periodClass.getDeclaredConstructor(new Class[] { Date.class });
/*      */         
/* 1201 */         result = (RegularTimePeriod)c.newInstance(new Object[] { millisecond });
/*      */       }
/*      */       catch (Exception e2) {}
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1208 */     return result;
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
/* 1219 */     stream.defaultWriteObject();
/* 1220 */     SerialUtilities.writeStroke(this.minorTickMarkStroke, stream);
/* 1221 */     SerialUtilities.writePaint(this.minorTickMarkPaint, stream);
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
/* 1234 */     stream.defaultReadObject();
/* 1235 */     this.minorTickMarkStroke = SerialUtilities.readStroke(stream);
/* 1236 */     this.minorTickMarkPaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/PeriodAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */