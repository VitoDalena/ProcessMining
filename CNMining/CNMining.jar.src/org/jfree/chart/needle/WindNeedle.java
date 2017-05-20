/*     */ package org.jfree.chart.needle;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
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
/*     */ public class WindNeedle
/*     */   extends ArrowNeedle
/*     */   implements Cloneable, Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -2861061368907167834L;
/*     */   
/*     */   public WindNeedle()
/*     */   {
/*  65 */     super(false);
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
/*  79 */     super.drawNeedle(g2, plotArea, rotate, angle);
/*  80 */     if ((rotate != null) && (plotArea != null))
/*     */     {
/*  82 */       int spacing = getSize() * 3;
/*  83 */       Rectangle2D newArea = new Rectangle2D.Double();
/*     */       
/*  85 */       Point2D newRotate = rotate;
/*  86 */       newArea.setRect(plotArea.getMinX() - spacing, plotArea.getMinY(), plotArea.getWidth(), plotArea.getHeight());
/*     */       
/*  88 */       super.drawNeedle(g2, newArea, newRotate, angle);
/*     */       
/*  90 */       newArea.setRect(plotArea.getMinX() + spacing, plotArea.getMinY(), plotArea.getWidth(), plotArea.getHeight());
/*     */       
/*     */ 
/*  93 */       super.drawNeedle(g2, newArea, newRotate, angle);
/*     */     }
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
/* 106 */     if (object == null) {
/* 107 */       return false;
/*     */     }
/* 109 */     if (object == this) {
/* 110 */       return true;
/*     */     }
/* 112 */     if ((super.equals(object)) && ((object instanceof WindNeedle))) {
/* 113 */       return true;
/*     */     }
/* 115 */     return false;
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
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/needle/WindNeedle.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */