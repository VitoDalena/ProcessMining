/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.text.TextUtilities;
/*     */ import org.jfree.ui.TextAnchor;
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
/*     */ public class DialTextAnnotation
/*     */   extends AbstractDialLayer
/*     */   implements DialLayer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = 3065267524054428071L;
/*     */   private String label;
/*     */   private Font font;
/*     */   private transient Paint paint;
/*     */   private double angle;
/*     */   private double radius;
/*     */   private TextAnchor anchor;
/*     */   
/*     */   public DialTextAnnotation(String label)
/*     */   {
/* 103 */     if (label == null) {
/* 104 */       throw new IllegalArgumentException("Null 'label' argument.");
/*     */     }
/* 106 */     this.angle = -90.0D;
/* 107 */     this.radius = 0.3D;
/* 108 */     this.font = new Font("Dialog", 1, 14);
/* 109 */     this.paint = Color.black;
/* 110 */     this.label = label;
/* 111 */     this.anchor = TextAnchor.TOP_CENTER;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String getLabel()
/*     */   {
/* 122 */     return this.label;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setLabel(String label)
/*     */   {
/* 134 */     if (label == null) {
/* 135 */       throw new IllegalArgumentException("Null 'label' argument.");
/*     */     }
/* 137 */     this.label = label;
/* 138 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Font getFont()
/*     */   {
/* 149 */     return this.font;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setFont(Font font)
/*     */   {
/* 161 */     if (font == null) {
/* 162 */       throw new IllegalArgumentException("Null 'font' argument.");
/*     */     }
/* 164 */     this.font = font;
/* 165 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getPaint()
/*     */   {
/* 176 */     return this.paint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setPaint(Paint paint)
/*     */   {
/* 188 */     if (paint == null) {
/* 189 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 191 */     this.paint = paint;
/* 192 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getAngle()
/*     */   {
/* 204 */     return this.angle;
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
/*     */   public void setAngle(double angle)
/*     */   {
/* 217 */     this.angle = angle;
/* 218 */     notifyListeners(new DialLayerChangeEvent(this));
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
/*     */   public double getRadius()
/*     */   {
/* 231 */     return this.radius;
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
/*     */   public void setRadius(double radius)
/*     */   {
/* 245 */     if (radius < 0.0D) {
/* 246 */       throw new IllegalArgumentException("The 'radius' cannot be negative.");
/*     */     }
/*     */     
/* 249 */     this.radius = radius;
/* 250 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public TextAnchor getAnchor()
/*     */   {
/* 262 */     return this.anchor;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setAnchor(TextAnchor anchor)
/*     */   {
/* 274 */     if (anchor == null) {
/* 275 */       throw new IllegalArgumentException("Null 'anchor' argument.");
/*     */     }
/* 277 */     this.anchor = anchor;
/* 278 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 288 */     return true;
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
/*     */   public void draw(Graphics2D g2, DialPlot plot, Rectangle2D frame, Rectangle2D view)
/*     */   {
/* 305 */     Rectangle2D f = DialPlot.rectangleByRadius(frame, this.radius, this.radius);
/*     */     
/* 307 */     Arc2D arc = new Arc2D.Double(f, this.angle, 0.0D, 0);
/* 308 */     Point2D pt = arc.getStartPoint();
/* 309 */     g2.setPaint(this.paint);
/* 310 */     g2.setFont(this.font);
/* 311 */     TextUtilities.drawAlignedString(this.label, g2, (float)pt.getX(), (float)pt.getY(), this.anchor);
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
/* 324 */     if (obj == this) {
/* 325 */       return true;
/*     */     }
/* 327 */     if (!(obj instanceof DialTextAnnotation)) {
/* 328 */       return false;
/*     */     }
/* 330 */     DialTextAnnotation that = (DialTextAnnotation)obj;
/* 331 */     if (!this.label.equals(that.label)) {
/* 332 */       return false;
/*     */     }
/* 334 */     if (!this.font.equals(that.font)) {
/* 335 */       return false;
/*     */     }
/* 337 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 338 */       return false;
/*     */     }
/* 340 */     if (this.radius != that.radius) {
/* 341 */       return false;
/*     */     }
/* 343 */     if (this.angle != that.angle) {
/* 344 */       return false;
/*     */     }
/* 346 */     if (!this.anchor.equals(that.anchor)) {
/* 347 */       return false;
/*     */     }
/* 349 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 358 */     int result = 193;
/* 359 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 360 */     result = 37 * result + this.font.hashCode();
/* 361 */     result = 37 * result + this.label.hashCode();
/* 362 */     result = 37 * result + this.anchor.hashCode();
/* 363 */     long temp = Double.doubleToLongBits(this.angle);
/* 364 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 365 */     temp = Double.doubleToLongBits(this.radius);
/* 366 */     result = 37 * result + (int)(temp ^ temp >>> 32);
/* 367 */     return result;
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
/* 379 */     return super.clone();
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
/* 390 */     stream.defaultWriteObject();
/* 391 */     SerialUtilities.writePaint(this.paint, stream);
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
/* 404 */     stream.defaultReadObject();
/* 405 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialTextAnnotation.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */