/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.GradientPaint;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.LegendItemSource;
/*      */ import org.jfree.chart.axis.AxisLocation;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.PlotEntity;
/*      */ import org.jfree.chart.event.AxisChangeEvent;
/*      */ import org.jfree.chart.event.AxisChangeListener;
/*      */ import org.jfree.chart.event.ChartChangeEventType;
/*      */ import org.jfree.chart.event.MarkerChangeEvent;
/*      */ import org.jfree.chart.event.MarkerChangeListener;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.PlotChangeListener;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetChangeListener;
/*      */ import org.jfree.data.general.DatasetGroup;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.G2TextMeasurer;
/*      */ import org.jfree.text.TextBlock;
/*      */ import org.jfree.text.TextBlockAnchor;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.Align;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
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
/*      */ public abstract class Plot
/*      */   implements AxisChangeListener, DatasetChangeListener, MarkerChangeListener, LegendItemSource, PublicCloneable, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -8831571430103671324L;
/*  195 */   public static final Number ZERO = new Integer(0);
/*      */   
/*      */ 
/*  198 */   public static final RectangleInsets DEFAULT_INSETS = new RectangleInsets(4.0D, 8.0D, 4.0D, 8.0D);
/*      */   
/*      */ 
/*      */ 
/*  202 */   public static final Stroke DEFAULT_OUTLINE_STROKE = new BasicStroke(0.5F);
/*      */   
/*      */ 
/*  205 */   public static final Paint DEFAULT_OUTLINE_PAINT = Color.gray;
/*      */   
/*      */ 
/*      */   public static final float DEFAULT_FOREGROUND_ALPHA = 1.0F;
/*      */   
/*      */ 
/*      */   public static final float DEFAULT_BACKGROUND_ALPHA = 1.0F;
/*      */   
/*      */ 
/*  214 */   public static final Paint DEFAULT_BACKGROUND_PAINT = Color.white;
/*      */   
/*      */ 
/*      */   public static final int MINIMUM_WIDTH_TO_DRAW = 10;
/*      */   
/*      */ 
/*      */   public static final int MINIMUM_HEIGHT_TO_DRAW = 10;
/*      */   
/*      */ 
/*  223 */   public static final Shape DEFAULT_LEGEND_ITEM_BOX = new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/*      */   
/*      */ 
/*      */ 
/*  227 */   public static final Shape DEFAULT_LEGEND_ITEM_CIRCLE = new Ellipse2D.Double(-4.0D, -4.0D, 8.0D, 8.0D);
/*      */   
/*      */ 
/*      */ 
/*      */   private Plot parent;
/*      */   
/*      */ 
/*      */ 
/*      */   private DatasetGroup datasetGroup;
/*      */   
/*      */ 
/*      */ 
/*      */   private String noDataMessage;
/*      */   
/*      */ 
/*      */ 
/*      */   private Font noDataMessageFont;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint noDataMessagePaint;
/*      */   
/*      */ 
/*      */   private RectangleInsets insets;
/*      */   
/*      */ 
/*      */   private boolean outlineVisible;
/*      */   
/*      */ 
/*      */   private transient Stroke outlineStroke;
/*      */   
/*      */ 
/*      */   private transient Paint outlinePaint;
/*      */   
/*      */ 
/*      */   private transient Paint backgroundPaint;
/*      */   
/*      */ 
/*      */   private transient Image backgroundImage;
/*      */   
/*      */ 
/*  268 */   private int backgroundImageAlignment = 15;
/*      */   
/*      */ 
/*  271 */   private float backgroundImageAlpha = 0.5F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private float foregroundAlpha;
/*      */   
/*      */ 
/*      */ 
/*      */   private float backgroundAlpha;
/*      */   
/*      */ 
/*      */ 
/*      */   private DrawingSupplier drawingSupplier;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient EventListenerList listenerList;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean notify;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Plot()
/*      */   {
/*  299 */     this.parent = null;
/*  300 */     this.insets = DEFAULT_INSETS;
/*  301 */     this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
/*  302 */     this.backgroundAlpha = 1.0F;
/*  303 */     this.backgroundImage = null;
/*  304 */     this.outlineVisible = true;
/*  305 */     this.outlineStroke = DEFAULT_OUTLINE_STROKE;
/*  306 */     this.outlinePaint = DEFAULT_OUTLINE_PAINT;
/*  307 */     this.foregroundAlpha = 1.0F;
/*      */     
/*  309 */     this.noDataMessage = null;
/*  310 */     this.noDataMessageFont = new Font("SansSerif", 0, 12);
/*  311 */     this.noDataMessagePaint = Color.black;
/*      */     
/*  313 */     this.drawingSupplier = new DefaultDrawingSupplier();
/*      */     
/*  315 */     this.notify = true;
/*  316 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DatasetGroup getDatasetGroup()
/*      */   {
/*  328 */     return this.datasetGroup;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setDatasetGroup(DatasetGroup group)
/*      */   {
/*  339 */     this.datasetGroup = group;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getNoDataMessage()
/*      */   {
/*  353 */     return this.noDataMessage;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNoDataMessage(String message)
/*      */   {
/*  366 */     this.noDataMessage = message;
/*  367 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getNoDataMessageFont()
/*      */   {
/*  379 */     return this.noDataMessageFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNoDataMessageFont(Font font)
/*      */   {
/*  391 */     if (font == null) {
/*  392 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  394 */     this.noDataMessageFont = font;
/*  395 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getNoDataMessagePaint()
/*      */   {
/*  407 */     return this.noDataMessagePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNoDataMessagePaint(Paint paint)
/*      */   {
/*  419 */     if (paint == null) {
/*  420 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  422 */     this.noDataMessagePaint = paint;
/*  423 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract String getPlotType();
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Plot getParent()
/*      */   {
/*  447 */     return this.parent;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setParent(Plot parent)
/*      */   {
/*  459 */     this.parent = parent;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Plot getRootPlot()
/*      */   {
/*  471 */     Plot p = getParent();
/*  472 */     if (p == null) {
/*  473 */       return this;
/*      */     }
/*      */     
/*  476 */     return p.getRootPlot();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isSubplot()
/*      */   {
/*  492 */     return getParent() != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getInsets()
/*      */   {
/*  503 */     return this.insets;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInsets(RectangleInsets insets)
/*      */   {
/*  516 */     setInsets(insets, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInsets(RectangleInsets insets, boolean notify)
/*      */   {
/*  531 */     if (insets == null) {
/*  532 */       throw new IllegalArgumentException("Null 'insets' argument.");
/*      */     }
/*  534 */     if (!this.insets.equals(insets)) {
/*  535 */       this.insets = insets;
/*  536 */       if (notify) {
/*  537 */         fireChangeEvent();
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
/*      */   public Paint getBackgroundPaint()
/*      */   {
/*  551 */     return this.backgroundPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBackgroundPaint(Paint paint)
/*      */   {
/*  564 */     if (paint == null) {
/*  565 */       if (this.backgroundPaint != null) {
/*  566 */         this.backgroundPaint = null;
/*  567 */         fireChangeEvent();
/*      */       }
/*      */     }
/*      */     else {
/*  571 */       if ((this.backgroundPaint != null) && 
/*  572 */         (this.backgroundPaint.equals(paint))) {
/*  573 */         return;
/*      */       }
/*      */       
/*  576 */       this.backgroundPaint = paint;
/*  577 */       fireChangeEvent();
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
/*      */   public float getBackgroundAlpha()
/*      */   {
/*  590 */     return this.backgroundAlpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBackgroundAlpha(float alpha)
/*      */   {
/*  602 */     if (this.backgroundAlpha != alpha) {
/*  603 */       this.backgroundAlpha = alpha;
/*  604 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DrawingSupplier getDrawingSupplier()
/*      */   {
/*  616 */     DrawingSupplier result = null;
/*  617 */     Plot p = getParent();
/*  618 */     if (p != null) {
/*  619 */       result = p.getDrawingSupplier();
/*      */     }
/*      */     else {
/*  622 */       result = this.drawingSupplier;
/*      */     }
/*  624 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDrawingSupplier(DrawingSupplier supplier)
/*      */   {
/*  640 */     this.drawingSupplier = supplier;
/*  641 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDrawingSupplier(DrawingSupplier supplier, boolean notify)
/*      */   {
/*  660 */     this.drawingSupplier = supplier;
/*  661 */     if (notify) {
/*  662 */       fireChangeEvent();
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
/*      */   public Image getBackgroundImage()
/*      */   {
/*  675 */     return this.backgroundImage;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBackgroundImage(Image image)
/*      */   {
/*  687 */     this.backgroundImage = image;
/*  688 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getBackgroundImageAlignment()
/*      */   {
/*  701 */     return this.backgroundImageAlignment;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBackgroundImageAlignment(int alignment)
/*      */   {
/*  715 */     if (this.backgroundImageAlignment != alignment) {
/*  716 */       this.backgroundImageAlignment = alignment;
/*  717 */       fireChangeEvent();
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
/*      */   public float getBackgroundImageAlpha()
/*      */   {
/*  731 */     return this.backgroundImageAlpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBackgroundImageAlpha(float alpha)
/*      */   {
/*  746 */     if ((alpha < 0.0F) || (alpha > 1.0F)) {
/*  747 */       throw new IllegalArgumentException("The 'alpha' value must be in the range 0.0f to 1.0f.");
/*      */     }
/*  749 */     if (this.backgroundImageAlpha != alpha) {
/*  750 */       this.backgroundImageAlpha = alpha;
/*  751 */       fireChangeEvent();
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
/*      */   public boolean isOutlineVisible()
/*      */   {
/*  769 */     return this.outlineVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOutlineVisible(boolean visible)
/*      */   {
/*  783 */     this.outlineVisible = visible;
/*  784 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getOutlineStroke()
/*      */   {
/*  795 */     return this.outlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOutlineStroke(Stroke stroke)
/*      */   {
/*  808 */     if (stroke == null) {
/*  809 */       if (this.outlineStroke != null) {
/*  810 */         this.outlineStroke = null;
/*  811 */         fireChangeEvent();
/*      */       }
/*      */     }
/*      */     else {
/*  815 */       if ((this.outlineStroke != null) && 
/*  816 */         (this.outlineStroke.equals(stroke))) {
/*  817 */         return;
/*      */       }
/*      */       
/*  820 */       this.outlineStroke = stroke;
/*  821 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getOutlinePaint()
/*      */   {
/*  833 */     return this.outlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setOutlinePaint(Paint paint)
/*      */   {
/*  846 */     if (paint == null) {
/*  847 */       if (this.outlinePaint != null) {
/*  848 */         this.outlinePaint = null;
/*  849 */         fireChangeEvent();
/*      */       }
/*      */     }
/*      */     else {
/*  853 */       if ((this.outlinePaint != null) && 
/*  854 */         (this.outlinePaint.equals(paint))) {
/*  855 */         return;
/*      */       }
/*      */       
/*  858 */       this.outlinePaint = paint;
/*  859 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getForegroundAlpha()
/*      */   {
/*  871 */     return this.foregroundAlpha;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setForegroundAlpha(float alpha)
/*      */   {
/*  883 */     if (this.foregroundAlpha != alpha) {
/*  884 */       this.foregroundAlpha = alpha;
/*  885 */       fireChangeEvent();
/*      */     }
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
/*  897 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isNotify()
/*      */   {
/*  911 */     return this.notify;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNotify(boolean notify)
/*      */   {
/*  925 */     this.notify = notify;
/*      */     
/*  927 */     if (notify) {
/*  928 */       notifyListeners(new PlotChangeEvent(this));
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addChangeListener(PlotChangeListener listener)
/*      */   {
/*  940 */     this.listenerList.add(PlotChangeListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeChangeListener(PlotChangeListener listener)
/*      */   {
/*  951 */     this.listenerList.remove(PlotChangeListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void notifyListeners(PlotChangeEvent event)
/*      */   {
/*  962 */     if (!this.notify) {
/*  963 */       return;
/*      */     }
/*  965 */     Object[] listeners = this.listenerList.getListenerList();
/*  966 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/*  967 */       if (listeners[i] == PlotChangeListener.class) {
/*  968 */         ((PlotChangeListener)listeners[(i + 1)]).plotChanged(event);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void fireChangeEvent()
/*      */   {
/*  979 */     notifyListeners(new PlotChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public abstract void draw(Graphics2D paramGraphics2D, Rectangle2D paramRectangle2D, Point2D paramPoint2D, PlotState paramPlotState, PlotRenderingInfo paramPlotRenderingInfo);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1015 */     fillBackground(g2, area);
/* 1016 */     drawBackgroundImage(g2, area);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void fillBackground(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 1030 */     fillBackground(g2, area, PlotOrientation.VERTICAL);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void fillBackground(Graphics2D g2, Rectangle2D area, PlotOrientation orientation)
/*      */   {
/* 1047 */     if (orientation == null) {
/* 1048 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1050 */     if (this.backgroundPaint == null) {
/* 1051 */       return;
/*      */     }
/* 1053 */     Paint p = this.backgroundPaint;
/* 1054 */     if ((p instanceof GradientPaint)) {
/* 1055 */       GradientPaint gp = (GradientPaint)p;
/* 1056 */       if (orientation == PlotOrientation.VERTICAL) {
/* 1057 */         p = new GradientPaint((float)area.getCenterX(), (float)area.getMaxY(), gp.getColor1(), (float)area.getCenterX(), (float)area.getMinY(), gp.getColor2());
/*      */ 
/*      */ 
/*      */ 
/*      */       }
/* 1062 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 1063 */         p = new GradientPaint((float)area.getMinX(), (float)area.getCenterY(), gp.getColor1(), (float)area.getMaxX(), (float)area.getCenterY(), gp.getColor2());
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1069 */     Composite originalComposite = g2.getComposite();
/* 1070 */     g2.setComposite(AlphaComposite.getInstance(3, this.backgroundAlpha));
/*      */     
/* 1072 */     g2.setPaint(p);
/* 1073 */     g2.fill(area);
/* 1074 */     g2.setComposite(originalComposite);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawBackgroundImage(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 1089 */     if (this.backgroundImage != null) {
/* 1090 */       Composite originalComposite = g2.getComposite();
/* 1091 */       g2.setComposite(AlphaComposite.getInstance(3, this.backgroundImageAlpha));
/*      */       
/* 1093 */       Rectangle2D dest = new Rectangle2D.Double(0.0D, 0.0D, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
/*      */       
/*      */ 
/* 1096 */       Align.align(dest, area, this.backgroundImageAlignment);
/* 1097 */       g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth() + 1, (int)dest.getHeight() + 1, null);
/*      */       
/*      */ 
/* 1100 */       g2.setComposite(originalComposite);
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
/*      */   public void drawOutline(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 1114 */     if (!this.outlineVisible) {
/* 1115 */       return;
/*      */     }
/* 1117 */     if ((this.outlineStroke != null) && (this.outlinePaint != null)) {
/* 1118 */       g2.setStroke(this.outlineStroke);
/* 1119 */       g2.setPaint(this.outlinePaint);
/* 1120 */       g2.draw(area);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawNoDataMessage(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 1131 */     Shape savedClip = g2.getClip();
/* 1132 */     g2.clip(area);
/* 1133 */     String message = this.noDataMessage;
/* 1134 */     if (message != null) {
/* 1135 */       g2.setFont(this.noDataMessageFont);
/* 1136 */       g2.setPaint(this.noDataMessagePaint);
/* 1137 */       TextBlock block = TextUtilities.createTextBlock(this.noDataMessage, this.noDataMessageFont, this.noDataMessagePaint, 0.9F * (float)area.getWidth(), new G2TextMeasurer(g2));
/*      */       
/*      */ 
/*      */ 
/* 1141 */       block.draw(g2, (float)area.getCenterX(), (float)area.getCenterY(), TextBlockAnchor.CENTER);
/*      */     }
/*      */     
/* 1144 */     g2.setClip(savedClip);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void createAndAddEntity(Rectangle2D dataArea, PlotRenderingInfo plotState, String toolTip, String urlText)
/*      */   {
/* 1163 */     if ((plotState != null) && (plotState.getOwner() != null)) {
/* 1164 */       EntityCollection e = plotState.getOwner().getEntityCollection();
/* 1165 */       if (e != null) {
/* 1166 */         e.add(new PlotEntity(dataArea, this, toolTip, urlText));
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
/*      */   public void zoom(double percent) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void axisChanged(AxisChangeEvent event)
/*      */   {
/* 1201 */     fireChangeEvent();
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
/* 1213 */     PlotChangeEvent newEvent = new PlotChangeEvent(this);
/* 1214 */     newEvent.setType(ChartChangeEventType.DATASET_UPDATED);
/* 1215 */     notifyListeners(newEvent);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void markerChanged(MarkerChangeEvent event)
/*      */   {
/* 1227 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double getRectX(double x, double w1, double w2, RectangleEdge edge)
/*      */   {
/* 1243 */     double result = x;
/* 1244 */     if (edge == RectangleEdge.LEFT) {
/* 1245 */       result += w1;
/*      */     }
/* 1247 */     else if (edge == RectangleEdge.RIGHT) {
/* 1248 */       result += w2;
/*      */     }
/* 1250 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double getRectY(double y, double h1, double h2, RectangleEdge edge)
/*      */   {
/* 1267 */     double result = y;
/* 1268 */     if (edge == RectangleEdge.TOP) {
/* 1269 */       result += h1;
/*      */     }
/* 1271 */     else if (edge == RectangleEdge.BOTTOM) {
/* 1272 */       result += h2;
/*      */     }
/* 1274 */     return result;
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
/* 1286 */     if (obj == this) {
/* 1287 */       return true;
/*      */     }
/* 1289 */     if (!(obj instanceof Plot)) {
/* 1290 */       return false;
/*      */     }
/* 1292 */     Plot that = (Plot)obj;
/* 1293 */     if (!ObjectUtilities.equal(this.noDataMessage, that.noDataMessage)) {
/* 1294 */       return false;
/*      */     }
/* 1296 */     if (!ObjectUtilities.equal(this.noDataMessageFont, that.noDataMessageFont))
/*      */     {
/*      */ 
/* 1299 */       return false;
/*      */     }
/* 1301 */     if (!PaintUtilities.equal(this.noDataMessagePaint, that.noDataMessagePaint))
/*      */     {
/* 1303 */       return false;
/*      */     }
/* 1305 */     if (!ObjectUtilities.equal(this.insets, that.insets)) {
/* 1306 */       return false;
/*      */     }
/* 1308 */     if (this.outlineVisible != that.outlineVisible) {
/* 1309 */       return false;
/*      */     }
/* 1311 */     if (!ObjectUtilities.equal(this.outlineStroke, that.outlineStroke)) {
/* 1312 */       return false;
/*      */     }
/* 1314 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 1315 */       return false;
/*      */     }
/* 1317 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 1318 */       return false;
/*      */     }
/* 1320 */     if (!ObjectUtilities.equal(this.backgroundImage, that.backgroundImage))
/*      */     {
/* 1322 */       return false;
/*      */     }
/* 1324 */     if (this.backgroundImageAlignment != that.backgroundImageAlignment) {
/* 1325 */       return false;
/*      */     }
/* 1327 */     if (this.backgroundImageAlpha != that.backgroundImageAlpha) {
/* 1328 */       return false;
/*      */     }
/* 1330 */     if (this.foregroundAlpha != that.foregroundAlpha) {
/* 1331 */       return false;
/*      */     }
/* 1333 */     if (this.backgroundAlpha != that.backgroundAlpha) {
/* 1334 */       return false;
/*      */     }
/* 1336 */     if (!this.drawingSupplier.equals(that.drawingSupplier)) {
/* 1337 */       return false;
/*      */     }
/* 1339 */     if (this.notify != that.notify) {
/* 1340 */       return false;
/*      */     }
/* 1342 */     return true;
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
/* 1355 */     Plot clone = (Plot)super.clone();
/*      */     
/*      */ 
/* 1358 */     if (this.datasetGroup != null) {
/* 1359 */       clone.datasetGroup = ((DatasetGroup)ObjectUtilities.clone(this.datasetGroup));
/*      */     }
/*      */     
/* 1362 */     clone.drawingSupplier = ((DrawingSupplier)ObjectUtilities.clone(this.drawingSupplier));
/*      */     
/* 1364 */     clone.listenerList = new EventListenerList();
/* 1365 */     return clone;
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
/* 1377 */     stream.defaultWriteObject();
/* 1378 */     SerialUtilities.writePaint(this.noDataMessagePaint, stream);
/* 1379 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
/* 1380 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/*      */     
/* 1382 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
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
/* 1395 */     stream.defaultReadObject();
/* 1396 */     this.noDataMessagePaint = SerialUtilities.readPaint(stream);
/* 1397 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/* 1398 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/*      */     
/* 1400 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/*      */     
/* 1402 */     this.listenerList = new EventListenerList();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static RectangleEdge resolveDomainAxisLocation(AxisLocation location, PlotOrientation orientation)
/*      */   {
/* 1417 */     if (location == null) {
/* 1418 */       throw new IllegalArgumentException("Null 'location' argument.");
/*      */     }
/* 1420 */     if (orientation == null) {
/* 1421 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*      */     
/* 1424 */     RectangleEdge result = null;
/*      */     
/* 1426 */     if (location == AxisLocation.TOP_OR_RIGHT) {
/* 1427 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1428 */         result = RectangleEdge.RIGHT;
/*      */       }
/* 1430 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1431 */         result = RectangleEdge.TOP;
/*      */       }
/*      */     }
/* 1434 */     else if (location == AxisLocation.TOP_OR_LEFT) {
/* 1435 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1436 */         result = RectangleEdge.LEFT;
/*      */       }
/* 1438 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1439 */         result = RectangleEdge.TOP;
/*      */       }
/*      */     }
/* 1442 */     else if (location == AxisLocation.BOTTOM_OR_RIGHT) {
/* 1443 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1444 */         result = RectangleEdge.RIGHT;
/*      */       }
/* 1446 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1447 */         result = RectangleEdge.BOTTOM;
/*      */       }
/*      */     }
/* 1450 */     else if (location == AxisLocation.BOTTOM_OR_LEFT) {
/* 1451 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1452 */         result = RectangleEdge.LEFT;
/*      */       }
/* 1454 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1455 */         result = RectangleEdge.BOTTOM;
/*      */       }
/*      */     }
/*      */     
/* 1459 */     if (result == null) {
/* 1460 */       throw new IllegalStateException("resolveDomainAxisLocation()");
/*      */     }
/* 1462 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static RectangleEdge resolveRangeAxisLocation(AxisLocation location, PlotOrientation orientation)
/*      */   {
/* 1477 */     if (location == null) {
/* 1478 */       throw new IllegalArgumentException("Null 'location' argument.");
/*      */     }
/* 1480 */     if (orientation == null) {
/* 1481 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*      */     
/* 1484 */     RectangleEdge result = null;
/*      */     
/* 1486 */     if (location == AxisLocation.TOP_OR_RIGHT) {
/* 1487 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1488 */         result = RectangleEdge.TOP;
/*      */       }
/* 1490 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1491 */         result = RectangleEdge.RIGHT;
/*      */       }
/*      */     }
/* 1494 */     else if (location == AxisLocation.TOP_OR_LEFT) {
/* 1495 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1496 */         result = RectangleEdge.TOP;
/*      */       }
/* 1498 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1499 */         result = RectangleEdge.LEFT;
/*      */       }
/*      */     }
/* 1502 */     else if (location == AxisLocation.BOTTOM_OR_RIGHT) {
/* 1503 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1504 */         result = RectangleEdge.BOTTOM;
/*      */       }
/* 1506 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1507 */         result = RectangleEdge.RIGHT;
/*      */       }
/*      */     }
/* 1510 */     else if (location == AxisLocation.BOTTOM_OR_LEFT) {
/* 1511 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 1512 */         result = RectangleEdge.BOTTOM;
/*      */       }
/* 1514 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 1515 */         result = RectangleEdge.LEFT;
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1520 */     if (result == null) {
/* 1521 */       throw new IllegalStateException("resolveRangeAxisLocation()");
/*      */     }
/* 1523 */     return result;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/Plot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */