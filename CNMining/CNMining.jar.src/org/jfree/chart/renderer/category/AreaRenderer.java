/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
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
/*     */ import org.jfree.chart.renderer.AreaRendererEndType;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AreaRenderer
/*     */   extends AbstractCategoryItemRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4231878281385812757L;
/*     */   private AreaRendererEndType endType;
/*     */   
/*     */   public AreaRenderer()
/*     */   {
/* 117 */     this.endType = AreaRendererEndType.TAPER;
/* 118 */     setBaseLegendShape(new Rectangle2D.Double(-4.0D, -4.0D, 8.0D, 8.0D));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AreaRendererEndType getEndType()
/*     */   {
/* 130 */     return this.endType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setEndType(AreaRendererEndType type)
/*     */   {
/* 142 */     if (type == null) {
/* 143 */       throw new IllegalArgumentException("Null 'type' argument.");
/*     */     }
/* 145 */     this.endType = type;
/* 146 */     fireChangeEvent();
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
/* 160 */     CategoryPlot cp = getPlot();
/* 161 */     if (cp == null) {
/* 162 */       return null;
/*     */     }
/*     */     
/*     */ 
/* 166 */     if ((!isSeriesVisible(series)) || (!isSeriesVisibleInLegend(series))) {
/* 167 */       return null;
/*     */     }
/*     */     
/* 170 */     CategoryDataset dataset = cp.getDataset(datasetIndex);
/* 171 */     String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*     */     
/* 173 */     String description = label;
/* 174 */     String toolTipText = null;
/* 175 */     if (getLegendItemToolTipGenerator() != null) {
/* 176 */       toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */     }
/*     */     
/* 179 */     String urlText = null;
/* 180 */     if (getLegendItemURLGenerator() != null) {
/* 181 */       urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */     }
/*     */     
/* 184 */     Shape shape = lookupLegendShape(series);
/* 185 */     Paint paint = lookupSeriesPaint(series);
/* 186 */     Paint outlinePaint = lookupSeriesOutlinePaint(series);
/* 187 */     Stroke outlineStroke = lookupSeriesOutlineStroke(series);
/*     */     
/* 189 */     LegendItem result = new LegendItem(label, description, toolTipText, urlText, shape, paint, outlineStroke, outlinePaint);
/*     */     
/* 191 */     result.setLabelFont(lookupLegendTextFont(series));
/* 192 */     Paint labelPaint = lookupLegendTextPaint(series);
/* 193 */     if (labelPaint != null) {
/* 194 */       result.setLabelPaint(labelPaint);
/*     */     }
/* 196 */     result.setDataset(dataset);
/* 197 */     result.setDatasetIndex(datasetIndex);
/* 198 */     result.setSeriesKey(dataset.getRowKey(series));
/* 199 */     result.setSeriesIndex(series);
/* 200 */     return result;
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
/* 230 */     if (!getItemVisible(row, column)) {
/* 231 */       return;
/*     */     }
/*     */     
/*     */ 
/* 235 */     Number value = dataset.getValue(row, column);
/* 236 */     if (value != null) {
/* 237 */       PlotOrientation orientation = plot.getOrientation();
/* 238 */       RectangleEdge axisEdge = plot.getDomainAxisEdge();
/* 239 */       int count = dataset.getColumnCount();
/* 240 */       float x0 = (float)domainAxis.getCategoryStart(column, count, dataArea, axisEdge);
/*     */       
/* 242 */       float x1 = (float)domainAxis.getCategoryMiddle(column, count, dataArea, axisEdge);
/*     */       
/* 244 */       float x2 = (float)domainAxis.getCategoryEnd(column, count, dataArea, axisEdge);
/*     */       
/*     */ 
/* 247 */       x0 = Math.round(x0);
/* 248 */       x1 = Math.round(x1);
/* 249 */       x2 = Math.round(x2);
/*     */       
/* 251 */       if (this.endType == AreaRendererEndType.TRUNCATE) {
/* 252 */         if (column == 0) {
/* 253 */           x0 = x1;
/*     */         }
/* 255 */         else if (column == getColumnCount() - 1) {
/* 256 */           x2 = x1;
/*     */         }
/*     */       }
/*     */       
/* 260 */       double yy1 = value.doubleValue();
/*     */       
/* 262 */       double yy0 = 0.0D;
/* 263 */       if (column > 0) {
/* 264 */         Number n0 = dataset.getValue(row, column - 1);
/* 265 */         if (n0 != null) {
/* 266 */           yy0 = (n0.doubleValue() + yy1) / 2.0D;
/*     */         }
/*     */       }
/*     */       
/* 270 */       double yy2 = 0.0D;
/* 271 */       if (column < dataset.getColumnCount() - 1) {
/* 272 */         Number n2 = dataset.getValue(row, column + 1);
/* 273 */         if (n2 != null) {
/* 274 */           yy2 = (n2.doubleValue() + yy1) / 2.0D;
/*     */         }
/*     */       }
/*     */       
/* 278 */       RectangleEdge edge = plot.getRangeAxisEdge();
/* 279 */       float y0 = (float)rangeAxis.valueToJava2D(yy0, dataArea, edge);
/* 280 */       float y1 = (float)rangeAxis.valueToJava2D(yy1, dataArea, edge);
/* 281 */       float y2 = (float)rangeAxis.valueToJava2D(yy2, dataArea, edge);
/* 282 */       float yz = (float)rangeAxis.valueToJava2D(0.0D, dataArea, edge);
/*     */       
/* 284 */       g2.setPaint(getItemPaint(row, column));
/* 285 */       g2.setStroke(getItemStroke(row, column));
/*     */       
/* 287 */       GeneralPath area = new GeneralPath();
/*     */       
/* 289 */       if (orientation == PlotOrientation.VERTICAL) {
/* 290 */         area.moveTo(x0, yz);
/* 291 */         area.lineTo(x0, y0);
/* 292 */         area.lineTo(x1, y1);
/* 293 */         area.lineTo(x2, y2);
/* 294 */         area.lineTo(x2, yz);
/*     */       }
/* 296 */       else if (orientation == PlotOrientation.HORIZONTAL) {
/* 297 */         area.moveTo(yz, x0);
/* 298 */         area.lineTo(y0, x0);
/* 299 */         area.lineTo(y1, x1);
/* 300 */         area.lineTo(y2, x2);
/* 301 */         area.lineTo(yz, x2);
/*     */       }
/* 303 */       area.closePath();
/*     */       
/* 305 */       g2.setPaint(getItemPaint(row, column));
/* 306 */       g2.fill(area);
/*     */       
/*     */ 
/* 309 */       if (isItemLabelVisible(row, column)) {
/* 310 */         drawItemLabel(g2, orientation, dataset, row, column, x1, y1, value.doubleValue() < 0.0D);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 315 */       int datasetIndex = plot.indexOf(dataset);
/* 316 */       updateCrosshairValues(state.getCrosshairState(), dataset.getRowKey(row), dataset.getColumnKey(column), yy1, datasetIndex, x1, y1, orientation);
/*     */       
/*     */ 
/*     */ 
/*     */ 
/* 321 */       EntityCollection entities = state.getEntityCollection();
/* 322 */       if (entities != null) {
/* 323 */         addItemEntity(entities, dataset, row, column, area);
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
/* 337 */     if (obj == this) {
/* 338 */       return true;
/*     */     }
/* 340 */     if (!(obj instanceof AreaRenderer)) {
/* 341 */       return false;
/*     */     }
/* 343 */     AreaRenderer that = (AreaRenderer)obj;
/* 344 */     if (!this.endType.equals(that.endType)) {
/* 345 */       return false;
/*     */     }
/* 347 */     return super.equals(obj);
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
/* 358 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/AreaRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */