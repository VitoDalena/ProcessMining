/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JProgressBar;
/*     */ import javax.swing.plaf.basic.BasicProgressBarUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerProgressBarUI
/*     */   extends BasicProgressBarUI
/*     */ {
/*  66 */   protected Color BG_PANE = new Color(65, 65, 65);
/*  67 */   protected Color DARKEN1 = new Color(0, 0, 0, 80);
/*  68 */   protected Color DARKEN2 = new Color(0, 0, 0, 120);
/*  69 */   protected Color BORDER_DARKEN1 = new Color(0, 0, 0, 50);
/*  70 */   protected Color BORDER_DARKEN2 = new Color(0, 0, 0, 140);
/*  71 */   protected Color GRAD_DARK = new Color(0, 0, 0, 80);
/*  72 */   protected Color GRAD_HILIGHT = new Color(240, 240, 240, 60);
/*     */   
/*  74 */   protected int animationIndex = -1;
/*  75 */   protected int animationMax = 60;
/*     */   
/*  77 */   protected int barWidth = 20;
/*  78 */   protected int prefDimension = 300;
/*  79 */   protected int minDimension = 50;
/*  80 */   protected int maxDimension = 2000;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent comp)
/*     */   {
/*  87 */     JProgressBar pBar = (JProgressBar)comp;
/*  88 */     int width = pBar.getWidth();
/*  89 */     int height = pBar.getHeight();
/*  90 */     Graphics2D g2d = (Graphics2D)g.create();
/*  91 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  93 */     g2d.setColor(this.BG_PANE);
/*  94 */     g2d.fillRect(2, 2, width - 4, height - 4);
/*     */     
/*  96 */     g2d.setColor(this.BORDER_DARKEN1);
/*  97 */     g2d.drawRect(0, 0, width - 1, height - 1);
/*  98 */     g2d.setColor(this.BORDER_DARKEN2);
/*  99 */     g2d.drawRect(1, 1, width - 3, height - 3);
/* 100 */     if (pBar.getOrientation() == 0)
/*     */     {
/*     */ 
/* 103 */       if (!pBar.isIndeterminate())
/*     */       {
/* 105 */         double progress = getProgressNormalized(pBar);
/* 106 */         int progressWidth = (int)(progress * (width - 4));
/* 107 */         g2d.setColor(encodeProgressColor(progress));
/* 108 */         g2d.fillRect(2, 2, progressWidth, height - 4);
/*     */       }
/*     */       else {
/* 111 */         int barLength = width - 4;
/* 112 */         double animation = this.animationIndex / this.animationMax;
/* 113 */         int x1 = (int)(barLength * animation);
/*     */         
/* 115 */         Color colorX1 = encodeProgressColor(0.0D);
/* 116 */         Color colorX2 = encodeProgressColor(1.0D);
/* 117 */         double edgeValue = animation * 2.0D;
/* 118 */         if (edgeValue > 1.0D) {
/* 119 */           edgeValue = 1.0D - (edgeValue - 1.0D);
/*     */         }
/* 121 */         Color edgeColor = encodeProgressColor(edgeValue);
/* 122 */         if (animation < 0.5D) {
/* 123 */           int x2 = x1 + barLength / 2;
/* 124 */           GradientPaint gradient = new GradientPaint(2.0F, 2.0F, edgeColor, x1 + 2, 2.0F, colorX1, false);
/* 125 */           g2d.setPaint(gradient);
/* 126 */           g2d.fillRect(2, 2, x1 + 2, height - 4);
/* 127 */           gradient = new GradientPaint(x1 + 2, 2.0F, colorX1, x2 + 2, 2.0F, colorX2, false);
/* 128 */           g2d.setPaint(gradient);
/* 129 */           g2d.fillRect(x1 + 2, 2, barLength / 2, height - 4);
/* 130 */           gradient = new GradientPaint(x2 + 2, 2.0F, colorX2, width - 4, 2.0F, edgeColor, false);
/* 131 */           g2d.setPaint(gradient);
/* 132 */           g2d.fillRect(x2 + 2, 2, width - x2 - 4, height - 4);
/*     */         } else {
/* 134 */           int x2 = x1 - barLength / 2;
/* 135 */           GradientPaint gradient = new GradientPaint(2.0F, 2.0F, edgeColor, x2 + 2, 2.0F, colorX2, false);
/* 136 */           g2d.setPaint(gradient);
/* 137 */           g2d.fillRect(2, 2, x2 + 2, height - 4);
/* 138 */           gradient = new GradientPaint(x2 + 2, 2.0F, colorX2, x1 + 2, 2.0F, colorX1, false);
/* 139 */           g2d.setPaint(gradient);
/* 140 */           g2d.fillRect(x2 + 2, 2, barLength / 2, height - 4);
/* 141 */           gradient = new GradientPaint(x1 + 2, 2.0F, colorX1, width - 4, 2.0F, edgeColor, false);
/* 142 */           g2d.setPaint(gradient);
/* 143 */           g2d.fillRect(x1 + 2, 2, width - x1 - 4, height - 4);
/*     */         }
/*     */       }
/*     */       
/*     */ 
/* 148 */       for (int x = 2; x < width - 3; x++) {
/* 149 */         int section = x % 4;
/* 150 */         if (section == 3) {
/* 151 */           g2d.setColor(this.DARKEN2);
/* 152 */         } else { if ((section != 0) && (section != 2)) continue;
/* 153 */           g2d.setColor(this.DARKEN1);
/*     */         }
/*     */         
/*     */ 
/* 157 */         g2d.drawLine(x, 2, x, height - 3);
/*     */       }
/*     */       
/* 160 */       GradientPaint gradient = new GradientPaint(0.0F, 2.0F, this.GRAD_DARK, 0.0F, 8.0F, this.GRAD_HILIGHT, false);
/* 161 */       g2d.setPaint(gradient);
/* 162 */       g2d.fillRect(2, 2, width - 4, 6);
/* 163 */       gradient = new GradientPaint(0.0F, 8.0F, this.GRAD_HILIGHT, 0.0F, height - 2, this.GRAD_DARK, false);
/* 164 */       g2d.setPaint(gradient);
/* 165 */       g2d.fillRect(2, 8, width - 4, height - 10);
/*     */     }
/*     */     else
/*     */     {
/* 169 */       if (!pBar.isIndeterminate()) {
/* 170 */         double progress = getProgressNormalized(pBar);
/* 171 */         int progressHeight = (int)(progress * (height - 4));
/* 172 */         g2d.setColor(encodeProgressColor(progress));
/* 173 */         g2d.fillRect(2, height - 4 - progressHeight, width - 4, progressHeight);
/*     */       }
/*     */       else {
/* 176 */         int barLength = height - 4;
/* 177 */         double animation = this.animationIndex / this.animationMax;
/* 178 */         int y1 = (int)(barLength * animation);
/*     */         
/* 180 */         Color colorY1 = encodeProgressColor(0.0D);
/* 181 */         Color colorY2 = encodeProgressColor(1.0D);
/* 182 */         double edgeValue = animation * 2.0D;
/* 183 */         if (edgeValue > 1.0D) {
/* 184 */           edgeValue = 1.0D - (edgeValue - 1.0D);
/*     */         }
/* 186 */         Color edgeColor = encodeProgressColor(edgeValue);
/* 187 */         if (animation < 0.5D) {
/* 188 */           int y2 = y1 + barLength / 2;
/* 189 */           GradientPaint gradient = new GradientPaint(2.0F, 2.0F, edgeColor, 2.0F, y1 + 2, colorY1, false);
/* 190 */           g2d.setPaint(gradient);
/* 191 */           g2d.fillRect(2, 2, width - 4, y1 + 2);
/* 192 */           gradient = new GradientPaint(2.0F, y1 + 2, colorY1, 2.0F, y2 + 2, colorY2, false);
/* 193 */           g2d.setPaint(gradient);
/* 194 */           g2d.fillRect(2, y1 + 2, width - 4, barLength / 2);
/* 195 */           gradient = new GradientPaint(2.0F, y2 + 2, colorY2, 2.0F, height - 4, edgeColor, false);
/* 196 */           g2d.setPaint(gradient);
/* 197 */           g2d.fillRect(2, y2 + 2, width - 4, width - y2 - 4);
/*     */         } else {
/* 199 */           int y2 = y1 - barLength / 2;
/* 200 */           GradientPaint gradient = new GradientPaint(2.0F, 2.0F, edgeColor, 2.0F, y2 + 2, colorY2, false);
/* 201 */           g2d.setPaint(gradient);
/* 202 */           g2d.fillRect(2, 2, width - 4, y2 + 2);
/* 203 */           gradient = new GradientPaint(2.0F, y2 + 2, colorY2, 2.0F, y1 + 2, colorY1, false);
/* 204 */           g2d.setPaint(gradient);
/* 205 */           g2d.fillRect(2, y2 + 2, width - 4, barLength / 2);
/* 206 */           gradient = new GradientPaint(2.0F, y1 + 2, colorY1, 2.0F, height - 4, edgeColor, false);
/* 207 */           g2d.setPaint(gradient);
/* 208 */           g2d.fillRect(2, y1 + 2, width - 4, height - y1 - 4);
/*     */         }
/*     */       }
/*     */       
/* 212 */       for (int y = 5; y < height - 4; y += 4) {
/* 213 */         g2d.setColor(this.DARKEN1);
/* 214 */         g2d.drawLine(y - 2, 2, y - 2, height - 3);
/* 215 */         g2d.setColor(this.DARKEN2);
/* 216 */         g2d.drawLine(y - 1, 2, y - 1, height - 3);
/* 217 */         g2d.setColor(this.DARKEN1);
/* 218 */         g2d.drawLine(y, 2, y, height - 3);
/*     */       }
/*     */       
/* 221 */       GradientPaint gradient = new GradientPaint(2.0F, 0.0F, this.GRAD_DARK, 10.0F, 0.0F, this.GRAD_HILIGHT, false);
/* 222 */       g2d.setPaint(gradient);
/* 223 */       g2d.fillRect(2, 2, 8, width - 4);
/* 224 */       gradient = new GradientPaint(10.0F, 0.0F, this.GRAD_HILIGHT, width - 2, 0.0F, this.GRAD_DARK, false);
/* 225 */       g2d.setPaint(gradient);
/* 226 */       g2d.fillRect(10, 2, width - 12, height - 4);
/*     */     }
/*     */     
/* 229 */     g2d.setColor(this.DARKEN2);
/* 230 */     g2d.drawRect(2, 2, width - 5, height - 5);
/* 231 */     g2d.setColor(this.DARKEN1);
/* 232 */     g2d.drawRect(3, 3, width - 7, height - 7);
/*     */     
/* 234 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   protected double getProgressNormalized(JProgressBar pBar) {
/* 238 */     int min = pBar.getMinimum();
/* 239 */     int max = pBar.getMaximum() - min;
/* 240 */     int value = pBar.getValue() - min;
/* 241 */     if ((value <= 0) || (max <= 0)) {
/* 242 */       return 0.0D;
/*     */     }
/* 244 */     double norm = value / max;
/* 245 */     return norm;
/*     */   }
/*     */   
/*     */   protected Color encodeProgressColor(double value)
/*     */   {
/* 250 */     if (value < 0.0D) value = 0.0D;
/* 251 */     if (value > 1.0D) value = 1.0D;
/*     */     float blue;
/* 253 */     float red; float green; float blue; if (value > 0.5D) {
/* 254 */       float red = (1.0F - (float)value) * 2.0F;
/* 255 */       float green = 1.0F;
/* 256 */       blue = 0.0F;
/*     */     } else {
/* 258 */       red = 1.0F;
/* 259 */       green = (float)value * 2.0F;
/* 260 */       blue = 0.0F;
/*     */     }
/* 262 */     red *= 0.8F;
/* 263 */     green *= 0.8F;
/* 264 */     return new Color(red, green, blue);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize(JComponent c)
/*     */   {
/* 272 */     JProgressBar pBar = (JProgressBar)c;
/* 273 */     if (pBar.getOrientation() == 0) {
/* 274 */       return new Dimension(this.maxDimension, this.barWidth);
/*     */     }
/* 276 */     return new Dimension(this.barWidth, this.maxDimension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize(JComponent c)
/*     */   {
/* 285 */     JProgressBar pBar = (JProgressBar)c;
/* 286 */     if (pBar.getOrientation() == 0) {
/* 287 */       return new Dimension(this.minDimension, this.barWidth);
/*     */     }
/* 289 */     return new Dimension(this.barWidth, this.minDimension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(JComponent c)
/*     */   {
/* 298 */     JProgressBar pBar = (JProgressBar)c;
/* 299 */     if (pBar.getOrientation() == 0) {
/* 300 */       return new Dimension(this.prefDimension, this.barWidth);
/*     */     }
/* 302 */     return new Dimension(this.barWidth, this.prefDimension);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void installDefaults()
/*     */   {
/* 311 */     if (this.progressBar.getOrientation() == 0) {
/* 312 */       this.progressBar.setMinimumSize(new Dimension(this.minDimension, this.barWidth));
/* 313 */       this.progressBar.setMaximumSize(new Dimension(this.maxDimension, this.barWidth));
/* 314 */       this.progressBar.setPreferredSize(new Dimension(this.prefDimension, this.barWidth));
/*     */     } else {
/* 316 */       this.progressBar.setMinimumSize(new Dimension(this.barWidth, this.minDimension));
/* 317 */       this.progressBar.setMaximumSize(new Dimension(this.barWidth, this.maxDimension));
/* 318 */       this.progressBar.setPreferredSize(new Dimension(this.barWidth, this.prefDimension));
/*     */     }
/* 320 */     this.progressBar.setOpaque(false);
/* 321 */     this.progressBar.setDoubleBuffered(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void startAnimationTimer()
/*     */   {
/* 329 */     this.animationIndex = 0;
/* 330 */     Thread animationThread = new Thread() {
/*     */       public void run() {
/* 332 */         while (SlickerProgressBarUI.this.animationIndex >= 0) {
/* 333 */           SlickerProgressBarUI.this.animationIndex += 1;
/* 334 */           if (SlickerProgressBarUI.this.animationIndex > SlickerProgressBarUI.this.animationMax) {
/* 335 */             SlickerProgressBarUI.this.animationIndex = 0;
/*     */           }
/* 337 */           SlickerProgressBarUI.this.progressBar.repaint();
/*     */           try {
/* 339 */             Thread.sleep(100L);
/*     */ 
/*     */           }
/*     */           catch (InterruptedException localInterruptedException) {}
/*     */         }
/*     */       }
/* 345 */     };
/* 346 */     animationThread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void stopAnimationTimer()
/*     */   {
/* 354 */     this.animationIndex = -1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerProgressBarUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */