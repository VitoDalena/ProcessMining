/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
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
/*     */ import org.jfree.data.statistics.StatisticalCategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class StatisticalBarRenderer
/*     */   extends BarRenderer
/*     */   implements CategoryItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4986038395414039117L;
/*     */   private transient Paint errorIndicatorPaint;
/*     */   private transient Stroke errorIndicatorStroke;
/*     */   
/*     */   public StatisticalBarRenderer()
/*     */   {
/* 122 */     this.errorIndicatorPaint = Color.gray;
/* 123 */     this.errorIndicatorStroke = new BasicStroke(1.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getErrorIndicatorPaint()
/*     */   {
/* 135 */     return this.errorIndicatorPaint;
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
/*     */   public void setErrorIndicatorPaint(Paint paint)
/*     */   {
/* 148 */     this.errorIndicatorPaint = paint;
/* 149 */     fireChangeEvent();
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
/*     */   public Stroke getErrorIndicatorStroke()
/*     */   {
/* 163 */     return this.errorIndicatorStroke;
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
/*     */   public void setErrorIndicatorStroke(Stroke stroke)
/*     */   {
/* 179 */     this.errorIndicatorStroke = stroke;
/* 180 */     fireChangeEvent();
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset data, int row, int column, int pass)
/*     */   {
/* 209 */     int visibleRow = state.getVisibleSeriesIndex(row);
/* 210 */     if (visibleRow < 0) {
/* 211 */       return;
/*     */     }
/*     */     
/* 214 */     if (!(data instanceof StatisticalCategoryDataset)) {
/* 215 */       throw new IllegalArgumentException("Requires StatisticalCategoryDataset.");
/*     */     }
/*     */     
/* 218 */     StatisticalCategoryDataset statData = (StatisticalCategoryDataset)data;
/*     */     
/* 220 */     PlotOrientation orientation = plot.getOrientation();
/* 221 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 222 */       drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, statData, visibleRow, row, column);
/*     */ 
/*     */     }
/* 225 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 226 */       drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, statData, visibleRow, row, column);
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
/*     */   protected void drawHorizontalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, StatisticalCategoryDataset dataset, int visibleRow, int row, int column)
/*     */   {
/* 256 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*     */     
/*     */ 
/* 259 */     double rectY = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, xAxisLocation);
/*     */     
/*     */ 
/* 262 */     int seriesCount = state.getVisibleSeriesCount() >= 0 ? state.getVisibleSeriesCount() : getRowCount();
/*     */     
/* 264 */     int categoryCount = getColumnCount();
/* 265 */     if (seriesCount > 1) {
/* 266 */       double seriesGap = dataArea.getHeight() * getItemMargin() / (categoryCount * (seriesCount - 1));
/*     */       
/* 268 */       rectY += visibleRow * (state.getBarWidth() + seriesGap);
/*     */     }
/*     */     else {
/* 271 */       rectY += visibleRow * state.getBarWidth();
/*     */     }
/*     */     
/*     */ 
/* 275 */     Number meanValue = dataset.getMeanValue(row, column);
/* 276 */     if (meanValue == null) {
/* 277 */       return;
/*     */     }
/* 279 */     double value = meanValue.doubleValue();
/* 280 */     double base = 0.0D;
/* 281 */     double lclip = getLowerClip();
/* 282 */     double uclip = getUpperClip();
/*     */     
/* 284 */     if (uclip <= 0.0D) {
/* 285 */       if (value >= uclip) {
/* 286 */         return;
/*     */       }
/* 288 */       base = uclip;
/* 289 */       if (value <= lclip) {
/* 290 */         value = lclip;
/*     */       }
/*     */     }
/* 293 */     else if (lclip <= 0.0D) {
/* 294 */       if (value >= uclip) {
/* 295 */         value = uclip;
/*     */ 
/*     */       }
/* 298 */       else if (value <= lclip) {
/* 299 */         value = lclip;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 304 */       if (value <= lclip) {
/* 305 */         return;
/*     */       }
/* 307 */       base = getLowerClip();
/* 308 */       if (value >= uclip) {
/* 309 */         value = uclip;
/*     */       }
/*     */     }
/*     */     
/* 313 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 314 */     double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
/* 315 */     double transY2 = rangeAxis.valueToJava2D(value, dataArea, yAxisLocation);
/*     */     
/* 317 */     double rectX = Math.min(transY2, transY1);
/*     */     
/* 319 */     double rectHeight = state.getBarWidth();
/* 320 */     double rectWidth = Math.abs(transY2 - transY1);
/*     */     
/* 322 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/*     */     
/* 324 */     Paint itemPaint = getItemPaint(row, column);
/* 325 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 326 */     if ((t != null) && ((itemPaint instanceof GradientPaint))) {
/* 327 */       itemPaint = t.transform((GradientPaint)itemPaint, bar);
/*     */     }
/* 329 */     g2.setPaint(itemPaint);
/* 330 */     g2.fill(bar);
/*     */     
/*     */ 
/* 333 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 335 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 336 */       Paint paint = getItemOutlinePaint(row, column);
/* 337 */       if ((stroke != null) && (paint != null)) {
/* 338 */         g2.setStroke(stroke);
/* 339 */         g2.setPaint(paint);
/* 340 */         g2.draw(bar);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 345 */     Number n = dataset.getStdDevValue(row, column);
/* 346 */     if (n != null) {
/* 347 */       double valueDelta = n.doubleValue();
/* 348 */       double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
/*     */       
/* 350 */       double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
/*     */       
/*     */ 
/* 353 */       if (this.errorIndicatorPaint != null) {
/* 354 */         g2.setPaint(this.errorIndicatorPaint);
/*     */       }
/*     */       else {
/* 357 */         g2.setPaint(getItemOutlinePaint(row, column));
/*     */       }
/* 359 */       if (this.errorIndicatorStroke != null) {
/* 360 */         g2.setStroke(this.errorIndicatorStroke);
/*     */       }
/*     */       else {
/* 363 */         g2.setStroke(getItemOutlineStroke(row, column));
/*     */       }
/* 365 */       Line2D line = null;
/* 366 */       line = new Line2D.Double(lowVal, rectY + rectHeight / 2.0D, highVal, rectY + rectHeight / 2.0D);
/*     */       
/* 368 */       g2.draw(line);
/* 369 */       line = new Line2D.Double(highVal, rectY + rectHeight * 0.25D, highVal, rectY + rectHeight * 0.75D);
/*     */       
/* 371 */       g2.draw(line);
/* 372 */       line = new Line2D.Double(lowVal, rectY + rectHeight * 0.25D, lowVal, rectY + rectHeight * 0.75D);
/*     */       
/* 374 */       g2.draw(line);
/*     */     }
/*     */     
/* 377 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 379 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 380 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 385 */     EntityCollection entities = state.getEntityCollection();
/* 386 */     if (entities != null) {
/* 387 */       addItemEntity(entities, dataset, row, column, bar);
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
/*     */   protected void drawVerticalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, StatisticalCategoryDataset dataset, int visibleRow, int row, int column)
/*     */   {
/* 417 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/*     */     
/*     */ 
/* 420 */     double rectX = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, xAxisLocation);
/*     */     
/*     */ 
/* 423 */     int seriesCount = state.getVisibleSeriesCount() >= 0 ? state.getVisibleSeriesCount() : getRowCount();
/*     */     
/* 425 */     int categoryCount = getColumnCount();
/* 426 */     if (seriesCount > 1) {
/* 427 */       double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/*     */       
/* 429 */       rectX += visibleRow * (state.getBarWidth() + seriesGap);
/*     */     }
/*     */     else {
/* 432 */       rectX += visibleRow * state.getBarWidth();
/*     */     }
/*     */     
/*     */ 
/* 436 */     Number meanValue = dataset.getMeanValue(row, column);
/* 437 */     if (meanValue == null) {
/* 438 */       return;
/*     */     }
/*     */     
/* 441 */     double value = meanValue.doubleValue();
/* 442 */     double base = 0.0D;
/* 443 */     double lclip = getLowerClip();
/* 444 */     double uclip = getUpperClip();
/*     */     
/* 446 */     if (uclip <= 0.0D) {
/* 447 */       if (value >= uclip) {
/* 448 */         return;
/*     */       }
/* 450 */       base = uclip;
/* 451 */       if (value <= lclip) {
/* 452 */         value = lclip;
/*     */       }
/*     */     }
/* 455 */     else if (lclip <= 0.0D) {
/* 456 */       if (value >= uclip) {
/* 457 */         value = uclip;
/*     */ 
/*     */       }
/* 460 */       else if (value <= lclip) {
/* 461 */         value = lclip;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 466 */       if (value <= lclip) {
/* 467 */         return;
/*     */       }
/* 469 */       base = getLowerClip();
/* 470 */       if (value >= uclip) {
/* 471 */         value = uclip;
/*     */       }
/*     */     }
/*     */     
/* 475 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 476 */     double transY1 = rangeAxis.valueToJava2D(base, dataArea, yAxisLocation);
/* 477 */     double transY2 = rangeAxis.valueToJava2D(value, dataArea, yAxisLocation);
/*     */     
/* 479 */     double rectY = Math.min(transY2, transY1);
/*     */     
/* 481 */     double rectWidth = state.getBarWidth();
/* 482 */     double rectHeight = Math.abs(transY2 - transY1);
/*     */     
/* 484 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/*     */     
/* 486 */     Paint itemPaint = getItemPaint(row, column);
/* 487 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 488 */     if ((t != null) && ((itemPaint instanceof GradientPaint))) {
/* 489 */       itemPaint = t.transform((GradientPaint)itemPaint, bar);
/*     */     }
/* 491 */     g2.setPaint(itemPaint);
/* 492 */     g2.fill(bar);
/*     */     
/* 494 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 496 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 497 */       Paint paint = getItemOutlinePaint(row, column);
/* 498 */       if ((stroke != null) && (paint != null)) {
/* 499 */         g2.setStroke(stroke);
/* 500 */         g2.setPaint(paint);
/* 501 */         g2.draw(bar);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 506 */     Number n = dataset.getStdDevValue(row, column);
/* 507 */     if (n != null) {
/* 508 */       double valueDelta = n.doubleValue();
/* 509 */       double highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
/*     */       
/* 511 */       double lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
/*     */       
/*     */ 
/* 514 */       if (this.errorIndicatorPaint != null) {
/* 515 */         g2.setPaint(this.errorIndicatorPaint);
/*     */       }
/*     */       else {
/* 518 */         g2.setPaint(getItemOutlinePaint(row, column));
/*     */       }
/* 520 */       if (this.errorIndicatorStroke != null) {
/* 521 */         g2.setStroke(this.errorIndicatorStroke);
/*     */       }
/*     */       else {
/* 524 */         g2.setStroke(getItemOutlineStroke(row, column));
/*     */       }
/*     */       
/* 527 */       Line2D line = null;
/* 528 */       line = new Line2D.Double(rectX + rectWidth / 2.0D, lowVal, rectX + rectWidth / 2.0D, highVal);
/*     */       
/* 530 */       g2.draw(line);
/* 531 */       line = new Line2D.Double(rectX + rectWidth / 2.0D - 5.0D, highVal, rectX + rectWidth / 2.0D + 5.0D, highVal);
/*     */       
/* 533 */       g2.draw(line);
/* 534 */       line = new Line2D.Double(rectX + rectWidth / 2.0D - 5.0D, lowVal, rectX + rectWidth / 2.0D + 5.0D, lowVal);
/*     */       
/* 536 */       g2.draw(line);
/*     */     }
/*     */     
/* 539 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 541 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 542 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 547 */     EntityCollection entities = state.getEntityCollection();
/* 548 */     if (entities != null) {
/* 549 */       addItemEntity(entities, dataset, row, column, bar);
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
/* 561 */     if (obj == this) {
/* 562 */       return true;
/*     */     }
/* 564 */     if (!(obj instanceof StatisticalBarRenderer)) {
/* 565 */       return false;
/*     */     }
/* 567 */     StatisticalBarRenderer that = (StatisticalBarRenderer)obj;
/* 568 */     if (!PaintUtilities.equal(this.errorIndicatorPaint, that.errorIndicatorPaint))
/*     */     {
/* 570 */       return false;
/*     */     }
/* 572 */     if (!ObjectUtilities.equal(this.errorIndicatorStroke, that.errorIndicatorStroke))
/*     */     {
/* 574 */       return false;
/*     */     }
/* 576 */     return super.equals(obj);
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
/* 587 */     stream.defaultWriteObject();
/* 588 */     SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
/* 589 */     SerialUtilities.writeStroke(this.errorIndicatorStroke, stream);
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
/* 602 */     stream.defaultReadObject();
/* 603 */     this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
/* 604 */     this.errorIndicatorStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/StatisticalBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */