/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.ColorUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Area;
/*     */ import java.awt.geom.Ellipse2D;
/*     */ import java.awt.geom.Ellipse2D.Double;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Rectangle2D.Double;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D.Double;
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
/*     */ public class SlickerRoundProgressBarUI
/*     */   extends BasicProgressBarUI
/*     */ {
/*  67 */   protected Color color = new Color(80, 80, 80);
/*     */   protected float size;
/*     */   protected Dimension dim;
/*  70 */   protected int animationIndex = -1;
/*     */   
/*  72 */   protected int segments = 12;
/*  73 */   protected double angle = 6.283185307179586D / this.segments;
/*     */   
/*     */   public SlickerRoundProgressBarUI(int size, Color color) {
/*  76 */     this.color = color;
/*  77 */     this.size = size;
/*  78 */     this.dim = new Dimension(size + 2, size + 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g, JComponent c)
/*     */   {
/*  86 */     double width = this.progressBar.getWidth();
/*  87 */     double height = this.progressBar.getHeight();
/*  88 */     Graphics2D g2d = (Graphics2D)g.create();
/*  89 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  90 */     if (this.progressBar.isOpaque())
/*     */     {
/*  92 */       g2d.setColor(this.progressBar.getBackground());
/*  93 */       g2d.fill(new Rectangle2D.Double(0.0D, 0.0D, width + 1.0D, height + 1.0D));
/*     */     }
/*  95 */     if (this.progressBar.isIndeterminate()) {
/*  96 */       paintIndeterminate(g2d);
/*     */     } else {
/*  98 */       paintDeterminate(g2d);
/*     */     }
/* 100 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   protected void paintDeterminate(Graphics2D g2d) {
/* 104 */     double radius = this.size * 0.5D;
/* 105 */     double width = this.progressBar.getWidth();
/* 106 */     double height = this.progressBar.getHeight();
/* 107 */     double centerX = width / 2.0D;
/* 108 */     double centerY = height / 2.0D;
/* 109 */     double value = this.progressBar.getValue();
/* 110 */     double minValue = this.progressBar.getMinimum();
/* 111 */     double maxValue = this.progressBar.getMaximum();
/* 112 */     double progress = (value - minValue) / (maxValue - minValue);
/* 113 */     Ellipse2D circle = new Ellipse2D.Double(centerX - radius, centerY - radius, this.size, this.size);
/* 114 */     g2d.setColor(this.color);
/* 115 */     if (progress >= 1.0D) {
/* 116 */       g2d.fill(circle);
/*     */     } else {
/* 118 */       double remainingProgress = progress;
/* 119 */       GeneralPath mask = new GeneralPath();
/* 120 */       mask.moveTo((float)centerX, (float)centerY);
/* 121 */       mask.lineTo((float)centerX, (float)centerY - (float)(radius * 2.0D));
/* 122 */       if (remainingProgress >= 0.25D) {
/* 123 */         mask.lineTo((float)centerX + (float)(radius * 2.0D), (float)centerY);
/* 124 */         remainingProgress -= 0.25D;
/*     */       }
/* 126 */       if (remainingProgress >= 0.25D) {
/* 127 */         mask.lineTo((float)centerX, (float)centerY + (float)(radius * 2.0D));
/* 128 */         remainingProgress -= 0.25D;
/*     */       }
/* 130 */       if (remainingProgress >= 0.25D) {
/* 131 */         mask.lineTo((float)centerX - (float)(radius * 2.0D), (float)centerY);
/* 132 */         remainingProgress -= 0.25D;
/*     */       }
/* 134 */       if (remainingProgress > 0.0D) {
/* 135 */         double theta = 6.283185307179586D * progress;
/* 136 */         AffineTransform transform = AffineTransform.getRotateInstance(theta, centerX, centerY);
/* 137 */         double[] points = { centerX, centerY - radius * 2.0D };
/* 138 */         transform.transform(points, 0, points, 0, 1);
/* 139 */         mask.lineTo((float)points[0], (float)points[1]);
/*     */       }
/* 141 */       mask.closePath();
/*     */       
/* 143 */       Area pie = new Area(circle);
/* 144 */       pie.intersect(new Area(mask));
/*     */       
/* 146 */       double innerRadius = this.size * 0.4D;
/* 147 */       double innerSize = this.size * 0.8D;
/* 148 */       Ellipse2D innerCircle = new Ellipse2D.Double(centerX - innerRadius, centerY - innerRadius, innerSize, innerSize);
/* 149 */       Area outerCircle = new Area(circle);
/* 150 */       outerCircle.subtract(new Area(innerCircle));
/*     */       
/* 152 */       pie.add(outerCircle);
/* 153 */       g2d.fill(pie);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void paintIndeterminate(Graphics2D g2d) {
/* 158 */     double width = this.progressBar.getWidth();
/* 159 */     double height = this.progressBar.getHeight();
/*     */     
/* 161 */     AffineTransform origTransform = g2d.getTransform();
/* 162 */     AffineTransform rotate = new AffineTransform(origTransform);
/* 163 */     double centerX = width / 2.0D;
/* 164 */     double centerY = height / 2.0D;
/* 165 */     double segHeight = this.size * 0.3D;
/* 166 */     double segY = centerY + this.size * 0.2D;
/* 167 */     double segWidth = this.size * 0.075D;
/* 168 */     double segX = centerX - segWidth / 2.0D;
/* 169 */     float alphaDecrement = 0.8F / this.segments;
/* 170 */     float alpha = 1.0F;
/* 171 */     rotate.rotate(this.animationIndex * this.angle, centerX, centerY);
/* 172 */     RoundRectangle2D segRect = new RoundRectangle2D.Double(segX, segY, segWidth, segHeight, segWidth, segWidth);
/* 173 */     for (int i = 0; i < this.segments; i++) {
/* 174 */       g2d.setTransform(rotate);
/* 175 */       g2d.setColor(ColorUtils.setAlphaFloat(this.color, alpha));
/* 176 */       g2d.fill(segRect);
/* 177 */       alpha -= alphaDecrement;
/* 178 */       rotate.rotate(-this.angle, centerX, centerY);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize(JComponent c)
/*     */   {
/* 187 */     return this.dim;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize(JComponent c)
/*     */   {
/* 195 */     return this.dim;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize(JComponent c)
/*     */   {
/* 203 */     return this.dim;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void installDefaults()
/*     */   {
/* 211 */     this.progressBar.setMinimumSize(this.dim);
/* 212 */     this.progressBar.setPreferredSize(this.dim);
/* 213 */     this.progressBar.setOpaque(false);
/* 214 */     this.progressBar.setDoubleBuffered(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void startAnimationTimer()
/*     */   {
/* 222 */     this.animationIndex = 0;
/* 223 */     Thread animationThread = new Thread() {
/*     */       public void run() {
/* 225 */         while (SlickerRoundProgressBarUI.this.animationIndex >= 0) {
/* 226 */           SlickerRoundProgressBarUI.this.animationIndex += 1;
/* 227 */           if (SlickerRoundProgressBarUI.this.animationIndex >= SlickerRoundProgressBarUI.this.segments) {
/* 228 */             SlickerRoundProgressBarUI.this.animationIndex = 0;
/*     */           }
/* 230 */           SlickerRoundProgressBarUI.this.progressBar.repaint();
/*     */           try {
/* 232 */             Thread.sleep(84L);
/*     */ 
/*     */           }
/*     */           catch (InterruptedException localInterruptedException) {}
/*     */         }
/*     */       }
/* 238 */     };
/* 239 */     animationThread.start();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected synchronized void stopAnimationTimer()
/*     */   {
/* 247 */     this.animationIndex = -1;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerRoundProgressBarUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */