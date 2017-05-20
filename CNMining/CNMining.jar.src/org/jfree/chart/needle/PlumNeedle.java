/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D;
/*     */ import java.awt.geom.Arc2D.Double;
/*     */ import java.awt.geom.Area;
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
/*     */ public class PlumNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3082660488660600718L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  75 */     Arc2D shape = new Arc2D.Double(2);
/*  76 */     double radius = plotArea.getHeight();
/*  77 */     double halfX = plotArea.getWidth() / 2.0D;
/*  78 */     double diameter = 2.0D * radius;
/*     */     
/*  80 */     shape.setFrame(plotArea.getMinX() + halfX - radius, plotArea.getMinY() - radius, diameter, diameter);
/*     */     
/*     */ 
/*  83 */     radius = Math.toDegrees(Math.asin(halfX / radius));
/*  84 */     shape.setAngleStart(270.0D - radius);
/*  85 */     shape.setAngleExtent(2.0D * radius);
/*     */     
/*  87 */     Area s = new Area(shape);
/*     */     
/*  89 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/*  91 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  92 */       s.transform(getTransform());
/*     */     }
/*     */     
/*  95 */     defaultDisplay(g2, s);
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
/* 106 */     if (obj == this) {
/* 107 */       return true;
/*     */     }
/* 109 */     if (!(obj instanceof PlumNeedle)) {
/* 110 */       return false;
/*     */     }
/* 112 */     if (!super.equals(obj)) {
/* 113 */       return false;
/*     */     }
/* 115 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 124 */     return super.hashCode();
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
/* 136 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/PlumNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */