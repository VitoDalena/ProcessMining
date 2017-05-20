/*     */ package com.fluxicon.slickerbox.ui;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Insets;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.plaf.basic.BasicRadioButtonUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerRadioButtonUI
/*     */   extends BasicRadioButtonUI
/*     */ {
/*  62 */   protected Color OUTER_BUTTON_BRIGHT = new Color(220, 220, 220);
/*  63 */   protected Color OUTER_BUTTON_DARK = new Color(100, 100, 100);
/*  64 */   protected Color INNER_BUTTON_BRIGHT = new Color(110, 110, 110);
/*  65 */   protected Color INNER_BUTTON_DARK = new Color(30, 30, 30);
/*     */   
/*  67 */   protected int BUTTON_SIZE = 14;
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void installDefaults(AbstractButton b)
/*     */   {
/*  74 */     super.installDefaults(b);
/*  75 */     b.setOpaque(false);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public synchronized void paint(Graphics g, JComponent c)
/*     */   {
/*  83 */     Graphics2D g2d = (Graphics2D)g;
/*  84 */     JRadioButton rButton = (JRadioButton)c;
/*  85 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  87 */     int width = rButton.getWidth();
/*  88 */     int height = rButton.getHeight();
/*  89 */     Insets insets = rButton.getInsets();
/*  90 */     int outerX = insets.left;
/*  91 */     int outerY = (height - this.BUTTON_SIZE) / 2;
/*     */     
/*  93 */     GradientPaint gp = new GradientPaint(outerX, outerY, this.OUTER_BUTTON_BRIGHT, 
/*  94 */       outerX + this.BUTTON_SIZE / 2, outerY + this.BUTTON_SIZE, this.OUTER_BUTTON_DARK, false);
/*  95 */     g2d.setPaint(gp);
/*  96 */     g2d.fillOval(outerX, outerY, this.BUTTON_SIZE, this.BUTTON_SIZE);
/*     */     
/*  98 */     g2d.setColor(new Color(10, 10, 10, 120));
/*  99 */     g2d.drawOval(outerX, outerY, this.BUTTON_SIZE, this.BUTTON_SIZE);
/* 100 */     g2d.setColor(new Color(10, 10, 10, 60));
/* 101 */     g2d.drawOval(outerX + 1, outerY + 1, this.BUTTON_SIZE - 2, this.BUTTON_SIZE - 2);
/* 102 */     g2d.setColor(new Color(10, 10, 10, 30));
/* 103 */     g2d.drawOval(outerX + 2, outerY + 2, this.BUTTON_SIZE - 4, this.BUTTON_SIZE - 4);
/* 104 */     if (rButton.isSelected())
/*     */     {
/* 106 */       int innerX = outerX + 3;
/* 107 */       int innerY = outerY + 3;
/* 108 */       int innerSize = this.BUTTON_SIZE - 6;
/* 109 */       gp = new GradientPaint(innerX, innerY, this.INNER_BUTTON_BRIGHT, 
/* 110 */         innerX + innerSize / 2, innerY + innerSize, this.INNER_BUTTON_DARK, false);
/* 111 */       g2d.setPaint(gp);
/* 112 */       g2d.fillOval(innerX, innerY, innerSize, innerSize);
/*     */       
/* 114 */       g2d.setColor(new Color(10, 10, 10, 120));
/* 115 */       g2d.drawOval(innerX, innerY, innerSize, innerSize);
/* 116 */       g2d.setColor(new Color(10, 10, 10, 60));
/* 117 */       g2d.drawOval(innerX + 1, innerY + 1, innerSize - 2, innerSize - 2);
/* 118 */       g2d.setColor(new Color(10, 10, 10, 30));
/* 119 */       g2d.drawOval(innerX + 2, innerY + 2, innerSize - 4, innerSize - 4);
/*     */     }
/*     */     
/* 122 */     if (rButton.getText() != null) {
/* 123 */       g2d.setColor(rButton.getForeground());
/* 124 */       g2d.setFont(rButton.getFont());
/* 125 */       int ascent = rButton.getFontMetrics(rButton.getFont()).getAscent();
/* 126 */       int fontY = outerY + this.BUTTON_SIZE - (this.BUTTON_SIZE - ascent) / 2 - 2;
/* 127 */       int fontX = outerX + this.BUTTON_SIZE + 8;
/* 128 */       g2d.drawString(rButton.getText(), fontX, fontY);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerRadioButtonUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */