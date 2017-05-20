/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.BoxAndWhiskerXYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.Outlier;
/*     */ import org.jfree.chart.renderer.OutlierList;
/*     */ import org.jfree.chart.renderer.OutlierListCollection;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.statistics.BoxAndWhiskerXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYBoxAndWhiskerRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8020170108532232324L;
/*     */   private double boxWidth;
/*     */   private transient Paint boxPaint;
/*     */   private boolean fillBox;
/* 147 */   private transient Paint artifactPaint = Color.black;
/*     */   
/*     */ 
/*     */ 
/*     */   public XYBoxAndWhiskerRenderer()
/*     */   {
/* 153 */     this(-1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYBoxAndWhiskerRenderer(double boxWidth)
/*     */   {
/* 166 */     this.boxWidth = boxWidth;
/* 167 */     this.boxPaint = Color.green;
/* 168 */     this.fillBox = true;
/* 169 */     setBaseToolTipGenerator(new BoxAndWhiskerXYToolTipGenerator());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBoxWidth()
/*     */   {
/* 180 */     return this.boxWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBoxWidth(double width)
/*     */   {
/* 195 */     if (width != this.boxWidth) {
/* 196 */       this.boxWidth = width;
/* 197 */       fireChangeEvent();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBoxPaint()
/*     */   {
/* 209 */     return this.boxPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBoxPaint(Paint paint)
/*     */   {
/* 221 */     this.boxPaint = paint;
/* 222 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getFillBox()
/*     */   {
/* 233 */     return this.fillBox;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFillBox(boolean flag)
/*     */   {
/* 245 */     this.fillBox = flag;
/* 246 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getArtifactPaint()
/*     */   {
/* 258 */     return this.artifactPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setArtifactPaint(Paint paint)
/*     */   {
/* 271 */     if (paint == null) {
/* 272 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 274 */     this.artifactPaint = paint;
/* 275 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 290 */     return findRangeBounds(dataset, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected Paint lookupBoxPaint(int series, int item)
/*     */   {
/* 306 */     Paint p = getBoxPaint();
/* 307 */     if (p != null) {
/* 308 */       return p;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 313 */     return getItemPaint(series, item);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 349 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 351 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 352 */       drawHorizontalItem(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */ 
/*     */     }
/* 355 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 356 */       drawVerticalItem(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawHorizontalItem(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 393 */     EntityCollection entities = null;
/* 394 */     if (info != null) {
/* 395 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 398 */     BoxAndWhiskerXYDataset boxAndWhiskerData = (BoxAndWhiskerXYDataset)dataset;
/*     */     
/*     */ 
/* 401 */     Number x = boxAndWhiskerData.getX(series, item);
/* 402 */     Number yMax = boxAndWhiskerData.getMaxRegularValue(series, item);
/* 403 */     Number yMin = boxAndWhiskerData.getMinRegularValue(series, item);
/* 404 */     Number yMedian = boxAndWhiskerData.getMedianValue(series, item);
/* 405 */     Number yAverage = boxAndWhiskerData.getMeanValue(series, item);
/* 406 */     Number yQ1Median = boxAndWhiskerData.getQ1Value(series, item);
/* 407 */     Number yQ3Median = boxAndWhiskerData.getQ3Value(series, item);
/*     */     
/* 409 */     double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, plot.getDomainAxisEdge());
/*     */     
/*     */ 
/* 412 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 413 */     double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, location);
/*     */     
/* 415 */     double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, location);
/*     */     
/* 417 */     double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), dataArea, location);
/*     */     
/* 419 */     double yyAverage = 0.0D;
/* 420 */     if (yAverage != null) {
/* 421 */       yyAverage = rangeAxis.valueToJava2D(yAverage.doubleValue(), dataArea, location);
/*     */     }
/*     */     
/* 424 */     double yyQ1Median = rangeAxis.valueToJava2D(yQ1Median.doubleValue(), dataArea, location);
/*     */     
/* 426 */     double yyQ3Median = rangeAxis.valueToJava2D(yQ3Median.doubleValue(), dataArea, location);
/*     */     
/*     */ 
/* 429 */     double exactBoxWidth = getBoxWidth();
/* 430 */     double width = exactBoxWidth;
/* 431 */     double dataAreaX = dataArea.getHeight();
/* 432 */     double maxBoxPercent = 0.1D;
/* 433 */     double maxBoxWidth = dataAreaX * maxBoxPercent;
/* 434 */     if (exactBoxWidth <= 0.0D) {
/* 435 */       int itemCount = boxAndWhiskerData.getItemCount(series);
/* 436 */       exactBoxWidth = dataAreaX / itemCount * 4.5D / 7.0D;
/* 437 */       if (exactBoxWidth < 3.0D) {
/* 438 */         width = 3.0D;
/*     */       }
/* 440 */       else if (exactBoxWidth > maxBoxWidth) {
/* 441 */         width = maxBoxWidth;
/*     */       }
/*     */       else {
/* 444 */         width = exactBoxWidth;
/*     */       }
/*     */     }
/*     */     
/* 448 */     g2.setPaint(getItemPaint(series, item));
/* 449 */     Stroke s = getItemStroke(series, item);
/* 450 */     g2.setStroke(s);
/*     */     
/*     */ 
/* 453 */     g2.draw(new Line2D.Double(yyMax, xx, yyQ3Median, xx));
/* 454 */     g2.draw(new Line2D.Double(yyMax, xx - width / 2.0D, yyMax, xx + width / 2.0D));
/*     */     
/*     */ 
/*     */ 
/* 458 */     g2.draw(new Line2D.Double(yyMin, xx, yyQ1Median, xx));
/* 459 */     g2.draw(new Line2D.Double(yyMin, xx - width / 2.0D, yyMin, xx + width / 2.0D));
/*     */     
/*     */ 
/*     */ 
/* 463 */     Shape box = null;
/* 464 */     if (yyQ1Median < yyQ3Median) {
/* 465 */       box = new Rectangle2D.Double(yyQ1Median, xx - width / 2.0D, yyQ3Median - yyQ1Median, width);
/*     */     }
/*     */     else
/*     */     {
/* 469 */       box = new Rectangle2D.Double(yyQ3Median, xx - width / 2.0D, yyQ1Median - yyQ3Median, width);
/*     */     }
/*     */     
/* 472 */     if (this.fillBox) {
/* 473 */       g2.setPaint(lookupBoxPaint(series, item));
/* 474 */       g2.fill(box);
/*     */     }
/* 476 */     g2.setStroke(getItemOutlineStroke(series, item));
/* 477 */     g2.setPaint(getItemOutlinePaint(series, item));
/* 478 */     g2.draw(box);
/*     */     
/*     */ 
/* 481 */     g2.setPaint(getArtifactPaint());
/* 482 */     g2.draw(new Line2D.Double(yyMedian, xx - width / 2.0D, yyMedian, xx + width / 2.0D));
/*     */     
/*     */ 
/*     */ 
/* 486 */     if (yAverage != null) {
/* 487 */       double aRadius = width / 4.0D;
/*     */       
/*     */ 
/* 490 */       if ((yyAverage > dataArea.getMinX() - aRadius) && (yyAverage < dataArea.getMaxX() + aRadius))
/*     */       {
/* 492 */         Ellipse2D.Double avgEllipse = new Ellipse2D.Double(yyAverage - aRadius, xx - aRadius, aRadius * 2.0D, aRadius * 2.0D);
/*     */         
/*     */ 
/* 495 */         g2.fill(avgEllipse);
/* 496 */         g2.draw(avgEllipse);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 503 */     if ((entities != null) && (box.intersects(dataArea))) {
/* 504 */       addEntity(entities, box, dataset, series, item, yyAverage, xx);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawVerticalItem(Graphics2D g2, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 540 */     EntityCollection entities = null;
/* 541 */     if (info != null) {
/* 542 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 545 */     BoxAndWhiskerXYDataset boxAndWhiskerData = (BoxAndWhiskerXYDataset)dataset;
/*     */     
/*     */ 
/* 548 */     Number x = boxAndWhiskerData.getX(series, item);
/* 549 */     Number yMax = boxAndWhiskerData.getMaxRegularValue(series, item);
/* 550 */     Number yMin = boxAndWhiskerData.getMinRegularValue(series, item);
/* 551 */     Number yMedian = boxAndWhiskerData.getMedianValue(series, item);
/* 552 */     Number yAverage = boxAndWhiskerData.getMeanValue(series, item);
/* 553 */     Number yQ1Median = boxAndWhiskerData.getQ1Value(series, item);
/* 554 */     Number yQ3Median = boxAndWhiskerData.getQ3Value(series, item);
/* 555 */     List yOutliers = boxAndWhiskerData.getOutliers(series, item);
/*     */     
/* 557 */     double xx = domainAxis.valueToJava2D(x.doubleValue(), dataArea, plot.getDomainAxisEdge());
/*     */     
/*     */ 
/* 560 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 561 */     double yyMax = rangeAxis.valueToJava2D(yMax.doubleValue(), dataArea, location);
/*     */     
/* 563 */     double yyMin = rangeAxis.valueToJava2D(yMin.doubleValue(), dataArea, location);
/*     */     
/* 565 */     double yyMedian = rangeAxis.valueToJava2D(yMedian.doubleValue(), dataArea, location);
/*     */     
/* 567 */     double yyAverage = 0.0D;
/* 568 */     if (yAverage != null) {
/* 569 */       yyAverage = rangeAxis.valueToJava2D(yAverage.doubleValue(), dataArea, location);
/*     */     }
/*     */     
/* 572 */     double yyQ1Median = rangeAxis.valueToJava2D(yQ1Median.doubleValue(), dataArea, location);
/*     */     
/* 574 */     double yyQ3Median = rangeAxis.valueToJava2D(yQ3Median.doubleValue(), dataArea, location);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 579 */     double exactBoxWidth = getBoxWidth();
/* 580 */     double width = exactBoxWidth;
/* 581 */     double dataAreaX = dataArea.getMaxX() - dataArea.getMinX();
/* 582 */     double maxBoxPercent = 0.1D;
/* 583 */     double maxBoxWidth = dataAreaX * maxBoxPercent;
/* 584 */     if (exactBoxWidth <= 0.0D) {
/* 585 */       int itemCount = boxAndWhiskerData.getItemCount(series);
/* 586 */       exactBoxWidth = dataAreaX / itemCount * 4.5D / 7.0D;
/* 587 */       if (exactBoxWidth < 3.0D) {
/* 588 */         width = 3.0D;
/*     */       }
/* 590 */       else if (exactBoxWidth > maxBoxWidth) {
/* 591 */         width = maxBoxWidth;
/*     */       }
/*     */       else {
/* 594 */         width = exactBoxWidth;
/*     */       }
/*     */     }
/*     */     
/* 598 */     g2.setPaint(getItemPaint(series, item));
/* 599 */     Stroke s = getItemStroke(series, item);
/* 600 */     g2.setStroke(s);
/*     */     
/*     */ 
/* 603 */     g2.draw(new Line2D.Double(xx, yyMax, xx, yyQ3Median));
/* 604 */     g2.draw(new Line2D.Double(xx - width / 2.0D, yyMax, xx + width / 2.0D, yyMax));
/*     */     
/*     */ 
/*     */ 
/* 608 */     g2.draw(new Line2D.Double(xx, yyMin, xx, yyQ1Median));
/* 609 */     g2.draw(new Line2D.Double(xx - width / 2.0D, yyMin, xx + width / 2.0D, yyMin));
/*     */     
/*     */ 
/*     */ 
/* 613 */     Shape box = null;
/* 614 */     if (yyQ1Median > yyQ3Median) {
/* 615 */       box = new Rectangle2D.Double(xx - width / 2.0D, yyQ3Median, width, yyQ1Median - yyQ3Median);
/*     */     }
/*     */     else
/*     */     {
/* 619 */       box = new Rectangle2D.Double(xx - width / 2.0D, yyQ1Median, width, yyQ3Median - yyQ1Median);
/*     */     }
/*     */     
/* 622 */     if (this.fillBox) {
/* 623 */       g2.setPaint(lookupBoxPaint(series, item));
/* 624 */       g2.fill(box);
/*     */     }
/* 626 */     g2.setStroke(getItemOutlineStroke(series, item));
/* 627 */     g2.setPaint(getItemOutlinePaint(series, item));
/* 628 */     g2.draw(box);
/*     */     
/*     */ 
/* 631 */     g2.setPaint(getArtifactPaint());
/* 632 */     g2.draw(new Line2D.Double(xx - width / 2.0D, yyMedian, xx + width / 2.0D, yyMedian));
/*     */     
/*     */ 
/* 635 */     double aRadius = 0.0D;
/* 636 */     double oRadius = width / 3.0D;
/*     */     
/*     */ 
/* 639 */     if (yAverage != null) {
/* 640 */       aRadius = width / 4.0D;
/*     */       
/*     */ 
/* 643 */       if ((yyAverage > dataArea.getMinY() - aRadius) && (yyAverage < dataArea.getMaxY() + aRadius))
/*     */       {
/* 645 */         Ellipse2D.Double avgEllipse = new Ellipse2D.Double(xx - aRadius, yyAverage - aRadius, aRadius * 2.0D, aRadius * 2.0D);
/*     */         
/* 647 */         g2.fill(avgEllipse);
/* 648 */         g2.draw(avgEllipse);
/*     */       }
/*     */     }
/*     */     
/* 652 */     List outliers = new ArrayList();
/* 653 */     OutlierListCollection outlierListCollection = new OutlierListCollection();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 661 */     for (int i = 0; i < yOutliers.size(); i++) {
/* 662 */       double outlier = ((Number)yOutliers.get(i)).doubleValue();
/* 663 */       if (outlier > boxAndWhiskerData.getMaxOutlier(series, item).doubleValue())
/*     */       {
/* 665 */         outlierListCollection.setHighFarOut(true);
/*     */       }
/* 667 */       else if (outlier < boxAndWhiskerData.getMinOutlier(series, item).doubleValue())
/*     */       {
/* 669 */         outlierListCollection.setLowFarOut(true);
/*     */       }
/* 671 */       else if (outlier > boxAndWhiskerData.getMaxRegularValue(series, item).doubleValue())
/*     */       {
/* 673 */         double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/*     */         
/* 675 */         outliers.add(new Outlier(xx, yyOutlier, oRadius));
/*     */       }
/* 677 */       else if (outlier < boxAndWhiskerData.getMinRegularValue(series, item).doubleValue())
/*     */       {
/* 679 */         double yyOutlier = rangeAxis.valueToJava2D(outlier, dataArea, location);
/*     */         
/* 681 */         outliers.add(new Outlier(xx, yyOutlier, oRadius));
/*     */       }
/* 683 */       Collections.sort(outliers);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 688 */     for (Iterator iterator = outliers.iterator(); iterator.hasNext();) {
/* 689 */       Outlier outlier = (Outlier)iterator.next();
/* 690 */       outlierListCollection.add(outlier);
/*     */     }
/*     */     
/*     */ 
/* 694 */     double maxAxisValue = rangeAxis.valueToJava2D(rangeAxis.getUpperBound(), dataArea, location) + aRadius;
/*     */     
/* 696 */     double minAxisValue = rangeAxis.valueToJava2D(rangeAxis.getLowerBound(), dataArea, location) - aRadius;
/*     */     
/*     */ 
/*     */ 
/* 700 */     Iterator iterator = outlierListCollection.iterator();
/* 701 */     while (iterator.hasNext()) {
/* 702 */       OutlierList list = (OutlierList)iterator.next();
/* 703 */       Outlier outlier = list.getAveragedOutlier();
/* 704 */       Point2D point = outlier.getPoint();
/*     */       
/* 706 */       if (list.isMultiple()) {
/* 707 */         drawMultipleEllipse(point, width, oRadius, g2);
/*     */       }
/*     */       else {
/* 710 */         drawEllipse(point, oRadius, g2);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 715 */     if (outlierListCollection.isHighFarOut()) {
/* 716 */       drawHighFarOut(aRadius, g2, xx, maxAxisValue);
/*     */     }
/*     */     
/* 719 */     if (outlierListCollection.isLowFarOut()) {
/* 720 */       drawLowFarOut(aRadius, g2, xx, minAxisValue);
/*     */     }
/*     */     
/*     */ 
/* 724 */     if ((entities != null) && (box.intersects(dataArea))) {
/* 725 */       addEntity(entities, box, dataset, series, item, xx, yyAverage);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawEllipse(Point2D point, double oRadius, Graphics2D g2)
/*     */   {
/* 738 */     Ellipse2D.Double dot = new Ellipse2D.Double(point.getX() + oRadius / 2.0D, point.getY(), oRadius, oRadius);
/*     */     
/* 740 */     g2.draw(dot);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawMultipleEllipse(Point2D point, double boxWidth, double oRadius, Graphics2D g2)
/*     */   {
/* 754 */     Ellipse2D.Double dot1 = new Ellipse2D.Double(point.getX() - boxWidth / 2.0D + oRadius, point.getY(), oRadius, oRadius);
/*     */     
/* 756 */     Ellipse2D.Double dot2 = new Ellipse2D.Double(point.getX() + boxWidth / 2.0D, point.getY(), oRadius, oRadius);
/*     */     
/* 758 */     g2.draw(dot1);
/* 759 */     g2.draw(dot2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawHighFarOut(double aRadius, Graphics2D g2, double xx, double m)
/*     */   {
/* 773 */     double side = aRadius * 2.0D;
/* 774 */     g2.draw(new Line2D.Double(xx - side, m + side, xx + side, m + side));
/* 775 */     g2.draw(new Line2D.Double(xx - side, m + side, xx, m));
/* 776 */     g2.draw(new Line2D.Double(xx + side, m + side, xx, m));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawLowFarOut(double aRadius, Graphics2D g2, double xx, double m)
/*     */   {
/* 789 */     double side = aRadius * 2.0D;
/* 790 */     g2.draw(new Line2D.Double(xx - side, m - side, xx + side, m - side));
/* 791 */     g2.draw(new Line2D.Double(xx - side, m - side, xx, m));
/* 792 */     g2.draw(new Line2D.Double(xx + side, m - side, xx, m));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object obj)
/*     */   {
/* 803 */     if (obj == this) {
/* 804 */       return true;
/*     */     }
/* 806 */     if (!(obj instanceof XYBoxAndWhiskerRenderer)) {
/* 807 */       return false;
/*     */     }
/* 809 */     if (!super.equals(obj)) {
/* 810 */       return false;
/*     */     }
/* 812 */     XYBoxAndWhiskerRenderer that = (XYBoxAndWhiskerRenderer)obj;
/* 813 */     if (this.boxWidth != that.getBoxWidth()) {
/* 814 */       return false;
/*     */     }
/* 816 */     if (!PaintUtilities.equal(this.boxPaint, that.boxPaint)) {
/* 817 */       return false;
/*     */     }
/* 819 */     if (!PaintUtilities.equal(this.artifactPaint, that.artifactPaint)) {
/* 820 */       return false;
/*     */     }
/* 822 */     if (this.fillBox != that.fillBox) {
/* 823 */       return false;
/*     */     }
/* 825 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 837 */     stream.defaultWriteObject();
/* 838 */     SerialUtilities.writePaint(this.boxPaint, stream);
/* 839 */     SerialUtilities.writePaint(this.artifactPaint, stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 853 */     stream.defaultReadObject();
/* 854 */     this.boxPaint = SerialUtilities.readPaint(stream);
/* 855 */     this.artifactPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 866 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYBoxAndWhiskerRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */