/*     */ package org.jfree.chart.renderer.xy;
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
/*     */ public class StandardXYBarPainter
/*     */   implements XYBarPainter, Serializable
/*     */ {
/*     */   public void paintBar(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base)
/*     */   {
/*  86 */     Paint itemPaint = renderer.getItemPaint(row, column);
/*  87 */     GradientPaintTransformer t = renderer.getGradientPaintTransformer();
/*  88 */     if ((t != null) && ((itemPaint instanceof GradientPaint))) {
/*  89 */       itemPaint = t.transform((GradientPaint)itemPaint, bar);
/*     */     }
/*  91 */     g2.setPaint(itemPaint);
/*  92 */     g2.fill(bar);
/*     */     
/*     */ 
/*  95 */     if (renderer.isDrawBarOutline())
/*     */     {
/*  97 */       Stroke stroke = renderer.getItemOutlineStroke(row, column);
/*  98 */       Paint paint = renderer.getItemOutlinePaint(row, column);
/*  99 */       if ((stroke != null) && (paint != null)) {
/* 100 */         g2.setStroke(stroke);
/* 101 */         g2.setPaint(paint);
/* 102 */         g2.draw(bar);
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
/*     */   public void paintBarShadow(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base, boolean pegShadow)
/*     */   {
/* 126 */     Paint itemPaint = renderer.getItemPaint(row, column);
/* 127 */     if ((itemPaint instanceof Color)) {
/* 128 */       Color c = (Color)itemPaint;
/* 129 */       if (c.getAlpha() == 0) {
/* 130 */         return;
/*     */       }
/*     */     }
/*     */     
/* 134 */     RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(), renderer.getShadowYOffset(), base, pegShadow);
/*     */     
/* 136 */     g2.setPaint(Color.gray);
/* 137 */     g2.fill(shadow);
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
/* 154 */     double x0 = bar.getMinX();
/* 155 */     double x1 = bar.getMaxX();
/* 156 */     double y0 = bar.getMinY();
/* 157 */     double y1 = bar.getMaxY();
/* 158 */     if (base == RectangleEdge.TOP) {
/* 159 */       x0 += xOffset;
/* 160 */       x1 += xOffset;
/* 161 */       if (!pegShadow) {
/* 162 */         y0 += yOffset;
/*     */       }
/* 164 */       y1 += yOffset;
/*     */     }
/* 166 */     else if (base == RectangleEdge.BOTTOM) {
/* 167 */       x0 += xOffset;
/* 168 */       x1 += xOffset;
/* 169 */       y0 += yOffset;
/* 170 */       if (!pegShadow) {
/* 171 */         y1 += yOffset;
/*     */       }
/*     */     }
/* 174 */     else if (base == RectangleEdge.LEFT) {
/* 175 */       if (!pegShadow) {
/* 176 */         x0 += xOffset;
/*     */       }
/* 178 */       x1 += xOffset;
/* 179 */       y0 += yOffset;
/* 180 */       y1 += yOffset;
/*     */     }
/* 182 */     else if (base == RectangleEdge.RIGHT) {
/* 183 */       x0 += xOffset;
/* 184 */       if (!pegShadow) {
/* 185 */         x1 += xOffset;
/*     */       }
/* 187 */       y0 += yOffset;
/* 188 */       y1 += yOffset;
/*     */     }
/* 190 */     return new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
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
/* 201 */     if (obj == this) {
/* 202 */       return true;
/*     */     }
/* 204 */     if (!(obj instanceof StandardXYBarPainter)) {
/* 205 */       return false;
/*     */     }
/* 207 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 216 */     int hash = 37;
/*     */     
/* 218 */     return hash;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/StandardXYBarPainter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */