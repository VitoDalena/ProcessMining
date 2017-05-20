/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.gantt.GanttCategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GanttRenderer
/*     */   extends IntervalBarRenderer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4010349116350119512L;
/*     */   private transient Paint completePaint;
/*     */   private transient Paint incompletePaint;
/*     */   private double startPercent;
/*     */   private double endPercent;
/*     */   
/*     */   public GanttRenderer()
/*     */   {
/* 116 */     setIncludeBaseInRange(false);
/* 117 */     this.completePaint = Color.green;
/* 118 */     this.incompletePaint = Color.red;
/* 119 */     this.startPercent = 0.35D;
/* 120 */     this.endPercent = 0.65D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getCompletePaint()
/*     */   {
/* 131 */     return this.completePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCompletePaint(Paint paint)
/*     */   {
/* 143 */     if (paint == null) {
/* 144 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 146 */     this.completePaint = paint;
/* 147 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getIncompletePaint()
/*     */   {
/* 158 */     return this.incompletePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setIncompletePaint(Paint paint)
/*     */   {
/* 170 */     if (paint == null) {
/* 171 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 173 */     this.incompletePaint = paint;
/* 174 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getStartPercent()
/*     */   {
/* 186 */     return this.startPercent;
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
/*     */   public void setStartPercent(double percent)
/*     */   {
/* 199 */     this.startPercent = percent;
/* 200 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getEndPercent()
/*     */   {
/* 212 */     return this.endPercent;
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
/*     */   public void setEndPercent(double percent)
/*     */   {
/* 225 */     this.endPercent = percent;
/* 226 */     fireChangeEvent();
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 254 */     if ((dataset instanceof GanttCategoryDataset)) {
/* 255 */       GanttCategoryDataset gcd = (GanttCategoryDataset)dataset;
/* 256 */       drawTasks(g2, state, dataArea, plot, domainAxis, rangeAxis, gcd, row, column);
/*     */     }
/*     */     else
/*     */     {
/* 260 */       super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
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
/*     */   protected void drawTasks(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, GanttCategoryDataset dataset, int row, int column)
/*     */   {
/* 289 */     int count = dataset.getSubIntervalCount(row, column);
/* 290 */     if (count == 0) {
/* 291 */       drawTask(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column);
/*     */     }
/*     */     
/*     */ 
/* 295 */     PlotOrientation orientation = plot.getOrientation();
/* 296 */     for (int subinterval = 0; subinterval < count; subinterval++)
/*     */     {
/* 298 */       RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/*     */       
/*     */ 
/* 301 */       Number value0 = dataset.getStartValue(row, column, subinterval);
/* 302 */       if (value0 == null) {
/* 303 */         return;
/*     */       }
/* 305 */       double translatedValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), dataArea, rangeAxisLocation);
/*     */       
/*     */ 
/*     */ 
/* 309 */       Number value1 = dataset.getEndValue(row, column, subinterval);
/* 310 */       if (value1 == null) {
/* 311 */         return;
/*     */       }
/* 313 */       double translatedValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), dataArea, rangeAxisLocation);
/*     */       
/*     */ 
/* 316 */       if (translatedValue1 < translatedValue0) {
/* 317 */         double temp = translatedValue1;
/* 318 */         translatedValue1 = translatedValue0;
/* 319 */         translatedValue0 = temp;
/*     */       }
/*     */       
/* 322 */       double rectStart = calculateBarW0(plot, plot.getOrientation(), dataArea, domainAxis, state, row, column);
/*     */       
/* 324 */       double rectLength = Math.abs(translatedValue1 - translatedValue0);
/* 325 */       double rectBreadth = state.getBarWidth();
/*     */       
/*     */ 
/* 328 */       Rectangle2D bar = null;
/* 329 */       RectangleEdge barBase = null;
/* 330 */       if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 331 */         bar = new Rectangle2D.Double(translatedValue0, rectStart, rectLength, rectBreadth);
/*     */         
/* 333 */         barBase = RectangleEdge.LEFT;
/*     */       }
/* 335 */       else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 336 */         bar = new Rectangle2D.Double(rectStart, translatedValue0, rectBreadth, rectLength);
/*     */         
/* 338 */         barBase = RectangleEdge.BOTTOM;
/*     */       }
/*     */       
/* 341 */       Rectangle2D completeBar = null;
/* 342 */       Rectangle2D incompleteBar = null;
/* 343 */       Number percent = dataset.getPercentComplete(row, column, subinterval);
/*     */       
/* 345 */       double start = getStartPercent();
/* 346 */       double end = getEndPercent();
/* 347 */       if (percent != null) {
/* 348 */         double p = percent.doubleValue();
/* 349 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 350 */           completeBar = new Rectangle2D.Double(translatedValue0, rectStart + start * rectBreadth, rectLength * p, rectBreadth * (end - start));
/*     */           
/*     */ 
/* 353 */           incompleteBar = new Rectangle2D.Double(translatedValue0 + rectLength * p, rectStart + start * rectBreadth, rectLength * (1.0D - p), rectBreadth * (end - start));
/*     */ 
/*     */ 
/*     */         }
/* 357 */         else if (orientation == PlotOrientation.VERTICAL) {
/* 358 */           completeBar = new Rectangle2D.Double(rectStart + start * rectBreadth, translatedValue0 + rectLength * (1.0D - p), rectBreadth * (end - start), rectLength * p);
/*     */           
/*     */ 
/*     */ 
/* 362 */           incompleteBar = new Rectangle2D.Double(rectStart + start * rectBreadth, translatedValue0, rectBreadth * (end - start), rectLength * (1.0D - p));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 369 */       if (getShadowsVisible()) {
/* 370 */         getBarPainter().paintBarShadow(g2, this, row, column, bar, barBase, true);
/*     */       }
/*     */       
/* 373 */       getBarPainter().paintBar(g2, this, row, column, bar, barBase);
/*     */       
/* 375 */       if (completeBar != null) {
/* 376 */         g2.setPaint(getCompletePaint());
/* 377 */         g2.fill(completeBar);
/*     */       }
/* 379 */       if (incompleteBar != null) {
/* 380 */         g2.setPaint(getIncompletePaint());
/* 381 */         g2.fill(incompleteBar);
/*     */       }
/* 383 */       if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */       {
/* 385 */         g2.setStroke(getItemStroke(row, column));
/* 386 */         g2.setPaint(getItemOutlinePaint(row, column));
/* 387 */         g2.draw(bar);
/*     */       }
/*     */       
/* 390 */       if (subinterval == count - 1)
/*     */       {
/* 392 */         int datasetIndex = plot.indexOf(dataset);
/* 393 */         Comparable columnKey = dataset.getColumnKey(column);
/* 394 */         Comparable rowKey = dataset.getRowKey(row);
/* 395 */         double xx = domainAxis.getCategorySeriesMiddle(columnKey, rowKey, dataset, getItemMargin(), dataArea, plot.getDomainAxisEdge());
/*     */         
/*     */ 
/* 398 */         updateCrosshairValues(state.getCrosshairState(), dataset.getRowKey(row), dataset.getColumnKey(column), value1.doubleValue(), datasetIndex, xx, translatedValue1, orientation);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 405 */       if (state.getInfo() != null) {
/* 406 */         EntityCollection entities = state.getEntityCollection();
/* 407 */         if (entities != null) {
/* 408 */           addItemEntity(entities, dataset, row, column, bar);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void drawTask(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, GanttCategoryDataset dataset, int row, int column)
/*     */   {
/* 437 */     PlotOrientation orientation = plot.getOrientation();
/* 438 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/*     */     
/*     */ 
/* 441 */     Number value0 = dataset.getEndValue(row, column);
/* 442 */     if (value0 == null) {
/* 443 */       return;
/*     */     }
/* 445 */     double java2dValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), dataArea, rangeAxisLocation);
/*     */     
/*     */ 
/*     */ 
/* 449 */     Number value1 = dataset.getStartValue(row, column);
/* 450 */     if (value1 == null) {
/* 451 */       return;
/*     */     }
/* 453 */     double java2dValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), dataArea, rangeAxisLocation);
/*     */     
/*     */ 
/* 456 */     if (java2dValue1 < java2dValue0) {
/* 457 */       double temp = java2dValue1;
/* 458 */       java2dValue1 = java2dValue0;
/* 459 */       java2dValue0 = temp;
/* 460 */       Number tempNum = value1;
/* 461 */       value1 = value0;
/* 462 */       value0 = tempNum;
/*     */     }
/*     */     
/* 465 */     double rectStart = calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
/*     */     
/* 467 */     double rectBreadth = state.getBarWidth();
/* 468 */     double rectLength = Math.abs(java2dValue1 - java2dValue0);
/*     */     
/* 470 */     Rectangle2D bar = null;
/* 471 */     RectangleEdge barBase = null;
/* 472 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 473 */       bar = new Rectangle2D.Double(java2dValue0, rectStart, rectLength, rectBreadth);
/*     */       
/* 475 */       barBase = RectangleEdge.LEFT;
/*     */     }
/* 477 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 478 */       bar = new Rectangle2D.Double(rectStart, java2dValue1, rectBreadth, rectLength);
/*     */       
/* 480 */       barBase = RectangleEdge.BOTTOM;
/*     */     }
/*     */     
/* 483 */     Rectangle2D completeBar = null;
/* 484 */     Rectangle2D incompleteBar = null;
/* 485 */     Number percent = dataset.getPercentComplete(row, column);
/* 486 */     double start = getStartPercent();
/* 487 */     double end = getEndPercent();
/* 488 */     if (percent != null) {
/* 489 */       double p = percent.doubleValue();
/* 490 */       if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 491 */         completeBar = new Rectangle2D.Double(java2dValue0, rectStart + start * rectBreadth, rectLength * p, rectBreadth * (end - start));
/*     */         
/*     */ 
/* 494 */         incompleteBar = new Rectangle2D.Double(java2dValue0 + rectLength * p, rectStart + start * rectBreadth, rectLength * (1.0D - p), rectBreadth * (end - start));
/*     */ 
/*     */ 
/*     */       }
/* 498 */       else if (plot.getOrientation() == PlotOrientation.VERTICAL) {
/* 499 */         completeBar = new Rectangle2D.Double(rectStart + start * rectBreadth, java2dValue1 + rectLength * (1.0D - p), rectBreadth * (end - start), rectLength * p);
/*     */         
/*     */ 
/* 502 */         incompleteBar = new Rectangle2D.Double(rectStart + start * rectBreadth, java2dValue1, rectBreadth * (end - start), rectLength * (1.0D - p));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 509 */     if (getShadowsVisible()) {
/* 510 */       getBarPainter().paintBarShadow(g2, this, row, column, bar, barBase, true);
/*     */     }
/*     */     
/* 513 */     getBarPainter().paintBar(g2, this, row, column, bar, barBase);
/*     */     
/* 515 */     if (completeBar != null) {
/* 516 */       g2.setPaint(getCompletePaint());
/* 517 */       g2.fill(completeBar);
/*     */     }
/* 519 */     if (incompleteBar != null) {
/* 520 */       g2.setPaint(getIncompletePaint());
/* 521 */       g2.fill(incompleteBar);
/*     */     }
/*     */     
/*     */ 
/* 525 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 527 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 528 */       Paint paint = getItemOutlinePaint(row, column);
/* 529 */       if ((stroke != null) && (paint != null)) {
/* 530 */         g2.setStroke(stroke);
/* 531 */         g2.setPaint(paint);
/* 532 */         g2.draw(bar);
/*     */       }
/*     */     }
/*     */     
/* 536 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 538 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 539 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, false);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 544 */     int datasetIndex = plot.indexOf(dataset);
/* 545 */     Comparable columnKey = dataset.getColumnKey(column);
/* 546 */     Comparable rowKey = dataset.getRowKey(row);
/* 547 */     double xx = domainAxis.getCategorySeriesMiddle(columnKey, rowKey, dataset, getItemMargin(), dataArea, plot.getDomainAxisEdge());
/*     */     
/* 549 */     updateCrosshairValues(state.getCrosshairState(), dataset.getRowKey(row), dataset.getColumnKey(column), value1.doubleValue(), datasetIndex, xx, java2dValue1, orientation);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 555 */     EntityCollection entities = state.getEntityCollection();
/* 556 */     if (entities != null) {
/* 557 */       addItemEntity(entities, dataset, row, column, bar);
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
/*     */   public double getItemMiddle(Comparable rowKey, Comparable columnKey, CategoryDataset dataset, CategoryAxis axis, Rectangle2D area, RectangleEdge edge)
/*     */   {
/* 578 */     return axis.getCategorySeriesMiddle(columnKey, rowKey, dataset, getItemMargin(), area, edge);
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
/* 590 */     if (obj == this) {
/* 591 */       return true;
/*     */     }
/* 593 */     if (!(obj instanceof GanttRenderer)) {
/* 594 */       return false;
/*     */     }
/* 596 */     GanttRenderer that = (GanttRenderer)obj;
/* 597 */     if (!PaintUtilities.equal(this.completePaint, that.completePaint)) {
/* 598 */       return false;
/*     */     }
/* 600 */     if (!PaintUtilities.equal(this.incompletePaint, that.incompletePaint)) {
/* 601 */       return false;
/*     */     }
/* 603 */     if (this.startPercent != that.startPercent) {
/* 604 */       return false;
/*     */     }
/* 606 */     if (this.endPercent != that.endPercent) {
/* 607 */       return false;
/*     */     }
/* 609 */     return super.equals(obj);
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
/* 620 */     stream.defaultWriteObject();
/* 621 */     SerialUtilities.writePaint(this.completePaint, stream);
/* 622 */     SerialUtilities.writePaint(this.incompletePaint, stream);
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
/* 635 */     stream.defaultReadObject();
/* 636 */     this.completePaint = SerialUtilities.readPaint(stream);
/* 637 */     this.incompletePaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/GanttRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */