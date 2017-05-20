/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.ColorUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D.Float;
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
/*     */ public class RoundGauge
/*     */   extends JComponent
/*     */ {
/*  62 */   protected Color bgColor = new Color(0, 0, 0);
/*  63 */   protected Color colorEmpty = new Color(0, 0, 220, 160);
/*  64 */   protected Color colorFull = new Color(240, 0, 0, 250);
/*     */   
/*  66 */   protected int radius = 50;
/*  67 */   protected int border = 5;
/*     */   
/*  69 */   protected int segments = 36;
/*     */   
/*  71 */   protected float percentage = 0.0F;
/*     */   
/*     */   public RoundGauge() {
/*  74 */     this(50, 5);
/*     */   }
/*     */   
/*     */   public RoundGauge(int radius, int border) {
/*  78 */     this.radius = radius;
/*  79 */     this.border = border;
/*  80 */     Dimension dim = new Dimension(radius + border + border, radius + border + border);
/*  81 */     setMinimumSize(dim);
/*  82 */     setMaximumSize(dim);
/*  83 */     setPreferredSize(dim);
/*  84 */     setOpaque(true);
/*     */   }
/*     */   
/*     */   public void setPercentage(float percentage) {
/*  88 */     this.percentage = percentage;
/*  89 */     if ((isDisplayable()) && (isVisible())) {
/*  90 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  95 */     Graphics2D g2d = (Graphics2D)g.create();
/*  96 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  97 */     g2d.setFont(g2d.getFont().deriveFont(9.0F));
/*  98 */     float width = getWidth();
/*  99 */     float height = getHeight();
/*     */     
/* 101 */     g2d.setColor(this.bgColor);
/* 102 */     g2d.fill(new Rectangle2D.Float(0.0F, 0.0F, width + 1.0F, height + 1.0F));
/*     */     
/* 104 */     float pathX = width / 2.0F;
/* 105 */     float heightThird = (height - this.border - this.border) / 3.0F;
/* 106 */     float pathY1 = height / 2.0F + heightThird / 2.0F;
/* 107 */     float pathY2 = height - this.border;
/* 108 */     float offset1 = heightThird * 0.025F;
/* 109 */     float offset2 = heightThird * 0.12F;
/* 110 */     GeneralPath path = new GeneralPath();
/* 111 */     path.moveTo(pathX - offset1, pathY1);
/* 112 */     path.lineTo(pathX + offset1, pathY1);
/* 113 */     path.lineTo(pathX + offset2, pathY2);
/* 114 */     path.lineTo(pathX - offset2, pathY2);
/* 115 */     path.closePath();
/*     */     
/* 117 */     int paintSegments = (int)Math.ceil((this.segments - 8) * this.percentage);
/* 118 */     double rotateStep = 6.283185307179586D / this.segments;
/* 119 */     g2d.rotate(rotateStep * 5.0D, width / 2.0F, height / 2.0F);
/* 120 */     for (int i = 0; i < paintSegments; i++) {
/* 121 */       g2d.setColor(ColorUtils.mix(this.colorEmpty, this.colorFull, i / (this.segments - 8)));
/* 122 */       g2d.fill(path);
/* 123 */       g2d.rotate(rotateStep, width / 2.0F, height / 2.0F);
/*     */     }
/* 125 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/RoundGauge.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */