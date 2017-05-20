/*     */ package org.jfree.chart.annotations;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.axis.ValueAxis;
/*     */ import org.jfree.chart.plot.Plot;
/*     */ import org.jfree.chart.plot.PlotOrientation;
/*     */ import org.jfree.chart.plot.PlotRenderingInfo;
/*     */ import org.jfree.chart.plot.XYPlot;
/*     */ import org.jfree.ui.Drawable;
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
/*     */ public class XYDrawableAnnotation
/*     */   extends AbstractXYAnnotation
/*     */   implements Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -6540812859722691020L;
/*     */   private double drawScaleFactor;
/*     */   private double x;
/*     */   private double y;
/*     */   private double displayWidth;
/*     */   private double displayHeight;
/*     */   private Drawable drawable;
/*     */   
/*     */   public XYDrawableAnnotation(double x, double y, double width, double height, Drawable drawable)
/*     */   {
/*  99 */     this(x, y, width, height, 1.0D, drawable);
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
/*     */   public XYDrawableAnnotation(double x, double y, double displayWidth, double displayHeight, double drawScaleFactor, Drawable drawable)
/*     */   {
/* 120 */     if (drawable == null) {
/* 121 */       throw new IllegalArgumentException("Null 'drawable' argument.");
/*     */     }
/* 123 */     this.x = x;
/* 124 */     this.y = y;
/* 125 */     this.displayWidth = displayWidth;
/* 126 */     this.displayHeight = displayHeight;
/* 127 */     this.drawScaleFactor = drawScaleFactor;
/* 128 */     this.drawable = drawable;
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
/*     */   public void draw(Graphics2D g2, XYPlot plot, Rectangle2D dataArea, ValueAxis domainAxis, ValueAxis rangeAxis, int rendererIndex, PlotRenderingInfo info)
/*     */   {
/* 149 */     PlotOrientation orientation = plot.getOrientation();
/* 150 */     RectangleEdge domainEdge = Plot.resolveDomainAxisLocation(plot.getDomainAxisLocation(), orientation);
/*     */     
/* 152 */     RectangleEdge rangeEdge = Plot.resolveRangeAxisLocation(plot.getRangeAxisLocation(), orientation);
/*     */     
/* 154 */     float j2DX = (float)domainAxis.valueToJava2D(this.x, dataArea, domainEdge);
/*     */     
/* 156 */     float j2DY = (float)rangeAxis.valueToJava2D(this.y, dataArea, rangeEdge);
/*     */     
/* 158 */     Rectangle2D displayArea = new Rectangle2D.Double(j2DX - this.displayWidth / 2.0D, j2DY - this.displayHeight / 2.0D, this.displayWidth, this.displayHeight);
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 166 */     AffineTransform savedTransform = g2.getTransform();
/* 167 */     Rectangle2D drawArea = new Rectangle2D.Double(0.0D, 0.0D, this.displayWidth * this.drawScaleFactor, this.displayHeight * this.drawScaleFactor);
/*     */     
/*     */ 
/*     */ 
/* 171 */     g2.scale(1.0D / this.drawScaleFactor, 1.0D / this.drawScaleFactor);
/* 172 */     g2.translate((j2DX - this.displayWidth / 2.0D) * this.drawScaleFactor, (j2DY - this.displayHeight / 2.0D) * this.drawScaleFactor);
/*     */     
/* 174 */     this.drawable.draw(g2, drawArea);
/* 175 */     g2.setTransform(savedTransform);
/* 176 */     String toolTip = getToolTipText();
/* 177 */     String url = getURL();
/* 178 */     if ((toolTip != null) || (url != null)) {
/* 179 */       addEntity(info, displayArea, rendererIndex, toolTip, url);
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
/* 193 */     if (obj == this) {
/* 194 */       return true;
/*     */     }
/*     */     
/* 197 */     if (!super.equals(obj)) {
/* 198 */       return false;
/*     */     }
/* 200 */     if (!(obj instanceof XYDrawableAnnotation)) {
/* 201 */       return false;
/*     */     }
/* 203 */     XYDrawableAnnotation that = (XYDrawableAnnotation)obj;
/* 204 */     if (this.x != that.x) {
/* 205 */       return false;
/*     */     }
/* 207 */     if (this.y != that.y) {
/* 208 */       return false;
/*     */     }
/* 210 */     if (this.displayWidth != that.displayWidth) {
/* 211 */       return false;
/*     */     }
/* 213 */     if (this.displayHeight != that.displayHeight) {
/* 214 */       return false;
/*     */     }
/* 216 */     if (this.drawScaleFactor != that.drawScaleFactor) {
/* 217 */       return false;
/*     */     }
/* 219 */     if (!ObjectUtilities.equal(this.drawable, that.drawable)) {
/* 220 */       return false;
/*     */     }
/*     */     
/* 223 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 235 */     long temp = Double.doubleToLongBits(this.x);
/* 236 */     int result = (int)(temp ^ temp >>> 32);
/* 237 */     temp = Double.doubleToLongBits(this.y);
/* 238 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 239 */     temp = Double.doubleToLongBits(this.displayWidth);
/* 240 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 241 */     temp = Double.doubleToLongBits(this.displayHeight);
/* 242 */     result = 29 * result + (int)(temp ^ temp >>> 32);
/* 243 */     return result;
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
/* 254 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/annotations/XYDrawableAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */