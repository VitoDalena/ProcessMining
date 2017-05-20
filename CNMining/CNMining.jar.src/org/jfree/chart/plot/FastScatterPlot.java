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
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.axis.AxisSpace;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.axis.ValueTick;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.util.ArrayUtilities;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FastScatterPlot
/*      */   extends Plot
/*      */   implements ValueAxisPlot, Pannable, Zoomable, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 7871545897358563521L;
/*  111 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  116 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.lightGray;
/*      */   
/*      */ 
/*      */ 
/*      */   private float[][] data;
/*      */   
/*      */ 
/*      */ 
/*      */   private Range xDataRange;
/*      */   
/*      */ 
/*      */ 
/*      */   private Range yDataRange;
/*      */   
/*      */ 
/*      */ 
/*      */   private ValueAxis domainAxis;
/*      */   
/*      */ 
/*      */ 
/*      */   private ValueAxis rangeAxis;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint paint;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean domainGridlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke domainGridlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint domainGridlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean rangeGridlinesVisible;
/*      */   
/*      */ 
/*      */   private transient Stroke rangeGridlineStroke;
/*      */   
/*      */ 
/*      */   private transient Paint rangeGridlinePaint;
/*      */   
/*      */ 
/*      */   private boolean domainPannable;
/*      */   
/*      */ 
/*      */   private boolean rangePannable;
/*      */   
/*      */ 
/*  171 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public FastScatterPlot()
/*      */   {
/*  180 */     this((float[][])null, new NumberAxis("X"), new NumberAxis("Y"));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public FastScatterPlot(float[][] data, ValueAxis domainAxis, ValueAxis rangeAxis)
/*      */   {
/*  196 */     if (domainAxis == null) {
/*  197 */       throw new IllegalArgumentException("Null 'domainAxis' argument.");
/*      */     }
/*  199 */     if (rangeAxis == null) {
/*  200 */       throw new IllegalArgumentException("Null 'rangeAxis' argument.");
/*      */     }
/*      */     
/*  203 */     this.data = data;
/*  204 */     this.xDataRange = calculateXDataRange(data);
/*  205 */     this.yDataRange = calculateYDataRange(data);
/*  206 */     this.domainAxis = domainAxis;
/*  207 */     this.domainAxis.setPlot(this);
/*  208 */     this.domainAxis.addChangeListener(this);
/*  209 */     this.rangeAxis = rangeAxis;
/*  210 */     this.rangeAxis.setPlot(this);
/*  211 */     this.rangeAxis.addChangeListener(this);
/*      */     
/*  213 */     this.paint = Color.red;
/*      */     
/*  215 */     this.domainGridlinesVisible = true;
/*  216 */     this.domainGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  217 */     this.domainGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*      */     
/*  219 */     this.rangeGridlinesVisible = true;
/*  220 */     this.rangeGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*  221 */     this.rangeGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/*  231 */     return localizationResources.getString("Fast_Scatter_Plot");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float[][] getData()
/*      */   {
/*  242 */     return this.data;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setData(float[][] data)
/*      */   {
/*  254 */     this.data = data;
/*  255 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PlotOrientation getOrientation()
/*      */   {
/*  264 */     return PlotOrientation.VERTICAL;
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
/*  275 */     return this.domainAxis;
/*      */   }
/*      */   
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
/*  289 */     if (axis == null) {
/*  290 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*      */     }
/*  292 */     this.domainAxis = axis;
/*  293 */     fireChangeEvent();
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
/*  304 */     return this.rangeAxis;
/*      */   }
/*      */   
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
/*  318 */     if (axis == null) {
/*  319 */       throw new IllegalArgumentException("Null 'axis' argument.");
/*      */     }
/*  321 */     this.rangeAxis = axis;
/*  322 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getPaint()
/*      */   {
/*  334 */     return this.paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPaint(Paint paint)
/*      */   {
/*  346 */     if (paint == null) {
/*  347 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  349 */     this.paint = paint;
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
/*      */   public boolean isDomainGridlinesVisible()
/*      */   {
/*  363 */     return this.domainGridlinesVisible;
/*      */   }
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
/*  376 */     if (this.domainGridlinesVisible != visible) {
/*  377 */       this.domainGridlinesVisible = visible;
/*  378 */       fireChangeEvent();
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
/*  391 */     return this.domainGridlineStroke;
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
/*  403 */     if (stroke == null) {
/*  404 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  406 */     this.domainGridlineStroke = stroke;
/*  407 */     fireChangeEvent();
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
/*  419 */     return this.domainGridlinePaint;
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
/*  431 */     if (paint == null) {
/*  432 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  434 */     this.domainGridlinePaint = paint;
/*  435 */     fireChangeEvent();
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
/*  447 */     return this.rangeGridlinesVisible;
/*      */   }
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
/*  460 */     if (this.rangeGridlinesVisible != visible) {
/*  461 */       this.rangeGridlinesVisible = visible;
/*  462 */       fireChangeEvent();
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
/*  475 */     return this.rangeGridlineStroke;
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
/*  487 */     if (stroke == null) {
/*  488 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/*  490 */     this.rangeGridlineStroke = stroke;
/*  491 */     fireChangeEvent();
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
/*  503 */     return this.rangeGridlinePaint;
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
/*  515 */     if (paint == null) {
/*  516 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  518 */     this.rangeGridlinePaint = paint;
/*  519 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  539 */     if (info != null) {
/*  540 */       info.setPlotArea(area);
/*      */     }
/*      */     
/*      */ 
/*  544 */     RectangleInsets insets = getInsets();
/*  545 */     insets.trim(area);
/*      */     
/*  547 */     AxisSpace space = new AxisSpace();
/*  548 */     space = this.domainAxis.reserveSpace(g2, this, area, RectangleEdge.BOTTOM, space);
/*      */     
/*  550 */     space = this.rangeAxis.reserveSpace(g2, this, area, RectangleEdge.LEFT, space);
/*      */     
/*  552 */     Rectangle2D dataArea = space.shrink(area, null);
/*      */     
/*  554 */     if (info != null) {
/*  555 */       info.setDataArea(dataArea);
/*      */     }
/*      */     
/*      */ 
/*  559 */     drawBackground(g2, dataArea);
/*      */     
/*  561 */     AxisState domainAxisState = this.domainAxis.draw(g2, dataArea.getMaxY(), area, dataArea, RectangleEdge.BOTTOM, info);
/*      */     
/*  563 */     AxisState rangeAxisState = this.rangeAxis.draw(g2, dataArea.getMinX(), area, dataArea, RectangleEdge.LEFT, info);
/*      */     
/*  565 */     drawDomainGridlines(g2, dataArea, domainAxisState.getTicks());
/*  566 */     drawRangeGridlines(g2, dataArea, rangeAxisState.getTicks());
/*      */     
/*  568 */     Shape originalClip = g2.getClip();
/*  569 */     Composite originalComposite = g2.getComposite();
/*      */     
/*  571 */     g2.clip(dataArea);
/*  572 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*      */ 
/*  575 */     render(g2, dataArea, info, null);
/*      */     
/*  577 */     g2.setClip(originalClip);
/*  578 */     g2.setComposite(originalComposite);
/*  579 */     drawOutline(g2, dataArea);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  600 */     g2.setPaint(this.paint);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  615 */     if (this.data != null) {
/*  616 */       for (int i = 0; i < this.data[0].length; i++) {
/*  617 */         float x = this.data[0][i];
/*  618 */         float y = this.data[1][i];
/*      */         
/*      */ 
/*      */ 
/*  622 */         int transX = (int)this.domainAxis.valueToJava2D(x, dataArea, RectangleEdge.BOTTOM);
/*      */         
/*  624 */         int transY = (int)this.rangeAxis.valueToJava2D(y, dataArea, RectangleEdge.LEFT);
/*      */         
/*  626 */         g2.fillRect(transX, transY, 1, 1);
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
/*      */   protected void drawDomainGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks)
/*      */   {
/*  646 */     if (isDomainGridlinesVisible()) {
/*  647 */       Iterator iterator = ticks.iterator();
/*  648 */       while (iterator.hasNext()) {
/*  649 */         ValueTick tick = (ValueTick)iterator.next();
/*  650 */         double v = this.domainAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.BOTTOM);
/*      */         
/*  652 */         Line2D line = new Line2D.Double(v, dataArea.getMinY(), v, dataArea.getMaxY());
/*      */         
/*  654 */         g2.setPaint(getDomainGridlinePaint());
/*  655 */         g2.setStroke(getDomainGridlineStroke());
/*  656 */         g2.draw(line);
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
/*      */   protected void drawRangeGridlines(Graphics2D g2, Rectangle2D dataArea, List ticks)
/*      */   {
/*  672 */     if (isRangeGridlinesVisible()) {
/*  673 */       Iterator iterator = ticks.iterator();
/*  674 */       while (iterator.hasNext()) {
/*  675 */         ValueTick tick = (ValueTick)iterator.next();
/*  676 */         double v = this.rangeAxis.valueToJava2D(tick.getValue(), dataArea, RectangleEdge.LEFT);
/*      */         
/*  678 */         Line2D line = new Line2D.Double(dataArea.getMinX(), v, dataArea.getMaxX(), v);
/*      */         
/*  680 */         g2.setPaint(getRangeGridlinePaint());
/*  681 */         g2.setStroke(getRangeGridlineStroke());
/*  682 */         g2.draw(line);
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
/*      */   public Range getDataRange(ValueAxis axis)
/*      */   {
/*  698 */     Range result = null;
/*  699 */     if (axis == this.domainAxis) {
/*  700 */       result = this.xDataRange;
/*      */     }
/*  702 */     else if (axis == this.rangeAxis) {
/*  703 */       result = this.yDataRange;
/*      */     }
/*  705 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Range calculateXDataRange(float[][] data)
/*      */   {
/*  717 */     Range result = null;
/*      */     
/*  719 */     if (data != null) {
/*  720 */       float lowest = Float.POSITIVE_INFINITY;
/*  721 */       float highest = Float.NEGATIVE_INFINITY;
/*  722 */       for (int i = 0; i < data[0].length; i++) {
/*  723 */         float v = data[0][i];
/*  724 */         if (v < lowest) {
/*  725 */           lowest = v;
/*      */         }
/*  727 */         if (v > highest) {
/*  728 */           highest = v;
/*      */         }
/*      */       }
/*  731 */       if (lowest <= highest) {
/*  732 */         result = new Range(lowest, highest);
/*      */       }
/*      */     }
/*      */     
/*  736 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Range calculateYDataRange(float[][] data)
/*      */   {
/*  749 */     Range result = null;
/*  750 */     if (data != null) {
/*  751 */       float lowest = Float.POSITIVE_INFINITY;
/*  752 */       float highest = Float.NEGATIVE_INFINITY;
/*  753 */       for (int i = 0; i < data[0].length; i++) {
/*  754 */         float v = data[1][i];
/*  755 */         if (v < lowest) {
/*  756 */           lowest = v;
/*      */         }
/*  758 */         if (v > highest) {
/*  759 */           highest = v;
/*      */         }
/*      */       }
/*  762 */       if (lowest <= highest) {
/*  763 */         result = new Range(lowest, highest);
/*      */       }
/*      */     }
/*  766 */     return result;
/*      */   }
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
/*  779 */     this.domainAxis.resizeRange(factor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  797 */     if (useAnchor)
/*      */     {
/*      */ 
/*  800 */       double sourceX = source.getX();
/*  801 */       double anchorX = this.domainAxis.java2DToValue(sourceX, info.getDataArea(), RectangleEdge.BOTTOM);
/*      */       
/*  803 */       this.domainAxis.resizeRange2(factor, anchorX);
/*      */     }
/*      */     else {
/*  806 */       this.domainAxis.resizeRange(factor);
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
/*      */   public void zoomDomainAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source)
/*      */   {
/*  823 */     this.domainAxis.zoomRange(lowerPercent, upperPercent);
/*      */   }
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
/*  835 */     this.rangeAxis.resizeRange(factor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  853 */     if (useAnchor)
/*      */     {
/*      */ 
/*  856 */       double sourceY = source.getY();
/*  857 */       double anchorY = this.rangeAxis.java2DToValue(sourceY, info.getDataArea(), RectangleEdge.LEFT);
/*      */       
/*  859 */       this.rangeAxis.resizeRange2(factor, anchorY);
/*      */     }
/*      */     else {
/*  862 */       this.rangeAxis.resizeRange(factor);
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
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo info, Point2D source)
/*      */   {
/*  879 */     this.rangeAxis.zoomRange(lowerPercent, upperPercent);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainZoomable()
/*      */   {
/*  888 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/*  897 */     return true;
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
/*  909 */     return this.domainPannable;
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
/*  921 */     this.domainPannable = pannable;
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
/*  933 */     return this.rangePannable;
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
/*  945 */     this.rangePannable = pannable;
/*      */   }
/*      */   
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
/*  959 */     if ((!isDomainPannable()) || (this.domainAxis == null)) {
/*  960 */       return;
/*      */     }
/*  962 */     double length = this.domainAxis.getRange().getLength();
/*  963 */     double adj = -percent * length;
/*  964 */     if (this.domainAxis.isInverted()) {
/*  965 */       adj = -adj;
/*      */     }
/*  967 */     this.domainAxis.setRange(this.domainAxis.getLowerBound() + adj, this.domainAxis.getUpperBound() + adj);
/*      */   }
/*      */   
/*      */ 
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
/*  982 */     if ((!isRangePannable()) || (this.rangeAxis == null)) {
/*  983 */       return;
/*      */     }
/*  985 */     double length = this.rangeAxis.getRange().getLength();
/*  986 */     double adj = percent * length;
/*  987 */     if (this.rangeAxis.isInverted()) {
/*  988 */       adj = -adj;
/*      */     }
/*  990 */     this.rangeAxis.setRange(this.rangeAxis.getLowerBound() + adj, this.rangeAxis.getUpperBound() + adj);
/*      */   }
/*      */   
/*      */ 
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
/* 1005 */     if (obj == this) {
/* 1006 */       return true;
/*      */     }
/* 1008 */     if (!super.equals(obj)) {
/* 1009 */       return false;
/*      */     }
/* 1011 */     if (!(obj instanceof FastScatterPlot)) {
/* 1012 */       return false;
/*      */     }
/* 1014 */     FastScatterPlot that = (FastScatterPlot)obj;
/* 1015 */     if (this.domainPannable != that.domainPannable) {
/* 1016 */       return false;
/*      */     }
/* 1018 */     if (this.rangePannable != that.rangePannable) {
/* 1019 */       return false;
/*      */     }
/* 1021 */     if (!ArrayUtilities.equal(this.data, that.data)) {
/* 1022 */       return false;
/*      */     }
/* 1024 */     if (!ObjectUtilities.equal(this.domainAxis, that.domainAxis)) {
/* 1025 */       return false;
/*      */     }
/* 1027 */     if (!ObjectUtilities.equal(this.rangeAxis, that.rangeAxis)) {
/* 1028 */       return false;
/*      */     }
/* 1030 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 1031 */       return false;
/*      */     }
/* 1033 */     if (this.domainGridlinesVisible != that.domainGridlinesVisible) {
/* 1034 */       return false;
/*      */     }
/* 1036 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/*      */     {
/* 1038 */       return false;
/*      */     }
/* 1040 */     if (!ObjectUtilities.equal(this.domainGridlineStroke, that.domainGridlineStroke))
/*      */     {
/* 1042 */       return false;
/*      */     }
/* 1044 */     if ((!this.rangeGridlinesVisible) == that.rangeGridlinesVisible) {
/* 1045 */       return false;
/*      */     }
/* 1047 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/*      */     {
/* 1049 */       return false;
/*      */     }
/* 1051 */     if (!ObjectUtilities.equal(this.rangeGridlineStroke, that.rangeGridlineStroke))
/*      */     {
/* 1053 */       return false;
/*      */     }
/* 1055 */     return true;
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
/* 1068 */     FastScatterPlot clone = (FastScatterPlot)super.clone();
/* 1069 */     if (this.data != null) {
/* 1070 */       clone.data = ArrayUtilities.clone(this.data);
/*      */     }
/* 1072 */     if (this.domainAxis != null) {
/* 1073 */       clone.domainAxis = ((ValueAxis)this.domainAxis.clone());
/* 1074 */       clone.domainAxis.setPlot(clone);
/* 1075 */       clone.domainAxis.addChangeListener(clone);
/*      */     }
/* 1077 */     if (this.rangeAxis != null) {
/* 1078 */       clone.rangeAxis = ((ValueAxis)this.rangeAxis.clone());
/* 1079 */       clone.rangeAxis.setPlot(clone);
/* 1080 */       clone.rangeAxis.addChangeListener(clone);
/*      */     }
/* 1082 */     return clone;
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
/* 1094 */     stream.defaultWriteObject();
/* 1095 */     SerialUtilities.writePaint(this.paint, stream);
/* 1096 */     SerialUtilities.writeStroke(this.domainGridlineStroke, stream);
/* 1097 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 1098 */     SerialUtilities.writeStroke(this.rangeGridlineStroke, stream);
/* 1099 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
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
/* 1112 */     stream.defaultReadObject();
/*      */     
/* 1114 */     this.paint = SerialUtilities.readPaint(stream);
/* 1115 */     this.domainGridlineStroke = SerialUtilities.readStroke(stream);
/* 1116 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/*      */     
/* 1118 */     this.rangeGridlineStroke = SerialUtilities.readStroke(stream);
/* 1119 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/*      */     
/* 1121 */     if (this.domainAxis != null) {
/* 1122 */       this.domainAxis.addChangeListener(this);
/*      */     }
/*      */     
/* 1125 */     if (this.rangeAxis != null) {
/* 1126 */       this.rangeAxis.addChangeListener(this);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/FastScatterPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */