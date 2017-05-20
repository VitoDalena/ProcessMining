/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.io.Serializable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PointerNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4744677345334729606L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  75 */     GeneralPath shape1 = new GeneralPath();
/*  76 */     GeneralPath shape2 = new GeneralPath();
/*  77 */     float minX = (float)plotArea.getMinX();
/*  78 */     float minY = (float)plotArea.getMinY();
/*  79 */     float maxX = (float)plotArea.getMaxX();
/*  80 */     float maxY = (float)plotArea.getMaxY();
/*  81 */     float midX = (float)(minX + plotArea.getWidth() / 2.0D);
/*  82 */     float midY = (float)(minY + plotArea.getHeight() / 2.0D);
/*     */     
/*  84 */     shape1.moveTo(minX, midY);
/*  85 */     shape1.lineTo(midX, minY);
/*  86 */     shape1.lineTo(maxX, midY);
/*  87 */     shape1.closePath();
/*     */     
/*  89 */     shape2.moveTo(minX, midY);
/*  90 */     shape2.lineTo(midX, maxY);
/*  91 */     shape2.lineTo(maxX, midY);
/*  92 */     shape2.closePath();
/*     */     
/*  94 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/*  96 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  97 */       shape1.transform(getTransform());
/*  98 */       shape2.transform(getTransform());
/*     */     }
/*     */     
/* 101 */     if (getFillPaint() != null) {
/* 102 */       g2.setPaint(getFillPaint());
/* 103 */       g2.fill(shape1);
/*     */     }
/*     */     
/* 106 */     if (getHighlightPaint() != null) {
/* 107 */       g2.setPaint(getHighlightPaint());
/* 108 */       g2.fill(shape2);
/*     */     }
/*     */     
/* 111 */     if (getOutlinePaint() != null) {
/* 112 */       g2.setStroke(getOutlineStroke());
/* 113 */       g2.setPaint(getOutlinePaint());
/* 114 */       g2.draw(shape1);
/* 115 */       g2.draw(shape2);
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
/* 127 */     if (obj == this) {
/* 128 */       return true;
/*     */     }
/* 130 */     if (!(obj instanceof PointerNeedle)) {
/* 131 */       return false;
/*     */     }
/* 133 */     if (!super.equals(obj)) {
/* 134 */       return false;
/*     */     }
/* 136 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 145 */     return super.hashCode();
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
/* 157 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/PointerNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */