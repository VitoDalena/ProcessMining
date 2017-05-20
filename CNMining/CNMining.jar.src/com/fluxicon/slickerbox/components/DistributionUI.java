/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.ColorUtils;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DistributionUI
/*     */   extends JComponent
/*     */ {
/*  61 */   protected int originalHeight = 200;
/*     */   protected int[] data;
/*     */   protected BufferedImage original;
/*     */   
/*     */   public DistributionUI(int[] values) {
/*  66 */     this.data = values;
/*  67 */     this.original = createHistogram(values);
/*     */   }
/*     */   
/*     */   protected BufferedImage createHistogram(int[] values) {
/*  71 */     setOpaque(false);
/*  72 */     setMinimumSize(new Dimension(160, 100));
/*  73 */     setMaximumSize(new Dimension(2000, this.originalHeight));
/*  74 */     setMaximumSize(new Dimension(220, this.originalHeight));
/*  75 */     BufferedImage histogram = new BufferedImage(values.length, this.originalHeight, 2);
/*  76 */     Graphics2D g2d = histogram.createGraphics();
/*  77 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  78 */     double max = values[(values.length - 1)];
/*  79 */     for (int x = 0; x < values.length; x++) {
/*  80 */       double value = values[x] / max;
/*  81 */       int y = this.originalHeight - (int)(value * this.originalHeight);
/*  82 */       g2d.setColor(ColorUtils.encodeZeroOneScale(value));
/*  83 */       g2d.drawLine(x, y, x, this.originalHeight - 1);
/*     */     }
/*  85 */     g2d.dispose();
/*  86 */     return histogram;
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  90 */     Graphics2D g2d = (Graphics2D)g;
/*  91 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  92 */     double width = getWidth();
/*  93 */     if (width > this.data.length) {
/*  94 */       double height = getHeight();
/*  95 */       double barWidth = width / this.data.length;
/*  96 */       double barX = 0.0D;
/*  97 */       double max = this.data[(this.data.length - 1)];
/*  98 */       for (int x = 0; x < this.data.length; x++) {
/*  99 */         double value = this.data[x] / max;
/* 100 */         double y = height - value * height - 0.5D;
/* 101 */         g2d.setColor(ColorUtils.encodeZeroOneScale(value));
/* 102 */         g2d.fill(new Rectangle2D.Double(barX, y, barWidth + 1.0D, height - y));
/* 103 */         barX += barWidth;
/*     */       }
/*     */     } else {
/* 106 */       g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
/* 107 */       g2d.drawImage(this.original, 0, 0, getWidth(), getHeight(), null);
/* 108 */       g2d.dispose();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/DistributionUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */