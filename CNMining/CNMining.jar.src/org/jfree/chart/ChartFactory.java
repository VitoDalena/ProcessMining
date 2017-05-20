/*      */ package org.jfree.chart;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Font;
/*      */ import java.text.DateFormat;
/*      */ import java.text.NumberFormat;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.CategoryAxis3D;
/*      */ import org.jfree.chart.axis.DateAxis;
/*      */ import org.jfree.chart.axis.NumberAxis;
/*      */ import org.jfree.chart.axis.NumberAxis3D;
/*      */ import org.jfree.chart.axis.Timeline;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.labels.BoxAndWhiskerToolTipGenerator;
/*      */ import org.jfree.chart.labels.HighLowItemLabelGenerator;
/*      */ import org.jfree.chart.labels.IntervalCategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.ItemLabelAnchor;
/*      */ import org.jfree.chart.labels.ItemLabelPosition;
/*      */ import org.jfree.chart.labels.PieToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
/*      */ import org.jfree.chart.labels.StandardPieToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardXYToolTipGenerator;
/*      */ import org.jfree.chart.labels.StandardXYZToolTipGenerator;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.Marker;
/*      */ import org.jfree.chart.plot.MultiplePiePlot;
/*      */ import org.jfree.chart.plot.PiePlot;
/*      */ import org.jfree.chart.plot.PiePlot3D;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PolarPlot;
/*      */ import org.jfree.chart.plot.RingPlot;
/*      */ import org.jfree.chart.plot.ValueMarker;
/*      */ import org.jfree.chart.plot.WaferMapPlot;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.renderer.DefaultPolarItemRenderer;
/*      */ import org.jfree.chart.renderer.WaferMapRenderer;
/*      */ import org.jfree.chart.renderer.category.AreaRenderer;
/*      */ import org.jfree.chart.renderer.category.BarRenderer;
/*      */ import org.jfree.chart.renderer.category.BarRenderer3D;
/*      */ import org.jfree.chart.renderer.category.BoxAndWhiskerRenderer;
/*      */ import org.jfree.chart.renderer.category.CategoryItemRenderer;
/*      */ import org.jfree.chart.renderer.category.GanttRenderer;
/*      */ import org.jfree.chart.renderer.category.GradientBarPainter;
/*      */ import org.jfree.chart.renderer.category.LineAndShapeRenderer;
/*      */ import org.jfree.chart.renderer.category.LineRenderer3D;
/*      */ import org.jfree.chart.renderer.category.StackedAreaRenderer;
/*      */ import org.jfree.chart.renderer.category.StackedBarRenderer;
/*      */ import org.jfree.chart.renderer.category.StackedBarRenderer3D;
/*      */ import org.jfree.chart.renderer.category.StandardBarPainter;
/*      */ import org.jfree.chart.renderer.category.WaterfallBarRenderer;
/*      */ import org.jfree.chart.renderer.xy.CandlestickRenderer;
/*      */ import org.jfree.chart.renderer.xy.GradientXYBarPainter;
/*      */ import org.jfree.chart.renderer.xy.HighLowRenderer;
/*      */ import org.jfree.chart.renderer.xy.StackedXYAreaRenderer2;
/*      */ import org.jfree.chart.renderer.xy.StandardXYBarPainter;
/*      */ import org.jfree.chart.renderer.xy.WindItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYAreaRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYBarRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYBoxAndWhiskerRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYBubbleRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYItemRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYStepAreaRenderer;
/*      */ import org.jfree.chart.renderer.xy.XYStepRenderer;
/*      */ import org.jfree.chart.title.TextTitle;
/*      */ import org.jfree.chart.urls.PieURLGenerator;
/*      */ import org.jfree.chart.urls.StandardCategoryURLGenerator;
/*      */ import org.jfree.chart.urls.StandardPieURLGenerator;
/*      */ import org.jfree.chart.urls.StandardXYURLGenerator;
/*      */ import org.jfree.chart.urls.StandardXYZURLGenerator;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.category.IntervalCategoryDataset;
/*      */ import org.jfree.data.general.DefaultPieDataset;
/*      */ import org.jfree.data.general.PieDataset;
/*      */ import org.jfree.data.general.WaferMapDataset;
/*      */ import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
/*      */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*      */ import org.jfree.data.xy.IntervalXYDataset;
/*      */ import org.jfree.data.xy.OHLCDataset;
/*      */ import org.jfree.data.xy.TableXYDataset;
/*      */ import org.jfree.data.xy.WindDataset;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.data.xy.XYZDataset;
/*      */ import org.jfree.ui.Layer;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.ui.RectangleInsets;
/*      */ import org.jfree.ui.TextAnchor;
/*      */ import org.jfree.util.SortOrder;
/*      */ import org.jfree.util.TableOrder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class ChartFactory
/*      */ {
/*  229 */   private static ChartTheme currentTheme = new StandardChartTheme("JFree");
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static ChartTheme getChartTheme()
/*      */   {
/*  242 */     return currentTheme;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static void setChartTheme(ChartTheme theme)
/*      */   {
/*  257 */     if (theme == null) {
/*  258 */       throw new IllegalArgumentException("Null 'theme' argument.");
/*      */     }
/*  260 */     currentTheme = theme;
/*      */     
/*      */ 
/*      */ 
/*  264 */     if ((theme instanceof StandardChartTheme)) {
/*  265 */       StandardChartTheme sct = (StandardChartTheme)theme;
/*  266 */       if (sct.getName().equals("Legacy")) {
/*  267 */         BarRenderer.setDefaultBarPainter(new StandardBarPainter());
/*  268 */         XYBarRenderer.setDefaultBarPainter(new StandardXYBarPainter());
/*      */       }
/*      */       else {
/*  271 */         BarRenderer.setDefaultBarPainter(new GradientBarPainter());
/*  272 */         XYBarRenderer.setDefaultBarPainter(new GradientXYBarPainter());
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
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPieChart(String title, PieDataset dataset, boolean legend, boolean tooltips, Locale locale)
/*      */   {
/*  296 */     PiePlot plot = new PiePlot(dataset);
/*  297 */     plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
/*  298 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  299 */     if (tooltips) {
/*  300 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
/*      */     }
/*  302 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  304 */     currentTheme.apply(chart);
/*  305 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPieChart(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  329 */     PiePlot plot = new PiePlot(dataset);
/*  330 */     plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
/*  331 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  332 */     if (tooltips) {
/*  333 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator());
/*      */     }
/*  335 */     if (urls) {
/*  336 */       plot.setURLGenerator(new StandardPieURLGenerator());
/*      */     }
/*  338 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  340 */     currentTheme.apply(chart);
/*  341 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPieChart(String title, PieDataset dataset, PieDataset previousDataset, int percentDiffForMaxScale, boolean greenForIncrease, boolean legend, boolean tooltips, Locale locale, boolean subTitle, boolean showDifference)
/*      */   {
/*  389 */     PiePlot plot = new PiePlot(dataset);
/*  390 */     plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
/*  391 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*      */     
/*  393 */     if (tooltips) {
/*  394 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
/*      */     }
/*      */     
/*  397 */     List keys = dataset.getKeys();
/*  398 */     DefaultPieDataset series = null;
/*  399 */     if (showDifference) {
/*  400 */       series = new DefaultPieDataset();
/*      */     }
/*      */     
/*  403 */     double colorPerPercent = 255.0D / percentDiffForMaxScale;
/*  404 */     for (Iterator it = keys.iterator(); it.hasNext();) {
/*  405 */       Comparable key = (Comparable)it.next();
/*  406 */       Number newValue = dataset.getValue(key);
/*  407 */       Number oldValue = previousDataset.getValue(key);
/*      */       
/*  409 */       if (oldValue == null) {
/*  410 */         if (greenForIncrease) {
/*  411 */           plot.setSectionPaint(key, Color.green);
/*      */         }
/*      */         else {
/*  414 */           plot.setSectionPaint(key, Color.red);
/*      */         }
/*  416 */         if (showDifference) {
/*  417 */           series.setValue(key + " (+100%)", newValue);
/*      */         }
/*      */       }
/*      */       else {
/*  421 */         double percentChange = (newValue.doubleValue() / oldValue.doubleValue() - 1.0D) * 100.0D;
/*      */         
/*  423 */         double shade = Math.abs(percentChange) >= percentDiffForMaxScale ? 255.0D : Math.abs(percentChange) * colorPerPercent;
/*      */         
/*      */ 
/*  426 */         if (((greenForIncrease) && (newValue.doubleValue() > oldValue.doubleValue())) || ((!greenForIncrease) && (newValue.doubleValue() < oldValue.doubleValue())))
/*      */         {
/*      */ 
/*      */ 
/*  430 */           plot.setSectionPaint(key, new Color(0, (int)shade, 0));
/*      */         }
/*      */         else {
/*  433 */           plot.setSectionPaint(key, new Color((int)shade, 0, 0));
/*      */         }
/*  435 */         if (showDifference) {
/*  436 */           series.setValue(key + " (" + (percentChange >= 0.0D ? "+" : "") + NumberFormat.getPercentInstance().format(percentChange / 100.0D) + ")", newValue);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  443 */     if (showDifference) {
/*  444 */       plot.setDataset(series);
/*      */     }
/*      */     
/*  447 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*      */ 
/*  450 */     if (subTitle) {
/*  451 */       TextTitle subtitle = null;
/*  452 */       subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" : "green") + "=change >=-" + percentDiffForMaxScale + "%, Bright " + (!greenForIncrease ? "red" : "green") + "=change >=+" + percentDiffForMaxScale + "%", new Font("SansSerif", 0, 10));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  457 */       chart.addSubtitle(subtitle);
/*      */     }
/*  459 */     currentTheme.apply(chart);
/*  460 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPieChart(String title, PieDataset dataset, PieDataset previousDataset, int percentDiffForMaxScale, boolean greenForIncrease, boolean legend, boolean tooltips, boolean urls, boolean subTitle, boolean showDifference)
/*      */   {
/*  512 */     PiePlot plot = new PiePlot(dataset);
/*  513 */     plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
/*  514 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*      */     
/*  516 */     if (tooltips) {
/*  517 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator());
/*      */     }
/*  519 */     if (urls) {
/*  520 */       plot.setURLGenerator(new StandardPieURLGenerator());
/*      */     }
/*      */     
/*  523 */     List keys = dataset.getKeys();
/*  524 */     DefaultPieDataset series = null;
/*  525 */     if (showDifference) {
/*  526 */       series = new DefaultPieDataset();
/*      */     }
/*      */     
/*  529 */     double colorPerPercent = 255.0D / percentDiffForMaxScale;
/*  530 */     for (Iterator it = keys.iterator(); it.hasNext();) {
/*  531 */       Comparable key = (Comparable)it.next();
/*  532 */       Number newValue = dataset.getValue(key);
/*  533 */       Number oldValue = previousDataset.getValue(key);
/*      */       
/*  535 */       if (oldValue == null) {
/*  536 */         if (greenForIncrease) {
/*  537 */           plot.setSectionPaint(key, Color.green);
/*      */         }
/*      */         else {
/*  540 */           plot.setSectionPaint(key, Color.red);
/*      */         }
/*  542 */         if (showDifference) {
/*  543 */           series.setValue(key + " (+100%)", newValue);
/*      */         }
/*      */       }
/*      */       else {
/*  547 */         double percentChange = (newValue.doubleValue() / oldValue.doubleValue() - 1.0D) * 100.0D;
/*      */         
/*  549 */         double shade = Math.abs(percentChange) >= percentDiffForMaxScale ? 255.0D : Math.abs(percentChange) * colorPerPercent;
/*      */         
/*      */ 
/*  552 */         if (((greenForIncrease) && (newValue.doubleValue() > oldValue.doubleValue())) || ((!greenForIncrease) && (newValue.doubleValue() < oldValue.doubleValue())))
/*      */         {
/*      */ 
/*      */ 
/*  556 */           plot.setSectionPaint(key, new Color(0, (int)shade, 0));
/*      */         }
/*      */         else {
/*  559 */           plot.setSectionPaint(key, new Color((int)shade, 0, 0));
/*      */         }
/*  561 */         if (showDifference) {
/*  562 */           series.setValue(key + " (" + (percentChange >= 0.0D ? "+" : "") + NumberFormat.getPercentInstance().format(percentChange / 100.0D) + ")", newValue);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  569 */     if (showDifference) {
/*  570 */       plot.setDataset(series);
/*      */     }
/*      */     
/*  573 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*      */ 
/*  576 */     if (subTitle) {
/*  577 */       TextTitle subtitle = null;
/*  578 */       subtitle = new TextTitle("Bright " + (greenForIncrease ? "red" : "green") + "=change >=-" + percentDiffForMaxScale + "%, Bright " + (!greenForIncrease ? "red" : "green") + "=change >=+" + percentDiffForMaxScale + "%", new Font("SansSerif", 0, 10));
/*      */       
/*      */ 
/*      */ 
/*      */ 
/*  583 */       chart.addSubtitle(subtitle);
/*      */     }
/*  585 */     currentTheme.apply(chart);
/*  586 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createRingChart(String title, PieDataset dataset, boolean legend, boolean tooltips, Locale locale)
/*      */   {
/*  608 */     RingPlot plot = new RingPlot(dataset);
/*  609 */     plot.setLabelGenerator(new StandardPieSectionLabelGenerator(locale));
/*  610 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  611 */     if (tooltips) {
/*  612 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
/*      */     }
/*  614 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  616 */     currentTheme.apply(chart);
/*  617 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createRingChart(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  640 */     RingPlot plot = new RingPlot(dataset);
/*  641 */     plot.setLabelGenerator(new StandardPieSectionLabelGenerator());
/*  642 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  643 */     if (tooltips) {
/*  644 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator());
/*      */     }
/*  646 */     if (urls) {
/*  647 */       plot.setURLGenerator(new StandardPieURLGenerator());
/*      */     }
/*  649 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  651 */     currentTheme.apply(chart);
/*  652 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createMultiplePieChart(String title, CategoryDataset dataset, TableOrder order, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  678 */     if (order == null) {
/*  679 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/*  681 */     MultiplePiePlot plot = new MultiplePiePlot(dataset);
/*  682 */     plot.setDataExtractOrder(order);
/*  683 */     plot.setBackgroundPaint(null);
/*  684 */     plot.setOutlineStroke(null);
/*      */     
/*  686 */     if (tooltips) {
/*  687 */       PieToolTipGenerator tooltipGenerator = new StandardPieToolTipGenerator();
/*      */       
/*  689 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  690 */       pp.setToolTipGenerator(tooltipGenerator);
/*      */     }
/*      */     
/*  693 */     if (urls) {
/*  694 */       PieURLGenerator urlGenerator = new StandardPieURLGenerator();
/*  695 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  696 */       pp.setURLGenerator(urlGenerator);
/*      */     }
/*      */     
/*  699 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  701 */     currentTheme.apply(chart);
/*  702 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPieChart3D(String title, PieDataset dataset, boolean legend, boolean tooltips, Locale locale)
/*      */   {
/*  724 */     PiePlot3D plot = new PiePlot3D(dataset);
/*  725 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  726 */     if (tooltips) {
/*  727 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator(locale));
/*      */     }
/*  729 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  731 */     currentTheme.apply(chart);
/*  732 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPieChart3D(String title, PieDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  755 */     PiePlot3D plot = new PiePlot3D(dataset);
/*  756 */     plot.setInsets(new RectangleInsets(0.0D, 5.0D, 5.0D, 5.0D));
/*  757 */     if (tooltips) {
/*  758 */       plot.setToolTipGenerator(new StandardPieToolTipGenerator());
/*      */     }
/*  760 */     if (urls) {
/*  761 */       plot.setURLGenerator(new StandardPieURLGenerator());
/*      */     }
/*  763 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  765 */     currentTheme.apply(chart);
/*  766 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createMultiplePieChart3D(String title, CategoryDataset dataset, TableOrder order, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  792 */     if (order == null) {
/*  793 */       throw new IllegalArgumentException("Null 'order' argument.");
/*      */     }
/*  795 */     MultiplePiePlot plot = new MultiplePiePlot(dataset);
/*  796 */     plot.setDataExtractOrder(order);
/*  797 */     plot.setBackgroundPaint(null);
/*  798 */     plot.setOutlineStroke(null);
/*      */     
/*  800 */     JFreeChart pieChart = new JFreeChart(new PiePlot3D(null));
/*  801 */     TextTitle seriesTitle = new TextTitle("Series Title", new Font("SansSerif", 1, 12));
/*      */     
/*  803 */     seriesTitle.setPosition(RectangleEdge.BOTTOM);
/*  804 */     pieChart.setTitle(seriesTitle);
/*  805 */     pieChart.removeLegend();
/*  806 */     pieChart.setBackgroundPaint(null);
/*  807 */     plot.setPieChart(pieChart);
/*      */     
/*  809 */     if (tooltips) {
/*  810 */       PieToolTipGenerator tooltipGenerator = new StandardPieToolTipGenerator();
/*      */       
/*  812 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  813 */       pp.setToolTipGenerator(tooltipGenerator);
/*      */     }
/*      */     
/*  816 */     if (urls) {
/*  817 */       PieURLGenerator urlGenerator = new StandardPieURLGenerator();
/*  818 */       PiePlot pp = (PiePlot)plot.getPieChart().getPlot();
/*  819 */       pp.setURLGenerator(urlGenerator);
/*      */     }
/*      */     
/*  822 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  824 */     currentTheme.apply(chart);
/*  825 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createBarChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  858 */     if (orientation == null) {
/*  859 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*  861 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/*  862 */     ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
/*      */     
/*  864 */     BarRenderer renderer = new BarRenderer();
/*  865 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  866 */       ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE3, TextAnchor.CENTER_LEFT);
/*      */       
/*  868 */       renderer.setBasePositiveItemLabelPosition(position1);
/*  869 */       ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE9, TextAnchor.CENTER_RIGHT);
/*      */       
/*  871 */       renderer.setBaseNegativeItemLabelPosition(position2);
/*      */     }
/*  873 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  874 */       ItemLabelPosition position1 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BOTTOM_CENTER);
/*      */       
/*  876 */       renderer.setBasePositiveItemLabelPosition(position1);
/*  877 */       ItemLabelPosition position2 = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_CENTER);
/*      */       
/*  879 */       renderer.setBaseNegativeItemLabelPosition(position2);
/*      */     }
/*  881 */     if (tooltips) {
/*  882 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/*  885 */     if (urls) {
/*  886 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/*  890 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/*  892 */     plot.setOrientation(orientation);
/*  893 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  895 */     currentTheme.apply(chart);
/*  896 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createStackedBarChart(String title, String domainAxisLabel, String rangeAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  930 */     if (orientation == null) {
/*  931 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*      */     
/*  934 */     CategoryAxis categoryAxis = new CategoryAxis(domainAxisLabel);
/*  935 */     ValueAxis valueAxis = new NumberAxis(rangeAxisLabel);
/*      */     
/*  937 */     StackedBarRenderer renderer = new StackedBarRenderer();
/*  938 */     if (tooltips) {
/*  939 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/*  942 */     if (urls) {
/*  943 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/*  947 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/*  949 */     plot.setOrientation(orientation);
/*  950 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/*  952 */     currentTheme.apply(chart);
/*  953 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createBarChart3D(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/*  986 */     if (orientation == null) {
/*  987 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/*  989 */     CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
/*  990 */     ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
/*      */     
/*  992 */     BarRenderer3D renderer = new BarRenderer3D();
/*  993 */     if (tooltips) {
/*  994 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/*  997 */     if (urls) {
/*  998 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/* 1002 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1004 */     plot.setOrientation(orientation);
/* 1005 */     if (orientation == PlotOrientation.HORIZONTAL)
/*      */     {
/*      */ 
/* 1008 */       plot.setRowRenderingOrder(SortOrder.DESCENDING);
/* 1009 */       plot.setColumnRenderingOrder(SortOrder.DESCENDING);
/*      */     }
/* 1011 */     plot.setForegroundAlpha(0.75F);
/*      */     
/* 1013 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1015 */     currentTheme.apply(chart);
/* 1016 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createStackedBarChart3D(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1050 */     if (orientation == null) {
/* 1051 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1053 */     CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
/* 1054 */     ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
/*      */     
/*      */ 
/* 1057 */     CategoryItemRenderer renderer = new StackedBarRenderer3D();
/* 1058 */     if (tooltips) {
/* 1059 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/* 1062 */     if (urls) {
/* 1063 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1068 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1070 */     plot.setOrientation(orientation);
/* 1071 */     if (orientation == PlotOrientation.HORIZONTAL)
/*      */     {
/*      */ 
/* 1074 */       plot.setColumnRenderingOrder(SortOrder.DESCENDING);
/*      */     }
/*      */     
/*      */ 
/* 1078 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1080 */     currentTheme.apply(chart);
/* 1081 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createAreaChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1114 */     if (orientation == null) {
/* 1115 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1117 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1118 */     categoryAxis.setCategoryMargin(0.0D);
/*      */     
/* 1120 */     ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
/*      */     
/* 1122 */     AreaRenderer renderer = new AreaRenderer();
/* 1123 */     if (tooltips) {
/* 1124 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/* 1127 */     if (urls) {
/* 1128 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/* 1132 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1134 */     plot.setOrientation(orientation);
/* 1135 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1137 */     currentTheme.apply(chart);
/* 1138 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createStackedAreaChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1168 */     if (orientation == null) {
/* 1169 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1171 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1172 */     categoryAxis.setCategoryMargin(0.0D);
/* 1173 */     ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
/*      */     
/* 1175 */     StackedAreaRenderer renderer = new StackedAreaRenderer();
/* 1176 */     if (tooltips) {
/* 1177 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/* 1180 */     if (urls) {
/* 1181 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/* 1185 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1187 */     plot.setOrientation(orientation);
/* 1188 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1190 */     currentTheme.apply(chart);
/* 1191 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createLineChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1224 */     if (orientation == null) {
/* 1225 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1227 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1228 */     ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
/*      */     
/* 1230 */     LineAndShapeRenderer renderer = new LineAndShapeRenderer(true, false);
/* 1231 */     if (tooltips) {
/* 1232 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/* 1235 */     if (urls) {
/* 1236 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/* 1239 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1241 */     plot.setOrientation(orientation);
/* 1242 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1244 */     currentTheme.apply(chart);
/* 1245 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createLineChart3D(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1278 */     if (orientation == null) {
/* 1279 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1281 */     CategoryAxis categoryAxis = new CategoryAxis3D(categoryAxisLabel);
/* 1282 */     ValueAxis valueAxis = new NumberAxis3D(valueAxisLabel);
/*      */     
/* 1284 */     LineRenderer3D renderer = new LineRenderer3D();
/* 1285 */     if (tooltips) {
/* 1286 */       renderer.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
/*      */     }
/*      */     
/* 1289 */     if (urls) {
/* 1290 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/* 1293 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1295 */     plot.setOrientation(orientation);
/* 1296 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1298 */     currentTheme.apply(chart);
/* 1299 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createGanttChart(String title, String categoryAxisLabel, String dateAxisLabel, IntervalCategoryDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1330 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1331 */     DateAxis dateAxis = new DateAxis(dateAxisLabel);
/*      */     
/* 1333 */     CategoryItemRenderer renderer = new GanttRenderer();
/* 1334 */     if (tooltips) {
/* 1335 */       renderer.setBaseToolTipGenerator(new IntervalCategoryToolTipGenerator("{3} - {4}", DateFormat.getDateInstance()));
/*      */     }
/*      */     
/*      */ 
/* 1339 */     if (urls) {
/* 1340 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/* 1344 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, dateAxis, renderer);
/*      */     
/* 1346 */     plot.setOrientation(PlotOrientation.HORIZONTAL);
/* 1347 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1349 */     currentTheme.apply(chart);
/* 1350 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createWaterfallChart(String title, String categoryAxisLabel, String valueAxisLabel, CategoryDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1383 */     if (orientation == null) {
/* 1384 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1386 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 1387 */     categoryAxis.setCategoryMargin(0.0D);
/*      */     
/* 1389 */     ValueAxis valueAxis = new NumberAxis(valueAxisLabel);
/*      */     
/* 1391 */     WaterfallBarRenderer renderer = new WaterfallBarRenderer();
/* 1392 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 1393 */       ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 1.5707963267948966D);
/*      */       
/*      */ 
/* 1396 */       renderer.setBasePositiveItemLabelPosition(position);
/* 1397 */       renderer.setBaseNegativeItemLabelPosition(position);
/*      */     }
/* 1399 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 1400 */       ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER, TextAnchor.CENTER, 0.0D);
/*      */       
/*      */ 
/* 1403 */       renderer.setBasePositiveItemLabelPosition(position);
/* 1404 */       renderer.setBaseNegativeItemLabelPosition(position);
/*      */     }
/* 1406 */     if (tooltips) {
/* 1407 */       StandardCategoryToolTipGenerator generator = new StandardCategoryToolTipGenerator();
/*      */       
/* 1409 */       renderer.setBaseToolTipGenerator(generator);
/*      */     }
/* 1411 */     if (urls) {
/* 1412 */       renderer.setBaseItemURLGenerator(new StandardCategoryURLGenerator());
/*      */     }
/*      */     
/*      */ 
/* 1416 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 1418 */     plot.clearRangeMarkers();
/* 1419 */     Marker baseline = new ValueMarker(0.0D);
/* 1420 */     baseline.setPaint(Color.black);
/* 1421 */     plot.addRangeMarker(baseline, Layer.FOREGROUND);
/* 1422 */     plot.setOrientation(orientation);
/* 1423 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1425 */     currentTheme.apply(chart);
/* 1426 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createPolarChart(String title, XYDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1450 */     PolarPlot plot = new PolarPlot();
/* 1451 */     plot.setDataset(dataset);
/* 1452 */     NumberAxis rangeAxis = new NumberAxis();
/* 1453 */     rangeAxis.setAxisLineVisible(false);
/* 1454 */     rangeAxis.setTickMarksVisible(false);
/* 1455 */     rangeAxis.setTickLabelInsets(new RectangleInsets(0.0D, 0.0D, 0.0D, 0.0D));
/* 1456 */     plot.setAxis(rangeAxis);
/* 1457 */     plot.setRenderer(new DefaultPolarItemRenderer());
/* 1458 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1460 */     currentTheme.apply(chart);
/* 1461 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createScatterPlot(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1488 */     if (orientation == null) {
/* 1489 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1491 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1492 */     xAxis.setAutoRangeIncludesZero(false);
/* 1493 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1494 */     yAxis.setAutoRangeIncludesZero(false);
/*      */     
/* 1496 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
/*      */     
/* 1498 */     XYToolTipGenerator toolTipGenerator = null;
/* 1499 */     if (tooltips) {
/* 1500 */       toolTipGenerator = new StandardXYToolTipGenerator();
/*      */     }
/*      */     
/* 1503 */     XYURLGenerator urlGenerator = null;
/* 1504 */     if (urls) {
/* 1505 */       urlGenerator = new StandardXYURLGenerator();
/*      */     }
/* 1507 */     XYItemRenderer renderer = new XYLineAndShapeRenderer(false, true);
/* 1508 */     renderer.setBaseToolTipGenerator(toolTipGenerator);
/* 1509 */     renderer.setURLGenerator(urlGenerator);
/* 1510 */     plot.setRenderer(renderer);
/* 1511 */     plot.setOrientation(orientation);
/*      */     
/* 1513 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1515 */     currentTheme.apply(chart);
/* 1516 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createXYBarChart(String title, String xAxisLabel, boolean dateAxis, String yAxisLabel, IntervalXYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1551 */     if (orientation == null) {
/* 1552 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1554 */     ValueAxis domainAxis = null;
/* 1555 */     if (dateAxis) {
/* 1556 */       domainAxis = new DateAxis(xAxisLabel);
/*      */     }
/*      */     else {
/* 1559 */       NumberAxis axis = new NumberAxis(xAxisLabel);
/* 1560 */       axis.setAutoRangeIncludesZero(false);
/* 1561 */       domainAxis = axis;
/*      */     }
/* 1563 */     ValueAxis valueAxis = new NumberAxis(yAxisLabel);
/*      */     
/* 1565 */     XYBarRenderer renderer = new XYBarRenderer();
/* 1566 */     if (tooltips) { XYToolTipGenerator tt;
/*      */       XYToolTipGenerator tt;
/* 1568 */       if (dateAxis) {
/* 1569 */         tt = StandardXYToolTipGenerator.getTimeSeriesInstance();
/*      */       }
/*      */       else {
/* 1572 */         tt = new StandardXYToolTipGenerator();
/*      */       }
/* 1574 */       renderer.setBaseToolTipGenerator(tt);
/*      */     }
/* 1576 */     if (urls) {
/* 1577 */       renderer.setURLGenerator(new StandardXYURLGenerator());
/*      */     }
/*      */     
/* 1580 */     XYPlot plot = new XYPlot(dataset, domainAxis, valueAxis, renderer);
/* 1581 */     plot.setOrientation(orientation);
/*      */     
/* 1583 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1585 */     currentTheme.apply(chart);
/* 1586 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createXYAreaChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1619 */     if (orientation == null) {
/* 1620 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1622 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1623 */     xAxis.setAutoRangeIncludesZero(false);
/* 1624 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1625 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
/* 1626 */     plot.setOrientation(orientation);
/* 1627 */     plot.setForegroundAlpha(0.5F);
/*      */     
/* 1629 */     XYToolTipGenerator tipGenerator = null;
/* 1630 */     if (tooltips) {
/* 1631 */       tipGenerator = new StandardXYToolTipGenerator();
/*      */     }
/*      */     
/* 1634 */     XYURLGenerator urlGenerator = null;
/* 1635 */     if (urls) {
/* 1636 */       urlGenerator = new StandardXYURLGenerator();
/*      */     }
/*      */     
/* 1639 */     plot.setRenderer(new XYAreaRenderer(4, tipGenerator, urlGenerator));
/*      */     
/* 1641 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1643 */     currentTheme.apply(chart);
/* 1644 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createStackedXYAreaChart(String title, String xAxisLabel, String yAxisLabel, TableXYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1675 */     if (orientation == null) {
/* 1676 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1678 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1679 */     xAxis.setAutoRangeIncludesZero(false);
/* 1680 */     xAxis.setLowerMargin(0.0D);
/* 1681 */     xAxis.setUpperMargin(0.0D);
/* 1682 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1683 */     XYToolTipGenerator toolTipGenerator = null;
/* 1684 */     if (tooltips) {
/* 1685 */       toolTipGenerator = new StandardXYToolTipGenerator();
/*      */     }
/*      */     
/* 1688 */     XYURLGenerator urlGenerator = null;
/* 1689 */     if (urls) {
/* 1690 */       urlGenerator = new StandardXYURLGenerator();
/*      */     }
/* 1692 */     StackedXYAreaRenderer2 renderer = new StackedXYAreaRenderer2(toolTipGenerator, urlGenerator);
/*      */     
/* 1694 */     renderer.setOutline(true);
/* 1695 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
/* 1696 */     plot.setOrientation(orientation);
/*      */     
/* 1698 */     plot.setRangeAxis(yAxis);
/*      */     
/* 1700 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1702 */     currentTheme.apply(chart);
/* 1703 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createXYLineChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1732 */     if (orientation == null) {
/* 1733 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1735 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1736 */     xAxis.setAutoRangeIncludesZero(false);
/* 1737 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1738 */     XYItemRenderer renderer = new XYLineAndShapeRenderer(true, false);
/* 1739 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
/* 1740 */     plot.setOrientation(orientation);
/* 1741 */     if (tooltips) {
/* 1742 */       renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
/*      */     }
/* 1744 */     if (urls) {
/* 1745 */       renderer.setURLGenerator(new StandardXYURLGenerator());
/*      */     }
/*      */     
/* 1748 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1750 */     currentTheme.apply(chart);
/* 1751 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createXYStepChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1779 */     if (orientation == null) {
/* 1780 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1782 */     DateAxis xAxis = new DateAxis(xAxisLabel);
/* 1783 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 1784 */     yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
/*      */     
/* 1786 */     XYToolTipGenerator toolTipGenerator = null;
/* 1787 */     if (tooltips) {
/* 1788 */       toolTipGenerator = new StandardXYToolTipGenerator();
/*      */     }
/*      */     
/* 1791 */     XYURLGenerator urlGenerator = null;
/* 1792 */     if (urls) {
/* 1793 */       urlGenerator = new StandardXYURLGenerator();
/*      */     }
/* 1795 */     XYItemRenderer renderer = new XYStepRenderer(toolTipGenerator, urlGenerator);
/*      */     
/*      */ 
/* 1798 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
/* 1799 */     plot.setRenderer(renderer);
/* 1800 */     plot.setOrientation(orientation);
/* 1801 */     plot.setDomainCrosshairVisible(false);
/* 1802 */     plot.setRangeCrosshairVisible(false);
/* 1803 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1805 */     currentTheme.apply(chart);
/* 1806 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createXYStepAreaChart(String title, String xAxisLabel, String yAxisLabel, XYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1834 */     if (orientation == null) {
/* 1835 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 1837 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 1838 */     xAxis.setAutoRangeIncludesZero(false);
/* 1839 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/*      */     
/* 1841 */     XYToolTipGenerator toolTipGenerator = null;
/* 1842 */     if (tooltips) {
/* 1843 */       toolTipGenerator = new StandardXYToolTipGenerator();
/*      */     }
/*      */     
/* 1846 */     XYURLGenerator urlGenerator = null;
/* 1847 */     if (urls) {
/* 1848 */       urlGenerator = new StandardXYURLGenerator();
/*      */     }
/* 1850 */     XYItemRenderer renderer = new XYStepAreaRenderer(3, toolTipGenerator, urlGenerator);
/*      */     
/*      */ 
/*      */ 
/* 1854 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
/* 1855 */     plot.setRenderer(renderer);
/* 1856 */     plot.setOrientation(orientation);
/* 1857 */     plot.setDomainCrosshairVisible(false);
/* 1858 */     plot.setRangeCrosshairVisible(false);
/* 1859 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1861 */     currentTheme.apply(chart);
/* 1862 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createTimeSeriesChart(String title, String timeAxisLabel, String valueAxisLabel, XYDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 1894 */     ValueAxis timeAxis = new DateAxis(timeAxisLabel);
/* 1895 */     timeAxis.setLowerMargin(0.02D);
/* 1896 */     timeAxis.setUpperMargin(0.02D);
/* 1897 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1898 */     valueAxis.setAutoRangeIncludesZero(false);
/* 1899 */     XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
/*      */     
/* 1901 */     XYToolTipGenerator toolTipGenerator = null;
/* 1902 */     if (tooltips) {
/* 1903 */       toolTipGenerator = StandardXYToolTipGenerator.getTimeSeriesInstance();
/*      */     }
/*      */     
/*      */ 
/* 1907 */     XYURLGenerator urlGenerator = null;
/* 1908 */     if (urls) {
/* 1909 */       urlGenerator = new StandardXYURLGenerator();
/*      */     }
/*      */     
/* 1912 */     XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, false);
/*      */     
/* 1914 */     renderer.setBaseToolTipGenerator(toolTipGenerator);
/* 1915 */     renderer.setURLGenerator(urlGenerator);
/* 1916 */     plot.setRenderer(renderer);
/*      */     
/* 1918 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1920 */     currentTheme.apply(chart);
/* 1921 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createCandlestickChart(String title, String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset, boolean legend)
/*      */   {
/* 1944 */     ValueAxis timeAxis = new DateAxis(timeAxisLabel);
/* 1945 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1946 */     XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, null);
/* 1947 */     plot.setRenderer(new CandlestickRenderer());
/* 1948 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1950 */     currentTheme.apply(chart);
/* 1951 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createHighLowChart(String title, String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset, boolean legend)
/*      */   {
/* 1974 */     ValueAxis timeAxis = new DateAxis(timeAxisLabel);
/* 1975 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 1976 */     HighLowRenderer renderer = new HighLowRenderer();
/* 1977 */     renderer.setBaseToolTipGenerator(new HighLowItemLabelGenerator());
/* 1978 */     XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
/* 1979 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 1981 */     currentTheme.apply(chart);
/* 1982 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createHighLowChart(String title, String timeAxisLabel, String valueAxisLabel, OHLCDataset dataset, Timeline timeline, boolean legend)
/*      */   {
/* 2011 */     DateAxis timeAxis = new DateAxis(timeAxisLabel);
/* 2012 */     timeAxis.setTimeline(timeline);
/* 2013 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 2014 */     HighLowRenderer renderer = new HighLowRenderer();
/* 2015 */     renderer.setBaseToolTipGenerator(new HighLowItemLabelGenerator());
/* 2016 */     XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
/* 2017 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2019 */     currentTheme.apply(chart);
/* 2020 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createBubbleChart(String title, String xAxisLabel, String yAxisLabel, XYZDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 2051 */     if (orientation == null) {
/* 2052 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 2054 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 2055 */     xAxis.setAutoRangeIncludesZero(false);
/* 2056 */     NumberAxis yAxis = new NumberAxis(yAxisLabel);
/* 2057 */     yAxis.setAutoRangeIncludesZero(false);
/*      */     
/* 2059 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, null);
/*      */     
/* 2061 */     XYItemRenderer renderer = new XYBubbleRenderer(2);
/*      */     
/* 2063 */     if (tooltips) {
/* 2064 */       renderer.setBaseToolTipGenerator(new StandardXYZToolTipGenerator());
/*      */     }
/* 2066 */     if (urls) {
/* 2067 */       renderer.setURLGenerator(new StandardXYZURLGenerator());
/*      */     }
/* 2069 */     plot.setRenderer(renderer);
/* 2070 */     plot.setOrientation(orientation);
/*      */     
/* 2072 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2074 */     currentTheme.apply(chart);
/* 2075 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createHistogram(String title, String xAxisLabel, String yAxisLabel, IntervalXYDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 2101 */     if (orientation == null) {
/* 2102 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 2104 */     NumberAxis xAxis = new NumberAxis(xAxisLabel);
/* 2105 */     xAxis.setAutoRangeIncludesZero(false);
/* 2106 */     ValueAxis yAxis = new NumberAxis(yAxisLabel);
/*      */     
/* 2108 */     XYItemRenderer renderer = new XYBarRenderer();
/* 2109 */     if (tooltips) {
/* 2110 */       renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
/*      */     }
/* 2112 */     if (urls) {
/* 2113 */       renderer.setURLGenerator(new StandardXYURLGenerator());
/*      */     }
/*      */     
/* 2116 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
/* 2117 */     plot.setOrientation(orientation);
/* 2118 */     plot.setDomainZeroBaselineVisible(true);
/* 2119 */     plot.setRangeZeroBaselineVisible(true);
/* 2120 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2122 */     currentTheme.apply(chart);
/* 2123 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createBoxAndWhiskerChart(String title, String categoryAxisLabel, String valueAxisLabel, BoxAndWhiskerCategoryDataset dataset, boolean legend)
/*      */   {
/* 2147 */     CategoryAxis categoryAxis = new CategoryAxis(categoryAxisLabel);
/* 2148 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 2149 */     valueAxis.setAutoRangeIncludesZero(false);
/*      */     
/* 2151 */     BoxAndWhiskerRenderer renderer = new BoxAndWhiskerRenderer();
/* 2152 */     renderer.setBaseToolTipGenerator(new BoxAndWhiskerToolTipGenerator());
/*      */     
/* 2154 */     CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valueAxis, renderer);
/*      */     
/* 2156 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2158 */     currentTheme.apply(chart);
/* 2159 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createBoxAndWhiskerChart(String title, String timeAxisLabel, String valueAxisLabel, BoxAndWhiskerXYDataset dataset, boolean legend)
/*      */   {
/* 2181 */     ValueAxis timeAxis = new DateAxis(timeAxisLabel);
/* 2182 */     NumberAxis valueAxis = new NumberAxis(valueAxisLabel);
/* 2183 */     valueAxis.setAutoRangeIncludesZero(false);
/* 2184 */     XYBoxAndWhiskerRenderer renderer = new XYBoxAndWhiskerRenderer(10.0D);
/* 2185 */     XYPlot plot = new XYPlot(dataset, timeAxis, valueAxis, renderer);
/* 2186 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2188 */     currentTheme.apply(chart);
/* 2189 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createWindPlot(String title, String xAxisLabel, String yAxisLabel, WindDataset dataset, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 2215 */     ValueAxis xAxis = new DateAxis(xAxisLabel);
/* 2216 */     ValueAxis yAxis = new NumberAxis(yAxisLabel);
/* 2217 */     yAxis.setRange(-12.0D, 12.0D);
/*      */     
/* 2219 */     WindItemRenderer renderer = new WindItemRenderer();
/* 2220 */     if (tooltips) {
/* 2221 */       renderer.setBaseToolTipGenerator(new StandardXYToolTipGenerator());
/*      */     }
/* 2223 */     if (urls) {
/* 2224 */       renderer.setURLGenerator(new StandardXYURLGenerator());
/*      */     }
/* 2226 */     XYPlot plot = new XYPlot(dataset, xAxis, yAxis, renderer);
/* 2227 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2229 */     currentTheme.apply(chart);
/* 2230 */     return chart;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public static JFreeChart createWaferMapChart(String title, WaferMapDataset dataset, PlotOrientation orientation, boolean legend, boolean tooltips, boolean urls)
/*      */   {
/* 2254 */     if (orientation == null) {
/* 2255 */       throw new IllegalArgumentException("Null 'orientation' argument.");
/*      */     }
/* 2257 */     WaferMapPlot plot = new WaferMapPlot(dataset);
/* 2258 */     WaferMapRenderer renderer = new WaferMapRenderer();
/* 2259 */     plot.setRenderer(renderer);
/*      */     
/* 2261 */     JFreeChart chart = new JFreeChart(title, JFreeChart.DEFAULT_TITLE_FONT, plot, legend);
/*      */     
/* 2263 */     currentTheme.apply(chart);
/* 2264 */     return chart;
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/ChartFactory.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */