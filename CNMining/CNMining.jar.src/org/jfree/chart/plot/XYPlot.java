/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
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
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.annotations.XYAnnotationBoundsInfo;
/*      */ import org.jfree.chart.axis.Axis;
/*      */ import org.jfree.chart.axis.AxisCollection;
/*      */ import org.jfree.chart.axis.AxisLocation;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.TickType;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.axis.ValueTick;
/*      */ import org.jfree.chart.event.ChartChangeEventType;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.renderer.RendererUtilities;
/*      */ import org.jfree.chart.renderer.xy.AbstractXYItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYItemRendererState;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.Dataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectList;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XYPlot
/*      */   extends Plot
/*      */   implements ValueAxisPlot, Pannable, Zoomable, RendererChangeListener, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 7044148245716569264L;
/*  308 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  313 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*      */   
/*      */ 
/*      */   public static final boolean DEFAULT_CROSSHAIR_VISIBLE = false;
/*      */   
/*      */ 
/*  319 */   public static final Stroke DEFAULT_CROSSHAIR_STROKE = DEFAULT_GRIDLINE_STROKE;
/*      */   
/*      */ 
/*      */ 
/*  323 */   public static final Paint DEFAULT_CROSSHAIR_PAINT = Color.blue;
/*      */   
/*      */ 
/*  326 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PlotOrientation orientation;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private RectangleInsets axisOffset;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private ObjectList domainAxes;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private ObjectList domainAxisLocations;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private ObjectList rangeAxes;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList rangeAxisLocations;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList datasets;
/*      */   
/*      */ 
/*      */ 
/*      */   private ObjectList renderers;
/*      */   
/*      */ 
/*      */ 
/*      */   private Map datasetToDomainAxesMap;
/*      */   
/*      */ 
/*      */ 
/*      */   private Map datasetToRangeAxesMap;
/*      */   
/*      */ 
/*      */ 
/*  375 */   private transient Point2D quadrantOrigin = new Point2D.Double(0.0D, 0.0D);
/*      */   
/*      */ 
/*  378 */   private transient Paint[] quadrantPaint = { null, null, null, null };
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean domainGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke domainGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint domainGridlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangeGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint rangeGridlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean domainMinorGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke domainMinorGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint domainMinorGridlinePaint;
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
/*      */   private boolean domainZeroBaselineVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke domainZeroBaselineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint domainZeroBaselinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangeZeroBaselineVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeZeroBaselineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint rangeZeroBaselinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean domainCrosshairVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double domainCrosshairValue;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke domainCrosshairStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint domainCrosshairPaint;
/*      */   
/*      */ 
/*      */ 
/*  491 */   private boolean domainCrosshairLockedOnData = true;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean rangeCrosshairVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private double rangeCrosshairValue;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke rangeCrosshairStroke;
/*      */   
/*      */ 
/*      */   private transient Paint rangeCrosshairPaint;
/*      */   
/*      */ 
/*  509 */   private boolean rangeCrosshairLockedOnData = true;
/*      */   
/*      */ 
/*      */ 
/*      */   private Map foregroundDomainMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */   private Map backgroundDomainMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */   private Map foregroundRangeMarkers;
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
/*      */   private transient Paint domainTickBandPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint rangeTickBandPaint;
/*      */   
/*      */ 
/*      */   private AxisSpace fixedDomainAxisSpace;
/*      */   
/*      */ 
/*      */   private AxisSpace fixedRangeAxisSpace;
/*      */   
/*      */ 
/*  546 */   private DatasetRenderingOrder datasetRenderingOrder = DatasetRenderingOrder.REVERSE;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  553 */   private SeriesRenderingOrder seriesRenderingOrder = SeriesRenderingOrder.REVERSE;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private int weight;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private LegendItemCollection fixedLegendItems;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean domainPannable;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean rangePannable;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYPlot()
/*      */   {
/*  589 */     this(null, null, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYPlot(XYDataset dataset, ValueAxis domainAxis, ValueAxis rangeAxis, XYItemRenderer renderer)
/*      */   {
/*  610 */     this.orientation = PlotOrientation.VERTICAL;
/*  611 */     this.weight = 1;
/*  612 */     this.axisOffset = RectangleInsets.ZERO_INSETS;
/*      */     
/*      */ 
/*  615 */     this.domainAxes = new ObjectList();
/*  616 */     this.domainAxisLocations = new ObjectList();
/*  617 */     this.foregroundDomainMarkers = new HashMap();
/*  618 */     this.backgroundDomainMarkers = new HashMap();
/*      */     
/*  620 */     this.rangeAxes = new ObjectList();
/*  621 */     this.rangeAxisLocations = new ObjectList();
/*  622 */     this.foregroundRangeMarkers = new HashMap();
/*  623 */     this.backgroundRangeMarkers = new HashMap();
/*      */     
/*  625 */     this.datasets = new ObjectList();
/*  626 */     this.renderers = new ObjectList();
/*      */     
/*  628 */     this.datasetToDomainAxesMap = new TreeMap();
/*  629 */     this.datasetToRangeAxesMap = new TreeMap();
/*      */     
/*  631 */     this.annotations = new ArrayList();
/*      */     
/*  633 */     this.datasets.set(0, dataset);
/*  634 */     if (dataset != null) {
/*  635 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*  638 */     this.renderers.set(0, renderer);
/*  639 */     if (renderer != null) {
/*  640 */       renderer.setPlot(this);
/*  641 */       renderer.addChangeListener(this);
/*      */     }
/*      */     
/*  644 */     this.domainAxes.set(0, domainAxis);
/*  645 */     mapDatasetToDomainAxis(0, 0);
/*  646 */     if (domainAxis != null) {
/*  647 */       domainAxis.setPlot(this);
/*  648 */       domainAxis.addChangeListener(this);
/*      */     }
/*  650 */     this.domainAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
/*      */     
/*  652 */     this.rangeAxes.set(0, rangeAxis);
/*  653 */     mapDatasetToRangeAxis(0, 0);
/*  654 */     if (rangeAxis != null) {
/*  655 */       rangeAxis.setPlot(this);
/*  656 */       rangeAxis.addChangeListener(this);
/*      */     }
/*  658 */     this.rangeAxisLocations.set(0, AxisLocation.BOTTOM_OR_LEFT);
/*      */     
/*  660 */     configureDomainAxes();
/*  661 */     configureRangeAxes();
/*      */     
/*  663 */     this.domainGridlinesVisible = true;
/*  664 */     this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  665 */     this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */     
/*  667 */     this.domainMinorGridlinesVisible = false;
/*  668 */     this.domainMinorGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  669 */     this.domainMinorGridlinePaint = Color.white;
/*      */     
/*  671 */     this.domainZeroBaselineVisible = false;
/*  672 */     this.domainZeroBaselinePaint = Color.black;
/*  673 */     this.domainZeroBaselineStroke = new BasicStroke(0.5F);
/*      */     
/*  675 */     this.rangeGridlinesVisible = true;
/*  676 */     this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  677 */     this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */     
/*  679 */     this.rangeMinorGridlinesVisible = false;
/*  680 */     this.rangeMinorGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  681 */     this.rangeMinorGridlinePaint = Color.white;
/*      */     
/*  683 */     this.rangeZeroBaselineVisible = false;
/*  684 */     this.rangeZeroBaselinePaint = Color.black;
/*  685 */     this.rangeZeroBaselineStroke = new BasicStroke(0.5F);
/*      */     
/*  687 */     this.domainCrosshairVisible = false;
/*  688 */     this.domainCrosshairValue = 0.0D;
/*  689 */     this.domainCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
/*  690 */     this.domainCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
/*      */     
/*  692 */     this.rangeCrosshairVisible = false;
/*  693 */     this.rangeCrosshairValue = 0.0D;
/*  694 */     this.rangeCrosshairStroke = DEFAULT_CROSSHAIR_STROKE;
/*  695 */     this.rangeCrosshairPaint = DEFAULT_CROSSHAIR_PAINT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/*  705 */     return localizationResources.getString("XY_Plot");
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
/*  716 */     return this.orientation;
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
/*  728 */     if (orientation == null) {
/*  729 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*  731 */     if (orientation != this.orientation) {
/*  732 */       this.orientation = orientation;
/*  733 */       fireChangeEvent();
/*      */     }
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
/*  745 */     return this.axisOffset;
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
/*  757 */     if (offset == null) {
/*  758 */       throw new IllegalArgumentException("Null 'offset' argument.");
/*      */     }
/*  760 */     this.axisOffset = offset;
/*  761 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getDomainAxis()
/*      */   {
/*  775 */     return getDomainAxis(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getDomainAxis(int index)
/*      */   {
/*  788 */     ValueAxis result = null;
/*  789 */     if (index < this.domainAxes.size()) {
/*  790 */       result = (ValueAxis)this.domainAxes.get(index);
/*      */     }
/*  792 */     if (result == null) {
/*  793 */       Plot parent = getParent();
/*  794 */       if ((parent instanceof XYPlot)) {
/*  795 */         XYPlot xy = (XYPlot)parent;
/*  796 */         result = xy.getDomainAxis(index);
/*      */       }
/*      */     }
/*  799 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxis(ValueAxis axis)
/*      */   {
/*  812 */     setDomainAxis(0, axis);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxis(int index, ValueAxis axis)
/*      */   {
/*  826 */     setDomainAxis(index, axis, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainAxis(int index, ValueAxis axis, boolean notify)
/*      */   {
/*  840 */     ValueAxis existing = getDomainAxis(index);
/*  841 */     if (existing != null) {
/*  842 */       existing.removeChangeListener(this);
/*      */     }
/*  844 */     if (axis != null) {
/*  845 */       axis.setPlot(this);
/*      */     }
/*  847 */     this.domainAxes.set(index, axis);
/*  848 */     if (axis != null) {
/*  849 */       axis.configure();
/*  850 */       axis.addChangeListener(this);
/*      */     }
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
/*      */   public void setDomainAxes(ValueAxis[] axes)
/*      */   {
/*  866 */     for (int i = 0; i < axes.length; i++) {
/*  867 */       setDomainAxis(i, axes[i], false);
/*      */     }
/*  869 */     fireChangeEvent();
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
/*  880 */     return (AxisLocation)this.domainAxisLocations.get(0);
/*      */   }
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
/*  893 */     setDomainAxisLocation(0, location, true);
/*      */   }
/*      */   
/*      */ 
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
/*  907 */     setDomainAxisLocation(0, location, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getDomainAxisEdge()
/*      */   {
/*  920 */     return Plot.resolveDomainAxisLocation(getDomainAxisLocation(), this.orientation);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDomainAxisCount()
/*      */   {
/*  932 */     return this.domainAxes.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainAxes()
/*      */   {
/*  942 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  943 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
/*  944 */       if (axis != null) {
/*  945 */         axis.removeChangeListener(this);
/*      */       }
/*      */     }
/*  948 */     this.domainAxes.clear();
/*  949 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void configureDomainAxes()
/*      */   {
/*  956 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/*  957 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
/*  958 */       if (axis != null) {
/*  959 */         axis.configure();
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
/*      */   public AxisLocation getDomainAxisLocation(int index)
/*      */   {
/*  976 */     AxisLocation result = null;
/*  977 */     if (index < this.domainAxisLocations.size()) {
/*  978 */       result = (AxisLocation)this.domainAxisLocations.get(index);
/*      */     }
/*  980 */     if (result == null) {
/*  981 */       result = AxisLocation.getOpposite(getDomainAxisLocation());
/*      */     }
/*  983 */     return result;
/*      */   }
/*      */   
/*      */ 
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
/*  998 */     setDomainAxisLocation(index, location, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1018 */     if ((index == 0) && (location == null)) {
/* 1019 */       throw new IllegalArgumentException("Null 'location' for index 0 not permitted.");
/*      */     }
/*      */     
/* 1022 */     this.domainAxisLocations.set(index, location);
/* 1023 */     if (notify) {
/* 1024 */       fireChangeEvent();
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
/*      */   public RectangleEdge getDomainAxisEdge(int index)
/*      */   {
/* 1038 */     AxisLocation location = getDomainAxisLocation(index);
/* 1039 */     RectangleEdge result = Plot.resolveDomainAxisLocation(location, this.orientation);
/*      */     
/* 1041 */     if (result == null) {
/* 1042 */       result = RectangleEdge.opposite(getDomainAxisEdge());
/*      */     }
/* 1044 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getRangeAxis()
/*      */   {
/* 1058 */     return getRangeAxis(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxis(ValueAxis axis)
/*      */   {
/* 1072 */     if (axis != null) {
/* 1073 */       axis.setPlot(this);
/*      */     }
/*      */     
/*      */ 
/* 1077 */     ValueAxis existing = getRangeAxis();
/* 1078 */     if (existing != null) {
/* 1079 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/* 1082 */     this.rangeAxes.set(0, axis);
/* 1083 */     if (axis != null) {
/* 1084 */       axis.configure();
/* 1085 */       axis.addChangeListener(this);
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
/*      */   public AxisLocation getRangeAxisLocation()
/*      */   {
/* 1099 */     return (AxisLocation)this.rangeAxisLocations.get(0);
/*      */   }
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
/* 1112 */     setRangeAxisLocation(0, location, true);
/*      */   }
/*      */   
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
/* 1126 */     setRangeAxisLocation(0, location, notify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getRangeAxisEdge()
/*      */   {
/* 1138 */     return Plot.resolveRangeAxisLocation(getRangeAxisLocation(), this.orientation);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getRangeAxis(int index)
/*      */   {
/* 1152 */     ValueAxis result = null;
/* 1153 */     if (index < this.rangeAxes.size()) {
/* 1154 */       result = (ValueAxis)this.rangeAxes.get(index);
/*      */     }
/* 1156 */     if (result == null) {
/* 1157 */       Plot parent = getParent();
/* 1158 */       if ((parent instanceof XYPlot)) {
/* 1159 */         XYPlot xy = (XYPlot)parent;
/* 1160 */         result = xy.getRangeAxis(index);
/*      */       }
/*      */     }
/* 1163 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeAxis(int index, ValueAxis axis)
/*      */   {
/* 1176 */     setRangeAxis(index, axis, true);
/*      */   }
/*      */   
/*      */ 
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
/* 1190 */     ValueAxis existing = getRangeAxis(index);
/* 1191 */     if (existing != null) {
/* 1192 */       existing.removeChangeListener(this);
/*      */     }
/* 1194 */     if (axis != null) {
/* 1195 */       axis.setPlot(this);
/*      */     }
/* 1197 */     this.rangeAxes.set(index, axis);
/* 1198 */     if (axis != null) {
/* 1199 */       axis.configure();
/* 1200 */       axis.addChangeListener(this);
/*      */     }
/* 1202 */     if (notify) {
/* 1203 */       fireChangeEvent();
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
/* 1216 */     for (int i = 0; i < axes.length; i++) {
/* 1217 */       setRangeAxis(i, axes[i], false);
/*      */     }
/* 1219 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getRangeAxisCount()
/*      */   {
/* 1230 */     return this.rangeAxes.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeAxes()
/*      */   {
/* 1240 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 1241 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 1242 */       if (axis != null) {
/* 1243 */         axis.removeChangeListener(this);
/*      */       }
/*      */     }
/* 1246 */     this.rangeAxes.clear();
/* 1247 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void configureRangeAxes()
/*      */   {
/* 1256 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 1257 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 1258 */       if (axis != null) {
/* 1259 */         axis.configure();
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
/*      */   public AxisLocation getRangeAxisLocation(int index)
/*      */   {
/* 1276 */     AxisLocation result = null;
/* 1277 */     if (index < this.rangeAxisLocations.size()) {
/* 1278 */       result = (AxisLocation)this.rangeAxisLocations.get(index);
/*      */     }
/* 1280 */     if (result == null) {
/* 1281 */       result = AxisLocation.getOpposite(getRangeAxisLocation());
/*      */     }
/* 1283 */     return result;
/*      */   }
/*      */   
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
/* 1297 */     setRangeAxisLocation(index, location, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1317 */     if ((index == 0) && (location == null)) {
/* 1318 */       throw new IllegalArgumentException("Null 'location' for index 0 not permitted.");
/*      */     }
/*      */     
/* 1321 */     this.rangeAxisLocations.set(index, location);
/* 1322 */     if (notify) {
/* 1323 */       fireChangeEvent();
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
/*      */   public RectangleEdge getRangeAxisEdge(int index)
/*      */   {
/* 1338 */     AxisLocation location = getRangeAxisLocation(index);
/* 1339 */     RectangleEdge result = Plot.resolveRangeAxisLocation(location, this.orientation);
/*      */     
/* 1341 */     if (result == null) {
/* 1342 */       result = RectangleEdge.opposite(getRangeAxisEdge());
/*      */     }
/* 1344 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYDataset getDataset()
/*      */   {
/* 1356 */     return getDataset(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYDataset getDataset(int index)
/*      */   {
/* 1369 */     XYDataset result = null;
/* 1370 */     if (this.datasets.size() > index) {
/* 1371 */       result = (XYDataset)this.datasets.get(index);
/*      */     }
/* 1373 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataset(XYDataset dataset)
/*      */   {
/* 1386 */     setDataset(0, dataset);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataset(int index, XYDataset dataset)
/*      */   {
/* 1398 */     XYDataset existing = getDataset(index);
/* 1399 */     if (existing != null) {
/* 1400 */       existing.removeChangeListener(this);
/*      */     }
/* 1402 */     this.datasets.set(index, dataset);
/* 1403 */     if (dataset != null) {
/* 1404 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/* 1408 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/* 1409 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDatasetCount()
/*      */   {
/* 1418 */     return this.datasets.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int indexOf(XYDataset dataset)
/*      */   {
/* 1430 */     int result = -1;
/* 1431 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1432 */       if (dataset == this.datasets.get(i)) {
/* 1433 */         result = i;
/* 1434 */         break;
/*      */       }
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
/*      */   public void mapDatasetToDomainAxis(int index, int axisIndex)
/*      */   {
/* 1450 */     List axisIndices = new ArrayList(1);
/* 1451 */     axisIndices.add(new Integer(axisIndex));
/* 1452 */     mapDatasetToDomainAxes(index, axisIndices);
/*      */   }
/*      */   
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
/* 1466 */     if (index < 0) {
/* 1467 */       throw new IllegalArgumentException("Requires 'index' >= 0.");
/*      */     }
/* 1469 */     checkAxisIndices(axisIndices);
/* 1470 */     Integer key = new Integer(index);
/* 1471 */     this.datasetToDomainAxesMap.put(key, new ArrayList(axisIndices));
/*      */     
/* 1473 */     datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
/*      */   }
/*      */   
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
/* 1486 */     List axisIndices = new ArrayList(1);
/* 1487 */     axisIndices.add(new Integer(axisIndex));
/* 1488 */     mapDatasetToRangeAxes(index, axisIndices);
/*      */   }
/*      */   
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
/* 1502 */     if (index < 0) {
/* 1503 */       throw new IllegalArgumentException("Requires 'index' >= 0.");
/*      */     }
/* 1505 */     checkAxisIndices(axisIndices);
/* 1506 */     Integer key = new Integer(index);
/* 1507 */     this.datasetToRangeAxesMap.put(key, new ArrayList(axisIndices));
/*      */     
/* 1509 */     datasetChanged(new DatasetChangeEvent(this, getDataset(index)));
/*      */   }
/*      */   
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
/* 1523 */     if (indices == null) {
/* 1524 */       return;
/*      */     }
/* 1526 */     int count = indices.size();
/* 1527 */     if (count == 0) {
/* 1528 */       throw new IllegalArgumentException("Empty list not permitted.");
/*      */     }
/* 1530 */     HashSet set = new HashSet();
/* 1531 */     for (int i = 0; i < count; i++) {
/* 1532 */       Object item = indices.get(i);
/* 1533 */       if (!(item instanceof Integer)) {
/* 1534 */         throw new IllegalArgumentException("Indices must be Integer instances.");
/*      */       }
/*      */       
/* 1537 */       if (set.contains(item)) {
/* 1538 */         throw new IllegalArgumentException("Indices must be unique.");
/*      */       }
/* 1540 */       set.add(item);
/*      */     }
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
/* 1552 */     return this.renderers.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYItemRenderer getRenderer()
/*      */   {
/* 1563 */     return getRenderer(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYItemRenderer getRenderer(int index)
/*      */   {
/* 1576 */     XYItemRenderer result = null;
/* 1577 */     if (this.renderers.size() > index) {
/* 1578 */       result = (XYItemRenderer)this.renderers.get(index);
/*      */     }
/* 1580 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(XYItemRenderer renderer)
/*      */   {
/* 1594 */     setRenderer(0, renderer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(int index, XYItemRenderer renderer)
/*      */   {
/* 1607 */     setRenderer(index, renderer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(int index, XYItemRenderer renderer, boolean notify)
/*      */   {
/* 1622 */     XYItemRenderer existing = getRenderer(index);
/* 1623 */     if (existing != null) {
/* 1624 */       existing.removeChangeListener(this);
/*      */     }
/* 1626 */     this.renderers.set(index, renderer);
/* 1627 */     if (renderer != null) {
/* 1628 */       renderer.setPlot(this);
/* 1629 */       renderer.addChangeListener(this);
/*      */     }
/* 1631 */     configureDomainAxes();
/* 1632 */     configureRangeAxes();
/* 1633 */     if (notify) {
/* 1634 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderers(XYItemRenderer[] renderers)
/*      */   {
/* 1645 */     for (int i = 0; i < renderers.length; i++) {
/* 1646 */       setRenderer(i, renderers[i], false);
/*      */     }
/* 1648 */     fireChangeEvent();
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
/* 1659 */     return this.datasetRenderingOrder;
/*      */   }
/*      */   
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
/* 1673 */     if (order == null) {
/* 1674 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/* 1676 */     this.datasetRenderingOrder = order;
/* 1677 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public SeriesRenderingOrder getSeriesRenderingOrder()
/*      */   {
/* 1688 */     return this.seriesRenderingOrder;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSeriesRenderingOrder(SeriesRenderingOrder order)
/*      */   {
/* 1702 */     if (order == null) {
/* 1703 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/* 1705 */     this.seriesRenderingOrder = order;
/* 1706 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getIndexOf(XYItemRenderer renderer)
/*      */   {
/* 1718 */     return this.renderers.indexOf(renderer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYItemRenderer getRendererForDataset(XYDataset dataset)
/*      */   {
/* 1731 */     XYItemRenderer result = null;
/* 1732 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 1733 */       if (this.datasets.get(i) == dataset) {
/* 1734 */         result = (XYItemRenderer)this.renderers.get(i);
/* 1735 */         if (result != null) break;
/* 1736 */         result = getRenderer(); break;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1741 */     return result;
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
/* 1753 */     return this.weight;
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
/* 1765 */     this.weight = weight;
/* 1766 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainGridlinesVisible()
/*      */   {
/* 1778 */     return this.domainGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
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
/* 1793 */     if (this.domainGridlinesVisible != visible) {
/* 1794 */       this.domainGridlinesVisible = visible;
/* 1795 */       fireChangeEvent();
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
/*      */   public boolean isDomainMinorGridlinesVisible()
/*      */   {
/* 1810 */     return this.domainMinorGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainMinorGridlinesVisible(boolean visible)
/*      */   {
/* 1827 */     if (this.domainMinorGridlinesVisible != visible) {
/* 1828 */       this.domainMinorGridlinesVisible = visible;
/* 1829 */       fireChangeEvent();
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
/*      */   public Stroke getDomainGridlineStroke()
/*      */   {
/* 1842 */     return this.domainGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 1857 */     if (stroke == null) {
/* 1858 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1860 */     this.domainGridlineStroke = stroke;
/* 1861 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getDomainMinorGridlineStroke()
/*      */   {
/* 1876 */     return this.domainMinorGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainMinorGridlineStroke(Stroke stroke)
/*      */   {
/* 1893 */     if (stroke == null) {
/* 1894 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1896 */     this.domainMinorGridlineStroke = stroke;
/* 1897 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDomainGridlinePaint()
/*      */   {
/* 1909 */     return this.domainGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 1924 */     if (paint == null) {
/* 1925 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1927 */     this.domainGridlinePaint = paint;
/* 1928 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDomainMinorGridlinePaint()
/*      */   {
/* 1942 */     return this.domainMinorGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainMinorGridlinePaint(Paint paint)
/*      */   {
/* 1959 */     if (paint == null) {
/* 1960 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1962 */     this.domainMinorGridlinePaint = paint;
/* 1963 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeGridlinesVisible()
/*      */   {
/* 1975 */     return this.rangeGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
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
/* 1990 */     if (this.rangeGridlinesVisible != visible) {
/* 1991 */       this.rangeGridlinesVisible = visible;
/* 1992 */       fireChangeEvent();
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
/*      */   public Stroke getRangeGridlineStroke()
/*      */   {
/* 2005 */     return this.rangeGridlineStroke;
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
/* 2017 */     if (stroke == null) {
/* 2018 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 2020 */     this.rangeGridlineStroke = stroke;
/* 2021 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeGridlinePaint()
/*      */   {
/* 2033 */     return this.rangeGridlinePaint;
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
/* 2045 */     if (paint == null) {
/* 2046 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2048 */     this.rangeGridlinePaint = paint;
/* 2049 */     fireChangeEvent();
/*      */   }
/*      */   
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
/* 2063 */     return this.rangeMinorGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/* 2080 */     if (this.rangeMinorGridlinesVisible != visible) {
/* 2081 */       this.rangeMinorGridlinesVisible = visible;
/* 2082 */       fireChangeEvent();
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
/* 2097 */     return this.rangeMinorGridlineStroke;
/*      */   }
/*      */   
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
/* 2111 */     if (stroke == null) {
/* 2112 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 2114 */     this.rangeMinorGridlineStroke = stroke;
/* 2115 */     fireChangeEvent();
/*      */   }
/*      */   
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
/* 2129 */     return this.rangeMinorGridlinePaint;
/*      */   }
/*      */   
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
/* 2143 */     if (paint == null) {
/* 2144 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2146 */     this.rangeMinorGridlinePaint = paint;
/* 2147 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainZeroBaselineVisible()
/*      */   {
/* 2161 */     return this.domainZeroBaselineVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainZeroBaselineVisible(boolean visible)
/*      */   {
/* 2176 */     this.domainZeroBaselineVisible = visible;
/* 2177 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getDomainZeroBaselineStroke()
/*      */   {
/* 2190 */     return this.domainZeroBaselineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainZeroBaselineStroke(Stroke stroke)
/*      */   {
/* 2204 */     if (stroke == null) {
/* 2205 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 2207 */     this.domainZeroBaselineStroke = stroke;
/* 2208 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDomainZeroBaselinePaint()
/*      */   {
/* 2222 */     return this.domainZeroBaselinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainZeroBaselinePaint(Paint paint)
/*      */   {
/* 2236 */     if (paint == null) {
/* 2237 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2239 */     this.domainZeroBaselinePaint = paint;
/* 2240 */     fireChangeEvent();
/*      */   }
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
/* 2252 */     return this.rangeZeroBaselineVisible;
/*      */   }
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
/* 2265 */     this.rangeZeroBaselineVisible = visible;
/* 2266 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getRangeZeroBaselineStroke()
/*      */   {
/* 2277 */     return this.rangeZeroBaselineStroke;
/*      */   }
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
/* 2289 */     if (stroke == null) {
/* 2290 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 2292 */     this.rangeZeroBaselineStroke = stroke;
/* 2293 */     fireChangeEvent();
/*      */   }
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
/* 2305 */     return this.rangeZeroBaselinePaint;
/*      */   }
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
/* 2317 */     if (paint == null) {
/* 2318 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 2320 */     this.rangeZeroBaselinePaint = paint;
/* 2321 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDomainTickBandPaint()
/*      */   {
/* 2333 */     return this.domainTickBandPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainTickBandPaint(Paint paint)
/*      */   {
/* 2344 */     this.domainTickBandPaint = paint;
/* 2345 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeTickBandPaint()
/*      */   {
/* 2357 */     return this.rangeTickBandPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeTickBandPaint(Paint paint)
/*      */   {
/* 2368 */     this.rangeTickBandPaint = paint;
/* 2369 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Point2D getQuadrantOrigin()
/*      */   {
/* 2381 */     return this.quadrantOrigin;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setQuadrantOrigin(Point2D origin)
/*      */   {
/* 2393 */     if (origin == null) {
/* 2394 */       throw new IllegalArgumentException("Null 'origin' argument.");
/*      */     }
/* 2396 */     this.quadrantOrigin = origin;
/* 2397 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getQuadrantPaint(int index)
/*      */   {
/* 2410 */     if ((index < 0) || (index > 3)) {
/* 2411 */       throw new IllegalArgumentException("The index value (" + index + ") should be in the range 0 to 3.");
/*      */     }
/*      */     
/* 2414 */     return this.quadrantPaint[index];
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setQuadrantPaint(int index, Paint paint)
/*      */   {
/* 2427 */     if ((index < 0) || (index > 3)) {
/* 2428 */       throw new IllegalArgumentException("The index value (" + index + ") should be in the range 0 to 3.");
/*      */     }
/*      */     
/* 2431 */     this.quadrantPaint[index] = paint;
/* 2432 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(Marker marker)
/*      */   {
/* 2449 */     addDomainMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(Marker marker, Layer layer)
/*      */   {
/* 2465 */     addDomainMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainMarkers()
/*      */   {
/* 2475 */     if (this.backgroundDomainMarkers != null) {
/* 2476 */       Set keys = this.backgroundDomainMarkers.keySet();
/* 2477 */       Iterator iterator = keys.iterator();
/* 2478 */       while (iterator.hasNext()) {
/* 2479 */         Integer key = (Integer)iterator.next();
/* 2480 */         clearDomainMarkers(key.intValue());
/*      */       }
/* 2482 */       this.backgroundDomainMarkers.clear();
/*      */     }
/* 2484 */     if (this.foregroundDomainMarkers != null) {
/* 2485 */       Set keys = this.foregroundDomainMarkers.keySet();
/* 2486 */       Iterator iterator = keys.iterator();
/* 2487 */       while (iterator.hasNext()) {
/* 2488 */         Integer key = (Integer)iterator.next();
/* 2489 */         clearDomainMarkers(key.intValue());
/*      */       }
/* 2491 */       this.foregroundDomainMarkers.clear();
/*      */     }
/* 2493 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainMarkers(int index)
/*      */   {
/* 2505 */     Integer key = new Integer(index);
/* 2506 */     if (this.backgroundDomainMarkers != null) {
/* 2507 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(key);
/*      */       
/* 2509 */       if (markers != null) {
/* 2510 */         Iterator iterator = markers.iterator();
/* 2511 */         while (iterator.hasNext()) {
/* 2512 */           Marker m = (Marker)iterator.next();
/* 2513 */           m.removeChangeListener(this);
/*      */         }
/* 2515 */         markers.clear();
/*      */       }
/*      */     }
/* 2518 */     if (this.foregroundRangeMarkers != null) {
/* 2519 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(key);
/*      */       
/* 2521 */       if (markers != null) {
/* 2522 */         Iterator iterator = markers.iterator();
/* 2523 */         while (iterator.hasNext()) {
/* 2524 */           Marker m = (Marker)iterator.next();
/* 2525 */           m.removeChangeListener(this);
/*      */         }
/* 2527 */         markers.clear();
/*      */       }
/*      */     }
/* 2530 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(int index, Marker marker, Layer layer)
/*      */   {
/* 2549 */     addDomainMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addDomainMarker(int index, Marker marker, Layer layer, boolean notify)
/*      */   {
/* 2569 */     if (marker == null) {
/* 2570 */       throw new IllegalArgumentException("Null 'marker' not permitted.");
/*      */     }
/* 2572 */     if (layer == null) {
/* 2573 */       throw new IllegalArgumentException("Null 'layer' not permitted.");
/*      */     }
/*      */     
/* 2576 */     if (layer == Layer.FOREGROUND) {
/* 2577 */       Collection markers = (Collection)this.foregroundDomainMarkers.get(new Integer(index));
/*      */       
/* 2579 */       if (markers == null) {
/* 2580 */         markers = new ArrayList();
/* 2581 */         this.foregroundDomainMarkers.put(new Integer(index), markers);
/*      */       }
/* 2583 */       markers.add(marker);
/*      */     }
/* 2585 */     else if (layer == Layer.BACKGROUND) {
/* 2586 */       Collection markers = (Collection)this.backgroundDomainMarkers.get(new Integer(index));
/*      */       
/* 2588 */       if (markers == null) {
/* 2589 */         markers = new ArrayList();
/* 2590 */         this.backgroundDomainMarkers.put(new Integer(index), markers);
/*      */       }
/* 2592 */       markers.add(marker);
/*      */     }
/* 2594 */     marker.addChangeListener(this);
/* 2595 */     if (notify) {
/* 2596 */       fireChangeEvent();
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
/*      */   public boolean removeDomainMarker(Marker marker)
/*      */   {
/* 2612 */     return removeDomainMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 2628 */     return removeDomainMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/* 2645 */     return removeDomainMarker(index, marker, layer, true);
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
/* 2665 */     if (layer == Layer.FOREGROUND) {
/* 2666 */       markers = (ArrayList)this.foregroundDomainMarkers.get(new Integer(index));
/*      */     }
/*      */     else
/*      */     {
/* 2670 */       markers = (ArrayList)this.backgroundDomainMarkers.get(new Integer(index));
/*      */     }
/*      */     
/* 2673 */     if (markers == null) {
/* 2674 */       return false;
/*      */     }
/* 2676 */     boolean removed = markers.remove(marker);
/* 2677 */     if ((removed) && (notify)) {
/* 2678 */       fireChangeEvent();
/*      */     }
/* 2680 */     return removed;
/*      */   }
/*      */   
/*      */ 
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
/* 2695 */     addRangeMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 2711 */     addRangeMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeMarkers()
/*      */   {
/* 2721 */     if (this.backgroundRangeMarkers != null) {
/* 2722 */       Set keys = this.backgroundRangeMarkers.keySet();
/* 2723 */       Iterator iterator = keys.iterator();
/* 2724 */       while (iterator.hasNext()) {
/* 2725 */         Integer key = (Integer)iterator.next();
/* 2726 */         clearRangeMarkers(key.intValue());
/*      */       }
/* 2728 */       this.backgroundRangeMarkers.clear();
/*      */     }
/* 2730 */     if (this.foregroundRangeMarkers != null) {
/* 2731 */       Set keys = this.foregroundRangeMarkers.keySet();
/* 2732 */       Iterator iterator = keys.iterator();
/* 2733 */       while (iterator.hasNext()) {
/* 2734 */         Integer key = (Integer)iterator.next();
/* 2735 */         clearRangeMarkers(key.intValue());
/*      */       }
/* 2737 */       this.foregroundRangeMarkers.clear();
/*      */     }
/* 2739 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2757 */     addRangeMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2777 */     if (layer == Layer.FOREGROUND) {
/* 2778 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(new Integer(index));
/*      */       
/* 2780 */       if (markers == null) {
/* 2781 */         markers = new ArrayList();
/* 2782 */         this.foregroundRangeMarkers.put(new Integer(index), markers);
/*      */       }
/* 2784 */       markers.add(marker);
/*      */     }
/* 2786 */     else if (layer == Layer.BACKGROUND) {
/* 2787 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(new Integer(index));
/*      */       
/* 2789 */       if (markers == null) {
/* 2790 */         markers = new ArrayList();
/* 2791 */         this.backgroundRangeMarkers.put(new Integer(index), markers);
/*      */       }
/* 2793 */       markers.add(marker);
/*      */     }
/* 2795 */     marker.addChangeListener(this);
/* 2796 */     if (notify) {
/* 2797 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeMarkers(int index)
/*      */   {
/* 2808 */     Integer key = new Integer(index);
/* 2809 */     if (this.backgroundRangeMarkers != null) {
/* 2810 */       Collection markers = (Collection)this.backgroundRangeMarkers.get(key);
/*      */       
/* 2812 */       if (markers != null) {
/* 2813 */         Iterator iterator = markers.iterator();
/* 2814 */         while (iterator.hasNext()) {
/* 2815 */           Marker m = (Marker)iterator.next();
/* 2816 */           m.removeChangeListener(this);
/*      */         }
/* 2818 */         markers.clear();
/*      */       }
/*      */     }
/* 2821 */     if (this.foregroundRangeMarkers != null) {
/* 2822 */       Collection markers = (Collection)this.foregroundRangeMarkers.get(key);
/*      */       
/* 2824 */       if (markers != null) {
/* 2825 */         Iterator iterator = markers.iterator();
/* 2826 */         while (iterator.hasNext()) {
/* 2827 */           Marker m = (Marker)iterator.next();
/* 2828 */           m.removeChangeListener(this);
/*      */         }
/* 2830 */         markers.clear();
/*      */       }
/*      */     }
/* 2833 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
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
/* 2848 */     return removeRangeMarker(marker, Layer.FOREGROUND);
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 2864 */     return removeRangeMarker(0, marker, layer);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/* 2881 */     return removeRangeMarker(index, marker, layer, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2900 */     if (marker == null)
/* 2901 */       throw new IllegalArgumentException("Null 'marker' argument.");
/*      */     ArrayList markers;
/*      */     ArrayList markers;
/* 2904 */     if (layer == Layer.FOREGROUND) {
/* 2905 */       markers = (ArrayList)this.foregroundRangeMarkers.get(new Integer(index));
/*      */     }
/*      */     else
/*      */     {
/* 2909 */       markers = (ArrayList)this.backgroundRangeMarkers.get(new Integer(index));
/*      */     }
/*      */     
/* 2912 */     if (markers == null) {
/* 2913 */       return false;
/*      */     }
/* 2915 */     boolean removed = markers.remove(marker);
/* 2916 */     if ((removed) && (notify)) {
/* 2917 */       fireChangeEvent();
/*      */     }
/* 2919 */     return removed;
/*      */   }
/*      */   
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
/* 2932 */     addAnnotation(annotation, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addAnnotation(XYAnnotation annotation, boolean notify)
/*      */   {
/* 2945 */     if (annotation == null) {
/* 2946 */       throw new IllegalArgumentException("Null 'annotation' argument.");
/*      */     }
/* 2948 */     this.annotations.add(annotation);
/* 2949 */     if (notify) {
/* 2950 */       fireChangeEvent();
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
/*      */   public boolean removeAnnotation(XYAnnotation annotation)
/*      */   {
/* 2966 */     return removeAnnotation(annotation, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean removeAnnotation(XYAnnotation annotation, boolean notify)
/*      */   {
/* 2981 */     if (annotation == null) {
/* 2982 */       throw new IllegalArgumentException("Null 'annotation' argument.");
/*      */     }
/* 2984 */     boolean removed = this.annotations.remove(annotation);
/* 2985 */     if ((removed) && (notify)) {
/* 2986 */       fireChangeEvent();
/*      */     }
/* 2988 */     return removed;
/*      */   }
/*      */   
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
/* 3001 */     return new ArrayList(this.annotations);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearAnnotations()
/*      */   {
/* 3011 */     this.annotations.clear();
/* 3012 */     fireChangeEvent();
/*      */   }
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
/* 3025 */     AxisSpace space = new AxisSpace();
/* 3026 */     space = calculateRangeAxisSpace(g2, plotArea, space);
/* 3027 */     Rectangle2D revPlotArea = space.shrink(plotArea, null);
/* 3028 */     space = calculateDomainAxisSpace(g2, revPlotArea, space);
/* 3029 */     return space;
/*      */   }
/*      */   
/*      */ 
/*      */ 
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
/* 3045 */     if (space == null) {
/* 3046 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/* 3050 */     if (this.fixedDomainAxisSpace != null) {
/* 3051 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3052 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getLeft(), RectangleEdge.LEFT);
/*      */         
/* 3054 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getRight(), RectangleEdge.RIGHT);
/*      */ 
/*      */       }
/* 3057 */       else if (this.orientation == PlotOrientation.VERTICAL) {
/* 3058 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getTop(), RectangleEdge.TOP);
/*      */         
/* 3060 */         space.ensureAtLeast(this.fixedDomainAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else {
/* 3066 */       for (int i = 0; i < this.domainAxes.size(); i++) {
/* 3067 */         Axis axis = (Axis)this.domainAxes.get(i);
/* 3068 */         if (axis != null) {
/* 3069 */           RectangleEdge edge = getDomainAxisEdge(i);
/* 3070 */           space = axis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3075 */     return space;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/* 3092 */     if (space == null) {
/* 3093 */       space = new AxisSpace();
/*      */     }
/*      */     
/*      */ 
/* 3097 */     if (this.fixedRangeAxisSpace != null) {
/* 3098 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3099 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getTop(), RectangleEdge.TOP);
/*      */         
/* 3101 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getBottom(), RectangleEdge.BOTTOM);
/*      */ 
/*      */       }
/* 3104 */       else if (this.orientation == PlotOrientation.VERTICAL) {
/* 3105 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getLeft(), RectangleEdge.LEFT);
/*      */         
/* 3107 */         space.ensureAtLeast(this.fixedRangeAxisSpace.getRight(), RectangleEdge.RIGHT);
/*      */       }
/*      */       
/*      */ 
/*      */     }
/*      */     else {
/* 3113 */       for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 3114 */         Axis axis = (Axis)this.rangeAxes.get(i);
/* 3115 */         if (axis != null) {
/* 3116 */           RectangleEdge edge = getRangeAxisEdge(i);
/* 3117 */           space = axis.reserveSpace(g2, this, plotArea, edge, space);
/*      */         }
/*      */       }
/*      */     }
/* 3121 */     return space;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 3141 */     boolean b1 = area.getWidth() <= 10.0D;
/* 3142 */     boolean b2 = area.getHeight() <= 10.0D;
/* 3143 */     if ((b1) || (b2)) {
/* 3144 */       return;
/*      */     }
/*      */     
/*      */ 
/* 3148 */     if (info != null) {
/* 3149 */       info.setPlotArea(area);
/*      */     }
/*      */     
/*      */ 
/* 3153 */     RectangleInsets insets = getInsets();
/* 3154 */     insets.trim(area);
/*      */     
/* 3156 */     AxisSpace space = calculateAxisSpace(g2, area);
/* 3157 */     Rectangle2D dataArea = space.shrink(area, null);
/* 3158 */     this.axisOffset.trim(dataArea);
/* 3159 */     createAndAddEntity((Rectangle2D)dataArea.clone(), info, null, null);
/* 3160 */     if (info != null) {
/* 3161 */       info.setDataArea(dataArea);
/*      */     }
/*      */     
/*      */ 
/* 3165 */     drawBackground(g2, dataArea);
/* 3166 */     Map axisStateMap = drawAxes(g2, area, dataArea, info);
/*      */     
/* 3168 */     PlotOrientation orient = getOrientation();
/*      */     
/*      */ 
/*      */ 
/* 3172 */     if ((anchor != null) && (!dataArea.contains(anchor))) {
/* 3173 */       anchor = null;
/*      */     }
/* 3175 */     CrosshairState crosshairState = new CrosshairState();
/* 3176 */     crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
/* 3177 */     crosshairState.setAnchor(anchor);
/*      */     
/* 3179 */     crosshairState.setAnchorX(NaN.0D);
/* 3180 */     crosshairState.setAnchorY(NaN.0D);
/* 3181 */     if (anchor != null) {
/* 3182 */       ValueAxis domainAxis = getDomainAxis();
/* 3183 */       if (domainAxis != null) { double x;
/*      */         double x;
/* 3185 */         if (orient == PlotOrientation.VERTICAL) {
/* 3186 */           x = domainAxis.java2DToValue(anchor.getX(), dataArea, getDomainAxisEdge());
/*      */         }
/*      */         else
/*      */         {
/* 3190 */           x = domainAxis.java2DToValue(anchor.getY(), dataArea, getDomainAxisEdge());
/*      */         }
/*      */         
/* 3193 */         crosshairState.setAnchorX(x);
/*      */       }
/* 3195 */       ValueAxis rangeAxis = getRangeAxis();
/* 3196 */       if (rangeAxis != null) { double y;
/*      */         double y;
/* 3198 */         if (orient == PlotOrientation.VERTICAL) {
/* 3199 */           y = rangeAxis.java2DToValue(anchor.getY(), dataArea, getRangeAxisEdge());
/*      */         }
/*      */         else
/*      */         {
/* 3203 */           y = rangeAxis.java2DToValue(anchor.getX(), dataArea, getRangeAxisEdge());
/*      */         }
/*      */         
/* 3206 */         crosshairState.setAnchorY(y);
/*      */       }
/*      */     }
/* 3209 */     crosshairState.setCrosshairX(getDomainCrosshairValue());
/* 3210 */     crosshairState.setCrosshairY(getRangeCrosshairValue());
/* 3211 */     Shape originalClip = g2.getClip();
/* 3212 */     Composite originalComposite = g2.getComposite();
/*      */     
/* 3214 */     g2.clip(dataArea);
/* 3215 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*      */ 
/* 3218 */     AxisState domainAxisState = (AxisState)axisStateMap.get(getDomainAxis());
/*      */     
/* 3220 */     if ((domainAxisState == null) && 
/* 3221 */       (parentState != null)) {
/* 3222 */       domainAxisState = (AxisState)parentState.getSharedAxisStates().get(getDomainAxis());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3227 */     AxisState rangeAxisState = (AxisState)axisStateMap.get(getRangeAxis());
/* 3228 */     if ((rangeAxisState == null) && 
/* 3229 */       (parentState != null)) {
/* 3230 */       rangeAxisState = (AxisState)parentState.getSharedAxisStates().get(getRangeAxis());
/*      */     }
/*      */     
/*      */ 
/* 3234 */     if (domainAxisState != null) {
/* 3235 */       drawDomainTickBands(g2, dataArea, domainAxisState.getTicks());
/*      */     }
/* 3237 */     if (rangeAxisState != null) {
/* 3238 */       drawRangeTickBands(g2, dataArea, rangeAxisState.getTicks());
/*      */     }
/* 3240 */     if (domainAxisState != null) {
/* 3241 */       drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
/* 3242 */       drawZeroDomainBaseline(g2, dataArea);
/*      */     }
/* 3244 */     if (rangeAxisState != null) {
/* 3245 */       drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
/* 3246 */       drawZeroRangeBaseline(g2, dataArea);
/*      */     }
/*      */     
/*      */ 
/* 3250 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3251 */       drawDomainMarkers(g2, dataArea, i, Layer.BACKGROUND);
/*      */     }
/* 3253 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3254 */       drawRangeMarkers(g2, dataArea, i, Layer.BACKGROUND);
/*      */     }
/*      */     
/*      */ 
/* 3258 */     boolean foundData = false;
/* 3259 */     DatasetRenderingOrder order = getDatasetRenderingOrder();
/* 3260 */     if (order == DatasetRenderingOrder.FORWARD)
/*      */     {
/*      */ 
/* 3263 */       int rendererCount = this.renderers.size();
/* 3264 */       for (int i = 0; i < rendererCount; i++) {
/* 3265 */         XYItemRenderer r = getRenderer(i);
/* 3266 */         if (r != null) {
/* 3267 */           ValueAxis domainAxis = getDomainAxisForDataset(i);
/* 3268 */           ValueAxis rangeAxis = getRangeAxisForDataset(i);
/* 3269 */           r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.BACKGROUND, info);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3275 */       for (int i = 0; i < getDatasetCount(); i++) {
/* 3276 */         foundData = (render(g2, dataArea, i, info, crosshairState)) || (foundData);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3281 */       for (int i = 0; i < rendererCount; i++) {
/* 3282 */         XYItemRenderer r = getRenderer(i);
/* 3283 */         if (r != null) {
/* 3284 */           ValueAxis domainAxis = getDomainAxisForDataset(i);
/* 3285 */           ValueAxis rangeAxis = getRangeAxisForDataset(i);
/* 3286 */           r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.FOREGROUND, info);
/*      */         }
/*      */         
/*      */       }
/*      */       
/*      */     }
/* 3292 */     else if (order == DatasetRenderingOrder.REVERSE)
/*      */     {
/*      */ 
/* 3295 */       int rendererCount = this.renderers.size();
/* 3296 */       for (int i = rendererCount - 1; i >= 0; i--) {
/* 3297 */         XYItemRenderer r = getRenderer(i);
/* 3298 */         if (i < getDatasetCount())
/*      */         {
/*      */ 
/* 3301 */           if (r != null) {
/* 3302 */             ValueAxis domainAxis = getDomainAxisForDataset(i);
/* 3303 */             ValueAxis rangeAxis = getRangeAxisForDataset(i);
/* 3304 */             r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.BACKGROUND, info);
/*      */           }
/*      */         }
/*      */       }
/*      */       
/* 3309 */       for (int i = getDatasetCount() - 1; i >= 0; i--) {
/* 3310 */         foundData = (render(g2, dataArea, i, info, crosshairState)) || (foundData);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/* 3315 */       for (int i = rendererCount - 1; i >= 0; i--) {
/* 3316 */         XYItemRenderer r = getRenderer(i);
/* 3317 */         if (i < getDatasetCount())
/*      */         {
/*      */ 
/* 3320 */           if (r != null) {
/* 3321 */             ValueAxis domainAxis = getDomainAxisForDataset(i);
/* 3322 */             ValueAxis rangeAxis = getRangeAxisForDataset(i);
/* 3323 */             r.drawAnnotations(g2, dataArea, domainAxis, rangeAxis, Layer.FOREGROUND, info);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 3331 */     int xAxisIndex = crosshairState.getDomainAxisIndex();
/* 3332 */     ValueAxis xAxis = getDomainAxis(xAxisIndex);
/* 3333 */     RectangleEdge xAxisEdge = getDomainAxisEdge(xAxisIndex);
/* 3334 */     if ((!this.domainCrosshairLockedOnData) && (anchor != null)) { double xx;
/*      */       double xx;
/* 3336 */       if (orient == PlotOrientation.VERTICAL) {
/* 3337 */         xx = xAxis.java2DToValue(anchor.getX(), dataArea, xAxisEdge);
/*      */       }
/*      */       else {
/* 3340 */         xx = xAxis.java2DToValue(anchor.getY(), dataArea, xAxisEdge);
/*      */       }
/* 3342 */       crosshairState.setCrosshairX(xx);
/*      */     }
/* 3344 */     setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
/* 3345 */     if (isDomainCrosshairVisible()) {
/* 3346 */       double x = getDomainCrosshairValue();
/* 3347 */       Paint paint = getDomainCrosshairPaint();
/* 3348 */       Stroke stroke = getDomainCrosshairStroke();
/* 3349 */       drawDomainCrosshair(g2, dataArea, orient, x, xAxis, stroke, paint);
/*      */     }
/*      */     
/*      */ 
/* 3353 */     int yAxisIndex = crosshairState.getRangeAxisIndex();
/* 3354 */     ValueAxis yAxis = getRangeAxis(yAxisIndex);
/* 3355 */     RectangleEdge yAxisEdge = getRangeAxisEdge(yAxisIndex);
/* 3356 */     if ((!this.rangeCrosshairLockedOnData) && (anchor != null)) { double yy;
/*      */       double yy;
/* 3358 */       if (orient == PlotOrientation.VERTICAL) {
/* 3359 */         yy = yAxis.java2DToValue(anchor.getY(), dataArea, yAxisEdge);
/*      */       } else {
/* 3361 */         yy = yAxis.java2DToValue(anchor.getX(), dataArea, yAxisEdge);
/*      */       }
/* 3363 */       crosshairState.setCrosshairY(yy);
/*      */     }
/* 3365 */     setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
/* 3366 */     if (isRangeCrosshairVisible()) {
/* 3367 */       double y = getRangeCrosshairValue();
/* 3368 */       Paint paint = getRangeCrosshairPaint();
/* 3369 */       Stroke stroke = getRangeCrosshairStroke();
/* 3370 */       drawRangeCrosshair(g2, dataArea, orient, y, yAxis, stroke, paint);
/*      */     }
/*      */     
/* 3373 */     if (!foundData) {
/* 3374 */       drawNoDataMessage(g2, dataArea);
/*      */     }
/*      */     
/* 3377 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3378 */       drawDomainMarkers(g2, dataArea, i, Layer.FOREGROUND);
/*      */     }
/* 3380 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 3381 */       drawRangeMarkers(g2, dataArea, i, Layer.FOREGROUND);
/*      */     }
/*      */     
/* 3384 */     drawAnnotations(g2, dataArea, info);
/* 3385 */     g2.setClip(originalClip);
/* 3386 */     g2.setComposite(originalComposite);
/*      */     
/* 3388 */     drawOutline(g2, dataArea);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawBackground(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 3399 */     fillBackground(g2, area, this.orientation);
/* 3400 */     drawQuadrants(g2, area);
/* 3401 */     drawBackgroundImage(g2, area);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawQuadrants(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 3417 */     boolean somethingToDraw = false;
/*      */     
/* 3419 */     ValueAxis xAxis = getDomainAxis();
/* 3420 */     if (xAxis == null) {
/* 3421 */       return;
/*      */     }
/* 3423 */     double x = xAxis.getRange().constrain(this.quadrantOrigin.getX());
/* 3424 */     double xx = xAxis.valueToJava2D(x, area, getDomainAxisEdge());
/*      */     
/* 3426 */     ValueAxis yAxis = getRangeAxis();
/* 3427 */     if (yAxis == null) {
/* 3428 */       return;
/*      */     }
/* 3430 */     double y = yAxis.getRange().constrain(this.quadrantOrigin.getY());
/* 3431 */     double yy = yAxis.valueToJava2D(y, area, getRangeAxisEdge());
/*      */     
/* 3433 */     double xmin = xAxis.getLowerBound();
/* 3434 */     double xxmin = xAxis.valueToJava2D(xmin, area, getDomainAxisEdge());
/*      */     
/* 3436 */     double xmax = xAxis.getUpperBound();
/* 3437 */     double xxmax = xAxis.valueToJava2D(xmax, area, getDomainAxisEdge());
/*      */     
/* 3439 */     double ymin = yAxis.getLowerBound();
/* 3440 */     double yymin = yAxis.valueToJava2D(ymin, area, getRangeAxisEdge());
/*      */     
/* 3442 */     double ymax = yAxis.getUpperBound();
/* 3443 */     double yymax = yAxis.valueToJava2D(ymax, area, getRangeAxisEdge());
/*      */     
/* 3445 */     Rectangle2D[] r = { null, null, null, null };
/* 3446 */     if ((this.quadrantPaint[0] != null) && 
/* 3447 */       (x > xmin) && (y < ymax)) {
/* 3448 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3449 */         r[0] = new Rectangle2D.Double(Math.min(yymax, yy), Math.min(xxmin, xx), Math.abs(yy - yymax), Math.abs(xx - xxmin));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 3454 */         r[0] = new Rectangle2D.Double(Math.min(xxmin, xx), Math.min(yymax, yy), Math.abs(xx - xxmin), Math.abs(yy - yymax));
/*      */       }
/*      */       
/*      */ 
/* 3458 */       somethingToDraw = true;
/*      */     }
/*      */     
/* 3461 */     if ((this.quadrantPaint[1] != null) && 
/* 3462 */       (x < xmax) && (y < ymax)) {
/* 3463 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3464 */         r[1] = new Rectangle2D.Double(Math.min(yymax, yy), Math.min(xxmax, xx), Math.abs(yy - yymax), Math.abs(xx - xxmax));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 3469 */         r[1] = new Rectangle2D.Double(Math.min(xx, xxmax), Math.min(yymax, yy), Math.abs(xx - xxmax), Math.abs(yy - yymax));
/*      */       }
/*      */       
/*      */ 
/* 3473 */       somethingToDraw = true;
/*      */     }
/*      */     
/* 3476 */     if ((this.quadrantPaint[2] != null) && 
/* 3477 */       (x > xmin) && (y > ymin)) {
/* 3478 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3479 */         r[2] = new Rectangle2D.Double(Math.min(yymin, yy), Math.min(xxmin, xx), Math.abs(yy - yymin), Math.abs(xx - xxmin));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 3484 */         r[2] = new Rectangle2D.Double(Math.min(xxmin, xx), Math.min(yymin, yy), Math.abs(xx - xxmin), Math.abs(yy - yymin));
/*      */       }
/*      */       
/*      */ 
/* 3488 */       somethingToDraw = true;
/*      */     }
/*      */     
/* 3491 */     if ((this.quadrantPaint[3] != null) && 
/* 3492 */       (x < xmax) && (y > ymin)) {
/* 3493 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 3494 */         r[3] = new Rectangle2D.Double(Math.min(yymin, yy), Math.min(xxmax, xx), Math.abs(yy - yymin), Math.abs(xx - xxmax));
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/* 3499 */         r[3] = new Rectangle2D.Double(Math.min(xx, xxmax), Math.min(yymin, yy), Math.abs(xx - xxmax), Math.abs(yy - yymin));
/*      */       }
/*      */       
/*      */ 
/* 3503 */       somethingToDraw = true;
/*      */     }
/*      */     
/* 3506 */     if (somethingToDraw) {
/* 3507 */       Composite originalComposite = g2.getComposite();
/* 3508 */       g2.setComposite(AlphaComposite.getInstance(3, getBackgroundAlpha()));
/*      */       
/* 3510 */       for (int i = 0; i < 4; i++) {
/* 3511 */         if ((this.quadrantPaint[i] != null) && (r[i] != null)) {
/* 3512 */           g2.setPaint(this.quadrantPaint[i]);
/* 3513 */           g2.fill(r[i]);
/*      */         }
/*      */       }
/* 3516 */       g2.setComposite(originalComposite);
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
/*      */   public void drawDomainTickBands(Graphics2D g2, Rectangle2D dataArea, List ticks)
/*      */   {
/* 3531 */     Paint bandPaint = getDomainTickBandPaint();
/* 3532 */     if (bandPaint != null) {
/* 3533 */       boolean fillBand = false;
/* 3534 */       ValueAxis xAxis = getDomainAxis();
/* 3535 */       double previous = xAxis.getLowerBound();
/* 3536 */       Iterator iterator = ticks.iterator();
/* 3537 */       while (iterator.hasNext()) {
/* 3538 */         ValueTick tick = (ValueTick)iterator.next();
/* 3539 */         double current = tick.getValue();
/* 3540 */         if (fillBand) {
/* 3541 */           getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, previous, current);
/*      */         }
/*      */         
/* 3544 */         previous = current;
/* 3545 */         fillBand = !fillBand;
/*      */       }
/* 3547 */       double end = xAxis.getUpperBound();
/* 3548 */       if (fillBand) {
/* 3549 */         getRenderer().fillDomainGridBand(g2, this, xAxis, dataArea, previous, end);
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
/*      */   public void drawRangeTickBands(Graphics2D g2, Rectangle2D dataArea, List ticks)
/*      */   {
/* 3566 */     Paint bandPaint = getRangeTickBandPaint();
/* 3567 */     if (bandPaint != null) {
/* 3568 */       boolean fillBand = false;
/* 3569 */       ValueAxis axis = getRangeAxis();
/* 3570 */       double previous = axis.getLowerBound();
/* 3571 */       Iterator iterator = ticks.iterator();
/* 3572 */       while (iterator.hasNext()) {
/* 3573 */         ValueTick tick = (ValueTick)iterator.next();
/* 3574 */         double current = tick.getValue();
/* 3575 */         if (fillBand) {
/* 3576 */           getRenderer().fillRangeGridBand(g2, this, axis, dataArea, previous, current);
/*      */         }
/*      */         
/* 3579 */         previous = current;
/* 3580 */         fillBand = !fillBand;
/*      */       }
/* 3582 */       double end = axis.getUpperBound();
/* 3583 */       if (fillBand) {
/* 3584 */         getRenderer().fillRangeGridBand(g2, this, axis, dataArea, previous, end);
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
/*      */   protected Map drawAxes(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea, PlotRenderingInfo plotState)
/*      */   {
/* 3606 */     AxisCollection axisCollection = new AxisCollection();
/*      */     
/*      */ 
/* 3609 */     for (int index = 0; index < this.domainAxes.size(); index++) {
/* 3610 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(index);
/* 3611 */       if (axis != null) {
/* 3612 */         axisCollection.add(axis, getDomainAxisEdge(index));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 3617 */     for (int index = 0; index < this.rangeAxes.size(); index++) {
/* 3618 */       ValueAxis yAxis = (ValueAxis)this.rangeAxes.get(index);
/* 3619 */       if (yAxis != null) {
/* 3620 */         axisCollection.add(yAxis, getRangeAxisEdge(index));
/*      */       }
/*      */     }
/*      */     
/* 3624 */     Map axisStateMap = new HashMap();
/*      */     
/*      */ 
/* 3627 */     double cursor = dataArea.getMinY() - this.axisOffset.calculateTopOutset(dataArea.getHeight());
/*      */     
/* 3629 */     Iterator iterator = axisCollection.getAxesAtTop().iterator();
/* 3630 */     while (iterator.hasNext()) {
/* 3631 */       ValueAxis axis = (ValueAxis)iterator.next();
/* 3632 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.TOP, plotState);
/*      */       
/* 3634 */       cursor = info.getCursor();
/* 3635 */       axisStateMap.put(axis, info);
/*      */     }
/*      */     
/*      */ 
/* 3639 */     cursor = dataArea.getMaxY() + this.axisOffset.calculateBottomOutset(dataArea.getHeight());
/*      */     
/* 3641 */     iterator = axisCollection.getAxesAtBottom().iterator();
/* 3642 */     while (iterator.hasNext()) {
/* 3643 */       ValueAxis axis = (ValueAxis)iterator.next();
/* 3644 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.BOTTOM, plotState);
/*      */       
/* 3646 */       cursor = info.getCursor();
/* 3647 */       axisStateMap.put(axis, info);
/*      */     }
/*      */     
/*      */ 
/* 3651 */     cursor = dataArea.getMinX() - this.axisOffset.calculateLeftOutset(dataArea.getWidth());
/*      */     
/* 3653 */     iterator = axisCollection.getAxesAtLeft().iterator();
/* 3654 */     while (iterator.hasNext()) {
/* 3655 */       ValueAxis axis = (ValueAxis)iterator.next();
/* 3656 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.LEFT, plotState);
/*      */       
/* 3658 */       cursor = info.getCursor();
/* 3659 */       axisStateMap.put(axis, info);
/*      */     }
/*      */     
/*      */ 
/* 3663 */     cursor = dataArea.getMaxX() + this.axisOffset.calculateRightOutset(dataArea.getWidth());
/*      */     
/* 3665 */     iterator = axisCollection.getAxesAtRight().iterator();
/* 3666 */     while (iterator.hasNext()) {
/* 3667 */       ValueAxis axis = (ValueAxis)iterator.next();
/* 3668 */       AxisState info = axis.draw(g2, cursor, plotArea, dataArea, RectangleEdge.RIGHT, plotState);
/*      */       
/* 3670 */       cursor = info.getCursor();
/* 3671 */       axisStateMap.put(axis, info);
/*      */     }
/*      */     
/* 3674 */     return axisStateMap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean render(Graphics2D g2, Rectangle2D dataArea, int index, PlotRenderingInfo info, CrosshairState crosshairState)
/*      */   {
/* 3696 */     boolean foundData = false;
/* 3697 */     XYDataset dataset = getDataset(index);
/* 3698 */     if (!DatasetUtilities.isEmptyOrNull(dataset)) {
/* 3699 */       foundData = true;
/* 3700 */       ValueAxis xAxis = getDomainAxisForDataset(index);
/* 3701 */       ValueAxis yAxis = getRangeAxisForDataset(index);
/* 3702 */       if ((xAxis == null) || (yAxis == null)) {
/* 3703 */         return foundData;
/*      */       }
/* 3705 */       XYItemRenderer renderer = getRenderer(index);
/* 3706 */       if (renderer == null) {
/* 3707 */         renderer = getRenderer();
/* 3708 */         if (renderer == null) {
/* 3709 */           return foundData;
/*      */         }
/*      */       }
/*      */       
/* 3713 */       XYItemRendererState state = renderer.initialise(g2, dataArea, this, dataset, info);
/*      */       
/* 3715 */       int passCount = renderer.getPassCount();
/*      */       
/* 3717 */       SeriesRenderingOrder seriesOrder = getSeriesRenderingOrder();
/* 3718 */       if (seriesOrder == SeriesRenderingOrder.REVERSE)
/*      */       {
/* 3720 */         for (int pass = 0; pass < passCount; pass++) {
/* 3721 */           int seriesCount = dataset.getSeriesCount();
/* 3722 */           for (int series = seriesCount - 1; series >= 0; series--) {
/* 3723 */             int firstItem = 0;
/* 3724 */             int lastItem = dataset.getItemCount(series) - 1;
/* 3725 */             if (lastItem != -1)
/*      */             {
/*      */ 
/* 3728 */               if (state.getProcessVisibleItemsOnly()) {
/* 3729 */                 int[] itemBounds = RendererUtilities.findLiveItems(dataset, series, xAxis.getLowerBound(), xAxis.getUpperBound());
/*      */                 
/*      */ 
/* 3732 */                 firstItem = Math.max(itemBounds[0] - 1, 0);
/* 3733 */                 lastItem = Math.min(itemBounds[1] + 1, lastItem);
/*      */               }
/* 3735 */               state.startSeriesPass(dataset, series, firstItem, lastItem, pass, passCount);
/*      */               
/* 3737 */               for (int item = firstItem; item <= lastItem; item++) {
/* 3738 */                 renderer.drawItem(g2, state, dataArea, info, this, xAxis, yAxis, dataset, series, item, crosshairState, pass);
/*      */               }
/*      */               
/*      */ 
/* 3742 */               state.endSeriesPass(dataset, series, firstItem, lastItem, pass, passCount);
/*      */             }
/*      */             
/*      */           }
/*      */           
/*      */         }
/*      */       } else {
/* 3749 */         for (int pass = 0; pass < passCount; pass++) {
/* 3750 */           int seriesCount = dataset.getSeriesCount();
/* 3751 */           for (int series = 0; series < seriesCount; series++) {
/* 3752 */             int firstItem = 0;
/* 3753 */             int lastItem = dataset.getItemCount(series) - 1;
/* 3754 */             if (state.getProcessVisibleItemsOnly()) {
/* 3755 */               int[] itemBounds = RendererUtilities.findLiveItems(dataset, series, xAxis.getLowerBound(), xAxis.getUpperBound());
/*      */               
/*      */ 
/* 3758 */               firstItem = Math.max(itemBounds[0] - 1, 0);
/* 3759 */               lastItem = Math.min(itemBounds[1] + 1, lastItem);
/*      */             }
/* 3761 */             state.startSeriesPass(dataset, series, firstItem, lastItem, pass, passCount);
/*      */             
/* 3763 */             for (int item = firstItem; item <= lastItem; item++) {
/* 3764 */               renderer.drawItem(g2, state, dataArea, info, this, xAxis, yAxis, dataset, series, item, crosshairState, pass);
/*      */             }
/*      */             
/*      */ 
/* 3768 */             state.endSeriesPass(dataset, series, firstItem, lastItem, pass, passCount);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 3774 */     return foundData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getDomainAxisForDataset(int index)
/*      */   {
/* 3785 */     int upper = Math.max(getDatasetCount(), getRendererCount());
/* 3786 */     if ((index < 0) || (index >= upper)) {
/* 3787 */       throw new IllegalArgumentException("Index " + index + " out of bounds.");
/*      */     }
/*      */     
/* 3790 */     ValueAxis valueAxis = null;
/* 3791 */     List axisIndices = (List)this.datasetToDomainAxesMap.get(new Integer(index));
/*      */     
/* 3793 */     if (axisIndices != null)
/*      */     {
/* 3795 */       Integer axisIndex = (Integer)axisIndices.get(0);
/* 3796 */       valueAxis = getDomainAxis(axisIndex.intValue());
/*      */     }
/*      */     else {
/* 3799 */       valueAxis = getDomainAxis(0);
/*      */     }
/* 3801 */     return valueAxis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getRangeAxisForDataset(int index)
/*      */   {
/* 3812 */     int upper = Math.max(getDatasetCount(), getRendererCount());
/* 3813 */     if ((index < 0) || (index >= upper)) {
/* 3814 */       throw new IllegalArgumentException("Index " + index + " out of bounds.");
/*      */     }
/*      */     
/* 3817 */     ValueAxis valueAxis = null;
/* 3818 */     List axisIndices = (List)this.datasetToRangeAxesMap.get(new Integer(index));
/*      */     
/* 3820 */     if (axisIndices != null)
/*      */     {
/* 3822 */       Integer axisIndex = (Integer)axisIndices.get(0);
/* 3823 */       valueAxis = getRangeAxis(axisIndex.intValue());
/*      */     }
/*      */     else {
/* 3826 */       valueAxis = getRangeAxis(0);
/*      */     }
/* 3828 */     return valueAxis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks)
/*      */   {
/* 3844 */     if (getRenderer() == null) {
/* 3845 */       return;
/*      */     }
/*      */     
/*      */ 
/* 3849 */     if ((isDomainGridlinesVisible()) || (isDomainMinorGridlinesVisible())) {
/* 3850 */       Stroke gridStroke = null;
/* 3851 */       Paint gridPaint = null;
/* 3852 */       Iterator iterator = ticks.iterator();
/* 3853 */       boolean paintLine = false;
/* 3854 */       while (iterator.hasNext()) {
/* 3855 */         paintLine = false;
/* 3856 */         ValueTick tick = (ValueTick)iterator.next();
/* 3857 */         if ((tick.getTickType() == TickType.MINOR) && (isDomainMinorGridlinesVisible())) {
/* 3858 */           gridStroke = getDomainMinorGridlineStroke();
/* 3859 */           gridPaint = getDomainMinorGridlinePaint();
/* 3860 */           paintLine = true;
/*      */         }
/* 3862 */         else if ((tick.getTickType() == TickType.MAJOR) && (isDomainGridlinesVisible())) {
/* 3863 */           gridStroke = getDomainGridlineStroke();
/* 3864 */           gridPaint = getDomainGridlinePaint();
/* 3865 */           paintLine = true;
/*      */         }
/* 3867 */         XYItemRenderer r = getRenderer();
/* 3868 */         if (((r instanceof AbstractXYItemRenderer)) && (paintLine)) {
/* 3869 */           ((AbstractXYItemRenderer)r).drawDomainLine(g2, this, getDomainAxis(), dataArea, tick.getValue(), gridPaint, gridStroke);
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
/*      */   protected void drawRangeGridlines(Graphics2D g2, Rectangle2D area, List ticks)
/*      */   {
/* 3891 */     if (getRenderer() == null) {
/* 3892 */       return;
/*      */     }
/*      */     
/*      */ 
/* 3896 */     if ((isRangeGridlinesVisible()) || (isRangeMinorGridlinesVisible())) {
/* 3897 */       Stroke gridStroke = null;
/* 3898 */       Paint gridPaint = null;
/* 3899 */       ValueAxis axis = getRangeAxis();
/* 3900 */       if (axis != null) {
/* 3901 */         Iterator iterator = ticks.iterator();
/* 3902 */         boolean paintLine = false;
/* 3903 */         while (iterator.hasNext()) {
/* 3904 */           paintLine = false;
/* 3905 */           ValueTick tick = (ValueTick)iterator.next();
/* 3906 */           if ((tick.getTickType() == TickType.MINOR) && (isRangeMinorGridlinesVisible()))
/*      */           {
/* 3908 */             gridStroke = getRangeMinorGridlineStroke();
/* 3909 */             gridPaint = getRangeMinorGridlinePaint();
/* 3910 */             paintLine = true;
/*      */           }
/* 3912 */           else if ((tick.getTickType() == TickType.MAJOR) && (isRangeGridlinesVisible()))
/*      */           {
/* 3914 */             gridStroke = getRangeGridlineStroke();
/* 3915 */             gridPaint = getRangeGridlinePaint();
/* 3916 */             paintLine = true;
/*      */           }
/* 3918 */           if (((tick.getValue() != 0.0D) || (!isRangeZeroBaselineVisible())) && (paintLine))
/*      */           {
/* 3920 */             getRenderer().drawRangeLine(g2, this, getRangeAxis(), area, tick.getValue(), gridPaint, gridStroke);
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
/*      */   protected void drawZeroDomainBaseline(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 3939 */     if (isDomainZeroBaselineVisible()) {
/* 3940 */       XYItemRenderer r = getRenderer();
/*      */       
/*      */ 
/*      */ 
/* 3944 */       if ((r instanceof AbstractXYItemRenderer)) {
/* 3945 */         AbstractXYItemRenderer renderer = (AbstractXYItemRenderer)r;
/* 3946 */         renderer.drawDomainLine(g2, this, getDomainAxis(), area, 0.0D, this.domainZeroBaselinePaint, this.domainZeroBaselineStroke);
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
/*      */   protected void drawZeroRangeBaseline(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 3962 */     if (isRangeZeroBaselineVisible()) {
/* 3963 */       getRenderer().drawRangeLine(g2, this, getRangeAxis(), area, 0.0D, this.rangeZeroBaselinePaint, this.rangeZeroBaselineStroke);
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
/*      */   public void drawAnnotations(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info)
/*      */   {
/* 3979 */     Iterator iterator = this.annotations.iterator();
/* 3980 */     while (iterator.hasNext()) {
/* 3981 */       XYAnnotation annotation = (XYAnnotation)iterator.next();
/* 3982 */       ValueAxis xAxis = getDomainAxis();
/* 3983 */       ValueAxis yAxis = getRangeAxis();
/* 3984 */       annotation.draw(g2, this, dataArea, xAxis, yAxis, 0, info);
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
/*      */   protected void drawDomainMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer)
/*      */   {
/* 4001 */     XYItemRenderer r = getRenderer(index);
/* 4002 */     if (r == null) {
/* 4003 */       return;
/*      */     }
/*      */     
/*      */ 
/* 4007 */     if (index >= getDatasetCount()) {
/* 4008 */       return;
/*      */     }
/* 4010 */     Collection markers = getDomainMarkers(index, layer);
/* 4011 */     ValueAxis axis = getDomainAxisForDataset(index);
/* 4012 */     if ((markers != null) && (axis != null)) {
/* 4013 */       Iterator iterator = markers.iterator();
/* 4014 */       while (iterator.hasNext()) {
/* 4015 */         Marker marker = (Marker)iterator.next();
/* 4016 */         r.drawDomainMarker(g2, this, axis, marker, dataArea);
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
/*      */   protected void drawRangeMarkers(Graphics2D g2, Rectangle2D dataArea, int index, Layer layer)
/*      */   {
/* 4034 */     XYItemRenderer r = getRenderer(index);
/* 4035 */     if (r == null) {
/* 4036 */       return;
/*      */     }
/*      */     
/*      */ 
/* 4040 */     if (index >= getDatasetCount()) {
/* 4041 */       return;
/*      */     }
/* 4043 */     Collection markers = getRangeMarkers(index, layer);
/* 4044 */     ValueAxis axis = getRangeAxisForDataset(index);
/* 4045 */     if ((markers != null) && (axis != null)) {
/* 4046 */       Iterator iterator = markers.iterator();
/* 4047 */       while (iterator.hasNext()) {
/* 4048 */         Marker marker = (Marker)iterator.next();
/* 4049 */         r.drawRangeMarker(g2, this, axis, marker, dataArea);
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
/*      */   public Collection getDomainMarkers(Layer layer)
/*      */   {
/* 4064 */     return getDomainMarkers(0, layer);
/*      */   }
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
/* 4077 */     return getRangeMarkers(0, layer);
/*      */   }
/*      */   
/*      */ 
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
/* 4092 */     Collection result = null;
/* 4093 */     Integer key = new Integer(index);
/* 4094 */     if (layer == Layer.FOREGROUND) {
/* 4095 */       result = (Collection)this.foregroundDomainMarkers.get(key);
/*      */     }
/* 4097 */     else if (layer == Layer.BACKGROUND) {
/* 4098 */       result = (Collection)this.backgroundDomainMarkers.get(key);
/*      */     }
/* 4100 */     if (result != null) {
/* 4101 */       result = Collections.unmodifiableCollection(result);
/*      */     }
/* 4103 */     return result;
/*      */   }
/*      */   
/*      */ 
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
/* 4118 */     Collection result = null;
/* 4119 */     Integer key = new Integer(index);
/* 4120 */     if (layer == Layer.FOREGROUND) {
/* 4121 */       result = (Collection)this.foregroundRangeMarkers.get(key);
/*      */     }
/* 4123 */     else if (layer == Layer.BACKGROUND) {
/* 4124 */       result = (Collection)this.backgroundRangeMarkers.get(key);
/*      */     }
/* 4126 */     if (result != null) {
/* 4127 */       result = Collections.unmodifiableCollection(result);
/*      */     }
/* 4129 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawHorizontalLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint)
/*      */   {
/* 4146 */     ValueAxis axis = getRangeAxis();
/* 4147 */     if (getOrientation() == PlotOrientation.HORIZONTAL) {
/* 4148 */       axis = getDomainAxis();
/*      */     }
/* 4150 */     if (axis.getRange().contains(value)) {
/* 4151 */       double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/* 4152 */       Line2D line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/*      */       
/* 4154 */       g2.setStroke(stroke);
/* 4155 */       g2.setPaint(paint);
/* 4156 */       g2.draw(line);
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
/*      */   protected void drawDomainCrosshair(Graphics2D g2, Rectangle2D dataArea, PlotOrientation orientation, double value, ValueAxis axis, Stroke stroke, Paint paint)
/*      */   {
/* 4178 */     if (axis.getRange().contains(value)) {
/* 4179 */       Line2D line = null;
/* 4180 */       if (orientation == PlotOrientation.VERTICAL) {
/* 4181 */         double xx = axis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/*      */         
/* 4183 */         line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/*      */       }
/*      */       else
/*      */       {
/* 4187 */         double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */         
/* 4189 */         line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/*      */       }
/*      */       
/* 4192 */       g2.setStroke(stroke);
/* 4193 */       g2.setPaint(paint);
/* 4194 */       g2.draw(line);
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
/*      */   protected void drawVerticalLine(Graphics2D g2, Rectangle2D dataArea, double value, Stroke stroke, Paint paint)
/*      */   {
/* 4211 */     ValueAxis axis = getDomainAxis();
/* 4212 */     if (getOrientation() == PlotOrientation.HORIZONTAL) {
/* 4213 */       axis = getRangeAxis();
/*      */     }
/* 4215 */     if (axis.getRange().contains(value)) {
/* 4216 */       double xx = axis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/*      */       
/* 4218 */       Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/*      */       
/* 4220 */       g2.setStroke(stroke);
/* 4221 */       g2.setPaint(paint);
/* 4222 */       g2.draw(line);
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
/*      */   protected void drawRangeCrosshair(Graphics2D g2, Rectangle2D dataArea, PlotOrientation orientation, double value, ValueAxis axis, Stroke stroke, Paint paint)
/*      */   {
/* 4244 */     if (axis.getRange().contains(value)) {
/* 4245 */       Line2D line = null;
/* 4246 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 4247 */         double xx = axis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/*      */         
/* 4249 */         line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/*      */       }
/*      */       else
/*      */       {
/* 4253 */         double yy = axis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */         
/* 4255 */         line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/*      */       }
/*      */       
/* 4258 */       g2.setStroke(stroke);
/* 4259 */       g2.setPaint(paint);
/* 4260 */       g2.draw(line);
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
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info)
/*      */   {
/* 4274 */     Rectangle2D dataArea = info.getDataArea();
/* 4275 */     if (dataArea.contains(x, y))
/*      */     {
/* 4277 */       ValueAxis xaxis = getDomainAxis();
/* 4278 */       if (xaxis != null) {
/* 4279 */         double hvalue = xaxis.java2DToValue(x, info.getDataArea(), getDomainAxisEdge());
/*      */         
/* 4281 */         setDomainCrosshairValue(hvalue);
/*      */       }
/*      */       
/*      */ 
/* 4285 */       ValueAxis yaxis = getRangeAxis();
/* 4286 */       if (yaxis != null) {
/* 4287 */         double vvalue = yaxis.java2DToValue(y, info.getDataArea(), getRangeAxisEdge());
/*      */         
/* 4289 */         setRangeCrosshairValue(vvalue);
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
/*      */   private List getDatasetsMappedToDomainAxis(Integer axisIndex)
/*      */   {
/* 4303 */     if (axisIndex == null) {
/* 4304 */       throw new IllegalArgumentException("Null 'axisIndex' argument.");
/*      */     }
/* 4306 */     List result = new ArrayList();
/* 4307 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 4308 */       List mappedAxes = (List)this.datasetToDomainAxesMap.get(new Integer(i));
/*      */       
/* 4310 */       if (mappedAxes == null) {
/* 4311 */         if (axisIndex.equals(ZERO)) {
/* 4312 */           result.add(this.datasets.get(i));
/*      */         }
/*      */         
/*      */       }
/* 4316 */       else if (mappedAxes.contains(axisIndex)) {
/* 4317 */         result.add(this.datasets.get(i));
/*      */       }
/*      */     }
/*      */     
/* 4321 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private List getDatasetsMappedToRangeAxis(Integer axisIndex)
/*      */   {
/* 4333 */     if (axisIndex == null) {
/* 4334 */       throw new IllegalArgumentException("Null 'axisIndex' argument.");
/*      */     }
/* 4336 */     List result = new ArrayList();
/* 4337 */     for (int i = 0; i < this.datasets.size(); i++) {
/* 4338 */       List mappedAxes = (List)this.datasetToRangeAxesMap.get(new Integer(i));
/*      */       
/* 4340 */       if (mappedAxes == null) {
/* 4341 */         if (axisIndex.equals(ZERO)) {
/* 4342 */           result.add(this.datasets.get(i));
/*      */         }
/*      */         
/*      */       }
/* 4346 */       else if (mappedAxes.contains(axisIndex)) {
/* 4347 */         result.add(this.datasets.get(i));
/*      */       }
/*      */     }
/*      */     
/* 4351 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDomainAxisIndex(ValueAxis axis)
/*      */   {
/* 4364 */     int result = this.domainAxes.indexOf(axis);
/* 4365 */     if (result < 0)
/*      */     {
/* 4367 */       Plot parent = getParent();
/* 4368 */       if ((parent instanceof XYPlot)) {
/* 4369 */         XYPlot p = (XYPlot)parent;
/* 4370 */         result = p.getDomainAxisIndex(axis);
/*      */       }
/*      */     }
/* 4373 */     return result;
/*      */   }
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
/* 4386 */     int result = this.rangeAxes.indexOf(axis);
/* 4387 */     if (result < 0)
/*      */     {
/* 4389 */       Plot parent = getParent();
/* 4390 */       if ((parent instanceof XYPlot)) {
/* 4391 */         XYPlot p = (XYPlot)parent;
/* 4392 */         result = p.getRangeAxisIndex(axis);
/*      */       }
/*      */     }
/* 4395 */     return result;
/*      */   }
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
/* 4407 */     Range result = null;
/* 4408 */     List mappedDatasets = new ArrayList();
/* 4409 */     List includedAnnotations = new ArrayList();
/* 4410 */     boolean isDomainAxis = true;
/*      */     
/*      */ 
/* 4413 */     int domainIndex = getDomainAxisIndex(axis);
/* 4414 */     if (domainIndex >= 0) {
/* 4415 */       isDomainAxis = true;
/* 4416 */       mappedDatasets.addAll(getDatasetsMappedToDomainAxis(new Integer(domainIndex)));
/*      */       
/* 4418 */       if (domainIndex == 0)
/*      */       {
/* 4420 */         Iterator iterator = this.annotations.iterator();
/* 4421 */         while (iterator.hasNext()) {
/* 4422 */           XYAnnotation annotation = (XYAnnotation)iterator.next();
/* 4423 */           if ((annotation instanceof XYAnnotationBoundsInfo)) {
/* 4424 */             includedAnnotations.add(annotation);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 4431 */     int rangeIndex = getRangeAxisIndex(axis);
/* 4432 */     if (rangeIndex >= 0) {
/* 4433 */       isDomainAxis = false;
/* 4434 */       mappedDatasets.addAll(getDatasetsMappedToRangeAxis(new Integer(rangeIndex)));
/*      */       
/* 4436 */       if (rangeIndex == 0) {
/* 4437 */         Iterator iterator = this.annotations.iterator();
/* 4438 */         while (iterator.hasNext()) {
/* 4439 */           XYAnnotation annotation = (XYAnnotation)iterator.next();
/* 4440 */           if ((annotation instanceof XYAnnotationBoundsInfo)) {
/* 4441 */             includedAnnotations.add(annotation);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 4449 */     Iterator iterator = mappedDatasets.iterator();
/* 4450 */     while (iterator.hasNext()) {
/* 4451 */       XYDataset d = (XYDataset)iterator.next();
/* 4452 */       if (d != null) {
/* 4453 */         XYItemRenderer r = getRendererForDataset(d);
/* 4454 */         if (isDomainAxis) {
/* 4455 */           if (r != null) {
/* 4456 */             result = Range.combine(result, r.findDomainBounds(d));
/*      */           }
/*      */           else {
/* 4459 */             result = Range.combine(result, DatasetUtilities.findDomainBounds(d));
/*      */           }
/*      */           
/*      */ 
/*      */         }
/* 4464 */         else if (r != null) {
/* 4465 */           result = Range.combine(result, r.findRangeBounds(d));
/*      */         }
/*      */         else {
/* 4468 */           result = Range.combine(result, DatasetUtilities.findRangeBounds(d));
/*      */         }
/*      */         
/*      */ 
/*      */ 
/*      */ 
/* 4474 */         if ((r instanceof AbstractXYItemRenderer)) {
/* 4475 */           AbstractXYItemRenderer rr = (AbstractXYItemRenderer)r;
/* 4476 */           Collection c = rr.getAnnotations();
/* 4477 */           Iterator i = c.iterator();
/* 4478 */           while (i.hasNext()) {
/* 4479 */             XYAnnotation a = (XYAnnotation)i.next();
/* 4480 */             if ((a instanceof XYAnnotationBoundsInfo)) {
/* 4481 */               includedAnnotations.add(a);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4488 */     Iterator it = includedAnnotations.iterator();
/* 4489 */     while (it.hasNext()) {
/* 4490 */       XYAnnotationBoundsInfo xyabi = (XYAnnotationBoundsInfo)it.next();
/* 4491 */       if (xyabi.getIncludeInDataBounds()) {
/* 4492 */         if (isDomainAxis) {
/* 4493 */           result = Range.combine(result, xyabi.getXRange());
/*      */         }
/*      */         else {
/* 4496 */           result = Range.combine(result, xyabi.getYRange());
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 4501 */     return result;
/*      */   }
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
/* 4513 */     configureDomainAxes();
/* 4514 */     configureRangeAxes();
/* 4515 */     if (getParent() != null) {
/* 4516 */       getParent().datasetChanged(event);
/*      */     }
/*      */     else {
/* 4519 */       PlotChangeEvent e = new PlotChangeEvent(this);
/* 4520 */       e.setType(ChartChangeEventType.DATASET_UPDATED);
/* 4521 */       notifyListeners(e);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void rendererChanged(RendererChangeEvent event)
/*      */   {
/* 4533 */     if (event.getSeriesVisibilityChanged()) {
/* 4534 */       configureDomainAxes();
/* 4535 */       configureRangeAxes();
/*      */     }
/* 4537 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainCrosshairVisible()
/*      */   {
/* 4548 */     return this.domainCrosshairVisible;
/*      */   }
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
/* 4561 */     if (this.domainCrosshairVisible != flag) {
/* 4562 */       this.domainCrosshairVisible = flag;
/* 4563 */       fireChangeEvent();
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
/*      */   public boolean isDomainCrosshairLockedOnData()
/*      */   {
/* 4576 */     return this.domainCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairLockedOnData(boolean flag)
/*      */   {
/* 4589 */     if (this.domainCrosshairLockedOnData != flag) {
/* 4590 */       this.domainCrosshairLockedOnData = flag;
/* 4591 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDomainCrosshairValue()
/*      */   {
/* 4603 */     return this.domainCrosshairValue;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairValue(double value)
/*      */   {
/* 4615 */     setDomainCrosshairValue(value, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairValue(double value, boolean notify)
/*      */   {
/* 4629 */     this.domainCrosshairValue = value;
/* 4630 */     if ((isDomainCrosshairVisible()) && (notify)) {
/* 4631 */       fireChangeEvent();
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
/*      */   public Stroke getDomainCrosshairStroke()
/*      */   {
/* 4645 */     return this.domainCrosshairStroke;
/*      */   }
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
/* 4658 */     if (stroke == null) {
/* 4659 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 4661 */     this.domainCrosshairStroke = stroke;
/* 4662 */     fireChangeEvent();
/*      */   }
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
/* 4675 */     return this.domainCrosshairPaint;
/*      */   }
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
/* 4687 */     if (paint == null) {
/* 4688 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 4690 */     this.domainCrosshairPaint = paint;
/* 4691 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeCrosshairVisible()
/*      */   {
/* 4703 */     return this.rangeCrosshairVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairVisible(boolean flag)
/*      */   {
/* 4716 */     if (this.rangeCrosshairVisible != flag) {
/* 4717 */       this.rangeCrosshairVisible = flag;
/* 4718 */       fireChangeEvent();
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
/* 4731 */     return this.rangeCrosshairLockedOnData;
/*      */   }
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
/* 4744 */     if (this.rangeCrosshairLockedOnData != flag) {
/* 4745 */       this.rangeCrosshairLockedOnData = flag;
/* 4746 */       fireChangeEvent();
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
/* 4758 */     return this.rangeCrosshairValue;
/*      */   }
/*      */   
/*      */ 
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
/* 4772 */     setRangeCrosshairValue(value, true);
/*      */   }
/*      */   
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
/* 4786 */     this.rangeCrosshairValue = value;
/* 4787 */     if ((isRangeCrosshairVisible()) && (notify)) {
/* 4788 */       fireChangeEvent();
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
/*      */   public Stroke getRangeCrosshairStroke()
/*      */   {
/* 4802 */     return this.rangeCrosshairStroke;
/*      */   }
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
/* 4815 */     if (stroke == null) {
/* 4816 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 4818 */     this.rangeCrosshairStroke = stroke;
/* 4819 */     fireChangeEvent();
/*      */   }
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
/* 4832 */     return this.rangeCrosshairPaint;
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
/* 4844 */     if (paint == null) {
/* 4845 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 4847 */     this.rangeCrosshairPaint = paint;
/* 4848 */     fireChangeEvent();
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
/* 4859 */     return this.fixedDomainAxisSpace;
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
/* 4871 */     setFixedDomainAxisSpace(space, true);
/*      */   }
/*      */   
/*      */ 
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
/* 4886 */     this.fixedDomainAxisSpace = space;
/* 4887 */     if (notify) {
/* 4888 */       fireChangeEvent();
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
/* 4900 */     return this.fixedRangeAxisSpace;
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
/* 4912 */     setFixedRangeAxisSpace(space, true);
/*      */   }
/*      */   
/*      */ 
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
/* 4927 */     this.fixedRangeAxisSpace = space;
/* 4928 */     if (notify) {
/* 4929 */       fireChangeEvent();
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
/*      */   public boolean isDomainPannable()
/*      */   {
/* 4942 */     return this.domainPannable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainPannable(boolean pannable)
/*      */   {
/* 4954 */     this.domainPannable = pannable;
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
/* 4966 */     return this.rangePannable;
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
/* 4978 */     this.rangePannable = pannable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void panDomainAxes(double percent, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 4992 */     if (!isDomainPannable()) {
/* 4993 */       return;
/*      */     }
/* 4995 */     int domainAxisCount = getDomainAxisCount();
/* 4996 */     for (int i = 0; i < domainAxisCount; i++) {
/* 4997 */       ValueAxis axis = getDomainAxis(i);
/* 4998 */       if (axis != null)
/*      */       {
/*      */ 
/* 5001 */         if (axis.isInverted()) {
/* 5002 */           percent = -percent;
/*      */         }
/* 5004 */         axis.pan(percent);
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
/*      */   public void panRangeAxes(double percent, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 5019 */     if (!isRangePannable()) {
/* 5020 */       return;
/*      */     }
/* 5022 */     int rangeAxisCount = getRangeAxisCount();
/* 5023 */     for (int i = 0; i < rangeAxisCount; i++) {
/* 5024 */       ValueAxis axis = getRangeAxis(i);
/* 5025 */       if (axis != null)
/*      */       {
/*      */ 
/* 5028 */         if (axis.isInverted()) {
/* 5029 */           percent = -percent;
/*      */         }
/* 5031 */         axis.pan(percent);
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
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 5047 */     zoomDomainAxes(factor, info, source, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo info, Point2D source, boolean useAnchor)
/*      */   {
/* 5066 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 5067 */       ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
/* 5068 */       if (domainAxis != null) {
/* 5069 */         if (useAnchor)
/*      */         {
/*      */ 
/* 5072 */           double sourceX = source.getX();
/* 5073 */           if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 5074 */             sourceX = source.getY();
/*      */           }
/* 5076 */           double anchorX = domainAxis.java2DToValue(sourceX, info.getDataArea(), getDomainAxisEdge());
/*      */           
/* 5078 */           domainAxis.resizeRange2(factor, anchorX);
/*      */         }
/*      */         else {
/* 5081 */           domainAxis.resizeRange(factor);
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
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 5103 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 5104 */       ValueAxis domainAxis = (ValueAxis)this.domainAxes.get(i);
/* 5105 */       if (domainAxis != null) {
/* 5106 */         domainAxis.zoomRange(lowerPercent, upperPercent);
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
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 5123 */     zoomRangeAxes(factor, info, source, false);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 5143 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 5144 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 5145 */       if (rangeAxis != null) {
/* 5146 */         if (useAnchor)
/*      */         {
/*      */ 
/* 5149 */           double sourceY = source.getY();
/* 5150 */           if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 5151 */             sourceY = source.getX();
/*      */           }
/* 5153 */           double anchorY = rangeAxis.java2DToValue(sourceY, info.getDataArea(), getRangeAxisEdge());
/*      */           
/* 5155 */           rangeAxis.resizeRange2(factor, anchorY);
/*      */         }
/*      */         else {
/* 5158 */           rangeAxis.resizeRange(factor);
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
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source)
/*      */   {
/* 5176 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 5177 */       ValueAxis rangeAxis = (ValueAxis)this.rangeAxes.get(i);
/* 5178 */       if (rangeAxis != null) {
/* 5179 */         rangeAxis.zoomRange(lowerPercent, upperPercent);
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
/* 5193 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/* 5205 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSeriesCount()
/*      */   {
/* 5215 */     int result = 0;
/* 5216 */     XYDataset dataset = getDataset();
/* 5217 */     if (dataset != null) {
/* 5218 */       result = dataset.getSeriesCount();
/*      */     }
/* 5220 */     return result;
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
/* 5231 */     return this.fixedLegendItems;
/*      */   }
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
/* 5244 */     this.fixedLegendItems = items;
/* 5245 */     fireChangeEvent();
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
/* 5256 */     if (this.fixedLegendItems != null) {
/* 5257 */       return this.fixedLegendItems;
/*      */     }
/* 5259 */     LegendItemCollection result = new LegendItemCollection();
/* 5260 */     int count = this.datasets.size();
/* 5261 */     for (int datasetIndex = 0; datasetIndex < count; datasetIndex++) {
/* 5262 */       XYDataset dataset = getDataset(datasetIndex);
/* 5263 */       if (dataset != null) {
/* 5264 */         XYItemRenderer renderer = getRenderer(datasetIndex);
/* 5265 */         if (renderer == null) {
/* 5266 */           renderer = getRenderer(0);
/*      */         }
/* 5268 */         if (renderer != null) {
/* 5269 */           int seriesCount = dataset.getSeriesCount();
/* 5270 */           for (int i = 0; i < seriesCount; i++) {
/* 5271 */             if ((renderer.isSeriesVisible(i)) && (renderer.isSeriesVisibleInLegend(i)))
/*      */             {
/* 5273 */               LegendItem item = renderer.getLegendItem(datasetIndex, i);
/*      */               
/* 5275 */               if (item != null) {
/* 5276 */                 result.add(item);
/*      */               }
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 5283 */     return result;
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
/* 5294 */     if (obj == this) {
/* 5295 */       return true;
/*      */     }
/* 5297 */     if (!(obj instanceof XYPlot)) {
/* 5298 */       return false;
/*      */     }
/* 5300 */     XYPlot that = (XYPlot)obj;
/* 5301 */     if (this.weight != that.weight) {
/* 5302 */       return false;
/*      */     }
/* 5304 */     if (this.orientation != that.orientation) {
/* 5305 */       return false;
/*      */     }
/* 5307 */     if (!this.domainAxes.equals(that.domainAxes)) {
/* 5308 */       return false;
/*      */     }
/* 5310 */     if (!this.domainAxisLocations.equals(that.domainAxisLocations)) {
/* 5311 */       return false;
/*      */     }
/* 5313 */     if (this.rangeCrosshairLockedOnData != that.rangeCrosshairLockedOnData)
/*      */     {
/* 5315 */       return false;
/*      */     }
/* 5317 */     if (this.domainGridlinesVisible != that.domainGridlinesVisible) {
/* 5318 */       return false;
/*      */     }
/* 5320 */     if (this.rangeGridlinesVisible != that.rangeGridlinesVisible) {
/* 5321 */       return false;
/*      */     }
/* 5323 */     if (this.domainMinorGridlinesVisible != that.domainMinorGridlinesVisible)
/*      */     {
/* 5325 */       return false;
/*      */     }
/* 5327 */     if (this.rangeMinorGridlinesVisible != that.rangeMinorGridlinesVisible)
/*      */     {
/* 5329 */       return false;
/*      */     }
/* 5331 */     if (this.domainZeroBaselineVisible != that.domainZeroBaselineVisible) {
/* 5332 */       return false;
/*      */     }
/* 5334 */     if (this.rangeZeroBaselineVisible != that.rangeZeroBaselineVisible) {
/* 5335 */       return false;
/*      */     }
/* 5337 */     if (this.domainCrosshairVisible != that.domainCrosshairVisible) {
/* 5338 */       return false;
/*      */     }
/* 5340 */     if (this.domainCrosshairValue != that.domainCrosshairValue) {
/* 5341 */       return false;
/*      */     }
/* 5343 */     if (this.domainCrosshairLockedOnData != that.domainCrosshairLockedOnData)
/*      */     {
/* 5345 */       return false;
/*      */     }
/* 5347 */     if (this.rangeCrosshairVisible != that.rangeCrosshairVisible) {
/* 5348 */       return false;
/*      */     }
/* 5350 */     if (this.rangeCrosshairValue != that.rangeCrosshairValue) {
/* 5351 */       return false;
/*      */     }
/* 5353 */     if (!ObjectUtilities.equal(this.axisOffset, that.axisOffset)) {
/* 5354 */       return false;
/*      */     }
/* 5356 */     if (!ObjectUtilities.equal(this.renderers, that.renderers)) {
/* 5357 */       return false;
/*      */     }
/* 5359 */     if (!ObjectUtilities.equal(this.rangeAxes, that.rangeAxes)) {
/* 5360 */       return false;
/*      */     }
/* 5362 */     if (!this.rangeAxisLocations.equals(that.rangeAxisLocations)) {
/* 5363 */       return false;
/*      */     }
/* 5365 */     if (!ObjectUtilities.equal(this.datasetToDomainAxesMap, that.datasetToDomainAxesMap))
/*      */     {
/* 5367 */       return false;
/*      */     }
/* 5369 */     if (!ObjectUtilities.equal(this.datasetToRangeAxesMap, that.datasetToRangeAxesMap))
/*      */     {
/* 5371 */       return false;
/*      */     }
/* 5373 */     if (!ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke))
/*      */     {
/* 5375 */       return false;
/*      */     }
/* 5377 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/*      */     {
/* 5379 */       return false;
/*      */     }
/* 5381 */     if (!ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke))
/*      */     {
/* 5383 */       return false;
/*      */     }
/* 5385 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/*      */     {
/* 5387 */       return false;
/*      */     }
/* 5389 */     if (!ObjectUtilities.equal(this.domainMinorGridlineStroke, that.domainMinorGridlineStroke))
/*      */     {
/* 5391 */       return false;
/*      */     }
/* 5393 */     if (!PaintUtilities.equal(this.domainMinorGridlinePaint, that.domainMinorGridlinePaint))
/*      */     {
/* 5395 */       return false;
/*      */     }
/* 5397 */     if (!ObjectUtilities.equal(this.rangeMinorGridlineStroke, that.rangeMinorGridlineStroke))
/*      */     {
/* 5399 */       return false;
/*      */     }
/* 5401 */     if (!PaintUtilities.equal(this.rangeMinorGridlinePaint, that.rangeMinorGridlinePaint))
/*      */     {
/* 5403 */       return false;
/*      */     }
/* 5405 */     if (!PaintUtilities.equal(this.domainZeroBaselinePaint, that.domainZeroBaselinePaint))
/*      */     {
/* 5407 */       return false;
/*      */     }
/* 5409 */     if (!ObjectUtilities.equal(this.domainZeroBaselineStroke, that.domainZeroBaselineStroke))
/*      */     {
/* 5411 */       return false;
/*      */     }
/* 5413 */     if (!PaintUtilities.equal(this.rangeZeroBaselinePaint, that.rangeZeroBaselinePaint))
/*      */     {
/* 5415 */       return false;
/*      */     }
/* 5417 */     if (!ObjectUtilities.equal(this.rangeZeroBaselineStroke, that.rangeZeroBaselineStroke))
/*      */     {
/* 5419 */       return false;
/*      */     }
/* 5421 */     if (!ObjectUtilities.equal(this.domainCrosshairStroke, that.domainCrosshairStroke))
/*      */     {
/* 5423 */       return false;
/*      */     }
/* 5425 */     if (!PaintUtilities.equal(this.domainCrosshairPaint, that.domainCrosshairPaint))
/*      */     {
/* 5427 */       return false;
/*      */     }
/* 5429 */     if (!ObjectUtilities.equal(this.rangeCrosshairStroke, that.rangeCrosshairStroke))
/*      */     {
/* 5431 */       return false;
/*      */     }
/* 5433 */     if (!PaintUtilities.equal(this.rangeCrosshairPaint, that.rangeCrosshairPaint))
/*      */     {
/* 5435 */       return false;
/*      */     }
/* 5437 */     if (!ObjectUtilities.equal(this.foregroundDomainMarkers, that.foregroundDomainMarkers))
/*      */     {
/* 5439 */       return false;
/*      */     }
/* 5441 */     if (!ObjectUtilities.equal(this.backgroundDomainMarkers, that.backgroundDomainMarkers))
/*      */     {
/* 5443 */       return false;
/*      */     }
/* 5445 */     if (!ObjectUtilities.equal(this.foregroundRangeMarkers, that.foregroundRangeMarkers))
/*      */     {
/* 5447 */       return false;
/*      */     }
/* 5449 */     if (!ObjectUtilities.equal(this.backgroundRangeMarkers, that.backgroundRangeMarkers))
/*      */     {
/* 5451 */       return false;
/*      */     }
/* 5453 */     if (!ObjectUtilities.equal(this.foregroundDomainMarkers, that.foregroundDomainMarkers))
/*      */     {
/* 5455 */       return false;
/*      */     }
/* 5457 */     if (!ObjectUtilities.equal(this.backgroundDomainMarkers, that.backgroundDomainMarkers))
/*      */     {
/* 5459 */       return false;
/*      */     }
/* 5461 */     if (!ObjectUtilities.equal(this.foregroundRangeMarkers, that.foregroundRangeMarkers))
/*      */     {
/* 5463 */       return false;
/*      */     }
/* 5465 */     if (!ObjectUtilities.equal(this.backgroundRangeMarkers, that.backgroundRangeMarkers))
/*      */     {
/* 5467 */       return false;
/*      */     }
/* 5469 */     if (!ObjectUtilities.equal(this.annotations, that.annotations)) {
/* 5470 */       return false;
/*      */     }
/* 5472 */     if (!PaintUtilities.equal(this.domainTickBandPaint, that.domainTickBandPaint))
/*      */     {
/* 5474 */       return false;
/*      */     }
/* 5476 */     if (!PaintUtilities.equal(this.rangeTickBandPaint, that.rangeTickBandPaint))
/*      */     {
/* 5478 */       return false;
/*      */     }
/* 5480 */     if (!this.quadrantOrigin.equals(that.quadrantOrigin)) {
/* 5481 */       return false;
/*      */     }
/* 5483 */     for (int i = 0; i < 4; i++) {
/* 5484 */       if (!PaintUtilities.equal(this.quadrantPaint[i], that.quadrantPaint[i]))
/*      */       {
/* 5486 */         return false;
/*      */       }
/*      */     }
/* 5489 */     return super.equals(obj);
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
/* 5502 */     XYPlot clone = (XYPlot)super.clone();
/* 5503 */     clone.domainAxes = ((ObjectList)ObjectUtilities.clone(this.domainAxes));
/* 5504 */     for (int i = 0; i < this.domainAxes.size(); i++) {
/* 5505 */       ValueAxis axis = (ValueAxis)this.domainAxes.get(i);
/* 5506 */       if (axis != null) {
/* 5507 */         ValueAxis clonedAxis = (ValueAxis)axis.clone();
/* 5508 */         clone.domainAxes.set(i, clonedAxis);
/* 5509 */         clonedAxis.setPlot(clone);
/* 5510 */         clonedAxis.addChangeListener(clone);
/*      */       }
/*      */     }
/* 5513 */     clone.domainAxisLocations = ((ObjectList)this.domainAxisLocations.clone());
/*      */     
/*      */ 
/* 5516 */     clone.rangeAxes = ((ObjectList)ObjectUtilities.clone(this.rangeAxes));
/* 5517 */     for (int i = 0; i < this.rangeAxes.size(); i++) {
/* 5518 */       ValueAxis axis = (ValueAxis)this.rangeAxes.get(i);
/* 5519 */       if (axis != null) {
/* 5520 */         ValueAxis clonedAxis = (ValueAxis)axis.clone();
/* 5521 */         clone.rangeAxes.set(i, clonedAxis);
/* 5522 */         clonedAxis.setPlot(clone);
/* 5523 */         clonedAxis.addChangeListener(clone);
/*      */       }
/*      */     }
/* 5526 */     clone.rangeAxisLocations = ((ObjectList)ObjectUtilities.clone(this.rangeAxisLocations));
/*      */     
/*      */ 
/*      */ 
/* 5530 */     clone.datasets = ((ObjectList)ObjectUtilities.clone(this.datasets));
/* 5531 */     for (int i = 0; i < clone.datasets.size(); i++) {
/* 5532 */       XYDataset d = getDataset(i);
/* 5533 */       if (d != null) {
/* 5534 */         d.addChangeListener(clone);
/*      */       }
/*      */     }
/*      */     
/* 5538 */     clone.datasetToDomainAxesMap = new TreeMap();
/* 5539 */     clone.datasetToDomainAxesMap.putAll(this.datasetToDomainAxesMap);
/* 5540 */     clone.datasetToRangeAxesMap = new TreeMap();
/* 5541 */     clone.datasetToRangeAxesMap.putAll(this.datasetToRangeAxesMap);
/*      */     
/* 5543 */     clone.renderers = ((ObjectList)ObjectUtilities.clone(this.renderers));
/* 5544 */     for (int i = 0; i < this.renderers.size(); i++) {
/* 5545 */       XYItemRenderer renderer2 = (XYItemRenderer)this.renderers.get(i);
/* 5546 */       if ((renderer2 instanceof PublicCloneable)) {
/* 5547 */         PublicCloneable pc = (PublicCloneable)renderer2;
/* 5548 */         clone.renderers.set(i, pc.clone());
/*      */       }
/*      */     }
/* 5551 */     clone.foregroundDomainMarkers = ((Map)ObjectUtilities.clone(this.foregroundDomainMarkers));
/*      */     
/* 5553 */     clone.backgroundDomainMarkers = ((Map)ObjectUtilities.clone(this.backgroundDomainMarkers));
/*      */     
/* 5555 */     clone.foregroundRangeMarkers = ((Map)ObjectUtilities.clone(this.foregroundRangeMarkers));
/*      */     
/* 5557 */     clone.backgroundRangeMarkers = ((Map)ObjectUtilities.clone(this.backgroundRangeMarkers));
/*      */     
/* 5559 */     clone.annotations = ((List)ObjectUtilities.deepClone(this.annotations));
/* 5560 */     if (this.fixedDomainAxisSpace != null) {
/* 5561 */       clone.fixedDomainAxisSpace = ((AxisSpace)ObjectUtilities.clone(this.fixedDomainAxisSpace));
/*      */     }
/*      */     
/* 5564 */     if (this.fixedRangeAxisSpace != null) {
/* 5565 */       clone.fixedRangeAxisSpace = ((AxisSpace)ObjectUtilities.clone(this.fixedRangeAxisSpace));
/*      */     }
/*      */     
/*      */ 
/* 5569 */     clone.quadrantOrigin = ((Point2D)ObjectUtilities.clone(this.quadrantOrigin));
/*      */     
/* 5571 */     clone.quadrantPaint = ((Paint[])this.quadrantPaint.clone());
/* 5572 */     return clone;
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
/* 5584 */     stream.defaultWriteObject();
/* 5585 */     SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
/* 5586 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 5587 */     SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
/* 5588 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
/* 5589 */     SerialUtilities.writeStroke(this.domainMinorGridlineStroke, stream);
/* 5590 */     SerialUtilities.writePaint(this.domainMinorGridlinePaint, stream);
/* 5591 */     SerialUtilities.writeStroke(this.rangeMinorGridlineStroke, stream);
/* 5592 */     SerialUtilities.writePaint(this.rangeMinorGridlinePaint, stream);
/* 5593 */     SerialUtilities.writeStroke(this.rangeZeroBaselineStroke, stream);
/* 5594 */     SerialUtilities.writePaint(this.rangeZeroBaselinePaint, stream);
/* 5595 */     SerialUtilities.writeStroke(this.domainCrosshairStroke, stream);
/* 5596 */     SerialUtilities.writePaint(this.domainCrosshairPaint, stream);
/* 5597 */     SerialUtilities.writeStroke(this.rangeCrosshairStroke, stream);
/* 5598 */     SerialUtilities.writePaint(this.rangeCrosshairPaint, stream);
/* 5599 */     SerialUtilities.writePaint(this.domainTickBandPaint, stream);
/* 5600 */     SerialUtilities.writePaint(this.rangeTickBandPaint, stream);
/* 5601 */     SerialUtilities.writePoint2D(this.quadrantOrigin, stream);
/* 5602 */     for (int i = 0; i < 4; i++) {
/* 5603 */       SerialUtilities.writePaint(this.quadrantPaint[i], stream);
/*      */     }
/* 5605 */     SerialUtilities.writeStroke(this.domainZeroBaselineStroke, stream);
/* 5606 */     SerialUtilities.writePaint(this.domainZeroBaselinePaint, stream);
/*      */   }
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
/* 5620 */     stream.defaultReadObject();
/* 5621 */     this.domainGridlineStroke = SerialUtilities.readStroke(stream);
/* 5622 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/* 5623 */     this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
/* 5624 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/* 5625 */     this.domainMinorGridlineStroke = SerialUtilities.readStroke(stream);
/* 5626 */     this.domainMinorGridlinePaint = SerialUtilities.readPaint(stream);
/* 5627 */     this.rangeMinorGridlineStroke = SerialUtilities.readStroke(stream);
/* 5628 */     this.rangeMinorGridlinePaint = SerialUtilities.readPaint(stream);
/* 5629 */     this.rangeZeroBaselineStroke = SerialUtilities.readStroke(stream);
/* 5630 */     this.rangeZeroBaselinePaint = SerialUtilities.readPaint(stream);
/* 5631 */     this.domainCrosshairStroke = SerialUtilities.readStroke(stream);
/* 5632 */     this.domainCrosshairPaint = SerialUtilities.readPaint(stream);
/* 5633 */     this.rangeCrosshairStroke = SerialUtilities.readStroke(stream);
/* 5634 */     this.rangeCrosshairPaint = SerialUtilities.readPaint(stream);
/* 5635 */     this.domainTickBandPaint = SerialUtilities.readPaint(stream);
/* 5636 */     this.rangeTickBandPaint = SerialUtilities.readPaint(stream);
/* 5637 */     this.quadrantOrigin = SerialUtilities.readPoint2D(stream);
/* 5638 */     this.quadrantPaint = new Paint[4];
/* 5639 */     for (int i = 0; i < 4; i++) {
/* 5640 */       this.quadrantPaint[i] = SerialUtilities.readPaint(stream);
/*      */     }
/*      */     
/* 5643 */     this.domainZeroBaselineStroke = SerialUtilities.readStroke(stream);
/* 5644 */     this.domainZeroBaselinePaint = SerialUtilities.readPaint(stream);
/*      */     
/*      */ 
/*      */ 
/* 5648 */     int domainAxisCount = this.domainAxes.size();
/* 5649 */     for (int i = 0; i < domainAxisCount; i++) {
/* 5650 */       Axis axis = (Axis)this.domainAxes.get(i);
/* 5651 */       if (axis != null) {
/* 5652 */         axis.setPlot(this);
/* 5653 */         axis.addChangeListener(this);
/*      */       }
/*      */     }
/* 5656 */     int rangeAxisCount = this.rangeAxes.size();
/* 5657 */     for (int i = 0; i < rangeAxisCount; i++) {
/* 5658 */       Axis axis = (Axis)this.rangeAxes.get(i);
/* 5659 */       if (axis != null) {
/* 5660 */         axis.setPlot(this);
/* 5661 */         axis.addChangeListener(this);
/*      */       }
/*      */     }
/* 5664 */     int datasetCount = this.datasets.size();
/* 5665 */     for (int i = 0; i < datasetCount; i++) {
/* 5666 */       Dataset dataset = (Dataset)this.datasets.get(i);
/* 5667 */       if (dataset != null) {
/* 5668 */         dataset.addChangeListener(this);
/*      */       }
/*      */     }
/* 5671 */     int rendererCount = this.renderers.size();
/* 5672 */     for (int i = 0; i < rendererCount; i++) {
/* 5673 */       XYItemRenderer renderer = (XYItemRenderer)this.renderers.get(i);
/* 5674 */       if (renderer != null) {
/* 5675 */         renderer.addChangeListener(this);
/*      */       }
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/XYPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */