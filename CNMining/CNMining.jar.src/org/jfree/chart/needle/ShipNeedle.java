/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Arc2D.Double;
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
/*     */ public class ShipNeedle
/*     */   extends MeterNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = 149554868169435612L;
/*     */   
/*     */   protected void drawNeedle(Graphics2D g2, Rectangle2D plotArea, Point2D rotate, double angle)
/*     */   {
/*  76 */     GeneralPath shape = new GeneralPath();
/*  77 */     shape.append(new Arc2D.Double(-9.0D, -7.0D, 10.0D, 14.0D, 0.0D, 25.5D, 0), true);
/*     */     
/*  79 */     shape.append(new Arc2D.Double(0.0D, -7.0D, 10.0D, 14.0D, 154.5D, 25.5D, 0), true);
/*     */     
/*  81 */     shape.closePath();
/*  82 */     getTransform().setToTranslation(plotArea.getMinX(), plotArea.getMaxY());
/*  83 */     getTransform().scale(plotArea.getWidth(), plotArea.getHeight() / 3.0D);
/*  84 */     shape.transform(getTransform());
/*     */     
/*  86 */     if ((rotate != null) && (angle != 0.0D))
/*     */     {
/*  88 */       getTransform().setToRotation(angle, rotate.getX(), rotate.getY());
/*  89 */       shape.transform(getTransform());
/*     */     }
/*     */     
/*  92 */     defaultDisplay(g2, shape);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public boolean equals(Object object)
/*     */   {
/* 103 */     if (object == null) {
/* 104 */       return false;
/*     */     }
/* 106 */     if (object == this) {
/* 107 */       return true;
/*     */     }
/* 109 */     if ((super.equals(object)) && ((object instanceof ShipNeedle))) {
/* 110 */       return true;
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 121 */     return super.hashCode();
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
/* 133 */     return super.clone();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/ShipNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */