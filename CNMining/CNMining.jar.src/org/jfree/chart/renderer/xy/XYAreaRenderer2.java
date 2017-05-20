/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Polygon;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.entity.XYItemEntity;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.labels.XYToolTipGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.urls.XYURLGenerator;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYAreaRenderer2
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, PublicCloneable
/*     */ {
/*     */   private static final long serialVersionUID = -7378069681579984133L;
/*     */   private boolean showOutline;
/*     */   private transient Shape legendArea;
/*     */   
/*     */   public XYAreaRenderer2()
/*     */   {
/* 138 */     this(null, null);
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
/*     */   public XYAreaRenderer2(XYToolTipGenerator labelGenerator, XYURLGenerator urlGenerator)
/*     */   {
/* 151 */     this.showOutline = false;
/* 152 */     setBaseToolTipGenerator(labelGenerator);
/* 153 */     setURLGenerator(urlGenerator);
/* 154 */     GeneralPath area = new GeneralPath();
/* 155 */     area.moveTo(0.0F, -4.0F);
/* 156 */     area.lineTo(3.0F, -2.0F);
/* 157 */     area.lineTo(4.0F, 4.0F);
/* 158 */     area.lineTo(-4.0F, 4.0F);
/* 159 */     area.lineTo(-3.0F, -2.0F);
/* 160 */     area.closePath();
/* 161 */     this.legendArea = area;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isOutline()
/*     */   {
/* 173 */     return this.showOutline;
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
/*     */   public void setOutline(boolean show)
/*     */   {
/* 186 */     this.showOutline = show;
/* 187 */     fireChangeEvent();
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
/*     */   public boolean getPlotLines()
/*     */   {
/* 200 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getLegendArea()
/*     */   {
/* 211 */     return this.legendArea;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendArea(Shape area)
/*     */   {
/* 223 */     if (area == null) {
/* 224 */       throw new IllegalArgumentException("Null 'area' argument.");
/*     */     }
/* 226 */     this.legendArea = area;
/* 227 */     fireChangeEvent();
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
/* 240 */     LegendItem result = null;
/* 241 */     XYPlot xyplot = getPlot();
/* 242 */     if (xyplot != null) {
/* 243 */       XYDataset dataset = xyplot.getDataset(datasetIndex);
/* 244 */       if (dataset != null) {
/* 245 */         XYSeriesLabelGenerator lg = getLegendItemLabelGenerator();
/* 246 */         String label = lg.generateLabel(dataset, series);
/* 247 */         String description = label;
/* 248 */         String toolTipText = null;
/* 249 */         if (getLegendItemToolTipGenerator() != null) {
/* 250 */           toolTipText = getLegendItemToolTipGenerator().generateLabel(dataset, series);
/*     */         }
/*     */         
/* 253 */         String urlText = null;
/* 254 */         if (getLegendItemURLGenerator() != null) {
/* 255 */           urlText = getLegendItemURLGenerator().generateLabel(dataset, series);
/*     */         }
/*     */         
/* 258 */         Paint paint = lookupSeriesPaint(series);
/* 259 */         result = new LegendItem(label, description, toolTipText, urlText, this.legendArea, paint);
/*     */         
/* 261 */         result.setLabelFont(lookupLegendTextFont(series));
/* 262 */         Paint labelPaint = lookupLegendTextPaint(series);
/* 263 */         if (labelPaint != null) {
/* 264 */           result.setLabelPaint(labelPaint);
/*     */         }
/* 266 */         result.setDataset(dataset);
/* 267 */         result.setDatasetIndex(datasetIndex);
/* 268 */         result.setSeriesKey(dataset.getSeriesKey(series));
/* 269 */         result.setSeriesIndex(series);
/*     */       }
/*     */     }
/* 272 */     return result;
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 306 */     if (!getItemVisible(series, item)) {
/* 307 */       return;
/*     */     }
/*     */     
/* 310 */     double x1 = dataset.getXValue(series, item);
/* 311 */     double y1 = dataset.getYValue(series, item);
/* 312 */     if (Double.isNaN(y1)) {
/* 313 */       y1 = 0.0D;
/*     */     }
/*     */     
/* 316 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 318 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 323 */     double x0 = dataset.getXValue(series, Math.max(item - 1, 0));
/* 324 */     double y0 = dataset.getYValue(series, Math.max(item - 1, 0));
/* 325 */     if (Double.isNaN(y0)) {
/* 326 */       y0 = 0.0D;
/*     */     }
/* 328 */     double transX0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 330 */     double transY0 = rangeAxis.valueToJava2D(y0, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 333 */     int itemCount = dataset.getItemCount(series);
/* 334 */     double x2 = dataset.getXValue(series, Math.min(item + 1, itemCount - 1));
/*     */     
/* 336 */     double y2 = dataset.getYValue(series, Math.min(item + 1, itemCount - 1));
/*     */     
/* 338 */     if (Double.isNaN(y2)) {
/* 339 */       y2 = 0.0D;
/*     */     }
/* 341 */     double transX2 = domainAxis.valueToJava2D(x2, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 343 */     double transY2 = rangeAxis.valueToJava2D(y2, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 346 */     double transZero = rangeAxis.valueToJava2D(0.0D, dataArea, plot.getRangeAxisEdge());
/*     */     
/* 348 */     Polygon hotspot = null;
/* 349 */     if (plot.getOrientation() == PlotOrientation.HORIZONTAL) {
/* 350 */       hotspot = new Polygon();
/* 351 */       hotspot.addPoint((int)transZero, (int)((transX0 + transX1) / 2.0D));
/*     */       
/* 353 */       hotspot.addPoint((int)((transY0 + transY1) / 2.0D), (int)((transX0 + transX1) / 2.0D));
/*     */       
/* 355 */       hotspot.addPoint((int)transY1, (int)transX1);
/* 356 */       hotspot.addPoint((int)((transY1 + transY2) / 2.0D), (int)((transX1 + transX2) / 2.0D));
/*     */       
/* 358 */       hotspot.addPoint((int)transZero, (int)((transX1 + transX2) / 2.0D));
/*     */     }
/*     */     else
/*     */     {
/* 362 */       hotspot = new Polygon();
/* 363 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)transZero);
/*     */       
/* 365 */       hotspot.addPoint((int)((transX0 + transX1) / 2.0D), (int)((transY0 + transY1) / 2.0D));
/*     */       
/* 367 */       hotspot.addPoint((int)transX1, (int)transY1);
/* 368 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)((transY1 + transY2) / 2.0D));
/*     */       
/* 370 */       hotspot.addPoint((int)((transX1 + transX2) / 2.0D), (int)transZero);
/*     */     }
/*     */     
/*     */ 
/* 374 */     PlotOrientation orientation = plot.getOrientation();
/* 375 */     Paint paint = getItemPaint(series, item);
/* 376 */     Stroke stroke = getItemStroke(series, item);
/* 377 */     g2.setPaint(paint);
/* 378 */     g2.setStroke(stroke);
/*     */     
/*     */ 
/*     */ 
/* 382 */     g2.fill(hotspot);
/*     */     
/*     */ 
/* 385 */     if (isOutline()) {
/* 386 */       g2.setStroke(lookupSeriesOutlineStroke(series));
/* 387 */       g2.setPaint(lookupSeriesOutlinePaint(series));
/* 388 */       g2.draw(hotspot);
/*     */     }
/* 390 */     int domainAxisIndex = plot.getDomainAxisIndex(domainAxis);
/* 391 */     int rangeAxisIndex = plot.getRangeAxisIndex(rangeAxis);
/* 392 */     updateCrosshairValues(crosshairState, x1, y1, domainAxisIndex, rangeAxisIndex, transX1, transY1, orientation);
/*     */     
/*     */ 
/*     */ 
/* 396 */     if (state.getInfo() != null) {
/* 397 */       EntityCollection entities = state.getEntityCollection();
/* 398 */       if ((entities != null) && (hotspot != null)) {
/* 399 */         String tip = null;
/* 400 */         XYToolTipGenerator generator = getToolTipGenerator(series, item);
/*     */         
/* 402 */         if (generator != null) {
/* 403 */           tip = generator.generateToolTip(dataset, series, item);
/*     */         }
/* 405 */         String url = null;
/* 406 */         if (getURLGenerator() != null) {
/* 407 */           url = getURLGenerator().generateURL(dataset, series, item);
/*     */         }
/* 409 */         XYItemEntity entity = new XYItemEntity(hotspot, dataset, series, item, tip, url);
/*     */         
/* 411 */         entities.add(entity);
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
/* 425 */     if (obj == this) {
/* 426 */       return true;
/*     */     }
/* 428 */     if (!(obj instanceof XYAreaRenderer2)) {
/* 429 */       return false;
/*     */     }
/* 431 */     XYAreaRenderer2 that = (XYAreaRenderer2)obj;
/* 432 */     if (this.showOutline != that.showOutline) {
/* 433 */       return false;
/*     */     }
/* 435 */     if (!ShapeUtilities.equal(this.legendArea, that.legendArea)) {
/* 436 */       return false;
/*     */     }
/* 438 */     return super.equals(obj);
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
/* 449 */     XYAreaRenderer2 clone = (XYAreaRenderer2)super.clone();
/* 450 */     clone.legendArea = ShapeUtilities.clone(this.legendArea);
/* 451 */     return clone;
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
/* 464 */     stream.defaultReadObject();
/* 465 */     this.legendArea = SerialUtilities.readShape(stream);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void writeObject(ObjectOutputStream stream)
/*     */     throws IOException
/*     */   {
/* 476 */     stream.defaultWriteObject();
/* 477 */     SerialUtilities.writeShape(this.legendArea, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYAreaRenderer2.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */