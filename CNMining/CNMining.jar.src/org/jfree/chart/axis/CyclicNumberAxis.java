/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CyclicNumberAxis
/*      */   extends NumberAxis
/*      */ {
/*      */   static final long serialVersionUID = -7514160997164582554L;
/*  131 */   public static Stroke DEFAULT_ADVANCE_LINE_STROKE = new BasicStroke(1.0F);
/*      */   
/*      */ 
/*  134 */   public static final Paint DEFAULT_ADVANCE_LINE_PAINT = Color.gray;
/*      */   
/*      */ 
/*      */   protected double offset;
/*      */   
/*      */ 
/*      */   protected double period;
/*      */   
/*      */ 
/*      */   protected boolean boundMappedToLastCycle;
/*      */   
/*      */ 
/*      */   protected boolean advanceLineVisible;
/*      */   
/*      */ 
/*  149 */   protected transient Stroke advanceLineStroke = DEFAULT_ADVANCE_LINE_STROKE;
/*      */   
/*      */ 
/*      */   protected transient Paint advanceLinePaint;
/*      */   
/*      */ 
/*      */   private transient boolean internalMarkerWhenTicksOverlap;
/*      */   
/*      */ 
/*      */   private transient Tick internalMarkerCycleBoundTick;
/*      */   
/*      */ 
/*      */   public CyclicNumberAxis(double period)
/*      */   {
/*  163 */     this(period, 0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CyclicNumberAxis(double period, double offset)
/*      */   {
/*  173 */     this(period, offset, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CyclicNumberAxis(double period, String label)
/*      */   {
/*  183 */     this(0.0D, period, label);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CyclicNumberAxis(double period, double offset, String label)
/*      */   {
/*  194 */     super(label);
/*  195 */     this.period = period;
/*  196 */     this.offset = offset;
/*  197 */     setFixedAutoRange(period);
/*  198 */     this.advanceLineVisible = true;
/*  199 */     this.advanceLinePaint = DEFAULT_ADVANCE_LINE_PAINT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isAdvanceLineVisible()
/*      */   {
/*  209 */     return this.advanceLineVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAdvanceLineVisible(boolean visible)
/*      */   {
/*  219 */     this.advanceLineVisible = visible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getAdvanceLinePaint()
/*      */   {
/*  229 */     return this.advanceLinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAdvanceLinePaint(Paint paint)
/*      */   {
/*  239 */     if (paint == null) {
/*  240 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  242 */     this.advanceLinePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getAdvanceLineStroke()
/*      */   {
/*  252 */     return this.advanceLineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAdvanceLineStroke(Stroke stroke)
/*      */   {
/*  261 */     if (stroke == null) {
/*  262 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  264 */     this.advanceLineStroke = stroke;
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
/*      */   public boolean isBoundMappedToLastCycle()
/*      */   {
/*  282 */     return this.boundMappedToLastCycle;
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
/*      */   public void setBoundMappedToLastCycle(boolean boundMappedToLastCycle)
/*      */   {
/*  299 */     this.boundMappedToLastCycle = boundMappedToLastCycle;
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
/*      */   protected void selectHorizontalAutoTickUnit(Graphics2D g2, Rectangle2D drawArea, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  315 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*      */     
/*      */ 
/*      */ 
/*  319 */     double n = getRange().getLength() * tickLabelWidth / dataArea.getWidth();
/*      */     
/*      */ 
/*  322 */     setTickUnit((NumberTickUnit)getStandardTickUnits().getCeilingTickUnit(n), false, false);
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
/*      */   protected void selectVerticalAutoTickUnit(Graphics2D g2, Rectangle2D drawArea, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  342 */     double tickLabelWidth = estimateMaximumTickLabelWidth(g2, getTickUnit());
/*      */     
/*      */ 
/*      */ 
/*  346 */     double n = getRange().getLength() * tickLabelWidth / dataArea.getHeight();
/*      */     
/*      */ 
/*  349 */     setTickUnit((NumberTickUnit)getStandardTickUnits().getCeilingTickUnit(n), false, false);
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
/*      */   protected static class CycleBoundTick
/*      */     extends NumberTick
/*      */   {
/*      */     public boolean mapToLastCycle;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public CycleBoundTick(boolean mapToLastCycle, Number number, String label, TextAnchor textAnchor, TextAnchor rotationAnchor, double angle)
/*      */     {
/*  380 */       super(label, textAnchor, rotationAnchor, angle);
/*  381 */       this.mapToLastCycle = mapToLastCycle;
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
/*      */   protected float[] calculateAnchorPoint(ValueTick tick, double cursor, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  398 */     if ((tick instanceof CycleBoundTick)) {
/*  399 */       boolean mapsav = this.boundMappedToLastCycle;
/*  400 */       this.boundMappedToLastCycle = ((CycleBoundTick)tick).mapToLastCycle;
/*      */       
/*  402 */       float[] ret = super.calculateAnchorPoint(tick, cursor, dataArea, edge);
/*      */       
/*      */ 
/*  405 */       this.boundMappedToLastCycle = mapsav;
/*  406 */       return ret;
/*      */     }
/*  408 */     return super.calculateAnchorPoint(tick, cursor, dataArea, edge);
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
/*      */   protected List refreshTicksHorizontal(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  427 */     List result = new ArrayList();
/*      */     
/*  429 */     Font tickLabelFont = getTickLabelFont();
/*  430 */     g2.setFont(tickLabelFont);
/*      */     
/*  432 */     if (isAutoTickUnitSelection()) {
/*  433 */       selectAutoTickUnit(g2, dataArea, edge);
/*      */     }
/*      */     
/*  436 */     double unit = getTickUnit().getSize();
/*  437 */     double cycleBound = getCycleBound();
/*  438 */     double currentTickValue = Math.ceil(cycleBound / unit) * unit;
/*  439 */     double upperValue = getRange().getUpperBound();
/*  440 */     boolean cycled = false;
/*      */     
/*  442 */     boolean boundMapping = this.boundMappedToLastCycle;
/*  443 */     this.boundMappedToLastCycle = false;
/*      */     
/*  445 */     CycleBoundTick lastTick = null;
/*  446 */     float lastX = 0.0F;
/*      */     
/*  448 */     if (upperValue == cycleBound) {
/*  449 */       currentTickValue = calculateLowestVisibleTickValue();
/*  450 */       cycled = true;
/*  451 */       this.boundMappedToLastCycle = true;
/*      */     }
/*      */     
/*  454 */     while (currentTickValue <= upperValue)
/*      */     {
/*      */ 
/*  457 */       boolean cyclenow = false;
/*  458 */       if ((currentTickValue + unit > upperValue) && (!cycled)) {
/*  459 */         cyclenow = true;
/*      */       }
/*      */       
/*  462 */       double xx = valueToJava2D(currentTickValue, dataArea, edge);
/*      */       
/*  464 */       NumberFormat formatter = getNumberFormatOverride();
/*  465 */       String tickLabel; String tickLabel; if (formatter != null) {
/*  466 */         tickLabel = formatter.format(currentTickValue);
/*      */       }
/*      */       else {
/*  469 */         tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */       }
/*  471 */       float x = (float)xx;
/*  472 */       TextAnchor anchor = null;
/*  473 */       TextAnchor rotationAnchor = null;
/*  474 */       double angle = 0.0D;
/*  475 */       if (isVerticalTickLabels()) {
/*  476 */         if (edge == RectangleEdge.TOP) {
/*  477 */           angle = 1.5707963267948966D;
/*      */         }
/*      */         else {
/*  480 */           angle = -1.5707963267948966D;
/*      */         }
/*  482 */         anchor = TextAnchor.CENTER_RIGHT;
/*      */         
/*  484 */         if ((lastTick != null) && (lastX == x) && (currentTickValue != cycleBound))
/*      */         {
/*  486 */           anchor = isInverted() ? TextAnchor.TOP_RIGHT : TextAnchor.BOTTOM_RIGHT;
/*      */           
/*  488 */           result.remove(result.size() - 1);
/*  489 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  494 */           this.internalMarkerWhenTicksOverlap = true;
/*  495 */           anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.TOP_RIGHT;
/*      */         }
/*      */         
/*  498 */         rotationAnchor = anchor;
/*      */ 
/*      */       }
/*  501 */       else if (edge == RectangleEdge.TOP) {
/*  502 */         anchor = TextAnchor.BOTTOM_CENTER;
/*  503 */         if ((lastTick != null) && (lastX == x) && (currentTickValue != cycleBound))
/*      */         {
/*  505 */           anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
/*      */           
/*  507 */           result.remove(result.size() - 1);
/*  508 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  513 */           this.internalMarkerWhenTicksOverlap = true;
/*  514 */           anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
/*      */         }
/*      */         
/*  517 */         rotationAnchor = anchor;
/*      */       }
/*      */       else {
/*  520 */         anchor = TextAnchor.TOP_CENTER;
/*  521 */         if ((lastTick != null) && (lastX == x) && (currentTickValue != cycleBound))
/*      */         {
/*  523 */           anchor = isInverted() ? TextAnchor.TOP_LEFT : TextAnchor.TOP_RIGHT;
/*      */           
/*  525 */           result.remove(result.size() - 1);
/*  526 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  531 */           this.internalMarkerWhenTicksOverlap = true;
/*  532 */           anchor = isInverted() ? TextAnchor.TOP_RIGHT : TextAnchor.TOP_LEFT;
/*      */         }
/*      */         
/*  535 */         rotationAnchor = anchor;
/*      */       }
/*      */       
/*      */ 
/*  539 */       CycleBoundTick tick = new CycleBoundTick(this.boundMappedToLastCycle, new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  544 */       if (currentTickValue == cycleBound) {
/*  545 */         this.internalMarkerCycleBoundTick = tick;
/*      */       }
/*  547 */       result.add(tick);
/*  548 */       lastTick = tick;
/*  549 */       lastX = x;
/*      */       
/*  551 */       currentTickValue += unit;
/*      */       
/*  553 */       if (cyclenow) {
/*  554 */         currentTickValue = calculateLowestVisibleTickValue();
/*  555 */         upperValue = cycleBound;
/*  556 */         cycled = true;
/*  557 */         this.boundMappedToLastCycle = true;
/*      */       }
/*      */     }
/*      */     
/*  561 */     this.boundMappedToLastCycle = boundMapping;
/*  562 */     return result;
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
/*      */   protected List refreshVerticalTicks(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  580 */     List result = new ArrayList();
/*  581 */     result.clear();
/*      */     
/*  583 */     Font tickLabelFont = getTickLabelFont();
/*  584 */     g2.setFont(tickLabelFont);
/*  585 */     if (isAutoTickUnitSelection()) {
/*  586 */       selectAutoTickUnit(g2, dataArea, edge);
/*      */     }
/*      */     
/*  589 */     double unit = getTickUnit().getSize();
/*  590 */     double cycleBound = getCycleBound();
/*  591 */     double currentTickValue = Math.ceil(cycleBound / unit) * unit;
/*  592 */     double upperValue = getRange().getUpperBound();
/*  593 */     boolean cycled = false;
/*      */     
/*  595 */     boolean boundMapping = this.boundMappedToLastCycle;
/*  596 */     this.boundMappedToLastCycle = true;
/*      */     
/*  598 */     NumberTick lastTick = null;
/*  599 */     float lastY = 0.0F;
/*      */     
/*  601 */     if (upperValue == cycleBound) {
/*  602 */       currentTickValue = calculateLowestVisibleTickValue();
/*  603 */       cycled = true;
/*  604 */       this.boundMappedToLastCycle = true;
/*      */     }
/*      */     
/*  607 */     while (currentTickValue <= upperValue)
/*      */     {
/*      */ 
/*  610 */       boolean cyclenow = false;
/*  611 */       if ((currentTickValue + unit > upperValue) && (!cycled)) {
/*  612 */         cyclenow = true;
/*      */       }
/*      */       
/*  615 */       double yy = valueToJava2D(currentTickValue, dataArea, edge);
/*      */       
/*  617 */       NumberFormat formatter = getNumberFormatOverride();
/*  618 */       String tickLabel; String tickLabel; if (formatter != null) {
/*  619 */         tickLabel = formatter.format(currentTickValue);
/*      */       }
/*      */       else {
/*  622 */         tickLabel = getTickUnit().valueToString(currentTickValue);
/*      */       }
/*      */       
/*  625 */       float y = (float)yy;
/*  626 */       TextAnchor anchor = null;
/*  627 */       TextAnchor rotationAnchor = null;
/*  628 */       double angle = 0.0D;
/*  629 */       if (isVerticalTickLabels())
/*      */       {
/*  631 */         if (edge == RectangleEdge.LEFT) {
/*  632 */           anchor = TextAnchor.BOTTOM_CENTER;
/*  633 */           if ((lastTick != null) && (lastY == y) && (currentTickValue != cycleBound))
/*      */           {
/*  635 */             anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
/*      */             
/*  637 */             result.remove(result.size() - 1);
/*  638 */             result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  643 */             this.internalMarkerWhenTicksOverlap = true;
/*  644 */             anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
/*      */           }
/*      */           
/*  647 */           rotationAnchor = anchor;
/*  648 */           angle = -1.5707963267948966D;
/*      */         }
/*      */         else {
/*  651 */           anchor = TextAnchor.BOTTOM_CENTER;
/*  652 */           if ((lastTick != null) && (lastY == y) && (currentTickValue != cycleBound))
/*      */           {
/*  654 */             anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.BOTTOM_LEFT;
/*      */             
/*  656 */             result.remove(result.size() - 1);
/*  657 */             result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */             
/*      */ 
/*      */ 
/*      */ 
/*  662 */             this.internalMarkerWhenTicksOverlap = true;
/*  663 */             anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.BOTTOM_RIGHT;
/*      */           }
/*      */           
/*  666 */           rotationAnchor = anchor;
/*  667 */           angle = 1.5707963267948966D;
/*      */         }
/*      */         
/*      */       }
/*  671 */       else if (edge == RectangleEdge.LEFT) {
/*  672 */         anchor = TextAnchor.CENTER_RIGHT;
/*  673 */         if ((lastTick != null) && (lastY == y) && (currentTickValue != cycleBound))
/*      */         {
/*  675 */           anchor = isInverted() ? TextAnchor.BOTTOM_RIGHT : TextAnchor.TOP_RIGHT;
/*      */           
/*  677 */           result.remove(result.size() - 1);
/*  678 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  683 */           this.internalMarkerWhenTicksOverlap = true;
/*  684 */           anchor = isInverted() ? TextAnchor.TOP_RIGHT : TextAnchor.BOTTOM_RIGHT;
/*      */         }
/*      */         
/*  687 */         rotationAnchor = anchor;
/*      */       }
/*      */       else {
/*  690 */         anchor = TextAnchor.CENTER_LEFT;
/*  691 */         if ((lastTick != null) && (lastY == y) && (currentTickValue != cycleBound))
/*      */         {
/*  693 */           anchor = isInverted() ? TextAnchor.BOTTOM_LEFT : TextAnchor.TOP_LEFT;
/*      */           
/*  695 */           result.remove(result.size() - 1);
/*  696 */           result.add(new CycleBoundTick(this.boundMappedToLastCycle, lastTick.getNumber(), lastTick.getText(), anchor, anchor, lastTick.getAngle()));
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*  701 */           this.internalMarkerWhenTicksOverlap = true;
/*  702 */           anchor = isInverted() ? TextAnchor.TOP_LEFT : TextAnchor.BOTTOM_LEFT;
/*      */         }
/*      */         
/*  705 */         rotationAnchor = anchor;
/*      */       }
/*      */       
/*      */ 
/*  709 */       CycleBoundTick tick = new CycleBoundTick(this.boundMappedToLastCycle, new Double(currentTickValue), tickLabel, anchor, rotationAnchor, angle);
/*      */       
/*      */ 
/*      */ 
/*  713 */       if (currentTickValue == cycleBound) {
/*  714 */         this.internalMarkerCycleBoundTick = tick;
/*      */       }
/*  716 */       result.add(tick);
/*  717 */       lastTick = tick;
/*  718 */       lastY = y;
/*      */       
/*  720 */       if (currentTickValue == cycleBound) {
/*  721 */         this.internalMarkerCycleBoundTick = tick;
/*      */       }
/*      */       
/*  724 */       currentTickValue += unit;
/*      */       
/*  726 */       if (cyclenow) {
/*  727 */         currentTickValue = calculateLowestVisibleTickValue();
/*  728 */         upperValue = cycleBound;
/*  729 */         cycled = true;
/*  730 */         this.boundMappedToLastCycle = false;
/*      */       }
/*      */     }
/*      */     
/*  734 */     this.boundMappedToLastCycle = boundMapping;
/*  735 */     return result;
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
/*      */   public double java2DToValue(double java2DValue, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  749 */     Range range = getRange();
/*      */     
/*  751 */     double vmax = range.getUpperBound();
/*  752 */     double vp = getCycleBound();
/*      */     
/*  754 */     double jmin = 0.0D;
/*  755 */     double jmax = 0.0D;
/*  756 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  757 */       jmin = dataArea.getMinX();
/*  758 */       jmax = dataArea.getMaxX();
/*      */     }
/*  760 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  761 */       jmin = dataArea.getMaxY();
/*  762 */       jmax = dataArea.getMinY();
/*      */     }
/*      */     
/*  765 */     if (isInverted()) {
/*  766 */       double jbreak = jmax - (vmax - vp) * (jmax - jmin) / this.period;
/*  767 */       if (java2DValue >= jbreak) {
/*  768 */         return vp + (jmax - java2DValue) * this.period / (jmax - jmin);
/*      */       }
/*      */       
/*  771 */       return vp - (java2DValue - jmin) * this.period / (jmax - jmin);
/*      */     }
/*      */     
/*      */ 
/*  775 */     double jbreak = (vmax - vp) * (jmax - jmin) / this.period + jmin;
/*  776 */     if (java2DValue <= jbreak) {
/*  777 */       return vp + (java2DValue - jmin) * this.period / (jmax - jmin);
/*      */     }
/*      */     
/*  780 */     return vp - (jmax - java2DValue) * this.period / (jmax - jmin);
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
/*      */   public double valueToJava2D(double value, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  796 */     Range range = getRange();
/*      */     
/*  798 */     double vmin = range.getLowerBound();
/*  799 */     double vmax = range.getUpperBound();
/*  800 */     double vp = getCycleBound();
/*      */     
/*  802 */     if ((value < vmin) || (value > vmax)) {
/*  803 */       return NaN.0D;
/*      */     }
/*      */     
/*      */ 
/*  807 */     double jmin = 0.0D;
/*  808 */     double jmax = 0.0D;
/*  809 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  810 */       jmin = dataArea.getMinX();
/*  811 */       jmax = dataArea.getMaxX();
/*      */     }
/*  813 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  814 */       jmax = dataArea.getMinY();
/*  815 */       jmin = dataArea.getMaxY();
/*      */     }
/*      */     
/*  818 */     if (isInverted()) {
/*  819 */       if (value == vp) {
/*  820 */         return this.boundMappedToLastCycle ? jmin : jmax;
/*      */       }
/*  822 */       if (value > vp) {
/*  823 */         return jmax - (value - vp) * (jmax - jmin) / this.period;
/*      */       }
/*      */       
/*  826 */       return jmin + (vp - value) * (jmax - jmin) / this.period;
/*      */     }
/*      */     
/*      */ 
/*  830 */     if (value == vp) {
/*  831 */       return this.boundMappedToLastCycle ? jmax : jmin;
/*      */     }
/*  833 */     if (value >= vp) {
/*  834 */       return jmin + (value - vp) * (jmax - jmin) / this.period;
/*      */     }
/*      */     
/*  837 */     return jmax - (vp - value) * (jmax - jmin) / this.period;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void centerRange(double value)
/*      */   {
/*  848 */     setRange(value - this.period / 2.0D, value + this.period / 2.0D);
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
/*      */   public void setAutoRangeMinimumSize(double size, boolean notify)
/*      */   {
/*  863 */     if (size > this.period) {
/*  864 */       this.period = size;
/*      */     }
/*  866 */     super.setAutoRangeMinimumSize(size, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedAutoRange(double length)
/*      */   {
/*  878 */     this.period = length;
/*  879 */     super.setFixedAutoRange(length);
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
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify)
/*      */   {
/*  894 */     double size = range.getUpperBound() - range.getLowerBound();
/*  895 */     if (size > this.period) {
/*  896 */       this.period = size;
/*      */     }
/*  898 */     super.setRange(range, turnOffAutoRange, notify);
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
/*      */   public double getCycleBound()
/*      */   {
/*  912 */     return Math.floor((getRange().getUpperBound() - this.offset) / this.period) * this.period + this.offset;
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
/*      */   public double getOffset()
/*      */   {
/*  928 */     return this.offset;
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
/*      */   public void setOffset(double offset)
/*      */   {
/*  942 */     this.offset = offset;
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
/*      */   public double getPeriod()
/*      */   {
/*  956 */     return this.period;
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
/*      */   public void setPeriod(double period)
/*      */   {
/*  970 */     this.period = period;
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
/*      */   protected AxisState drawTickMarksAndLabels(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  986 */     this.internalMarkerWhenTicksOverlap = false;
/*  987 */     AxisState ret = super.drawTickMarksAndLabels(g2, cursor, plotArea, dataArea, edge);
/*      */     
/*      */ 
/*      */ 
/*  991 */     if (!this.internalMarkerWhenTicksOverlap) {
/*  992 */       return ret;
/*      */     }
/*      */     
/*  995 */     double ol = getTickMarkOutsideLength();
/*  996 */     FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/*      */     
/*  998 */     if (isVerticalTickLabels()) {
/*  999 */       ol = fm.getMaxAdvance();
/*      */     }
/*      */     else {
/* 1002 */       ol = fm.getHeight();
/*      */     }
/*      */     
/* 1005 */     double il = 0.0D;
/* 1006 */     if (isTickMarksVisible()) {
/* 1007 */       float xx = (float)valueToJava2D(getRange().getUpperBound(), dataArea, edge);
/*      */       
/* 1009 */       Line2D mark = null;
/* 1010 */       g2.setStroke(getTickMarkStroke());
/* 1011 */       g2.setPaint(getTickMarkPaint());
/* 1012 */       if (edge == RectangleEdge.LEFT) {
/* 1013 */         mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
/*      */       }
/* 1015 */       else if (edge == RectangleEdge.RIGHT) {
/* 1016 */         mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
/*      */       }
/* 1018 */       else if (edge == RectangleEdge.TOP) {
/* 1019 */         mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
/*      */       }
/* 1021 */       else if (edge == RectangleEdge.BOTTOM) {
/* 1022 */         mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
/*      */       }
/* 1024 */       g2.draw(mark);
/*      */     }
/* 1026 */     return ret;
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
/*      */   public AxisState draw(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, PlotRenderingInfo plotState)
/*      */   {
/* 1049 */     AxisState ret = super.draw(g2, cursor, plotArea, dataArea, edge, plotState);
/*      */     
/*      */ 
/* 1052 */     if (isAdvanceLineVisible()) {
/* 1053 */       double xx = valueToJava2D(getRange().getUpperBound(), dataArea, edge);
/*      */       
/*      */ 
/* 1056 */       Line2D mark = null;
/* 1057 */       g2.setStroke(getAdvanceLineStroke());
/* 1058 */       g2.setPaint(getAdvanceLinePaint());
/* 1059 */       if (edge == RectangleEdge.LEFT) {
/* 1060 */         mark = new Line2D.Double(cursor, xx, cursor + dataArea.getWidth(), xx);
/*      */ 
/*      */ 
/*      */       }
/* 1064 */       else if (edge == RectangleEdge.RIGHT) {
/* 1065 */         mark = new Line2D.Double(cursor - dataArea.getWidth(), xx, cursor, xx);
/*      */ 
/*      */ 
/*      */       }
/* 1069 */       else if (edge == RectangleEdge.TOP) {
/* 1070 */         mark = new Line2D.Double(xx, cursor + dataArea.getHeight(), xx, cursor);
/*      */ 
/*      */ 
/*      */       }
/* 1074 */       else if (edge == RectangleEdge.BOTTOM) {
/* 1075 */         mark = new Line2D.Double(xx, cursor, xx, cursor - dataArea.getHeight());
/*      */       }
/*      */       
/*      */ 
/* 1079 */       g2.draw(mark);
/*      */     }
/* 1081 */     return ret;
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
/*      */   public AxisSpace reserveSpace(Graphics2D g2, Plot plot, Rectangle2D plotArea, RectangleEdge edge, AxisSpace space)
/*      */   {
/* 1102 */     this.internalMarkerCycleBoundTick = null;
/* 1103 */     AxisSpace ret = super.reserveSpace(g2, plot, plotArea, edge, space);
/* 1104 */     if (this.internalMarkerCycleBoundTick == null) {
/* 1105 */       return ret;
/*      */     }
/*      */     
/* 1108 */     FontMetrics fm = g2.getFontMetrics(getTickLabelFont());
/* 1109 */     Rectangle2D r = TextUtilities.getTextBounds(this.internalMarkerCycleBoundTick.getText(), g2, fm);
/*      */     
/*      */ 
/*      */ 
/* 1113 */     if (RectangleEdge.isTopOrBottom(edge)) {
/* 1114 */       if (isVerticalTickLabels()) {
/* 1115 */         space.add(r.getHeight() / 2.0D, RectangleEdge.RIGHT);
/*      */       }
/*      */       else {
/* 1118 */         space.add(r.getWidth() / 2.0D, RectangleEdge.RIGHT);
/*      */       }
/*      */     }
/* 1121 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1122 */       if (isVerticalTickLabels()) {
/* 1123 */         space.add(r.getWidth() / 2.0D, RectangleEdge.TOP);
/*      */       }
/*      */       else {
/* 1126 */         space.add(r.getHeight() / 2.0D, RectangleEdge.TOP);
/*      */       }
/*      */     }
/*      */     
/* 1130 */     return ret;
/*      */   }
/*      */   
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
/* 1143 */     stream.defaultWriteObject();
/* 1144 */     SerialUtilities.writePaint(this.advanceLinePaint, stream);
/* 1145 */     SerialUtilities.writeStroke(this.advanceLineStroke, stream);
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
/*      */   private void readObject(ObjectInputStream stream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1160 */     stream.defaultReadObject();
/* 1161 */     this.advanceLinePaint = SerialUtilities.readPaint(stream);
/* 1162 */     this.advanceLineStroke = SerialUtilities.readStroke(stream);
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
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1175 */     if (obj == this) {
/* 1176 */       return true;
/*      */     }
/* 1178 */     if (!(obj instanceof CyclicNumberAxis)) {
/* 1179 */       return false;
/*      */     }
/* 1181 */     if (!super.equals(obj)) {
/* 1182 */       return false;
/*      */     }
/* 1184 */     CyclicNumberAxis that = (CyclicNumberAxis)obj;
/* 1185 */     if (this.period != that.period) {
/* 1186 */       return false;
/*      */     }
/* 1188 */     if (this.offset != that.offset) {
/* 1189 */       return false;
/*      */     }
/* 1191 */     if (!PaintUtilities.equal(this.advanceLinePaint, that.advanceLinePaint))
/*      */     {
/* 1193 */       return false;
/*      */     }
/* 1195 */     if (!ObjectUtilities.equal(this.advanceLineStroke, that.advanceLineStroke))
/*      */     {
/* 1197 */       return false;
/*      */     }
/* 1199 */     if (this.advanceLineVisible != that.advanceLineVisible) {
/* 1200 */       return false;
/*      */     }
/* 1202 */     if (this.boundMappedToLastCycle != that.boundMappedToLastCycle) {
/* 1203 */       return false;
/*      */     }
/* 1205 */     return true;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CyclicNumberAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */