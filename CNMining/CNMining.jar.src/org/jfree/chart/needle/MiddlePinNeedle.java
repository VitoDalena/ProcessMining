/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
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
/*     */ public class MiddlePinNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6237073996403125310L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  77 */     GeneralPath pointer = new GeneralPath();
/*     */     
/*  79 */     int minY = (int)plotArea.getMinY();
/*     */     
/*  81 */     int maxY = (int)plotArea.getMaxY();
/*  82 */     int midY = (maxY - minY) / 2 + minY;
/*     */     
/*  84 */     int midX = (int)(plotArea.getMinX() + plotArea.getWidth() / 2.0D);
/*     */     
/*  86 */     int lenX = (int)(plotArea.getWidth() / 10.0D);
/*  87 */     if (lenX < 2) {
/*  88 */       lenX = 2;
/*     */     }
/*     */     
/*  91 */     pointer.moveTo(midX - lenX, midY - lenX);
/*  92 */     pointer.lineTo(midX + lenX, midY - lenX);
/*  93 */     pointer.lineTo(midX, minY);
/*  94 */     pointer.closePath();
/*     */     
/*  96 */     lenX = 4 * lenX;
/*  97 */     Ellipse2D circle = new Ellipse2D.Double(midX - lenX / 2, midY - lenX, lenX, lenX);
/*     */     
/*     */ 
/* 100 */     Area shape = new Area(circle);
/* 101 */     shape.add(new Area(pointer));
/* 102 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/* 104 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 105 */       shape.transform(getTransform());
/*     */     }
/*     */     
/* 108 */     defaultDisplay(g2, shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 120 */     if (object == null) {
/* 121 */       return false;
/*     */     }
/* 123 */     if (object == this) {
/* 124 */       return true;
/*     */     }
/* 126 */     if ((super.equals(object)) && ((object instanceof MiddlePinNeedle))) {
/* 127 */       return true;
/*     */     }
/* 129 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 138 */     return super.hashCode();
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
/* 150 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/MiddlePinNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */