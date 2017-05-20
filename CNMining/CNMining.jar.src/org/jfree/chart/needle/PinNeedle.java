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
/*     */ public class PinNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -3787089953079863373L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  77 */     GeneralPath pointer = new GeneralPath();
/*     */     
/*  79 */     int minY = (int)plotArea.getMinY();
/*     */     
/*  81 */     int maxY = (int)plotArea.getMaxY();
/*  82 */     int midX = (int)(plotArea.getMinX() + plotArea.getWidth() / 2.0D);
/*     */     
/*  84 */     int lenX = (int)(plotArea.getWidth() / 10.0D);
/*  85 */     if (lenX < 2) {
/*  86 */       lenX = 2;
/*     */     }
/*     */     
/*  89 */     pointer.moveTo(midX - lenX, maxY - lenX);
/*  90 */     pointer.lineTo(midX + lenX, maxY - lenX);
/*  91 */     pointer.lineTo(midX, minY + lenX);
/*  92 */     pointer.closePath();
/*     */     
/*  94 */     lenX = 4 * lenX;
/*  95 */     Ellipse2D circle = new Ellipse2D.Double(midX - lenX / 2, plotArea.getMaxY() - lenX, lenX, lenX);
/*     */     
/*     */ 
/*  98 */     Area shape = new Area(circle);
/*  99 */     shape.add(new Area(pointer));
/* 100 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/* 102 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/* 103 */       shape.transform(getTransform());
/*     */     }
/*     */     
/* 106 */     defaultDisplay(g2, shape);
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
/* 118 */     if (obj == this) {
/* 119 */       return true;
/*     */     }
/* 121 */     if (!(obj instanceof PinNeedle)) {
/* 122 */       return false;
/*     */     }
/* 124 */     if (!super.equals(obj)) {
/* 125 */       return false;
/*     */     }
/* 127 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 136 */     return super.hashCode();
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
/* 148 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/PinNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */