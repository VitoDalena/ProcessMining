/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import com.fluxicon.slickerbox.util.RuntimeUtils;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorConvertOp;
/*     */ import java.awt.image.RescaleOp;
/*     */ import java.lang.ref.SoftReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FullScreenHeaderBar
/*     */   extends HeaderBar
/*     */ {
/*     */   protected BufferedImage icon;
/*     */   
/*     */   public FullScreenHeaderBar(String title, BufferedImage icon)
/*     */   {
/*  71 */     super(title);
/*  72 */     setHeight(50);
/*  73 */     setColors(new Color(10, 10, 10), new Color(20, 20, 20));
/*  74 */     BufferedImage tmp = GraphicsUtilities.createTranslucentCompatibleImage(icon.getWidth(), icon.getHeight());
/*  75 */     tmp.getGraphics().drawImage(icon, 0, 0, null);
/*  76 */     float scaleFactor = 4.0F;
/*  77 */     RenderingHints hints = new RenderingHints(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
/*  78 */     RescaleOp op = new RescaleOp(scaleFactor, 0.0F, hints);
/*  79 */     op.filter(tmp, tmp);
/*  80 */     ColorSpace colorSpace = ColorSpace.getInstance(
/*  81 */       1003);
/*  82 */     ColorConvertOp cop = new ColorConvertOp(colorSpace, null);
/*  83 */     this.icon = cop.filter(tmp, null);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void createBuffer()
/*     */   {
/*  91 */     int width = getWidth();
/*  92 */     int height = getHeight();
/*  93 */     this.buffer = new SoftReference(GraphicsUtilities.createCompatibleImage(width, height));
/*  94 */     Graphics2D g2dBuf = (Graphics2D)((BufferedImage)this.buffer.get()).getGraphics();
/*  95 */     g2dBuf.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  97 */     int hilightY = height / 5;
/*  98 */     GradientPaint gradient = new GradientPaint(20.0F, 0.0F, this.COLOR_BG, 20.0F, hilightY, this.COLOR_HILIGHT, false);
/*  99 */     g2dBuf.setPaint(gradient);
/* 100 */     g2dBuf.fillRect(0, 0, width, hilightY);
/* 101 */     gradient = new GradientPaint(20.0F, hilightY, this.COLOR_HILIGHT, 20.0F, 30.0F, this.COLOR_BG, false);
/* 102 */     g2dBuf.setPaint(gradient);
/* 103 */     g2dBuf.fillRect(0, hilightY, width, height);
/*     */     
/* 105 */     gradient = new GradientPaint(0.0F, height / 4, new Color(120, 120, 120, 0), 0.0F, height, new Color(120, 120, 120, 70), false);
/* 106 */     g2dBuf.setPaint(gradient);
/* 107 */     for (int x = -40; x < width; x += 5) {
/* 108 */       g2dBuf.drawLine(x, height, x + 40, 0);
/*     */     }
/*     */     
/* 111 */     Graphics2D g2dTmp = (Graphics2D)g2dBuf.create();
/* 112 */     g2dTmp.setComposite(AlphaComposite.getInstance(3, 0.6F));
/* 113 */     g2dTmp.drawImage(this.icon, 20, (height - this.icon.getHeight()) / 2, null);
/* 114 */     g2dTmp.dispose();
/*     */     
/* 116 */     gradient = new GradientPaint(0.0F, height, new Color(0, 0, 0, 180), 0.0F, height - 20, new Color(0, 0, 0, 0), false);
/* 117 */     g2dBuf.setPaint(gradient);
/* 118 */     g2dBuf.fillRect(0, 0, width, height);
/*     */     
/* 120 */     int maxWidth = width - 30;
/* 121 */     if (this.listener != null) {
/* 122 */       maxWidth -= 100;
/*     */     }
/* 124 */     BufferedImage titleBuffer = new BufferedImage(width, height, 2);
/* 125 */     Graphics2D g2dt = (Graphics2D)titleBuffer.getGraphics();
/* 126 */     g2dt.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 127 */     int stringBaseY = height * 2 / 3 + 2;
/* 128 */     g2dt.setFont(g2dt.getFont().deriveFont(16.0F));
/* 129 */     if (!RuntimeUtils.isRunningMacOsX()) {
/* 130 */       g2dt.setFont(g2dt.getFont().deriveFont(1));
/*     */     }
/* 132 */     g2dt.setColor(new Color(30, 30, 30));
/* 133 */     g2dt.drawString(this.title, this.icon.getWidth() + 42, stringBaseY);
/* 134 */     g2dt.setColor(new Color(200, 200, 200));
/* 135 */     g2dt.drawString(this.title, this.icon.getWidth() + 40, stringBaseY - 2);
/* 136 */     int stringWidth = (int)g2dt.getFontMetrics().getStringBounds(this.title, g2dt).getWidth();
/* 137 */     if (stringWidth > maxWidth) {
/* 138 */       GradientPaint maskGradient = new GradientPaint(maxWidth - 40, 0.0F, new Color(0, 0, 0, 0), maxWidth, 0.0F, new Color(255, 255, 255, 255), false);
/* 139 */       g2dt.setPaint(maskGradient);
/* 140 */       g2dt.setComposite(AlphaComposite.DstOut);
/* 141 */       g2dt.fillRect(0, 0, width, height);
/*     */     }
/* 143 */     g2dt.dispose();
/* 144 */     g2dBuf.drawImage(titleBuffer, 0, 0, null);
/* 145 */     g2dBuf.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/FullScreenHeaderBar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */