/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.io.SerialUtilities;
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
/*     */ public class StandardDialFrame
/*     */   extends AbstractDialLayer
/*     */   implements DialFrame, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 1016585407507121596L;
/*     */   private double radius;
/*     */   private transient Paint backgroundPaint;
/*     */   private transient Paint foregroundPaint;
/*     */   private transient Stroke stroke;
/*     */   
/*     */   public StandardDialFrame()
/*     */   {
/* 100 */     this.backgroundPaint = Color.gray;
/* 101 */     this.foregroundPaint = Color.black;
/* 102 */     this.stroke = new BasicStroke(2.0F);
/* 103 */     this.radius = 0.95D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/* 114 */     return this.radius;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setRadius(double radius)
/*     */   {
/* 126 */     if (radius <= 0.0D) {
/* 127 */       throw new IllegalArgumentException("The 'radius' must be positive.");
/*     */     }
/*     */     
/* 130 */     this.radius = radius;
/* 131 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getBackgroundPaint()
/*     */   {
/* 142 */     return this.backgroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setBackgroundPaint(Paint paint)
/*     */   {
/* 154 */     if (paint == null) {
/* 155 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 157 */     this.backgroundPaint = paint;
/* 158 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getForegroundPaint()
/*     */   {
/* 169 */     return this.foregroundPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setForegroundPaint(Paint paint)
/*     */   {
/* 181 */     if (paint == null) {
/* 182 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 184 */     this.foregroundPaint = paint;
/* 185 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getStroke()
/*     */   {
/* 196 */     return this.stroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setStroke(Stroke stroke)
/*     */   {
/* 208 */     if (stroke == null) {
/* 209 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 211 */     this.stroke = stroke;
/* 212 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Shape getWindow(Rectangle2D frame)
/*     */   {
/* 224 */     Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, this.radius);
/*     */     
/* 226 */     return new Ellipse2D.Double(f.getX(), f.getY(), f.getWidth(), f.getHeight());
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 237 */     return false;
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
/*     */   public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */   {
/* 252 */     Shape window = getWindow(frame);
/*     */     
/* 254 */     Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius + 0.02D, this.radius + 0.02D);
/*     */     
/* 256 */     Ellipse2D e = new Ellipse2D.Double(f.getX(), f.getY(), f.getWidth(), f.getHeight());
/*     */     
/*     */ 
/* 259 */     Area area = new Area(e);
/* 260 */     Area area2 = new Area(window);
/* 261 */     area.subtract(area2);
/* 262 */     g2.setPaint(this.backgroundPaint);
/* 263 */     g2.fill(area);
/*     */     
/* 265 */     g2.setStroke(this.stroke);
/* 266 */     g2.setPaint(this.foregroundPaint);
/* 267 */     g2.draw(window);
/* 268 */     g2.draw(e);
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
/* 279 */     if (obj == this) {
/* 280 */       return true;
/*     */     }
/* 282 */     if (!(obj instanceof StandardDialFrame)) {
/* 283 */       return false;
/*     */     }
/* 285 */     StandardDialFrame that = (StandardDialFrame)obj;
/* 286 */     if (!PaintUtilities.equal(this.backgroundPaint, that.backgroundPaint)) {
/* 287 */       return false;
/*     */     }
/* 289 */     if (!PaintUtilities.equal(this.foregroundPaint, that.foregroundPaint)) {
/* 290 */       return false;
/*     */     }
/* 292 */     if (this.radius != that.radius) {
/* 293 */       return false;
/*     */     }
/* 295 */     if (!this.stroke.equals(that.stroke)) {
/* 296 */       return false;
/*     */     }
/* 298 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 307 */     int result = 193;
/* 308 */     long temp = Double.doubleToLongBits(this.radius);
/* 309 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 310 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.backgroundPaint);
/*     */     
/* 312 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.foregroundPaint);
/*     */     
/* 314 */     result = 37 * result + this.stroke.hashCode();
/* 315 */     return result;
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
/* 327 */     return super.clone();
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
/* 338 */     stream.defaultWriteObject();
/* 339 */     SerialUtilities.writePaint(this.backgroundPaint, stream);
/* 340 */     SerialUtilities.writePaint(this.foregroundPaint, stream);
/* 341 */     SerialUtilities.writeStroke(this.stroke, stream);
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
/* 354 */     stream.defaultReadObject();
/* 355 */     this.backgroundPaint = SerialUtilities.readPaint(stream);
/* 356 */     this.foregroundPaint = SerialUtilities.readPaint(stream);
/* 357 */     this.stroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/StandardDialFrame.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */