/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.ProgressEstimator;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.geom.Rectangle2D;
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
/*     */ 
/*     */ public class SlickerDarkEstimatingProgressBarUI
/*     */   extends SlickerDarkProgressBarUI
/*     */ {
/*  61 */   private ProgressEstimator estimator = new ProgressEstimator();
/*  62 */   private double lastUpdate = 2.0D;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent c)
/*     */   {
/*  69 */     if (this.progressBar.getOrientation() == 1)
/*     */     {
/*     */ 
/*  72 */       float width = this.progressBar.getWidth();
/*  73 */       float height = this.progressBar.getHeight();
/*  74 */       float canvasWidth = width - this.border - this.border;
/*  75 */       float canvasHeight = height - this.border - this.border - 20.0F;
/*  76 */       Graphics2D g2d = (Graphics2D)g.create();
/*     */       
/*  78 */       paint(g2d, canvasWidth, canvasHeight);
/*  79 */       g2d.dispose();
/*  80 */       return;
/*     */     }
/*  82 */     double progress = this.progressBar.getPercentComplete();
/*  83 */     if (progress < this.lastUpdate)
/*     */     {
/*  85 */       this.estimator.initialize(this.progressBar);
/*  86 */       this.lastUpdate = progress;
/*     */     }
/*  88 */     float width = this.progressBar.getWidth();
/*  89 */     float height = this.progressBar.getHeight();
/*  90 */     float canvasWidth = width - this.border - this.border;
/*  91 */     float canvasHeight = height - this.border - this.border - 20.0F;
/*  92 */     Graphics2D g2d = (Graphics2D)g.create();
/*     */     
/*  94 */     paint(g2d, canvasWidth, canvasHeight);
/*  95 */     g2d.dispose();
/*  96 */     g2d = (Graphics2D)g.create();
/*     */     String estimation;
/*     */     String estimation;
/*  99 */     if (this.progressBar.isIndeterminate()) {
/* 100 */       estimation = this.estimator.getIndeterminate();
/*     */     } else {
/* 102 */       estimation = this.estimator.getEstimation(this.progressBar);
/*     */     }
/* 104 */     g2d.setColor(new Color(10, 10, 10, 200));
/* 105 */     g2d.setFont(g2d.getFont().deriveFont(10.0F));
/* 106 */     float fontX = this.border + canvasWidth - (float)g2d.getFontMetrics().getStringBounds(estimation, g2d).getWidth();
/* 107 */     g2d.drawString(estimation, fontX, canvasHeight + 18.0F);
/* 108 */     g2d.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize(JComponent c)
/*     */   {
/* 117 */     JProgressBar progressBar = (JProgressBar)c;
/* 118 */     if (progressBar.getOrientation() == 0) {
/* 119 */       return new Dimension((int)this.maxWidth, (int)this.barHeight + 20);
/*     */     }
/* 121 */     return new Dimension((int)this.barHeight, (int)this.maxWidth);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize(JComponent c)
/*     */   {
/* 130 */     JProgressBar progressBar = (JProgressBar)c;
/* 131 */     if (progressBar.getOrientation() == 0) {
/* 132 */       return new Dimension((int)this.minWidth, (int)this.barHeight + 20);
/*     */     }
/* 134 */     return new Dimension((int)this.barHeight, (int)this.minWidth);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(JComponent c)
/*     */   {
/* 143 */     JProgressBar progressBar = (JProgressBar)c;
/* 144 */     if (progressBar.getOrientation() == 0) {
/* 145 */       return new Dimension((int)this.prefWidth, (int)this.barHeight + 20);
/*     */     }
/* 147 */     return new Dimension((int)this.barHeight, (int)this.prefWidth);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void installDefaults()
/*     */   {
/* 156 */     if (this.progressBar.getOrientation() == 0) {
/* 157 */       this.progressBar.setMinimumSize(new Dimension((int)this.minWidth, (int)this.barHeight + 20));
/* 158 */       this.progressBar.setMaximumSize(new Dimension((int)this.maxWidth, (int)this.barHeight + 20));
/* 159 */       this.progressBar.setPreferredSize(new Dimension((int)this.prefWidth, (int)this.barHeight + 20));
/*     */     } else {
/* 161 */       this.progressBar.setMinimumSize(new Dimension((int)this.barHeight, (int)this.minWidth));
/* 162 */       this.progressBar.setMaximumSize(new Dimension((int)this.barHeight, (int)this.maxWidth));
/* 163 */       this.progressBar.setPreferredSize(new Dimension((int)this.barHeight, (int)this.prefWidth));
/*     */     }
/* 165 */     this.progressBar.setOpaque(false);
/* 166 */     this.progressBar.setDoubleBuffered(true);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerDarkEstimatingProgressBarUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */