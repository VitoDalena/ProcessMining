/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.DataUtilities;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StackedBarRenderer
/*     */   extends BarRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 6402943811500067531L;
/*     */   private boolean renderAsPercentages;
/*     */   
/*     */   public StackedBarRenderer()
/*     */   {
/* 144 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StackedBarRenderer(boolean renderAsPercentages)
/*     */   {
/* 155 */     this.renderAsPercentages = renderAsPercentages;
/*     */     
/*     */ 
/*     */ 
/* 159 */     ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
/*     */     
/* 161 */     setBasePositiveItemLabelPosition(p);
/* 162 */     setBaseNegativeItemLabelPosition(p);
/* 163 */     setPositiveItemLabelPositionFallback(null);
/* 164 */     setNegativeItemLabelPositionFallback(null);
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
/*     */   public boolean getRenderAsPercentages()
/*     */   {
/* 177 */     return this.renderAsPercentages;
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
/*     */   public void setRenderAsPercentages(boolean asPercentages)
/*     */   {
/* 190 */     this.renderAsPercentages = asPercentages;
/* 191 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 203 */     return 3;
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
/* 215 */     if (dataset == null) {
/* 216 */       return null;
/*     */     }
/* 218 */     if (this.renderAsPercentages) {
/* 219 */       return new Range(0.0D, 1.0D);
/*     */     }
/*     */     
/* 222 */     return DatasetUtilities.findStackedRangeBounds(dataset, getBase());
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
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state)
/*     */   {
/* 240 */     CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
/* 241 */     CategoryDataset data = plot.getDataset(rendererIndex);
/* 242 */     if (data != null) {
/* 243 */       PlotOrientation orientation = plot.getOrientation();
/* 244 */       double space = 0.0D;
/* 245 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 246 */         space = dataArea.getHeight();
/*     */       }
/* 248 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 249 */         space = dataArea.getWidth();
/*     */       }
/* 251 */       double maxWidth = space * getMaximumBarWidth();
/* 252 */       int columns = data.getColumnCount();
/* 253 */       double categoryMargin = 0.0D;
/* 254 */       if (columns > 1) {
/* 255 */         categoryMargin = xAxis.getCategoryMargin();
/*     */       }
/*     */       
/* 258 */       double used = space * (1.0D - xAxis.getLowerMargin() - xAxis.getUpperMargin() - categoryMargin);
/*     */       
/*     */ 
/* 261 */       if (columns > 0) {
/* 262 */         state.setBarWidth(Math.min(used / columns, maxWidth));
/*     */       }
/*     */       else {
/* 265 */         state.setBarWidth(Math.min(used, maxWidth));
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
/*     */ 
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 296 */     if (!isSeriesVisible(row)) {
/* 297 */       return;
/*     */     }
/*     */     
/*     */ 
/* 301 */     Number dataValue = dataset.getValue(row, column);
/* 302 */     if (dataValue == null) {
/* 303 */       return;
/*     */     }
/*     */     
/* 306 */     double value = dataValue.doubleValue();
/* 307 */     double total = 0.0D;
/* 308 */     if (this.renderAsPercentages) {
/* 309 */       total = DataUtilities.calculateColumnTotal(dataset, column, state.getVisibleSeriesArray());
/*     */       
/* 311 */       value /= total;
/*     */     }
/*     */     
/* 314 */     PlotOrientation orientation = plot.getOrientation();
/* 315 */     double barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     
/*     */ 
/*     */ 
/* 319 */     double positiveBase = getBase();
/* 320 */     double negativeBase = positiveBase;
/*     */     
/* 322 */     for (int i = 0; i < row; i++) {
/* 323 */       Number v = dataset.getValue(i, column);
/* 324 */       if ((v != null) && (isSeriesVisible(i))) {
/* 325 */         double d = v.doubleValue();
/* 326 */         if (this.renderAsPercentages) {
/* 327 */           d /= total;
/*     */         }
/* 329 */         if (d > 0.0D) {
/* 330 */           positiveBase += d;
/*     */         }
/*     */         else {
/* 333 */           negativeBase += d;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 340 */     boolean positive = value > 0.0D;
/* 341 */     boolean inverted = rangeAxis.isInverted();
/*     */     RectangleEdge barBase;
/* 343 */     RectangleEdge barBase; if (orientation == PlotOrientation.HORIZONTAL) { RectangleEdge barBase;
/* 344 */       if (((positive) && (inverted)) || ((!positive) && (!inverted))) {
/* 345 */         barBase = RectangleEdge.RIGHT;
/*     */       }
/*     */       else {
/* 348 */         barBase = RectangleEdge.LEFT;
/*     */       }
/*     */     } else {
/*     */       RectangleEdge barBase;
/* 352 */       if (((positive) && (!inverted)) || ((!positive) && (inverted))) {
/* 353 */         barBase = RectangleEdge.BOTTOM;
/*     */       }
/*     */       else {
/* 356 */         barBase = RectangleEdge.TOP;
/*     */       }
/*     */     }
/*     */     
/* 360 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 361 */     double translatedValue; double translatedBase; double translatedValue; if (positive) {
/* 362 */       double translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, location);
/*     */       
/* 364 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, location);
/*     */     }
/*     */     else
/*     */     {
/* 368 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, location);
/*     */       
/* 370 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, location);
/*     */     }
/*     */     
/* 373 */     double barL0 = Math.min(translatedBase, translatedValue);
/* 374 */     double barLength = Math.max(Math.abs(translatedValue - translatedBase), getMinimumBarLength());
/*     */     
/*     */ 
/* 377 */     Rectangle2D bar = null;
/* 378 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 379 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     }
/*     */     else
/*     */     {
/* 383 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     }
/*     */     
/* 386 */     if (pass == 0) {
/* 387 */       if (getShadowsVisible()) {
/* 388 */         boolean pegToBase = ((positive) && (positiveBase == getBase())) || ((!positive) && (negativeBase == getBase()));
/*     */         
/* 390 */         getBarPainter().paintBarShadow(g2, this, row, column, bar, barBase, pegToBase);
/*     */       }
/*     */       
/*     */     }
/* 394 */     else if (pass == 1) {
/* 395 */       getBarPainter().paintBar(g2, this, row, column, bar, barBase);
/*     */       
/*     */ 
/* 398 */       EntityCollection entities = state.getEntityCollection();
/* 399 */       if (entities != null) {
/* 400 */         addItemEntity(entities, dataset, row, column, bar);
/*     */       }
/*     */     }
/* 403 */     else if (pass == 2) {
/* 404 */       CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */       
/* 406 */       if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 407 */         drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0D);
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
/* 421 */     if (obj == this) {
/* 422 */       return true;
/*     */     }
/* 424 */     if (!(obj instanceof StackedBarRenderer)) {
/* 425 */       return false;
/*     */     }
/* 427 */     StackedBarRenderer that = (StackedBarRenderer)obj;
/* 428 */     if (this.renderAsPercentages != that.renderAsPercentages) {
/* 429 */       return false;
/*     */     }
/* 431 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/StackedBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */