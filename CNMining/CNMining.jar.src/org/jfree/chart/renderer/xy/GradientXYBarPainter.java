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
/*     */ import org.jfree.chart.HashUtilities;
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
/*     */ public class GradientXYBarPainter
/*     */   implements XYBarPainter, Serializable
/*     */ {
/*     */   private double g1;
/*     */   private double g2;
/*     */   private double g3;
/*     */   
/*     */   public GradientXYBarPainter()
/*     */   {
/*  77 */     this(0.1D, 0.2D, 0.8D);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public GradientXYBarPainter(double g1, double g2, double g3)
/*     */   {
/*  88 */     this.g1 = g1;
/*  89 */     this.g2 = g2;
/*  90 */     this.g3 = g3;
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
/*     */   public void paintBar(Graphics2D g2, XYBarRenderer renderer, int row, int column, RectangularShape bar, RectangleEdge base)
/*     */   {
/* 107 */     Paint itemPaint = renderer.getItemPaint(row, column);
/*     */     Color c1;
/*     */     Color c0;
/* 110 */     Color c1; if ((itemPaint instanceof Color)) {
/* 111 */       Color c0 = (Color)itemPaint;
/* 112 */       c1 = c0.brighter();
/*     */     } else { Color c1;
/* 114 */       if ((itemPaint instanceof GradientPaint)) {
/* 115 */         GradientPaint gp = (GradientPaint)itemPaint;
/* 116 */         Color c0 = gp.getColor1();
/* 117 */         c1 = gp.getColor2();
/*     */       }
/*     */       else {
/* 120 */         c0 = Color.blue;
/* 121 */         c1 = Color.blue.brighter();
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 126 */     if (c0.getAlpha() == 0) {
/* 127 */       return;
/*     */     }
/*     */     
/* 130 */     if ((base == RectangleEdge.TOP) || (base == RectangleEdge.BOTTOM)) {
/* 131 */       Rectangle2D[] regions = splitVerticalBar(bar, this.g1, this.g2, this.g3);
/*     */       
/* 133 */       GradientPaint gp = new GradientPaint((float)regions[0].getMinX(), 0.0F, c0, (float)regions[0].getMaxX(), 0.0F, Color.white);
/*     */       
/* 135 */       g2.setPaint(gp);
/* 136 */       g2.fill(regions[0]);
/*     */       
/* 138 */       gp = new GradientPaint((float)regions[1].getMinX(), 0.0F, Color.white, (float)regions[1].getMaxX(), 0.0F, c0);
/*     */       
/* 140 */       g2.setPaint(gp);
/* 141 */       g2.fill(regions[1]);
/*     */       
/* 143 */       gp = new GradientPaint((float)regions[2].getMinX(), 0.0F, c0, (float)regions[2].getMaxX(), 0.0F, c1);
/*     */       
/* 145 */       g2.setPaint(gp);
/* 146 */       g2.fill(regions[2]);
/*     */       
/* 148 */       gp = new GradientPaint((float)regions[3].getMinX(), 0.0F, c1, (float)regions[3].getMaxX(), 0.0F, c0);
/*     */       
/* 150 */       g2.setPaint(gp);
/* 151 */       g2.fill(regions[3]);
/*     */     }
/* 153 */     else if ((base == RectangleEdge.LEFT) || (base == RectangleEdge.RIGHT)) {
/* 154 */       Rectangle2D[] regions = splitHorizontalBar(bar, this.g1, this.g2, this.g3);
/*     */       
/* 156 */       GradientPaint gp = new GradientPaint(0.0F, (float)regions[0].getMinY(), c0, 0.0F, (float)regions[0].getMaxX(), Color.white);
/*     */       
/*     */ 
/* 159 */       g2.setPaint(gp);
/* 160 */       g2.fill(regions[0]);
/*     */       
/* 162 */       gp = new GradientPaint(0.0F, (float)regions[1].getMinY(), Color.white, 0.0F, (float)regions[1].getMaxY(), c0);
/*     */       
/* 164 */       g2.setPaint(gp);
/* 165 */       g2.fill(regions[1]);
/*     */       
/* 167 */       gp = new GradientPaint(0.0F, (float)regions[2].getMinY(), c0, 0.0F, (float)regions[2].getMaxY(), c1);
/*     */       
/* 169 */       g2.setPaint(gp);
/* 170 */       g2.fill(regions[2]);
/*     */       
/* 172 */       gp = new GradientPaint(0.0F, (float)regions[3].getMinY(), c1, 0.0F, (float)regions[3].getMaxY(), c0);
/*     */       
/* 174 */       g2.setPaint(gp);
/* 175 */       g2.fill(regions[3]);
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 180 */     if (renderer.isDrawBarOutline()) {
/* 181 */       Stroke stroke = renderer.getItemOutlineStroke(row, column);
/* 182 */       Paint paint = renderer.getItemOutlinePaint(row, column);
/* 183 */       if ((stroke != null) && (paint != null)) {
/* 184 */         g2.setStroke(stroke);
/* 185 */         g2.setPaint(paint);
/* 186 */         g2.draw(bar);
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
/* 210 */     Paint itemPaint = renderer.getItemPaint(row, column);
/* 211 */     if ((itemPaint instanceof Color)) {
/* 212 */       Color c = (Color)itemPaint;
/* 213 */       if (c.getAlpha() == 0) {
/* 214 */         return;
/*     */       }
/*     */     }
/*     */     
/* 218 */     RectangularShape shadow = createShadow(bar, renderer.getShadowXOffset(), renderer.getShadowYOffset(), base, pegShadow);
/*     */     
/* 220 */     g2.setPaint(Color.gray);
/* 221 */     g2.fill(shadow);
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
/* 238 */     double x0 = bar.getMinX();
/* 239 */     double x1 = bar.getMaxX();
/* 240 */     double y0 = bar.getMinY();
/* 241 */     double y1 = bar.getMaxY();
/* 242 */     if (base == RectangleEdge.TOP) {
/* 243 */       x0 += xOffset;
/* 244 */       x1 += xOffset;
/* 245 */       if (!pegShadow) {
/* 246 */         y0 += yOffset;
/*     */       }
/* 248 */       y1 += yOffset;
/*     */     }
/* 250 */     else if (base == RectangleEdge.BOTTOM) {
/* 251 */       x0 += xOffset;
/* 252 */       x1 += xOffset;
/* 253 */       y0 += yOffset;
/* 254 */       if (!pegShadow) {
/* 255 */         y1 += yOffset;
/*     */       }
/*     */     }
/* 258 */     else if (base == RectangleEdge.LEFT) {
/* 259 */       if (!pegShadow) {
/* 260 */         x0 += xOffset;
/*     */       }
/* 262 */       x1 += xOffset;
/* 263 */       y0 += yOffset;
/* 264 */       y1 += yOffset;
/*     */     }
/* 266 */     else if (base == RectangleEdge.RIGHT) {
/* 267 */       x0 += xOffset;
/* 268 */       if (!pegShadow) {
/* 269 */         x1 += xOffset;
/*     */       }
/* 271 */       y0 += yOffset;
/* 272 */       y1 += yOffset;
/*     */     }
/* 274 */     return new Rectangle2D.Double(x0, y0, x1 - x0, y1 - y0);
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
/*     */   private Rectangle2D[] splitVerticalBar(RectangularShape bar, double a, double b, double c)
/*     */   {
/* 290 */     Rectangle2D[] result = new Rectangle2D[4];
/* 291 */     double x0 = bar.getMinX();
/* 292 */     double x1 = Math.rint(x0 + bar.getWidth() * a);
/* 293 */     double x2 = Math.rint(x0 + bar.getWidth() * b);
/* 294 */     double x3 = Math.rint(x0 + bar.getWidth() * c);
/* 295 */     result[0] = new Rectangle2D.Double(bar.getMinX(), bar.getMinY(), x1 - x0, bar.getHeight());
/*     */     
/* 297 */     result[1] = new Rectangle2D.Double(x1, bar.getMinY(), x2 - x1, bar.getHeight());
/*     */     
/* 299 */     result[2] = new Rectangle2D.Double(x2, bar.getMinY(), x3 - x2, bar.getHeight());
/*     */     
/* 301 */     result[3] = new Rectangle2D.Double(x3, bar.getMinY(), bar.getMaxX() - x3, bar.getHeight());
/*     */     
/* 303 */     return result;
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
/*     */   private Rectangle2D[] splitHorizontalBar(RectangularShape bar, double a, double b, double c)
/*     */   {
/* 319 */     Rectangle2D[] result = new Rectangle2D[4];
/* 320 */     double y0 = bar.getMinY();
/* 321 */     double y1 = Math.rint(y0 + bar.getHeight() * a);
/* 322 */     double y2 = Math.rint(y0 + bar.getHeight() * b);
/* 323 */     double y3 = Math.rint(y0 + bar.getHeight() * c);
/* 324 */     result[0] = new Rectangle2D.Double(bar.getMinX(), bar.getMinY(), bar.getWidth(), y1 - y0);
/*     */     
/* 326 */     result[1] = new Rectangle2D.Double(bar.getMinX(), y1, bar.getWidth(), y2 - y1);
/*     */     
/* 328 */     result[2] = new Rectangle2D.Double(bar.getMinX(), y2, bar.getWidth(), y3 - y2);
/*     */     
/* 330 */     result[3] = new Rectangle2D.Double(bar.getMinX(), y3, bar.getWidth(), bar.getMaxY() - y3);
/*     */     
/* 332 */     return result;
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
/* 343 */     if (obj == this) {
/* 344 */       return true;
/*     */     }
/* 346 */     if (!(obj instanceof GradientXYBarPainter)) {
/* 347 */       return false;
/*     */     }
/* 349 */     GradientXYBarPainter that = (GradientXYBarPainter)obj;
/* 350 */     if (this.g1 != that.g1) {
/* 351 */       return false;
/*     */     }
/* 353 */     if (this.g2 != that.g2) {
/* 354 */       return false;
/*     */     }
/* 356 */     if (this.g3 != that.g3) {
/* 357 */       return false;
/*     */     }
/* 359 */     return true;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int hashCode()
/*     */   {
/* 368 */     int hash = 37;
/* 369 */     hash = HashUtilities.hashCode(hash, this.g1);
/* 370 */     hash = HashUtilities.hashCode(hash, this.g2);
/* 371 */     hash = HashUtilities.hashCode(hash, this.g3);
/* 372 */     return hash;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/renderer/xy/GradientXYBarPainter.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */