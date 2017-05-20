/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.ProgressEstimator;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JProgressBar;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerRoundProgressBarVerboseUI
/*     */   extends SlickerRoundProgressBarUI
/*     */ {
/*  60 */   protected long lastTime = 0L;
/*  61 */   protected double lastProgress = 2.0D;
/*  62 */   private ProgressEstimator estimator = new ProgressEstimator();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public SlickerRoundProgressBarVerboseUI(int size, Color color)
/*     */   {
/*  69 */     super(size, color);
/*  70 */     this.dim = new Dimension(size * 7, size + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent c)
/*     */   {
/*  78 */     int offset = (int)(3.0F * this.size);
/*  79 */     g.translate(-offset, 0);
/*  80 */     super.paint(g, c);
/*  81 */     g.translate(offset, 0);
/*  82 */     if (!this.progressBar.isIndeterminate()) {
/*  83 */       double progress = this.progressBar.getPercentComplete();
/*  84 */       if (progress < this.lastProgress)
/*     */       {
/*  86 */         this.estimator.initialize(this.progressBar);
/*  87 */         this.lastProgress = progress;
/*     */       }
/*  89 */       String estimate = this.estimator.getEstimation(progress);
/*  90 */       Graphics2D g2d = (Graphics2D)g.create();
/*  91 */       g2d.setColor(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), (int)(this.color.getAlpha() * 0.8D)));
/*  92 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  93 */       g2d.setFont(g2d.getFont().deriveFont(this.size * 0.6F));
/*  94 */       float stringX = this.size * 1.3F;
/*  95 */       float stringY = this.progressBar.getHeight() / 2.0F;
/*  96 */       long time = System.currentTimeMillis();
/*  97 */       if (time - this.lastTime > 300L) {
/*  98 */         this.lastProgress = progress;
/*  99 */         this.lastTime = time;
/*     */       } else {
/* 101 */         progress = this.lastProgress;
/*     */       }
/* 103 */       int progressInt = (int)(progress * 100.0D);
/* 104 */       String progressString = progressInt + " %";
/* 105 */       if (progressInt < 100) {
/* 106 */         progressString = " " + progressString;
/*     */       }
/* 108 */       g2d.drawString(progressString, stringX, stringY);
/*     */       
/* 110 */       float smallFontSize = this.size * 0.3F;
/* 111 */       if (smallFontSize > 7.0F) {
/* 112 */         g2d.setFont(g2d.getFont().deriveFont(smallFontSize));
/* 113 */         stringY = (float)(stringY + g2d.getFontMetrics().getAscent() * 1.2D);
/* 114 */         g2d.drawString(estimate, stringX, stringY);
/*     */       }
/* 116 */       g2d.dispose();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerRoundProgressBarVerboseUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */