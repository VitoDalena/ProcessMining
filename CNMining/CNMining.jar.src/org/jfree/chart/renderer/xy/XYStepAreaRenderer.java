/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.util.PublicCloneable;
/*     */ import org.jfree.util.ShapeUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYStepAreaRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -7311560779702649635L;
/*     */   public static final int SHAPES = 1;
/*     */   public static final int AREA = 2;
/*     */   public static final int AREA_AND_SHAPES = 3;
/*     */   private boolean shapesVisible;
/*     */   private boolean shapesFilled;
/*     */   private boolean plotArea;
/*     */   private boolean showOutline;
/* 117 */   protected transient Polygon pArea = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private double rangeBase;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYStepAreaRenderer()
/*     */   {
/* 129 */     this(2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYStepAreaRenderer(int type)
/*     */   {
/* 138 */     this(type, null, null);
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
/*     */   public XYStepAreaRenderer(int type, XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 157 */     setBaseToolTipGenerator(toolTipGenerator);
/* 158 */     setURLGenerator(urlGenerator);
/*     */     
/* 160 */     if (type == 2) {
/* 161 */       this.plotArea = true;
/*     */     }
/* 163 */     else if (type == 1) {
/* 164 */       this.shapesVisible = true;
/*     */     }
/* 166 */     else if (type == 3) {
/* 167 */       this.plotArea = true;
/* 168 */       this.shapesVisible = true;
/*     */     }
/* 170 */     this.showOutline = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isOutline()
/*     */   {
/* 182 */     return this.showOutline;
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
/*     */   public void setOutline(boolean show)
/*     */   {
/* 195 */     this.showOutline = show;
/* 196 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getShapesVisible()
/*     */   {
/* 207 */     return this.shapesVisible;
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
/*     */   public void setShapesVisible(boolean flag)
/*     */   {
/* 220 */     this.shapesVisible = flag;
/* 221 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isShapesFilled()
/*     */   {
/* 232 */     return this.shapesFilled;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapesFilled(boolean filled)
/*     */   {
/* 244 */     this.shapesFilled = filled;
/* 245 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getPlotArea()
/*     */   {
/* 256 */     return this.plotArea;
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
/*     */   public void setPlotArea(boolean flag)
/*     */   {
/* 269 */     this.plotArea = flag;
/* 270 */     fireChangeEvent();
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
/*     */   public double getRangeBase()
/*     */   {
/* 283 */     return this.rangeBase;
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
/*     */   public void setRangeBase(double val)
/*     */   {
/* 298 */     this.rangeBase = val;
/* 299 */     fireChangeEvent();
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*     */   {
/* 322 */     XYItemRendererState state = super.initialise(g2, dataArea, plot, data, info);
/*     */     
/*     */ 
/*     */ 
/* 326 */     state.setProcessVisibleItemsOnly(false);
/* 327 */     return state;
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
/* 363 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/*     */ 
/* 367 */     int itemCount = dataset.getItemCount(series);
/*     */     
/* 369 */     Paint paint = getItemPaint(series, item);
/* 370 */     Stroke seriesStroke = getItemStroke(series, item);
/* 371 */     g2.setPaint(paint);
/* 372 */     g2.setStroke(seriesStroke);
/*     */     
/*     */ 
/* 375 */     double x1 = dataset.getXValue(series, item);
/* 376 */     double y1 = dataset.getYValue(series, item);
/* 377 */     double x = x1;
/* 378 */     double y = Double.isNaN(y1) ? getRangeBase() : y1;
/* 379 */     double transX1 = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 381 */     double transY1 = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/*     */ 
/* 385 */     transY1 = restrictValueToDataArea(transY1, plot, dataArea);
/*     */     
/* 387 */     if ((this.pArea == null) && (!Double.isNaN(y1)))
/*     */     {
/*     */ 
/* 390 */       this.pArea = new Polygon();
/*     */       
/*     */ 
/* 393 */       double transY2 = rangeAxis.valueToJava2D(getRangeBase(), dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/*     */ 
/* 397 */       transY2 = restrictValueToDataArea(transY2, plot, dataArea);
/*     */       
/*     */ 
/* 400 */       if (orientation == PlotOrientation.VERTICAL) {
/* 401 */         this.pArea.addPoint((int)transX1, (int)transY2);
/*     */       }
/* 403 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 404 */         this.pArea.addPoint((int)transY2, (int)transX1);
/*     */       }
/*     */     }
/*     */     
/* 408 */     double transX0 = 0.0D;
/* 409 */     double transY0 = restrictValueToDataArea(getRangeBase(), plot, dataArea);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 414 */     if (item > 0)
/*     */     {
/* 416 */       double x0 = dataset.getXValue(series, item - 1);
/* 417 */       double y0 = Double.isNaN(y1) ? y1 : dataset.getYValue(series, item - 1);
/*     */       
/* 419 */       x = x0;
/* 420 */       y = Double.isNaN(y0) ? getRangeBase() : y0;
/* 421 */       transX0 = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/*     */       
/* 423 */       transY0 = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/*     */ 
/* 427 */       transY0 = restrictValueToDataArea(transY0, plot, dataArea);
/*     */       
/* 429 */       if (Double.isNaN(y1))
/*     */       {
/*     */ 
/* 432 */         transX1 = transX0;
/* 433 */         transY0 = transY1;
/*     */       }
/* 435 */       if (transY0 != transY1)
/*     */       {
/* 437 */         if (orientation == PlotOrientation.VERTICAL) {
/* 438 */           this.pArea.addPoint((int)transX1, (int)transY0);
/*     */         }
/* 440 */         else if (orientation == PlotOrientation.HORIZONTAL) {
/* 441 */           this.pArea.addPoint((int)transY0, (int)transX1);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 446 */     Shape shape = null;
/* 447 */     if (!Double.isNaN(y1))
/*     */     {
/* 449 */       if (orientation == PlotOrientation.VERTICAL) {
/* 450 */         this.pArea.addPoint((int)transX1, (int)transY1);
/*     */       }
/* 452 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 453 */         this.pArea.addPoint((int)transY1, (int)transX1);
/*     */       }
/*     */       
/* 456 */       if (getShapesVisible()) {
/* 457 */         shape = getItemShape(series, item);
/* 458 */         if (orientation == PlotOrientation.VERTICAL) {
/* 459 */           shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*     */ 
/*     */         }
/* 462 */         else if (orientation == PlotOrientation.HORIZONTAL) {
/* 463 */           shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*     */         }
/*     */         
/* 466 */         if (isShapesFilled()) {
/* 467 */           g2.fill(shape);
/*     */         }
/*     */         else {
/* 470 */           g2.draw(shape);
/*     */         }
/*     */         
/*     */       }
/* 474 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 475 */         shape = new Rectangle2D.Double(transX1 - 2.0D, transY1 - 2.0D, 4.0D, 4.0D);
/*     */ 
/*     */       }
/* 478 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 479 */         shape = new Rectangle2D.Double(transY1 - 2.0D, transX1 - 2.0D, 4.0D, 4.0D);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 488 */     if ((getPlotArea()) && (item > 0) && (this.pArea != null) && ((item == itemCount - 1) || (Double.isNaN(y1))))
/*     */     {
/*     */ 
/* 491 */       double transY2 = rangeAxis.valueToJava2D(getRangeBase(), dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/*     */ 
/* 495 */       transY2 = restrictValueToDataArea(transY2, plot, dataArea);
/*     */       
/* 497 */       if (orientation == PlotOrientation.VERTICAL)
/*     */       {
/* 499 */         this.pArea.addPoint((int)transX1, (int)transY2);
/*     */       }
/* 501 */       else if (orientation == PlotOrientation.HORIZONTAL)
/*     */       {
/* 503 */         this.pArea.addPoint((int)transY2, (int)transX1);
/*     */       }
/*     */       
/*     */ 
/* 507 */       g2.fill(this.pArea);
/*     */       
/*     */ 
/* 510 */       if (isOutline()) {
/* 511 */         g2.setStroke(plot.getOutlineStroke());
/* 512 */         g2.setPaint(plot.getOutlinePaint());
/* 513 */         g2.draw(this.pArea);
/*     */       }
/*     */       
/*     */ 
/* 517 */       this.pArea = null;
/*     */     }
/*     */     
/*     */ 
/* 521 */     if (!Double.isNaN(y1)) {
/* 522 */       int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 523 */       int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 524 */       updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 529 */     EntityCollection entities = state.getEntityCollection();
/* 530 */     if (entities != null) {
/* 531 */       addEntity(entities, shape, dataset, series, item, transX1, transY1);
/*     */     }
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
/* 543 */     if (obj == this) {
/* 544 */       return true;
/*     */     }
/* 546 */     if (!(obj instanceof XYStepAreaRenderer)) {
/* 547 */       return false;
/*     */     }
/* 549 */     XYStepAreaRenderer that = (XYStepAreaRenderer)obj;
/* 550 */     if (this.showOutline != that.showOutline) {
/* 551 */       return false;
/*     */     }
/* 553 */     if (this.shapesVisible != that.shapesVisible) {
/* 554 */       return false;
/*     */     }
/* 556 */     if (this.shapesFilled != that.shapesFilled) {
/* 557 */       return false;
/*     */     }
/* 559 */     if (this.plotArea != that.plotArea) {
/* 560 */       return false;
/*     */     }
/* 562 */     if (this.rangeBase != that.rangeBase) {
/* 563 */       return false;
/*     */     }
/* 565 */     return super.equals(obj);
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
/* 576 */     return super.clone();
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
/*     */   protected static double restrictValueToDataArea(double value, XYPlot plot, Rectangle2D dataArea)
/*     */   {
/* 597 */     double min = 0.0D;
/* 598 */     double max = 0.0D;
/* 599 */     if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 600 */       min = dataArea.getMinY();
/* 601 */       max = dataArea.getMaxY();
/*     */     }
/* 603 */     else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 604 */       min = dataArea.getMinX();
/* 605 */       max = dataArea.getMaxX();
/*     */     }
/* 607 */     if (value < min) {
/* 608 */       value = min;
/*     */     }
/* 610 */     else if (value > max) {
/* 611 */       value = max;
/*     */     }
/* 613 */     return value;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYStepAreaRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */