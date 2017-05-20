/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.PathIterator;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.LegendItem;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.labels.XYSeriesLabelGenerator;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class SamplingXYLineRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private transient Shape legendLine;
/*     */   
/*     */   public SamplingXYLineRenderer()
/*     */   {
/*  82 */     this.legendLine = new Line2D.Double(-7.0D, 0.0D, 7.0D, 0.0D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getLegendLine()
/*     */   {
/*  93 */     return this.legendLine;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLegendLine(Shape line)
/*     */   {
/* 105 */     if (line == null) {
/* 106 */       throw new IllegalArgumentException("Null 'line' argument.");
/*     */     }
/* 108 */     this.legendLine = line;
/* 109 */     fireChangeEvent();
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
/* 120 */     return 1;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static class State
/*     */     extends XYItemRendererState
/*     */   {
/*     */     GeneralPath seriesPath;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     GeneralPath intervalPath;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 143 */     double dX = 1.0D;
/*     */     
/*     */ 
/*     */     double lastX;
/*     */     
/*     */ 
/* 149 */     double openY = 0.0D;
/*     */     
/*     */ 
/* 152 */     double highY = 0.0D;
/*     */     
/*     */ 
/* 155 */     double lowY = 0.0D;
/*     */     
/*     */ 
/* 158 */     double closeY = 0.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     boolean lastPointGood;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public State(PlotRenderingInfo info)
/*     */     {
/* 172 */       super();
/*     */     }
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     public void startSeriesPass(XYDataset dataset, int series, int firstItem, int lastItem, int pass, int passCount)
/*     */     {
/* 188 */       this.seriesPath.reset();
/* 189 */       this.lastPointGood = false;
/* 190 */       super.startSeriesPass(dataset, series, firstItem, lastItem, pass, passCount);
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
/*     */   public XYItemRendererState initialise(Graphics2D g2, Rectangle2D dataArea, XYPlot plot, XYDataset data, PlotRenderingInfo info)
/*     */   {
/* 216 */     double dpi = 72.0D;
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 221 */     State state = new State(info);
/* 222 */     state.seriesPath = new GeneralPath();
/* 223 */     state.intervalPath = new GeneralPath();
/* 224 */     state.dX = (72.0D / dpi);
/* 225 */     return state;
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
/* 260 */     if (!getItemVisible(series, item)) {
/* 261 */       return;
/*     */     }
/* 263 */     RectangleEdge xAxisLocation = plot.getDomainAxisEdge();
/* 264 */     RectangleEdge yAxisLocation = plot.getRangeAxisEdge();
/*     */     
/*     */ 
/* 267 */     double x1 = dataset.getXValue(series, item);
/* 268 */     double y1 = dataset.getYValue(series, item);
/* 269 */     double transX1 = domainAxis.valueToJava2D(x1, dataArea, xAxisLocation);
/* 270 */     double transY1 = rangeAxis.valueToJava2D(y1, dataArea, yAxisLocation);
/*     */     
/* 272 */     State s = (State)state;
/*     */     
/* 274 */     if ((!Double.isNaN(transX1)) && (!Double.isNaN(transY1))) {
/* 275 */       float x = (float)transX1;
/* 276 */       float y = (float)transY1;
/* 277 */       PlotOrientation orientation = plot.getOrientation();
/* 278 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 279 */         x = (float)transY1;
/* 280 */         y = (float)transX1;
/*     */       }
/* 282 */       if (s.lastPointGood) {
/* 283 */         if (Math.abs(x - s.lastX) > s.dX) {
/* 284 */           s.seriesPath.lineTo(x, y);
/* 285 */           if (s.lowY < s.highY) {
/* 286 */             s.intervalPath.moveTo((float)s.lastX, (float)s.lowY);
/* 287 */             s.intervalPath.lineTo((float)s.lastX, (float)s.highY);
/*     */           }
/* 289 */           s.lastX = x;
/* 290 */           s.openY = y;
/* 291 */           s.highY = y;
/* 292 */           s.lowY = y;
/* 293 */           s.closeY = y;
/*     */         }
/*     */         else {
/* 296 */           s.highY = Math.max(s.highY, y);
/* 297 */           s.lowY = Math.min(s.lowY, y);
/* 298 */           s.closeY = y;
/*     */         }
/*     */       }
/*     */       else {
/* 302 */         s.seriesPath.moveTo(x, y);
/* 303 */         s.lastX = x;
/* 304 */         s.openY = y;
/* 305 */         s.highY = y;
/* 306 */         s.lowY = y;
/* 307 */         s.closeY = y;
/*     */       }
/* 309 */       s.lastPointGood = true;
/*     */     }
/*     */     else {
/* 312 */       s.lastPointGood = false;
/*     */     }
/*     */     
/* 315 */     if (item == s.getLastItemIndex())
/*     */     {
/* 317 */       PathIterator pi = s.seriesPath.getPathIterator(null);
/* 318 */       int count = 0;
/* 319 */       while (!pi.isDone()) {
/* 320 */         count++;
/* 321 */         pi.next();
/*     */       }
/* 323 */       g2.setStroke(getItemStroke(series, item));
/* 324 */       g2.setPaint(getItemPaint(series, item));
/* 325 */       g2.draw(s.seriesPath);
/* 326 */       g2.draw(s.intervalPath);
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
/*     */   public LegendItem getLegendItem(int datasetIndex, int series)
/*     */   {
/* 340 */     XYPlot plot = getPlot();
/* 341 */     if (plot == null) {
/* 342 */       return null;
/*     */     }
/*     */     
/* 345 */     LegendItem result = null;
/* 346 */     XYDataset dataset = plot.getDataset(datasetIndex);
/* 347 */     if ((dataset != null) && 
/* 348 */       (getItemVisible(series, 0))) {
/* 349 */       String label = getLegendItemLabelGenerator().generateLabel(dataset, series);
/*     */       
/* 351 */       result = new LegendItem(label);
/* 352 */       result.setLabelFont(lookupLegendTextFont(series));
/* 353 */       Paint labelPaint = lookupLegendTextPaint(series);
/* 354 */       if (labelPaint != null) {
/* 355 */         result.setLabelPaint(labelPaint);
/*     */       }
/* 357 */       result.setSeriesKey(dataset.getSeriesKey(series));
/* 358 */       result.setSeriesIndex(series);
/* 359 */       result.setDataset(dataset);
/* 360 */       result.setDatasetIndex(datasetIndex);
/*     */     }
/*     */     
/* 363 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Object clone()
/*     */     throws CloneNotSupportedException
/*     */   {
/* 375 */     SamplingXYLineRenderer clone = (SamplingXYLineRenderer)super.clone();
/* 376 */     if (this.legendLine != null) {
/* 377 */       clone.legendLine = ShapeUtilities.clone(this.legendLine);
/*     */     }
/* 379 */     return clone;
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
/* 390 */     if (obj == this) {
/* 391 */       return true;
/*     */     }
/* 393 */     if (!(obj instanceof SamplingXYLineRenderer)) {
/* 394 */       return false;
/*     */     }
/* 396 */     if (!super.equals(obj)) {
/* 397 */       return false;
/*     */     }
/* 399 */     SamplingXYLineRenderer that = (SamplingXYLineRenderer)obj;
/* 400 */     if (!ShapeUtilities.equal(this.legendLine, that.legendLine)) {
/* 401 */       return false;
/*     */     }
/* 403 */     return true;
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
/* 416 */     stream.defaultReadObject();
/* 417 */     this.legendLine = SerialUtilities.readShape(stream);
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
/* 428 */     stream.defaultWriteObject();
/* 429 */     SerialUtilities.writeShape(this.legendLine, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/SamplingXYLineRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */