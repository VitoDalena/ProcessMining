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
/*     */ import org.jfree.chart.plot.CategoryPlot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.data.KeyToGroupMap;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.category.CategoryDataset;
/*     */ import org.jfree.data.general.DatasetUtilities;
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
/*     */ public class GroupedStackedBarRenderer
/*     */   extends StackedBarRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2725921399005922939L;
/*     */   private KeyToGroupMap seriesToGroupMap;
/*     */   
/*     */   public GroupedStackedBarRenderer()
/*     */   {
/*  92 */     this.seriesToGroupMap = new KeyToGroupMap();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setSeriesToGroupMap(KeyToGroupMap map)
/*     */   {
/* 102 */     if (map == null) {
/* 103 */       throw new IllegalArgumentException("Null 'map' argument.");
/*     */     }
/* 105 */     this.seriesToGroupMap = map;
/* 106 */     fireChangeEvent();
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
/* 119 */     if (dataset == null) {
/* 120 */       return null;
/*     */     }
/* 122 */     Range r = DatasetUtilities.findStackedRangeBounds(dataset, this.seriesToGroupMap);
/*     */     
/* 124 */     return r;
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
/*     */   protected void calculateBarWidth(CategoryPlot plot, Rectangle2D dataArea, int rendererIndex, CategoryItemRendererState state)
/*     */   {
/* 143 */     CategoryAxis xAxis = plot.getDomainAxisForDataset(rendererIndex);
/* 144 */     CategoryDataset data = plot.getDataset(rendererIndex);
/* 145 */     if (data != null) {
/* 146 */       PlotOrientation orientation = plot.getOrientation();
/* 147 */       double space = 0.0D;
/* 148 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 149 */         space = dataArea.getHeight();
/*     */       }
/* 151 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 152 */         space = dataArea.getWidth();
/*     */       }
/* 154 */       double maxWidth = space * getMaximumBarWidth();
/* 155 */       int groups = this.seriesToGroupMap.getGroupCount();
/* 156 */       int categories = data.getColumnCount();
/* 157 */       int columns = groups * categories;
/* 158 */       double categoryMargin = 0.0D;
/* 159 */       double itemMargin = 0.0D;
/* 160 */       if (categories > 1) {
/* 161 */         categoryMargin = xAxis.getCategoryMargin();
/*     */       }
/* 163 */       if (groups > 1) {
/* 164 */         itemMargin = getItemMargin();
/*     */       }
/*     */       
/* 167 */       double used = space * (1.0D - xAxis.getLowerMargin() - xAxis.getUpperMargin() - categoryMargin - itemMargin);
/*     */       
/*     */ 
/* 170 */       if (columns > 0) {
/* 171 */         state.setBarWidth(Math.min(used / columns, maxWidth));
/*     */       }
/*     */       else {
/* 174 */         state.setBarWidth(Math.min(used, maxWidth));
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
/*     */   protected double calculateBarW0(CategoryPlot plot, PlotOrientation orientation, Rectangle2D dataArea, CategoryAxis domainAxis, CategoryItemRendererState state, int row, int column)
/*     */   {
/* 203 */     double space = 0.0D;
/* 204 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 205 */       space = dataArea.getHeight();
/*     */     }
/*     */     else {
/* 208 */       space = dataArea.getWidth();
/*     */     }
/* 210 */     double barW0 = domainAxis.getCategoryStart(column, getColumnCount(), dataArea, plot.getDomainAxisEdge());
/*     */     
/* 212 */     int groupCount = this.seriesToGroupMap.getGroupCount();
/* 213 */     int groupIndex = this.seriesToGroupMap.getGroupIndex(this.seriesToGroupMap.getGroup(plot.getDataset(plot.getIndexOf(this)).getRowKey(row)));
/*     */     
/*     */ 
/* 216 */     int categoryCount = getColumnCount();
/* 217 */     if (groupCount > 1) {
/* 218 */       double groupGap = space * getItemMargin() / (categoryCount * (groupCount - 1));
/*     */       
/* 220 */       double groupW = calculateSeriesWidth(space, domainAxis, categoryCount, groupCount);
/*     */       
/* 222 */       barW0 = barW0 + groupIndex * (groupW + groupGap) + groupW / 2.0D - state.getBarWidth() / 2.0D;
/*     */     }
/*     */     else
/*     */     {
/* 226 */       barW0 = domainAxis.getCategoryMiddle(column, getColumnCount(), dataArea, plot.getDomainAxisEdge()) - state.getBarWidth() / 2.0D;
/*     */     }
/*     */     
/*     */ 
/* 230 */     return barW0;
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
/* 259 */     Number dataValue = dataset.getValue(row, column);
/* 260 */     if (dataValue == null) {
/* 261 */       return;
/*     */     }
/*     */     
/* 264 */     double value = dataValue.doubleValue();
/* 265 */     Comparable group = this.seriesToGroupMap.getGroup(dataset.getRowKey(row));
/*     */     
/* 267 */     PlotOrientation orientation = plot.getOrientation();
/* 268 */     double barW0 = calculateBarW0(plot, orientation, dataArea, domainAxis, state, row, column);
/*     */     
/*     */ 
/* 271 */     double positiveBase = 0.0D;
/* 272 */     double negativeBase = 0.0D;
/*     */     
/* 274 */     for (int i = 0; i < row; i++) {
/* 275 */       if (group.equals(this.seriesToGroupMap.getGroup(dataset.getRowKey(i))))
/*     */       {
/* 277 */         Number v = dataset.getValue(i, column);
/* 278 */         if (v != null) {
/* 279 */           double d = v.doubleValue();
/* 280 */           if (d > 0.0D) {
/* 281 */             positiveBase += d;
/*     */           }
/*     */           else {
/* 284 */             negativeBase += d;
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 292 */     boolean positive = value > 0.0D;
/* 293 */     boolean inverted = rangeAxis.isInverted();
/*     */     RectangleEdge barBase;
/* 295 */     RectangleEdge barBase; if (orientation == PlotOrientation.HORIZONTAL) { RectangleEdge barBase;
/* 296 */       if (((positive) && (inverted)) || ((!positive) && (!inverted))) {
/* 297 */         barBase = RectangleEdge.RIGHT;
/*     */       }
/*     */       else {
/* 300 */         barBase = RectangleEdge.LEFT;
/*     */       }
/*     */     } else {
/*     */       RectangleEdge barBase;
/* 304 */       if (((positive) && (!inverted)) || ((!positive) && (inverted))) {
/* 305 */         barBase = RectangleEdge.BOTTOM;
/*     */       }
/*     */       else {
/* 308 */         barBase = RectangleEdge.TOP;
/*     */       }
/*     */     }
/* 311 */     RectangleEdge location = plot.getRangeAxisEdge();
/* 312 */     double translatedValue; double translatedBase; double translatedValue; if (value > 0.0D) {
/* 313 */       double translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, location);
/*     */       
/* 315 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, location);
/*     */     }
/*     */     else
/*     */     {
/* 319 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, location);
/*     */       
/* 321 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, location);
/*     */     }
/*     */     
/* 324 */     double barL0 = Math.min(translatedBase, translatedValue);
/* 325 */     double barLength = Math.max(Math.abs(translatedValue - translatedBase), getMinimumBarLength());
/*     */     
/*     */ 
/* 328 */     Rectangle2D bar = null;
/* 329 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 330 */       bar = new Rectangle2D.Double(barL0, barW0, barLength, state.getBarWidth());
/*     */     }
/*     */     else
/*     */     {
/* 334 */       bar = new Rectangle2D.Double(barW0, barL0, state.getBarWidth(), barLength);
/*     */     }
/*     */     
/* 337 */     getBarPainter().paintBar(g2, this, row, column, bar, barBase);
/*     */     
/* 339 */     CategoryItemLabelGenerator generator = getItemLabelGenerator(row, column);
/*     */     
/* 341 */     if ((generator != null) && (isItemLabelVisible(row, column))) {
/* 342 */       drawItemLabel(g2, dataset, row, column, plot, generator, bar, value < 0.0D);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 347 */     if (state.getInfo() != null) {
/* 348 */       EntityCollection entities = state.getEntityCollection();
/* 349 */       if (entities != null) {
/* 350 */         addItemEntity(entities, dataset, row, column, bar);
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
/* 364 */     if (obj == this) {
/* 365 */       return true;
/*     */     }
/* 367 */     if (!(obj instanceof GroupedStackedBarRenderer)) {
/* 368 */       return false;
/*     */     }
/* 370 */     GroupedStackedBarRenderer that = (GroupedStackedBarRenderer)obj;
/* 371 */     if (!this.seriesToGroupMap.equals(that.seriesToGroupMap)) {
/* 372 */       return false;
/*     */     }
/* 374 */     return super.equals(obj);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/GroupedStackedBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */