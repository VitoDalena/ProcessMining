/*      */ package org.jfree.chart.renderer;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Point2D.Double;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Arrays;
/*      */ import java.util.EventListener;
/*      */ import java.util.List;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.HashUtilities;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.labels.ItemLabelAnchor;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.BooleanList;
/*      */ import org.jfree.util.BooleanUtilities;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintList;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.ShapeList;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.StrokeList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class AbstractRenderer
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -828267569428206075L;
/*  144 */   public static final Double ZERO = new Double(0.0D);
/*      */   
/*      */ 
/*  147 */   public static final Paint DEFAULT_PAINT = Color.blue;
/*      */   
/*      */ 
/*  150 */   public static final Paint DEFAULT_OUTLINE_PAINT = Color.gray;
/*      */   
/*      */ 
/*  153 */   public static final Stroke DEFAULT_STROKE = new BasicStroke(1.0F);
/*      */   
/*      */ 
/*  156 */   public static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(1.0F);
/*      */   
/*      */ 
/*  159 */   public static final Shape DEFAULT_SHAPE = new Rectangle2D.Double(-3.0D, -3.0D, 6.0D, 6.0D);
/*      */   
/*      */ 
/*      */ 
/*  163 */   public static final Font DEFAULT_VALUE_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*      */ 
/*      */ 
/*  167 */   public static final Paint DEFAULT_VALUE_LABEL_PAINT = Color.black;
/*      */   
/*      */ 
/*      */ 
/*      */   private BooleanList seriesVisibleList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean baseSeriesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private BooleanList seriesVisibleInLegendList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean baseSeriesVisibleInLegend;
/*      */   
/*      */ 
/*      */ 
/*      */   private PaintList paintList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSeriesPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint basePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private PaintList fillPaintList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSeriesFillPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint baseFillPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private PaintList outlinePaintList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSeriesOutlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint baseOutlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private StrokeList strokeList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSeriesStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke baseStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private StrokeList outlineStrokeList;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke baseOutlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSeriesOutlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private ShapeList shapeList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSeriesShape;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Shape baseShape;
/*      */   
/*      */ 
/*      */ 
/*      */   private BooleanList itemLabelsVisibleList;
/*      */   
/*      */ 
/*      */ 
/*      */   private Boolean baseItemLabelsVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList itemLabelFontList;
/*      */   
/*      */ 
/*      */ 
/*      */   private Font baseItemLabelFont;
/*      */   
/*      */ 
/*      */ 
/*      */   private PaintList itemLabelPaintList;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint baseItemLabelPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList positiveItemLabelPositionList;
/*      */   
/*      */ 
/*      */ 
/*      */   private ItemLabelPosition basePositiveItemLabelPosition;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList negativeItemLabelPositionList;
/*      */   
/*      */ 
/*      */ 
/*      */   private ItemLabelPosition baseNegativeItemLabelPosition;
/*      */   
/*      */ 
/*      */ 
/*  299 */   private double itemLabelAnchorOffset = 2.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private BooleanList createEntitiesList;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean baseCreateEntities;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private ShapeList legendShape;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Shape baseLegendShape;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private ObjectList legendTextFont;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Font baseLegendTextFont;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private PaintList legendTextPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint baseLegendTextPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  364 */   private boolean dataBoundsIncludesVisibleSeriesOnly = true;
/*      */   
/*      */ 
/*      */ 
/*      */   private int defaultEntityRadius;
/*      */   
/*      */ 
/*      */   private transient EventListenerList listenerList;
/*      */   
/*      */ 
/*      */   private transient RendererChangeEvent event;
/*      */   
/*      */ 
/*      */ 
/*      */   public AbstractRenderer()
/*      */   {
/*  380 */     this.seriesVisible = null;
/*  381 */     this.seriesVisibleList = new BooleanList();
/*  382 */     this.baseSeriesVisible = true;
/*      */     
/*  384 */     this.seriesVisibleInLegend = null;
/*  385 */     this.seriesVisibleInLegendList = new BooleanList();
/*  386 */     this.baseSeriesVisibleInLegend = true;
/*      */     
/*  388 */     this.paint = null;
/*  389 */     this.paintList = new PaintList();
/*  390 */     this.basePaint = DEFAULT_PAINT;
/*  391 */     this.autoPopulateSeriesPaint = true;
/*      */     
/*  393 */     this.fillPaint = null;
/*  394 */     this.fillPaintList = new PaintList();
/*  395 */     this.baseFillPaint = Color.white;
/*  396 */     this.autoPopulateSeriesFillPaint = false;
/*      */     
/*  398 */     this.outlinePaint = null;
/*  399 */     this.outlinePaintList = new PaintList();
/*  400 */     this.baseOutlinePaint = DEFAULT_OUTLINE_PAINT;
/*  401 */     this.autoPopulateSeriesOutlinePaint = false;
/*      */     
/*  403 */     this.stroke = null;
/*  404 */     this.strokeList = new StrokeList();
/*  405 */     this.baseStroke = DEFAULT_STROKE;
/*  406 */     this.autoPopulateSeriesStroke = true;
/*      */     
/*  408 */     this.outlineStroke = null;
/*  409 */     this.outlineStrokeList = new StrokeList();
/*  410 */     this.baseOutlineStroke = DEFAULT_OUTLINE_STROKE;
/*  411 */     this.autoPopulateSeriesOutlineStroke = false;
/*      */     
/*  413 */     this.shape = null;
/*  414 */     this.shapeList = new ShapeList();
/*  415 */     this.baseShape = DEFAULT_SHAPE;
/*  416 */     this.autoPopulateSeriesShape = true;
/*      */     
/*  418 */     this.itemLabelsVisible = null;
/*  419 */     this.itemLabelsVisibleList = new BooleanList();
/*  420 */     this.baseItemLabelsVisible = Boolean.FALSE;
/*      */     
/*  422 */     this.itemLabelFont = null;
/*  423 */     this.itemLabelFontList = new ObjectList();
/*  424 */     this.baseItemLabelFont = new Font("SansSerif", 0, 10);
/*      */     
/*  426 */     this.itemLabelPaint = null;
/*  427 */     this.itemLabelPaintList = new PaintList();
/*  428 */     this.baseItemLabelPaint = Color.black;
/*      */     
/*  430 */     this.positiveItemLabelPosition = null;
/*  431 */     this.positiveItemLabelPositionList = new ObjectList();
/*  432 */     this.basePositiveItemLabelPosition = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
/*      */     
/*      */ 
/*  435 */     this.negativeItemLabelPosition = null;
/*  436 */     this.negativeItemLabelPositionList = new ObjectList();
/*  437 */     this.baseNegativeItemLabelPosition = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
/*      */     
/*      */ 
/*  440 */     this.createEntities = null;
/*  441 */     this.createEntitiesList = new BooleanList();
/*  442 */     this.baseCreateEntities = true;
/*      */     
/*  444 */     this.defaultEntityRadius = 3;
/*      */     
/*  446 */     this.legendShape = new ShapeList();
/*  447 */     this.baseLegendShape = null;
/*      */     
/*  449 */     this.legendTextFont = new ObjectList();
/*  450 */     this.baseLegendTextFont = null;
/*      */     
/*  452 */     this.legendTextPaint = new PaintList();
/*  453 */     this.baseLegendTextPaint = null;
/*      */     
/*  455 */     this.listenerList = new EventListenerList();
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
/*      */   public abstract DrawingSupplier getDrawingSupplier();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getItemVisible(int series, int item)
/*      */   {
/*  478 */     return isSeriesVisible(series);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSeriesVisible(int series)
/*      */   {
/*  490 */     boolean result = this.baseSeriesVisible;
/*  491 */     if (this.seriesVisible != null) {
/*  492 */       result = this.seriesVisible.booleanValue();
/*      */     }
/*      */     else {
/*  495 */       Boolean b = this.seriesVisibleList.getBoolean(series);
/*  496 */       if (b != null) {
/*  497 */         result = b.booleanValue();
/*      */       }
/*      */     }
/*  500 */     return result;
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
/*      */   public Boolean getSeriesVisible(int series)
/*      */   {
/*  513 */     return this.seriesVisibleList.getBoolean(series);
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
/*      */   public void setSeriesVisible(int series, Boolean visible)
/*      */   {
/*  526 */     setSeriesVisible(series, visible, true);
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
/*      */   public void setSeriesVisible(int series, Boolean visible, boolean notify)
/*      */   {
/*  541 */     this.seriesVisibleList.setBoolean(series, visible);
/*  542 */     if (notify)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  547 */       RendererChangeEvent e = new RendererChangeEvent(this, true);
/*  548 */       notifyListeners(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBaseSeriesVisible()
/*      */   {
/*  560 */     return this.baseSeriesVisible;
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
/*      */   public void setBaseSeriesVisible(boolean visible)
/*      */   {
/*  573 */     setBaseSeriesVisible(visible, true);
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
/*      */   public void setBaseSeriesVisible(boolean visible, boolean notify)
/*      */   {
/*  586 */     this.baseSeriesVisible = visible;
/*  587 */     if (notify)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*  592 */       RendererChangeEvent e = new RendererChangeEvent(this, true);
/*  593 */       notifyListeners(e);
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
/*      */   public boolean isSeriesVisibleInLegend(int series)
/*      */   {
/*  608 */     boolean result = this.baseSeriesVisibleInLegend;
/*  609 */     if (this.seriesVisibleInLegend != null) {
/*  610 */       result = this.seriesVisibleInLegend.booleanValue();
/*      */     }
/*      */     else {
/*  613 */       Boolean b = this.seriesVisibleInLegendList.getBoolean(series);
/*  614 */       if (b != null) {
/*  615 */         result = b.booleanValue();
/*      */       }
/*      */     }
/*  618 */     return result;
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
/*      */   public Boolean getSeriesVisibleInLegend(int series)
/*      */   {
/*  634 */     return this.seriesVisibleInLegendList.getBoolean(series);
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
/*      */   public void setSeriesVisibleInLegend(int series, Boolean visible)
/*      */   {
/*  647 */     setSeriesVisibleInLegend(series, visible, true);
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
/*      */   public void setSeriesVisibleInLegend(int series, Boolean visible, boolean notify)
/*      */   {
/*  663 */     this.seriesVisibleInLegendList.setBoolean(series, visible);
/*  664 */     if (notify) {
/*  665 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBaseSeriesVisibleInLegend()
/*      */   {
/*  677 */     return this.baseSeriesVisibleInLegend;
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
/*      */   public void setBaseSeriesVisibleInLegend(boolean visible)
/*      */   {
/*  690 */     setBaseSeriesVisibleInLegend(visible, true);
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
/*      */   public void setBaseSeriesVisibleInLegend(boolean visible, boolean notify)
/*      */   {
/*  703 */     this.baseSeriesVisibleInLegend = visible;
/*  704 */     if (notify) {
/*  705 */       fireChangeEvent();
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
/*      */   public Paint getItemPaint(int row, int column)
/*      */   {
/*  724 */     return lookupSeriesPaint(row);
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
/*      */   public Paint lookupSeriesPaint(int series)
/*      */   {
/*  739 */     if (this.paint != null) {
/*  740 */       return this.paint;
/*      */     }
/*      */     
/*      */ 
/*  744 */     Paint seriesPaint = getSeriesPaint(series);
/*  745 */     if ((seriesPaint == null) && (this.autoPopulateSeriesPaint)) {
/*  746 */       DrawingSupplier supplier = getDrawingSupplier();
/*  747 */       if (supplier != null) {
/*  748 */         seriesPaint = supplier.getNextPaint();
/*  749 */         setSeriesPaint(series, seriesPaint, false);
/*      */       }
/*      */     }
/*  752 */     if (seriesPaint == null) {
/*  753 */       seriesPaint = this.basePaint;
/*      */     }
/*  755 */     return seriesPaint;
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
/*      */   public Paint getSeriesPaint(int series)
/*      */   {
/*  769 */     return this.paintList.getPaint(series);
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
/*      */   public void setSeriesPaint(int series, Paint paint)
/*      */   {
/*  782 */     setSeriesPaint(series, paint, true);
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
/*      */   public void setSeriesPaint(int series, Paint paint, boolean notify)
/*      */   {
/*  796 */     this.paintList.setPaint(series, paint);
/*  797 */     if (notify) {
/*  798 */       fireChangeEvent();
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
/*      */   public void clearSeriesPaints(boolean notify)
/*      */   {
/*  811 */     this.paintList.clear();
/*  812 */     if (notify) {
/*  813 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBasePaint()
/*      */   {
/*  825 */     return this.basePaint;
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
/*      */   public void setBasePaint(Paint paint)
/*      */   {
/*  838 */     setBasePaint(paint, true);
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
/*      */   public void setBasePaint(Paint paint, boolean notify)
/*      */   {
/*  851 */     this.basePaint = paint;
/*  852 */     if (notify) {
/*  853 */       fireChangeEvent();
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
/*      */   public boolean getAutoPopulateSeriesPaint()
/*      */   {
/*  868 */     return this.autoPopulateSeriesPaint;
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
/*      */   public void setAutoPopulateSeriesPaint(boolean auto)
/*      */   {
/*  882 */     this.autoPopulateSeriesPaint = auto;
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
/*      */   public Paint getItemFillPaint(int row, int column)
/*      */   {
/*  899 */     return lookupSeriesFillPaint(row);
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
/*      */   public Paint lookupSeriesFillPaint(int series)
/*      */   {
/*  914 */     if (this.fillPaint != null) {
/*  915 */       return this.fillPaint;
/*      */     }
/*      */     
/*      */ 
/*  919 */     Paint seriesFillPaint = getSeriesFillPaint(series);
/*  920 */     if ((seriesFillPaint == null) && (this.autoPopulateSeriesFillPaint)) {
/*  921 */       DrawingSupplier supplier = getDrawingSupplier();
/*  922 */       if (supplier != null) {
/*  923 */         seriesFillPaint = supplier.getNextFillPaint();
/*  924 */         setSeriesFillPaint(series, seriesFillPaint, false);
/*      */       }
/*      */     }
/*  927 */     if (seriesFillPaint == null) {
/*  928 */       seriesFillPaint = this.baseFillPaint;
/*      */     }
/*  930 */     return seriesFillPaint;
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
/*      */   public Paint getSeriesFillPaint(int series)
/*      */   {
/*  944 */     return this.fillPaintList.getPaint(series);
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
/*      */   public void setSeriesFillPaint(int series, Paint paint)
/*      */   {
/*  957 */     setSeriesFillPaint(series, paint, true);
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
/*      */   public void setSeriesFillPaint(int series, Paint paint, boolean notify)
/*      */   {
/*  971 */     this.fillPaintList.setPaint(series, paint);
/*  972 */     if (notify) {
/*  973 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaseFillPaint()
/*      */   {
/*  985 */     return this.baseFillPaint;
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
/*      */   public void setBaseFillPaint(Paint paint)
/*      */   {
/*  998 */     setBaseFillPaint(paint, true);
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
/*      */   public void setBaseFillPaint(Paint paint, boolean notify)
/*      */   {
/* 1011 */     if (paint == null) {
/* 1012 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1014 */     this.baseFillPaint = paint;
/* 1015 */     if (notify) {
/* 1016 */       fireChangeEvent();
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
/*      */   public boolean getAutoPopulateSeriesFillPaint()
/*      */   {
/* 1032 */     return this.autoPopulateSeriesFillPaint;
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
/*      */   public void setAutoPopulateSeriesFillPaint(boolean auto)
/*      */   {
/* 1047 */     this.autoPopulateSeriesFillPaint = auto;
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
/*      */   public Paint getItemOutlinePaint(int row, int column)
/*      */   {
/* 1065 */     return lookupSeriesOutlinePaint(row);
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
/*      */   public Paint lookupSeriesOutlinePaint(int series)
/*      */   {
/* 1080 */     if (this.outlinePaint != null) {
/* 1081 */       return this.outlinePaint;
/*      */     }
/*      */     
/*      */ 
/* 1085 */     Paint seriesOutlinePaint = getSeriesOutlinePaint(series);
/* 1086 */     if ((seriesOutlinePaint == null) && (this.autoPopulateSeriesOutlinePaint)) {
/* 1087 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1088 */       if (supplier != null) {
/* 1089 */         seriesOutlinePaint = supplier.getNextOutlinePaint();
/* 1090 */         setSeriesOutlinePaint(series, seriesOutlinePaint, false);
/*      */       }
/*      */     }
/* 1093 */     if (seriesOutlinePaint == null) {
/* 1094 */       seriesOutlinePaint = this.baseOutlinePaint;
/*      */     }
/* 1096 */     return seriesOutlinePaint;
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
/*      */   public Paint getSeriesOutlinePaint(int series)
/*      */   {
/* 1110 */     return this.outlinePaintList.getPaint(series);
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
/*      */   public void setSeriesOutlinePaint(int series, Paint paint)
/*      */   {
/* 1123 */     setSeriesOutlinePaint(series, paint, true);
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
/*      */   public void setSeriesOutlinePaint(int series, Paint paint, boolean notify)
/*      */   {
/* 1137 */     this.outlinePaintList.setPaint(series, paint);
/* 1138 */     if (notify) {
/* 1139 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaseOutlinePaint()
/*      */   {
/* 1151 */     return this.baseOutlinePaint;
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
/*      */   public void setBaseOutlinePaint(Paint paint)
/*      */   {
/* 1164 */     setBaseOutlinePaint(paint, true);
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
/*      */   public void setBaseOutlinePaint(Paint paint, boolean notify)
/*      */   {
/* 1177 */     if (paint == null) {
/* 1178 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1180 */     this.baseOutlinePaint = paint;
/* 1181 */     if (notify) {
/* 1182 */       fireChangeEvent();
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
/*      */   public boolean getAutoPopulateSeriesOutlinePaint()
/*      */   {
/* 1198 */     return this.autoPopulateSeriesOutlinePaint;
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
/*      */   public void setAutoPopulateSeriesOutlinePaint(boolean auto)
/*      */   {
/* 1213 */     this.autoPopulateSeriesOutlinePaint = auto;
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
/*      */   public Stroke getItemStroke(int row, int column)
/*      */   {
/* 1230 */     return lookupSeriesStroke(row);
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
/*      */   public Stroke lookupSeriesStroke(int series)
/*      */   {
/* 1245 */     if (this.stroke != null) {
/* 1246 */       return this.stroke;
/*      */     }
/*      */     
/*      */ 
/* 1250 */     Stroke result = getSeriesStroke(series);
/* 1251 */     if ((result == null) && (this.autoPopulateSeriesStroke)) {
/* 1252 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1253 */       if (supplier != null) {
/* 1254 */         result = supplier.getNextStroke();
/* 1255 */         setSeriesStroke(series, result, false);
/*      */       }
/*      */     }
/* 1258 */     if (result == null) {
/* 1259 */       result = this.baseStroke;
/*      */     }
/* 1261 */     return result;
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
/*      */   public Stroke getSeriesStroke(int series)
/*      */   {
/* 1275 */     return this.strokeList.getStroke(series);
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
/*      */   public void setSeriesStroke(int series, Stroke stroke)
/*      */   {
/* 1288 */     setSeriesStroke(series, stroke, true);
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
/*      */   public void setSeriesStroke(int series, Stroke stroke, boolean notify)
/*      */   {
/* 1302 */     this.strokeList.setStroke(series, stroke);
/* 1303 */     if (notify) {
/* 1304 */       fireChangeEvent();
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
/*      */   public void clearSeriesStrokes(boolean notify)
/*      */   {
/* 1317 */     this.strokeList.clear();
/* 1318 */     if (notify) {
/* 1319 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getBaseStroke()
/*      */   {
/* 1331 */     return this.baseStroke;
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
/*      */   public void setBaseStroke(Stroke stroke)
/*      */   {
/* 1344 */     setBaseStroke(stroke, true);
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
/*      */   public void setBaseStroke(Stroke stroke, boolean notify)
/*      */   {
/* 1357 */     if (stroke == null) {
/* 1358 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1360 */     this.baseStroke = stroke;
/* 1361 */     if (notify) {
/* 1362 */       fireChangeEvent();
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
/*      */   public boolean getAutoPopulateSeriesStroke()
/*      */   {
/* 1377 */     return this.autoPopulateSeriesStroke;
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
/*      */   public void setAutoPopulateSeriesStroke(boolean auto)
/*      */   {
/* 1391 */     this.autoPopulateSeriesStroke = auto;
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
/*      */   public Stroke getItemOutlineStroke(int row, int column)
/*      */   {
/* 1408 */     return lookupSeriesOutlineStroke(row);
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
/*      */   public Stroke lookupSeriesOutlineStroke(int series)
/*      */   {
/* 1423 */     if (this.outlineStroke != null) {
/* 1424 */       return this.outlineStroke;
/*      */     }
/*      */     
/*      */ 
/* 1428 */     Stroke result = getSeriesOutlineStroke(series);
/* 1429 */     if ((result == null) && (this.autoPopulateSeriesOutlineStroke)) {
/* 1430 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1431 */       if (supplier != null) {
/* 1432 */         result = supplier.getNextOutlineStroke();
/* 1433 */         setSeriesOutlineStroke(series, result, false);
/*      */       }
/*      */     }
/* 1436 */     if (result == null) {
/* 1437 */       result = this.baseOutlineStroke;
/*      */     }
/* 1439 */     return result;
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
/*      */   public Stroke getSeriesOutlineStroke(int series)
/*      */   {
/* 1453 */     return this.outlineStrokeList.getStroke(series);
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
/*      */   public void setSeriesOutlineStroke(int series, Stroke stroke)
/*      */   {
/* 1466 */     setSeriesOutlineStroke(series, stroke, true);
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
/*      */   public void setSeriesOutlineStroke(int series, Stroke stroke, boolean notify)
/*      */   {
/* 1481 */     this.outlineStrokeList.setStroke(series, stroke);
/* 1482 */     if (notify) {
/* 1483 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getBaseOutlineStroke()
/*      */   {
/* 1495 */     return this.baseOutlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseOutlineStroke(Stroke stroke)
/*      */   {
/* 1507 */     setBaseOutlineStroke(stroke, true);
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
/*      */   public void setBaseOutlineStroke(Stroke stroke, boolean notify)
/*      */   {
/* 1521 */     if (stroke == null) {
/* 1522 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1524 */     this.baseOutlineStroke = stroke;
/* 1525 */     if (notify) {
/* 1526 */       fireChangeEvent();
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
/*      */   public boolean getAutoPopulateSeriesOutlineStroke()
/*      */   {
/* 1542 */     return this.autoPopulateSeriesOutlineStroke;
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
/*      */   public void setAutoPopulateSeriesOutlineStroke(boolean auto)
/*      */   {
/* 1557 */     this.autoPopulateSeriesOutlineStroke = auto;
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
/*      */   public Shape getItemShape(int row, int column)
/*      */   {
/* 1574 */     return lookupSeriesShape(row);
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
/*      */   public Shape lookupSeriesShape(int series)
/*      */   {
/* 1589 */     if (this.shape != null) {
/* 1590 */       return this.shape;
/*      */     }
/*      */     
/*      */ 
/* 1594 */     Shape result = getSeriesShape(series);
/* 1595 */     if ((result == null) && (this.autoPopulateSeriesShape)) {
/* 1596 */       DrawingSupplier supplier = getDrawingSupplier();
/* 1597 */       if (supplier != null) {
/* 1598 */         result = supplier.getNextShape();
/* 1599 */         setSeriesShape(series, result, false);
/*      */       }
/*      */     }
/* 1602 */     if (result == null) {
/* 1603 */       result = this.baseShape;
/*      */     }
/* 1605 */     return result;
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
/*      */   public Shape getSeriesShape(int series)
/*      */   {
/* 1619 */     return this.shapeList.getShape(series);
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
/*      */   public void setSeriesShape(int series, Shape shape)
/*      */   {
/* 1632 */     setSeriesShape(series, shape, true);
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
/*      */   public void setSeriesShape(int series, Shape shape, boolean notify)
/*      */   {
/* 1646 */     this.shapeList.setShape(series, shape);
/* 1647 */     if (notify) {
/* 1648 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getBaseShape()
/*      */   {
/* 1660 */     return this.baseShape;
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
/*      */   public void setBaseShape(Shape shape)
/*      */   {
/* 1673 */     setBaseShape(shape, true);
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
/*      */   public void setBaseShape(Shape shape, boolean notify)
/*      */   {
/* 1686 */     if (shape == null) {
/* 1687 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*      */     }
/* 1689 */     this.baseShape = shape;
/* 1690 */     if (notify) {
/* 1691 */       fireChangeEvent();
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
/*      */   public boolean getAutoPopulateSeriesShape()
/*      */   {
/* 1706 */     return this.autoPopulateSeriesShape;
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
/*      */   public void setAutoPopulateSeriesShape(boolean auto)
/*      */   {
/* 1720 */     this.autoPopulateSeriesShape = auto;
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
/*      */   public boolean isItemLabelVisible(int row, int column)
/*      */   {
/* 1735 */     return isSeriesItemLabelsVisible(row);
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
/*      */   public boolean isSeriesItemLabelsVisible(int series)
/*      */   {
/* 1749 */     if (this.itemLabelsVisible != null) {
/* 1750 */       return this.itemLabelsVisible.booleanValue();
/*      */     }
/*      */     
/*      */ 
/* 1754 */     Boolean b = this.itemLabelsVisibleList.getBoolean(series);
/* 1755 */     if (b == null) {
/* 1756 */       b = this.baseItemLabelsVisible;
/*      */     }
/* 1758 */     if (b == null) {
/* 1759 */       b = Boolean.FALSE;
/*      */     }
/* 1761 */     return b.booleanValue();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesItemLabelsVisible(int series, boolean visible)
/*      */   {
/* 1773 */     setSeriesItemLabelsVisible(series, BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesItemLabelsVisible(int series, Boolean visible)
/*      */   {
/* 1784 */     setSeriesItemLabelsVisible(series, visible, true);
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
/*      */   public void setSeriesItemLabelsVisible(int series, Boolean visible, boolean notify)
/*      */   {
/* 1798 */     this.itemLabelsVisibleList.setBoolean(series, visible);
/* 1799 */     if (notify) {
/* 1800 */       fireChangeEvent();
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
/*      */   public Boolean getBaseItemLabelsVisible()
/*      */   {
/* 1816 */     return this.baseItemLabelsVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseItemLabelsVisible(boolean visible)
/*      */   {
/* 1828 */     setBaseItemLabelsVisible(BooleanUtilities.valueOf(visible));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseItemLabelsVisible(Boolean visible)
/*      */   {
/* 1839 */     setBaseItemLabelsVisible(visible, true);
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
/*      */   public void setBaseItemLabelsVisible(Boolean visible, boolean notify)
/*      */   {
/* 1854 */     this.baseItemLabelsVisible = visible;
/* 1855 */     if (notify) {
/* 1856 */       fireChangeEvent();
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
/*      */   public Font getItemLabelFont(int row, int column)
/*      */   {
/* 1871 */     Font result = this.itemLabelFont;
/* 1872 */     if (result == null) {
/* 1873 */       result = getSeriesItemLabelFont(row);
/* 1874 */       if (result == null) {
/* 1875 */         result = this.baseItemLabelFont;
/*      */       }
/*      */     }
/* 1878 */     return result;
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
/*      */   public Font getSeriesItemLabelFont(int series)
/*      */   {
/* 1891 */     return (Font)this.itemLabelFontList.get(series);
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
/*      */   public void setSeriesItemLabelFont(int series, Font font)
/*      */   {
/* 1904 */     setSeriesItemLabelFont(series, font, true);
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
/*      */   public void setSeriesItemLabelFont(int series, Font font, boolean notify)
/*      */   {
/* 1919 */     this.itemLabelFontList.set(series, font);
/* 1920 */     if (notify) {
/* 1921 */       fireChangeEvent();
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
/*      */   public Font getBaseItemLabelFont()
/*      */   {
/* 1934 */     return this.baseItemLabelFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseItemLabelFont(Font font)
/*      */   {
/* 1946 */     if (font == null) {
/* 1947 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/* 1949 */     setBaseItemLabelFont(font, true);
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
/*      */   public void setBaseItemLabelFont(Font font, boolean notify)
/*      */   {
/* 1963 */     this.baseItemLabelFont = font;
/* 1964 */     if (notify) {
/* 1965 */       fireChangeEvent();
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
/*      */   public Paint getItemLabelPaint(int row, int column)
/*      */   {
/* 1980 */     Paint result = this.itemLabelPaint;
/* 1981 */     if (result == null) {
/* 1982 */       result = getSeriesItemLabelPaint(row);
/* 1983 */       if (result == null) {
/* 1984 */         result = this.baseItemLabelPaint;
/*      */       }
/*      */     }
/* 1987 */     return result;
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
/*      */   public Paint getSeriesItemLabelPaint(int series)
/*      */   {
/* 2000 */     return this.itemLabelPaintList.getPaint(series);
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
/*      */   public void setSeriesItemLabelPaint(int series, Paint paint)
/*      */   {
/* 2013 */     setSeriesItemLabelPaint(series, paint, true);
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
/*      */   public void setSeriesItemLabelPaint(int series, Paint paint, boolean notify)
/*      */   {
/* 2029 */     this.itemLabelPaintList.setPaint(series, paint);
/* 2030 */     if (notify) {
/* 2031 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaseItemLabelPaint()
/*      */   {
/* 2043 */     return this.baseItemLabelPaint;
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
/*      */   public void setBaseItemLabelPaint(Paint paint)
/*      */   {
/* 2056 */     setBaseItemLabelPaint(paint, true);
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
/*      */   public void setBaseItemLabelPaint(Paint paint, boolean notify)
/*      */   {
/* 2070 */     if (paint == null) {
/* 2071 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2073 */     this.baseItemLabelPaint = paint;
/* 2074 */     if (notify) {
/* 2075 */       fireChangeEvent();
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
/*      */   public ItemLabelPosition getPositiveItemLabelPosition(int row, int column)
/*      */   {
/* 2092 */     return getSeriesPositiveItemLabelPosition(row);
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
/*      */   public ItemLabelPosition getSeriesPositiveItemLabelPosition(int series)
/*      */   {
/* 2107 */     if (this.positiveItemLabelPosition != null) {
/* 2108 */       return this.positiveItemLabelPosition;
/*      */     }
/*      */     
/*      */ 
/* 2112 */     ItemLabelPosition position = (ItemLabelPosition)this.positiveItemLabelPositionList.get(series);
/*      */     
/* 2114 */     if (position == null) {
/* 2115 */       position = this.basePositiveItemLabelPosition;
/*      */     }
/* 2117 */     return position;
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
/*      */   public void setSeriesPositiveItemLabelPosition(int series, ItemLabelPosition position)
/*      */   {
/* 2132 */     setSeriesPositiveItemLabelPosition(series, position, true);
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
/*      */   public void setSeriesPositiveItemLabelPosition(int series, ItemLabelPosition position, boolean notify)
/*      */   {
/* 2149 */     this.positiveItemLabelPositionList.set(series, position);
/* 2150 */     if (notify) {
/* 2151 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ItemLabelPosition getBasePositiveItemLabelPosition()
/*      */   {
/* 2163 */     return this.basePositiveItemLabelPosition;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBasePositiveItemLabelPosition(ItemLabelPosition position)
/*      */   {
/* 2175 */     setBasePositiveItemLabelPosition(position, true);
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
/*      */   public void setBasePositiveItemLabelPosition(ItemLabelPosition position, boolean notify)
/*      */   {
/* 2189 */     if (position == null) {
/* 2190 */       throw new IllegalArgumentException("Null 'position' argument.");
/*      */     }
/* 2192 */     this.basePositiveItemLabelPosition = position;
/* 2193 */     if (notify) {
/* 2194 */       fireChangeEvent();
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
/*      */   public ItemLabelPosition getNegativeItemLabelPosition(int row, int column)
/*      */   {
/* 2213 */     return getSeriesNegativeItemLabelPosition(row);
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
/*      */   public ItemLabelPosition getSeriesNegativeItemLabelPosition(int series)
/*      */   {
/* 2228 */     if (this.negativeItemLabelPosition != null) {
/* 2229 */       return this.negativeItemLabelPosition;
/*      */     }
/*      */     
/*      */ 
/* 2233 */     ItemLabelPosition position = (ItemLabelPosition)this.negativeItemLabelPositionList.get(series);
/*      */     
/* 2235 */     if (position == null) {
/* 2236 */       position = this.baseNegativeItemLabelPosition;
/*      */     }
/* 2238 */     return position;
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
/*      */   public void setSeriesNegativeItemLabelPosition(int series, ItemLabelPosition position)
/*      */   {
/* 2253 */     setSeriesNegativeItemLabelPosition(series, position, true);
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
/*      */   public void setSeriesNegativeItemLabelPosition(int series, ItemLabelPosition position, boolean notify)
/*      */   {
/* 2270 */     this.negativeItemLabelPositionList.set(series, position);
/* 2271 */     if (notify) {
/* 2272 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ItemLabelPosition getBaseNegativeItemLabelPosition()
/*      */   {
/* 2284 */     return this.baseNegativeItemLabelPosition;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseNegativeItemLabelPosition(ItemLabelPosition position)
/*      */   {
/* 2296 */     setBaseNegativeItemLabelPosition(position, true);
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
/*      */   public void setBaseNegativeItemLabelPosition(ItemLabelPosition position, boolean notify)
/*      */   {
/* 2310 */     if (position == null) {
/* 2311 */       throw new IllegalArgumentException("Null 'position' argument.");
/*      */     }
/* 2313 */     this.baseNegativeItemLabelPosition = position;
/* 2314 */     if (notify) {
/* 2315 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getItemLabelAnchorOffset()
/*      */   {
/* 2327 */     return this.itemLabelAnchorOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setItemLabelAnchorOffset(double offset)
/*      */   {
/* 2338 */     this.itemLabelAnchorOffset = offset;
/* 2339 */     fireChangeEvent();
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
/*      */   public boolean getItemCreateEntity(int series, int item)
/*      */   {
/* 2352 */     if (this.createEntities != null) {
/* 2353 */       return this.createEntities.booleanValue();
/*      */     }
/*      */     
/* 2356 */     Boolean b = getSeriesCreateEntities(series);
/* 2357 */     if (b != null) {
/* 2358 */       return b.booleanValue();
/*      */     }
/*      */     
/* 2361 */     return this.baseCreateEntities;
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
/*      */   public Boolean getSeriesCreateEntities(int series)
/*      */   {
/* 2377 */     return this.createEntitiesList.getBoolean(series);
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
/*      */   public void setSeriesCreateEntities(int series, Boolean create)
/*      */   {
/* 2390 */     setSeriesCreateEntities(series, create, true);
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
/*      */   public void setSeriesCreateEntities(int series, Boolean create, boolean notify)
/*      */   {
/* 2406 */     this.createEntitiesList.setBoolean(series, create);
/* 2407 */     if (notify) {
/* 2408 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getBaseCreateEntities()
/*      */   {
/* 2420 */     return this.baseCreateEntities;
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
/*      */   public void setBaseCreateEntities(boolean create)
/*      */   {
/* 2434 */     setBaseCreateEntities(create, true);
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
/*      */   public void setBaseCreateEntities(boolean create, boolean notify)
/*      */   {
/* 2448 */     this.baseCreateEntities = create;
/* 2449 */     if (notify) {
/* 2450 */       fireChangeEvent();
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
/*      */   public int getDefaultEntityRadius()
/*      */   {
/* 2463 */     return this.defaultEntityRadius;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDefaultEntityRadius(int radius)
/*      */   {
/* 2475 */     this.defaultEntityRadius = radius;
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
/*      */   public Shape lookupLegendShape(int series)
/*      */   {
/* 2488 */     Shape result = getLegendShape(series);
/* 2489 */     if (result == null) {
/* 2490 */       result = this.baseLegendShape;
/*      */     }
/* 2492 */     if (result == null) {
/* 2493 */       result = lookupSeriesShape(series);
/*      */     }
/* 2495 */     return result;
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
/*      */   public Shape getLegendShape(int series)
/*      */   {
/* 2511 */     return this.legendShape.getShape(series);
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
/*      */   public void setLegendShape(int series, Shape shape)
/*      */   {
/* 2524 */     this.legendShape.setShape(series, shape);
/* 2525 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getBaseLegendShape()
/*      */   {
/* 2536 */     return this.baseLegendShape;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseLegendShape(Shape shape)
/*      */   {
/* 2548 */     this.baseLegendShape = shape;
/* 2549 */     fireChangeEvent();
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
/*      */   public Font lookupLegendTextFont(int series)
/*      */   {
/* 2562 */     Font result = getLegendTextFont(series);
/* 2563 */     if (result == null) {
/* 2564 */       result = this.baseLegendTextFont;
/*      */     }
/* 2566 */     return result;
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
/*      */   public Font getLegendTextFont(int series)
/*      */   {
/* 2582 */     return (Font)this.legendTextFont.get(series);
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
/*      */   public void setLegendTextFont(int series, Font font)
/*      */   {
/* 2595 */     this.legendTextFont.set(series, font);
/* 2596 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getBaseLegendTextFont()
/*      */   {
/* 2607 */     return this.baseLegendTextFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseLegendTextFont(Font font)
/*      */   {
/* 2619 */     this.baseLegendTextFont = font;
/* 2620 */     fireChangeEvent();
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
/*      */   public Paint lookupLegendTextPaint(int series)
/*      */   {
/* 2633 */     Paint result = getLegendTextPaint(series);
/* 2634 */     if (result == null) {
/* 2635 */       result = this.baseLegendTextPaint;
/*      */     }
/* 2637 */     return result;
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
/*      */   public Paint getLegendTextPaint(int series)
/*      */   {
/* 2653 */     return this.legendTextPaint.getPaint(series);
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
/*      */   public void setLegendTextPaint(int series, Paint paint)
/*      */   {
/* 2666 */     this.legendTextPaint.setPaint(series, paint);
/* 2667 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaseLegendTextPaint()
/*      */   {
/* 2678 */     return this.baseLegendTextPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseLegendTextPaint(Paint paint)
/*      */   {
/* 2690 */     this.baseLegendTextPaint = paint;
/* 2691 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getDataBoundsIncludesVisibleSeriesOnly()
/*      */   {
/* 2703 */     return this.dataBoundsIncludesVisibleSeriesOnly;
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
/*      */   public void setDataBoundsIncludesVisibleSeriesOnly(boolean visibleOnly)
/*      */   {
/* 2716 */     this.dataBoundsIncludesVisibleSeriesOnly = visibleOnly;
/* 2717 */     notifyListeners(new RendererChangeEvent(this, true));
/*      */   }
/*      */   
/*      */ 
/* 2721 */   private static final double ADJ = Math.cos(0.5235987755982988D);
/*      */   
/*      */ 
/* 2724 */   private static final double OPP = Math.sin(0.5235987755982988D);
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean seriesVisible;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private Boolean seriesVisibleInLegend;
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private transient Paint paint;
/*      */   
/* 2738 */   protected Point2D calculateLabelAnchorPoint(ItemLabelAnchor anchor, double x, double y, PlotOrientation orientation) { Point2D result = null;
/* 2739 */     if (anchor == ItemLabelAnchor.CENTER) {
/* 2740 */       result = new Point2D.Double(x, y);
/*      */     }
/* 2742 */     else if (anchor == ItemLabelAnchor.INSIDE1) {
/* 2743 */       result = new Point2D.Double(x + OPP * this.itemLabelAnchorOffset, y - ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2746 */     else if (anchor == ItemLabelAnchor.INSIDE2) {
/* 2747 */       result = new Point2D.Double(x + ADJ * this.itemLabelAnchorOffset, y - OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2750 */     else if (anchor == ItemLabelAnchor.INSIDE3) {
/* 2751 */       result = new Point2D.Double(x + this.itemLabelAnchorOffset, y);
/*      */     }
/* 2753 */     else if (anchor == ItemLabelAnchor.INSIDE4) {
/* 2754 */       result = new Point2D.Double(x + ADJ * this.itemLabelAnchorOffset, y + OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2757 */     else if (anchor == ItemLabelAnchor.INSIDE5) {
/* 2758 */       result = new Point2D.Double(x + OPP * this.itemLabelAnchorOffset, y + ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2761 */     else if (anchor == ItemLabelAnchor.INSIDE6) {
/* 2762 */       result = new Point2D.Double(x, y + this.itemLabelAnchorOffset);
/*      */     }
/* 2764 */     else if (anchor == ItemLabelAnchor.INSIDE7) {
/* 2765 */       result = new Point2D.Double(x - OPP * this.itemLabelAnchorOffset, y + ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2768 */     else if (anchor == ItemLabelAnchor.INSIDE8) {
/* 2769 */       result = new Point2D.Double(x - ADJ * this.itemLabelAnchorOffset, y + OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2772 */     else if (anchor == ItemLabelAnchor.INSIDE9) {
/* 2773 */       result = new Point2D.Double(x - this.itemLabelAnchorOffset, y);
/*      */     }
/* 2775 */     else if (anchor == ItemLabelAnchor.INSIDE10) {
/* 2776 */       result = new Point2D.Double(x - ADJ * this.itemLabelAnchorOffset, y - OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2779 */     else if (anchor == ItemLabelAnchor.INSIDE11) {
/* 2780 */       result = new Point2D.Double(x - OPP * this.itemLabelAnchorOffset, y - ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2783 */     else if (anchor == ItemLabelAnchor.INSIDE12) {
/* 2784 */       result = new Point2D.Double(x, y - this.itemLabelAnchorOffset);
/*      */     }
/* 2786 */     else if (anchor == ItemLabelAnchor.OUTSIDE1) {
/* 2787 */       result = new Point2D.Double(x + 2.0D * OPP * this.itemLabelAnchorOffset, y - 2.0D * ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2791 */     else if (anchor == ItemLabelAnchor.OUTSIDE2) {
/* 2792 */       result = new Point2D.Double(x + 2.0D * ADJ * this.itemLabelAnchorOffset, y - 2.0D * OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2796 */     else if (anchor == ItemLabelAnchor.OUTSIDE3) {
/* 2797 */       result = new Point2D.Double(x + 2.0D * this.itemLabelAnchorOffset, y);
/*      */ 
/*      */     }
/* 2800 */     else if (anchor == ItemLabelAnchor.OUTSIDE4) {
/* 2801 */       result = new Point2D.Double(x + 2.0D * ADJ * this.itemLabelAnchorOffset, y + 2.0D * OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2805 */     else if (anchor == ItemLabelAnchor.OUTSIDE5) {
/* 2806 */       result = new Point2D.Double(x + 2.0D * OPP * this.itemLabelAnchorOffset, y + 2.0D * ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2810 */     else if (anchor == ItemLabelAnchor.OUTSIDE6) {
/* 2811 */       result = new Point2D.Double(x, y + 2.0D * this.itemLabelAnchorOffset);
/*      */ 
/*      */     }
/* 2814 */     else if (anchor == ItemLabelAnchor.OUTSIDE7) {
/* 2815 */       result = new Point2D.Double(x - 2.0D * OPP * this.itemLabelAnchorOffset, y + 2.0D * ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2819 */     else if (anchor == ItemLabelAnchor.OUTSIDE8) {
/* 2820 */       result = new Point2D.Double(x - 2.0D * ADJ * this.itemLabelAnchorOffset, y + 2.0D * OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2824 */     else if (anchor == ItemLabelAnchor.OUTSIDE9) {
/* 2825 */       result = new Point2D.Double(x - 2.0D * this.itemLabelAnchorOffset, y);
/*      */ 
/*      */     }
/* 2828 */     else if (anchor == ItemLabelAnchor.OUTSIDE10) {
/* 2829 */       result = new Point2D.Double(x - 2.0D * ADJ * this.itemLabelAnchorOffset, y - 2.0D * OPP * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2833 */     else if (anchor == ItemLabelAnchor.OUTSIDE11) {
/* 2834 */       result = new Point2D.Double(x - 2.0D * OPP * this.itemLabelAnchorOffset, y - 2.0D * ADJ * this.itemLabelAnchorOffset);
/*      */ 
/*      */ 
/*      */     }
/* 2838 */     else if (anchor == ItemLabelAnchor.OUTSIDE12) {
/* 2839 */       result = new Point2D.Double(x, y - 2.0D * this.itemLabelAnchorOffset);
/*      */     }
/*      */     
/* 2842 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addChangeListener(RendererChangeListener listener)
/*      */   {
/* 2853 */     if (listener == null) {
/* 2854 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*      */     }
/* 2856 */     this.listenerList.add(RendererChangeListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeChangeListener(RendererChangeListener listener)
/*      */   {
/* 2868 */     if (listener == null) {
/* 2869 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*      */     }
/* 2871 */     this.listenerList.remove(RendererChangeListener.class, listener);
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
/*      */   public boolean hasListener(EventListener listener)
/*      */   {
/* 2884 */     List list = Arrays.asList(this.listenerList.getListenerList());
/* 2885 */     return list.contains(listener);
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
/*      */   protected void fireChangeEvent()
/*      */   {
/* 2904 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void notifyListeners(RendererChangeEvent event)
/*      */   {
/* 2913 */     Object[] ls = this.listenerList.getListenerList();
/* 2914 */     for (int i = ls.length - 2; i >= 0; i -= 2) {
/* 2915 */       if (ls[i] == RendererChangeListener.class) {
/* 2916 */         ((RendererChangeListener)ls[(i + 1)]).rendererChanged(event);
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
/*      */   public boolean equals(Object obj)
/*      */   {
/* 2929 */     if (obj == this) {
/* 2930 */       return true;
/*      */     }
/* 2932 */     if (!(obj instanceof AbstractRenderer)) {
/* 2933 */       return false;
/*      */     }
/* 2935 */     AbstractRenderer that = (AbstractRenderer)obj;
/* 2936 */     if (this.dataBoundsIncludesVisibleSeriesOnly != that.dataBoundsIncludesVisibleSeriesOnly)
/*      */     {
/* 2938 */       return false;
/*      */     }
/* 2940 */     if (this.defaultEntityRadius != that.defaultEntityRadius) {
/* 2941 */       return false;
/*      */     }
/* 2943 */     if (!ObjectUtilities.equal(this.seriesVisible, that.seriesVisible)) {
/* 2944 */       return false;
/*      */     }
/* 2946 */     if (!this.seriesVisibleList.equals(that.seriesVisibleList)) {
/* 2947 */       return false;
/*      */     }
/* 2949 */     if (this.baseSeriesVisible != that.baseSeriesVisible) {
/* 2950 */       return false;
/*      */     }
/* 2952 */     if (!ObjectUtilities.equal(this.seriesVisibleInLegend, that.seriesVisibleInLegend))
/*      */     {
/* 2954 */       return false;
/*      */     }
/* 2956 */     if (!this.seriesVisibleInLegendList.equals(that.seriesVisibleInLegendList))
/*      */     {
/* 2958 */       return false;
/*      */     }
/* 2960 */     if (this.baseSeriesVisibleInLegend != that.baseSeriesVisibleInLegend) {
/* 2961 */       return false;
/*      */     }
/* 2963 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 2964 */       return false;
/*      */     }
/* 2966 */     if (!ObjectUtilities.equal(this.paintList, that.paintList)) {
/* 2967 */       return false;
/*      */     }
/* 2969 */     if (!PaintUtilities.equal(this.basePaint, that.basePaint)) {
/* 2970 */       return false;
/*      */     }
/* 2972 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 2973 */       return false;
/*      */     }
/* 2975 */     if (!ObjectUtilities.equal(this.fillPaintList, that.fillPaintList)) {
/* 2976 */       return false;
/*      */     }
/* 2978 */     if (!PaintUtilities.equal(this.baseFillPaint, that.baseFillPaint)) {
/* 2979 */       return false;
/*      */     }
/* 2981 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 2982 */       return false;
/*      */     }
/* 2984 */     if (!ObjectUtilities.equal(this.outlinePaintList, that.outlinePaintList))
/*      */     {
/* 2986 */       return false;
/*      */     }
/* 2988 */     if (!PaintUtilities.equal(this.baseOutlinePaint, that.baseOutlinePaint))
/*      */     {
/* 2990 */       return false;
/*      */     }
/* 2992 */     if (!ObjectUtilities.equal(this.stroke, that.stroke)) {
/* 2993 */       return false;
/*      */     }
/* 2995 */     if (!ObjectUtilities.equal(this.strokeList, that.strokeList)) {
/* 2996 */       return false;
/*      */     }
/* 2998 */     if (!ObjectUtilities.equal(this.baseStroke, that.baseStroke)) {
/* 2999 */       return false;
/*      */     }
/* 3001 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
/* 3002 */       return false;
/*      */     }
/* 3004 */     if (!ObjectUtilities.equal(this.outlineStrokeList, that.outlineStrokeList))
/*      */     {
/* 3006 */       return false;
/*      */     }
/* 3008 */     if (!ObjectUtilities.equal(this.baseOutlineStroke, that.baseOutlineStroke))
/*      */     {
/*      */ 
/* 3011 */       return false;
/*      */     }
/* 3013 */     if (!ShapeUtilities.equal(this.shape, that.shape)) {
/* 3014 */       return false;
/*      */     }
/* 3016 */     if (!ObjectUtilities.equal(this.shapeList, that.shapeList)) {
/* 3017 */       return false;
/*      */     }
/* 3019 */     if (!ShapeUtilities.equal(this.baseShape, that.baseShape)) {
/* 3020 */       return false;
/*      */     }
/* 3022 */     if (!ObjectUtilities.equal(this.itemLabelsVisible, that.itemLabelsVisible))
/*      */     {
/* 3024 */       return false;
/*      */     }
/* 3026 */     if (!ObjectUtilities.equal(this.itemLabelsVisibleList, that.itemLabelsVisibleList))
/*      */     {
/* 3028 */       return false;
/*      */     }
/* 3030 */     if (!ObjectUtilities.equal(this.baseItemLabelsVisible, that.baseItemLabelsVisible))
/*      */     {
/* 3032 */       return false;
/*      */     }
/* 3034 */     if (!ObjectUtilities.equal(this.itemLabelFont, that.itemLabelFont)) {
/* 3035 */       return false;
/*      */     }
/* 3037 */     if (!ObjectUtilities.equal(this.itemLabelFontList, that.itemLabelFontList))
/*      */     {
/* 3039 */       return false;
/*      */     }
/* 3041 */     if (!ObjectUtilities.equal(this.baseItemLabelFont, that.baseItemLabelFont))
/*      */     {
/* 3043 */       return false;
/*      */     }
/*      */     
/* 3046 */     if (!PaintUtilities.equal(this.itemLabelPaint, that.itemLabelPaint)) {
/* 3047 */       return false;
/*      */     }
/* 3049 */     if (!ObjectUtilities.equal(this.itemLabelPaintList, that.itemLabelPaintList))
/*      */     {
/* 3051 */       return false;
/*      */     }
/* 3053 */     if (!PaintUtilities.equal(this.baseItemLabelPaint, that.baseItemLabelPaint))
/*      */     {
/* 3055 */       return false;
/*      */     }
/*      */     
/* 3058 */     if (!ObjectUtilities.equal(this.positiveItemLabelPosition, that.positiveItemLabelPosition))
/*      */     {
/* 3060 */       return false;
/*      */     }
/* 3062 */     if (!ObjectUtilities.equal(this.positiveItemLabelPositionList, that.positiveItemLabelPositionList))
/*      */     {
/* 3064 */       return false;
/*      */     }
/* 3066 */     if (!ObjectUtilities.equal(this.basePositiveItemLabelPosition, that.basePositiveItemLabelPosition))
/*      */     {
/* 3068 */       return false;
/*      */     }
/*      */     
/* 3071 */     if (!ObjectUtilities.equal(this.negativeItemLabelPosition, that.negativeItemLabelPosition))
/*      */     {
/* 3073 */       return false;
/*      */     }
/* 3075 */     if (!ObjectUtilities.equal(this.negativeItemLabelPositionList, that.negativeItemLabelPositionList))
/*      */     {
/* 3077 */       return false;
/*      */     }
/* 3079 */     if (!ObjectUtilities.equal(this.baseNegativeItemLabelPosition, that.baseNegativeItemLabelPosition))
/*      */     {
/* 3081 */       return false;
/*      */     }
/* 3083 */     if (this.itemLabelAnchorOffset != that.itemLabelAnchorOffset) {
/* 3084 */       return false;
/*      */     }
/* 3086 */     if (!ObjectUtilities.equal(this.createEntities, that.createEntities)) {
/* 3087 */       return false;
/*      */     }
/* 3089 */     if (!ObjectUtilities.equal(this.createEntitiesList, that.createEntitiesList))
/*      */     {
/* 3091 */       return false;
/*      */     }
/* 3093 */     if (this.baseCreateEntities != that.baseCreateEntities) {
/* 3094 */       return false;
/*      */     }
/* 3096 */     if (!ObjectUtilities.equal(this.legendShape, that.legendShape)) {
/* 3097 */       return false;
/*      */     }
/* 3099 */     if (!ShapeUtilities.equal(this.baseLegendShape, that.baseLegendShape))
/*      */     {
/* 3101 */       return false;
/*      */     }
/* 3103 */     if (!ObjectUtilities.equal(this.legendTextFont, that.legendTextFont)) {
/* 3104 */       return false;
/*      */     }
/* 3106 */     if (!ObjectUtilities.equal(this.baseLegendTextFont, that.baseLegendTextFont))
/*      */     {
/* 3108 */       return false;
/*      */     }
/* 3110 */     if (!ObjectUtilities.equal(this.legendTextPaint, that.legendTextPaint))
/*      */     {
/* 3112 */       return false;
/*      */     }
/* 3114 */     if (!PaintUtilities.equal(this.baseLegendTextPaint, that.baseLegendTextPaint))
/*      */     {
/* 3116 */       return false;
/*      */     }
/* 3118 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int hashCode()
/*      */   {
/* 3127 */     int result = 193;
/* 3128 */     result = HashUtilities.hashCode(result, this.seriesVisibleList);
/* 3129 */     result = HashUtilities.hashCode(result, this.baseSeriesVisible);
/* 3130 */     result = HashUtilities.hashCode(result, this.seriesVisibleInLegendList);
/* 3131 */     result = HashUtilities.hashCode(result, this.baseSeriesVisibleInLegend);
/* 3132 */     result = HashUtilities.hashCode(result, this.paintList);
/* 3133 */     result = HashUtilities.hashCode(result, this.basePaint);
/* 3134 */     result = HashUtilities.hashCode(result, this.fillPaintList);
/* 3135 */     result = HashUtilities.hashCode(result, this.baseFillPaint);
/* 3136 */     result = HashUtilities.hashCode(result, this.outlinePaintList);
/* 3137 */     result = HashUtilities.hashCode(result, this.baseOutlinePaint);
/* 3138 */     result = HashUtilities.hashCode(result, this.strokeList);
/* 3139 */     result = HashUtilities.hashCode(result, this.baseStroke);
/* 3140 */     result = HashUtilities.hashCode(result, this.outlineStrokeList);
/* 3141 */     result = HashUtilities.hashCode(result, this.baseOutlineStroke);
/*      */     
/*      */ 
/* 3144 */     result = HashUtilities.hashCode(result, this.itemLabelsVisibleList);
/* 3145 */     result = HashUtilities.hashCode(result, this.baseItemLabelsVisible);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 3157 */     return result;
/*      */   }
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
/* 3169 */     AbstractRenderer clone = (AbstractRenderer)super.clone();
/*      */     
/* 3171 */     if (this.seriesVisibleList != null) {
/* 3172 */       clone.seriesVisibleList = ((BooleanList)this.seriesVisibleList.clone());
/*      */     }
/*      */     
/*      */ 
/* 3176 */     if (this.seriesVisibleInLegendList != null) {
/* 3177 */       clone.seriesVisibleInLegendList = ((BooleanList)this.seriesVisibleInLegendList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3182 */     if (this.paintList != null) {
/* 3183 */       clone.paintList = ((PaintList)this.paintList.clone());
/*      */     }
/*      */     
/*      */ 
/* 3187 */     if (this.fillPaintList != null) {
/* 3188 */       clone.fillPaintList = ((PaintList)this.fillPaintList.clone());
/*      */     }
/*      */     
/* 3191 */     if (this.outlinePaintList != null) {
/* 3192 */       clone.outlinePaintList = ((PaintList)this.outlinePaintList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3197 */     if (this.strokeList != null) {
/* 3198 */       clone.strokeList = ((StrokeList)this.strokeList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3203 */     if (this.outlineStrokeList != null) {
/* 3204 */       clone.outlineStrokeList = ((StrokeList)this.outlineStrokeList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3209 */     if (this.shape != null) {
/* 3210 */       clone.shape = ShapeUtilities.clone(this.shape);
/*      */     }
/* 3212 */     if (this.shapeList != null) {
/* 3213 */       clone.shapeList = ((ShapeList)this.shapeList.clone());
/*      */     }
/* 3215 */     if (this.baseShape != null) {
/* 3216 */       clone.baseShape = ShapeUtilities.clone(this.baseShape);
/*      */     }
/*      */     
/*      */ 
/* 3220 */     if (this.itemLabelsVisibleList != null) {
/* 3221 */       clone.itemLabelsVisibleList = ((BooleanList)this.itemLabelsVisibleList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3227 */     if (this.itemLabelFontList != null) {
/* 3228 */       clone.itemLabelFontList = ((ObjectList)this.itemLabelFontList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3234 */     if (this.itemLabelPaintList != null) {
/* 3235 */       clone.itemLabelPaintList = ((PaintList)this.itemLabelPaintList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3241 */     if (this.positiveItemLabelPositionList != null) {
/* 3242 */       clone.positiveItemLabelPositionList = ((ObjectList)this.positiveItemLabelPositionList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3248 */     if (this.negativeItemLabelPositionList != null) {
/* 3249 */       clone.negativeItemLabelPositionList = ((ObjectList)this.negativeItemLabelPositionList.clone());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3254 */     if (this.createEntitiesList != null) {
/* 3255 */       clone.createEntitiesList = ((BooleanList)this.createEntitiesList.clone());
/*      */     }
/*      */     
/*      */ 
/* 3259 */     if (this.legendShape != null) {
/* 3260 */       clone.legendShape = ((ShapeList)this.legendShape.clone());
/*      */     }
/* 3262 */     if (this.legendTextFont != null) {
/* 3263 */       clone.legendTextFont = ((ObjectList)this.legendTextFont.clone());
/*      */     }
/* 3265 */     if (this.legendTextPaint != null) {
/* 3266 */       clone.legendTextPaint = ((PaintList)this.legendTextPaint.clone());
/*      */     }
/* 3268 */     clone.listenerList = new EventListenerList();
/* 3269 */     clone.event = null;
/* 3270 */     return clone;
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
/* 3282 */     stream.defaultWriteObject();
/* 3283 */     SerialUtilities.writePaint(this.paint, stream);
/* 3284 */     SerialUtilities.writePaint(this.basePaint, stream);
/* 3285 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 3286 */     SerialUtilities.writePaint(this.baseFillPaint, stream);
/* 3287 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 3288 */     SerialUtilities.writePaint(this.baseOutlinePaint, stream);
/* 3289 */     SerialUtilities.writeStroke(this.stroke, stream);
/* 3290 */     SerialUtilities.writeStroke(this.baseStroke, stream);
/* 3291 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 3292 */     SerialUtilities.writeStroke(this.baseOutlineStroke, stream);
/* 3293 */     SerialUtilities.writeShape(this.shape, stream);
/* 3294 */     SerialUtilities.writeShape(this.baseShape, stream);
/* 3295 */     SerialUtilities.writePaint(this.itemLabelPaint, stream);
/* 3296 */     SerialUtilities.writePaint(this.baseItemLabelPaint, stream);
/* 3297 */     SerialUtilities.writeShape(this.baseLegendShape, stream);
/* 3298 */     SerialUtilities.writePaint(this.baseLegendTextPaint, stream);
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
/* 3313 */     stream.defaultReadObject();
/* 3314 */     this.paint = SerialUtilities.readPaint(stream);
/* 3315 */     this.basePaint = SerialUtilities.readPaint(stream);
/* 3316 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 3317 */     this.baseFillPaint = SerialUtilities.readPaint(stream);
/* 3318 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 3319 */     this.baseOutlinePaint = SerialUtilities.readPaint(stream);
/* 3320 */     this.stroke = SerialUtilities.readStroke(stream);
/* 3321 */     this.baseStroke = SerialUtilities.readStroke(stream);
/* 3322 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 3323 */     this.baseOutlineStroke = SerialUtilities.readStroke(stream);
/* 3324 */     this.shape = SerialUtilities.readShape(stream);
/* 3325 */     this.baseShape = SerialUtilities.readShape(stream);
/* 3326 */     this.itemLabelPaint = SerialUtilities.readPaint(stream);
/* 3327 */     this.baseItemLabelPaint = SerialUtilities.readPaint(stream);
/* 3328 */     this.baseLegendShape = SerialUtilities.readShape(stream);
/* 3329 */     this.baseLegendTextPaint = SerialUtilities.readPaint(stream);
/*      */     
/*      */ 
/*      */ 
/* 3333 */     this.listenerList = new EventListenerList();
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
/*      */   private transient Paint fillPaint;
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
/*      */   private transient Paint outlinePaint;
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
/*      */   private transient Stroke stroke;
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
/*      */   private transient Stroke outlineStroke;
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
/*      */   private transient Shape shape;
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
/*      */   private Boolean itemLabelsVisible;
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
/*      */   private Font itemLabelFont;
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
/*      */   private transient Paint itemLabelPaint;
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
/*      */   private ItemLabelPosition positiveItemLabelPosition;
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
/*      */   private ItemLabelPosition negativeItemLabelPosition;
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
/*      */   private Boolean createEntities;
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
/*      */   public Boolean getSeriesVisible()
/*      */   {
/* 3471 */     return this.seriesVisible;
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
/*      */   public void setSeriesVisible(Boolean visible)
/*      */   {
/* 3489 */     setSeriesVisible(visible, true);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setSeriesVisible(Boolean visible, boolean notify)
/*      */   {
/* 3508 */     this.seriesVisible = visible;
/* 3509 */     if (notify)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/* 3514 */       RendererChangeEvent e = new RendererChangeEvent(this, true);
/* 3515 */       notifyListeners(e);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Boolean getSeriesVisibleInLegend()
/*      */   {
/* 3534 */     return this.seriesVisibleInLegend;
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
/*      */   public void setSeriesVisibleInLegend(Boolean visible)
/*      */   {
/* 3552 */     setSeriesVisibleInLegend(visible, true);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setSeriesVisibleInLegend(Boolean visible, boolean notify)
/*      */   {
/* 3572 */     this.seriesVisibleInLegend = visible;
/* 3573 */     if (notify) {
/* 3574 */       fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setPaint(Paint paint)
/*      */   {
/* 3590 */     setPaint(paint, true);
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
/*      */   public void setPaint(Paint paint, boolean notify)
/*      */   {
/* 3605 */     this.paint = paint;
/* 3606 */     if (notify) {
/* 3607 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setFillPaint(Paint paint)
/*      */   {
/* 3621 */     setFillPaint(paint, true);
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
/*      */   public void setFillPaint(Paint paint, boolean notify)
/*      */   {
/* 3636 */     this.fillPaint = paint;
/* 3637 */     if (notify) {
/* 3638 */       fireChangeEvent();
/*      */     }
/*      */   }
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
/*      */   public void setOutlinePaint(Paint paint)
/*      */   {
/* 3653 */     setOutlinePaint(paint, true);
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
/*      */   public void setOutlinePaint(Paint paint, boolean notify)
/*      */   {
/* 3668 */     this.outlinePaint = paint;
/* 3669 */     if (notify) {
/* 3670 */       fireChangeEvent();
/*      */     }
/*      */   }
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
/*      */   public void setStroke(Stroke stroke)
/*      */   {
/* 3685 */     setStroke(stroke, true);
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
/*      */   public void setStroke(Stroke stroke, boolean notify)
/*      */   {
/* 3700 */     this.stroke = stroke;
/* 3701 */     if (notify) {
/* 3702 */       fireChangeEvent();
/*      */     }
/*      */   }
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
/*      */   public void setOutlineStroke(Stroke stroke)
/*      */   {
/* 3717 */     setOutlineStroke(stroke, true);
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
/*      */   public void setOutlineStroke(Stroke stroke, boolean notify)
/*      */   {
/* 3732 */     this.outlineStroke = stroke;
/* 3733 */     if (notify) {
/* 3734 */       fireChangeEvent();
/*      */     }
/*      */   }
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
/*      */   public void setShape(Shape shape)
/*      */   {
/* 3749 */     setShape(shape, true);
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
/*      */   public void setShape(Shape shape, boolean notify)
/*      */   {
/* 3764 */     this.shape = shape;
/* 3765 */     if (notify) {
/* 3766 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setItemLabelsVisible(boolean visible)
/*      */   {
/* 3780 */     setItemLabelsVisible(BooleanUtilities.valueOf(visible));
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
/*      */   public void setItemLabelsVisible(Boolean visible)
/*      */   {
/* 3796 */     setItemLabelsVisible(visible, true);
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
/*      */   public void setItemLabelsVisible(Boolean visible, boolean notify)
/*      */   {
/* 3814 */     this.itemLabelsVisible = visible;
/* 3815 */     if (notify) {
/* 3816 */       fireChangeEvent();
/*      */     }
/*      */   }
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
/*      */   public Font getItemLabelFont()
/*      */   {
/* 3831 */     return this.itemLabelFont;
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
/*      */   public void setItemLabelFont(Font font)
/*      */   {
/* 3847 */     setItemLabelFont(font, true);
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
/*      */   public void setItemLabelFont(Font font, boolean notify)
/*      */   {
/* 3863 */     this.itemLabelFont = font;
/* 3864 */     if (notify) {
/* 3865 */       fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Paint getItemLabelPaint()
/*      */   {
/* 3881 */     return this.itemLabelPaint;
/*      */   }
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
/*      */   public void setItemLabelPaint(Paint paint)
/*      */   {
/* 3895 */     setItemLabelPaint(paint, true);
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
/*      */   public void setItemLabelPaint(Paint paint, boolean notify)
/*      */   {
/* 3911 */     this.itemLabelPaint = paint;
/* 3912 */     if (notify) {
/* 3913 */       fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public ItemLabelPosition getPositiveItemLabelPosition()
/*      */   {
/* 3930 */     return this.positiveItemLabelPosition;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setPositiveItemLabelPosition(ItemLabelPosition position)
/*      */   {
/* 3949 */     setPositiveItemLabelPosition(position, true);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setPositiveItemLabelPosition(ItemLabelPosition position, boolean notify)
/*      */   {
/* 3969 */     this.positiveItemLabelPosition = position;
/* 3970 */     if (notify) {
/* 3971 */       fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public ItemLabelPosition getNegativeItemLabelPosition()
/*      */   {
/* 3988 */     return this.negativeItemLabelPosition;
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setNegativeItemLabelPosition(ItemLabelPosition position)
/*      */   {
/* 4007 */     setNegativeItemLabelPosition(position, true);
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public void setNegativeItemLabelPosition(ItemLabelPosition position, boolean notify)
/*      */   {
/* 4028 */     this.negativeItemLabelPosition = position;
/* 4029 */     if (notify) {
/* 4030 */       fireChangeEvent();
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
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   public Boolean getCreateEntities()
/*      */   {
/* 4047 */     return this.createEntities;
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
/*      */   public void setCreateEntities(Boolean create)
/*      */   {
/* 4064 */     setCreateEntities(create, true);
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
/*      */   public void setCreateEntities(Boolean create, boolean notify)
/*      */   {
/* 4082 */     this.createEntities = create;
/* 4083 */     if (notify) {
/* 4084 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/AbstractRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */