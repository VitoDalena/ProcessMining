/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.awt.geom.RectangularShape;
/*      */ import java.beans.PropertyChangeEvent;
/*      */ import java.beans.PropertyChangeListener;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.ClipPath;
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.ColorBar;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.ContourEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.labels.ContourToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardContourToolTipGenerator;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.contour.ContourDataset;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ /**
/*      */  * @deprecated
/*      */  */
/*      */ public class ContourPlot
/*      */   extends Plot
/*      */   implements ContourValuePlot, ValueAxisPlot, PropertyChangeListener, Serializable, Cloneable
/*      */ {
/*      */   private static final long serialVersionUID = 7861072556590502247L;
/*  128 */   protected static final RectangleInsets DEFAULT_INSETS = new RectangleInsets(2.0D, 2.0D, 100.0D, 10.0D);
/*      */   
/*      */ 
/*      */ 
/*      */   private ValueAxis domainAxis;
/*      */   
/*      */ 
/*      */   private ValueAxis rangeAxis;
/*      */   
/*      */ 
/*      */   private ContourDataset dataset;
/*      */   
/*      */ 
/*  141 */   private ColorBar colorBar = null;
/*      */   
/*      */ 
/*      */ 
/*      */   private RectangleEdge colorBarLocation;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean domainCrosshairVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private double domainCrosshairValue;
/*      */   
/*      */ 
/*      */   private transient Stroke domainCrosshairStroke;
/*      */   
/*      */ 
/*      */   private transient Paint domainCrosshairPaint;
/*      */   
/*      */ 
/*  162 */   private boolean domainCrosshairLockedOnData = true;
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
/*  180 */   private boolean rangeCrosshairLockedOnData = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  191 */   private double dataAreaRatio = 0.0D;
/*      */   
/*      */ 
/*      */ 
/*      */   private List domainMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */   private List rangeMarkers;
/*      */   
/*      */ 
/*      */ 
/*      */   private List annotations;
/*      */   
/*      */ 
/*      */   private ContourToolTipGenerator toolTipGenerator;
/*      */   
/*      */ 
/*      */   private XYURLGenerator urlGenerator;
/*      */   
/*      */ 
/*  212 */   private boolean renderAsPoints = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  218 */   private double ptSizePct = 0.05D;
/*      */   
/*      */ 
/*  221 */   private transient ClipPath clipPath = null;
/*      */   
/*      */ 
/*  224 */   private transient Paint missingPaint = null;
/*      */   
/*      */ 
/*  227 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ContourPlot()
/*      */   {
/*  235 */     this(null, null, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ContourPlot(ContourDataset dataset, ValueAxis domainAxis, ValueAxis rangeAxis, ColorBar colorBar)
/*      */   {
/*  253 */     this.dataset = dataset;
/*  254 */     if (dataset != null) {
/*  255 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*  258 */     this.domainAxis = domainAxis;
/*  259 */     if (domainAxis != null) {
/*  260 */       domainAxis.setPlot(this);
/*  261 */       domainAxis.addChangeListener(this);
/*      */     }
/*      */     
/*  264 */     this.rangeAxis = rangeAxis;
/*  265 */     if (rangeAxis != null) {
/*  266 */       rangeAxis.setPlot(this);
/*  267 */       rangeAxis.addChangeListener(this);
/*      */     }
/*      */     
/*  270 */     this.colorBar = colorBar;
/*  271 */     if (colorBar != null) {
/*  272 */       colorBar.getAxis().setPlot(this);
/*  273 */       colorBar.getAxis().addChangeListener(this);
/*  274 */       colorBar.configure(this);
/*      */     }
/*  276 */     this.colorBarLocation = RectangleEdge.LEFT;
/*      */     
/*  278 */     this.toolTipGenerator = new StandardContourToolTipGenerator();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleEdge getColorBarLocation()
/*      */   {
/*  288 */     return this.colorBarLocation;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setColorBarLocation(RectangleEdge edge)
/*      */   {
/*  298 */     this.colorBarLocation = edge;
/*  299 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ContourDataset getDataset()
/*      */   {
/*  308 */     return this.dataset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataset(ContourDataset dataset)
/*      */   {
/*  321 */     ContourDataset existing = this.dataset;
/*  322 */     if (existing != null) {
/*  323 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  327 */     this.dataset = dataset;
/*  328 */     if (dataset != null) {
/*  329 */       setDatasetGroup(dataset.getGroup());
/*  330 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  334 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/*  335 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getDomainAxis()
/*      */   {
/*  346 */     ValueAxis result = this.domainAxis;
/*      */     
/*  348 */     return result;
/*      */   }
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
/*  360 */     if (isCompatibleDomainAxis(axis))
/*      */     {
/*  362 */       if (axis != null) {
/*  363 */         axis.setPlot(this);
/*  364 */         axis.addChangeListener(this);
/*      */       }
/*      */       
/*      */ 
/*  368 */       if (this.domainAxis != null) {
/*  369 */         this.domainAxis.removeChangeListener(this);
/*      */       }
/*      */       
/*  372 */       this.domainAxis = axis;
/*  373 */       fireChangeEvent();
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
/*      */   public ValueAxis getRangeAxis()
/*      */   {
/*  386 */     ValueAxis result = this.rangeAxis;
/*      */     
/*  388 */     return result;
/*      */   }
/*      */   
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
/*  402 */     if (axis != null) {
/*  403 */       axis.setPlot(this);
/*  404 */       axis.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  408 */     if (this.rangeAxis != null) {
/*  409 */       this.rangeAxis.removeChangeListener(this);
/*      */     }
/*      */     
/*  412 */     this.rangeAxis = axis;
/*  413 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setColorBarAxis(ColorBar axis)
/*      */   {
/*  424 */     this.colorBar = axis;
/*  425 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDataAreaRatio()
/*      */   {
/*  435 */     return this.dataAreaRatio;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataAreaRatio(double ratio)
/*      */   {
/*  444 */     this.dataAreaRatio = ratio;
/*      */   }
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
/*  457 */     if (this.domainMarkers == null) {
/*  458 */       this.domainMarkers = new ArrayList();
/*      */     }
/*  460 */     this.domainMarkers.add(marker);
/*  461 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearDomainMarkers()
/*      */   {
/*  469 */     if (this.domainMarkers != null) {
/*  470 */       this.domainMarkers.clear();
/*  471 */       fireChangeEvent();
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
/*      */   public void addRangeMarker(Marker marker)
/*      */   {
/*  485 */     if (this.rangeMarkers == null) {
/*  486 */       this.rangeMarkers = new ArrayList();
/*      */     }
/*  488 */     this.rangeMarkers.add(marker);
/*  489 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearRangeMarkers()
/*      */   {
/*  497 */     if (this.rangeMarkers != null) {
/*  498 */       this.rangeMarkers.clear();
/*  499 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addAnnotation(XYAnnotation annotation)
/*      */   {
/*  510 */     if (this.annotations == null) {
/*  511 */       this.annotations = new ArrayList();
/*      */     }
/*  513 */     this.annotations.add(annotation);
/*  514 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearAnnotations()
/*      */   {
/*  522 */     if (this.annotations != null) {
/*  523 */       this.annotations.clear();
/*  524 */       fireChangeEvent();
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
/*      */   public boolean isCompatibleDomainAxis(ValueAxis axis)
/*      */   {
/*  538 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  563 */     boolean b1 = area.getWidth() <= 10.0D;
/*  564 */     boolean b2 = area.getHeight() <= 10.0D;
/*  565 */     if ((b1) || (b2)) {
/*  566 */       return;
/*      */     }
/*      */     
/*      */ 
/*  570 */     if (info != null) {
/*  571 */       info.setPlotArea(area);
/*      */     }
/*      */     
/*      */ 
/*  575 */     RectangleInsets insets = getInsets();
/*  576 */     insets.trim(area);
/*      */     
/*  578 */     AxisSpace space = new AxisSpace();
/*      */     
/*  580 */     space = this.domainAxis.reserveSpace(g2, this, area, RectangleEdge.BOTTOM, space);
/*      */     
/*  582 */     space = this.rangeAxis.reserveSpace(g2, this, area, RectangleEdge.LEFT, space);
/*      */     
/*      */ 
/*  585 */     Rectangle2D estimatedDataArea = space.shrink(area, null);
/*      */     
/*  587 */     AxisSpace space2 = new AxisSpace();
/*  588 */     space2 = this.colorBar.reserveSpace(g2, this, area, estimatedDataArea, this.colorBarLocation, space2);
/*      */     
/*  590 */     Rectangle2D adjustedPlotArea = space2.shrink(area, null);
/*      */     
/*  592 */     Rectangle2D dataArea = space.shrink(adjustedPlotArea, null);
/*      */     
/*  594 */     Rectangle2D colorBarArea = space2.reserved(area, this.colorBarLocation);
/*      */     
/*      */ 
/*  597 */     if (getDataAreaRatio() != 0.0D) {
/*  598 */       double ratio = getDataAreaRatio();
/*  599 */       Rectangle2D tmpDataArea = (Rectangle2D)dataArea.clone();
/*  600 */       double h = tmpDataArea.getHeight();
/*  601 */       double w = tmpDataArea.getWidth();
/*      */       
/*  603 */       if (ratio > 0.0D) {
/*  604 */         if (w * ratio <= h) {
/*  605 */           h = ratio * w;
/*      */         }
/*      */         else {
/*  608 */           w = h / ratio;
/*      */         }
/*      */       }
/*      */       else {
/*  612 */         ratio *= -1.0D;
/*  613 */         double xLength = getDomainAxis().getRange().getLength();
/*  614 */         double yLength = getRangeAxis().getRange().getLength();
/*  615 */         double unitRatio = yLength / xLength;
/*      */         
/*  617 */         ratio = unitRatio * ratio;
/*      */         
/*  619 */         if (w * ratio <= h) {
/*  620 */           h = ratio * w;
/*      */         }
/*      */         else {
/*  623 */           w = h / ratio;
/*      */         }
/*      */       }
/*      */       
/*  627 */       dataArea.setRect(tmpDataArea.getX() + tmpDataArea.getWidth() / 2.0D - w / 2.0D, tmpDataArea.getY(), w, h);
/*      */     }
/*      */     
/*      */ 
/*  631 */     if (info != null) {
/*  632 */       info.setDataArea(dataArea);
/*      */     }
/*      */     
/*  635 */     CrosshairState crosshairState = new CrosshairState();
/*  636 */     crosshairState.setCrosshairDistance(Double.POSITIVE_INFINITY);
/*      */     
/*      */ 
/*  639 */     drawBackground(g2, dataArea);
/*      */     
/*  641 */     double cursor = dataArea.getMaxY();
/*  642 */     if (this.domainAxis != null) {
/*  643 */       this.domainAxis.draw(g2, cursor, adjustedPlotArea, dataArea, RectangleEdge.BOTTOM, info);
/*      */     }
/*      */     
/*      */ 
/*  647 */     if (this.rangeAxis != null) {
/*  648 */       cursor = dataArea.getMinX();
/*  649 */       this.rangeAxis.draw(g2, cursor, adjustedPlotArea, dataArea, RectangleEdge.LEFT, info);
/*      */     }
/*      */     
/*      */ 
/*  653 */     if (this.colorBar != null) {
/*  654 */       cursor = 0.0D;
/*  655 */       cursor = this.colorBar.draw(g2, cursor, adjustedPlotArea, dataArea, colorBarArea, this.colorBarLocation);
/*      */     }
/*      */     
/*  658 */     Shape originalClip = g2.getClip();
/*  659 */     Composite originalComposite = g2.getComposite();
/*      */     
/*  661 */     g2.clip(dataArea);
/*  662 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*  664 */     render(g2, dataArea, info, crosshairState);
/*      */     
/*  666 */     if (this.domainMarkers != null) {
/*  667 */       Iterator iterator = this.domainMarkers.iterator();
/*  668 */       while (iterator.hasNext()) {
/*  669 */         Marker marker = (Marker)iterator.next();
/*  670 */         drawDomainMarker(g2, this, getDomainAxis(), marker, dataArea);
/*      */       }
/*      */     }
/*      */     
/*  674 */     if (this.rangeMarkers != null) {
/*  675 */       Iterator iterator = this.rangeMarkers.iterator();
/*  676 */       while (iterator.hasNext()) {
/*  677 */         Marker marker = (Marker)iterator.next();
/*  678 */         drawRangeMarker(g2, this, getRangeAxis(), marker, dataArea);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  699 */     g2.setClip(originalClip);
/*  700 */     g2.setComposite(originalComposite);
/*  701 */     drawOutline(g2, dataArea);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, CrosshairState crosshairState)
/*      */   {
/*  722 */     ContourDataset data = getDataset();
/*  723 */     if (data != null)
/*      */     {
/*  725 */       ColorBar zAxis = getColorBar();
/*      */       
/*  727 */       if (this.clipPath != null) {
/*  728 */         GeneralPath clipper = getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
/*      */         
/*  730 */         if (this.clipPath.isClip()) {
/*  731 */           g2.clip(clipper);
/*      */         }
/*      */       }
/*      */       
/*  735 */       if (this.renderAsPoints) {
/*  736 */         pointRenderer(g2, dataArea, info, this, this.domainAxis, this.rangeAxis, zAxis, data, crosshairState);
/*      */       }
/*      */       else
/*      */       {
/*  740 */         contourRenderer(g2, dataArea, info, this, this.domainAxis, this.rangeAxis, zAxis, data, crosshairState);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  745 */       setDomainCrosshairValue(crosshairState.getCrosshairX(), false);
/*  746 */       if (isDomainCrosshairVisible()) {
/*  747 */         drawVerticalLine(g2, dataArea, getDomainCrosshairValue(), getDomainCrosshairStroke(), getDomainCrosshairPaint());
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  754 */       setRangeCrosshairValue(crosshairState.getCrosshairY(), false);
/*  755 */       if (isRangeCrosshairVisible()) {
/*  756 */         drawHorizontalLine(g2, dataArea, getRangeCrosshairValue(), getRangeCrosshairStroke(), getRangeCrosshairPaint());
/*      */ 
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */     }
/*  763 */     else if (this.clipPath != null) {
/*  764 */       getClipPath().draw(g2, dataArea, this.domainAxis, this.rangeAxis);
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
/*      */ 
/*      */ 
/*      */ 
/*      */   public void contourRenderer(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, ContourPlot plot, ValueAxis horizontalAxis, ValueAxis verticalAxis, ColorBar colorBar, ContourDataset data, CrosshairState crosshairState)
/*      */   {
/*  794 */     Rectangle2D.Double entityArea = null;
/*  795 */     EntityCollection entities = null;
/*  796 */     if (info != null) {
/*  797 */       entities = info.getOwner().getEntityCollection();
/*      */     }
/*      */     
/*  800 */     Rectangle2D.Double rect = null;
/*  801 */     rect = new Rectangle2D.Double();
/*      */     
/*      */ 
/*  804 */     Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/*  805 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*      */     
/*      */ 
/*      */ 
/*  809 */     Number[] xNumber = data.getXValues();
/*  810 */     Number[] yNumber = data.getYValues();
/*  811 */     Number[] zNumber = data.getZValues();
/*      */     
/*  813 */     double[] x = new double[xNumber.length];
/*  814 */     double[] y = new double[yNumber.length];
/*      */     
/*  816 */     for (int i = 0; i < x.length; i++) {
/*  817 */       x[i] = xNumber[i].doubleValue();
/*  818 */       y[i] = yNumber[i].doubleValue();
/*      */     }
/*      */     
/*  821 */     int[] xIndex = data.indexX();
/*  822 */     int[] indexX = data.getXIndices();
/*  823 */     boolean vertInverted = ((NumberAxis)verticalAxis).isInverted();
/*  824 */     boolean horizInverted = false;
/*  825 */     if ((horizontalAxis instanceof NumberAxis)) {
/*  826 */       horizInverted = ((NumberAxis)horizontalAxis).isInverted();
/*      */     }
/*  828 */     double transX = 0.0D;
/*  829 */     double transXm1 = 0.0D;
/*  830 */     double transXp1 = 0.0D;
/*  831 */     double transDXm1 = 0.0D;
/*  832 */     double transDXp1 = 0.0D;
/*  833 */     double transDX = 0.0D;
/*  834 */     double transY = 0.0D;
/*  835 */     double transYm1 = 0.0D;
/*  836 */     double transYp1 = 0.0D;
/*  837 */     double transDYm1 = 0.0D;
/*  838 */     double transDYp1 = 0.0D;
/*  839 */     double transDY = 0.0D;
/*  840 */     int iMax = xIndex[(xIndex.length - 1)];
/*  841 */     for (int k = 0; k < x.length; k++) {
/*  842 */       int i = xIndex[k];
/*  843 */       if (indexX[i] == k) {
/*  844 */         if (i == 0) {
/*  845 */           transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
/*      */           
/*  847 */           transXm1 = transX;
/*  848 */           transXp1 = horizontalAxis.valueToJava2D(x[indexX[(i + 1)]], dataArea, RectangleEdge.BOTTOM);
/*      */           
/*  850 */           transDXm1 = Math.abs(0.5D * (transX - transXm1));
/*  851 */           transDXp1 = Math.abs(0.5D * (transX - transXp1));
/*      */         }
/*  853 */         else if (i == iMax) {
/*  854 */           transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
/*      */           
/*  856 */           transXm1 = horizontalAxis.valueToJava2D(x[indexX[(i - 1)]], dataArea, RectangleEdge.BOTTOM);
/*      */           
/*  858 */           transXp1 = transX;
/*  859 */           transDXm1 = Math.abs(0.5D * (transX - transXm1));
/*  860 */           transDXp1 = Math.abs(0.5D * (transX - transXp1));
/*      */         }
/*      */         else {
/*  863 */           transX = horizontalAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM);
/*      */           
/*  865 */           transXp1 = horizontalAxis.valueToJava2D(x[indexX[(i + 1)]], dataArea, RectangleEdge.BOTTOM);
/*      */           
/*  867 */           transDXm1 = transDXp1;
/*  868 */           transDXp1 = Math.abs(0.5D * (transX - transXp1));
/*      */         }
/*      */         
/*  871 */         if (horizInverted) {
/*  872 */           transX -= transDXp1;
/*      */         }
/*      */         else {
/*  875 */           transX -= transDXm1;
/*      */         }
/*      */         
/*  878 */         transDX = transDXm1 + transDXp1;
/*      */         
/*  880 */         transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
/*      */         
/*  882 */         transYm1 = transY;
/*  883 */         if (k + 1 == y.length) {
/*      */           continue;
/*      */         }
/*  886 */         transYp1 = verticalAxis.valueToJava2D(y[(k + 1)], dataArea, RectangleEdge.LEFT);
/*      */         
/*  888 */         transDYm1 = Math.abs(0.5D * (transY - transYm1));
/*  889 */         transDYp1 = Math.abs(0.5D * (transY - transYp1));
/*      */       }
/*  891 */       else if (((i < indexX.length - 1) && (indexX[(i + 1)] - 1 == k)) || (k == x.length - 1))
/*      */       {
/*      */ 
/*  894 */         transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
/*      */         
/*  896 */         transYm1 = verticalAxis.valueToJava2D(y[(k - 1)], dataArea, RectangleEdge.LEFT);
/*      */         
/*  898 */         transYp1 = transY;
/*  899 */         transDYm1 = Math.abs(0.5D * (transY - transYm1));
/*  900 */         transDYp1 = Math.abs(0.5D * (transY - transYp1));
/*      */       }
/*      */       else {
/*  903 */         transY = verticalAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT);
/*      */         
/*  905 */         transYp1 = verticalAxis.valueToJava2D(y[(k + 1)], dataArea, RectangleEdge.LEFT);
/*      */         
/*  907 */         transDYm1 = transDYp1;
/*  908 */         transDYp1 = Math.abs(0.5D * (transY - transYp1));
/*      */       }
/*  910 */       if (vertInverted) {
/*  911 */         transY -= transDYm1;
/*      */       }
/*      */       else {
/*  914 */         transY -= transDYp1;
/*      */       }
/*      */       
/*  917 */       transDY = transDYm1 + transDYp1;
/*      */       
/*  919 */       rect.setRect(transX, transY, transDX, transDY);
/*  920 */       if (zNumber[k] != null) {
/*  921 */         g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
/*  922 */         g2.fill(rect);
/*      */       }
/*  924 */       else if (this.missingPaint != null) {
/*  925 */         g2.setPaint(this.missingPaint);
/*  926 */         g2.fill(rect);
/*      */       }
/*      */       
/*  929 */       entityArea = rect;
/*      */       
/*      */ 
/*  932 */       if (entities != null) {
/*  933 */         String tip = "";
/*  934 */         if (getToolTipGenerator() != null) {
/*  935 */           tip = this.toolTipGenerator.generateToolTip(data, k);
/*      */         }
/*      */         
/*      */ 
/*  939 */         String url = null;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  945 */         ContourEntity entity = new ContourEntity((Rectangle2D.Double)entityArea.clone(), tip, url);
/*      */         
/*  947 */         entity.setIndex(k);
/*  948 */         entities.add(entity);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  953 */       if (plot.isDomainCrosshairLockedOnData()) {
/*  954 */         if (plot.isRangeCrosshairLockedOnData())
/*      */         {
/*  956 */           crosshairState.updateCrosshairPoint(x[k], y[k], transX, transY, PlotOrientation.VERTICAL);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  961 */           crosshairState.updateCrosshairX(transX);
/*      */         }
/*      */         
/*      */       }
/*  965 */       else if (plot.isRangeCrosshairLockedOnData())
/*      */       {
/*  967 */         crosshairState.updateCrosshairY(transY);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  972 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void pointRenderer(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, ContourPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, ColorBar colorBar, ContourDataset data, CrosshairState crosshairState)
/*      */   {
/* 1003 */     RectangularShape entityArea = null;
/* 1004 */     EntityCollection entities = null;
/* 1005 */     if (info != null) {
/* 1006 */       entities = info.getOwner().getEntityCollection();
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1011 */     RectangularShape rect = new Ellipse2D.Double();
/*      */     
/*      */ 
/*      */ 
/* 1015 */     Object antiAlias = g2.getRenderingHint(RenderingHints.KEY_ANTIALIASING);
/* 1016 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1021 */     Number[] xNumber = data.getXValues();
/* 1022 */     Number[] yNumber = data.getYValues();
/* 1023 */     Number[] zNumber = data.getZValues();
/*      */     
/* 1025 */     double[] x = new double[xNumber.length];
/* 1026 */     double[] y = new double[yNumber.length];
/*      */     
/* 1028 */     for (int i = 0; i < x.length; i++) {
/* 1029 */       x[i] = xNumber[i].doubleValue();
/* 1030 */       y[i] = yNumber[i].doubleValue();
/*      */     }
/*      */     
/* 1033 */     double transX = 0.0D;
/* 1034 */     double transDX = 0.0D;
/* 1035 */     double transY = 0.0D;
/* 1036 */     double transDY = 0.0D;
/* 1037 */     double size = dataArea.getWidth() * this.ptSizePct;
/* 1038 */     for (int k = 0; k < x.length; k++)
/*      */     {
/* 1040 */       transX = domainAxis.valueToJava2D(x[k], dataArea, RectangleEdge.BOTTOM) - 0.5D * size;
/*      */       
/* 1042 */       transY = rangeAxis.valueToJava2D(y[k], dataArea, RectangleEdge.LEFT) - 0.5D * size;
/*      */       
/* 1044 */       transDX = size;
/* 1045 */       transDY = size;
/*      */       
/* 1047 */       rect.setFrame(transX, transY, transDX, transDY);
/*      */       
/* 1049 */       if (zNumber[k] != null) {
/* 1050 */         g2.setPaint(colorBar.getPaint(zNumber[k].doubleValue()));
/* 1051 */         g2.fill(rect);
/*      */       }
/* 1053 */       else if (this.missingPaint != null) {
/* 1054 */         g2.setPaint(this.missingPaint);
/* 1055 */         g2.fill(rect);
/*      */       }
/*      */       
/*      */ 
/* 1059 */       entityArea = rect;
/*      */       
/*      */ 
/* 1062 */       if (entities != null) {
/* 1063 */         String tip = null;
/* 1064 */         if (getToolTipGenerator() != null) {
/* 1065 */           tip = this.toolTipGenerator.generateToolTip(data, k);
/*      */         }
/* 1067 */         String url = null;
/*      */         
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1073 */         ContourEntity entity = new ContourEntity((RectangularShape)entityArea.clone(), tip, url);
/*      */         
/* 1075 */         entity.setIndex(k);
/* 1076 */         entities.add(entity);
/*      */       }
/*      */       
/*      */ 
/* 1080 */       if (plot.isDomainCrosshairLockedOnData()) {
/* 1081 */         if (plot.isRangeCrosshairLockedOnData())
/*      */         {
/* 1083 */           crosshairState.updateCrosshairPoint(x[k], y[k], transX, transY, PlotOrientation.VERTICAL);
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/* 1088 */           crosshairState.updateCrosshairX(transX);
/*      */         }
/*      */         
/*      */       }
/* 1092 */       else if (plot.isRangeCrosshairLockedOnData())
/*      */       {
/* 1094 */         crosshairState.updateCrosshairY(transY);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1100 */     g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1118 */     double xx = getDomainAxis().valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/*      */     
/* 1120 */     Line2D line = new Line2D.Double(xx, dataArea.getMinY(), xx, dataArea.getMaxY());
/*      */     
/* 1122 */     g2.setStroke(stroke);
/* 1123 */     g2.setPaint(paint);
/* 1124 */     g2.draw(line);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
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
/* 1141 */     double yy = getRangeAxis().valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */     
/* 1143 */     Line2D line = new Line2D.Double(dataArea.getMinX(), yy, dataArea.getMaxX(), yy);
/*      */     
/* 1145 */     g2.setStroke(stroke);
/* 1146 */     g2.setPaint(paint);
/* 1147 */     g2.draw(line);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void handleClick(int x, int y, PlotRenderingInfo info) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1190 */     if (percent <= 0.0D)
/*      */     {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1200 */       getRangeAxis().setAutoRange(true);
/* 1201 */       getDomainAxis().setAutoRange(true);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/* 1212 */     return localizationResources.getString("Contour_Plot");
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
/* 1224 */     if (this.dataset == null) {
/* 1225 */       return null;
/*      */     }
/*      */     
/* 1228 */     Range result = null;
/*      */     
/* 1230 */     if (axis == getDomainAxis()) {
/* 1231 */       result = DatasetUtilities.findDomainBounds(this.dataset);
/*      */     }
/* 1233 */     else if (axis == getRangeAxis()) {
/* 1234 */       result = DatasetUtilities.findRangeBounds(this.dataset);
/*      */     }
/*      */     
/* 1237 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range getContourDataRange()
/*      */   {
/* 1248 */     Range result = null;
/*      */     
/* 1250 */     ContourDataset data = getDataset();
/*      */     
/* 1252 */     if (data != null) {
/* 1253 */       Range h = getDomainAxis().getRange();
/* 1254 */       Range v = getRangeAxis().getRange();
/* 1255 */       result = visibleRange(data, h, v);
/*      */     }
/*      */     
/* 1258 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void propertyChange(PropertyChangeEvent event)
/*      */   {
/* 1269 */     fireChangeEvent();
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
/* 1281 */     if (this.domainAxis != null) {
/* 1282 */       this.domainAxis.configure();
/*      */     }
/* 1284 */     if (this.rangeAxis != null) {
/* 1285 */       this.rangeAxis.configure();
/*      */     }
/* 1287 */     if (this.colorBar != null) {
/* 1288 */       this.colorBar.configure(this);
/*      */     }
/* 1290 */     super.datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ColorBar getColorBar()
/*      */   {
/* 1299 */     return this.colorBar;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainCrosshairVisible()
/*      */   {
/* 1308 */     return this.domainCrosshairVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairVisible(boolean flag)
/*      */   {
/* 1318 */     if (this.domainCrosshairVisible != flag) {
/* 1319 */       this.domainCrosshairVisible = flag;
/* 1320 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainCrosshairLockedOnData()
/*      */   {
/* 1332 */     return this.domainCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairLockedOnData(boolean flag)
/*      */   {
/* 1342 */     if (this.domainCrosshairLockedOnData != flag) {
/* 1343 */       this.domainCrosshairLockedOnData = flag;
/* 1344 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getDomainCrosshairValue()
/*      */   {
/* 1354 */     return this.domainCrosshairValue;
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
/* 1366 */     setDomainCrosshairValue(value, true);
/*      */   }
/*      */   
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
/* 1380 */     this.domainCrosshairValue = value;
/* 1381 */     if ((isDomainCrosshairVisible()) && (notify)) {
/* 1382 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getDomainCrosshairStroke()
/*      */   {
/* 1392 */     return this.domainCrosshairStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairStroke(Stroke stroke)
/*      */   {
/* 1402 */     this.domainCrosshairStroke = stroke;
/* 1403 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getDomainCrosshairPaint()
/*      */   {
/* 1412 */     return this.domainCrosshairPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainCrosshairPaint(Paint paint)
/*      */   {
/* 1422 */     this.domainCrosshairPaint = paint;
/* 1423 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeCrosshairVisible()
/*      */   {
/* 1432 */     return this.rangeCrosshairVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairVisible(boolean flag)
/*      */   {
/* 1441 */     if (this.rangeCrosshairVisible != flag) {
/* 1442 */       this.rangeCrosshairVisible = flag;
/* 1443 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeCrosshairLockedOnData()
/*      */   {
/* 1454 */     return this.rangeCrosshairLockedOnData;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairLockedOnData(boolean flag)
/*      */   {
/* 1464 */     if (this.rangeCrosshairLockedOnData != flag) {
/* 1465 */       this.rangeCrosshairLockedOnData = flag;
/* 1466 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getRangeCrosshairValue()
/*      */   {
/* 1476 */     return this.rangeCrosshairValue;
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
/* 1488 */     setRangeCrosshairValue(value, true);
/*      */   }
/*      */   
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
/* 1502 */     this.rangeCrosshairValue = value;
/* 1503 */     if ((isRangeCrosshairVisible()) && (notify)) {
/* 1504 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getRangeCrosshairStroke()
/*      */   {
/* 1514 */     return this.rangeCrosshairStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairStroke(Stroke stroke)
/*      */   {
/* 1524 */     this.rangeCrosshairStroke = stroke;
/* 1525 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getRangeCrosshairPaint()
/*      */   {
/* 1534 */     return this.rangeCrosshairPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeCrosshairPaint(Paint paint)
/*      */   {
/* 1544 */     this.rangeCrosshairPaint = paint;
/* 1545 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ContourToolTipGenerator getToolTipGenerator()
/*      */   {
/* 1554 */     return this.toolTipGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setToolTipGenerator(ContourToolTipGenerator generator)
/*      */   {
/* 1564 */     this.toolTipGenerator = generator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYURLGenerator getURLGenerator()
/*      */   {
/* 1573 */     return this.urlGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURLGenerator(XYURLGenerator urlGenerator)
/*      */   {
/* 1583 */     this.urlGenerator = urlGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawDomainMarker(Graphics2D g2, ContourPlot plot, ValueAxis domainAxis, Marker marker, Rectangle2D dataArea)
/*      */   {
/* 1601 */     if ((marker instanceof ValueMarker)) {
/* 1602 */       ValueMarker vm = (ValueMarker)marker;
/* 1603 */       double value = vm.getValue();
/* 1604 */       Range range = domainAxis.getRange();
/* 1605 */       if (!range.contains(value)) {
/* 1606 */         return;
/*      */       }
/*      */       
/* 1609 */       double x = domainAxis.valueToJava2D(value, dataArea, RectangleEdge.BOTTOM);
/*      */       
/* 1611 */       Line2D line = new Line2D.Double(x, dataArea.getMinY(), x, dataArea.getMaxY());
/*      */       
/* 1613 */       Paint paint = marker.getOutlinePaint();
/* 1614 */       Stroke stroke = marker.getOutlineStroke();
/* 1615 */       g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 1616 */       g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 1617 */       g2.draw(line);
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
/*      */   public void drawRangeMarker(Graphics2D g2, ContourPlot plot, ValueAxis rangeAxis, Marker marker, Rectangle2D dataArea)
/*      */   {
/* 1637 */     if ((marker instanceof ValueMarker)) {
/* 1638 */       ValueMarker vm = (ValueMarker)marker;
/* 1639 */       double value = vm.getValue();
/* 1640 */       Range range = rangeAxis.getRange();
/* 1641 */       if (!range.contains(value)) {
/* 1642 */         return;
/*      */       }
/*      */       
/* 1645 */       double y = rangeAxis.valueToJava2D(value, dataArea, RectangleEdge.LEFT);
/*      */       
/* 1647 */       Line2D line = new Line2D.Double(dataArea.getMinX(), y, dataArea.getMaxX(), y);
/*      */       
/* 1649 */       Paint paint = marker.getOutlinePaint();
/* 1650 */       Stroke stroke = marker.getOutlineStroke();
/* 1651 */       g2.setPaint(paint != null ? paint : Plot.DEFAULT_OUTLINE_PAINT);
/* 1652 */       g2.setStroke(stroke != null ? stroke : Plot.DEFAULT_OUTLINE_STROKE);
/* 1653 */       g2.draw(line);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ClipPath getClipPath()
/*      */   {
/* 1663 */     return this.clipPath;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setClipPath(ClipPath clipPath)
/*      */   {
/* 1671 */     this.clipPath = clipPath;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getPtSizePct()
/*      */   {
/* 1679 */     return this.ptSizePct;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRenderAsPoints()
/*      */   {
/* 1687 */     return this.renderAsPoints;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPtSizePct(double ptSizePct)
/*      */   {
/* 1695 */     this.ptSizePct = ptSizePct;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderAsPoints(boolean renderAsPoints)
/*      */   {
/* 1703 */     this.renderAsPoints = renderAsPoints;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void axisChanged(AxisChangeEvent event)
/*      */   {
/* 1712 */     Object source = event.getSource();
/* 1713 */     if ((source.equals(this.rangeAxis)) || (source.equals(this.domainAxis))) {
/* 1714 */       ColorBar cba = this.colorBar;
/* 1715 */       if (this.colorBar.getAxis().isAutoRange()) {
/* 1716 */         cba.getAxis().configure();
/*      */       }
/*      */     }
/*      */     
/* 1720 */     super.axisChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range visibleRange(ContourDataset data, Range x, Range y)
/*      */   {
/* 1733 */     Range range = null;
/* 1734 */     range = data.getZValueRange(x, y);
/* 1735 */     return range;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getMissingPaint()
/*      */   {
/* 1743 */     return this.missingPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMissingPaint(Paint paint)
/*      */   {
/* 1752 */     this.missingPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomDomainAxes(double x, double y, double factor) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomDomainAxes(double x, double y, double lowerPercent, double upperPercent) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRangeAxes(double x, double y, double factor) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomRangeAxes(double x, double y, double lowerPercent, double upperPercent) {}
/*      */   
/*      */ 
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
/* 1810 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/* 1819 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1827 */     ContourPlot clone = (ContourPlot)super.clone();
/*      */     
/* 1829 */     if (this.domainAxis != null) {
/* 1830 */       clone.domainAxis = ((ValueAxis)this.domainAxis.clone());
/* 1831 */       clone.domainAxis.setPlot(clone);
/* 1832 */       clone.domainAxis.addChangeListener(clone);
/*      */     }
/* 1834 */     if (this.rangeAxis != null) {
/* 1835 */       clone.rangeAxis = ((ValueAxis)this.rangeAxis.clone());
/* 1836 */       clone.rangeAxis.setPlot(clone);
/* 1837 */       clone.rangeAxis.addChangeListener(clone);
/*      */     }
/*      */     
/* 1840 */     if (clone.dataset != null) {
/* 1841 */       clone.dataset.addChangeListener(clone);
/*      */     }
/*      */     
/* 1844 */     if (this.colorBar != null) {
/* 1845 */       clone.colorBar = ((ColorBar)this.colorBar.clone());
/*      */     }
/*      */     
/* 1848 */     clone.domainMarkers = ((List)ObjectUtilities.deepClone(this.domainMarkers));
/*      */     
/* 1850 */     clone.rangeMarkers = ((List)ObjectUtilities.deepClone(this.rangeMarkers));
/*      */     
/* 1852 */     clone.annotations = ((List)ObjectUtilities.deepClone(this.annotations));
/*      */     
/* 1854 */     if (this.clipPath != null) {
/* 1855 */       clone.clipPath = ((ClipPath)this.clipPath.clone());
/*      */     }
/*      */     
/* 1858 */     return clone;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/ContourPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */