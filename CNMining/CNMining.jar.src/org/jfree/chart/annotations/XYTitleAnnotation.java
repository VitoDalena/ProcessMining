/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.ChartRenderingInfo;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.chart.axis.AxisLocation;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.block.BlockParams;
/*     */ import org.jfree.chart.block.EntityBlockResult;
/*     */ import org.jfree.chart.block.RectangleConstraint;
/*     */ import org.jfree.chart.entity.EntityCollection;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.chart.title.Title;
/*     */ import org.jfree.chart.util.XYCoordinateType;
/*     */ import org.jfree.data.Range;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ import org.jfree.ui.Size2D;
/*     */ import org.jfree.util.ObjectUtilities;
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
/*     */ public class XYTitleAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4364694501921559958L;
/*     */   private XYCoordinateType coordinateType;
/*     */   private double x;
/*     */   private double y;
/*     */   private double maxWidth;
/*     */   private double maxHeight;
/*     */   private Title title;
/*     */   private RectangleAnchor anchor;
/*     */   
/*     */   public XYTitleAnnotation(double x, double y, Title title)
/*     */   {
/* 115 */     this(x, y, title, RectangleAnchor.CENTER);
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
/*     */   public XYTitleAnnotation(double x, double y, Title title, RectangleAnchor anchor)
/*     */   {
/* 129 */     if (title == null) {
/* 130 */       throw new IllegalArgumentException("Null 'title' argument.");
/*     */     }
/* 132 */     if (anchor == null) {
/* 133 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 135 */     this.coordinateType = XYCoordinateType.RELATIVE;
/* 136 */     this.x = x;
/* 137 */     this.y = y;
/* 138 */     this.maxWidth = 0.0D;
/* 139 */     this.maxHeight = 0.0D;
/* 140 */     this.title = title;
/* 141 */     this.anchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public XYCoordinateType getCoordinateType()
/*     */   {
/* 150 */     return this.coordinateType;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/* 159 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 168 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Title getTitle()
/*     */   {
/* 177 */     return this.title;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getTitleAnchor()
/*     */   {
/* 186 */     return this.anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaxWidth()
/*     */   {
/* 195 */     return this.maxWidth;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaxWidth(double max)
/*     */   {
/* 204 */     this.maxWidth = max;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getMaxHeight()
/*     */   {
/* 213 */     return this.maxHeight;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMaxHeight(double max)
/*     */   {
/* 222 */     this.maxHeight = max;
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
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 244 */     PlotOrientation orientation = plot.getOrientation();
/* 245 */     AxisLocation domainAxisLocation = plot.getDomainAxisLocation();
/* 246 */     AxisLocation rangeAxisLocation = plot.getRangeAxisLocation();
/* 247 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(domainAxisLocation, orientation);
/*     */     
/* 249 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(rangeAxisLocation, orientation);
/*     */     
/* 251 */     Range xRange = domainAxis.getRange();
/* 252 */     Range yRange = rangeAxis.getRange();
/* 253 */     double anchorX = 0.0D;
/* 254 */     double anchorY = 0.0D;
/* 255 */     if (this.coordinateType == XYCoordinateType.RELATIVE) {
/* 256 */       anchorX = xRange.getLowerBound() + this.x * xRange.getLength();
/* 257 */       anchorY = yRange.getLowerBound() + this.y * yRange.getLength();
/*     */     }
/*     */     else {
/* 260 */       anchorX = domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/* 261 */       anchorY = rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/*     */     }
/*     */     
/* 264 */     float j2DX = (float)domainAxis.valueToJava2D(anchorX, dataArea, domainEdge);
/*     */     
/* 266 */     float j2DY = (float)rangeAxis.valueToJava2D(anchorY, dataArea, rangeEdge);
/*     */     
/* 268 */     float xx = 0.0F;
/* 269 */     float yy = 0.0F;
/* 270 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 271 */       xx = j2DY;
/* 272 */       yy = j2DX;
/*     */     }
/* 274 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 275 */       xx = j2DX;
/* 276 */       yy = j2DY;
/*     */     }
/*     */     
/* 279 */     double maxW = dataArea.getWidth();
/* 280 */     double maxH = dataArea.getHeight();
/* 281 */     if (this.coordinateType == XYCoordinateType.RELATIVE) {
/* 282 */       if (this.maxWidth > 0.0D) {
/* 283 */         maxW *= this.maxWidth;
/*     */       }
/* 285 */       if (this.maxHeight > 0.0D) {
/* 286 */         maxH *= this.maxHeight;
/*     */       }
/*     */     }
/* 289 */     if (this.coordinateType == XYCoordinateType.DATA) {
/* 290 */       maxW = this.maxWidth;
/* 291 */       maxH = this.maxHeight;
/*     */     }
/* 293 */     RectangleConstraint rc = new RectangleConstraint(new Range(0.0D, maxW), new Range(0.0D, maxH));
/*     */     
/*     */ 
/* 296 */     Size2D size = this.title.arrange(g2, rc);
/* 297 */     Rectangle2D titleRect = new Rectangle2D.Double(0.0D, 0.0D, size.width, size.height);
/*     */     
/* 299 */     Point2D anchorPoint = RectangleAnchor.coordinates(titleRect, this.anchor);
/*     */     
/* 301 */     xx -= (float)anchorPoint.getX();
/* 302 */     yy -= (float)anchorPoint.getY();
/* 303 */     titleRect.setRect(xx, yy, titleRect.getWidth(), titleRect.getHeight());
/* 304 */     BlockParams p = new BlockParams();
/* 305 */     if ((info != null) && 
/* 306 */       (info.getOwner().getEntityCollection() != null)) {
/* 307 */       p.setGenerateEntities(true);
/*     */     }
/*     */     
/* 310 */     Object result = this.title.draw(g2, titleRect, p);
/* 311 */     if (info != null) {
/* 312 */       if ((result instanceof EntityBlockResult)) {
/* 313 */         EntityBlockResult ebr = (EntityBlockResult)result;
/* 314 */         info.getOwner().getEntityCollection().addAll(ebr.getEntityCollection());
/*     */       }
/*     */       
/* 317 */       String toolTip = getToolTipText();
/* 318 */       String url = getURL();
/* 319 */       if ((toolTip != null) || (url != null)) {
/* 320 */         addEntity(info, new Rectangle2D.Float(xx, yy, (float)size.width, (float)size.height), rendererIndex, toolTip, url);
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
/* 335 */     if (obj == this) {
/* 336 */       return true;
/*     */     }
/* 338 */     if (!(obj instanceof XYTitleAnnotation)) {
/* 339 */       return false;
/*     */     }
/* 341 */     XYTitleAnnotation that = (XYTitleAnnotation)obj;
/* 342 */     if (this.coordinateType != that.coordinateType) {
/* 343 */       return false;
/*     */     }
/* 345 */     if (this.x != that.x) {
/* 346 */       return false;
/*     */     }
/* 348 */     if (this.y != that.y) {
/* 349 */       return false;
/*     */     }
/* 351 */     if (this.maxWidth != that.maxWidth) {
/* 352 */       return false;
/*     */     }
/* 354 */     if (this.maxHeight != that.maxHeight) {
/* 355 */       return false;
/*     */     }
/* 357 */     if (!ObjectUtilities.equal(this.title, that.title)) {
/* 358 */       return false;
/*     */     }
/* 360 */     if (!this.anchor.equals(that.anchor)) {
/* 361 */       return false;
/*     */     }
/* 363 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 372 */     int result = 193;
/* 373 */     result = HashUtilities.hashCode(result, this.anchor);
/* 374 */     result = HashUtilities.hashCode(result, this.coordinateType);
/* 375 */     result = HashUtilities.hashCode(result, this.x);
/* 376 */     result = HashUtilities.hashCode(result, this.y);
/* 377 */     result = HashUtilities.hashCode(result, this.maxWidth);
/* 378 */     result = HashUtilities.hashCode(result, this.maxHeight);
/* 379 */     result = HashUtilities.hashCode(result, this.title);
/* 380 */     return result;
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
/* 391 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYTitleAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */