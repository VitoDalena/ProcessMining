/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
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
/*     */ public class ClusteredXYBarRenderer
/*     */   extends XYBarRenderer
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 5864462149177133147L;
/*     */   private boolean centerBarAtStartValue;
/*     */   
/*     */   public ClusteredXYBarRenderer()
/*     */   {
/* 113 */     this(0.0D, false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ClusteredXYBarRenderer(double margin, boolean centerBarAtStartValue)
/*     */   {
/* 125 */     super(margin);
/* 126 */     this.centerBarAtStartValue = centerBarAtStartValue;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 137 */     return 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range findDomainBounds(XYDataset dataset)
/*     */   {
/* 148 */     if (dataset == null) {
/* 149 */       return null;
/*     */     }
/*     */     
/* 152 */     if (this.centerBarAtStartValue) {
/* 153 */       return findDomainBoundsWithOffset((IntervalXYDataset)dataset);
/*     */     }
/*     */     
/* 156 */     return super.findDomainBounds(dataset);
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
/*     */   protected Range findDomainBoundsWithOffset(IntervalXYDataset dataset)
/*     */   {
/* 170 */     if (dataset == null) {
/* 171 */       throw new IllegalArgumentException("Null 'dataset' argument.");
/*     */     }
/* 173 */     double minimum = Double.POSITIVE_INFINITY;
/* 174 */     double maximum = Double.NEGATIVE_INFINITY;
/* 175 */     int seriesCount = dataset.getSeriesCount();
/*     */     
/*     */ 
/* 178 */     for (int series = 0; series < seriesCount; series++) {
/* 179 */       int itemCount = dataset.getItemCount(series);
/* 180 */       for (int item = 0; item < itemCount; item++) {
/* 181 */         double lvalue = dataset.getStartXValue(series, item);
/* 182 */         double uvalue = dataset.getEndXValue(series, item);
/* 183 */         double offset = (uvalue - lvalue) / 2.0D;
/* 184 */         lvalue -= offset;
/* 185 */         uvalue -= offset;
/* 186 */         minimum = Math.min(minimum, lvalue);
/* 187 */         maximum = Math.max(maximum, uvalue);
/*     */       }
/*     */     }
/*     */     
/* 191 */     if (minimum > maximum) {
/* 192 */       return null;
/*     */     }
/*     */     
/* 195 */     return new Range(minimum, maximum);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 233 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/*     */     double y1;
/*     */     double y0;
/*     */     double y1;
/* 237 */     if (getUseYInterval()) {
/* 238 */       double y0 = intervalDataset.getStartYValue(series, item);
/* 239 */       y1 = intervalDataset.getEndYValue(series, item);
/*     */     }
/*     */     else {
/* 242 */       y0 = getBase();
/* 243 */       y1 = intervalDataset.getYValue(series, item);
/*     */     }
/* 245 */     if ((Double.isNaN(y0)) || (Double.isNaN(y1))) {
/* 246 */       return;
/*     */     }
/*     */     
/* 249 */     double yy0 = rangeAxis.valueToJava2D(y0, dataArea, plot.getRangeAxisEdge());
/*     */     
/* 251 */     double yy1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 254 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 255 */     double x0 = intervalDataset.getStartXValue(series, item);
/* 256 */     double xx0 = domainAxis.valueToJava2D(x0, dataArea, xAxisLocation);
/*     */     
/* 258 */     double x1 = intervalDataset.getEndXValue(series, item);
/* 259 */     double xx1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/*     */     
/* 261 */     double intervalW = xx1 - xx0;
/* 262 */     double baseX = xx0;
/* 263 */     if (this.centerBarAtStartValue) {
/* 264 */       baseX -= intervalW / 2.0D;
/*     */     }
/* 266 */     double m = getMargin();
/* 267 */     if (m > 0.0D) {
/* 268 */       double cut = intervalW * getMargin();
/* 269 */       intervalW -= cut;
/* 270 */       baseX += cut / 2.0D;
/*     */     }
/*     */     
/* 273 */     double intervalH = Math.abs(yy0 - yy1);
/*     */     
/* 275 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/* 277 */     int numSeries = dataset.getSeriesCount();
/* 278 */     double seriesBarWidth = intervalW / numSeries;
/*     */     
/* 280 */     Rectangle2D bar = null;
/* 281 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 282 */       double barY0 = baseX + seriesBarWidth * series;
/* 283 */       double barY1 = barY0 + seriesBarWidth;
/* 284 */       double rx = Math.min(yy0, yy1);
/* 285 */       double rw = intervalH;
/* 286 */       double ry = Math.min(barY0, barY1);
/* 287 */       double rh = Math.abs(barY1 - barY0);
/* 288 */       bar = new Rectangle2D.Double(rx, ry, rw, rh);
/*     */     }
/* 290 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 291 */       double barX0 = baseX + seriesBarWidth * series;
/* 292 */       double barX1 = barX0 + seriesBarWidth;
/* 293 */       double rx = Math.min(barX0, barX1);
/* 294 */       double rw = Math.abs(barX1 - barX0);
/* 295 */       double ry = Math.min(yy0, yy1);
/* 296 */       double rh = intervalH;
/* 297 */       bar = new Rectangle2D.Double(rx, ry, rw, rh);
/*     */     }
/* 299 */     boolean positive = y1 > 0.0D;
/* 300 */     boolean inverted = rangeAxis.isInverted();
/*     */     RectangleEdge barBase;
/* 302 */     RectangleEdge barBase; if (orientation == PlotOrientation.HORIZONTAL) { RectangleEdge barBase;
/* 303 */       if (((positive) && (inverted)) || ((!positive) && (!inverted))) {
/* 304 */         barBase = RectangleEdge.RIGHT;
/*     */       }
/*     */       else {
/* 307 */         barBase = RectangleEdge.LEFT;
/*     */       }
/*     */     } else {
/*     */       RectangleEdge barBase;
/* 311 */       if (((positive) && (!inverted)) || ((!positive) && (inverted))) {
/* 312 */         barBase = RectangleEdge.BOTTOM;
/*     */       }
/*     */       else {
/* 315 */         barBase = RectangleEdge.TOP;
/*     */       }
/*     */     }
/* 318 */     if ((pass == 0) && (getShadowsVisible())) {
/* 319 */       getBarPainter().paintBarShadow(g2, this, series, item, bar, barBase, !getUseYInterval());
/*     */     }
/*     */     
/* 322 */     if (pass == 1) {
/* 323 */       getBarPainter().paintBar(g2, this, series, item, bar, barBase);
/*     */       
/* 325 */       if (isItemLabelVisible(series, item)) {
/* 326 */         XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
/*     */         
/* 328 */         drawItemLabel(g2, dataset, series, item, plot, generator, bar, y1 < 0.0D);
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 333 */       if (info != null) {
/* 334 */         EntityCollection entities = info.getOwner().getEntityCollection();
/*     */         
/* 336 */         if (entities != null) {
/* 337 */           addEntity(entities, bar, dataset, series, item, bar.getCenterX(), bar.getCenterY());
/*     */         }
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 356 */     if (obj == this) {
/* 357 */       return true;
/*     */     }
/* 359 */     if (!(obj instanceof ClusteredXYBarRenderer)) {
/* 360 */       return false;
/*     */     }
/* 362 */     ClusteredXYBarRenderer that = (ClusteredXYBarRenderer)obj;
/* 363 */     if (this.centerBarAtStartValue != that.centerBarAtStartValue) {
/* 364 */       return false;
/*     */     }
/* 366 */     return super.equals(obj);
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
/* 377 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/ClusteredXYBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */