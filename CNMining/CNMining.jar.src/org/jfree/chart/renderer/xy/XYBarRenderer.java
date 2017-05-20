/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Point2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.ItemLabelAnchor;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*      */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.IntervalXYDataset;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.GradientPaintTransformer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.StandardGradientPaintTransformer;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PublicCloneable;
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
/*      */ public class XYBarRenderer
/*      */   extends AbstractXYItemRenderer
/*      */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 770559577251370036L;
/*  161 */   private static XYBarPainter defaultBarPainter = new GradientXYBarPainter();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static XYBarPainter getDefaultBarPainter()
/*      */   {
/*  171 */     return defaultBarPainter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setDefaultBarPainter(XYBarPainter painter)
/*      */   {
/*  182 */     if (painter == null) {
/*  183 */       throw new IllegalArgumentException("Null 'painter' argument.");
/*      */     }
/*  185 */     defaultBarPainter = painter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  191 */   private static boolean defaultShadowsVisible = true;
/*      */   
/*      */   private double base;
/*      */   
/*      */   private boolean useYInterval;
/*      */   private double margin;
/*      */   private boolean drawBarOutline;
/*      */   private GradientPaintTransformer gradientPaintTransformer;
/*      */   private transient Shape legendBar;
/*      */   
/*      */   public static boolean getDefaultShadowsVisible()
/*      */   {
/*  203 */     return defaultShadowsVisible;
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
/*  216 */     defaultShadowsVisible = visible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected class XYBarRendererState
/*      */     extends XYItemRendererState
/*      */   {
/*      */     private double g2Base;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     public XYBarRendererState(PlotRenderingInfo info)
/*      */     {
/*  233 */       super();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public double getG2Base()
/*      */     {
/*  242 */       return this.g2Base;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     public void setG2Base(double value)
/*      */     {
/*  251 */       this.g2Base = value;
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
/*      */   private ItemLabelPosition positiveItemLabelPositionFallback;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private ItemLabelPosition negativeItemLabelPositionFallback;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private XYBarPainter barPainter;
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
/*      */   private double barAlignmentFactor;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYBarRenderer()
/*      */   {
/*  333 */     this(0.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYBarRenderer(double margin)
/*      */   {
/*  343 */     this.margin = margin;
/*  344 */     this.base = 0.0D;
/*  345 */     this.useYInterval = false;
/*  346 */     this.gradientPaintTransformer = new StandardGradientPaintTransformer();
/*  347 */     this.drawBarOutline = false;
/*  348 */     this.legendBar = new Rectangle2D.Double(-3.0D, -5.0D, 6.0D, 10.0D);
/*  349 */     this.barPainter = getDefaultBarPainter();
/*  350 */     this.shadowsVisible = getDefaultShadowsVisible();
/*  351 */     this.shadowXOffset = 4.0D;
/*  352 */     this.shadowYOffset = 4.0D;
/*  353 */     this.barAlignmentFactor = -1.0D;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getBase()
/*      */   {
/*  364 */     return this.base;
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
/*      */   public void setBase(double base)
/*      */   {
/*  378 */     this.base = base;
/*  379 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getUseYInterval()
/*      */   {
/*  391 */     return this.useYInterval;
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
/*      */   public void setUseYInterval(boolean use)
/*      */   {
/*  404 */     if (this.useYInterval != use) {
/*  405 */       this.useYInterval = use;
/*  406 */       fireChangeEvent();
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
/*      */   public double getMargin()
/*      */   {
/*  419 */     return this.margin;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMargin(double margin)
/*      */   {
/*  431 */     this.margin = margin;
/*  432 */     fireChangeEvent();
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
/*  443 */     return this.drawBarOutline;
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
/*  455 */     this.drawBarOutline = draw;
/*  456 */     fireChangeEvent();
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
/*  468 */     return this.gradientPaintTransformer;
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
/*  481 */     this.gradientPaintTransformer = transformer;
/*  482 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getLegendBar()
/*      */   {
/*  494 */     return this.legendBar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendBar(Shape bar)
/*      */   {
/*  506 */     if (bar == null) {
/*  507 */       throw new IllegalArgumentException("Null 'bar' argument.");
/*      */     }
/*  509 */     this.legendBar = bar;
/*  510 */     fireChangeEvent();
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
/*      */   public ItemLabelPosition getPositiveItemLabelPositionFallback()
/*      */   {
/*  523 */     return this.positiveItemLabelPositionFallback;
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
/*      */   public void setPositiveItemLabelPositionFallback(ItemLabelPosition position)
/*      */   {
/*  538 */     this.positiveItemLabelPositionFallback = position;
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
/*      */   public ItemLabelPosition getNegativeItemLabelPositionFallback()
/*      */   {
/*  552 */     return this.negativeItemLabelPositionFallback;
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
/*      */   public void setNegativeItemLabelPositionFallback(ItemLabelPosition position)
/*      */   {
/*  567 */     this.negativeItemLabelPositionFallback = position;
/*  568 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYBarPainter getBarPainter()
/*      */   {
/*  579 */     return this.barPainter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBarPainter(XYBarPainter painter)
/*      */   {
/*  591 */     if (painter == null) {
/*  592 */       throw new IllegalArgumentException("Null 'painter' argument.");
/*      */     }
/*  594 */     this.barPainter = painter;
/*  595 */     fireChangeEvent();
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
/*  607 */     return this.shadowsVisible;
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
/*      */   public void setShadowVisible(boolean visible)
/*      */   {
/*  620 */     this.shadowsVisible = visible;
/*  621 */     fireChangeEvent();
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
/*  632 */     return this.shadowXOffset;
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
/*  644 */     this.shadowXOffset = offset;
/*  645 */     fireChangeEvent();
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
/*  656 */     return this.shadowYOffset;
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
/*  668 */     this.shadowYOffset = offset;
/*  669 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getBarAlignmentFactor()
/*      */   {
/*  680 */     return this.barAlignmentFactor;
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
/*      */   public void setBarAlignmentFactor(double factor)
/*      */   {
/*  693 */     this.barAlignmentFactor = factor;
/*  694 */     fireChangeEvent();
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
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset dataset, PlotRenderingInfo info)
/*      */   {
/*  715 */     XYBarRendererState state = new XYBarRendererState(info);
/*  716 */     ValueAxis rangeAxis = plot.getRangeAxisForDataset(plot.indexOf(dataset));
/*      */     
/*  718 */     state.setG2Base(rangeAxis.valueToJava2D(this.base, dataArea, plot.getRangeAxisEdge()));
/*      */     
/*  720 */     return state;
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
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/*  734 */     LegendItem result = null;
/*  735 */     XYPlot xyplot = getPlot();
/*  736 */     if (xyplot != null) {
/*  737 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/*  738 */       if (dataset != null) {
/*  739 */         XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
/*  740 */         String label = lg.generateLabel(dataset, series);
/*  741 */         String description = label;
/*  742 */         String toolTipText = null;
/*  743 */         if (getLegendItemToolTipGenerator() != null) {
/*  744 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */         }
/*      */         
/*  747 */         String urlText = null;
/*  748 */         if (getLegendItemURLGenerator() != null) {
/*  749 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */         }
/*      */         
/*  752 */         Shape shape = this.legendBar;
/*  753 */         Paint paint = lookupSeriesPaint(series);
/*  754 */         Paint outlinePaint = lookupSeriesOutlinePaint(series);
/*  755 */         Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*  756 */         if (this.drawBarOutline) {
/*  757 */           result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*      */         }
/*      */         else
/*      */         {
/*  761 */           result = new LegendItem(label, description, toolTipText, urlText, shape, paint);
/*      */         }
/*      */         
/*  764 */         result.setLabelFont(lookupLegendTextFont(series));
/*  765 */         Paint labelPaint = lookupLegendTextPaint(series);
/*  766 */         if (labelPaint != null) {
/*  767 */           result.setLabelPaint(labelPaint);
/*      */         }
/*  769 */         result.setDataset(dataset);
/*  770 */         result.setDatasetIndex(datasetIndex);
/*  771 */         result.setSeriesKey(dataset.getSeriesKey(series));
/*  772 */         result.setSeriesIndex(series);
/*  773 */         if (getGradientPaintTransformer() != null) {
/*  774 */           result.setFillPaintTransformer(getGradientPaintTransformer());
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*  779 */     return result;
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
/*      */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*      */   {
/*  813 */     if (!getItemVisible(series, item)) {
/*  814 */       return;
/*      */     }
/*  816 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/*      */     double value1;
/*      */     double value0;
/*      */     double value1;
/*  820 */     if (this.useYInterval) {
/*  821 */       double value0 = intervalDataset.getStartYValue(series, item);
/*  822 */       value1 = intervalDataset.getEndYValue(series, item);
/*      */     }
/*      */     else {
/*  825 */       value0 = this.base;
/*  826 */       value1 = intervalDataset.getYValue(series, item);
/*      */     }
/*  828 */     if ((Double.isNaN(value0)) || (Double.isNaN(value1))) {
/*  829 */       return;
/*      */     }
/*  831 */     if (value0 <= value1) {
/*  832 */       if (rangeAxis.getRange().intersects(value0, value1)) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*  837 */     else if (!rangeAxis.getRange().intersects(value1, value0)) {
/*  838 */       return;
/*      */     }
/*      */     
/*      */ 
/*  842 */     double translatedValue0 = rangeAxis.valueToJava2D(value0, dataArea, plot.getRangeAxisEdge());
/*      */     
/*  844 */     double translatedValue1 = rangeAxis.valueToJava2D(value1, dataArea, plot.getRangeAxisEdge());
/*      */     
/*  846 */     double bottom = Math.min(translatedValue0, translatedValue1);
/*  847 */     double top = Math.max(translatedValue0, translatedValue1);
/*      */     
/*  849 */     double startX = intervalDataset.getStartXValue(series, item);
/*  850 */     if (Double.isNaN(startX)) {
/*  851 */       return;
/*      */     }
/*  853 */     double endX = intervalDataset.getEndXValue(series, item);
/*  854 */     if (Double.isNaN(endX)) {
/*  855 */       return;
/*      */     }
/*  857 */     if (startX <= endX) {
/*  858 */       if (domainAxis.getRange().intersects(startX, endX)) {}
/*      */ 
/*      */ 
/*      */ 
/*      */     }
/*  863 */     else if (!domainAxis.getRange().intersects(endX, startX)) {
/*  864 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  869 */     if ((this.barAlignmentFactor >= 0.0D) && (this.barAlignmentFactor <= 1.0D)) {
/*  870 */       double x = intervalDataset.getXValue(series, item);
/*  871 */       double interval = endX - startX;
/*  872 */       startX = x - interval * this.barAlignmentFactor;
/*  873 */       endX = startX + interval;
/*      */     }
/*      */     
/*  876 */     RectangleEdge location = plot.getDomainAxisEdge();
/*  877 */     double translatedStartX = domainAxis.valueToJava2D(startX, dataArea, location);
/*      */     
/*  879 */     double translatedEndX = domainAxis.valueToJava2D(endX, dataArea, location);
/*      */     
/*      */ 
/*  882 */     double translatedWidth = Math.max(1.0D, Math.abs(translatedEndX - translatedStartX));
/*      */     
/*      */ 
/*  885 */     double left = Math.min(translatedStartX, translatedEndX);
/*  886 */     if (getMargin() > 0.0D) {
/*  887 */       double cut = translatedWidth * getMargin();
/*  888 */       translatedWidth -= cut;
/*  889 */       left += cut / 2.0D;
/*      */     }
/*      */     
/*  892 */     Rectangle2D bar = null;
/*  893 */     PlotOrientation orientation = plot.getOrientation();
/*  894 */     if (orientation == PlotOrientation.HORIZONTAL)
/*      */     {
/*  896 */       bottom = Math.max(bottom, dataArea.getMinX());
/*  897 */       top = Math.min(top, dataArea.getMaxX());
/*  898 */       bar = new Rectangle2D.Double(bottom, left, top - bottom, translatedWidth);
/*      */ 
/*      */     }
/*  901 */     else if (orientation == PlotOrientation.VERTICAL)
/*      */     {
/*  903 */       bottom = Math.max(bottom, dataArea.getMinY());
/*  904 */       top = Math.min(top, dataArea.getMaxY());
/*  905 */       bar = new Rectangle2D.Double(left, bottom, translatedWidth, top - bottom);
/*      */     }
/*      */     
/*      */ 
/*  909 */     boolean positive = value1 > 0.0D;
/*  910 */     boolean inverted = rangeAxis.isInverted();
/*      */     RectangleEdge barBase;
/*  912 */     RectangleEdge barBase; if (orientation == PlotOrientation.HORIZONTAL) { RectangleEdge barBase;
/*  913 */       if (((positive) && (inverted)) || ((!positive) && (!inverted))) {
/*  914 */         barBase = RectangleEdge.RIGHT;
/*      */       }
/*      */       else {
/*  917 */         barBase = RectangleEdge.LEFT;
/*      */       }
/*      */     } else {
/*      */       RectangleEdge barBase;
/*  921 */       if (((positive) && (!inverted)) || ((!positive) && (inverted))) {
/*  922 */         barBase = RectangleEdge.BOTTOM;
/*      */       }
/*      */       else {
/*  925 */         barBase = RectangleEdge.TOP;
/*      */       }
/*      */     }
/*  928 */     if (getShadowsVisible()) {
/*  929 */       this.barPainter.paintBarShadow(g2, this, series, item, bar, barBase, !this.useYInterval);
/*      */     }
/*      */     
/*  932 */     this.barPainter.paintBar(g2, this, series, item, bar, barBase);
/*      */     
/*  934 */     if (isItemLabelVisible(series, item)) {
/*  935 */       XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
/*      */       
/*  937 */       drawItemLabel(g2, dataset, series, item, plot, generator, bar, value1 < 0.0D);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  942 */     double x1 = (startX + endX) / 2.0D;
/*  943 */     double y1 = dataset.getYValue(series, item);
/*  944 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, location);
/*  945 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
/*      */     
/*  947 */     int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/*  948 */     int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/*  949 */     updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, plot.getOrientation());
/*      */     
/*      */ 
/*  952 */     EntityCollection entities = state.getEntityCollection();
/*  953 */     if (entities != null) {
/*  954 */       addEntity(entities, bar, dataset, series, item, 0.0D, 0.0D);
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
/*      */   protected void drawItemLabel(Graphics2D g2, XYDataset dataset, int series, int item, XYPlot plot, XYItemLabelGenerator generator, Rectangle2D bar, boolean negative)
/*      */   {
/*  979 */     if (generator == null) {
/*  980 */       return;
/*      */     }
/*  982 */     String label = generator.generateLabel(dataset, series, item);
/*  983 */     if (label == null) {
/*  984 */       return;
/*      */     }
/*      */     
/*  987 */     Font labelFont = getItemLabelFont(series, item);
/*  988 */     g2.setFont(labelFont);
/*  989 */     Paint paint = getItemLabelPaint(series, item);
/*  990 */     g2.setPaint(paint);
/*      */     
/*      */ 
/*  993 */     ItemLabelPosition position = null;
/*  994 */     if (!negative) {
/*  995 */       position = getPositiveItemLabelPosition(series, item);
/*      */     }
/*      */     else {
/*  998 */       position = getNegativeItemLabelPosition(series, item);
/*      */     }
/*      */     
/*      */ 
/* 1002 */     Point2D anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), bar, plot.getOrientation());
/*      */     
/*      */ 
/* 1005 */     if (isInternalAnchor(position.getItemLabelAnchor())) {
/* 1006 */       Shape bounds = TextUtilities.calculateRotatedStringBounds(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1011 */       if ((bounds != null) && 
/* 1012 */         (!bar.contains(bounds.getBounds2D()))) {
/* 1013 */         if (!negative) {
/* 1014 */           position = getPositiveItemLabelPositionFallback();
/*      */         }
/*      */         else {
/* 1017 */           position = getNegativeItemLabelPositionFallback();
/*      */         }
/* 1019 */         if (position != null) {
/* 1020 */           anchorPoint = calculateLabelAnchorPoint(position.getItemLabelAnchor(), bar, plot.getOrientation());
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1029 */     if (position != null) {
/* 1030 */       TextUtilities.drawRotatedString(label, g2, (float)anchorPoint.getX(), (float)anchorPoint.getY(), position.getTextAnchor(), position.getAngle(), position.getRotationAnchor());
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
/*      */   private Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor, Rectangle2D bar, PlotOrientation orientation)
/*      */   {
/* 1049 */     Point2D result = null;
/* 1050 */     double offset = getItemLabelAnchorOffset();
/* 1051 */     double x0 = bar.getX() - offset;
/* 1052 */     double x1 = bar.getX();
/* 1053 */     double x2 = bar.getX() + offset;
/* 1054 */     double x3 = bar.getCenterX();
/* 1055 */     double x4 = bar.getMaxX() - offset;
/* 1056 */     double x5 = bar.getMaxX();
/* 1057 */     double x6 = bar.getMaxX() + offset;
/*      */     
/* 1059 */     double y0 = bar.getMaxY() + offset;
/* 1060 */     double y1 = bar.getMaxY();
/* 1061 */     double y2 = bar.getMaxY() - offset;
/* 1062 */     double y3 = bar.getCenterY();
/* 1063 */     double y4 = bar.getMinY() + offset;
/* 1064 */     double y5 = bar.getMinY();
/* 1065 */     double y6 = bar.getMinY() - offset;
/*      */     
/* 1067 */     if (anchor == ItemLabelAnchor.CENTER) {
/* 1068 */       result = new Point2D.Double(x3, y3);
/*      */     }
/* 1070 */     else if (anchor == ItemLabelAnchor.INSIDE1) {
/* 1071 */       result = new Point2D.Double(x4, y4);
/*      */     }
/* 1073 */     else if (anchor == ItemLabelAnchor.INSIDE2) {
/* 1074 */       result = new Point2D.Double(x4, y4);
/*      */     }
/* 1076 */     else if (anchor == ItemLabelAnchor.INSIDE3) {
/* 1077 */       result = new Point2D.Double(x4, y3);
/*      */     }
/* 1079 */     else if (anchor == ItemLabelAnchor.INSIDE4) {
/* 1080 */       result = new Point2D.Double(x4, y2);
/*      */     }
/* 1082 */     else if (anchor == ItemLabelAnchor.INSIDE5) {
/* 1083 */       result = new Point2D.Double(x4, y2);
/*      */     }
/* 1085 */     else if (anchor == ItemLabelAnchor.INSIDE6) {
/* 1086 */       result = new Point2D.Double(x3, y2);
/*      */     }
/* 1088 */     else if (anchor == ItemLabelAnchor.INSIDE7) {
/* 1089 */       result = new Point2D.Double(x2, y2);
/*      */     }
/* 1091 */     else if (anchor == ItemLabelAnchor.INSIDE8) {
/* 1092 */       result = new Point2D.Double(x2, y2);
/*      */     }
/* 1094 */     else if (anchor == ItemLabelAnchor.INSIDE9) {
/* 1095 */       result = new Point2D.Double(x2, y3);
/*      */     }
/* 1097 */     else if (anchor == ItemLabelAnchor.INSIDE10) {
/* 1098 */       result = new Point2D.Double(x2, y4);
/*      */     }
/* 1100 */     else if (anchor == ItemLabelAnchor.INSIDE11) {
/* 1101 */       result = new Point2D.Double(x2, y4);
/*      */     }
/* 1103 */     else if (anchor == ItemLabelAnchor.INSIDE12) {
/* 1104 */       result = new Point2D.Double(x3, y4);
/*      */     }
/* 1106 */     else if (anchor == ItemLabelAnchor.OUTSIDE1) {
/* 1107 */       result = new Point2D.Double(x5, y6);
/*      */     }
/* 1109 */     else if (anchor == ItemLabelAnchor.OUTSIDE2) {
/* 1110 */       result = new Point2D.Double(x6, y5);
/*      */     }
/* 1112 */     else if (anchor == ItemLabelAnchor.OUTSIDE3) {
/* 1113 */       result = new Point2D.Double(x6, y3);
/*      */     }
/* 1115 */     else if (anchor == ItemLabelAnchor.OUTSIDE4) {
/* 1116 */       result = new Point2D.Double(x6, y1);
/*      */     }
/* 1118 */     else if (anchor == ItemLabelAnchor.OUTSIDE5) {
/* 1119 */       result = new Point2D.Double(x5, y0);
/*      */     }
/* 1121 */     else if (anchor == ItemLabelAnchor.OUTSIDE6) {
/* 1122 */       result = new Point2D.Double(x3, y0);
/*      */     }
/* 1124 */     else if (anchor == ItemLabelAnchor.OUTSIDE7) {
/* 1125 */       result = new Point2D.Double(x1, y0);
/*      */     }
/* 1127 */     else if (anchor == ItemLabelAnchor.OUTSIDE8) {
/* 1128 */       result = new Point2D.Double(x0, y1);
/*      */     }
/* 1130 */     else if (anchor == ItemLabelAnchor.OUTSIDE9) {
/* 1131 */       result = new Point2D.Double(x0, y3);
/*      */     }
/* 1133 */     else if (anchor == ItemLabelAnchor.OUTSIDE10) {
/* 1134 */       result = new Point2D.Double(x0, y5);
/*      */     }
/* 1136 */     else if (anchor == ItemLabelAnchor.OUTSIDE11) {
/* 1137 */       result = new Point2D.Double(x1, y6);
/*      */     }
/* 1139 */     else if (anchor == ItemLabelAnchor.OUTSIDE12) {
/* 1140 */       result = new Point2D.Double(x3, y6);
/*      */     }
/*      */     
/* 1143 */     return result;
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
/* 1155 */     return (anchor == ItemLabelAnchor.CENTER) || (anchor == ItemLabelAnchor.INSIDE1) || (anchor == ItemLabelAnchor.INSIDE2) || (anchor == ItemLabelAnchor.INSIDE3) || (anchor == ItemLabelAnchor.INSIDE4) || (anchor == ItemLabelAnchor.INSIDE5) || (anchor == ItemLabelAnchor.INSIDE6) || (anchor == ItemLabelAnchor.INSIDE7) || (anchor == ItemLabelAnchor.INSIDE8) || (anchor == ItemLabelAnchor.INSIDE9) || (anchor == ItemLabelAnchor.INSIDE10) || (anchor == ItemLabelAnchor.INSIDE11) || (anchor == ItemLabelAnchor.INSIDE12);
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
/*      */   public Range findDomainBounds(XYDataset dataset)
/*      */   {
/* 1181 */     if (dataset != null) {
/* 1182 */       return DatasetUtilities.findDomainBounds(dataset, true);
/*      */     }
/*      */     
/* 1185 */     return null;
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
/*      */   public Range findRangeBounds(XYDataset dataset)
/*      */   {
/* 1200 */     if (dataset != null) {
/* 1201 */       return DatasetUtilities.findRangeBounds(dataset, this.useYInterval);
/*      */     }
/*      */     
/*      */ 
/* 1205 */     return null;
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
/* 1217 */     XYBarRenderer result = (XYBarRenderer)super.clone();
/* 1218 */     if (this.gradientPaintTransformer != null) {
/* 1219 */       result.gradientPaintTransformer = ((GradientPaintTransformer)ObjectUtilities.clone(this.gradientPaintTransformer));
/*      */     }
/*      */     
/* 1222 */     result.legendBar = ShapeUtilities.clone(this.legendBar);
/* 1223 */     return result;
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
/* 1234 */     if (obj == this) {
/* 1235 */       return true;
/*      */     }
/* 1237 */     if (!(obj instanceof XYBarRenderer)) {
/* 1238 */       return false;
/*      */     }
/* 1240 */     XYBarRenderer that = (XYBarRenderer)obj;
/* 1241 */     if (this.base != that.base) {
/* 1242 */       return false;
/*      */     }
/* 1244 */     if (this.drawBarOutline != that.drawBarOutline) {
/* 1245 */       return false;
/*      */     }
/* 1247 */     if (this.margin != that.margin) {
/* 1248 */       return false;
/*      */     }
/* 1250 */     if (this.useYInterval != that.useYInterval) {
/* 1251 */       return false;
/*      */     }
/* 1253 */     if (!ObjectUtilities.equal(this.gradientPaintTransformer, that.gradientPaintTransformer))
/*      */     {
/*      */ 
/* 1256 */       return false;
/*      */     }
/* 1258 */     if (!ShapeUtilities.equal(this.legendBar, that.legendBar)) {
/* 1259 */       return false;
/*      */     }
/* 1261 */     if (!ObjectUtilities.equal(this.positiveItemLabelPositionFallback, that.positiveItemLabelPositionFallback))
/*      */     {
/* 1263 */       return false;
/*      */     }
/* 1265 */     if (!ObjectUtilities.equal(this.negativeItemLabelPositionFallback, that.negativeItemLabelPositionFallback))
/*      */     {
/* 1267 */       return false;
/*      */     }
/* 1269 */     if (!this.barPainter.equals(that.barPainter)) {
/* 1270 */       return false;
/*      */     }
/* 1272 */     if (this.shadowsVisible != that.shadowsVisible) {
/* 1273 */       return false;
/*      */     }
/* 1275 */     if (this.shadowXOffset != that.shadowXOffset) {
/* 1276 */       return false;
/*      */     }
/* 1278 */     if (this.shadowYOffset != that.shadowYOffset) {
/* 1279 */       return false;
/*      */     }
/* 1281 */     if (this.barAlignmentFactor != that.barAlignmentFactor) {
/* 1282 */       return false;
/*      */     }
/* 1284 */     return super.equals(obj);
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
/* 1297 */     stream.defaultReadObject();
/* 1298 */     this.legendBar = SerialUtilities.readShape(stream);
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
/* 1309 */     stream.defaultWriteObject();
/* 1310 */     SerialUtilities.writeShape(this.legendBar, stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */