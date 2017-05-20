/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.Serializable;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueAxisPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.RangeType;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class NumberAxis
/*      */   extends ValueAxis
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 2805933088476185789L;
/*      */   public static final boolean DEFAULT_AUTO_RANGE_INCLUDES_ZERO = true;
/*      */   public static final boolean DEFAULT_AUTO_RANGE_STICKY_ZERO = true;
/*  150 */   public static final NumberTickUnit DEFAULT_TICK_UNIT = new NumberTickUnit(1.0D, new DecimalFormat("0"));
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean DEFAULT_VERTICAL_TICK_LABELS = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private RangeType rangeType;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean autoRangeIncludesZero;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean autoRangeStickyZero;
/*      */   
/*      */ 
/*      */ 
/*      */   private NumberTickUnit tickUnit;
/*      */   
/*      */ 
/*      */ 
/*      */   private NumberFormat numberFormatOverride;
/*      */   
/*      */ 
/*      */ 
/*      */   private MarkerAxisBand markerBand;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public NumberAxis()
/*      */   {
/*  189 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public NumberAxis(String label)
/*      */   {
/*  198 */     super(label, createStandardTickUnits());
/*  199 */     this.rangeType = RangeType.FULL;
/*  200 */     this.autoRangeIncludesZero = true;
/*  201 */     this.autoRangeStickyZero = true;
/*  202 */     this.tickUnit = DEFAULT_TICK_UNIT;
/*  203 */     this.numberFormatOverride = null;
/*  204 */     this.markerBand = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RangeType getRangeType()
/*      */   {
/*  215 */     return this.rangeType;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeType(RangeType rangeType)
/*      */   {
/*  226 */     if (rangeType == null) {
/*  227 */       throw new IllegalArgumentException("Null 'rangeType' argument.");
/*      */     }
/*  229 */     this.rangeType = rangeType;
/*  230 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoRangeIncludesZero()
/*      */   {
/*  240 */     return this.autoRangeIncludesZero;
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
/*      */   public void setAutoRangeIncludesZero(boolean flag)
/*      */   {
/*  257 */     if (this.autoRangeIncludesZero != flag) {
/*  258 */       this.autoRangeIncludesZero = flag;
/*  259 */       if (isAutoRange()) {
/*  260 */         autoAdjustRange();
/*      */       }
/*  262 */       notifyListeners(new AxisChangeEvent(this));
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
/*      */   public boolean getAutoRangeStickyZero()
/*      */   {
/*  275 */     return this.autoRangeStickyZero;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoRangeStickyZero(boolean flag)
/*      */   {
/*  287 */     if (this.autoRangeStickyZero != flag) {
/*  288 */       this.autoRangeStickyZero = flag;
/*  289 */       if (isAutoRange()) {
/*  290 */         autoAdjustRange();
/*      */       }
/*  292 */       notifyListeners(new AxisChangeEvent(this));
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
/*      */   public NumberTickUnit getTickUnit()
/*      */   {
/*  310 */     return this.tickUnit;
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
/*      */   public void setTickUnit(NumberTickUnit unit)
/*      */   {
/*  327 */     setTickUnit(unit, true, true);
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
/*      */   public void setTickUnit(NumberTickUnit unit, boolean notify, boolean turnOffAutoSelect)
/*      */   {
/*  344 */     if (unit == null) {
/*  345 */       throw new IllegalArgumentException("Null 'unit' argument.");
/*      */     }
/*  347 */     this.tickUnit = unit;
/*  348 */     if (turnOffAutoSelect) {
/*  349 */       setAutoTickUnitSelection(false, false);
/*      */     }
/*  351 */     if (notify) {
/*  352 */       notifyListeners(new AxisChangeEvent(this));
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
/*      */   public NumberFormat getNumberFormatOverride()
/*      */   {
/*  366 */     return this.numberFormatOverride;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNumberFormatOverride(NumberFormat formatter)
/*      */   {
/*  378 */     this.numberFormatOverride = formatter;
/*  379 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public MarkerAxisBand getMarkerBand()
/*      */   {
/*  390 */     return this.markerBand;
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
/*      */   public void setMarkerBand(MarkerAxisBand band)
/*      */   {
/*  404 */     this.markerBand = band;
/*  405 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configure()
/*      */   {
/*  413 */     if (isAutoRange()) {
/*  414 */       autoAdjustRange();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void autoAdjustRange()
/*      */   {
/*  423 */     Plot plot = getPlot();
/*  424 */     if (plot == null) {
/*  425 */       return;
/*      */     }
/*      */     
/*  428 */     if ((plot instanceof ValueAxisPlot)) {
/*  429 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*      */       
/*  431 */       Range r = vap.getDataRange(this);
/*  432 */       if (r == null) {
/*  433 */         r = getDefaultAutoRange();
/*      */       }
/*      */       
/*  436 */       double upper = r.getUpperBound();
/*  437 */       double lower = r.getLowerBound();
/*  438 */       if (this.rangeType == RangeType.POSITIVE) {
/*  439 */         lower = Math.max(0.0D, lower);
/*  440 */         upper = Math.max(0.0D, upper);
/*      */       }
/*  442 */       else if (this.rangeType == RangeType.NEGATIVE) {
/*  443 */         lower = Math.min(0.0D, lower);
/*  444 */         upper = Math.min(0.0D, upper);
/*      */       }
/*      */       
/*  447 */       if (getAutoRangeIncludesZero()) {
/*  448 */         lower = Math.min(lower, 0.0D);
/*  449 */         upper = Math.max(upper, 0.0D);
/*      */       }
/*  451 */       double range = upper - lower;
/*      */       
/*      */ 
/*  454 */       double fixedAutoRange = getFixedAutoRange();
/*  455 */       if (fixedAutoRange > 0.0D) {
/*  456 */         lower = upper - fixedAutoRange;
/*      */       }
/*      */       else
/*      */       {
/*  460 */         double minRange = getAutoRangeMinimumSize();
/*  461 */         if (range < minRange) {
/*  462 */           double expand = (minRange - range) / 2.0D;
/*  463 */           upper += expand;
/*  464 */           lower -= expand;
/*  465 */           if (lower == upper) {
/*  466 */             double adjust = Math.abs(lower) / 10.0D;
/*  467 */             lower -= adjust;
/*  468 */             upper += adjust;
/*      */           }
/*  470 */           if (this.rangeType == RangeType.POSITIVE) {
/*  471 */             if (lower < 0.0D) {
/*  472 */               upper -= lower;
/*  473 */               lower = 0.0D;
/*      */             }
/*      */           }
/*  476 */           else if ((this.rangeType == RangeType.NEGATIVE) && 
/*  477 */             (upper > 0.0D)) {
/*  478 */             lower -= upper;
/*  479 */             upper = 0.0D;
/*      */           }
/*      */         }
/*      */         
/*      */ 
/*  484 */         if (getAutoRangeStickyZero()) {
/*  485 */           if (upper <= 0.0D) {
/*  486 */             upper = Math.min(0.0D, upper + getUpperMargin() * range);
/*      */           }
/*      */           else {
/*  489 */             upper += getUpperMargin() * range;
/*      */           }
/*  491 */           if (lower >= 0.0D) {
/*  492 */             lower = Math.max(0.0D, lower - getLowerMargin() * range);
/*      */           }
/*      */           else {
/*  495 */             lower -= getLowerMargin() * range;
/*      */           }
/*      */         }
/*      */         else {
/*  499 */           upper += getUpperMargin() * range;
/*  500 */           lower -= getLowerMargin() * range;
/*      */         }
/*      */       }
/*      */       
/*  504 */       setRange(new Range(lower, upper), false, false);
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
/*      */ 
/*      */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  526 */     Range range = getRange();
/*  527 */     double axisMin = range.getLowerBound();
/*  528 */     double axisMax = range.getUpperBound();
/*      */     
/*  530 */     double min = 0.0D;
/*  531 */     double max = 0.0D;
/*  532 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  533 */       min = area.getX();
/*  534 */       max = area.getMaxX();
/*      */     }
/*  536 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  537 */       max = area.getMinY();
/*  538 */       min = area.getMaxY();
/*      */     }
/*  540 */     if (isInverted()) {
/*  541 */       return max - (value - axisMin) / (axisMax - axisMin) * (max - min);
/*      */     }
/*      */     
/*      */ 
/*  545 */     return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
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
/*      */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  566 */     Range range = getRange();
/*  567 */     double axisMin = range.getLowerBound();
/*  568 */     double axisMax = range.getUpperBound();
/*      */     
/*  570 */     double min = 0.0D;
/*  571 */     double max = 0.0D;
/*  572 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  573 */       min = area.getX();
/*  574 */       max = area.getMaxX();
/*      */     }
/*  576 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  577 */       min = area.getMaxY();
/*  578 */       max = area.getY();
/*      */     }
/*  580 */     if (isInverted()) {
/*  581 */       return axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*      */     }
/*      */     
/*      */ 
/*  585 */     return axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
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
/*      */   protected double calculateLowestVisibleTickValue()
/*      */   {
/*  600 */     double unit = getTickUnit().getSize();
/*  601 */     double index = Math.ceil(getRange().getLowerBound() / unit);
/*  602 */     return index * unit;
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
/*      */   protected double calculateHighestVisibleTickValue()
/*      */   {
/*  615 */     double unit = getTickUnit().getSize();
/*  616 */     double index = Math.floor(getRange().getUpperBound() / unit);
/*  617 */     return index * unit;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int calculateVisibleTickCount()
/*      */   {
/*  628 */     double unit = getTickUnit().getSize();
/*  629 */     Range range = getRange();
/*  630 */     return (int)(Math.floor(range.getUpperBound() / unit) - Math.ceil(range.getLowerBound() / unit) + 1.0D);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*      */   {
/*  655 */     AxisState state = null;
/*      */     
/*  657 */     if (!isVisible()) {
/*  658 */       state = new AxisState(cursor);
/*      */       
/*      */ 
/*  661 */       List ticks = refreshTicks(g2, state, dataArea, edge);
/*  662 */       state.setTicks(ticks);
/*  663 */       return state;
/*      */     }
/*      */     
/*      */ 
/*  667 */     state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  678 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/*  679 */     createAndAddEntity(cursor, state, dataArea, edge, plotState);
/*  680 */     return state;
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
/*      */   public static TickUnitSource createStandardTickUnits()
/*      */   {
/*  698 */     TickUnits units = new TickUnits();
/*  699 */     DecimalFormat df0 = new DecimalFormat("0.00000000");
/*  700 */     DecimalFormat df1 = new DecimalFormat("0.0000000");
/*  701 */     DecimalFormat df2 = new DecimalFormat("0.000000");
/*  702 */     DecimalFormat df3 = new DecimalFormat("0.00000");
/*  703 */     DecimalFormat df4 = new DecimalFormat("0.0000");
/*  704 */     DecimalFormat df5 = new DecimalFormat("0.000");
/*  705 */     DecimalFormat df6 = new DecimalFormat("0.00");
/*  706 */     DecimalFormat df7 = new DecimalFormat("0.0");
/*  707 */     DecimalFormat df8 = new DecimalFormat("#,##0");
/*  708 */     DecimalFormat df9 = new DecimalFormat("#,###,##0");
/*  709 */     DecimalFormat df10 = new DecimalFormat("#,###,###,##0");
/*      */     
/*      */ 
/*      */ 
/*  713 */     units.add(new NumberTickUnit(1.0E-7D, df1, 2));
/*  714 */     units.add(new NumberTickUnit(1.0E-6D, df2, 2));
/*  715 */     units.add(new NumberTickUnit(1.0E-5D, df3, 2));
/*  716 */     units.add(new NumberTickUnit(1.0E-4D, df4, 2));
/*  717 */     units.add(new NumberTickUnit(0.001D, df5, 2));
/*  718 */     units.add(new NumberTickUnit(0.01D, df6, 2));
/*  719 */     units.add(new NumberTickUnit(0.1D, df7, 2));
/*  720 */     units.add(new NumberTickUnit(1.0D, df8, 2));
/*  721 */     units.add(new NumberTickUnit(10.0D, df8, 2));
/*  722 */     units.add(new NumberTickUnit(100.0D, df8, 2));
/*  723 */     units.add(new NumberTickUnit(1000.0D, df8, 2));
/*  724 */     units.add(new NumberTickUnit(10000.0D, df8, 2));
/*  725 */     units.add(new NumberTickUnit(100000.0D, df8, 2));
/*  726 */     units.add(new NumberTickUnit(1000000.0D, df9, 2));
/*  727 */     units.add(new NumberTickUnit(1.0E7D, df9, 2));
/*  728 */     units.add(new NumberTickUnit(1.0E8D, df9, 2));
/*  729 */     units.add(new NumberTickUnit(1.0E9D, df10, 2));
/*  730 */     units.add(new NumberTickUnit(1.0E10D, df10, 2));
/*  731 */     units.add(new NumberTickUnit(1.0E11D, df10, 2));
/*      */     
/*  733 */     units.add(new NumberTickUnit(2.5E-7D, df0, 5));
/*  734 */     units.add(new NumberTickUnit(2.5E-6D, df1, 5));
/*  735 */     units.add(new NumberTickUnit(2.5E-5D, df2, 5));
/*  736 */     units.add(new NumberTickUnit(2.5E-4D, df3, 5));
/*  737 */     units.add(new NumberTickUnit(0.0025D, df4, 5));
/*  738 */     units.add(new NumberTickUnit(0.025D, df5, 5));
/*  739 */     units.add(new NumberTickUnit(0.25D, df6, 5));
/*  740 */     units.add(new NumberTickUnit(2.5D, df7, 5));
/*  741 */     units.add(new NumberTickUnit(25.0D, df8, 5));
/*  742 */     units.add(new NumberTickUnit(250.0D, df8, 5));
/*  743 */     units.add(new NumberTickUnit(2500.0D, df8, 5));
/*  744 */     units.add(new NumberTickUnit(25000.0D, df8, 5));
/*  745 */     units.add(new NumberTickUnit(250000.0D, df8, 5));
/*  746 */     units.add(new NumberTickUnit(2500000.0D, df9, 5));
/*  747 */     units.add(new NumberTickUnit(2.5E7D, df9, 5));
/*  748 */     units.add(new NumberTickUnit(2.5E8D, df9, 5));
/*  749 */     units.add(new NumberTickUnit(2.5E9D, df10, 5));
/*  750 */     units.add(new NumberTickUnit(2.5E10D, df10, 5));
/*  751 */     units.add(new NumberTickUnit(2.5E11D, df10, 5));
/*      */     
/*  753 */     units.add(new NumberTickUnit(5.0E-7D, df1, 5));
/*  754 */     units.add(new NumberTickUnit(5.0E-6D, df2, 5));
/*  755 */     units.add(new NumberTickUnit(5.0E-5D, df3, 5));
/*  756 */     units.add(new NumberTickUnit(5.0E-4D, df4, 5));
/*  757 */     units.add(new NumberTickUnit(0.005D, df5, 5));
/*  758 */     units.add(new NumberTickUnit(0.05D, df6, 5));
/*  759 */     units.add(new NumberTickUnit(0.5D, df7, 5));
/*  760 */     units.add(new NumberTickUnit(5.0D, df8, 5));
/*  761 */     units.add(new NumberTickUnit(50.0D, df8, 5));
/*  762 */     units.add(new NumberTickUnit(500.0D, df8, 5));
/*  763 */     units.add(new NumberTickUnit(5000.0D, df8, 5));
/*  764 */     units.add(new NumberTickUnit(50000.0D, df8, 5));
/*  765 */     units.add(new NumberTickUnit(500000.0D, df8, 5));
/*  766 */     units.add(new NumberTickUnit(5000000.0D, df9, 5));
/*  767 */     units.add(new NumberTickUnit(5.0E7D, df9, 5));
/*  768 */     units.add(new NumberTickUnit(5.0E8D, df9, 5));
/*  769 */     units.add(new NumberTickUnit(5.0E9D, df10, 5));
/*  770 */     units.add(new NumberTickUnit(5.0E10D, df10, 5));
/*  771 */     units.add(new NumberTickUnit(5.0E11D, df10, 5));
/*      */     
/*  773 */     return units;
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
/*      */   public static TickUnitSource createIntegerTickUnits()
/*      */   {
/*  786 */     TickUnits units = new TickUnits();
/*  787 */     DecimalFormat df0 = new DecimalFormat("0");
/*  788 */     DecimalFormat df1 = new DecimalFormat("#,##0");
/*  789 */     units.add(new NumberTickUnit(1.0D, df0, 2));
/*  790 */     units.add(new NumberTickUnit(2.0D, df0, 2));
/*  791 */     units.add(new NumberTickUnit(5.0D, df0, 5));
/*  792 */     units.add(new NumberTickUnit(10.0D, df0, 2));
/*  793 */     units.add(new NumberTickUnit(20.0D, df0, 2));
/*  794 */     units.add(new NumberTickUnit(50.0D, df0, 5));
/*  795 */     units.add(new NumberTickUnit(100.0D, df0, 2));
/*  796 */     units.add(new NumberTickUnit(200.0D, df0, 2));
/*  797 */     units.add(new NumberTickUnit(500.0D, df0, 5));
/*  798 */     units.add(new NumberTickUnit(1000.0D, df1, 2));
/*  799 */     units.add(new NumberTickUnit(2000.0D, df1, 2));
/*  800 */     units.add(new NumberTickUnit(5000.0D, df1, 5));
/*  801 */     units.add(new NumberTickUnit(10000.0D, df1, 2));
/*  802 */     units.add(new NumberTickUnit(20000.0D, df1, 2));
/*  803 */     units.add(new NumberTickUnit(50000.0D, df1, 5));
/*  804 */     units.add(new NumberTickUnit(100000.0D, df1, 2));
/*  805 */     units.add(new NumberTickUnit(200000.0D, df1, 2));
/*  806 */     units.add(new NumberTickUnit(500000.0D, df1, 5));
/*  807 */     units.add(new NumberTickUnit(1000000.0D, df1, 2));
/*  808 */     units.add(new NumberTickUnit(2000000.0D, df1, 2));
/*  809 */     units.add(new NumberTickUnit(5000000.0D, df1, 5));
/*  810 */     units.add(new NumberTickUnit(1.0E7D, df1, 2));
/*  811 */     units.add(new NumberTickUnit(2.0E7D, df1, 2));
/*  812 */     units.add(new NumberTickUnit(5.0E7D, df1, 5));
/*  813 */     units.add(new NumberTickUnit(1.0E8D, df1, 2));
/*  814 */     units.add(new NumberTickUnit(2.0E8D, df1, 2));
/*  815 */     units.add(new NumberTickUnit(5.0E8D, df1, 5));
/*  816 */     units.add(new NumberTickUnit(1.0E9D, df1, 2));
/*  817 */     units.add(new NumberTickUnit(2.0E9D, df1, 2));
/*  818 */     units.add(new NumberTickUnit(5.0E9D, df1, 5));
/*  819 */     units.add(new NumberTickUnit(1.0E10D, df1, 2));
/*  820 */     return units;
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
/*      */   public static TickUnitSource createStandardTickUnits(Locale locale)
/*      */   {
/*  840 */     TickUnits units = new TickUnits();
/*  841 */     NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
/*      */     
/*      */ 
/*  844 */     units.add(new NumberTickUnit(1.0E-7D, numberFormat, 2));
/*  845 */     units.add(new NumberTickUnit(1.0E-6D, numberFormat, 2));
/*  846 */     units.add(new NumberTickUnit(1.0E-5D, numberFormat, 2));
/*  847 */     units.add(new NumberTickUnit(1.0E-4D, numberFormat, 2));
/*  848 */     units.add(new NumberTickUnit(0.001D, numberFormat, 2));
/*  849 */     units.add(new NumberTickUnit(0.01D, numberFormat, 2));
/*  850 */     units.add(new NumberTickUnit(0.1D, numberFormat, 2));
/*  851 */     units.add(new NumberTickUnit(1.0D, numberFormat, 2));
/*  852 */     units.add(new NumberTickUnit(10.0D, numberFormat, 2));
/*  853 */     units.add(new NumberTickUnit(100.0D, numberFormat, 2));
/*  854 */     units.add(new NumberTickUnit(1000.0D, numberFormat, 2));
/*  855 */     units.add(new NumberTickUnit(10000.0D, numberFormat, 2));
/*  856 */     units.add(new NumberTickUnit(100000.0D, numberFormat, 2));
/*  857 */     units.add(new NumberTickUnit(1000000.0D, numberFormat, 2));
/*  858 */     units.add(new NumberTickUnit(1.0E7D, numberFormat, 2));
/*  859 */     units.add(new NumberTickUnit(1.0E8D, numberFormat, 2));
/*  860 */     units.add(new NumberTickUnit(1.0E9D, numberFormat, 2));
/*  861 */     units.add(new NumberTickUnit(1.0E10D, numberFormat, 2));
/*      */     
/*  863 */     units.add(new NumberTickUnit(2.5E-7D, numberFormat, 5));
/*  864 */     units.add(new NumberTickUnit(2.5E-6D, numberFormat, 5));
/*  865 */     units.add(new NumberTickUnit(2.5E-5D, numberFormat, 5));
/*  866 */     units.add(new NumberTickUnit(2.5E-4D, numberFormat, 5));
/*  867 */     units.add(new NumberTickUnit(0.0025D, numberFormat, 5));
/*  868 */     units.add(new NumberTickUnit(0.025D, numberFormat, 5));
/*  869 */     units.add(new NumberTickUnit(0.25D, numberFormat, 5));
/*  870 */     units.add(new NumberTickUnit(2.5D, numberFormat, 5));
/*  871 */     units.add(new NumberTickUnit(25.0D, numberFormat, 5));
/*  872 */     units.add(new NumberTickUnit(250.0D, numberFormat, 5));
/*  873 */     units.add(new NumberTickUnit(2500.0D, numberFormat, 5));
/*  874 */     units.add(new NumberTickUnit(25000.0D, numberFormat, 5));
/*  875 */     units.add(new NumberTickUnit(250000.0D, numberFormat, 5));
/*  876 */     units.add(new NumberTickUnit(2500000.0D, numberFormat, 5));
/*  877 */     units.add(new NumberTickUnit(2.5E7D, numberFormat, 5));
/*  878 */     units.add(new NumberTickUnit(2.5E8D, numberFormat, 5));
/*  879 */     units.add(new NumberTickUnit(2.5E9D, numberFormat, 5));
/*  880 */     units.add(new NumberTickUnit(2.5E10D, numberFormat, 5));
/*      */     
/*  882 */     units.add(new NumberTickUnit(5.0E-7D, numberFormat, 5));
/*  883 */     units.add(new NumberTickUnit(5.0E-6D, numberFormat, 5));
/*  884 */     units.add(new NumberTickUnit(5.0E-5D, numberFormat, 5));
/*  885 */     units.add(new NumberTickUnit(5.0E-4D, numberFormat, 5));
/*  886 */     units.add(new NumberTickUnit(0.005D, numberFormat, 5));
/*  887 */     units.add(new NumberTickUnit(0.05D, numberFormat, 5));
/*  888 */     units.add(new NumberTickUnit(0.5D, numberFormat, 5));
/*  889 */     units.add(new NumberTickUnit(5.0D, numberFormat, 5));
/*  890 */     units.add(new NumberTickUnit(50.0D, numberFormat, 5));
/*  891 */     units.add(new NumberTickUnit(500.0D, numberFormat, 5));
/*  892 */     units.add(new NumberTickUnit(5000.0D, numberFormat, 5));
/*  893 */     units.add(new NumberTickUnit(50000.0D, numberFormat, 5));
/*  894 */     units.add(new NumberTickUnit(500000.0D, numberFormat, 5));
/*  895 */     units.add(new NumberTickUnit(5000000.0D, numberFormat, 5));
/*  896 */     units.add(new NumberTickUnit(5.0E7D, numberFormat, 5));
/*  897 */     units.add(new NumberTickUnit(5.0E8D, numberFormat, 5));
/*  898 */     units.add(new NumberTickUnit(5.0E9D, numberFormat, 5));
/*  899 */     units.add(new NumberTickUnit(5.0E10D, numberFormat, 5));
/*      */     
/*  901 */     return units;
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
/*      */   public static TickUnitSource createIntegerTickUnits(Locale locale)
/*      */   {
/*  916 */     TickUnits units = new TickUnits();
/*  917 */     NumberFormat numberFormat = NumberFormat.getNumberInstance(locale);
/*  918 */     units.add(new NumberTickUnit(1.0D, numberFormat, 2));
/*  919 */     units.add(new NumberTickUnit(2.0D, numberFormat, 2));
/*  920 */     units.add(new NumberTickUnit(5.0D, numberFormat, 5));
/*  921 */     units.add(new NumberTickUnit(10.0D, numberFormat, 2));
/*  922 */     units.add(new NumberTickUnit(20.0D, numberFormat, 2));
/*  923 */     units.add(new NumberTickUnit(50.0D, numberFormat, 5));
/*  924 */     units.add(new NumberTickUnit(100.0D, numberFormat, 2));
/*  925 */     units.add(new NumberTickUnit(200.0D, numberFormat, 2));
/*  926 */     units.add(new NumberTickUnit(500.0D, numberFormat, 5));
/*  927 */     units.add(new NumberTickUnit(1000.0D, numberFormat, 2));
/*  928 */     units.add(new NumberTickUnit(2000.0D, numberFormat, 2));
/*  929 */     units.add(new NumberTickUnit(5000.0D, numberFormat, 5));
/*  930 */     units.add(new NumberTickUnit(10000.0D, numberFormat, 2));
/*  931 */     units.add(new NumberTickUnit(20000.0D, numberFormat, 2));
/*  932 */     units.add(new NumberTickUnit(50000.0D, numberFormat, 5));
/*  933 */     units.add(new NumberTickUnit(100000.0D, numberFormat, 2));
/*  934 */     units.add(new NumberTickUnit(200000.0D, numberFormat, 2));
/*  935 */     units.add(new NumberTickUnit(500000.0D, numberFormat, 5));
/*  936 */     units.add(new NumberTickUnit(1000000.0D, numberFormat, 2));
/*  937 */     units.add(new NumberTickUnit(2000000.0D, numberFormat, 2));
/*  938 */     units.add(new NumberTickUnit(5000000.0D, numberFormat, 5));
/*  939 */     units.add(new NumberTickUnit(1.0E7D, numberFormat, 2));
/*  940 */     units.add(new NumberTickUnit(2.0E7D, numberFormat, 2));
/*  941 */     units.add(new NumberTickUnit(5.0E7D, numberFormat, 5));
/*  942 */     units.add(new NumberTickUnit(1.0E8D, numberFormat, 2));
/*  943 */     units.add(new NumberTickUnit(2.0E8D, numberFormat, 2));
/*  944 */     units.add(new NumberTickUnit(5.0E8D, numberFormat, 5));
/*  945 */     units.add(new NumberTickUnit(1.0E9D, numberFormat, 2));
/*  946 */     units.add(new NumberTickUnit(2.0E9D, numberFormat, 2));
/*  947 */     units.add(new NumberTickUnit(5.0E9D, numberFormat, 5));
/*  948 */     units.add(new NumberTickUnit(1.0E10D, numberFormat, 2));
/*  949 */     return units;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double estimateMaximumTickLabelHeight(Graphics2D g2)
/*      */   {
/*  961 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/*  962 */     double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
/*      */     
/*  964 */     Font tickLabelFont = getTickLabelFont();
/*  965 */     FontRenderContext frc = g2.getFontRenderContext();
/*  966 */     result += tickLabelFont.getLineMetrics("123", frc).getHeight();
/*  967 */     return result;
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
/*      */   protected double estimateMaximumTickLabelWidth(Graphics2D g2, TickUnit unit)
/*      */   {
/*  987 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/*  988 */     double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
/*      */     
/*  990 */     if (isVerticalTickLabels())
/*      */     {
/*      */ 
/*  993 */       FontRenderContext frc = g2.getFontRenderContext();
/*  994 */       LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
/*  995 */       result += lm.getHeight();
/*      */     }
/*      */     else
/*      */     {
/*  999 */       FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/* 1000 */       Range range = getRange();
/* 1001 */       double lower = range.getLowerBound();
/* 1002 */       double upper = range.getUpperBound();
/* 1003 */       String lowerStr = "";
/* 1004 */       String upperStr = "";
/* 1005 */       NumberFormat formatter = getNumberFormatOverride();
/* 1006 */       if (formatter != null) {
/* 1007 */         lowerStr = formatter.format(lower);
/* 1008 */         upperStr = formatter.format(upper);
/*      */       }
/*      */       else {
/* 1011 */         lowerStr = unit.valueToString(lower);
/* 1012 */         upperStr = unit.valueToString(upper);
/*      */       }
/* 1014 */       double w1 = fm.stringWidth(lowerStr);
/* 1015 */       double w2 = fm.stringWidth(upperStr);
/* 1016 */       result += Math.max(w1, w2);
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
/*      */   protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1036 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1037 */       selectHorizontalAutoTickUnit(g2, dataArea, edge);
/*      */     }
/* 1039 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1040 */       selectVerticalAutoTickUnit(g2, dataArea, edge);
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
/*      */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1058 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*      */     
/*      */ 
/*      */ 
/* 1062 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1063 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 1064 */     double unit1Width = lengthToJava2D(unit1.getSize(), dataArea, edge);
/*      */     
/*      */ 
/* 1067 */     double guess = tickLabelWidth / unit1Width * unit1.getSize();
/*      */     
/* 1069 */     NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
/*      */     
/* 1071 */     double unit2Width = lengthToJava2D(unit2.getSize(), dataArea, edge);
/*      */     
/* 1073 */     tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
/* 1074 */     if (tickLabelWidth > unit2Width) {
/* 1075 */       unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2);
/*      */     }
/*      */     
/* 1078 */     setTickUnit(unit2, false, false);
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
/*      */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1095 */     double tickLabelHeight = estimateMaximumTickLabelHeight(g2);
/*      */     
/*      */ 
/* 1098 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 1099 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 1100 */     double unitHeight = lengthToJava2D(unit1.getSize(), dataArea, edge);
/*      */     
/*      */ 
/* 1103 */     double guess = tickLabelHeight / unitHeight * unit1.getSize();
/*      */     
/* 1105 */     NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
/*      */     
/* 1107 */     double unit2Height = lengthToJava2D(unit2.getSize(), dataArea, edge);
/*      */     
/* 1109 */     tickLabelHeight = estimateMaximumTickLabelHeight(g2);
/* 1110 */     if (tickLabelHeight > unit2Height) {
/* 1111 */       unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2);
/*      */     }
/*      */     
/* 1114 */     setTickUnit(unit2, false, false);
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
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1135 */     List result = new ArrayList();
/* 1136 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1137 */       result = refreshTicksHorizontal(g2, dataArea, edge);
/*      */     }
/* 1139 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1140 */       result = refreshTicksVertical(g2, dataArea, edge);
/*      */     }
/* 1142 */     return result;
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
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1159 */     List result = new ArrayList();
/*      */     
/* 1161 */     Font tickLabelFont = getTickLabelFont();
/* 1162 */     g2.setFont(tickLabelFont);
/*      */     
/* 1164 */     if (isAutoTickUnitSelection()) {
/* 1165 */       selectAutoTickUnit(g2, dataArea, edge);
/*      */     }
/*      */     
/* 1168 */     TickUnit tu = getTickUnit();
/* 1169 */     double size = tu.getSize();
/* 1170 */     int count = calculateVisibleTickCount();
/* 1171 */     double lowestTickValue = calculateLowestVisibleTickValue();
/*      */     
/* 1173 */     if (count <= 500) {
/* 1174 */       int minorTickSpaces = getMinorTickCount();
/* 1175 */       if (minorTickSpaces <= 0) {
/* 1176 */         minorTickSpaces = tu.getMinorTickCount();
/*      */       }
/* 1178 */       for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
/* 1179 */         double minorTickValue = lowestTickValue - size * minorTick / minorTickSpaces;
/*      */         
/* 1181 */         if (getRange().contains(minorTickValue)) {
/* 1182 */           result.add(new NumberTick(TickType.MINOR, minorTickValue, "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/* 1187 */       for (int i = 0; i < count; i++) {
/* 1188 */         double currentTickValue = lowestTickValue + i * size;
/*      */         
/* 1190 */         NumberFormat formatter = getNumberFormatOverride();
/* 1191 */         String tickLabel; String tickLabel; if (formatter != null) {
/* 1192 */           tickLabel = formatter.format(currentTickValue);
/*      */         }
/*      */         else {
/* 1195 */           tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */         }
/* 1197 */         TextAnchor anchor = null;
/* 1198 */         TextAnchor rotationAnchor = null;
/* 1199 */         double angle = 0.0D;
/* 1200 */         if (isVerticalTickLabels()) {
/* 1201 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1202 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/* 1203 */           if (edge == RectangleEdge.TOP) {
/* 1204 */             angle = 1.5707963267948966D;
/*      */           }
/*      */           else {
/* 1207 */             angle = -1.5707963267948966D;
/*      */           }
/*      */           
/*      */         }
/* 1211 */         else if (edge == RectangleEdge.TOP) {
/* 1212 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 1213 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*      */         }
/*      */         else {
/* 1216 */           anchor = TextAnchor.TOP_CENTER;
/* 1217 */           rotationAnchor = TextAnchor.TOP_CENTER;
/*      */         }
/*      */         
/*      */ 
/* 1221 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*      */         
/* 1223 */         result.add(tick);
/* 1224 */         double nextTickValue = lowestTickValue + (i + 1) * size;
/* 1225 */         for (int minorTick = 1; minorTick < minorTickSpaces; 
/* 1226 */             minorTick++) {
/* 1227 */           double minorTickValue = currentTickValue + (nextTickValue - currentTickValue) * minorTick / minorTickSpaces;
/*      */           
/*      */ 
/* 1230 */           if (getRange().contains(minorTickValue)) {
/* 1231 */             result.add(new NumberTick(TickType.MINOR, minorTickValue, "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1238 */     return result;
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
/*      */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1255 */     List result = new ArrayList();
/* 1256 */     result.clear();
/*      */     
/* 1258 */     Font tickLabelFont = getTickLabelFont();
/* 1259 */     g2.setFont(tickLabelFont);
/* 1260 */     if (isAutoTickUnitSelection()) {
/* 1261 */       selectAutoTickUnit(g2, dataArea, edge);
/*      */     }
/*      */     
/* 1264 */     TickUnit tu = getTickUnit();
/* 1265 */     double size = tu.getSize();
/* 1266 */     int count = calculateVisibleTickCount();
/* 1267 */     double lowestTickValue = calculateLowestVisibleTickValue();
/*      */     
/* 1269 */     if (count <= 500) {
/* 1270 */       int minorTickSpaces = getMinorTickCount();
/* 1271 */       if (minorTickSpaces <= 0) {
/* 1272 */         minorTickSpaces = tu.getMinorTickCount();
/*      */       }
/* 1274 */       for (int minorTick = 1; minorTick < minorTickSpaces; minorTick++) {
/* 1275 */         double minorTickValue = lowestTickValue - size * minorTick / minorTickSpaces;
/*      */         
/* 1277 */         if (getRange().contains(minorTickValue)) {
/* 1278 */           result.add(new NumberTick(TickType.MINOR, minorTickValue, "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1284 */       for (int i = 0; i < count; i++) {
/* 1285 */         double currentTickValue = lowestTickValue + i * size;
/*      */         
/* 1287 */         NumberFormat formatter = getNumberFormatOverride();
/* 1288 */         String tickLabel; String tickLabel; if (formatter != null) {
/* 1289 */           tickLabel = formatter.format(currentTickValue);
/*      */         }
/*      */         else {
/* 1292 */           tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */         }
/*      */         
/* 1295 */         TextAnchor anchor = null;
/* 1296 */         TextAnchor rotationAnchor = null;
/* 1297 */         double angle = 0.0D;
/* 1298 */         if (isVerticalTickLabels()) {
/* 1299 */           if (edge == RectangleEdge.LEFT) {
/* 1300 */             anchor = TextAnchor.BOTTOM_CENTER;
/* 1301 */             rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1302 */             angle = -1.5707963267948966D;
/*      */           }
/*      */           else {
/* 1305 */             anchor = TextAnchor.BOTTOM_CENTER;
/* 1306 */             rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 1307 */             angle = 1.5707963267948966D;
/*      */           }
/*      */           
/*      */         }
/* 1311 */         else if (edge == RectangleEdge.LEFT) {
/* 1312 */           anchor = TextAnchor.CENTER_RIGHT;
/* 1313 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/*      */         }
/*      */         else {
/* 1316 */           anchor = TextAnchor.CENTER_LEFT;
/* 1317 */           rotationAnchor = TextAnchor.CENTER_LEFT;
/*      */         }
/*      */         
/*      */ 
/* 1321 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*      */         
/* 1323 */         result.add(tick);
/*      */         
/* 1325 */         double nextTickValue = lowestTickValue + (i + 1) * size;
/* 1326 */         for (int minorTick = 1; minorTick < minorTickSpaces; 
/* 1327 */             minorTick++) {
/* 1328 */           double minorTickValue = currentTickValue + (nextTickValue - currentTickValue) * minorTick / minorTickSpaces;
/*      */           
/*      */ 
/* 1331 */           if (getRange().contains(minorTickValue)) {
/* 1332 */             result.add(new NumberTick(TickType.MINOR, minorTickValue, "", TextAnchor.TOP_CENTER, TextAnchor.CENTER, 0.0D));
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1339 */     return result;
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
/* 1352 */     NumberAxis clone = (NumberAxis)super.clone();
/* 1353 */     if (this.numberFormatOverride != null) {
/* 1354 */       clone.numberFormatOverride = ((NumberFormat)this.numberFormatOverride.clone());
/*      */     }
/*      */     
/* 1357 */     return clone;
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
/* 1368 */     if (obj == this) {
/* 1369 */       return true;
/*      */     }
/* 1371 */     if (!(obj instanceof NumberAxis)) {
/* 1372 */       return false;
/*      */     }
/* 1374 */     NumberAxis that = (NumberAxis)obj;
/* 1375 */     if (this.autoRangeIncludesZero != that.autoRangeIncludesZero) {
/* 1376 */       return false;
/*      */     }
/* 1378 */     if (this.autoRangeStickyZero != that.autoRangeStickyZero) {
/* 1379 */       return false;
/*      */     }
/* 1381 */     if (!ObjectUtilities.equal(this.tickUnit, that.tickUnit)) {
/* 1382 */       return false;
/*      */     }
/* 1384 */     if (!ObjectUtilities.equal(this.numberFormatOverride, that.numberFormatOverride))
/*      */     {
/* 1386 */       return false;
/*      */     }
/* 1388 */     if (!this.rangeType.equals(that.rangeType)) {
/* 1389 */       return false;
/*      */     }
/* 1391 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1400 */     if (getLabel() != null) {
/* 1401 */       return getLabel().hashCode();
/*      */     }
/*      */     
/* 1404 */     return 0;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/NumberAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */