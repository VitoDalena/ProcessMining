/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategorySeriesLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.data.category.CategoryDataset;
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
/*     */ public class CategoryStepRenderer
/*     */   extends AbstractCategoryItemRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -5121079703118261470L;
/*     */   public static final int STAGGER_WIDTH = 5;
/*     */   
/*     */   protected static class State
/*     */     extends CategoryItemRendererState
/*     */   {
/*     */     public Line2D line;
/*     */     
/*     */     public State(PlotRenderingInfo info)
/*     */     {
/* 103 */       super();
/* 104 */       this.line = new Line2D.Double();
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
/* 119 */   private boolean stagger = false;
/*     */   
/*     */ 
/*     */ 
/*     */   public CategoryStepRenderer()
/*     */   {
/* 125 */     this(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public CategoryStepRenderer(boolean stagger)
/*     */   {
/* 135 */     this.stagger = stagger;
/* 136 */     setBaseLegendShape(new Rectangle2D.Double(-4.0D, -3.0D, 8.0D, 6.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getStagger()
/*     */   {
/* 145 */     return this.stagger;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStagger(boolean shouldStagger)
/*     */   {
/* 156 */     this.stagger = shouldStagger;
/* 157 */     fireChangeEvent();
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
/* 170 */     CategoryPlot p = getPlot();
/* 171 */     if (p == null) {
/* 172 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 176 */     if ((!isSeriesVisible(series)) || (!isSeriesVisibleInLegend(series))) {
/* 177 */       return null;
/*     */     }
/*     */     
/* 180 */     CategoryDataset dataset = p.getDataset(datasetIndex);
/* 181 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*     */     
/* 183 */     String description = label;
/* 184 */     String toolTipText = null;
/* 185 */     if (getLegendItemToolTipGenerator() != null) {
/* 186 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */     }
/*     */     
/* 189 */     String urlText = null;
/* 190 */     if (getLegendItemURLGenerator() != null) {
/* 191 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */     }
/*     */     
/* 194 */     Shape shape = lookupLegendShape(series);
/* 195 */     Paint paint = lookupSeriesPaint(series);
/*     */     
/* 197 */     LegendItem item = new LegendItem(label, description, toolTipText, urlText, shape, paint);
/*     */     
/* 199 */     item.setLabelFont(lookupLegendTextFont(series));
/* 200 */     Paint labelPaint = lookupLegendTextPaint(series);
/* 201 */     if (labelPaint != null) {
/* 202 */       item.setLabelPaint(labelPaint);
/*     */     }
/* 204 */     item.setSeriesKey(dataset.getRowKey(series));
/* 205 */     item.setSeriesIndex(series);
/* 206 */     item.setDataset(dataset);
/* 207 */     item.setDatasetIndex(datasetIndex);
/* 208 */     return item;
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
/*     */   protected CategoryItemRendererState createState(PlotRenderingInfo info)
/*     */   {
/* 222 */     return new State(info);
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
/*     */   protected void drawLine(Graphics2D g2, State state, PlotOrientation orientation, double x0, double y0, double x1, double y1)
/*     */   {
/* 246 */     if (orientation == PlotOrientation.VERTICAL) {
/* 247 */       state.line.setLine(x0, y0, x1, y1);
/* 248 */       g2.draw(state.line);
/*     */     }
/* 250 */     else if (orientation == PlotOrientation.HORIZONTAL) {
/* 251 */       state.line.setLine(y0, x0, y1, x1);
/* 252 */       g2.draw(state.line);
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
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 283 */     if (!getItemVisible(row, column)) {
/* 284 */       return;
/*     */     }
/*     */     
/* 287 */     Number value = dataset.getValue(row, column);
/* 288 */     if (value == null) {
/* 289 */       return;
/*     */     }
/* 291 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/* 294 */     double x1s = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/* 296 */     double x1 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/* 298 */     double x1e = 2.0D * x1 - x1s;
/* 299 */     double y1 = rangeAxis.valueToJava2D(value.doubleValue(), dataArea, plot.getRangeAxisEdge());
/*     */     
/* 301 */     g2.setPaint(getItemPaint(row, column));
/* 302 */     g2.setStroke(getItemStroke(row, column));
/*     */     
/* 304 */     if (column != 0) {
/* 305 */       Number previousValue = dataset.getValue(row, column - 1);
/* 306 */       if (previousValue != null)
/*     */       {
/* 308 */         double previous = previousValue.doubleValue();
/* 309 */         double x0s = domainAxis.getCategoryStart(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */         
/* 311 */         double x0 = domainAxis.getCategoryMiddle(column - 1, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */         
/* 313 */         double x0e = 2.0D * x0 - x0s;
/* 314 */         double y0 = rangeAxis.valueToJava2D(previous, dataArea, plot.getRangeAxisEdge());
/*     */         
/* 316 */         if (getStagger()) {
/* 317 */           int xStagger = row * 5;
/* 318 */           if (xStagger > x1s - x0e) {
/* 319 */             xStagger = (int)(x1s - x0e);
/*     */           }
/* 321 */           x1s = x0e + xStagger;
/*     */         }
/* 323 */         drawLine(g2, (State)state, orientation, x0e, y0, x1s, y0);
/*     */         
/*     */ 
/* 326 */         drawLine(g2, (State)state, orientation, x1s, y0, x1s, y1);
/*     */       }
/*     */     }
/*     */     
/* 330 */     drawLine(g2, (State)state, orientation, x1s, y1, x1e, y1);
/*     */     
/*     */ 
/*     */ 
/* 334 */     if (isItemLabelVisible(row, column)) {
/* 335 */       drawItemLabel(g2, orientation, dataset, row, column, x1, y1, value.doubleValue() < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 340 */     EntityCollection entities = state.getEntityCollection();
/* 341 */     if (entities != null) {
/* 342 */       Rectangle2D hotspot = new Rectangle2D.Double();
/* 343 */       if (orientation == PlotOrientation.VERTICAL) {
/* 344 */         hotspot.setRect(x1s, y1, x1e - x1s, 4.0D);
/*     */       }
/*     */       else {
/* 347 */         hotspot.setRect(y1 - 2.0D, x1s, 4.0D, x1e - x1s);
/*     */       }
/* 349 */       addItemEntity(entities, dataset, row, column, hotspot);
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
/* 362 */     if (obj == this) {
/* 363 */       return true;
/*     */     }
/* 365 */     if (!(obj instanceof CategoryStepRenderer)) {
/* 366 */       return false;
/*     */     }
/* 368 */     CategoryStepRenderer that = (CategoryStepRenderer)obj;
/* 369 */     if (this.stagger != that.stagger) {
/* 370 */       return false;
/*     */     }
/* 372 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/CategoryStepRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */