/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LayeredBarRenderer
/*     */   extends BarRenderer
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8716572894780469487L;
/*     */   protected ObjectList seriesBarWidthList;
/*     */   
/*     */   public LayeredBarRenderer()
/*     */   {
/*  95 */     this.seriesBarWidthList = new ObjectList();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getSeriesBarWidth(int series)
/*     */   {
/* 107 */     double result = NaN.0D;
/* 108 */     Number n = (Number)this.seriesBarWidthList.get(series);
/* 109 */     if (n != null) {
/* 110 */       result = n.doubleValue();
/*     */     }
/* 112 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesBarWidth(int series, double width)
/*     */   {
/* 123 */     this.seriesBarWidthList.set(series, new Double(width));
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
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state)
/*     */   {
/* 143 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 144 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/* 145 */     if (dataset != null) {
/* 146 */       int columns = dataset.getColumnCount();
/* 147 */       int rows = dataset.getRowCount();
/* 148 */       double space = 0.0D;
/* 149 */       PlotOrientation orientation = plot.getOrientation();
/* 150 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 151 */         space = dataArea.getHeight();
/*     */       }
/* 153 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 154 */         space = dataArea.getWidth();
/*     */       }
/* 156 */       double maxWidth = space * getMaximumBarWidth();
/* 157 */       double categoryMargin = 0.0D;
/* 158 */       if (columns > 1) {
/* 159 */         categoryMargin = domainAxis.getCategoryMargin();
/*     */       }
/* 161 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin);
/*     */       
/* 163 */       if (rows * columns > 0) {
/* 164 */         state.setBarWidth(Math.min(used / dataset.getColumnCount(), maxWidth));
/*     */       }
/*     */       else
/*     */       {
/* 168 */         state.setBarWidth(Math.min(used, maxWidth));
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
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset data, int row, int column, int pass)
/*     */   {
/* 198 */     PlotOrientation orientation = plot.getOrientation();
/* 199 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 200 */       drawHorizontalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, data, row, column);
/*     */ 
/*     */     }
/* 203 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 204 */       drawVerticalItem(g2, state, dataArea, plot, domainAxis, rangeAxis, data, row, column);
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
/*     */   protected void drawHorizontalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column)
/*     */   {
/* 234 */     Number dataValue = dataset.getValue(row, column);
/* 235 */     if (dataValue == null) {
/* 236 */       return;
/*     */     }
/*     */     
/*     */ 
/* 240 */     double value = dataValue.doubleValue();
/* 241 */     double base = 0.0D;
/* 242 */     double lclip = getLowerClip();
/* 243 */     double uclip = getUpperClip();
/* 244 */     if (uclip <= 0.0D) {
/* 245 */       if (value >= uclip) {
/* 246 */         return;
/*     */       }
/* 248 */       base = uclip;
/* 249 */       if (value <= lclip) {
/* 250 */         value = lclip;
/*     */       }
/*     */     }
/* 253 */     else if (lclip <= 0.0D) {
/* 254 */       if (value >= uclip) {
/* 255 */         value = uclip;
/*     */ 
/*     */       }
/* 258 */       else if (value <= lclip) {
/* 259 */         value = lclip;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 264 */       if (value <= lclip) {
/* 265 */         return;
/*     */       }
/* 267 */       base = lclip;
/* 268 */       if (value >= uclip) {
/* 269 */         value = uclip;
/*     */       }
/*     */     }
/*     */     
/* 273 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 274 */     double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
/* 275 */     double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
/* 276 */     double rectX = Math.min(transX1, transX2);
/* 277 */     double rectWidth = Math.abs(transX2 - transX1);
/*     */     
/*     */ 
/* 280 */     double rectY = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     
/*     */ 
/* 283 */     int seriesCount = getRowCount();
/*     */     
/*     */ 
/* 286 */     double shift = 0.0D;
/* 287 */     double rectHeight = 0.0D;
/* 288 */     double widthFactor = 1.0D;
/* 289 */     double seriesBarWidth = getSeriesBarWidth(row);
/* 290 */     if (!Double.isNaN(seriesBarWidth)) {
/* 291 */       widthFactor = seriesBarWidth;
/*     */     }
/* 293 */     rectHeight = widthFactor * state.getBarWidth();
/* 294 */     rectY += (1.0D - widthFactor) * state.getBarWidth() / 2.0D;
/* 295 */     if (seriesCount > 1) {
/* 296 */       shift = rectHeight * 0.2D / (seriesCount - 1);
/*     */     }
/*     */     
/* 299 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY + (seriesCount - 1 - row) * shift, rectWidth, rectHeight - (seriesCount - 1 - row) * shift * 2.0D);
/*     */     
/*     */ 
/*     */ 
/* 303 */     Paint itemPaint = getItemPaint(row, column);
/* 304 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 305 */     if ((t != null) && ((itemPaint instanceof GradientPaint))) {
/* 306 */       itemPaint = t.transform((GradientPaint)itemPaint, bar);
/*     */     }
/* 308 */     g2.setPaint(itemPaint);
/* 309 */     g2.fill(bar);
/*     */     
/*     */ 
/* 312 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 314 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 315 */       Paint paint = getItemOutlinePaint(row, column);
/* 316 */       if ((stroke != null) && (paint != null)) {
/* 317 */         g2.setStroke(stroke);
/* 318 */         g2.setPaint(paint);
/* 319 */         g2.draw(bar);
/*     */       }
/*     */     }
/*     */     
/* 323 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 325 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 326 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, transX1 > transX2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 331 */     EntityCollection entities = state.getEntityCollection();
/* 332 */     if (entities != null) {
/* 333 */       addItemEntity(entities, dataset, row, column, bar);
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
/*     */   protected void drawVerticalItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column)
/*     */   {
/* 361 */     Number dataValue = dataset.getValue(row, column);
/* 362 */     if (dataValue == null) {
/* 363 */       return;
/*     */     }
/*     */     
/*     */ 
/* 367 */     double rectX = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     
/*     */ 
/* 370 */     int seriesCount = getRowCount();
/*     */     
/*     */ 
/* 373 */     double value = dataValue.doubleValue();
/* 374 */     double base = 0.0D;
/* 375 */     double lclip = getLowerClip();
/* 376 */     double uclip = getUpperClip();
/*     */     
/* 378 */     if (uclip <= 0.0D) {
/* 379 */       if (value >= uclip) {
/* 380 */         return;
/*     */       }
/* 382 */       base = uclip;
/* 383 */       if (value <= lclip) {
/* 384 */         value = lclip;
/*     */       }
/*     */     }
/* 387 */     else if (lclip <= 0.0D) {
/* 388 */       if (value >= uclip) {
/* 389 */         value = uclip;
/*     */ 
/*     */       }
/* 392 */       else if (value <= lclip) {
/* 393 */         value = lclip;
/*     */       }
/*     */     }
/*     */     else
/*     */     {
/* 398 */       if (value <= lclip) {
/* 399 */         return;
/*     */       }
/* 401 */       base = getLowerClip();
/* 402 */       if (value >= uclip) {
/* 403 */         value = uclip;
/*     */       }
/*     */     }
/*     */     
/* 407 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 408 */     double transY1 = rangeAxis.valueToJava2D(base, dataArea, edge);
/* 409 */     double transY2 = rangeAxis.valueToJava2D(value, dataArea, edge);
/* 410 */     double rectY = Math.min(transY2, transY1);
/*     */     
/* 412 */     double rectWidth = state.getBarWidth();
/* 413 */     double rectHeight = Math.abs(transY2 - transY1);
/*     */     
/*     */ 
/* 416 */     double shift = 0.0D;
/* 417 */     rectWidth = 0.0D;
/* 418 */     double widthFactor = 1.0D;
/* 419 */     double seriesBarWidth = getSeriesBarWidth(row);
/* 420 */     if (!Double.isNaN(seriesBarWidth)) {
/* 421 */       widthFactor = seriesBarWidth;
/*     */     }
/* 423 */     rectWidth = widthFactor * state.getBarWidth();
/* 424 */     rectX += (1.0D - widthFactor) * state.getBarWidth() / 2.0D;
/* 425 */     if (seriesCount > 1)
/*     */     {
/* 427 */       shift = rectWidth * 0.2D / (seriesCount - 1);
/*     */     }
/*     */     
/* 430 */     Rectangle2D bar = new Rectangle2D.Double(rectX + (seriesCount - 1 - row) * shift, rectY, rectWidth - (seriesCount - 1 - row) * shift * 2.0D, rectHeight);
/*     */     
/*     */ 
/* 433 */     Paint itemPaint = getItemPaint(row, column);
/* 434 */     GradientPaintTransformer t = getGradientPaintTransformer();
/* 435 */     if ((t != null) && ((itemPaint instanceof GradientPaint))) {
/* 436 */       itemPaint = t.transform((GradientPaint)itemPaint, bar);
/*     */     }
/* 438 */     g2.setPaint(itemPaint);
/* 439 */     g2.fill(bar);
/*     */     
/*     */ 
/* 442 */     if ((isDrawBarOutline()) && (state.getBarWidth() > 3.0D))
/*     */     {
/* 444 */       Stroke stroke = getItemOutlineStroke(row, column);
/* 445 */       Paint paint = getItemOutlinePaint(row, column);
/* 446 */       if ((stroke != null) && (paint != null)) {
/* 447 */         g2.setStroke(stroke);
/* 448 */         g2.setPaint(paint);
/* 449 */         g2.draw(bar);
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 454 */     double transX1 = rangeAxis.valueToJava2D(base, dataArea, edge);
/* 455 */     double transX2 = rangeAxis.valueToJava2D(value, dataArea, edge);
/*     */     
/* 457 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 459 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 460 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, transX1 > transX2);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 465 */     EntityCollection entities = state.getEntityCollection();
/* 466 */     if (entities != null) {
/* 467 */       addItemEntity(entities, dataset, row, column, bar);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/LayeredBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */