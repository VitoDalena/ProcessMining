/*     */ package org.jfree.chart.util;
/*     */ 
/*     */ import java.awt.geom.Line2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LineUtilities
/*     */ {
/*     */   public static boolean clipLine(Line2D line, Rectangle2D rect)
/*     */   {
/*  64 */     double x1 = line.getX1();
/*  65 */     double y1 = line.getY1();
/*  66 */     double x2 = line.getX2();
/*  67 */     double y2 = line.getY2();
/*     */     
/*  69 */     double minX = rect.getMinX();
/*  70 */     double maxX = rect.getMaxX();
/*  71 */     double minY = rect.getMinY();
/*  72 */     double maxY = rect.getMaxY();
/*     */     
/*  74 */     int f1 = rect.outcode(x1, y1);
/*  75 */     int f2 = rect.outcode(x2, y2);
/*     */     
/*  77 */     while ((f1 | f2) != 0) {
/*  78 */       if ((f1 & f2) != 0) {
/*  79 */         return false;
/*     */       }
/*  81 */       double dx = x2 - x1;
/*  82 */       double dy = y2 - y1;
/*     */       
/*     */ 
/*  85 */       if (f1 != 0)
/*     */       {
/*     */ 
/*  88 */         if (((f1 & 0x1) == 1) && (dx != 0.0D))
/*     */         {
/*  90 */           y1 += (minX - x1) * dy / dx;
/*  91 */           x1 = minX;
/*     */         }
/*  93 */         else if (((f1 & 0x4) == 4) && (dx != 0.0D))
/*     */         {
/*  95 */           y1 += (maxX - x1) * dy / dx;
/*  96 */           x1 = maxX;
/*     */         }
/*  98 */         else if (((f1 & 0x8) == 8) && (dy != 0.0D))
/*     */         {
/* 100 */           x1 += (maxY - y1) * dx / dy;
/* 101 */           y1 = maxY;
/*     */         }
/* 103 */         else if (((f1 & 0x2) == 2) && (dy != 0.0D))
/*     */         {
/* 105 */           x1 += (minY - y1) * dx / dy;
/* 106 */           y1 = minY;
/*     */         }
/* 108 */         f1 = rect.outcode(x1, y1);
/*     */       }
/* 110 */       else if (f2 != 0)
/*     */       {
/*     */ 
/* 113 */         if (((f2 & 0x1) == 1) && (dx != 0.0D))
/*     */         {
/* 115 */           y2 += (minX - x2) * dy / dx;
/* 116 */           x2 = minX;
/*     */         }
/* 118 */         else if (((f2 & 0x4) == 4) && (dx != 0.0D))
/*     */         {
/* 120 */           y2 += (maxX - x2) * dy / dx;
/* 121 */           x2 = maxX;
/*     */         }
/* 123 */         else if (((f2 & 0x8) == 8) && (dy != 0.0D))
/*     */         {
/* 125 */           x2 += (maxY - y2) * dx / dy;
/* 126 */           y2 = maxY;
/*     */         }
/* 128 */         else if (((f2 & 0x2) == 2) && (dy != 0.0D))
/*     */         {
/* 130 */           x2 += (minY - y2) * dx / dy;
/* 131 */           y2 = minY;
/*     */         }
/* 133 */         f2 = rect.outcode(x2, y2);
/*     */       }
/*     */     }
/*     */     
/* 137 */     line.setLine(x1, y1, x2, y2);
/* 138 */     return true;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/jfree/chart/util/LineUtilities.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       0.7.1
 */