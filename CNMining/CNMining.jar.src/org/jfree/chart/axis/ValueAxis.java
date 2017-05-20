/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Shape;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
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
/*      */ public abstract class ValueAxis
/*      */   extends Axis
/*      */   implements Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 3698345477322391456L;
/*  151 */   public static final Range DEFAULT_RANGE = new Range(0.0D, 1.0D);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean DEFAULT_AUTO_RANGE = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean DEFAULT_INVERTED = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double DEFAULT_AUTO_RANGE_MINIMUM_SIZE = 1.0E-8D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double DEFAULT_LOWER_MARGIN = 0.05D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double DEFAULT_UPPER_MARGIN = 0.05D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static final double DEFAULT_LOWER_BOUND = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public static final double DEFAULT_UPPER_BOUND = 1.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean DEFAULT_AUTO_TICK_UNIT_SELECTION = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final int MAXIMUM_TICK_COUNT = 500;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean positiveArrowVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean negativeArrowVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Shape upArrow;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Shape downArrow;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Shape leftArrow;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Shape rightArrow;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean inverted;
/*      */   
/*      */ 
/*      */ 
/*      */   private Range range;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoRange;
/*      */   
/*      */ 
/*      */ 
/*      */   private double autoRangeMinimumSize;
/*      */   
/*      */ 
/*      */ 
/*      */   private Range defaultAutoRange;
/*      */   
/*      */ 
/*      */ 
/*      */   private double upperMargin;
/*      */   
/*      */ 
/*      */ 
/*      */   private double lowerMargin;
/*      */   
/*      */ 
/*      */ 
/*      */   private double fixedAutoRange;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoTickUnitSelection;
/*      */   
/*      */ 
/*      */ 
/*      */   private TickUnitSource standardTickUnits;
/*      */   
/*      */ 
/*      */ 
/*      */   private int autoTickIndex;
/*      */   
/*      */ 
/*      */ 
/*      */   private int minorTickCount;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean verticalTickLabels;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected ValueAxis(String label, TickUnitSource standardTickUnits)
/*      */   {
/*  289 */     super(label);
/*      */     
/*  291 */     this.positiveArrowVisible = false;
/*  292 */     this.negativeArrowVisible = false;
/*      */     
/*  294 */     this.range = DEFAULT_RANGE;
/*  295 */     this.autoRange = true;
/*  296 */     this.defaultAutoRange = DEFAULT_RANGE;
/*      */     
/*  298 */     this.inverted = false;
/*  299 */     this.autoRangeMinimumSize = 1.0E-8D;
/*      */     
/*  301 */     this.lowerMargin = 0.05D;
/*  302 */     this.upperMargin = 0.05D;
/*      */     
/*  304 */     this.fixedAutoRange = 0.0D;
/*      */     
/*  306 */     this.autoTickUnitSelection = true;
/*  307 */     this.standardTickUnits = standardTickUnits;
/*      */     
/*  309 */     Polygon p1 = new Polygon();
/*  310 */     p1.addPoint(0, 0);
/*  311 */     p1.addPoint(-2, 2);
/*  312 */     p1.addPoint(2, 2);
/*      */     
/*  314 */     this.upArrow = p1;
/*      */     
/*  316 */     Polygon p2 = new Polygon();
/*  317 */     p2.addPoint(0, 0);
/*  318 */     p2.addPoint(-2, -2);
/*  319 */     p2.addPoint(2, -2);
/*      */     
/*  321 */     this.downArrow = p2;
/*      */     
/*  323 */     Polygon p3 = new Polygon();
/*  324 */     p3.addPoint(0, 0);
/*  325 */     p3.addPoint(-2, -2);
/*  326 */     p3.addPoint(-2, 2);
/*      */     
/*  328 */     this.rightArrow = p3;
/*      */     
/*  330 */     Polygon p4 = new Polygon();
/*  331 */     p4.addPoint(0, 0);
/*  332 */     p4.addPoint(2, -2);
/*  333 */     p4.addPoint(2, 2);
/*      */     
/*  335 */     this.leftArrow = p4;
/*      */     
/*  337 */     this.verticalTickLabels = false;
/*  338 */     this.minorTickCount = 0;
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
/*      */   public boolean isVerticalTickLabels()
/*      */   {
/*  351 */     return this.verticalTickLabels;
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
/*      */   public void setVerticalTickLabels(boolean flag)
/*      */   {
/*  365 */     if (this.verticalTickLabels != flag) {
/*  366 */       this.verticalTickLabels = flag;
/*  367 */       notifyListeners(new AxisChangeEvent(this));
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
/*      */   public boolean isPositiveArrowVisible()
/*      */   {
/*  380 */     return this.positiveArrowVisible;
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
/*      */   public void setPositiveArrowVisible(boolean visible)
/*      */   {
/*  393 */     this.positiveArrowVisible = visible;
/*  394 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isNegativeArrowVisible()
/*      */   {
/*  406 */     return this.negativeArrowVisible;
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
/*      */   public void setNegativeArrowVisible(boolean visible)
/*      */   {
/*  419 */     this.negativeArrowVisible = visible;
/*  420 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getUpArrow()
/*      */   {
/*  432 */     return this.upArrow;
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
/*      */   public void setUpArrow(Shape arrow)
/*      */   {
/*  445 */     if (arrow == null) {
/*  446 */       throw new IllegalArgumentException("Null 'arrow' argument.");
/*      */     }
/*  448 */     this.upArrow = arrow;
/*  449 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getDownArrow()
/*      */   {
/*  461 */     return this.downArrow;
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
/*      */   public void setDownArrow(Shape arrow)
/*      */   {
/*  474 */     if (arrow == null) {
/*  475 */       throw new IllegalArgumentException("Null 'arrow' argument.");
/*      */     }
/*  477 */     this.downArrow = arrow;
/*  478 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getLeftArrow()
/*      */   {
/*  490 */     return this.leftArrow;
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
/*      */   public void setLeftArrow(Shape arrow)
/*      */   {
/*  503 */     if (arrow == null) {
/*  504 */       throw new IllegalArgumentException("Null 'arrow' argument.");
/*      */     }
/*  506 */     this.leftArrow = arrow;
/*  507 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getRightArrow()
/*      */   {
/*  519 */     return this.rightArrow;
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
/*      */   public void setRightArrow(Shape arrow)
/*      */   {
/*  532 */     if (arrow == null) {
/*  533 */       throw new IllegalArgumentException("Null 'arrow' argument.");
/*      */     }
/*  535 */     this.rightArrow = arrow;
/*  536 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   protected void drawAxisLine(Graphics2D g2, double cursor, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  549 */     Line2D axisLine = null;
/*  550 */     if (edge == RectangleEdge.TOP) {
/*  551 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/*      */ 
/*      */     }
/*  554 */     else if (edge == RectangleEdge.BOTTOM) {
/*  555 */       axisLine = new Line2D.Double(dataArea.getX(), cursor, dataArea.getMaxX(), cursor);
/*      */ 
/*      */     }
/*  558 */     else if (edge == RectangleEdge.LEFT) {
/*  559 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*      */ 
/*      */     }
/*  562 */     else if (edge == RectangleEdge.RIGHT) {
/*  563 */       axisLine = new Line2D.Double(cursor, dataArea.getY(), cursor, dataArea.getMaxY());
/*      */     }
/*      */     
/*  566 */     g2.setPaint(getAxisLinePaint());
/*  567 */     g2.setStroke(getAxisLineStroke());
/*  568 */     g2.draw(axisLine);
/*      */     
/*  570 */     boolean drawUpOrRight = false;
/*  571 */     boolean drawDownOrLeft = false;
/*  572 */     if (this.positiveArrowVisible) {
/*  573 */       if (this.inverted) {
/*  574 */         drawDownOrLeft = true;
/*      */       }
/*      */       else {
/*  577 */         drawUpOrRight = true;
/*      */       }
/*      */     }
/*  580 */     if (this.negativeArrowVisible) {
/*  581 */       if (this.inverted) {
/*  582 */         drawUpOrRight = true;
/*      */       }
/*      */       else {
/*  585 */         drawDownOrLeft = true;
/*      */       }
/*      */     }
/*  588 */     if (drawUpOrRight) {
/*  589 */       double x = 0.0D;
/*  590 */       double y = 0.0D;
/*  591 */       Shape arrow = null;
/*  592 */       if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/*  593 */         x = dataArea.getMaxX();
/*  594 */         y = cursor;
/*  595 */         arrow = this.rightArrow;
/*      */       }
/*  597 */       else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*      */       {
/*  599 */         x = cursor;
/*  600 */         y = dataArea.getMinY();
/*  601 */         arrow = this.upArrow;
/*      */       }
/*      */       
/*      */ 
/*  605 */       AffineTransform transformer = new AffineTransform();
/*  606 */       transformer.setToTranslation(x, y);
/*  607 */       Shape shape = transformer.createTransformedShape(arrow);
/*  608 */       g2.fill(shape);
/*  609 */       g2.draw(shape);
/*      */     }
/*      */     
/*  612 */     if (drawDownOrLeft) {
/*  613 */       double x = 0.0D;
/*  614 */       double y = 0.0D;
/*  615 */       Shape arrow = null;
/*  616 */       if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/*  617 */         x = dataArea.getMinX();
/*  618 */         y = cursor;
/*  619 */         arrow = this.leftArrow;
/*      */       }
/*  621 */       else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*      */       {
/*  623 */         x = cursor;
/*  624 */         y = dataArea.getMaxY();
/*  625 */         arrow = this.downArrow;
/*      */       }
/*      */       
/*      */ 
/*  629 */       AffineTransform transformer = new AffineTransform();
/*  630 */       transformer.setToTranslation(x, y);
/*  631 */       Shape shape = transformer.createTransformedShape(arrow);
/*  632 */       g2.fill(shape);
/*  633 */       g2.draw(shape);
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
/*      */   protected float[] calculateAnchorPoint(ValueTick tick, double cursor, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  653 */     RectangleInsets insets = getTickLabelInsets();
/*  654 */     float[] result = new float[2];
/*  655 */     if (edge == RectangleEdge.TOP) {
/*  656 */       result[0] = ((float)valueToJava2D(tick.getValue(), dataArea, edge));
/*  657 */       result[1] = ((float)(cursor - insets.getBottom() - 2.0D));
/*      */     }
/*  659 */     else if (edge == RectangleEdge.BOTTOM) {
/*  660 */       result[0] = ((float)valueToJava2D(tick.getValue(), dataArea, edge));
/*  661 */       result[1] = ((float)(cursor + insets.getTop() + 2.0D));
/*      */     }
/*  663 */     else if (edge == RectangleEdge.LEFT) {
/*  664 */       result[0] = ((float)(cursor - insets.getLeft() - 2.0D));
/*  665 */       result[1] = ((float)valueToJava2D(tick.getValue(), dataArea, edge));
/*      */     }
/*  667 */     else if (edge == RectangleEdge.RIGHT) {
/*  668 */       result[0] = ((float)(cursor + insets.getRight() + 2.0D));
/*  669 */       result[1] = ((float)valueToJava2D(tick.getValue(), dataArea, edge));
/*      */     }
/*  671 */     return result;
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
/*      */   protected AxisState drawTickMarksAndLabels(Graphics2D g2, double cursor, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/*  689 */     AxisState state = new AxisState(cursor);
/*      */     
/*  691 */     if (isAxisLineVisible()) {
/*  692 */       drawAxisLine(g2, cursor, dataArea, edge);
/*      */     }
/*      */     
/*  695 */     List ticks = refreshTicks(g2, state, dataArea, edge);
/*  696 */     state.setTicks(ticks);
/*  697 */     g2.setFont(getTickLabelFont());
/*  698 */     Iterator iterator = ticks.iterator();
/*  699 */     while (iterator.hasNext()) {
/*  700 */       ValueTick tick = (ValueTick)iterator.next();
/*  701 */       if (isTickLabelsVisible()) {
/*  702 */         g2.setPaint(getTickLabelPaint());
/*  703 */         float[] anchorPoint = calculateAnchorPoint(tick, cursor, dataArea, edge);
/*      */         
/*  705 */         TextUtilities.drawRotatedString(tick.getText(), g2, anchorPoint[0], anchorPoint[1], tick.getTextAnchor(), tick.getAngle(), tick.getRotationAnchor());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  710 */       if (((isTickMarksVisible()) && (tick.getTickType().equals(TickType.MAJOR))) || ((isMinorTickMarksVisible()) && (tick.getTickType().equals(TickType.MINOR))))
/*      */       {
/*      */ 
/*      */ 
/*  714 */         double ol = tick.getTickType().equals(TickType.MINOR) ? getMinorTickMarkOutsideLength() : getTickMarkOutsideLength();
/*      */         
/*      */ 
/*  717 */         double il = tick.getTickType().equals(TickType.MINOR) ? getMinorTickMarkInsideLength() : getTickMarkInsideLength();
/*      */         
/*      */ 
/*  720 */         float xx = (float)valueToJava2D(tick.getValue(), dataArea, edge);
/*      */         
/*  722 */         Line2D mark = null;
/*  723 */         g2.setStroke(getTickMarkStroke());
/*  724 */         g2.setPaint(getTickMarkPaint());
/*  725 */         if (edge == RectangleEdge.LEFT) {
/*  726 */           mark = new Line2D.Double(cursor - ol, xx, cursor + il, xx);
/*      */         }
/*  728 */         else if (edge == RectangleEdge.RIGHT) {
/*  729 */           mark = new Line2D.Double(cursor + ol, xx, cursor - il, xx);
/*      */         }
/*  731 */         else if (edge == RectangleEdge.TOP) {
/*  732 */           mark = new Line2D.Double(xx, cursor - ol, xx, cursor + il);
/*      */         }
/*  734 */         else if (edge == RectangleEdge.BOTTOM) {
/*  735 */           mark = new Line2D.Double(xx, cursor + ol, xx, cursor - il);
/*      */         }
/*  737 */         g2.draw(mark);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  743 */     double used = 0.0D;
/*  744 */     if (isTickLabelsVisible()) {
/*  745 */       if (edge == RectangleEdge.LEFT) {
/*  746 */         used += findMaximumTickLabelWidth(ticks, g2, plotArea, isVerticalTickLabels());
/*      */         
/*  748 */         state.cursorLeft(used);
/*      */       }
/*  750 */       else if (edge == RectangleEdge.RIGHT) {
/*  751 */         used = findMaximumTickLabelWidth(ticks, g2, plotArea, isVerticalTickLabels());
/*      */         
/*  753 */         state.cursorRight(used);
/*      */       }
/*  755 */       else if (edge == RectangleEdge.TOP) {
/*  756 */         used = findMaximumTickLabelHeight(ticks, g2, plotArea, isVerticalTickLabels());
/*      */         
/*  758 */         state.cursorUp(used);
/*      */       }
/*  760 */       else if (edge == RectangleEdge.BOTTOM) {
/*  761 */         used = findMaximumTickLabelHeight(ticks, g2, plotArea, isVerticalTickLabels());
/*      */         
/*  763 */         state.cursorDown(used);
/*      */       }
/*      */     }
/*      */     
/*  767 */     return state;
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
/*  787 */     if (space == null) {
/*  788 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/*  792 */     if (!isVisible()) {
/*  793 */       return space;
/*      */     }
/*      */     
/*      */ 
/*  797 */     double dimension = getFixedDimension();
/*  798 */     if (dimension > 0.0D) {
/*  799 */       space.ensureAtLeast(dimension, edge);
/*      */     }
/*      */     
/*      */ 
/*  803 */     double tickLabelHeight = 0.0D;
/*  804 */     double tickLabelWidth = 0.0D;
/*  805 */     if (isTickLabelsVisible()) {
/*  806 */       g2.setFont(getTickLabelFont());
/*  807 */       List ticks = refreshTicks(g2, new AxisState(), plotArea, edge);
/*  808 */       if (RectangleEdge.isTopOrBottom(edge)) {
/*  809 */         tickLabelHeight = findMaximumTickLabelHeight(ticks, g2, plotArea, isVerticalTickLabels());
/*      */ 
/*      */       }
/*  812 */       else if (RectangleEdge.isLeftOrRight(edge)) {
/*  813 */         tickLabelWidth = findMaximumTickLabelWidth(ticks, g2, plotArea, isVerticalTickLabels());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  819 */     Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
/*  820 */     double labelHeight = 0.0D;
/*  821 */     double labelWidth = 0.0D;
/*  822 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  823 */       labelHeight = labelEnclosure.getHeight();
/*  824 */       space.add(labelHeight + tickLabelHeight, edge);
/*      */     }
/*  826 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  827 */       labelWidth = labelEnclosure.getWidth();
/*  828 */       space.add(labelWidth + tickLabelWidth, edge);
/*      */     }
/*      */     
/*  831 */     return space;
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
/*      */   protected double findMaximumTickLabelHeight(List ticks, Graphics2D g2, Rectangle2D drawArea, boolean vertical)
/*      */   {
/*  851 */     RectangleInsets insets = getTickLabelInsets();
/*  852 */     Font font = getTickLabelFont();
/*  853 */     double maxHeight = 0.0D;
/*  854 */     if (vertical) {
/*  855 */       FontMetrics fm = g2.getFontMetrics(font);
/*  856 */       Iterator iterator = ticks.iterator();
/*  857 */       while (iterator.hasNext()) {
/*  858 */         Tick tick = (Tick)iterator.next();
/*  859 */         Rectangle2D labelBounds = TextUtilities.getTextBounds(tick.getText(), g2, fm);
/*      */         
/*  861 */         if (labelBounds.getWidth() + insets.getTop() + insets.getBottom() > maxHeight)
/*      */         {
/*  863 */           maxHeight = labelBounds.getWidth() + insets.getTop() + insets.getBottom();
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  869 */       LineMetrics metrics = font.getLineMetrics("ABCxyz", g2.getFontRenderContext());
/*      */       
/*  871 */       maxHeight = metrics.getHeight() + insets.getTop() + insets.getBottom();
/*      */     }
/*      */     
/*  874 */     return maxHeight;
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
/*      */   protected double findMaximumTickLabelWidth(List ticks, Graphics2D g2, Rectangle2D drawArea, boolean vertical)
/*      */   {
/*  894 */     RectangleInsets insets = getTickLabelInsets();
/*  895 */     Font font = getTickLabelFont();
/*  896 */     double maxWidth = 0.0D;
/*  897 */     if (!vertical) {
/*  898 */       FontMetrics fm = g2.getFontMetrics(font);
/*  899 */       Iterator iterator = ticks.iterator();
/*  900 */       while (iterator.hasNext()) {
/*  901 */         Tick tick = (Tick)iterator.next();
/*  902 */         Rectangle2D labelBounds = TextUtilities.getTextBounds(tick.getText(), g2, fm);
/*      */         
/*  904 */         if (labelBounds.getWidth() + insets.getLeft() + insets.getRight() > maxWidth)
/*      */         {
/*  906 */           maxWidth = labelBounds.getWidth() + insets.getLeft() + insets.getRight();
/*      */         }
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  912 */       LineMetrics metrics = font.getLineMetrics("ABCxyz", g2.getFontRenderContext());
/*      */       
/*  914 */       maxWidth = metrics.getHeight() + insets.getTop() + insets.getBottom();
/*      */     }
/*      */     
/*  917 */     return maxWidth;
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
/*      */   public boolean isInverted()
/*      */   {
/*  933 */     return this.inverted;
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
/*      */   public void setInverted(boolean flag)
/*      */   {
/*  946 */     if (this.inverted != flag) {
/*  947 */       this.inverted = flag;
/*  948 */       notifyListeners(new AxisChangeEvent(this));
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
/*      */   public boolean isAutoRange()
/*      */   {
/*  962 */     return this.autoRange;
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
/*      */   public void setAutoRange(boolean auto)
/*      */   {
/*  975 */     setAutoRange(auto, true);
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
/*      */   protected void setAutoRange(boolean auto, boolean notify)
/*      */   {
/*  988 */     if (this.autoRange != auto) {
/*  989 */       this.autoRange = auto;
/*  990 */       if (this.autoRange) {
/*  991 */         autoAdjustRange();
/*      */       }
/*  993 */       if (notify) {
/*  994 */         notifyListeners(new AxisChangeEvent(this));
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
/*      */   public double getAutoRangeMinimumSize()
/*      */   {
/* 1008 */     return this.autoRangeMinimumSize;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoRangeMinimumSize(double size)
/*      */   {
/* 1020 */     setAutoRangeMinimumSize(size, true);
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
/*      */   public void setAutoRangeMinimumSize(double size, boolean notify)
/*      */   {
/* 1034 */     if (size <= 0.0D) {
/* 1035 */       throw new IllegalArgumentException("NumberAxis.setAutoRangeMinimumSize(double): must be > 0.0.");
/*      */     }
/*      */     
/* 1038 */     if (this.autoRangeMinimumSize != size) {
/* 1039 */       this.autoRangeMinimumSize = size;
/* 1040 */       if (this.autoRange) {
/* 1041 */         autoAdjustRange();
/*      */       }
/* 1043 */       if (notify) {
/* 1044 */         notifyListeners(new AxisChangeEvent(this));
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
/*      */   public Range getDefaultAutoRange()
/*      */   {
/* 1060 */     return this.defaultAutoRange;
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
/*      */   public void setDefaultAutoRange(Range range)
/*      */   {
/* 1074 */     if (range == null) {
/* 1075 */       throw new IllegalArgumentException("Null 'range' argument.");
/*      */     }
/* 1077 */     this.defaultAutoRange = range;
/* 1078 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public double getLowerMargin()
/*      */   {
/* 1092 */     return this.lowerMargin;
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
/*      */   public void setLowerMargin(double margin)
/*      */   {
/* 1107 */     this.lowerMargin = margin;
/* 1108 */     if (isAutoRange()) {
/* 1109 */       autoAdjustRange();
/*      */     }
/* 1111 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public double getUpperMargin()
/*      */   {
/* 1125 */     return this.upperMargin;
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
/*      */   public void setUpperMargin(double margin)
/*      */   {
/* 1140 */     this.upperMargin = margin;
/* 1141 */     if (isAutoRange()) {
/* 1142 */       autoAdjustRange();
/*      */     }
/* 1144 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getFixedAutoRange()
/*      */   {
/* 1155 */     return this.fixedAutoRange;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedAutoRange(double length)
/*      */   {
/* 1166 */     this.fixedAutoRange = length;
/* 1167 */     if (isAutoRange()) {
/* 1168 */       autoAdjustRange();
/*      */     }
/* 1170 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getLowerBound()
/*      */   {
/* 1181 */     return this.range.getLowerBound();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLowerBound(double min)
/*      */   {
/* 1193 */     if (this.range.getUpperBound() > min) {
/* 1194 */       setRange(new Range(min, this.range.getUpperBound()));
/*      */     }
/*      */     else {
/* 1197 */       setRange(new Range(min, min + 1.0D));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getUpperBound()
/*      */   {
/* 1209 */     return this.range.getUpperBound();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setUpperBound(double max)
/*      */   {
/* 1221 */     if (this.range.getLowerBound() < max) {
/* 1222 */       setRange(new Range(this.range.getLowerBound(), max));
/*      */     }
/*      */     else {
/* 1225 */       setRange(max - 1.0D, max);
/*      */     }
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
/* 1237 */     return this.range;
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
/*      */   public void setRange(Range range)
/*      */   {
/* 1251 */     setRange(range, true, true);
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
/*      */   public void setRange(Range range, boolean turnOffAutoRange, boolean notify)
/*      */   {
/* 1269 */     if (range == null) {
/* 1270 */       throw new IllegalArgumentException("Null 'range' argument.");
/*      */     }
/* 1272 */     if (turnOffAutoRange) {
/* 1273 */       this.autoRange = false;
/*      */     }
/* 1275 */     this.range = range;
/* 1276 */     if (notify) {
/* 1277 */       notifyListeners(new AxisChangeEvent(this));
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
/*      */   public void setRange(double lower, double upper)
/*      */   {
/* 1293 */     setRange(new Range(lower, upper));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeWithMargins(Range range)
/*      */   {
/* 1304 */     setRangeWithMargins(range, true, true);
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
/*      */   public void setRangeWithMargins(Range range, boolean turnOffAutoRange, boolean notify)
/*      */   {
/* 1322 */     if (range == null) {
/* 1323 */       throw new IllegalArgumentException("Null 'range' argument.");
/*      */     }
/* 1325 */     setRange(Range.expand(range, getLowerMargin(), getUpperMargin()), turnOffAutoRange, notify);
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
/*      */   public void setRangeWithMargins(double lower, double upper)
/*      */   {
/* 1338 */     setRangeWithMargins(new Range(lower, upper));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAboutValue(double value, double length)
/*      */   {
/* 1349 */     setRange(new Range(value - length / 2.0D, value + length / 2.0D));
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
/*      */   public boolean isAutoTickUnitSelection()
/*      */   {
/* 1362 */     return this.autoTickUnitSelection;
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
/*      */   public void setAutoTickUnitSelection(boolean flag)
/*      */   {
/* 1375 */     setAutoTickUnitSelection(flag, true);
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
/*      */   public void setAutoTickUnitSelection(boolean flag, boolean notify)
/*      */   {
/* 1389 */     if (this.autoTickUnitSelection != flag) {
/* 1390 */       this.autoTickUnitSelection = flag;
/* 1391 */       if (notify) {
/* 1392 */         notifyListeners(new AxisChangeEvent(this));
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
/*      */   public TickUnitSource getStandardTickUnits()
/*      */   {
/* 1405 */     return this.standardTickUnits;
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
/*      */   public void setStandardTickUnits(TickUnitSource source)
/*      */   {
/* 1421 */     this.standardTickUnits = source;
/* 1422 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public int getMinorTickCount()
/*      */   {
/* 1435 */     return this.minorTickCount;
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
/*      */   public void setMinorTickCount(int count)
/*      */   {
/* 1449 */     this.minorTickCount = count;
/* 1450 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public abstract double valueToJava2D(double paramDouble, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double lengthToJava2D(double length, Rectangle2D area, RectangleEdge edge)
/*      */   {
/* 1482 */     double zero = valueToJava2D(0.0D, area, edge);
/* 1483 */     double l = valueToJava2D(length, area, edge);
/* 1484 */     return Math.abs(l - zero);
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
/*      */   public abstract double java2DToValue(double paramDouble, Rectangle2D paramRectangle2D, RectangleEdge paramRectangleEdge);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected abstract void autoAdjustRange();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void centerRange(double value)
/*      */   {
/* 1519 */     double central = this.range.getCentralValue();
/* 1520 */     Range adjusted = new Range(this.range.getLowerBound() + value - central, this.range.getUpperBound() + value - central);
/*      */     
/* 1522 */     setRange(adjusted);
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
/*      */   public void resizeRange(double percent)
/*      */   {
/* 1539 */     resizeRange(percent, this.range.getCentralValue());
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
/*      */   public void resizeRange(double percent, double anchorValue)
/*      */   {
/* 1556 */     if (percent > 0.0D) {
/* 1557 */       double halfLength = this.range.getLength() * percent / 2.0D;
/* 1558 */       Range adjusted = new Range(anchorValue - halfLength, anchorValue + halfLength);
/*      */       
/* 1560 */       setRange(adjusted);
/*      */     }
/*      */     else {
/* 1563 */       setAutoRange(true);
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
/*      */   public void resizeRange2(double percent, double anchorValue)
/*      */   {
/* 1583 */     if (percent > 0.0D) {
/* 1584 */       double left = anchorValue - getLowerBound();
/* 1585 */       double right = getUpperBound() - anchorValue;
/* 1586 */       Range adjusted = new Range(anchorValue - left * percent, anchorValue + right * percent);
/*      */       
/* 1588 */       setRange(adjusted);
/*      */     }
/*      */     else {
/* 1591 */       setAutoRange(true);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRange(double lowerPercent, double upperPercent)
/*      */   {
/* 1602 */     double start = this.range.getLowerBound();
/* 1603 */     double length = this.range.getLength();
/* 1604 */     Range adjusted = null;
/* 1605 */     if (isInverted()) {
/* 1606 */       adjusted = new Range(start + length * (1.0D - upperPercent), start + length * (1.0D - lowerPercent));
/*      */     }
/*      */     else
/*      */     {
/* 1610 */       adjusted = new Range(start + length * lowerPercent, start + length * upperPercent);
/*      */     }
/*      */     
/* 1613 */     setRange(adjusted);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void pan(double percent)
/*      */   {
/* 1624 */     Range range = getRange();
/* 1625 */     double length = range.getLength();
/* 1626 */     double adj = length * percent;
/* 1627 */     double lower = range.getLowerBound() + adj;
/* 1628 */     double upper = range.getUpperBound() + adj;
/* 1629 */     setRange(lower, upper);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected int getAutoTickIndex()
/*      */   {
/* 1640 */     return this.autoTickIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setAutoTickIndex(int index)
/*      */   {
/* 1651 */     this.autoTickIndex = index;
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
/* 1662 */     if (obj == this) {
/* 1663 */       return true;
/*      */     }
/* 1665 */     if (!(obj instanceof ValueAxis)) {
/* 1666 */       return false;
/*      */     }
/* 1668 */     ValueAxis that = (ValueAxis)obj;
/* 1669 */     if (this.positiveArrowVisible != that.positiveArrowVisible) {
/* 1670 */       return false;
/*      */     }
/* 1672 */     if (this.negativeArrowVisible != that.negativeArrowVisible) {
/* 1673 */       return false;
/*      */     }
/* 1675 */     if (this.inverted != that.inverted) {
/* 1676 */       return false;
/*      */     }
/*      */     
/* 1679 */     if ((!this.autoRange) && (!ObjectUtilities.equal(this.range, that.range))) {
/* 1680 */       return false;
/*      */     }
/* 1682 */     if (this.autoRange != that.autoRange) {
/* 1683 */       return false;
/*      */     }
/* 1685 */     if (this.autoRangeMinimumSize != that.autoRangeMinimumSize) {
/* 1686 */       return false;
/*      */     }
/* 1688 */     if (!this.defaultAutoRange.equals(that.defaultAutoRange)) {
/* 1689 */       return false;
/*      */     }
/* 1691 */     if (this.upperMargin != that.upperMargin) {
/* 1692 */       return false;
/*      */     }
/* 1694 */     if (this.lowerMargin != that.lowerMargin) {
/* 1695 */       return false;
/*      */     }
/* 1697 */     if (this.fixedAutoRange != that.fixedAutoRange) {
/* 1698 */       return false;
/*      */     }
/* 1700 */     if (this.autoTickUnitSelection != that.autoTickUnitSelection) {
/* 1701 */       return false;
/*      */     }
/* 1703 */     if (!ObjectUtilities.equal(this.standardTickUnits, that.standardTickUnits))
/*      */     {
/* 1705 */       return false;
/*      */     }
/* 1707 */     if (this.verticalTickLabels != that.verticalTickLabels) {
/* 1708 */       return false;
/*      */     }
/* 1710 */     if (this.minorTickCount != that.minorTickCount) {
/* 1711 */       return false;
/*      */     }
/* 1713 */     return super.equals(obj);
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
/* 1725 */     ValueAxis clone = (ValueAxis)super.clone();
/* 1726 */     return clone;
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
/* 1737 */     stream.defaultWriteObject();
/* 1738 */     SerialUtilities.writeShape(this.upArrow, stream);
/* 1739 */     SerialUtilities.writeShape(this.downArrow, stream);
/* 1740 */     SerialUtilities.writeShape(this.leftArrow, stream);
/* 1741 */     SerialUtilities.writeShape(this.rightArrow, stream);
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
/*      */   private void readObject(ObjectInputStream stream)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1755 */     stream.defaultReadObject();
/* 1756 */     this.upArrow = SerialUtilities.readShape(stream);
/* 1757 */     this.downArrow = SerialUtilities.readShape(stream);
/* 1758 */     this.leftArrow = SerialUtilities.readShape(stream);
/* 1759 */     this.rightArrow = SerialUtilities.readShape(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/ValueAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */