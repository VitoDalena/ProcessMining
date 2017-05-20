/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Polygon;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.font.FontRenderContext;
/*      */ import java.awt.font.LineMetrics;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Arc2D.Double;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Point2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.entity.CategoryItemEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*      */ import org.jfree.chart.labels.CategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
/*      */ import org.jfree.chart.urls.CategoryURLGenerator;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintList;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.Rotation;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.StrokeList;
/*      */ import org.jfree.util.TableOrder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class SpiderWebPlot
/*      */   extends Plot
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -5376340422031599463L;
/*      */   public static final double DEFAULT_HEAD = 0.01D;
/*      */   public static final double DEFAULT_AXIS_LABEL_GAP = 0.1D;
/*      */   public static final double DEFAULT_INTERIOR_GAP = 0.25D;
/*      */   public static final double MAX_INTERIOR_GAP = 0.4D;
/*      */   public static final double DEFAULT_START_ANGLE = 90.0D;
/*  145 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*      */ 
/*      */ 
/*  149 */   public static final Paint DEFAULT_LABEL_PAINT = Color.black;
/*      */   
/*      */ 
/*  152 */   public static final Paint DEFAULT_LABEL_BACKGROUND_PAINT = new Color(255, 255, 192);
/*      */   
/*      */ 
/*      */ 
/*  156 */   public static final Paint DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
/*      */   
/*      */ 
/*  159 */   public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE = new BasicStroke(0.5F);
/*      */   
/*      */ 
/*      */ 
/*  163 */   public static final Paint DEFAULT_LABEL_SHADOW_PAINT = Color.lightGray;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final double DEFAULT_MAX_VALUE = -1.0D;
/*      */   
/*      */ 
/*      */ 
/*      */   protected double headPercent;
/*      */   
/*      */ 
/*      */ 
/*      */   private double interiorGap;
/*      */   
/*      */ 
/*      */ 
/*      */   private double axisLabelGap;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint axisLinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke axisLineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private CategoryDataset dataset;
/*      */   
/*      */ 
/*      */ 
/*      */   private double maxValue;
/*      */   
/*      */ 
/*      */ 
/*      */   private TableOrder dataExtractOrder;
/*      */   
/*      */ 
/*      */ 
/*      */   private double startAngle;
/*      */   
/*      */ 
/*      */ 
/*      */   private Rotation direction;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Shape legendItemShape;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint seriesPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private PaintList seriesPaintList;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint baseSeriesPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint seriesOutlinePaint;
/*      */   
/*      */ 
/*      */   private PaintList seriesOutlinePaintList;
/*      */   
/*      */ 
/*      */   private transient Paint baseSeriesOutlinePaint;
/*      */   
/*      */ 
/*      */   private transient Stroke seriesOutlineStroke;
/*      */   
/*      */ 
/*      */   private StrokeList seriesOutlineStrokeList;
/*      */   
/*      */ 
/*      */   private transient Stroke baseSeriesOutlineStroke;
/*      */   
/*      */ 
/*      */   private Font labelFont;
/*      */   
/*      */ 
/*      */   private transient Paint labelPaint;
/*      */   
/*      */ 
/*      */   private CategoryItemLabelGenerator labelGenerator;
/*      */   
/*      */ 
/*  254 */   private boolean webFilled = true;
/*      */   
/*      */ 
/*      */   private CategoryToolTipGenerator toolTipGenerator;
/*      */   
/*      */ 
/*      */   private CategoryURLGenerator urlGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */   public SpiderWebPlot()
/*      */   {
/*  266 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SpiderWebPlot(CategoryDataset dataset)
/*      */   {
/*  276 */     this(dataset, TableOrder.BY_ROW);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SpiderWebPlot(CategoryDataset dataset, TableOrder extract)
/*      */   {
/*  288 */     if (extract == null) {
/*  289 */       throw new IllegalArgumentException("Null 'extract' argument.");
/*      */     }
/*  291 */     this.dataset = dataset;
/*  292 */     if (dataset != null) {
/*  293 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*  296 */     this.dataExtractOrder = extract;
/*  297 */     this.headPercent = 0.01D;
/*  298 */     this.axisLabelGap = 0.1D;
/*  299 */     this.axisLinePaint = Color.black;
/*  300 */     this.axisLineStroke = new BasicStroke(1.0F);
/*      */     
/*  302 */     this.interiorGap = 0.25D;
/*  303 */     this.startAngle = 90.0D;
/*  304 */     this.direction = Rotation.CLOCKWISE;
/*  305 */     this.maxValue = -1.0D;
/*      */     
/*  307 */     this.seriesPaint = null;
/*  308 */     this.seriesPaintList = new PaintList();
/*  309 */     this.baseSeriesPaint = null;
/*      */     
/*  311 */     this.seriesOutlinePaint = null;
/*  312 */     this.seriesOutlinePaintList = new PaintList();
/*  313 */     this.baseSeriesOutlinePaint = DEFAULT_OUTLINE_PAINT;
/*      */     
/*  315 */     this.seriesOutlineStroke = null;
/*  316 */     this.seriesOutlineStrokeList = new StrokeList();
/*  317 */     this.baseSeriesOutlineStroke = DEFAULT_OUTLINE_STROKE;
/*      */     
/*  319 */     this.labelFont = DEFAULT_LABEL_FONT;
/*  320 */     this.labelPaint = DEFAULT_LABEL_PAINT;
/*  321 */     this.labelGenerator = new StandardCategoryItemLabelGenerator();
/*      */     
/*  323 */     this.legendItemShape = DEFAULT_LEGEND_ITEM_CIRCLE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/*  333 */     return "Spider Web Plot";
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryDataset getDataset()
/*      */   {
/*  344 */     return this.dataset;
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
/*      */   public void setDataset(CategoryDataset dataset)
/*      */   {
/*  358 */     if (this.dataset != null) {
/*  359 */       this.dataset.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  363 */     this.dataset = dataset;
/*  364 */     if (dataset != null) {
/*  365 */       setDatasetGroup(dataset.getGroup());
/*  366 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  370 */     datasetChanged(new DatasetChangeEvent(this, dataset));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isWebFilled()
/*      */   {
/*  381 */     return this.webFilled;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWebFilled(boolean flag)
/*      */   {
/*  393 */     this.webFilled = flag;
/*  394 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TableOrder getDataExtractOrder()
/*      */   {
/*  405 */     return this.dataExtractOrder;
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
/*      */   public void setDataExtractOrder(TableOrder order)
/*      */   {
/*  420 */     if (order == null) {
/*  421 */       throw new IllegalArgumentException("Null 'order' argument");
/*      */     }
/*  423 */     this.dataExtractOrder = order;
/*  424 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getHeadPercent()
/*      */   {
/*  435 */     return this.headPercent;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setHeadPercent(double percent)
/*      */   {
/*  447 */     this.headPercent = percent;
/*  448 */     fireChangeEvent();
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
/*      */   public double getStartAngle()
/*      */   {
/*  462 */     return this.startAngle;
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
/*      */   public void setStartAngle(double angle)
/*      */   {
/*  478 */     this.startAngle = angle;
/*  479 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMaxValue()
/*      */   {
/*  490 */     return this.maxValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaxValue(double value)
/*      */   {
/*  502 */     this.maxValue = value;
/*  503 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rotation getDirection()
/*      */   {
/*  515 */     return this.direction;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDirection(Rotation direction)
/*      */   {
/*  527 */     if (direction == null) {
/*  528 */       throw new IllegalArgumentException("Null 'direction' argument.");
/*      */     }
/*  530 */     this.direction = direction;
/*  531 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getInteriorGap()
/*      */   {
/*  543 */     return this.interiorGap;
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
/*      */   public void setInteriorGap(double percent)
/*      */   {
/*  556 */     if ((percent < 0.0D) || (percent > 0.4D)) {
/*  557 */       throw new IllegalArgumentException("Percentage outside valid range.");
/*      */     }
/*      */     
/*  560 */     if (this.interiorGap != percent) {
/*  561 */       this.interiorGap = percent;
/*  562 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getAxisLabelGap()
/*      */   {
/*  574 */     return this.axisLabelGap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxisLabelGap(double gap)
/*      */   {
/*  586 */     this.axisLabelGap = gap;
/*  587 */     fireChangeEvent();
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
/*      */   public Paint getAxisLinePaint()
/*      */   {
/*  600 */     return this.axisLinePaint;
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
/*      */   public void setAxisLinePaint(Paint paint)
/*      */   {
/*  613 */     if (paint == null) {
/*  614 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  616 */     this.axisLinePaint = paint;
/*  617 */     fireChangeEvent();
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
/*      */   public Stroke getAxisLineStroke()
/*      */   {
/*  630 */     return this.axisLineStroke;
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
/*      */   public void setAxisLineStroke(Stroke stroke)
/*      */   {
/*  643 */     if (stroke == null) {
/*  644 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  646 */     this.axisLineStroke = stroke;
/*  647 */     fireChangeEvent();
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
/*      */   public Paint getSeriesPaint()
/*      */   {
/*  660 */     return this.seriesPaint;
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
/*      */   public void setSeriesPaint(Paint paint)
/*      */   {
/*  673 */     this.seriesPaint = paint;
/*  674 */     fireChangeEvent();
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
/*      */   public Paint getSeriesPaint(int series)
/*      */   {
/*  689 */     if (this.seriesPaint != null) {
/*  690 */       return this.seriesPaint;
/*      */     }
/*      */     
/*      */ 
/*  694 */     Paint result = this.seriesPaintList.getPaint(series);
/*  695 */     if (result == null) {
/*  696 */       DrawingSupplier supplier = getDrawingSupplier();
/*  697 */       if (supplier != null) {
/*  698 */         Paint p = supplier.getNextPaint();
/*  699 */         this.seriesPaintList.setPaint(series, p);
/*  700 */         result = p;
/*      */       }
/*      */       else {
/*  703 */         result = this.baseSeriesPaint;
/*      */       }
/*      */     }
/*  706 */     return result;
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
/*      */   public void setSeriesPaint(int series, Paint paint)
/*      */   {
/*  720 */     this.seriesPaintList.setPaint(series, paint);
/*  721 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaseSeriesPaint()
/*      */   {
/*  733 */     return this.baseSeriesPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseSeriesPaint(Paint paint)
/*      */   {
/*  744 */     if (paint == null) {
/*  745 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  747 */     this.baseSeriesPaint = paint;
/*  748 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getSeriesOutlinePaint()
/*      */   {
/*  759 */     return this.seriesOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesOutlinePaint(Paint paint)
/*      */   {
/*  770 */     this.seriesOutlinePaint = paint;
/*  771 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getSeriesOutlinePaint(int series)
/*      */   {
/*  783 */     if (this.seriesOutlinePaint != null) {
/*  784 */       return this.seriesOutlinePaint;
/*      */     }
/*      */     
/*  787 */     Paint result = this.seriesOutlinePaintList.getPaint(series);
/*  788 */     if (result == null) {
/*  789 */       result = this.baseSeriesOutlinePaint;
/*      */     }
/*  791 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesOutlinePaint(int series, Paint paint)
/*      */   {
/*  802 */     this.seriesOutlinePaintList.setPaint(series, paint);
/*  803 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaseSeriesOutlinePaint()
/*      */   {
/*  813 */     return this.baseSeriesOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseSeriesOutlinePaint(Paint paint)
/*      */   {
/*  822 */     if (paint == null) {
/*  823 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  825 */     this.baseSeriesOutlinePaint = paint;
/*  826 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getSeriesOutlineStroke()
/*      */   {
/*  837 */     return this.seriesOutlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesOutlineStroke(Stroke stroke)
/*      */   {
/*  848 */     this.seriesOutlineStroke = stroke;
/*  849 */     fireChangeEvent();
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
/*      */   public Stroke getSeriesOutlineStroke(int series)
/*      */   {
/*  862 */     if (this.seriesOutlineStroke != null) {
/*  863 */       return this.seriesOutlineStroke;
/*      */     }
/*      */     
/*      */ 
/*  867 */     Stroke result = this.seriesOutlineStrokeList.getStroke(series);
/*  868 */     if (result == null) {
/*  869 */       result = this.baseSeriesOutlineStroke;
/*      */     }
/*  871 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesOutlineStroke(int series, Stroke stroke)
/*      */   {
/*  883 */     this.seriesOutlineStrokeList.setStroke(series, stroke);
/*  884 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getBaseSeriesOutlineStroke()
/*      */   {
/*  894 */     return this.baseSeriesOutlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseSeriesOutlineStroke(Stroke stroke)
/*      */   {
/*  903 */     if (stroke == null) {
/*  904 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  906 */     this.baseSeriesOutlineStroke = stroke;
/*  907 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getLegendItemShape()
/*      */   {
/*  918 */     return this.legendItemShape;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendItemShape(Shape shape)
/*      */   {
/*  930 */     if (shape == null) {
/*  931 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*      */     }
/*  933 */     this.legendItemShape = shape;
/*  934 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getLabelFont()
/*      */   {
/*  945 */     return this.labelFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelFont(Font font)
/*      */   {
/*  957 */     if (font == null) {
/*  958 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  960 */     this.labelFont = font;
/*  961 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelPaint()
/*      */   {
/*  972 */     return this.labelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelPaint(Paint paint)
/*      */   {
/*  984 */     if (paint == null) {
/*  985 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  987 */     this.labelPaint = paint;
/*  988 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryItemLabelGenerator getLabelGenerator()
/*      */   {
/*  999 */     return this.labelGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelGenerator(CategoryItemLabelGenerator generator)
/*      */   {
/* 1011 */     if (generator == null) {
/* 1012 */       throw new IllegalArgumentException("Null 'generator' argument.");
/*      */     }
/* 1014 */     this.labelGenerator = generator;
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
/*      */   public CategoryToolTipGenerator getToolTipGenerator()
/*      */   {
/* 1027 */     return this.toolTipGenerator;
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
/*      */   public void setToolTipGenerator(CategoryToolTipGenerator generator)
/*      */   {
/* 1041 */     this.toolTipGenerator = generator;
/* 1042 */     fireChangeEvent();
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
/*      */   public CategoryURLGenerator getURLGenerator()
/*      */   {
/* 1055 */     return this.urlGenerator;
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
/*      */   public void setURLGenerator(CategoryURLGenerator generator)
/*      */   {
/* 1069 */     this.urlGenerator = generator;
/* 1070 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItemCollection getLegendItems()
/*      */   {
/* 1079 */     LegendItemCollection result = new LegendItemCollection();
/* 1080 */     if (getDataset() == null) {
/* 1081 */       return result;
/*      */     }
/*      */     
/* 1084 */     List keys = null;
/* 1085 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 1086 */       keys = this.dataset.getRowKeys();
/*      */     }
/* 1088 */     else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
/* 1089 */       keys = this.dataset.getColumnKeys();
/*      */     }
/*      */     
/* 1092 */     if (keys != null) {
/* 1093 */       int series = 0;
/* 1094 */       Iterator iterator = keys.iterator();
/* 1095 */       Shape shape = getLegendItemShape();
/*      */       
/* 1097 */       while (iterator.hasNext()) {
/* 1098 */         String label = iterator.next().toString();
/* 1099 */         String description = label;
/*      */         
/* 1101 */         Paint paint = getSeriesPaint(series);
/* 1102 */         Paint outlinePaint = getSeriesOutlinePaint(series);
/* 1103 */         Stroke stroke = getSeriesOutlineStroke(series);
/* 1104 */         LegendItem item = new LegendItem(label, description, null, null, shape, paint, stroke, outlinePaint);
/*      */         
/* 1106 */         item.setDataset(getDataset());
/* 1107 */         result.add(item);
/* 1108 */         series++;
/*      */       }
/*      */     }
/*      */     
/* 1112 */     return result;
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
/*      */   protected Point2D getWebPoint(Rectangle2D bounds, double angle, double length)
/*      */   {
/* 1127 */     double angrad = Math.toRadians(angle);
/* 1128 */     double x = Math.cos(angrad) * length * bounds.getWidth() / 2.0D;
/* 1129 */     double y = -Math.sin(angrad) * length * bounds.getHeight() / 2.0D;
/*      */     
/* 1131 */     return new Point2D.Double(bounds.getX() + x + bounds.getWidth() / 2.0D, bounds.getY() + y + bounds.getHeight() / 2.0D);
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
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info)
/*      */   {
/* 1149 */     RectangleInsets insets = getInsets();
/* 1150 */     insets.trim(area);
/*      */     
/* 1152 */     if (info != null) {
/* 1153 */       info.setPlotArea(area);
/* 1154 */       info.setDataArea(area);
/*      */     }
/*      */     
/* 1157 */     drawBackground(g2, area);
/* 1158 */     drawOutline(g2, area);
/*      */     
/* 1160 */     Shape savedClip = g2.getClip();
/*      */     
/* 1162 */     g2.clip(area);
/* 1163 */     Composite originalComposite = g2.getComposite();
/* 1164 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*      */ 
/* 1167 */     if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
/* 1168 */       int seriesCount = 0;int catCount = 0;
/*      */       
/* 1170 */       if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 1171 */         seriesCount = this.dataset.getRowCount();
/* 1172 */         catCount = this.dataset.getColumnCount();
/*      */       }
/*      */       else {
/* 1175 */         seriesCount = this.dataset.getColumnCount();
/* 1176 */         catCount = this.dataset.getRowCount();
/*      */       }
/*      */       
/*      */ 
/* 1180 */       if (this.maxValue == -1.0D) {
/* 1181 */         calculateMaxValue(seriesCount, catCount);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1187 */       double gapHorizontal = area.getWidth() * getInteriorGap();
/* 1188 */       double gapVertical = area.getHeight() * getInteriorGap();
/*      */       
/* 1190 */       double X = area.getX() + gapHorizontal / 2.0D;
/* 1191 */       double Y = area.getY() + gapVertical / 2.0D;
/* 1192 */       double W = area.getWidth() - gapHorizontal;
/* 1193 */       double H = area.getHeight() - gapVertical;
/*      */       
/* 1195 */       double headW = area.getWidth() * this.headPercent;
/* 1196 */       double headH = area.getHeight() * this.headPercent;
/*      */       
/*      */ 
/* 1199 */       double min = Math.min(W, H) / 2.0D;
/* 1200 */       X = (X + X + W) / 2.0D - min;
/* 1201 */       Y = (Y + Y + H) / 2.0D - min;
/* 1202 */       W = 2.0D * min;
/* 1203 */       H = 2.0D * min;
/*      */       
/* 1205 */       Point2D centre = new Point2D.Double(X + W / 2.0D, Y + H / 2.0D);
/* 1206 */       Rectangle2D radarArea = new Rectangle2D.Double(X, Y, W, H);
/*      */       
/*      */ 
/* 1209 */       for (int cat = 0; cat < catCount; cat++) {
/* 1210 */         double angle = getStartAngle() + getDirection().getFactor() * cat * 360.0D / catCount;
/*      */         
/*      */ 
/* 1213 */         Point2D endPoint = getWebPoint(radarArea, angle, 1.0D);
/*      */         
/* 1215 */         Line2D line = new Line2D.Double(centre, endPoint);
/* 1216 */         g2.setPaint(this.axisLinePaint);
/* 1217 */         g2.setStroke(this.axisLineStroke);
/* 1218 */         g2.draw(line);
/* 1219 */         drawLabel(g2, radarArea, 0.0D, cat, angle, 360.0D / catCount);
/*      */       }
/*      */       
/*      */ 
/* 1223 */       for (int series = 0; series < seriesCount; series++) {
/* 1224 */         drawRadarPoly(g2, radarArea, centre, info, series, catCount, headH, headW);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 1229 */       drawNoDataMessage(g2, area);
/*      */     }
/* 1231 */     g2.setClip(savedClip);
/* 1232 */     g2.setComposite(originalComposite);
/* 1233 */     drawOutline(g2, area);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void calculateMaxValue(int seriesCount, int catCount)
/*      */   {
/* 1244 */     double v = 0.0D;
/* 1245 */     Number nV = null;
/*      */     
/* 1247 */     for (int seriesIndex = 0; seriesIndex < seriesCount; seriesIndex++) {
/* 1248 */       for (int catIndex = 0; catIndex < catCount; catIndex++) {
/* 1249 */         nV = getPlotValue(seriesIndex, catIndex);
/* 1250 */         if (nV != null) {
/* 1251 */           v = nV.doubleValue();
/* 1252 */           if (v > this.maxValue) {
/* 1253 */             this.maxValue = v;
/*      */           }
/*      */         }
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
/*      */   protected void drawRadarPoly(Graphics2D g2, Rectangle2D plotArea, Point2D centre, PlotRenderingInfo info, int series, int catCount, double headH, double headW)
/*      */   {
/* 1279 */     Polygon polygon = new Polygon();
/*      */     
/* 1281 */     EntityCollection entities = null;
/* 1282 */     if (info != null) {
/* 1283 */       entities = info.getOwner().getEntityCollection();
/*      */     }
/*      */     
/*      */ 
/* 1287 */     for (int cat = 0; cat < catCount; cat++)
/*      */     {
/* 1289 */       Number dataValue = getPlotValue(series, cat);
/*      */       
/* 1291 */       if (dataValue != null) {
/* 1292 */         double value = dataValue.doubleValue();
/*      */         
/* 1294 */         if (value >= 0.0D)
/*      */         {
/*      */ 
/*      */ 
/* 1298 */           double angle = getStartAngle() + getDirection().getFactor() * cat * 360.0D / catCount;
/*      */           
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1312 */           Point2D point = getWebPoint(plotArea, angle, value / this.maxValue);
/*      */           
/* 1314 */           polygon.addPoint((int)point.getX(), (int)point.getY());
/*      */           
/*      */ 
/*      */ 
/* 1318 */           Paint paint = getSeriesPaint(series);
/* 1319 */           Paint outlinePaint = getSeriesOutlinePaint(series);
/* 1320 */           Stroke outlineStroke = getSeriesOutlineStroke(series);
/*      */           
/* 1322 */           Ellipse2D head = new Ellipse2D.Double(point.getX() - headW / 2.0D, point.getY() - headH / 2.0D, headW, headH);
/*      */           
/*      */ 
/* 1325 */           g2.setPaint(paint);
/* 1326 */           g2.fill(head);
/* 1327 */           g2.setStroke(outlineStroke);
/* 1328 */           g2.setPaint(outlinePaint);
/* 1329 */           g2.draw(head);
/*      */           
/* 1331 */           if (entities != null) {
/* 1332 */             int row = 0;int col = 0;
/* 1333 */             if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 1334 */               row = series;
/* 1335 */               col = cat;
/*      */             }
/*      */             else {
/* 1338 */               row = cat;
/* 1339 */               col = series;
/*      */             }
/* 1341 */             String tip = null;
/* 1342 */             if (this.toolTipGenerator != null) {
/* 1343 */               tip = this.toolTipGenerator.generateToolTip(this.dataset, row, col);
/*      */             }
/*      */             
/*      */ 
/* 1347 */             String url = null;
/* 1348 */             if (this.urlGenerator != null) {
/* 1349 */               url = this.urlGenerator.generateURL(this.dataset, row, col);
/*      */             }
/*      */             
/*      */ 
/* 1353 */             Shape area = new Rectangle((int)(point.getX() - headW), (int)(point.getY() - headH), (int)(headW * 2.0D), (int)(headH * 2.0D));
/*      */             
/*      */ 
/*      */ 
/* 1357 */             CategoryItemEntity entity = new CategoryItemEntity(area, tip, url, this.dataset, this.dataset.getRowKey(row), this.dataset.getColumnKey(col));
/*      */             
/*      */ 
/*      */ 
/* 1361 */             entities.add(entity);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1369 */     Paint paint = getSeriesPaint(series);
/* 1370 */     g2.setPaint(paint);
/* 1371 */     g2.setStroke(getSeriesOutlineStroke(series));
/* 1372 */     g2.draw(polygon);
/*      */     
/*      */ 
/*      */ 
/* 1376 */     if (this.webFilled) {
/* 1377 */       g2.setComposite(AlphaComposite.getInstance(3, 0.1F));
/*      */       
/* 1379 */       g2.fill(polygon);
/* 1380 */       g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
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
/*      */   protected Number getPlotValue(int series, int cat)
/*      */   {
/* 1400 */     Number value = null;
/* 1401 */     if (this.dataExtractOrder == TableOrder.BY_ROW) {
/* 1402 */       value = this.dataset.getValue(series, cat);
/*      */     }
/* 1404 */     else if (this.dataExtractOrder == TableOrder.BY_COLUMN) {
/* 1405 */       value = this.dataset.getValue(cat, series);
/*      */     }
/* 1407 */     return value;
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
/*      */   protected void drawLabel(Graphics2D g2, Rectangle2D plotArea, double value, int cat, double startAngle, double extent)
/*      */   {
/* 1422 */     FontRenderContext frc = g2.getFontRenderContext();
/*      */     
/* 1424 */     String label = null;
/* 1425 */     if (this.dataExtractOrder == TableOrder.BY_ROW)
/*      */     {
/* 1427 */       label = this.labelGenerator.generateColumnLabel(this.dataset, cat);
/*      */     }
/*      */     else
/*      */     {
/* 1431 */       label = this.labelGenerator.generateRowLabel(this.dataset, cat);
/*      */     }
/*      */     
/* 1434 */     Rectangle2D labelBounds = getLabelFont().getStringBounds(label, frc);
/* 1435 */     LineMetrics lm = getLabelFont().getLineMetrics(label, frc);
/* 1436 */     double ascent = lm.getAscent();
/*      */     
/* 1438 */     Point2D labelLocation = calculateLabelLocation(labelBounds, ascent, plotArea, startAngle);
/*      */     
/*      */ 
/* 1441 */     Composite saveComposite = g2.getComposite();
/*      */     
/* 1443 */     g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*      */     
/* 1445 */     g2.setPaint(getLabelPaint());
/* 1446 */     g2.setFont(getLabelFont());
/* 1447 */     g2.drawString(label, (float)labelLocation.getX(), (float)labelLocation.getY());
/*      */     
/* 1449 */     g2.setComposite(saveComposite);
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
/*      */   protected Point2D calculateLabelLocation(Rectangle2D labelBounds, double ascent, Rectangle2D plotArea, double startAngle)
/*      */   {
/* 1467 */     Arc2D arc1 = new Arc2D.Double(plotArea, startAngle, 0.0D, 0);
/* 1468 */     Point2D point1 = arc1.getEndPoint();
/*      */     
/* 1470 */     double deltaX = -(point1.getX() - plotArea.getCenterX()) * this.axisLabelGap;
/*      */     
/* 1472 */     double deltaY = -(point1.getY() - plotArea.getCenterY()) * this.axisLabelGap;
/*      */     
/*      */ 
/* 1475 */     double labelX = point1.getX() - deltaX;
/* 1476 */     double labelY = point1.getY() - deltaY;
/*      */     
/* 1478 */     if (labelX < plotArea.getCenterX()) {
/* 1479 */       labelX -= labelBounds.getWidth();
/*      */     }
/*      */     
/* 1482 */     if (labelX == plotArea.getCenterX()) {
/* 1483 */       labelX -= labelBounds.getWidth() / 2.0D;
/*      */     }
/*      */     
/* 1486 */     if (labelY > plotArea.getCenterY()) {
/* 1487 */       labelY += ascent;
/*      */     }
/*      */     
/* 1490 */     return new Point2D.Double(labelX, labelY);
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
/* 1501 */     if (obj == this) {
/* 1502 */       return true;
/*      */     }
/* 1504 */     if (!(obj instanceof SpiderWebPlot)) {
/* 1505 */       return false;
/*      */     }
/* 1507 */     if (!super.equals(obj)) {
/* 1508 */       return false;
/*      */     }
/* 1510 */     SpiderWebPlot that = (SpiderWebPlot)obj;
/* 1511 */     if (!this.dataExtractOrder.equals(that.dataExtractOrder)) {
/* 1512 */       return false;
/*      */     }
/* 1514 */     if (this.headPercent != that.headPercent) {
/* 1515 */       return false;
/*      */     }
/* 1517 */     if (this.interiorGap != that.interiorGap) {
/* 1518 */       return false;
/*      */     }
/* 1520 */     if (this.startAngle != that.startAngle) {
/* 1521 */       return false;
/*      */     }
/* 1523 */     if (!this.direction.equals(that.direction)) {
/* 1524 */       return false;
/*      */     }
/* 1526 */     if (this.maxValue != that.maxValue) {
/* 1527 */       return false;
/*      */     }
/* 1529 */     if (this.webFilled != that.webFilled) {
/* 1530 */       return false;
/*      */     }
/* 1532 */     if (this.axisLabelGap != that.axisLabelGap) {
/* 1533 */       return false;
/*      */     }
/* 1535 */     if (!PaintUtilities.equal(this.axisLinePaint, that.axisLinePaint)) {
/* 1536 */       return false;
/*      */     }
/* 1538 */     if (!this.axisLineStroke.equals(that.axisLineStroke)) {
/* 1539 */       return false;
/*      */     }
/* 1541 */     if (!ShapeUtilities.equal(this.legendItemShape, that.legendItemShape)) {
/* 1542 */       return false;
/*      */     }
/* 1544 */     if (!PaintUtilities.equal(this.seriesPaint, that.seriesPaint)) {
/* 1545 */       return false;
/*      */     }
/* 1547 */     if (!this.seriesPaintList.equals(that.seriesPaintList)) {
/* 1548 */       return false;
/*      */     }
/* 1550 */     if (!PaintUtilities.equal(this.baseSeriesPaint, that.baseSeriesPaint)) {
/* 1551 */       return false;
/*      */     }
/* 1553 */     if (!PaintUtilities.equal(this.seriesOutlinePaint, that.seriesOutlinePaint))
/*      */     {
/* 1555 */       return false;
/*      */     }
/* 1557 */     if (!this.seriesOutlinePaintList.equals(that.seriesOutlinePaintList)) {
/* 1558 */       return false;
/*      */     }
/* 1560 */     if (!PaintUtilities.equal(this.baseSeriesOutlinePaint, that.baseSeriesOutlinePaint))
/*      */     {
/* 1562 */       return false;
/*      */     }
/* 1564 */     if (!ObjectUtilities.equal(this.seriesOutlineStroke, that.seriesOutlineStroke))
/*      */     {
/* 1566 */       return false;
/*      */     }
/* 1568 */     if (!this.seriesOutlineStrokeList.equals(that.seriesOutlineStrokeList))
/*      */     {
/* 1570 */       return false;
/*      */     }
/* 1572 */     if (!this.baseSeriesOutlineStroke.equals(that.baseSeriesOutlineStroke))
/*      */     {
/* 1574 */       return false;
/*      */     }
/* 1576 */     if (!this.labelFont.equals(that.labelFont)) {
/* 1577 */       return false;
/*      */     }
/* 1579 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint)) {
/* 1580 */       return false;
/*      */     }
/* 1582 */     if (!this.labelGenerator.equals(that.labelGenerator)) {
/* 1583 */       return false;
/*      */     }
/* 1585 */     if (!ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator))
/*      */     {
/* 1587 */       return false;
/*      */     }
/* 1589 */     if (!ObjectUtilities.equal(this.urlGenerator, that.urlGenerator))
/*      */     {
/* 1591 */       return false;
/*      */     }
/* 1593 */     return true;
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
/* 1605 */     SpiderWebPlot clone = (SpiderWebPlot)super.clone();
/* 1606 */     clone.legendItemShape = ShapeUtilities.clone(this.legendItemShape);
/* 1607 */     clone.seriesPaintList = ((PaintList)this.seriesPaintList.clone());
/* 1608 */     clone.seriesOutlinePaintList = ((PaintList)this.seriesOutlinePaintList.clone());
/*      */     
/* 1610 */     clone.seriesOutlineStrokeList = ((StrokeList)this.seriesOutlineStrokeList.clone());
/*      */     
/* 1612 */     return clone;
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
/* 1623 */     stream.defaultWriteObject();
/*      */     
/* 1625 */     SerialUtilities.writeShape(this.legendItemShape, stream);
/* 1626 */     SerialUtilities.writePaint(this.seriesPaint, stream);
/* 1627 */     SerialUtilities.writePaint(this.baseSeriesPaint, stream);
/* 1628 */     SerialUtilities.writePaint(this.seriesOutlinePaint, stream);
/* 1629 */     SerialUtilities.writePaint(this.baseSeriesOutlinePaint, stream);
/* 1630 */     SerialUtilities.writeStroke(this.seriesOutlineStroke, stream);
/* 1631 */     SerialUtilities.writeStroke(this.baseSeriesOutlineStroke, stream);
/* 1632 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 1633 */     SerialUtilities.writePaint(this.axisLinePaint, stream);
/* 1634 */     SerialUtilities.writeStroke(this.axisLineStroke, stream);
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
/* 1647 */     stream.defaultReadObject();
/*      */     
/* 1649 */     this.legendItemShape = SerialUtilities.readShape(stream);
/* 1650 */     this.seriesPaint = SerialUtilities.readPaint(stream);
/* 1651 */     this.baseSeriesPaint = SerialUtilities.readPaint(stream);
/* 1652 */     this.seriesOutlinePaint = SerialUtilities.readPaint(stream);
/* 1653 */     this.baseSeriesOutlinePaint = SerialUtilities.readPaint(stream);
/* 1654 */     this.seriesOutlineStroke = SerialUtilities.readStroke(stream);
/* 1655 */     this.baseSeriesOutlineStroke = SerialUtilities.readStroke(stream);
/* 1656 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 1657 */     this.axisLinePaint = SerialUtilities.readPaint(stream);
/* 1658 */     this.axisLineStroke = SerialUtilities.readStroke(stream);
/* 1659 */     if (this.dataset != null) {
/* 1660 */       this.dataset.addChangeListener(this);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/SpiderWebPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */