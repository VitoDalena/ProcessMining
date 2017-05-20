/*     */ package org.jfree.chart.renderer.category;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.geom.RectangularShape;
/*     */ import java.io.Serializable;
/*     */ import org.jfree.ui.GradientPaintTransformer;
/*     */ import org.jfree.ui.RectangleEdge;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardBarPainter
/*     */   implements BarPainter, Serializable
/*     */ {
/*     */   public void paintBar(Graphics2D g2, BarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base)
/*     */   {
/*  87 */     Paint itemPaint = renderer.getItemPaint(row, column);
/*  88 */     GradientPaintTransformer t = renderer.getGradientPaintTransformer();
/*  89 */     if ((t != null) && ((itemPaint instanceof GradientPaint))) {
/*  90 */       itemPaint = t.transform((GradientPaint)itemPaint, bar);
/*     */     }
/*  92 */     g2.setPaint(itemPaint);
/*  93 */     g2.fill(bar);
/*     */     
/*     */ 
/*  96 */     if (renderer.isDrawBarOutline())
/*     */     {
/*  98 */       Stroke stroke = renderer.getItemOutlineStroke(row, column);
/*  99 */       Paint paint = renderer.getItemOutlinePaint(row, column);
/* 100 */       if ((stroke != null) && (paint != null)) {
/* 101 */         g2.setStroke(stroke);
/* 102 */         g2.setPaint(paint);
/* 103 */         g2.draw(bar);
/*     */       }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paintBarShadow(Graphics2D g2, BarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base, boolean pegShadow)
/*     */   {
/* 127 */     Paint itemPaint = renderer.getItemPaint(row, column);
/* 128 */     if ((itemPaint instanceof Color)) {
/* 129 */       Color c = (Color)itemPaint;
/* 130 */       if (c.getAlpha() == 0) {
/* 131 */         return;
/*     */       }
/*     */     }
/*     */     
/* 135 */     RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(), renderer.getShadowYOffset(), base, pegShadow);
/*     */     
/* 137 */     g2.setPaint(renderer.getShadowPaint());
/* 138 */     g2.fill(shadow);
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
/*     */   private Rectangle2D createShadow(RectangularShape bar, double xOffset, double yOffset, RectangleEdge base, boolean pegShadow)
/*     */   {
/* 155 */     double x0 = bar.getMinX();
/* 156 */     double x1 = bar.getMaxX();
/* 157 */     double y0 = bar.getMinY();
/* 158 */     double y1 = bar.getMaxY();
/* 159 */     if (base == RectangleEdge.TOP) {
/* 160 */       x0 += xOffset;
/* 161 */       x1 += xOffset;
/* 162 */       if (!pegShadow) {
/* 163 */         y0 += yOffset;
/*     */       }
/* 165 */       y1 += yOffset;
/*     */     }
/* 167 */     else if (base == RectangleEdge.BOTTOM) {
/* 168 */       x0 += xOffset;
/* 169 */       x1 += xOffset;
/* 170 */       y0 += yOffset;
/* 171 */       if (!pegShadow) {
/* 172 */         y1 += yOffset;
/*     */       }
/*     */     }
/* 175 */     else if (base == RectangleEdge.LEFT) {
/* 176 */       if (!pegShadow) {
/* 177 */         x0 += xOffset;
/*     */       }
/* 179 */       x1 += xOffset;
/* 180 */       y0 += yOffset;
/* 181 */       y1 += yOffset;
/*     */     }
/* 183 */     else if (base == RectangleEdge.RIGHT) {
/* 184 */       x0 += xOffset;
/* 185 */       if (!pegShadow) {
/* 186 */         x1 += xOffset;
/*     */       }
/* 188 */       y0 += yOffset;
/* 189 */       y1 += yOffset;
/*     */     }
/* 191 */     return new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
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
/* 202 */     if (obj == this) {
/* 203 */       return true;
/*     */     }
/* 205 */     if (!(obj instanceof StandardBarPainter)) {
/* 206 */       return false;
/*     */     }
/* 208 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 217 */     int hash = 37;
/*     */     
/* 219 */     return hash;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/category/StandardBarPainter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */