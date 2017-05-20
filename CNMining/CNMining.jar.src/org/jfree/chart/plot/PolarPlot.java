/*      */ package org.jfree.chart.plot;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.FontMetrics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.axis.AxisState;
/*      */ import org.jfree.chart.axis.NumberTick;
/*      */ import org.jfree.chart.axis.NumberTickUnit;
/*      */ import org.jfree.chart.axis.TickUnit;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.event.RendererChangeListener;
/*      */ import org.jfree.chart.renderer.PolarItemRenderer;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
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
/*      */ public class PolarPlot
/*      */   extends Plot
/*      */   implements ValueAxisPlot, Zoomable, RendererChangeListener, Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 3794383185924179525L;
/*      */   private static final int MARGIN = 20;
/*      */   private static final double ANNOTATION_MARGIN = 7.0D;
/*      */   public static final double DEFAULT_ANGLE_TICK_UNIT_SIZE = 45.0D;
/*  126 */   public static final Stroke DEFAULT_GRIDLINE_STROKE = new BasicStroke(0.5F, 0, 2, 0.0F, new float[] { 2.0F, 2.0F }, 0.0F);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  131 */   public static final Paint DEFAULT_GRIDLINE_PAINT = Color.gray;
/*      */   
/*      */ 
/*  134 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private List angleTicks;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private ValueAxis axis;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private XYDataset dataset;
/*      */   
/*      */ 
/*      */ 
/*      */   private PolarItemRenderer renderer;
/*      */   
/*      */ 
/*      */ 
/*      */   private TickUnit angleTickUnit;
/*      */   
/*      */ 
/*      */ 
/*  161 */   private boolean angleLabelsVisible = true;
/*      */   
/*      */ 
/*  164 */   private Font angleLabelFont = new Font("SansSerif", 0, 12);
/*      */   
/*      */ 
/*  167 */   private transient Paint angleLabelPaint = Color.black;
/*      */   
/*      */ 
/*      */   private boolean angleGridlinesVisible;
/*      */   
/*      */ 
/*      */   private transient Stroke angleGridlineStroke;
/*      */   
/*      */ 
/*      */   private transient Paint angleGridlinePaint;
/*      */   
/*      */ 
/*      */   private boolean radiusGridlinesVisible;
/*      */   
/*      */ 
/*      */   private transient Stroke radiusGridlineStroke;
/*      */   
/*      */ 
/*      */   private transient Paint radiusGridlinePaint;
/*      */   
/*      */ 
/*  188 */   private List cornerTextItems = new ArrayList();
/*      */   
/*      */ 
/*      */ 
/*      */   public PolarPlot()
/*      */   {
/*  194 */     this(null, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PolarPlot(XYDataset dataset, ValueAxis radiusAxis, PolarItemRenderer renderer)
/*      */   {
/*  210 */     this.dataset = dataset;
/*  211 */     if (this.dataset != null) {
/*  212 */       this.dataset.addChangeListener(this);
/*      */     }
/*  214 */     this.angleTickUnit = new NumberTickUnit(45.0D);
/*      */     
/*  216 */     this.axis = radiusAxis;
/*  217 */     if (this.axis != null) {
/*  218 */       this.axis.setPlot(this);
/*  219 */       this.axis.addChangeListener(this);
/*      */     }
/*      */     
/*  222 */     this.renderer = renderer;
/*  223 */     if (this.renderer != null) {
/*  224 */       this.renderer.setPlot(this);
/*  225 */       this.renderer.addChangeListener(this);
/*      */     }
/*      */     
/*  228 */     this.angleGridlinesVisible = true;
/*  229 */     this.angleGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  230 */     this.angleGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */     
/*  232 */     this.radiusGridlinesVisible = true;
/*  233 */     this.radiusGridlineStroke = DEFAULT_GRIDLINE_STROKE;
/*  234 */     this.radiusGridlinePaint = DEFAULT_GRIDLINE_PAINT;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addCornerTextItem(String text)
/*      */   {
/*  246 */     if (text == null) {
/*  247 */       throw new IllegalArgumentException("Null 'text' argument.");
/*      */     }
/*  249 */     this.cornerTextItems.add(text);
/*  250 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeCornerTextItem(String text)
/*      */   {
/*  262 */     boolean removed = this.cornerTextItems.remove(text);
/*  263 */     if (removed) {
/*  264 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearCornerTextItems()
/*      */   {
/*  276 */     if (this.cornerTextItems.size() > 0) {
/*  277 */       this.cornerTextItems.clear();
/*  278 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/*  288 */     return localizationResources.getString("Polar_Plot");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ValueAxis getAxis()
/*      */   {
/*  299 */     return this.axis;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxis(ValueAxis axis)
/*      */   {
/*  309 */     if (axis != null) {
/*  310 */       axis.setPlot(this);
/*      */     }
/*      */     
/*      */ 
/*  314 */     if (this.axis != null) {
/*  315 */       this.axis.removeChangeListener(this);
/*      */     }
/*      */     
/*  318 */     this.axis = axis;
/*  319 */     if (this.axis != null) {
/*  320 */       this.axis.configure();
/*  321 */       this.axis.addChangeListener(this);
/*      */     }
/*  323 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYDataset getDataset()
/*      */   {
/*  334 */     return this.dataset;
/*      */   }
/*      */   
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
/*  348 */     XYDataset existing = this.dataset;
/*  349 */     if (existing != null) {
/*  350 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  354 */     this.dataset = dataset;
/*  355 */     if (this.dataset != null) {
/*  356 */       setDatasetGroup(this.dataset.getGroup());
/*  357 */       this.dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  361 */     DatasetChangeEvent event = new DatasetChangeEvent(this, this.dataset);
/*  362 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PolarItemRenderer getRenderer()
/*      */   {
/*  373 */     return this.renderer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderer(PolarItemRenderer renderer)
/*      */   {
/*  387 */     if (this.renderer != null) {
/*  388 */       this.renderer.removeChangeListener(this);
/*      */     }
/*      */     
/*  391 */     this.renderer = renderer;
/*  392 */     if (this.renderer != null) {
/*  393 */       this.renderer.setPlot(this);
/*      */     }
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
/*      */   public TickUnit getAngleTickUnit()
/*      */   {
/*  407 */     return this.angleTickUnit;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleTickUnit(TickUnit unit)
/*      */   {
/*  419 */     if (unit == null) {
/*  420 */       throw new IllegalArgumentException("Null 'unit' argument.");
/*      */     }
/*  422 */     this.angleTickUnit = unit;
/*  423 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isAngleLabelsVisible()
/*      */   {
/*  434 */     return this.angleLabelsVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleLabelsVisible(boolean visible)
/*      */   {
/*  446 */     if (this.angleLabelsVisible != visible) {
/*  447 */       this.angleLabelsVisible = visible;
/*  448 */       fireChangeEvent();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getAngleLabelFont()
/*      */   {
/*  460 */     return this.angleLabelFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleLabelFont(Font font)
/*      */   {
/*  472 */     if (font == null) {
/*  473 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  475 */     this.angleLabelFont = font;
/*  476 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getAngleLabelPaint()
/*      */   {
/*  487 */     return this.angleLabelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleLabelPaint(Paint paint)
/*      */   {
/*  497 */     if (paint == null) {
/*  498 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  500 */     this.angleLabelPaint = paint;
/*  501 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isAngleGridlinesVisible()
/*      */   {
/*  513 */     return this.angleGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleGridlinesVisible(boolean visible)
/*      */   {
/*  528 */     if (this.angleGridlinesVisible != visible) {
/*  529 */       this.angleGridlinesVisible = visible;
/*  530 */       fireChangeEvent();
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
/*      */   public Stroke getAngleGridlineStroke()
/*      */   {
/*  543 */     return this.angleGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleGridlineStroke(Stroke stroke)
/*      */   {
/*  557 */     this.angleGridlineStroke = stroke;
/*  558 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getAngleGridlinePaint()
/*      */   {
/*  570 */     return this.angleGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAngleGridlinePaint(Paint paint)
/*      */   {
/*  583 */     this.angleGridlinePaint = paint;
/*  584 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRadiusGridlinesVisible()
/*      */   {
/*  596 */     return this.radiusGridlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRadiusGridlinesVisible(boolean visible)
/*      */   {
/*  611 */     if (this.radiusGridlinesVisible != visible) {
/*  612 */       this.radiusGridlinesVisible = visible;
/*  613 */       fireChangeEvent();
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
/*      */   public Stroke getRadiusGridlineStroke()
/*      */   {
/*  626 */     return this.radiusGridlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRadiusGridlineStroke(Stroke stroke)
/*      */   {
/*  640 */     this.radiusGridlineStroke = stroke;
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
/*      */   public Paint getRadiusGridlinePaint()
/*      */   {
/*  653 */     return this.radiusGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRadiusGridlinePaint(Paint paint)
/*      */   {
/*  667 */     this.radiusGridlinePaint = paint;
/*  668 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected List refreshAngleTicks()
/*      */   {
/*  679 */     List ticks = new ArrayList();
/*  680 */     for (double currentTickVal = 0.0D; currentTickVal < 360.0D; 
/*  681 */         currentTickVal += this.angleTickUnit.getSize()) {
/*  682 */       NumberTick tick = new NumberTick(new Double(currentTickVal), this.angleTickUnit.valueToString(currentTickVal), TextAnchor.CENTER, TextAnchor.CENTER, 0.0D);
/*      */       
/*      */ 
/*  685 */       ticks.add(tick);
/*      */     }
/*  687 */     return ticks;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*  717 */     boolean b1 = area.getWidth() <= 10.0D;
/*  718 */     boolean b2 = area.getHeight() <= 10.0D;
/*  719 */     if ((b1) || (b2)) {
/*  720 */       return;
/*      */     }
/*      */     
/*      */ 
/*  724 */     if (info != null) {
/*  725 */       info.setPlotArea(area);
/*      */     }
/*      */     
/*      */ 
/*  729 */     RectangleInsets insets = getInsets();
/*  730 */     insets.trim(area);
/*      */     
/*  732 */     Rectangle2D dataArea = area;
/*  733 */     if (info != null) {
/*  734 */       info.setDataArea(dataArea);
/*      */     }
/*      */     
/*      */ 
/*  738 */     drawBackground(g2, dataArea);
/*  739 */     double h = Math.min(dataArea.getWidth() / 2.0D, dataArea.getHeight() / 2.0D) - 20.0D;
/*      */     
/*  741 */     Rectangle2D quadrant = new Rectangle2D.Double(dataArea.getCenterX(), dataArea.getCenterY(), h, h);
/*      */     
/*  743 */     AxisState state = drawAxis(g2, area, quadrant);
/*  744 */     if (this.renderer != null) {
/*  745 */       Shape originalClip = g2.getClip();
/*  746 */       Composite originalComposite = g2.getComposite();
/*      */       
/*  748 */       g2.clip(dataArea);
/*  749 */       g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */       
/*      */ 
/*  752 */       this.angleTicks = refreshAngleTicks();
/*  753 */       drawGridlines(g2, dataArea, this.angleTicks, state.getTicks());
/*      */       
/*      */ 
/*  756 */       render(g2, dataArea, info);
/*      */       
/*  758 */       g2.setClip(originalClip);
/*  759 */       g2.setComposite(originalComposite);
/*      */     }
/*  761 */     drawOutline(g2, dataArea);
/*  762 */     drawCornerTextItems(g2, dataArea);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawCornerTextItems(Graphics2D g2, Rectangle2D area)
/*      */   {
/*  772 */     if (this.cornerTextItems.isEmpty()) {
/*  773 */       return;
/*      */     }
/*      */     
/*  776 */     g2.setColor(Color.black);
/*  777 */     double width = 0.0D;
/*  778 */     double height = 0.0D;
/*  779 */     for (Iterator it = this.cornerTextItems.iterator(); it.hasNext();) {
/*  780 */       String msg = (String)it.next();
/*  781 */       FontMetrics fm = g2.getFontMetrics();
/*  782 */       Rectangle2D bounds = TextUtilities.getTextBounds(msg, g2, fm);
/*  783 */       width = Math.max(width, bounds.getWidth());
/*  784 */       height += bounds.getHeight();
/*      */     }
/*      */     
/*  787 */     double xadj = 14.0D;
/*  788 */     double yadj = 7.0D;
/*  789 */     width += xadj;
/*  790 */     height += yadj;
/*      */     
/*  792 */     double x = area.getMaxX() - width;
/*  793 */     double y = area.getMaxY() - height;
/*  794 */     g2.drawRect((int)x, (int)y, (int)width, (int)height);
/*  795 */     x += 7.0D;
/*  796 */     for (Iterator it = this.cornerTextItems.iterator(); it.hasNext();) {
/*  797 */       String msg = (String)it.next();
/*  798 */       Rectangle2D bounds = TextUtilities.getTextBounds(msg, g2, g2.getFontMetrics());
/*      */       
/*  800 */       y += bounds.getHeight();
/*  801 */       g2.drawString(msg, (int)x, (int)y);
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
/*      */   protected AxisState drawAxis(Graphics2D g2, Rectangle2D plotArea, Rectangle2D dataArea)
/*      */   {
/*  816 */     return this.axis.draw(g2, dataArea.getMinY(), plotArea, dataArea, RectangleEdge.TOP, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void render(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info)
/*      */   {
/*  835 */     if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
/*  836 */       int seriesCount = this.dataset.getSeriesCount();
/*  837 */       for (int series = 0; series < seriesCount; series++) {
/*  838 */         this.renderer.drawSeries(g2, dataArea, info, this, this.dataset, series);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/*  843 */       drawNoDataMessage(g2, dataArea);
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
/*      */   protected void drawGridlines(Graphics2D g2, Rectangle2D dataArea, List angularTicks, List radialTicks)
/*      */   {
/*  859 */     if (this.renderer == null) {
/*  860 */       return;
/*      */     }
/*      */     
/*      */ 
/*  864 */     if (isAngleGridlinesVisible()) {
/*  865 */       Stroke gridStroke = getAngleGridlineStroke();
/*  866 */       Paint gridPaint = getAngleGridlinePaint();
/*  867 */       if ((gridStroke != null) && (gridPaint != null)) {
/*  868 */         this.renderer.drawAngularGridLines(g2, this, angularTicks, dataArea);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  874 */     if (isRadiusGridlinesVisible()) {
/*  875 */       Stroke gridStroke = getRadiusGridlineStroke();
/*  876 */       Paint gridPaint = getRadiusGridlinePaint();
/*  877 */       if ((gridStroke != null) && (gridPaint != null)) {
/*  878 */         this.renderer.drawRadialGridLines(g2, this, this.axis, radialTicks, dataArea);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoom(double percent)
/*      */   {
/*  890 */     if (percent > 0.0D) {
/*  891 */       double radius = getMaxRadius();
/*  892 */       double scaledRadius = radius * percent;
/*  893 */       this.axis.setUpperBound(scaledRadius);
/*  894 */       getAxis().setAutoRange(false);
/*      */     }
/*      */     else {
/*  897 */       getAxis().setAutoRange(true);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range getDataRange(ValueAxis axis)
/*      */   {
/*  909 */     Range result = null;
/*  910 */     if (this.dataset != null) {
/*  911 */       result = Range.combine(result, DatasetUtilities.findRangeBounds(this.dataset));
/*      */     }
/*      */     
/*  914 */     return result;
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
/*  926 */     if (this.axis != null) {
/*  927 */       this.axis.configure();
/*      */     }
/*      */     
/*  930 */     if (getParent() != null) {
/*  931 */       getParent().datasetChanged(event);
/*      */     }
/*      */     else {
/*  934 */       super.datasetChanged(event);
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
/*  946 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSeriesCount()
/*      */   {
/*  956 */     int result = 0;
/*      */     
/*  958 */     if (this.dataset != null) {
/*  959 */       result = this.dataset.getSeriesCount();
/*      */     }
/*  961 */     return result;
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
/*  972 */     LegendItemCollection result = new LegendItemCollection();
/*      */     
/*      */ 
/*  975 */     if ((this.dataset != null) && 
/*  976 */       (this.renderer != null)) {
/*  977 */       int seriesCount = this.dataset.getSeriesCount();
/*  978 */       for (int i = 0; i < seriesCount; i++) {
/*  979 */         LegendItem item = this.renderer.getLegendItem(i);
/*  980 */         result.add(item);
/*      */       }
/*      */     }
/*      */     
/*  984 */     return result;
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
/*  995 */     if (obj == this) {
/*  996 */       return true;
/*      */     }
/*  998 */     if (!(obj instanceof PolarPlot)) {
/*  999 */       return false;
/*      */     }
/* 1001 */     PolarPlot that = (PolarPlot)obj;
/* 1002 */     if (!ObjectUtilities.equal(this.axis, that.axis)) {
/* 1003 */       return false;
/*      */     }
/* 1005 */     if (!ObjectUtilities.equal(this.renderer, that.renderer)) {
/* 1006 */       return false;
/*      */     }
/* 1008 */     if (!this.angleTickUnit.equals(that.angleTickUnit)) {
/* 1009 */       return false;
/*      */     }
/* 1011 */     if (this.angleGridlinesVisible != that.angleGridlinesVisible) {
/* 1012 */       return false;
/*      */     }
/* 1014 */     if (this.angleLabelsVisible != that.angleLabelsVisible) {
/* 1015 */       return false;
/*      */     }
/* 1017 */     if (!this.angleLabelFont.equals(that.angleLabelFont)) {
/* 1018 */       return false;
/*      */     }
/* 1020 */     if (!PaintUtilities.equal(this.angleLabelPaint, that.angleLabelPaint)) {
/* 1021 */       return false;
/*      */     }
/* 1023 */     if (!ObjectUtilities.equal(this.angleGridlineStroke, that.angleGridlineStroke))
/*      */     {
/* 1025 */       return false;
/*      */     }
/* 1027 */     if (!PaintUtilities.equal(this.angleGridlinePaint, that.angleGridlinePaint))
/*      */     {
/*      */ 
/* 1030 */       return false;
/*      */     }
/* 1032 */     if (this.radiusGridlinesVisible != that.radiusGridlinesVisible) {
/* 1033 */       return false;
/*      */     }
/* 1035 */     if (!ObjectUtilities.equal(this.radiusGridlineStroke, that.radiusGridlineStroke))
/*      */     {
/* 1037 */       return false;
/*      */     }
/* 1039 */     if (!PaintUtilities.equal(this.radiusGridlinePaint, that.radiusGridlinePaint))
/*      */     {
/* 1041 */       return false;
/*      */     }
/* 1043 */     if (!this.cornerTextItems.equals(that.cornerTextItems)) {
/* 1044 */       return false;
/*      */     }
/* 1046 */     return super.equals(obj);
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
/* 1059 */     PolarPlot clone = (PolarPlot)super.clone();
/* 1060 */     if (this.axis != null) {
/* 1061 */       clone.axis = ((ValueAxis)ObjectUtilities.clone(this.axis));
/* 1062 */       clone.axis.setPlot(clone);
/* 1063 */       clone.axis.addChangeListener(clone);
/*      */     }
/*      */     
/* 1066 */     if (clone.dataset != null) {
/* 1067 */       clone.dataset.addChangeListener(clone);
/*      */     }
/*      */     
/* 1070 */     if (this.renderer != null) {
/* 1071 */       clone.renderer = ((PolarItemRenderer)ObjectUtilities.clone(this.renderer));
/*      */     }
/*      */     
/*      */ 
/* 1075 */     clone.cornerTextItems = new ArrayList(this.cornerTextItems);
/*      */     
/* 1077 */     return clone;
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
/* 1088 */     stream.defaultWriteObject();
/* 1089 */     SerialUtilities.writeStroke(this.angleGridlineStroke, stream);
/* 1090 */     SerialUtilities.writePaint(this.angleGridlinePaint, stream);
/* 1091 */     SerialUtilities.writeStroke(this.radiusGridlineStroke, stream);
/* 1092 */     SerialUtilities.writePaint(this.radiusGridlinePaint, stream);
/* 1093 */     SerialUtilities.writePaint(this.angleLabelPaint, stream);
/*      */   }
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
/* 1107 */     stream.defaultReadObject();
/* 1108 */     this.angleGridlineStroke = SerialUtilities.readStroke(stream);
/* 1109 */     this.angleGridlinePaint = SerialUtilities.readPaint(stream);
/* 1110 */     this.radiusGridlineStroke = SerialUtilities.readStroke(stream);
/* 1111 */     this.radiusGridlinePaint = SerialUtilities.readPaint(stream);
/* 1112 */     this.angleLabelPaint = SerialUtilities.readPaint(stream);
/*      */     
/* 1114 */     if (this.axis != null) {
/* 1115 */       this.axis.setPlot(this);
/* 1116 */       this.axis.addChangeListener(this);
/*      */     }
/*      */     
/* 1119 */     if (this.dataset != null) {
/* 1120 */       this.dataset.addChangeListener(this);
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
/*      */   public void zoomDomainAxes(double factor, PlotRenderingInfo state, Point2D source, boolean useAnchor) {}
/*      */   
/*      */ 
/*      */ 
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
/*      */   public void zoomRangeAxes(double factor, PlotRenderingInfo state, Point2D source)
/*      */   {
/* 1176 */     zoom(factor);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 1194 */     if (useAnchor)
/*      */     {
/*      */ 
/* 1197 */       double sourceX = source.getX();
/* 1198 */       double anchorX = this.axis.java2DToValue(sourceX, info.getDataArea(), RectangleEdge.BOTTOM);
/*      */       
/* 1200 */       this.axis.resizeRange(factor, anchorX);
/*      */     }
/*      */     else {
/* 1203 */       this.axis.resizeRange(factor);
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
/*      */   public void zoomRangeAxes(double lowerPercent, double upperPercent, PlotRenderingInfo state, Point2D source)
/*      */   {
/* 1218 */     zoom((upperPercent + lowerPercent) / 2.0D);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainZoomable()
/*      */   {
/* 1227 */     return false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/* 1236 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PlotOrientation getOrientation()
/*      */   {
/* 1245 */     return PlotOrientation.HORIZONTAL;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMaxRadius()
/*      */   {
/* 1254 */     return this.axis.getUpperBound();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Point translateValueThetaRadiusToJava2D(double angleDegrees, double radius, Rectangle2D dataArea)
/*      */   {
/* 1272 */     double radians = Math.toRadians(angleDegrees - 90.0D);
/*      */     
/* 1274 */     double minx = dataArea.getMinX() + 20.0D;
/* 1275 */     double maxx = dataArea.getMaxX() - 20.0D;
/* 1276 */     double miny = dataArea.getMinY() + 20.0D;
/* 1277 */     double maxy = dataArea.getMaxY() - 20.0D;
/*      */     
/* 1279 */     double lengthX = maxx - minx;
/* 1280 */     double lengthY = maxy - miny;
/* 1281 */     double length = Math.min(lengthX, lengthY);
/*      */     
/* 1283 */     double midX = minx + lengthX / 2.0D;
/* 1284 */     double midY = miny + lengthY / 2.0D;
/*      */     
/* 1286 */     double axisMin = this.axis.getLowerBound();
/* 1287 */     double axisMax = getMaxRadius();
/* 1288 */     double adjustedRadius = Math.max(radius, axisMin);
/*      */     
/* 1290 */     double xv = length / 2.0D * Math.cos(radians);
/* 1291 */     double yv = length / 2.0D * Math.sin(radians);
/*      */     
/* 1293 */     float x = (float)(midX + xv * (adjustedRadius - axisMin) / (axisMax - axisMin));
/*      */     
/* 1295 */     float y = (float)(midY + yv * (adjustedRadius - axisMin) / (axisMax - axisMin));
/*      */     
/*      */ 
/* 1298 */     int ix = Math.round(x);
/* 1299 */     int iy = Math.round(y);
/*      */     
/* 1301 */     Point p = new Point(ix, iy);
/* 1302 */     return p;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PolarPlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */