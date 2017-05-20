/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Line2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.event.RendererChangeEvent;
/*     */ import org.jfree.chart.plot.CrosshairState;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.renderer.LookupPaintScale;
/*     */ import org.jfree.chart.renderer.PaintScale;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.data.general.DatasetUtilities;
/*     */ import org.jfree.data.xy.XYDataset;
/*     */ import org.jfree.data.xy.XYZDataset;
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
/*     */ public class XYShapeRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 8320552104211173221L;
/*     */   private PaintScale paintScale;
/*     */   private boolean drawOutlines;
/*     */   private boolean useOutlinePaint;
/*     */   private boolean useFillPaint;
/*     */   private boolean guideLinesVisible;
/*     */   private transient Paint guideLinePaint;
/*     */   private transient Stroke guideLineStroke;
/*     */   
/*     */   public XYShapeRenderer()
/*     */   {
/* 133 */     this.paintScale = new LookupPaintScale();
/* 134 */     this.useFillPaint = false;
/* 135 */     this.drawOutlines = false;
/* 136 */     this.useOutlinePaint = true;
/* 137 */     this.guideLinesVisible = false;
/* 138 */     this.guideLinePaint = Color.darkGray;
/* 139 */     this.guideLineStroke = new BasicStroke();
/* 140 */     setBaseShape(new Ellipse2D.Double(-5.0D, -5.0D, 10.0D, 10.0D));
/* 141 */     setAutoPopulateSeriesShape(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PaintScale getPaintScale()
/*     */   {
/* 152 */     return this.paintScale;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaintScale(PaintScale scale)
/*     */   {
/* 164 */     if (scale == null) {
/* 165 */       throw new IllegalArgumentException("Null 'scale' argument.");
/*     */     }
/* 167 */     this.paintScale = scale;
/* 168 */     notifyListeners(new RendererChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getDrawOutlines()
/*     */   {
/* 180 */     return this.drawOutlines;
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
/*     */   public void setDrawOutlines(boolean flag)
/*     */   {
/* 196 */     this.drawOutlines = flag;
/* 197 */     fireChangeEvent();
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
/*     */   public boolean getUseFillPaint()
/*     */   {
/* 214 */     return this.useFillPaint;
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
/*     */   public void setUseFillPaint(boolean flag)
/*     */   {
/* 227 */     this.useFillPaint = flag;
/* 228 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getUseOutlinePaint()
/*     */   {
/* 240 */     return this.useOutlinePaint;
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
/*     */   public void setUseOutlinePaint(boolean use)
/*     */   {
/* 253 */     this.useOutlinePaint = use;
/* 254 */     fireChangeEvent();
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
/*     */   public boolean isGuideLinesVisible()
/*     */   {
/* 267 */     return this.guideLinesVisible;
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
/*     */   public void setGuideLinesVisible(boolean visible)
/*     */   {
/* 280 */     this.guideLinesVisible = visible;
/* 281 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getGuideLinePaint()
/*     */   {
/* 292 */     return this.guideLinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGuideLinePaint(Paint paint)
/*     */   {
/* 304 */     if (paint == null) {
/* 305 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 307 */     this.guideLinePaint = paint;
/* 308 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getGuideLineStroke()
/*     */   {
/* 319 */     return this.guideLineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setGuideLineStroke(Stroke stroke)
/*     */   {
/* 331 */     if (stroke == null) {
/* 332 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 334 */     this.guideLineStroke = stroke;
/* 335 */     fireChangeEvent();
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
/* 348 */     if (dataset != null) {
/* 349 */       Range r = DatasetUtilities.findDomainBounds(dataset, false);
/* 350 */       double offset = 0.0D;
/* 351 */       return new Range(r.getLowerBound() + offset, r.getUpperBound() + offset);
/*     */     }
/*     */     
/*     */ 
/* 355 */     return null;
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
/* 369 */     if (dataset != null) {
/* 370 */       Range r = DatasetUtilities.findRangeBounds(dataset, false);
/* 371 */       double offset = 0.0D;
/* 372 */       return new Range(r.getLowerBound() + offset, r.getUpperBound() + offset);
/*     */     }
/*     */     
/*     */ 
/* 376 */     return null;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 386 */     return 2;
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
/*     */   public void drawItem(Graphics2D g2, XYItemRendererState state, Rectangle2D dataArea, PlotRenderingInfo info, XYPlot plot, ValueAxis domainAxis, ValueAxis rangeAxis, XYDataset dataset, int series, int item, CrosshairState crosshairState, int pass)
/*     */   {
/* 410 */     Shape hotspot = null;
/* 411 */     EntityCollection entities = null;
/* 412 */     if (info != null) {
/* 413 */       entities = info.getOwner().getEntityCollection();
/*     */     }
/*     */     
/* 416 */     double x = dataset.getXValue(series, item);
/* 417 */     double y = dataset.getYValue(series, item);
/* 418 */     if ((Double.isNaN(x)) || (Double.isNaN(y)))
/*     */     {
/* 420 */       return;
/*     */     }
/*     */     
/* 423 */     double transX = domainAxis.valueToJava2D(x, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 425 */     double transY = rangeAxis.valueToJava2D(y, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 428 */     PlotOrientation orientation = plot.getOrientation();
/*     */     
/*     */ 
/* 431 */     if ((pass == 0) && (this.guideLinesVisible)) {
/* 432 */       g2.setStroke(this.guideLineStroke);
/* 433 */       g2.setPaint(this.guideLinePaint);
/* 434 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 435 */         g2.draw(new Line2D.Double(transY, dataArea.getMinY(), transY, dataArea.getMaxY()));
/*     */         
/* 437 */         g2.draw(new Line2D.Double(dataArea.getMinX(), transX, dataArea.getMaxX(), transX));
/*     */       }
/*     */       else
/*     */       {
/* 441 */         g2.draw(new Line2D.Double(transX, dataArea.getMinY(), transX, dataArea.getMaxY()));
/*     */         
/* 443 */         g2.draw(new Line2D.Double(dataArea.getMinX(), transY, dataArea.getMaxX(), transY));
/*     */       }
/*     */       
/*     */     }
/* 447 */     else if (pass == 1) {
/* 448 */       Shape shape = getItemShape(series, item);
/* 449 */       if (orientation == PlotOrientation.HORIZONTAL) {
/* 450 */         shape = ShapeUtilities.createTranslatedShape(shape, transY, transX);
/*     */ 
/*     */       }
/* 453 */       else if (orientation == PlotOrientation.VERTICAL) {
/* 454 */         shape = ShapeUtilities.createTranslatedShape(shape, transX, transY);
/*     */       }
/*     */       
/* 457 */       hotspot = shape;
/* 458 */       if (shape.intersects(dataArea))
/*     */       {
/* 460 */         g2.setPaint(getPaint(dataset, series, item));
/* 461 */         g2.fill(shape);
/*     */         
/* 463 */         if (this.drawOutlines) {
/* 464 */           if (getUseOutlinePaint()) {
/* 465 */             g2.setPaint(getItemOutlinePaint(series, item));
/*     */           }
/*     */           else {
/* 468 */             g2.setPaint(getItemPaint(series, item));
/*     */           }
/* 470 */           g2.setStroke(getItemOutlineStroke(series, item));
/* 471 */           g2.draw(shape);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 476 */       if (entities != null) {
/* 477 */         addEntity(entities, hotspot, dataset, series, item, transX, transY);
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
/*     */   protected Paint getPaint(XYDataset dataset, int series, int item)
/*     */   {
/* 493 */     Paint p = null;
/* 494 */     if ((dataset instanceof XYZDataset)) {
/* 495 */       double z = ((XYZDataset)dataset).getZValue(series, item);
/* 496 */       p = this.paintScale.getPaint(z);
/*     */ 
/*     */     }
/* 499 */     else if (this.useFillPaint) {
/* 500 */       p = getItemFillPaint(series, item);
/*     */     }
/*     */     else {
/* 503 */       p = getItemPaint(series, item);
/*     */     }
/*     */     
/* 506 */     return p;
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 524 */     if (obj == this) {
/* 525 */       return true;
/*     */     }
/* 527 */     if (!(obj instanceof XYShapeRenderer)) {
/* 528 */       return false;
/*     */     }
/* 530 */     XYShapeRenderer that = (XYShapeRenderer)obj;
/* 531 */     if (((this.paintScale == null) && (that.paintScale != null)) || (!this.paintScale.equals(that.paintScale)))
/*     */     {
/* 533 */       return false;
/*     */     }
/* 535 */     if (this.drawOutlines != that.drawOutlines) {
/* 536 */       return false;
/*     */     }
/* 538 */     if (this.useOutlinePaint != that.useOutlinePaint) {
/* 539 */       return false;
/*     */     }
/* 541 */     if (this.useFillPaint != that.useFillPaint) {
/* 542 */       return false;
/*     */     }
/* 544 */     if (this.guideLinesVisible != that.guideLinesVisible) {
/* 545 */       return false;
/*     */     }
/* 547 */     if (((this.guideLinePaint == null) && (that.guideLinePaint != null)) || (!this.guideLinePaint.equals(that.guideLinePaint)))
/*     */     {
/* 549 */       return false; }
/* 550 */     if (((this.guideLineStroke == null) && (that.guideLineStroke != null)) || (!this.guideLineStroke.equals(that.guideLineStroke)))
/*     */     {
/* 552 */       return false;
/*     */     }
/* 554 */     return super.equals(obj);
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
/* 566 */     XYShapeRenderer clone = (XYShapeRenderer)super.clone();
/* 567 */     if ((this.paintScale instanceof PublicCloneable)) {
/* 568 */       PublicCloneable pc = (PublicCloneable)this.paintScale;
/* 569 */       clone.paintScale = ((PaintScale)pc.clone());
/*     */     }
/* 571 */     return clone;
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
/* 584 */     stream.defaultReadObject();
/* 585 */     this.guideLinePaint = SerialUtilities.readPaint(stream);
/* 586 */     this.guideLineStroke = SerialUtilities.readStroke(stream);
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
/* 597 */     stream.defaultWriteObject();
/* 598 */     SerialUtilities.writePaint(this.guideLinePaint, stream);
/* 599 */     SerialUtilities.writeStroke(this.guideLineStroke, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYShapeRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */