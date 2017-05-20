/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.HighLowItemLabelGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.OHLCDataset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CandlestickRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 50390395841817121L;
/*     */   public static final int WIDTHMETHOD_AVERAGE = 0;
/*     */   public static final int WIDTHMETHOD_SMALLEST = 1;
/*     */   public static final int WIDTHMETHOD_INTERVALDATA = 2;
/* 150 */   private int autoWidthMethod = 0;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 157 */   private double autoWidthFactor = 0.6428571428571429D;
/*     */   
/*     */ 
/* 160 */   private double autoWidthGap = 0.0D;
/*     */   
/*     */ 
/*     */   private double candleWidth;
/*     */   
/*     */ 
/* 166 */   private double maxCandleWidthInMilliseconds = 7.2E7D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private double maxCandleWidth;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint upPaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint downPaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean drawVolume;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient Paint volumePaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private transient double maxVolume;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private boolean useOutlinePaint;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CandlestickRenderer()
/*     */   {
/* 210 */     this(-1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CandlestickRenderer(double candleWidth)
/*     */   {
/* 222 */     this(candleWidth, true, new HighLowItemLabelGenerator());
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
/*     */   public CandlestickRenderer(double candleWidth, boolean drawVolume, XYToolTipGenerator toolTipGenerator)
/*     */   {
/* 240 */     setBaseToolTipGenerator(toolTipGenerator);
/* 241 */     this.candleWidth = candleWidth;
/* 242 */     this.drawVolume = drawVolume;
/* 243 */     this.volumePaint = Color.gray;
/* 244 */     this.upPaint = Color.green;
/* 245 */     this.downPaint = Color.red;
/* 246 */     this.useOutlinePaint = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getCandleWidth()
/*     */   {
/* 258 */     return this.candleWidth;
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
/*     */   public void setCandleWidth(double width)
/*     */   {
/* 275 */     if (width != this.candleWidth) {
/* 276 */       this.candleWidth = width;
/* 277 */       fireChangeEvent();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaxCandleWidthInMilliseconds()
/*     */   {
/* 289 */     return this.maxCandleWidthInMilliseconds;
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
/*     */   public void setMaxCandleWidthInMilliseconds(double millis)
/*     */   {
/* 305 */     this.maxCandleWidthInMilliseconds = millis;
/* 306 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getAutoWidthMethod()
/*     */   {
/* 317 */     return this.autoWidthMethod;
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
/*     */   public void setAutoWidthMethod(int autoWidthMethod)
/*     */   {
/* 347 */     if (this.autoWidthMethod != autoWidthMethod) {
/* 348 */       this.autoWidthMethod = autoWidthMethod;
/* 349 */       fireChangeEvent();
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
/*     */   public double getAutoWidthFactor()
/*     */   {
/* 363 */     return this.autoWidthFactor;
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
/*     */   public void setAutoWidthFactor(double autoWidthFactor)
/*     */   {
/* 379 */     if (this.autoWidthFactor != autoWidthFactor) {
/* 380 */       this.autoWidthFactor = autoWidthFactor;
/* 381 */       fireChangeEvent();
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
/*     */   public double getAutoWidthGap()
/*     */   {
/* 394 */     return this.autoWidthGap;
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
/*     */   public void setAutoWidthGap(double autoWidthGap)
/*     */   {
/* 411 */     if (this.autoWidthGap != autoWidthGap) {
/* 412 */       this.autoWidthGap = autoWidthGap;
/* 413 */       fireChangeEvent();
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
/*     */   public Paint getUpPaint()
/*     */   {
/* 426 */     return this.upPaint;
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
/*     */   public void setUpPaint(Paint paint)
/*     */   {
/* 439 */     this.upPaint = paint;
/* 440 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getDownPaint()
/*     */   {
/* 452 */     return this.downPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDownPaint(Paint paint)
/*     */   {
/* 463 */     this.downPaint = paint;
/* 464 */     fireChangeEvent();
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
/*     */   public boolean getDrawVolume()
/*     */   {
/* 478 */     return this.drawVolume;
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
/*     */   public void setDrawVolume(boolean flag)
/*     */   {
/* 491 */     if (this.drawVolume != flag) {
/* 492 */       this.drawVolume = flag;
/* 493 */       fireChangeEvent();
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
/*     */   public Paint getVolumePaint()
/*     */   {
/* 508 */     return this.volumePaint;
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
/*     */   public void setVolumePaint(Paint paint)
/*     */   {
/* 523 */     if (paint == null) {
/* 524 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 526 */     this.volumePaint = paint;
/* 527 */     fireChangeEvent();
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
/*     */   public boolean getUseOutlinePaint()
/*     */   {
/* 542 */     return this.useOutlinePaint;
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
/*     */   public void setUseOutlinePaint(boolean use)
/*     */   {
/* 557 */     if (this.useOutlinePaint != use) {
/* 558 */       this.useOutlinePaint = use;
/* 559 */       fireChangeEvent();
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 573 */     return findRangeBounds(dataset, true);
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset dataset, PlotRenderingInfo info)
/*     */   {
/* 599 */     ValueAxis axis = plot.getDomainAxis();
/* 600 */     double x1 = axis.getLowerBound();
/* 601 */     double x2 = x1 + this.maxCandleWidthInMilliseconds;
/* 602 */     RectangleEdge edge = plot.getDomainAxisEdge();
/* 603 */     double xx1 = axis.valueToJava2D(x1, dataArea, edge);
/* 604 */     double xx2 = axis.valueToJava2D(x2, dataArea, edge);
/* 605 */     this.maxCandleWidth = Math.abs(xx2 - xx1);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 610 */     if (this.drawVolume) {
/* 611 */       OHLCDataset highLowDataset = (OHLCDataset)dataset;
/* 612 */       this.maxVolume = 0.0D;
/* 613 */       for (int series = 0; series < highLowDataset.getSeriesCount(); 
/* 614 */           series++) {
/* 615 */         for (int item = 0; item < highLowDataset.getItemCount(series); 
/* 616 */             item++) {
/* 617 */           double volume = highLowDataset.getVolumeValue(series, item);
/* 618 */           if (volume > this.maxVolume) {
/* 619 */             this.maxVolume = volume;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 626 */     return new XYItemRendererState(info);
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 661 */     PlotOrientation orientation = plot.getOrientation();
/* 662 */     boolean horiz; if (orientation == PlotOrientation.HORIZONTAL) {
/* 663 */       horiz = true;
/*     */     } else { boolean horiz;
/* 665 */       if (orientation == PlotOrientation.VERTICAL) {
/* 666 */         horiz = false;
/*     */       } else {
/*     */         return;
/*     */       }
/*     */     }
/*     */     
/*     */     boolean horiz;
/* 673 */     EntityCollection entities = null;
/* 674 */     if (info != null) {
/* 675 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 678 */     OHLCDataset highLowData = (OHLCDataset)dataset;
/*     */     
/* 680 */     double x = highLowData.getXValue(series, item);
/* 681 */     double yHigh = highLowData.getHighValue(series, item);
/* 682 */     double yLow = highLowData.getLowValue(series, item);
/* 683 */     double yOpen = highLowData.getOpenValue(series, item);
/* 684 */     double yClose = highLowData.getCloseValue(series, item);
/*     */     
/* 686 */     RectangleEdge domainEdge = plot.getDomainAxisEdge();
/* 687 */     double xx = domainAxis.valueToJava2D(x, dataArea, domainEdge);
/*     */     
/* 689 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 690 */     double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, edge);
/* 691 */     double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, edge);
/* 692 */     double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea, edge);
/* 693 */     double yyClose = rangeAxis.valueToJava2D(yClose, dataArea, edge);
/*     */     double stickWidth;
/*     */     double volumeWidth;
/*     */     double stickWidth;
/* 697 */     if (this.candleWidth > 0.0D)
/*     */     {
/*     */ 
/* 700 */       double volumeWidth = this.candleWidth;
/* 701 */       stickWidth = this.candleWidth;
/*     */     }
/*     */     else {
/* 704 */       double xxWidth = 0.0D;
/*     */       int itemCount;
/* 706 */       switch (this.autoWidthMethod)
/*     */       {
/*     */       case 0: 
/* 709 */         itemCount = highLowData.getItemCount(series);
/* 710 */         if (horiz) {
/* 711 */           xxWidth = dataArea.getHeight() / itemCount;
/*     */         }
/*     */         else {
/* 714 */           xxWidth = dataArea.getWidth() / itemCount;
/*     */         }
/* 716 */         break;
/*     */       
/*     */ 
/*     */       case 1: 
/* 720 */         itemCount = highLowData.getItemCount(series);
/* 721 */         double lastPos = -1.0D;
/* 722 */         xxWidth = dataArea.getWidth();
/* 723 */         for (int i = 0; i < itemCount; i++) {
/* 724 */           double pos = domainAxis.valueToJava2D(highLowData.getXValue(series, i), dataArea, domainEdge);
/*     */           
/*     */ 
/* 727 */           if (lastPos != -1.0D) {
/* 728 */             xxWidth = Math.min(xxWidth, Math.abs(pos - lastPos));
/*     */           }
/*     */           
/* 731 */           lastPos = pos;
/*     */         }
/* 733 */         break;
/*     */       
/*     */       case 2: 
/* 736 */         IntervalXYDataset intervalXYData = (IntervalXYDataset)dataset;
/*     */         
/* 738 */         double startPos = domainAxis.valueToJava2D(intervalXYData.getStartXValue(series, item), dataArea, plot.getDomainAxisEdge());
/*     */         
/*     */ 
/* 741 */         double endPos = domainAxis.valueToJava2D(intervalXYData.getEndXValue(series, item), dataArea, plot.getDomainAxisEdge());
/*     */         
/*     */ 
/* 744 */         xxWidth = Math.abs(endPos - startPos);
/*     */       }
/*     */       
/*     */       
/* 748 */       xxWidth -= 2.0D * this.autoWidthGap;
/* 749 */       xxWidth *= this.autoWidthFactor;
/* 750 */       xxWidth = Math.min(xxWidth, this.maxCandleWidth);
/* 751 */       volumeWidth = Math.max(Math.min(1.0D, this.maxCandleWidth), xxWidth);
/* 752 */       stickWidth = Math.max(Math.min(3.0D, this.maxCandleWidth), xxWidth);
/*     */     }
/*     */     
/* 755 */     Paint p = getItemPaint(series, item);
/* 756 */     Paint outlinePaint = null;
/* 757 */     if (this.useOutlinePaint) {
/* 758 */       outlinePaint = getItemOutlinePaint(series, item);
/*     */     }
/* 760 */     Stroke s = getItemStroke(series, item);
/*     */     
/* 762 */     g2.setStroke(s);
/*     */     
/* 764 */     if (this.drawVolume) {
/* 765 */       int volume = (int)highLowData.getVolumeValue(series, item);
/* 766 */       double volumeHeight = volume / this.maxVolume;
/*     */       double max;
/*     */       double min;
/* 769 */       double max; if (horiz) {
/* 770 */         double min = dataArea.getMinX();
/* 771 */         max = dataArea.getMaxX();
/*     */       }
/*     */       else {
/* 774 */         min = dataArea.getMinY();
/* 775 */         max = dataArea.getMaxY();
/*     */       }
/*     */       
/* 778 */       double zzVolume = volumeHeight * (max - min);
/*     */       
/* 780 */       g2.setPaint(getVolumePaint());
/* 781 */       Composite originalComposite = g2.getComposite();
/* 782 */       g2.setComposite(AlphaComposite.getInstance(3, 0.3F));
/*     */       
/*     */ 
/* 785 */       if (horiz) {
/* 786 */         g2.fill(new Rectangle2D.Double(min, xx - volumeWidth / 2.0D, zzVolume, volumeWidth));
/*     */       }
/*     */       else
/*     */       {
/* 790 */         g2.fill(new Rectangle2D.Double(xx - volumeWidth / 2.0D, max - zzVolume, volumeWidth, zzVolume));
/*     */       }
/*     */       
/*     */ 
/* 794 */       g2.setComposite(originalComposite);
/*     */     }
/*     */     
/* 797 */     if (this.useOutlinePaint) {
/* 798 */       g2.setPaint(outlinePaint);
/*     */     }
/*     */     else {
/* 801 */       g2.setPaint(p);
/*     */     }
/*     */     
/* 804 */     double yyMaxOpenClose = Math.max(yyOpen, yyClose);
/* 805 */     double yyMinOpenClose = Math.min(yyOpen, yyClose);
/* 806 */     double maxOpenClose = Math.max(yOpen, yClose);
/* 807 */     double minOpenClose = Math.min(yOpen, yClose);
/*     */     
/*     */ 
/* 810 */     if (yHigh > maxOpenClose) {
/* 811 */       if (horiz) {
/* 812 */         g2.draw(new Line2D.Double(yyHigh, xx, yyMaxOpenClose, xx));
/*     */       }
/*     */       else {
/* 815 */         g2.draw(new Line2D.Double(xx, yyHigh, xx, yyMaxOpenClose));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 820 */     if (yLow < minOpenClose) {
/* 821 */       if (horiz) {
/* 822 */         g2.draw(new Line2D.Double(yyLow, xx, yyMinOpenClose, xx));
/*     */       }
/*     */       else {
/* 825 */         g2.draw(new Line2D.Double(xx, yyLow, xx, yyMinOpenClose));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 830 */     Rectangle2D body = null;
/* 831 */     Rectangle2D hotspot = null;
/* 832 */     double length = Math.abs(yyHigh - yyLow);
/* 833 */     double base = Math.min(yyHigh, yyLow);
/* 834 */     if (horiz) {
/* 835 */       body = new Rectangle2D.Double(yyMinOpenClose, xx - stickWidth / 2.0D, yyMaxOpenClose - yyMinOpenClose, stickWidth);
/*     */       
/* 837 */       hotspot = new Rectangle2D.Double(base, xx - stickWidth / 2.0D, length, stickWidth);
/*     */     }
/*     */     else
/*     */     {
/* 841 */       body = new Rectangle2D.Double(xx - stickWidth / 2.0D, yyMinOpenClose, stickWidth, yyMaxOpenClose - yyMinOpenClose);
/*     */       
/* 843 */       hotspot = new Rectangle2D.Double(xx - stickWidth / 2.0D, base, stickWidth, length);
/*     */     }
/*     */     
/* 846 */     if (yClose > yOpen) {
/* 847 */       if (this.upPaint != null) {
/* 848 */         g2.setPaint(this.upPaint);
/*     */       }
/*     */       else {
/* 851 */         g2.setPaint(p);
/*     */       }
/* 853 */       g2.fill(body);
/*     */     }
/*     */     else {
/* 856 */       if (this.downPaint != null) {
/* 857 */         g2.setPaint(this.downPaint);
/*     */       }
/*     */       else {
/* 860 */         g2.setPaint(p);
/*     */       }
/* 862 */       g2.fill(body);
/*     */     }
/* 864 */     if (this.useOutlinePaint) {
/* 865 */       g2.setPaint(outlinePaint);
/*     */     }
/*     */     else {
/* 868 */       g2.setPaint(p);
/*     */     }
/* 870 */     g2.draw(body);
/*     */     
/*     */ 
/* 873 */     if (entities != null) {
/* 874 */       addEntity(entities, hotspot, dataset, series, item, 0.0D, 0.0D);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 887 */     if (obj == this) {
/* 888 */       return true;
/*     */     }
/* 890 */     if (!(obj instanceof CandlestickRenderer)) {
/* 891 */       return false;
/*     */     }
/* 893 */     CandlestickRenderer that = (CandlestickRenderer)obj;
/* 894 */     if (this.candleWidth != that.candleWidth) {
/* 895 */       return false;
/*     */     }
/* 897 */     if (!PaintUtilities.equal(this.upPaint, that.upPaint)) {
/* 898 */       return false;
/*     */     }
/* 900 */     if (!PaintUtilities.equal(this.downPaint, that.downPaint)) {
/* 901 */       return false;
/*     */     }
/* 903 */     if (this.drawVolume != that.drawVolume) {
/* 904 */       return false;
/*     */     }
/* 906 */     if (this.maxCandleWidthInMilliseconds != that.maxCandleWidthInMilliseconds)
/*     */     {
/* 908 */       return false;
/*     */     }
/* 910 */     if (this.autoWidthMethod != that.autoWidthMethod) {
/* 911 */       return false;
/*     */     }
/* 913 */     if (this.autoWidthFactor != that.autoWidthFactor) {
/* 914 */       return false;
/*     */     }
/* 916 */     if (this.autoWidthGap != that.autoWidthGap) {
/* 917 */       return false;
/*     */     }
/* 919 */     if (this.useOutlinePaint != that.useOutlinePaint) {
/* 920 */       return false;
/*     */     }
/* 922 */     if (!PaintUtilities.equal(this.volumePaint, that.volumePaint)) {
/* 923 */       return false;
/*     */     }
/* 925 */     return super.equals(obj);
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
/* 936 */     return super.clone();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 947 */     stream.defaultWriteObject();
/* 948 */     SerialUtilities.writePaint(this.upPaint, stream);
/* 949 */     SerialUtilities.writePaint(this.downPaint, stream);
/* 950 */     SerialUtilities.writePaint(this.volumePaint, stream);
/*     */   }
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
/* 963 */     stream.defaultReadObject();
/* 964 */     this.upPaint = SerialUtilities.readPaint(stream);
/* 965 */     this.downPaint = SerialUtilities.readPaint(stream);
/* 966 */     this.volumePaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public boolean drawVolume()
/*     */   {
/* 981 */     return this.drawVolume;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/CandlestickRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */