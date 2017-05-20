/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.AlphaComposite;
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Composite;
/*      */ import java.awt.Font;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Image;
/*      */ import java.awt.Paint;
/*      */ import java.awt.RenderingHints;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.PrintStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import javax.swing.UIManager;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.block.BlockParams;
/*      */ import org.jfree.chart.block.EntityBlockResult;
/*      */ import org.jfree.chart.block.LengthConstraintType;
/*      */ import org.jfree.chart.block.LineBorder;
/*      */ import org.jfree.chart.block.RectangleConstraint;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.JFreeChartEntity;
/*      */ import org.jfree.chart.event.ChartChangeEvent;
/*      */ import org.jfree.chart.event.ChartChangeListener;
/*      */ import org.jfree.chart.event.ChartProgressEvent;
/*      */ import org.jfree.chart.event.ChartProgressListener;
/*      */ import org.jfree.chart.event.PlotChangeEvent;
/*      */ import org.jfree.chart.event.PlotChangeListener;
/*      */ import org.jfree.chart.event.TitleChangeEvent;
/*      */ import org.jfree.chart.event.TitleChangeListener;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.title.LegendTitle;
/*      */ import org.jfree.chart.title.TextTitle;
/*      */ import org.jfree.chart.title.Title;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.Align;
/*      */ import org.jfree.ui.Drawable;
/*      */ import org.jfree.ui.HorizontalAlignment;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.Size2D;
/*      */ import org.jfree.ui.VerticalAlignment;
/*      */ import org.jfree.ui.about.ProjectInfo;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class JFreeChart
/*      */   implements Drawable, TitleChangeListener, PlotChangeListener, Serializable, Cloneable
/*      */ {
/*      */   private static final long serialVersionUID = -3470703747817429120L;
/*  254 */   public static final ProjectInfo INFO = new JFreeChartInfo();
/*      */   
/*      */ 
/*  257 */   public static final Font DEFAULT_TITLE_FONT = new Font("SansSerif", 1, 18);
/*      */   
/*      */ 
/*      */ 
/*  261 */   public static final Paint DEFAULT_BACKGROUND_PAINT = UIManager.getColor("Panel.background");
/*      */   
/*      */ 
/*      */ 
/*  265 */   public static final Image DEFAULT_BACKGROUND_IMAGE = null;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final int DEFAULT_BACKGROUND_IMAGE_ALIGNMENT = 15;
/*      */   
/*      */ 
/*      */ 
/*      */   public static final float DEFAULT_BACKGROUND_IMAGE_ALPHA = 0.5F;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient RenderingHints renderingHints;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean borderVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke borderStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint borderPaint;
/*      */   
/*      */ 
/*      */   private RectangleInsets padding;
/*      */   
/*      */ 
/*      */   private TextTitle title;
/*      */   
/*      */ 
/*      */   private List subtitles;
/*      */   
/*      */ 
/*      */   private Plot plot;
/*      */   
/*      */ 
/*      */   private transient Paint backgroundPaint;
/*      */   
/*      */ 
/*      */   private transient Image backgroundImage;
/*      */   
/*      */ 
/*  310 */   private int backgroundImageAlignment = 15;
/*      */   
/*      */ 
/*  313 */   private float backgroundImageAlpha = 0.5F;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient EventListenerList changeListeners;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient EventListenerList progressListeners;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean notify;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JFreeChart(Plot plot)
/*      */   {
/*  339 */     this(null, null, plot, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JFreeChart(String title, Plot plot)
/*      */   {
/*  355 */     this(title, DEFAULT_TITLE_FONT, plot, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JFreeChart(String title, Font titleFont, Plot plot, boolean createLegend)
/*      */   {
/*  378 */     if (plot == null) {
/*  379 */       throw new NullPointerException("Null 'plot' argument.");
/*      */     }
/*      */     
/*      */ 
/*  383 */     this.progressListeners = new EventListenerList();
/*  384 */     this.changeListeners = new EventListenerList();
/*  385 */     this.notify = true;
/*      */     
/*      */ 
/*  388 */     this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*      */     
/*      */ 
/*      */ 
/*  392 */     this.borderVisible = false;
/*  393 */     this.borderStroke = new BasicStroke(1.0F);
/*  394 */     this.borderPaint = Color.black;
/*      */     
/*  396 */     this.padding = RectangleInsets.ZERO_INSETS;
/*      */     
/*  398 */     this.plot = plot;
/*  399 */     plot.addChangeListener(this);
/*      */     
/*  401 */     this.subtitles = new ArrayList();
/*      */     
/*      */ 
/*  404 */     if (createLegend) {
/*  405 */       LegendTitle legend = new LegendTitle(this.plot);
/*  406 */       legend.setMargin(new RectangleInsets(1.0D, 1.0D, 1.0D, 1.0D));
/*  407 */       legend.setFrame(new LineBorder());
/*  408 */       legend.setBackgroundPaint(Color.white);
/*  409 */       legend.setPosition(RectangleEdge.BOTTOM);
/*  410 */       this.subtitles.add(legend);
/*  411 */       legend.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  415 */     if (title != null) {
/*  416 */       if (titleFont == null) {
/*  417 */         titleFont = DEFAULT_TITLE_FONT;
/*      */       }
/*  419 */       this.title = new TextTitle(title, titleFont);
/*  420 */       this.title.addChangeListener(this);
/*      */     }
/*      */     
/*  423 */     this.backgroundPaint = DEFAULT_BACKGROUND_PAINT;
/*      */     
/*  425 */     this.backgroundImage = DEFAULT_BACKGROUND_IMAGE;
/*  426 */     this.backgroundImageAlignment = 15;
/*  427 */     this.backgroundImageAlpha = 0.5F;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RenderingHints getRenderingHints()
/*      */   {
/*  439 */     return this.renderingHints;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRenderingHints(RenderingHints renderingHints)
/*      */   {
/*  453 */     if (renderingHints == null) {
/*  454 */       throw new NullPointerException("RenderingHints given are null");
/*      */     }
/*  456 */     this.renderingHints = renderingHints;
/*  457 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isBorderVisible()
/*      */   {
/*  469 */     return this.borderVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBorderVisible(boolean visible)
/*      */   {
/*  481 */     this.borderVisible = visible;
/*  482 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getBorderStroke()
/*      */   {
/*  493 */     return this.borderStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBorderStroke(Stroke stroke)
/*      */   {
/*  504 */     this.borderStroke = stroke;
/*  505 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBorderPaint()
/*      */   {
/*  516 */     return this.borderPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBorderPaint(Paint paint)
/*      */   {
/*  527 */     this.borderPaint = paint;
/*  528 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getPadding()
/*      */   {
/*  539 */     return this.padding;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPadding(RectangleInsets padding)
/*      */   {
/*  551 */     if (padding == null) {
/*  552 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*      */     }
/*  554 */     this.padding = padding;
/*  555 */     notifyListeners(new ChartChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public TextTitle getTitle()
/*      */   {
/*  569 */     return this.title;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTitle(TextTitle title)
/*      */   {
/*  583 */     if (this.title != null) {
/*  584 */       this.title.removeChangeListener(this);
/*      */     }
/*  586 */     this.title = title;
/*  587 */     if (title != null) {
/*  588 */       title.addChangeListener(this);
/*      */     }
/*  590 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTitle(String text)
/*      */   {
/*  606 */     if (text != null) {
/*  607 */       if (this.title == null) {
/*  608 */         setTitle(new TextTitle(text, DEFAULT_TITLE_FONT));
/*      */       }
/*      */       else {
/*  611 */         this.title.setText(text);
/*      */       }
/*      */     }
/*      */     else {
/*  615 */       setTitle((TextTitle)null);
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
/*      */   public void addLegend(LegendTitle legend)
/*      */   {
/*  628 */     addSubtitle(legend);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendTitle getLegend()
/*      */   {
/*  640 */     return getLegend(0);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendTitle getLegend(int index)
/*      */   {
/*  653 */     int seen = 0;
/*  654 */     Iterator iterator = this.subtitles.iterator();
/*  655 */     while (iterator.hasNext()) {
/*  656 */       Title subtitle = (Title)iterator.next();
/*  657 */       if ((subtitle instanceof LegendTitle)) {
/*  658 */         if (seen == index) {
/*  659 */           return (LegendTitle)subtitle;
/*      */         }
/*      */         
/*  662 */         seen++;
/*      */       }
/*      */     }
/*      */     
/*  666 */     return null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeLegend()
/*      */   {
/*  676 */     removeSubtitle(getLegend());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public List getSubtitles()
/*      */   {
/*  687 */     return new ArrayList(this.subtitles);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSubtitles(List subtitles)
/*      */   {
/*  701 */     if (subtitles == null) {
/*  702 */       throw new NullPointerException("Null 'subtitles' argument.");
/*      */     }
/*  704 */     setNotify(false);
/*  705 */     clearSubtitles();
/*  706 */     Iterator iterator = subtitles.iterator();
/*  707 */     while (iterator.hasNext()) {
/*  708 */       Title t = (Title)iterator.next();
/*  709 */       if (t != null) {
/*  710 */         addSubtitle(t);
/*      */       }
/*      */     }
/*  713 */     setNotify(true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getSubtitleCount()
/*      */   {
/*  724 */     return this.subtitles.size();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Title getSubtitle(int index)
/*      */   {
/*  737 */     if ((index < 0) || (index >= getSubtitleCount())) {
/*  738 */       throw new IllegalArgumentException("Index out of range.");
/*      */     }
/*  740 */     return (Title)this.subtitles.get(index);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addSubtitle(Title subtitle)
/*      */   {
/*  752 */     if (subtitle == null) {
/*  753 */       throw new IllegalArgumentException("Null 'subtitle' argument.");
/*      */     }
/*  755 */     this.subtitles.add(subtitle);
/*  756 */     subtitle.addChangeListener(this);
/*  757 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addSubtitle(int index, Title subtitle)
/*      */   {
/*  770 */     if ((index < 0) || (index > getSubtitleCount())) {
/*  771 */       throw new IllegalArgumentException("The 'index' argument is out of range.");
/*      */     }
/*      */     
/*  774 */     if (subtitle == null) {
/*  775 */       throw new IllegalArgumentException("Null 'subtitle' argument.");
/*      */     }
/*  777 */     this.subtitles.add(index, subtitle);
/*  778 */     subtitle.addChangeListener(this);
/*  779 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearSubtitles()
/*      */   {
/*  789 */     Iterator iterator = this.subtitles.iterator();
/*  790 */     while (iterator.hasNext()) {
/*  791 */       Title t = (Title)iterator.next();
/*  792 */       t.removeChangeListener(this);
/*      */     }
/*  794 */     this.subtitles.clear();
/*  795 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeSubtitle(Title title)
/*      */   {
/*  807 */     this.subtitles.remove(title);
/*  808 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Plot getPlot()
/*      */   {
/*  819 */     return this.plot;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryPlot getCategoryPlot()
/*      */   {
/*  833 */     return (CategoryPlot)this.plot;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYPlot getXYPlot()
/*      */   {
/*  847 */     return (XYPlot)this.plot;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAntiAlias()
/*      */   {
/*  859 */     Object val = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
/*  860 */     return RenderingHints.VALUE_ANTIALIAS_ON.equals(val);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAntiAlias(boolean flag)
/*      */   {
/*  875 */     Object val = this.renderingHints.get(RenderingHints.KEY_ANTIALIASING);
/*  876 */     if (val == null) {
/*  877 */       val = RenderingHints.VALUE_ANTIALIAS_DEFAULT;
/*      */     }
/*  879 */     if (((!flag) && (RenderingHints.VALUE_ANTIALIAS_OFF.equals(val))) || ((flag) && (RenderingHints.VALUE_ANTIALIAS_ON.equals(val))))
/*      */     {
/*      */ 
/*  882 */       return;
/*      */     }
/*  884 */     if (flag) {
/*  885 */       this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*      */     }
/*      */     else
/*      */     {
/*  889 */       this.renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
/*      */     }
/*      */     
/*  892 */     fireChartChanged();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object getTextAntiAlias()
/*      */   {
/*  907 */     return this.renderingHints.get(RenderingHints.KEY_TEXT_ANTIALIASING);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTextAntiAlias(boolean flag)
/*      */   {
/*  925 */     if (flag) {
/*  926 */       setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
/*      */     }
/*      */     else {
/*  929 */       setTextAntiAlias(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
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
/*      */   public void setTextAntiAlias(Object val)
/*      */   {
/*  946 */     this.renderingHints.put(RenderingHints.KEY_TEXT_ANTIALIASING, val);
/*  947 */     notifyListeners(new ChartChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBackgroundPaint()
/*      */   {
/*  958 */     return this.backgroundPaint;
/*      */   }
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
/*  971 */     if (this.backgroundPaint != null) {
/*  972 */       if (!this.backgroundPaint.equals(paint)) {
/*  973 */         this.backgroundPaint = paint;
/*  974 */         fireChartChanged();
/*      */       }
/*      */       
/*      */     }
/*  978 */     else if (paint != null) {
/*  979 */       this.backgroundPaint = paint;
/*  980 */       fireChartChanged();
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
/*      */   public Image getBackgroundImage()
/*      */   {
/*  995 */     return this.backgroundImage;
/*      */   }
/*      */   
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
/* 1008 */     if (this.backgroundImage != null) {
/* 1009 */       if (!this.backgroundImage.equals(image)) {
/* 1010 */         this.backgroundImage = image;
/* 1011 */         fireChartChanged();
/*      */       }
/*      */       
/*      */     }
/* 1015 */     else if (image != null) {
/* 1016 */       this.backgroundImage = image;
/* 1017 */       fireChartChanged();
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
/*      */   public int getBackgroundImageAlignment()
/*      */   {
/* 1033 */     return this.backgroundImageAlignment;
/*      */   }
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
/* 1045 */     if (this.backgroundImageAlignment != alignment) {
/* 1046 */       this.backgroundImageAlignment = alignment;
/* 1047 */       fireChartChanged();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public float getBackgroundImageAlpha()
/*      */   {
/* 1059 */     return this.backgroundImageAlpha;
/*      */   }
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
/* 1072 */     if (this.backgroundImageAlpha != alpha) {
/* 1073 */       this.backgroundImageAlpha = alpha;
/* 1074 */       fireChartChanged();
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
/*      */   public boolean isNotify()
/*      */   {
/* 1088 */     return this.notify;
/*      */   }
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
/* 1100 */     this.notify = notify;
/*      */     
/* 1102 */     if (notify) {
/* 1103 */       notifyListeners(new ChartChangeEvent(this));
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
/*      */   public void draw(Graphics2D g2, Rectangle2D area)
/*      */   {
/* 1117 */     draw(g2, area, null, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void draw(Graphics2D g2, Rectangle2D area, ChartRenderingInfo info)
/*      */   {
/* 1129 */     draw(g2, area, null, info);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void draw(Graphics2D g2, Rectangle2D chartArea, Point2D anchor, ChartRenderingInfo info)
/*      */   {
/* 1148 */     notifyListeners(new ChartProgressEvent(this, this, 1, 0));
/*      */     
/*      */ 
/* 1151 */     EntityCollection entities = null;
/*      */     
/* 1153 */     if (info != null) {
/* 1154 */       info.clear();
/* 1155 */       info.setChartArea(chartArea);
/* 1156 */       entities = info.getEntityCollection();
/*      */     }
/* 1158 */     if (entities != null) {
/* 1159 */       entities.add(new JFreeChartEntity((Rectangle2D)chartArea.clone(), this));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1164 */     Shape savedClip = g2.getClip();
/* 1165 */     g2.clip(chartArea);
/*      */     
/* 1167 */     g2.addRenderingHints(this.renderingHints);
/*      */     
/*      */ 
/* 1170 */     if (this.backgroundPaint != null) {
/* 1171 */       g2.setPaint(this.backgroundPaint);
/* 1172 */       g2.fill(chartArea);
/*      */     }
/*      */     
/* 1175 */     if (this.backgroundImage != null) {
/* 1176 */       Composite originalComposite = g2.getComposite();
/* 1177 */       g2.setComposite(AlphaComposite.getInstance(3, this.backgroundImageAlpha));
/*      */       
/* 1179 */       Rectangle2D dest = new Rectangle2D.Double(0.0D, 0.0D, this.backgroundImage.getWidth(null), this.backgroundImage.getHeight(null));
/*      */       
/*      */ 
/* 1182 */       Align.align(dest, chartArea, this.backgroundImageAlignment);
/* 1183 */       g2.drawImage(this.backgroundImage, (int)dest.getX(), (int)dest.getY(), (int)dest.getWidth(), (int)dest.getHeight(), null);
/*      */       
/*      */ 
/* 1186 */       g2.setComposite(originalComposite);
/*      */     }
/*      */     
/* 1189 */     if (isBorderVisible()) {
/* 1190 */       Paint paint = getBorderPaint();
/* 1191 */       Stroke stroke = getBorderStroke();
/* 1192 */       if ((paint != null) && (stroke != null)) {
/* 1193 */         Rectangle2D borderArea = new Rectangle2D.Double(chartArea.getX(), chartArea.getY(), chartArea.getWidth() - 1.0D, chartArea.getHeight() - 1.0D);
/*      */         
/*      */ 
/*      */ 
/* 1197 */         g2.setPaint(paint);
/* 1198 */         g2.setStroke(stroke);
/* 1199 */         g2.draw(borderArea);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1204 */     Rectangle2D nonTitleArea = new Rectangle2D.Double();
/* 1205 */     nonTitleArea.setRect(chartArea);
/* 1206 */     this.padding.trim(nonTitleArea);
/*      */     
/* 1208 */     if (this.title != null) {
/* 1209 */       EntityCollection e = drawTitle(this.title, g2, nonTitleArea, entities != null);
/*      */       
/* 1211 */       if (e != null) {
/* 1212 */         entities.addAll(e);
/*      */       }
/*      */     }
/*      */     
/* 1216 */     Iterator iterator = this.subtitles.iterator();
/* 1217 */     while (iterator.hasNext()) {
/* 1218 */       Title currentTitle = (Title)iterator.next();
/* 1219 */       if (currentTitle.isVisible()) {
/* 1220 */         EntityCollection e = drawTitle(currentTitle, g2, nonTitleArea, entities != null);
/*      */         
/* 1222 */         if (e != null) {
/* 1223 */           entities.addAll(e);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 1228 */     Rectangle2D plotArea = nonTitleArea;
/*      */     
/*      */ 
/* 1231 */     PlotRenderingInfo plotInfo = null;
/* 1232 */     if (info != null) {
/* 1233 */       plotInfo = info.getPlotInfo();
/*      */     }
/* 1235 */     this.plot.draw(g2, plotArea, anchor, null, plotInfo);
/*      */     
/* 1237 */     g2.setClip(savedClip);
/*      */     
/* 1239 */     notifyListeners(new ChartProgressEvent(this, this, 2, 100));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private Rectangle2D createAlignedRectangle2D(Size2D dimensions, Rectangle2D frame, HorizontalAlignment hAlign, VerticalAlignment vAlign)
/*      */   {
/* 1256 */     double x = NaN.0D;
/* 1257 */     double y = NaN.0D;
/* 1258 */     if (hAlign == HorizontalAlignment.LEFT) {
/* 1259 */       x = frame.getX();
/*      */     }
/* 1261 */     else if (hAlign == HorizontalAlignment.CENTER) {
/* 1262 */       x = frame.getCenterX() - dimensions.width / 2.0D;
/*      */     }
/* 1264 */     else if (hAlign == HorizontalAlignment.RIGHT) {
/* 1265 */       x = frame.getMaxX() - dimensions.width;
/*      */     }
/* 1267 */     if (vAlign == VerticalAlignment.TOP) {
/* 1268 */       y = frame.getY();
/*      */     }
/* 1270 */     else if (vAlign == VerticalAlignment.CENTER) {
/* 1271 */       y = frame.getCenterY() - dimensions.height / 2.0D;
/*      */     }
/* 1273 */     else if (vAlign == VerticalAlignment.BOTTOM) {
/* 1274 */       y = frame.getMaxY() - dimensions.height;
/*      */     }
/*      */     
/* 1277 */     return new Rectangle2D.Double(x, y, dimensions.width, dimensions.height);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected EntityCollection drawTitle(Title t, Graphics2D g2, Rectangle2D area, boolean entities)
/*      */   {
/* 1298 */     if (t == null) {
/* 1299 */       throw new IllegalArgumentException("Null 't' argument.");
/*      */     }
/* 1301 */     if (area == null) {
/* 1302 */       throw new IllegalArgumentException("Null 'area' argument.");
/*      */     }
/* 1304 */     Rectangle2D titleArea = new Rectangle2D.Double();
/* 1305 */     RectangleEdge position = t.getPosition();
/* 1306 */     double ww = area.getWidth();
/* 1307 */     if (ww <= 0.0D) {
/* 1308 */       return null;
/*      */     }
/* 1310 */     double hh = area.getHeight();
/* 1311 */     if (hh <= 0.0D) {
/* 1312 */       return null;
/*      */     }
/* 1314 */     RectangleConstraint constraint = new RectangleConstraint(ww, new Range(0.0D, ww), LengthConstraintType.RANGE, hh, new Range(0.0D, hh), LengthConstraintType.RANGE);
/*      */     
/*      */ 
/* 1317 */     Object retValue = null;
/* 1318 */     BlockParams p = new BlockParams();
/* 1319 */     p.setGenerateEntities(entities);
/* 1320 */     if (position == RectangleEdge.TOP) {
/* 1321 */       Size2D size = t.arrange(g2, constraint);
/* 1322 */       titleArea = createAlignedRectangle2D(size, area, t.getHorizontalAlignment(), VerticalAlignment.TOP);
/*      */       
/* 1324 */       retValue = t.draw(g2, titleArea, p);
/* 1325 */       area.setRect(area.getX(), Math.min(area.getY() + size.height, area.getMaxY()), area.getWidth(), Math.max(area.getHeight() - size.height, 0.0D));
/*      */ 
/*      */ 
/*      */     }
/* 1329 */     else if (position == RectangleEdge.BOTTOM) {
/* 1330 */       Size2D size = t.arrange(g2, constraint);
/* 1331 */       titleArea = createAlignedRectangle2D(size, area, t.getHorizontalAlignment(), VerticalAlignment.BOTTOM);
/*      */       
/* 1333 */       retValue = t.draw(g2, titleArea, p);
/* 1334 */       area.setRect(area.getX(), area.getY(), area.getWidth(), area.getHeight() - size.height);
/*      */ 
/*      */     }
/* 1337 */     else if (position == RectangleEdge.RIGHT) {
/* 1338 */       Size2D size = t.arrange(g2, constraint);
/* 1339 */       titleArea = createAlignedRectangle2D(size, area, HorizontalAlignment.RIGHT, t.getVerticalAlignment());
/*      */       
/* 1341 */       retValue = t.draw(g2, titleArea, p);
/* 1342 */       area.setRect(area.getX(), area.getY(), area.getWidth() - size.width, area.getHeight());
/*      */ 
/*      */ 
/*      */     }
/* 1346 */     else if (position == RectangleEdge.LEFT) {
/* 1347 */       Size2D size = t.arrange(g2, constraint);
/* 1348 */       titleArea = createAlignedRectangle2D(size, area, HorizontalAlignment.LEFT, t.getVerticalAlignment());
/*      */       
/* 1350 */       retValue = t.draw(g2, titleArea, p);
/* 1351 */       area.setRect(area.getX() + size.width, area.getY(), area.getWidth() - size.width, area.getHeight());
/*      */     }
/*      */     else
/*      */     {
/* 1355 */       throw new RuntimeException("Unrecognised title position.");
/*      */     }
/* 1357 */     EntityCollection result = null;
/* 1358 */     if ((retValue instanceof EntityBlockResult)) {
/* 1359 */       EntityBlockResult ebr = (EntityBlockResult)retValue;
/* 1360 */       result = ebr.getEntityCollection();
/*      */     }
/* 1362 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BufferedImage createBufferedImage(int width, int height)
/*      */   {
/* 1374 */     return createBufferedImage(width, height, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BufferedImage createBufferedImage(int width, int height, ChartRenderingInfo info)
/*      */   {
/* 1389 */     return createBufferedImage(width, height, 2, info);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BufferedImage createBufferedImage(int width, int height, int imageType, ChartRenderingInfo info)
/*      */   {
/* 1407 */     BufferedImage image = new BufferedImage(width, height, imageType);
/* 1408 */     Graphics2D g2 = image.createGraphics();
/* 1409 */     draw(g2, new Rectangle2D.Double(0.0D, 0.0D, width, height), null, info);
/* 1410 */     g2.dispose();
/* 1411 */     return image;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BufferedImage createBufferedImage(int imageWidth, int imageHeight, double drawWidth, double drawHeight, ChartRenderingInfo info)
/*      */   {
/* 1434 */     BufferedImage image = new BufferedImage(imageWidth, imageHeight, 2);
/*      */     
/* 1436 */     Graphics2D g2 = image.createGraphics();
/* 1437 */     double scaleX = imageWidth / drawWidth;
/* 1438 */     double scaleY = imageHeight / drawHeight;
/* 1439 */     AffineTransform st = AffineTransform.getScaleInstance(scaleX, scaleY);
/* 1440 */     g2.transform(st);
/* 1441 */     draw(g2, new Rectangle2D.Double(0.0D, 0.0D, drawWidth, drawHeight), null, info);
/*      */     
/* 1443 */     g2.dispose();
/* 1444 */     return image;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void handleClick(int x, int y, ChartRenderingInfo info)
/*      */   {
/* 1464 */     this.plot.handleClick(x, y, info.getPlotInfo());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addChangeListener(ChartChangeListener listener)
/*      */   {
/* 1476 */     if (listener == null) {
/* 1477 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*      */     }
/* 1479 */     this.changeListeners.add(ChartChangeListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeChangeListener(ChartChangeListener listener)
/*      */   {
/* 1490 */     if (listener == null) {
/* 1491 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*      */     }
/* 1493 */     this.changeListeners.remove(ChartChangeListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void fireChartChanged()
/*      */   {
/* 1502 */     ChartChangeEvent event = new ChartChangeEvent(this);
/* 1503 */     notifyListeners(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void notifyListeners(ChartChangeEvent event)
/*      */   {
/* 1513 */     if (this.notify) {
/* 1514 */       Object[] listeners = this.changeListeners.getListenerList();
/* 1515 */       for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 1516 */         if (listeners[i] == ChartChangeListener.class) {
/* 1517 */           ((ChartChangeListener)listeners[(i + 1)]).chartChanged(event);
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
/*      */   public void addProgressListener(ChartProgressListener listener)
/*      */   {
/* 1533 */     this.progressListeners.add(ChartProgressListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeProgressListener(ChartProgressListener listener)
/*      */   {
/* 1544 */     this.progressListeners.remove(ChartProgressListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void notifyListeners(ChartProgressEvent event)
/*      */   {
/* 1555 */     Object[] listeners = this.progressListeners.getListenerList();
/* 1556 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 1557 */       if (listeners[i] == ChartProgressListener.class) {
/* 1558 */         ((ChartProgressListener)listeners[(i + 1)]).chartProgress(event);
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
/*      */   public void titleChanged(TitleChangeEvent event)
/*      */   {
/* 1571 */     event.setChart(this);
/* 1572 */     notifyListeners(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void plotChanged(PlotChangeEvent event)
/*      */   {
/* 1582 */     event.setChart(this);
/* 1583 */     notifyListeners(event);
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
/* 1594 */     if (obj == this) {
/* 1595 */       return true;
/*      */     }
/* 1597 */     if (!(obj instanceof JFreeChart)) {
/* 1598 */       return false;
/*      */     }
/* 1600 */     JFreeChart that = (JFreeChart)obj;
/* 1601 */     if (!this.renderingHints.equals(that.renderingHints)) {
/* 1602 */       return false;
/*      */     }
/* 1604 */     if (this.borderVisible != that.borderVisible) {
/* 1605 */       return false;
/*      */     }
/* 1607 */     if (!ObjectUtilities.equal(this.borderStroke, that.borderStroke)) {
/* 1608 */       return false;
/*      */     }
/* 1610 */     if (!PaintUtilities.equal(this.borderPaint, that.borderPaint)) {
/* 1611 */       return false;
/*      */     }
/* 1613 */     if (!this.padding.equals(that.padding)) {
/* 1614 */       return false;
/*      */     }
/* 1616 */     if (!ObjectUtilities.equal(this.title, that.title)) {
/* 1617 */       return false;
/*      */     }
/* 1619 */     if (!ObjectUtilities.equal(this.subtitles, that.subtitles)) {
/* 1620 */       return false;
/*      */     }
/* 1622 */     if (!ObjectUtilities.equal(this.plot, that.plot)) {
/* 1623 */       return false;
/*      */     }
/* 1625 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint))
/*      */     {
/*      */ 
/* 1628 */       return false;
/*      */     }
/* 1630 */     if (!ObjectUtilities.equal(this.backgroundImage, that.backgroundImage))
/*      */     {
/* 1632 */       return false;
/*      */     }
/* 1634 */     if (this.backgroundImageAlignment != that.backgroundImageAlignment) {
/* 1635 */       return false;
/*      */     }
/* 1637 */     if (this.backgroundImageAlpha != that.backgroundImageAlpha) {
/* 1638 */       return false;
/*      */     }
/* 1640 */     if (this.notify != that.notify) {
/* 1641 */       return false;
/*      */     }
/* 1643 */     return true;
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
/* 1654 */     stream.defaultWriteObject();
/* 1655 */     SerialUtilities.writeStroke(this.borderStroke, stream);
/* 1656 */     SerialUtilities.writePaint(this.borderPaint, stream);
/* 1657 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
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
/* 1670 */     stream.defaultReadObject();
/* 1671 */     this.borderStroke = SerialUtilities.readStroke(stream);
/* 1672 */     this.borderPaint = SerialUtilities.readPaint(stream);
/* 1673 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 1674 */     this.progressListeners = new EventListenerList();
/* 1675 */     this.changeListeners = new EventListenerList();
/* 1676 */     this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1681 */     if (this.title != null) {
/* 1682 */       this.title.addChangeListener(this);
/*      */     }
/*      */     
/* 1685 */     for (int i = 0; i < getSubtitleCount(); i++) {
/* 1686 */       getSubtitle(i).addChangeListener(this);
/*      */     }
/* 1688 */     this.plot.addChangeListener(this);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void main(String[] args)
/*      */   {
/* 1697 */     System.out.println(INFO.toString());
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
/* 1709 */     JFreeChart chart = (JFreeChart)super.clone();
/*      */     
/* 1711 */     chart.renderingHints = ((RenderingHints)this.renderingHints.clone());
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1716 */     if (this.title != null) {
/* 1717 */       chart.title = ((TextTitle)this.title.clone());
/* 1718 */       chart.title.addChangeListener(chart);
/*      */     }
/*      */     
/* 1721 */     chart.subtitles = new ArrayList();
/* 1722 */     for (int i = 0; i < getSubtitleCount(); i++) {
/* 1723 */       Title subtitle = (Title)getSubtitle(i).clone();
/* 1724 */       chart.subtitles.add(subtitle);
/* 1725 */       subtitle.addChangeListener(chart);
/*      */     }
/*      */     
/* 1728 */     if (this.plot != null) {
/* 1729 */       chart.plot = ((Plot)this.plot.clone());
/* 1730 */       chart.plot.addChangeListener(chart);
/*      */     }
/*      */     
/* 1733 */     chart.progressListeners = new EventListenerList();
/* 1734 */     chart.changeListeners = new EventListenerList();
/* 1735 */     return chart;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/JFreeChart.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */