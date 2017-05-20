/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Collections;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.Set;
/*      */ import java.util.TreeMap;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.annotations.CategoryAnnotation;
/*      */ import org.jfree.chart.axis.Axis;
/*      */ import org.jfree.chart.axis.AxisCollection;
/*      */ import org.jfree.chart.axis.AxisLocation;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.CategoryAnchor;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.TickType;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.axis.ValueTick;
/*      */ import org.jfree.chart.event.ChartChangeEventType;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.renderer.category.AbstractCategoryItemRenderer;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRendererState;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.SortOrder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CategoryPlot
/*      */   extends Plot
/*      */   implements ValueAxisPlot, Pannable, Zoomable, RendererChangeListener, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -3537691700434728188L;
/*      */   public static final boolean DEFAULT_DOMAIN_GRIDLINES_VISIBLE = false;
/*      */   public static final boolean DEFAULT_RANGE_GRIDLINES_VISIBLE = true;
/*  268 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  273 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*      */   
/*      */ 
/*  276 */   public static final Font DEFAULT_VALUE_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  291 */   public static final Stroke DEFAULT_CROSSHAIR_STROKE = DEFAULT_GRIDLINE_STROKE;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  299 */   public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
/*      */   
/*      */ 
/*  302 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */   private PlotOrientation orientation;
/*      */   
/*      */ 
/*      */ 
/*      */   private RectangleInsets axisOffset;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList domainAxes;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList domainAxisLocations;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean drawSharedDomainAxis;
/*      */   
/*      */ 
/*      */   private ObjectList rangeAxes;
/*      */   
/*      */ 
/*      */   private ObjectList rangeAxisLocations;
/*      */   
/*      */ 
/*      */   private ObjectList datasets;
/*      */   
/*      */ 
/*      */   private TreeMap datasetToDomainAxesMap;
/*      */   
/*      */ 
/*      */   private TreeMap datasetToRangeAxesMap;
/*      */   
/*      */ 
/*      */   private ObjectList renderers;
/*      */   
/*      */ 
/*  343 */   private DatasetRenderingOrder renderingOrder = DatasetRenderingOrder.REVERSE;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  350 */   private SortOrder columnRenderingOrder = SortOrder.ASCENDING;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  356 */   private SortOrder rowRenderingOrder = SortOrder.ASCENDING;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean domainGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private CategoryAnchor domainGridlinePosition;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke domainGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint domainGridlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangeZeroBaselineVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeZeroBaselineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint rangeZeroBaselinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangeGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint rangeGridlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangeMinorGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeMinorGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint rangeMinorGridlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double anchorValue;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int crosshairDatasetIndex;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean domainCrosshairVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Comparable domainCrosshairRowKey;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Comparable domainCrosshairColumnKey;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke domainCrosshairStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint domainCrosshairPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangeCrosshairVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double rangeCrosshairValue;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeCrosshairStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint rangeCrosshairPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  491 */   private boolean rangeCrosshairLockedOnData = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map foregroundDomainMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map backgroundDomainMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map foregroundRangeMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map backgroundRangeMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */   private List annotations;
/*      */   
/*      */ 
/*      */ 
/*      */   private int weight;
/*      */   
/*      */ 
/*      */ 
/*      */   private AxisSpace fixedDomainAxisSpace;
/*      */   
/*      */ 
/*      */ 
/*      */   private AxisSpace fixedRangeAxisSpace;
/*      */   
/*      */ 
/*      */ 
/*      */   private LegendItemCollection fixedLegendItems;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean rangePannable;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryPlot()
/*      */   {
/*  542 */     this(null, null, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryPlot(CategoryDataset dataset, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryItemRenderer renderer)
/*      */   {
/*  561 */     this.orientation = PlotOrientation.VERTICAL;
/*      */     
/*      */ 
/*  564 */     this.domainAxes = new ObjectList();
/*  565 */     this.domainAxisLocations = new ObjectList();
/*  566 */     this.rangeAxes = new ObjectList();
/*  567 */     this.rangeAxisLocations = new ObjectList();
/*      */     
/*  569 */     this.datasetToDomainAxesMap = new TreeMap();
/*  570 */     this.datasetToRangeAxesMap = new TreeMap();
/*      */     
/*  572 */     this.renderers = new ObjectList();
/*      */     
/*  574 */     this.datasets = new ObjectList();
/*  575 */     this.datasets.set(0, dataset);
/*  576 */     if (dataset != null) {
/*  577 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*  580 */     this.axisOffset = RectangleInsets.ZERO_INSETS;
/*      */     
/*  582 */     setDomainAxisLocation(AxisLocation.BOTTOM_OR_LEFT, false);
/*  583 */     setRangeAxisLocation(AxisLocation.TOP_OR_LEFT, false);
/*      */     
/*  585 */     this.renderers.set(0, renderer);
/*  586 */     if (renderer != null) {
/*  587 */       renderer.setPlot(this);
/*  588 */       renderer.addChangeListener(this);
/*      */     }
/*      */     
/*  591 */     this.domainAxes.set(0, domainAxis);
/*  592 */     mapDatasetToDomainAxis(0, 0);
/*  593 */     if (domainAxis != null) {
/*  594 */       domainAxis.setPlot(this);
/*  595 */       domainAxis.addChangeListener(this);
/*      */     }
/*  597 */     this.drawSharedDomainAxis = false;
/*      */     
/*  599 */     this.rangeAxes.set(0, rangeAxis);
/*  600 */     mapDatasetToRangeAxis(0, 0);
/*  601 */     if (rangeAxis != null) {
/*  602 */       rangeAxis.setPlot(this);
/*  603 */       rangeAxis.addChangeListener(this);
/*      */     }
/*      */     
/*  606 */     configureDomainAxes();
/*  607 */     configureRangeAxes();
/*      */     
/*  609 */     this.domainGridlinesVisible = false;
/*  610 */     this.domainGridlinePosition = CategoryAnchor.MIDDLE;
/*  611 */     this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  612 */     this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */     
/*  614 */     this.rangeZeroBaselineVisible = false;
/*  615 */     this.rangeZeroBaselinePaint = Color.black;
/*  616 */     this.rangeZeroBaselineStroke = new BasicStroke(0.5F);
/*      */     
/*  618 */     this.rangeGridlinesVisible = true;
/*  619 */     this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  620 */     this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */     
/*  622 */     this.rangeMinorGridlinesVisible = false;
/*  623 */     this.rangeMinorGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  624 */     this.rangeMinorGridlinePaint = Color.white;
/*      */     
/*  626 */     this.foregroundDomainMarkers = new HashMap();
/*  627 */     this.backgroundDomainMarkers = new HashMap();
/*  628 */     this.foregroundRangeMarkers = new HashMap();
/*  629 */     this.backgroundRangeMarkers = new HashMap();
/*      */     
/*  631 */     this.anchorValue = 0.0D;
/*      */     
/*  633 */     this.domainCrosshairVisible = false;
/*  634 */     this.domainCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
/*  635 */     this.domainCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
/*      */     
/*  637 */     this.rangeCrosshairVisible = false;
/*  638 */     this.rangeCrosshairValue = 0.0D;
/*  639 */     this.rangeCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
/*  640 */     this.rangeCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
/*      */     
/*  642 */     this.annotations = new ArrayList();
/*      */     
/*  644 */     this.rangePannable = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/*  653 */     return localizationResources.getString("Category_Plot");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PlotOrientation getOrientation()
/*      */   {
/*  664 */     return this.orientation;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOrientation(PlotOrientation orientation)
/*      */   {
/*  676 */     if (orientation == null) {
/*  677 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*  679 */     this.orientation = orientation;
/*  680 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getAxisOffset()
/*      */   {
/*  691 */     return this.axisOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxisOffset(RectangleInsets offset)
/*      */   {
/*  703 */     if (offset == null) {
/*  704 */       throw new IllegalArgumentException("Null 'offset' argument.");
/*      */     }
/*  706 */     this.axisOffset = offset;
/*  707 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryAxis getDomainAxis()
/*      */   {
/*  720 */     return getDomainAxis(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryAxis getDomainAxis(int index)
/*      */   {
/*  733 */     CategoryAxis result = null;
/*  734 */     if (index < this.domainAxes.size()) {
/*  735 */       result = (CategoryAxis)this.domainAxes.get(index);
/*      */     }
/*  737 */     if (result == null) {
/*  738 */       Plot parent = getParent();
/*  739 */       if ((parent instanceof CategoryPlot)) {
/*  740 */         CategoryPlot cp = (CategoryPlot)parent;
/*  741 */         result = cp.getDomainAxis(index);
/*      */       }
/*      */     }
/*  744 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxis(CategoryAxis axis)
/*      */   {
/*  756 */     setDomainAxis(0, axis);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxis(int index, CategoryAxis axis)
/*      */   {
/*  769 */     setDomainAxis(index, axis, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxis(int index, CategoryAxis axis, boolean notify)
/*      */   {
/*  781 */     CategoryAxis existing = (CategoryAxis)this.domainAxes.get(index);
/*  782 */     if (existing != null) {
/*  783 */       existing.removeChangeListener(this);
/*      */     }
/*  785 */     if (axis != null) {
/*  786 */       axis.setPlot(this);
/*      */     }
/*  788 */     this.domainAxes.set(index, axis);
/*  789 */     if (axis != null) {
/*  790 */       axis.configure();
/*  791 */       axis.addChangeListener(this);
/*      */     }
/*  793 */     if (notify) {
/*  794 */       fireChangeEvent();
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
/*      */   public void setDomainAxes(CategoryAxis[] axes)
/*      */   {
/*  807 */     for (int i = 0; i < axes.length; i++) {
/*  808 */       setDomainAxis(i, axes[i], false);
/*      */     }
/*  810 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDomainAxisIndex(CategoryAxis axis)
/*      */   {
/*  827 */     if (axis == null) {
/*  828 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*      */     }
/*  830 */     return this.domainAxes.indexOf(axis);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisLocation getDomainAxisLocation()
/*      */   {
/*  841 */     return getDomainAxisLocation(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisLocation getDomainAxisLocation(int index)
/*      */   {
/*  854 */     AxisLocation result = null;
/*  855 */     if (index < this.domainAxisLocations.size()) {
/*  856 */       result = (AxisLocation)this.domainAxisLocations.get(index);
/*      */     }
/*  858 */     if (result == null) {
/*  859 */       result = AxisLocation.getOpposite(getDomainAxisLocation(0));
/*      */     }
/*  861 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxisLocation(AxisLocation location)
/*      */   {
/*  875 */     setDomainAxisLocation(0, location, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxisLocation(AxisLocation location, boolean notify)
/*      */   {
/*  887 */     setDomainAxisLocation(0, location, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxisLocation(int index, AxisLocation location)
/*      */   {
/*  902 */     setDomainAxisLocation(index, location, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxisLocation(int index, AxisLocation location, boolean notify)
/*      */   {
/*  920 */     if ((index == 0) && (location == null)) {
/*  921 */       throw new IllegalArgumentException("Null 'location' for index 0 not permitted.");
/*      */     }
/*      */     
/*  924 */     this.domainAxisLocations.set(index, location);
/*  925 */     if (notify) {
/*  926 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getDomainAxisEdge()
/*      */   {
/*  937 */     return getDomainAxisEdge(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getDomainAxisEdge(int index)
/*      */   {
/*  948 */     RectangleEdge result = null;
/*  949 */     AxisLocation location = getDomainAxisLocation(index);
/*  950 */     if (location != null) {
/*  951 */       result = Plot.resolveDomainAxisLocation(location, this.orientation);
/*      */     }
/*      */     else {
/*  954 */       result = RectangleEdge.opposite(getDomainAxisEdge(0));
/*      */     }
/*  956 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDomainAxisCount()
/*      */   {
/*  965 */     return this.domainAxes.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainAxes()
/*      */   {
/*  973 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  974 */       CategoryAxis axis = (CategoryAxis)this.domainAxes.get(i);
/*  975 */       if (axis != null) {
/*  976 */         axis.removeChangeListener(this);
/*      */       }
/*      */     }
/*  979 */     this.domainAxes.clear();
/*  980 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void configureDomainAxes()
/*      */   {
/*  987 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  988 */       CategoryAxis axis = (CategoryAxis)this.domainAxes.get(i);
/*  989 */       if (axis != null) {
/*  990 */         axis.configure();
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
/*      */   public ValueAxis getRangeAxis()
/*      */   {
/* 1003 */     return getRangeAxis(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getRangeAxis(int index)
/*      */   {
/* 1014 */     ValueAxis result = null;
/* 1015 */     if (index < this.rangeAxes.size()) {
/* 1016 */       result = (ValueAxis)this.rangeAxes.get(index);
/*      */     }
/* 1018 */     if (result == null) {
/* 1019 */       Plot parent = getParent();
/* 1020 */       if ((parent instanceof CategoryPlot)) {
/* 1021 */         CategoryPlot cp = (CategoryPlot)parent;
/* 1022 */         result = cp.getRangeAxis(index);
/*      */       }
/*      */     }
/* 1025 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxis(ValueAxis axis)
/*      */   {
/* 1035 */     setRangeAxis(0, axis);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxis(int index, ValueAxis axis)
/*      */   {
/* 1046 */     setRangeAxis(index, axis, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxis(int index, ValueAxis axis, boolean notify)
/*      */   {
/* 1058 */     ValueAxis existing = (ValueAxis)this.rangeAxes.get(index);
/* 1059 */     if (existing != null) {
/* 1060 */       existing.removeChangeListener(this);
/*      */     }
/* 1062 */     if (axis != null) {
/* 1063 */       axis.setPlot(this);
/*      */     }
/* 1065 */     this.rangeAxes.set(index, axis);
/* 1066 */     if (axis != null) {
/* 1067 */       axis.configure();
/* 1068 */       axis.addChangeListener(this);
/*      */     }
/* 1070 */     if (notify) {
/* 1071 */       fireChangeEvent();
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
/*      */   public void setRangeAxes(ValueAxis[] axes)
/*      */   {
/* 1084 */     for (int i = 0; i < axes.length; i++) {
/* 1085 */       setRangeAxis(i, axes[i], false);
/*      */     }
/* 1087 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRangeAxisIndex(ValueAxis axis)
/*      */   {
/* 1104 */     if (axis == null) {
/* 1105 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*      */     }
/* 1107 */     int result = this.rangeAxes.indexOf(axis);
/* 1108 */     if (result < 0) {
/* 1109 */       Plot parent = getParent();
/* 1110 */       if ((parent instanceof CategoryPlot)) {
/* 1111 */         CategoryPlot p = (CategoryPlot)parent;
/* 1112 */         result = p.getRangeAxisIndex(axis);
/*      */       }
/*      */     }
/* 1115 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisLocation getRangeAxisLocation()
/*      */   {
/* 1124 */     return getRangeAxisLocation(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisLocation getRangeAxisLocation(int index)
/*      */   {
/* 1137 */     AxisLocation result = null;
/* 1138 */     if (index < this.rangeAxisLocations.size()) {
/* 1139 */       result = (AxisLocation)this.rangeAxisLocations.get(index);
/*      */     }
/* 1141 */     if (result == null) {
/* 1142 */       result = AxisLocation.getOpposite(getRangeAxisLocation(0));
/*      */     }
/* 1144 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxisLocation(AxisLocation location)
/*      */   {
/* 1158 */     setRangeAxisLocation(location, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxisLocation(AxisLocation location, boolean notify)
/*      */   {
/* 1171 */     setRangeAxisLocation(0, location, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxisLocation(int index, AxisLocation location)
/*      */   {
/* 1185 */     setRangeAxisLocation(index, location, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxisLocation(int index, AxisLocation location, boolean notify)
/*      */   {
/* 1201 */     if ((index == 0) && (location == null)) {
/* 1202 */       throw new IllegalArgumentException("Null 'location' for index 0 not permitted.");
/*      */     }
/*      */     
/* 1205 */     this.rangeAxisLocations.set(index, location);
/* 1206 */     if (notify) {
/* 1207 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getRangeAxisEdge()
/*      */   {
/* 1217 */     return getRangeAxisEdge(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getRangeAxisEdge(int index)
/*      */   {
/* 1228 */     AxisLocation location = getRangeAxisLocation(index);
/* 1229 */     RectangleEdge result = Plot.resolveRangeAxisLocation(location, this.orientation);
/*      */     
/* 1231 */     if (result == null) {
/* 1232 */       result = RectangleEdge.opposite(getRangeAxisEdge(0));
/*      */     }
/* 1234 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRangeAxisCount()
/*      */   {
/* 1243 */     return this.rangeAxes.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeAxes()
/*      */   {
/* 1251 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 1252 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 1253 */       if (axis != null) {
/* 1254 */         axis.removeChangeListener(this);
/*      */       }
/*      */     }
/* 1257 */     this.rangeAxes.clear();
/* 1258 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void configureRangeAxes()
/*      */   {
/* 1265 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 1266 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 1267 */       if (axis != null) {
/* 1268 */         axis.configure();
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
/*      */   public CategoryDataset getDataset()
/*      */   {
/* 1281 */     return getDataset(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryDataset getDataset(int index)
/*      */   {
/* 1294 */     CategoryDataset result = null;
/* 1295 */     if (this.datasets.size() > index) {
/* 1296 */       result = (CategoryDataset)this.datasets.get(index);
/*      */     }
/* 1298 */     return result;
/*      */   }
/*      */   
/*      */ 
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
/* 1313 */     setDataset(0, dataset);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataset(int index, CategoryDataset dataset)
/*      */   {
/* 1326 */     CategoryDataset existing = (CategoryDataset)this.datasets.get(index);
/* 1327 */     if (existing != null) {
/* 1328 */       existing.removeChangeListener(this);
/*      */     }
/* 1330 */     this.datasets.set(index, dataset);
/* 1331 */     if (dataset != null) {
/* 1332 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/* 1336 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/* 1337 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDatasetCount()
/*      */   {
/* 1349 */     return this.datasets.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int indexOf(CategoryDataset dataset)
/*      */   {
/* 1363 */     int result = -1;
/* 1364 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1365 */       if (dataset == this.datasets.get(i)) {
/* 1366 */         result = i;
/* 1367 */         break;
/*      */       }
/*      */     }
/* 1370 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mapDatasetToDomainAxis(int index, int axisIndex)
/*      */   {
/* 1382 */     List axisIndices = new ArrayList(1);
/* 1383 */     axisIndices.add(new Integer(axisIndex));
/* 1384 */     mapDatasetToDomainAxes(index, axisIndices);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mapDatasetToDomainAxes(int index, List axisIndices)
/*      */   {
/* 1398 */     if (index < 0) {
/* 1399 */       throw new IllegalArgumentException("Requires 'index' >= 0.");
/*      */     }
/* 1401 */     checkAxisIndices(axisIndices);
/* 1402 */     Integer key = new Integer(index);
/* 1403 */     this.datasetToDomainAxesMap.put(key, new ArrayList(axisIndices));
/*      */     
/* 1405 */     datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void checkAxisIndices(List indices)
/*      */   {
/* 1419 */     if (indices == null) {
/* 1420 */       return;
/*      */     }
/* 1422 */     int count = indices.size();
/* 1423 */     if (count == 0) {
/* 1424 */       throw new IllegalArgumentException("Empty list not permitted.");
/*      */     }
/* 1426 */     HashSet set = new HashSet();
/* 1427 */     for (int i = 0; i < count; i++) {
/* 1428 */       Object item = indices.get(i);
/* 1429 */       if (!(item instanceof Integer)) {
/* 1430 */         throw new IllegalArgumentException("Indices must be Integer instances.");
/*      */       }
/*      */       
/* 1433 */       if (set.contains(item)) {
/* 1434 */         throw new IllegalArgumentException("Indices must be unique.");
/*      */       }
/* 1436 */       set.add(item);
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
/*      */   public CategoryAxis getDomainAxisForDataset(int index)
/*      */   {
/* 1451 */     if (index < 0) {
/* 1452 */       throw new IllegalArgumentException("Negative 'index'.");
/*      */     }
/* 1454 */     CategoryAxis axis = null;
/* 1455 */     List axisIndices = (List)this.datasetToDomainAxesMap.get(new Integer(index));
/*      */     
/* 1457 */     if (axisIndices != null)
/*      */     {
/* 1459 */       Integer axisIndex = (Integer)axisIndices.get(0);
/* 1460 */       axis = getDomainAxis(axisIndex.intValue());
/*      */     }
/*      */     else {
/* 1463 */       axis = getDomainAxis(0);
/*      */     }
/* 1465 */     return axis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mapDatasetToRangeAxis(int index, int axisIndex)
/*      */   {
/* 1477 */     List axisIndices = new ArrayList(1);
/* 1478 */     axisIndices.add(new Integer(axisIndex));
/* 1479 */     mapDatasetToRangeAxes(index, axisIndices);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mapDatasetToRangeAxes(int index, List axisIndices)
/*      */   {
/* 1493 */     if (index < 0) {
/* 1494 */       throw new IllegalArgumentException("Requires 'index' >= 0.");
/*      */     }
/* 1496 */     checkAxisIndices(axisIndices);
/* 1497 */     Integer key = new Integer(index);
/* 1498 */     this.datasetToRangeAxesMap.put(key, new ArrayList(axisIndices));
/*      */     
/* 1500 */     datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getRangeAxisForDataset(int index)
/*      */   {
/* 1514 */     if (index < 0) {
/* 1515 */       throw new IllegalArgumentException("Negative 'index'.");
/*      */     }
/* 1517 */     ValueAxis axis = null;
/* 1518 */     List axisIndices = (List)this.datasetToRangeAxesMap.get(new Integer(index));
/*      */     
/* 1520 */     if (axisIndices != null)
/*      */     {
/* 1522 */       Integer axisIndex = (Integer)axisIndices.get(0);
/* 1523 */       axis = getRangeAxis(axisIndex.intValue());
/*      */     }
/*      */     else {
/* 1526 */       axis = getRangeAxis(0);
/*      */     }
/* 1528 */     return axis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRendererCount()
/*      */   {
/* 1539 */     return this.renderers.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryItemRenderer getRenderer()
/*      */   {
/* 1550 */     return getRenderer(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryItemRenderer getRenderer(int index)
/*      */   {
/* 1563 */     CategoryItemRenderer result = null;
/* 1564 */     if (this.renderers.size() > index) {
/* 1565 */       result = (CategoryItemRenderer)this.renderers.get(index);
/*      */     }
/* 1567 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(CategoryItemRenderer renderer)
/*      */   {
/* 1580 */     setRenderer(0, renderer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(CategoryItemRenderer renderer, boolean notify)
/*      */   {
/* 1601 */     setRenderer(0, renderer, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(int index, CategoryItemRenderer renderer)
/*      */   {
/* 1615 */     setRenderer(index, renderer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(int index, CategoryItemRenderer renderer, boolean notify)
/*      */   {
/* 1632 */     CategoryItemRenderer existing = (CategoryItemRenderer)this.renderers.get(index);
/*      */     
/* 1634 */     if (existing != null) {
/* 1635 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/* 1639 */     this.renderers.set(index, renderer);
/* 1640 */     if (renderer != null) {
/* 1641 */       renderer.setPlot(this);
/* 1642 */       renderer.addChangeListener(this);
/*      */     }
/*      */     
/* 1645 */     configureDomainAxes();
/* 1646 */     configureRangeAxes();
/*      */     
/* 1648 */     if (notify) {
/* 1649 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderers(CategoryItemRenderer[] renderers)
/*      */   {
/* 1660 */     for (int i = 0; i < renderers.length; i++) {
/* 1661 */       setRenderer(i, renderers[i], false);
/*      */     }
/* 1663 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryItemRenderer getRendererForDataset(CategoryDataset dataset)
/*      */   {
/* 1675 */     CategoryItemRenderer result = null;
/* 1676 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1677 */       if (this.datasets.get(i) == dataset) {
/* 1678 */         result = (CategoryItemRenderer)this.renderers.get(i);
/* 1679 */         break;
/*      */       }
/*      */     }
/* 1682 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getIndexOf(CategoryItemRenderer renderer)
/*      */   {
/* 1694 */     return this.renderers.indexOf(renderer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DatasetRenderingOrder getDatasetRenderingOrder()
/*      */   {
/* 1705 */     return this.renderingOrder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDatasetRenderingOrder(DatasetRenderingOrder order)
/*      */   {
/* 1719 */     if (order == null) {
/* 1720 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/* 1722 */     this.renderingOrder = order;
/* 1723 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SortOrder getColumnRenderingOrder()
/*      */   {
/* 1735 */     return this.columnRenderingOrder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setColumnRenderingOrder(SortOrder order)
/*      */   {
/* 1750 */     if (order == null) {
/* 1751 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/* 1753 */     this.columnRenderingOrder = order;
/* 1754 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SortOrder getRowRenderingOrder()
/*      */   {
/* 1766 */     return this.rowRenderingOrder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRowRenderingOrder(SortOrder order)
/*      */   {
/* 1781 */     if (order == null) {
/* 1782 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/* 1784 */     this.rowRenderingOrder = order;
/* 1785 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainGridlinesVisible()
/*      */   {
/* 1796 */     return this.domainGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainGridlinesVisible(boolean visible)
/*      */   {
/* 1811 */     if (this.domainGridlinesVisible != visible) {
/* 1812 */       this.domainGridlinesVisible = visible;
/* 1813 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryAnchor getDomainGridlinePosition()
/*      */   {
/* 1825 */     return this.domainGridlinePosition;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainGridlinePosition(CategoryAnchor position)
/*      */   {
/* 1837 */     if (position == null) {
/* 1838 */       throw new IllegalArgumentException("Null 'position' argument.");
/*      */     }
/* 1840 */     this.domainGridlinePosition = position;
/* 1841 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getDomainGridlineStroke()
/*      */   {
/* 1852 */     return this.domainGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainGridlineStroke(Stroke stroke)
/*      */   {
/* 1864 */     if (stroke == null) {
/* 1865 */       throw new IllegalArgumentException("Null 'stroke' not permitted.");
/*      */     }
/* 1867 */     this.domainGridlineStroke = stroke;
/* 1868 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDomainGridlinePaint()
/*      */   {
/* 1879 */     return this.domainGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainGridlinePaint(Paint paint)
/*      */   {
/* 1891 */     if (paint == null) {
/* 1892 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1894 */     this.domainGridlinePaint = paint;
/* 1895 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZeroBaselineVisible()
/*      */   {
/* 1909 */     return this.rangeZeroBaselineVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeZeroBaselineVisible(boolean visible)
/*      */   {
/* 1924 */     this.rangeZeroBaselineVisible = visible;
/* 1925 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getRangeZeroBaselineStroke()
/*      */   {
/* 1938 */     return this.rangeZeroBaselineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeZeroBaselineStroke(Stroke stroke)
/*      */   {
/* 1952 */     if (stroke == null) {
/* 1953 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1955 */     this.rangeZeroBaselineStroke = stroke;
/* 1956 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeZeroBaselinePaint()
/*      */   {
/* 1970 */     return this.rangeZeroBaselinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeZeroBaselinePaint(Paint paint)
/*      */   {
/* 1984 */     if (paint == null) {
/* 1985 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1987 */     this.rangeZeroBaselinePaint = paint;
/* 1988 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeGridlinesVisible()
/*      */   {
/* 1999 */     return this.rangeGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeGridlinesVisible(boolean visible)
/*      */   {
/* 2012 */     if (this.rangeGridlinesVisible != visible) {
/* 2013 */       this.rangeGridlinesVisible = visible;
/* 2014 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getRangeGridlineStroke()
/*      */   {
/* 2026 */     return this.rangeGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeGridlineStroke(Stroke stroke)
/*      */   {
/* 2038 */     if (stroke == null) {
/* 2039 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 2041 */     this.rangeGridlineStroke = stroke;
/* 2042 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeGridlinePaint()
/*      */   {
/* 2053 */     return this.rangeGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeGridlinePaint(Paint paint)
/*      */   {
/* 2065 */     if (paint == null) {
/* 2066 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2068 */     this.rangeGridlinePaint = paint;
/* 2069 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeMinorGridlinesVisible()
/*      */   {
/* 2083 */     return this.rangeMinorGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeMinorGridlinesVisible(boolean visible)
/*      */   {
/* 2100 */     if (this.rangeMinorGridlinesVisible != visible) {
/* 2101 */       this.rangeMinorGridlinesVisible = visible;
/* 2102 */       fireChangeEvent();
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
/*      */   public Stroke getRangeMinorGridlineStroke()
/*      */   {
/* 2117 */     return this.rangeMinorGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeMinorGridlineStroke(Stroke stroke)
/*      */   {
/* 2131 */     if (stroke == null) {
/* 2132 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 2134 */     this.rangeMinorGridlineStroke = stroke;
/* 2135 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeMinorGridlinePaint()
/*      */   {
/* 2149 */     return this.rangeMinorGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeMinorGridlinePaint(Paint paint)
/*      */   {
/* 2163 */     if (paint == null) {
/* 2164 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2166 */     this.rangeMinorGridlinePaint = paint;
/* 2167 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItemCollection getFixedLegendItems()
/*      */   {
/* 2178 */     return this.fixedLegendItems;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedLegendItems(LegendItemCollection items)
/*      */   {
/* 2191 */     this.fixedLegendItems = items;
/* 2192 */     fireChangeEvent();
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
/* 2203 */     LegendItemCollection result = this.fixedLegendItems;
/* 2204 */     if (result == null) {
/* 2205 */       result = new LegendItemCollection();
/*      */       
/* 2207 */       int count = this.datasets.size();
/* 2208 */       for (int datasetIndex = 0; datasetIndex < count; datasetIndex++) {
/* 2209 */         CategoryDataset dataset = getDataset(datasetIndex);
/* 2210 */         if (dataset != null) {
/* 2211 */           CategoryItemRenderer renderer = getRenderer(datasetIndex);
/* 2212 */           if (renderer != null) {
/* 2213 */             int seriesCount = dataset.getRowCount();
/* 2214 */             for (int i = 0; i < seriesCount; i++) {
/* 2215 */               LegendItem item = renderer.getLegendItem(datasetIndex, i);
/*      */               
/* 2217 */               if (item != null) {
/* 2218 */                 result.add(item);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2225 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info)
/*      */   {
/* 2238 */     Rectangle2D dataArea = info.getDataArea();
/* 2239 */     if (dataArea.contains(x, y))
/*      */     {
/* 2241 */       double java2D = 0.0D;
/* 2242 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2243 */         java2D = x;
/*      */       }
/* 2245 */       else if (this.orientation == PlotOrientation.VERTICAL) {
/* 2246 */         java2D = y;
/*      */       }
/* 2248 */       RectangleEdge edge = Plot.resolveRangeAxisLocation(getRangeAxisLocation(), this.orientation);
/*      */       
/* 2250 */       double value = getRangeAxis().java2DToValue(java2D, info.getDataArea(), edge);
/*      */       
/* 2252 */       setAnchorValue(value);
/* 2253 */       setRangeCrosshairValue(value);
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
/*      */   public void zoom(double percent)
/*      */   {
/* 2269 */     if (percent > 0.0D) {
/* 2270 */       double range = getRangeAxis().getRange().getLength();
/* 2271 */       double scaledRange = range * percent;
/* 2272 */       getRangeAxis().setRange(this.anchorValue - scaledRange / 2.0D, this.anchorValue + scaledRange / 2.0D);
/*      */     }
/*      */     else
/*      */     {
/* 2276 */       getRangeAxis().setAutoRange(true);
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
/*      */   public void datasetChanged(DatasetChangeEvent event)
/*      */   {
/* 2290 */     int count = this.rangeAxes.size();
/* 2291 */     for (int axisIndex = 0; axisIndex < count; axisIndex++) {
/* 2292 */       ValueAxis yAxis = getRangeAxis(axisIndex);
/* 2293 */       if (yAxis != null) {
/* 2294 */         yAxis.configure();
/*      */       }
/*      */     }
/* 2297 */     if (getParent() != null) {
/* 2298 */       getParent().datasetChanged(event);
/*      */     }
/*      */     else {
/* 2301 */       PlotChangeEvent e = new PlotChangeEvent(this);
/* 2302 */       e.setType(ChartChangeEventType.DATASET_UPDATED);
/* 2303 */       notifyListeners(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void rendererChanged(RendererChangeEvent event)
/*      */   {
/* 2314 */     Plot parent = getParent();
/* 2315 */     if (parent != null) {
/* 2316 */       if ((parent instanceof RendererChangeListener)) {
/* 2317 */         RendererChangeListener rcl = (RendererChangeListener)parent;
/* 2318 */         rcl.rendererChanged(event);
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 2323 */         throw new RuntimeException("The renderer has changed and I don't know what to do!");
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2328 */       configureRangeAxes();
/* 2329 */       PlotChangeEvent e = new PlotChangeEvent(this);
/* 2330 */       notifyListeners(e);
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
/*      */   public void addDomainMarker(CategoryMarker marker)
/*      */   {
/* 2345 */     addDomainMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(CategoryMarker marker, Layer layer)
/*      */   {
/* 2361 */     addDomainMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(int index, CategoryMarker marker, Layer layer)
/*      */   {
/* 2378 */     addDomainMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(int index, CategoryMarker marker, Layer layer, boolean notify)
/*      */   {
/* 2399 */     if (marker == null) {
/* 2400 */       throw new IllegalArgumentException("Null 'marker' not permitted.");
/*      */     }
/* 2402 */     if (layer == null) {
/* 2403 */       throw new IllegalArgumentException("Null 'layer' not permitted.");
/*      */     }
/*      */     
/* 2406 */     if (layer == Layer.FOREGROUND) {
/* 2407 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(new Integer(index));
/*      */       
/* 2409 */       if (markers == null) {
/* 2410 */         markers = new ArrayList();
/* 2411 */         this.foregroundDomainMarkers.put(new Integer(index), markers);
/*      */       }
/* 2413 */       markers.add(marker);
/*      */     }
/* 2415 */     else if (layer == Layer.BACKGROUND) {
/* 2416 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(new Integer(index));
/*      */       
/* 2418 */       if (markers == null) {
/* 2419 */         markers = new ArrayList();
/* 2420 */         this.backgroundDomainMarkers.put(new Integer(index), markers);
/*      */       }
/* 2422 */       markers.add(marker);
/*      */     }
/* 2424 */     marker.addChangeListener(this);
/* 2425 */     if (notify) {
/* 2426 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainMarkers()
/*      */   {
/* 2437 */     if (this.backgroundDomainMarkers != null) {
/* 2438 */       Set keys = this.backgroundDomainMarkers.keySet();
/* 2439 */       Iterator iterator = keys.iterator();
/* 2440 */       while (iterator.hasNext()) {
/* 2441 */         Integer key = (Integer)iterator.next();
/* 2442 */         clearDomainMarkers(key.intValue());
/*      */       }
/* 2444 */       this.backgroundDomainMarkers.clear();
/*      */     }
/* 2446 */     if (this.foregroundDomainMarkers != null) {
/* 2447 */       Set keys = this.foregroundDomainMarkers.keySet();
/* 2448 */       Iterator iterator = keys.iterator();
/* 2449 */       while (iterator.hasNext()) {
/* 2450 */         Integer key = (Integer)iterator.next();
/* 2451 */         clearDomainMarkers(key.intValue());
/*      */       }
/* 2453 */       this.foregroundDomainMarkers.clear();
/*      */     }
/* 2455 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection getDomainMarkers(Layer layer)
/*      */   {
/* 2466 */     return getDomainMarkers(0, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection getDomainMarkers(int index, Layer layer)
/*      */   {
/* 2479 */     Collection result = null;
/* 2480 */     Integer key = new Integer(index);
/* 2481 */     if (layer == Layer.FOREGROUND) {
/* 2482 */       result = (Collection)this.foregroundDomainMarkers.get(key);
/*      */     }
/* 2484 */     else if (layer == Layer.BACKGROUND) {
/* 2485 */       result = (Collection)this.backgroundDomainMarkers.get(key);
/*      */     }
/* 2487 */     if (result != null) {
/* 2488 */       result = Collections.unmodifiableCollection(result);
/*      */     }
/* 2490 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainMarkers(int index)
/*      */   {
/* 2501 */     Integer key = new Integer(index);
/* 2502 */     if (this.backgroundDomainMarkers != null) {
/* 2503 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(key);
/*      */       
/* 2505 */       if (markers != null) {
/* 2506 */         Iterator iterator = markers.iterator();
/* 2507 */         while (iterator.hasNext()) {
/* 2508 */           Marker m = (Marker)iterator.next();
/* 2509 */           m.removeChangeListener(this);
/*      */         }
/* 2511 */         markers.clear();
/*      */       }
/*      */     }
/* 2514 */     if (this.foregroundDomainMarkers != null) {
/* 2515 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(key);
/*      */       
/* 2517 */       if (markers != null) {
/* 2518 */         Iterator iterator = markers.iterator();
/* 2519 */         while (iterator.hasNext()) {
/* 2520 */           Marker m = (Marker)iterator.next();
/* 2521 */           m.removeChangeListener(this);
/*      */         }
/* 2523 */         markers.clear();
/*      */       }
/*      */     }
/* 2526 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeDomainMarker(Marker marker)
/*      */   {
/* 2541 */     return removeDomainMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeDomainMarker(Marker marker, Layer layer)
/*      */   {
/* 2557 */     return removeDomainMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeDomainMarker(int index, Marker marker, Layer layer)
/*      */   {
/* 2574 */     return removeDomainMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeDomainMarker(int index, Marker marker, Layer layer, boolean notify)
/*      */   {
/*      */     ArrayList markers;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */     ArrayList markers;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2594 */     if (layer == Layer.FOREGROUND) {
/* 2595 */       markers = (ArrayList)this.foregroundDomainMarkers.get(new Integer(index));
/*      */     }
/*      */     else
/*      */     {
/* 2599 */       markers = (ArrayList)this.backgroundDomainMarkers.get(new Integer(index));
/*      */     }
/*      */     
/* 2602 */     if (markers == null) {
/* 2603 */       return false;
/*      */     }
/* 2605 */     boolean removed = markers.remove(marker);
/* 2606 */     if ((removed) && (notify)) {
/* 2607 */       fireChangeEvent();
/*      */     }
/* 2609 */     return removed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addRangeMarker(Marker marker)
/*      */   {
/* 2623 */     addRangeMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addRangeMarker(Marker marker, Layer layer)
/*      */   {
/* 2639 */     addRangeMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addRangeMarker(int index, Marker marker, Layer layer)
/*      */   {
/* 2656 */     addRangeMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addRangeMarker(int index, Marker marker, Layer layer, boolean notify)
/*      */   {
/* 2678 */     if (layer == Layer.FOREGROUND) {
/* 2679 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(new Integer(index));
/*      */       
/* 2681 */       if (markers == null) {
/* 2682 */         markers = new ArrayList();
/* 2683 */         this.foregroundRangeMarkers.put(new Integer(index), markers);
/*      */       }
/* 2685 */       markers.add(marker);
/*      */     }
/* 2687 */     else if (layer == Layer.BACKGROUND) {
/* 2688 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(new Integer(index));
/*      */       
/* 2690 */       if (markers == null) {
/* 2691 */         markers = new ArrayList();
/* 2692 */         this.backgroundRangeMarkers.put(new Integer(index), markers);
/*      */       }
/* 2694 */       markers.add(marker);
/*      */     }
/* 2696 */     marker.addChangeListener(this);
/* 2697 */     if (notify) {
/* 2698 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeMarkers()
/*      */   {
/* 2709 */     if (this.backgroundRangeMarkers != null) {
/* 2710 */       Set keys = this.backgroundRangeMarkers.keySet();
/* 2711 */       Iterator iterator = keys.iterator();
/* 2712 */       while (iterator.hasNext()) {
/* 2713 */         Integer key = (Integer)iterator.next();
/* 2714 */         clearRangeMarkers(key.intValue());
/*      */       }
/* 2716 */       this.backgroundRangeMarkers.clear();
/*      */     }
/* 2718 */     if (this.foregroundRangeMarkers != null) {
/* 2719 */       Set keys = this.foregroundRangeMarkers.keySet();
/* 2720 */       Iterator iterator = keys.iterator();
/* 2721 */       while (iterator.hasNext()) {
/* 2722 */         Integer key = (Integer)iterator.next();
/* 2723 */         clearRangeMarkers(key.intValue());
/*      */       }
/* 2725 */       this.foregroundRangeMarkers.clear();
/*      */     }
/* 2727 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection getRangeMarkers(Layer layer)
/*      */   {
/* 2740 */     return getRangeMarkers(0, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Collection getRangeMarkers(int index, Layer layer)
/*      */   {
/* 2753 */     Collection result = null;
/* 2754 */     Integer key = new Integer(index);
/* 2755 */     if (layer == Layer.FOREGROUND) {
/* 2756 */       result = (Collection)this.foregroundRangeMarkers.get(key);
/*      */     }
/* 2758 */     else if (layer == Layer.BACKGROUND) {
/* 2759 */       result = (Collection)this.backgroundRangeMarkers.get(key);
/*      */     }
/* 2761 */     if (result != null) {
/* 2762 */       result = Collections.unmodifiableCollection(result);
/*      */     }
/* 2764 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeMarkers(int index)
/*      */   {
/* 2775 */     Integer key = new Integer(index);
/* 2776 */     if (this.backgroundRangeMarkers != null) {
/* 2777 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(key);
/*      */       
/* 2779 */       if (markers != null) {
/* 2780 */         Iterator iterator = markers.iterator();
/* 2781 */         while (iterator.hasNext()) {
/* 2782 */           Marker m = (Marker)iterator.next();
/* 2783 */           m.removeChangeListener(this);
/*      */         }
/* 2785 */         markers.clear();
/*      */       }
/*      */     }
/* 2788 */     if (this.foregroundRangeMarkers != null) {
/* 2789 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(key);
/*      */       
/* 2791 */       if (markers != null) {
/* 2792 */         Iterator iterator = markers.iterator();
/* 2793 */         while (iterator.hasNext()) {
/* 2794 */           Marker m = (Marker)iterator.next();
/* 2795 */           m.removeChangeListener(this);
/*      */         }
/* 2797 */         markers.clear();
/*      */       }
/*      */     }
/* 2800 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeRangeMarker(Marker marker)
/*      */   {
/* 2817 */     return removeRangeMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeRangeMarker(Marker marker, Layer layer)
/*      */   {
/* 2835 */     return removeRangeMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeRangeMarker(int index, Marker marker, Layer layer)
/*      */   {
/* 2854 */     return removeRangeMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeRangeMarker(int index, Marker marker, Layer layer, boolean notify)
/*      */   {
/* 2875 */     if (marker == null)
/* 2876 */       throw new IllegalArgumentException("Null 'marker' argument.");
/*      */     ArrayList markers;
/*      */     ArrayList markers;
/* 2879 */     if (layer == Layer.FOREGROUND) {
/* 2880 */       markers = (ArrayList)this.foregroundRangeMarkers.get(new Integer(index));
/*      */     }
/*      */     else
/*      */     {
/* 2884 */       markers = (ArrayList)this.backgroundRangeMarkers.get(new Integer(index));
/*      */     }
/*      */     
/* 2887 */     if (markers == null) {
/* 2888 */       return false;
/*      */     }
/* 2890 */     boolean removed = markers.remove(marker);
/* 2891 */     if ((removed) && (notify)) {
/* 2892 */       fireChangeEvent();
/*      */     }
/* 2894 */     return removed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainCrosshairVisible()
/*      */   {
/* 2908 */     return this.domainCrosshairVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairVisible(boolean flag)
/*      */   {
/* 2924 */     if (this.domainCrosshairVisible != flag) {
/* 2925 */       this.domainCrosshairVisible = flag;
/* 2926 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Comparable getDomainCrosshairRowKey()
/*      */   {
/* 2938 */     return this.domainCrosshairRowKey;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairRowKey(Comparable key)
/*      */   {
/* 2950 */     setDomainCrosshairRowKey(key, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairRowKey(Comparable key, boolean notify)
/*      */   {
/* 2963 */     this.domainCrosshairRowKey = key;
/* 2964 */     if (notify) {
/* 2965 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Comparable getDomainCrosshairColumnKey()
/*      */   {
/* 2977 */     return this.domainCrosshairColumnKey;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairColumnKey(Comparable key)
/*      */   {
/* 2989 */     setDomainCrosshairColumnKey(key, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairColumnKey(Comparable key, boolean notify)
/*      */   {
/* 3002 */     this.domainCrosshairColumnKey = key;
/* 3003 */     if (notify) {
/* 3004 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getCrosshairDatasetIndex()
/*      */   {
/* 3016 */     return this.crosshairDatasetIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCrosshairDatasetIndex(int index)
/*      */   {
/* 3028 */     setCrosshairDatasetIndex(index, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCrosshairDatasetIndex(int index, boolean notify)
/*      */   {
/* 3041 */     this.crosshairDatasetIndex = index;
/* 3042 */     if (notify) {
/* 3043 */       fireChangeEvent();
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
/*      */   public Paint getDomainCrosshairPaint()
/*      */   {
/* 3058 */     return this.domainCrosshairPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairPaint(Paint paint)
/*      */   {
/* 3071 */     if (paint == null) {
/* 3072 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 3074 */     this.domainCrosshairPaint = paint;
/* 3075 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getDomainCrosshairStroke()
/*      */   {
/* 3089 */     return this.domainCrosshairStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairStroke(Stroke stroke)
/*      */   {
/* 3103 */     if (stroke == null) {
/* 3104 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 3106 */     this.domainCrosshairStroke = stroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeCrosshairVisible()
/*      */   {
/* 3117 */     return this.rangeCrosshairVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairVisible(boolean flag)
/*      */   {
/* 3128 */     if (this.rangeCrosshairVisible != flag) {
/* 3129 */       this.rangeCrosshairVisible = flag;
/* 3130 */       fireChangeEvent();
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
/*      */   public boolean isRangeCrosshairLockedOnData()
/*      */   {
/* 3143 */     return this.rangeCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairLockedOnData(boolean flag)
/*      */   {
/* 3156 */     if (this.rangeCrosshairLockedOnData != flag) {
/* 3157 */       this.rangeCrosshairLockedOnData = flag;
/* 3158 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getRangeCrosshairValue()
/*      */   {
/* 3170 */     return this.rangeCrosshairValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairValue(double value)
/*      */   {
/* 3182 */     setRangeCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairValue(double value, boolean notify)
/*      */   {
/* 3197 */     this.rangeCrosshairValue = value;
/* 3198 */     if ((isRangeCrosshairVisible()) && (notify)) {
/* 3199 */       fireChangeEvent();
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
/*      */   public Stroke getRangeCrosshairStroke()
/*      */   {
/* 3214 */     return this.rangeCrosshairStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairStroke(Stroke stroke)
/*      */   {
/* 3228 */     if (stroke == null) {
/* 3229 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 3231 */     this.rangeCrosshairStroke = stroke;
/* 3232 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeCrosshairPaint()
/*      */   {
/* 3245 */     return this.rangeCrosshairPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairPaint(Paint paint)
/*      */   {
/* 3257 */     if (paint == null) {
/* 3258 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 3260 */     this.rangeCrosshairPaint = paint;
/* 3261 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getAnnotations()
/*      */   {
/* 3273 */     return this.annotations;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addAnnotation(CategoryAnnotation annotation)
/*      */   {
/* 3285 */     addAnnotation(annotation, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addAnnotation(CategoryAnnotation annotation, boolean notify)
/*      */   {
/* 3298 */     if (annotation == null) {
/* 3299 */       throw new IllegalArgumentException("Null 'annotation' argument.");
/*      */     }
/* 3301 */     this.annotations.add(annotation);
/* 3302 */     if (notify) {
/* 3303 */       fireChangeEvent();
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
/*      */   public boolean removeAnnotation(CategoryAnnotation annotation)
/*      */   {
/* 3318 */     return removeAnnotation(annotation, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeAnnotation(CategoryAnnotation annotation, boolean notify)
/*      */   {
/* 3334 */     if (annotation == null) {
/* 3335 */       throw new IllegalArgumentException("Null 'annotation' argument.");
/*      */     }
/* 3337 */     boolean removed = this.annotations.remove(annotation);
/* 3338 */     if ((removed) && (notify)) {
/* 3339 */       fireChangeEvent();
/*      */     }
/* 3341 */     return removed;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearAnnotations()
/*      */   {
/* 3349 */     this.annotations.clear();
/* 3350 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AxisSpace calculateDomainAxisSpace(Graphics2D g2, Rectangle2D plotArea, AxisSpace space)
/*      */   {
/* 3366 */     if (space == null) {
/* 3367 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/* 3371 */     if (this.fixedDomainAxisSpace != null) {
/* 3372 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3373 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getLeft(), RectangleEdge.LEFT);
/*      */         
/* 3375 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), RectangleEdge.RIGHT);
/*      */ 
/*      */       }
/* 3378 */       else if (this.orientation == PlotOrientation.VERTICAL) {
/* 3379 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), RectangleEdge.TOP);
/*      */         
/* 3381 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/*      */       }
/*      */       
/*      */     }
/*      */     else
/*      */     {
/* 3387 */       RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(getDomainAxisLocation(), this.orientation);
/*      */       
/* 3389 */       if (this.drawSharedDomainAxis) {
/* 3390 */         space = getDomainAxis().reserveSpace(g2, this, plotArea, domainEdge, space);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3395 */       for (int i = 0; i < this.domainAxes.size(); i++) {
/* 3396 */         Axis xAxis = (Axis)this.domainAxes.get(i);
/* 3397 */         if (xAxis != null) {
/* 3398 */           RectangleEdge edge = getDomainAxisEdge(i);
/* 3399 */           space = xAxis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3404 */     return space;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AxisSpace calculateRangeAxisSpace(Graphics2D g2, Rectangle2D plotArea, AxisSpace space)
/*      */   {
/* 3421 */     if (space == null) {
/* 3422 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/* 3426 */     if (this.fixedRangeAxisSpace != null) {
/* 3427 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3428 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), RectangleEdge.TOP);
/*      */         
/* 3430 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/*      */ 
/*      */       }
/* 3433 */       else if (this.orientation == PlotOrientation.VERTICAL) {
/* 3434 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), RectangleEdge.LEFT);
/*      */         
/* 3436 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), RectangleEdge.RIGHT);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else {
/* 3442 */       for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 3443 */         Axis yAxis = (Axis)this.rangeAxes.get(i);
/* 3444 */         if (yAxis != null) {
/* 3445 */           RectangleEdge edge = getRangeAxisEdge(i);
/* 3446 */           space = yAxis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         }
/*      */       }
/*      */     }
/* 3450 */     return space;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected AxisSpace calculateAxisSpace(Graphics2D g2, Rectangle2D plotArea)
/*      */   {
/* 3464 */     AxisSpace space = new AxisSpace();
/* 3465 */     space = calculateRangeAxisSpace(g2, plotArea, space);
/* 3466 */     space = calculateDomainAxisSpace(g2, plotArea, space);
/* 3467 */     return space;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo state)
/*      */   {
/* 3490 */     boolean b1 = area.getWidth() <= 10.0D;
/* 3491 */     boolean b2 = area.getHeight() <= 10.0D;
/* 3492 */     if ((b1) || (b2)) {
/* 3493 */       return;
/*      */     }
/*      */     
/*      */ 
/* 3497 */     if (state == null)
/*      */     {
/*      */ 
/*      */ 
/* 3501 */       state = new PlotRenderingInfo(null);
/*      */     }
/* 3503 */     state.setPlotArea(area);
/*      */     
/*      */ 
/* 3506 */     RectangleInsets insets = getInsets();
/* 3507 */     insets.trim(area);
/*      */     
/*      */ 
/* 3510 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 3511 */     Rectangle2D dataArea = space.shrink(area, null);
/* 3512 */     this.axisOffset.trim(dataArea);
/*      */     
/* 3514 */     state.setDataArea(dataArea);
/* 3515 */     createAndAddEntity((Rectangle2D)dataArea.clone(), state, null, null);
/*      */     
/*      */ 
/*      */ 
/* 3519 */     if (getRenderer() != null) {
/* 3520 */       getRenderer().drawBackground(g2, this, dataArea);
/*      */     }
/*      */     else {
/* 3523 */       drawBackground(g2, dataArea);
/*      */     }
/*      */     
/* 3526 */     Map axisStateMap = drawAxes(g2, area, dataArea, state);
/*      */     
/*      */ 
/*      */ 
/* 3530 */     if ((anchor != null) && (!dataArea.contains(anchor))) {
/* 3531 */       anchor = ShapeUtilities.getPointInRectangle(anchor.getX(), anchor.getY(), dataArea);
/*      */     }
/*      */     
/* 3534 */     CategoryCrosshairState crosshairState = new CategoryCrosshairState();
/* 3535 */     crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
/* 3536 */     crosshairState.setAnchor(anchor);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 3541 */     crosshairState.setAnchorX(NaN.0D);
/* 3542 */     crosshairState.setAnchorY(NaN.0D);
/* 3543 */     if (anchor != null) {
/* 3544 */       ValueAxis rangeAxis = getRangeAxis();
/* 3545 */       if (rangeAxis != null) { double y;
/*      */         double y;
/* 3547 */         if (getOrientation() == PlotOrientation.VERTICAL) {
/* 3548 */           y = rangeAxis.java2DToValue(anchor.getY(), dataArea, getRangeAxisEdge());
/*      */         }
/*      */         else
/*      */         {
/* 3552 */           y = rangeAxis.java2DToValue(anchor.getX(), dataArea, getRangeAxisEdge());
/*      */         }
/*      */         
/* 3555 */         crosshairState.setAnchorY(y);
/*      */       }
/*      */     }
/* 3558 */     crosshairState.setRowKey(getDomainCrosshairRowKey());
/* 3559 */     crosshairState.setColumnKey(getDomainCrosshairColumnKey());
/* 3560 */     crosshairState.setCrosshairY(getRangeCrosshairValue());
/*      */     
/*      */ 
/* 3563 */     Shape savedClip = g2.getClip();
/* 3564 */     g2.clip(dataArea);
/*      */     
/* 3566 */     drawDomainGridlines(g2, dataArea);
/*      */     
/* 3568 */     AxisState rangeAxisState = (AxisState)axisStateMap.get(getRangeAxis());
/* 3569 */     if ((rangeAxisState == null) && 
/* 3570 */       (parentState != null)) {
/* 3571 */       rangeAxisState = (AxisState)parentState.getSharedAxisStates().get(getRangeAxis());
/*      */     }
/*      */     
/*      */ 
/* 3575 */     if (rangeAxisState != null) {
/* 3576 */       drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
/* 3577 */       drawZeroRangeBaseline(g2, dataArea);
/*      */     }
/*      */     
/*      */ 
/* 3581 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3582 */       drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND);
/*      */     }
/* 3584 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3585 */       drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND);
/*      */     }
/*      */     
/*      */ 
/* 3589 */     boolean foundData = false;
/*      */     
/*      */ 
/* 3592 */     Composite originalComposite = g2.getComposite();
/* 3593 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*      */ 
/* 3596 */     DatasetRenderingOrder order = getDatasetRenderingOrder();
/* 3597 */     if (order == DatasetRenderingOrder.FORWARD) {
/* 3598 */       for (int i = 0; i < this.datasets.size(); i++) {
/* 3599 */         foundData = (render(g2, dataArea, i, state, crosshairState)) || (foundData);
/*      */       }
/*      */       
/*      */     }
/*      */     else {
/* 3604 */       for (int i = this.datasets.size() - 1; i >= 0; i--) {
/* 3605 */         foundData = (render(g2, dataArea, i, state, crosshairState)) || (foundData);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3610 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3611 */       drawDomainMarkers(g2, dataArea, i, Layer.FOREGROUND);
/*      */     }
/* 3613 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3614 */       drawRangeMarkers(g2, dataArea, i, Layer.FOREGROUND);
/*      */     }
/*      */     
/*      */ 
/* 3618 */     drawAnnotations(g2, dataArea);
/*      */     
/* 3620 */     g2.setClip(savedClip);
/* 3621 */     g2.setComposite(originalComposite);
/*      */     
/* 3623 */     if (!foundData) {
/* 3624 */       drawNoDataMessage(g2, dataArea);
/*      */     }
/*      */     
/* 3627 */     int datasetIndex = crosshairState.getDatasetIndex();
/* 3628 */     setCrosshairDatasetIndex(datasetIndex, false);
/*      */     
/*      */ 
/* 3631 */     Comparable rowKey = crosshairState.getRowKey();
/* 3632 */     Comparable columnKey = crosshairState.getColumnKey();
/* 3633 */     setDomainCrosshairRowKey(rowKey, false);
/* 3634 */     setDomainCrosshairColumnKey(columnKey, false);
/* 3635 */     if ((isDomainCrosshairVisible()) && (columnKey != null)) {
/* 3636 */       Paint paint = getDomainCrosshairPaint();
/* 3637 */       Stroke stroke = getDomainCrosshairStroke();
/* 3638 */       drawDomainCrosshair(g2, dataArea, this.orientation, datasetIndex, rowKey, columnKey, stroke, paint);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3643 */     ValueAxis yAxis = getRangeAxisForDataset(datasetIndex);
/* 3644 */     RectangleEdge yAxisEdge = getRangeAxisEdge();
/* 3645 */     if ((!this.rangeCrosshairLockedOnData) && (anchor != null)) { double yy;
/*      */       double yy;
/* 3647 */       if (getOrientation() == PlotOrientation.VERTICAL) {
/* 3648 */         yy = yAxis.java2DToValue(anchor.getY(), dataArea, yAxisEdge);
/*      */       }
/*      */       else {
/* 3651 */         yy = yAxis.java2DToValue(anchor.getX(), dataArea, yAxisEdge);
/*      */       }
/* 3653 */       crosshairState.setCrosshairY(yy);
/*      */     }
/* 3655 */     setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
/* 3656 */     if (isRangeCrosshairVisible()) {
/* 3657 */       double y = getRangeCrosshairValue();
/* 3658 */       Paint paint = getRangeCrosshairPaint();
/* 3659 */       Stroke stroke = getRangeCrosshairStroke();
/* 3660 */       drawRangeCrosshair(g2, dataArea, getOrientation(), y, yAxis, stroke, paint);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3665 */     if (isOutlineVisible()) {
/* 3666 */       if (getRenderer() != null) {
/* 3667 */         getRenderer().drawOutline(g2, this, dataArea);
/*      */       }
/*      */       else {
/* 3670 */         drawOutline(g2, dataArea);
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
/*      */   public void drawBackground(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 3687 */     fillBackground(g2, area, this.orientation);
/* 3688 */     drawBackgroundImage(g2, area);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Map drawAxes(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, PlotRenderingInfo plotState)
/*      */   {
/* 3707 */     AxisCollection axisCollection = new AxisCollection();
/*      */     
/*      */ 
/* 3710 */     for (int index = 0; index < this.domainAxes.size(); index++) {
/* 3711 */       CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(index);
/* 3712 */       if (xAxis != null) {
/* 3713 */         axisCollection.add(xAxis, getDomainAxisEdge(index));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3718 */     for (int index = 0; index < this.rangeAxes.size(); index++) {
/* 3719 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(index);
/* 3720 */       if (yAxis != null) {
/* 3721 */         axisCollection.add(yAxis, getRangeAxisEdge(index));
/*      */       }
/*      */     }
/*      */     
/* 3725 */     Map axisStateMap = new HashMap();
/*      */     
/*      */ 
/* 3728 */     double cursor = dataArea.getMinY() - this.axisOffset.calculateTopOutset(dataArea.getHeight());
/*      */     
/* 3730 */     Iterator iterator = axisCollection.getAxesAtTop().iterator();
/* 3731 */     while (iterator.hasNext()) {
/* 3732 */       Axis axis = (Axis)iterator.next();
/* 3733 */       if (axis != null) {
/* 3734 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.TOP, plotState);
/*      */         
/* 3736 */         cursor = axisState.getCursor();
/* 3737 */         axisStateMap.put(axis, axisState);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3742 */     cursor = dataArea.getMaxY() + this.axisOffset.calculateBottomOutset(dataArea.getHeight());
/*      */     
/* 3744 */     iterator = axisCollection.getAxesAtBottom().iterator();
/* 3745 */     while (iterator.hasNext()) {
/* 3746 */       Axis axis = (Axis)iterator.next();
/* 3747 */       if (axis != null) {
/* 3748 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.BOTTOM, plotState);
/*      */         
/* 3750 */         cursor = axisState.getCursor();
/* 3751 */         axisStateMap.put(axis, axisState);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3756 */     cursor = dataArea.getMinX() - this.axisOffset.calculateLeftOutset(dataArea.getWidth());
/*      */     
/* 3758 */     iterator = axisCollection.getAxesAtLeft().iterator();
/* 3759 */     while (iterator.hasNext()) {
/* 3760 */       Axis axis = (Axis)iterator.next();
/* 3761 */       if (axis != null) {
/* 3762 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.LEFT, plotState);
/*      */         
/* 3764 */         cursor = axisState.getCursor();
/* 3765 */         axisStateMap.put(axis, axisState);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3770 */     cursor = dataArea.getMaxX() + this.axisOffset.calculateRightOutset(dataArea.getWidth());
/*      */     
/* 3772 */     iterator = axisCollection.getAxesAtRight().iterator();
/* 3773 */     while (iterator.hasNext()) {
/* 3774 */       Axis axis = (Axis)iterator.next();
/* 3775 */       if (axis != null) {
/* 3776 */         AxisState axisState = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.RIGHT, plotState);
/*      */         
/* 3778 */         cursor = axisState.getCursor();
/* 3779 */         axisStateMap.put(axis, axisState);
/*      */       }
/*      */     }
/*      */     
/* 3783 */     return axisStateMap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean render(Graphics2D g2, Rectangle2D dataArea, int index, PlotRenderingInfo info, CategoryCrosshairState crosshairState)
/*      */   {
/* 3805 */     boolean foundData = false;
/* 3806 */     CategoryDataset currentDataset = getDataset(index);
/* 3807 */     CategoryItemRenderer renderer = getRenderer(index);
/* 3808 */     CategoryAxis domainAxis = getDomainAxisForDataset(index);
/* 3809 */     ValueAxis rangeAxis = getRangeAxisForDataset(index);
/* 3810 */     boolean hasData = !DatasetUtilities.isEmptyOrNull(currentDataset);
/* 3811 */     if ((hasData) && (renderer != null))
/*      */     {
/* 3813 */       foundData = true;
/* 3814 */       CategoryItemRendererState state = renderer.initialise(g2, dataArea, this, index, info);
/*      */       
/* 3816 */       state.setCrosshairState(crosshairState);
/* 3817 */       int columnCount = currentDataset.getColumnCount();
/* 3818 */       int rowCount = currentDataset.getRowCount();
/* 3819 */       int passCount = renderer.getPassCount();
/* 3820 */       for (int pass = 0; pass < passCount; pass++) {
/* 3821 */         if (this.columnRenderingOrder == SortOrder.ASCENDING) {
/* 3822 */           for (int column = 0; column < columnCount; column++) {
/* 3823 */             if (this.rowRenderingOrder == SortOrder.ASCENDING) {
/* 3824 */               for (int row = 0; row < rowCount; row++) {
/* 3825 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass);
/*      */               }
/*      */               
/*      */             }
/*      */             else
/*      */             {
/* 3831 */               for (int row = rowCount - 1; row >= 0; row--) {
/* 3832 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass);
/*      */               }
/*      */               
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         } else {
/* 3840 */           for (int column = columnCount - 1; column >= 0; column--) {
/* 3841 */             if (this.rowRenderingOrder == SortOrder.ASCENDING) {
/* 3842 */               for (int row = 0; row < rowCount; row++) {
/* 3843 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass);
/*      */               }
/*      */               
/*      */             }
/*      */             else
/*      */             {
/* 3849 */               for (int row = rowCount - 1; row >= 0; row--) {
/* 3850 */                 renderer.drawItem(g2, state, dataArea, this, domainAxis, rangeAxis, currentDataset, row, column, pass);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3859 */     return foundData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea)
/*      */   {
/* 3873 */     if (!isDomainGridlinesVisible()) {
/* 3874 */       return;
/*      */     }
/* 3876 */     CategoryAnchor anchor = getDomainGridlinePosition();
/* 3877 */     RectangleEdge domainAxisEdge = getDomainAxisEdge();
/* 3878 */     CategoryDataset dataset = getDataset();
/* 3879 */     if (dataset == null) {
/* 3880 */       return;
/*      */     }
/* 3882 */     CategoryAxis axis = getDomainAxis();
/* 3883 */     if (axis != null) {
/* 3884 */       int columnCount = dataset.getColumnCount();
/* 3885 */       for (int c = 0; c < columnCount; c++) {
/* 3886 */         double xx = axis.getCategoryJava2DCoordinate(anchor, c, columnCount, dataArea, domainAxisEdge);
/*      */         
/* 3888 */         CategoryItemRenderer renderer1 = getRenderer();
/* 3889 */         if (renderer1 != null) {
/* 3890 */           renderer1.drawDomainGridline(g2, this, dataArea, xx);
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
/*      */   protected void drawRangeGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks)
/*      */   {
/* 3908 */     if ((!isRangeGridlinesVisible()) && (!isRangeMinorGridlinesVisible())) {
/* 3909 */       return;
/*      */     }
/*      */     
/* 3912 */     ValueAxis axis = getRangeAxis();
/* 3913 */     if (axis == null) {
/* 3914 */       return;
/*      */     }
/*      */     
/* 3917 */     CategoryItemRenderer r = getRenderer();
/* 3918 */     if (r == null) {
/* 3919 */       return;
/*      */     }
/*      */     
/* 3922 */     Stroke gridStroke = null;
/* 3923 */     Paint gridPaint = null;
/* 3924 */     boolean paintLine = false;
/* 3925 */     Iterator iterator = ticks.iterator();
/* 3926 */     while (iterator.hasNext()) {
/* 3927 */       paintLine = false;
/* 3928 */       ValueTick tick = (ValueTick)iterator.next();
/* 3929 */       if ((tick.getTickType() == TickType.MINOR) && (isRangeMinorGridlinesVisible()))
/*      */       {
/* 3931 */         gridStroke = getRangeMinorGridlineStroke();
/* 3932 */         gridPaint = getRangeMinorGridlinePaint();
/* 3933 */         paintLine = true;
/*      */       }
/* 3935 */       else if ((tick.getTickType() == TickType.MAJOR) && (isRangeGridlinesVisible()))
/*      */       {
/* 3937 */         gridStroke = getRangeGridlineStroke();
/* 3938 */         gridPaint = getRangeGridlinePaint();
/* 3939 */         paintLine = true;
/*      */       }
/* 3941 */       if (((tick.getValue() != 0.0D) || (!isRangeZeroBaselineVisible())) && (paintLine))
/*      */       {
/*      */ 
/*      */ 
/* 3945 */         if ((r instanceof AbstractCategoryItemRenderer)) {
/* 3946 */           AbstractCategoryItemRenderer aci = (AbstractCategoryItemRenderer)r;
/*      */           
/* 3948 */           aci.drawRangeLine(g2, this, axis, dataArea, tick.getValue(), gridPaint, gridStroke);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*      */ 
/* 3954 */           r.drawRangeGridline(g2, this, axis, dataArea, tick.getValue());
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
/*      */   protected void drawZeroRangeBaseline(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 3972 */     if (!isRangeZeroBaselineVisible()) {
/* 3973 */       return;
/*      */     }
/* 3975 */     CategoryItemRenderer r = getRenderer();
/* 3976 */     if ((r instanceof AbstractCategoryItemRenderer)) {
/* 3977 */       AbstractCategoryItemRenderer aci = (AbstractCategoryItemRenderer)r;
/* 3978 */       aci.drawRangeLine(g2, this, getRangeAxis(), area, 0.0D, this.rangeZeroBaselinePaint, this.rangeZeroBaselineStroke);
/*      */     }
/*      */     else
/*      */     {
/* 3982 */       r.drawRangeGridline(g2, this, getRangeAxis(), area, 0.0D);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawAnnotations(Graphics2D g2, Rectangle2D dataArea)
/*      */   {
/* 3994 */     if (getAnnotations() != null) {
/* 3995 */       Iterator iterator = getAnnotations().iterator();
/* 3996 */       while (iterator.hasNext()) {
/* 3997 */         CategoryAnnotation annotation = (CategoryAnnotation)iterator.next();
/*      */         
/* 3999 */         annotation.draw(g2, this, dataArea, getDomainAxis(), getRangeAxis());
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
/*      */   protected void drawDomainMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer)
/*      */   {
/* 4020 */     CategoryItemRenderer r = getRenderer(index);
/* 4021 */     if (r == null) {
/* 4022 */       return;
/*      */     }
/*      */     
/* 4025 */     Collection markers = getDomainMarkers(index, layer);
/* 4026 */     CategoryAxis axis = getDomainAxisForDataset(index);
/* 4027 */     if ((markers != null) && (axis != null)) {
/* 4028 */       Iterator iterator = markers.iterator();
/* 4029 */       while (iterator.hasNext()) {
/* 4030 */         CategoryMarker marker = (CategoryMarker)iterator.next();
/* 4031 */         r.drawDomainMarker(g2, this, axis, marker, dataArea);
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
/*      */   protected void drawRangeMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer)
/*      */   {
/* 4051 */     CategoryItemRenderer r = getRenderer(index);
/* 4052 */     if (r == null) {
/* 4053 */       return;
/*      */     }
/*      */     
/* 4056 */     Collection markers = getRangeMarkers(index, layer);
/* 4057 */     ValueAxis axis = getRangeAxisForDataset(index);
/* 4058 */     if ((markers != null) && (axis != null)) {
/* 4059 */       Iterator iterator = markers.iterator();
/* 4060 */       while (iterator.hasNext()) {
/* 4061 */         Marker marker = (Marker)iterator.next();
/* 4062 */         r.drawRangeMarker(g2, this, axis, marker, dataArea);
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
/*      */   protected void drawRangeLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint)
/*      */   {
/* 4081 */     double java2D = getRangeAxis().valueToJava2D(value, dataArea, getRangeAxisEdge());
/*      */     
/* 4083 */     Line2D line = null;
/* 4084 */     if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 4085 */       line = new Line2D.Double(java2D, dataArea.getMinY(), java2D, dataArea.getMaxY());
/*      */ 
/*      */     }
/* 4088 */     else if (this.orientation == PlotOrientation.VERTICAL) {
/* 4089 */       line = new Line2D.Double(dataArea.getMinX(), java2D, dataArea.getMaxX(), java2D);
/*      */     }
/*      */     
/* 4092 */     g2.setStroke(stroke);
/* 4093 */     g2.setPaint(paint);
/* 4094 */     g2.draw(line);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawDomainCrosshair(Graphics2D g2, Rectangle2D dataArea, PlotOrientation orientation, int datasetIndex, Comparable rowKey, Comparable columnKey, Stroke stroke, Paint paint)
/*      */   {
/* 4120 */     CategoryDataset dataset = getDataset(datasetIndex);
/* 4121 */     CategoryAxis axis = getDomainAxisForDataset(datasetIndex);
/* 4122 */     CategoryItemRenderer renderer = getRenderer(datasetIndex);
/* 4123 */     Line2D line = null;
/* 4124 */     if (orientation == PlotOrientation.VERTICAL) {
/* 4125 */       double xx = renderer.getItemMiddle(rowKey, columnKey, dataset, axis, dataArea, RectangleEdge.BOTTOM);
/*      */       
/* 4127 */       line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/*      */     }
/*      */     else
/*      */     {
/* 4131 */       double yy = renderer.getItemMiddle(rowKey, columnKey, dataset, axis, dataArea, RectangleEdge.LEFT);
/*      */       
/* 4133 */       line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/*      */     }
/*      */     
/* 4136 */     g2.setStroke(stroke);
/* 4137 */     g2.setPaint(paint);
/* 4138 */     g2.draw(line);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawRangeCrosshair(Graphics2D g2, Rectangle2D dataArea, PlotOrientation orientation, double value, ValueAxis axis, Stroke stroke, Paint paint)
/*      */   {
/* 4162 */     if (!axis.getRange().contains(value)) {
/* 4163 */       return;
/*      */     }
/* 4165 */     Line2D line = null;
/* 4166 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 4167 */       double xx = axis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/*      */       
/* 4169 */       line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/*      */     }
/*      */     else
/*      */     {
/* 4173 */       double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */       
/* 4175 */       line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/*      */     }
/*      */     
/* 4178 */     g2.setStroke(stroke);
/* 4179 */     g2.setPaint(paint);
/* 4180 */     g2.draw(line);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range getDataRange(ValueAxis axis)
/*      */   {
/* 4195 */     Range result = null;
/* 4196 */     List mappedDatasets = new ArrayList();
/*      */     
/* 4198 */     int rangeIndex = this.rangeAxes.indexOf(axis);
/* 4199 */     if (rangeIndex >= 0) {
/* 4200 */       mappedDatasets.addAll(datasetsMappedToRangeAxis(rangeIndex));
/*      */     }
/* 4202 */     else if (axis == getRangeAxis()) {
/* 4203 */       mappedDatasets.addAll(datasetsMappedToRangeAxis(0));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4208 */     Iterator iterator = mappedDatasets.iterator();
/* 4209 */     while (iterator.hasNext()) {
/* 4210 */       CategoryDataset d = (CategoryDataset)iterator.next();
/* 4211 */       CategoryItemRenderer r = getRendererForDataset(d);
/* 4212 */       if (r != null) {
/* 4213 */         result = Range.combine(result, r.findRangeBounds(d));
/*      */       }
/*      */     }
/* 4216 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private List datasetsMappedToDomainAxis(int axisIndex)
/*      */   {
/* 4231 */     Integer key = new Integer(axisIndex);
/* 4232 */     List result = new ArrayList();
/* 4233 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 4234 */       List mappedAxes = (List)this.datasetToDomainAxesMap.get(new Integer(i));
/*      */       
/* 4236 */       CategoryDataset dataset = (CategoryDataset)this.datasets.get(i);
/* 4237 */       if (mappedAxes == null) {
/* 4238 */         if ((key.equals(ZERO)) && 
/* 4239 */           (dataset != null)) {
/* 4240 */           result.add(dataset);
/*      */         }
/*      */         
/*      */ 
/*      */       }
/* 4245 */       else if ((mappedAxes.contains(key)) && 
/* 4246 */         (dataset != null)) {
/* 4247 */         result.add(dataset);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4252 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private List datasetsMappedToRangeAxis(int index)
/*      */   {
/* 4264 */     Integer key = new Integer(index);
/* 4265 */     List result = new ArrayList();
/* 4266 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 4267 */       List mappedAxes = (List)this.datasetToRangeAxesMap.get(new Integer(i));
/*      */       
/* 4269 */       if (mappedAxes == null) {
/* 4270 */         if (key.equals(ZERO)) {
/* 4271 */           result.add(this.datasets.get(i));
/*      */         }
/*      */         
/*      */       }
/* 4275 */       else if (mappedAxes.contains(key)) {
/* 4276 */         result.add(this.datasets.get(i));
/*      */       }
/*      */     }
/*      */     
/* 4280 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getWeight()
/*      */   {
/* 4292 */     return this.weight;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWeight(int weight)
/*      */   {
/* 4304 */     this.weight = weight;
/* 4305 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisSpace getFixedDomainAxisSpace()
/*      */   {
/* 4316 */     return this.fixedDomainAxisSpace;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedDomainAxisSpace(AxisSpace space)
/*      */   {
/* 4328 */     setFixedDomainAxisSpace(space, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedDomainAxisSpace(AxisSpace space, boolean notify)
/*      */   {
/* 4343 */     this.fixedDomainAxisSpace = space;
/* 4344 */     if (notify) {
/* 4345 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AxisSpace getFixedRangeAxisSpace()
/*      */   {
/* 4357 */     return this.fixedRangeAxisSpace;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedRangeAxisSpace(AxisSpace space)
/*      */   {
/* 4369 */     setFixedRangeAxisSpace(space, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFixedRangeAxisSpace(AxisSpace space, boolean notify)
/*      */   {
/* 4384 */     this.fixedRangeAxisSpace = space;
/* 4385 */     if (notify) {
/* 4386 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getCategories()
/*      */   {
/* 4398 */     List result = null;
/* 4399 */     if (getDataset() != null) {
/* 4400 */       result = Collections.unmodifiableList(getDataset().getColumnKeys());
/*      */     }
/* 4402 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getCategoriesForAxis(CategoryAxis axis)
/*      */   {
/* 4416 */     List result = new ArrayList();
/* 4417 */     int axisIndex = this.domainAxes.indexOf(axis);
/* 4418 */     List datasets = datasetsMappedToDomainAxis(axisIndex);
/* 4419 */     Iterator iterator = datasets.iterator();
/* 4420 */     while (iterator.hasNext()) {
/* 4421 */       CategoryDataset dataset = (CategoryDataset)iterator.next();
/*      */       
/* 4423 */       for (int i = 0; i < dataset.getColumnCount(); i++) {
/* 4424 */         Comparable category = dataset.getColumnKey(i);
/* 4425 */         if (!result.contains(category)) {
/* 4426 */           result.add(category);
/*      */         }
/*      */       }
/*      */     }
/* 4430 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getDrawSharedDomainAxis()
/*      */   {
/* 4442 */     return this.drawSharedDomainAxis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDrawSharedDomainAxis(boolean draw)
/*      */   {
/* 4454 */     this.drawSharedDomainAxis = draw;
/* 4455 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainPannable()
/*      */   {
/* 4467 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangePannable()
/*      */   {
/* 4479 */     return this.rangePannable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangePannable(boolean pannable)
/*      */   {
/* 4491 */     this.rangePannable = pannable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void panDomainAxes(double percent, PlotRenderingInfo info, Point2D source) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void panRangeAxes(double percent, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 4519 */     if (!isRangePannable()) {
/* 4520 */       return;
/*      */     }
/* 4522 */     int rangeAxisCount = getRangeAxisCount();
/* 4523 */     for (int i = 0; i < rangeAxisCount; i++) {
/* 4524 */       ValueAxis axis = getRangeAxis(i);
/* 4525 */       if (axis != null)
/*      */       {
/*      */ 
/* 4528 */         double length = axis.getRange().getLength();
/* 4529 */         double adj = percent * length;
/* 4530 */         if (axis.isInverted()) {
/* 4531 */           adj = -adj;
/*      */         }
/* 4533 */         axis.setRange(axis.getLowerBound() + adj, axis.getUpperBound() + adj);
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
/*      */   public boolean isDomainZoomable()
/*      */   {
/* 4547 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/* 4558 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source)
/*      */   {
/* 4616 */     zoomRangeAxes(factor, state, source, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor)
/*      */   {
/* 4636 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 4637 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 4638 */       if (rangeAxis != null) {
/* 4639 */         if (useAnchor)
/*      */         {
/*      */ 
/* 4642 */           double sourceY = source.getY();
/* 4643 */           if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 4644 */             sourceY = source.getX();
/*      */           }
/* 4646 */           double anchorY = rangeAxis.java2DToValue(sourceY, info.getDataArea(), getRangeAxisEdge());
/*      */           
/* 4648 */           rangeAxis.resizeRange2(factor, anchorY);
/*      */         }
/*      */         else {
/* 4651 */           rangeAxis.resizeRange(factor);
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
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source)
/*      */   {
/* 4667 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 4668 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 4669 */       if (rangeAxis != null) {
/* 4670 */         rangeAxis.zoomRange(lowerPercent, upperPercent);
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
/*      */   public double getAnchorValue()
/*      */   {
/* 4683 */     return this.anchorValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAnchorValue(double value)
/*      */   {
/* 4695 */     setAnchorValue(value, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAnchorValue(double value, boolean notify)
/*      */   {
/* 4708 */     this.anchorValue = value;
/* 4709 */     if (notify) {
/* 4710 */       fireChangeEvent();
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
/*      */   public boolean equals(Object obj)
/*      */   {
/* 4723 */     if (obj == this) {
/* 4724 */       return true;
/*      */     }
/* 4726 */     if (!(obj instanceof CategoryPlot)) {
/* 4727 */       return false;
/*      */     }
/* 4729 */     CategoryPlot that = (CategoryPlot)obj;
/* 4730 */     if (this.orientation != that.orientation) {
/* 4731 */       return false;
/*      */     }
/* 4733 */     if (!ObjectUtilities.equal(this.axisOffset, that.axisOffset)) {
/* 4734 */       return false;
/*      */     }
/* 4736 */     if (!this.domainAxes.equals(that.domainAxes)) {
/* 4737 */       return false;
/*      */     }
/* 4739 */     if (!this.domainAxisLocations.equals(that.domainAxisLocations)) {
/* 4740 */       return false;
/*      */     }
/* 4742 */     if (this.drawSharedDomainAxis != that.drawSharedDomainAxis) {
/* 4743 */       return false;
/*      */     }
/* 4745 */     if (!this.rangeAxes.equals(that.rangeAxes)) {
/* 4746 */       return false;
/*      */     }
/* 4748 */     if (!this.rangeAxisLocations.equals(that.rangeAxisLocations)) {
/* 4749 */       return false;
/*      */     }
/* 4751 */     if (!ObjectUtilities.equal(this.datasetToDomainAxesMap, that.datasetToDomainAxesMap))
/*      */     {
/* 4753 */       return false;
/*      */     }
/* 4755 */     if (!ObjectUtilities.equal(this.datasetToRangeAxesMap, that.datasetToRangeAxesMap))
/*      */     {
/* 4757 */       return false;
/*      */     }
/* 4759 */     if (!ObjectUtilities.equal(this.renderers, that.renderers)) {
/* 4760 */       return false;
/*      */     }
/* 4762 */     if (this.renderingOrder != that.renderingOrder) {
/* 4763 */       return false;
/*      */     }
/* 4765 */     if (this.columnRenderingOrder != that.columnRenderingOrder) {
/* 4766 */       return false;
/*      */     }
/* 4768 */     if (this.rowRenderingOrder != that.rowRenderingOrder) {
/* 4769 */       return false;
/*      */     }
/* 4771 */     if (this.domainGridlinesVisible != that.domainGridlinesVisible) {
/* 4772 */       return false;
/*      */     }
/* 4774 */     if (this.domainGridlinePosition != that.domainGridlinePosition) {
/* 4775 */       return false;
/*      */     }
/* 4777 */     if (!ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke))
/*      */     {
/* 4779 */       return false;
/*      */     }
/* 4781 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/*      */     {
/* 4783 */       return false;
/*      */     }
/* 4785 */     if (this.rangeGridlinesVisible != that.rangeGridlinesVisible) {
/* 4786 */       return false;
/*      */     }
/* 4788 */     if (!ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke))
/*      */     {
/* 4790 */       return false;
/*      */     }
/* 4792 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/*      */     {
/* 4794 */       return false;
/*      */     }
/* 4796 */     if (this.anchorValue != that.anchorValue) {
/* 4797 */       return false;
/*      */     }
/* 4799 */     if (this.rangeCrosshairVisible != that.rangeCrosshairVisible) {
/* 4800 */       return false;
/*      */     }
/* 4802 */     if (this.rangeCrosshairValue != that.rangeCrosshairValue) {
/* 4803 */       return false;
/*      */     }
/* 4805 */     if (!ObjectUtilities.equal(this.rangeCrosshairStroke, that.rangeCrosshairStroke))
/*      */     {
/* 4807 */       return false;
/*      */     }
/* 4809 */     if (!PaintUtilities.equal(this.rangeCrosshairPaint, that.rangeCrosshairPaint))
/*      */     {
/* 4811 */       return false;
/*      */     }
/* 4813 */     if (this.rangeCrosshairLockedOnData != that.rangeCrosshairLockedOnData)
/*      */     {
/* 4815 */       return false;
/*      */     }
/* 4817 */     if (!ObjectUtilities.equal(this.foregroundDomainMarkers, that.foregroundDomainMarkers))
/*      */     {
/* 4819 */       return false;
/*      */     }
/* 4821 */     if (!ObjectUtilities.equal(this.backgroundDomainMarkers, that.backgroundDomainMarkers))
/*      */     {
/* 4823 */       return false;
/*      */     }
/* 4825 */     if (!ObjectUtilities.equal(this.foregroundRangeMarkers, that.foregroundRangeMarkers))
/*      */     {
/* 4827 */       return false;
/*      */     }
/* 4829 */     if (!ObjectUtilities.equal(this.backgroundRangeMarkers, that.backgroundRangeMarkers))
/*      */     {
/* 4831 */       return false;
/*      */     }
/* 4833 */     if (!ObjectUtilities.equal(this.annotations, that.annotations)) {
/* 4834 */       return false;
/*      */     }
/* 4836 */     if (this.weight != that.weight) {
/* 4837 */       return false;
/*      */     }
/* 4839 */     if (!ObjectUtilities.equal(this.fixedDomainAxisSpace, that.fixedDomainAxisSpace))
/*      */     {
/* 4841 */       return false;
/*      */     }
/* 4843 */     if (!ObjectUtilities.equal(this.fixedRangeAxisSpace, that.fixedRangeAxisSpace))
/*      */     {
/* 4845 */       return false;
/*      */     }
/* 4847 */     if (!ObjectUtilities.equal(this.fixedLegendItems, that.fixedLegendItems))
/*      */     {
/* 4849 */       return false;
/*      */     }
/* 4851 */     if (this.domainCrosshairVisible != that.domainCrosshairVisible) {
/* 4852 */       return false;
/*      */     }
/* 4854 */     if (this.crosshairDatasetIndex != that.crosshairDatasetIndex) {
/* 4855 */       return false;
/*      */     }
/* 4857 */     if (!ObjectUtilities.equal(this.domainCrosshairColumnKey, that.domainCrosshairColumnKey))
/*      */     {
/* 4859 */       return false;
/*      */     }
/* 4861 */     if (!ObjectUtilities.equal(this.domainCrosshairRowKey, that.domainCrosshairRowKey))
/*      */     {
/* 4863 */       return false;
/*      */     }
/* 4865 */     if (!PaintUtilities.equal(this.domainCrosshairPaint, that.domainCrosshairPaint))
/*      */     {
/* 4867 */       return false;
/*      */     }
/* 4869 */     if (!ObjectUtilities.equal(this.domainCrosshairStroke, that.domainCrosshairStroke))
/*      */     {
/* 4871 */       return false;
/*      */     }
/* 4873 */     if (this.rangeMinorGridlinesVisible != that.rangeMinorGridlinesVisible)
/*      */     {
/* 4875 */       return false;
/*      */     }
/* 4877 */     if (!PaintUtilities.equal(this.rangeMinorGridlinePaint, that.rangeMinorGridlinePaint))
/*      */     {
/* 4879 */       return false;
/*      */     }
/* 4881 */     if (!ObjectUtilities.equal(this.rangeMinorGridlineStroke, that.rangeMinorGridlineStroke))
/*      */     {
/* 4883 */       return false;
/*      */     }
/* 4885 */     if (this.rangeZeroBaselineVisible != that.rangeZeroBaselineVisible) {
/* 4886 */       return false;
/*      */     }
/* 4888 */     if (!PaintUtilities.equal(this.rangeZeroBaselinePaint, that.rangeZeroBaselinePaint))
/*      */     {
/* 4890 */       return false;
/*      */     }
/* 4892 */     if (!ObjectUtilities.equal(this.rangeZeroBaselineStroke, that.rangeZeroBaselineStroke))
/*      */     {
/* 4894 */       return false;
/*      */     }
/* 4896 */     return super.equals(obj);
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
/* 4909 */     CategoryPlot clone = (CategoryPlot)super.clone();
/*      */     
/* 4911 */     clone.domainAxes = new ObjectList();
/* 4912 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 4913 */       CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(i);
/* 4914 */       if (xAxis != null) {
/* 4915 */         CategoryAxis clonedAxis = (CategoryAxis)xAxis.clone();
/* 4916 */         clone.setDomainAxis(i, clonedAxis);
/*      */       }
/*      */     }
/* 4919 */     clone.domainAxisLocations = ((ObjectList)this.domainAxisLocations.clone());
/*      */     
/*      */ 
/* 4922 */     clone.rangeAxes = new ObjectList();
/* 4923 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 4924 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(i);
/* 4925 */       if (yAxis != null) {
/* 4926 */         ValueAxis clonedAxis = (ValueAxis)yAxis.clone();
/* 4927 */         clone.setRangeAxis(i, clonedAxis);
/*      */       }
/*      */     }
/* 4930 */     clone.rangeAxisLocations = ((ObjectList)this.rangeAxisLocations.clone());
/*      */     
/* 4932 */     clone.datasets = ((ObjectList)this.datasets.clone());
/* 4933 */     for (int i = 0; i < clone.datasets.size(); i++) {
/* 4934 */       CategoryDataset dataset = clone.getDataset(i);
/* 4935 */       if (dataset != null) {
/* 4936 */         dataset.addChangeListener(clone);
/*      */       }
/*      */     }
/* 4939 */     clone.datasetToDomainAxesMap = new TreeMap();
/* 4940 */     clone.datasetToDomainAxesMap.putAll(this.datasetToDomainAxesMap);
/* 4941 */     clone.datasetToRangeAxesMap = new TreeMap();
/* 4942 */     clone.datasetToRangeAxesMap.putAll(this.datasetToRangeAxesMap);
/*      */     
/* 4944 */     clone.renderers = ((ObjectList)this.renderers.clone());
/* 4945 */     if (this.fixedDomainAxisSpace != null) {
/* 4946 */       clone.fixedDomainAxisSpace = ((AxisSpace)ObjectUtilities.clone(this.fixedDomainAxisSpace));
/*      */     }
/*      */     
/* 4949 */     if (this.fixedRangeAxisSpace != null) {
/* 4950 */       clone.fixedRangeAxisSpace = ((AxisSpace)ObjectUtilities.clone(this.fixedRangeAxisSpace));
/*      */     }
/*      */     
/*      */ 
/* 4954 */     clone.annotations = ((List)ObjectUtilities.deepClone(this.annotations));
/* 4955 */     clone.foregroundDomainMarkers = cloneMarkerMap(this.foregroundDomainMarkers);
/*      */     
/* 4957 */     clone.backgroundDomainMarkers = cloneMarkerMap(this.backgroundDomainMarkers);
/*      */     
/* 4959 */     clone.foregroundRangeMarkers = cloneMarkerMap(this.foregroundRangeMarkers);
/*      */     
/* 4961 */     clone.backgroundRangeMarkers = cloneMarkerMap(this.backgroundRangeMarkers);
/*      */     
/* 4963 */     if (this.fixedLegendItems != null) {
/* 4964 */       clone.fixedLegendItems = ((LegendItemCollection)this.fixedLegendItems.clone());
/*      */     }
/*      */     
/* 4967 */     return clone;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map cloneMarkerMap(Map map)
/*      */     throws CloneNotSupportedException
/*      */   {
/* 4982 */     Map clone = new HashMap();
/* 4983 */     Set keys = map.keySet();
/* 4984 */     Iterator iterator = keys.iterator();
/* 4985 */     while (iterator.hasNext()) {
/* 4986 */       Object key = iterator.next();
/* 4987 */       List entry = (List)map.get(key);
/* 4988 */       Object toAdd = ObjectUtilities.deepClone(entry);
/* 4989 */       clone.put(key, toAdd);
/*      */     }
/* 4991 */     return clone;
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
/* 5002 */     stream.defaultWriteObject();
/* 5003 */     SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
/* 5004 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 5005 */     SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
/* 5006 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
/* 5007 */     SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
/* 5008 */     SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
/* 5009 */     SerialUtilities.writeStroke(this.domainCrosshairStroke, stream);
/* 5010 */     SerialUtilities.writePaint(this.domainCrosshairPaint, stream);
/* 5011 */     SerialUtilities.writeStroke(this.rangeMinorGridlineStroke, stream);
/* 5012 */     SerialUtilities.writePaint(this.rangeMinorGridlinePaint, stream);
/* 5013 */     SerialUtilities.writeStroke(this.rangeZeroBaselineStroke, stream);
/* 5014 */     SerialUtilities.writePaint(this.rangeZeroBaselinePaint, stream);
/*      */   }
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
/* 5028 */     stream.defaultReadObject();
/* 5029 */     this.domainGridlineStroke = SerialUtilities.readStroke(stream);
/* 5030 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/* 5031 */     this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
/* 5032 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/* 5033 */     this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
/* 5034 */     this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
/* 5035 */     this.domainCrosshairStroke = SerialUtilities.readStroke(stream);
/* 5036 */     this.domainCrosshairPaint = SerialUtilities.readPaint(stream);
/* 5037 */     this.rangeMinorGridlineStroke = SerialUtilities.readStroke(stream);
/* 5038 */     this.rangeMinorGridlinePaint = SerialUtilities.readPaint(stream);
/* 5039 */     this.rangeZeroBaselineStroke = SerialUtilities.readStroke(stream);
/* 5040 */     this.rangeZeroBaselinePaint = SerialUtilities.readPaint(stream);
/*      */     
/* 5042 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 5043 */       CategoryAxis xAxis = (CategoryAxis)this.domainAxes.get(i);
/* 5044 */       if (xAxis != null) {
/* 5045 */         xAxis.setPlot(this);
/* 5046 */         xAxis.addChangeListener(this);
/*      */       }
/*      */     }
/* 5049 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 5050 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(i);
/* 5051 */       if (yAxis != null) {
/* 5052 */         yAxis.setPlot(this);
/* 5053 */         yAxis.addChangeListener(this);
/*      */       }
/*      */     }
/* 5056 */     int datasetCount = this.datasets.size();
/* 5057 */     for (int i = 0; i < datasetCount; i++) {
/* 5058 */       Dataset dataset = (Dataset)this.datasets.get(i);
/* 5059 */       if (dataset != null) {
/* 5060 */         dataset.addChangeListener(this);
/*      */       }
/*      */     }
/* 5063 */     int rendererCount = this.renderers.size();
/* 5064 */     for (int i = 0; i < rendererCount; i++) {
/* 5065 */       CategoryItemRenderer renderer = (CategoryItemRenderer)this.renderers.get(i);
/*      */       
/* 5067 */       if (renderer != null) {
/* 5068 */         renderer.addChangeListener(this);
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/CategoryPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */