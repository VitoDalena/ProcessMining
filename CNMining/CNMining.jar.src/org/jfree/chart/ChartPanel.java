/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Cursor;
/*      */ import java.awt.Dimension;
/*      */ import java.awt.Graphics;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.GraphicsConfiguration;
/*      */ import java.awt.Image;
/*      */ import java.awt.Insets;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Point;
/*      */ import java.awt.Rectangle;
/*      */ import java.awt.Toolkit;
/*      */ import java.awt.datatransfer.Clipboard;
/*      */ import java.awt.event.ActionEvent;
/*      */ import java.awt.event.ActionListener;
/*      */ import java.awt.event.MouseEvent;
/*      */ import java.awt.event.MouseListener;
/*      */ import java.awt.event.MouseMotionListener;
/*      */ import java.awt.geom.AffineTransform;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Float;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Point2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.awt.print.PageFormat;
/*      */ import java.awt.print.Printable;
/*      */ import java.awt.print.PrinterException;
/*      */ import java.awt.print.PrinterJob;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.lang.reflect.Constructor;
/*      */ import java.lang.reflect.InvocationTargetException;
/*      */ import java.lang.reflect.Method;
/*      */ import java.util.ArrayList;
/*      */ import java.util.EventListener;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.ResourceBundle;
/*      */ import javax.swing.JFileChooser;
/*      */ import javax.swing.JMenu;
/*      */ import javax.swing.JMenuItem;
/*      */ import javax.swing.JOptionPane;
/*      */ import javax.swing.JPanel;
/*      */ import javax.swing.JPopupMenu;
/*      */ import javax.swing.SwingUtilities;
/*      */ import javax.swing.ToolTipManager;
/*      */ import javax.swing.event.EventListenerList;
/*      */ import org.jfree.chart.editor.ChartEditor;
/*      */ import org.jfree.chart.editor.ChartEditorManager;
/*      */ import org.jfree.chart.entity.ChartEntity;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.event.ChartChangeEvent;
/*      */ import org.jfree.chart.event.ChartChangeListener;
/*      */ import org.jfree.chart.event.ChartProgressEvent;
/*      */ import org.jfree.chart.event.ChartProgressListener;
/*      */ import org.jfree.chart.event.OverlayChangeEvent;
/*      */ import org.jfree.chart.event.OverlayChangeListener;
/*      */ import org.jfree.chart.panel.Overlay;
/*      */ import org.jfree.chart.plot.Pannable;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.Zoomable;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.ExtensionFileFilter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ChartPanel
/*      */   extends JPanel
/*      */   implements ChartChangeListener, ChartProgressListener, ActionListener, MouseListener, MouseMotionListener, OverlayChangeListener, Printable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 6046366297214274674L;
/*      */   public static final boolean DEFAULT_BUFFER_USED = true;
/*      */   public static final int DEFAULT_WIDTH = 680;
/*      */   public static final int DEFAULT_HEIGHT = 420;
/*      */   public static final int DEFAULT_MINIMUM_DRAW_WIDTH = 300;
/*      */   public static final int DEFAULT_MINIMUM_DRAW_HEIGHT = 200;
/*      */   public static final int DEFAULT_MAXIMUM_DRAW_WIDTH = 1024;
/*      */   public static final int DEFAULT_MAXIMUM_DRAW_HEIGHT = 768;
/*      */   public static final int DEFAULT_ZOOM_TRIGGER_DISTANCE = 10;
/*      */   public static final String PROPERTIES_COMMAND = "PROPERTIES";
/*      */   public static final String COPY_COMMAND = "COPY";
/*      */   public static final String SAVE_COMMAND = "SAVE";
/*      */   public static final String PRINT_COMMAND = "PRINT";
/*      */   public static final String ZOOM_IN_BOTH_COMMAND = "ZOOM_IN_BOTH";
/*      */   public static final String ZOOM_IN_DOMAIN_COMMAND = "ZOOM_IN_DOMAIN";
/*      */   public static final String ZOOM_IN_RANGE_COMMAND = "ZOOM_IN_RANGE";
/*      */   public static final String ZOOM_OUT_BOTH_COMMAND = "ZOOM_OUT_BOTH";
/*      */   public static final String ZOOM_OUT_DOMAIN_COMMAND = "ZOOM_DOMAIN_BOTH";
/*      */   public static final String ZOOM_OUT_RANGE_COMMAND = "ZOOM_RANGE_BOTH";
/*      */   public static final String ZOOM_RESET_BOTH_COMMAND = "ZOOM_RESET_BOTH";
/*      */   public static final String ZOOM_RESET_DOMAIN_COMMAND = "ZOOM_RESET_DOMAIN";
/*      */   public static final String ZOOM_RESET_RANGE_COMMAND = "ZOOM_RESET_RANGE";
/*      */   private JFreeChart chart;
/*      */   private transient EventListenerList chartMouseListeners;
/*      */   private boolean useBuffer;
/*      */   private boolean refreshBuffer;
/*      */   private transient Image chartBuffer;
/*      */   private int chartBufferHeight;
/*      */   private int chartBufferWidth;
/*      */   private int minimumDrawWidth;
/*      */   private int minimumDrawHeight;
/*      */   private int maximumDrawWidth;
/*      */   private int maximumDrawHeight;
/*      */   private JPopupMenu popup;
/*      */   private ChartRenderingInfo info;
/*      */   private Point2D anchor;
/*      */   private double scaleX;
/*      */   private double scaleY;
/*  385 */   private PlotOrientation orientation = PlotOrientation.VERTICAL;
/*      */   
/*      */ 
/*  388 */   private boolean domainZoomable = false;
/*      */   
/*      */ 
/*  391 */   private boolean rangeZoomable = false;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  398 */   private Point2D zoomPoint = null;
/*      */   
/*      */ 
/*  401 */   private transient Rectangle2D zoomRectangle = null;
/*      */   
/*      */ 
/*  404 */   private boolean fillZoomRectangle = true;
/*      */   
/*      */ 
/*      */   private int zoomTriggerDistance;
/*      */   
/*      */ 
/*  410 */   private boolean horizontalAxisTrace = false;
/*      */   
/*      */ 
/*  413 */   private boolean verticalAxisTrace = false;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Line2D verticalTraceLine;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Line2D horizontalTraceLine;
/*      */   
/*      */ 
/*      */ 
/*      */   private JMenuItem zoomInBothMenuItem;
/*      */   
/*      */ 
/*      */ 
/*      */   private JMenuItem zoomInDomainMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomInRangeMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomOutBothMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomOutDomainMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomOutRangeMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomResetBothMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomResetDomainMenuItem;
/*      */   
/*      */ 
/*      */   private JMenuItem zoomResetRangeMenuItem;
/*      */   
/*      */ 
/*      */   private File defaultDirectoryForSaveAs;
/*      */   
/*      */ 
/*      */   private boolean enforceFileExtensions;
/*      */   
/*      */ 
/*      */   private boolean ownToolTipDelaysActive;
/*      */   
/*      */ 
/*      */   private int originalToolTipInitialDelay;
/*      */   
/*      */ 
/*      */   private int originalToolTipReshowDelay;
/*      */   
/*      */ 
/*      */   private int originalToolTipDismissDelay;
/*      */   
/*      */ 
/*      */   private int ownToolTipInitialDelay;
/*      */   
/*      */ 
/*      */   private int ownToolTipReshowDelay;
/*      */   
/*      */ 
/*      */   private int ownToolTipDismissDelay;
/*      */   
/*      */ 
/*  480 */   private double zoomInFactor = 0.5D;
/*      */   
/*      */ 
/*  483 */   private double zoomOutFactor = 2.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean zoomAroundAnchor;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint zoomOutlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint zoomFillPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  508 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.LocalizationBundle");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double panW;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double panH;
/*      */   
/*      */ 
/*      */ 
/*      */   private Point panLast;
/*      */   
/*      */ 
/*      */ 
/*  526 */   private int panMask = 2;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private List overlays;
/*      */   
/*      */ 
/*      */ 
/*      */   private Object mouseWheelHandler;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartPanel(JFreeChart chart)
/*      */   {
/*  542 */     this(chart, 680, 420, 300, 200, 1024, 768, true, true, true, true, true, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartPanel(JFreeChart chart, boolean useBuffer)
/*      */   {
/*  578 */     this(chart, 680, 420, 300, 200, 1024, 768, useBuffer, true, true, true, true, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartPanel(JFreeChart chart, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips)
/*      */   {
/*  612 */     this(chart, 680, 420, 300, 200, 1024, 768, true, properties, save, print, zoom, tooltips);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartPanel(JFreeChart chart, int width, int height, int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth, int maximumDrawHeight, boolean useBuffer, boolean properties, boolean save, boolean print, boolean zoom, boolean tooltips)
/*      */   {
/*  658 */     this(chart, width, height, minimumDrawWidth, minimumDrawHeight, maximumDrawWidth, maximumDrawHeight, useBuffer, properties, true, save, print, zoom, tooltips);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartPanel(JFreeChart chart, int width, int height, int minimumDrawWidth, int minimumDrawHeight, int maximumDrawWidth, int maximumDrawHeight, boolean useBuffer, boolean properties, boolean copy, boolean save, boolean print, boolean zoom, boolean tooltips)
/*      */   {
/*  697 */     setChart(chart);
/*  698 */     this.chartMouseListeners = new EventListenerList();
/*  699 */     this.info = new ChartRenderingInfo();
/*  700 */     setPreferredSize(new Dimension(width, height));
/*  701 */     this.useBuffer = useBuffer;
/*  702 */     this.refreshBuffer = false;
/*  703 */     this.minimumDrawWidth = minimumDrawWidth;
/*  704 */     this.minimumDrawHeight = minimumDrawHeight;
/*  705 */     this.maximumDrawWidth = maximumDrawWidth;
/*  706 */     this.maximumDrawHeight = maximumDrawHeight;
/*  707 */     this.zoomTriggerDistance = 10;
/*      */     
/*      */ 
/*  710 */     this.popup = null;
/*  711 */     if ((properties) || (copy) || (save) || (print) || (zoom)) {
/*  712 */       this.popup = createPopupMenu(properties, copy, save, print, zoom);
/*      */     }
/*      */     
/*  715 */     enableEvents(16L);
/*  716 */     enableEvents(32L);
/*  717 */     setDisplayToolTips(tooltips);
/*  718 */     addMouseListener(this);
/*  719 */     addMouseMotionListener(this);
/*      */     
/*  721 */     this.defaultDirectoryForSaveAs = null;
/*  722 */     this.enforceFileExtensions = true;
/*      */     
/*      */ 
/*      */ 
/*  726 */     ToolTipManager ttm = ToolTipManager.sharedInstance();
/*  727 */     this.ownToolTipInitialDelay = ttm.getInitialDelay();
/*  728 */     this.ownToolTipDismissDelay = ttm.getDismissDelay();
/*  729 */     this.ownToolTipReshowDelay = ttm.getReshowDelay();
/*      */     
/*  731 */     this.zoomAroundAnchor = false;
/*  732 */     this.zoomOutlinePaint = Color.blue;
/*  733 */     this.zoomFillPaint = new Color(0, 0, 255, 63);
/*      */     
/*  735 */     this.panMask = 2;
/*      */     
/*      */ 
/*  738 */     String osName = System.getProperty("os.name").toLowerCase();
/*  739 */     if (osName.startsWith("mac os x")) {
/*  740 */       this.panMask = 8;
/*      */     }
/*      */     
/*  743 */     this.overlays = new ArrayList();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JFreeChart getChart()
/*      */   {
/*  752 */     return this.chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setChart(JFreeChart chart)
/*      */   {
/*  763 */     if (this.chart != null) {
/*  764 */       this.chart.removeChangeListener(this);
/*  765 */       this.chart.removeProgressListener(this);
/*      */     }
/*      */     
/*      */ 
/*  769 */     this.chart = chart;
/*  770 */     if (chart != null) {
/*  771 */       this.chart.addChangeListener(this);
/*  772 */       this.chart.addProgressListener(this);
/*  773 */       Plot plot = chart.getPlot();
/*  774 */       this.domainZoomable = false;
/*  775 */       this.rangeZoomable = false;
/*  776 */       if ((plot instanceof Zoomable)) {
/*  777 */         Zoomable z = (Zoomable)plot;
/*  778 */         this.domainZoomable = z.isDomainZoomable();
/*  779 */         this.rangeZoomable = z.isRangeZoomable();
/*  780 */         this.orientation = z.getOrientation();
/*      */       }
/*      */     }
/*      */     else {
/*  784 */       this.domainZoomable = false;
/*  785 */       this.rangeZoomable = false;
/*      */     }
/*  787 */     if (this.useBuffer) {
/*  788 */       this.refreshBuffer = true;
/*      */     }
/*  790 */     repaint();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinimumDrawWidth()
/*      */   {
/*  803 */     return this.minimumDrawWidth;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinimumDrawWidth(int width)
/*      */   {
/*  816 */     this.minimumDrawWidth = width;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaximumDrawWidth()
/*      */   {
/*  828 */     return this.maximumDrawWidth;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumDrawWidth(int width)
/*      */   {
/*  841 */     this.maximumDrawWidth = width;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMinimumDrawHeight()
/*      */   {
/*  853 */     return this.minimumDrawHeight;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinimumDrawHeight(int height)
/*      */   {
/*  866 */     this.minimumDrawHeight = height;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getMaximumDrawHeight()
/*      */   {
/*  878 */     return this.maximumDrawHeight;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumDrawHeight(int height)
/*      */   {
/*  891 */     this.maximumDrawHeight = height;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getScaleX()
/*      */   {
/*  901 */     return this.scaleX;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getScaleY()
/*      */   {
/*  911 */     return this.scaleY;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Point2D getAnchor()
/*      */   {
/*  920 */     return this.anchor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setAnchor(Point2D anchor)
/*      */   {
/*  930 */     this.anchor = anchor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public JPopupMenu getPopupMenu()
/*      */   {
/*  939 */     return this.popup;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPopupMenu(JPopupMenu popup)
/*      */   {
/*  948 */     this.popup = popup;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartRenderingInfo getChartRenderingInfo()
/*      */   {
/*  957 */     return this.info;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMouseZoomable(boolean flag)
/*      */   {
/*  967 */     setMouseZoomable(flag, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMouseZoomable(boolean flag, boolean fillRectangle)
/*      */   {
/*  978 */     setDomainZoomable(flag);
/*  979 */     setRangeZoomable(flag);
/*  980 */     setFillZoomRectangle(fillRectangle);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isDomainZoomable()
/*      */   {
/*  990 */     return this.domainZoomable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainZoomable(boolean flag)
/*      */   {
/* 1001 */     if (flag) {
/* 1002 */       Plot plot = this.chart.getPlot();
/* 1003 */       if ((plot instanceof Zoomable)) {
/* 1004 */         Zoomable z = (Zoomable)plot;
/* 1005 */         this.domainZoomable = ((flag) && (z.isDomainZoomable()));
/*      */       }
/*      */     }
/*      */     else {
/* 1009 */       this.domainZoomable = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isRangeZoomable()
/*      */   {
/* 1020 */     return this.rangeZoomable;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeZoomable(boolean flag)
/*      */   {
/* 1029 */     if (flag) {
/* 1030 */       Plot plot = this.chart.getPlot();
/* 1031 */       if ((plot instanceof Zoomable)) {
/* 1032 */         Zoomable z = (Zoomable)plot;
/* 1033 */         this.rangeZoomable = ((flag) && (z.isRangeZoomable()));
/*      */       }
/*      */     }
/*      */     else {
/* 1037 */       this.rangeZoomable = false;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getFillZoomRectangle()
/*      */   {
/* 1048 */     return this.fillZoomRectangle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFillZoomRectangle(boolean flag)
/*      */   {
/* 1058 */     this.fillZoomRectangle = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getZoomTriggerDistance()
/*      */   {
/* 1068 */     return this.zoomTriggerDistance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoomTriggerDistance(int distance)
/*      */   {
/* 1078 */     this.zoomTriggerDistance = distance;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getHorizontalAxisTrace()
/*      */   {
/* 1088 */     return this.horizontalAxisTrace;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setHorizontalAxisTrace(boolean flag)
/*      */   {
/* 1098 */     this.horizontalAxisTrace = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Line2D getHorizontalTraceLine()
/*      */   {
/* 1107 */     return this.horizontalTraceLine;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setHorizontalTraceLine(Line2D line)
/*      */   {
/* 1116 */     this.horizontalTraceLine = line;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getVerticalAxisTrace()
/*      */   {
/* 1126 */     return this.verticalAxisTrace;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setVerticalAxisTrace(boolean flag)
/*      */   {
/* 1136 */     this.verticalAxisTrace = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Line2D getVerticalTraceLine()
/*      */   {
/* 1145 */     return this.verticalTraceLine;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void setVerticalTraceLine(Line2D line)
/*      */   {
/* 1154 */     this.verticalTraceLine = line;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public File getDefaultDirectoryForSaveAs()
/*      */   {
/* 1165 */     return this.defaultDirectoryForSaveAs;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDefaultDirectoryForSaveAs(File directory)
/*      */   {
/* 1177 */     if ((directory != null) && 
/* 1178 */       (!directory.isDirectory())) {
/* 1179 */       throw new IllegalArgumentException("The 'directory' argument is not a directory.");
/*      */     }
/*      */     
/*      */ 
/* 1183 */     this.defaultDirectoryForSaveAs = directory;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isEnforceFileExtensions()
/*      */   {
/* 1195 */     return this.enforceFileExtensions;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setEnforceFileExtensions(boolean enforce)
/*      */   {
/* 1206 */     this.enforceFileExtensions = enforce;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getZoomAroundAnchor()
/*      */   {
/* 1220 */     return this.zoomAroundAnchor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoomAroundAnchor(boolean zoomAroundAnchor)
/*      */   {
/* 1234 */     this.zoomAroundAnchor = zoomAroundAnchor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getZoomFillPaint()
/*      */   {
/* 1248 */     return this.zoomFillPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoomFillPaint(Paint paint)
/*      */   {
/* 1262 */     if (paint == null) {
/* 1263 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1265 */     this.zoomFillPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getZoomOutlinePaint()
/*      */   {
/* 1279 */     return this.zoomOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoomOutlinePaint(Paint paint)
/*      */   {
/* 1293 */     this.zoomOutlinePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isMouseWheelEnabled()
/*      */   {
/* 1312 */     return this.mouseWheelHandler != null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMouseWheelEnabled(boolean flag)
/*      */   {
/* 1326 */     if ((flag) && (this.mouseWheelHandler == null))
/*      */     {
/*      */       try
/*      */       {
/*      */ 
/* 1331 */         Class c = Class.forName("org.jfree.chart.MouseWheelHandler");
/* 1332 */         Constructor cc = c.getConstructor(new Class[] { ChartPanel.class });
/*      */         
/* 1334 */         Object mwh = cc.newInstance(new Object[] { this });
/* 1335 */         this.mouseWheelHandler = mwh;
/*      */ 
/*      */ 
/*      */       }
/*      */       catch (ClassNotFoundException e) {}catch (SecurityException e)
/*      */       {
/*      */ 
/* 1342 */         e.printStackTrace();
/*      */       }
/*      */       catch (NoSuchMethodException e) {
/* 1345 */         e.printStackTrace();
/*      */       }
/*      */       catch (IllegalArgumentException e) {
/* 1348 */         e.printStackTrace();
/*      */       }
/*      */       catch (InstantiationException e) {
/* 1351 */         e.printStackTrace();
/*      */       }
/*      */       catch (IllegalAccessException e) {
/* 1354 */         e.printStackTrace();
/*      */       }
/*      */       catch (InvocationTargetException e) {
/* 1357 */         e.printStackTrace();
/*      */ 
/*      */       }
/*      */       
/*      */     }
/* 1362 */     else if (this.mouseWheelHandler != null) {
/*      */       try
/*      */       {
/* 1365 */         Class mwl = Class.forName("java.awt.event.MouseWheelListener");
/*      */         
/* 1367 */         Class c2 = ChartPanel.class;
/* 1368 */         Method m = c2.getMethod("removeMouseWheelListener", new Class[] { mwl });
/*      */         
/* 1370 */         m.invoke(this, new Object[] { this.mouseWheelHandler });
/*      */ 
/*      */       }
/*      */       catch (ClassNotFoundException e) {}catch (SecurityException e)
/*      */       {
/*      */ 
/* 1376 */         e.printStackTrace();
/*      */       }
/*      */       catch (NoSuchMethodException e) {
/* 1379 */         e.printStackTrace();
/*      */       }
/*      */       catch (IllegalArgumentException e) {
/* 1382 */         e.printStackTrace();
/*      */       }
/*      */       catch (IllegalAccessException e) {
/* 1385 */         e.printStackTrace();
/*      */       }
/*      */       catch (InvocationTargetException e) {
/* 1388 */         e.printStackTrace();
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
/*      */   public void addOverlay(Overlay overlay)
/*      */   {
/* 1402 */     if (overlay == null) {
/* 1403 */       throw new IllegalArgumentException("Null 'overlay' argument.");
/*      */     }
/* 1405 */     this.overlays.add(overlay);
/* 1406 */     overlay.addChangeListener(this);
/* 1407 */     repaint();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeOverlay(Overlay overlay)
/*      */   {
/* 1418 */     if (overlay == null) {
/* 1419 */       throw new IllegalArgumentException("Null 'overlay' argument.");
/*      */     }
/* 1421 */     boolean removed = this.overlays.remove(overlay);
/* 1422 */     if (removed) {
/* 1423 */       overlay.removeChangeListener(this);
/* 1424 */       repaint();
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void overlayChanged(OverlayChangeEvent event)
/*      */   {
/* 1436 */     repaint();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDisplayToolTips(boolean flag)
/*      */   {
/* 1448 */     if (flag) {
/* 1449 */       ToolTipManager.sharedInstance().registerComponent(this);
/*      */     }
/*      */     else {
/* 1452 */       ToolTipManager.sharedInstance().unregisterComponent(this);
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
/*      */   public String getToolTipText(MouseEvent e)
/*      */   {
/* 1465 */     String result = null;
/* 1466 */     if (this.info != null) {
/* 1467 */       EntityCollection entities = this.info.getEntityCollection();
/* 1468 */       if (entities != null) {
/* 1469 */         Insets insets = getInsets();
/* 1470 */         ChartEntity entity = entities.getEntity((int)((e.getX() - insets.left) / this.scaleX), (int)((e.getY() - insets.top) / this.scaleY));
/*      */         
/*      */ 
/* 1473 */         if (entity != null) {
/* 1474 */           result = entity.getToolTipText();
/*      */         }
/*      */       }
/*      */     }
/* 1478 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Point translateJava2DToScreen(Point2D java2DPoint)
/*      */   {
/* 1490 */     Insets insets = getInsets();
/* 1491 */     int x = (int)(java2DPoint.getX() * this.scaleX + insets.left);
/* 1492 */     int y = (int)(java2DPoint.getY() * this.scaleY + insets.top);
/* 1493 */     return new Point(x, y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Point2D translateScreenToJava2D(Point screenPoint)
/*      */   {
/* 1505 */     Insets insets = getInsets();
/* 1506 */     double x = (screenPoint.getX() - insets.left) / this.scaleX;
/* 1507 */     double y = (screenPoint.getY() - insets.top) / this.scaleY;
/* 1508 */     return new Point2D.Double(x, y);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rectangle2D scale(Rectangle2D rect)
/*      */   {
/* 1520 */     Insets insets = getInsets();
/* 1521 */     double x = rect.getX() * getScaleX() + insets.left;
/* 1522 */     double y = rect.getY() * getScaleY() + insets.top;
/* 1523 */     double w = rect.getWidth() * getScaleX();
/* 1524 */     double h = rect.getHeight() * getScaleY();
/* 1525 */     return new Rectangle2D.Double(x, y, w, h);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public ChartEntity getEntityForPoint(int viewX, int viewY)
/*      */   {
/* 1541 */     ChartEntity result = null;
/* 1542 */     if (this.info != null) {
/* 1543 */       Insets insets = getInsets();
/* 1544 */       double x = (viewX - insets.left) / this.scaleX;
/* 1545 */       double y = (viewY - insets.top) / this.scaleY;
/* 1546 */       EntityCollection entities = this.info.getEntityCollection();
/* 1547 */       result = entities != null ? entities.getEntity(x, y) : null;
/*      */     }
/* 1549 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getRefreshBuffer()
/*      */   {
/* 1560 */     return this.refreshBuffer;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRefreshBuffer(boolean flag)
/*      */   {
/* 1571 */     this.refreshBuffer = flag;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void paintComponent(Graphics g)
/*      */   {
/* 1583 */     super.paintComponent(g);
/* 1584 */     if (this.chart == null) {
/* 1585 */       return;
/*      */     }
/* 1587 */     Graphics2D g2 = (Graphics2D)g.create();
/*      */     
/*      */ 
/* 1590 */     Dimension size = getSize();
/* 1591 */     Insets insets = getInsets();
/* 1592 */     Rectangle2D available = new Rectangle2D.Double(insets.left, insets.top, size.getWidth() - insets.left - insets.right, size.getHeight() - insets.top - insets.bottom);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1597 */     boolean scale = false;
/* 1598 */     double drawWidth = available.getWidth();
/* 1599 */     double drawHeight = available.getHeight();
/* 1600 */     this.scaleX = 1.0D;
/* 1601 */     this.scaleY = 1.0D;
/*      */     
/* 1603 */     if (drawWidth < this.minimumDrawWidth) {
/* 1604 */       this.scaleX = (drawWidth / this.minimumDrawWidth);
/* 1605 */       drawWidth = this.minimumDrawWidth;
/* 1606 */       scale = true;
/*      */     }
/* 1608 */     else if (drawWidth > this.maximumDrawWidth) {
/* 1609 */       this.scaleX = (drawWidth / this.maximumDrawWidth);
/* 1610 */       drawWidth = this.maximumDrawWidth;
/* 1611 */       scale = true;
/*      */     }
/*      */     
/* 1614 */     if (drawHeight < this.minimumDrawHeight) {
/* 1615 */       this.scaleY = (drawHeight / this.minimumDrawHeight);
/* 1616 */       drawHeight = this.minimumDrawHeight;
/* 1617 */       scale = true;
/*      */     }
/* 1619 */     else if (drawHeight > this.maximumDrawHeight) {
/* 1620 */       this.scaleY = (drawHeight / this.maximumDrawHeight);
/* 1621 */       drawHeight = this.maximumDrawHeight;
/* 1622 */       scale = true;
/*      */     }
/*      */     
/* 1625 */     Rectangle2D chartArea = new Rectangle2D.Double(0.0D, 0.0D, drawWidth, drawHeight);
/*      */     
/*      */ 
/*      */ 
/* 1629 */     if (this.useBuffer)
/*      */     {
/*      */ 
/* 1632 */       if ((this.chartBuffer == null) || (this.chartBufferWidth != available.getWidth()) || (this.chartBufferHeight != available.getHeight()))
/*      */       {
/*      */ 
/* 1635 */         this.chartBufferWidth = ((int)available.getWidth());
/* 1636 */         this.chartBufferHeight = ((int)available.getHeight());
/* 1637 */         GraphicsConfiguration gc = g2.getDeviceConfiguration();
/* 1638 */         this.chartBuffer = gc.createCompatibleImage(this.chartBufferWidth, this.chartBufferHeight, 3);
/*      */         
/*      */ 
/* 1641 */         this.refreshBuffer = true;
/*      */       }
/*      */       
/*      */ 
/* 1645 */       if (this.refreshBuffer)
/*      */       {
/* 1647 */         this.refreshBuffer = false;
/*      */         
/* 1649 */         Rectangle2D bufferArea = new Rectangle2D.Double(0.0D, 0.0D, this.chartBufferWidth, this.chartBufferHeight);
/*      */         
/*      */ 
/* 1652 */         Graphics2D bufferG2 = (Graphics2D)this.chartBuffer.getGraphics();
/*      */         
/* 1654 */         Rectangle r = new Rectangle(0, 0, this.chartBufferWidth, this.chartBufferHeight);
/*      */         
/* 1656 */         bufferG2.setPaint(getBackground());
/* 1657 */         bufferG2.fill(r);
/* 1658 */         if (scale) {
/* 1659 */           AffineTransform saved = bufferG2.getTransform();
/* 1660 */           AffineTransform st = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/*      */           
/* 1662 */           bufferG2.transform(st);
/* 1663 */           this.chart.draw(bufferG2, chartArea, this.anchor, this.info);
/*      */           
/* 1665 */           bufferG2.setTransform(saved);
/*      */         }
/*      */         else {
/* 1668 */           this.chart.draw(bufferG2, bufferArea, this.anchor, this.info);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*      */ 
/* 1675 */       g2.drawImage(this.chartBuffer, insets.left, insets.top, this);
/*      */ 
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*      */ 
/* 1682 */       AffineTransform saved = g2.getTransform();
/* 1683 */       g2.translate(insets.left, insets.top);
/* 1684 */       if (scale) {
/* 1685 */         AffineTransform st = AffineTransform.getScaleInstance(this.scaleX, this.scaleY);
/*      */         
/* 1687 */         g2.transform(st);
/*      */       }
/* 1689 */       this.chart.draw(g2, chartArea, this.anchor, this.info);
/* 1690 */       g2.setTransform(saved);
/*      */     }
/*      */     
/*      */ 
/* 1694 */     Iterator iterator = this.overlays.iterator();
/* 1695 */     while (iterator.hasNext()) {
/* 1696 */       Overlay overlay = (Overlay)iterator.next();
/* 1697 */       overlay.paintOverlay(g2, this);
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1703 */     drawZoomRectangle(g2, !this.useBuffer);
/*      */     
/* 1705 */     g2.dispose();
/*      */     
/* 1707 */     this.anchor = null;
/* 1708 */     this.verticalTraceLine = null;
/* 1709 */     this.horizontalTraceLine = null;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void chartChanged(ChartChangeEvent event)
/*      */   {
/* 1719 */     this.refreshBuffer = true;
/* 1720 */     Plot plot = this.chart.getPlot();
/* 1721 */     if ((plot instanceof Zoomable)) {
/* 1722 */       Zoomable z = (Zoomable)plot;
/* 1723 */       this.orientation = z.getOrientation();
/*      */     }
/* 1725 */     repaint();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void chartProgress(ChartProgressEvent event) {}
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void actionPerformed(ActionEvent event)
/*      */   {
/* 1744 */     String command = event.getActionCommand();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1749 */     double screenX = -1.0D;
/* 1750 */     double screenY = -1.0D;
/* 1751 */     if (this.zoomPoint != null) {
/* 1752 */       screenX = this.zoomPoint.getX();
/* 1753 */       screenY = this.zoomPoint.getY();
/*      */     }
/*      */     
/* 1756 */     if (command.equals("PROPERTIES")) {
/* 1757 */       doEditChartProperties();
/*      */     }
/* 1759 */     else if (command.equals("COPY")) {
/* 1760 */       doCopy();
/*      */     }
/* 1762 */     else if (command.equals("SAVE")) {
/*      */       try {
/* 1764 */         doSaveAs();
/*      */       }
/*      */       catch (IOException e) {
/* 1767 */         e.printStackTrace();
/*      */       }
/*      */       
/* 1770 */     } else if (command.equals("PRINT")) {
/* 1771 */       createChartPrintJob();
/*      */     }
/* 1773 */     else if (command.equals("ZOOM_IN_BOTH")) {
/* 1774 */       zoomInBoth(screenX, screenY);
/*      */     }
/* 1776 */     else if (command.equals("ZOOM_IN_DOMAIN")) {
/* 1777 */       zoomInDomain(screenX, screenY);
/*      */     }
/* 1779 */     else if (command.equals("ZOOM_IN_RANGE")) {
/* 1780 */       zoomInRange(screenX, screenY);
/*      */     }
/* 1782 */     else if (command.equals("ZOOM_OUT_BOTH")) {
/* 1783 */       zoomOutBoth(screenX, screenY);
/*      */     }
/* 1785 */     else if (command.equals("ZOOM_DOMAIN_BOTH")) {
/* 1786 */       zoomOutDomain(screenX, screenY);
/*      */     }
/* 1788 */     else if (command.equals("ZOOM_RANGE_BOTH")) {
/* 1789 */       zoomOutRange(screenX, screenY);
/*      */     }
/* 1791 */     else if (command.equals("ZOOM_RESET_BOTH")) {
/* 1792 */       restoreAutoBounds();
/*      */     }
/* 1794 */     else if (command.equals("ZOOM_RESET_DOMAIN")) {
/* 1795 */       restoreAutoDomainBounds();
/*      */     }
/* 1797 */     else if (command.equals("ZOOM_RESET_RANGE")) {
/* 1798 */       restoreAutoRangeBounds();
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
/*      */   public void mouseEntered(MouseEvent e)
/*      */   {
/* 1811 */     if (!this.ownToolTipDelaysActive) {
/* 1812 */       ToolTipManager ttm = ToolTipManager.sharedInstance();
/*      */       
/* 1814 */       this.originalToolTipInitialDelay = ttm.getInitialDelay();
/* 1815 */       ttm.setInitialDelay(this.ownToolTipInitialDelay);
/*      */       
/* 1817 */       this.originalToolTipReshowDelay = ttm.getReshowDelay();
/* 1818 */       ttm.setReshowDelay(this.ownToolTipReshowDelay);
/*      */       
/* 1820 */       this.originalToolTipDismissDelay = ttm.getDismissDelay();
/* 1821 */       ttm.setDismissDelay(this.ownToolTipDismissDelay);
/*      */       
/* 1823 */       this.ownToolTipDelaysActive = true;
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mouseExited(MouseEvent e)
/*      */   {
/* 1835 */     if (this.ownToolTipDelaysActive)
/*      */     {
/* 1837 */       ToolTipManager ttm = ToolTipManager.sharedInstance();
/* 1838 */       ttm.setInitialDelay(this.originalToolTipInitialDelay);
/* 1839 */       ttm.setReshowDelay(this.originalToolTipReshowDelay);
/* 1840 */       ttm.setDismissDelay(this.originalToolTipDismissDelay);
/* 1841 */       this.ownToolTipDelaysActive = false;
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
/*      */   public void mousePressed(MouseEvent e)
/*      */   {
/* 1854 */     Plot plot = this.chart.getPlot();
/* 1855 */     int mods = e.getModifiers();
/* 1856 */     if ((mods & this.panMask) == this.panMask)
/*      */     {
/* 1858 */       if ((plot instanceof Pannable)) {
/* 1859 */         Pannable pannable = (Pannable)plot;
/* 1860 */         if ((pannable.isDomainPannable()) || (pannable.isRangePannable())) {
/* 1861 */           Rectangle2D screenDataArea = getScreenDataArea(e.getX(), e.getY());
/*      */           
/* 1863 */           if ((screenDataArea != null) && (screenDataArea.contains(e.getPoint())))
/*      */           {
/* 1865 */             this.panW = screenDataArea.getWidth();
/* 1866 */             this.panH = screenDataArea.getHeight();
/* 1867 */             this.panLast = e.getPoint();
/* 1868 */             setCursor(Cursor.getPredefinedCursor(13));
/*      */           }
/*      */           
/*      */         }
/*      */         
/*      */       }
/*      */     }
/* 1875 */     else if (this.zoomRectangle == null) {
/* 1876 */       Rectangle2D screenDataArea = getScreenDataArea(e.getX(), e.getY());
/* 1877 */       if (screenDataArea != null) {
/* 1878 */         this.zoomPoint = getPointInRectangle(e.getX(), e.getY(), screenDataArea);
/*      */       }
/*      */       else
/*      */       {
/* 1882 */         this.zoomPoint = null;
/*      */       }
/* 1884 */       if ((e.isPopupTrigger()) && 
/* 1885 */         (this.popup != null)) {
/* 1886 */         displayPopupMenu(e.getX(), e.getY());
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
/*      */   private Point2D getPointInRectangle(int x, int y, Rectangle2D area)
/*      */   {
/* 1903 */     double xx = Math.max(area.getMinX(), Math.min(x, area.getMaxX()));
/* 1904 */     double yy = Math.max(area.getMinY(), Math.min(y, area.getMaxY()));
/* 1905 */     return new Point2D.Double(xx, yy);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mouseDragged(MouseEvent e)
/*      */   {
/* 1916 */     if ((this.popup != null) && (this.popup.isShowing())) {
/* 1917 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1921 */     if (this.panLast != null) {
/* 1922 */       double dx = e.getX() - this.panLast.getX();
/* 1923 */       double dy = e.getY() - this.panLast.getY();
/* 1924 */       if ((dx == 0.0D) && (dy == 0.0D)) {
/* 1925 */         return;
/*      */       }
/* 1927 */       double wPercent = -dx / this.panW;
/* 1928 */       double hPercent = dy / this.panH;
/* 1929 */       boolean old = this.chart.getPlot().isNotify();
/* 1930 */       this.chart.getPlot().setNotify(false);
/* 1931 */       Pannable p = (Pannable)this.chart.getPlot();
/* 1932 */       if (p.getOrientation() == PlotOrientation.VERTICAL) {
/* 1933 */         p.panDomainAxes(wPercent, this.info.getPlotInfo(), this.panLast);
/*      */         
/* 1935 */         p.panRangeAxes(hPercent, this.info.getPlotInfo(), this.panLast);
/*      */       }
/*      */       else
/*      */       {
/* 1939 */         p.panDomainAxes(hPercent, this.info.getPlotInfo(), this.panLast);
/*      */         
/* 1941 */         p.panRangeAxes(wPercent, this.info.getPlotInfo(), this.panLast);
/*      */       }
/*      */       
/* 1944 */       this.panLast = e.getPoint();
/* 1945 */       this.chart.getPlot().setNotify(old);
/* 1946 */       return;
/*      */     }
/*      */     
/*      */ 
/* 1950 */     if (this.zoomPoint == null) {
/* 1951 */       return;
/*      */     }
/* 1953 */     Graphics2D g2 = (Graphics2D)getGraphics();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1959 */     if (!this.useBuffer) {
/* 1960 */       drawZoomRectangle(g2, true);
/*      */     }
/*      */     
/* 1963 */     boolean hZoom = false;
/* 1964 */     boolean vZoom = false;
/* 1965 */     if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 1966 */       hZoom = this.rangeZoomable;
/* 1967 */       vZoom = this.domainZoomable;
/*      */     }
/*      */     else {
/* 1970 */       hZoom = this.domainZoomable;
/* 1971 */       vZoom = this.rangeZoomable;
/*      */     }
/* 1973 */     Rectangle2D scaledDataArea = getScreenDataArea((int)this.zoomPoint.getX(), (int)this.zoomPoint.getY());
/*      */     
/* 1975 */     if ((hZoom) && (vZoom))
/*      */     {
/* 1977 */       double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
/* 1978 */       double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
/* 1979 */       this.zoomRectangle = new Rectangle2D.Double(this.zoomPoint.getX(), this.zoomPoint.getY(), xmax - this.zoomPoint.getX(), ymax - this.zoomPoint.getY());
/*      */ 
/*      */ 
/*      */     }
/* 1983 */     else if (hZoom) {
/* 1984 */       double xmax = Math.min(e.getX(), scaledDataArea.getMaxX());
/* 1985 */       this.zoomRectangle = new Rectangle2D.Double(this.zoomPoint.getX(), scaledDataArea.getMinY(), xmax - this.zoomPoint.getX(), scaledDataArea.getHeight());
/*      */ 
/*      */ 
/*      */     }
/* 1989 */     else if (vZoom) {
/* 1990 */       double ymax = Math.min(e.getY(), scaledDataArea.getMaxY());
/* 1991 */       this.zoomRectangle = new Rectangle2D.Double(scaledDataArea.getMinX(), this.zoomPoint.getY(), scaledDataArea.getWidth(), ymax - this.zoomPoint.getY());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1997 */     if (this.useBuffer) {
/* 1998 */       repaint();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2003 */       drawZoomRectangle(g2, true);
/*      */     }
/* 2005 */     g2.dispose();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mouseReleased(MouseEvent e)
/*      */   {
/* 2020 */     if (this.panLast != null) {
/* 2021 */       this.panLast = null;
/* 2022 */       setCursor(Cursor.getDefaultCursor());
/*      */ 
/*      */     }
/* 2025 */     else if (this.zoomRectangle != null) {
/* 2026 */       boolean hZoom = false;
/* 2027 */       boolean vZoom = false;
/* 2028 */       if (this.orientation == PlotOrientation.HORIZONTAL) {
/* 2029 */         hZoom = this.rangeZoomable;
/* 2030 */         vZoom = this.domainZoomable;
/*      */       }
/*      */       else {
/* 2033 */         hZoom = this.domainZoomable;
/* 2034 */         vZoom = this.rangeZoomable;
/*      */       }
/*      */       
/* 2037 */       boolean zoomTrigger1 = (hZoom) && (Math.abs(e.getX() - this.zoomPoint.getX()) >= this.zoomTriggerDistance);
/*      */       
/* 2039 */       boolean zoomTrigger2 = (vZoom) && (Math.abs(e.getY() - this.zoomPoint.getY()) >= this.zoomTriggerDistance);
/*      */       
/* 2041 */       if ((zoomTrigger1) || (zoomTrigger2)) {
/* 2042 */         if (((hZoom) && (e.getX() < this.zoomPoint.getX())) || ((vZoom) && (e.getY() < this.zoomPoint.getY())))
/*      */         {
/* 2044 */           restoreAutoBounds();
/*      */         }
/*      */         else
/*      */         {
/* 2048 */           Rectangle2D screenDataArea = getScreenDataArea((int)this.zoomPoint.getX(), (int)this.zoomPoint.getY());
/*      */           
/*      */ 
/* 2051 */           double maxX = screenDataArea.getMaxX();
/* 2052 */           double maxY = screenDataArea.getMaxY();
/*      */           double h;
/*      */           double x;
/*      */           double y;
/* 2056 */           double w; double h; if (!vZoom) {
/* 2057 */             double x = this.zoomPoint.getX();
/* 2058 */             double y = screenDataArea.getMinY();
/* 2059 */             double w = Math.min(this.zoomRectangle.getWidth(), maxX - this.zoomPoint.getX());
/*      */             
/* 2061 */             h = screenDataArea.getHeight();
/*      */           } else { double h;
/* 2063 */             if (!hZoom) {
/* 2064 */               double x = screenDataArea.getMinX();
/* 2065 */               double y = this.zoomPoint.getY();
/* 2066 */               double w = screenDataArea.getWidth();
/* 2067 */               h = Math.min(this.zoomRectangle.getHeight(), maxY - this.zoomPoint.getY());
/*      */             }
/*      */             else
/*      */             {
/* 2071 */               x = this.zoomPoint.getX();
/* 2072 */               y = this.zoomPoint.getY();
/* 2073 */               w = Math.min(this.zoomRectangle.getWidth(), maxX - this.zoomPoint.getX());
/*      */               
/* 2075 */               h = Math.min(this.zoomRectangle.getHeight(), maxY - this.zoomPoint.getY());
/*      */             }
/*      */           }
/* 2078 */           Rectangle2D zoomArea = new Rectangle2D.Double(x, y, w, h);
/* 2079 */           zoom(zoomArea);
/*      */         }
/* 2081 */         this.zoomPoint = null;
/* 2082 */         this.zoomRectangle = null;
/*      */       }
/*      */       else
/*      */       {
/* 2086 */         Graphics2D g2 = (Graphics2D)getGraphics();
/* 2087 */         if (this.useBuffer) {
/* 2088 */           repaint();
/*      */         }
/*      */         else {
/* 2091 */           drawZoomRectangle(g2, true);
/*      */         }
/* 2093 */         g2.dispose();
/* 2094 */         this.zoomPoint = null;
/* 2095 */         this.zoomRectangle = null;
/*      */       }
/*      */       
/*      */ 
/*      */     }
/* 2100 */     else if ((e.isPopupTrigger()) && 
/* 2101 */       (this.popup != null)) {
/* 2102 */       displayPopupMenu(e.getX(), e.getY());
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
/*      */   public void mouseClicked(MouseEvent event)
/*      */   {
/* 2116 */     Insets insets = getInsets();
/* 2117 */     int x = (int)((event.getX() - insets.left) / this.scaleX);
/* 2118 */     int y = (int)((event.getY() - insets.top) / this.scaleY);
/*      */     
/* 2120 */     this.anchor = new Point2D.Double(x, y);
/* 2121 */     if (this.chart == null) {
/* 2122 */       return;
/*      */     }
/* 2124 */     this.chart.setNotify(true);
/*      */     
/* 2126 */     Object[] listeners = this.chartMouseListeners.getListeners(ChartMouseListener.class);
/*      */     
/* 2128 */     if (listeners.length == 0) {
/* 2129 */       return;
/*      */     }
/*      */     
/* 2132 */     ChartEntity entity = null;
/* 2133 */     if (this.info != null) {
/* 2134 */       EntityCollection entities = this.info.getEntityCollection();
/* 2135 */       if (entities != null) {
/* 2136 */         entity = entities.getEntity(x, y);
/*      */       }
/*      */     }
/* 2139 */     ChartMouseEvent chartEvent = new ChartMouseEvent(getChart(), event, entity);
/*      */     
/* 2141 */     for (int i = listeners.length - 1; i >= 0; i--) {
/* 2142 */       ((ChartMouseListener)listeners[i]).chartMouseClicked(chartEvent);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void mouseMoved(MouseEvent e)
/*      */   {
/* 2153 */     Graphics2D g2 = (Graphics2D)getGraphics();
/* 2154 */     if (this.horizontalAxisTrace) {
/* 2155 */       drawHorizontalAxisTrace(g2, e.getX());
/*      */     }
/* 2157 */     if (this.verticalAxisTrace) {
/* 2158 */       drawVerticalAxisTrace(g2, e.getY());
/*      */     }
/* 2160 */     g2.dispose();
/*      */     
/* 2162 */     Object[] listeners = this.chartMouseListeners.getListeners(ChartMouseListener.class);
/*      */     
/* 2164 */     if (listeners.length == 0) {
/* 2165 */       return;
/*      */     }
/* 2167 */     Insets insets = getInsets();
/* 2168 */     int x = (int)((e.getX() - insets.left) / this.scaleX);
/* 2169 */     int y = (int)((e.getY() - insets.top) / this.scaleY);
/*      */     
/* 2171 */     ChartEntity entity = null;
/* 2172 */     if (this.info != null) {
/* 2173 */       EntityCollection entities = this.info.getEntityCollection();
/* 2174 */       if (entities != null) {
/* 2175 */         entity = entities.getEntity(x, y);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2181 */     if (this.chart != null) {
/* 2182 */       ChartMouseEvent event = new ChartMouseEvent(getChart(), e, entity);
/* 2183 */       for (int i = listeners.length - 1; i >= 0; i--) {
/* 2184 */         ((ChartMouseListener)listeners[i]).chartMouseMoved(event);
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
/*      */   public void zoomInBoth(double x, double y)
/*      */   {
/* 2197 */     Plot plot = this.chart.getPlot();
/* 2198 */     if (plot == null) {
/* 2199 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2204 */     boolean savedNotify = plot.isNotify();
/* 2205 */     plot.setNotify(false);
/* 2206 */     zoomInDomain(x, y);
/* 2207 */     zoomInRange(x, y);
/* 2208 */     plot.setNotify(savedNotify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomInDomain(double x, double y)
/*      */   {
/* 2220 */     Plot plot = this.chart.getPlot();
/* 2221 */     if ((plot instanceof Zoomable))
/*      */     {
/*      */ 
/*      */ 
/* 2225 */       boolean savedNotify = plot.isNotify();
/* 2226 */       plot.setNotify(false);
/* 2227 */       Zoomable z = (Zoomable)plot;
/* 2228 */       z.zoomDomainAxes(this.zoomInFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)), this.zoomAroundAnchor);
/*      */       
/*      */ 
/* 2231 */       plot.setNotify(savedNotify);
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
/*      */   public void zoomInRange(double x, double y)
/*      */   {
/* 2244 */     Plot plot = this.chart.getPlot();
/* 2245 */     if ((plot instanceof Zoomable))
/*      */     {
/*      */ 
/*      */ 
/* 2249 */       boolean savedNotify = plot.isNotify();
/* 2250 */       plot.setNotify(false);
/* 2251 */       Zoomable z = (Zoomable)plot;
/* 2252 */       z.zoomRangeAxes(this.zoomInFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)), this.zoomAroundAnchor);
/*      */       
/*      */ 
/* 2255 */       plot.setNotify(savedNotify);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomOutBoth(double x, double y)
/*      */   {
/* 2266 */     Plot plot = this.chart.getPlot();
/* 2267 */     if (plot == null) {
/* 2268 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2273 */     boolean savedNotify = plot.isNotify();
/* 2274 */     plot.setNotify(false);
/* 2275 */     zoomOutDomain(x, y);
/* 2276 */     zoomOutRange(x, y);
/* 2277 */     plot.setNotify(savedNotify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void zoomOutDomain(double x, double y)
/*      */   {
/* 2289 */     Plot plot = this.chart.getPlot();
/* 2290 */     if ((plot instanceof Zoomable))
/*      */     {
/*      */ 
/*      */ 
/* 2294 */       boolean savedNotify = plot.isNotify();
/* 2295 */       plot.setNotify(false);
/* 2296 */       Zoomable z = (Zoomable)plot;
/* 2297 */       z.zoomDomainAxes(this.zoomOutFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)), this.zoomAroundAnchor);
/*      */       
/*      */ 
/* 2300 */       plot.setNotify(savedNotify);
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
/*      */   public void zoomOutRange(double x, double y)
/*      */   {
/* 2313 */     Plot plot = this.chart.getPlot();
/* 2314 */     if ((plot instanceof Zoomable))
/*      */     {
/*      */ 
/*      */ 
/* 2318 */       boolean savedNotify = plot.isNotify();
/* 2319 */       plot.setNotify(false);
/* 2320 */       Zoomable z = (Zoomable)plot;
/* 2321 */       z.zoomRangeAxes(this.zoomOutFactor, this.info.getPlotInfo(), translateScreenToJava2D(new Point((int)x, (int)y)), this.zoomAroundAnchor);
/*      */       
/*      */ 
/* 2324 */       plot.setNotify(savedNotify);
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
/*      */   public void zoom(Rectangle2D selection)
/*      */   {
/* 2337 */     Point2D selectOrigin = translateScreenToJava2D(new Point((int)Math.ceil(selection.getX()), (int)Math.ceil(selection.getY())));
/*      */     
/*      */ 
/* 2340 */     PlotRenderingInfo plotInfo = this.info.getPlotInfo();
/* 2341 */     Rectangle2D scaledDataArea = getScreenDataArea((int)selection.getCenterX(), (int)selection.getCenterY());
/*      */     
/* 2343 */     if ((selection.getHeight() > 0.0D) && (selection.getWidth() > 0.0D))
/*      */     {
/* 2345 */       double hLower = (selection.getMinX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
/*      */       
/* 2347 */       double hUpper = (selection.getMaxX() - scaledDataArea.getMinX()) / scaledDataArea.getWidth();
/*      */       
/* 2349 */       double vLower = (scaledDataArea.getMaxY() - selection.getMaxY()) / scaledDataArea.getHeight();
/*      */       
/* 2351 */       double vUpper = (scaledDataArea.getMaxY() - selection.getMinY()) / scaledDataArea.getHeight();
/*      */       
/*      */ 
/* 2354 */       Plot p = this.chart.getPlot();
/* 2355 */       if ((p instanceof Zoomable))
/*      */       {
/*      */ 
/*      */ 
/* 2359 */         boolean savedNotify = p.isNotify();
/* 2360 */         p.setNotify(false);
/* 2361 */         Zoomable z = (Zoomable)p;
/* 2362 */         if (z.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 2363 */           z.zoomDomainAxes(vLower, vUpper, plotInfo, selectOrigin);
/* 2364 */           z.zoomRangeAxes(hLower, hUpper, plotInfo, selectOrigin);
/*      */         }
/*      */         else {
/* 2367 */           z.zoomDomainAxes(hLower, hUpper, plotInfo, selectOrigin);
/* 2368 */           z.zoomRangeAxes(vLower, vUpper, plotInfo, selectOrigin);
/*      */         }
/* 2370 */         p.setNotify(savedNotify);
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void restoreAutoBounds()
/*      */   {
/* 2381 */     Plot plot = this.chart.getPlot();
/* 2382 */     if (plot == null) {
/* 2383 */       return;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2388 */     boolean savedNotify = plot.isNotify();
/* 2389 */     plot.setNotify(false);
/* 2390 */     restoreAutoDomainBounds();
/* 2391 */     restoreAutoRangeBounds();
/* 2392 */     plot.setNotify(savedNotify);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void restoreAutoDomainBounds()
/*      */   {
/* 2399 */     Plot plot = this.chart.getPlot();
/* 2400 */     if ((plot instanceof Zoomable)) {
/* 2401 */       Zoomable z = (Zoomable)plot;
/*      */       
/*      */ 
/*      */ 
/* 2405 */       boolean savedNotify = plot.isNotify();
/* 2406 */       plot.setNotify(false);
/*      */       
/* 2408 */       Point2D zp = this.zoomPoint != null ? this.zoomPoint : new Point();
/*      */       
/* 2410 */       z.zoomDomainAxes(0.0D, this.info.getPlotInfo(), zp);
/* 2411 */       plot.setNotify(savedNotify);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */   public void restoreAutoRangeBounds()
/*      */   {
/* 2419 */     Plot plot = this.chart.getPlot();
/* 2420 */     if ((plot instanceof Zoomable)) {
/* 2421 */       Zoomable z = (Zoomable)plot;
/*      */       
/*      */ 
/*      */ 
/* 2425 */       boolean savedNotify = plot.isNotify();
/* 2426 */       plot.setNotify(false);
/*      */       
/* 2428 */       Point2D zp = this.zoomPoint != null ? this.zoomPoint : new Point();
/*      */       
/* 2430 */       z.zoomRangeAxes(0.0D, this.info.getPlotInfo(), zp);
/* 2431 */       plot.setNotify(savedNotify);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rectangle2D getScreenDataArea()
/*      */   {
/* 2442 */     Rectangle2D dataArea = this.info.getPlotInfo().getDataArea();
/* 2443 */     Insets insets = getInsets();
/* 2444 */     double x = dataArea.getX() * this.scaleX + insets.left;
/* 2445 */     double y = dataArea.getY() * this.scaleY + insets.top;
/* 2446 */     double w = dataArea.getWidth() * this.scaleX;
/* 2447 */     double h = dataArea.getHeight() * this.scaleY;
/* 2448 */     return new Rectangle2D.Double(x, y, w, h);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Rectangle2D getScreenDataArea(int x, int y)
/*      */   {
/* 2461 */     PlotRenderingInfo plotInfo = this.info.getPlotInfo();
/*      */     Rectangle2D result;
/* 2463 */     Rectangle2D result; if (plotInfo.getSubplotCount() == 0) {
/* 2464 */       result = getScreenDataArea();
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/* 2469 */       Point2D selectOrigin = translateScreenToJava2D(new Point(x, y));
/* 2470 */       int subplotIndex = plotInfo.getSubplotIndex(selectOrigin);
/* 2471 */       if (subplotIndex == -1) {
/* 2472 */         return null;
/*      */       }
/* 2474 */       result = scale(plotInfo.getSubplotInfo(subplotIndex).getDataArea());
/*      */     }
/* 2476 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getInitialDelay()
/*      */   {
/* 2487 */     return this.ownToolTipInitialDelay;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getReshowDelay()
/*      */   {
/* 2498 */     return this.ownToolTipReshowDelay;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getDismissDelay()
/*      */   {
/* 2510 */     return this.ownToolTipDismissDelay;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setInitialDelay(int delay)
/*      */   {
/* 2522 */     this.ownToolTipInitialDelay = delay;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setReshowDelay(int delay)
/*      */   {
/* 2534 */     this.ownToolTipReshowDelay = delay;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDismissDelay(int delay)
/*      */   {
/* 2546 */     this.ownToolTipDismissDelay = delay;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getZoomInFactor()
/*      */   {
/* 2557 */     return this.zoomInFactor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoomInFactor(double factor)
/*      */   {
/* 2568 */     this.zoomInFactor = factor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getZoomOutFactor()
/*      */   {
/* 2579 */     return this.zoomOutFactor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setZoomOutFactor(double factor)
/*      */   {
/* 2590 */     this.zoomOutFactor = factor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void drawZoomRectangle(Graphics2D g2, boolean xor)
/*      */   {
/* 2604 */     if (this.zoomRectangle != null) {
/* 2605 */       if (xor)
/*      */       {
/* 2607 */         g2.setXORMode(Color.gray);
/*      */       }
/* 2609 */       if (this.fillZoomRectangle) {
/* 2610 */         g2.setPaint(this.zoomFillPaint);
/* 2611 */         g2.fill(this.zoomRectangle);
/*      */       }
/*      */       else {
/* 2614 */         g2.setPaint(this.zoomOutlinePaint);
/* 2615 */         g2.draw(this.zoomRectangle);
/*      */       }
/* 2617 */       if (xor)
/*      */       {
/* 2619 */         g2.setPaintMode();
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
/*      */   private void drawHorizontalAxisTrace(Graphics2D g2, int x)
/*      */   {
/* 2633 */     Rectangle2D dataArea = getScreenDataArea();
/*      */     
/* 2635 */     g2.setXORMode(Color.orange);
/* 2636 */     if (((int)dataArea.getMinX() < x) && (x < (int)dataArea.getMaxX()))
/*      */     {
/* 2638 */       if (this.verticalTraceLine != null) {
/* 2639 */         g2.draw(this.verticalTraceLine);
/* 2640 */         this.verticalTraceLine.setLine(x, (int)dataArea.getMinY(), x, (int)dataArea.getMaxY());
/*      */       }
/*      */       else
/*      */       {
/* 2644 */         this.verticalTraceLine = new Line2D.Float(x, (int)dataArea.getMinY(), x, (int)dataArea.getMaxY());
/*      */       }
/*      */       
/* 2647 */       g2.draw(this.verticalTraceLine);
/*      */     }
/*      */     
/*      */ 
/* 2651 */     g2.setPaintMode();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void drawVerticalAxisTrace(Graphics2D g2, int y)
/*      */   {
/* 2663 */     Rectangle2D dataArea = getScreenDataArea();
/*      */     
/* 2665 */     g2.setXORMode(Color.orange);
/* 2666 */     if (((int)dataArea.getMinY() < y) && (y < (int)dataArea.getMaxY()))
/*      */     {
/* 2668 */       if (this.horizontalTraceLine != null) {
/* 2669 */         g2.draw(this.horizontalTraceLine);
/* 2670 */         this.horizontalTraceLine.setLine((int)dataArea.getMinX(), y, (int)dataArea.getMaxX(), y);
/*      */       }
/*      */       else
/*      */       {
/* 2674 */         this.horizontalTraceLine = new Line2D.Float((int)dataArea.getMinX(), y, (int)dataArea.getMaxX(), y);
/*      */       }
/*      */       
/*      */ 
/* 2678 */       g2.draw(this.horizontalTraceLine);
/*      */     }
/*      */     
/*      */ 
/* 2682 */     g2.setPaintMode();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void doEditChartProperties()
/*      */   {
/* 2693 */     ChartEditor editor = ChartEditorManager.getChartEditor(this.chart);
/* 2694 */     int result = JOptionPane.showConfirmDialog(this, editor, localizationResources.getString("Chart_Properties"), 2, -1);
/*      */     
/*      */ 
/* 2697 */     if (result == 0) {
/* 2698 */       editor.updateChart(this.chart);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void doCopy()
/*      */   {
/* 2709 */     Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
/*      */     
/* 2711 */     ChartTransferable selection = new ChartTransferable(this.chart, getWidth(), getHeight());
/*      */     
/* 2713 */     systemClipboard.setContents(selection, null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void doSaveAs()
/*      */     throws IOException
/*      */   {
/* 2724 */     JFileChooser fileChooser = new JFileChooser();
/* 2725 */     fileChooser.setCurrentDirectory(this.defaultDirectoryForSaveAs);
/* 2726 */     ExtensionFileFilter filter = new ExtensionFileFilter(localizationResources.getString("PNG_Image_Files"), ".png");
/*      */     
/* 2728 */     fileChooser.addChoosableFileFilter(filter);
/*      */     
/* 2730 */     int option = fileChooser.showSaveDialog(this);
/* 2731 */     if (option == 0) {
/* 2732 */       String filename = fileChooser.getSelectedFile().getPath();
/* 2733 */       if ((isEnforceFileExtensions()) && 
/* 2734 */         (!filename.endsWith(".png"))) {
/* 2735 */         filename = filename + ".png";
/*      */       }
/*      */       
/* 2738 */       ChartUtilities.saveChartAsPNG(new File(filename), this.chart, getWidth(), getHeight());
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void createChartPrintJob()
/*      */   {
/* 2749 */     PrinterJob job = PrinterJob.getPrinterJob();
/* 2750 */     PageFormat pf = job.defaultPage();
/* 2751 */     PageFormat pf2 = job.pageDialog(pf);
/* 2752 */     if (pf2 != pf) {
/* 2753 */       job.setPrintable(this, pf2);
/* 2754 */       if (job.printDialog()) {
/*      */         try {
/* 2756 */           job.print();
/*      */         }
/*      */         catch (PrinterException e) {
/* 2759 */           JOptionPane.showMessageDialog(this, e);
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
/*      */   public int print(Graphics g, PageFormat pf, int pageIndex)
/*      */   {
/* 2778 */     if (pageIndex != 0) {
/* 2779 */       return 1;
/*      */     }
/* 2781 */     Graphics2D g2 = (Graphics2D)g;
/* 2782 */     double x = pf.getImageableX();
/* 2783 */     double y = pf.getImageableY();
/* 2784 */     double w = pf.getImageableWidth();
/* 2785 */     double h = pf.getImageableHeight();
/* 2786 */     this.chart.draw(g2, new Rectangle2D.Double(x, y, w, h), this.anchor, null);
/*      */     
/* 2788 */     return 0;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void addChartMouseListener(ChartMouseListener listener)
/*      */   {
/* 2798 */     if (listener == null) {
/* 2799 */       throw new IllegalArgumentException("Null 'listener' argument.");
/*      */     }
/* 2801 */     this.chartMouseListeners.add(ChartMouseListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void removeChartMouseListener(ChartMouseListener listener)
/*      */   {
/* 2811 */     this.chartMouseListeners.remove(ChartMouseListener.class, listener);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public EventListener[] getListeners(Class listenerType)
/*      */   {
/* 2823 */     if (listenerType == ChartMouseListener.class)
/*      */     {
/* 2825 */       return this.chartMouseListeners.getListeners(listenerType);
/*      */     }
/*      */     
/* 2828 */     return super.getListeners(listenerType);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected JPopupMenu createPopupMenu(boolean properties, boolean save, boolean print, boolean zoom)
/*      */   {
/* 2844 */     return createPopupMenu(properties, false, save, print, zoom);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected JPopupMenu createPopupMenu(boolean properties, boolean copy, boolean save, boolean print, boolean zoom)
/*      */   {
/* 2863 */     JPopupMenu result = new JPopupMenu("Chart:");
/* 2864 */     boolean separator = false;
/*      */     
/* 2866 */     if (properties) {
/* 2867 */       JMenuItem propertiesItem = new JMenuItem(localizationResources.getString("Properties..."));
/*      */       
/* 2869 */       propertiesItem.setActionCommand("PROPERTIES");
/* 2870 */       propertiesItem.addActionListener(this);
/* 2871 */       result.add(propertiesItem);
/* 2872 */       separator = true;
/*      */     }
/*      */     
/* 2875 */     if (copy) {
/* 2876 */       if (separator) {
/* 2877 */         result.addSeparator();
/* 2878 */         separator = false;
/*      */       }
/* 2880 */       JMenuItem copyItem = new JMenuItem(localizationResources.getString("Copy"));
/*      */       
/* 2882 */       copyItem.setActionCommand("COPY");
/* 2883 */       copyItem.addActionListener(this);
/* 2884 */       result.add(copyItem);
/* 2885 */       separator = !save;
/*      */     }
/*      */     
/* 2888 */     if (save) {
/* 2889 */       if (separator) {
/* 2890 */         result.addSeparator();
/* 2891 */         separator = false;
/*      */       }
/* 2893 */       JMenuItem saveItem = new JMenuItem(localizationResources.getString("Save_as..."));
/*      */       
/* 2895 */       saveItem.setActionCommand("SAVE");
/* 2896 */       saveItem.addActionListener(this);
/* 2897 */       result.add(saveItem);
/* 2898 */       separator = true;
/*      */     }
/*      */     
/* 2901 */     if (print) {
/* 2902 */       if (separator) {
/* 2903 */         result.addSeparator();
/* 2904 */         separator = false;
/*      */       }
/* 2906 */       JMenuItem printItem = new JMenuItem(localizationResources.getString("Print..."));
/*      */       
/* 2908 */       printItem.setActionCommand("PRINT");
/* 2909 */       printItem.addActionListener(this);
/* 2910 */       result.add(printItem);
/* 2911 */       separator = true;
/*      */     }
/*      */     
/* 2914 */     if (zoom) {
/* 2915 */       if (separator) {
/* 2916 */         result.addSeparator();
/* 2917 */         separator = false;
/*      */       }
/*      */       
/* 2920 */       JMenu zoomInMenu = new JMenu(localizationResources.getString("Zoom_In"));
/*      */       
/*      */ 
/* 2923 */       this.zoomInBothMenuItem = new JMenuItem(localizationResources.getString("All_Axes"));
/*      */       
/* 2925 */       this.zoomInBothMenuItem.setActionCommand("ZOOM_IN_BOTH");
/* 2926 */       this.zoomInBothMenuItem.addActionListener(this);
/* 2927 */       zoomInMenu.add(this.zoomInBothMenuItem);
/*      */       
/* 2929 */       zoomInMenu.addSeparator();
/*      */       
/* 2931 */       this.zoomInDomainMenuItem = new JMenuItem(localizationResources.getString("Domain_Axis"));
/*      */       
/* 2933 */       this.zoomInDomainMenuItem.setActionCommand("ZOOM_IN_DOMAIN");
/* 2934 */       this.zoomInDomainMenuItem.addActionListener(this);
/* 2935 */       zoomInMenu.add(this.zoomInDomainMenuItem);
/*      */       
/* 2937 */       this.zoomInRangeMenuItem = new JMenuItem(localizationResources.getString("Range_Axis"));
/*      */       
/* 2939 */       this.zoomInRangeMenuItem.setActionCommand("ZOOM_IN_RANGE");
/* 2940 */       this.zoomInRangeMenuItem.addActionListener(this);
/* 2941 */       zoomInMenu.add(this.zoomInRangeMenuItem);
/*      */       
/* 2943 */       result.add(zoomInMenu);
/*      */       
/* 2945 */       JMenu zoomOutMenu = new JMenu(localizationResources.getString("Zoom_Out"));
/*      */       
/*      */ 
/* 2948 */       this.zoomOutBothMenuItem = new JMenuItem(localizationResources.getString("All_Axes"));
/*      */       
/* 2950 */       this.zoomOutBothMenuItem.setActionCommand("ZOOM_OUT_BOTH");
/* 2951 */       this.zoomOutBothMenuItem.addActionListener(this);
/* 2952 */       zoomOutMenu.add(this.zoomOutBothMenuItem);
/*      */       
/* 2954 */       zoomOutMenu.addSeparator();
/*      */       
/* 2956 */       this.zoomOutDomainMenuItem = new JMenuItem(localizationResources.getString("Domain_Axis"));
/*      */       
/* 2958 */       this.zoomOutDomainMenuItem.setActionCommand("ZOOM_DOMAIN_BOTH");
/*      */       
/* 2960 */       this.zoomOutDomainMenuItem.addActionListener(this);
/* 2961 */       zoomOutMenu.add(this.zoomOutDomainMenuItem);
/*      */       
/* 2963 */       this.zoomOutRangeMenuItem = new JMenuItem(localizationResources.getString("Range_Axis"));
/*      */       
/* 2965 */       this.zoomOutRangeMenuItem.setActionCommand("ZOOM_RANGE_BOTH");
/* 2966 */       this.zoomOutRangeMenuItem.addActionListener(this);
/* 2967 */       zoomOutMenu.add(this.zoomOutRangeMenuItem);
/*      */       
/* 2969 */       result.add(zoomOutMenu);
/*      */       
/* 2971 */       JMenu autoRangeMenu = new JMenu(localizationResources.getString("Auto_Range"));
/*      */       
/*      */ 
/* 2974 */       this.zoomResetBothMenuItem = new JMenuItem(localizationResources.getString("All_Axes"));
/*      */       
/* 2976 */       this.zoomResetBothMenuItem.setActionCommand("ZOOM_RESET_BOTH");
/*      */       
/* 2978 */       this.zoomResetBothMenuItem.addActionListener(this);
/* 2979 */       autoRangeMenu.add(this.zoomResetBothMenuItem);
/*      */       
/* 2981 */       autoRangeMenu.addSeparator();
/* 2982 */       this.zoomResetDomainMenuItem = new JMenuItem(localizationResources.getString("Domain_Axis"));
/*      */       
/* 2984 */       this.zoomResetDomainMenuItem.setActionCommand("ZOOM_RESET_DOMAIN");
/*      */       
/* 2986 */       this.zoomResetDomainMenuItem.addActionListener(this);
/* 2987 */       autoRangeMenu.add(this.zoomResetDomainMenuItem);
/*      */       
/* 2989 */       this.zoomResetRangeMenuItem = new JMenuItem(localizationResources.getString("Range_Axis"));
/*      */       
/* 2991 */       this.zoomResetRangeMenuItem.setActionCommand("ZOOM_RESET_RANGE");
/*      */       
/* 2993 */       this.zoomResetRangeMenuItem.addActionListener(this);
/* 2994 */       autoRangeMenu.add(this.zoomResetRangeMenuItem);
/*      */       
/* 2996 */       result.addSeparator();
/* 2997 */       result.add(autoRangeMenu);
/*      */     }
/*      */     
/*      */ 
/* 3001 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void displayPopupMenu(int x, int y)
/*      */   {
/* 3014 */     if (this.popup != null)
/*      */     {
/*      */ 
/*      */ 
/* 3018 */       Plot plot = this.chart.getPlot();
/* 3019 */       boolean isDomainZoomable = false;
/* 3020 */       boolean isRangeZoomable = false;
/* 3021 */       if ((plot instanceof Zoomable)) {
/* 3022 */         Zoomable z = (Zoomable)plot;
/* 3023 */         isDomainZoomable = z.isDomainZoomable();
/* 3024 */         isRangeZoomable = z.isRangeZoomable();
/*      */       }
/*      */       
/* 3027 */       if (this.zoomInDomainMenuItem != null) {
/* 3028 */         this.zoomInDomainMenuItem.setEnabled(isDomainZoomable);
/*      */       }
/* 3030 */       if (this.zoomOutDomainMenuItem != null) {
/* 3031 */         this.zoomOutDomainMenuItem.setEnabled(isDomainZoomable);
/*      */       }
/* 3033 */       if (this.zoomResetDomainMenuItem != null) {
/* 3034 */         this.zoomResetDomainMenuItem.setEnabled(isDomainZoomable);
/*      */       }
/*      */       
/* 3037 */       if (this.zoomInRangeMenuItem != null) {
/* 3038 */         this.zoomInRangeMenuItem.setEnabled(isRangeZoomable);
/*      */       }
/* 3040 */       if (this.zoomOutRangeMenuItem != null) {
/* 3041 */         this.zoomOutRangeMenuItem.setEnabled(isRangeZoomable);
/*      */       }
/*      */       
/* 3044 */       if (this.zoomResetRangeMenuItem != null) {
/* 3045 */         this.zoomResetRangeMenuItem.setEnabled(isRangeZoomable);
/*      */       }
/*      */       
/* 3048 */       if (this.zoomInBothMenuItem != null) {
/* 3049 */         this.zoomInBothMenuItem.setEnabled((isDomainZoomable) && (isRangeZoomable));
/*      */       }
/*      */       
/* 3052 */       if (this.zoomOutBothMenuItem != null) {
/* 3053 */         this.zoomOutBothMenuItem.setEnabled((isDomainZoomable) && (isRangeZoomable));
/*      */       }
/*      */       
/* 3056 */       if (this.zoomResetBothMenuItem != null) {
/* 3057 */         this.zoomResetBothMenuItem.setEnabled((isDomainZoomable) && (isRangeZoomable));
/*      */       }
/*      */       
/*      */ 
/* 3061 */       this.popup.show(this, x, y);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void updateUI()
/*      */   {
/* 3072 */     if (this.popup != null) {
/* 3073 */       SwingUtilities.updateComponentTreeUI(this.popup);
/*      */     }
/* 3075 */     super.updateUI();
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
/* 3086 */     stream.defaultWriteObject();
/* 3087 */     SerialUtilities.writePaint(this.zoomFillPaint, stream);
/* 3088 */     SerialUtilities.writePaint(this.zoomOutlinePaint, stream);
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
/* 3101 */     stream.defaultReadObject();
/* 3102 */     this.zoomFillPaint = SerialUtilities.readPaint(stream);
/* 3103 */     this.zoomOutlinePaint = SerialUtilities.readPaint(stream);
/*      */     
/*      */ 
/* 3106 */     this.chartMouseListeners = new EventListenerList();
/*      */     
/*      */ 
/* 3109 */     if (this.chart != null) {
/* 3110 */       this.chart.addChangeListener(this);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartPanel.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */