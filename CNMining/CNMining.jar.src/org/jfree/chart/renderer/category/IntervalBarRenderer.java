/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import org.jfree.chart.axis.CategoryAxis;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.CategoryItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.category.IntervalCategoryDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class IntervalBarRenderer
/*     */   extends BarRenderer
/*     */ {
/*     */   private static final long serialVersionUID = -5068857361615528725L;
/*     */   
/*     */   public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass)
/*     */   {
/* 127 */     if ((dataset instanceof IntervalCategoryDataset)) {
/* 128 */       IntervalCategoryDataset d = (IntervalCategoryDataset)dataset;
/* 129 */       drawInterval(g2, state, dataArea, plot, domainAxis, rangeAxis, d, row, column);
/*     */     }
/*     */     else
/*     */     {
/* 133 */       super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
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
/*     */   protected void drawInterval(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, IntervalCategoryDataset dataset, int row, int column)
/*     */   {
/* 162 */     int visibleRow = state.getVisibleSeriesIndex(row);
/* 163 */     if (visibleRow < 0) {
/* 164 */       return;
/*     */     }
/* 166 */     int seriesCount = state.getVisibleSeriesCount() >= 0 ? state.getVisibleSeriesCount() : getRowCount();
/*     */     
/*     */ 
/* 169 */     int categoryCount = getColumnCount();
/*     */     
/* 171 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 173 */     double rectX = 0.0D;
/* 174 */     double rectY = 0.0D;
/*     */     
/* 176 */     RectangleEdge domainAxisLocation = plot.getDomainAxisEdge();
/* 177 */     RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();
/*     */     
/*     */ 
/* 180 */     Number value0 = dataset.getEndValue(row, column);
/* 181 */     if (value0 == null) {
/* 182 */       return;
/*     */     }
/* 184 */     double java2dValue0 = rangeAxis.valueToJava2D(value0.doubleValue(), dataArea, rangeAxisLocation);
/*     */     
/*     */ 
/*     */ 
/* 188 */     Number value1 = dataset.getStartValue(row, column);
/* 189 */     if (value1 == null) {
/* 190 */       return;
/*     */     }
/* 192 */     double java2dValue1 = rangeAxis.valueToJava2D(value1.doubleValue(), dataArea, rangeAxisLocation);
/*     */     
/*     */ 
/* 195 */     if (java2dValue1 < java2dValue0) {
/* 196 */       double temp = java2dValue1;
/* 197 */       java2dValue1 = java2dValue0;
/* 198 */       java2dValue0 = temp;
/* 199 */       Number tempNum = value1;
/* 200 */       value1 = value0;
/* 201 */       value0 = tempNum;
/*     */     }
/*     */     
/*     */ 
/* 205 */     double rectWidth = state.getBarWidth();
/*     */     
/*     */ 
/* 208 */     double rectHeight = Math.abs(java2dValue1 - java2dValue0);
/*     */     
/* 210 */     RectangleEdge barBase = RectangleEdge.LEFT;
/* 211 */     if (orientation == PlotOrientation.HORIZONTAL)
/*     */     {
/* 213 */       rectY = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, domainAxisLocation);
/*     */       
/* 215 */       if (seriesCount > 1) {
/* 216 */         double seriesGap = dataArea.getHeight() * getItemMargin() / (categoryCount * (seriesCount - 1));
/*     */         
/* 218 */         rectY += visibleRow * (state.getBarWidth() + seriesGap);
/*     */       }
/*     */       else {
/* 221 */         rectY += visibleRow * state.getBarWidth();
/*     */       }
/*     */       
/* 224 */       rectX = java2dValue0;
/*     */       
/* 226 */       rectHeight = state.getBarWidth();
/* 227 */       rectWidth = Math.abs(java2dValue1 - java2dValue0);
/* 228 */       barBase = RectangleEdge.LEFT;
/*     */     }
/* 230 */     else if (orientation == PlotOrientation.VERTICAL)
/*     */     {
/* 232 */       rectX = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, domainAxisLocation);
/*     */       
/*     */ 
/* 235 */       if (seriesCount > 1) {
/* 236 */         double seriesGap = dataArea.getWidth() * getItemMargin() / (categoryCount * (seriesCount - 1));
/*     */         
/* 238 */         rectX += visibleRow * (state.getBarWidth() + seriesGap);
/*     */       }
/*     */       else {
/* 241 */         rectX += visibleRow * state.getBarWidth();
/*     */       }
/*     */       
/* 244 */       rectY = java2dValue0;
/* 245 */       barBase = RectangleEdge.BOTTOM;
/*     */     }
/* 247 */     Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth, rectHeight);
/*     */     
/* 249 */     BarPainter painter = getBarPainter();
/* 250 */     if (getShadowsVisible()) {
/* 251 */       painter.paintBarShadow(g2, this, row, column, bar, barBase, false);
/*     */     }
/* 253 */     getBarPainter().paintBar(g2, this, row, column, bar, barBase);
/*     */     
/* 255 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 257 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 258 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, false);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 263 */     EntityCollection entities = state.getEntityCollection();
/* 264 */     if (entities != null) {
/* 265 */       addItemEntity(entities, dataset, row, column, bar);
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
/* 278 */     if (obj == this) {
/* 279 */       return true;
/*     */     }
/* 281 */     if (!(obj instanceof IntervalBarRenderer)) {
/* 282 */       return false;
/*     */     }
/*     */     
/* 285 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/IntervalBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */