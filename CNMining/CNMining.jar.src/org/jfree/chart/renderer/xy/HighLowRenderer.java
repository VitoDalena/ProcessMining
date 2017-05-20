/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.OHLCDataset;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.util.PaintUtilities;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class HighLowRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -8135673815876552516L;
/*     */   private boolean drawOpenTicks;
/*     */   private boolean drawCloseTicks;
/*     */   private transient Paint openTickPaint;
/*     */   private transient Paint closeTickPaint;
/*     */   private double tickLength;
/*     */   
/*     */   public HighLowRenderer()
/*     */   {
/* 146 */     this.drawOpenTicks = true;
/* 147 */     this.drawCloseTicks = true;
/* 148 */     this.tickLength = 2.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawOpenTicks()
/*     */   {
/* 160 */     return this.drawOpenTicks;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDrawOpenTicks(boolean draw)
/*     */   {
/* 172 */     this.drawOpenTicks = draw;
/* 173 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawCloseTicks()
/*     */   {
/* 185 */     return this.drawCloseTicks;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setDrawCloseTicks(boolean draw)
/*     */   {
/* 197 */     this.drawCloseTicks = draw;
/* 198 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOpenTickPaint()
/*     */   {
/* 210 */     return this.openTickPaint;
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
/*     */   public void setOpenTickPaint(Paint paint)
/*     */   {
/* 224 */     this.openTickPaint = paint;
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
/*     */   public Paint getCloseTickPaint()
/*     */   {
/* 237 */     return this.closeTickPaint;
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
/*     */   public void setCloseTickPaint(Paint paint)
/*     */   {
/* 251 */     this.closeTickPaint = paint;
/* 252 */     fireChangeEvent();
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
/*     */   public double getTickLength()
/*     */   {
/* 265 */     return this.tickLength;
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
/*     */   public void setTickLength(double length)
/*     */   {
/* 279 */     this.tickLength = length;
/* 280 */     fireChangeEvent();
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
/* 293 */     if (dataset != null) {
/* 294 */       return DatasetUtilities.findRangeBounds(dataset, true);
/*     */     }
/*     */     
/* 297 */     return null;
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
/* 332 */     double x = dataset.getXValue(series, item);
/* 333 */     if (!domainAxis.getRange().contains(x)) {
/* 334 */       return;
/*     */     }
/* 336 */     double xx = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/*     */     
/*     */ 
/*     */ 
/* 340 */     Shape entityArea = null;
/* 341 */     EntityCollection entities = null;
/* 342 */     if (info != null) {
/* 343 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 346 */     PlotOrientation orientation = plot.getOrientation();
/* 347 */     RectangleEdge location = plot.getRangeAxisEdge();
/*     */     
/* 349 */     Paint itemPaint = getItemPaint(series, item);
/* 350 */     Stroke itemStroke = getItemStroke(series, item);
/* 351 */     g2.setPaint(itemPaint);
/* 352 */     g2.setStroke(itemStroke);
/*     */     
/* 354 */     if ((dataset instanceof OHLCDataset)) {
/* 355 */       OHLCDataset hld = (OHLCDataset)dataset;
/*     */       
/* 357 */       double yHigh = hld.getHighValue(series, item);
/* 358 */       double yLow = hld.getLowValue(series, item);
/* 359 */       if ((!Double.isNaN(yHigh)) && (!Double.isNaN(yLow))) {
/* 360 */         double yyHigh = rangeAxis.valueToJava2D(yHigh, dataArea, location);
/*     */         
/* 362 */         double yyLow = rangeAxis.valueToJava2D(yLow, dataArea, location);
/*     */         
/* 364 */         if (orientation == PlotOrientation.HORIZONTAL) {
/* 365 */           g2.draw(new Line2D.Double(yyLow, xx, yyHigh, xx));
/* 366 */           entityArea = new Rectangle2D.Double(Math.min(yyLow, yyHigh), xx - 1.0D, Math.abs(yyHigh - yyLow), 2.0D);
/*     */ 
/*     */         }
/* 369 */         else if (orientation == PlotOrientation.VERTICAL) {
/* 370 */           g2.draw(new Line2D.Double(xx, yyLow, xx, yyHigh));
/* 371 */           entityArea = new Rectangle2D.Double(xx - 1.0D, Math.min(yyLow, yyHigh), 2.0D, Math.abs(yyHigh - yyLow));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */ 
/* 377 */       double delta = getTickLength();
/* 378 */       if (domainAxis.isInverted()) {
/* 379 */         delta = -delta;
/*     */       }
/* 381 */       if (getDrawOpenTicks()) {
/* 382 */         double yOpen = hld.getOpenValue(series, item);
/* 383 */         if (!Double.isNaN(yOpen)) {
/* 384 */           double yyOpen = rangeAxis.valueToJava2D(yOpen, dataArea, location);
/*     */           
/* 386 */           if (this.openTickPaint != null) {
/* 387 */             g2.setPaint(this.openTickPaint);
/*     */           }
/*     */           else {
/* 390 */             g2.setPaint(itemPaint);
/*     */           }
/* 392 */           if (orientation == PlotOrientation.HORIZONTAL) {
/* 393 */             g2.draw(new Line2D.Double(yyOpen, xx + delta, yyOpen, xx));
/*     */ 
/*     */           }
/* 396 */           else if (orientation == PlotOrientation.VERTICAL) {
/* 397 */             g2.draw(new Line2D.Double(xx - delta, yyOpen, xx, yyOpen));
/*     */           }
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 403 */       if (getDrawCloseTicks()) {
/* 404 */         double yClose = hld.getCloseValue(series, item);
/* 405 */         if (!Double.isNaN(yClose)) {
/* 406 */           double yyClose = rangeAxis.valueToJava2D(yClose, dataArea, location);
/*     */           
/* 408 */           if (this.closeTickPaint != null) {
/* 409 */             g2.setPaint(this.closeTickPaint);
/*     */           }
/*     */           else {
/* 412 */             g2.setPaint(itemPaint);
/*     */           }
/* 414 */           if (orientation == PlotOrientation.HORIZONTAL) {
/* 415 */             g2.draw(new Line2D.Double(yyClose, xx, yyClose, xx - delta));
/*     */ 
/*     */           }
/* 418 */           else if (orientation == PlotOrientation.VERTICAL) {
/* 419 */             g2.draw(new Line2D.Double(xx, yyClose, xx + delta, yyClose));
/*     */ 
/*     */           }
/*     */           
/*     */         }
/*     */         
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 429 */     else if (item > 0) {
/* 430 */       double x0 = dataset.getXValue(series, item - 1);
/* 431 */       double y0 = dataset.getYValue(series, item - 1);
/* 432 */       double y = dataset.getYValue(series, item);
/* 433 */       if ((Double.isNaN(x0)) || (Double.isNaN(y0)) || (Double.isNaN(y))) {
/* 434 */         return;
/*     */       }
/* 436 */       double xx0 = domainAxis.valueToJava2D(x0, dataArea, plot.getDomainAxisEdge());
/*     */       
/* 438 */       double yy0 = rangeAxis.valueToJava2D(y0, dataArea, location);
/* 439 */       double yy = rangeAxis.valueToJava2D(y, dataArea, location);
/* 440 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 441 */         g2.draw(new Line2D.Double(yy0, xx0, yy, xx));
/*     */       }
/* 443 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 444 */         g2.draw(new Line2D.Double(xx0, yy0, xx, yy));
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 449 */     if (entities != null) {
/* 450 */       addEntity(entities, entityArea, dataset, series, item, 0.0D, 0.0D);
/*     */     }
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
/* 463 */     return super.clone();
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
/* 474 */     if (this == obj) {
/* 475 */       return true;
/*     */     }
/* 477 */     if (!(obj instanceof HighLowRenderer)) {
/* 478 */       return false;
/*     */     }
/* 480 */     HighLowRenderer that = (HighLowRenderer)obj;
/* 481 */     if (this.drawOpenTicks != that.drawOpenTicks) {
/* 482 */       return false;
/*     */     }
/* 484 */     if (this.drawCloseTicks != that.drawCloseTicks) {
/* 485 */       return false;
/*     */     }
/* 487 */     if (!PaintUtilities.equal(this.openTickPaint, that.openTickPaint)) {
/* 488 */       return false;
/*     */     }
/* 490 */     if (!PaintUtilities.equal(this.closeTickPaint, that.closeTickPaint)) {
/* 491 */       return false;
/*     */     }
/* 493 */     if (this.tickLength != that.tickLength) {
/* 494 */       return false;
/*     */     }
/* 496 */     if (!super.equals(obj)) {
/* 497 */       return false;
/*     */     }
/* 499 */     return true;
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
/* 512 */     stream.defaultReadObject();
/* 513 */     this.openTickPaint = SerialUtilities.readPaint(stream);
/* 514 */     this.closeTickPaint = SerialUtilities.readPaint(stream);
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
/* 525 */     stream.defaultWriteObject();
/* 526 */     SerialUtilities.writePaint(this.openTickPaint, stream);
/* 527 */     SerialUtilities.writePaint(this.closeTickPaint, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/HighLowRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */