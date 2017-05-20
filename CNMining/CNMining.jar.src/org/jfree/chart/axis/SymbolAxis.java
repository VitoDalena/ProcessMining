/*     */ package org.jfree.chart.axis;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.NumberFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.event.AxisChangeEvent;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.ValueAxisPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SymbolAxis
/*     */   extends NumberAxis
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 7216330468770619716L;
/* 120 */   public static final Paint DEFAULT_GRID_BAND_PAINT = new Color(232, 234, 232, 128);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 128 */   public static final Paint DEFAULT_GRID_BAND_ALTERNATE_PAINT = new Color(0, 0, 0, 0);
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private List symbols;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean gridBandsVisible;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint gridBandPaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint gridBandAlternatePaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public SymbolAxis(String label, String[] sv)
/*     */   {
/* 156 */     super(label);
/* 157 */     this.symbols = Arrays.asList(sv);
/* 158 */     this.gridBandsVisible = true;
/* 159 */     this.gridBandPaint = DEFAULT_GRID_BAND_PAINT;
/* 160 */     this.gridBandAlternatePaint = DEFAULT_GRID_BAND_ALTERNATE_PAINT;
/* 161 */     setAutoTickUnitSelection(false, false);
/* 162 */     setAutoRangeStickyZero(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String[] getSymbols()
/*     */   {
/* 172 */     String[] result = new String[this.symbols.size()];
/* 173 */     result = (String[])this.symbols.toArray(result);
/* 174 */     return result;
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
/*     */   public boolean isGridBandsVisible()
/*     */   {
/* 187 */     return this.gridBandsVisible;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGridBandsVisible(boolean flag)
/*     */   {
/* 199 */     if (this.gridBandsVisible != flag) {
/* 200 */       this.gridBandsVisible = flag;
/* 201 */       notifyListeners(new AxisChangeEvent(this));
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
/*     */   public Paint getGridBandPaint()
/*     */   {
/* 214 */     return this.gridBandPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGridBandPaint(Paint paint)
/*     */   {
/* 226 */     if (paint == null) {
/* 227 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 229 */     this.gridBandPaint = paint;
/* 230 */     notifyListeners(new AxisChangeEvent(this));
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
/*     */   public Paint getGridBandAlternatePaint()
/*     */   {
/* 244 */     return this.gridBandAlternatePaint;
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
/*     */   public void setGridBandAlternatePaint(Paint paint)
/*     */   {
/* 259 */     if (paint == null) {
/* 260 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 262 */     this.gridBandAlternatePaint = paint;
/* 263 */     notifyListeners(new AxisChangeEvent(this));
/*     */   }
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
/* 275 */     throw new UnsupportedOperationException();
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
/*     */ 
/*     */ 
/*     */ 
/*     */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*     */   {
/* 301 */     AxisState info = new AxisState(cursor);
/* 302 */     if (isVisible()) {
/* 303 */       info = super.draw(g2, cursor, plotArea, dataArea, edge, plotState);
/*     */     }
/* 305 */     if (this.gridBandsVisible) {
/* 306 */       drawGridBands(g2, plotArea, dataArea, edge, info.getTicks());
/*     */     }
/* 308 */     return info;
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
/*     */   protected void drawGridBands(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, List ticks)
/*     */   {
/* 330 */     Shape savedClip = g2.getClip();
/* 331 */     g2.clip(dataArea);
/* 332 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 333 */       drawGridBandsHorizontal(g2, plotArea, dataArea, true, ticks);
/*     */     }
/* 335 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 336 */       drawGridBandsVertical(g2, plotArea, dataArea, true, ticks);
/*     */     }
/* 338 */     g2.setClip(savedClip);
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
/*     */ 
/*     */   protected void drawGridBandsHorizontal(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, boolean firstGridBandIsDark, List ticks)
/*     */   {
/* 362 */     boolean currentGridBandIsDark = firstGridBandIsDark;
/* 363 */     double yy = dataArea.getY();
/*     */     
/*     */     double outlineStrokeWidth;
/*     */     
/*     */     double outlineStrokeWidth;
/* 368 */     if (getPlot().getOutlineStroke() != null) {
/* 369 */       outlineStrokeWidth = ((BasicStroke)getPlot().getOutlineStroke()).getLineWidth();
/*     */     }
/*     */     else
/*     */     {
/* 373 */       outlineStrokeWidth = 1.0D;
/*     */     }
/*     */     
/* 376 */     Iterator iterator = ticks.iterator();
/*     */     
/*     */ 
/* 379 */     while (iterator.hasNext()) {
/* 380 */       ValueTick tick = (ValueTick)iterator.next();
/* 381 */       double xx1 = valueToJava2D(tick.getValue() - 0.5D, dataArea, RectangleEdge.BOTTOM);
/*     */       
/* 383 */       double xx2 = valueToJava2D(tick.getValue() + 0.5D, dataArea, RectangleEdge.BOTTOM);
/*     */       
/* 385 */       if (currentGridBandIsDark) {
/* 386 */         g2.setPaint(this.gridBandPaint);
/*     */       }
/*     */       else {
/* 389 */         g2.setPaint(this.gridBandAlternatePaint);
/*     */       }
/* 391 */       Rectangle2D band = new Rectangle2D.Double(xx1, yy + outlineStrokeWidth, xx2 - xx1, dataArea.getMaxY() - yy - outlineStrokeWidth);
/*     */       
/* 393 */       g2.fill(band);
/* 394 */       currentGridBandIsDark = !currentGridBandIsDark;
/*     */     }
/* 396 */     g2.setPaintMode();
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
/*     */   protected void drawGridBandsVertical(Graphics2D g2, Rectangle2D drawArea, Rectangle2D plotArea, boolean firstGridBandIsDark, List ticks)
/*     */   {
/* 419 */     boolean currentGridBandIsDark = firstGridBandIsDark;
/* 420 */     double xx = plotArea.getX();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 425 */     Stroke outlineStroke = getPlot().getOutlineStroke();
/* 426 */     double outlineStrokeWidth; double outlineStrokeWidth; if ((outlineStroke != null) && ((outlineStroke instanceof BasicStroke))) {
/* 427 */       outlineStrokeWidth = ((BasicStroke)outlineStroke).getLineWidth();
/*     */     }
/*     */     else {
/* 430 */       outlineStrokeWidth = 1.0D;
/*     */     }
/*     */     
/* 433 */     Iterator iterator = ticks.iterator();
/*     */     
/*     */ 
/* 436 */     while (iterator.hasNext()) {
/* 437 */       ValueTick tick = (ValueTick)iterator.next();
/* 438 */       double yy1 = valueToJava2D(tick.getValue() + 0.5D, plotArea, RectangleEdge.LEFT);
/*     */       
/* 440 */       double yy2 = valueToJava2D(tick.getValue() - 0.5D, plotArea, RectangleEdge.LEFT);
/*     */       
/* 442 */       if (currentGridBandIsDark) {
/* 443 */         g2.setPaint(this.gridBandPaint);
/*     */       }
/*     */       else {
/* 446 */         g2.setPaint(this.gridBandAlternatePaint);
/*     */       }
/* 448 */       Rectangle2D band = new Rectangle2D.Double(xx + outlineStrokeWidth, yy1, plotArea.getMaxX() - xx - outlineStrokeWidth, yy2 - yy1);
/*     */       
/* 450 */       g2.fill(band);
/* 451 */       currentGridBandIsDark = !currentGridBandIsDark;
/*     */     }
/* 453 */     g2.setPaintMode();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void autoAdjustRange()
/*     */   {
/* 461 */     Plot plot = getPlot();
/* 462 */     if (plot == null) {
/* 463 */       return;
/*     */     }
/*     */     
/* 466 */     if ((plot instanceof ValueAxisPlot))
/*     */     {
/*     */ 
/* 469 */       double upper = this.symbols.size() - 1;
/* 470 */       double lower = 0.0D;
/* 471 */       double range = upper - lower;
/*     */       
/*     */ 
/* 474 */       double minRange = getAutoRangeMinimumSize();
/* 475 */       if (range < minRange) {
/* 476 */         upper = (upper + lower + minRange) / 2.0D;
/* 477 */         lower = (upper + lower - minRange) / 2.0D;
/*     */       }
/*     */       
/*     */ 
/* 481 */       double upperMargin = 0.5D;
/* 482 */       double lowerMargin = 0.5D;
/*     */       
/* 484 */       if (getAutoRangeIncludesZero()) {
/* 485 */         if (getAutoRangeStickyZero()) {
/* 486 */           if (upper <= 0.0D) {
/* 487 */             upper = 0.0D;
/*     */           }
/*     */           else {
/* 490 */             upper += upperMargin;
/*     */           }
/* 492 */           if (lower >= 0.0D) {
/* 493 */             lower = 0.0D;
/*     */           }
/*     */           else {
/* 496 */             lower -= lowerMargin;
/*     */           }
/*     */         }
/*     */         else {
/* 500 */           upper = Math.max(0.0D, upper + upperMargin);
/* 501 */           lower = Math.min(0.0D, lower - lowerMargin);
/*     */         }
/*     */         
/*     */       }
/* 505 */       else if (getAutoRangeStickyZero()) {
/* 506 */         if (upper <= 0.0D) {
/* 507 */           upper = Math.min(0.0D, upper + upperMargin);
/*     */         }
/*     */         else {
/* 510 */           upper += upperMargin * range;
/*     */         }
/* 512 */         if (lower >= 0.0D) {
/* 513 */           lower = Math.max(0.0D, lower - lowerMargin);
/*     */         }
/*     */         else {
/* 516 */           lower -= lowerMargin;
/*     */         }
/*     */       }
/*     */       else {
/* 520 */         upper += upperMargin;
/* 521 */         lower -= lowerMargin;
/*     */       }
/*     */       
/*     */ 
/* 525 */       setRange(new Range(lower, upper), false, false);
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
/*     */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 546 */     List ticks = null;
/* 547 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 548 */       ticks = refreshTicksHorizontal(g2, dataArea, edge);
/*     */     }
/* 550 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 551 */       ticks = refreshTicksVertical(g2, dataArea, edge);
/*     */     }
/* 553 */     return ticks;
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
/*     */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 570 */     List ticks = new ArrayList();
/*     */     
/* 572 */     Font tickLabelFont = getTickLabelFont();
/* 573 */     g2.setFont(tickLabelFont);
/*     */     
/* 575 */     double size = getTickUnit().getSize();
/* 576 */     int count = calculateVisibleTickCount();
/* 577 */     double lowestTickValue = calculateLowestVisibleTickValue();
/*     */     
/* 579 */     double previousDrawnTickLabelPos = 0.0D;
/* 580 */     double previousDrawnTickLabelLength = 0.0D;
/*     */     
/* 582 */     if (count <= 500) {
/* 583 */       for (int i = 0; i < count; i++) {
/* 584 */         double currentTickValue = lowestTickValue + i * size;
/* 585 */         double xx = valueToJava2D(currentTickValue, dataArea, edge);
/*     */         
/* 587 */         NumberFormat formatter = getNumberFormatOverride();
/* 588 */         String tickLabel; String tickLabel; if (formatter != null) {
/* 589 */           tickLabel = formatter.format(currentTickValue);
/*     */         }
/*     */         else {
/* 592 */           tickLabel = valueToString(currentTickValue);
/*     */         }
/*     */         
/*     */ 
/* 596 */         Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, g2.getFontMetrics());
/*     */         
/* 598 */         double tickLabelLength = isVerticalTickLabels() ? bounds.getHeight() : bounds.getWidth();
/*     */         
/* 600 */         boolean tickLabelsOverlapping = false;
/* 601 */         if (i > 0) {
/* 602 */           double avgTickLabelLength = (previousDrawnTickLabelLength + tickLabelLength) / 2.0D;
/*     */           
/* 604 */           if (Math.abs(xx - previousDrawnTickLabelPos) < avgTickLabelLength)
/*     */           {
/* 606 */             tickLabelsOverlapping = true;
/*     */           }
/*     */         }
/* 609 */         if (tickLabelsOverlapping) {
/* 610 */           tickLabel = "";
/*     */         }
/*     */         else
/*     */         {
/* 614 */           previousDrawnTickLabelPos = xx;
/* 615 */           previousDrawnTickLabelLength = tickLabelLength;
/*     */         }
/*     */         
/* 618 */         TextAnchor anchor = null;
/* 619 */         TextAnchor rotationAnchor = null;
/* 620 */         double angle = 0.0D;
/* 621 */         if (isVerticalTickLabels()) {
/* 622 */           anchor = TextAnchor.CENTER_RIGHT;
/* 623 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/* 624 */           if (edge == RectangleEdge.TOP) {
/* 625 */             angle = 1.5707963267948966D;
/*     */           }
/*     */           else {
/* 628 */             angle = -1.5707963267948966D;
/*     */           }
/*     */           
/*     */         }
/* 632 */         else if (edge == RectangleEdge.TOP) {
/* 633 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 634 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/*     */         }
/*     */         else {
/* 637 */           anchor = TextAnchor.TOP_CENTER;
/* 638 */           rotationAnchor = TextAnchor.TOP_CENTER;
/*     */         }
/*     */         
/* 641 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*     */         
/* 643 */         ticks.add(tick);
/*     */       }
/*     */     }
/* 646 */     return ticks;
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
/*     */   protected List refreshTicksVertical(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*     */   {
/* 664 */     List ticks = new ArrayList();
/*     */     
/* 666 */     Font tickLabelFont = getTickLabelFont();
/* 667 */     g2.setFont(tickLabelFont);
/*     */     
/* 669 */     double size = getTickUnit().getSize();
/* 670 */     int count = calculateVisibleTickCount();
/* 671 */     double lowestTickValue = calculateLowestVisibleTickValue();
/*     */     
/* 673 */     double previousDrawnTickLabelPos = 0.0D;
/* 674 */     double previousDrawnTickLabelLength = 0.0D;
/*     */     
/* 676 */     if (count <= 500) {
/* 677 */       for (int i = 0; i < count; i++) {
/* 678 */         double currentTickValue = lowestTickValue + i * size;
/* 679 */         double yy = valueToJava2D(currentTickValue, dataArea, edge);
/*     */         
/* 681 */         NumberFormat formatter = getNumberFormatOverride();
/* 682 */         String tickLabel; String tickLabel; if (formatter != null) {
/* 683 */           tickLabel = formatter.format(currentTickValue);
/*     */         }
/*     */         else {
/* 686 */           tickLabel = valueToString(currentTickValue);
/*     */         }
/*     */         
/*     */ 
/* 690 */         Rectangle2D bounds = TextUtilities.getTextBounds(tickLabel, g2, g2.getFontMetrics());
/*     */         
/* 692 */         double tickLabelLength = isVerticalTickLabels() ? bounds.getWidth() : bounds.getHeight();
/*     */         
/* 694 */         boolean tickLabelsOverlapping = false;
/* 695 */         if (i > 0) {
/* 696 */           double avgTickLabelLength = (previousDrawnTickLabelLength + tickLabelLength) / 2.0D;
/*     */           
/* 698 */           if (Math.abs(yy - previousDrawnTickLabelPos) < avgTickLabelLength)
/*     */           {
/* 700 */             tickLabelsOverlapping = true;
/*     */           }
/*     */         }
/* 703 */         if (tickLabelsOverlapping) {
/* 704 */           tickLabel = "";
/*     */         }
/*     */         else
/*     */         {
/* 708 */           previousDrawnTickLabelPos = yy;
/* 709 */           previousDrawnTickLabelLength = tickLabelLength;
/*     */         }
/*     */         
/* 712 */         TextAnchor anchor = null;
/* 713 */         TextAnchor rotationAnchor = null;
/* 714 */         double angle = 0.0D;
/* 715 */         if (isVerticalTickLabels()) {
/* 716 */           anchor = TextAnchor.BOTTOM_CENTER;
/* 717 */           rotationAnchor = TextAnchor.BOTTOM_CENTER;
/* 718 */           if (edge == RectangleEdge.LEFT) {
/* 719 */             angle = -1.5707963267948966D;
/*     */           }
/*     */           else {
/* 722 */             angle = 1.5707963267948966D;
/*     */           }
/*     */           
/*     */         }
/* 726 */         else if (edge == RectangleEdge.LEFT) {
/* 727 */           anchor = TextAnchor.CENTER_RIGHT;
/* 728 */           rotationAnchor = TextAnchor.CENTER_RIGHT;
/*     */         }
/*     */         else {
/* 731 */           anchor = TextAnchor.CENTER_LEFT;
/* 732 */           rotationAnchor = TextAnchor.CENTER_LEFT;
/*     */         }
/*     */         
/* 735 */         Tick tick = new NumberTick(new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*     */         
/* 737 */         ticks.add(tick);
/*     */       }
/*     */     }
/* 740 */     return ticks;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public String valueToString(double value)
/*     */   {
/*     */     String strToReturn;
/*     */     
/*     */ 
/*     */ 
/*     */     try
/*     */     {
/* 754 */       strToReturn = (String)this.symbols.get((int)value);
/*     */     }
/*     */     catch (IndexOutOfBoundsException ex) {
/* 757 */       strToReturn = "";
/*     */     }
/* 759 */     return strToReturn;
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
/* 770 */     if (obj == this) {
/* 771 */       return true;
/*     */     }
/* 773 */     if (!(obj instanceof SymbolAxis)) {
/* 774 */       return false;
/*     */     }
/* 776 */     SymbolAxis that = (SymbolAxis)obj;
/* 777 */     if (!this.symbols.equals(that.symbols)) {
/* 778 */       return false;
/*     */     }
/* 780 */     if (this.gridBandsVisible != that.gridBandsVisible) {
/* 781 */       return false;
/*     */     }
/* 783 */     if (!PaintUtilities.equal(this.gridBandPaint, that.gridBandPaint)) {
/* 784 */       return false;
/*     */     }
/* 786 */     if (!PaintUtilities.equal(this.gridBandAlternatePaint, that.gridBandAlternatePaint))
/*     */     {
/* 788 */       return false;
/*     */     }
/* 790 */     return super.equals(obj);
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
/* 801 */     stream.defaultWriteObject();
/* 802 */     SerialUtilities.writePaint(this.gridBandPaint, stream);
/* 803 */     SerialUtilities.writePaint(this.gridBandAlternatePaint, stream);
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
/* 816 */     stream.defaultReadObject();
/* 817 */     this.gridBandPaint = SerialUtilities.readPaint(stream);
/* 818 */     this.gridBandAlternatePaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/SymbolAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */