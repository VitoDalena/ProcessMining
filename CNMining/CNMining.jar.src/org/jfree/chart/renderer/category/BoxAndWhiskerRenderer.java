/*      */ package org.jfree.chart.renderer.category;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.Ellipse2D;
/*      */ import java.awt.geom.Ellipse2D.Double;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Point2D;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.io.Serializable;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collections;
/*      */ import java.util.Iterator;
/*      */ import java.util.List;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.CategoryAxis;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*      */ import org.jfree.chart.plot.CategoryPlot;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.renderer.Outlier;
/*      */ import org.jfree.chart.renderer.OutlierList;
/*      */ import org.jfree.chart.renderer.OutlierListCollection;
/*      */ import org.jfree.data.Range;
/*      */ import org.jfree.data.category.CategoryDataset;
/*      */ import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
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
/*      */ public class BoxAndWhiskerRenderer
/*      */   extends AbstractCategoryItemRenderer
/*      */   implements Cloneable, PublicCloneable, Serializable
/*      */ {
/*      */   private static final long serialVersionUID = 632027470694481177L;
/*      */   private transient Paint artifactPaint;
/*      */   private boolean fillBox;
/*      */   private double itemMargin;
/*      */   private double maximumBarWidth;
/*      */   private boolean medianVisible;
/*      */   private boolean meanVisible;
/*      */   
/*      */   public BoxAndWhiskerRenderer()
/*      */   {
/*  167 */     this.artifactPaint = Color.black;
/*  168 */     this.fillBox = true;
/*  169 */     this.itemMargin = 0.2D;
/*  170 */     this.maximumBarWidth = 1.0D;
/*  171 */     this.medianVisible = true;
/*  172 */     this.meanVisible = true;
/*  173 */     setBaseLegendShape(new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getArtifactPaint()
/*      */   {
/*  185 */     return this.artifactPaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setArtifactPaint(Paint paint)
/*      */   {
/*  197 */     if (paint == null) {
/*  198 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  200 */     this.artifactPaint = paint;
/*  201 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getFillBox()
/*      */   {
/*  212 */     return this.fillBox;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setFillBox(boolean flag)
/*      */   {
/*  224 */     this.fillBox = flag;
/*  225 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public double getItemMargin()
/*      */   {
/*  237 */     return this.itemMargin;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setItemMargin(double margin)
/*      */   {
/*  249 */     this.itemMargin = margin;
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
/*      */ 
/*      */ 
/*      */   public double getMaximumBarWidth()
/*      */   {
/*  264 */     return this.maximumBarWidth;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMaximumBarWidth(double percent)
/*      */   {
/*  279 */     this.maximumBarWidth = percent;
/*  280 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isMeanVisible()
/*      */   {
/*  294 */     return this.meanVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMeanVisible(boolean visible)
/*      */   {
/*  309 */     if (this.meanVisible == visible) {
/*  310 */       return;
/*      */     }
/*  312 */     this.meanVisible = visible;
/*  313 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean isMedianVisible()
/*      */   {
/*  327 */     return this.medianVisible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setMedianVisible(boolean visible)
/*      */   {
/*  342 */     this.medianVisible = visible;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/*  355 */     CategoryPlot cp = getPlot();
/*  356 */     if (cp == null) {
/*  357 */       return null;
/*      */     }
/*      */     
/*      */ 
/*  361 */     if ((!isSeriesVisible(series)) || (!isSeriesVisibleInLegend(series))) {
/*  362 */       return null;
/*      */     }
/*      */     
/*  365 */     CategoryDataset dataset = cp.getDataset(datasetIndex);
/*  366 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*      */     
/*  368 */     String description = label;
/*  369 */     String toolTipText = null;
/*  370 */     if (getLegendItemToolTipGenerator() != null) {
/*  371 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */     }
/*      */     
/*  374 */     String urlText = null;
/*  375 */     if (getLegendItemURLGenerator() != null) {
/*  376 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */     }
/*      */     
/*  379 */     Shape shape = lookupLegendShape(series);
/*  380 */     Paint paint = lookupSeriesPaint(series);
/*  381 */     Paint outlinePaint = lookupSeriesOutlinePaint(series);
/*  382 */     Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*  383 */     LegendItem result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*      */     
/*  385 */     result.setLabelFont(lookupLegendTextFont(series));
/*  386 */     Paint labelPaint = lookupLegendTextPaint(series);
/*  387 */     if (labelPaint != null) {
/*  388 */       result.setLabelPaint(labelPaint);
/*      */     }
/*  390 */     result.setDataset(dataset);
/*  391 */     result.setDatasetIndex(datasetIndex);
/*  392 */     result.setSeriesKey(dataset.getRowKey(series));
/*  393 */     result.setSeriesIndex(series);
/*  394 */     return result;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Range findRangeBounds(CategoryDataset dataset)
/*      */   {
/*  407 */     return super.findRangeBounds(dataset, true);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info)
/*      */   {
/*  428 */     CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
/*      */     
/*      */ 
/*  431 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/*  432 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/*  433 */     if (dataset != null) {
/*  434 */       int columns = dataset.getColumnCount();
/*  435 */       int rows = dataset.getRowCount();
/*  436 */       double space = 0.0D;
/*  437 */       PlotOrientation orientation = plot.getOrientation();
/*  438 */       if (orientation == PlotOrientation.HORIZONTAL) {
/*  439 */         space = dataArea.getHeight();
/*      */       }
/*  441 */       else if (orientation == PlotOrientation.VERTICAL) {
/*  442 */         space = dataArea.getWidth();
/*      */       }
/*  444 */       double maxWidth = space * getMaximumBarWidth();
/*  445 */       double categoryMargin = 0.0D;
/*  446 */       double currentItemMargin = 0.0D;
/*  447 */       if (columns > 1) {
/*  448 */         categoryMargin = domainAxis.getCategoryMargin();
/*      */       }
/*  450 */       if (rows > 1) {
/*  451 */         currentItemMargin = getItemMargin();
/*      */       }
/*  453 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
/*      */       
/*      */ 
/*  456 */       if (rows * columns > 0) {
/*  457 */         state.setBarWidth(Math.min(used / (dataset.getColumnCount() * dataset.getRowCount()), maxWidth));
/*      */       }
/*      */       else
/*      */       {
/*  461 */         state.setBarWidth(Math.min(used, maxWidth));
/*      */       }
/*      */     }
/*  464 */     return state;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*      */   {
/*  495 */     if (!getItemVisible(row, column)) {
/*  496 */       return;
/*      */     }
/*      */     
/*  499 */     if (!(dataset instanceof BoxAndWhiskerCategoryDataset)) {
/*  500 */       throw new IllegalArgumentException("BoxAndWhiskerRenderer.drawItem() : the data should be of type BoxAndWhiskerCategoryDataset only.");
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  505 */     PlotOrientation orientation = plot.getOrientation();
/*      */     
/*  507 */     if (orientation == PlotOrientation.HORIZONTAL) {
/*  508 */       drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column);
/*      */ 
/*      */     }
/*  511 */     else if (orientation == PlotOrientation.VERTICAL) {
/*  512 */       drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column);
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
/*      */ 
/*      */ 
/*      */   public void drawHorizontalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column)
/*      */   {
/*  544 */     BoxAndWhiskerCategoryDataset bawDataset = (BoxAndWhiskerCategoryDataset)dataset;
/*      */     
/*      */ 
/*  547 */     double categoryEnd = domainAxis.getCategoryEnd(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */     
/*  549 */     double categoryStart = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */     
/*  551 */     double categoryWidth = Math.abs(categoryEnd - categoryStart);
/*      */     
/*  553 */     double yy = categoryStart;
/*  554 */     int seriesCount = getRowCount();
/*  555 */     int categoryCount = getColumnCount();
/*      */     
/*  557 */     if (seriesCount > 1) {
/*  558 */       double seriesGap = dataArea.getHeight() * getItemMargin() / (categoryCount * (seriesCount - 1));
/*      */       
/*  560 */       double usedWidth = state.getBarWidth() * seriesCount + seriesGap * (seriesCount - 1);
/*      */       
/*      */ 
/*      */ 
/*  564 */       double offset = (categoryWidth - usedWidth) / 2.0D;
/*  565 */       yy = yy + offset + row * (state.getBarWidth() + seriesGap);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  570 */       double offset = (categoryWidth - state.getBarWidth()) / 2.0D;
/*  571 */       yy += offset;
/*      */     }
/*      */     
/*  574 */     g2.setPaint(getItemPaint(row, column));
/*  575 */     Stroke s = getItemStroke(row, column);
/*  576 */     g2.setStroke(s);
/*      */     
/*  578 */     RectangleEdge location = plot.getRangeAxisEdge();
/*      */     
/*  580 */     Number xQ1 = bawDataset.getQ1Value(row, column);
/*  581 */     Number xQ3 = bawDataset.getQ3Value(row, column);
/*  582 */     Number xMax = bawDataset.getMaxRegularValue(row, column);
/*  583 */     Number xMin = bawDataset.getMinRegularValue(row, column);
/*      */     
/*  585 */     Shape box = null;
/*  586 */     if ((xQ1 != null) && (xQ3 != null) && (xMax != null) && (xMin != null))
/*      */     {
/*  588 */       double xxQ1 = rangeAxis.valueToJava2D(xQ1.doubleValue(), dataArea, location);
/*      */       
/*  590 */       double xxQ3 = rangeAxis.valueToJava2D(xQ3.doubleValue(), dataArea, location);
/*      */       
/*  592 */       double xxMax = rangeAxis.valueToJava2D(xMax.doubleValue(), dataArea, location);
/*      */       
/*  594 */       double xxMin = rangeAxis.valueToJava2D(xMin.doubleValue(), dataArea, location);
/*      */       
/*  596 */       double yymid = yy + state.getBarWidth() / 2.0D;
/*      */       
/*      */ 
/*  599 */       g2.draw(new Line2D.Double(xxMax, yymid, xxQ3, yymid));
/*  600 */       g2.draw(new Line2D.Double(xxMax, yy, xxMax, yy + state.getBarWidth()));
/*      */       
/*      */ 
/*      */ 
/*  604 */       g2.draw(new Line2D.Double(xxMin, yymid, xxQ1, yymid));
/*  605 */       g2.draw(new Line2D.Double(xxMin, yy, xxMin, yy + state.getBarWidth()));
/*      */       
/*      */ 
/*      */ 
/*  609 */       box = new Rectangle2D.Double(Math.min(xxQ1, xxQ3), yy, Math.abs(xxQ1 - xxQ3), state.getBarWidth());
/*      */       
/*  611 */       if (this.fillBox) {
/*  612 */         g2.fill(box);
/*      */       }
/*  614 */       g2.setStroke(getItemOutlineStroke(row, column));
/*  615 */       g2.setPaint(getItemOutlinePaint(row, column));
/*  616 */       g2.draw(box);
/*      */     }
/*      */     
/*      */ 
/*  620 */     g2.setPaint(this.artifactPaint);
/*  621 */     double aRadius = 0.0D;
/*  622 */     if (this.meanVisible) {
/*  623 */       Number xMean = bawDataset.getMeanValue(row, column);
/*  624 */       if (xMean != null) {
/*  625 */         double xxMean = rangeAxis.valueToJava2D(xMean.doubleValue(), dataArea, location);
/*      */         
/*  627 */         aRadius = state.getBarWidth() / 4.0D;
/*      */         
/*      */ 
/*  630 */         if ((xxMean > dataArea.getMinX() - aRadius) && (xxMean < dataArea.getMaxX() + aRadius))
/*      */         {
/*  632 */           Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xxMean - aRadius, yy + aRadius, aRadius * 2.0D, aRadius * 2.0D);
/*      */           
/*  634 */           g2.fill(avgEllipse);
/*  635 */           g2.draw(avgEllipse);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  641 */     if (this.medianVisible) {
/*  642 */       Number xMedian = bawDataset.getMedianValue(row, column);
/*  643 */       if (xMedian != null) {
/*  644 */         double xxMedian = rangeAxis.valueToJava2D(xMedian.doubleValue(), dataArea, location);
/*      */         
/*  646 */         g2.draw(new Line2D.Double(xxMedian, yy, xxMedian, yy + state.getBarWidth()));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  652 */     if ((state.getInfo() != null) && (box != null)) {
/*  653 */       EntityCollection entities = state.getEntityCollection();
/*  654 */       if (entities != null) {
/*  655 */         addItemEntity(entities, dataset, row, column, box);
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void drawVerticalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column)
/*      */   {
/*  687 */     BoxAndWhiskerCategoryDataset bawDataset = (BoxAndWhiskerCategoryDataset)dataset;
/*      */     
/*      */ 
/*  690 */     double categoryEnd = domainAxis.getCategoryEnd(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */     
/*  692 */     double categoryStart = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*      */     
/*  694 */     double categoryWidth = categoryEnd - categoryStart;
/*      */     
/*  696 */     double xx = categoryStart;
/*  697 */     int seriesCount = getRowCount();
/*  698 */     int categoryCount = getColumnCount();
/*      */     
/*  700 */     if (seriesCount > 1) {
/*  701 */       double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/*      */       
/*  703 */       double usedWidth = state.getBarWidth() * seriesCount + seriesGap * (seriesCount - 1);
/*      */       
/*      */ 
/*      */ 
/*  707 */       double offset = (categoryWidth - usedWidth) / 2.0D;
/*  708 */       xx = xx + offset + row * (state.getBarWidth() + seriesGap);
/*      */ 
/*      */     }
/*      */     else
/*      */     {
/*  713 */       double offset = (categoryWidth - state.getBarWidth()) / 2.0D;
/*  714 */       xx += offset;
/*      */     }
/*      */     
/*  717 */     double yyAverage = 0.0D;
/*      */     
/*      */ 
/*  720 */     Paint itemPaint = getItemPaint(row, column);
/*  721 */     g2.setPaint(itemPaint);
/*  722 */     Stroke s = getItemStroke(row, column);
/*  723 */     g2.setStroke(s);
/*      */     
/*  725 */     double aRadius = 0.0D;
/*      */     
/*  727 */     RectangleEdge location = plot.getRangeAxisEdge();
/*      */     
/*  729 */     Number yQ1 = bawDataset.getQ1Value(row, column);
/*  730 */     Number yQ3 = bawDataset.getQ3Value(row, column);
/*  731 */     Number yMax = bawDataset.getMaxRegularValue(row, column);
/*  732 */     Number yMin = bawDataset.getMinRegularValue(row, column);
/*  733 */     Shape box = null;
/*  734 */     if ((yQ1 != null) && (yQ3 != null) && (yMax != null) && (yMin != null))
/*      */     {
/*  736 */       double yyQ1 = rangeAxis.valueToJava2D(yQ1.doubleValue(), dataArea, location);
/*      */       
/*  738 */       double yyQ3 = rangeAxis.valueToJava2D(yQ3.doubleValue(), dataArea, location);
/*      */       
/*  740 */       double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, location);
/*      */       
/*  742 */       double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, location);
/*      */       
/*  744 */       double xxmid = xx + state.getBarWidth() / 2.0D;
/*      */       
/*      */ 
/*  747 */       g2.draw(new Line2D.Double(xxmid, yyMax, xxmid, yyQ3));
/*  748 */       g2.draw(new Line2D.Double(xx, yyMax, xx + state.getBarWidth(), yyMax));
/*      */       
/*      */ 
/*      */ 
/*  752 */       g2.draw(new Line2D.Double(xxmid, yyMin, xxmid, yyQ1));
/*  753 */       g2.draw(new Line2D.Double(xx, yyMin, xx + state.getBarWidth(), yyMin));
/*      */       
/*      */ 
/*      */ 
/*  757 */       box = new Rectangle2D.Double(xx, Math.min(yyQ1, yyQ3), state.getBarWidth(), Math.abs(yyQ1 - yyQ3));
/*      */       
/*  759 */       if (this.fillBox) {
/*  760 */         g2.fill(box);
/*      */       }
/*  762 */       g2.setStroke(getItemOutlineStroke(row, column));
/*  763 */       g2.setPaint(getItemOutlinePaint(row, column));
/*  764 */       g2.draw(box);
/*      */     }
/*      */     
/*  767 */     g2.setPaint(this.artifactPaint);
/*      */     
/*      */ 
/*  770 */     if (this.meanVisible) {
/*  771 */       Number yMean = bawDataset.getMeanValue(row, column);
/*  772 */       if (yMean != null) {
/*  773 */         yyAverage = rangeAxis.valueToJava2D(yMean.doubleValue(), dataArea, location);
/*      */         
/*  775 */         aRadius = state.getBarWidth() / 4.0D;
/*      */         
/*      */ 
/*  778 */         if ((yyAverage > dataArea.getMinY() - aRadius) && (yyAverage < dataArea.getMaxY() + aRadius))
/*      */         {
/*  780 */           Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xx + aRadius, yyAverage - aRadius, aRadius * 2.0D, aRadius * 2.0D);
/*      */           
/*      */ 
/*  783 */           g2.fill(avgEllipse);
/*  784 */           g2.draw(avgEllipse);
/*      */         }
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  790 */     if (this.medianVisible) {
/*  791 */       Number yMedian = bawDataset.getMedianValue(row, column);
/*  792 */       if (yMedian != null) {
/*  793 */         double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), dataArea, location);
/*      */         
/*  795 */         g2.draw(new Line2D.Double(xx, yyMedian, xx + state.getBarWidth(), yyMedian));
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  801 */     double maxAxisValue = rangeAxis.valueToJava2D(rangeAxis.getUpperBound(), dataArea, location) + aRadius;
/*      */     
/*  803 */     double minAxisValue = rangeAxis.valueToJava2D(rangeAxis.getLowerBound(), dataArea, location) - aRadius;
/*      */     
/*      */ 
/*  806 */     g2.setPaint(itemPaint);
/*      */     
/*      */ 
/*  809 */     double oRadius = state.getBarWidth() / 3.0D;
/*  810 */     List outliers = new ArrayList();
/*  811 */     OutlierListCollection outlierListCollection = new OutlierListCollection();
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*  817 */     List yOutliers = bawDataset.getOutliers(row, column);
/*  818 */     if (yOutliers != null) {
/*  819 */       for (int i = 0; i < yOutliers.size(); i++) {
/*  820 */         double outlier = ((Number)yOutliers.get(i)).doubleValue();
/*  821 */         Number minOutlier = bawDataset.getMinOutlier(row, column);
/*  822 */         Number maxOutlier = bawDataset.getMaxOutlier(row, column);
/*  823 */         Number minRegular = bawDataset.getMinRegularValue(row, column);
/*  824 */         Number maxRegular = bawDataset.getMaxRegularValue(row, column);
/*  825 */         if (outlier > maxOutlier.doubleValue()) {
/*  826 */           outlierListCollection.setHighFarOut(true);
/*      */         }
/*  828 */         else if (outlier < minOutlier.doubleValue()) {
/*  829 */           outlierListCollection.setLowFarOut(true);
/*      */         }
/*  831 */         else if (outlier > maxRegular.doubleValue()) {
/*  832 */           double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/*      */           
/*  834 */           outliers.add(new Outlier(xx + state.getBarWidth() / 2.0D, yyOutlier, oRadius));
/*      */ 
/*      */         }
/*  837 */         else if (outlier < minRegular.doubleValue()) {
/*  838 */           double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/*      */           
/*  840 */           outliers.add(new Outlier(xx + state.getBarWidth() / 2.0D, yyOutlier, oRadius));
/*      */         }
/*      */         
/*  843 */         Collections.sort(outliers);
/*      */       }
/*      */       
/*      */ 
/*      */ 
/*  848 */       for (Iterator iterator = outliers.iterator(); iterator.hasNext();) {
/*  849 */         Outlier outlier = (Outlier)iterator.next();
/*  850 */         outlierListCollection.add(outlier);
/*      */       }
/*      */       
/*  853 */       Iterator iterator = outlierListCollection.iterator();
/*  854 */       while (iterator.hasNext()) {
/*  855 */         OutlierList list = (OutlierList)iterator.next();
/*  856 */         Outlier outlier = list.getAveragedOutlier();
/*  857 */         Point2D point = outlier.getPoint();
/*      */         
/*  859 */         if (list.isMultiple()) {
/*  860 */           drawMultipleEllipse(point, state.getBarWidth(), oRadius, g2);
/*      */         }
/*      */         else
/*      */         {
/*  864 */           drawEllipse(point, oRadius, g2);
/*      */         }
/*      */       }
/*      */       
/*      */ 
/*  869 */       if (outlierListCollection.isHighFarOut()) {
/*  870 */         drawHighFarOut(aRadius / 2.0D, g2, xx + state.getBarWidth() / 2.0D, maxAxisValue);
/*      */       }
/*      */       
/*      */ 
/*  874 */       if (outlierListCollection.isLowFarOut()) {
/*  875 */         drawLowFarOut(aRadius / 2.0D, g2, xx + state.getBarWidth() / 2.0D, minAxisValue);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*  880 */     if ((state.getInfo() != null) && (box != null)) {
/*  881 */       EntityCollection entities = state.getEntityCollection();
/*  882 */       if (entities != null) {
/*  883 */         addItemEntity(entities, dataset, row, column, box);
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
/*      */   private void drawEllipse(Point2D point, double oRadius, Graphics2D g2)
/*      */   {
/*  897 */     Ellipse2D dot = new Ellipse2D.Double(point.getX() + oRadius / 2.0D, point.getY(), oRadius, oRadius);
/*      */     
/*  899 */     g2.draw(dot);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void drawMultipleEllipse(Point2D point, double boxWidth, double oRadius, Graphics2D g2)
/*      */   {
/*  913 */     Ellipse2D dot1 = new Ellipse2D.Double(point.getX() - boxWidth / 2.0D + oRadius, point.getY(), oRadius, oRadius);
/*      */     
/*  915 */     Ellipse2D dot2 = new Ellipse2D.Double(point.getX() + boxWidth / 2.0D, point.getY(), oRadius, oRadius);
/*      */     
/*  917 */     g2.draw(dot1);
/*  918 */     g2.draw(dot2);
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void drawHighFarOut(double aRadius, Graphics2D g2, double xx, double m)
/*      */   {
/*  931 */     double side = aRadius * 2.0D;
/*  932 */     g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
/*  933 */     g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
/*  934 */     g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   private void drawLowFarOut(double aRadius, Graphics2D g2, double xx, double m)
/*      */   {
/*  947 */     double side = aRadius * 2.0D;
/*  948 */     g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
/*  949 */     g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
/*  950 */     g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
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
/*  961 */     if (obj == this) {
/*  962 */       return true;
/*      */     }
/*  964 */     if (!(obj instanceof BoxAndWhiskerRenderer)) {
/*  965 */       return false;
/*      */     }
/*  967 */     BoxAndWhiskerRenderer that = (BoxAndWhiskerRenderer)obj;
/*  968 */     if (this.fillBox != that.fillBox) {
/*  969 */       return false;
/*      */     }
/*  971 */     if (this.itemMargin != that.itemMargin) {
/*  972 */       return false;
/*      */     }
/*  974 */     if (this.maximumBarWidth != that.maximumBarWidth) {
/*  975 */       return false;
/*      */     }
/*  977 */     if (this.meanVisible != that.meanVisible) {
/*  978 */       return false;
/*      */     }
/*  980 */     if (this.medianVisible != that.medianVisible) {
/*  981 */       return false;
/*      */     }
/*  983 */     if (!PaintUtilities.equal(this.artifactPaint, that.artifactPaint)) {
/*  984 */       return false;
/*      */     }
/*  986 */     return super.equals(obj);
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
/*  997 */     stream.defaultWriteObject();
/*  998 */     SerialUtilities.writePaint(this.artifactPaint, stream);
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
/* 1011 */     stream.defaultReadObject();
/* 1012 */     this.artifactPaint = SerialUtilities.readPaint(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/BoxAndWhiskerRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */