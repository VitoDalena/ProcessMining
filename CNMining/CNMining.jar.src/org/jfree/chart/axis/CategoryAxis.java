/*      */ package org.jfree.chart.axis;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Map.Entry;
/*      */ import java.util.Set;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.entity.CategoryLabelEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.G2TextMeasurer;
/*      */ import org.jfree.text.TextBlock;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.Size2D;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CategoryAxis
/*      */   extends Axis
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 5886554608114265863L;
/*      */   public static final double DEFAULT_AXIS_MARGIN = 0.05D;
/*      */   public static final double DEFAULT_CATEGORY_MARGIN = 0.2D;
/*      */   private double lowerMargin;
/*      */   private double upperMargin;
/*      */   private double categoryMargin;
/*      */   private int maximumCategoryLabelLines;
/*      */   private float maximumCategoryLabelWidthRatio;
/*      */   private int categoryLabelPositionOffset;
/*      */   private CategoryLabelPositions categoryLabelPositions;
/*      */   private Map tickLabelFontMap;
/*      */   private transient Map tickLabelPaintMap;
/*      */   private Map categoryLabelToolTips;
/*      */   
/*      */   public CategoryAxis()
/*      */   {
/*  196 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryAxis(String label)
/*      */   {
/*  206 */     super(label);
/*      */     
/*  208 */     this.lowerMargin = 0.05D;
/*  209 */     this.upperMargin = 0.05D;
/*  210 */     this.categoryMargin = 0.2D;
/*  211 */     this.maximumCategoryLabelLines = 1;
/*  212 */     this.maximumCategoryLabelWidthRatio = 0.0F;
/*      */     
/*  214 */     this.categoryLabelPositionOffset = 4;
/*  215 */     this.categoryLabelPositions = CategoryLabelPositions.STANDARD;
/*  216 */     this.tickLabelFontMap = new HashMap();
/*  217 */     this.tickLabelPaintMap = new HashMap();
/*  218 */     this.categoryLabelToolTips = new HashMap();
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
/*      */   public double getLowerMargin()
/*      */   {
/*  231 */     return this.lowerMargin;
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
/*      */   public void setLowerMargin(double margin)
/*      */   {
/*  244 */     this.lowerMargin = margin;
/*  245 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
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
/*  257 */     return this.upperMargin;
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
/*      */   public void setUpperMargin(double margin)
/*      */   {
/*  270 */     this.upperMargin = margin;
/*  271 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getCategoryMargin()
/*      */   {
/*  282 */     return this.categoryMargin;
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
/*      */   public void setCategoryMargin(double margin)
/*      */   {
/*  296 */     this.categoryMargin = margin;
/*  297 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaximumCategoryLabelLines()
/*      */   {
/*  308 */     return this.maximumCategoryLabelLines;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumCategoryLabelLines(int lines)
/*      */   {
/*  320 */     this.maximumCategoryLabelLines = lines;
/*  321 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getMaximumCategoryLabelWidthRatio()
/*      */   {
/*  332 */     return this.maximumCategoryLabelWidthRatio;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumCategoryLabelWidthRatio(float ratio)
/*      */   {
/*  344 */     this.maximumCategoryLabelWidthRatio = ratio;
/*  345 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getCategoryLabelPositionOffset()
/*      */   {
/*  357 */     return this.categoryLabelPositionOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCategoryLabelPositionOffset(int offset)
/*      */   {
/*  369 */     this.categoryLabelPositionOffset = offset;
/*  370 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryLabelPositions getCategoryLabelPositions()
/*      */   {
/*  382 */     return this.categoryLabelPositions;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCategoryLabelPositions(CategoryLabelPositions positions)
/*      */   {
/*  394 */     if (positions == null) {
/*  395 */       throw new IllegalArgumentException("Null 'positions' argument.");
/*      */     }
/*  397 */     this.categoryLabelPositions = positions;
/*  398 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public Font getTickLabelFont(Comparable category)
/*      */   {
/*  411 */     if (category == null) {
/*  412 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  414 */     Font result = (Font)this.tickLabelFontMap.get(category);
/*      */     
/*  416 */     if (result == null) {
/*  417 */       result = getTickLabelFont();
/*      */     }
/*  419 */     return result;
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
/*      */   public void setTickLabelFont(Comparable category, Font font)
/*      */   {
/*  432 */     if (category == null) {
/*  433 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  435 */     if (font == null) {
/*  436 */       this.tickLabelFontMap.remove(category);
/*      */     }
/*      */     else {
/*  439 */       this.tickLabelFontMap.put(category, font);
/*      */     }
/*  441 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public Paint getTickLabelPaint(Comparable category)
/*      */   {
/*  454 */     if (category == null) {
/*  455 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  457 */     Paint result = (Paint)this.tickLabelPaintMap.get(category);
/*      */     
/*  459 */     if (result == null) {
/*  460 */       result = getTickLabelPaint();
/*      */     }
/*  462 */     return result;
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
/*      */   public void setTickLabelPaint(Comparable category, Paint paint)
/*      */   {
/*  475 */     if (category == null) {
/*  476 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  478 */     if (paint == null) {
/*  479 */       this.tickLabelPaintMap.remove(category);
/*      */     }
/*      */     else {
/*  482 */       this.tickLabelPaintMap.put(category, paint);
/*      */     }
/*  484 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public void addCategoryLabelToolTip(Comparable category, String tooltip)
/*      */   {
/*  497 */     if (category == null) {
/*  498 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  500 */     this.categoryLabelToolTips.put(category, tooltip);
/*  501 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public String getCategoryLabelToolTip(Comparable category)
/*      */   {
/*  516 */     if (category == null) {
/*  517 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  519 */     return (String)this.categoryLabelToolTips.get(category);
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
/*      */   public void removeCategoryLabelToolTip(Comparable category)
/*      */   {
/*  532 */     if (category == null) {
/*  533 */       throw new IllegalArgumentException("Null 'category' argument.");
/*      */     }
/*  535 */     this.categoryLabelToolTips.remove(category);
/*  536 */     notifyListeners(new AxisChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearCategoryLabelToolTips()
/*      */   {
/*  547 */     this.categoryLabelToolTips.clear();
/*  548 */     notifyListeners(new AxisChangeEvent(this));
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
/*      */   public double getCategoryJava2DCoordinate(CategoryAnchor anchor, int category, int categoryCount, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  568 */     double result = 0.0D;
/*  569 */     if (anchor == CategoryAnchor.START) {
/*  570 */       result = getCategoryStart(category, categoryCount, area, edge);
/*      */     }
/*  572 */     else if (anchor == CategoryAnchor.MIDDLE) {
/*  573 */       result = getCategoryMiddle(category, categoryCount, area, edge);
/*      */     }
/*  575 */     else if (anchor == CategoryAnchor.END) {
/*  576 */       result = getCategoryEnd(category, categoryCount, area, edge);
/*      */     }
/*  578 */     return result;
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
/*      */   public double getCategoryStart(int category, int categoryCount, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  599 */     double result = 0.0D;
/*  600 */     if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/*  601 */       result = area.getX() + area.getWidth() * getLowerMargin();
/*      */     }
/*  603 */     else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*      */     {
/*  605 */       result = area.getMinY() + area.getHeight() * getLowerMargin();
/*      */     }
/*      */     
/*  608 */     double categorySize = calculateCategorySize(categoryCount, area, edge);
/*  609 */     double categoryGapWidth = calculateCategoryGapSize(categoryCount, area, edge);
/*      */     
/*      */ 
/*  612 */     result += category * (categorySize + categoryGapWidth);
/*  613 */     return result;
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
/*      */   public double getCategoryMiddle(int category, int categoryCount, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  633 */     if ((category < 0) || (category >= categoryCount)) {
/*  634 */       throw new IllegalArgumentException("Invalid category index: " + category);
/*      */     }
/*      */     
/*  637 */     return getCategoryStart(category, categoryCount, area, edge) + calculateCategorySize(categoryCount, area, edge) / 2.0D;
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
/*      */   public double getCategoryEnd(int category, int categoryCount, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  658 */     return getCategoryStart(category, categoryCount, area, edge) + calculateCategorySize(categoryCount, area, edge);
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
/*      */   public double getCategoryMiddle(Comparable category, List categories, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  682 */     if (categories == null) {
/*  683 */       throw new IllegalArgumentException("Null 'categories' argument.");
/*      */     }
/*  685 */     int categoryIndex = categories.indexOf(category);
/*  686 */     int categoryCount = categories.size();
/*  687 */     return getCategoryMiddle(categoryIndex, categoryCount, area, edge);
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
/*      */   public double getCategorySeriesMiddle(Comparable category, Comparable seriesKey, CategoryDataset dataset, double itemMargin, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  709 */     int categoryIndex = dataset.getColumnIndex(category);
/*  710 */     int categoryCount = dataset.getColumnCount();
/*  711 */     int seriesIndex = dataset.getRowIndex(seriesKey);
/*  712 */     int seriesCount = dataset.getRowCount();
/*  713 */     double start = getCategoryStart(categoryIndex, categoryCount, area, edge);
/*      */     
/*  715 */     double end = getCategoryEnd(categoryIndex, categoryCount, area, edge);
/*  716 */     double width = end - start;
/*  717 */     if (seriesCount == 1) {
/*  718 */       return start + width / 2.0D;
/*      */     }
/*      */     
/*  721 */     double gap = width * itemMargin / (seriesCount - 1);
/*  722 */     double ww = width * (1.0D - itemMargin) / seriesCount;
/*  723 */     return start + seriesIndex * (ww + gap) + ww / 2.0D;
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
/*      */   public double getCategorySeriesMiddle(int categoryIndex, int categoryCount, int seriesIndex, int seriesCount, double itemMargin, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  747 */     double start = getCategoryStart(categoryIndex, categoryCount, area, edge);
/*      */     
/*  749 */     double end = getCategoryEnd(categoryIndex, categoryCount, area, edge);
/*  750 */     double width = end - start;
/*  751 */     if (seriesCount == 1) {
/*  752 */       return start + width / 2.0D;
/*      */     }
/*      */     
/*  755 */     double gap = width * itemMargin / (seriesCount - 1);
/*  756 */     double ww = width * (1.0D - itemMargin) / seriesCount;
/*  757 */     return start + seriesIndex * (ww + gap) + ww / 2.0D;
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
/*      */   protected double calculateCategorySize(int categoryCount, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  774 */     double result = 0.0D;
/*  775 */     double available = 0.0D;
/*      */     
/*  777 */     if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/*  778 */       available = area.getWidth();
/*      */     }
/*  780 */     else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*      */     {
/*  782 */       available = area.getHeight();
/*      */     }
/*  784 */     if (categoryCount > 1) {
/*  785 */       result = available * (1.0D - getLowerMargin() - getUpperMargin() - getCategoryMargin());
/*      */       
/*  787 */       result /= categoryCount;
/*      */     }
/*      */     else {
/*  790 */       result = available * (1.0D - getLowerMargin() - getUpperMargin());
/*      */     }
/*  792 */     return result;
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
/*      */   protected double calculateCategoryGapSize(int categoryCount, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  810 */     double result = 0.0D;
/*  811 */     double available = 0.0D;
/*      */     
/*  813 */     if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/*  814 */       available = area.getWidth();
/*      */     }
/*  816 */     else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*      */     {
/*  818 */       available = area.getHeight();
/*      */     }
/*      */     
/*  821 */     if (categoryCount > 1) {
/*  822 */       result = available * getCategoryMargin() / (categoryCount - 1);
/*      */     }
/*      */     
/*  825 */     return result;
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
/*  845 */     if (space == null) {
/*  846 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/*  850 */     if (!isVisible()) {
/*  851 */       return space;
/*      */     }
/*      */     
/*      */ 
/*  855 */     double tickLabelHeight = 0.0D;
/*  856 */     double tickLabelWidth = 0.0D;
/*  857 */     if (isTickLabelsVisible()) {
/*  858 */       g2.setFont(getTickLabelFont());
/*  859 */       AxisState state = new AxisState();
/*      */       
/*  861 */       refreshTicks(g2, state, plotArea, edge);
/*  862 */       if (edge == RectangleEdge.TOP) {
/*  863 */         tickLabelHeight = state.getMax();
/*      */       }
/*  865 */       else if (edge == RectangleEdge.BOTTOM) {
/*  866 */         tickLabelHeight = state.getMax();
/*      */       }
/*  868 */       else if (edge == RectangleEdge.LEFT) {
/*  869 */         tickLabelWidth = state.getMax();
/*      */       }
/*  871 */       else if (edge == RectangleEdge.RIGHT) {
/*  872 */         tickLabelWidth = state.getMax();
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  877 */     Rectangle2D labelEnclosure = getLabelEnclosure(g2, edge);
/*  878 */     double labelHeight = 0.0D;
/*  879 */     double labelWidth = 0.0D;
/*  880 */     if (RectangleEdge.isTopOrBottom(edge)) {
/*  881 */       labelHeight = labelEnclosure.getHeight();
/*  882 */       space.add(labelHeight + tickLabelHeight + this.categoryLabelPositionOffset, edge);
/*      */ 
/*      */     }
/*  885 */     else if (RectangleEdge.isLeftOrRight(edge)) {
/*  886 */       labelWidth = labelEnclosure.getWidth();
/*  887 */       space.add(labelWidth + tickLabelWidth + this.categoryLabelPositionOffset, edge);
/*      */     }
/*      */     
/*  890 */     return space;
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
/*      */   public void configure() {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/*  922 */     if (!isVisible()) {
/*  923 */       return new AxisState(cursor);
/*      */     }
/*      */     
/*  926 */     if (isAxisLineVisible()) {
/*  927 */       drawAxisLine(g2, cursor, dataArea, edge);
/*      */     }
/*  929 */     AxisState state = new AxisState(cursor);
/*  930 */     if (isTickMarksVisible()) {
/*  931 */       drawTickMarks(g2, cursor, dataArea, edge, state);
/*      */     }
/*      */     
/*      */ 
/*  935 */     state = drawCategoryLabels(g2, plotArea, dataArea, edge, state, plotState);
/*      */     
/*  937 */     state = drawLabel(getLabel(), g2, plotArea, dataArea, edge, state);
/*  938 */     createAndAddEntity(cursor, state, dataArea, edge, plotState);
/*  939 */     return state;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected AxisState drawCategoryLabels(Graphics2D g2, Rectangle2D dataArea, RectangleEdge edge, AxisState state, PlotRenderingInfo plotState)
/*      */   {
/*  967 */     return drawCategoryLabels(g2, dataArea, dataArea, edge, state, plotState);
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
/*      */   protected AxisState drawCategoryLabels(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, RectangleEdge edge, AxisState state, PlotRenderingInfo plotState)
/*      */   {
/*  992 */     if (state == null) {
/*  993 */       throw new IllegalArgumentException("Null 'state' argument.");
/*      */     }
/*      */     
/*  996 */     if (isTickLabelsVisible()) {
/*  997 */       List ticks = refreshTicks(g2, state, plotArea, edge);
/*  998 */       state.setTicks(ticks);
/*      */       
/* 1000 */       int categoryIndex = 0;
/* 1001 */       Iterator iterator = ticks.iterator();
/* 1002 */       while (iterator.hasNext())
/*      */       {
/* 1004 */         CategoryTick tick = (CategoryTick)iterator.next();
/* 1005 */         g2.setFont(getTickLabelFont(tick.getCategory()));
/* 1006 */         g2.setPaint(getTickLabelPaint(tick.getCategory()));
/*      */         
/* 1008 */         CategoryLabelPosition position = this.categoryLabelPositions.getLabelPosition(edge);
/*      */         
/* 1010 */         double x0 = 0.0D;
/* 1011 */         double x1 = 0.0D;
/* 1012 */         double y0 = 0.0D;
/* 1013 */         double y1 = 0.0D;
/* 1014 */         if (edge == RectangleEdge.TOP) {
/* 1015 */           x0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1017 */           x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1019 */           y1 = state.getCursor() - this.categoryLabelPositionOffset;
/* 1020 */           y0 = y1 - state.getMax();
/*      */         }
/* 1022 */         else if (edge == RectangleEdge.BOTTOM) {
/* 1023 */           x0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1025 */           x1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1027 */           y0 = state.getCursor() + this.categoryLabelPositionOffset;
/* 1028 */           y1 = y0 + state.getMax();
/*      */         }
/* 1030 */         else if (edge == RectangleEdge.LEFT) {
/* 1031 */           y0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1033 */           y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1035 */           x1 = state.getCursor() - this.categoryLabelPositionOffset;
/* 1036 */           x0 = x1 - state.getMax();
/*      */         }
/* 1038 */         else if (edge == RectangleEdge.RIGHT) {
/* 1039 */           y0 = getCategoryStart(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1041 */           y1 = getCategoryEnd(categoryIndex, ticks.size(), dataArea, edge);
/*      */           
/* 1043 */           x0 = state.getCursor() + this.categoryLabelPositionOffset;
/* 1044 */           x1 = x0 - state.getMax();
/*      */         }
/* 1046 */         Rectangle2D area = new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
/*      */         
/* 1048 */         Point2D anchorPoint = RectangleAnchor.coordinates(area, position.getCategoryAnchor());
/*      */         
/* 1050 */         TextBlock block = tick.getLabel();
/* 1051 */         block.draw(g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getLabelAnchor(), (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getAngle());
/*      */         
/*      */ 
/*      */ 
/* 1055 */         Shape bounds = block.calculateBounds(g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getLabelAnchor(), (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getAngle());
/*      */         
/*      */ 
/*      */ 
/* 1059 */         if ((plotState != null) && (plotState.getOwner() != null)) {
/* 1060 */           EntityCollection entities = plotState.getOwner().getEntityCollection();
/*      */           
/* 1062 */           if (entities != null) {
/* 1063 */             String tooltip = getCategoryLabelToolTip(tick.getCategory());
/*      */             
/* 1065 */             entities.add(new CategoryLabelEntity(tick.getCategory(), bounds, tooltip, null));
/*      */           }
/*      */         }
/*      */         
/* 1069 */         categoryIndex++;
/*      */       }
/*      */       
/* 1072 */       if (edge.equals(RectangleEdge.TOP)) {
/* 1073 */         double h = state.getMax() + this.categoryLabelPositionOffset;
/* 1074 */         state.cursorUp(h);
/*      */       }
/* 1076 */       else if (edge.equals(RectangleEdge.BOTTOM)) {
/* 1077 */         double h = state.getMax() + this.categoryLabelPositionOffset;
/* 1078 */         state.cursorDown(h);
/*      */       }
/* 1080 */       else if (edge == RectangleEdge.LEFT) {
/* 1081 */         double w = state.getMax() + this.categoryLabelPositionOffset;
/* 1082 */         state.cursorLeft(w);
/*      */       }
/* 1084 */       else if (edge == RectangleEdge.RIGHT) {
/* 1085 */         double w = state.getMax() + this.categoryLabelPositionOffset;
/* 1086 */         state.cursorRight(w);
/*      */       }
/*      */     }
/* 1089 */     return state;
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
/*      */   public List refreshTicks(Graphics2D g2, AxisState state, Rectangle2D dataArea, RectangleEdge edge)
/*      */   {
/* 1107 */     List ticks = new ArrayList();
/*      */     
/*      */ 
/* 1110 */     if ((dataArea.getHeight() <= 0.0D) || (dataArea.getWidth() < 0.0D)) {
/* 1111 */       return ticks;
/*      */     }
/*      */     
/* 1114 */     CategoryPlot plot = (CategoryPlot)getPlot();
/* 1115 */     List categories = plot.getCategoriesForAxis(this);
/* 1116 */     double max = 0.0D;
/*      */     
/* 1118 */     if (categories != null) {
/* 1119 */       CategoryLabelPosition position = this.categoryLabelPositions.getLabelPosition(edge);
/*      */       
/* 1121 */       float r = this.maximumCategoryLabelWidthRatio;
/* 1122 */       if (r <= 0.0D) {
/* 1123 */         r = position.getWidthRatio();
/*      */       }
/*      */       
/* 1126 */       float l = 0.0F;
/* 1127 */       if (position.getWidthType() == CategoryLabelWidthType.CATEGORY) {
/* 1128 */         l = (float)calculateCategorySize(categories.size(), dataArea, edge);
/*      */ 
/*      */ 
/*      */       }
/* 1132 */       else if (RectangleEdge.isLeftOrRight(edge)) {
/* 1133 */         l = (float)dataArea.getWidth();
/*      */       }
/*      */       else {
/* 1136 */         l = (float)dataArea.getHeight();
/*      */       }
/*      */       
/* 1139 */       int categoryIndex = 0;
/* 1140 */       Iterator iterator = categories.iterator();
/* 1141 */       while (iterator.hasNext()) {
/* 1142 */         Comparable category = (Comparable)iterator.next();
/* 1143 */         g2.setFont(getTickLabelFont(category));
/* 1144 */         TextBlock label = createLabel(category, l * r, edge, g2);
/* 1145 */         if ((edge == RectangleEdge.TOP) || (edge == RectangleEdge.BOTTOM)) {
/* 1146 */           max = Math.max(max, calculateTextBlockHeight(label, position, g2));
/*      */ 
/*      */         }
/* 1149 */         else if ((edge == RectangleEdge.LEFT) || (edge == RectangleEdge.RIGHT))
/*      */         {
/* 1151 */           max = Math.max(max, calculateTextBlockWidth(label, position, g2));
/*      */         }
/*      */         
/* 1154 */         Tick tick = new CategoryTick(category, label, position.getLabelAnchor(), position.getRotationAnchor(), position.getAngle());
/*      */         
/*      */ 
/* 1157 */         ticks.add(tick);
/* 1158 */         categoryIndex += 1;
/*      */       }
/*      */     }
/* 1161 */     state.setMax(max);
/* 1162 */     return ticks;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawTickMarks(Graphics2D g2, double cursor, Rectangle2D dataArea, RectangleEdge edge, AxisState state)
/*      */   {
/* 1174 */     Plot p = getPlot();
/* 1175 */     if (p == null) {
/* 1176 */       return;
/*      */     }
/* 1178 */     CategoryPlot plot = (CategoryPlot)p;
/* 1179 */     double il = getTickMarkInsideLength();
/* 1180 */     double ol = getTickMarkOutsideLength();
/* 1181 */     Line2D line = new Line2D.Double();
/* 1182 */     List categories = plot.getCategoriesForAxis(this);
/* 1183 */     g2.setPaint(getTickMarkPaint());
/* 1184 */     g2.setStroke(getTickMarkStroke());
/* 1185 */     if (edge.equals(RectangleEdge.TOP)) {
/* 1186 */       Iterator iterator = categories.iterator();
/* 1187 */       while (iterator.hasNext()) {
/* 1188 */         Comparable key = (Comparable)iterator.next();
/* 1189 */         double x = getCategoryMiddle(key, categories, dataArea, edge);
/* 1190 */         line.setLine(x, cursor, x, cursor + il);
/* 1191 */         g2.draw(line);
/* 1192 */         line.setLine(x, cursor, x, cursor - ol);
/* 1193 */         g2.draw(line);
/*      */       }
/* 1195 */       state.cursorUp(ol);
/*      */     }
/* 1197 */     else if (edge.equals(RectangleEdge.BOTTOM)) {
/* 1198 */       Iterator iterator = categories.iterator();
/* 1199 */       while (iterator.hasNext()) {
/* 1200 */         Comparable key = (Comparable)iterator.next();
/* 1201 */         double x = getCategoryMiddle(key, categories, dataArea, edge);
/* 1202 */         line.setLine(x, cursor, x, cursor - il);
/* 1203 */         g2.draw(line);
/* 1204 */         line.setLine(x, cursor, x, cursor + ol);
/* 1205 */         g2.draw(line);
/*      */       }
/* 1207 */       state.cursorDown(ol);
/*      */     }
/* 1209 */     else if (edge.equals(RectangleEdge.LEFT)) {
/* 1210 */       Iterator iterator = categories.iterator();
/* 1211 */       while (iterator.hasNext()) {
/* 1212 */         Comparable key = (Comparable)iterator.next();
/* 1213 */         double y = getCategoryMiddle(key, categories, dataArea, edge);
/* 1214 */         line.setLine(cursor, y, cursor + il, y);
/* 1215 */         g2.draw(line);
/* 1216 */         line.setLine(cursor, y, cursor - ol, y);
/* 1217 */         g2.draw(line);
/*      */       }
/* 1219 */       state.cursorLeft(ol);
/*      */     }
/* 1221 */     else if (edge.equals(RectangleEdge.RIGHT)) {
/* 1222 */       Iterator iterator = categories.iterator();
/* 1223 */       while (iterator.hasNext()) {
/* 1224 */         Comparable key = (Comparable)iterator.next();
/* 1225 */         double y = getCategoryMiddle(key, categories, dataArea, edge);
/* 1226 */         line.setLine(cursor, y, cursor - il, y);
/* 1227 */         g2.draw(line);
/* 1228 */         line.setLine(cursor, y, cursor + ol, y);
/* 1229 */         g2.draw(line);
/*      */       }
/* 1231 */       state.cursorRight(ol);
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
/*      */   protected TextBlock createLabel(Comparable category, float width, RectangleEdge edge, Graphics2D g2)
/*      */   {
/* 1247 */     TextBlock label = TextUtilities.createTextBlock(category.toString(), getTickLabelFont(category), getTickLabelPaint(category), width, this.maximumCategoryLabelLines, new G2TextMeasurer(g2));
/*      */     
/*      */ 
/* 1250 */     return label;
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
/*      */   protected double calculateTextBlockWidth(TextBlock block, CategoryLabelPosition position, Graphics2D g2)
/*      */   {
/* 1265 */     RectangleInsets insets = getTickLabelInsets();
/* 1266 */     Size2D size = block.calculateDimensions(g2);
/* 1267 */     Rectangle2D box = new Rectangle2D.Double(0.0D, 0.0D, size.getWidth(), size.getHeight());
/*      */     
/* 1269 */     Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(), 0.0F, 0.0F);
/*      */     
/* 1271 */     double w = rotatedBox.getBounds2D().getWidth() + insets.getLeft() + insets.getRight();
/*      */     
/* 1273 */     return w;
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
/*      */   protected double calculateTextBlockHeight(TextBlock block, CategoryLabelPosition position, Graphics2D g2)
/*      */   {
/* 1290 */     RectangleInsets insets = getTickLabelInsets();
/* 1291 */     Size2D size = block.calculateDimensions(g2);
/* 1292 */     Rectangle2D box = new Rectangle2D.Double(0.0D, 0.0D, size.getWidth(), size.getHeight());
/*      */     
/* 1294 */     Shape rotatedBox = ShapeUtilities.rotateShape(box, position.getAngle(), 0.0F, 0.0F);
/*      */     
/* 1296 */     double h = rotatedBox.getBounds2D().getHeight() + insets.getTop() + insets.getBottom();
/*      */     
/* 1298 */     return h;
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
/* 1311 */     CategoryAxis clone = (CategoryAxis)super.clone();
/* 1312 */     clone.tickLabelFontMap = new HashMap(this.tickLabelFontMap);
/* 1313 */     clone.tickLabelPaintMap = new HashMap(this.tickLabelPaintMap);
/* 1314 */     clone.categoryLabelToolTips = new HashMap(this.categoryLabelToolTips);
/* 1315 */     return clone;
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
/* 1326 */     if (obj == this) {
/* 1327 */       return true;
/*      */     }
/* 1329 */     if (!(obj instanceof CategoryAxis)) {
/* 1330 */       return false;
/*      */     }
/* 1332 */     if (!super.equals(obj)) {
/* 1333 */       return false;
/*      */     }
/* 1335 */     CategoryAxis that = (CategoryAxis)obj;
/* 1336 */     if (that.lowerMargin != this.lowerMargin) {
/* 1337 */       return false;
/*      */     }
/* 1339 */     if (that.upperMargin != this.upperMargin) {
/* 1340 */       return false;
/*      */     }
/* 1342 */     if (that.categoryMargin != this.categoryMargin) {
/* 1343 */       return false;
/*      */     }
/* 1345 */     if (that.maximumCategoryLabelWidthRatio != this.maximumCategoryLabelWidthRatio)
/*      */     {
/* 1347 */       return false;
/*      */     }
/* 1349 */     if (that.categoryLabelPositionOffset != this.categoryLabelPositionOffset)
/*      */     {
/* 1351 */       return false;
/*      */     }
/* 1353 */     if (!ObjectUtilities.equal(that.categoryLabelPositions, this.categoryLabelPositions))
/*      */     {
/* 1355 */       return false;
/*      */     }
/* 1357 */     if (!ObjectUtilities.equal(that.categoryLabelToolTips, this.categoryLabelToolTips))
/*      */     {
/* 1359 */       return false;
/*      */     }
/* 1361 */     if (!ObjectUtilities.equal(this.tickLabelFontMap, that.tickLabelFontMap))
/*      */     {
/* 1363 */       return false;
/*      */     }
/* 1365 */     if (!equalPaintMaps(this.tickLabelPaintMap, that.tickLabelPaintMap)) {
/* 1366 */       return false;
/*      */     }
/* 1368 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1377 */     if (getLabel() != null) {
/* 1378 */       return getLabel().hashCode();
/*      */     }
/*      */     
/* 1381 */     return 0;
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
/* 1393 */     stream.defaultWriteObject();
/* 1394 */     writePaintMap(this.tickLabelPaintMap, stream);
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
/* 1407 */     stream.defaultReadObject();
/* 1408 */     this.tickLabelPaintMap = readPaintMap(stream);
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
/*      */   private Map readPaintMap(ObjectInputStream in)
/*      */     throws IOException, ClassNotFoundException
/*      */   {
/* 1426 */     boolean isNull = in.readBoolean();
/* 1427 */     if (isNull) {
/* 1428 */       return null;
/*      */     }
/* 1430 */     Map result = new HashMap();
/* 1431 */     int count = in.readInt();
/* 1432 */     for (int i = 0; i < count; i++) {
/* 1433 */       Comparable category = (Comparable)in.readObject();
/* 1434 */       Paint paint = SerialUtilities.readPaint(in);
/* 1435 */       result.put(category, paint);
/*      */     }
/* 1437 */     return result;
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
/*      */   private void writePaintMap(Map map, ObjectOutputStream out)
/*      */     throws IOException
/*      */   {
/* 1453 */     if (map == null) {
/* 1454 */       out.writeBoolean(true);
/*      */     }
/*      */     else {
/* 1457 */       out.writeBoolean(false);
/* 1458 */       Set keys = map.keySet();
/* 1459 */       int count = keys.size();
/* 1460 */       out.writeInt(count);
/* 1461 */       Iterator iterator = keys.iterator();
/* 1462 */       while (iterator.hasNext()) {
/* 1463 */         Comparable key = (Comparable)iterator.next();
/* 1464 */         out.writeObject(key);
/* 1465 */         SerialUtilities.writePaint((Paint)map.get(key), out);
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
/*      */   private boolean equalPaintMaps(Map map1, Map map2)
/*      */   {
/* 1480 */     if (map1.size() != map2.size()) {
/* 1481 */       return false;
/*      */     }
/* 1483 */     Set entries = map1.entrySet();
/* 1484 */     Iterator iterator = entries.iterator();
/* 1485 */     while (iterator.hasNext()) {
/* 1486 */       Map.Entry entry = (Map.Entry)iterator.next();
/* 1487 */       Paint p1 = (Paint)entry.getValue();
/* 1488 */       Paint p2 = (Paint)map2.get(entry.getKey());
/* 1489 */       if (!PaintUtilities.equal(p1, p2)) {
/* 1490 */         return false;
/*      */       }
/*      */     }
/* 1493 */     return true;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/axis/CategoryAxis.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */