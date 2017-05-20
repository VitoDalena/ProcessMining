/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Point;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.Stack;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StackedXYAreaRenderer
/*     */   extends XYAreaRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5217394318178570889L;
/*     */   
/*     */   static class StackedXYAreaRendererState
/*     */     extends XYItemRendererState
/*     */   {
/*     */     private Polygon seriesArea;
/*     */     private Line2D line;
/*     */     private Stack lastSeriesPoints;
/*     */     private Stack currentSeriesPoints;
/*     */     
/*     */     public StackedXYAreaRendererState(PlotRenderingInfo info)
/*     */     {
/* 148 */       super();
/* 149 */       this.seriesArea = null;
/* 150 */       this.line = new Line2D.Double();
/* 151 */       this.lastSeriesPoints = new Stack();
/* 152 */       this.currentSeriesPoints = new Stack();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Polygon getSeriesArea()
/*     */     {
/* 161 */       return this.seriesArea;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setSeriesArea(Polygon area)
/*     */     {
/* 170 */       this.seriesArea = area;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Line2D getLine()
/*     */     {
/* 179 */       return this.line;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Stack getCurrentSeriesPoints()
/*     */     {
/* 188 */       return this.currentSeriesPoints;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setCurrentSeriesPoints(Stack points)
/*     */     {
/* 197 */       this.currentSeriesPoints = points;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public Stack getLastSeriesPoints()
/*     */     {
/* 206 */       return this.lastSeriesPoints;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void setLastSeriesPoints(Stack points)
/*     */     {
/* 215 */       this.lastSeriesPoints = points;
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 223 */   private transient Paint shapePaint = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 229 */   private transient Stroke shapeStroke = null;
/*     */   
/*     */ 
/*     */ 
/*     */   public StackedXYAreaRenderer()
/*     */   {
/* 235 */     this(4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StackedXYAreaRenderer(int type)
/*     */   {
/* 244 */     this(type, null, null);
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
/*     */   public StackedXYAreaRenderer(int type, XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 262 */     super(type, labelGenerator, urlGenerator);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getShapePaint()
/*     */   {
/* 274 */     return this.shapePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapePaint(Paint shapePaint)
/*     */   {
/* 286 */     this.shapePaint = shapePaint;
/* 287 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getShapeStroke()
/*     */   {
/* 299 */     return this.shapeStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setShapeStroke(Stroke shapeStroke)
/*     */   {
/* 311 */     this.shapeStroke = shapeStroke;
/* 312 */     fireChangeEvent();
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*     */   {
/* 336 */     XYItemRendererState state = new StackedXYAreaRendererState(info);
/*     */     
/*     */ 
/* 339 */     state.setProcessVisibleItemsOnly(false);
/* 340 */     return state;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 349 */     return 2;
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 365 */     if (dataset != null) {
/* 366 */       return DatasetUtilities.findStackedRangeBounds((TableXYDataset)dataset);
/*     */     }
/*     */     
/*     */ 
/* 370 */     return null;
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
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 408 */     PlotOrientation orientation = plot.getOrientation();
/* 409 */     StackedXYAreaRendererState areaState = (StackedXYAreaRendererState)state;
/*     */     
/*     */ 
/*     */ 
/* 413 */     TableXYDataset tdataset = (TableXYDataset)dataset;
/* 414 */     int itemCount = tdataset.getItemCount();
/*     */     
/*     */ 
/* 417 */     double x1 = dataset.getXValue(series, item);
/* 418 */     double y1 = dataset.getYValue(series, item);
/* 419 */     boolean nullPoint = false;
/* 420 */     if (Double.isNaN(y1)) {
/* 421 */       y1 = 0.0D;
/* 422 */       nullPoint = true;
/*     */     }
/*     */     
/*     */ 
/* 426 */     double ph1 = getPreviousHeight(tdataset, series, item);
/* 427 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 429 */     double transY1 = rangeAxis.valueToJava2D(y1 + ph1, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/*     */ 
/* 433 */     Paint seriesPaint = getItemPaint(series, item);
/* 434 */     Stroke seriesStroke = getItemStroke(series, item);
/*     */     
/* 436 */     if (pass == 0)
/*     */     {
/*     */ 
/* 439 */       if (item == 0)
/*     */       {
/* 441 */         areaState.setSeriesArea(new Polygon());
/* 442 */         areaState.setLastSeriesPoints(areaState.getCurrentSeriesPoints());
/*     */         
/* 444 */         areaState.setCurrentSeriesPoints(new Stack());
/*     */         
/*     */ 
/* 447 */         double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, plot.getRangeAxisEdge());
/*     */         
/*     */ 
/*     */ 
/* 451 */         if (orientation == PlotOrientation.VERTICAL) {
/* 452 */           areaState.getSeriesArea().addPoint((int)transX1, (int)transY2);
/*     */ 
/*     */         }
/* 455 */         else if (orientation == PlotOrientation.HORIZONTAL) {
/* 456 */           areaState.getSeriesArea().addPoint((int)transY2, (int)transX1);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 462 */       if (orientation == PlotOrientation.VERTICAL) {
/* 463 */         Point point = new Point((int)transX1, (int)transY1);
/* 464 */         areaState.getSeriesArea().addPoint((int)point.getX(), (int)point.getY());
/*     */         
/* 466 */         areaState.getCurrentSeriesPoints().push(point);
/*     */       }
/* 468 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 469 */         areaState.getSeriesArea().addPoint((int)transY1, (int)transX1);
/*     */       }
/*     */       
/*     */ 
/* 473 */       if ((getPlotLines()) && 
/* 474 */         (item > 0))
/*     */       {
/* 476 */         double x0 = dataset.getXValue(series, item - 1);
/* 477 */         double y0 = dataset.getYValue(series, item - 1);
/* 478 */         double ph0 = getPreviousHeight(tdataset, series, item - 1);
/* 479 */         double transX0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/*     */         
/* 481 */         double transY0 = rangeAxis.valueToJava2D(y0 + ph0, dataArea, plot.getRangeAxisEdge());
/*     */         
/*     */ 
/* 484 */         if (orientation == PlotOrientation.VERTICAL) {
/* 485 */           areaState.getLine().setLine(transX0, transY0, transX1, transY1);
/*     */ 
/*     */         }
/* 488 */         else if (orientation == PlotOrientation.HORIZONTAL) {
/* 489 */           areaState.getLine().setLine(transY0, transX0, transY1, transX1);
/*     */         }
/*     */         
/* 492 */         g2.draw(areaState.getLine());
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 498 */       if ((getPlotArea()) && (item > 0) && (item == itemCount - 1))
/*     */       {
/* 500 */         double transY2 = rangeAxis.valueToJava2D(ph1, dataArea, plot.getRangeAxisEdge());
/*     */         
/*     */ 
/* 503 */         if (orientation == PlotOrientation.VERTICAL)
/*     */         {
/* 505 */           areaState.getSeriesArea().addPoint((int)transX1, (int)transY2);
/*     */ 
/*     */         }
/* 508 */         else if (orientation == PlotOrientation.HORIZONTAL)
/*     */         {
/* 510 */           areaState.getSeriesArea().addPoint((int)transY2, (int)transX1);
/*     */         }
/*     */         
/*     */ 
/*     */ 
/*     */ 
/* 516 */         if (series != 0) {
/* 517 */           Stack points = areaState.getLastSeriesPoints();
/* 518 */           while (!points.empty()) {
/* 519 */             Point point = (Point)points.pop();
/* 520 */             areaState.getSeriesArea().addPoint((int)point.getX(), (int)point.getY());
/*     */           }
/*     */         }
/*     */         
/*     */ 
/*     */ 
/* 526 */         g2.setPaint(seriesPaint);
/* 527 */         g2.setStroke(seriesStroke);
/* 528 */         g2.fill(areaState.getSeriesArea());
/*     */         
/*     */ 
/* 531 */         if (isOutline()) {
/* 532 */           g2.setStroke(lookupSeriesOutlineStroke(series));
/* 533 */           g2.setPaint(lookupSeriesOutlinePaint(series));
/* 534 */           g2.draw(areaState.getSeriesArea());
/*     */         }
/*     */       }
/*     */       
/* 538 */       int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 539 */       int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 540 */       updateCrosshairValues(crosshairState, x1, ph1 + y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*     */ 
/*     */ 
/*     */     }
/* 544 */     else if (pass == 1)
/*     */     {
/*     */ 
/*     */ 
/* 548 */       Shape shape = null;
/* 549 */       if (getPlotShapes()) {
/* 550 */         shape = getItemShape(series, item);
/* 551 */         if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 552 */           shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*     */ 
/*     */         }
/* 555 */         else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 556 */           shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*     */         }
/*     */         
/* 559 */         if (!nullPoint) {
/* 560 */           if (getShapePaint() != null) {
/* 561 */             g2.setPaint(getShapePaint());
/*     */           }
/*     */           else {
/* 564 */             g2.setPaint(seriesPaint);
/*     */           }
/* 566 */           if (getShapeStroke() != null) {
/* 567 */             g2.setStroke(getShapeStroke());
/*     */           }
/*     */           else {
/* 570 */             g2.setStroke(seriesStroke);
/*     */           }
/* 572 */           g2.draw(shape);
/*     */         }
/*     */         
/*     */       }
/* 576 */       else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 577 */         shape = new Rectangle2D.Double(transX1 - 3.0D, transY1 - 3.0D, 6.0D, 6.0D);
/*     */ 
/*     */       }
/* 580 */       else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 581 */         shape = new Rectangle2D.Double(transY1 - 3.0D, transX1 - 3.0D, 6.0D, 6.0D);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 587 */       if (state.getInfo() != null) {
/* 588 */         EntityCollection entities = state.getEntityCollection();
/* 589 */         if ((entities != null) && (shape != null) && (!nullPoint)) {
/* 590 */           String tip = null;
/* 591 */           XYToolTipGenerator generator = getToolTipGenerator(series, item);
/*     */           
/* 593 */           if (generator != null) {
/* 594 */             tip = generator.generateToolTip(dataset, series, item);
/*     */           }
/* 596 */           String url = null;
/* 597 */           if (getURLGenerator() != null) {
/* 598 */             url = getURLGenerator().generateURL(dataset, series, item);
/*     */           }
/*     */           
/* 601 */           XYItemEntity entity = new XYItemEntity(shape, dataset, series, item, tip, url);
/*     */           
/* 603 */           entities.add(entity);
/*     */         }
/*     */       }
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
/*     */   protected double getPreviousHeight(TableXYDataset dataset, int series, int index)
/*     */   {
/* 624 */     double result = 0.0D;
/* 625 */     for (int i = 0; i < series; i++) {
/* 626 */       double value = dataset.getYValue(i, index);
/* 627 */       if (!Double.isNaN(value)) {
/* 628 */         result += value;
/*     */       }
/*     */     }
/* 631 */     return result;
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
/* 642 */     if (obj == this) {
/* 643 */       return true;
/*     */     }
/* 645 */     if ((!(obj instanceof StackedXYAreaRenderer)) || (!super.equals(obj))) {
/* 646 */       return false;
/*     */     }
/* 648 */     StackedXYAreaRenderer that = (StackedXYAreaRenderer)obj;
/* 649 */     if (!PaintUtilities.equal(this.shapePaint, that.shapePaint)) {
/* 650 */       return false;
/*     */     }
/* 652 */     if (!ObjectUtilities.equal(this.shapeStroke, that.shapeStroke)) {
/* 653 */       return false;
/*     */     }
/* 655 */     return true;
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
/* 666 */     return super.clone();
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
/* 679 */     stream.defaultReadObject();
/* 680 */     this.shapePaint = SerialUtilities.readPaint(stream);
/* 681 */     this.shapeStroke = SerialUtilities.readStroke(stream);
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
/* 692 */     stream.defaultWriteObject();
/* 693 */     SerialUtilities.writePaint(this.shapePaint, stream);
/* 694 */     SerialUtilities.writeStroke(this.shapeStroke, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/StackedXYAreaRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */