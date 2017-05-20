/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.entity.EntityCollection;
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
/*     */ import org.jfree.ui.RectangleAnchor;
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
/*     */ public class XYBlockRenderer
/*     */   extends AbstractXYItemRenderer
/*     */   implements XYItemRenderer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*  88 */   private double blockWidth = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*  93 */   private double blockHeight = 1.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*  99 */   private RectangleAnchor blockAnchor = RectangleAnchor.CENTER;
/*     */   
/*     */ 
/*     */ 
/*     */   private double xOffset;
/*     */   
/*     */ 
/*     */   private double yOffset;
/*     */   
/*     */ 
/*     */   private PaintScale paintScale;
/*     */   
/*     */ 
/*     */ 
/*     */   public XYBlockRenderer()
/*     */   {
/* 115 */     updateOffsets();
/* 116 */     this.paintScale = new LookupPaintScale();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBlockWidth()
/*     */   {
/* 127 */     return this.blockWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBlockWidth(double width)
/*     */   {
/* 139 */     if (width <= 0.0D) {
/* 140 */       throw new IllegalArgumentException("The 'width' argument must be > 0.0");
/*     */     }
/*     */     
/* 143 */     this.blockWidth = width;
/* 144 */     updateOffsets();
/* 145 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getBlockHeight()
/*     */   {
/* 156 */     return this.blockHeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBlockHeight(double height)
/*     */   {
/* 168 */     if (height <= 0.0D) {
/* 169 */       throw new IllegalArgumentException("The 'height' argument must be > 0.0");
/*     */     }
/*     */     
/* 172 */     this.blockHeight = height;
/* 173 */     updateOffsets();
/* 174 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getBlockAnchor()
/*     */   {
/* 186 */     return this.blockAnchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBlockAnchor(RectangleAnchor anchor)
/*     */   {
/* 198 */     if (anchor == null) {
/* 199 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 201 */     if (this.blockAnchor.equals(anchor)) {
/* 202 */       return;
/*     */     }
/* 204 */     this.blockAnchor = anchor;
/* 205 */     updateOffsets();
/* 206 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public PaintScale getPaintScale()
/*     */   {
/* 218 */     return this.paintScale;
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
/*     */   public void setPaintScale(PaintScale scale)
/*     */   {
/* 231 */     if (scale == null) {
/* 232 */       throw new IllegalArgumentException("Null 'scale' argument.");
/*     */     }
/* 234 */     this.paintScale = scale;
/* 235 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   private void updateOffsets()
/*     */   {
/* 243 */     if (this.blockAnchor.equals(RectangleAnchor.BOTTOM_LEFT)) {
/* 244 */       this.xOffset = 0.0D;
/* 245 */       this.yOffset = 0.0D;
/*     */     }
/* 247 */     else if (this.blockAnchor.equals(RectangleAnchor.BOTTOM)) {
/* 248 */       this.xOffset = (-this.blockWidth / 2.0D);
/* 249 */       this.yOffset = 0.0D;
/*     */     }
/* 251 */     else if (this.blockAnchor.equals(RectangleAnchor.BOTTOM_RIGHT)) {
/* 252 */       this.xOffset = (-this.blockWidth);
/* 253 */       this.yOffset = 0.0D;
/*     */     }
/* 255 */     else if (this.blockAnchor.equals(RectangleAnchor.LEFT)) {
/* 256 */       this.xOffset = 0.0D;
/* 257 */       this.yOffset = (-this.blockHeight / 2.0D);
/*     */     }
/* 259 */     else if (this.blockAnchor.equals(RectangleAnchor.CENTER)) {
/* 260 */       this.xOffset = (-this.blockWidth / 2.0D);
/* 261 */       this.yOffset = (-this.blockHeight / 2.0D);
/*     */     }
/* 263 */     else if (this.blockAnchor.equals(RectangleAnchor.RIGHT)) {
/* 264 */       this.xOffset = (-this.blockWidth);
/* 265 */       this.yOffset = (-this.blockHeight / 2.0D);
/*     */     }
/* 267 */     else if (this.blockAnchor.equals(RectangleAnchor.TOP_LEFT)) {
/* 268 */       this.xOffset = 0.0D;
/* 269 */       this.yOffset = (-this.blockHeight);
/*     */     }
/* 271 */     else if (this.blockAnchor.equals(RectangleAnchor.TOP)) {
/* 272 */       this.xOffset = (-this.blockWidth / 2.0D);
/* 273 */       this.yOffset = (-this.blockHeight);
/*     */     }
/* 275 */     else if (this.blockAnchor.equals(RectangleAnchor.TOP_RIGHT)) {
/* 276 */       this.xOffset = (-this.blockWidth);
/* 277 */       this.yOffset = (-this.blockHeight);
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
/*     */   public Range findDomainBounds(XYDataset dataset)
/*     */   {
/* 293 */     if (dataset != null) {
/* 294 */       Range r = DatasetUtilities.findDomainBounds(dataset, false);
/* 295 */       if (r == null) {
/* 296 */         return null;
/*     */       }
/*     */       
/* 299 */       return new Range(r.getLowerBound() + this.xOffset, r.getUpperBound() + this.blockWidth + this.xOffset);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 304 */     return null;
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
/*     */   public Range findRangeBounds(XYDataset dataset)
/*     */   {
/* 320 */     if (dataset != null) {
/* 321 */       Range r = DatasetUtilities.findRangeBounds(dataset, false);
/* 322 */       if (r == null) {
/* 323 */         return null;
/*     */       }
/*     */       
/* 326 */       return new Range(r.getLowerBound() + this.yOffset, r.getUpperBound() + this.blockHeight + this.yOffset);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 331 */     return null;
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
/* 356 */     double x = dataset.getXValue(series, item);
/* 357 */     double y = dataset.getYValue(series, item);
/* 358 */     double z = 0.0D;
/* 359 */     if ((dataset instanceof XYZDataset)) {
/* 360 */       z = ((XYZDataset)dataset).getZValue(series, item);
/*     */     }
/* 362 */     Paint p = this.paintScale.getPaint(z);
/* 363 */     double xx0 = domainAxis.valueToJava2D(x + this.xOffset, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 365 */     double yy0 = rangeAxis.valueToJava2D(y + this.yOffset, dataArea, plot.getRangeAxisEdge());
/*     */     
/* 367 */     double xx1 = domainAxis.valueToJava2D(x + this.blockWidth + this.xOffset, dataArea, plot.getDomainAxisEdge());
/*     */     
/* 369 */     double yy1 = rangeAxis.valueToJava2D(y + this.blockHeight + this.yOffset, dataArea, plot.getRangeAxisEdge());
/*     */     
/*     */ 
/* 372 */     PlotOrientation orientation = plot.getOrientation();
/* 373 */     Rectangle2D block; Rectangle2D block; if (orientation.equals(PlotOrientation.HORIZONTAL)) {
/* 374 */       block = new Rectangle2D.Double(Math.min(yy0, yy1), Math.min(xx0, xx1), Math.abs(yy1 - yy0), Math.abs(xx0 - xx1));
/*     */ 
/*     */     }
/*     */     else
/*     */     {
/* 379 */       block = new Rectangle2D.Double(Math.min(xx0, xx1), Math.min(yy0, yy1), Math.abs(xx1 - xx0), Math.abs(yy1 - yy0));
/*     */     }
/*     */     
/*     */ 
/* 383 */     g2.setPaint(p);
/* 384 */     g2.fill(block);
/* 385 */     g2.setStroke(new BasicStroke(1.0F));
/* 386 */     g2.draw(block);
/*     */     
/* 388 */     EntityCollection entities = state.getEntityCollection();
/* 389 */     if (entities != null) {
/* 390 */       addEntity(entities, block, dataset, series, item, 0.0D, 0.0D);
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
/*     */   public boolean equals(Object obj)
/*     */   {
/* 410 */     if (obj == this) {
/* 411 */       return true;
/*     */     }
/* 413 */     if (!(obj instanceof XYBlockRenderer)) {
/* 414 */       return false;
/*     */     }
/* 416 */     XYBlockRenderer that = (XYBlockRenderer)obj;
/* 417 */     if (this.blockHeight != that.blockHeight) {
/* 418 */       return false;
/*     */     }
/* 420 */     if (this.blockWidth != that.blockWidth) {
/* 421 */       return false;
/*     */     }
/* 423 */     if (!this.blockAnchor.equals(that.blockAnchor)) {
/* 424 */       return false;
/*     */     }
/* 426 */     if (!this.paintScale.equals(that.paintScale)) {
/* 427 */       return false;
/*     */     }
/* 429 */     return super.equals(obj);
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
/* 441 */     XYBlockRenderer clone = (XYBlockRenderer)super.clone();
/* 442 */     if ((this.paintScale instanceof PublicCloneable)) {
/* 443 */       PublicCloneable pc = (PublicCloneable)this.paintScale;
/* 444 */       clone.paintScale = ((PaintScale)pc.clone());
/*     */     }
/* 446 */     return clone;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYBlockRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */