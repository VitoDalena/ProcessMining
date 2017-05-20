/*      */ package org.jfree.chart.renderer.xy;
/*      */ 
/*      */ import java.awt.Color;
/*      */ import java.awt.Graphics2D;
/*      */ import java.awt.Paint;
/*      */ import java.awt.Shape;
/*      */ import java.awt.Stroke;
/*      */ import java.awt.geom.GeneralPath;
/*      */ import java.awt.geom.Line2D;
/*      */ import java.awt.geom.Line2D.Double;
/*      */ import java.awt.geom.Rectangle2D;
/*      */ import java.awt.geom.Rectangle2D.Double;
/*      */ import java.io.IOException;
/*      */ import java.io.ObjectInputStream;
/*      */ import java.io.ObjectOutputStream;
/*      */ import java.util.Collections;
/*      */ import java.util.LinkedList;
/*      */ import org.jfree.chart.ChartRenderingInfo;
/*      */ import org.jfree.chart.LegendItem;
/*      */ import org.jfree.chart.axis.ValueAxis;
/*      */ import org.jfree.chart.entity.EntityCollection;
/*      */ import org.jfree.chart.entity.XYItemEntity;
/*      */ import org.jfree.chart.event.RendererChangeEvent;
/*      */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*      */ import org.jfree.chart.labels.XYToolTipGenerator;
/*      */ import org.jfree.chart.plot.CrosshairState;
/*      */ import org.jfree.chart.plot.PlotOrientation;
/*      */ import org.jfree.chart.plot.PlotRenderingInfo;
/*      */ import org.jfree.chart.plot.XYPlot;
/*      */ import org.jfree.chart.urls.XYURLGenerator;
/*      */ import org.jfree.data.xy.XYDataset;
/*      */ import org.jfree.io.SerialUtilities;
/*      */ import org.jfree.ui.RectangleEdge;
/*      */ import org.jfree.util.PaintUtilities;
/*      */ import org.jfree.util.PublicCloneable;
/*      */ import org.jfree.util.ShapeUtilities;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class XYDifferenceRenderer
/*      */   extends AbstractXYItemRenderer
/*      */   implements XYItemRenderer, PublicCloneable
/*      */ {
/*      */   private static final long serialVersionUID = -8447915602375584857L;
/*      */   private transient Paint positivePaint;
/*      */   private transient Paint negativePaint;
/*      */   private boolean shapesVisible;
/*      */   private transient Shape legendLine;
/*      */   private boolean roundXCoordinates;
/*      */   
/*      */   public XYDifferenceRenderer()
/*      */   {
/*  153 */     this(Color.green, Color.red, false);
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
/*      */   public XYDifferenceRenderer(Paint positivePaint, Paint negativePaint, boolean shapes)
/*      */   {
/*  167 */     if (positivePaint == null) {
/*  168 */       throw new IllegalArgumentException("Null 'positivePaint' argument.");
/*      */     }
/*      */     
/*  171 */     if (negativePaint == null) {
/*  172 */       throw new IllegalArgumentException("Null 'negativePaint' argument.");
/*      */     }
/*      */     
/*  175 */     this.positivePaint = positivePaint;
/*  176 */     this.negativePaint = negativePaint;
/*  177 */     this.shapesVisible = shapes;
/*  178 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*  179 */     this.roundXCoordinates = false;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getPositivePaint()
/*      */   {
/*  190 */     return this.positivePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setPositivePaint(Paint paint)
/*      */   {
/*  202 */     if (paint == null) {
/*  203 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  205 */     this.positivePaint = paint;
/*  206 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Paint getNegativePaint()
/*      */   {
/*  217 */     return this.negativePaint;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setNegativePaint(Paint paint)
/*      */   {
/*  228 */     if (paint == null) {
/*  229 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*      */     }
/*  231 */     this.negativePaint = paint;
/*  232 */     notifyListeners(new RendererChangeEvent(this));
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public boolean getShapesVisible()
/*      */   {
/*  244 */     return this.shapesVisible;
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
/*      */   public void setShapesVisible(boolean flag)
/*      */   {
/*  257 */     this.shapesVisible = flag;
/*  258 */     fireChangeEvent();
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public Shape getLegendLine()
/*      */   {
/*  269 */     return this.legendLine;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public void setLegendLine(Shape line)
/*      */   {
/*  281 */     if (line == null) {
/*  282 */       throw new IllegalArgumentException("Null 'line' argument.");
/*      */     }
/*  284 */     this.legendLine = line;
/*  285 */     fireChangeEvent();
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
/*      */   public boolean getRoundXCoordinates()
/*      */   {
/*  299 */     return this.roundXCoordinates;
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
/*      */   public void setRoundXCoordinates(boolean round)
/*      */   {
/*  314 */     this.roundXCoordinates = round;
/*  315 */     fireChangeEvent();
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
/*      */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*      */   {
/*  340 */     XYItemRendererState state = super.initialise(g2, dataArea, plot, data, info);
/*      */     
/*  342 */     state.setProcessVisibleItemsOnly(false);
/*  343 */     return state;
/*      */   }
/*      */   
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   public int getPassCount()
/*      */   {
/*  354 */     return 2;
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
/*      */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*      */   {
/*  388 */     if (pass == 0) {
/*  389 */       drawItemPass0(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState);
/*      */ 
/*      */     }
/*  392 */     else if (pass == 1) {
/*  393 */       drawItemPass1(g2, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState);
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
/*      */ 
/*      */   protected void drawItemPass0(Graphics2D x_graphics, Rectangle2D x_dataArea, PlotRenderingInfo x_info, XYPlot x_plot, ValueAxis x_domainAxis, ValueAxis x_rangeAxis, XYDataset x_dataset, int x_series, int x_item, CrosshairState x_crosshairState)
/*      */   {
/*  426 */     if ((0 != x_series) || (0 != x_item)) {
/*  427 */       return;
/*      */     }
/*      */     
/*  430 */     boolean b_impliedZeroSubtrahend = 1 == x_dataset.getSeriesCount();
/*      */     
/*      */ 
/*  433 */     if (isEitherSeriesDegenerate(x_dataset, b_impliedZeroSubtrahend)) {
/*  434 */       return;
/*      */     }
/*      */     
/*      */ 
/*  438 */     if ((!b_impliedZeroSubtrahend) && (areSeriesDisjoint(x_dataset))) {
/*  439 */       return;
/*      */     }
/*      */     
/*      */ 
/*  443 */     LinkedList l_minuendXs = new LinkedList();
/*  444 */     LinkedList l_minuendYs = new LinkedList();
/*  445 */     LinkedList l_subtrahendXs = new LinkedList();
/*  446 */     LinkedList l_subtrahendYs = new LinkedList();
/*  447 */     LinkedList l_polygonXs = new LinkedList();
/*  448 */     LinkedList l_polygonYs = new LinkedList();
/*      */     
/*      */ 
/*  451 */     int l_minuendItem = 0;
/*  452 */     int l_minuendItemCount = x_dataset.getItemCount(0);
/*  453 */     Double l_minuendCurX = null;
/*  454 */     Double l_minuendNextX = null;
/*  455 */     Double l_minuendCurY = null;
/*  456 */     Double l_minuendNextY = null;
/*  457 */     double l_minuendMaxY = Double.NEGATIVE_INFINITY;
/*  458 */     double l_minuendMinY = Double.POSITIVE_INFINITY;
/*      */     
/*  460 */     int l_subtrahendItem = 0;
/*  461 */     int l_subtrahendItemCount = 0;
/*  462 */     Double l_subtrahendCurX = null;
/*  463 */     Double l_subtrahendNextX = null;
/*  464 */     Double l_subtrahendCurY = null;
/*  465 */     Double l_subtrahendNextY = null;
/*  466 */     double l_subtrahendMaxY = Double.NEGATIVE_INFINITY;
/*  467 */     double l_subtrahendMinY = Double.POSITIVE_INFINITY;
/*      */     
/*      */ 
/*  470 */     if (b_impliedZeroSubtrahend) {
/*  471 */       l_subtrahendItem = 0;
/*  472 */       l_subtrahendItemCount = 2;
/*  473 */       l_subtrahendCurX = new Double(x_dataset.getXValue(0, 0));
/*  474 */       l_subtrahendNextX = new Double(x_dataset.getXValue(0, l_minuendItemCount - 1));
/*      */       
/*  476 */       l_subtrahendCurY = new Double(0.0D);
/*  477 */       l_subtrahendNextY = new Double(0.0D);
/*  478 */       l_subtrahendMaxY = 0.0D;
/*  479 */       l_subtrahendMinY = 0.0D;
/*      */       
/*  481 */       l_subtrahendXs.add(l_subtrahendCurX);
/*  482 */       l_subtrahendYs.add(l_subtrahendCurY);
/*      */     }
/*      */     else {
/*  485 */       l_subtrahendItemCount = x_dataset.getItemCount(1);
/*      */     }
/*      */     
/*  488 */     boolean b_minuendDone = false;
/*  489 */     boolean b_minuendAdvanced = true;
/*  490 */     boolean b_minuendAtIntersect = false;
/*  491 */     boolean b_minuendFastForward = false;
/*  492 */     boolean b_subtrahendDone = false;
/*  493 */     boolean b_subtrahendAdvanced = true;
/*  494 */     boolean b_subtrahendAtIntersect = false;
/*  495 */     boolean b_subtrahendFastForward = false;
/*  496 */     boolean b_colinear = false;
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  501 */     double l_x1 = 0.0D;double l_y1 = 0.0D;
/*  502 */     double l_x2 = 0.0D;double l_y2 = 0.0D;
/*  503 */     double l_x3 = 0.0D;double l_y3 = 0.0D;
/*  504 */     double l_x4 = 0.0D;double l_y4 = 0.0D;
/*      */     
/*      */ 
/*  507 */     boolean b_fastForwardDone = false;
/*  508 */     while (!b_fastForwardDone)
/*      */     {
/*  510 */       l_x1 = x_dataset.getXValue(0, l_minuendItem);
/*  511 */       l_y1 = x_dataset.getYValue(0, l_minuendItem);
/*  512 */       l_x2 = x_dataset.getXValue(0, l_minuendItem + 1);
/*  513 */       l_y2 = x_dataset.getYValue(0, l_minuendItem + 1);
/*      */       
/*  515 */       l_minuendCurX = new Double(l_x1);
/*  516 */       l_minuendCurY = new Double(l_y1);
/*  517 */       l_minuendNextX = new Double(l_x2);
/*  518 */       l_minuendNextY = new Double(l_y2);
/*      */       
/*  520 */       if (b_impliedZeroSubtrahend) {
/*  521 */         l_x3 = l_subtrahendCurX.doubleValue();
/*  522 */         l_y3 = l_subtrahendCurY.doubleValue();
/*  523 */         l_x4 = l_subtrahendNextX.doubleValue();
/*  524 */         l_y4 = l_subtrahendNextY.doubleValue();
/*      */       }
/*      */       else {
/*  527 */         l_x3 = x_dataset.getXValue(1, l_subtrahendItem);
/*  528 */         l_y3 = x_dataset.getYValue(1, l_subtrahendItem);
/*  529 */         l_x4 = x_dataset.getXValue(1, l_subtrahendItem + 1);
/*  530 */         l_y4 = x_dataset.getYValue(1, l_subtrahendItem + 1);
/*      */         
/*  532 */         l_subtrahendCurX = new Double(l_x3);
/*  533 */         l_subtrahendCurY = new Double(l_y3);
/*  534 */         l_subtrahendNextX = new Double(l_x4);
/*  535 */         l_subtrahendNextY = new Double(l_y4);
/*      */       }
/*      */       
/*  538 */       if (l_x2 <= l_x3)
/*      */       {
/*  540 */         l_minuendItem++;
/*  541 */         b_minuendFastForward = true;
/*      */ 
/*      */ 
/*      */       }
/*  545 */       else if (l_x4 <= l_x1)
/*      */       {
/*  547 */         l_subtrahendItem++;
/*  548 */         b_subtrahendFastForward = true;
/*      */ 
/*      */       }
/*      */       else
/*      */       {
/*  553 */         if ((l_x3 < l_x1) && (l_x1 < l_x4))
/*      */         {
/*  555 */           double l_slope = (l_y4 - l_y3) / (l_x4 - l_x3);
/*  556 */           l_subtrahendCurX = l_minuendCurX;
/*  557 */           l_subtrahendCurY = new Double(l_slope * l_x1 + (l_y3 - l_slope * l_x3));
/*      */           
/*      */ 
/*  560 */           l_subtrahendXs.add(l_subtrahendCurX);
/*  561 */           l_subtrahendYs.add(l_subtrahendCurY);
/*      */         }
/*      */         
/*  564 */         if ((l_x1 < l_x3) && (l_x3 < l_x2))
/*      */         {
/*  566 */           double l_slope = (l_y2 - l_y1) / (l_x2 - l_x1);
/*  567 */           l_minuendCurX = l_subtrahendCurX;
/*  568 */           l_minuendCurY = new Double(l_slope * l_x3 + (l_y1 - l_slope * l_x1));
/*      */           
/*      */ 
/*  571 */           l_minuendXs.add(l_minuendCurX);
/*  572 */           l_minuendYs.add(l_minuendCurY);
/*      */         }
/*      */         
/*  575 */         l_minuendMaxY = l_minuendCurY.doubleValue();
/*  576 */         l_minuendMinY = l_minuendCurY.doubleValue();
/*  577 */         l_subtrahendMaxY = l_subtrahendCurY.doubleValue();
/*  578 */         l_subtrahendMinY = l_subtrahendCurY.doubleValue();
/*      */         
/*  580 */         b_fastForwardDone = true;
/*      */       }
/*      */     }
/*      */     
/*  584 */     while ((!b_minuendDone) && (!b_subtrahendDone)) {
/*  585 */       if ((!b_minuendDone) && (!b_minuendFastForward) && (b_minuendAdvanced)) {
/*  586 */         l_x1 = x_dataset.getXValue(0, l_minuendItem);
/*  587 */         l_y1 = x_dataset.getYValue(0, l_minuendItem);
/*  588 */         l_minuendCurX = new Double(l_x1);
/*  589 */         l_minuendCurY = new Double(l_y1);
/*      */         
/*  591 */         if (!b_minuendAtIntersect) {
/*  592 */           l_minuendXs.add(l_minuendCurX);
/*  593 */           l_minuendYs.add(l_minuendCurY);
/*      */         }
/*      */         
/*  596 */         l_minuendMaxY = Math.max(l_minuendMaxY, l_y1);
/*  597 */         l_minuendMinY = Math.min(l_minuendMinY, l_y1);
/*      */         
/*  599 */         l_x2 = x_dataset.getXValue(0, l_minuendItem + 1);
/*  600 */         l_y2 = x_dataset.getYValue(0, l_minuendItem + 1);
/*  601 */         l_minuendNextX = new Double(l_x2);
/*  602 */         l_minuendNextY = new Double(l_y2);
/*      */       }
/*      */       
/*      */ 
/*  606 */       if ((!b_impliedZeroSubtrahend) && (!b_subtrahendDone) && (!b_subtrahendFastForward) && (b_subtrahendAdvanced))
/*      */       {
/*  608 */         l_x3 = x_dataset.getXValue(1, l_subtrahendItem);
/*  609 */         l_y3 = x_dataset.getYValue(1, l_subtrahendItem);
/*  610 */         l_subtrahendCurX = new Double(l_x3);
/*  611 */         l_subtrahendCurY = new Double(l_y3);
/*      */         
/*  613 */         if (!b_subtrahendAtIntersect) {
/*  614 */           l_subtrahendXs.add(l_subtrahendCurX);
/*  615 */           l_subtrahendYs.add(l_subtrahendCurY);
/*      */         }
/*      */         
/*  618 */         l_subtrahendMaxY = Math.max(l_subtrahendMaxY, l_y3);
/*  619 */         l_subtrahendMinY = Math.min(l_subtrahendMinY, l_y3);
/*      */         
/*  621 */         l_x4 = x_dataset.getXValue(1, l_subtrahendItem + 1);
/*  622 */         l_y4 = x_dataset.getYValue(1, l_subtrahendItem + 1);
/*  623 */         l_subtrahendNextX = new Double(l_x4);
/*  624 */         l_subtrahendNextY = new Double(l_y4);
/*      */       }
/*      */       
/*      */ 
/*  628 */       b_minuendFastForward = false;
/*  629 */       b_subtrahendFastForward = false;
/*      */       
/*  631 */       Double l_intersectX = null;
/*  632 */       Double l_intersectY = null;
/*  633 */       boolean b_intersect = false;
/*      */       
/*  635 */       b_minuendAtIntersect = false;
/*  636 */       b_subtrahendAtIntersect = false;
/*      */       
/*      */ 
/*  639 */       if ((l_x2 == l_x4) && (l_y2 == l_y4))
/*      */       {
/*  641 */         if ((l_x1 == l_x3) && (l_y1 == l_y3)) {
/*  642 */           b_colinear = true;
/*      */ 
/*      */         }
/*      */         else
/*      */         {
/*  647 */           l_intersectX = new Double(l_x2);
/*  648 */           l_intersectY = new Double(l_y2);
/*      */           
/*  650 */           b_intersect = true;
/*  651 */           b_minuendAtIntersect = true;
/*  652 */           b_subtrahendAtIntersect = true;
/*      */         }
/*      */       }
/*      */       else
/*      */       {
/*  657 */         double l_denominator = (l_y4 - l_y3) * (l_x2 - l_x1) - (l_x4 - l_x3) * (l_y2 - l_y1);
/*      */         
/*      */ 
/*      */ 
/*  661 */         double l_deltaY = l_y1 - l_y3;
/*  662 */         double l_deltaX = l_x1 - l_x3;
/*      */         
/*      */ 
/*  665 */         double l_numeratorA = (l_x4 - l_x3) * l_deltaY - (l_y4 - l_y3) * l_deltaX;
/*      */         
/*  667 */         double l_numeratorB = (l_x2 - l_x1) * l_deltaY - (l_y2 - l_y1) * l_deltaX;
/*      */         
/*      */ 
/*      */ 
/*  671 */         if ((0.0D == l_numeratorA) && (0.0D == l_numeratorB) && (0.0D == l_denominator))
/*      */         {
/*  673 */           b_colinear = true;
/*      */         }
/*      */         else
/*      */         {
/*  677 */           if (b_colinear)
/*      */           {
/*  679 */             l_minuendXs.clear();
/*  680 */             l_minuendYs.clear();
/*  681 */             l_subtrahendXs.clear();
/*  682 */             l_subtrahendYs.clear();
/*  683 */             l_polygonXs.clear();
/*  684 */             l_polygonYs.clear();
/*      */             
/*  686 */             b_colinear = false;
/*      */             
/*      */ 
/*  689 */             boolean b_useMinuend = (l_x3 <= l_x1) && (l_x1 <= l_x4);
/*      */             
/*  691 */             l_polygonXs.add(b_useMinuend ? l_minuendCurX : l_subtrahendCurX);
/*      */             
/*  693 */             l_polygonYs.add(b_useMinuend ? l_minuendCurY : l_subtrahendCurY);
/*      */           }
/*      */           
/*      */ 
/*      */ 
/*  698 */           double l_slopeA = l_numeratorA / l_denominator;
/*  699 */           double l_slopeB = l_numeratorB / l_denominator;
/*      */           
/*      */ 
/*  702 */           if ((0.0D < l_slopeA) && (l_slopeA <= 1.0D) && (0.0D < l_slopeB) && (l_slopeB <= 1.0D))
/*      */           {
/*      */ 
/*  705 */             double l_xi = l_x1 + l_slopeA * (l_x2 - l_x1);
/*  706 */             double l_yi = l_y1 + l_slopeA * (l_y2 - l_y1);
/*      */             
/*  708 */             l_intersectX = new Double(l_xi);
/*  709 */             l_intersectY = new Double(l_yi);
/*  710 */             b_intersect = true;
/*  711 */             b_minuendAtIntersect = (l_xi == l_x2) && (l_yi == l_y2);
/*      */             
/*  713 */             b_subtrahendAtIntersect = (l_xi == l_x4) && (l_yi == l_y4);
/*      */             
/*      */ 
/*      */ 
/*  717 */             l_minuendCurX = l_intersectX;
/*  718 */             l_minuendCurY = l_intersectY;
/*  719 */             l_subtrahendCurX = l_intersectX;
/*  720 */             l_subtrahendCurY = l_intersectY;
/*      */           }
/*      */         }
/*      */       }
/*      */       
/*  725 */       if (b_intersect)
/*      */       {
/*      */ 
/*  728 */         l_polygonXs.addAll(l_minuendXs);
/*  729 */         l_polygonYs.addAll(l_minuendYs);
/*      */         
/*      */ 
/*  732 */         l_polygonXs.add(l_intersectX);
/*  733 */         l_polygonYs.add(l_intersectY);
/*      */         
/*      */ 
/*  736 */         Collections.reverse(l_subtrahendXs);
/*  737 */         Collections.reverse(l_subtrahendYs);
/*  738 */         l_polygonXs.addAll(l_subtrahendXs);
/*  739 */         l_polygonYs.addAll(l_subtrahendYs);
/*      */         
/*      */ 
/*  742 */         boolean b_positive = (l_subtrahendMaxY <= l_minuendMaxY) && (l_subtrahendMinY <= l_minuendMinY);
/*      */         
/*  744 */         createPolygon(x_graphics, x_dataArea, x_plot, x_domainAxis, x_rangeAxis, b_positive, l_polygonXs, l_polygonYs);
/*      */         
/*      */ 
/*      */ 
/*  748 */         l_minuendXs.clear();
/*  749 */         l_minuendYs.clear();
/*  750 */         l_subtrahendXs.clear();
/*  751 */         l_subtrahendYs.clear();
/*  752 */         l_polygonXs.clear();
/*  753 */         l_polygonYs.clear();
/*      */         
/*      */ 
/*  756 */         double l_y = l_intersectY.doubleValue();
/*  757 */         l_minuendMaxY = l_y;
/*  758 */         l_subtrahendMaxY = l_y;
/*  759 */         l_minuendMinY = l_y;
/*  760 */         l_subtrahendMinY = l_y;
/*      */         
/*      */ 
/*  763 */         l_polygonXs.add(l_intersectX);
/*  764 */         l_polygonYs.add(l_intersectY);
/*      */       }
/*      */       
/*      */ 
/*  768 */       if (l_x2 <= l_x4) {
/*  769 */         l_minuendItem++;
/*  770 */         b_minuendAdvanced = true;
/*      */       }
/*      */       else {
/*  773 */         b_minuendAdvanced = false;
/*      */       }
/*      */       
/*      */ 
/*  777 */       if (l_x4 <= l_x2) {
/*  778 */         l_subtrahendItem++;
/*  779 */         b_subtrahendAdvanced = true;
/*      */       }
/*      */       else {
/*  782 */         b_subtrahendAdvanced = false;
/*      */       }
/*      */       
/*  785 */       b_minuendDone = l_minuendItem == l_minuendItemCount - 1;
/*  786 */       b_subtrahendDone = l_subtrahendItem == l_subtrahendItemCount - 1;
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*  791 */     if ((b_minuendDone) && (l_x3 < l_x2) && (l_x2 < l_x4))
/*      */     {
/*  793 */       double l_slope = (l_y4 - l_y3) / (l_x4 - l_x3);
/*  794 */       l_subtrahendNextX = l_minuendNextX;
/*  795 */       l_subtrahendNextY = new Double(l_slope * l_x2 + (l_y3 - l_slope * l_x3));
/*      */     }
/*      */     
/*      */ 
/*  799 */     if ((b_subtrahendDone) && (l_x1 < l_x4) && (l_x4 < l_x2))
/*      */     {
/*  801 */       double l_slope = (l_y2 - l_y1) / (l_x2 - l_x1);
/*  802 */       l_minuendNextX = l_subtrahendNextX;
/*  803 */       l_minuendNextY = new Double(l_slope * l_x4 + (l_y1 - l_slope * l_x1));
/*      */     }
/*      */     
/*      */ 
/*      */ 
/*      */ 
/*  809 */     l_minuendMaxY = Math.max(l_minuendMaxY, l_minuendNextY.doubleValue());
/*      */     
/*  811 */     l_subtrahendMaxY = Math.max(l_subtrahendMaxY, l_subtrahendNextY.doubleValue());
/*      */     
/*  813 */     l_minuendMinY = Math.min(l_minuendMinY, l_minuendNextY.doubleValue());
/*      */     
/*  815 */     l_subtrahendMinY = Math.min(l_subtrahendMinY, l_subtrahendNextY.doubleValue());
/*      */     
/*      */ 
/*      */ 
/*  819 */     l_minuendXs.add(l_minuendNextX);
/*  820 */     l_minuendYs.add(l_minuendNextY);
/*  821 */     l_subtrahendXs.add(l_subtrahendNextX);
/*  822 */     l_subtrahendYs.add(l_subtrahendNextY);
/*      */     
/*      */ 
/*      */ 
/*  826 */     l_polygonXs.addAll(l_minuendXs);
/*  827 */     l_polygonYs.addAll(l_minuendYs);
/*      */     
/*      */ 
/*  830 */     Collections.reverse(l_subtrahendXs);
/*  831 */     Collections.reverse(l_subtrahendYs);
/*  832 */     l_polygonXs.addAll(l_subtrahendXs);
/*  833 */     l_polygonYs.addAll(l_subtrahendYs);
/*      */     
/*      */ 
/*  836 */     boolean b_positive = (l_subtrahendMaxY <= l_minuendMaxY) && (l_subtrahendMinY <= l_minuendMinY);
/*      */     
/*  838 */     createPolygon(x_graphics, x_dataArea, x_plot, x_domainAxis, x_rangeAxis, b_positive, l_polygonXs, l_polygonYs);
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
/*      */   protected void drawItemPass1(Graphics2D x_graphics, Rectangle2D x_dataArea, PlotRenderingInfo x_info, XYPlot x_plot, ValueAxis x_domainAxis, ValueAxis x_rangeAxis, XYDataset x_dataset, int x_series, int x_item, CrosshairState x_crosshairState)
/*      */   {
/*  871 */     Shape l_entityArea = null;
/*  872 */     EntityCollection l_entities = null;
/*  873 */     if (null != x_info) {
/*  874 */       l_entities = x_info.getOwner().getEntityCollection();
/*      */     }
/*      */     
/*  877 */     Paint l_seriesPaint = getItemPaint(x_series, x_item);
/*  878 */     Stroke l_seriesStroke = getItemStroke(x_series, x_item);
/*  879 */     x_graphics.setPaint(l_seriesPaint);
/*  880 */     x_graphics.setStroke(l_seriesStroke);
/*      */     
/*  882 */     PlotOrientation l_orientation = x_plot.getOrientation();
/*  883 */     RectangleEdge l_domainAxisLocation = x_plot.getDomainAxisEdge();
/*  884 */     RectangleEdge l_rangeAxisLocation = x_plot.getRangeAxisEdge();
/*      */     
/*  886 */     double l_x0 = x_dataset.getXValue(x_series, x_item);
/*  887 */     double l_y0 = x_dataset.getYValue(x_series, x_item);
/*  888 */     double l_x1 = x_domainAxis.valueToJava2D(l_x0, x_dataArea, l_domainAxisLocation);
/*      */     
/*  890 */     double l_y1 = x_rangeAxis.valueToJava2D(l_y0, x_dataArea, l_rangeAxisLocation);
/*      */     
/*      */ 
/*  893 */     if (getShapesVisible()) {
/*  894 */       Shape l_shape = getItemShape(x_series, x_item);
/*  895 */       if (l_orientation == PlotOrientation.HORIZONTAL) {
/*  896 */         l_shape = ShapeUtilities.createTranslatedShape(l_shape, l_y1, l_x1);
/*      */       }
/*      */       else
/*      */       {
/*  900 */         l_shape = ShapeUtilities.createTranslatedShape(l_shape, l_x1, l_y1);
/*      */       }
/*      */       
/*  903 */       if (l_shape.intersects(x_dataArea)) {
/*  904 */         x_graphics.setPaint(getItemPaint(x_series, x_item));
/*  905 */         x_graphics.fill(l_shape);
/*      */       }
/*  907 */       l_entityArea = l_shape;
/*      */     }
/*      */     
/*      */ 
/*  911 */     if (null != l_entities) {
/*  912 */       if (null == l_entityArea) {
/*  913 */         l_entityArea = new Rectangle2D.Double(l_x1 - 2.0D, l_y1 - 2.0D, 4.0D, 4.0D);
/*      */       }
/*      */       
/*  916 */       String l_tip = null;
/*  917 */       XYToolTipGenerator l_tipGenerator = getToolTipGenerator(x_series, x_item);
/*      */       
/*  919 */       if (null != l_tipGenerator) {
/*  920 */         l_tip = l_tipGenerator.generateToolTip(x_dataset, x_series, x_item);
/*      */       }
/*      */       
/*  923 */       String l_url = null;
/*  924 */       XYURLGenerator l_urlGenerator = getURLGenerator();
/*  925 */       if (null != l_urlGenerator) {
/*  926 */         l_url = l_urlGenerator.generateURL(x_dataset, x_series, x_item);
/*      */       }
/*      */       
/*  929 */       XYItemEntity l_entity = new XYItemEntity(l_entityArea, x_dataset, x_series, x_item, l_tip, l_url);
/*      */       
/*  931 */       l_entities.add(l_entity);
/*      */     }
/*      */     
/*      */ 
/*  935 */     if (isItemLabelVisible(x_series, x_item)) {
/*  936 */       drawItemLabel(x_graphics, l_orientation, x_dataset, x_series, x_item, l_x1, l_y1, l_y1 < 0.0D);
/*      */     }
/*      */     
/*      */ 
/*  940 */     int l_domainAxisIndex = x_plot.getDomainAxisIndex(x_domainAxis);
/*  941 */     int l_rangeAxisIndex = x_plot.getRangeAxisIndex(x_rangeAxis);
/*  942 */     updateCrosshairValues(x_crosshairState, l_x0, l_y0, l_domainAxisIndex, l_rangeAxisIndex, l_x1, l_y1, l_orientation);
/*      */     
/*      */ 
/*  945 */     if (0 == x_item) {
/*  946 */       return;
/*      */     }
/*      */     
/*  949 */     double l_x2 = x_domainAxis.valueToJava2D(x_dataset.getXValue(x_series, x_item - 1), x_dataArea, l_domainAxisLocation);
/*      */     
/*  951 */     double l_y2 = x_rangeAxis.valueToJava2D(x_dataset.getYValue(x_series, x_item - 1), x_dataArea, l_rangeAxisLocation);
/*      */     
/*      */ 
/*  954 */     Line2D l_line = null;
/*  955 */     if (PlotOrientation.HORIZONTAL == l_orientation) {
/*  956 */       l_line = new Line2D.Double(l_y1, l_x1, l_y2, l_x2);
/*      */     }
/*  958 */     else if (PlotOrientation.VERTICAL == l_orientation) {
/*  959 */       l_line = new Line2D.Double(l_x1, l_y1, l_x2, l_y2);
/*      */     }
/*      */     
/*  962 */     if ((null != l_line) && (l_line.intersects(x_dataArea))) {
/*  963 */       x_graphics.setPaint(getItemPaint(x_series, x_item));
/*  964 */       x_graphics.setStroke(getItemStroke(x_series, x_item));
/*  965 */       x_graphics.draw(l_line);
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
/*      */   private boolean isEitherSeriesDegenerate(XYDataset x_dataset, boolean x_impliedZeroSubtrahend)
/*      */   {
/*  981 */     if (x_impliedZeroSubtrahend) {
/*  982 */       return x_dataset.getItemCount(0) < 2;
/*      */     }
/*      */     
/*  985 */     return (x_dataset.getItemCount(0) < 2) || (x_dataset.getItemCount(1) < 2);
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
/*      */   private boolean areSeriesDisjoint(XYDataset x_dataset)
/*      */   {
/*  999 */     int l_minuendItemCount = x_dataset.getItemCount(0);
/* 1000 */     double l_minuendFirst = x_dataset.getXValue(0, 0);
/* 1001 */     double l_minuendLast = x_dataset.getXValue(0, l_minuendItemCount - 1);
/*      */     
/* 1003 */     int l_subtrahendItemCount = x_dataset.getItemCount(1);
/* 1004 */     double l_subtrahendFirst = x_dataset.getXValue(1, 0);
/* 1005 */     double l_subtrahendLast = x_dataset.getXValue(1, l_subtrahendItemCount - 1);
/*      */     
/*      */ 
/* 1008 */     return (l_minuendLast < l_subtrahendFirst) || (l_subtrahendLast < l_minuendFirst);
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
/*      */   private void createPolygon(Graphics2D x_graphics, Rectangle2D x_dataArea, XYPlot x_plot, ValueAxis x_domainAxis, ValueAxis x_rangeAxis, boolean x_positive, LinkedList x_xValues, LinkedList x_yValues)
/*      */   {
/* 1037 */     PlotOrientation l_orientation = x_plot.getOrientation();
/* 1038 */     RectangleEdge l_domainAxisLocation = x_plot.getDomainAxisEdge();
/* 1039 */     RectangleEdge l_rangeAxisLocation = x_plot.getRangeAxisEdge();
/*      */     
/* 1041 */     Object[] l_xValues = x_xValues.toArray();
/* 1042 */     Object[] l_yValues = x_yValues.toArray();
/*      */     
/* 1044 */     GeneralPath l_path = new GeneralPath();
/*      */     
/* 1046 */     if (PlotOrientation.VERTICAL == l_orientation) {
/* 1047 */       double l_x = x_domainAxis.valueToJava2D(((Double)l_xValues[0]).doubleValue(), x_dataArea, l_domainAxisLocation);
/*      */       
/*      */ 
/* 1050 */       if (this.roundXCoordinates) {
/* 1051 */         l_x = Math.rint(l_x);
/*      */       }
/*      */       
/* 1054 */       double l_y = x_rangeAxis.valueToJava2D(((Double)l_yValues[0]).doubleValue(), x_dataArea, l_rangeAxisLocation);
/*      */       
/*      */ 
/*      */ 
/* 1058 */       l_path.moveTo((float)l_x, (float)l_y);
/* 1059 */       for (int i = 1; i < l_xValues.length; i++) {
/* 1060 */         l_x = x_domainAxis.valueToJava2D(((Double)l_xValues[i]).doubleValue(), x_dataArea, l_domainAxisLocation);
/*      */         
/*      */ 
/* 1063 */         if (this.roundXCoordinates) {
/* 1064 */           l_x = Math.rint(l_x);
/*      */         }
/*      */         
/* 1067 */         l_y = x_rangeAxis.valueToJava2D(((Double)l_yValues[i]).doubleValue(), x_dataArea, l_rangeAxisLocation);
/*      */         
/*      */ 
/* 1070 */         l_path.lineTo((float)l_x, (float)l_y);
/*      */       }
/* 1072 */       l_path.closePath();
/*      */     }
/*      */     else {
/* 1075 */       double l_x = x_domainAxis.valueToJava2D(((Double)l_xValues[0]).doubleValue(), x_dataArea, l_domainAxisLocation);
/*      */       
/*      */ 
/* 1078 */       if (this.roundXCoordinates) {
/* 1079 */         l_x = Math.rint(l_x);
/*      */       }
/*      */       
/* 1082 */       double l_y = x_rangeAxis.valueToJava2D(((Double)l_yValues[0]).doubleValue(), x_dataArea, l_rangeAxisLocation);
/*      */       
/*      */ 
/*      */ 
/* 1086 */       l_path.moveTo((float)l_y, (float)l_x);
/* 1087 */       for (int i = 1; i < l_xValues.length; i++) {
/* 1088 */         l_x = x_domainAxis.valueToJava2D(((Double)l_xValues[i]).doubleValue(), x_dataArea, l_domainAxisLocation);
/*      */         
/*      */ 
/* 1091 */         if (this.roundXCoordinates) {
/* 1092 */           l_x = Math.rint(l_x);
/*      */         }
/*      */         
/* 1095 */         l_y = x_rangeAxis.valueToJava2D(((Double)l_yValues[i]).doubleValue(), x_dataArea, l_rangeAxisLocation);
/*      */         
/*      */ 
/* 1098 */         l_path.lineTo((float)l_y, (float)l_x);
/*      */       }
/* 1100 */       l_path.closePath();
/*      */     }
/*      */     
/* 1103 */     if (l_path.intersects(x_dataArea)) {
/* 1104 */       x_graphics.setPaint(x_positive ? getPositivePaint() : getNegativePaint());
/*      */       
/* 1106 */       x_graphics.fill(l_path);
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
/*      */   public LegendItem getLegendItem(int datasetIndex, int series)
/*      */   {
/* 1120 */     LegendItem result = null;
/* 1121 */     XYPlot p = getPlot();
/* 1122 */     if (p != null) {
/* 1123 */       XYDataset dataset = p.getDataset(datasetIndex);
/* 1124 */       if ((dataset != null) && 
/* 1125 */         (getItemVisible(series, 0))) {
/* 1126 */         String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*      */         
/* 1128 */         String description = label;
/* 1129 */         String toolTipText = null;
/* 1130 */         if (getLegendItemToolTipGenerator() != null) {
/* 1131 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*      */         }
/*      */         
/*      */ 
/* 1135 */         String urlText = null;
/* 1136 */         if (getLegendItemURLGenerator() != null) {
/* 1137 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*      */         }
/*      */         
/* 1140 */         Paint paint = lookupSeriesPaint(series);
/* 1141 */         Stroke stroke = lookupSeriesStroke(series);
/* 1142 */         Shape line = getLegendLine();
/* 1143 */         result = new LegendItem(label, description, toolTipText, urlText, line, stroke, paint);
/*      */         
/* 1145 */         result.setLabelFont(lookupLegendTextFont(series));
/* 1146 */         Paint labelPaint = lookupLegendTextPaint(series);
/* 1147 */         if (labelPaint != null) {
/* 1148 */           result.setLabelPaint(labelPaint);
/*      */         }
/* 1150 */         result.setDataset(dataset);
/* 1151 */         result.setDatasetIndex(datasetIndex);
/* 1152 */         result.setSeriesKey(dataset.getSeriesKey(series));
/* 1153 */         result.setSeriesIndex(series);
/*      */       }
/*      */     }
/*      */     
/*      */ 
/*      */ 
/* 1159 */     return result;
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
/* 1171 */     if (obj == this) {
/* 1172 */       return true;
/*      */     }
/* 1174 */     if (!(obj instanceof XYDifferenceRenderer)) {
/* 1175 */       return false;
/*      */     }
/* 1177 */     if (!super.equals(obj)) {
/* 1178 */       return false;
/*      */     }
/* 1180 */     XYDifferenceRenderer that = (XYDifferenceRenderer)obj;
/* 1181 */     if (!PaintUtilities.equal(this.positivePaint, that.positivePaint)) {
/* 1182 */       return false;
/*      */     }
/* 1184 */     if (!PaintUtilities.equal(this.negativePaint, that.negativePaint)) {
/* 1185 */       return false;
/*      */     }
/* 1187 */     if (this.shapesVisible != that.shapesVisible) {
/* 1188 */       return false;
/*      */     }
/* 1190 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine)) {
/* 1191 */       return false;
/*      */     }
/* 1193 */     if (this.roundXCoordinates != that.roundXCoordinates) {
/* 1194 */       return false;
/*      */     }
/* 1196 */     return true;
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
/* 1207 */     XYDifferenceRenderer clone = (XYDifferenceRenderer)super.clone();
/* 1208 */     clone.legendLine = ShapeUtilities.clone(this.legendLine);
/* 1209 */     return clone;
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
/* 1220 */     stream.defaultWriteObject();
/* 1221 */     SerialUtilities.writePaint(this.positivePaint, stream);
/* 1222 */     SerialUtilities.writePaint(this.negativePaint, stream);
/* 1223 */     SerialUtilities.writeShape(this.legendLine, stream);
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
/* 1236 */     stream.defaultReadObject();
/* 1237 */     this.positivePaint = SerialUtilities.readPaint(stream);
/* 1238 */     this.negativePaint = SerialUtilities.readPaint(stream);
/* 1239 */     this.legendLine = SerialUtilities.readShape(stream);
/*      */   }
/*      */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYDifferenceRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */