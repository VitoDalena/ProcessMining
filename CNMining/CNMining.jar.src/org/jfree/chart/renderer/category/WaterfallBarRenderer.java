/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformType;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.StandardGradientPaintTransformer;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WaterfallBarRenderer
/*     */   extends BarRenderer
/*     */ {
/*     */   private static final long serialVersionUID = -2482910643727230911L;
/*     */   private transient Paint firstBarPaint;
/*     */   private transient Paint lastBarPaint;
/*     */   private transient Paint positiveBarPaint;
/*     */   private transient Paint negativeBarPaint;
/*     */   
/*     */   public WaterfallBarRenderer()
/*     */   {
/* 126 */     this(new GradientPaint(0.0F, 0.0F, new Color(34, 34, 255), 0.0F, 0.0F, new Color(102, 102, 255)), new GradientPaint(0.0F, 0.0F, new Color(34, 255, 34), 0.0F, 0.0F, new Color(102, 255, 102)), new GradientPaint(0.0F, 0.0F, new Color(255, 34, 34), 0.0F, 0.0F, new Color(255, 102, 102)), new GradientPaint(0.0F, 0.0F, new Color(255, 255, 34), 0.0F, 0.0F, new Color(255, 255, 102)));
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
/*     */   public WaterfallBarRenderer(Paint firstBarPaint, Paint positiveBarPaint, Paint negativeBarPaint, Paint lastBarPaint)
/*     */   {
/* 153 */     if (firstBarPaint == null) {
/* 154 */       throw new IllegalArgumentException("Null 'firstBarPaint' argument");
/*     */     }
/* 156 */     if (positiveBarPaint == null) {
/* 157 */       throw new IllegalArgumentException("Null 'positiveBarPaint' argument");
/*     */     }
/*     */     
/* 160 */     if (negativeBarPaint == null) {
/* 161 */       throw new IllegalArgumentException("Null 'negativeBarPaint' argument");
/*     */     }
/*     */     
/* 164 */     if (lastBarPaint == null) {
/* 165 */       throw new IllegalArgumentException("Null 'lastBarPaint' argument");
/*     */     }
/* 167 */     this.firstBarPaint = firstBarPaint;
/* 168 */     this.lastBarPaint = lastBarPaint;
/* 169 */     this.positiveBarPaint = positiveBarPaint;
/* 170 */     this.negativeBarPaint = negativeBarPaint;
/* 171 */     setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.CENTER_VERTICAL));
/*     */     
/* 173 */     setMinimumBarLength(1.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getFirstBarPaint()
/*     */   {
/* 182 */     return this.firstBarPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFirstBarPaint(Paint paint)
/*     */   {
/* 192 */     if (paint == null) {
/* 193 */       throw new IllegalArgumentException("Null 'paint' argument");
/*     */     }
/* 195 */     this.firstBarPaint = paint;
/* 196 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getLastBarPaint()
/*     */   {
/* 205 */     return this.lastBarPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLastBarPaint(Paint paint)
/*     */   {
/* 215 */     if (paint == null) {
/* 216 */       throw new IllegalArgumentException("Null 'paint' argument");
/*     */     }
/* 218 */     this.lastBarPaint = paint;
/* 219 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPositiveBarPaint()
/*     */   {
/* 228 */     return this.positiveBarPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPositiveBarPaint(Paint paint)
/*     */   {
/* 237 */     if (paint == null) {
/* 238 */       throw new IllegalArgumentException("Null 'paint' argument");
/*     */     }
/* 240 */     this.positiveBarPaint = paint;
/* 241 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getNegativeBarPaint()
/*     */   {
/* 250 */     return this.negativeBarPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setNegativeBarPaint(Paint paint)
/*     */   {
/* 260 */     if (paint == null) {
/* 261 */       throw new IllegalArgumentException("Null 'paint' argument");
/*     */     }
/* 263 */     this.negativeBarPaint = paint;
/* 264 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range findRangeBounds(CategoryDataset dataset)
/*     */   {
/* 276 */     if (dataset == null) {
/* 277 */       return null;
/*     */     }
/* 279 */     boolean allItemsNull = true;
/*     */     
/* 281 */     double minimum = 0.0D;
/* 282 */     double maximum = 0.0D;
/* 283 */     int columnCount = dataset.getColumnCount();
/* 284 */     for (int row = 0; row < dataset.getRowCount(); row++) {
/* 285 */       double runningTotal = 0.0D;
/* 286 */       for (int column = 0; column <= columnCount - 1; column++) {
/* 287 */         Number n = dataset.getValue(row, column);
/* 288 */         if (n != null) {
/* 289 */           allItemsNull = false;
/* 290 */           double value = n.doubleValue();
/* 291 */           if (column == columnCount - 1)
/*     */           {
/* 293 */             runningTotal = value;
/*     */           }
/*     */           else {
/* 296 */             runningTotal += value;
/*     */           }
/* 298 */           minimum = Math.min(minimum, runningTotal);
/* 299 */           maximum = Math.max(maximum, runningTotal);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 304 */     if (!allItemsNull) {
/* 305 */       return new Range(minimum, maximum);
/*     */     }
/*     */     
/* 308 */     return null;
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 338 */     double previous = state.getSeriesRunningTotal();
/* 339 */     if (column == dataset.getColumnCount() - 1) {
/* 340 */       previous = 0.0D;
/*     */     }
/* 342 */     double current = 0.0D;
/* 343 */     Number n = dataset.getValue(row, column);
/* 344 */     if (n != null) {
/* 345 */       current = previous + n.doubleValue();
/*     */     }
/* 347 */     state.setSeriesRunningTotal(current);
/*     */     
/* 349 */     int categoryCount = getColumnCount();
/* 350 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 352 */     double rectX = 0.0D;
/* 353 */     double rectY = 0.0D;
/*     */     
/* 355 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/*     */     
/*     */ 
/* 358 */     double j2dy0 = rangeAxis.valueToJava2D(previous, dataArea, rangeAxisLocation);
/*     */     
/*     */ 
/*     */ 
/* 362 */     double j2dy1 = rangeAxis.valueToJava2D(current, dataArea, rangeAxisLocation);
/*     */     
/*     */ 
/* 365 */     double valDiff = current - previous;
/* 366 */     if (j2dy1 < j2dy0) {
/* 367 */       double temp = j2dy1;
/* 368 */       j2dy1 = j2dy0;
/* 369 */       j2dy0 = temp;
/*     */     }
/*     */     
/*     */ 
/* 373 */     double rectWidth = state.getBarWidth();
/*     */     
/*     */ 
/* 376 */     double rectHeight = Math.max(getMinimumBarLength(), Math.abs(j2dy1 - j2dy0));
/*     */     
/*     */ 
/* 379 */     Comparable seriesKey = dataset.getRowKey(row);
/* 380 */     Comparable categoryKey = dataset.getColumnKey(column);
/* 381 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 382 */       rectY = domainAxis.getCategorySeriesMiddle(categoryKey, seriesKey, dataset, getItemMargin(), dataArea, RectangleEdge.LEFT);
/*     */       
/*     */ 
/* 385 */       rectX = j2dy0;
/* 386 */       rectHeight = state.getBarWidth();
/* 387 */       rectY -= rectHeight / 2.0D;
/* 388 */       rectWidth = Math.max(getMinimumBarLength(), Math.abs(j2dy1 - j2dy0));
/*     */ 
/*     */ 
/*     */     }
/* 392 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 393 */       rectX = domainAxis.getCategorySeriesMiddle(categoryKey, seriesKey, dataset, getItemMargin(), dataArea, RectangleEdge.TOP);
/*     */       
/* 395 */       rectX -= rectWidth / 2.0D;
/* 396 */       rectY = j2dy0;
/*     */     }
/* 398 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/*     */     
/* 400 */     Paint seriesPaint = getFirstBarPaint();
/* 401 */     if (column == 0) {
/* 402 */       seriesPaint = getFirstBarPaint();
/*     */     }
/* 404 */     else if (column == categoryCount - 1) {
/* 405 */       seriesPaint = getLastBarPaint();
/*     */ 
/*     */     }
/* 408 */     else if (valDiff < 0.0D) {
/* 409 */       seriesPaint = getNegativeBarPaint();
/*     */     }
/* 411 */     else if (valDiff > 0.0D) {
/* 412 */       seriesPaint = getPositiveBarPaint();
/*     */     }
/*     */     else {
/* 415 */       seriesPaint = getLastBarPaint();
/*     */     }
/*     */     
/* 418 */     if ((getGradientPaintTransformer() != null) && ((seriesPaint instanceof GradientPaint)))
/*     */     {
/* 420 */       GradientPaint gp = (GradientPaint)seriesPaint;
/* 421 */       seriesPaint = getGradientPaintTransformer().transform(gp, bar);
/*     */     }
/* 423 */     g2.setPaint(seriesPaint);
/* 424 */     g2.fill(bar);
/*     */     
/*     */ 
/* 427 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 429 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 430 */       Paint paint = getItemOutlinePaint(row, column);
/* 431 */       if ((stroke != null) && (paint != null)) {
/* 432 */         g2.setStroke(stroke);
/* 433 */         g2.setPaint(paint);
/* 434 */         g2.draw(bar);
/*     */       }
/*     */     }
/*     */     
/* 438 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 440 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 441 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, valDiff < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 446 */     EntityCollection entities = state.getEntityCollection();
/* 447 */     if (entities != null) {
/* 448 */       addItemEntity(entities, dataset, row, column, bar);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 462 */     if (obj == this) {
/* 463 */       return true;
/*     */     }
/* 465 */     if (!super.equals(obj)) {
/* 466 */       return false;
/*     */     }
/* 468 */     if (!(obj instanceof WaterfallBarRenderer)) {
/* 469 */       return false;
/*     */     }
/* 471 */     WaterfallBarRenderer that = (WaterfallBarRenderer)obj;
/* 472 */     if (!PaintUtilities.equal(this.firstBarPaint, that.firstBarPaint)) {
/* 473 */       return false;
/*     */     }
/* 475 */     if (!PaintUtilities.equal(this.lastBarPaint, that.lastBarPaint)) {
/* 476 */       return false;
/*     */     }
/* 478 */     if (!PaintUtilities.equal(this.positiveBarPaint, that.positiveBarPaint))
/*     */     {
/* 480 */       return false;
/*     */     }
/* 482 */     if (!PaintUtilities.equal(this.negativeBarPaint, that.negativeBarPaint))
/*     */     {
/* 484 */       return false;
/*     */     }
/* 486 */     return true;
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
/* 498 */     stream.defaultWriteObject();
/* 499 */     SerialUtilities.writePaint(this.firstBarPaint, stream);
/* 500 */     SerialUtilities.writePaint(this.lastBarPaint, stream);
/* 501 */     SerialUtilities.writePaint(this.positiveBarPaint, stream);
/* 502 */     SerialUtilities.writePaint(this.negativeBarPaint, stream);
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
/* 515 */     stream.defaultReadObject();
/* 516 */     this.firstBarPaint = SerialUtilities.readPaint(stream);
/* 517 */     this.lastBarPaint = SerialUtilities.readPaint(stream);
/* 518 */     this.positiveBarPaint = SerialUtilities.readPaint(stream);
/* 519 */     this.negativeBarPaint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/WaterfallBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */