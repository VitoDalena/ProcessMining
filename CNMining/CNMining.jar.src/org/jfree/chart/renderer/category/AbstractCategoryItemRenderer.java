/*      */ package org.jfree.chart.renderer.category;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.GradientPaint;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.CategoryItemEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*      */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.plot.CategoryCrosshairState;
/*      */ import org.jfree.chart.plot.CategoryMarker;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.IntervalMarker;
/*      */ import org.jfree.chart.plot.Marker;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueMarker;
/*      */ import org.jfree.chart.renderer.AbstractRenderer;
/*      */ import org.jfree.chart.urls.CategoryURLGenerator;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.LengthAdjustmentType;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractCategoryItemRenderer
/*      */   extends AbstractRenderer
/*      */   implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 1247553218442497391L;
/*      */   private CategoryPlot plot;
/*      */   private ObjectList itemLabelGeneratorList;
/*      */   private CategoryItemLabelGenerator baseItemLabelGenerator;
/*      */   private ObjectList toolTipGeneratorList;
/*      */   private CategoryToolTipGenerator baseToolTipGenerator;
/*      */   private ObjectList itemURLGeneratorList;
/*      */   private CategoryURLGenerator baseItemURLGenerator;
/*      */   private CategorySeriesLabelGenerator legendItemLabelGenerator;
/*      */   private CategorySeriesLabelGenerator legendItemToolTipGenerator;
/*      */   private CategorySeriesLabelGenerator legendItemURLGenerator;
/*      */   private transient int rowCount;
/*      */   private transient int columnCount;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private CategoryItemLabelGenerator itemLabelGenerator;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private CategoryToolTipGenerator toolTipGenerator;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private CategoryURLGenerator itemURLGenerator;
/*      */   
/*      */   protected AbstractCategoryItemRenderer()
/*      */   {
/*  219 */     this.itemLabelGenerator = null;
/*  220 */     this.itemLabelGeneratorList = new ObjectList();
/*  221 */     this.toolTipGenerator = null;
/*  222 */     this.toolTipGeneratorList = new ObjectList();
/*  223 */     this.itemURLGenerator = null;
/*  224 */     this.itemURLGeneratorList = new ObjectList();
/*  225 */     this.legendItemLabelGenerator = new StandardCategorySeriesLabelGenerator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPassCount()
/*      */   {
/*  237 */     return 1;
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
/*      */   public CategoryPlot getPlot()
/*      */   {
/*  250 */     return this.plot;
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
/*      */   public void setPlot(CategoryPlot plot)
/*      */   {
/*  263 */     if (plot == null) {
/*  264 */       throw new IllegalArgumentException("Null 'plot' argument.");
/*      */     }
/*  266 */     this.plot = plot;
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
/*      */   public CategoryItemLabelGenerator getItemLabelGenerator(int row, int column)
/*      */   {
/*  284 */     return getSeriesItemLabelGenerator(row);
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
/*      */   public CategoryItemLabelGenerator getSeriesItemLabelGenerator(int series)
/*      */   {
/*  299 */     if (this.itemLabelGenerator != null) {
/*  300 */       return this.itemLabelGenerator;
/*      */     }
/*      */     
/*      */ 
/*  304 */     CategoryItemLabelGenerator generator = (CategoryItemLabelGenerator)this.itemLabelGeneratorList.get(series);
/*      */     
/*  306 */     if (generator == null) {
/*  307 */       generator = this.baseItemLabelGenerator;
/*      */     }
/*  309 */     return generator;
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
/*      */   public void setSeriesItemLabelGenerator(int series, CategoryItemLabelGenerator generator)
/*      */   {
/*  324 */     this.itemLabelGeneratorList.set(series, generator);
/*  325 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryItemLabelGenerator getBaseItemLabelGenerator()
/*      */   {
/*  336 */     return this.baseItemLabelGenerator;
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
/*      */   public void setBaseItemLabelGenerator(CategoryItemLabelGenerator generator)
/*      */   {
/*  349 */     this.baseItemLabelGenerator = generator;
/*  350 */     fireChangeEvent();
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
/*      */   public CategoryToolTipGenerator getToolTipGenerator(int row, int column)
/*      */   {
/*  369 */     CategoryToolTipGenerator result = null;
/*  370 */     if (this.toolTipGenerator != null) {
/*  371 */       result = this.toolTipGenerator;
/*      */     }
/*      */     else {
/*  374 */       result = getSeriesToolTipGenerator(row);
/*  375 */       if (result == null) {
/*  376 */         result = this.baseToolTipGenerator;
/*      */       }
/*      */     }
/*  379 */     return result;
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
/*      */   public CategoryToolTipGenerator getSeriesToolTipGenerator(int series)
/*      */   {
/*  393 */     return (CategoryToolTipGenerator)this.toolTipGeneratorList.get(series);
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
/*      */   public void setSeriesToolTipGenerator(int series, CategoryToolTipGenerator generator)
/*      */   {
/*  407 */     this.toolTipGeneratorList.set(series, generator);
/*  408 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryToolTipGenerator getBaseToolTipGenerator()
/*      */   {
/*  419 */     return this.baseToolTipGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseToolTipGenerator(CategoryToolTipGenerator generator)
/*      */   {
/*  431 */     this.baseToolTipGenerator = generator;
/*  432 */     fireChangeEvent();
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
/*      */   public CategoryURLGenerator getItemURLGenerator(int row, int column)
/*      */   {
/*  448 */     return getSeriesItemURLGenerator(row);
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
/*      */   public CategoryURLGenerator getSeriesItemURLGenerator(int series)
/*      */   {
/*  463 */     if (this.itemURLGenerator != null) {
/*  464 */       return this.itemURLGenerator;
/*      */     }
/*      */     
/*      */ 
/*  468 */     CategoryURLGenerator generator = (CategoryURLGenerator)this.itemURLGeneratorList.get(series);
/*      */     
/*  470 */     if (generator == null) {
/*  471 */       generator = this.baseItemURLGenerator;
/*      */     }
/*  473 */     return generator;
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
/*      */   public void setSeriesItemURLGenerator(int series, CategoryURLGenerator generator)
/*      */   {
/*  488 */     this.itemURLGeneratorList.set(series, generator);
/*  489 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryURLGenerator getBaseItemURLGenerator()
/*      */   {
/*  500 */     return this.baseItemURLGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseItemURLGenerator(CategoryURLGenerator generator)
/*      */   {
/*  512 */     this.baseItemURLGenerator = generator;
/*  513 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRowCount()
/*      */   {
/*  523 */     return this.rowCount;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getColumnCount()
/*      */   {
/*  533 */     return this.columnCount;
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
/*      */   protected CategoryItemRendererState createState(PlotRenderingInfo info)
/*      */   {
/*  549 */     return new CategoryItemRendererState(info);
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
/*      */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info)
/*      */   {
/*  573 */     setPlot(plot);
/*  574 */     CategoryDataset data = plot.getDataset(rendererIndex);
/*  575 */     if (data != null) {
/*  576 */       this.rowCount = data.getRowCount();
/*  577 */       this.columnCount = data.getColumnCount();
/*      */     }
/*      */     else {
/*  580 */       this.rowCount = 0;
/*  581 */       this.columnCount = 0;
/*      */     }
/*  583 */     CategoryItemRendererState state = createState(info);
/*  584 */     int[] visibleSeriesTemp = new int[this.rowCount];
/*  585 */     int visibleSeriesCount = 0;
/*  586 */     for (int row = 0; row < this.rowCount; row++) {
/*  587 */       if (isSeriesVisible(row)) {
/*  588 */         visibleSeriesTemp[visibleSeriesCount] = row;
/*  589 */         visibleSeriesCount++;
/*      */       }
/*      */     }
/*  592 */     int[] visibleSeries = new int[visibleSeriesCount];
/*  593 */     System.arraycopy(visibleSeriesTemp, 0, visibleSeries, 0, visibleSeriesCount);
/*      */     
/*  595 */     state.setVisibleSeriesArray(visibleSeries);
/*  596 */     return state;
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
/*      */   public Range findRangeBounds(CategoryDataset dataset)
/*      */   {
/*  609 */     return findRangeBounds(dataset, false);
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
/*      */   protected Range findRangeBounds(CategoryDataset dataset, boolean includeInterval)
/*      */   {
/*  626 */     if (dataset == null) {
/*  627 */       return null;
/*      */     }
/*  629 */     if (getDataBoundsIncludesVisibleSeriesOnly()) {
/*  630 */       List visibleSeriesKeys = new ArrayList();
/*  631 */       int seriesCount = dataset.getRowCount();
/*  632 */       for (int s = 0; s < seriesCount; s++) {
/*  633 */         if (isSeriesVisible(s)) {
/*  634 */           visibleSeriesKeys.add(dataset.getRowKey(s));
/*      */         }
/*      */       }
/*  637 */       return DatasetUtilities.findRangeBounds(dataset, visibleSeriesKeys, includeInterval);
/*      */     }
/*      */     
/*      */ 
/*  641 */     return DatasetUtilities.findRangeBounds(dataset, includeInterval);
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
/*      */   public double getItemMiddle(Comparable rowKey, Comparable columnKey, CategoryDataset dataset, CategoryAxis axis, Rectangle2D area, RectangleEdge edge)
/*      */   {
/*  662 */     return axis.getCategoryMiddle(columnKey, dataset.getColumnKeys(), area, edge);
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
/*      */   public void drawBackground(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea)
/*      */   {
/*  679 */     plot.drawBackground(g2, dataArea);
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
/*      */   public void drawOutline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea)
/*      */   {
/*  696 */     plot.drawOutline(g2, dataArea);
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
/*      */   public void drawDomainGridline(Graphics2D g2, CategoryPlot plot, Rectangle2D dataArea, double value)
/*      */   {
/*  721 */     Line2D line = null;
/*  722 */     PlotOrientation orientation = plot.getOrientation();
/*      */     
/*  724 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  725 */       line = new Line2D.Double(dataArea.getMinX(), value, dataArea.getMaxX(), value);
/*      */ 
/*      */     }
/*  728 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  729 */       line = new Line2D.Double(value, dataArea.getMinY(), value, dataArea.getMaxY());
/*      */     }
/*      */     
/*      */ 
/*  733 */     Paint paint = plot.getDomainGridlinePaint();
/*  734 */     if (paint == null) {
/*  735 */       paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
/*      */     }
/*  737 */     g2.setPaint(paint);
/*      */     
/*  739 */     Stroke stroke = plot.getDomainGridlineStroke();
/*  740 */     if (stroke == null) {
/*  741 */       stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
/*      */     }
/*  743 */     g2.setStroke(stroke);
/*      */     
/*  745 */     g2.draw(line);
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
/*      */   public void drawRangeGridline(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value)
/*      */   {
/*  767 */     Range range = axis.getRange();
/*  768 */     if (!range.contains(value)) {
/*  769 */       return;
/*      */     }
/*      */     
/*  772 */     PlotOrientation orientation = plot.getOrientation();
/*  773 */     double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*  774 */     Line2D line = null;
/*  775 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  776 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */ 
/*      */     }
/*  779 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  780 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */     }
/*      */     
/*      */ 
/*  784 */     Paint paint = plot.getRangeGridlinePaint();
/*  785 */     if (paint == null) {
/*  786 */       paint = CategoryPlot.DEFAULT_GRIDLINE_PAINT;
/*      */     }
/*  788 */     g2.setPaint(paint);
/*      */     
/*  790 */     Stroke stroke = plot.getRangeGridlineStroke();
/*  791 */     if (stroke == null) {
/*  792 */       stroke = CategoryPlot.DEFAULT_GRIDLINE_STROKE;
/*      */     }
/*  794 */     g2.setStroke(stroke);
/*      */     
/*  796 */     g2.draw(line);
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
/*      */   public void drawRangeLine(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Rectangle2D dataArea, double value, Paint paint, Stroke stroke)
/*      */   {
/*  821 */     Range range = axis.getRange();
/*  822 */     if (!range.contains(value)) {
/*  823 */       return;
/*      */     }
/*      */     
/*  826 */     PlotOrientation orientation = plot.getOrientation();
/*  827 */     Line2D line = null;
/*  828 */     double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*  829 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  830 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */ 
/*      */     }
/*  833 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  834 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */     }
/*      */     
/*      */ 
/*  838 */     g2.setPaint(paint);
/*  839 */     g2.setStroke(stroke);
/*  840 */     g2.draw(line);
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
/*      */   public void drawDomainMarker(Graphics2D g2, CategoryPlot plot, CategoryAxis axis, CategoryMarker marker, Rectangle2D dataArea)
/*      */   {
/*  862 */     Comparable category = marker.getKey();
/*  863 */     CategoryDataset dataset = plot.getDataset(plot.getIndexOf(this));
/*  864 */     int columnIndex = dataset.getColumnIndex(category);
/*  865 */     if (columnIndex < 0) {
/*  866 */       return;
/*      */     }
/*      */     
/*  869 */     Composite savedComposite = g2.getComposite();
/*  870 */     g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */     
/*      */ 
/*  873 */     PlotOrientation orientation = plot.getOrientation();
/*  874 */     Rectangle2D bounds = null;
/*  875 */     if (marker.getDrawAsLine()) {
/*  876 */       double v = axis.getCategoryMiddle(columnIndex, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */       
/*      */ 
/*  879 */       Line2D line = null;
/*  880 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  881 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */ 
/*      */       }
/*  884 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  885 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */       }
/*      */       
/*  888 */       g2.setPaint(marker.getPaint());
/*  889 */       g2.setStroke(marker.getStroke());
/*  890 */       g2.draw(line);
/*  891 */       bounds = line.getBounds2D();
/*      */     }
/*      */     else {
/*  894 */       double v0 = axis.getCategoryStart(columnIndex, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */       
/*      */ 
/*  897 */       double v1 = axis.getCategoryEnd(columnIndex, dataset.getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */       
/*      */ 
/*  900 */       Rectangle2D area = null;
/*  901 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  902 */         area = new Rectangle2D.Double(dataArea.getMinX(), v0, dataArea.getWidth(), v1 - v0);
/*      */ 
/*      */       }
/*  905 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  906 */         area = new Rectangle2D.Double(v0, dataArea.getMinY(), v1 - v0, dataArea.getHeight());
/*      */       }
/*      */       
/*  909 */       g2.setPaint(marker.getPaint());
/*  910 */       g2.fill(area);
/*  911 */       bounds = area;
/*      */     }
/*      */     
/*  914 */     String label = marker.getLabel();
/*  915 */     RectangleAnchor anchor = marker.getLabelAnchor();
/*  916 */     if (label != null) {
/*  917 */       Font labelFont = marker.getLabelFont();
/*  918 */       g2.setFont(labelFont);
/*  919 */       g2.setPaint(marker.getLabelPaint());
/*  920 */       Point2D coordinates = calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, bounds, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*      */       
/*      */ 
/*  923 */       TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */     }
/*      */     
/*      */ 
/*  927 */     g2.setComposite(savedComposite);
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
/*      */   public void drawRangeMarker(Graphics2D g2, CategoryPlot plot, ValueAxis axis, Marker marker, Rectangle2D dataArea)
/*      */   {
/*  948 */     if ((marker instanceof ValueMarker)) {
/*  949 */       ValueMarker vm = (ValueMarker)marker;
/*  950 */       double value = vm.getValue();
/*  951 */       Range range = axis.getRange();
/*      */       
/*  953 */       if (!range.contains(value)) {
/*  954 */         return;
/*      */       }
/*      */       
/*  957 */       Composite savedComposite = g2.getComposite();
/*  958 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */       
/*      */ 
/*  961 */       PlotOrientation orientation = plot.getOrientation();
/*  962 */       double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*      */       
/*  964 */       Line2D line = null;
/*  965 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  966 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */ 
/*      */       }
/*  969 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  970 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */       }
/*      */       
/*      */ 
/*  974 */       g2.setPaint(marker.getPaint());
/*  975 */       g2.setStroke(marker.getStroke());
/*  976 */       g2.draw(line);
/*      */       
/*  978 */       String label = marker.getLabel();
/*  979 */       RectangleAnchor anchor = marker.getLabelAnchor();
/*  980 */       if (label != null) {
/*  981 */         Font labelFont = marker.getLabelFont();
/*  982 */         g2.setFont(labelFont);
/*  983 */         g2.setPaint(marker.getLabelPaint());
/*  984 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/*      */         
/*      */ 
/*      */ 
/*  988 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       }
/*      */       
/*      */ 
/*  992 */       g2.setComposite(savedComposite);
/*      */     }
/*  994 */     else if ((marker instanceof IntervalMarker)) {
/*  995 */       IntervalMarker im = (IntervalMarker)marker;
/*  996 */       double start = im.getStartValue();
/*  997 */       double end = im.getEndValue();
/*  998 */       Range range = axis.getRange();
/*  999 */       if (!range.intersects(start, end)) {
/* 1000 */         return;
/*      */       }
/*      */       
/* 1003 */       Composite savedComposite = g2.getComposite();
/* 1004 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */       
/*      */ 
/* 1007 */       double start2d = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
/*      */       
/* 1009 */       double end2d = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
/*      */       
/* 1011 */       double low = Math.min(start2d, end2d);
/* 1012 */       double high = Math.max(start2d, end2d);
/*      */       
/* 1014 */       PlotOrientation orientation = plot.getOrientation();
/* 1015 */       Rectangle2D rect = null;
/* 1016 */       if (orientation == PlotOrientation.HORIZONTAL)
/*      */       {
/* 1018 */         low = Math.max(low, dataArea.getMinX());
/* 1019 */         high = Math.min(high, dataArea.getMaxX());
/* 1020 */         rect = new Rectangle2D.Double(low, dataArea.getMinY(), high - low, dataArea.getHeight());
/*      */ 
/*      */ 
/*      */       }
/* 1024 */       else if (orientation == PlotOrientation.VERTICAL)
/*      */       {
/* 1026 */         low = Math.max(low, dataArea.getMinY());
/* 1027 */         high = Math.min(high, dataArea.getMaxY());
/* 1028 */         rect = new Rectangle2D.Double(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
/*      */       }
/*      */       
/*      */ 
/* 1032 */       Paint p = marker.getPaint();
/* 1033 */       if ((p instanceof GradientPaint)) {
/* 1034 */         GradientPaint gp = (GradientPaint)p;
/* 1035 */         GradientPaintTransformer t = im.getGradientPaintTransformer();
/* 1036 */         if (t != null) {
/* 1037 */           gp = t.transform(gp, rect);
/*      */         }
/* 1039 */         g2.setPaint(gp);
/*      */       }
/*      */       else {
/* 1042 */         g2.setPaint(p);
/*      */       }
/* 1044 */       g2.fill(rect);
/*      */       
/*      */ 
/* 1047 */       if ((im.getOutlinePaint() != null) && (im.getOutlineStroke() != null)) {
/* 1048 */         if (orientation == PlotOrientation.VERTICAL) {
/* 1049 */           Line2D line = new Line2D.Double();
/* 1050 */           double x0 = dataArea.getMinX();
/* 1051 */           double x1 = dataArea.getMaxX();
/* 1052 */           g2.setPaint(im.getOutlinePaint());
/* 1053 */           g2.setStroke(im.getOutlineStroke());
/* 1054 */           if (range.contains(start)) {
/* 1055 */             line.setLine(x0, start2d, x1, start2d);
/* 1056 */             g2.draw(line);
/*      */           }
/* 1058 */           if (range.contains(end)) {
/* 1059 */             line.setLine(x0, end2d, x1, end2d);
/* 1060 */             g2.draw(line);
/*      */           }
/*      */         }
/*      */         else {
/* 1064 */           Line2D line = new Line2D.Double();
/* 1065 */           double y0 = dataArea.getMinY();
/* 1066 */           double y1 = dataArea.getMaxY();
/* 1067 */           g2.setPaint(im.getOutlinePaint());
/* 1068 */           g2.setStroke(im.getOutlineStroke());
/* 1069 */           if (range.contains(start)) {
/* 1070 */             line.setLine(start2d, y0, start2d, y1);
/* 1071 */             g2.draw(line);
/*      */           }
/* 1073 */           if (range.contains(end)) {
/* 1074 */             line.setLine(end2d, y0, end2d, y1);
/* 1075 */             g2.draw(line);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1080 */       String label = marker.getLabel();
/* 1081 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1082 */       if (label != null) {
/* 1083 */         Font labelFont = marker.getLabelFont();
/* 1084 */         g2.setFont(labelFont);
/* 1085 */         g2.setPaint(marker.getLabelPaint());
/* 1086 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*      */         
/*      */ 
/*      */ 
/* 1090 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       }
/*      */       
/*      */ 
/* 1094 */       g2.setComposite(savedComposite);
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
/*      */   protected Point2D calculateDomainMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetType, RectangleAnchor anchor)
/*      */   {
/* 1120 */     Rectangle2D anchorRect = null;
/* 1121 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1122 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
/*      */ 
/*      */     }
/* 1125 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 1126 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
/*      */     }
/*      */     
/* 1129 */     return RectangleAnchor.coordinates(anchorRect, anchor);
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
/*      */   protected Point2D calculateRangeMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetType, RectangleAnchor anchor)
/*      */   {
/* 1154 */     Rectangle2D anchorRect = null;
/* 1155 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1156 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
/*      */ 
/*      */     }
/* 1159 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 1160 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
/*      */     }
/*      */     
/* 1163 */     return RectangleAnchor.coordinates(anchorRect, anchor);
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
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/* 1181 */     CategoryPlot p = getPlot();
/* 1182 */     if (p == null) {
/* 1183 */       return null;
/*      */     }
/*      */     
/*      */ 
/* 1187 */     if ((!isSeriesVisible(series)) || (!isSeriesVisibleInLegend(series))) {
/* 1188 */       return null;
/*      */     }
/*      */     
/* 1191 */     CategoryDataset dataset = p.getDataset(datasetIndex);
/* 1192 */     String label = this.legendItemLabelGenerator.generateLabel(dataset, series);
/*      */     
/* 1194 */     String description = label;
/* 1195 */     String toolTipText = null;
/* 1196 */     if (this.legendItemToolTipGenerator != null) {
/* 1197 */       toolTipText = this.legendItemToolTipGenerator.generateLabel(dataset, series);
/*      */     }
/*      */     
/* 1200 */     String urlText = null;
/* 1201 */     if (this.legendItemURLGenerator != null) {
/* 1202 */       urlText = this.legendItemURLGenerator.generateLabel(dataset, series);
/*      */     }
/*      */     
/* 1205 */     Shape shape = lookupLegendShape(series);
/* 1206 */     Paint paint = lookupSeriesPaint(series);
/* 1207 */     Paint outlinePaint = lookupSeriesOutlinePaint(series);
/* 1208 */     Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*      */     
/* 1210 */     LegendItem item = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*      */     
/* 1212 */     item.setLabelFont(lookupLegendTextFont(series));
/* 1213 */     Paint labelPaint = lookupLegendTextPaint(series);
/* 1214 */     if (labelPaint != null) {
/* 1215 */       item.setLabelPaint(labelPaint);
/*      */     }
/* 1217 */     item.setSeriesKey(dataset.getRowKey(series));
/* 1218 */     item.setSeriesIndex(series);
/* 1219 */     item.setDataset(dataset);
/* 1220 */     item.setDatasetIndex(datasetIndex);
/* 1221 */     return item;
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
/* 1233 */     if (obj == this) {
/* 1234 */       return true;
/*      */     }
/* 1236 */     if (!(obj instanceof AbstractCategoryItemRenderer)) {
/* 1237 */       return false;
/*      */     }
/* 1239 */     AbstractCategoryItemRenderer that = (AbstractCategoryItemRenderer)obj;
/*      */     
/* 1241 */     if (!ObjectUtilities.equal(this.itemLabelGenerator, that.itemLabelGenerator))
/*      */     {
/* 1243 */       return false;
/*      */     }
/* 1245 */     if (!ObjectUtilities.equal(this.itemLabelGeneratorList, that.itemLabelGeneratorList))
/*      */     {
/* 1247 */       return false;
/*      */     }
/* 1249 */     if (!ObjectUtilities.equal(this.baseItemLabelGenerator, that.baseItemLabelGenerator))
/*      */     {
/* 1251 */       return false;
/*      */     }
/* 1253 */     if (!ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator))
/*      */     {
/* 1255 */       return false;
/*      */     }
/* 1257 */     if (!ObjectUtilities.equal(this.toolTipGeneratorList, that.toolTipGeneratorList))
/*      */     {
/* 1259 */       return false;
/*      */     }
/* 1261 */     if (!ObjectUtilities.equal(this.baseToolTipGenerator, that.baseToolTipGenerator))
/*      */     {
/* 1263 */       return false;
/*      */     }
/* 1265 */     if (!ObjectUtilities.equal(this.itemURLGenerator, that.itemURLGenerator))
/*      */     {
/* 1267 */       return false;
/*      */     }
/* 1269 */     if (!ObjectUtilities.equal(this.itemURLGeneratorList, that.itemURLGeneratorList))
/*      */     {
/* 1271 */       return false;
/*      */     }
/* 1273 */     if (!ObjectUtilities.equal(this.baseItemURLGenerator, that.baseItemURLGenerator))
/*      */     {
/* 1275 */       return false;
/*      */     }
/* 1277 */     if (!ObjectUtilities.equal(this.legendItemLabelGenerator, that.legendItemLabelGenerator))
/*      */     {
/* 1279 */       return false;
/*      */     }
/* 1281 */     if (!ObjectUtilities.equal(this.legendItemToolTipGenerator, that.legendItemToolTipGenerator))
/*      */     {
/* 1283 */       return false;
/*      */     }
/* 1285 */     if (!ObjectUtilities.equal(this.legendItemURLGenerator, that.legendItemURLGenerator))
/*      */     {
/* 1287 */       return false;
/*      */     }
/* 1289 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 1298 */     int result = super.hashCode();
/* 1299 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DrawingSupplier getDrawingSupplier()
/*      */   {
/* 1308 */     DrawingSupplier result = null;
/* 1309 */     CategoryPlot cp = getPlot();
/* 1310 */     if (cp != null) {
/* 1311 */       result = cp.getDrawingSupplier();
/*      */     }
/* 1313 */     return result;
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
/*      */   protected void updateCrosshairValues(CategoryCrosshairState crosshairState, Comparable rowKey, Comparable columnKey, double value, int datasetIndex, double transX, double transY, PlotOrientation orientation)
/*      */   {
/* 1339 */     if (orientation == null) {
/* 1340 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*      */     
/* 1343 */     if (crosshairState != null) {
/* 1344 */       if (this.plot.isRangeCrosshairLockedOnData())
/*      */       {
/* 1346 */         crosshairState.updateCrosshairPoint(rowKey, columnKey, value, datasetIndex, transX, transY, orientation);
/*      */       }
/*      */       else
/*      */       {
/* 1350 */         crosshairState.updateCrosshairX(rowKey, columnKey, datasetIndex, transX, orientation);
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
/*      */   protected void drawItemLabel(Graphics2D g2, PlotOrientation orientation, CategoryDataset dataset, int row, int column, double x, double y, boolean negative)
/*      */   {
/* 1373 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*      */     
/* 1375 */     if (generator != null) {
/* 1376 */       Font labelFont = getItemLabelFont(row, column);
/* 1377 */       Paint paint = getItemLabelPaint(row, column);
/* 1378 */       g2.setFont(labelFont);
/* 1379 */       g2.setPaint(paint);
/* 1380 */       String label = generator.generateLabel(dataset, row, column);
/* 1381 */       ItemLabelPosition position = null;
/* 1382 */       if (!negative) {
/* 1383 */         position = getPositiveItemLabelPosition(row, column);
/*      */       }
/*      */       else {
/* 1386 */         position = getNegativeItemLabelPosition(row, column);
/*      */       }
/* 1388 */       Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
/*      */       
/* 1390 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
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
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1410 */     AbstractCategoryItemRenderer clone = (AbstractCategoryItemRenderer)super.clone();
/*      */     
/*      */ 
/* 1413 */     if (this.itemLabelGenerator != null) {
/* 1414 */       if ((this.itemLabelGenerator instanceof PublicCloneable)) {
/* 1415 */         PublicCloneable pc = (PublicCloneable)this.itemLabelGenerator;
/* 1416 */         clone.itemLabelGenerator = ((CategoryItemLabelGenerator)pc.clone());
/*      */       }
/*      */       else
/*      */       {
/* 1420 */         throw new CloneNotSupportedException("ItemLabelGenerator not cloneable.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1425 */     if (this.itemLabelGeneratorList != null) {
/* 1426 */       clone.itemLabelGeneratorList = ((ObjectList)this.itemLabelGeneratorList.clone());
/*      */     }
/*      */     
/*      */ 
/* 1430 */     if (this.baseItemLabelGenerator != null) {
/* 1431 */       if ((this.baseItemLabelGenerator instanceof PublicCloneable)) {
/* 1432 */         PublicCloneable pc = (PublicCloneable)this.baseItemLabelGenerator;
/*      */         
/* 1434 */         clone.baseItemLabelGenerator = ((CategoryItemLabelGenerator)pc.clone());
/*      */       }
/*      */       else
/*      */       {
/* 1438 */         throw new CloneNotSupportedException("ItemLabelGenerator not cloneable.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1443 */     if (this.toolTipGenerator != null) {
/* 1444 */       if ((this.toolTipGenerator instanceof PublicCloneable)) {
/* 1445 */         PublicCloneable pc = (PublicCloneable)this.toolTipGenerator;
/* 1446 */         clone.toolTipGenerator = ((CategoryToolTipGenerator)pc.clone());
/*      */       }
/*      */       else {
/* 1449 */         throw new CloneNotSupportedException("Tool tip generator not cloneable.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1454 */     if (this.toolTipGeneratorList != null) {
/* 1455 */       clone.toolTipGeneratorList = ((ObjectList)this.toolTipGeneratorList.clone());
/*      */     }
/*      */     
/*      */ 
/* 1459 */     if (this.baseToolTipGenerator != null) {
/* 1460 */       if ((this.baseToolTipGenerator instanceof PublicCloneable)) {
/* 1461 */         PublicCloneable pc = (PublicCloneable)this.baseToolTipGenerator;
/*      */         
/* 1463 */         clone.baseToolTipGenerator = ((CategoryToolTipGenerator)pc.clone());
/*      */       }
/*      */       else
/*      */       {
/* 1467 */         throw new CloneNotSupportedException("Base tool tip generator not cloneable.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1472 */     if (this.itemURLGenerator != null) {
/* 1473 */       if ((this.itemURLGenerator instanceof PublicCloneable)) {
/* 1474 */         PublicCloneable pc = (PublicCloneable)this.itemURLGenerator;
/* 1475 */         clone.itemURLGenerator = ((CategoryURLGenerator)pc.clone());
/*      */       }
/*      */       else {
/* 1478 */         throw new CloneNotSupportedException("Item URL generator not cloneable.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1483 */     if (this.itemURLGeneratorList != null) {
/* 1484 */       clone.itemURLGeneratorList = ((ObjectList)this.itemURLGeneratorList.clone());
/*      */     }
/*      */     
/*      */ 
/* 1488 */     if (this.baseItemURLGenerator != null) {
/* 1489 */       if ((this.baseItemURLGenerator instanceof PublicCloneable)) {
/* 1490 */         PublicCloneable pc = (PublicCloneable)this.baseItemURLGenerator;
/*      */         
/* 1492 */         clone.baseItemURLGenerator = ((CategoryURLGenerator)pc.clone());
/*      */       }
/*      */       else {
/* 1495 */         throw new CloneNotSupportedException("Base item URL generator not cloneable.");
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1500 */     if ((this.legendItemLabelGenerator instanceof PublicCloneable)) {
/* 1501 */       clone.legendItemLabelGenerator = ((CategorySeriesLabelGenerator)ObjectUtilities.clone(this.legendItemLabelGenerator));
/*      */     }
/*      */     
/* 1504 */     if ((this.legendItemToolTipGenerator instanceof PublicCloneable)) {
/* 1505 */       clone.legendItemToolTipGenerator = ((CategorySeriesLabelGenerator)ObjectUtilities.clone(this.legendItemToolTipGenerator));
/*      */     }
/*      */     
/* 1508 */     if ((this.legendItemURLGenerator instanceof PublicCloneable)) {
/* 1509 */       clone.legendItemURLGenerator = ((CategorySeriesLabelGenerator)ObjectUtilities.clone(this.legendItemURLGenerator));
/*      */     }
/*      */     
/* 1512 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected CategoryAxis getDomainAxis(CategoryPlot plot, int index)
/*      */   {
/* 1524 */     CategoryAxis result = plot.getDomainAxis(index);
/* 1525 */     if (result == null) {
/* 1526 */       result = plot.getDomainAxis();
/*      */     }
/* 1528 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected ValueAxis getRangeAxis(CategoryPlot plot, int index)
/*      */   {
/* 1540 */     ValueAxis result = plot.getRangeAxis(index);
/* 1541 */     if (result == null) {
/* 1542 */       result = plot.getRangeAxis();
/*      */     }
/* 1544 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItemCollection getLegendItems()
/*      */   {
/* 1556 */     if (this.plot == null) {
/* 1557 */       return new LegendItemCollection();
/*      */     }
/* 1559 */     LegendItemCollection result = new LegendItemCollection();
/* 1560 */     int index = this.plot.getIndexOf(this);
/* 1561 */     CategoryDataset dataset = this.plot.getDataset(index);
/* 1562 */     if (dataset != null) {
/* 1563 */       int seriesCount = dataset.getRowCount();
/* 1564 */       for (int i = 0; i < seriesCount; i++) {
/* 1565 */         if (isSeriesVisibleInLegend(i)) {
/* 1566 */           LegendItem item = getLegendItem(index, i);
/* 1567 */           if (item != null) {
/* 1568 */             result.add(item);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1574 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategorySeriesLabelGenerator getLegendItemLabelGenerator()
/*      */   {
/* 1585 */     return this.legendItemLabelGenerator;
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
/*      */   public void setLegendItemLabelGenerator(CategorySeriesLabelGenerator generator)
/*      */   {
/* 1598 */     if (generator == null) {
/* 1599 */       throw new IllegalArgumentException("Null 'generator' argument.");
/*      */     }
/* 1601 */     this.legendItemLabelGenerator = generator;
/* 1602 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategorySeriesLabelGenerator getLegendItemToolTipGenerator()
/*      */   {
/* 1613 */     return this.legendItemToolTipGenerator;
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
/*      */   public void setLegendItemToolTipGenerator(CategorySeriesLabelGenerator generator)
/*      */   {
/* 1626 */     this.legendItemToolTipGenerator = generator;
/* 1627 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategorySeriesLabelGenerator getLegendItemURLGenerator()
/*      */   {
/* 1638 */     return this.legendItemURLGenerator;
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
/*      */   public void setLegendItemURLGenerator(CategorySeriesLabelGenerator generator)
/*      */   {
/* 1651 */     this.legendItemURLGenerator = generator;
/* 1652 */     fireChangeEvent();
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
/*      */   protected void addItemEntity(EntityCollection entities, CategoryDataset dataset, int row, int column, Shape hotspot)
/*      */   {
/* 1667 */     if (hotspot == null) {
/* 1668 */       throw new IllegalArgumentException("Null 'hotspot' argument.");
/*      */     }
/* 1670 */     if (!getItemCreateEntity(row, column)) {
/* 1671 */       return;
/*      */     }
/* 1673 */     String tip = null;
/* 1674 */     CategoryToolTipGenerator tipster = getToolTipGenerator(row, column);
/* 1675 */     if (tipster != null) {
/* 1676 */       tip = tipster.generateToolTip(dataset, row, column);
/*      */     }
/* 1678 */     String url = null;
/* 1679 */     CategoryURLGenerator urlster = getItemURLGenerator(row, column);
/* 1680 */     if (urlster != null) {
/* 1681 */       url = urlster.generateURL(dataset, row, column);
/*      */     }
/* 1683 */     CategoryItemEntity entity = new CategoryItemEntity(hotspot, tip, url, dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
/*      */     
/* 1685 */     entities.add(entity);
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
/*      */   protected void addEntity(EntityCollection entities, Shape hotspot, CategoryDataset dataset, int row, int column, double entityX, double entityY)
/*      */   {
/* 1707 */     if (!getItemCreateEntity(row, column)) {
/* 1708 */       return;
/*      */     }
/* 1710 */     Shape s = hotspot;
/* 1711 */     if (hotspot == null) {
/* 1712 */       double r = getDefaultEntityRadius();
/* 1713 */       double w = r * 2.0D;
/* 1714 */       if (getPlot().getOrientation() == PlotOrientation.VERTICAL) {
/* 1715 */         s = new Ellipse2D.Double(entityX - r, entityY - r, w, w);
/*      */       }
/*      */       else {
/* 1718 */         s = new Ellipse2D.Double(entityY - r, entityX - r, w, w);
/*      */       }
/*      */     }
/* 1721 */     String tip = null;
/* 1722 */     CategoryToolTipGenerator generator = getToolTipGenerator(row, column);
/* 1723 */     if (generator != null) {
/* 1724 */       tip = generator.generateToolTip(dataset, row, column);
/*      */     }
/* 1726 */     String url = null;
/* 1727 */     CategoryURLGenerator urlster = getItemURLGenerator(row, column);
/* 1728 */     if (urlster != null) {
/* 1729 */       url = urlster.generateURL(dataset, row, column);
/*      */     }
/* 1731 */     CategoryItemEntity entity = new CategoryItemEntity(s, tip, url, dataset, dataset.getRowKey(row), dataset.getColumnKey(column));
/*      */     
/* 1733 */     entities.add(entity);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setItemLabelGenerator(CategoryItemLabelGenerator generator)
/*      */   {
/* 1771 */     this.itemLabelGenerator = generator;
/* 1772 */     fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public CategoryToolTipGenerator getToolTipGenerator()
/*      */   {
/* 1788 */     return this.toolTipGenerator;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setToolTipGenerator(CategoryToolTipGenerator generator)
/*      */   {
/* 1806 */     this.toolTipGenerator = generator;
/* 1807 */     fireChangeEvent();
/*      */   }
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
/*      */   public void setItemURLGenerator(CategoryURLGenerator generator)
/*      */   {
/* 1822 */     this.itemURLGenerator = generator;
/* 1823 */     fireChangeEvent();
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/AbstractCategoryItemRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */