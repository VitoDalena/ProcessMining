/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import javax.swing.JToolTip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PToolTip
/*     */   extends JToolTip
/*     */ {
/*  59 */   protected static Color colorBg = new Color(20, 20, 20);
/*  60 */   protected static Color colorText = new Color(200, 200, 200);
/*     */   
/*  62 */   protected float fontSize = 12.0F;
/*  63 */   protected int fontSpacing = 3;
/*  64 */   protected int border = 5;
/*     */   
/*     */   protected static String[] splitStringInMultiLines(String string) {
/*  67 */     return string.split("\\n");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMaximumSize()
/*     */   {
/*  78 */     return getPreferredSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getMinimumSize()
/*     */   {
/*  86 */     return getPreferredSize();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public Dimension getPreferredSize()
/*     */   {
/*  94 */     String text = getTipText();
/*  95 */     if (text != null) {
/*  96 */       String[] lines = splitStringInMultiLines(text);
/*  97 */       FontMetrics metrics = getFontMetrics(getFont());
/*  98 */       int width = 0;
/*  99 */       int height = this.border;
/* 100 */       String[] arrayOfString1; int j = (arrayOfString1 = lines).length; for (int i = 0; i < j; i++) { String line = arrayOfString1[i];
/* 101 */         int currentWidth = (int)metrics.getStringBounds(line, getGraphics()).getWidth();
/* 102 */         if (currentWidth > width) width = currentWidth;
/* 103 */         height += metrics.getHeight();
/* 104 */         height += this.fontSpacing;
/*     */       }
/* 106 */       height += this.border;
/* 107 */       width += 2 * this.border;
/* 108 */       return new Dimension(width, height);
/*     */     }
/*     */     
/* 111 */     return new Dimension(0, 0);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void paint(Graphics g)
/*     */   {
/* 120 */     FontMetrics metrics = getFontMetrics(getFont());
/* 121 */     int width = getWidth();
/* 122 */     int height = getHeight();
/* 123 */     Graphics2D g2d = (Graphics2D)g;
/* 124 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 126 */     g2d.setColor(colorBg);
/* 127 */     g2d.fillRect(0, 0, width, height);
/*     */     
/* 129 */     g2d.setColor(colorText);
/* 130 */     int lineX = this.border;
/* 131 */     int lineY = this.border + metrics.getHeight();
/* 132 */     int lineAdvance = metrics.getHeight() + this.fontSpacing;
/* 133 */     String[] arrayOfString; int j = (arrayOfString = splitStringInMultiLines(getTipText())).length; for (int i = 0; i < j; i++) { String line = arrayOfString[i];
/* 134 */       g2d.drawString(line, lineX, lineY);
/* 135 */       lineY += lineAdvance;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/PToolTip.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */