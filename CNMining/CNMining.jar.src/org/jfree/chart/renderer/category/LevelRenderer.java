/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class LevelRenderer
/*     */   extends AbstractCategoryItemRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8204856624355025117L;
/*     */   public static final double DEFAULT_ITEM_MARGIN = 0.2D;
/*     */   private double itemMargin;
/*     */   private double maxItemWidth;
/*     */   
/*     */   public LevelRenderer()
/*     */   {
/* 104 */     this.itemMargin = 0.2D;
/* 105 */     this.maxItemWidth = 1.0D;
/*     */     
/* 107 */     setBaseLegendShape(new Rectangle2D.Float(-5.0F, -1.0F, 10.0F, 2.0F));
/*     */     
/*     */ 
/* 110 */     setBaseOutlinePaint(new Color(0, 0, 0, 0));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getItemMargin()
/*     */   {
/* 121 */     return this.itemMargin;
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
/*     */   public void setItemMargin(double percent)
/*     */   {
/* 135 */     this.itemMargin = percent;
/* 136 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaximumItemWidth()
/*     */   {
/* 148 */     return getMaxItemWidth();
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
/*     */   public void setMaximumItemWidth(double percent)
/*     */   {
/* 161 */     setMaxItemWidth(percent);
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
/*     */   public CategoryItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, CategoryPlot plot, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 183 */     CategoryItemRendererState state = super.initialise(g2, dataArea, plot, rendererIndex, info);
/*     */     
/* 185 */     calculateItemWidth(plot, dataArea, rendererIndex, state);
/* 186 */     return state;
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
/*     */   protected void calculateItemWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state)
/*     */   {
/* 202 */     CategoryAxis domainAxis = getDomainAxis(plot, rendererIndex);
/* 203 */     CategoryDataset dataset = plot.getDataset(rendererIndex);
/* 204 */     if (dataset != null) {
/* 205 */       int columns = dataset.getColumnCount();
/* 206 */       int rows = state.getVisibleSeriesCount() >= 0 ? state.getVisibleSeriesCount() : dataset.getRowCount();
/*     */       
/* 208 */       double space = 0.0D;
/* 209 */       PlotOrientation orientation = plot.getOrientation();
/* 210 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 211 */         space = dataArea.getHeight();
/*     */       }
/* 213 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 214 */         space = dataArea.getWidth();
/*     */       }
/* 216 */       double maxWidth = space * getMaximumItemWidth();
/* 217 */       double categoryMargin = 0.0D;
/* 218 */       double currentItemMargin = 0.0D;
/* 219 */       if (columns > 1) {
/* 220 */         categoryMargin = domainAxis.getCategoryMargin();
/*     */       }
/* 222 */       if (rows > 1) {
/* 223 */         currentItemMargin = getItemMargin();
/*     */       }
/* 225 */       double used = space * (1.0D - domainAxis.getLowerMargin() - domainAxis.getUpperMargin() - categoryMargin - currentItemMargin);
/*     */       
/*     */ 
/* 228 */       if (rows * columns > 0) {
/* 229 */         state.setBarWidth(Math.min(used / (rows * columns), maxWidth));
/*     */       }
/*     */       else {
/* 232 */         state.setBarWidth(Math.min(used, maxWidth));
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
/*     */   protected double calculateBarW0(CategoryPlot plot, PlotOrientation orientation, Rectangle2D dataArea, CategoryAxis domainAxis, CategoryItemRendererState state, int row, int column)
/*     */   {
/* 260 */     double space = 0.0D;
/* 261 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 262 */       space = dataArea.getHeight();
/*     */     }
/*     */     else {
/* 265 */       space = dataArea.getWidth();
/*     */     }
/* 267 */     double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/* 269 */     int seriesCount = state.getVisibleSeriesCount();
/* 270 */     if (seriesCount < 0) {
/* 271 */       seriesCount = getRowCount();
/*     */     }
/* 273 */     int categoryCount = getColumnCount();
/* 274 */     if (seriesCount > 1) {
/* 275 */       double seriesGap = space * getItemMargin() / (categoryCount * (seriesCount - 1));
/*     */       
/* 277 */       double seriesW = calculateSeriesWidth(space, domainAxis, categoryCount, seriesCount);
/*     */       
/* 279 */       barW0 = barW0 + row * (seriesW + seriesGap) + seriesW / 2.0D - state.getBarWidth() / 2.0D;
/*     */     }
/*     */     else
/*     */     {
/* 283 */       barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     }
/*     */     
/*     */ 
/* 287 */     return barW0;
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 311 */     int visibleRow = state.getVisibleSeriesIndex(row);
/* 312 */     if (visibleRow < 0) {
/* 313 */       return;
/*     */     }
/*     */     
/*     */ 
/* 317 */     Number dataValue = dataset.getValue(row, column);
/* 318 */     if (dataValue == null) {
/* 319 */       return;
/*     */     }
/*     */     
/* 322 */     double value = dataValue.doubleValue();
/*     */     
/* 324 */     PlotOrientation orientation = plot.getOrientation();
/* 325 */     double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, state, visibleRow, column);
/*     */     
/* 327 */     RectangleEdge edge = plot.getRangeAxisEdge();
/* 328 */     double barL = rangeAxis.valueToJava2D(value, dataArea, edge);
/*     */     
/*     */ 
/* 331 */     Line2D line = null;
/* 332 */     double x = 0.0D;
/* 333 */     double y = 0.0D;
/* 334 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 335 */       x = barL;
/* 336 */       y = barW0 + state.getBarWidth() / 2.0D;
/* 337 */       line = new Line2D.Double(barL, barW0, barL, barW0 + state.getBarWidth());
/*     */     }
/*     */     else
/*     */     {
/* 341 */       x = barW0 + state.getBarWidth() / 2.0D;
/* 342 */       y = barL;
/* 343 */       line = new Line2D.Double(barW0, barL, barW0 + state.getBarWidth(), barL);
/*     */     }
/*     */     
/* 346 */     Stroke itemStroke = getItemStroke(row, column);
/* 347 */     Paint itemPaint = getItemPaint(row, column);
/* 348 */     g2.setStroke(itemStroke);
/* 349 */     g2.setPaint(itemPaint);
/* 350 */     g2.draw(line);
/*     */     
/* 352 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 354 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 355 */       drawItemLabel(g2, orientation, dataset, row, column, x, y, value < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 360 */     int datasetIndex = plot.indexOf(dataset);
/* 361 */     updateCrosshairValues(state.getCrosshairState(), dataset.getRowKey(row), dataset.getColumnKey(column), value, datasetIndex, barW0, barL, orientation);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 366 */     EntityCollection entities = state.getEntityCollection();
/* 367 */     if (entities != null) {
/* 368 */       addItemEntity(entities, dataset, row, column, line.getBounds());
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
/*     */   protected double calculateSeriesWidth(double space, CategoryAxis axis, int categories, int series)
/*     */   {
/* 385 */     double factor = 1.0D - getItemMargin() - axis.getLowerMargin() - axis.getUpperMargin();
/*     */     
/* 387 */     if (categories > 1) {
/* 388 */       factor -= axis.getCategoryMargin();
/*     */     }
/* 390 */     return space * factor / (categories * series);
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
/* 410 */     return axis.getCategorySeriesMiddle(columnKey, rowKey, dataset, this.itemMargin, area, edge);
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
/* 422 */     if (obj == this) {
/* 423 */       return true;
/*     */     }
/* 425 */     if (!(obj instanceof LevelRenderer)) {
/* 426 */       return false;
/*     */     }
/* 428 */     LevelRenderer that = (LevelRenderer)obj;
/* 429 */     if (this.itemMargin != that.itemMargin) {
/* 430 */       return false;
/*     */     }
/* 432 */     if (this.maxItemWidth != that.maxItemWidth) {
/* 433 */       return false;
/*     */     }
/* 435 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 444 */     int hash = super.hashCode();
/* 445 */     hash = HashUtilities.hashCode(hash, this.itemMargin);
/* 446 */     hash = HashUtilities.hashCode(hash, this.maxItemWidth);
/* 447 */     return hash;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public double getMaxItemWidth()
/*     */   {
/* 459 */     return this.maxItemWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   /**
/*     */    * @deprecated
/*     */    */
/*     */   public void setMaxItemWidth(double percent)
/*     */   {
/* 472 */     this.maxItemWidth = percent;
/* 473 */     fireChangeEvent();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/LevelRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */