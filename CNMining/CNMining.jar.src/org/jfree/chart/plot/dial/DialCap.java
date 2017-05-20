/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DialCap
/*     */   extends AbstractDialLayer
/*     */   implements DialLayer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -2929484264982524463L;
/*     */   private double radius;
/*     */   private transient Paint fillPaint;
/*     */   private transient Paint outlinePaint;
/*     */   private transient Stroke outlineStroke;
/*     */   
/*     */   public DialCap()
/*     */   {
/* 103 */     this.radius = 0.05D;
/* 104 */     this.fillPaint = Color.white;
/* 105 */     this.outlinePaint = Color.black;
/* 106 */     this.outlineStroke = new BasicStroke(2.0F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getRadius()
/*     */   {
/* 118 */     return this.radius;
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
/*     */   public void setRadius(double radius)
/*     */   {
/* 131 */     if (radius <= 0.0D) {
/* 132 */       throw new IllegalArgumentException("Requires radius > 0.0.");
/*     */     }
/* 134 */     this.radius = radius;
/* 135 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getFillPaint()
/*     */   {
/* 146 */     return this.fillPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFillPaint(Paint paint)
/*     */   {
/* 158 */     if (paint == null) {
/* 159 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 161 */     this.fillPaint = paint;
/* 162 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getOutlinePaint()
/*     */   {
/* 173 */     return this.outlinePaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlinePaint(Paint paint)
/*     */   {
/* 185 */     if (paint == null) {
/* 186 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 188 */     this.outlinePaint = paint;
/* 189 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Stroke getOutlineStroke()
/*     */   {
/* 200 */     return this.outlineStroke;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setOutlineStroke(Stroke stroke)
/*     */   {
/* 212 */     if (stroke == null) {
/* 213 */       throw new IllegalArgumentException("Null 'stroke' argument.");
/*     */     }
/* 215 */     this.outlineStroke = stroke;
/* 216 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 226 */     return true;
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
/*     */   public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */   {
/* 242 */     g2.setPaint(this.fillPaint);
/*     */     
/* 244 */     Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, this.radius);
/*     */     
/* 246 */     Ellipse2D e = new Ellipse2D.Double(f.getX(), f.getY(), f.getWidth(), f.getHeight());
/*     */     
/* 248 */     g2.fill(e);
/* 249 */     g2.setPaint(this.outlinePaint);
/* 250 */     g2.setStroke(this.outlineStroke);
/* 251 */     g2.draw(e);
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
/* 263 */     if (obj == this) {
/* 264 */       return true;
/*     */     }
/* 266 */     if (!(obj instanceof DialCap)) {
/* 267 */       return false;
/*     */     }
/* 269 */     DialCap that = (DialCap)obj;
/* 270 */     if (this.radius != that.radius) {
/* 271 */       return false;
/*     */     }
/* 273 */     if (!PaintUtilities.equal(this.fillPaint, that.fillPaint)) {
/* 274 */       return false;
/*     */     }
/* 276 */     if (!PaintUtilities.equal(this.outlinePaint, that.outlinePaint)) {
/* 277 */       return false;
/*     */     }
/* 279 */     if (!this.outlineStroke.equals(that.outlineStroke)) {
/* 280 */       return false;
/*     */     }
/* 282 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 291 */     int result = 193;
/* 292 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.fillPaint);
/* 293 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.outlinePaint);
/*     */     
/* 295 */     result = 37 * result + this.outlineStroke.hashCode();
/* 296 */     return result;
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
/* 308 */     return super.clone();
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
/* 319 */     stream.defaultWriteObject();
/* 320 */     SerialUtilities.writePaint(this.fillPaint, stream);
/* 321 */     SerialUtilities.writePaint(this.outlinePaint, stream);
/* 322 */     SerialUtilities.writeStroke(this.outlineStroke, stream);
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
/* 335 */     stream.defaultReadObject();
/* 336 */     this.fillPaint = SerialUtilities.readPaint(stream);
/* 337 */     this.outlinePaint = SerialUtilities.readPaint(stream);
/* 338 */     this.outlineStroke = SerialUtilities.readStroke(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialCap.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */