/*      */ package org.jfree.chart.renderer.xy;
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
/*      */ import java.util.Collection;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.XYItemEntity;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.StandardXYSeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*      */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.IntervalMarker;
/*      */ import org.jfree.chart.plot.Marker;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.ValueMarker;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.renderer.AbstractRenderer;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.LengthAdjustmentType;
/*      */ import org.jfree.ui.RectangleAnchor;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractXYItemRenderer
/*      */   extends AbstractRenderer
/*      */   implements XYItemRenderer, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 8019124836026607990L;
/*      */   private XYPlot plot;
/*      */   private ObjectList itemLabelGeneratorList;
/*      */   private XYItemLabelGenerator baseItemLabelGenerator;
/*      */   private ObjectList toolTipGeneratorList;
/*      */   private XYToolTipGenerator baseToolTipGenerator;
/*      */   private XYURLGenerator urlGenerator;
/*      */   private List backgroundAnnotations;
/*      */   private List foregroundAnnotations;
/*      */   private XYSeriesLabelGenerator legendItemLabelGenerator;
/*      */   private XYSeriesLabelGenerator legendItemToolTipGenerator;
/*      */   private XYSeriesLabelGenerator legendItemURLGenerator;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private XYItemLabelGenerator itemLabelGenerator;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private XYToolTipGenerator toolTipGenerator;
/*      */   
/*      */   protected AbstractXYItemRenderer()
/*      */   {
/*  226 */     this.itemLabelGenerator = null;
/*  227 */     this.itemLabelGeneratorList = new ObjectList();
/*  228 */     this.toolTipGenerator = null;
/*  229 */     this.toolTipGeneratorList = new ObjectList();
/*  230 */     this.urlGenerator = null;
/*  231 */     this.backgroundAnnotations = new ArrayList();
/*  232 */     this.foregroundAnnotations = new ArrayList();
/*  233 */     this.legendItemLabelGenerator = new StandardXYSeriesLabelGenerator("{0}");
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
/*  245 */     return 1;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYPlot getPlot()
/*      */   {
/*  254 */     return this.plot;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlot(XYPlot plot)
/*      */   {
/*  263 */     this.plot = plot;
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
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*      */   {
/*  289 */     XYItemRendererState state = new XYItemRendererState(info);
/*  290 */     return state;
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
/*      */   public XYItemLabelGenerator getItemLabelGenerator(int series, int item)
/*      */   {
/*  309 */     if (this.itemLabelGenerator != null) {
/*  310 */       return this.itemLabelGenerator;
/*      */     }
/*      */     
/*      */ 
/*  314 */     XYItemLabelGenerator generator = (XYItemLabelGenerator)this.itemLabelGeneratorList.get(series);
/*      */     
/*  316 */     if (generator == null) {
/*  317 */       generator = this.baseItemLabelGenerator;
/*      */     }
/*  319 */     return generator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYItemLabelGenerator getSeriesItemLabelGenerator(int series)
/*      */   {
/*  330 */     return (XYItemLabelGenerator)this.itemLabelGeneratorList.get(series);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesItemLabelGenerator(int series, XYItemLabelGenerator generator)
/*      */   {
/*  342 */     this.itemLabelGeneratorList.set(series, generator);
/*  343 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYItemLabelGenerator getBaseItemLabelGenerator()
/*      */   {
/*  352 */     return this.baseItemLabelGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseItemLabelGenerator(XYItemLabelGenerator generator)
/*      */   {
/*  362 */     this.baseItemLabelGenerator = generator;
/*  363 */     fireChangeEvent();
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
/*      */   public XYToolTipGenerator getToolTipGenerator(int series, int item)
/*      */   {
/*  380 */     if (this.toolTipGenerator != null) {
/*  381 */       return this.toolTipGenerator;
/*      */     }
/*      */     
/*      */ 
/*  385 */     XYToolTipGenerator generator = (XYToolTipGenerator)this.toolTipGeneratorList.get(series);
/*      */     
/*  387 */     if (generator == null) {
/*  388 */       generator = this.baseToolTipGenerator;
/*      */     }
/*  390 */     return generator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYToolTipGenerator getSeriesToolTipGenerator(int series)
/*      */   {
/*  401 */     return (XYToolTipGenerator)this.toolTipGeneratorList.get(series);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesToolTipGenerator(int series, XYToolTipGenerator generator)
/*      */   {
/*  413 */     this.toolTipGeneratorList.set(series, generator);
/*  414 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYToolTipGenerator getBaseToolTipGenerator()
/*      */   {
/*  425 */     return this.baseToolTipGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseToolTipGenerator(XYToolTipGenerator generator)
/*      */   {
/*  437 */     this.baseToolTipGenerator = generator;
/*  438 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYURLGenerator getURLGenerator()
/*      */   {
/*  449 */     return this.urlGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURLGenerator(XYURLGenerator urlGenerator)
/*      */   {
/*  459 */     this.urlGenerator = urlGenerator;
/*  460 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addAnnotation(XYAnnotation annotation)
/*      */   {
/*  472 */     addAnnotation(annotation, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addAnnotation(XYAnnotation annotation, Layer layer)
/*      */   {
/*  483 */     if (annotation == null) {
/*  484 */       throw new IllegalArgumentException("Null 'annotation' argument.");
/*      */     }
/*  486 */     if (layer.equals(Layer.FOREGROUND)) {
/*  487 */       this.foregroundAnnotations.add(annotation);
/*  488 */       fireChangeEvent();
/*      */     }
/*  490 */     else if (layer.equals(Layer.BACKGROUND)) {
/*  491 */       this.backgroundAnnotations.add(annotation);
/*  492 */       fireChangeEvent();
/*      */     }
/*      */     else
/*      */     {
/*  496 */       throw new RuntimeException("Unknown layer.");
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
/*      */   public boolean removeAnnotation(XYAnnotation annotation)
/*      */   {
/*  510 */     boolean removed = this.foregroundAnnotations.remove(annotation);
/*  511 */     removed &= this.backgroundAnnotations.remove(annotation);
/*  512 */     fireChangeEvent();
/*  513 */     return removed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeAnnotations()
/*      */   {
/*  521 */     this.foregroundAnnotations.clear();
/*  522 */     this.backgroundAnnotations.clear();
/*  523 */     fireChangeEvent();
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
/*      */   public Collection getAnnotations()
/*      */   {
/*  536 */     List result = new ArrayList(this.foregroundAnnotations);
/*  537 */     result.addAll(this.backgroundAnnotations);
/*  538 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYSeriesLabelGenerator getLegendItemLabelGenerator()
/*      */   {
/*  549 */     return this.legendItemLabelGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendItemLabelGenerator(XYSeriesLabelGenerator generator)
/*      */   {
/*  561 */     if (generator == null) {
/*  562 */       throw new IllegalArgumentException("Null 'generator' argument.");
/*      */     }
/*  564 */     this.legendItemLabelGenerator = generator;
/*  565 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYSeriesLabelGenerator getLegendItemToolTipGenerator()
/*      */   {
/*  576 */     return this.legendItemToolTipGenerator;
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
/*      */   public void setLegendItemToolTipGenerator(XYSeriesLabelGenerator generator)
/*      */   {
/*  589 */     this.legendItemToolTipGenerator = generator;
/*  590 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYSeriesLabelGenerator getLegendItemURLGenerator()
/*      */   {
/*  601 */     return this.legendItemURLGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendItemURLGenerator(XYSeriesLabelGenerator generator)
/*      */   {
/*  613 */     this.legendItemURLGenerator = generator;
/*  614 */     fireChangeEvent();
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
/*      */   public Range findDomainBounds(XYDataset dataset)
/*      */   {
/*  629 */     return findDomainBounds(dataset, false);
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
/*      */   protected Range findDomainBounds(XYDataset dataset, boolean includeInterval)
/*      */   {
/*  645 */     if (dataset == null) {
/*  646 */       return null;
/*      */     }
/*  648 */     if (getDataBoundsIncludesVisibleSeriesOnly()) {
/*  649 */       List visibleSeriesKeys = new ArrayList();
/*  650 */       int seriesCount = dataset.getSeriesCount();
/*  651 */       for (int s = 0; s < seriesCount; s++) {
/*  652 */         if (isSeriesVisible(s)) {
/*  653 */           visibleSeriesKeys.add(dataset.getSeriesKey(s));
/*      */         }
/*      */       }
/*  656 */       return DatasetUtilities.findDomainBounds(dataset, visibleSeriesKeys, includeInterval);
/*      */     }
/*      */     
/*      */ 
/*  660 */     return DatasetUtilities.findDomainBounds(dataset, includeInterval);
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
/*      */   public Range findRangeBounds(XYDataset dataset)
/*      */   {
/*  676 */     return findRangeBounds(dataset, false);
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
/*      */   protected Range findRangeBounds(XYDataset dataset, boolean includeInterval)
/*      */   {
/*  692 */     if (dataset == null) {
/*  693 */       return null;
/*      */     }
/*  695 */     if (getDataBoundsIncludesVisibleSeriesOnly()) {
/*  696 */       List visibleSeriesKeys = new ArrayList();
/*  697 */       int seriesCount = dataset.getSeriesCount();
/*  698 */       for (int s = 0; s < seriesCount; s++) {
/*  699 */         if (isSeriesVisible(s)) {
/*  700 */           visibleSeriesKeys.add(dataset.getSeriesKey(s));
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  705 */       Range xRange = null;
/*  706 */       XYPlot p = getPlot();
/*  707 */       if (p != null) {
/*  708 */         ValueAxis xAxis = null;
/*  709 */         int index = p.getIndexOf(this);
/*  710 */         if (index >= 0) {
/*  711 */           xAxis = this.plot.getDomainAxisForDataset(index);
/*      */         }
/*  713 */         if (xAxis != null) {
/*  714 */           xRange = xAxis.getRange();
/*      */         }
/*      */       }
/*  717 */       if (xRange == null) {
/*  718 */         xRange = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
/*      */       }
/*      */       
/*  721 */       return DatasetUtilities.findRangeBounds(dataset, visibleSeriesKeys, xRange, includeInterval);
/*      */     }
/*      */     
/*      */ 
/*  725 */     return DatasetUtilities.findRangeBounds(dataset, includeInterval);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItemCollection getLegendItems()
/*      */   {
/*  736 */     if (this.plot == null) {
/*  737 */       return new LegendItemCollection();
/*      */     }
/*  739 */     LegendItemCollection result = new LegendItemCollection();
/*  740 */     int index = this.plot.getIndexOf(this);
/*  741 */     XYDataset dataset = this.plot.getDataset(index);
/*  742 */     if (dataset != null) {
/*  743 */       int seriesCount = dataset.getSeriesCount();
/*  744 */       for (int i = 0; i < seriesCount; i++) {
/*  745 */         if (isSeriesVisibleInLegend(i)) {
/*  746 */           LegendItem item = getLegendItem(index, i);
/*  747 */           if (item != null) {
/*  748 */             result.add(item);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  754 */     return result;
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
/*  767 */     LegendItem result = null;
/*  768 */     XYPlot xyplot = getPlot();
/*  769 */     if (xyplot != null) {
/*  770 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/*  771 */       if (dataset != null) {
/*  772 */         String label = this.legendItemLabelGenerator.generateLabel(dataset, series);
/*      */         
/*  774 */         String description = label;
/*  775 */         String toolTipText = null;
/*  776 */         if (getLegendItemToolTipGenerator() != null) {
/*  777 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */         }
/*      */         
/*  780 */         String urlText = null;
/*  781 */         if (getLegendItemURLGenerator() != null) {
/*  782 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */         }
/*      */         
/*  785 */         Shape shape = lookupLegendShape(series);
/*  786 */         Paint paint = lookupSeriesPaint(series);
/*  787 */         Paint outlinePaint = lookupSeriesOutlinePaint(series);
/*  788 */         Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*  789 */         result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*      */         
/*  791 */         Paint labelPaint = lookupLegendTextPaint(series);
/*  792 */         result.setLabelFont(lookupLegendTextFont(series));
/*  793 */         if (labelPaint != null) {
/*  794 */           result.setLabelPaint(labelPaint);
/*      */         }
/*  796 */         result.setSeriesKey(dataset.getSeriesKey(series));
/*  797 */         result.setSeriesIndex(series);
/*  798 */         result.setDataset(dataset);
/*  799 */         result.setDatasetIndex(datasetIndex);
/*      */       }
/*      */     }
/*  802 */     return result;
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
/*      */   public void fillDomainGridBand(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double start, double end)
/*      */   {
/*  819 */     double x1 = axis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
/*      */     
/*  821 */     double x2 = axis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
/*      */     Rectangle2D band;
/*      */     Rectangle2D band;
/*  824 */     if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/*  825 */       band = new Rectangle2D.Double(Math.min(x1, x2), dataArea.getMinY(), Math.abs(x2 - x1), dataArea.getWidth());
/*      */     }
/*      */     else
/*      */     {
/*  829 */       band = new Rectangle2D.Double(dataArea.getMinX(), Math.min(x1, x2), dataArea.getWidth(), Math.abs(x2 - x1));
/*      */     }
/*      */     
/*  832 */     Paint paint = plot.getDomainTickBandPaint();
/*      */     
/*  834 */     if (paint != null) {
/*  835 */       g2.setPaint(paint);
/*  836 */       g2.fill(band);
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
/*      */   public void fillRangeGridBand(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double start, double end)
/*      */   {
/*  855 */     double y1 = axis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
/*      */     
/*  857 */     double y2 = axis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
/*      */     Rectangle2D band;
/*  859 */     Rectangle2D band; if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/*  860 */       band = new Rectangle2D.Double(dataArea.getMinX(), Math.min(y1, y2), dataArea.getWidth(), Math.abs(y2 - y1));
/*      */     }
/*      */     else
/*      */     {
/*  864 */       band = new Rectangle2D.Double(Math.min(y1, y2), dataArea.getMinY(), Math.abs(y2 - y1), dataArea.getHeight());
/*      */     }
/*      */     
/*  867 */     Paint paint = plot.getRangeTickBandPaint();
/*      */     
/*  869 */     if (paint != null) {
/*  870 */       g2.setPaint(paint);
/*  871 */       g2.fill(band);
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
/*      */   public void drawDomainGridLine(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double value)
/*      */   {
/*  892 */     Range range = axis.getRange();
/*  893 */     if (!range.contains(value)) {
/*  894 */       return;
/*      */     }
/*      */     
/*  897 */     PlotOrientation orientation = plot.getOrientation();
/*  898 */     double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
/*      */     
/*  900 */     Line2D line = null;
/*  901 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  902 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */ 
/*      */     }
/*  905 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  906 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */     }
/*      */     
/*      */ 
/*  910 */     Paint paint = plot.getDomainGridlinePaint();
/*  911 */     Stroke stroke = plot.getDomainGridlineStroke();
/*  912 */     g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/*  913 */     g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/*  914 */     g2.draw(line);
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
/*      */   public void drawDomainLine(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double value, Paint paint, Stroke stroke)
/*      */   {
/*  935 */     Range range = axis.getRange();
/*  936 */     if (!range.contains(value)) {
/*  937 */       return;
/*      */     }
/*      */     
/*  940 */     PlotOrientation orientation = plot.getOrientation();
/*  941 */     Line2D line = null;
/*  942 */     double v = axis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
/*      */     
/*  944 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  945 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */ 
/*      */     }
/*  948 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  949 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */     }
/*      */     
/*      */ 
/*  953 */     g2.setPaint(paint);
/*  954 */     g2.setStroke(stroke);
/*  955 */     g2.draw(line);
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
/*      */   public void drawRangeLine(Graphics2D g2, XYPlot plot, ValueAxis axis, Rectangle2D dataArea, double value, Paint paint, Stroke stroke)
/*      */   {
/*  979 */     Range range = axis.getRange();
/*  980 */     if (!range.contains(value)) {
/*  981 */       return;
/*      */     }
/*      */     
/*  984 */     PlotOrientation orientation = plot.getOrientation();
/*  985 */     Line2D line = null;
/*  986 */     double v = axis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*  987 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  988 */       line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */ 
/*      */     }
/*  991 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  992 */       line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */     }
/*      */     
/*      */ 
/*  996 */     g2.setPaint(paint);
/*  997 */     g2.setStroke(stroke);
/*  998 */     g2.draw(line);
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
/*      */   public void drawDomainMarker(Graphics2D g2, XYPlot plot, ValueAxis domainAxis, Marker marker, Rectangle2D dataArea)
/*      */   {
/* 1017 */     if ((marker instanceof ValueMarker)) {
/* 1018 */       ValueMarker vm = (ValueMarker)marker;
/* 1019 */       double value = vm.getValue();
/* 1020 */       Range range = domainAxis.getRange();
/* 1021 */       if (!range.contains(value)) {
/* 1022 */         return;
/*      */       }
/*      */       
/* 1025 */       double v = domainAxis.valueToJava2D(value, dataArea, plot.getDomainAxisEdge());
/*      */       
/*      */ 
/* 1028 */       PlotOrientation orientation = plot.getOrientation();
/* 1029 */       Line2D line = null;
/* 1030 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1031 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */ 
/*      */       }
/* 1034 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1035 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */       }
/*      */       
/*      */ 
/* 1039 */       Composite originalComposite = g2.getComposite();
/* 1040 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */       
/* 1042 */       g2.setPaint(marker.getPaint());
/* 1043 */       g2.setStroke(marker.getStroke());
/* 1044 */       g2.draw(line);
/*      */       
/* 1046 */       String label = marker.getLabel();
/* 1047 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1048 */       if (label != null) {
/* 1049 */         Font labelFont = marker.getLabelFont();
/* 1050 */         g2.setFont(labelFont);
/* 1051 */         g2.setPaint(marker.getLabelPaint());
/* 1052 */         Point2D coordinates = calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/*      */         
/*      */ 
/*      */ 
/* 1056 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       }
/*      */       
/*      */ 
/* 1060 */       g2.setComposite(originalComposite);
/*      */     }
/* 1062 */     else if ((marker instanceof IntervalMarker)) {
/* 1063 */       IntervalMarker im = (IntervalMarker)marker;
/* 1064 */       double start = im.getStartValue();
/* 1065 */       double end = im.getEndValue();
/* 1066 */       Range range = domainAxis.getRange();
/* 1067 */       if (!range.intersects(start, end)) {
/* 1068 */         return;
/*      */       }
/*      */       
/* 1071 */       double start2d = domainAxis.valueToJava2D(start, dataArea, plot.getDomainAxisEdge());
/*      */       
/* 1073 */       double end2d = domainAxis.valueToJava2D(end, dataArea, plot.getDomainAxisEdge());
/*      */       
/* 1075 */       double low = Math.min(start2d, end2d);
/* 1076 */       double high = Math.max(start2d, end2d);
/*      */       
/* 1078 */       PlotOrientation orientation = plot.getOrientation();
/* 1079 */       Rectangle2D rect = null;
/* 1080 */       if (orientation == PlotOrientation.HORIZONTAL)
/*      */       {
/* 1082 */         low = Math.max(low, dataArea.getMinY());
/* 1083 */         high = Math.min(high, dataArea.getMaxY());
/* 1084 */         rect = new Rectangle2D.Double(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
/*      */ 
/*      */ 
/*      */       }
/* 1088 */       else if (orientation == PlotOrientation.VERTICAL)
/*      */       {
/* 1090 */         low = Math.max(low, dataArea.getMinX());
/* 1091 */         high = Math.min(high, dataArea.getMaxX());
/* 1092 */         rect = new Rectangle2D.Double(low, dataArea.getMinY(), high - low, dataArea.getHeight());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1097 */       Composite originalComposite = g2.getComposite();
/* 1098 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */       
/* 1100 */       Paint p = marker.getPaint();
/* 1101 */       if ((p instanceof GradientPaint)) {
/* 1102 */         GradientPaint gp = (GradientPaint)p;
/* 1103 */         GradientPaintTransformer t = im.getGradientPaintTransformer();
/* 1104 */         if (t != null) {
/* 1105 */           gp = t.transform(gp, rect);
/*      */         }
/* 1107 */         g2.setPaint(gp);
/*      */       }
/*      */       else {
/* 1110 */         g2.setPaint(p);
/*      */       }
/* 1112 */       g2.fill(rect);
/*      */       
/*      */ 
/* 1115 */       if ((im.getOutlinePaint() != null) && (im.getOutlineStroke() != null)) {
/* 1116 */         if (orientation == PlotOrientation.VERTICAL) {
/* 1117 */           Line2D line = new Line2D.Double();
/* 1118 */           double y0 = dataArea.getMinY();
/* 1119 */           double y1 = dataArea.getMaxY();
/* 1120 */           g2.setPaint(im.getOutlinePaint());
/* 1121 */           g2.setStroke(im.getOutlineStroke());
/* 1122 */           if (range.contains(start)) {
/* 1123 */             line.setLine(start2d, y0, start2d, y1);
/* 1124 */             g2.draw(line);
/*      */           }
/* 1126 */           if (range.contains(end)) {
/* 1127 */             line.setLine(end2d, y0, end2d, y1);
/* 1128 */             g2.draw(line);
/*      */           }
/*      */         }
/*      */         else {
/* 1132 */           Line2D line = new Line2D.Double();
/* 1133 */           double x0 = dataArea.getMinX();
/* 1134 */           double x1 = dataArea.getMaxX();
/* 1135 */           g2.setPaint(im.getOutlinePaint());
/* 1136 */           g2.setStroke(im.getOutlineStroke());
/* 1137 */           if (range.contains(start)) {
/* 1138 */             line.setLine(x0, start2d, x1, start2d);
/* 1139 */             g2.draw(line);
/*      */           }
/* 1141 */           if (range.contains(end)) {
/* 1142 */             line.setLine(x0, end2d, x1, end2d);
/* 1143 */             g2.draw(line);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1148 */       String label = marker.getLabel();
/* 1149 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1150 */       if (label != null) {
/* 1151 */         Font labelFont = marker.getLabelFont();
/* 1152 */         g2.setFont(labelFont);
/* 1153 */         g2.setPaint(marker.getLabelPaint());
/* 1154 */         Point2D coordinates = calculateDomainMarkerTextAnchorPoint(g2, orientation, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*      */         
/*      */ 
/*      */ 
/* 1158 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       }
/*      */       
/*      */ 
/* 1162 */       g2.setComposite(originalComposite);
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
/*      */   protected Point2D calculateDomainMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetType, RectangleAnchor anchor)
/*      */   {
/* 1189 */     Rectangle2D anchorRect = null;
/* 1190 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1191 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetType);
/*      */ 
/*      */     }
/* 1194 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 1195 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetType, LengthAdjustmentType.CONTRACT);
/*      */     }
/*      */     
/* 1198 */     return RectangleAnchor.coordinates(anchorRect, anchor);
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
/*      */   public void drawRangeMarker(Graphics2D g2, XYPlot plot, ValueAxis rangeAxis, Marker marker, Rectangle2D dataArea)
/*      */   {
/* 1217 */     if ((marker instanceof ValueMarker)) {
/* 1218 */       ValueMarker vm = (ValueMarker)marker;
/* 1219 */       double value = vm.getValue();
/* 1220 */       Range range = rangeAxis.getRange();
/* 1221 */       if (!range.contains(value)) {
/* 1222 */         return;
/*      */       }
/*      */       
/* 1225 */       double v = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*      */       
/* 1227 */       PlotOrientation orientation = plot.getOrientation();
/* 1228 */       Line2D line = null;
/* 1229 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1230 */         line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */ 
/*      */       }
/* 1233 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1234 */         line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */       }
/*      */       
/*      */ 
/* 1238 */       Composite originalComposite = g2.getComposite();
/* 1239 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */       
/* 1241 */       g2.setPaint(marker.getPaint());
/* 1242 */       g2.setStroke(marker.getStroke());
/* 1243 */       g2.draw(line);
/*      */       
/* 1245 */       String label = marker.getLabel();
/* 1246 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1247 */       if (label != null) {
/* 1248 */         Font labelFont = marker.getLabelFont();
/* 1249 */         g2.setFont(labelFont);
/* 1250 */         g2.setPaint(marker.getLabelPaint());
/* 1251 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, line.getBounds2D(), marker.getLabelOffset(), LengthAdjustmentType.EXPAND, anchor);
/*      */         
/*      */ 
/*      */ 
/* 1255 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       }
/*      */       
/*      */ 
/* 1259 */       g2.setComposite(originalComposite);
/*      */     }
/* 1261 */     else if ((marker instanceof IntervalMarker)) {
/* 1262 */       IntervalMarker im = (IntervalMarker)marker;
/* 1263 */       double start = im.getStartValue();
/* 1264 */       double end = im.getEndValue();
/* 1265 */       Range range = rangeAxis.getRange();
/* 1266 */       if (!range.intersects(start, end)) {
/* 1267 */         return;
/*      */       }
/*      */       
/* 1270 */       double start2d = rangeAxis.valueToJava2D(start, dataArea, plot.getRangeAxisEdge());
/*      */       
/* 1272 */       double end2d = rangeAxis.valueToJava2D(end, dataArea, plot.getRangeAxisEdge());
/*      */       
/* 1274 */       double low = Math.min(start2d, end2d);
/* 1275 */       double high = Math.max(start2d, end2d);
/*      */       
/* 1277 */       PlotOrientation orientation = plot.getOrientation();
/* 1278 */       Rectangle2D rect = null;
/* 1279 */       if (orientation == PlotOrientation.HORIZONTAL)
/*      */       {
/* 1281 */         low = Math.max(low, dataArea.getMinX());
/* 1282 */         high = Math.min(high, dataArea.getMaxX());
/* 1283 */         rect = new Rectangle2D.Double(low, dataArea.getMinY(), high - low, dataArea.getHeight());
/*      */ 
/*      */ 
/*      */       }
/* 1287 */       else if (orientation == PlotOrientation.VERTICAL)
/*      */       {
/* 1289 */         low = Math.max(low, dataArea.getMinY());
/* 1290 */         high = Math.min(high, dataArea.getMaxY());
/* 1291 */         rect = new Rectangle2D.Double(dataArea.getMinX(), low, dataArea.getWidth(), high - low);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 1296 */       Composite originalComposite = g2.getComposite();
/* 1297 */       g2.setComposite(AlphaComposite.getInstance(3, marker.getAlpha()));
/*      */       
/* 1299 */       Paint p = marker.getPaint();
/* 1300 */       if ((p instanceof GradientPaint)) {
/* 1301 */         GradientPaint gp = (GradientPaint)p;
/* 1302 */         GradientPaintTransformer t = im.getGradientPaintTransformer();
/* 1303 */         if (t != null) {
/* 1304 */           gp = t.transform(gp, rect);
/*      */         }
/* 1306 */         g2.setPaint(gp);
/*      */       }
/*      */       else {
/* 1309 */         g2.setPaint(p);
/*      */       }
/* 1311 */       g2.fill(rect);
/*      */       
/*      */ 
/* 1314 */       if ((im.getOutlinePaint() != null) && (im.getOutlineStroke() != null)) {
/* 1315 */         if (orientation == PlotOrientation.VERTICAL) {
/* 1316 */           Line2D line = new Line2D.Double();
/* 1317 */           double x0 = dataArea.getMinX();
/* 1318 */           double x1 = dataArea.getMaxX();
/* 1319 */           g2.setPaint(im.getOutlinePaint());
/* 1320 */           g2.setStroke(im.getOutlineStroke());
/* 1321 */           if (range.contains(start)) {
/* 1322 */             line.setLine(x0, start2d, x1, start2d);
/* 1323 */             g2.draw(line);
/*      */           }
/* 1325 */           if (range.contains(end)) {
/* 1326 */             line.setLine(x0, end2d, x1, end2d);
/* 1327 */             g2.draw(line);
/*      */           }
/*      */         }
/*      */         else {
/* 1331 */           Line2D line = new Line2D.Double();
/* 1332 */           double y0 = dataArea.getMinY();
/* 1333 */           double y1 = dataArea.getMaxY();
/* 1334 */           g2.setPaint(im.getOutlinePaint());
/* 1335 */           g2.setStroke(im.getOutlineStroke());
/* 1336 */           if (range.contains(start)) {
/* 1337 */             line.setLine(start2d, y0, start2d, y1);
/* 1338 */             g2.draw(line);
/*      */           }
/* 1340 */           if (range.contains(end)) {
/* 1341 */             line.setLine(end2d, y0, end2d, y1);
/* 1342 */             g2.draw(line);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 1347 */       String label = marker.getLabel();
/* 1348 */       RectangleAnchor anchor = marker.getLabelAnchor();
/* 1349 */       if (label != null) {
/* 1350 */         Font labelFont = marker.getLabelFont();
/* 1351 */         g2.setFont(labelFont);
/* 1352 */         g2.setPaint(marker.getLabelPaint());
/* 1353 */         Point2D coordinates = calculateRangeMarkerTextAnchorPoint(g2, orientation, dataArea, rect, marker.getLabelOffset(), marker.getLabelOffsetType(), anchor);
/*      */         
/*      */ 
/*      */ 
/* 1357 */         TextUtilities.drawAlignedString(label, g2, (float)coordinates.getX(), (float)coordinates.getY(), marker.getLabelTextAnchor());
/*      */       }
/*      */       
/*      */ 
/* 1361 */       g2.setComposite(originalComposite);
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
/*      */   private Point2D calculateRangeMarkerTextAnchorPoint(Graphics2D g2, PlotOrientation orientation, Rectangle2D dataArea, Rectangle2D markerArea, RectangleInsets markerOffset, LengthAdjustmentType labelOffsetForRange, RectangleAnchor anchor)
/*      */   {
/* 1386 */     Rectangle2D anchorRect = null;
/* 1387 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1388 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, labelOffsetForRange, LengthAdjustmentType.CONTRACT);
/*      */ 
/*      */     }
/* 1391 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 1392 */       anchorRect = markerOffset.createAdjustedRectangle(markerArea, LengthAdjustmentType.CONTRACT, labelOffsetForRange);
/*      */     }
/*      */     
/* 1395 */     return RectangleAnchor.coordinates(anchorRect, anchor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1408 */     AbstractXYItemRenderer clone = (AbstractXYItemRenderer)super.clone();
/*      */     
/*      */ 
/* 1411 */     if ((this.itemLabelGenerator != null) && ((this.itemLabelGenerator instanceof PublicCloneable)))
/*      */     {
/* 1413 */       PublicCloneable pc = (PublicCloneable)this.itemLabelGenerator;
/* 1414 */       clone.itemLabelGenerator = ((XYItemLabelGenerator)pc.clone());
/*      */     }
/* 1416 */     clone.itemLabelGeneratorList = ((ObjectList)this.itemLabelGeneratorList.clone());
/*      */     
/* 1418 */     if ((this.baseItemLabelGenerator != null) && ((this.baseItemLabelGenerator instanceof PublicCloneable)))
/*      */     {
/* 1420 */       PublicCloneable pc = (PublicCloneable)this.baseItemLabelGenerator;
/* 1421 */       clone.baseItemLabelGenerator = ((XYItemLabelGenerator)pc.clone());
/*      */     }
/*      */     
/* 1424 */     if ((this.toolTipGenerator != null) && ((this.toolTipGenerator instanceof PublicCloneable)))
/*      */     {
/* 1426 */       PublicCloneable pc = (PublicCloneable)this.toolTipGenerator;
/* 1427 */       clone.toolTipGenerator = ((XYToolTipGenerator)pc.clone());
/*      */     }
/* 1429 */     clone.toolTipGeneratorList = ((ObjectList)this.toolTipGeneratorList.clone());
/*      */     
/* 1431 */     if ((this.baseToolTipGenerator != null) && ((this.baseToolTipGenerator instanceof PublicCloneable)))
/*      */     {
/* 1433 */       PublicCloneable pc = (PublicCloneable)this.baseToolTipGenerator;
/* 1434 */       clone.baseToolTipGenerator = ((XYToolTipGenerator)pc.clone());
/*      */     }
/*      */     
/* 1437 */     if ((clone.legendItemLabelGenerator instanceof PublicCloneable)) {
/* 1438 */       clone.legendItemLabelGenerator = ((XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemLabelGenerator));
/*      */     }
/*      */     
/* 1441 */     if ((clone.legendItemToolTipGenerator instanceof PublicCloneable)) {
/* 1442 */       clone.legendItemToolTipGenerator = ((XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemToolTipGenerator));
/*      */     }
/*      */     
/* 1445 */     if ((clone.legendItemURLGenerator instanceof PublicCloneable)) {
/* 1446 */       clone.legendItemURLGenerator = ((XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemURLGenerator));
/*      */     }
/*      */     
/*      */ 
/* 1450 */     clone.foregroundAnnotations = ((List)ObjectUtilities.deepClone(this.foregroundAnnotations));
/*      */     
/* 1452 */     clone.backgroundAnnotations = ((List)ObjectUtilities.deepClone(this.backgroundAnnotations));
/*      */     
/*      */ 
/* 1455 */     if ((clone.legendItemLabelGenerator instanceof PublicCloneable)) {
/* 1456 */       clone.legendItemLabelGenerator = ((XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemLabelGenerator));
/*      */     }
/*      */     
/* 1459 */     if ((clone.legendItemToolTipGenerator instanceof PublicCloneable)) {
/* 1460 */       clone.legendItemToolTipGenerator = ((XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemToolTipGenerator));
/*      */     }
/*      */     
/* 1463 */     if ((clone.legendItemURLGenerator instanceof PublicCloneable)) {
/* 1464 */       clone.legendItemURLGenerator = ((XYSeriesLabelGenerator)ObjectUtilities.clone(this.legendItemURLGenerator));
/*      */     }
/*      */     
/*      */ 
/* 1468 */     return clone;
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
/* 1479 */     if (obj == this) {
/* 1480 */       return true;
/*      */     }
/* 1482 */     if (!(obj instanceof AbstractXYItemRenderer)) {
/* 1483 */       return false;
/*      */     }
/* 1485 */     AbstractXYItemRenderer that = (AbstractXYItemRenderer)obj;
/* 1486 */     if (!ObjectUtilities.equal(this.itemLabelGenerator, that.itemLabelGenerator))
/*      */     {
/* 1488 */       return false;
/*      */     }
/* 1490 */     if (!this.itemLabelGeneratorList.equals(that.itemLabelGeneratorList)) {
/* 1491 */       return false;
/*      */     }
/* 1493 */     if (!ObjectUtilities.equal(this.baseItemLabelGenerator, that.baseItemLabelGenerator))
/*      */     {
/* 1495 */       return false;
/*      */     }
/* 1497 */     if (!ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator))
/*      */     {
/* 1499 */       return false;
/*      */     }
/* 1501 */     if (!this.toolTipGeneratorList.equals(that.toolTipGeneratorList)) {
/* 1502 */       return false;
/*      */     }
/* 1504 */     if (!ObjectUtilities.equal(this.baseToolTipGenerator, that.baseToolTipGenerator))
/*      */     {
/* 1506 */       return false;
/*      */     }
/* 1508 */     if (!ObjectUtilities.equal(this.urlGenerator, that.urlGenerator)) {
/* 1509 */       return false;
/*      */     }
/* 1511 */     if (!this.foregroundAnnotations.equals(that.foregroundAnnotations)) {
/* 1512 */       return false;
/*      */     }
/* 1514 */     if (!this.backgroundAnnotations.equals(that.backgroundAnnotations)) {
/* 1515 */       return false;
/*      */     }
/* 1517 */     if (!ObjectUtilities.equal(this.legendItemLabelGenerator, that.legendItemLabelGenerator))
/*      */     {
/* 1519 */       return false;
/*      */     }
/* 1521 */     if (!ObjectUtilities.equal(this.legendItemToolTipGenerator, that.legendItemToolTipGenerator))
/*      */     {
/* 1523 */       return false;
/*      */     }
/* 1525 */     if (!ObjectUtilities.equal(this.legendItemURLGenerator, that.legendItemURLGenerator))
/*      */     {
/* 1527 */       return false;
/*      */     }
/* 1529 */     return super.equals(obj);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DrawingSupplier getDrawingSupplier()
/*      */   {
/* 1538 */     DrawingSupplier result = null;
/* 1539 */     XYPlot p = getPlot();
/* 1540 */     if (p != null) {
/* 1541 */       result = p.getDrawingSupplier();
/*      */     }
/* 1543 */     return result;
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
/*      */   protected void updateCrosshairValues(CrosshairState crosshairState, double x, double y, int domainAxisIndex, int rangeAxisIndex, double transX, double transY, PlotOrientation orientation)
/*      */   {
/* 1568 */     if (orientation == null) {
/* 1569 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*      */     
/* 1572 */     if (crosshairState != null)
/*      */     {
/* 1574 */       if (this.plot.isDomainCrosshairLockedOnData()) {
/* 1575 */         if (this.plot.isRangeCrosshairLockedOnData())
/*      */         {
/* 1577 */           crosshairState.updateCrosshairPoint(x, y, domainAxisIndex, rangeAxisIndex, transX, transY, orientation);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1582 */           crosshairState.updateCrosshairX(x, domainAxisIndex);
/*      */         }
/*      */         
/*      */       }
/* 1586 */       else if (this.plot.isRangeCrosshairLockedOnData())
/*      */       {
/* 1588 */         crosshairState.updateCrosshairY(y, rangeAxisIndex);
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
/*      */   protected void drawItemLabel(Graphics2D g2, PlotOrientation orientation, XYDataset dataset, int series, int item, double x, double y, boolean negative)
/*      */   {
/* 1612 */     XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
/* 1613 */     if (generator != null) {
/* 1614 */       Font labelFont = getItemLabelFont(series, item);
/* 1615 */       Paint paint = getItemLabelPaint(series, item);
/* 1616 */       g2.setFont(labelFont);
/* 1617 */       g2.setPaint(paint);
/* 1618 */       String label = generator.generateLabel(dataset, series, item);
/*      */       
/*      */ 
/* 1621 */       ItemLabelPosition position = null;
/* 1622 */       if (!negative) {
/* 1623 */         position = getPositiveItemLabelPosition(series, item);
/*      */       }
/*      */       else {
/* 1626 */         position = getNegativeItemLabelPosition(series, item);
/*      */       }
/*      */       
/*      */ 
/* 1630 */       Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), x, y, orientation);
/*      */       
/* 1632 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
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
/*      */   public void drawAnnotations(Graphics2D g2, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, Layer layer, PlotRenderingInfo info)
/*      */   {
/* 1657 */     Iterator iterator = null;
/* 1658 */     if (layer.equals(Layer.FOREGROUND)) {
/* 1659 */       iterator = this.foregroundAnnotations.iterator();
/*      */     }
/* 1661 */     else if (layer.equals(Layer.BACKGROUND)) {
/* 1662 */       iterator = this.backgroundAnnotations.iterator();
/*      */     }
/*      */     else
/*      */     {
/* 1666 */       throw new RuntimeException("Unknown layer.");
/*      */     }
/* 1668 */     while (iterator.hasNext()) {
/* 1669 */       XYAnnotation annotation = (XYAnnotation)iterator.next();
/* 1670 */       annotation.draw(g2, this.plot, dataArea, domainAxis, rangeAxis, 0, info);
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
/*      */   protected void addEntity(EntityCollection entities, Shape area, XYDataset dataset, int series, int item, double entityX, double entityY)
/*      */   {
/* 1693 */     if (!getItemCreateEntity(series, item)) {
/* 1694 */       return;
/*      */     }
/* 1696 */     Shape hotspot = area;
/* 1697 */     if (hotspot == null) {
/* 1698 */       double r = getDefaultEntityRadius();
/* 1699 */       double w = r * 2.0D;
/* 1700 */       if (getPlot().getOrientation() == PlotOrientation.VERTICAL) {
/* 1701 */         hotspot = new Ellipse2D.Double(entityX - r, entityY - r, w, w);
/*      */       }
/*      */       else {
/* 1704 */         hotspot = new Ellipse2D.Double(entityY - r, entityX - r, w, w);
/*      */       }
/*      */     }
/* 1707 */     String tip = null;
/* 1708 */     XYToolTipGenerator generator = getToolTipGenerator(series, item);
/* 1709 */     if (generator != null) {
/* 1710 */       tip = generator.generateToolTip(dataset, series, item);
/*      */     }
/* 1712 */     String url = null;
/* 1713 */     if (getURLGenerator() != null) {
/* 1714 */       url = getURLGenerator().generateURL(dataset, series, item);
/*      */     }
/* 1716 */     XYItemEntity entity = new XYItemEntity(hotspot, dataset, series, item, tip, url);
/*      */     
/* 1718 */     entities.add(entity);
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
/*      */   public static boolean isPointInRect(Rectangle2D rect, double x, double y)
/*      */   {
/* 1736 */     return (x >= rect.getMinX()) && (x <= rect.getMaxX()) && (y >= rect.getMinY()) && (y <= rect.getMaxY());
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public XYItemLabelGenerator getItemLabelGenerator()
/*      */   {
/* 1772 */     return this.itemLabelGenerator;
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
/*      */   public void setItemLabelGenerator(XYItemLabelGenerator generator)
/*      */   {
/* 1788 */     this.itemLabelGenerator = generator;
/* 1789 */     fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public XYToolTipGenerator getToolTipGenerator()
/*      */   {
/* 1806 */     return this.toolTipGenerator;
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
/*      */   public void setToolTipGenerator(XYToolTipGenerator generator)
/*      */   {
/* 1822 */     this.toolTipGenerator = generator;
/* 1823 */     fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   protected void updateCrosshairValues(CrosshairState crosshairState, double x, double y, double transX, double transY, PlotOrientation orientation)
/*      */   {
/* 1847 */     updateCrosshairValues(crosshairState, x, y, 0, 0, transX, transY, orientation);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/AbstractXYItemRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */