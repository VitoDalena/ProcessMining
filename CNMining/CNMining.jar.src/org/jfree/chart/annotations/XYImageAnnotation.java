/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.AxisLocation;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.ui.RectangleAnchor;
/*     */ import org.jfree.ui.RectangleEdge;
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
/*     */ public class XYImageAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4364694501921559958L;
/*     */   private double x;
/*     */   private double y;
/*     */   private transient Image image;
/*     */   private RectangleAnchor anchor;
/*     */   
/*     */   public XYImageAnnotation(double x, double y, Image image)
/*     */   {
/* 106 */     this(x, y, image, RectangleAnchor.CENTER);
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
/*     */   public XYImageAnnotation(double x, double y, Image image, RectangleAnchor anchor)
/*     */   {
/* 122 */     if (image == null) {
/* 123 */       throw new IllegalArgumentException("Null 'image' argument.");
/*     */     }
/* 125 */     if (anchor == null) {
/* 126 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 128 */     this.x = x;
/* 129 */     this.y = y;
/* 130 */     this.image = image;
/* 131 */     this.anchor = anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/* 142 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 153 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Image getImage()
/*     */   {
/* 164 */     return this.image;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public RectangleAnchor getImageAnchor()
/*     */   {
/* 175 */     return this.anchor;
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
/* 197 */     PlotOrientation orientation = plot.getOrientation();
/* 198 */     AxisLocation domainAxisLocation = plot.getDomainAxisLocation();
/* 199 */     AxisLocation rangeAxisLocation = plot.getRangeAxisLocation();
/* 200 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(domainAxisLocation, orientation);
/*     */     
/* 202 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(rangeAxisLocation, orientation);
/*     */     
/* 204 */     float j2DX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/*     */     
/* 206 */     float j2DY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/*     */     
/* 208 */     float xx = 0.0F;
/* 209 */     float yy = 0.0F;
/* 210 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 211 */       xx = j2DY;
/* 212 */       yy = j2DX;
/*     */     }
/* 214 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 215 */       xx = j2DX;
/* 216 */       yy = j2DY;
/*     */     }
/* 218 */     int w = this.image.getWidth(null);
/* 219 */     int h = this.image.getHeight(null);
/*     */     
/* 221 */     Rectangle2D imageRect = new Rectangle2D.Double(0.0D, 0.0D, w, h);
/* 222 */     Point2D anchorPoint = RectangleAnchor.coordinates(imageRect, this.anchor);
/*     */     
/* 224 */     xx -= (float)anchorPoint.getX();
/* 225 */     yy -= (float)anchorPoint.getY();
/* 226 */     g2.drawImage(this.image, (int)xx, (int)yy, null);
/*     */     
/* 228 */     String toolTip = getToolTipText();
/* 229 */     String url = getURL();
/* 230 */     if ((toolTip != null) || (url != null)) {
/* 231 */       addEntity(info, new Rectangle2D.Float(xx, yy, w, h), rendererIndex, toolTip, url);
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
/* 244 */     if (obj == this) {
/* 245 */       return true;
/*     */     }
/*     */     
/* 248 */     if (!super.equals(obj)) {
/* 249 */       return false;
/*     */     }
/* 251 */     if (!(obj instanceof XYImageAnnotation)) {
/* 252 */       return false;
/*     */     }
/* 254 */     XYImageAnnotation that = (XYImageAnnotation)obj;
/* 255 */     if (this.x != that.x) {
/* 256 */       return false;
/*     */     }
/* 258 */     if (this.y != that.y) {
/* 259 */       return false;
/*     */     }
/* 261 */     if (!ObjectUtilities.equal(this.image, that.image)) {
/* 262 */       return false;
/*     */     }
/* 264 */     if (!this.anchor.equals(that.anchor)) {
/* 265 */       return false;
/*     */     }
/*     */     
/* 268 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 277 */     return this.image.hashCode();
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
/* 288 */     return super.clone();
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
/* 299 */     stream.defaultWriteObject();
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
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 313 */     stream.defaultReadObject();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYImageAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */