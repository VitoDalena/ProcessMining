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
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.plaf.basic.BasicCheckBoxUI;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SlickerCheckBoxUI
/*     */   extends BasicCheckBoxUI
/*     */ {
/*  62 */   protected Color OUTER_BOX_BRIGHT = new Color(220, 220, 220);
/*  63 */   protected Color OUTER_BOX_DARK = new Color(100, 100, 100);
/*  64 */   protected Color INNER_BOX_BRIGHT = new Color(110, 110, 110);
/*  65 */   protected Color INNER_BOX_DARK = new Color(30, 30, 30);
/*     */   
/*  67 */   protected int BOX_SIZE = 14;
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
/*  84 */     JCheckBox cBox = (JCheckBox)c;
/*  85 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  87 */     int width = cBox.getWidth();
/*  88 */     int height = cBox.getHeight();
/*  89 */     Insets insets = cBox.getInsets();
/*  90 */     int outerX = insets.left;
/*  91 */     int outerY = (height - this.BOX_SIZE) / 2;
/*     */     
/*  93 */     GradientPaint gp = new GradientPaint(outerX, outerY, this.OUTER_BOX_BRIGHT, 
/*  94 */       outerX + this.BOX_SIZE / 2, outerY + this.BOX_SIZE, this.OUTER_BOX_DARK, false);
/*  95 */     g2d.setPaint(gp);
/*  96 */     g2d.fillRoundRect(outerX, outerY, this.BOX_SIZE, this.BOX_SIZE, 5, 5);
/*     */     
/*  98 */     g2d.setColor(new Color(10, 10, 10, 120));
/*  99 */     g2d.drawRoundRect(outerX, outerY, this.BOX_SIZE, this.BOX_SIZE, 5, 5);
/* 100 */     g2d.setColor(new Color(10, 10, 10, 60));
/* 101 */     g2d.drawRoundRect(outerX + 1, outerY + 1, this.BOX_SIZE - 2, this.BOX_SIZE - 2, 5, 5);
/* 102 */     g2d.setColor(new Color(10, 10, 10, 30));
/* 103 */     g2d.drawRoundRect(outerX + 2, outerY + 2, this.BOX_SIZE - 4, this.BOX_SIZE - 4, 5, 5);
/* 104 */     if (cBox.isSelected())
/*     */     {
/* 106 */       int innerX = outerX + 3;
/* 107 */       int innerY = outerY + 3;
/* 108 */       int innerSize = this.BOX_SIZE - 6;
/* 109 */       gp = new GradientPaint(innerX, innerY, this.INNER_BOX_BRIGHT, 
/* 110 */         innerX + innerSize / 2, innerY + innerSize, this.INNER_BOX_DARK, false);
/* 111 */       g2d.setPaint(gp);
/* 112 */       g2d.fillRoundRect(innerX, innerY, innerSize, innerSize, 3, 3);
/*     */       
/* 114 */       g2d.setColor(new Color(10, 10, 10, 120));
/* 115 */       g2d.drawRoundRect(innerX, innerY, innerSize, innerSize, 3, 3);
/* 116 */       g2d.setColor(new Color(10, 10, 10, 60));
/* 117 */       g2d.drawRoundRect(innerX + 1, innerY + 1, innerSize - 2, innerSize - 2, 3, 3);
/* 118 */       g2d.setColor(new Color(10, 10, 10, 30));
/* 119 */       g2d.drawRoundRect(innerX + 2, innerY + 2, innerSize - 4, innerSize - 4, 3, 3);
/*     */     }
/*     */     
/* 122 */     if (cBox.getText() != null) {
/* 123 */       g2d.setColor(cBox.getForeground());
/* 124 */       g2d.setFont(cBox.getFont());
/* 125 */       int ascent = cBox.getFontMetrics(cBox.getFont()).getAscent();
/* 126 */       int fontY = outerY + this.BOX_SIZE - (this.BOX_SIZE - ascent) / 2 - 2;
/* 127 */       int fontX = outerX + this.BOX_SIZE + 8;
/* 128 */       g2d.drawString(cBox.getText(), fontX, fontY);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/ui/SlickerCheckBoxUI.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */