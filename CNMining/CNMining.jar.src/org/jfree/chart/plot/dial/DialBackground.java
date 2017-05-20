/*     */ package org.jfree.chart.plot.dial;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.HashUtilities;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.StandardGradientPaintTransformer;
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
/*     */ public class DialBackground
/*     */   extends AbstractDialLayer
/*     */   implements DialLayer, Cloneable, PublicCloneable, Serializable
/*     */ {
/*     */   static final long serialVersionUID = -9019069533317612375L;
/*     */   private transient Paint paint;
/*     */   private GradientPaintTransformer gradientPaintTransformer;
/*     */   
/*     */   public DialBackground()
/*     */   {
/*  89 */     this(Color.white);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DialBackground(Paint paint)
/*     */   {
/* 101 */     if (paint == null) {
/* 102 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 104 */     this.paint = paint;
/* 105 */     this.gradientPaintTransformer = new StandardGradientPaintTransformer();
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
/* 116 */     return this.paint;
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
/* 128 */     if (paint == null) {
/* 129 */       throw new IllegalArgumentException("Null 'paint' argument.");
/*     */     }
/* 131 */     this.paint = paint;
/* 132 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GradientPaintTransformer getGradientPaintTransformer()
/*     */   {
/* 144 */     return this.gradientPaintTransformer;
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
/*     */   public void setGradientPaintTransformer(GradientPaintTransformer t)
/*     */   {
/* 157 */     if (t == null) {
/* 158 */       throw new IllegalArgumentException("Null 't' argument.");
/*     */     }
/* 160 */     this.gradientPaintTransformer = t;
/* 161 */     notifyListeners(new DialLayerChangeEvent(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean isClippedToWindow()
/*     */   {
/* 171 */     return true;
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
/* 187 */     Paint p = this.paint;
/* 188 */     if ((p instanceof GradientPaint)) {
/* 189 */       p = this.gradientPaintTransformer.transform((GradientPaint)p, view);
/*     */     }
/*     */     
/* 192 */     g2.setPaint(p);
/* 193 */     g2.fill(view);
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
/* 204 */     if (obj == this) {
/* 205 */       return true;
/*     */     }
/* 207 */     if (!(obj instanceof DialBackground)) {
/* 208 */       return false;
/*     */     }
/* 210 */     DialBackground that = (DialBackground)obj;
/* 211 */     if (!PaintUtilities.equal(this.paint, that.paint)) {
/* 212 */       return false;
/*     */     }
/* 214 */     if (!this.gradientPaintTransformer.equals(that.gradientPaintTransformer))
/*     */     {
/* 216 */       return false;
/*     */     }
/* 218 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 227 */     int result = 193;
/* 228 */     result = 37 * result + HashUtilities.hashCodeForPaint(this.paint);
/* 229 */     result = 37 * result + this.gradientPaintTransformer.hashCode();
/* 230 */     return result;
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
/* 242 */     return super.clone();
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
/* 253 */     stream.defaultWriteObject();
/* 254 */     SerialUtilities.writePaint(this.paint, stream);
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
/* 267 */     stream.defaultReadObject();
/* 268 */     this.paint = SerialUtilities.readPaint(stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/plot/dial/DialBackground.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */