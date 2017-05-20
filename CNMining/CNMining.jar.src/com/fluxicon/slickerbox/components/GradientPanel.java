/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.Color;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.lang.ref.SoftReference;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GradientPanel
/*     */   extends JPanel
/*     */ {
/*     */   protected Color topColor;
/*     */   protected Color bottomColor;
/*     */   protected SoftReference<BufferedImage> buffer;
/*     */   
/*     */   public GradientPanel(Color colorTop, Color colorBottom)
/*     */   {
/*  67 */     this.topColor = colorTop;
/*  68 */     this.bottomColor = colorBottom;
/*  69 */     this.buffer = null;
/*  70 */     setOpaque(true);
/*  71 */     setDoubleBuffered(true);
/*     */   }
/*     */   
/*     */   public void setColors(Color colorTop, Color colorBottom) {
/*  75 */     this.topColor = colorTop;
/*  76 */     this.bottomColor = colorBottom;
/*  77 */     if (this.buffer != null) {
/*  78 */       this.buffer = null;
/*  79 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  88 */     int height = getHeight();
/*  89 */     if ((this.buffer == null) || (this.buffer.get() == null) || (Math.abs(((BufferedImage)this.buffer.get()).getHeight() - height) > height / 4)) {
/*  90 */       this.buffer = new SoftReference(GraphicsUtilities.createCompatibleImage(1, height));
/*  91 */       Graphics2D bg2d = ((BufferedImage)this.buffer.get()).createGraphics();
/*  92 */       bg2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  93 */       GradientPaint gradient = new GradientPaint(0.0F, 0.0F, this.topColor, 0.0F, height, this.bottomColor, false);
/*  94 */       bg2d.setPaint(gradient);
/*  95 */       bg2d.fillRect(0, 0, 1, height);
/*  96 */       bg2d.dispose();
/*     */     }
/*  98 */     Rectangle clip = g.getClipBounds();
/*  99 */     g.drawImage((Image)this.buffer.get(), clip.x, clip.y, clip.x + clip.width, clip.y + clip.height, 
/* 100 */       0, clip.y, 1, clip.y + clip.height, null);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/GradientPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */