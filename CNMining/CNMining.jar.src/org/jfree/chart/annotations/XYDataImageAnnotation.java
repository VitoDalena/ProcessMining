/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import org.jfree.chart.axis.AxisLocation;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.data.Range;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYDataImageAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, XYAnnotationBoundsInfo
/*     */ {
/*     */   private transient Image image;
/*     */   private double x;
/*     */   private double y;
/*     */   private double w;
/*     */   private double h;
/*     */   private boolean includeInDataBounds;
/*     */   
/*     */   public XYDataImageAnnotation(Image image, double x, double y, double w, double h)
/*     */   {
/* 115 */     this(image, x, y, w, h, false);
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
/*     */   public XYDataImageAnnotation(Image image, double x, double y, double w, double h, boolean includeInDataBounds)
/*     */   {
/* 134 */     if (image == null) {
/* 135 */       throw new IllegalArgumentException("Null 'image' argument.");
/*     */     }
/* 137 */     this.image = image;
/* 138 */     this.x = x;
/* 139 */     this.y = y;
/* 140 */     this.w = w;
/* 141 */     this.h = h;
/* 142 */     this.includeInDataBounds = includeInDataBounds;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Image getImage()
/*     */   {
/* 151 */     return this.image;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getX()
/*     */   {
/* 160 */     return this.x;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getY()
/*     */   {
/* 169 */     return this.y;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getWidth()
/*     */   {
/* 179 */     return this.w;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getHeight()
/*     */   {
/* 189 */     return this.h;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean getIncludeInDataBounds()
/*     */   {
/* 201 */     return this.includeInDataBounds;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getXRange()
/*     */   {
/* 212 */     return new Range(this.x, this.x + this.w);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Range getYRange()
/*     */   {
/* 223 */     return new Range(this.y, this.y + this.h);
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
/* 245 */     PlotOrientation orientation = plot.getOrientation();
/* 246 */     AxisLocation xAxisLocation = plot.getDomainAxisLocation();
/* 247 */     AxisLocation yAxisLocation = plot.getRangeAxisLocation();
/* 248 */     RectangleEdge xEdge = Plot.resolveDomainAxisLocation(xAxisLocation, orientation);
/*     */     
/* 250 */     RectangleEdge yEdge = Plot.resolveRangeAxisLocation(yAxisLocation, orientation);
/*     */     
/* 252 */     float j2DX0 = (float)domainAxis.valueToJava2D(this.x, dataArea, xEdge);
/* 253 */     float j2DY0 = (float)rangeAxis.valueToJava2D(this.y, dataArea, yEdge);
/* 254 */     float j2DX1 = (float)domainAxis.valueToJava2D(this.x + this.w, dataArea, xEdge);
/*     */     
/* 256 */     float j2DY1 = (float)rangeAxis.valueToJava2D(this.y + this.h, dataArea, yEdge);
/*     */     
/* 258 */     float xx0 = 0.0F;
/* 259 */     float yy0 = 0.0F;
/* 260 */     float xx1 = 0.0F;
/* 261 */     float yy1 = 0.0F;
/* 262 */     if (orientation == PlotOrientation.HORIZONTAL) {
/* 263 */       xx0 = j2DY0;
/* 264 */       xx1 = j2DY1;
/* 265 */       yy0 = j2DX0;
/* 266 */       yy1 = j2DX1;
/*     */     }
/* 268 */     else if (orientation == PlotOrientation.VERTICAL) {
/* 269 */       xx0 = j2DX0;
/* 270 */       xx1 = j2DX1;
/* 271 */       yy0 = j2DY0;
/* 272 */       yy1 = j2DY1;
/*     */     }
/*     */     
/* 275 */     g2.drawImage(this.image, (int)xx0, (int)Math.min(yy0, yy1), (int)(xx1 - xx0), (int)Math.abs(yy1 - yy0), null);
/*     */     
/* 277 */     String toolTip = getToolTipText();
/* 278 */     String url = getURL();
/* 279 */     if ((toolTip != null) || (url != null)) {
/* 280 */       addEntity(info, new Rectangle2D.Float(xx0, yy0, xx1 - xx0, yy1 - yy0), rendererIndex, toolTip, url);
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
/* 293 */     if (obj == this) {
/* 294 */       return true;
/*     */     }
/*     */     
/* 297 */     if (!super.equals(obj)) {
/* 298 */       return false;
/*     */     }
/* 300 */     if (!(obj instanceof XYDataImageAnnotation)) {
/* 301 */       return false;
/*     */     }
/* 303 */     XYDataImageAnnotation that = (XYDataImageAnnotation)obj;
/* 304 */     if (this.x != that.x) {
/* 305 */       return false;
/*     */     }
/* 307 */     if (this.y != that.y) {
/* 308 */       return false;
/*     */     }
/* 310 */     if (this.w != that.w) {
/* 311 */       return false;
/*     */     }
/* 313 */     if (this.h != that.h) {
/* 314 */       return false;
/*     */     }
/* 316 */     if (this.includeInDataBounds != that.includeInDataBounds) {
/* 317 */       return false;
/*     */     }
/* 319 */     if (!ObjectUtilities.equal(this.image, that.image)) {
/* 320 */       return false;
/*     */     }
/*     */     
/* 323 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 332 */     return this.image.hashCode();
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
/* 343 */     return super.clone();
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
/* 354 */     stream.defaultWriteObject();
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
/*     */   private void readObject(ObjectInputStream stream)
/*     */     throws IOException, ClassNotFoundException
/*     */   {
/* 369 */     stream.defaultReadObject();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYDataImageAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */