/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.LineMetrics;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.ValueAxisPlot;
/*     */ import org.jfree.chart.util.LogFormat;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.RectangleInsets;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LogAxis
/*     */   extends ValueAxis
/*     */ {
/*  93 */   private double base = 10.0D;
/*     */   
/*     */ 
/*  96 */   private double baseLog = Math.log(10.0D);
/*     */   
/*     */ 
/*  99 */   private double smallestValue = 1.0E-100D;
/*     */   
/*     */ 
/*     */   private NumberTickUnit tickUnit;
/*     */   
/*     */ 
/*     */   private NumberFormat numberFormatOverride;
/*     */   
/*     */ 
/*     */ 
/*     */   public LogAxis()
/*     */   {
/* 111 */     this(null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public LogAxis(String label)
/*     */   {
/* 120 */     super(label, createLogTickUnits(Locale.getDefault()));
/* 121 */     setDefaultAutoRange(new Range(0.01D, 1.0D));
/* 122 */     this.tickUnit = new NumberTickUnit(1.0D, new DecimalFormat("0.#"), 9);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBase()
/*     */   {
/* 133 */     return this.base;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBase(double base)
/*     */   {
/* 145 */     if (base <= 1.0D) {
/* 146 */       throw new IllegalArgumentException("Requires 'base' > 1.0.");
/*     */     }
/* 148 */     this.base = base;
/* 149 */     this.baseLog = Math.log(base);
/* 150 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSmallestValue()
/*     */   {
/* 161 */     return this.smallestValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSmallestValue(double value)
/*     */   {
/* 173 */     if (value <= 0.0D) {
/* 174 */       throw new IllegalArgumentException("Requires 'value' > 0.0.");
/*     */     }
/* 176 */     this.smallestValue = value;
/* 177 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public NumberTickUnit getTickUnit()
/*     */   {
/* 188 */     return this.tickUnit;
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
/*     */   public void setTickUnit(NumberTickUnit unit)
/*     */   {
/* 204 */     setTickUnit(unit, true, true);
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
/*     */   public void setTickUnit(NumberTickUnit unit, boolean notify, boolean turnOffAutoSelect)
/*     */   {
/* 223 */     if (unit == null) {
/* 224 */       throw new IllegalArgumentException("Null 'unit' argument.");
/*     */     }
/* 226 */     this.tickUnit = unit;
/* 227 */     if (turnOffAutoSelect) {
/* 228 */       setAutoTickUnitSelection(false, false);
/*     */     }
/* 230 */     if (notify) {
/* 231 */       notifyListeners(new AxisChangeEvent(this));
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
/*     */   public NumberFormat getNumberFormatOverride()
/*     */   {
/* 245 */     return this.numberFormatOverride;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNumberFormatOverride(NumberFormat formatter)
/*     */   {
/* 257 */     this.numberFormatOverride = formatter;
/* 258 */     notifyListeners(new AxisChangeEvent(this));
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
/*     */   public double calculateLog(double value)
/*     */   {
/* 272 */     return Math.log(value) / this.baseLog;
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
/*     */   public double calculateValue(double log)
/*     */   {
/* 286 */     return Math.pow(this.base, log);
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
/*     */   public double java2DToValue(double java2DValue, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 302 */     Range range = getRange();
/* 303 */     double axisMin = calculateLog(range.getLowerBound());
/* 304 */     double axisMax = calculateLog(range.getUpperBound());
/*     */     
/* 306 */     double min = 0.0D;
/* 307 */     double max = 0.0D;
/* 308 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 309 */       min = area.getX();
/* 310 */       max = area.getMaxX();
/*     */     }
/* 312 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 313 */       min = area.getMaxY();
/* 314 */       max = area.getY();
/*     */     }
/* 316 */     double log = 0.0D;
/* 317 */     if (isInverted()) {
/* 318 */       log = axisMax - (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*     */     }
/*     */     else
/*     */     {
/* 322 */       log = axisMin + (java2DValue - min) / (max - min) * (axisMax - axisMin);
/*     */     }
/*     */     
/* 325 */     return calculateValue(log);
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
/*     */   public double valueToJava2D(double value, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 342 */     Range range = getRange();
/* 343 */     double axisMin = calculateLog(range.getLowerBound());
/* 344 */     double axisMax = calculateLog(range.getUpperBound());
/* 345 */     value = calculateLog(value);
/*     */     
/* 347 */     double min = 0.0D;
/* 348 */     double max = 0.0D;
/* 349 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 350 */       min = area.getX();
/* 351 */       max = area.getMaxX();
/*     */     }
/* 353 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 354 */       max = area.getMinY();
/* 355 */       min = area.getMaxY();
/*     */     }
/* 357 */     if (isInverted()) {
/* 358 */       return max - (value - axisMin) / (axisMax - axisMin) * (max - min);
/*     */     }
/*     */     
/*     */ 
/* 362 */     return min + (value - axisMin) / (axisMax - axisMin) * (max - min);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void configure()
/*     */   {
/* 372 */     if (isAutoRange()) {
/* 373 */       autoAdjustRange();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void autoAdjustRange()
/*     */   {
/* 382 */     Plot plot = getPlot();
/* 383 */     if (plot == null) {
/* 384 */       return;
/*     */     }
/*     */     
/* 387 */     if ((plot instanceof ValueAxisPlot)) {
/* 388 */       ValueAxisPlot vap = (ValueAxisPlot)plot;
/*     */       
/* 390 */       Range r = vap.getDataRange(this);
/* 391 */       if (r == null) {
/* 392 */         r = getDefaultAutoRange();
/*     */       }
/*     */       
/* 395 */       double upper = r.getUpperBound();
/* 396 */       double lower = Math.max(r.getLowerBound(), this.smallestValue);
/* 397 */       double range = upper - lower;
/*     */       
/*     */ 
/* 400 */       double fixedAutoRange = getFixedAutoRange();
/* 401 */       if (fixedAutoRange > 0.0D) {
/* 402 */         lower = Math.max(upper - fixedAutoRange, this.smallestValue);
/*     */       }
/*     */       else
/*     */       {
/* 406 */         double minRange = getAutoRangeMinimumSize();
/* 407 */         if (range < minRange) {
/* 408 */           double expand = (minRange - range) / 2.0D;
/* 409 */           upper += expand;
/* 410 */           lower -= expand;
/*     */         }
/*     */         
/*     */ 
/* 414 */         double logUpper = calculateLog(upper);
/* 415 */         double logLower = calculateLog(lower);
/* 416 */         double logRange = logUpper - logLower;
/* 417 */         logUpper += getUpperMargin() * logRange;
/* 418 */         logLower -= getLowerMargin() * logRange;
/* 419 */         upper = calculateValue(logUpper);
/* 420 */         lower = calculateValue(logLower);
/*     */       }
/*     */       
/* 423 */       setRange(new Range(lower, upper), false, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*     */   {
/* 446 */     AxisState state = null;
/*     */     
/* 448 */     if (!isVisible()) {
/* 449 */       state = new AxisState(cursor);
/*     */       
/*     */ 
/* 452 */       List ticks = refreshTicks(g2, state, dataArea, edge);
/* 453 */       state.setTicks(ticks);
/* 454 */       return state;
/*     */     }
/* 456 */     state = drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/* 457 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/* 458 */     createAndAddEntity(cursor, state, dataArea, edge, plotState);
/* 459 */     return state;
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
/*     */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 477 */     List result = new ArrayList();
/* 478 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 479 */       result = refreshTicksHorizontal(g2, dataArea, edge);
/*     */     }
/* 481 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 482 */       result = refreshTicksVertical(g2, dataArea, edge);
/*     */     }
/* 484 */     return result;
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
/*     */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 500 */     Range range = getRange();
/* 501 */     List ticks = new ArrayList();
/* 502 */     Font tickLabelFont = getTickLabelFont();
/* 503 */     g2.setFont(tickLabelFont);
/*     */     TextAnchor textAnchor;
/* 505 */     TextAnchor textAnchor; if (edge == RectangleEdge.TOP) {
/* 506 */       textAnchor = TextAnchor.BOTTOM_CENTER;
/*     */     }
/*     */     else {
/* 509 */       textAnchor = TextAnchor.TOP_CENTER;
/*     */     }
/*     */     
/* 512 */     if (isAutoTickUnitSelection()) {
/* 513 */       selectAutoTickUnit(g2, dataArea, edge);
/*     */     }
/* 515 */     int minorTickCount = this.tickUnit.getMinorTickCount();
/* 516 */     double start = Math.floor(calculateLog(getLowerBound()));
/* 517 */     double end = Math.ceil(calculateLog(getUpperBound()));
/* 518 */     double current = start;
/* 519 */     while (current <= end) {
/* 520 */       double v = calculateValue(current);
/* 521 */       if (range.contains(v)) {
/* 522 */         ticks.add(new NumberTick(TickType.MAJOR, v, createTickLabel(v), textAnchor, TextAnchor.CENTER, 0.0D));
/*     */       }
/*     */       
/*     */ 
/* 526 */       double next = Math.pow(this.base, current + this.tickUnit.getSize());
/*     */       
/* 528 */       for (int i = 1; i < minorTickCount; i++) {
/* 529 */         double minorV = v + i * ((next - v) / minorTickCount);
/* 530 */         if (range.contains(minorV)) {
/* 531 */           ticks.add(new NumberTick(TickType.MINOR, minorV, "", textAnchor, TextAnchor.CENTER, 0.0D));
/*     */         }
/*     */       }
/*     */       
/* 535 */       current += this.tickUnit.getSize();
/*     */     }
/* 537 */     return ticks;
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
/*     */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 552 */     Range range = getRange();
/* 553 */     List ticks = new ArrayList();
/* 554 */     Font tickLabelFont = getTickLabelFont();
/* 555 */     g2.setFont(tickLabelFont);
/*     */     TextAnchor textAnchor;
/* 557 */     TextAnchor textAnchor; if (edge == RectangleEdge.RIGHT) {
/* 558 */       textAnchor = TextAnchor.CENTER_LEFT;
/*     */     }
/*     */     else {
/* 561 */       textAnchor = TextAnchor.CENTER_RIGHT;
/*     */     }
/*     */     
/* 564 */     if (isAutoTickUnitSelection()) {
/* 565 */       selectAutoTickUnit(g2, dataArea, edge);
/*     */     }
/* 567 */     int minorTickCount = this.tickUnit.getMinorTickCount();
/* 568 */     double start = Math.floor(calculateLog(getLowerBound()));
/* 569 */     double end = Math.ceil(calculateLog(getUpperBound()));
/* 570 */     double current = start;
/* 571 */     while (current <= end) {
/* 572 */       double v = calculateValue(current);
/* 573 */       if (range.contains(v)) {
/* 574 */         ticks.add(new NumberTick(TickType.MAJOR, v, createTickLabel(v), textAnchor, TextAnchor.CENTER, 0.0D));
/*     */       }
/*     */       
/*     */ 
/* 578 */       double next = Math.pow(this.base, current + this.tickUnit.getSize());
/*     */       
/* 580 */       for (int i = 1; i < minorTickCount; i++) {
/* 581 */         double minorV = v + i * ((next - v) / minorTickCount);
/* 582 */         if (range.contains(minorV)) {
/* 583 */           ticks.add(new NumberTick(TickType.MINOR, minorV, "", textAnchor, TextAnchor.CENTER, 0.0D));
/*     */         }
/*     */       }
/*     */       
/* 587 */       current += this.tickUnit.getSize();
/*     */     }
/* 589 */     return ticks;
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
/*     */   protected void selectAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 606 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 607 */       selectHorizontalAutoTickUnit(g2, dataArea, edge);
/*     */     }
/* 609 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 610 */       selectVerticalAutoTickUnit(g2, dataArea, edge);
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
/*     */ 
/*     */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 629 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*     */     
/*     */ 
/*     */ 
/* 633 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 634 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 635 */     double unit1Width = exponentLengthToJava2D(unit1.getSize(), dataArea, edge);
/*     */     
/*     */ 
/*     */ 
/* 639 */     double guess = tickLabelWidth / unit1Width * unit1.getSize();
/*     */     
/* 641 */     NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
/*     */     
/* 643 */     double unit2Width = exponentLengthToJava2D(unit2.getSize(), dataArea, edge);
/*     */     
/*     */ 
/* 646 */     tickLabelWidth = estimateMaximumTickLabelWidth(g2, unit2);
/* 647 */     if (tickLabelWidth > unit2Width) {
/* 648 */       unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2);
/*     */     }
/*     */     
/* 651 */     setTickUnit(unit2, false, false);
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
/*     */   public double exponentLengthToJava2D(double length, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 669 */     double one = valueToJava2D(calculateValue(1.0D), area, edge);
/* 670 */     double l = valueToJava2D(calculateValue(length + 1.0D), area, edge);
/* 671 */     return Math.abs(l - one);
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
/*     */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 689 */     double tickLabelHeight = estimateMaximumTickLabelHeight(g2);
/*     */     
/*     */ 
/* 692 */     TickUnitSource tickUnits = getStandardTickUnits();
/* 693 */     TickUnit unit1 = tickUnits.getCeilingTickUnit(getTickUnit());
/* 694 */     double unitHeight = exponentLengthToJava2D(unit1.getSize(), dataArea, edge);
/*     */     
/*     */ 
/*     */ 
/* 698 */     double guess = tickLabelHeight / unitHeight * unit1.getSize();
/*     */     
/* 700 */     NumberTickUnit unit2 = (NumberTickUnit)tickUnits.getCeilingTickUnit(guess);
/*     */     
/* 702 */     double unit2Height = exponentLengthToJava2D(unit2.getSize(), dataArea, edge);
/*     */     
/*     */ 
/* 705 */     tickLabelHeight = estimateMaximumTickLabelHeight(g2);
/* 706 */     if (tickLabelHeight > unit2Height) {
/* 707 */       unit2 = (NumberTickUnit)tickUnits.getLargerTickUnit(unit2);
/*     */     }
/*     */     
/* 710 */     setTickUnit(unit2, false, false);
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
/*     */   protected double estimateMaximumTickLabelHeight(Graphics2D g2)
/*     */   {
/* 725 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/* 726 */     double result = tickLabelInsets.getTop() + tickLabelInsets.getBottom();
/*     */     
/* 728 */     Font tickLabelFont = getTickLabelFont();
/* 729 */     FontRenderContext frc = g2.getFontRenderContext();
/* 730 */     result += tickLabelFont.getLineMetrics("123", frc).getHeight();
/* 731 */     return result;
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
/*     */   protected double estimateMaximumTickLabelWidth(Graphics2D g2, TickUnit unit)
/*     */   {
/* 753 */     RectangleInsets tickLabelInsets = getTickLabelInsets();
/* 754 */     double result = tickLabelInsets.getLeft() + tickLabelInsets.getRight();
/*     */     
/* 756 */     if (isVerticalTickLabels())
/*     */     {
/*     */ 
/* 759 */       FontRenderContext frc = g2.getFontRenderContext();
/* 760 */       LineMetrics lm = getTickLabelFont().getLineMetrics("0", frc);
/* 761 */       result += lm.getHeight();
/*     */     }
/*     */     else
/*     */     {
/* 765 */       FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/* 766 */       Range range = getRange();
/* 767 */       double lower = range.getLowerBound();
/* 768 */       double upper = range.getUpperBound();
/* 769 */       String lowerStr = "";
/* 770 */       String upperStr = "";
/* 771 */       NumberFormat formatter = getNumberFormatOverride();
/* 772 */       if (formatter != null) {
/* 773 */         lowerStr = formatter.format(lower);
/* 774 */         upperStr = formatter.format(upper);
/*     */       }
/*     */       else {
/* 777 */         lowerStr = unit.valueToString(lower);
/* 778 */         upperStr = unit.valueToString(upper);
/*     */       }
/* 780 */       double w1 = fm.stringWidth(lowerStr);
/* 781 */       double w2 = fm.stringWidth(upperStr);
/* 782 */       result += Math.max(w1, w2);
/*     */     }
/*     */     
/* 785 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void zoomRange(double lowerPercent, double upperPercent)
/*     */   {
/* 796 */     Range range = getRange();
/* 797 */     double start = range.getLowerBound();
/* 798 */     double end = range.getUpperBound();
/* 799 */     double log1 = calculateLog(start);
/* 800 */     double log2 = calculateLog(end);
/* 801 */     double length = log2 - log1;
/* 802 */     Range adjusted = null;
/* 803 */     if (isInverted()) {
/* 804 */       double logA = log1 + length * (1.0D - upperPercent);
/* 805 */       double logB = log1 + length * (1.0D - lowerPercent);
/* 806 */       adjusted = new Range(calculateValue(logA), calculateValue(logB));
/*     */     }
/*     */     else {
/* 809 */       double logA = log1 + length * lowerPercent;
/* 810 */       double logB = log1 + length * upperPercent;
/* 811 */       adjusted = new Range(calculateValue(logA), calculateValue(logB));
/*     */     }
/* 813 */     setRange(adjusted);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void pan(double percent)
/*     */   {
/* 824 */     Range range = getRange();
/* 825 */     double lower = range.getLowerBound();
/* 826 */     double upper = range.getUpperBound();
/* 827 */     double log1 = calculateLog(lower);
/* 828 */     double log2 = calculateLog(upper);
/* 829 */     double length = log2 - log1;
/* 830 */     double adj = length * percent;
/* 831 */     log1 += adj;
/* 832 */     log2 += adj;
/* 833 */     setRange(calculateValue(log1), calculateValue(log2));
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
/*     */   protected String createTickLabel(double value)
/*     */   {
/* 847 */     if (this.numberFormatOverride != null) {
/* 848 */       return this.numberFormatOverride.format(value);
/*     */     }
/*     */     
/* 851 */     return this.tickUnit.valueToString(value);
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
/* 863 */     if (obj == this) {
/* 864 */       return true;
/*     */     }
/* 866 */     if (!(obj instanceof LogAxis)) {
/* 867 */       return false;
/*     */     }
/* 869 */     LogAxis that = (LogAxis)obj;
/* 870 */     if (this.base != that.base) {
/* 871 */       return false;
/*     */     }
/* 873 */     if (this.smallestValue != that.smallestValue) {
/* 874 */       return false;
/*     */     }
/* 876 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 885 */     int result = 193;
/* 886 */     long temp = Double.doubleToLongBits(this.base);
/* 887 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 888 */     temp = Double.doubleToLongBits(this.smallestValue);
/* 889 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 890 */     if (this.numberFormatOverride != null) {
/* 891 */       result = 37 * result + this.numberFormatOverride.hashCode();
/*     */     }
/* 893 */     result = 37 * result + this.tickUnit.hashCode();
/* 894 */     return result;
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
/*     */   public static TickUnitSource createLogTickUnits(Locale locale)
/*     */   {
/* 908 */     TickUnits units = new TickUnits();
/* 909 */     NumberFormat numberFormat = new LogFormat();
/* 910 */     units.add(new NumberTickUnit(0.05D, numberFormat, 2));
/* 911 */     units.add(new NumberTickUnit(0.1D, numberFormat, 10));
/* 912 */     units.add(new NumberTickUnit(0.2D, numberFormat, 2));
/* 913 */     units.add(new NumberTickUnit(0.5D, numberFormat, 5));
/* 914 */     units.add(new NumberTickUnit(1.0D, numberFormat, 10));
/* 915 */     units.add(new NumberTickUnit(2.0D, numberFormat, 10));
/* 916 */     units.add(new NumberTickUnit(3.0D, numberFormat, 15));
/* 917 */     units.add(new NumberTickUnit(4.0D, numberFormat, 20));
/* 918 */     units.add(new NumberTickUnit(5.0D, numberFormat, 25));
/* 919 */     units.add(new NumberTickUnit(6.0D, numberFormat));
/* 920 */     units.add(new NumberTickUnit(7.0D, numberFormat));
/* 921 */     units.add(new NumberTickUnit(8.0D, numberFormat));
/* 922 */     units.add(new NumberTickUnit(9.0D, numberFormat));
/* 923 */     units.add(new NumberTickUnit(10.0D, numberFormat));
/* 924 */     return units;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/LogAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */