/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.labels.ItemLabelAnchor;
/*     */ import org.jfree.chart.labels.ItemLabelPosition;
/*     */ import org.jfree.chart.labels.XYItemLabelGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.TableXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.TextAnchor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StackedXYBarRenderer
/*     */   extends XYBarRenderer
/*     */ {
/*     */   private static final long serialVersionUID = -7049101055533436444L;
/*     */   private boolean renderAsPercentages;
/*     */   
/*     */   public StackedXYBarRenderer()
/*     */   {
/* 106 */     this(0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public StackedXYBarRenderer(double margin)
/*     */   {
/* 115 */     super(margin);
/* 116 */     this.renderAsPercentages = false;
/*     */     
/*     */ 
/*     */ 
/* 120 */     ItemLabelPosition p = new ItemLabelPosition(ItemLabelAnchor.CENTER, TextAnchor.CENTER);
/*     */     
/* 122 */     setBasePositiveItemLabelPosition(p);
/* 123 */     setBaseNegativeItemLabelPosition(p);
/* 124 */     setPositiveItemLabelPositionFallback(null);
/* 125 */     setNegativeItemLabelPositionFallback(null);
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
/*     */   public boolean getRenderAsPercentages()
/*     */   {
/* 140 */     return this.renderAsPercentages;
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
/*     */   public void setRenderAsPercentages(boolean asPercentages)
/*     */   {
/* 155 */     this.renderAsPercentages = asPercentages;
/* 156 */     fireChangeEvent();
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
/* 168 */     return 3;
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*     */   {
/* 190 */     return new XYBarRenderer.XYBarRendererState(this, info);
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 203 */     if (dataset != null) {
/* 204 */       if (this.renderAsPercentages) {
/* 205 */         return new Range(0.0D, 1.0D);
/*     */       }
/*     */       
/* 208 */       return DatasetUtilities.findStackedRangeBounds((TableXYDataset)dataset);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 213 */     return null;
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 248 */     if ((!(dataset instanceof IntervalXYDataset)) || (!(dataset instanceof TableXYDataset)))
/*     */     {
/* 250 */       String message = "dataset (type " + dataset.getClass().getName() + ") has wrong type:";
/*     */       
/* 252 */       boolean and = false;
/* 253 */       if (!IntervalXYDataset.class.isAssignableFrom(dataset.getClass())) {
/* 254 */         message = message + " it is no IntervalXYDataset";
/* 255 */         and = true;
/*     */       }
/* 257 */       if (!TableXYDataset.class.isAssignableFrom(dataset.getClass())) {
/* 258 */         if (and) {
/* 259 */           message = message + " and";
/*     */         }
/* 261 */         message = message + " it is no TableXYDataset";
/*     */       }
/*     */       
/* 264 */       throw new IllegalArgumentException(message);
/*     */     }
/*     */     
/* 267 */     IntervalXYDataset intervalDataset = (IntervalXYDataset)dataset;
/* 268 */     double value = intervalDataset.getYValue(series, item);
/* 269 */     if (Double.isNaN(value)) {
/* 270 */       return;
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 280 */     double total = 0.0D;
/* 281 */     if (this.renderAsPercentages) {
/* 282 */       total = DatasetUtilities.calculateStackTotal((TableXYDataset)dataset, item);
/*     */       
/* 284 */       value /= total;
/*     */     }
/*     */     
/* 287 */     double positiveBase = 0.0D;
/* 288 */     double negativeBase = 0.0D;
/*     */     
/* 290 */     for (int i = 0; i < series; i++) {
/* 291 */       double v = dataset.getYValue(i, item);
/* 292 */       if (!Double.isNaN(v)) {
/* 293 */         if (this.renderAsPercentages) {
/* 294 */           v /= total;
/*     */         }
/* 296 */         if (v > 0.0D) {
/* 297 */           positiveBase += v;
/*     */         }
/*     */         else {
/* 300 */           negativeBase += v;
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 307 */     RectangleEdge edgeR = plot.getRangeAxisEdge();
/* 308 */     double translatedValue; double translatedBase; double translatedValue; if (value > 0.0D) {
/* 309 */       double translatedBase = rangeAxis.valueToJava2D(positiveBase, dataArea, edgeR);
/*     */       
/* 311 */       translatedValue = rangeAxis.valueToJava2D(positiveBase + value, dataArea, edgeR);
/*     */     }
/*     */     else
/*     */     {
/* 315 */       translatedBase = rangeAxis.valueToJava2D(negativeBase, dataArea, edgeR);
/*     */       
/* 317 */       translatedValue = rangeAxis.valueToJava2D(negativeBase + value, dataArea, edgeR);
/*     */     }
/*     */     
/*     */ 
/* 321 */     RectangleEdge edgeD = plot.getDomainAxisEdge();
/* 322 */     double startX = intervalDataset.getStartXValue(series, item);
/* 323 */     if (Double.isNaN(startX)) {
/* 324 */       return;
/*     */     }
/* 326 */     double translatedStartX = domainAxis.valueToJava2D(startX, dataArea, edgeD);
/*     */     
/*     */ 
/* 329 */     double endX = intervalDataset.getEndXValue(series, item);
/* 330 */     if (Double.isNaN(endX)) {
/* 331 */       return;
/*     */     }
/* 333 */     double translatedEndX = domainAxis.valueToJava2D(endX, dataArea, edgeD);
/*     */     
/* 335 */     double translatedWidth = Math.max(1.0D, Math.abs(translatedEndX - translatedStartX));
/*     */     
/* 337 */     double translatedHeight = Math.abs(translatedValue - translatedBase);
/* 338 */     if (getMargin() > 0.0D) {
/* 339 */       double cut = translatedWidth * getMargin();
/* 340 */       translatedWidth -= cut;
/* 341 */       translatedStartX += cut / 2.0D;
/*     */     }
/*     */     
/* 344 */     Rectangle2D bar = null;
/* 345 */     PlotOrientation orientation = plot.getOrientation();
/* 346 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 347 */       bar = new Rectangle2D.Double(Math.min(translatedBase, translatedValue), translatedEndX, translatedHeight, translatedWidth);
/*     */ 
/*     */ 
/*     */     }
/* 351 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 352 */       bar = new Rectangle2D.Double(translatedStartX, Math.min(translatedBase, translatedValue), translatedWidth, translatedHeight);
/*     */     }
/*     */     
/*     */ 
/* 356 */     boolean positive = value > 0.0D;
/* 357 */     boolean inverted = rangeAxis.isInverted();
/*     */     RectangleEdge barBase;
/* 359 */     RectangleEdge barBase; if (orientation == PlotOrientation.HORIZONTAL) { RectangleEdge barBase;
/* 360 */       if (((positive) && (inverted)) || ((!positive) && (!inverted))) {
/* 361 */         barBase = RectangleEdge.RIGHT;
/*     */       }
/*     */       else {
/* 364 */         barBase = RectangleEdge.LEFT;
/*     */       }
/*     */     } else {
/*     */       RectangleEdge barBase;
/* 368 */       if (((positive) && (!inverted)) || ((!positive) && (inverted))) {
/* 369 */         barBase = RectangleEdge.BOTTOM;
/*     */       }
/*     */       else {
/* 372 */         barBase = RectangleEdge.TOP;
/*     */       }
/*     */     }
/*     */     
/* 376 */     if (pass == 0) {
/* 377 */       if (getShadowsVisible()) {
/* 378 */         getBarPainter().paintBarShadow(g2, this, series, item, bar, barBase, false);
/*     */       }
/*     */       
/*     */     }
/* 382 */     else if (pass == 1) {
/* 383 */       getBarPainter().paintBar(g2, this, series, item, bar, barBase);
/*     */       
/*     */ 
/* 386 */       if (info != null) {
/* 387 */         EntityCollection entities = info.getOwner().getEntityCollection();
/*     */         
/* 389 */         if (entities != null) {
/* 390 */           addEntity(entities, bar, dataset, series, item, bar.getCenterX(), bar.getCenterY());
/*     */         }
/*     */         
/*     */       }
/*     */     }
/* 395 */     else if (pass == 2)
/*     */     {
/*     */ 
/* 398 */       if (isItemLabelVisible(series, item)) {
/* 399 */         XYItemLabelGenerator generator = getItemLabelGenerator(series, item);
/*     */         
/* 401 */         drawItemLabel(g2, dataset, series, item, plot, generator, bar, value < 0.0D);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 416 */     if (obj == this) {
/* 417 */       return true;
/*     */     }
/* 419 */     if (!(obj instanceof StackedXYBarRenderer)) {
/* 420 */       return false;
/*     */     }
/* 422 */     StackedXYBarRenderer that = (StackedXYBarRenderer)obj;
/* 423 */     if (this.renderAsPercentages != that.renderAsPercentages) {
/* 424 */       return false;
/*     */     }
/* 426 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 435 */     int result = super.hashCode();
/* 436 */     result = result * 37 + (this.renderAsPercentages ? 1 : 0);
/* 437 */     return result;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/StackedXYBarRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */