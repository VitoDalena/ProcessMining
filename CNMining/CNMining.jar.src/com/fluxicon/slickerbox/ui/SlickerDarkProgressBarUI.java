/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.Rectangle2D.Float;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import java.awt.image.BufferedImage;
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
/*     */ public class SlickerDarkProgressBarUI
/*     */   extends BasicProgressBarUI
/*     */ {
/*  67 */   protected Color dotColor = new Color(200, 120, 0, 220);
/*     */   
/*  69 */   protected float barHeight = 20.0F;
/*  70 */   protected float minWidth = 50.0F;
/*  71 */   protected float maxWidth = 3000.0F;
/*  72 */   protected float prefWidth = 300.0F;
/*  73 */   protected float border = 2.0F;
/*  74 */   protected float radius = 6.0F;
/*  75 */   protected float lightWidth = 3.0F;
/*  76 */   protected float lightBorder = 1.0F;
/*     */   
/*  78 */   protected float animationIndex = -1.0F;
/*     */   
/*  80 */   protected BufferedImage reflectionBuffer = null;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent c)
/*     */   {
/*  88 */     float width = this.progressBar.getWidth();
/*  89 */     float height = this.progressBar.getHeight();
/*  90 */     float canvasWidth = width - this.border - this.border;
/*  91 */     float canvasHeight = height - this.border - this.border;
/*  92 */     Graphics2D g2d = (Graphics2D)g.create();
/*  93 */     paint(g2d, canvasWidth, canvasHeight);
/*  94 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   protected void paint(Graphics2D g2d, float canvasWidth, float canvasHeight) {
/*  98 */     float width = this.progressBar.getWidth();
/*  99 */     float height = this.progressBar.getHeight();
/* 100 */     Shape originalClip = g2d.getClip();
/* 101 */     RoundRectangle2D.Float clip = new RoundRectangle2D.Float(this.border, this.border, canvasWidth, canvasHeight, this.radius, this.radius);
/* 102 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 103 */     if (this.progressBar.isOpaque())
/*     */     {
/* 105 */       g2d.setColor(this.progressBar.getBackground());
/* 106 */       g2d.fill(new Rectangle2D.Float(0.0F, 0.0F, width + 1.0F, height + 1.0F));
/*     */     }
/*     */     
/* 109 */     g2d.setColor(new Color(0, 0, 0));
/* 110 */     g2d.fill(clip);
/* 111 */     g2d.setClip(clip);
/*     */     
/* 113 */     int numLights = (int)((canvasWidth - this.lightWidth - this.border - this.border) / (this.lightWidth + this.lightBorder)) + 1;
/* 114 */     int maxLightOn = (int)Math.ceil(numLights * getProgressNormalized(this.progressBar));
/* 115 */     float x = this.border + (canvasWidth - numLights * this.lightWidth - (numLights - 1) * this.lightBorder) / 2.0F;
/* 116 */     float y = this.border + this.border;
/* 117 */     float lightHeight = canvasHeight - this.border - this.border;
/* 118 */     if (!this.progressBar.isIndeterminate())
/*     */     {
/* 120 */       for (int i = 0; i < numLights; i++) {
/* 121 */         if (i > maxLightOn) {
/* 122 */           g2d.setColor(new Color(20, 20, 20, 120));
/*     */         } else {
/* 124 */           g2d.setColor(this.dotColor);
/*     */         }
/* 126 */         RoundRectangle2D.Float rect = new RoundRectangle2D.Float(x, y, this.lightWidth, lightHeight, 5.0F, 5.0F);
/* 127 */         g2d.fill(rect);
/* 128 */         g2d.setColor(new Color(0, 0, 0, 80));
/* 129 */         g2d.draw(rect);
/* 130 */         x += this.lightWidth;
/* 131 */         x += this.lightBorder;
/*     */       }
/*     */     }
/*     */     else {
/* 135 */       int activeIndex = (int)(this.animationIndex * numLights);
/* 136 */       for (int i = 0; i < numLights; i++) {
/* 137 */         RoundRectangle2D.Float rect = new RoundRectangle2D.Float(x, y, this.lightWidth, lightHeight, 5.0F, 5.0F);
/* 138 */         g2d.setColor(dampen(this.dotColor, i, activeIndex, numLights));
/* 139 */         g2d.fill(rect);
/* 140 */         g2d.setColor(new Color(0, 0, 0, 80));
/* 141 */         g2d.draw(rect);
/* 142 */         x += this.lightWidth;
/* 143 */         x += this.lightBorder;
/*     */       }
/*     */     }
/*     */     
/* 147 */     ensureReflectionBuffer();
/* 148 */     height = this.border + canvasHeight + this.border;
/* 149 */     g2d.drawImage(this.reflectionBuffer, 0, 0, (int)(width + 1.0F), (int)height, null);
/*     */     
/* 151 */     g2d.setClip(originalClip);
/* 152 */     g2d.setColor(new Color(0, 0, 0, 220));
/* 153 */     g2d.draw(clip);
/*     */   }
/*     */   
/*     */   protected void ensureReflectionBuffer() {
/* 157 */     if ((this.reflectionBuffer == null) || (this.reflectionBuffer.getHeight() != this.progressBar.getHeight())) {
/* 158 */       int height = this.progressBar.getHeight();
/* 159 */       int yMid = height / 2;
/* 160 */       this.reflectionBuffer = GraphicsUtilities.createTranslucentCompatibleImage(1, height);
/* 161 */       Graphics2D g2d = this.reflectionBuffer.createGraphics();
/* 162 */       g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 163 */       GradientPaint gradient = new GradientPaint(0.0F, 0.0F, new Color(0, 0, 0, 0), 0.0F, height, new Color(0, 0, 0, 160), false);
/* 164 */       g2d.setPaint(gradient);
/* 165 */       g2d.fillRect(0, 0, 1, height);
/* 166 */       gradient = new GradientPaint(0.0F, 0.0F, new Color(200, 200, 200, 10), 0.0F, yMid, new Color(200, 200, 200, 40));
/* 167 */       g2d.setPaint(gradient);
/* 168 */       g2d.fillRect(0, 0, 1, yMid);
/* 169 */       g2d.dispose();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Color dampen(Color color, int index, int activeIndex, int size) {
/* 174 */     int maxDistance = size / 2;
/* 175 */     int distance = index - activeIndex;
/* 176 */     if (distance < 0) {
/* 177 */       distance = -distance;
/*     */     }
/* 179 */     if (distance > maxDistance) {
/* 180 */       distance = maxDistance - (distance - maxDistance);
/*     */     }
/* 182 */     float percentage = 0.2F + 0.8F * (distance / maxDistance);
/* 183 */     if (percentage > 1.0F) {
/* 184 */       percentage = 1.0F;
/* 185 */     } else if (percentage < 0.0F) {
/* 186 */       percentage = 0.0F;
/*     */     }
/* 188 */     float red = color.getRed() * percentage;
/* 189 */     float green = color.getGreen() * percentage;
/* 190 */     float blue = color.getBlue() * percentage;
/* 191 */     return new Color(red / 255.0F, green / 255.0F, blue / 255.0F);
/*     */   }
/*     */   
/*     */   protected double getProgressNormalized(JProgressBar pBar) {
/* 195 */     int min = pBar.getMinimum();
/* 196 */     int max = pBar.getMaximum() - min;
/* 197 */     int value = pBar.getValue() - min;
/* 198 */     if ((value <= 0) || (max <= 0)) {
/* 199 */       return 0.0D;
/*     */     }
/* 201 */     double norm = value / max;
/* 202 */     return norm;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize(JComponent c)
/*     */   {
/* 211 */     JProgressBar progressBar = (JProgressBar)c;
/* 212 */     if (progressBar.getOrientation() == 0) {
/* 213 */       return new Dimension((int)this.maxWidth, (int)this.barHeight);
/*     */     }
/* 215 */     return new Dimension((int)this.barHeight, (int)this.maxWidth);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize(JComponent c)
/*     */   {
/* 224 */     JProgressBar progressBar = (JProgressBar)c;
/* 225 */     if (progressBar.getOrientation() == 0) {
/* 226 */       return new Dimension((int)this.minWidth, (int)this.barHeight);
/*     */     }
/* 228 */     return new Dimension((int)this.barHeight, (int)this.minWidth);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(JComponent c)
/*     */   {
/* 237 */     JProgressBar progressBar = (JProgressBar)c;
/* 238 */     if (progressBar.getOrientation() == 0) {
/* 239 */       return new Dimension((int)this.prefWidth, (int)this.barHeight);
/*     */     }
/* 241 */     return new Dimension((int)this.barHeight, (int)this.prefWidth);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void installDefaults()
/*     */   {
/* 250 */     if (this.progressBar.getOrientation() == 0) {
/* 251 */       this.progressBar.setMinimumSize(new Dimension((int)this.minWidth, (int)this.barHeight));
/* 252 */       this.progressBar.setMaximumSize(new Dimension((int)this.maxWidth, (int)this.barHeight));
/* 253 */       this.progressBar.setPreferredSize(new Dimension((int)this.prefWidth, (int)this.barHeight));
/*     */     } else {
/* 255 */       this.progressBar.setMinimumSize(new Dimension((int)this.barHeight, (int)this.minWidth));
/* 256 */       this.progressBar.setMaximumSize(new Dimension((int)this.barHeight, (int)this.maxWidth));
/* 257 */       this.progressBar.setPreferredSize(new Dimension((int)this.barHeight, (int)this.prefWidth));
/*     */     }
/* 259 */     this.progressBar.setOpaque(false);
/* 260 */     this.progressBar.setDoubleBuffered(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void startAnimationTimer()
/*     */   {
/* 268 */     this.animationIndex = 0.0F;
/* 269 */     Thread animationThread = new Thread() {
/*     */       public void run() {
/* 271 */         while (SlickerDarkProgressBarUI.this.animationIndex >= 0.0F) {
/* 272 */           SlickerDarkProgressBarUI tmp7_4 = SlickerDarkProgressBarUI.this;tmp7_4.animationIndex = ((float)(tmp7_4.animationIndex + 0.05D));
/* 273 */           if (SlickerDarkProgressBarUI.this.animationIndex > 1.0F) {
/* 274 */             SlickerDarkProgressBarUI.this.animationIndex = 0.0F;
/*     */           }
/* 276 */           SlickerDarkProgressBarUI.this.progressBar.repaint();
/*     */           try {
/* 278 */             Thread.sleep(100L);
/*     */ 
/*     */           }
/*     */           catch (InterruptedException localInterruptedException) {}
/*     */         }
/*     */       }
/* 284 */     };
/* 285 */     animationThread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void stopAnimationTimer()
/*     */   {
/* 293 */     this.animationIndex = -1.0F;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerDarkProgressBarUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */