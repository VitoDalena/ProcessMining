/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
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
/*     */ public class LongNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -4319985779783688159L;
/*     */   
/*     */   public LongNeedle()
/*     */   {
/*  69 */     setRotateY(0.8D);
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
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  83 */     GeneralPath shape1 = new GeneralPath();
/*  84 */     GeneralPath shape2 = new GeneralPath();
/*  85 */     GeneralPath shape3 = new GeneralPath();
/*     */     
/*  87 */     float minX = (float)plotArea.getMinX();
/*  88 */     float minY = (float)plotArea.getMinY();
/*  89 */     float maxX = (float)plotArea.getMaxX();
/*  90 */     float maxY = (float)plotArea.getMaxY();
/*     */     
/*     */ 
/*  93 */     float midX = (float)(minX + plotArea.getWidth() * 0.5D);
/*  94 */     float midY = (float)(minY + plotArea.getHeight() * 0.8D);
/*  95 */     float y = maxY - 2.0F * (maxY - midY);
/*  96 */     if (y < minY) {
/*  97 */       y = minY;
/*     */     }
/*  99 */     shape1.moveTo(minX, midY);
/* 100 */     shape1.lineTo(midX, minY);
/* 101 */     shape1.lineTo(midX, y);
/* 102 */     shape1.closePath();
/*     */     
/* 104 */     shape2.moveTo(maxX, midY);
/* 105 */     shape2.lineTo(midX, minY);
/* 106 */     shape2.lineTo(midX, y);
/* 107 */     shape2.closePath();
/*     */     
/* 109 */     shape3.moveTo(minX, midY);
/* 110 */     shape3.lineTo(midX, maxY);
/* 111 */     shape3.lineTo(maxX, midY);
/* 112 */     shape3.lineTo(midX, y);
/* 113 */     shape3.closePath();
/*     */     
/* 115 */     Shape s1 = shape1;
/* 116 */     Shape s2 = shape2;
/* 117 */     Shape s3 = shape3;
/*     */     
/* 119 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/* 121 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 122 */       s1 = shape1.createTransformedShape(transform);
/* 123 */       s2 = shape2.createTransformedShape(transform);
/* 124 */       s3 = shape3.createTransformedShape(transform);
/*     */     }
/*     */     
/*     */ 
/* 128 */     if (getHighlightPaint() != null) {
/* 129 */       g2.setPaint(getHighlightPaint());
/* 130 */       g2.fill(s3);
/*     */     }
/*     */     
/* 133 */     if (getFillPaint() != null) {
/* 134 */       g2.setPaint(getFillPaint());
/* 135 */       g2.fill(s1);
/* 136 */       g2.fill(s2);
/*     */     }
/*     */     
/*     */ 
/* 140 */     if (getOutlinePaint() != null) {
/* 141 */       g2.setStroke(getOutlineStroke());
/* 142 */       g2.setPaint(getOutlinePaint());
/* 143 */       g2.draw(s1);
/* 144 */       g2.draw(s2);
/* 145 */       g2.draw(s3);
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
/* 157 */     if (obj == this) {
/* 158 */       return true;
/*     */     }
/* 160 */     if (!(obj instanceof LongNeedle)) {
/* 161 */       return false;
/*     */     }
/* 163 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 172 */     return super.hashCode();
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
/* 184 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/LongNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */