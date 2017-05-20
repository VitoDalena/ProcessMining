/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.BasicStroke;
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Stroke;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.annotations.XYAnnotation;
/*      */ import org.jfree.chart.annotations.XYTextAnnotation;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.PeriodAxis;
/*      */ import org.jfree.chart.axis.PeriodAxisLabelInfo;
/*      */ import org.jfree.chart.axis.SubCategoryAxis;
/*      */ import org.jfree.chart.axis.SymbolAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.block.Block;
/*      */ import org.jfree.chart.block.BlockContainer;
/*      */ import org.jfree.chart.block.LabelBlock;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.CombinedDomainCategoryPlot;
/*      */ import org.jfree.chart.plot.CombinedDomainXYPlot;
/*      */ import org.jfree.chart.plot.CombinedRangeCategoryPlot;
/*      */ import org.jfree.chart.plot.CombinedRangeXYPlot;
/*      */ import org.jfree.chart.plot.DefaultDrawingSupplier;
/*      */ import org.jfree.chart.plot.DrawingSupplier;
/*      */ import org.jfree.chart.plot.FastScatterPlot;
/*      */ import org.jfree.chart.plot.MeterPlot;
/*      */ import org.jfree.chart.plot.MultiplePiePlot;
/*      */ import org.jfree.chart.plot.PieLabelLinkStyle;
/*      */ import org.jfree.chart.plot.PiePlot;
/*      */ import org.jfree.chart.plot.Plot;
/*      */ import org.jfree.chart.plot.PolarPlot;
/*      */ import org.jfree.chart.plot.SpiderWebPlot;
/*      */ import org.jfree.chart.plot.ThermometerPlot;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.renderer.AbstractRenderer;
/*      */ import org.jfree.chart.renderer.category.BarPainter;
/*      */ import org.jfree.chart.renderer.category.BarRenderer;
/*      */ import org.jfree.chart.renderer.category.BarRenderer3D;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*      */ import org.jfree.chart.renderer.category.GradientBarPainter;
/*      */ import org.jfree.chart.renderer.category.LineRenderer3D;
/*      */ import org.jfree.chart.renderer.category.MinMaxCategoryRenderer;
/*      */ import org.jfree.chart.renderer.category.StatisticalBarRenderer;
/*      */ import org.jfree.chart.renderer.xy.GradientXYBarPainter;
/*      */ import org.jfree.chart.renderer.xy.XYBarPainter;
/*      */ import org.jfree.chart.renderer.xy.XYBarRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*      */ import org.jfree.chart.title.CompositeTitle;
/*      */ import org.jfree.chart.title.LegendTitle;
/*      */ import org.jfree.chart.title.PaintScaleLegend;
/*      */ import org.jfree.chart.title.TextTitle;
/*      */ import org.jfree.chart.title.Title;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleInsets;
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
/*      */ public class StandardChartTheme
/*      */   implements ChartTheme, Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private String name;
/*      */   private Font extraLargeFont;
/*      */   private Font largeFont;
/*      */   private Font regularFont;
/*      */   private Font smallFont;
/*      */   private transient Paint titlePaint;
/*      */   private transient Paint subtitlePaint;
/*      */   private transient Paint chartBackgroundPaint;
/*      */   private transient Paint legendBackgroundPaint;
/*      */   private transient Paint legendItemPaint;
/*      */   private DrawingSupplier drawingSupplier;
/*      */   private transient Paint plotBackgroundPaint;
/*      */   private transient Paint plotOutlinePaint;
/*      */   private PieLabelLinkStyle labelLinkStyle;
/*      */   private transient Paint labelLinkPaint;
/*      */   private transient Paint domainGridlinePaint;
/*      */   private transient Paint rangeGridlinePaint;
/*      */   private transient Paint baselinePaint;
/*      */   private transient Paint crosshairPaint;
/*      */   private RectangleInsets axisOffset;
/*      */   private transient Paint axisLabelPaint;
/*      */   private transient Paint tickLabelPaint;
/*      */   private transient Paint itemLabelPaint;
/*      */   private boolean shadowVisible;
/*      */   private transient Paint shadowPaint;
/*      */   private BarPainter barPainter;
/*      */   private XYBarPainter xyBarPainter;
/*      */   private transient Paint thermometerPaint;
/*      */   private transient Paint wallPaint;
/*      */   private transient Paint errorIndicatorPaint;
/*  227 */   private transient Paint gridBandPaint = SymbolAxis.DEFAULT_GRID_BAND_PAINT;
/*      */   
/*      */ 
/*  230 */   private transient Paint gridBandAlternatePaint = SymbolAxis.DEFAULT_GRID_BAND_ALTERNATE_PAINT;
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ChartTheme createJFreeTheme()
/*      */   {
/*  239 */     return new StandardChartTheme("JFree");
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ChartTheme createDarknessTheme()
/*      */   {
/*  249 */     StandardChartTheme theme = new StandardChartTheme("Darkness");
/*  250 */     theme.titlePaint = Color.white;
/*  251 */     theme.subtitlePaint = Color.white;
/*  252 */     theme.legendBackgroundPaint = Color.black;
/*  253 */     theme.legendItemPaint = Color.white;
/*  254 */     theme.chartBackgroundPaint = Color.black;
/*  255 */     theme.plotBackgroundPaint = Color.black;
/*  256 */     theme.plotOutlinePaint = Color.yellow;
/*  257 */     theme.baselinePaint = Color.white;
/*  258 */     theme.crosshairPaint = Color.red;
/*  259 */     theme.labelLinkPaint = Color.lightGray;
/*  260 */     theme.tickLabelPaint = Color.white;
/*  261 */     theme.axisLabelPaint = Color.white;
/*  262 */     theme.shadowPaint = Color.darkGray;
/*  263 */     theme.itemLabelPaint = Color.white;
/*  264 */     theme.drawingSupplier = new DefaultDrawingSupplier(new Paint[] { Color.decode("0xFFFF00"), Color.decode("0x0036CC"), Color.decode("0xFF0000"), Color.decode("0xFFFF7F"), Color.decode("0x6681CC"), Color.decode("0xFF7F7F"), Color.decode("0xFFFFBF"), Color.decode("0x99A6CC"), Color.decode("0xFFBFBF"), Color.decode("0xA9A938"), Color.decode("0x2D4587") }, new Paint[] { Color.decode("0xFFFF00"), Color.decode("0x0036CC") }, new Stroke[] { new BasicStroke(2.0F) }, new Stroke[] { new BasicStroke(0.5F) }, DefaultDrawingSupplier.DEFAULT_SHAPE_SEQUENCE);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  276 */     theme.wallPaint = Color.darkGray;
/*  277 */     theme.errorIndicatorPaint = Color.lightGray;
/*  278 */     theme.gridBandPaint = new Color(255, 255, 255, 20);
/*  279 */     theme.gridBandAlternatePaint = new Color(255, 255, 255, 40);
/*  280 */     return theme;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ChartTheme createLegacyTheme()
/*      */   {
/*  290 */     StandardChartTheme theme = new StandardChartTheme("Legacy")
/*      */     {
/*      */       public void apply(JFreeChart chart) {}
/*      */ 
/*  294 */     };
/*  295 */     return theme;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public StandardChartTheme(String name)
/*      */   {
/*  304 */     if (name == null) {
/*  305 */       throw new IllegalArgumentException("Null 'name' argument.");
/*      */     }
/*  307 */     this.name = name;
/*  308 */     this.extraLargeFont = new Font("Tahoma", 1, 20);
/*  309 */     this.largeFont = new Font("Tahoma", 1, 14);
/*  310 */     this.regularFont = new Font("Tahoma", 0, 12);
/*  311 */     this.smallFont = new Font("Tahoma", 0, 10);
/*  312 */     this.titlePaint = Color.black;
/*  313 */     this.subtitlePaint = Color.black;
/*  314 */     this.legendBackgroundPaint = Color.white;
/*  315 */     this.legendItemPaint = Color.darkGray;
/*  316 */     this.chartBackgroundPaint = Color.white;
/*  317 */     this.drawingSupplier = new DefaultDrawingSupplier();
/*  318 */     this.plotBackgroundPaint = Color.lightGray;
/*  319 */     this.plotOutlinePaint = Color.black;
/*  320 */     this.labelLinkPaint = Color.black;
/*  321 */     this.labelLinkStyle = PieLabelLinkStyle.CUBIC_CURVE;
/*  322 */     this.axisOffset = new RectangleInsets(4.0D, 4.0D, 4.0D, 4.0D);
/*  323 */     this.domainGridlinePaint = Color.white;
/*  324 */     this.rangeGridlinePaint = Color.white;
/*  325 */     this.baselinePaint = Color.black;
/*  326 */     this.crosshairPaint = Color.blue;
/*  327 */     this.axisLabelPaint = Color.darkGray;
/*  328 */     this.tickLabelPaint = Color.darkGray;
/*  329 */     this.barPainter = new GradientBarPainter();
/*  330 */     this.xyBarPainter = new GradientXYBarPainter();
/*  331 */     this.shadowVisible = true;
/*  332 */     this.shadowPaint = Color.gray;
/*  333 */     this.itemLabelPaint = Color.black;
/*  334 */     this.thermometerPaint = Color.white;
/*  335 */     this.wallPaint = BarRenderer3D.DEFAULT_WALL_PAINT;
/*  336 */     this.errorIndicatorPaint = Color.black;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getExtraLargeFont()
/*      */   {
/*  347 */     return this.extraLargeFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setExtraLargeFont(Font font)
/*      */   {
/*  358 */     if (font == null) {
/*  359 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  361 */     this.extraLargeFont = font;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getLargeFont()
/*      */   {
/*  372 */     return this.largeFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLargeFont(Font font)
/*      */   {
/*  383 */     if (font == null) {
/*  384 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  386 */     this.largeFont = font;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getRegularFont()
/*      */   {
/*  397 */     return this.regularFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRegularFont(Font font)
/*      */   {
/*  408 */     if (font == null) {
/*  409 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  411 */     this.regularFont = font;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Font getSmallFont()
/*      */   {
/*  424 */     return this.smallFont;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSmallFont(Font font)
/*      */   {
/*  437 */     if (font == null) {
/*  438 */       throw new IllegalArgumentException("Null 'font' argument.");
/*      */     }
/*  440 */     this.smallFont = font;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getTitlePaint()
/*      */   {
/*  451 */     return this.titlePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTitlePaint(Paint paint)
/*      */   {
/*  462 */     if (paint == null) {
/*  463 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  465 */     this.titlePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getSubtitlePaint()
/*      */   {
/*  476 */     return this.subtitlePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setSubtitlePaint(Paint paint)
/*      */   {
/*  487 */     if (paint == null) {
/*  488 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  490 */     this.subtitlePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getChartBackgroundPaint()
/*      */   {
/*  501 */     return this.chartBackgroundPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setChartBackgroundPaint(Paint paint)
/*      */   {
/*  512 */     if (paint == null) {
/*  513 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  515 */     this.chartBackgroundPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLegendBackgroundPaint()
/*      */   {
/*  526 */     return this.legendBackgroundPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendBackgroundPaint(Paint paint)
/*      */   {
/*  537 */     if (paint == null) {
/*  538 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  540 */     this.legendBackgroundPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLegendItemPaint()
/*      */   {
/*  551 */     return this.legendItemPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendItemPaint(Paint paint)
/*      */   {
/*  562 */     if (paint == null) {
/*  563 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  565 */     this.legendItemPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getPlotBackgroundPaint()
/*      */   {
/*  576 */     return this.plotBackgroundPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlotBackgroundPaint(Paint paint)
/*      */   {
/*  587 */     if (paint == null) {
/*  588 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  590 */     this.plotBackgroundPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getPlotOutlinePaint()
/*      */   {
/*  601 */     return this.plotOutlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPlotOutlinePaint(Paint paint)
/*      */   {
/*  612 */     if (paint == null) {
/*  613 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  615 */     this.plotOutlinePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public PieLabelLinkStyle getLabelLinkStyle()
/*      */   {
/*  626 */     return this.labelLinkStyle;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinkStyle(PieLabelLinkStyle style)
/*      */   {
/*  637 */     if (style == null) {
/*  638 */       throw new IllegalArgumentException("Null 'style' argument.");
/*      */     }
/*  640 */     this.labelLinkStyle = style;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getLabelLinkPaint()
/*      */   {
/*  651 */     return this.labelLinkPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLabelLinkPaint(Paint paint)
/*      */   {
/*  662 */     if (paint == null) {
/*  663 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  665 */     this.labelLinkPaint = paint;
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
/*  676 */     return this.domainGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDomainGridlinePaint(Paint paint)
/*      */   {
/*  687 */     if (paint == null) {
/*  688 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  690 */     this.domainGridlinePaint = paint;
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
/*  701 */     return this.rangeGridlinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setRangeGridlinePaint(Paint paint)
/*      */   {
/*  712 */     if (paint == null) {
/*  713 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  715 */     this.rangeGridlinePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getBaselinePaint()
/*      */   {
/*  726 */     return this.baselinePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBaselinePaint(Paint paint)
/*      */   {
/*  737 */     if (paint == null) {
/*  738 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  740 */     this.baselinePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getCrosshairPaint()
/*      */   {
/*  749 */     return this.crosshairPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setCrosshairPaint(Paint paint)
/*      */   {
/*  758 */     if (paint == null) {
/*  759 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  761 */     this.crosshairPaint = paint;
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
/*  772 */     return this.axisOffset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxisOffset(RectangleInsets offset)
/*      */   {
/*  783 */     if (offset == null) {
/*  784 */       throw new IllegalArgumentException("Null 'offset' argument.");
/*      */     }
/*  786 */     this.axisOffset = offset;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getAxisLabelPaint()
/*      */   {
/*  797 */     return this.axisLabelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setAxisLabelPaint(Paint paint)
/*      */   {
/*  808 */     if (paint == null) {
/*  809 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  811 */     this.axisLabelPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getTickLabelPaint()
/*      */   {
/*  822 */     return this.tickLabelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setTickLabelPaint(Paint paint)
/*      */   {
/*  833 */     if (paint == null) {
/*  834 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  836 */     this.tickLabelPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getItemLabelPaint()
/*      */   {
/*  847 */     return this.itemLabelPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setItemLabelPaint(Paint paint)
/*      */   {
/*  858 */     if (paint == null) {
/*  859 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  861 */     this.itemLabelPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isShadowVisible()
/*      */   {
/*  872 */     return this.shadowVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowVisible(boolean visible)
/*      */   {
/*  883 */     this.shadowVisible = visible;
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
/*  894 */     return this.shadowPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setShadowPaint(Paint paint)
/*      */   {
/*  905 */     if (paint == null) {
/*  906 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  908 */     this.shadowPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public BarPainter getBarPainter()
/*      */   {
/*  919 */     return this.barPainter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setBarPainter(BarPainter painter)
/*      */   {
/*  930 */     if (painter == null) {
/*  931 */       throw new IllegalArgumentException("Null 'painter' argument.");
/*      */     }
/*  933 */     this.barPainter = painter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public XYBarPainter getXYBarPainter()
/*      */   {
/*  944 */     return this.xyBarPainter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setXYBarPainter(XYBarPainter painter)
/*      */   {
/*  955 */     if (painter == null) {
/*  956 */       throw new IllegalArgumentException("Null 'painter' argument.");
/*      */     }
/*  958 */     this.xyBarPainter = painter;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getThermometerPaint()
/*      */   {
/*  969 */     return this.thermometerPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setThermometerPaint(Paint paint)
/*      */   {
/*  980 */     if (paint == null) {
/*  981 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  983 */     this.thermometerPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getWallPaint()
/*      */   {
/*  994 */     return this.wallPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setWallPaint(Paint paint)
/*      */   {
/* 1005 */     if (paint == null) {
/* 1006 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1008 */     this.wallPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getErrorIndicatorPaint()
/*      */   {
/* 1019 */     return this.errorIndicatorPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setErrorIndicatorPaint(Paint paint)
/*      */   {
/* 1030 */     if (paint == null) {
/* 1031 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1033 */     this.errorIndicatorPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getGridBandPaint()
/*      */   {
/* 1044 */     return this.gridBandPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setGridBandPaint(Paint paint)
/*      */   {
/* 1055 */     if (paint == null) {
/* 1056 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1058 */     this.gridBandPaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getGridBandAlternatePaint()
/*      */   {
/* 1069 */     return this.gridBandAlternatePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setGridBandAlternatePaint(Paint paint)
/*      */   {
/* 1080 */     if (paint == null) {
/* 1081 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/* 1083 */     this.gridBandAlternatePaint = paint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public String getName()
/*      */   {
/* 1092 */     return this.name;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public DrawingSupplier getDrawingSupplier()
/*      */   {
/* 1101 */     DrawingSupplier result = null;
/* 1102 */     if ((this.drawingSupplier instanceof PublicCloneable)) {
/* 1103 */       PublicCloneable pc = (PublicCloneable)this.drawingSupplier;
/*      */       try {
/* 1105 */         result = (DrawingSupplier)pc.clone();
/*      */       }
/*      */       catch (CloneNotSupportedException e) {
/* 1108 */         e.printStackTrace();
/*      */       }
/*      */     }
/* 1111 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setDrawingSupplier(DrawingSupplier supplier)
/*      */   {
/* 1122 */     if (supplier == null) {
/* 1123 */       throw new IllegalArgumentException("Null 'supplier' argument.");
/*      */     }
/* 1125 */     this.drawingSupplier = supplier;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void apply(JFreeChart chart)
/*      */   {
/* 1134 */     if (chart == null) {
/* 1135 */       throw new IllegalArgumentException("Null 'chart' argument.");
/*      */     }
/* 1137 */     TextTitle title = chart.getTitle();
/* 1138 */     if (title != null) {
/* 1139 */       title.setFont(this.extraLargeFont);
/* 1140 */       title.setPaint(this.titlePaint);
/*      */     }
/*      */     
/* 1143 */     int subtitleCount = chart.getSubtitleCount();
/* 1144 */     for (int i = 0; i < subtitleCount; i++) {
/* 1145 */       applyToTitle(chart.getSubtitle(i));
/*      */     }
/*      */     
/* 1148 */     chart.setBackgroundPaint(this.chartBackgroundPaint);
/*      */     
/*      */ 
/* 1151 */     Plot plot = chart.getPlot();
/* 1152 */     if (plot != null) {
/* 1153 */       applyToPlot(plot);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToTitle(Title title)
/*      */   {
/* 1163 */     if ((title instanceof TextTitle)) {
/* 1164 */       TextTitle tt = (TextTitle)title;
/* 1165 */       tt.setFont(this.largeFont);
/* 1166 */       tt.setPaint(this.subtitlePaint);
/*      */     }
/* 1168 */     else if ((title instanceof LegendTitle)) {
/* 1169 */       LegendTitle lt = (LegendTitle)title;
/* 1170 */       if (lt.getBackgroundPaint() != null) {
/* 1171 */         lt.setBackgroundPaint(this.legendBackgroundPaint);
/*      */       }
/* 1173 */       lt.setItemFont(this.regularFont);
/* 1174 */       lt.setItemPaint(this.legendItemPaint);
/* 1175 */       if (lt.getWrapper() != null) {
/* 1176 */         applyToBlockContainer(lt.getWrapper());
/*      */       }
/*      */     }
/* 1179 */     else if ((title instanceof PaintScaleLegend)) {
/* 1180 */       PaintScaleLegend psl = (PaintScaleLegend)title;
/* 1181 */       psl.setBackgroundPaint(this.legendBackgroundPaint);
/* 1182 */       ValueAxis axis = psl.getAxis();
/* 1183 */       if (axis != null) {
/* 1184 */         applyToValueAxis(axis);
/*      */       }
/*      */     }
/* 1187 */     else if ((title instanceof CompositeTitle)) {
/* 1188 */       CompositeTitle ct = (CompositeTitle)title;
/* 1189 */       BlockContainer bc = ct.getContainer();
/* 1190 */       List blocks = bc.getBlocks();
/* 1191 */       Iterator iterator = blocks.iterator();
/* 1192 */       while (iterator.hasNext()) {
/* 1193 */         Block b = (Block)iterator.next();
/* 1194 */         if ((b instanceof Title)) {
/* 1195 */           applyToTitle((Title)b);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToBlockContainer(BlockContainer bc)
/*      */   {
/* 1207 */     Iterator iterator = bc.getBlocks().iterator();
/* 1208 */     while (iterator.hasNext()) {
/* 1209 */       Block b = (Block)iterator.next();
/* 1210 */       applyToBlock(b);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToBlock(Block b)
/*      */   {
/* 1220 */     if ((b instanceof Title)) {
/* 1221 */       applyToTitle((Title)b);
/*      */     }
/* 1223 */     else if ((b instanceof LabelBlock)) {
/* 1224 */       LabelBlock lb = (LabelBlock)b;
/* 1225 */       lb.setFont(this.regularFont);
/* 1226 */       lb.setPaint(this.legendItemPaint);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToPlot(Plot plot)
/*      */   {
/* 1236 */     if (plot == null) {
/* 1237 */       throw new IllegalArgumentException("Null 'plot' argument.");
/*      */     }
/* 1239 */     if (plot.getDrawingSupplier() != null) {
/* 1240 */       plot.setDrawingSupplier(getDrawingSupplier());
/*      */     }
/* 1242 */     if (plot.getBackgroundPaint() != null) {
/* 1243 */       plot.setBackgroundPaint(this.plotBackgroundPaint);
/*      */     }
/* 1245 */     plot.setOutlinePaint(this.plotOutlinePaint);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/* 1251 */     if ((plot instanceof PiePlot)) {
/* 1252 */       applyToPiePlot((PiePlot)plot);
/*      */     }
/* 1254 */     else if ((plot instanceof MultiplePiePlot)) {
/* 1255 */       applyToMultiplePiePlot((MultiplePiePlot)plot);
/*      */     }
/* 1257 */     else if ((plot instanceof CategoryPlot)) {
/* 1258 */       applyToCategoryPlot((CategoryPlot)plot);
/*      */     }
/* 1260 */     else if ((plot instanceof XYPlot)) {
/* 1261 */       applyToXYPlot((XYPlot)plot);
/*      */     }
/* 1263 */     else if ((plot instanceof FastScatterPlot)) {
/* 1264 */       applyToFastScatterPlot((FastScatterPlot)plot);
/*      */     }
/* 1266 */     else if ((plot instanceof MeterPlot)) {
/* 1267 */       applyToMeterPlot((MeterPlot)plot);
/*      */     }
/* 1269 */     else if ((plot instanceof ThermometerPlot)) {
/* 1270 */       applyToThermometerPlot((ThermometerPlot)plot);
/*      */     }
/* 1272 */     else if ((plot instanceof SpiderWebPlot)) {
/* 1273 */       applyToSpiderWebPlot((SpiderWebPlot)plot);
/*      */     }
/* 1275 */     else if ((plot instanceof PolarPlot)) {
/* 1276 */       applyToPolarPlot((PolarPlot)plot);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToPiePlot(PiePlot plot)
/*      */   {
/* 1288 */     plot.setLabelLinkPaint(this.labelLinkPaint);
/* 1289 */     plot.setLabelLinkStyle(this.labelLinkStyle);
/* 1290 */     plot.setLabelFont(this.regularFont);
/*      */     
/*      */ 
/*      */ 
/* 1294 */     if (plot.getAutoPopulateSectionPaint()) {
/* 1295 */       plot.clearSectionPaints(false);
/*      */     }
/* 1297 */     if (plot.getAutoPopulateSectionOutlinePaint()) {
/* 1298 */       plot.clearSectionOutlinePaints(false);
/*      */     }
/* 1300 */     if (plot.getAutoPopulateSectionOutlineStroke()) {
/* 1301 */       plot.clearSectionOutlineStrokes(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToMultiplePiePlot(MultiplePiePlot plot)
/*      */   {
/* 1311 */     apply(plot.getPieChart());
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToCategoryPlot(CategoryPlot plot)
/*      */   {
/* 1320 */     plot.setAxisOffset(this.axisOffset);
/* 1321 */     plot.setDomainGridlinePaint(this.domainGridlinePaint);
/* 1322 */     plot.setRangeGridlinePaint(this.rangeGridlinePaint);
/* 1323 */     plot.setRangeZeroBaselinePaint(this.baselinePaint);
/*      */     
/*      */ 
/* 1326 */     int domainAxisCount = plot.getDomainAxisCount();
/* 1327 */     for (int i = 0; i < domainAxisCount; i++) {
/* 1328 */       CategoryAxis axis = plot.getDomainAxis(i);
/* 1329 */       if (axis != null) {
/* 1330 */         applyToCategoryAxis(axis);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1335 */     int rangeAxisCount = plot.getRangeAxisCount();
/* 1336 */     for (int i = 0; i < rangeAxisCount; i++) {
/* 1337 */       ValueAxis axis = plot.getRangeAxis(i);
/* 1338 */       if (axis != null) {
/* 1339 */         applyToValueAxis(axis);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1344 */     int rendererCount = plot.getRendererCount();
/* 1345 */     for (int i = 0; i < rendererCount; i++) {
/* 1346 */       CategoryItemRenderer r = plot.getRenderer(i);
/* 1347 */       if (r != null) {
/* 1348 */         applyToCategoryItemRenderer(r);
/*      */       }
/*      */     }
/*      */     
/* 1352 */     if ((plot instanceof CombinedDomainCategoryPlot)) {
/* 1353 */       CombinedDomainCategoryPlot cp = (CombinedDomainCategoryPlot)plot;
/* 1354 */       Iterator iterator = cp.getSubplots().iterator();
/* 1355 */       while (iterator.hasNext()) {
/* 1356 */         CategoryPlot subplot = (CategoryPlot)iterator.next();
/* 1357 */         if (subplot != null) {
/* 1358 */           applyToPlot(subplot);
/*      */         }
/*      */       }
/*      */     }
/* 1362 */     if ((plot instanceof CombinedRangeCategoryPlot)) {
/* 1363 */       CombinedRangeCategoryPlot cp = (CombinedRangeCategoryPlot)plot;
/* 1364 */       Iterator iterator = cp.getSubplots().iterator();
/* 1365 */       while (iterator.hasNext()) {
/* 1366 */         CategoryPlot subplot = (CategoryPlot)iterator.next();
/* 1367 */         if (subplot != null) {
/* 1368 */           applyToPlot(subplot);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToXYPlot(XYPlot plot)
/*      */   {
/* 1380 */     plot.setAxisOffset(this.axisOffset);
/* 1381 */     plot.setDomainZeroBaselinePaint(this.baselinePaint);
/* 1382 */     plot.setRangeZeroBaselinePaint(this.baselinePaint);
/* 1383 */     plot.setDomainGridlinePaint(this.domainGridlinePaint);
/* 1384 */     plot.setRangeGridlinePaint(this.rangeGridlinePaint);
/* 1385 */     plot.setDomainCrosshairPaint(this.crosshairPaint);
/* 1386 */     plot.setRangeCrosshairPaint(this.crosshairPaint);
/*      */     
/* 1388 */     int domainAxisCount = plot.getDomainAxisCount();
/* 1389 */     for (int i = 0; i < domainAxisCount; i++) {
/* 1390 */       ValueAxis axis = plot.getDomainAxis(i);
/* 1391 */       if (axis != null) {
/* 1392 */         applyToValueAxis(axis);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1397 */     int rangeAxisCount = plot.getRangeAxisCount();
/* 1398 */     for (int i = 0; i < rangeAxisCount; i++) {
/* 1399 */       ValueAxis axis = plot.getRangeAxis(i);
/* 1400 */       if (axis != null) {
/* 1401 */         applyToValueAxis(axis);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1406 */     int rendererCount = plot.getRendererCount();
/* 1407 */     for (int i = 0; i < rendererCount; i++) {
/* 1408 */       XYItemRenderer r = plot.getRenderer(i);
/* 1409 */       if (r != null) {
/* 1410 */         applyToXYItemRenderer(r);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/* 1415 */     Iterator iter = plot.getAnnotations().iterator();
/* 1416 */     while (iter.hasNext()) {
/* 1417 */       XYAnnotation a = (XYAnnotation)iter.next();
/* 1418 */       applyToXYAnnotation(a);
/*      */     }
/*      */     
/* 1421 */     if ((plot instanceof CombinedDomainXYPlot)) {
/* 1422 */       CombinedDomainXYPlot cp = (CombinedDomainXYPlot)plot;
/* 1423 */       Iterator iterator = cp.getSubplots().iterator();
/* 1424 */       while (iterator.hasNext()) {
/* 1425 */         XYPlot subplot = (XYPlot)iterator.next();
/* 1426 */         if (subplot != null) {
/* 1427 */           applyToPlot(subplot);
/*      */         }
/*      */       }
/*      */     }
/* 1431 */     if ((plot instanceof CombinedRangeXYPlot)) {
/* 1432 */       CombinedRangeXYPlot cp = (CombinedRangeXYPlot)plot;
/* 1433 */       Iterator iterator = cp.getSubplots().iterator();
/* 1434 */       while (iterator.hasNext()) {
/* 1435 */         XYPlot subplot = (XYPlot)iterator.next();
/* 1436 */         if (subplot != null) {
/* 1437 */           applyToPlot(subplot);
/*      */         }
/*      */       }
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToFastScatterPlot(FastScatterPlot plot)
/*      */   {
/* 1448 */     plot.setDomainGridlinePaint(this.domainGridlinePaint);
/* 1449 */     plot.setRangeGridlinePaint(this.rangeGridlinePaint);
/* 1450 */     ValueAxis xAxis = plot.getDomainAxis();
/* 1451 */     if (xAxis != null) {
/* 1452 */       applyToValueAxis(xAxis);
/*      */     }
/* 1454 */     ValueAxis yAxis = plot.getRangeAxis();
/* 1455 */     if (yAxis != null) {
/* 1456 */       applyToValueAxis(yAxis);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToPolarPlot(PolarPlot plot)
/*      */   {
/* 1468 */     plot.setAngleLabelFont(this.regularFont);
/* 1469 */     plot.setAngleLabelPaint(this.tickLabelPaint);
/* 1470 */     plot.setAngleGridlinePaint(this.domainGridlinePaint);
/* 1471 */     plot.setRadiusGridlinePaint(this.rangeGridlinePaint);
/* 1472 */     ValueAxis axis = plot.getAxis();
/* 1473 */     if (axis != null) {
/* 1474 */       applyToValueAxis(axis);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToSpiderWebPlot(SpiderWebPlot plot)
/*      */   {
/* 1484 */     plot.setLabelFont(this.regularFont);
/* 1485 */     plot.setLabelPaint(this.axisLabelPaint);
/* 1486 */     plot.setAxisLinePaint(this.axisLabelPaint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToMeterPlot(MeterPlot plot)
/*      */   {
/* 1495 */     plot.setDialBackgroundPaint(this.plotBackgroundPaint);
/* 1496 */     plot.setValueFont(this.largeFont);
/* 1497 */     plot.setValuePaint(this.axisLabelPaint);
/* 1498 */     plot.setDialOutlinePaint(this.plotOutlinePaint);
/* 1499 */     plot.setNeedlePaint(this.thermometerPaint);
/* 1500 */     plot.setTickLabelFont(this.regularFont);
/* 1501 */     plot.setTickLabelPaint(this.tickLabelPaint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToThermometerPlot(ThermometerPlot plot)
/*      */   {
/* 1511 */     plot.setValueFont(this.largeFont);
/* 1512 */     plot.setThermometerPaint(this.thermometerPaint);
/* 1513 */     ValueAxis axis = plot.getRangeAxis();
/* 1514 */     if (axis != null) {
/* 1515 */       applyToValueAxis(axis);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToCategoryAxis(CategoryAxis axis)
/*      */   {
/* 1525 */     axis.setLabelFont(this.largeFont);
/* 1526 */     axis.setLabelPaint(this.axisLabelPaint);
/* 1527 */     axis.setTickLabelFont(this.regularFont);
/* 1528 */     axis.setTickLabelPaint(this.tickLabelPaint);
/* 1529 */     if ((axis instanceof SubCategoryAxis)) {
/* 1530 */       SubCategoryAxis sca = (SubCategoryAxis)axis;
/* 1531 */       sca.setSubLabelFont(this.regularFont);
/* 1532 */       sca.setSubLabelPaint(this.tickLabelPaint);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToValueAxis(ValueAxis axis)
/*      */   {
/* 1542 */     axis.setLabelFont(this.largeFont);
/* 1543 */     axis.setLabelPaint(this.axisLabelPaint);
/* 1544 */     axis.setTickLabelFont(this.regularFont);
/* 1545 */     axis.setTickLabelPaint(this.tickLabelPaint);
/* 1546 */     if ((axis instanceof SymbolAxis)) {
/* 1547 */       applyToSymbolAxis((SymbolAxis)axis);
/*      */     }
/* 1549 */     if ((axis instanceof PeriodAxis)) {
/* 1550 */       applyToPeriodAxis((PeriodAxis)axis);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToSymbolAxis(SymbolAxis axis)
/*      */   {
/* 1560 */     axis.setGridBandPaint(this.gridBandPaint);
/* 1561 */     axis.setGridBandAlternatePaint(this.gridBandAlternatePaint);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToPeriodAxis(PeriodAxis axis)
/*      */   {
/* 1570 */     PeriodAxisLabelInfo[] info = axis.getLabelInfo();
/* 1571 */     for (int i = 0; i < info.length; i++) {
/* 1572 */       PeriodAxisLabelInfo e = info[i];
/* 1573 */       PeriodAxisLabelInfo n = new PeriodAxisLabelInfo(e.getPeriodClass(), e.getDateFormat(), e.getPadding(), this.regularFont, this.tickLabelPaint, e.getDrawDividers(), e.getDividerStroke(), e.getDividerPaint());
/*      */       
/*      */ 
/*      */ 
/* 1577 */       info[i] = n;
/*      */     }
/* 1579 */     axis.setLabelInfo(info);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToAbstractRenderer(AbstractRenderer renderer)
/*      */   {
/* 1588 */     if (renderer.getAutoPopulateSeriesPaint()) {
/* 1589 */       renderer.clearSeriesPaints(false);
/*      */     }
/* 1591 */     if (renderer.getAutoPopulateSeriesStroke()) {
/* 1592 */       renderer.clearSeriesStrokes(false);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToCategoryItemRenderer(CategoryItemRenderer renderer)
/*      */   {
/* 1602 */     if (renderer == null) {
/* 1603 */       throw new IllegalArgumentException("Null 'renderer' argument.");
/*      */     }
/*      */     
/* 1606 */     if ((renderer instanceof AbstractRenderer)) {
/* 1607 */       applyToAbstractRenderer((AbstractRenderer)renderer);
/*      */     }
/*      */     
/* 1610 */     renderer.setBaseItemLabelFont(this.regularFont);
/* 1611 */     renderer.setBaseItemLabelPaint(this.itemLabelPaint);
/*      */     
/*      */ 
/*      */ 
/*      */ 
/* 1616 */     if ((renderer instanceof BarRenderer)) {
/* 1617 */       BarRenderer br = (BarRenderer)renderer;
/* 1618 */       br.setBarPainter(this.barPainter);
/* 1619 */       br.setShadowVisible(this.shadowVisible);
/* 1620 */       br.setShadowPaint(this.shadowPaint);
/*      */     }
/*      */     
/*      */ 
/* 1624 */     if ((renderer instanceof BarRenderer3D)) {
/* 1625 */       BarRenderer3D br3d = (BarRenderer3D)renderer;
/* 1626 */       br3d.setWallPaint(this.wallPaint);
/*      */     }
/*      */     
/*      */ 
/* 1630 */     if ((renderer instanceof LineRenderer3D)) {
/* 1631 */       LineRenderer3D lr3d = (LineRenderer3D)renderer;
/* 1632 */       lr3d.setWallPaint(this.wallPaint);
/*      */     }
/*      */     
/*      */ 
/* 1636 */     if ((renderer instanceof StatisticalBarRenderer)) {
/* 1637 */       StatisticalBarRenderer sbr = (StatisticalBarRenderer)renderer;
/* 1638 */       sbr.setErrorIndicatorPaint(this.errorIndicatorPaint);
/*      */     }
/*      */     
/*      */ 
/* 1642 */     if ((renderer instanceof MinMaxCategoryRenderer)) {
/* 1643 */       MinMaxCategoryRenderer mmcr = (MinMaxCategoryRenderer)renderer;
/* 1644 */       mmcr.setGroupPaint(this.errorIndicatorPaint);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToXYItemRenderer(XYItemRenderer renderer)
/*      */   {
/* 1654 */     if (renderer == null) {
/* 1655 */       throw new IllegalArgumentException("Null 'renderer' argument.");
/*      */     }
/* 1657 */     if ((renderer instanceof AbstractRenderer)) {
/* 1658 */       applyToAbstractRenderer((AbstractRenderer)renderer);
/*      */     }
/* 1660 */     renderer.setBaseItemLabelFont(this.regularFont);
/* 1661 */     renderer.setBaseItemLabelPaint(this.itemLabelPaint);
/* 1662 */     if ((renderer instanceof XYBarRenderer)) {
/* 1663 */       XYBarRenderer br = (XYBarRenderer)renderer;
/* 1664 */       br.setBarPainter(this.xyBarPainter);
/* 1665 */       br.setShadowVisible(this.shadowVisible);
/*      */     }
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   protected void applyToXYAnnotation(XYAnnotation annotation)
/*      */   {
/* 1675 */     if (annotation == null) {
/* 1676 */       throw new IllegalArgumentException("Null 'annotation' argument.");
/*      */     }
/* 1678 */     if ((annotation instanceof XYTextAnnotation)) {
/* 1679 */       XYTextAnnotation xyta = (XYTextAnnotation)annotation;
/* 1680 */       xyta.setFont(this.smallFont);
/* 1681 */       xyta.setPaint(this.itemLabelPaint);
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
/* 1693 */     if (obj == this) {
/* 1694 */       return true;
/*      */     }
/* 1696 */     if (!(obj instanceof StandardChartTheme)) {
/* 1697 */       return false;
/*      */     }
/* 1699 */     StandardChartTheme that = (StandardChartTheme)obj;
/* 1700 */     if (!this.name.equals(that.name)) {
/* 1701 */       return false;
/*      */     }
/* 1703 */     if (!this.extraLargeFont.equals(that.extraLargeFont)) {
/* 1704 */       return false;
/*      */     }
/* 1706 */     if (!this.largeFont.equals(that.largeFont)) {
/* 1707 */       return false;
/*      */     }
/* 1709 */     if (!this.regularFont.equals(that.regularFont)) {
/* 1710 */       return false;
/*      */     }
/* 1712 */     if (!this.smallFont.equals(that.smallFont)) {
/* 1713 */       return false;
/*      */     }
/* 1715 */     if (!PaintUtilities.equal(this.titlePaint, that.titlePaint)) {
/* 1716 */       return false;
/*      */     }
/* 1718 */     if (!PaintUtilities.equal(this.subtitlePaint, that.subtitlePaint)) {
/* 1719 */       return false;
/*      */     }
/* 1721 */     if (!PaintUtilities.equal(this.chartBackgroundPaint, that.chartBackgroundPaint))
/*      */     {
/* 1723 */       return false;
/*      */     }
/* 1725 */     if (!PaintUtilities.equal(this.legendBackgroundPaint, that.legendBackgroundPaint))
/*      */     {
/* 1727 */       return false;
/*      */     }
/* 1729 */     if (!PaintUtilities.equal(this.legendItemPaint, that.legendItemPaint)) {
/* 1730 */       return false;
/*      */     }
/* 1732 */     if (!this.drawingSupplier.equals(that.drawingSupplier)) {
/* 1733 */       return false;
/*      */     }
/* 1735 */     if (!PaintUtilities.equal(this.plotBackgroundPaint, that.plotBackgroundPaint))
/*      */     {
/* 1737 */       return false;
/*      */     }
/* 1739 */     if (!PaintUtilities.equal(this.plotOutlinePaint, that.plotOutlinePaint))
/*      */     {
/* 1741 */       return false;
/*      */     }
/* 1743 */     if (!this.labelLinkStyle.equals(that.labelLinkStyle)) {
/* 1744 */       return false;
/*      */     }
/* 1746 */     if (!PaintUtilities.equal(this.labelLinkPaint, that.labelLinkPaint)) {
/* 1747 */       return false;
/*      */     }
/* 1749 */     if (!PaintUtilities.equal(this.domainGridlinePaint, that.domainGridlinePaint))
/*      */     {
/* 1751 */       return false;
/*      */     }
/* 1753 */     if (!PaintUtilities.equal(this.rangeGridlinePaint, that.rangeGridlinePaint))
/*      */     {
/* 1755 */       return false;
/*      */     }
/* 1757 */     if (!PaintUtilities.equal(this.crosshairPaint, that.crosshairPaint)) {
/* 1758 */       return false;
/*      */     }
/* 1760 */     if (!this.axisOffset.equals(that.axisOffset)) {
/* 1761 */       return false;
/*      */     }
/* 1763 */     if (!PaintUtilities.equal(this.axisLabelPaint, that.axisLabelPaint)) {
/* 1764 */       return false;
/*      */     }
/* 1766 */     if (!PaintUtilities.equal(this.tickLabelPaint, that.tickLabelPaint)) {
/* 1767 */       return false;
/*      */     }
/* 1769 */     if (!PaintUtilities.equal(this.itemLabelPaint, that.itemLabelPaint)) {
/* 1770 */       return false;
/*      */     }
/* 1772 */     if (this.shadowVisible != that.shadowVisible) {
/* 1773 */       return false;
/*      */     }
/* 1775 */     if (!PaintUtilities.equal(this.shadowPaint, that.shadowPaint)) {
/* 1776 */       return false;
/*      */     }
/* 1778 */     if (!this.barPainter.equals(that.barPainter)) {
/* 1779 */       return false;
/*      */     }
/* 1781 */     if (!this.xyBarPainter.equals(that.xyBarPainter)) {
/* 1782 */       return false;
/*      */     }
/* 1784 */     if (!PaintUtilities.equal(this.thermometerPaint, that.thermometerPaint))
/*      */     {
/* 1786 */       return false;
/*      */     }
/* 1788 */     if (!PaintUtilities.equal(this.wallPaint, that.wallPaint)) {
/* 1789 */       return false;
/*      */     }
/* 1791 */     if (!PaintUtilities.equal(this.errorIndicatorPaint, that.errorIndicatorPaint))
/*      */     {
/* 1793 */       return false;
/*      */     }
/* 1795 */     if (!PaintUtilities.equal(this.gridBandPaint, that.gridBandPaint)) {
/* 1796 */       return false;
/*      */     }
/* 1798 */     if (!PaintUtilities.equal(this.gridBandAlternatePaint, that.gridBandAlternatePaint))
/*      */     {
/* 1800 */       return false;
/*      */     }
/* 1802 */     return true;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Object clone()
/*      */     throws CloneNotSupportedException
/*      */   {
/* 1813 */     return super.clone();
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
/* 1824 */     stream.defaultWriteObject();
/* 1825 */     SerialUtilities.writePaint(this.titlePaint, stream);
/* 1826 */     SerialUtilities.writePaint(this.subtitlePaint, stream);
/* 1827 */     SerialUtilities.writePaint(this.chartBackgroundPaint, stream);
/* 1828 */     SerialUtilities.writePaint(this.legendBackgroundPaint, stream);
/* 1829 */     SerialUtilities.writePaint(this.legendItemPaint, stream);
/* 1830 */     SerialUtilities.writePaint(this.plotBackgroundPaint, stream);
/* 1831 */     SerialUtilities.writePaint(this.plotOutlinePaint, stream);
/* 1832 */     SerialUtilities.writePaint(this.labelLinkPaint, stream);
/* 1833 */     SerialUtilities.writePaint(this.baselinePaint, stream);
/* 1834 */     SerialUtilities.writePaint(this.domainGridlinePaint, stream);
/* 1835 */     SerialUtilities.writePaint(this.rangeGridlinePaint, stream);
/* 1836 */     SerialUtilities.writePaint(this.crosshairPaint, stream);
/* 1837 */     SerialUtilities.writePaint(this.axisLabelPaint, stream);
/* 1838 */     SerialUtilities.writePaint(this.tickLabelPaint, stream);
/* 1839 */     SerialUtilities.writePaint(this.itemLabelPaint, stream);
/* 1840 */     SerialUtilities.writePaint(this.shadowPaint, stream);
/* 1841 */     SerialUtilities.writePaint(this.thermometerPaint, stream);
/* 1842 */     SerialUtilities.writePaint(this.wallPaint, stream);
/* 1843 */     SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
/* 1844 */     SerialUtilities.writePaint(this.gridBandPaint, stream);
/* 1845 */     SerialUtilities.writePaint(this.gridBandAlternatePaint, stream);
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
/* 1858 */     stream.defaultReadObject();
/* 1859 */     this.titlePaint = SerialUtilities.readPaint(stream);
/* 1860 */     this.subtitlePaint = SerialUtilities.readPaint(stream);
/* 1861 */     this.chartBackgroundPaint = SerialUtilities.readPaint(stream);
/* 1862 */     this.legendBackgroundPaint = SerialUtilities.readPaint(stream);
/* 1863 */     this.legendItemPaint = SerialUtilities.readPaint(stream);
/* 1864 */     this.plotBackgroundPaint = SerialUtilities.readPaint(stream);
/* 1865 */     this.plotOutlinePaint = SerialUtilities.readPaint(stream);
/* 1866 */     this.labelLinkPaint = SerialUtilities.readPaint(stream);
/* 1867 */     this.baselinePaint = SerialUtilities.readPaint(stream);
/* 1868 */     this.domainGridlinePaint = SerialUtilities.readPaint(stream);
/* 1869 */     this.rangeGridlinePaint = SerialUtilities.readPaint(stream);
/* 1870 */     this.crosshairPaint = SerialUtilities.readPaint(stream);
/* 1871 */     this.axisLabelPaint = SerialUtilities.readPaint(stream);
/* 1872 */     this.tickLabelPaint = SerialUtilities.readPaint(stream);
/* 1873 */     this.itemLabelPaint = SerialUtilities.readPaint(stream);
/* 1874 */     this.shadowPaint = SerialUtilities.readPaint(stream);
/* 1875 */     this.thermometerPaint = SerialUtilities.readPaint(stream);
/* 1876 */     this.wallPaint = SerialUtilities.readPaint(stream);
/* 1877 */     this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
/* 1878 */     this.gridBandPaint = SerialUtilities.readPaint(stream);
/* 1879 */     this.gridBandAlternatePaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/StandardChartTheme.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */