/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import java.util.List;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.statistics.MultiValueCategoryDataset;
/*     */ import org.jfree.util.BooleanList;
/*     */ import org.jfree.util.BooleanUtilities;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class ScatterRenderer
/*     */   extends AbstractCategoryItemRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private BooleanList seriesShapesFilled;
/*     */   private boolean baseShapesFilled;
/*     */   private boolean useFillPaint;
/*     */   private boolean drawOutlines;
/*     */   private boolean useOutlinePaint;
/*     */   private boolean useSeriesOffset;
/*     */   private double itemMargin;
/*     */   
/*     */   public ScatterRenderer()
/*     */   {
/* 132 */     this.seriesShapesFilled = new BooleanList();
/* 133 */     this.baseShapesFilled = true;
/* 134 */     this.useFillPaint = false;
/* 135 */     this.drawOutlines = false;
/* 136 */     this.useOutlinePaint = false;
/* 137 */     this.useSeriesOffset = true;
/* 138 */     this.itemMargin = 0.2D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getUseSeriesOffset()
/*     */   {
/* 150 */     return this.useSeriesOffset;
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
/*     */   public void setUseSeriesOffset(boolean offset)
/*     */   {
/* 163 */     this.useSeriesOffset = offset;
/* 164 */     fireChangeEvent();
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
/*     */   public double getItemMargin()
/*     */   {
/* 179 */     return this.itemMargin;
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
/*     */   public void setItemMargin(double margin)
/*     */   {
/* 193 */     if ((margin < 0.0D) || (margin >= 1.0D)) {
/* 194 */       throw new IllegalArgumentException("Requires 0.0 <= margin < 1.0.");
/*     */     }
/* 196 */     this.itemMargin = margin;
/* 197 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawOutlines()
/*     */   {
/* 209 */     return this.drawOutlines;
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
/*     */   public void setDrawOutlines(boolean flag)
/*     */   {
/* 225 */     this.drawOutlines = flag;
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
/*     */   public boolean getUseOutlinePaint()
/*     */   {
/* 238 */     return this.useOutlinePaint;
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
/*     */   public void setUseOutlinePaint(boolean use)
/*     */   {
/* 251 */     this.useOutlinePaint = use;
/* 252 */     fireChangeEvent();
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
/*     */   public boolean getItemShapeFilled(int series, int item)
/*     */   {
/* 268 */     return getSeriesShapesFilled(series);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getSeriesShapesFilled(int series)
/*     */   {
/* 279 */     Boolean flag = this.seriesShapesFilled.getBoolean(series);
/* 280 */     if (flag != null) {
/* 281 */       return flag.booleanValue();
/*     */     }
/*     */     
/* 284 */     return this.baseShapesFilled;
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
/*     */   public void setSeriesShapesFilled(int series, Boolean filled)
/*     */   {
/* 297 */     this.seriesShapesFilled.setBoolean(series, filled);
/* 298 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesShapesFilled(int series, boolean filled)
/*     */   {
/* 309 */     this.seriesShapesFilled.setBoolean(series, BooleanUtilities.valueOf(filled));
/*     */     
/* 311 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getBaseShapesFilled()
/*     */   {
/* 320 */     return this.baseShapesFilled;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBaseShapesFilled(boolean flag)
/*     */   {
/* 330 */     this.baseShapesFilled = flag;
/* 331 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getUseFillPaint()
/*     */   {
/* 342 */     return this.useFillPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setUseFillPaint(boolean flag)
/*     */   {
/* 353 */     this.useFillPaint = flag;
/* 354 */     fireChangeEvent();
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 377 */     if (!getItemVisible(row, column)) {
/* 378 */       return;
/*     */     }
/* 380 */     int visibleRow = state.getVisibleSeriesIndex(row);
/* 381 */     if (visibleRow < 0) {
/* 382 */       return;
/*     */     }
/* 384 */     int visibleRowCount = state.getVisibleSeriesCount();
/*     */     
/* 386 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 388 */     MultiValueCategoryDataset d = (MultiValueCategoryDataset)dataset;
/* 389 */     List values = d.getValues(row, column);
/* 390 */     if (values == null) {
/* 391 */       return;
/*     */     }
/* 393 */     int valueCount = values.size();
/* 394 */     for (int i = 0; i < valueCount; i++) {
/*     */       double x1;
/*     */       double x1;
/* 397 */       if (this.useSeriesOffset) {
/* 398 */         x1 = domainAxis.getCategorySeriesMiddle(column, dataset.getColumnCount(), visibleRow, visibleRowCount, this.itemMargin, dataArea, plot.getDomainAxisEdge());
/*     */ 
/*     */       }
/*     */       else
/*     */       {
/* 403 */         x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */       }
/*     */       
/* 406 */       Number n = (Number)values.get(i);
/* 407 */       double value = n.doubleValue();
/* 408 */       double y1 = rangeAxis.valueToJava2D(value, dataArea, plot.getRangeAxisEdge());
/*     */       
/*     */ 
/* 411 */       Shape shape = getItemShape(row, column);
/* 412 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 413 */         shape = ShapeUtilities.createTranslatedShape(shape, y1, x1);
/*     */       }
/* 415 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 416 */         shape = ShapeUtilities.createTranslatedShape(shape, x1, y1);
/*     */       }
/* 418 */       if (getItemShapeFilled(row, column)) {
/* 419 */         if (this.useFillPaint) {
/* 420 */           g2.setPaint(getItemFillPaint(row, column));
/*     */         }
/*     */         else {
/* 423 */           g2.setPaint(getItemPaint(row, column));
/*     */         }
/* 425 */         g2.fill(shape);
/*     */       }
/* 427 */       if (this.drawOutlines) {
/* 428 */         if (this.useOutlinePaint) {
/* 429 */           g2.setPaint(getItemOutlinePaint(row, column));
/*     */         }
/*     */         else {
/* 432 */           g2.setPaint(getItemPaint(row, column));
/*     */         }
/* 434 */         g2.setStroke(getItemOutlineStroke(row, column));
/* 435 */         g2.draw(shape);
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
/*     */   public LegendItem getLegendItem(int datasetIndex, int series)
/*     */   {
/* 451 */     CategoryPlot cp = getPlot();
/* 452 */     if (cp == null) {
/* 453 */       return null;
/*     */     }
/*     */     
/* 456 */     if ((isSeriesVisible(series)) && (isSeriesVisibleInLegend(series))) {
/* 457 */       CategoryDataset dataset = cp.getDataset(datasetIndex);
/* 458 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*     */       
/* 460 */       String description = label;
/* 461 */       String toolTipText = null;
/* 462 */       if (getLegendItemToolTipGenerator() != null) {
/* 463 */         toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */       }
/*     */       
/* 466 */       String urlText = null;
/* 467 */       if (getLegendItemURLGenerator() != null) {
/* 468 */         urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */       }
/*     */       
/* 471 */       Shape shape = lookupLegendShape(series);
/* 472 */       Paint paint = lookupSeriesPaint(series);
/* 473 */       Paint fillPaint = this.useFillPaint ? getItemFillPaint(series, 0) : paint;
/*     */       
/* 475 */       boolean shapeOutlineVisible = this.drawOutlines;
/* 476 */       Paint outlinePaint = this.useOutlinePaint ? getItemOutlinePaint(series, 0) : paint;
/*     */       
/* 478 */       Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/* 479 */       LegendItem result = new LegendItem(label, description, toolTipText, urlText, true, shape, getItemShapeFilled(series, 0), fillPaint, shapeOutlineVisible, outlinePaint, outlineStroke, false, new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D), getItemStroke(series, 0), getItemPaint(series, 0));
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 484 */       result.setLabelFont(lookupLegendTextFont(series));
/* 485 */       Paint labelPaint = lookupLegendTextPaint(series);
/* 486 */       if (labelPaint != null) {
/* 487 */         result.setLabelPaint(labelPaint);
/*     */       }
/* 489 */       result.setDataset(dataset);
/* 490 */       result.setDatasetIndex(datasetIndex);
/* 491 */       result.setSeriesKey(dataset.getRowKey(series));
/* 492 */       result.setSeriesIndex(series);
/* 493 */       return result;
/*     */     }
/* 495 */     return null;
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
/* 506 */     if (obj == this) {
/* 507 */       return true;
/*     */     }
/* 509 */     if (!(obj instanceof ScatterRenderer)) {
/* 510 */       return false;
/*     */     }
/* 512 */     ScatterRenderer that = (ScatterRenderer)obj;
/* 513 */     if (!ObjectUtilities.equal(this.seriesShapesFilled, that.seriesShapesFilled))
/*     */     {
/* 515 */       return false;
/*     */     }
/* 517 */     if (this.baseShapesFilled != that.baseShapesFilled) {
/* 518 */       return false;
/*     */     }
/* 520 */     if (this.useFillPaint != that.useFillPaint) {
/* 521 */       return false;
/*     */     }
/* 523 */     if (this.drawOutlines != that.drawOutlines) {
/* 524 */       return false;
/*     */     }
/* 526 */     if (this.useOutlinePaint != that.useOutlinePaint) {
/* 527 */       return false;
/*     */     }
/* 529 */     if (this.useSeriesOffset != that.useSeriesOffset) {
/* 530 */       return false;
/*     */     }
/* 532 */     if (this.itemMargin != that.itemMargin) {
/* 533 */       return false;
/*     */     }
/* 535 */     return super.equals(obj);
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
/* 546 */     ScatterRenderer clone = (ScatterRenderer)super.clone();
/* 547 */     clone.seriesShapesFilled = ((BooleanList)this.seriesShapesFilled.clone());
/*     */     
/* 549 */     return clone;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 559 */     stream.defaultWriteObject();
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
/* 572 */     stream.defaultReadObject();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/ScatterRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */