/*      */ package org.jfree.chart.renderer.category;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D.Float;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Point2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*      */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.ItemLabelAnchor;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.StandardGradientPaintTransformer;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
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
/*      */ public class BarRenderer
/*      */   extends AbstractCategoryItemRenderer
/*      */   implements Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 6000649414965887481L;
/*      */   public static final double DEFAULT_ITEM_MARGIN = 0.2D;
/*      */   public static final double BAR_OUTLINE_WIDTH_THRESHOLD = 3.0D;
/*  163 */   private static BarPainter defaultBarPainter = new GradientBarPainter();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static BarPainter getDefaultBarPainter()
/*      */   {
/*  173 */     return defaultBarPainter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setDefaultBarPainter(BarPainter painter)
/*      */   {
/*  184 */     if (painter == null) {
/*  185 */       throw new IllegalArgumentException("Null 'painter' argument.");
/*      */     }
/*  187 */     defaultBarPainter = painter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  193 */   private static boolean defaultShadowsVisible = true;
/*      */   private double itemMargin;
/*      */   private boolean drawBarOutline;
/*      */   private double maximumBarWidth;
/*      */   private double minimumBarLength;
/*      */   private GradientPaintTransformer gradientPaintTransformer;
/*      */   private ItemLabelPosition positiveItemLabelPositionFallback;
/*      */   private ItemLabelPosition negativeItemLabelPositionFallback;
/*      */   private double upperClip;
/*      */   
/*      */   public static boolean getDefaultShadowsVisible()
/*      */   {
/*  205 */     return defaultShadowsVisible;
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
/*      */   public static void setDefaultShadowsVisible(boolean visible)
/*      */   {
/*  218 */     defaultShadowsVisible = visible;
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
/*      */   private double lowerClip;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double base;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean includeBaseInRange;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private BarPainter barPainter;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean shadowsVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint shadowPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double shadowXOffset;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private double shadowYOffset;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BarRenderer()
/*      */   {
/*  308 */     this.base = 0.0D;
/*  309 */     this.includeBaseInRange = true;
/*  310 */     this.itemMargin = 0.2D;
/*  311 */     this.drawBarOutline = false;
/*  312 */     this.maximumBarWidth = 1.0D;
/*      */     
/*  314 */     this.positiveItemLabelPositionFallback = null;
/*  315 */     this.negativeItemLabelPositionFallback = null;
/*  316 */     this.gradientPaintTransformer = new StandardGradientPaintTransformer();
/*  317 */     this.minimumBarLength = 0.0D;
/*  318 */     setBaseLegendShape(new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D));
/*  319 */     this.barPainter = getDefaultBarPainter();
/*  320 */     this.shadowsVisible = getDefaultShadowsVisible();
/*  321 */     this.shadowPaint = Color.gray;
/*  322 */     this.shadowXOffset = 4.0D;
/*  323 */     this.shadowYOffset = 4.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getBase()
/*      */   {
/*  335 */     return this.base;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBase(double base)
/*      */   {
/*  347 */     this.base = base;
/*  348 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getItemMargin()
/*      */   {
/*  360 */     return this.itemMargin;
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
/*      */   public void setItemMargin(double percent)
/*      */   {
/*  374 */     this.itemMargin = percent;
/*  375 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDrawBarOutline()
/*      */   {
/*  386 */     return this.drawBarOutline;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDrawBarOutline(boolean draw)
/*      */   {
/*  398 */     this.drawBarOutline = draw;
/*  399 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMaximumBarWidth()
/*      */   {
/*  411 */     return this.maximumBarWidth;
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
/*      */   public void setMaximumBarWidth(double percent)
/*      */   {
/*  424 */     this.maximumBarWidth = percent;
/*  425 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMinimumBarLength()
/*      */   {
/*  437 */     return this.minimumBarLength;
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
/*      */   public void setMinimumBarLength(double min)
/*      */   {
/*  455 */     if (min < 0.0D) {
/*  456 */       throw new IllegalArgumentException("Requires 'min' >= 0.0");
/*      */     }
/*  458 */     this.minimumBarLength = min;
/*  459 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public GradientPaintTransformer getGradientPaintTransformer()
/*      */   {
/*  471 */     return this.gradientPaintTransformer;
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
/*      */   public void setGradientPaintTransformer(GradientPaintTransformer transformer)
/*      */   {
/*  484 */     this.gradientPaintTransformer = transformer;
/*  485 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ItemLabelPosition getPositiveItemLabelPositionFallback()
/*      */   {
/*  497 */     return this.positiveItemLabelPositionFallback;
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
/*      */   public void setPositiveItemLabelPositionFallback(ItemLabelPosition position)
/*      */   {
/*  511 */     this.positiveItemLabelPositionFallback = position;
/*  512 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ItemLabelPosition getNegativeItemLabelPositionFallback()
/*      */   {
/*  524 */     return this.negativeItemLabelPositionFallback;
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
/*      */   public void setNegativeItemLabelPositionFallback(ItemLabelPosition position)
/*      */   {
/*  538 */     this.negativeItemLabelPositionFallback = position;
/*  539 */     fireChangeEvent();
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
/*      */   public boolean getIncludeBaseInRange()
/*      */   {
/*  555 */     return this.includeBaseInRange;
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
/*      */   public void setIncludeBaseInRange(boolean include)
/*      */   {
/*  571 */     if (this.includeBaseInRange != include) {
/*  572 */       this.includeBaseInRange = include;
/*  573 */       fireChangeEvent();
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
/*      */   public BarPainter getBarPainter()
/*      */   {
/*  587 */     return this.barPainter;
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
/*      */   public void setBarPainter(BarPainter painter)
/*      */   {
/*  601 */     if (painter == null) {
/*  602 */       throw new IllegalArgumentException("Null 'painter' argument.");
/*      */     }
/*  604 */     this.barPainter = painter;
/*  605 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getShadowsVisible()
/*      */   {
/*  617 */     return this.shadowsVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowVisible(boolean visible)
/*      */   {
/*  629 */     this.shadowsVisible = visible;
/*  630 */     fireChangeEvent();
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
/*      */   public Paint getShadowPaint()
/*      */   {
/*  643 */     return this.shadowPaint;
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
/*      */   public void setShadowPaint(Paint paint)
/*      */   {
/*  657 */     if (paint == null) {
/*  658 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  660 */     this.shadowPaint = paint;
/*  661 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getShadowXOffset()
/*      */   {
/*  672 */     return this.shadowXOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowXOffset(double offset)
/*      */   {
/*  684 */     this.shadowXOffset = offset;
/*  685 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getShadowYOffset()
/*      */   {
/*  696 */     return this.shadowYOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowYOffset(double offset)
/*      */   {
/*  708 */     this.shadowYOffset = offset;
/*  709 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getLowerClip()
/*      */   {
/*  720 */     return this.lowerClip;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getUpperClip()
/*      */   {
/*  731 */     return this.upperClip;
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
/*      */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info)
/*      */   {
/*  753 */     CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
/*      */     
/*      */ 
/*      */ 
/*  757 */     ValueAxis rangeAxis = plot.getRangeAxisForDataset(rendererIndex);
/*  758 */     this.lowerClip = rangeAxis.getRange().getLowerBound();
/*  759 */     this.upperClip = rangeAxis.getRange().getUpperBound();
/*      */     
/*      */ 
/*  762 */     calculateBarWidth(plot, dataArea, rendererIndex, state);
/*      */     
/*  764 */     return state;
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
/*      */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state)
/*      */   {
/*  781 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/*  782 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/*  783 */     if (dataset != null) {
/*  784 */       int columns = dataset.getColumnCount();
/*  785 */       int rows = state.getVisibleSeriesCount() >= 0 ? state.getVisibleSeriesCount() : dataset.getRowCount();
/*      */       
/*  787 */       double space = 0.0D;
/*  788 */       PlotOrientation orientation = plot.getOrientation();
/*  789 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  790 */         space = dataArea.getHeight();
/*      */       }
/*  792 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  793 */         space = dataArea.getWidth();
/*      */       }
/*  795 */       double maxWidth = space * getMaximumBarWidth();
/*  796 */       double categoryMargin = 0.0D;
/*  797 */       double currentItemMargin = 0.0D;
/*  798 */       if (columns > 1) {
/*  799 */         categoryMargin = domainAxis.getCategoryMargin();
/*      */       }
/*  801 */       if (rows > 1) {
/*  802 */         currentItemMargin = getItemMargin();
/*      */       }
/*  804 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
/*      */       
/*      */ 
/*  807 */       if (rows * columns > 0) {
/*  808 */         state.setBarWidth(Math.min(used / (rows * columns), maxWidth));
/*      */       }
/*      */       else {
/*  811 */         state.setBarWidth(Math.min(used, maxWidth));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double calculateBarW0(CategoryPlot plot, PlotOrientation orientation, Rectangle2D dataArea, CategoryAxis domainAxis, CategoryItemRendererState state, int row, int column)
/*      */   {
/*  839 */     double space = 0.0D;
/*  840 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  841 */       space = dataArea.getHeight();
/*      */     }
/*      */     else {
/*  844 */       space = dataArea.getWidth();
/*      */     }
/*  846 */     double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */     
/*  848 */     int seriesCount = state.getVisibleSeriesCount() >= 0 ? state.getVisibleSeriesCount() : getRowCount();
/*      */     
/*  850 */     int categoryCount = getColumnCount();
/*  851 */     if (seriesCount > 1) {
/*  852 */       double seriesGap = space * getItemMargin() / (categoryCount * (seriesCount - 1));
/*      */       
/*  854 */       double seriesW = calculateSeriesWidth(space, domainAxis, categoryCount, seriesCount);
/*      */       
/*  856 */       barW0 = barW0 + row * (seriesW + seriesGap) + seriesW / 2.0D - state.getBarWidth() / 2.0D;
/*      */     }
/*      */     else
/*      */     {
/*  860 */       barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*      */     }
/*      */     
/*      */ 
/*  864 */     return barW0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double[] calculateBarL0L1(double value)
/*      */   {
/*  876 */     double lclip = getLowerClip();
/*  877 */     double uclip = getUpperClip();
/*  878 */     double barLow = Math.min(this.base, value);
/*  879 */     double barHigh = Math.max(this.base, value);
/*  880 */     if (barHigh < lclip) {
/*  881 */       return null;
/*      */     }
/*  883 */     if (barLow > uclip) {
/*  884 */       return null;
/*      */     }
/*  886 */     barLow = Math.max(barLow, lclip);
/*  887 */     barHigh = Math.min(barHigh, uclip);
/*  888 */     return new double[] { barLow, barHigh };
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
/*      */   public Range findRangeBounds(CategoryDataset dataset)
/*      */   {
/*  903 */     if (dataset == null) {
/*  904 */       return null;
/*      */     }
/*  906 */     Range result = DatasetUtilities.findRangeBounds(dataset);
/*  907 */     if ((result != null) && 
/*  908 */       (this.includeBaseInRange)) {
/*  909 */       result = Range.expandToInclude(result, this.base);
/*      */     }
/*      */     
/*  912 */     return result;
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
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/*  925 */     CategoryPlot cp = getPlot();
/*  926 */     if (cp == null) {
/*  927 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  931 */     if ((!isSeriesVisible(series)) || (!isSeriesVisibleInLegend(series))) {
/*  932 */       return null;
/*      */     }
/*      */     
/*  935 */     CategoryDataset dataset = cp.getDataset(datasetIndex);
/*  936 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*      */     
/*  938 */     String description = label;
/*  939 */     String toolTipText = null;
/*  940 */     if (getLegendItemToolTipGenerator() != null) {
/*  941 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */     }
/*      */     
/*  944 */     String urlText = null;
/*  945 */     if (getLegendItemURLGenerator() != null) {
/*  946 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */     }
/*      */     
/*  949 */     Shape shape = lookupLegendShape(series);
/*  950 */     Paint paint = lookupSeriesPaint(series);
/*  951 */     Paint outlinePaint = lookupSeriesOutlinePaint(series);
/*  952 */     Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*      */     
/*  954 */     LegendItem result = new LegendItem(label, description, toolTipText, urlText, true, shape, true, paint, isDrawBarOutline(), outlinePaint, outlineStroke, false, new Line2D.Float(), new BasicStroke(1.0F), Color.black);
/*      */     
/*      */ 
/*      */ 
/*  958 */     result.setLabelFont(lookupLegendTextFont(series));
/*  959 */     Paint labelPaint = lookupLegendTextPaint(series);
/*  960 */     if (labelPaint != null) {
/*  961 */       result.setLabelPaint(labelPaint);
/*      */     }
/*  963 */     result.setDataset(dataset);
/*  964 */     result.setDatasetIndex(datasetIndex);
/*  965 */     result.setSeriesKey(dataset.getRowKey(series));
/*  966 */     result.setSeriesIndex(series);
/*  967 */     if (this.gradientPaintTransformer != null) {
/*  968 */       result.setFillPaintTransformer(this.gradientPaintTransformer);
/*      */     }
/*  970 */     return result;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*      */   {
/* 1000 */     int visibleRow = state.getVisibleSeriesIndex(row);
/* 1001 */     if (visibleRow < 0) {
/* 1002 */       return;
/*      */     }
/*      */     
/* 1005 */     Number dataValue = dataset.getValue(row, column);
/* 1006 */     if (dataValue == null) {
/* 1007 */       return;
/*      */     }
/*      */     
/* 1010 */     double value = dataValue.doubleValue();
/* 1011 */     PlotOrientation orientation = plot.getOrientation();
/* 1012 */     double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, state, visibleRow, column);
/*      */     
/* 1014 */     double[] barL0L1 = calculateBarL0L1(value);
/* 1015 */     if (barL0L1 == null) {
/* 1016 */       return;
/*      */     }
/*      */     
/* 1019 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 1020 */     double transL0 = rangeAxis.valueToJava2D(barL0L1[0], dataArea, edge);
/* 1021 */     double transL1 = rangeAxis.valueToJava2D(barL0L1[1], dataArea, edge);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1030 */     boolean positive = value >= this.base;
/* 1031 */     boolean inverted = rangeAxis.isInverted();
/* 1032 */     double barL0 = Math.min(transL0, transL1);
/* 1033 */     double barLength = Math.abs(transL1 - transL0);
/* 1034 */     double barLengthAdj = 0.0D;
/* 1035 */     if ((barLength > 0.0D) && (barLength < getMinimumBarLength())) {
/* 1036 */       barLengthAdj = getMinimumBarLength() - barLength;
/*      */     }
/* 1038 */     double barL0Adj = 0.0D;
/*      */     RectangleEdge barBase;
/* 1040 */     RectangleEdge barBase; if (orientation == PlotOrientation.HORIZONTAL) { RectangleEdge barBase;
/* 1041 */       if (((positive) && (inverted)) || ((!positive) && (!inverted))) {
/* 1042 */         barL0Adj = barLengthAdj;
/* 1043 */         barBase = RectangleEdge.RIGHT;
/*      */       }
/*      */       else {
/* 1046 */         barBase = RectangleEdge.LEFT;
/*      */       }
/*      */     } else {
/*      */       RectangleEdge barBase;
/* 1050 */       if (((positive) && (!inverted)) || ((!positive) && (inverted))) {
/* 1051 */         barL0Adj = barLengthAdj;
/* 1052 */         barBase = RectangleEdge.BOTTOM;
/*      */       }
/*      */       else {
/* 1055 */         barBase = RectangleEdge.TOP;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1060 */     Rectangle2D bar = null;
/* 1061 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1062 */       bar = new Rectangle2D.Double(barL0 - barL0Adj, barW0, barLength + barLengthAdj, state.getBarWidth());
/*      */     }
/*      */     else
/*      */     {
/* 1066 */       bar = new Rectangle2D.Double(barW0, barL0 - barL0Adj, state.getBarWidth(), barLength + barLengthAdj);
/*      */     }
/*      */     
/* 1069 */     if (getShadowsVisible()) {
/* 1070 */       this.barPainter.paintBarShadow(g2, this, row, column, bar, barBase, true);
/*      */     }
/*      */     
/* 1073 */     this.barPainter.paintBar(g2, this, row, column, bar, barBase);
/*      */     
/* 1075 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*      */     
/* 1077 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 1078 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1083 */     int datasetIndex = plot.indexOf(dataset);
/* 1084 */     updateCrosshairValues(state.getCrosshairState(), dataset.getRowKey(row), dataset.getColumnKey(column), value, datasetIndex, barW0, barL0, orientation);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1089 */     EntityCollection entities = state.getEntityCollection();
/* 1090 */     if (entities != null) {
/* 1091 */       addItemEntity(entities, dataset, row, column, bar);
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
/*      */   protected double calculateSeriesWidth(double space, CategoryAxis axis, int categories, int series)
/*      */   {
/* 1108 */     double factor = 1.0D - getItemMargin() - axis.getLowerMargin() - axis.getUpperMargin();
/*      */     
/* 1110 */     if (categories > 1) {
/* 1111 */       factor -= axis.getCategoryMargin();
/*      */     }
/* 1113 */     return space * factor / (categories * series);
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
/*      */   protected void drawItemLabel(Graphics2D g2, CategoryDataset data, int row, int column, CategoryPlot plot, CategoryItemLabelGenerator generator, Rectangle2D bar, boolean negative)
/*      */   {
/* 1138 */     String label = generator.generateLabel(data, row, column);
/* 1139 */     if (label == null) {
/* 1140 */       return;
/*      */     }
/*      */     
/* 1143 */     Font labelFont = getItemLabelFont(row, column);
/* 1144 */     g2.setFont(labelFont);
/* 1145 */     Paint paint = getItemLabelPaint(row, column);
/* 1146 */     g2.setPaint(paint);
/*      */     
/*      */ 
/* 1149 */     ItemLabelPosition position = null;
/* 1150 */     if (!negative) {
/* 1151 */       position = getPositiveItemLabelPosition(row, column);
/*      */     }
/*      */     else {
/* 1154 */       position = getNegativeItemLabelPosition(row, column);
/*      */     }
/*      */     
/*      */ 
/* 1158 */     Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), bar, plot.getOrientation());
/*      */     
/*      */ 
/* 1161 */     if (isInternalAnchor(position.getItemLabelAnchor())) {
/* 1162 */       Shape bounds = TextUtilities.calculateRotatedStringBounds(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1167 */       if ((bounds != null) && 
/* 1168 */         (!bar.contains(bounds.getBounds2D()))) {
/* 1169 */         if (!negative) {
/* 1170 */           position = getPositiveItemLabelPositionFallback();
/*      */         }
/*      */         else {
/* 1173 */           position = getNegativeItemLabelPositionFallback();
/*      */         }
/* 1175 */         if (position != null) {
/* 1176 */           anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), bar, plot.getOrientation());
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1185 */     if (position != null) {
/* 1186 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
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
/*      */   private Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor, Rectangle2D bar, PlotOrientation orientation)
/*      */   {
/* 1206 */     Point2D result = null;
/* 1207 */     double offset = getItemLabelAnchorOffset();
/* 1208 */     double x0 = bar.getX() - offset;
/* 1209 */     double x1 = bar.getX();
/* 1210 */     double x2 = bar.getX() + offset;
/* 1211 */     double x3 = bar.getCenterX();
/* 1212 */     double x4 = bar.getMaxX() - offset;
/* 1213 */     double x5 = bar.getMaxX();
/* 1214 */     double x6 = bar.getMaxX() + offset;
/*      */     
/* 1216 */     double y0 = bar.getMaxY() + offset;
/* 1217 */     double y1 = bar.getMaxY();
/* 1218 */     double y2 = bar.getMaxY() - offset;
/* 1219 */     double y3 = bar.getCenterY();
/* 1220 */     double y4 = bar.getMinY() + offset;
/* 1221 */     double y5 = bar.getMinY();
/* 1222 */     double y6 = bar.getMinY() - offset;
/*      */     
/* 1224 */     if (anchor == ItemLabelAnchor.CENTER) {
/* 1225 */       result = new Point2D.Double(x3, y3);
/*      */     }
/* 1227 */     else if (anchor == ItemLabelAnchor.INSIDE1) {
/* 1228 */       result = new Point2D.Double(x4, y4);
/*      */     }
/* 1230 */     else if (anchor == ItemLabelAnchor.INSIDE2) {
/* 1231 */       result = new Point2D.Double(x4, y4);
/*      */     }
/* 1233 */     else if (anchor == ItemLabelAnchor.INSIDE3) {
/* 1234 */       result = new Point2D.Double(x4, y3);
/*      */     }
/* 1236 */     else if (anchor == ItemLabelAnchor.INSIDE4) {
/* 1237 */       result = new Point2D.Double(x4, y2);
/*      */     }
/* 1239 */     else if (anchor == ItemLabelAnchor.INSIDE5) {
/* 1240 */       result = new Point2D.Double(x4, y2);
/*      */     }
/* 1242 */     else if (anchor == ItemLabelAnchor.INSIDE6) {
/* 1243 */       result = new Point2D.Double(x3, y2);
/*      */     }
/* 1245 */     else if (anchor == ItemLabelAnchor.INSIDE7) {
/* 1246 */       result = new Point2D.Double(x2, y2);
/*      */     }
/* 1248 */     else if (anchor == ItemLabelAnchor.INSIDE8) {
/* 1249 */       result = new Point2D.Double(x2, y2);
/*      */     }
/* 1251 */     else if (anchor == ItemLabelAnchor.INSIDE9) {
/* 1252 */       result = new Point2D.Double(x2, y3);
/*      */     }
/* 1254 */     else if (anchor == ItemLabelAnchor.INSIDE10) {
/* 1255 */       result = new Point2D.Double(x2, y4);
/*      */     }
/* 1257 */     else if (anchor == ItemLabelAnchor.INSIDE11) {
/* 1258 */       result = new Point2D.Double(x2, y4);
/*      */     }
/* 1260 */     else if (anchor == ItemLabelAnchor.INSIDE12) {
/* 1261 */       result = new Point2D.Double(x3, y4);
/*      */     }
/* 1263 */     else if (anchor == ItemLabelAnchor.OUTSIDE1) {
/* 1264 */       result = new Point2D.Double(x5, y6);
/*      */     }
/* 1266 */     else if (anchor == ItemLabelAnchor.OUTSIDE2) {
/* 1267 */       result = new Point2D.Double(x6, y5);
/*      */     }
/* 1269 */     else if (anchor == ItemLabelAnchor.OUTSIDE3) {
/* 1270 */       result = new Point2D.Double(x6, y3);
/*      */     }
/* 1272 */     else if (anchor == ItemLabelAnchor.OUTSIDE4) {
/* 1273 */       result = new Point2D.Double(x6, y1);
/*      */     }
/* 1275 */     else if (anchor == ItemLabelAnchor.OUTSIDE5) {
/* 1276 */       result = new Point2D.Double(x5, y0);
/*      */     }
/* 1278 */     else if (anchor == ItemLabelAnchor.OUTSIDE6) {
/* 1279 */       result = new Point2D.Double(x3, y0);
/*      */     }
/* 1281 */     else if (anchor == ItemLabelAnchor.OUTSIDE7) {
/* 1282 */       result = new Point2D.Double(x1, y0);
/*      */     }
/* 1284 */     else if (anchor == ItemLabelAnchor.OUTSIDE8) {
/* 1285 */       result = new Point2D.Double(x0, y1);
/*      */     }
/* 1287 */     else if (anchor == ItemLabelAnchor.OUTSIDE9) {
/* 1288 */       result = new Point2D.Double(x0, y3);
/*      */     }
/* 1290 */     else if (anchor == ItemLabelAnchor.OUTSIDE10) {
/* 1291 */       result = new Point2D.Double(x0, y5);
/*      */     }
/* 1293 */     else if (anchor == ItemLabelAnchor.OUTSIDE11) {
/* 1294 */       result = new Point2D.Double(x1, y6);
/*      */     }
/* 1296 */     else if (anchor == ItemLabelAnchor.OUTSIDE12) {
/* 1297 */       result = new Point2D.Double(x3, y6);
/*      */     }
/*      */     
/* 1300 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean isInternalAnchor(ItemLabelAnchor anchor)
/*      */   {
/* 1312 */     return (anchor == ItemLabelAnchor.CENTER) || (anchor == ItemLabelAnchor.INSIDE1) || (anchor == ItemLabelAnchor.INSIDE2) || (anchor == ItemLabelAnchor.INSIDE3) || (anchor == ItemLabelAnchor.INSIDE4) || (anchor == ItemLabelAnchor.INSIDE5) || (anchor == ItemLabelAnchor.INSIDE6) || (anchor == ItemLabelAnchor.INSIDE7) || (anchor == ItemLabelAnchor.INSIDE8) || (anchor == ItemLabelAnchor.INSIDE9) || (anchor == ItemLabelAnchor.INSIDE10) || (anchor == ItemLabelAnchor.INSIDE11) || (anchor == ItemLabelAnchor.INSIDE12);
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
/*      */   public boolean equals(Object obj)
/*      */   {
/* 1335 */     if (obj == this) {
/* 1336 */       return true;
/*      */     }
/* 1338 */     if (!(obj instanceof BarRenderer)) {
/* 1339 */       return false;
/*      */     }
/* 1341 */     BarRenderer that = (BarRenderer)obj;
/* 1342 */     if (this.base != that.base) {
/* 1343 */       return false;
/*      */     }
/* 1345 */     if (this.itemMargin != that.itemMargin) {
/* 1346 */       return false;
/*      */     }
/* 1348 */     if (this.drawBarOutline != that.drawBarOutline) {
/* 1349 */       return false;
/*      */     }
/* 1351 */     if (this.maximumBarWidth != that.maximumBarWidth) {
/* 1352 */       return false;
/*      */     }
/* 1354 */     if (this.minimumBarLength != that.minimumBarLength) {
/* 1355 */       return false;
/*      */     }
/* 1357 */     if (!ObjectUtilities.equal(this.gradientPaintTransformer, that.gradientPaintTransformer))
/*      */     {
/* 1359 */       return false;
/*      */     }
/* 1361 */     if (!ObjectUtilities.equal(this.positiveItemLabelPositionFallback, that.positiveItemLabelPositionFallback))
/*      */     {
/* 1363 */       return false;
/*      */     }
/* 1365 */     if (!ObjectUtilities.equal(this.negativeItemLabelPositionFallback, that.negativeItemLabelPositionFallback))
/*      */     {
/* 1367 */       return false;
/*      */     }
/* 1369 */     if (!this.barPainter.equals(that.barPainter)) {
/* 1370 */       return false;
/*      */     }
/* 1372 */     if (this.shadowsVisible != that.shadowsVisible) {
/* 1373 */       return false;
/*      */     }
/* 1375 */     if (!PaintUtilities.equal(this.shadowPaint, that.shadowPaint)) {
/* 1376 */       return false;
/*      */     }
/* 1378 */     if (this.shadowXOffset != that.shadowXOffset) {
/* 1379 */       return false;
/*      */     }
/* 1381 */     if (this.shadowYOffset != that.shadowYOffset) {
/* 1382 */       return false;
/*      */     }
/* 1384 */     return super.equals(obj);
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
/* 1395 */     stream.defaultWriteObject();
/* 1396 */     SerialUtilities.writePaint(this.shadowPaint, stream);
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
/* 1409 */     stream.defaultReadObject();
/* 1410 */     this.shadowPaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/BarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */