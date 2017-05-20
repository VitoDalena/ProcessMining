/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.StatisticalCategoryDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class StatisticalLineAndShapeRenderer
/*     */   extends LineAndShapeRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3557517173697777579L;
/*     */   private transient Paint errorIndicatorPaint;
/*     */   private transient Stroke errorIndicatorStroke;
/*     */   
/*     */   public StatisticalLineAndShapeRenderer()
/*     */   {
/* 120 */     this(true, true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StatisticalLineAndShapeRenderer(boolean linesVisible, boolean shapesVisible)
/*     */   {
/* 131 */     super(linesVisible, shapesVisible);
/* 132 */     this.errorIndicatorPaint = null;
/* 133 */     this.errorIndicatorStroke = null;
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
/* 145 */     return this.errorIndicatorPaint;
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
/* 158 */     this.errorIndicatorPaint = paint;
/* 159 */     fireChangeEvent();
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
/* 173 */     return this.errorIndicatorStroke;
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
/*     */   public void setErrorIndicatorStroke(Stroke stroke)
/*     */   {
/* 188 */     this.errorIndicatorStroke = stroke;
/* 189 */     fireChangeEvent();
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
/*     */   public Range findRangeBounds(CategoryDataset dataset)
/*     */   {
/* 202 */     return findRangeBounds(dataset, true);
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
/* 232 */     if (!getItemVisible(row, column)) {
/* 233 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 238 */     if (!(dataset instanceof StatisticalCategoryDataset)) {
/* 239 */       super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
/*     */       
/* 241 */       return;
/*     */     }
/*     */     
/* 244 */     int visibleRow = state.getVisibleSeriesIndex(row);
/* 245 */     if (visibleRow < 0) {
/* 246 */       return;
/*     */     }
/* 248 */     int visibleRowCount = state.getVisibleSeriesCount();
/*     */     
/* 250 */     StatisticalCategoryDataset statDataset = (StatisticalCategoryDataset)dataset;
/*     */     
/* 252 */     Number meanValue = statDataset.getMeanValue(row, column);
/* 253 */     if (meanValue == null) {
/* 254 */       return;
/*     */     }
/* 256 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */     double x1;
/*     */     double x1;
/* 260 */     if (getUseSeriesOffset()) {
/* 261 */       x1 = domainAxis.getCategorySeriesMiddle(column, dataset.getColumnCount(), visibleRow, visibleRowCount, getItemMargin(), dataArea, plot.getDomainAxisEdge());
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/*     */ 
/* 267 */       x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     }
/*     */     
/* 270 */     double y1 = rangeAxis.valueToJava2D(meanValue.doubleValue(), dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 276 */     Number sdv = statDataset.getStdDevValue(row, column);
/* 277 */     if ((pass == 1) && (sdv != null))
/*     */     {
/* 279 */       RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/* 280 */       double valueDelta = sdv.doubleValue();
/*     */       double highVal;
/* 282 */       double highVal; if (meanValue.doubleValue() + valueDelta > rangeAxis.getRange().getUpperBound())
/*     */       {
/* 284 */         highVal = rangeAxis.valueToJava2D(rangeAxis.getRange().getUpperBound(), dataArea, yAxisLocation);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 289 */         highVal = rangeAxis.valueToJava2D(meanValue.doubleValue() + valueDelta, dataArea, yAxisLocation);
/*     */       }
/*     */       double lowVal;
/*     */       double lowVal;
/* 293 */       if (meanValue.doubleValue() + valueDelta < rangeAxis.getRange().getLowerBound())
/*     */       {
/* 295 */         lowVal = rangeAxis.valueToJava2D(rangeAxis.getRange().getLowerBound(), dataArea, yAxisLocation);
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 300 */         lowVal = rangeAxis.valueToJava2D(meanValue.doubleValue() - valueDelta, dataArea, yAxisLocation);
/*     */       }
/*     */       
/*     */ 
/* 304 */       if (this.errorIndicatorPaint != null) {
/* 305 */         g2.setPaint(this.errorIndicatorPaint);
/*     */       }
/*     */       else {
/* 308 */         g2.setPaint(getItemPaint(row, column));
/*     */       }
/* 310 */       if (this.errorIndicatorStroke != null) {
/* 311 */         g2.setStroke(this.errorIndicatorStroke);
/*     */       }
/*     */       else {
/* 314 */         g2.setStroke(getItemOutlineStroke(row, column));
/*     */       }
/* 316 */       Line2D line = new Line2D.Double();
/* 317 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 318 */         line.setLine(lowVal, x1, highVal, x1);
/* 319 */         g2.draw(line);
/* 320 */         line.setLine(lowVal, x1 - 5.0D, lowVal, x1 + 5.0D);
/* 321 */         g2.draw(line);
/* 322 */         line.setLine(highVal, x1 - 5.0D, highVal, x1 + 5.0D);
/* 323 */         g2.draw(line);
/*     */       }
/*     */       else {
/* 326 */         line.setLine(x1, lowVal, x1, highVal);
/* 327 */         g2.draw(line);
/* 328 */         line.setLine(x1 - 5.0D, highVal, x1 + 5.0D, highVal);
/* 329 */         g2.draw(line);
/* 330 */         line.setLine(x1 - 5.0D, lowVal, x1 + 5.0D, lowVal);
/* 331 */         g2.draw(line);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 336 */     Shape hotspot = null;
/* 337 */     if ((pass == 1) && (getItemShapeVisible(row, column))) {
/* 338 */       Shape shape = getItemShape(row, column);
/* 339 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 340 */         shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/*     */       }
/* 342 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 343 */         shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*     */       }
/* 345 */       hotspot = shape;
/*     */       
/* 347 */       if (getItemShapeFilled(row, column)) {
/* 348 */         if (getUseFillPaint()) {
/* 349 */           g2.setPaint(getItemFillPaint(row, column));
/*     */         }
/*     */         else {
/* 352 */           g2.setPaint(getItemPaint(row, column));
/*     */         }
/* 354 */         g2.fill(shape);
/*     */       }
/* 356 */       if (getDrawOutlines()) {
/* 357 */         if (getUseOutlinePaint()) {
/* 358 */           g2.setPaint(getItemOutlinePaint(row, column));
/*     */         }
/*     */         else {
/* 361 */           g2.setPaint(getItemPaint(row, column));
/*     */         }
/* 363 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 364 */         g2.draw(shape);
/*     */       }
/*     */       
/* 367 */       if (isItemLabelVisible(row, column)) {
/* 368 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 369 */           drawItemLabel(g2, orientation, dataset, row, column, y1, x1, meanValue.doubleValue() < 0.0D);
/*     */ 
/*     */         }
/* 372 */         else if (orientation == PlotOrientation.VERTICAL) {
/* 373 */           drawItemLabel(g2, orientation, dataset, row, column, x1, y1, meanValue.doubleValue() < 0.0D);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 379 */     if ((pass == 0) && (getItemLineVisible(row, column)) && 
/* 380 */       (column != 0))
/*     */     {
/* 382 */       Number previousValue = statDataset.getValue(row, column - 1);
/* 383 */       if (previousValue != null)
/*     */       {
/*     */ 
/* 386 */         double previous = previousValue.doubleValue();
/*     */         double x0;
/* 388 */         double x0; if (getUseSeriesOffset()) {
/* 389 */           x0 = domainAxis.getCategorySeriesMiddle(column - 1, dataset.getColumnCount(), visibleRow, visibleRowCount, getItemMargin(), dataArea, plot.getDomainAxisEdge());
/*     */ 
/*     */ 
/*     */         }
/*     */         else
/*     */         {
/*     */ 
/* 396 */           x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */         }
/*     */         
/*     */ 
/* 400 */         double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/*     */         
/*     */ 
/* 403 */         Line2D line = null;
/* 404 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 405 */           line = new Line2D.Double(y0, x0, y1, x1);
/*     */         }
/* 407 */         else if (orientation == PlotOrientation.VERTICAL) {
/* 408 */           line = new Line2D.Double(x0, y0, x1, y1);
/*     */         }
/* 410 */         g2.setPaint(getItemPaint(row, column));
/* 411 */         g2.setStroke(getItemStroke(row, column));
/* 412 */         g2.draw(line);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 417 */     if (pass == 1)
/*     */     {
/* 419 */       EntityCollection entities = state.getEntityCollection();
/* 420 */       if (entities != null) {
/* 421 */         addEntity(entities, hotspot, dataset, row, column, x1, y1);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 435 */     if (obj == this) {
/* 436 */       return true;
/*     */     }
/* 438 */     if (!(obj instanceof StatisticalLineAndShapeRenderer)) {
/* 439 */       return false;
/*     */     }
/* 441 */     StatisticalLineAndShapeRenderer that = (StatisticalLineAndShapeRenderer)obj;
/*     */     
/* 443 */     if (!PaintUtilities.equal(this.errorIndicatorPaint, that.errorIndicatorPaint))
/*     */     {
/* 445 */       return false;
/*     */     }
/* 447 */     if (!ObjectUtilities.equal(this.errorIndicatorStroke, that.errorIndicatorStroke))
/*     */     {
/* 449 */       return false;
/*     */     }
/* 451 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 460 */     int hash = super.hashCode();
/* 461 */     hash = HashUtilities.hashCode(hash, this.errorIndicatorPaint);
/* 462 */     return hash;
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
/* 473 */     stream.defaultWriteObject();
/* 474 */     SerialUtilities.writePaint(this.errorIndicatorPaint, stream);
/* 475 */     SerialUtilities.writeStroke(this.errorIndicatorStroke, stream);
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
/* 488 */     stream.defaultReadObject();
/* 489 */     this.errorIndicatorPaint = SerialUtilities.readPaint(stream);
/* 490 */     this.errorIndicatorStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/StatisticalLineAndShapeRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */