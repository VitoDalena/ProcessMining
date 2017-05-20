/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
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
/*     */ public class XYAreaRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, PublicCloneable
/*     */ {
/*     */   private static final long serialVersionUID = -4481971353973876747L;
/*     */   public static final int SHAPES = 1;
/*     */   public static final int LINES = 2;
/*     */   public static final int SHAPES_AND_LINES = 3;
/*     */   public static final int AREA = 4;
/*     */   public static final int AREA_AND_SHAPES = 5;
/*     */   private boolean plotShapes;
/*     */   private boolean plotLines;
/*     */   private boolean plotArea;
/*     */   private boolean showOutline;
/*     */   private transient Shape legendArea;
/*     */   
/*     */   static class XYAreaRendererState
/*     */     extends XYItemRendererState
/*     */   {
/*     */     public Polygon area;
/*     */     public Line2D line;
/*     */     
/*     */     public XYAreaRendererState(PlotRenderingInfo info)
/*     */     {
/* 146 */       super();
/* 147 */       this.area = new Polygon();
/* 148 */       this.line = new Line2D.Double();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYAreaRenderer()
/*     */   {
/* 194 */     this(4);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYAreaRenderer(int type)
/*     */   {
/* 203 */     this(type, null, null);
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
/*     */   public XYAreaRenderer(int type, XYToolTipGenerator toolTipGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 221 */     setBaseToolTipGenerator(toolTipGenerator);
/* 222 */     setURLGenerator(urlGenerator);
/*     */     
/* 224 */     if (type == 1) {
/* 225 */       this.plotShapes = true;
/*     */     }
/* 227 */     if (type == 2) {
/* 228 */       this.plotLines = true;
/*     */     }
/* 230 */     if (type == 3) {
/* 231 */       this.plotShapes = true;
/* 232 */       this.plotLines = true;
/*     */     }
/* 234 */     if (type == 4) {
/* 235 */       this.plotArea = true;
/*     */     }
/* 237 */     if (type == 5) {
/* 238 */       this.plotArea = true;
/* 239 */       this.plotShapes = true;
/*     */     }
/* 241 */     this.showOutline = false;
/* 242 */     GeneralPath area = new GeneralPath();
/* 243 */     area.moveTo(0.0F, -4.0F);
/* 244 */     area.lineTo(3.0F, -2.0F);
/* 245 */     area.lineTo(4.0F, 4.0F);
/* 246 */     area.lineTo(-4.0F, 4.0F);
/* 247 */     area.lineTo(-3.0F, -2.0F);
/* 248 */     area.closePath();
/* 249 */     this.legendArea = area;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getPlotShapes()
/*     */   {
/* 259 */     return this.plotShapes;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getPlotLines()
/*     */   {
/* 268 */     return this.plotLines;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getPlotArea()
/*     */   {
/* 277 */     return this.plotArea;
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
/* 289 */     return this.showOutline;
/*     */   }
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
/* 301 */     this.showOutline = show;
/* 302 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getLegendArea()
/*     */   {
/* 311 */     return this.legendArea;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendArea(Shape area)
/*     */   {
/* 321 */     if (area == null) {
/* 322 */       throw new IllegalArgumentException("Null 'area' argument.");
/*     */     }
/* 324 */     this.legendArea = area;
/* 325 */     fireChangeEvent();
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*     */   {
/* 343 */     XYAreaRendererState state = new XYAreaRendererState(info);
/*     */     
/*     */ 
/*     */ 
/* 347 */     state.setProcessVisibleItemsOnly(false);
/* 348 */     return state;
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
/*     */   public LegendItem getLegendItem(int datasetIndex, int series)
/*     */   {
/* 361 */     LegendItem result = null;
/* 362 */     XYPlot xyplot = getPlot();
/* 363 */     if (xyplot != null) {
/* 364 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/* 365 */       if (dataset != null) {
/* 366 */         XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
/* 367 */         String label = lg.generateLabel(dataset, series);
/* 368 */         String description = label;
/* 369 */         String toolTipText = null;
/* 370 */         if (getLegendItemToolTipGenerator() != null) {
/* 371 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */         }
/*     */         
/* 374 */         String urlText = null;
/* 375 */         if (getLegendItemURLGenerator() != null) {
/* 376 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */         }
/*     */         
/* 379 */         Paint paint = lookupSeriesPaint(series);
/* 380 */         result = new LegendItem(label, description, toolTipText, urlText, this.legendArea, paint);
/*     */         
/* 382 */         result.setLabelFont(lookupLegendTextFont(series));
/* 383 */         Paint labelPaint = lookupLegendTextPaint(series);
/* 384 */         if (labelPaint != null) {
/* 385 */           result.setLabelPaint(labelPaint);
/*     */         }
/* 387 */         result.setDataset(dataset);
/* 388 */         result.setDatasetIndex(datasetIndex);
/* 389 */         result.setSeriesKey(dataset.getSeriesKey(series));
/* 390 */         result.setSeriesIndex(series);
/*     */       }
/*     */     }
/* 393 */     return result;
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 419 */     if (!getItemVisible(series, item)) {
/* 420 */       return;
/*     */     }
/* 422 */     XYAreaRendererState areaState = (XYAreaRendererState)state;
/*     */     
/*     */ 
/* 425 */     double x1 = dataset.getXValue(series, item);
/* 426 */     double y1 = dataset.getYValue(series, item);
/* 427 */     if (Double.isNaN(y1)) {
/* 428 */       y1 = 0.0D;
/*     */     }
/* 430 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 432 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 437 */     int itemCount = dataset.getItemCount(series);
/* 438 */     double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
/* 439 */     double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
/* 440 */     if (Double.isNaN(y0)) {
/* 441 */       y0 = 0.0D;
/*     */     }
/* 443 */     double transX0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 445 */     double transY0 = rangeAxis.valueToJava2D(y0, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 448 */     double x2 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
/*     */     
/* 450 */     double y2 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
/*     */     
/* 452 */     if (Double.isNaN(y2)) {
/* 453 */       y2 = 0.0D;
/*     */     }
/* 455 */     double transX2 = domainAxis.valueToJava2D(x2, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 457 */     double transY2 = rangeAxis.valueToJava2D(y2, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 460 */     double transZero = rangeAxis.valueToJava2D(0.0D, dataArea, plot.getRangeAxisEdge());
/*     */     
/* 462 */     Polygon hotspot = null;
/* 463 */     if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 464 */       hotspot = new Polygon();
/* 465 */       hotspot.addPoint((int)transZero, (int)((transX0 + transX1) / 2.0D));
/*     */       
/* 467 */       hotspot.addPoint((int)((transY0 + transY1) / 2.0D), (int)((transX0 + transX1) / 2.0D));
/*     */       
/* 469 */       hotspot.addPoint((int)transY1, (int)transX1);
/* 470 */       hotspot.addPoint((int)((transY1 + transY2) / 2.0D), (int)((transX1 + transX2) / 2.0D));
/*     */       
/* 472 */       hotspot.addPoint((int)transZero, (int)((transX1 + transX2) / 2.0D));
/*     */     }
/*     */     else
/*     */     {
/* 476 */       hotspot = new Polygon();
/* 477 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)transZero);
/*     */       
/* 479 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)((transY0 + transY1) / 2.0D));
/*     */       
/* 481 */       hotspot.addPoint((int)transX1, (int)transY1);
/* 482 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)((transY1 + transY2) / 2.0D));
/*     */       
/* 484 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)transZero);
/*     */     }
/*     */     
/*     */ 
/* 488 */     if (item == 0) {
/* 489 */       areaState.area = new Polygon();
/*     */       
/* 491 */       double zero = rangeAxis.valueToJava2D(0.0D, dataArea, plot.getRangeAxisEdge());
/*     */       
/* 493 */       if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 494 */         areaState.area.addPoint((int)transX1, (int)zero);
/*     */       }
/* 496 */       else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 497 */         areaState.area.addPoint((int)zero, (int)transX1);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 502 */     if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 503 */       areaState.area.addPoint((int)transX1, (int)transY1);
/*     */     }
/* 505 */     else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 506 */       areaState.area.addPoint((int)transY1, (int)transX1);
/*     */     }
/*     */     
/* 509 */     PlotOrientation orientation = plot.getOrientation();
/* 510 */     Paint paint = getItemPaint(series, item);
/* 511 */     Stroke stroke = getItemStroke(series, item);
/* 512 */     g2.setPaint(paint);
/* 513 */     g2.setStroke(stroke);
/*     */     
/* 515 */     Shape shape = null;
/* 516 */     if (getPlotShapes()) {
/* 517 */       shape = getItemShape(series, item);
/* 518 */       if (orientation == PlotOrientation.VERTICAL) {
/* 519 */         shape = ShapeUtilities.createTranslatedShape(shape, transX1, transY1);
/*     */ 
/*     */       }
/* 522 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 523 */         shape = ShapeUtilities.createTranslatedShape(shape, transY1, transX1);
/*     */       }
/*     */       
/* 526 */       g2.draw(shape);
/*     */     }
/*     */     
/* 529 */     if ((getPlotLines()) && 
/* 530 */       (item > 0)) {
/* 531 */       if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 532 */         areaState.line.setLine(transX0, transY0, transX1, transY1);
/*     */       }
/* 534 */       else if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 535 */         areaState.line.setLine(transY0, transX0, transY1, transX1);
/*     */       }
/* 537 */       g2.draw(areaState.line);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 543 */     if ((getPlotArea()) && (item > 0) && (item == itemCount - 1))
/*     */     {
/* 545 */       if (orientation == PlotOrientation.VERTICAL)
/*     */       {
/* 547 */         areaState.area.addPoint((int)transX1, (int)transZero);
/*     */       }
/* 549 */       else if (orientation == PlotOrientation.HORIZONTAL)
/*     */       {
/* 551 */         areaState.area.addPoint((int)transZero, (int)transX1);
/*     */       }
/*     */       
/* 554 */       g2.fill(areaState.area);
/*     */       
/*     */ 
/* 557 */       if (isOutline()) {
/* 558 */         Shape area = areaState.area;
/*     */         
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 565 */         Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/* 566 */         if ((outlineStroke instanceof BasicStroke)) {
/* 567 */           BasicStroke bs = (BasicStroke)outlineStroke;
/* 568 */           if (bs.getDashArray() != null) {
/* 569 */             Area poly = new Area(areaState.area);
/*     */             
/*     */ 
/*     */ 
/* 573 */             Area clip = new Area(new Rectangle2D.Double(dataArea.getX() - 5.0D, dataArea.getY() - 5.0D, dataArea.getWidth() + 10.0D, dataArea.getHeight() + 10.0D));
/*     */             
/*     */ 
/*     */ 
/* 577 */             poly.intersect(clip);
/* 578 */             area = poly;
/*     */           }
/*     */         }
/*     */         
/* 582 */         g2.setStroke(outlineStroke);
/* 583 */         g2.setPaint(lookupSeriesOutlinePaint(series));
/* 584 */         g2.draw(area);
/*     */       }
/*     */     }
/*     */     
/* 588 */     int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 589 */     int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 590 */     updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*     */     
/*     */ 
/*     */ 
/* 594 */     EntityCollection entities = state.getEntityCollection();
/* 595 */     if ((entities != null) && (hotspot != null)) {
/* 596 */       addEntity(entities, hotspot, dataset, series, item, 0.0D, 0.0D);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 609 */     XYAreaRenderer clone = (XYAreaRenderer)super.clone();
/* 610 */     clone.legendArea = ShapeUtilities.clone(this.legendArea);
/* 611 */     return clone;
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
/* 622 */     if (obj == this) {
/* 623 */       return true;
/*     */     }
/* 625 */     if (!(obj instanceof XYAreaRenderer)) {
/* 626 */       return false;
/*     */     }
/* 628 */     XYAreaRenderer that = (XYAreaRenderer)obj;
/* 629 */     if (this.plotArea != that.plotArea) {
/* 630 */       return false;
/*     */     }
/* 632 */     if (this.plotLines != that.plotLines) {
/* 633 */       return false;
/*     */     }
/* 635 */     if (this.plotShapes != that.plotShapes) {
/* 636 */       return false;
/*     */     }
/* 638 */     if (this.showOutline != that.showOutline) {
/* 639 */       return false;
/*     */     }
/* 641 */     if (!ShapeUtilities.equal(this.legendArea, that.legendArea)) {
/* 642 */       return false;
/*     */     }
/* 644 */     return true;
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
/* 657 */     stream.defaultReadObject();
/* 658 */     this.legendArea = SerialUtilities.readShape(stream);
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
/* 669 */     stream.defaultWriteObject();
/* 670 */     SerialUtilities.writeShape(this.legendArea, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYAreaRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */