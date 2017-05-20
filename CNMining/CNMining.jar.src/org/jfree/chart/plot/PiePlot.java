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
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Arc2D;
/*      */ import java.awt.geom.Arc2D.Double;
/*      */ import java.awt.geom.CubicCurve2D;
/*      */ import java.awt.geom.CubicCurve2D.Float;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Line2D.Float;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.QuadCurve2D;
/*      */ import java.awt.geom.QuadCurve2D.Float;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.ResourceBundle;
/*      */ import java.util.TreeMap;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.LegendItemCollection;
/*      */ import org.jfree.chart.PaintMap;
/*      */ import org.jfree.chart.StrokeMap;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.PieSectionEntity;
/*      */ import org.jfree.chart.labels.PieSectionLabelGenerator;
/*      */ import org.jfree.chart.labels.PieToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
/*      */ import org.jfree.chart.urls.PieURLGenerator;
/*      */ import org.jfree.chart.util.ResourceBundleWrapper;
/*      */ import org.jfree.data.DefaultKeyedValues;
/*      */ import org.jfree.data.KeyedValues;
/*      */ import org.jfree.data.general.DatasetChangeEvent;
/*      */ import org.jfree.data.general.DatasetUtilities;
/*      */ import org.jfree.data.general.PieDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.text.G2TextMeasurer;
/*      */ import org.jfree.text.TextBlock;
/*      */ import org.jfree.text.TextBox;
/*      */ import org.jfree.text.TextUtilities;
/*      */ import org.jfree.ui.RectangleAnchor;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.ObjectUtilities;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.Rotation;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ import org.jfree.util.UnitType;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class PiePlot
/*      */   extends Plot
/*      */   implements Cloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = -795612466005590431L;
/*      */   public static final double DEFAULT_INTERIOR_GAP = 0.08D;
/*      */   public static final double MAX_INTERIOR_GAP = 0.4D;
/*      */   public static final double DEFAULT_START_ANGLE = 90.0D;
/*  265 */   public static final Font DEFAULT_LABEL_FONT = new Font("SansSerif", 0, 10);
/*      */   
/*      */ 
/*      */ 
/*  269 */   public static final Paint DEFAULT_LABEL_PAINT = Color.black;
/*      */   
/*      */ 
/*  272 */   public static final Paint DEFAULT_LABEL_BACKGROUND_PAINT = new Color(255, 255, 192);
/*      */   
/*      */ 
/*      */ 
/*  276 */   public static final Paint DEFAULT_LABEL_OUTLINE_PAINT = Color.black;
/*      */   
/*      */ 
/*  279 */   public static final Stroke DEFAULT_LABEL_OUTLINE_STROKE = new BasicStroke(0.5F);
/*      */   
/*      */ 
/*      */ 
/*  283 */   public static final Paint DEFAULT_LABEL_SHADOW_PAINT = new Color(151, 151, 151, 128);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   public static final double DEFAULT_MINIMUM_ARC_ANGLE_TO_DRAW = 1.0E-5D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieDataset dataset;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private int pieIndex;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double interiorGap;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean circular;
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
/*      */   private PaintMap sectionPaintMap;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint baseSectionPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSectionPaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean sectionOutlinesVisible;
/*      */   
/*      */ 
/*      */ 
/*      */   private PaintMap sectionOutlinePaintMap;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint baseSectionOutlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSectionOutlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */   private StrokeMap sectionOutlineStrokeMap;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Stroke baseSectionOutlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private boolean autoPopulateSectionOutlineStroke;
/*      */   
/*      */ 
/*      */ 
/*  359 */   private transient Paint shadowPaint = Color.gray;
/*      */   
/*      */ 
/*  362 */   private double shadowXOffset = 4.0D;
/*      */   
/*      */ 
/*  365 */   private double shadowYOffset = 4.0D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Map explodePercentages;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieSectionLabelGenerator labelGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private Font labelFont;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint labelPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint labelBackgroundPaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Paint labelOutlinePaint;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Stroke labelOutlineStroke;
/*      */   
/*      */ 
/*      */ 
/*      */   private transient Paint labelShadowPaint;
/*      */   
/*      */ 
/*      */ 
/*  408 */   private boolean simpleLabels = true;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private RectangleInsets labelPadding;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private RectangleInsets simpleLabelOffset;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  426 */   private double maximumLabelWidth = 0.14D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  432 */   private double labelGap = 0.025D;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean labelLinksVisible;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  442 */   private PieLabelLinkStyle labelLinkStyle = PieLabelLinkStyle.STANDARD;
/*      */   
/*      */ 
/*  445 */   private double labelLinkMargin = 0.025D;
/*      */   
/*      */ 
/*  448 */   private transient Paint labelLinkPaint = Color.black;
/*      */   
/*      */ 
/*  451 */   private transient Stroke labelLinkStroke = new BasicStroke(0.5F);
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private AbstractPieLabelDistributor labelDistributor;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieToolTipGenerator toolTipGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieURLGenerator urlGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieSectionLabelGenerator legendLabelGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieSectionLabelGenerator legendLabelToolTipGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private PieURLGenerator legendLabelURLGenerator;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean ignoreNullValues;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private boolean ignoreZeroValues;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private transient Shape legendItemShape;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   private double minimumArcAngleToDraw;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*  506 */   protected static ResourceBundle localizationResources = ResourceBundleWrapper.getBundle("org.jfree.chart.plot.LocalizationBundle");
/*      */   
/*      */ 
/*      */   static final boolean DEBUG_DRAW_INTERIOR = false;
/*      */   
/*      */ 
/*      */   static final boolean DEBUG_DRAW_LINK_AREA = false;
/*      */   
/*      */   static final boolean DEBUG_DRAW_PIE_AREA = false;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private transient Paint sectionPaint;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private transient Paint sectionOutlinePaint;
/*      */   
/*      */   /**
/*      */    * @deprecated
/*      */    */
/*      */   private transient Stroke sectionOutlineStroke;
/*      */   
/*      */ 
/*      */   public PiePlot()
/*      */   {
/*  534 */     this(null);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PiePlot(PieDataset dataset)
/*      */   {
/*  544 */     this.dataset = dataset;
/*  545 */     if (dataset != null) {
/*  546 */       dataset.addChangeListener(this);
/*      */     }
/*  548 */     this.pieIndex = 0;
/*      */     
/*  550 */     this.interiorGap = 0.08D;
/*  551 */     this.circular = true;
/*  552 */     this.startAngle = 90.0D;
/*  553 */     this.direction = Rotation.CLOCKWISE;
/*  554 */     this.minimumArcAngleToDraw = 1.0E-5D;
/*      */     
/*  556 */     this.sectionPaint = null;
/*  557 */     this.sectionPaintMap = new PaintMap();
/*  558 */     this.baseSectionPaint = Color.gray;
/*  559 */     this.autoPopulateSectionPaint = true;
/*      */     
/*  561 */     this.sectionOutlinesVisible = true;
/*  562 */     this.sectionOutlinePaint = null;
/*  563 */     this.sectionOutlinePaintMap = new PaintMap();
/*  564 */     this.baseSectionOutlinePaint = DEFAULT_OUTLINE_PAINT;
/*  565 */     this.autoPopulateSectionOutlinePaint = false;
/*      */     
/*  567 */     this.sectionOutlineStroke = null;
/*  568 */     this.sectionOutlineStrokeMap = new StrokeMap();
/*  569 */     this.baseSectionOutlineStroke = DEFAULT_OUTLINE_STROKE;
/*  570 */     this.autoPopulateSectionOutlineStroke = false;
/*      */     
/*  572 */     this.explodePercentages = new TreeMap();
/*      */     
/*  574 */     this.labelGenerator = new StandardPieSectionLabelGenerator();
/*  575 */     this.labelFont = DEFAULT_LABEL_FONT;
/*  576 */     this.labelPaint = DEFAULT_LABEL_PAINT;
/*  577 */     this.labelBackgroundPaint = DEFAULT_LABEL_BACKGROUND_PAINT;
/*  578 */     this.labelOutlinePaint = DEFAULT_LABEL_OUTLINE_PAINT;
/*  579 */     this.labelOutlineStroke = DEFAULT_LABEL_OUTLINE_STROKE;
/*  580 */     this.labelShadowPaint = DEFAULT_LABEL_SHADOW_PAINT;
/*  581 */     this.labelLinksVisible = true;
/*  582 */     this.labelDistributor = new PieLabelDistributor(0);
/*      */     
/*  584 */     this.simpleLabels = false;
/*  585 */     this.simpleLabelOffset = new RectangleInsets(UnitType.RELATIVE, 0.18D, 0.18D, 0.18D, 0.18D);
/*      */     
/*  587 */     this.labelPadding = new RectangleInsets(2.0D, 2.0D, 2.0D, 2.0D);
/*      */     
/*  589 */     this.toolTipGenerator = null;
/*  590 */     this.urlGenerator = null;
/*  591 */     this.legendLabelGenerator = new StandardPieSectionLabelGenerator();
/*  592 */     this.legendLabelToolTipGenerator = null;
/*  593 */     this.legendLabelURLGenerator = null;
/*  594 */     this.legendItemShape = Plot.DEFAULT_LEGEND_ITEM_CIRCLE;
/*      */     
/*  596 */     this.ignoreNullValues = false;
/*  597 */     this.ignoreZeroValues = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieDataset getDataset()
/*      */   {
/*  608 */     return this.dataset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDataset(PieDataset dataset)
/*      */   {
/*  621 */     PieDataset existing = this.dataset;
/*  622 */     if (existing != null) {
/*  623 */       existing.removeChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  627 */     this.dataset = dataset;
/*  628 */     if (dataset != null) {
/*  629 */       setDatasetGroup(dataset.getGroup());
/*  630 */       dataset.addChangeListener(this);
/*      */     }
/*      */     
/*      */ 
/*  634 */     DatasetChangeEvent event = new DatasetChangeEvent(this, dataset);
/*  635 */     datasetChanged(event);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPieIndex()
/*      */   {
/*  647 */     return this.pieIndex;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPieIndex(int index)
/*      */   {
/*  659 */     this.pieIndex = index;
/*      */   }
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
/*  671 */     return this.startAngle;
/*      */   }
/*      */   
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
/*  685 */     this.startAngle = angle;
/*  686 */     fireChangeEvent();
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
/*  698 */     return this.direction;
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
/*  710 */     if (direction == null) {
/*  711 */       throw new IllegalArgumentException("Null 'direction' argument.");
/*      */     }
/*  713 */     this.direction = direction;
/*  714 */     fireChangeEvent();
/*      */   }
/*      */   
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
/*  727 */     return this.interiorGap;
/*      */   }
/*      */   
/*      */ 
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
/*  742 */     if ((percent < 0.0D) || (percent > 0.4D)) {
/*  743 */       throw new IllegalArgumentException("Invalid 'percent' (" + percent + ") argument.");
/*      */     }
/*      */     
/*      */ 
/*  747 */     if (this.interiorGap != percent) {
/*  748 */       this.interiorGap = percent;
/*  749 */       fireChangeEvent();
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
/*      */   public boolean isCircular()
/*      */   {
/*  763 */     return this.circular;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCircular(boolean flag)
/*      */   {
/*  775 */     setCircular(flag, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCircular(boolean circular, boolean notify)
/*      */   {
/*  788 */     this.circular = circular;
/*  789 */     if (notify) {
/*  790 */       fireChangeEvent();
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
/*      */   public boolean getIgnoreNullValues()
/*      */   {
/*  803 */     return this.ignoreNullValues;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIgnoreNullValues(boolean flag)
/*      */   {
/*  818 */     this.ignoreNullValues = flag;
/*  819 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getIgnoreZeroValues()
/*      */   {
/*  831 */     return this.ignoreZeroValues;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setIgnoreZeroValues(boolean flag)
/*      */   {
/*  846 */     this.ignoreZeroValues = flag;
/*  847 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Paint lookupSectionPaint(Comparable key)
/*      */   {
/*  865 */     return lookupSectionPaint(key, getAutoPopulateSectionPaint());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Paint lookupSectionPaint(Comparable key, boolean autoPopulate)
/*      */   {
/*  894 */     Paint result = getSectionPaint();
/*  895 */     if (result != null) {
/*  896 */       return result;
/*      */     }
/*      */     
/*      */ 
/*  900 */     result = this.sectionPaintMap.getPaint(key);
/*  901 */     if (result != null) {
/*  902 */       return result;
/*      */     }
/*      */     
/*      */ 
/*  906 */     if (autoPopulate) {
/*  907 */       DrawingSupplier ds = getDrawingSupplier();
/*  908 */       if (ds != null) {
/*  909 */         result = ds.getNextPaint();
/*  910 */         this.sectionPaintMap.put(key, result);
/*      */       }
/*      */       else {
/*  913 */         result = this.baseSectionPaint;
/*      */       }
/*      */     }
/*      */     else {
/*  917 */       result = this.baseSectionPaint;
/*      */     }
/*  919 */     return result;
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
/*      */   public Paint getSectionPaint()
/*      */   {
/*  933 */     return this.sectionPaint;
/*      */   }
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
/*      */   public void setSectionPaint(Paint paint)
/*      */   {
/*  949 */     this.sectionPaint = paint;
/*  950 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Comparable getSectionKey(int section)
/*      */   {
/*  968 */     Comparable key = null;
/*  969 */     if ((this.dataset != null) && 
/*  970 */       (section >= 0) && (section < this.dataset.getItemCount())) {
/*  971 */       key = this.dataset.getKey(section);
/*      */     }
/*      */     
/*  974 */     if (key == null) {
/*  975 */       key = new Integer(section);
/*      */     }
/*  977 */     return key;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getSectionPaint(Comparable key)
/*      */   {
/*  998 */     return this.sectionPaintMap.getPaint(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSectionPaint(Comparable key, Paint paint)
/*      */   {
/* 1017 */     this.sectionPaintMap.put(key, paint);
/* 1018 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearSectionPaints(boolean notify)
/*      */   {
/* 1034 */     this.sectionPaintMap.clear();
/* 1035 */     if (notify) {
/* 1036 */       fireChangeEvent();
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
/*      */   public Paint getBaseSectionPaint()
/*      */   {
/* 1049 */     return this.baseSectionPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseSectionPaint(Paint paint)
/*      */   {
/* 1061 */     if (paint == null) {
/* 1062 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1064 */     this.baseSectionPaint = paint;
/* 1065 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoPopulateSectionPaint()
/*      */   {
/* 1077 */     return this.autoPopulateSectionPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoPopulateSectionPaint(boolean auto)
/*      */   {
/* 1090 */     this.autoPopulateSectionPaint = auto;
/* 1091 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getSectionOutlinesVisible()
/*      */   {
/* 1106 */     return this.sectionOutlinesVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSectionOutlinesVisible(boolean visible)
/*      */   {
/* 1119 */     this.sectionOutlinesVisible = visible;
/* 1120 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Paint lookupSectionOutlinePaint(Comparable key)
/*      */   {
/* 1137 */     return lookupSectionOutlinePaint(key, getAutoPopulateSectionOutlinePaint());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Paint lookupSectionOutlinePaint(Comparable key, boolean autoPopulate)
/*      */   {
/* 1168 */     Paint result = getSectionOutlinePaint();
/* 1169 */     if (result != null) {
/* 1170 */       return result;
/*      */     }
/*      */     
/*      */ 
/* 1174 */     result = this.sectionOutlinePaintMap.getPaint(key);
/* 1175 */     if (result != null) {
/* 1176 */       return result;
/*      */     }
/*      */     
/*      */ 
/* 1180 */     if (autoPopulate) {
/* 1181 */       DrawingSupplier ds = getDrawingSupplier();
/* 1182 */       if (ds != null) {
/* 1183 */         result = ds.getNextOutlinePaint();
/* 1184 */         this.sectionOutlinePaintMap.put(key, result);
/*      */       }
/*      */       else {
/* 1187 */         result = this.baseSectionOutlinePaint;
/*      */       }
/*      */     }
/*      */     else {
/* 1191 */       result = this.baseSectionOutlinePaint;
/*      */     }
/* 1193 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getSectionOutlinePaint(Comparable key)
/*      */   {
/* 1214 */     return this.sectionOutlinePaintMap.getPaint(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSectionOutlinePaint(Comparable key, Paint paint)
/*      */   {
/* 1233 */     this.sectionOutlinePaintMap.put(key, paint);
/* 1234 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearSectionOutlinePaints(boolean notify)
/*      */   {
/* 1250 */     this.sectionOutlinePaintMap.clear();
/* 1251 */     if (notify) {
/* 1252 */       fireChangeEvent();
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
/*      */   public Paint getBaseSectionOutlinePaint()
/*      */   {
/* 1265 */     return this.baseSectionOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseSectionOutlinePaint(Paint paint)
/*      */   {
/* 1276 */     if (paint == null) {
/* 1277 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1279 */     this.baseSectionOutlinePaint = paint;
/* 1280 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoPopulateSectionOutlinePaint()
/*      */   {
/* 1293 */     return this.autoPopulateSectionOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoPopulateSectionOutlinePaint(boolean auto)
/*      */   {
/* 1306 */     this.autoPopulateSectionOutlinePaint = auto;
/* 1307 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Stroke lookupSectionOutlineStroke(Comparable key)
/*      */   {
/* 1326 */     return lookupSectionOutlineStroke(key, getAutoPopulateSectionOutlineStroke());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Stroke lookupSectionOutlineStroke(Comparable key, boolean autoPopulate)
/*      */   {
/* 1357 */     Stroke result = getSectionOutlineStroke();
/* 1358 */     if (result != null) {
/* 1359 */       return result;
/*      */     }
/*      */     
/*      */ 
/* 1363 */     result = this.sectionOutlineStrokeMap.getStroke(key);
/* 1364 */     if (result != null) {
/* 1365 */       return result;
/*      */     }
/*      */     
/*      */ 
/* 1369 */     if (autoPopulate) {
/* 1370 */       DrawingSupplier ds = getDrawingSupplier();
/* 1371 */       if (ds != null) {
/* 1372 */         result = ds.getNextOutlineStroke();
/* 1373 */         this.sectionOutlineStrokeMap.put(key, result);
/*      */       }
/*      */       else {
/* 1376 */         result = this.baseSectionOutlineStroke;
/*      */       }
/*      */     }
/*      */     else {
/* 1380 */       result = this.baseSectionOutlineStroke;
/*      */     }
/* 1382 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getSectionOutlineStroke(Comparable key)
/*      */   {
/* 1403 */     return this.sectionOutlineStrokeMap.getStroke(key);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSectionOutlineStroke(Comparable key, Stroke stroke)
/*      */   {
/* 1422 */     this.sectionOutlineStrokeMap.put(key, stroke);
/* 1423 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void clearSectionOutlineStrokes(boolean notify)
/*      */   {
/* 1439 */     this.sectionOutlineStrokeMap.clear();
/* 1440 */     if (notify) {
/* 1441 */       fireChangeEvent();
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
/*      */   public Stroke getBaseSectionOutlineStroke()
/*      */   {
/* 1454 */     return this.baseSectionOutlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaseSectionOutlineStroke(Stroke stroke)
/*      */   {
/* 1465 */     if (stroke == null) {
/* 1466 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1468 */     this.baseSectionOutlineStroke = stroke;
/* 1469 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getAutoPopulateSectionOutlineStroke()
/*      */   {
/* 1482 */     return this.autoPopulateSectionOutlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAutoPopulateSectionOutlineStroke(boolean auto)
/*      */   {
/* 1495 */     this.autoPopulateSectionOutlineStroke = auto;
/* 1496 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getShadowPaint()
/*      */   {
/* 1507 */     return this.shadowPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowPaint(Paint paint)
/*      */   {
/* 1519 */     this.shadowPaint = paint;
/* 1520 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getShadowXOffset()
/*      */   {
/* 1531 */     return this.shadowXOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowXOffset(double offset)
/*      */   {
/* 1543 */     this.shadowXOffset = offset;
/* 1544 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getShadowYOffset()
/*      */   {
/* 1555 */     return this.shadowYOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowYOffset(double offset)
/*      */   {
/* 1567 */     this.shadowYOffset = offset;
/* 1568 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getExplodePercent(Comparable key)
/*      */   {
/* 1588 */     double result = 0.0D;
/* 1589 */     if (this.explodePercentages != null) {
/* 1590 */       Number percent = (Number)this.explodePercentages.get(key);
/* 1591 */       if (percent != null) {
/* 1592 */         result = percent.doubleValue();
/*      */       }
/*      */     }
/* 1595 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExplodePercent(Comparable key, double percent)
/*      */   {
/* 1610 */     if (key == null) {
/* 1611 */       throw new IllegalArgumentException("Null 'key' argument.");
/*      */     }
/* 1613 */     if (this.explodePercentages == null) {
/* 1614 */       this.explodePercentages = new TreeMap();
/*      */     }
/* 1616 */     this.explodePercentages.put(key, new Double(percent));
/* 1617 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMaximumExplodePercent()
/*      */   {
/* 1626 */     if (this.dataset == null) {
/* 1627 */       return 0.0D;
/*      */     }
/* 1629 */     double result = 0.0D;
/* 1630 */     Iterator iterator = this.dataset.getKeys().iterator();
/* 1631 */     while (iterator.hasNext()) {
/* 1632 */       Comparable key = (Comparable)iterator.next();
/* 1633 */       Number explode = (Number)this.explodePercentages.get(key);
/* 1634 */       if (explode != null) {
/* 1635 */         result = Math.max(result, explode.doubleValue());
/*      */       }
/*      */     }
/* 1638 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieSectionLabelGenerator getLabelGenerator()
/*      */   {
/* 1649 */     return this.labelGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelGenerator(PieSectionLabelGenerator generator)
/*      */   {
/* 1661 */     this.labelGenerator = generator;
/* 1662 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getLabelGap()
/*      */   {
/* 1674 */     return this.labelGap;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelGap(double gap)
/*      */   {
/* 1687 */     this.labelGap = gap;
/* 1688 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMaximumLabelWidth()
/*      */   {
/* 1699 */     return this.maximumLabelWidth;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumLabelWidth(double width)
/*      */   {
/* 1711 */     this.maximumLabelWidth = width;
/* 1712 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getLabelLinksVisible()
/*      */   {
/* 1724 */     return this.labelLinksVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinksVisible(boolean visible)
/*      */   {
/* 1739 */     this.labelLinksVisible = visible;
/* 1740 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieLabelLinkStyle getLabelLinkStyle()
/*      */   {
/* 1753 */     return this.labelLinkStyle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinkStyle(PieLabelLinkStyle style)
/*      */   {
/* 1767 */     if (style == null) {
/* 1768 */       throw new IllegalArgumentException("Null 'style' argument.");
/*      */     }
/* 1770 */     this.labelLinkStyle = style;
/* 1771 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getLabelLinkMargin()
/*      */   {
/* 1783 */     return this.labelLinkMargin;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinkMargin(double margin)
/*      */   {
/* 1795 */     this.labelLinkMargin = margin;
/* 1796 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelLinkPaint()
/*      */   {
/* 1808 */     return this.labelLinkPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinkPaint(Paint paint)
/*      */   {
/* 1821 */     if (paint == null) {
/* 1822 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1824 */     this.labelLinkPaint = paint;
/* 1825 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getLabelLinkStroke()
/*      */   {
/* 1836 */     return this.labelLinkStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinkStroke(Stroke stroke)
/*      */   {
/* 1848 */     if (stroke == null) {
/* 1849 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*      */     }
/* 1851 */     this.labelLinkStroke = stroke;
/* 1852 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected double getLabelLinkDepth()
/*      */   {
/* 1867 */     return 0.1D;
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
/* 1878 */     return this.labelFont;
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
/* 1890 */     if (font == null) {
/* 1891 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/* 1893 */     this.labelFont = font;
/* 1894 */     fireChangeEvent();
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
/* 1905 */     return this.labelPaint;
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
/* 1917 */     if (paint == null) {
/* 1918 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1920 */     this.labelPaint = paint;
/* 1921 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelBackgroundPaint()
/*      */   {
/* 1932 */     return this.labelBackgroundPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelBackgroundPaint(Paint paint)
/*      */   {
/* 1944 */     this.labelBackgroundPaint = paint;
/* 1945 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelOutlinePaint()
/*      */   {
/* 1956 */     return this.labelOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelOutlinePaint(Paint paint)
/*      */   {
/* 1968 */     this.labelOutlinePaint = paint;
/* 1969 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Stroke getLabelOutlineStroke()
/*      */   {
/* 1980 */     return this.labelOutlineStroke;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelOutlineStroke(Stroke stroke)
/*      */   {
/* 1992 */     this.labelOutlineStroke = stroke;
/* 1993 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelShadowPaint()
/*      */   {
/* 2004 */     return this.labelShadowPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelShadowPaint(Paint paint)
/*      */   {
/* 2016 */     this.labelShadowPaint = paint;
/* 2017 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getLabelPadding()
/*      */   {
/* 2030 */     return this.labelPadding;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelPadding(RectangleInsets padding)
/*      */   {
/* 2044 */     if (padding == null) {
/* 2045 */       throw new IllegalArgumentException("Null 'padding' argument.");
/*      */     }
/* 2047 */     this.labelPadding = padding;
/* 2048 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getSimpleLabels()
/*      */   {
/* 2060 */     return this.simpleLabels;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSimpleLabels(boolean simple)
/*      */   {
/* 2073 */     this.simpleLabels = simple;
/* 2074 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public RectangleInsets getSimpleLabelOffset()
/*      */   {
/* 2087 */     return this.simpleLabelOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSimpleLabelOffset(RectangleInsets offset)
/*      */   {
/* 2101 */     if (offset == null) {
/* 2102 */       throw new IllegalArgumentException("Null 'offset' argument.");
/*      */     }
/* 2104 */     this.simpleLabelOffset = offset;
/* 2105 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public AbstractPieLabelDistributor getLabelDistributor()
/*      */   {
/* 2117 */     return this.labelDistributor;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelDistributor(AbstractPieLabelDistributor distributor)
/*      */   {
/* 2129 */     if (distributor == null) {
/* 2130 */       throw new IllegalArgumentException("Null 'distributor' argument.");
/*      */     }
/* 2132 */     this.labelDistributor = distributor;
/* 2133 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieToolTipGenerator getToolTipGenerator()
/*      */   {
/* 2146 */     return this.toolTipGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setToolTipGenerator(PieToolTipGenerator generator)
/*      */   {
/* 2159 */     this.toolTipGenerator = generator;
/* 2160 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieURLGenerator getURLGenerator()
/*      */   {
/* 2171 */     return this.urlGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setURLGenerator(PieURLGenerator generator)
/*      */   {
/* 2183 */     this.urlGenerator = generator;
/* 2184 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getMinimumArcAngleToDraw()
/*      */   {
/* 2196 */     return this.minimumArcAngleToDraw;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMinimumArcAngleToDraw(double angle)
/*      */   {
/* 2218 */     this.minimumArcAngleToDraw = angle;
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
/* 2229 */     return this.legendItemShape;
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
/* 2241 */     if (shape == null) {
/* 2242 */       throw new IllegalArgumentException("Null 'shape' argument.");
/*      */     }
/* 2244 */     this.legendItemShape = shape;
/* 2245 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieSectionLabelGenerator getLegendLabelGenerator()
/*      */   {
/* 2256 */     return this.legendLabelGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendLabelGenerator(PieSectionLabelGenerator generator)
/*      */   {
/* 2268 */     if (generator == null) {
/* 2269 */       throw new IllegalArgumentException("Null 'generator' argument.");
/*      */     }
/* 2271 */     this.legendLabelGenerator = generator;
/* 2272 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieSectionLabelGenerator getLegendLabelToolTipGenerator()
/*      */   {
/* 2283 */     return this.legendLabelToolTipGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendLabelToolTipGenerator(PieSectionLabelGenerator generator)
/*      */   {
/* 2296 */     this.legendLabelToolTipGenerator = generator;
/* 2297 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieURLGenerator getLegendLabelURLGenerator()
/*      */   {
/* 2310 */     return this.legendLabelURLGenerator;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendLabelURLGenerator(PieURLGenerator generator)
/*      */   {
/* 2324 */     this.legendLabelURLGenerator = generator;
/* 2325 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PiePlotState initialise(Graphics2D g2, Rectangle2D plotArea, PiePlot plot, Integer index, PlotRenderingInfo info)
/*      */   {
/* 2346 */     PiePlotState state = new PiePlotState(info);
/* 2347 */     state.setPassesRequired(2);
/* 2348 */     if (this.dataset != null) {
/* 2349 */       state.setTotal(DatasetUtilities.calculatePieDatasetTotal(plot.getDataset()));
/*      */     }
/*      */     
/* 2352 */     state.setLatestAngle(plot.getStartAngle());
/* 2353 */     return state;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/* 2372 */     RectangleInsets insets = getInsets();
/* 2373 */     insets.trim(area);
/*      */     
/* 2375 */     if (info != null) {
/* 2376 */       info.setPlotArea(area);
/* 2377 */       info.setDataArea(area);
/*      */     }
/*      */     
/* 2380 */     drawBackground(g2, area);
/* 2381 */     drawOutline(g2, area);
/*      */     
/* 2383 */     Shape savedClip = g2.getClip();
/* 2384 */     g2.clip(area);
/*      */     
/* 2386 */     Composite originalComposite = g2.getComposite();
/* 2387 */     g2.setComposite(AlphaComposite.getInstance(3, getForegroundAlpha()));
/*      */     
/*      */ 
/* 2390 */     if (!DatasetUtilities.isEmptyOrNull(this.dataset)) {
/* 2391 */       drawPie(g2, area, info);
/*      */     }
/*      */     else {
/* 2394 */       drawNoDataMessage(g2, area);
/*      */     }
/*      */     
/* 2397 */     g2.setClip(savedClip);
/* 2398 */     g2.setComposite(originalComposite);
/*      */     
/* 2400 */     drawOutline(g2, area);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawPie(Graphics2D g2, Rectangle2D plotArea, PlotRenderingInfo info)
/*      */   {
/* 2414 */     PiePlotState state = initialise(g2, plotArea, this, null, info);
/*      */     
/*      */ 
/* 2417 */     double labelReserve = 0.0D;
/* 2418 */     if ((this.labelGenerator != null) && (!this.simpleLabels)) {
/* 2419 */       labelReserve = this.labelGap + this.maximumLabelWidth;
/*      */     }
/* 2421 */     double gapHorizontal = plotArea.getWidth() * (this.interiorGap + labelReserve) * 2.0D;
/*      */     
/* 2423 */     double gapVertical = plotArea.getHeight() * this.interiorGap * 2.0D;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2439 */     double linkX = plotArea.getX() + gapHorizontal / 2.0D;
/* 2440 */     double linkY = plotArea.getY() + gapVertical / 2.0D;
/* 2441 */     double linkW = plotArea.getWidth() - gapHorizontal;
/* 2442 */     double linkH = plotArea.getHeight() - gapVertical;
/*      */     
/*      */ 
/* 2445 */     if (this.circular) {
/* 2446 */       double min = Math.min(linkW, linkH) / 2.0D;
/* 2447 */       linkX = (linkX + linkX + linkW) / 2.0D - min;
/* 2448 */       linkY = (linkY + linkY + linkH) / 2.0D - min;
/* 2449 */       linkW = 2.0D * min;
/* 2450 */       linkH = 2.0D * min;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 2455 */     Rectangle2D linkArea = new Rectangle2D.Double(linkX, linkY, linkW, linkH);
/*      */     
/* 2457 */     state.setLinkArea(linkArea);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2470 */     double lm = 0.0D;
/* 2471 */     if (!this.simpleLabels) {
/* 2472 */       lm = this.labelLinkMargin;
/*      */     }
/* 2474 */     double hh = linkArea.getWidth() * lm * 2.0D;
/* 2475 */     double vv = linkArea.getHeight() * lm * 2.0D;
/* 2476 */     Rectangle2D explodeArea = new Rectangle2D.Double(linkX + hh / 2.0D, linkY + vv / 2.0D, linkW - hh, linkH - vv);
/*      */     
/*      */ 
/* 2479 */     state.setExplodedPieArea(explodeArea);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2484 */     double maximumExplodePercent = getMaximumExplodePercent();
/* 2485 */     double percent = maximumExplodePercent / (1.0D + maximumExplodePercent);
/*      */     
/* 2487 */     double h1 = explodeArea.getWidth() * percent;
/* 2488 */     double v1 = explodeArea.getHeight() * percent;
/* 2489 */     Rectangle2D pieArea = new Rectangle2D.Double(explodeArea.getX() + h1 / 2.0D, explodeArea.getY() + v1 / 2.0D, explodeArea.getWidth() - h1, explodeArea.getHeight() - v1);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 2497 */     state.setPieArea(pieArea);
/* 2498 */     state.setPieCenterX(pieArea.getCenterX());
/* 2499 */     state.setPieCenterY(pieArea.getCenterY());
/* 2500 */     state.setPieWRadius(pieArea.getWidth() / 2.0D);
/* 2501 */     state.setPieHRadius(pieArea.getHeight() / 2.0D);
/*      */     
/*      */ 
/* 2504 */     if ((this.dataset != null) && (this.dataset.getKeys().size() > 0))
/*      */     {
/* 2506 */       List keys = this.dataset.getKeys();
/* 2507 */       double totalValue = DatasetUtilities.calculatePieDatasetTotal(this.dataset);
/*      */       
/*      */ 
/* 2510 */       int passesRequired = state.getPassesRequired();
/* 2511 */       for (int pass = 0; pass < passesRequired; pass++) {
/* 2512 */         double runningTotal = 0.0D;
/* 2513 */         for (int section = 0; section < keys.size(); section++) {
/* 2514 */           Number n = this.dataset.getValue(section);
/* 2515 */           if (n != null) {
/* 2516 */             double value = n.doubleValue();
/* 2517 */             if (value > 0.0D) {
/* 2518 */               runningTotal += value;
/* 2519 */               drawItem(g2, section, explodeArea, state, pass);
/*      */             }
/*      */           }
/*      */         }
/*      */       }
/* 2524 */       if (this.simpleLabels) {
/* 2525 */         drawSimpleLabels(g2, keys, totalValue, plotArea, linkArea, state);
/*      */       }
/*      */       else
/*      */       {
/* 2529 */         drawLabels(g2, keys, totalValue, plotArea, linkArea, state);
/*      */       }
/*      */     }
/*      */     else
/*      */     {
/* 2534 */       drawNoDataMessage(g2, plotArea);
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
/*      */   protected void drawItem(Graphics2D g2, int section, Rectangle2D dataArea, PiePlotState state, int currentPass)
/*      */   {
/* 2550 */     Number n = this.dataset.getValue(section);
/* 2551 */     if (n == null) {
/* 2552 */       return;
/*      */     }
/* 2554 */     double value = n.doubleValue();
/* 2555 */     double angle1 = 0.0D;
/* 2556 */     double angle2 = 0.0D;
/*      */     
/* 2558 */     if (this.direction == Rotation.CLOCKWISE) {
/* 2559 */       angle1 = state.getLatestAngle();
/* 2560 */       angle2 = angle1 - value / state.getTotal() * 360.0D;
/*      */     }
/* 2562 */     else if (this.direction == Rotation.ANTICLOCKWISE) {
/* 2563 */       angle1 = state.getLatestAngle();
/* 2564 */       angle2 = angle1 + value / state.getTotal() * 360.0D;
/*      */     }
/*      */     else {
/* 2567 */       throw new IllegalStateException("Rotation type not recognised.");
/*      */     }
/*      */     
/* 2570 */     double angle = angle2 - angle1;
/* 2571 */     if (Math.abs(angle) > getMinimumArcAngleToDraw()) {
/* 2572 */       double ep = 0.0D;
/* 2573 */       double mep = getMaximumExplodePercent();
/* 2574 */       if (mep > 0.0D) {
/* 2575 */         ep = getExplodePercent(section) / mep;
/*      */       }
/* 2577 */       Rectangle2D arcBounds = getArcBounds(state.getPieArea(), state.getExplodedPieArea(), angle1, angle, ep);
/*      */       
/* 2579 */       Arc2D.Double arc = new Arc2D.Double(arcBounds, angle1, angle, 2);
/*      */       
/*      */ 
/* 2582 */       if (currentPass == 0) {
/* 2583 */         if (this.shadowPaint != null) {
/* 2584 */           Shape shadowArc = ShapeUtilities.createTranslatedShape(arc, (float)this.shadowXOffset, (float)this.shadowYOffset);
/*      */           
/*      */ 
/* 2587 */           g2.setPaint(this.shadowPaint);
/* 2588 */           g2.fill(shadowArc);
/*      */         }
/*      */       }
/* 2591 */       else if (currentPass == 1) {
/* 2592 */         Comparable key = getSectionKey(section);
/* 2593 */         Paint paint = lookupSectionPaint(key);
/* 2594 */         g2.setPaint(paint);
/* 2595 */         g2.fill(arc);
/*      */         
/* 2597 */         Paint outlinePaint = lookupSectionOutlinePaint(key);
/* 2598 */         Stroke outlineStroke = lookupSectionOutlineStroke(key);
/* 2599 */         if (this.sectionOutlinesVisible) {
/* 2600 */           g2.setPaint(outlinePaint);
/* 2601 */           g2.setStroke(outlineStroke);
/* 2602 */           g2.draw(arc);
/*      */         }
/*      */         
/*      */ 
/*      */ 
/* 2607 */         if (state.getInfo() != null) {
/* 2608 */           EntityCollection entities = state.getEntityCollection();
/* 2609 */           if (entities != null) {
/* 2610 */             String tip = null;
/* 2611 */             if (this.toolTipGenerator != null) {
/* 2612 */               tip = this.toolTipGenerator.generateToolTip(this.dataset, key);
/*      */             }
/*      */             
/* 2615 */             String url = null;
/* 2616 */             if (this.urlGenerator != null) {
/* 2617 */               url = this.urlGenerator.generateURL(this.dataset, key, this.pieIndex);
/*      */             }
/*      */             
/* 2620 */             PieSectionEntity entity = new PieSectionEntity(arc, this.dataset, this.pieIndex, section, key, tip, url);
/*      */             
/*      */ 
/* 2623 */             entities.add(entity);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/* 2628 */     state.setLatestAngle(angle2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawSimpleLabels(Graphics2D g2, List keys, double totalValue, Rectangle2D plotArea, Rectangle2D pieArea, PiePlotState state)
/*      */   {
/* 2647 */     Composite originalComposite = g2.getComposite();
/* 2648 */     g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*      */     
/*      */ 
/* 2651 */     RectangleInsets labelInsets = new RectangleInsets(UnitType.RELATIVE, 0.18D, 0.18D, 0.18D, 0.18D);
/*      */     
/* 2653 */     Rectangle2D labelsArea = labelInsets.createInsetRectangle(pieArea);
/* 2654 */     double runningTotal = 0.0D;
/* 2655 */     Iterator iterator = keys.iterator();
/* 2656 */     while (iterator.hasNext()) {
/* 2657 */       Comparable key = (Comparable)iterator.next();
/* 2658 */       boolean include = true;
/* 2659 */       double v = 0.0D;
/* 2660 */       Number n = getDataset().getValue(key);
/* 2661 */       if (n == null) {
/* 2662 */         include = !getIgnoreNullValues();
/*      */       }
/*      */       else {
/* 2665 */         v = n.doubleValue();
/* 2666 */         include = v > 0.0D;
/*      */       }
/*      */       
/* 2669 */       if (include) {
/* 2670 */         runningTotal += v;
/*      */         
/*      */ 
/* 2673 */         double mid = getStartAngle() + getDirection().getFactor() * ((runningTotal - v / 2.0D) * 360.0D) / totalValue;
/*      */         
/*      */ 
/* 2676 */         Arc2D arc = new Arc2D.Double(labelsArea, getStartAngle(), mid - getStartAngle(), 0);
/*      */         
/* 2678 */         int x = (int)arc.getEndPoint().getX();
/* 2679 */         int y = (int)arc.getEndPoint().getY();
/*      */         
/* 2681 */         PieSectionLabelGenerator labelGenerator = getLabelGenerator();
/* 2682 */         if (labelGenerator != null)
/*      */         {
/*      */ 
/* 2685 */           String label = labelGenerator.generateSectionLabel(this.dataset, key);
/*      */           
/* 2687 */           if (label != null)
/*      */           {
/*      */ 
/* 2690 */             g2.setFont(this.labelFont);
/* 2691 */             FontMetrics fm = g2.getFontMetrics();
/* 2692 */             Rectangle2D bounds = TextUtilities.getTextBounds(label, g2, fm);
/* 2693 */             Rectangle2D out = this.labelPadding.createOutsetRectangle(bounds);
/*      */             
/* 2695 */             Shape bg = ShapeUtilities.createTranslatedShape(out, x - bounds.getCenterX(), y - bounds.getCenterY());
/*      */             
/* 2697 */             if (this.labelShadowPaint != null) {
/* 2698 */               Shape shadow = ShapeUtilities.createTranslatedShape(bg, this.shadowXOffset, this.shadowYOffset);
/*      */               
/* 2700 */               g2.setPaint(this.labelShadowPaint);
/* 2701 */               g2.fill(shadow);
/*      */             }
/* 2703 */             if (this.labelBackgroundPaint != null) {
/* 2704 */               g2.setPaint(this.labelBackgroundPaint);
/* 2705 */               g2.fill(bg);
/*      */             }
/* 2707 */             if ((this.labelOutlinePaint != null) && (this.labelOutlineStroke != null))
/*      */             {
/* 2709 */               g2.setPaint(this.labelOutlinePaint);
/* 2710 */               g2.setStroke(this.labelOutlineStroke);
/* 2711 */               g2.draw(bg);
/*      */             }
/*      */             
/* 2714 */             g2.setPaint(this.labelPaint);
/* 2715 */             g2.setFont(this.labelFont);
/* 2716 */             TextUtilities.drawAlignedString(getLabelGenerator().generateSectionLabel(getDataset(), key), g2, x, y, TextAnchor.CENTER);
/*      */           }
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 2723 */     g2.setComposite(originalComposite);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawLabels(Graphics2D g2, List keys, double totalValue, Rectangle2D plotArea, Rectangle2D linkArea, PiePlotState state)
/*      */   {
/* 2741 */     Composite originalComposite = g2.getComposite();
/* 2742 */     g2.setComposite(AlphaComposite.getInstance(3, 1.0F));
/*      */     
/*      */ 
/*      */ 
/* 2746 */     DefaultKeyedValues leftKeys = new DefaultKeyedValues();
/* 2747 */     DefaultKeyedValues rightKeys = new DefaultKeyedValues();
/*      */     
/* 2749 */     double runningTotal = 0.0D;
/* 2750 */     Iterator iterator = keys.iterator();
/* 2751 */     while (iterator.hasNext()) {
/* 2752 */       Comparable key = (Comparable)iterator.next();
/* 2753 */       boolean include = true;
/* 2754 */       double v = 0.0D;
/* 2755 */       Number n = this.dataset.getValue(key);
/* 2756 */       if (n == null) {
/* 2757 */         include = !this.ignoreNullValues;
/*      */       }
/*      */       else {
/* 2760 */         v = n.doubleValue();
/* 2761 */         include = v > 0.0D;
/*      */       }
/*      */       
/* 2764 */       if (include) {
/* 2765 */         runningTotal += v;
/*      */         
/*      */ 
/* 2768 */         double mid = this.startAngle + this.direction.getFactor() * ((runningTotal - v / 2.0D) * 360.0D) / totalValue;
/*      */         
/* 2770 */         if (Math.cos(Math.toRadians(mid)) < 0.0D) {
/* 2771 */           leftKeys.addValue(key, new Double(mid));
/*      */         }
/*      */         else {
/* 2774 */           rightKeys.addValue(key, new Double(mid));
/*      */         }
/*      */       }
/*      */     }
/*      */     
/* 2779 */     g2.setFont(getLabelFont());
/*      */     
/*      */ 
/*      */ 
/* 2783 */     double marginX = plotArea.getX() + this.interiorGap * plotArea.getWidth();
/*      */     
/* 2785 */     double gap = plotArea.getWidth() * this.labelGap;
/* 2786 */     double ww = linkArea.getX() - gap - marginX;
/* 2787 */     float labelWidth = (float)this.labelPadding.trimWidth(ww);
/*      */     
/*      */ 
/* 2790 */     if (this.labelGenerator != null) {
/* 2791 */       drawLeftLabels(leftKeys, g2, plotArea, linkArea, labelWidth, state);
/*      */       
/* 2793 */       drawRightLabels(rightKeys, g2, plotArea, linkArea, labelWidth, state);
/*      */     }
/*      */     
/* 2796 */     g2.setComposite(originalComposite);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawLeftLabels(KeyedValues leftKeys, Graphics2D g2, Rectangle2D plotArea, Rectangle2D linkArea, float maxLabelWidth, PiePlotState state)
/*      */   {
/* 2816 */     this.labelDistributor.clear();
/* 2817 */     double lGap = plotArea.getWidth() * this.labelGap;
/* 2818 */     double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0D;
/* 2819 */     for (int i = 0; i < leftKeys.getItemCount(); i++) {
/* 2820 */       String label = this.labelGenerator.generateSectionLabel(this.dataset, leftKeys.getKey(i));
/*      */       
/* 2822 */       if (label != null) {
/* 2823 */         TextBlock block = TextUtilities.createTextBlock(label, this.labelFont, this.labelPaint, maxLabelWidth, new G2TextMeasurer(g2));
/*      */         
/*      */ 
/* 2826 */         TextBox labelBox = new TextBox(block);
/* 2827 */         labelBox.setBackgroundPaint(this.labelBackgroundPaint);
/* 2828 */         labelBox.setOutlinePaint(this.labelOutlinePaint);
/* 2829 */         labelBox.setOutlineStroke(this.labelOutlineStroke);
/* 2830 */         labelBox.setShadowPaint(this.labelShadowPaint);
/* 2831 */         labelBox.setInteriorGap(this.labelPadding);
/* 2832 */         double theta = Math.toRadians(leftKeys.getValue(i).doubleValue());
/*      */         
/* 2834 */         double baseY = state.getPieCenterY() - Math.sin(theta) * verticalLinkRadius;
/*      */         
/* 2836 */         double hh = labelBox.getHeight(g2);
/*      */         
/* 2838 */         this.labelDistributor.addPieLabelRecord(new PieLabelRecord(leftKeys.getKey(i), theta, baseY, labelBox, hh, lGap / 2.0D + lGap / 2.0D * -Math.cos(theta), 1.0D - getLabelLinkDepth() + getExplodePercent(leftKeys.getKey(i))));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2845 */     double hh = plotArea.getHeight();
/* 2846 */     double gap = hh * getInteriorGap();
/* 2847 */     this.labelDistributor.distributeLabels(plotArea.getMinY() + gap, hh - 2.0D * gap);
/*      */     
/* 2849 */     for (int i = 0; i < this.labelDistributor.getItemCount(); i++) {
/* 2850 */       drawLeftLabel(g2, state, this.labelDistributor.getPieLabelRecord(i));
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
/*      */   protected void drawRightLabels(KeyedValues keys, Graphics2D g2, Rectangle2D plotArea, Rectangle2D linkArea, float maxLabelWidth, PiePlotState state)
/*      */   {
/* 2870 */     this.labelDistributor.clear();
/* 2871 */     double lGap = plotArea.getWidth() * this.labelGap;
/* 2872 */     double verticalLinkRadius = state.getLinkArea().getHeight() / 2.0D;
/*      */     
/* 2874 */     for (int i = 0; i < keys.getItemCount(); i++) {
/* 2875 */       String label = this.labelGenerator.generateSectionLabel(this.dataset, keys.getKey(i));
/*      */       
/*      */ 
/* 2878 */       if (label != null) {
/* 2879 */         TextBlock block = TextUtilities.createTextBlock(label, this.labelFont, this.labelPaint, maxLabelWidth, new G2TextMeasurer(g2));
/*      */         
/*      */ 
/* 2882 */         TextBox labelBox = new TextBox(block);
/* 2883 */         labelBox.setBackgroundPaint(this.labelBackgroundPaint);
/* 2884 */         labelBox.setOutlinePaint(this.labelOutlinePaint);
/* 2885 */         labelBox.setOutlineStroke(this.labelOutlineStroke);
/* 2886 */         labelBox.setShadowPaint(this.labelShadowPaint);
/* 2887 */         labelBox.setInteriorGap(this.labelPadding);
/* 2888 */         double theta = Math.toRadians(keys.getValue(i).doubleValue());
/* 2889 */         double baseY = state.getPieCenterY() - Math.sin(theta) * verticalLinkRadius;
/*      */         
/* 2891 */         double hh = labelBox.getHeight(g2);
/* 2892 */         this.labelDistributor.addPieLabelRecord(new PieLabelRecord(keys.getKey(i), theta, baseY, labelBox, hh, lGap / 2.0D + lGap / 2.0D * Math.cos(theta), 1.0D - getLabelLinkDepth() + getExplodePercent(keys.getKey(i))));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 2899 */     double hh = plotArea.getHeight();
/* 2900 */     double gap = hh * getInteriorGap();
/* 2901 */     this.labelDistributor.distributeLabels(plotArea.getMinY() + gap, hh - 2.0D * gap);
/*      */     
/* 2903 */     for (int i = 0; i < this.labelDistributor.getItemCount(); i++) {
/* 2904 */       drawRightLabel(g2, state, this.labelDistributor.getPieLabelRecord(i));
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
/*      */   public LegendItemCollection getLegendItems()
/*      */   {
/* 2917 */     LegendItemCollection result = new LegendItemCollection();
/* 2918 */     if (this.dataset == null) {
/* 2919 */       return result;
/*      */     }
/* 2921 */     List keys = this.dataset.getKeys();
/* 2922 */     int section = 0;
/* 2923 */     Shape shape = getLegendItemShape();
/* 2924 */     Iterator iterator = keys.iterator();
/* 2925 */     while (iterator.hasNext()) {
/* 2926 */       Comparable key = (Comparable)iterator.next();
/* 2927 */       Number n = this.dataset.getValue(key);
/* 2928 */       boolean include = true;
/* 2929 */       if (n == null) {
/* 2930 */         include = !this.ignoreNullValues;
/*      */       }
/*      */       else {
/* 2933 */         double v = n.doubleValue();
/* 2934 */         if (v == 0.0D) {
/* 2935 */           include = !this.ignoreZeroValues;
/*      */         }
/*      */         else {
/* 2938 */           include = v > 0.0D;
/*      */         }
/*      */       }
/* 2941 */       if (include) {
/* 2942 */         String label = this.legendLabelGenerator.generateSectionLabel(this.dataset, key);
/*      */         
/* 2944 */         if (label != null) {
/* 2945 */           String description = label;
/* 2946 */           String toolTipText = null;
/* 2947 */           if (this.legendLabelToolTipGenerator != null) {
/* 2948 */             toolTipText = this.legendLabelToolTipGenerator.generateSectionLabel(this.dataset, key);
/*      */           }
/*      */           
/* 2951 */           String urlText = null;
/* 2952 */           if (this.legendLabelURLGenerator != null) {
/* 2953 */             urlText = this.legendLabelURLGenerator.generateURL(this.dataset, key, this.pieIndex);
/*      */           }
/*      */           
/* 2956 */           Paint paint = lookupSectionPaint(key);
/* 2957 */           Paint outlinePaint = lookupSectionOutlinePaint(key);
/* 2958 */           Stroke outlineStroke = lookupSectionOutlineStroke(key);
/* 2959 */           LegendItem item = new LegendItem(label, description, toolTipText, urlText, true, shape, true, paint, true, outlinePaint, outlineStroke, false, new Line2D.Float(), new BasicStroke(), Color.black);
/*      */           
/*      */ 
/*      */ 
/*      */ 
/* 2964 */           item.setDataset(getDataset());
/* 2965 */           item.setSeriesIndex(this.dataset.getIndex(key));
/* 2966 */           item.setSeriesKey(key);
/* 2967 */           result.add(item);
/*      */         }
/* 2969 */         section++;
/*      */       }
/*      */       else {
/* 2972 */         section++;
/*      */       }
/*      */     }
/* 2975 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getPlotType()
/*      */   {
/* 2984 */     return localizationResources.getString("Pie_Plot");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected Rectangle2D getArcBounds(Rectangle2D unexploded, Rectangle2D exploded, double angle, double extent, double explodePercent)
/*      */   {
/* 3006 */     if (explodePercent == 0.0D) {
/* 3007 */       return unexploded;
/*      */     }
/*      */     
/* 3010 */     Arc2D arc1 = new Arc2D.Double(unexploded, angle, extent / 2.0D, 0);
/*      */     
/* 3012 */     Point2D point1 = arc1.getEndPoint();
/* 3013 */     Arc2D.Double arc2 = new Arc2D.Double(exploded, angle, extent / 2.0D, 0);
/*      */     
/* 3015 */     Point2D point2 = arc2.getEndPoint();
/* 3016 */     double deltaX = (point1.getX() - point2.getX()) * explodePercent;
/* 3017 */     double deltaY = (point1.getY() - point2.getY()) * explodePercent;
/* 3018 */     return new Rectangle2D.Double(unexploded.getX() - deltaX, unexploded.getY() - deltaY, unexploded.getWidth(), unexploded.getHeight());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawLeftLabel(Graphics2D g2, PiePlotState state, PieLabelRecord record)
/*      */   {
/* 3034 */     double anchorX = state.getLinkArea().getMinX();
/* 3035 */     double targetX = anchorX - record.getGap();
/* 3036 */     double targetY = record.getAllocatedY();
/*      */     
/* 3038 */     if (this.labelLinksVisible) {
/* 3039 */       double theta = record.getAngle();
/* 3040 */       double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius() * record.getLinkPercent();
/*      */       
/* 3042 */       double linkY = state.getPieCenterY() - Math.sin(theta) * state.getPieHRadius() * record.getLinkPercent();
/*      */       
/* 3044 */       double elbowX = state.getPieCenterX() + Math.cos(theta) * state.getLinkArea().getWidth() / 2.0D;
/*      */       
/* 3046 */       double elbowY = state.getPieCenterY() - Math.sin(theta) * state.getLinkArea().getHeight() / 2.0D;
/*      */       
/* 3048 */       double anchorY = elbowY;
/* 3049 */       g2.setPaint(this.labelLinkPaint);
/* 3050 */       g2.setStroke(this.labelLinkStroke);
/* 3051 */       PieLabelLinkStyle style = getLabelLinkStyle();
/* 3052 */       if (style.equals(PieLabelLinkStyle.STANDARD)) {
/* 3053 */         g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
/* 3054 */         g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
/* 3055 */         g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
/*      */       }
/* 3057 */       else if (style.equals(PieLabelLinkStyle.QUAD_CURVE)) {
/* 3058 */         QuadCurve2D q = new QuadCurve2D.Float();
/* 3059 */         q.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY);
/* 3060 */         g2.draw(q);
/* 3061 */         g2.draw(new Line2D.Double(elbowX, elbowY, linkX, linkY));
/*      */       }
/* 3063 */       else if (style.equals(PieLabelLinkStyle.CUBIC_CURVE)) {
/* 3064 */         CubicCurve2D c = new CubicCurve2D.Float();
/* 3065 */         c.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY, linkX, linkY);
/*      */         
/* 3067 */         g2.draw(c);
/*      */       }
/*      */     }
/* 3070 */     TextBox tb = record.getLabel();
/* 3071 */     tb.draw(g2, (float)targetX, (float)targetY, RectangleAnchor.RIGHT);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void drawRightLabel(Graphics2D g2, PiePlotState state, PieLabelRecord record)
/*      */   {
/* 3085 */     double anchorX = state.getLinkArea().getMaxX();
/* 3086 */     double targetX = anchorX + record.getGap();
/* 3087 */     double targetY = record.getAllocatedY();
/*      */     
/* 3089 */     if (this.labelLinksVisible) {
/* 3090 */       double theta = record.getAngle();
/* 3091 */       double linkX = state.getPieCenterX() + Math.cos(theta) * state.getPieWRadius() * record.getLinkPercent();
/*      */       
/* 3093 */       double linkY = state.getPieCenterY() - Math.sin(theta) * state.getPieHRadius() * record.getLinkPercent();
/*      */       
/* 3095 */       double elbowX = state.getPieCenterX() + Math.cos(theta) * state.getLinkArea().getWidth() / 2.0D;
/*      */       
/* 3097 */       double elbowY = state.getPieCenterY() - Math.sin(theta) * state.getLinkArea().getHeight() / 2.0D;
/*      */       
/* 3099 */       double anchorY = elbowY;
/* 3100 */       g2.setPaint(this.labelLinkPaint);
/* 3101 */       g2.setStroke(this.labelLinkStroke);
/* 3102 */       PieLabelLinkStyle style = getLabelLinkStyle();
/* 3103 */       if (style.equals(PieLabelLinkStyle.STANDARD)) {
/* 3104 */         g2.draw(new Line2D.Double(linkX, linkY, elbowX, elbowY));
/* 3105 */         g2.draw(new Line2D.Double(anchorX, anchorY, elbowX, elbowY));
/* 3106 */         g2.draw(new Line2D.Double(anchorX, anchorY, targetX, targetY));
/*      */       }
/* 3108 */       else if (style.equals(PieLabelLinkStyle.QUAD_CURVE)) {
/* 3109 */         QuadCurve2D q = new QuadCurve2D.Float();
/* 3110 */         q.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY);
/* 3111 */         g2.draw(q);
/* 3112 */         g2.draw(new Line2D.Double(elbowX, elbowY, linkX, linkY));
/*      */       }
/* 3114 */       else if (style.equals(PieLabelLinkStyle.CUBIC_CURVE)) {
/* 3115 */         CubicCurve2D c = new CubicCurve2D.Float();
/* 3116 */         c.setCurve(targetX, targetY, anchorX, anchorY, elbowX, elbowY, linkX, linkY);
/*      */         
/* 3118 */         g2.draw(c);
/*      */       }
/*      */     }
/*      */     
/* 3122 */     TextBox tb = record.getLabel();
/* 3123 */     tb.draw(g2, (float)targetX, (float)targetY, RectangleAnchor.LEFT);
/*      */   }
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
/* 3136 */     if (obj == this) {
/* 3137 */       return true;
/*      */     }
/* 3139 */     if (!(obj instanceof PiePlot)) {
/* 3140 */       return false;
/*      */     }
/* 3142 */     if (!super.equals(obj)) {
/* 3143 */       return false;
/*      */     }
/* 3145 */     PiePlot that = (PiePlot)obj;
/* 3146 */     if (this.pieIndex != that.pieIndex) {
/* 3147 */       return false;
/*      */     }
/* 3149 */     if (this.interiorGap != that.interiorGap) {
/* 3150 */       return false;
/*      */     }
/* 3152 */     if (this.circular != that.circular) {
/* 3153 */       return false;
/*      */     }
/* 3155 */     if (this.startAngle != that.startAngle) {
/* 3156 */       return false;
/*      */     }
/* 3158 */     if (this.direction != that.direction) {
/* 3159 */       return false;
/*      */     }
/* 3161 */     if (this.ignoreZeroValues != that.ignoreZeroValues) {
/* 3162 */       return false;
/*      */     }
/* 3164 */     if (this.ignoreNullValues != that.ignoreNullValues) {
/* 3165 */       return false;
/*      */     }
/* 3167 */     if (!PaintUtilities.equal(this.sectionPaint, that.sectionPaint)) {
/* 3168 */       return false;
/*      */     }
/* 3170 */     if (!ObjectUtilities.equal(this.sectionPaintMap, that.sectionPaintMap))
/*      */     {
/* 3172 */       return false;
/*      */     }
/* 3174 */     if (!PaintUtilities.equal(this.baseSectionPaint, that.baseSectionPaint))
/*      */     {
/* 3176 */       return false;
/*      */     }
/* 3178 */     if (this.sectionOutlinesVisible != that.sectionOutlinesVisible) {
/* 3179 */       return false;
/*      */     }
/* 3181 */     if (!PaintUtilities.equal(this.sectionOutlinePaint, that.sectionOutlinePaint))
/*      */     {
/* 3183 */       return false;
/*      */     }
/* 3185 */     if (!ObjectUtilities.equal(this.sectionOutlinePaintMap, that.sectionOutlinePaintMap))
/*      */     {
/* 3187 */       return false;
/*      */     }
/* 3189 */     if (!PaintUtilities.equal(this.baseSectionOutlinePaint, that.baseSectionOutlinePaint))
/*      */     {
/*      */ 
/* 3192 */       return false;
/*      */     }
/* 3194 */     if (!ObjectUtilities.equal(this.sectionOutlineStroke, that.sectionOutlineStroke))
/*      */     {
/* 3196 */       return false;
/*      */     }
/* 3198 */     if (!ObjectUtilities.equal(this.sectionOutlineStrokeMap, that.sectionOutlineStrokeMap))
/*      */     {
/* 3200 */       return false;
/*      */     }
/* 3202 */     if (!ObjectUtilities.equal(this.baseSectionOutlineStroke, that.baseSectionOutlineStroke))
/*      */     {
/*      */ 
/* 3205 */       return false;
/*      */     }
/* 3207 */     if (!PaintUtilities.equal(this.shadowPaint, that.shadowPaint)) {
/* 3208 */       return false;
/*      */     }
/* 3210 */     if (this.shadowXOffset != that.shadowXOffset) {
/* 3211 */       return false;
/*      */     }
/* 3213 */     if (this.shadowYOffset != that.shadowYOffset) {
/* 3214 */       return false;
/*      */     }
/* 3216 */     if (!ObjectUtilities.equal(this.explodePercentages, that.explodePercentages))
/*      */     {
/* 3218 */       return false;
/*      */     }
/* 3220 */     if (!ObjectUtilities.equal(this.labelGenerator, that.labelGenerator))
/*      */     {
/* 3222 */       return false;
/*      */     }
/* 3224 */     if (!ObjectUtilities.equal(this.labelFont, that.labelFont)) {
/* 3225 */       return false;
/*      */     }
/* 3227 */     if (!PaintUtilities.equal(this.labelPaint, that.labelPaint)) {
/* 3228 */       return false;
/*      */     }
/* 3230 */     if (!PaintUtilities.equal(this.labelBackgroundPaint, that.labelBackgroundPaint))
/*      */     {
/* 3232 */       return false;
/*      */     }
/* 3234 */     if (!PaintUtilities.equal(this.labelOutlinePaint, that.labelOutlinePaint))
/*      */     {
/* 3236 */       return false;
/*      */     }
/* 3238 */     if (!ObjectUtilities.equal(this.labelOutlineStroke, that.labelOutlineStroke))
/*      */     {
/* 3240 */       return false;
/*      */     }
/* 3242 */     if (!PaintUtilities.equal(this.labelShadowPaint, that.labelShadowPaint))
/*      */     {
/* 3244 */       return false;
/*      */     }
/* 3246 */     if (this.simpleLabels != that.simpleLabels) {
/* 3247 */       return false;
/*      */     }
/* 3249 */     if (!this.simpleLabelOffset.equals(that.simpleLabelOffset)) {
/* 3250 */       return false;
/*      */     }
/* 3252 */     if (!this.labelPadding.equals(that.labelPadding)) {
/* 3253 */       return false;
/*      */     }
/* 3255 */     if (this.maximumLabelWidth != that.maximumLabelWidth) {
/* 3256 */       return false;
/*      */     }
/* 3258 */     if (this.labelGap != that.labelGap) {
/* 3259 */       return false;
/*      */     }
/* 3261 */     if (this.labelLinkMargin != that.labelLinkMargin) {
/* 3262 */       return false;
/*      */     }
/* 3264 */     if (this.labelLinksVisible != that.labelLinksVisible) {
/* 3265 */       return false;
/*      */     }
/* 3267 */     if (!this.labelLinkStyle.equals(that.labelLinkStyle)) {
/* 3268 */       return false;
/*      */     }
/* 3270 */     if (!PaintUtilities.equal(this.labelLinkPaint, that.labelLinkPaint)) {
/* 3271 */       return false;
/*      */     }
/* 3273 */     if (!ObjectUtilities.equal(this.labelLinkStroke, that.labelLinkStroke))
/*      */     {
/* 3275 */       return false;
/*      */     }
/* 3277 */     if (!ObjectUtilities.equal(this.toolTipGenerator, that.toolTipGenerator))
/*      */     {
/* 3279 */       return false;
/*      */     }
/* 3281 */     if (!ObjectUtilities.equal(this.urlGenerator, that.urlGenerator)) {
/* 3282 */       return false;
/*      */     }
/* 3284 */     if (this.minimumArcAngleToDraw != that.minimumArcAngleToDraw) {
/* 3285 */       return false;
/*      */     }
/* 3287 */     if (!ShapeUtilities.equal(this.legendItemShape, that.legendItemShape)) {
/* 3288 */       return false;
/*      */     }
/* 3290 */     if (!ObjectUtilities.equal(this.legendLabelGenerator, that.legendLabelGenerator))
/*      */     {
/* 3292 */       return false;
/*      */     }
/* 3294 */     if (!ObjectUtilities.equal(this.legendLabelToolTipGenerator, that.legendLabelToolTipGenerator))
/*      */     {
/* 3296 */       return false;
/*      */     }
/* 3298 */     if (!ObjectUtilities.equal(this.legendLabelURLGenerator, that.legendLabelURLGenerator))
/*      */     {
/* 3300 */       return false;
/*      */     }
/* 3302 */     if (this.autoPopulateSectionPaint != that.autoPopulateSectionPaint) {
/* 3303 */       return false;
/*      */     }
/* 3305 */     if (this.autoPopulateSectionOutlinePaint != that.autoPopulateSectionOutlinePaint)
/*      */     {
/* 3307 */       return false;
/*      */     }
/* 3309 */     if (this.autoPopulateSectionOutlineStroke != that.autoPopulateSectionOutlineStroke)
/*      */     {
/* 3311 */       return false;
/*      */     }
/*      */     
/* 3314 */     return true;
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
/* 3326 */     PiePlot clone = (PiePlot)super.clone();
/* 3327 */     if (clone.dataset != null) {
/* 3328 */       clone.dataset.addChangeListener(clone);
/*      */     }
/* 3330 */     if ((this.urlGenerator instanceof PublicCloneable)) {
/* 3331 */       clone.urlGenerator = ((PieURLGenerator)ObjectUtilities.clone(this.urlGenerator));
/*      */     }
/*      */     
/* 3334 */     clone.legendItemShape = ShapeUtilities.clone(this.legendItemShape);
/* 3335 */     if (this.legendLabelGenerator != null) {
/* 3336 */       clone.legendLabelGenerator = ((PieSectionLabelGenerator)ObjectUtilities.clone(this.legendLabelGenerator));
/*      */     }
/*      */     
/* 3339 */     if (this.legendLabelToolTipGenerator != null) {
/* 3340 */       clone.legendLabelToolTipGenerator = ((PieSectionLabelGenerator)ObjectUtilities.clone(this.legendLabelToolTipGenerator));
/*      */     }
/*      */     
/* 3343 */     if ((this.legendLabelURLGenerator instanceof PublicCloneable)) {
/* 3344 */       clone.legendLabelURLGenerator = ((PieURLGenerator)ObjectUtilities.clone(this.legendLabelURLGenerator));
/*      */     }
/*      */     
/* 3347 */     return clone;
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
/* 3358 */     stream.defaultWriteObject();
/* 3359 */     SerialUtilities.writePaint(this.sectionPaint, stream);
/* 3360 */     SerialUtilities.writePaint(this.baseSectionPaint, stream);
/* 3361 */     SerialUtilities.writePaint(this.sectionOutlinePaint, stream);
/* 3362 */     SerialUtilities.writePaint(this.baseSectionOutlinePaint, stream);
/* 3363 */     SerialUtilities.writeStroke(this.sectionOutlineStroke, stream);
/* 3364 */     SerialUtilities.writeStroke(this.baseSectionOutlineStroke, stream);
/* 3365 */     SerialUtilities.writePaint(this.shadowPaint, stream);
/* 3366 */     SerialUtilities.writePaint(this.labelPaint, stream);
/* 3367 */     SerialUtilities.writePaint(this.labelBackgroundPaint, stream);
/* 3368 */     SerialUtilities.writePaint(this.labelOutlinePaint, stream);
/* 3369 */     SerialUtilities.writeStroke(this.labelOutlineStroke, stream);
/* 3370 */     SerialUtilities.writePaint(this.labelShadowPaint, stream);
/* 3371 */     SerialUtilities.writePaint(this.labelLinkPaint, stream);
/* 3372 */     SerialUtilities.writeStroke(this.labelLinkStroke, stream);
/* 3373 */     SerialUtilities.writeShape(this.legendItemShape, stream);
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
/* 3386 */     stream.defaultReadObject();
/* 3387 */     this.sectionPaint = SerialUtilities.readPaint(stream);
/* 3388 */     this.baseSectionPaint = SerialUtilities.readPaint(stream);
/* 3389 */     this.sectionOutlinePaint = SerialUtilities.readPaint(stream);
/* 3390 */     this.baseSectionOutlinePaint = SerialUtilities.readPaint(stream);
/* 3391 */     this.sectionOutlineStroke = SerialUtilities.readStroke(stream);
/* 3392 */     this.baseSectionOutlineStroke = SerialUtilities.readStroke(stream);
/* 3393 */     this.shadowPaint = SerialUtilities.readPaint(stream);
/* 3394 */     this.labelPaint = SerialUtilities.readPaint(stream);
/* 3395 */     this.labelBackgroundPaint = SerialUtilities.readPaint(stream);
/* 3396 */     this.labelOutlinePaint = SerialUtilities.readPaint(stream);
/* 3397 */     this.labelOutlineStroke = SerialUtilities.readStroke(stream);
/* 3398 */     this.labelShadowPaint = SerialUtilities.readPaint(stream);
/* 3399 */     this.labelLinkPaint = SerialUtilities.readPaint(stream);
/* 3400 */     this.labelLinkStroke = SerialUtilities.readStroke(stream);
/* 3401 */     this.legendItemShape = SerialUtilities.readShape(stream);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */   public Paint getSectionPaint(int section)
/*      */   {
/* 3443 */     Comparable key = getSectionKey(section);
/* 3444 */     return getSectionPaint(key);
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
/*      */   public void setSectionPaint(int section, Paint paint)
/*      */   {
/* 3457 */     Comparable key = getSectionKey(section);
/* 3458 */     setSectionPaint(key, paint);
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
/*      */   public Paint getSectionOutlinePaint()
/*      */   {
/* 3473 */     return this.sectionOutlinePaint;
/*      */   }
/*      */   
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
/*      */   public void setSectionOutlinePaint(Paint paint)
/*      */   {
/* 3490 */     this.sectionOutlinePaint = paint;
/* 3491 */     fireChangeEvent();
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
/*      */   public Paint getSectionOutlinePaint(int section)
/*      */   {
/* 3504 */     Comparable key = getSectionKey(section);
/* 3505 */     return getSectionOutlinePaint(key);
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
/*      */   public void setSectionOutlinePaint(int section, Paint paint)
/*      */   {
/* 3519 */     Comparable key = getSectionKey(section);
/* 3520 */     setSectionOutlinePaint(key, paint);
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
/*      */   public Stroke getSectionOutlineStroke()
/*      */   {
/* 3535 */     return this.sectionOutlineStroke;
/*      */   }
/*      */   
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
/*      */   public void setSectionOutlineStroke(Stroke stroke)
/*      */   {
/* 3552 */     this.sectionOutlineStroke = stroke;
/* 3553 */     fireChangeEvent();
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
/*      */   public Stroke getSectionOutlineStroke(int section)
/*      */   {
/* 3566 */     Comparable key = getSectionKey(section);
/* 3567 */     return getSectionOutlineStroke(key);
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
/*      */   public void setSectionOutlineStroke(int section, Stroke stroke)
/*      */   {
/* 3581 */     Comparable key = getSectionKey(section);
/* 3582 */     setSectionOutlineStroke(key, stroke);
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
/*      */   public double getExplodePercent(int section)
/*      */   {
/* 3595 */     Comparable key = getSectionKey(section);
/* 3596 */     return getExplodePercent(key);
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
/*      */   public void setExplodePercent(int section, double percent)
/*      */   {
/* 3609 */     Comparable key = getSectionKey(section);
/* 3610 */     setExplodePercent(key, percent);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/PiePlot.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */