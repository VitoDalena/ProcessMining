/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.IntervalXYDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.ObjectUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYErrorRenderer
/*     */   extends XYLineAndShapeRenderer
/*     */ {
/*     */   static final long serialVersionUID = 5162283570955172424L;
/*     */   private boolean drawXError;
/*     */   private boolean drawYError;
/*     */   private double capLength;
/*     */   private transient Paint errorPaint;
/*     */   private transient Stroke errorStroke;
/*     */   
/*     */   public XYErrorRenderer()
/*     */   {
/* 114 */     super(false, true);
/* 115 */     this.drawXError = true;
/* 116 */     this.drawYError = true;
/* 117 */     this.errorPaint = null;
/* 118 */     this.errorStroke = null;
/* 119 */     this.capLength = 4.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawXError()
/*     */   {
/* 131 */     return this.drawXError;
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
/*     */   public void setDrawXError(boolean draw)
/*     */   {
/* 144 */     if (this.drawXError != draw) {
/* 145 */       this.drawXError = draw;
/* 146 */       fireChangeEvent();
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
/*     */   public boolean getDrawYError()
/*     */   {
/* 159 */     return this.drawYError;
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
/*     */   public void setDrawYError(boolean draw)
/*     */   {
/* 172 */     if (this.drawYError != draw) {
/* 173 */       this.drawYError = draw;
/* 174 */       fireChangeEvent();
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
/*     */   public double getCapLength()
/*     */   {
/* 187 */     return this.capLength;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setCapLength(double length)
/*     */   {
/* 199 */     this.capLength = length;
/* 200 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getErrorPaint()
/*     */   {
/* 212 */     return this.errorPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setErrorPaint(Paint paint)
/*     */   {
/* 224 */     this.errorPaint = paint;
/* 225 */     fireChangeEvent();
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
/*     */   public Stroke getErrorStroke()
/*     */   {
/* 240 */     return this.errorStroke;
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
/*     */   public void setErrorStroke(Stroke stroke)
/*     */   {
/* 254 */     this.errorStroke = stroke;
/* 255 */     fireChangeEvent();
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
/*     */   public Range findDomainBounds(XYDataset dataset)
/*     */   {
/* 268 */     if (dataset != null) {
/* 269 */       return DatasetUtilities.findDomainBounds(dataset, true);
/*     */     }
/*     */     
/* 272 */     return null;
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 286 */     if (dataset != null) {
/* 287 */       return DatasetUtilities.findRangeBounds(dataset, true);
/*     */     }
/*     */     
/* 290 */     return null;
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 315 */     if ((pass == 0) && ((dataset instanceof IntervalXYDataset)) && (getItemVisible(series, item)))
/*     */     {
/* 317 */       IntervalXYDataset ixyd = (IntervalXYDataset)dataset;
/* 318 */       PlotOrientation orientation = plot.getOrientation();
/* 319 */       if (this.drawXError)
/*     */       {
/* 321 */         double x0 = ixyd.getStartXValue(series, item);
/* 322 */         double x1 = ixyd.getEndXValue(series, item);
/* 323 */         double y = ixyd.getYValue(series, item);
/* 324 */         RectangleEdge edge = plot.getDomainAxisEdge();
/* 325 */         double xx0 = domainAxis.valueToJava2D(x0, dataArea, edge);
/* 326 */         double xx1 = domainAxis.valueToJava2D(x1, dataArea, edge);
/* 327 */         double yy = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/*     */         
/*     */ 
/* 330 */         Line2D cap1 = null;
/* 331 */         Line2D cap2 = null;
/* 332 */         double adj = this.capLength / 2.0D;
/* 333 */         Line2D line; if (orientation == PlotOrientation.VERTICAL) {
/* 334 */           Line2D line = new Line2D.Double(xx0, yy, xx1, yy);
/* 335 */           cap1 = new Line2D.Double(xx0, yy - adj, xx0, yy + adj);
/* 336 */           cap2 = new Line2D.Double(xx1, yy - adj, xx1, yy + adj);
/*     */         }
/*     */         else {
/* 339 */           line = new Line2D.Double(yy, xx0, yy, xx1);
/* 340 */           cap1 = new Line2D.Double(yy - adj, xx0, yy + adj, xx0);
/* 341 */           cap2 = new Line2D.Double(yy - adj, xx1, yy + adj, xx1);
/*     */         }
/* 343 */         if (this.errorPaint != null) {
/* 344 */           g2.setPaint(this.errorPaint);
/*     */         }
/*     */         else {
/* 347 */           g2.setPaint(getItemPaint(series, item));
/*     */         }
/* 349 */         if (this.errorStroke != null) {
/* 350 */           g2.setStroke(this.errorStroke);
/*     */         }
/*     */         else {
/* 353 */           g2.setStroke(getItemStroke(series, item));
/*     */         }
/* 355 */         g2.draw(line);
/* 356 */         g2.draw(cap1);
/* 357 */         g2.draw(cap2);
/*     */       }
/* 359 */       if (this.drawYError)
/*     */       {
/* 361 */         double y0 = ixyd.getStartYValue(series, item);
/* 362 */         double y1 = ixyd.getEndYValue(series, item);
/* 363 */         double x = ixyd.getXValue(series, item);
/* 364 */         RectangleEdge edge = plot.getRangeAxisEdge();
/* 365 */         double yy0 = rangeAxis.valueToJava2D(y0, dataArea, edge);
/* 366 */         double yy1 = rangeAxis.valueToJava2D(y1, dataArea, edge);
/* 367 */         double xx = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/*     */         
/*     */ 
/* 370 */         Line2D cap1 = null;
/* 371 */         Line2D cap2 = null;
/* 372 */         double adj = this.capLength / 2.0D;
/* 373 */         Line2D line; if (orientation == PlotOrientation.VERTICAL) {
/* 374 */           Line2D line = new Line2D.Double(xx, yy0, xx, yy1);
/* 375 */           cap1 = new Line2D.Double(xx - adj, yy0, xx + adj, yy0);
/* 376 */           cap2 = new Line2D.Double(xx - adj, yy1, xx + adj, yy1);
/*     */         }
/*     */         else {
/* 379 */           line = new Line2D.Double(yy0, xx, yy1, xx);
/* 380 */           cap1 = new Line2D.Double(yy0, xx - adj, yy0, xx + adj);
/* 381 */           cap2 = new Line2D.Double(yy1, xx - adj, yy1, xx + adj);
/*     */         }
/* 383 */         if (this.errorPaint != null) {
/* 384 */           g2.setPaint(this.errorPaint);
/*     */         }
/*     */         else {
/* 387 */           g2.setPaint(getItemPaint(series, item));
/*     */         }
/* 389 */         if (this.errorStroke != null) {
/* 390 */           g2.setStroke(this.errorStroke);
/*     */         }
/*     */         else {
/* 393 */           g2.setStroke(getItemStroke(series, item));
/*     */         }
/* 395 */         g2.draw(line);
/* 396 */         g2.draw(cap1);
/* 397 */         g2.draw(cap2);
/*     */       }
/*     */     }
/* 400 */     super.drawItem(g2, state, dataArea, info, plot, domainAxis, rangeAxis, dataset, series, item, crosshairState, pass);
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
/* 412 */     if (obj == this) {
/* 413 */       return true;
/*     */     }
/* 415 */     if (!(obj instanceof XYErrorRenderer)) {
/* 416 */       return false;
/*     */     }
/* 418 */     XYErrorRenderer that = (XYErrorRenderer)obj;
/* 419 */     if (this.drawXError != that.drawXError) {
/* 420 */       return false;
/*     */     }
/* 422 */     if (this.drawYError != that.drawYError) {
/* 423 */       return false;
/*     */     }
/* 425 */     if (this.capLength != that.capLength) {
/* 426 */       return false;
/*     */     }
/* 428 */     if (!PaintUtilities.equal(this.errorPaint, that.errorPaint)) {
/* 429 */       return false;
/*     */     }
/* 431 */     if (!ObjectUtilities.equal(this.errorStroke, that.errorStroke)) {
/* 432 */       return false;
/*     */     }
/* 434 */     return super.equals(obj);
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
/* 447 */     stream.defaultReadObject();
/* 448 */     this.errorPaint = SerialUtilities.readPaint(stream);
/* 449 */     this.errorStroke = SerialUtilities.readStroke(stream);
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
/* 460 */     stream.defaultWriteObject();
/* 461 */     SerialUtilities.writePaint(this.errorPaint, stream);
/* 462 */     SerialUtilities.writeStroke(this.errorStroke, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYErrorRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */