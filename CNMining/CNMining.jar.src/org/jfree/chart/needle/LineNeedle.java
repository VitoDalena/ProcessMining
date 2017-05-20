/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Line2D.Double;
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
/*     */ public class LineNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 6215321387896748945L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  75 */     Line2D shape = new Line2D.Double();
/*     */     
/*  77 */     double x = plotArea.getMinX() + plotArea.getWidth() / 2.0D;
/*  78 */     shape.setLine(x, plotArea.getMinY(), x, plotArea.getMaxY());
/*     */     
/*  80 */     Shape s = shape;
/*     */     
/*  82 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/*  84 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  85 */       s = getTransform().createTransformedShape(s);
/*     */     }
/*     */     
/*  88 */     defaultDisplay(g2, s);
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
/* 100 */     if (obj == this) {
/* 101 */       return true;
/*     */     }
/* 103 */     if (!(obj instanceof LineNeedle)) {
/* 104 */       return false;
/*     */     }
/* 106 */     return super.equals(obj);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 115 */     return super.hashCode();
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
/* 127 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/LineNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */