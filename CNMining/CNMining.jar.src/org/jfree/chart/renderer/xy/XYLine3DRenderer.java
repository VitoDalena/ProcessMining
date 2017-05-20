/*     */ package org.jfree.chart.renderer.xy;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.ObjectOutputStream;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.chart.Effect3D;
/*     */ import org.jfree.io.SerialUtilities;
/*     */ import org.jfree.util.PaintUtilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XYLine3DRenderer
/*     */   extends XYLineAndShapeRenderer
/*     */   implements Effect3D, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 588933208243446087L;
/*     */   public static final double DEFAULT_X_OFFSET = 12.0D;
/*     */   public static final double DEFAULT_Y_OFFSET = 8.0D;
/*  75 */   public static final Paint DEFAULT_WALL_PAINT = new Color(221, 221, 221);
/*     */   
/*     */ 
/*     */   private double xOffset;
/*     */   
/*     */ 
/*     */   private double yOffset;
/*     */   
/*     */ 
/*     */   private transient Paint wallPaint;
/*     */   
/*     */ 
/*     */ 
/*     */   public XYLine3DRenderer()
/*     */   {
/*  90 */     this.wallPaint = DEFAULT_WALL_PAINT;
/*  91 */     this.xOffset = 12.0D;
/*  92 */     this.yOffset = 8.0D;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getXOffset()
/*     */   {
/* 101 */     return this.xOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public double getYOffset()
/*     */   {
/* 110 */     return this.yOffset;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setXOffset(double xOffset)
/*     */   {
/* 120 */     this.xOffset = xOffset;
/* 121 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setYOffset(double yOffset)
/*     */   {
/* 131 */     this.yOffset = yOffset;
/* 132 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Paint getWallPaint()
/*     */   {
/* 142 */     return this.wallPaint;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setWallPaint(Paint paint)
/*     */   {
/* 153 */     this.wallPaint = paint;
/* 154 */     fireChangeEvent();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getPassCount()
/*     */   {
/* 165 */     return 3;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isLinePass(int pass)
/*     */   {
/* 176 */     return (pass == 0) || (pass == 1);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isItemPass(int pass)
/*     */   {
/* 187 */     return pass == 2;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected boolean isShadowPass(int pass)
/*     */   {
/* 198 */     return pass == 0;
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
/*     */   protected void drawFirstPassShape(Graphics2D g2, int pass, int series, int item, Shape shape)
/*     */   {
/* 215 */     if (isShadowPass(pass)) {
/* 216 */       if (getWallPaint() != null) {
/* 217 */         g2.setStroke(getItemStroke(series, item));
/* 218 */         g2.setPaint(getWallPaint());
/* 219 */         g2.translate(getXOffset(), getYOffset());
/* 220 */         g2.draw(shape);
/* 221 */         g2.translate(-getXOffset(), -getYOffset());
/*     */       }
/*     */       
/*     */     }
/*     */     else {
/* 226 */       super.drawFirstPassShape(g2, pass, series, item, shape);
/*     */     }
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
/* 238 */     if (obj == this) {
/* 239 */       return true;
/*     */     }
/* 241 */     if (!(obj instanceof XYLine3DRenderer)) {
/* 242 */       return false;
/*     */     }
/* 244 */     XYLine3DRenderer that = (XYLine3DRenderer)obj;
/* 245 */     if (this.xOffset != that.xOffset) {
/* 246 */       return false;
/*     */     }
/* 248 */     if (this.yOffset != that.yOffset) {
/* 249 */       return false;
/*     */     }
/* 251 */     if (!PaintUtilities.equal(this.wallPaint, that.wallPaint)) {
/* 252 */       return false;
/*     */     }
/* 254 */     return super.equals(obj);
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
/* 268 */     this.wallPaint = SerialUtilities.readPaint(stream);
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
/* 279 */     stream.defaultWriteObject();
/* 280 */     SerialUtilities.writePaint(this.wallPaint, stream);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/XYLine3DRenderer.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */