/*     */ package org.deckfour.uitopia.ui.components;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.JToggleButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageToggleButton
/*     */   extends JToggleButton
/*     */ {
/*     */   private static final long serialVersionUID = 1490591566011034808L;
/*     */   protected final Image icon;
/*     */   private final Color colorBgActive;
/*     */   protected final Color colorBgPassive;
/*     */   protected final Color colorBgMouseOver;
/*     */   protected final int border;
/*     */   
/*     */   public ImageToggleButton(Image icon)
/*     */   {
/*  61 */     this(icon, new Color(140, 140, 20), new Color(140, 140, 140), new Color(40, 140, 40), 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ImageToggleButton(Image icon, Color colorBgActive, Color colorBgPassive, Color colorBgMouseOver, int border)
/*     */   {
/*  72 */     this.icon = icon;
/*  73 */     this.colorBgActive = colorBgActive;
/*  74 */     this.colorBgPassive = colorBgPassive;
/*  75 */     this.colorBgMouseOver = colorBgMouseOver;
/*  76 */     this.border = border;
/*  77 */     setBorder(BorderFactory.createEmptyBorder());
/*  78 */     int width = icon.getWidth(null) + border + border + 2;
/*  79 */     int height = icon.getHeight(null) + border + border + 2;
/*  80 */     Dimension dim = new Dimension(width, height);
/*  81 */     setMinimumSize(dim);
/*  82 */     setMaximumSize(dim);
/*  83 */     setPreferredSize(dim);
/*  84 */     setSize(dim);
/*  85 */     setOpaque(false);
/*  86 */     setRolloverEnabled(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  94 */     Graphics2D g2d = (Graphics2D)g.create();
/*  95 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  96 */     boolean mouseOver = getModel().isRollover();
/*  97 */     int width = getWidth();
/*  98 */     int height = getHeight();
/*  99 */     int size = (width > height ? height : width) - 1;
/* 100 */     if (isSelected()) {
/* 101 */       g2d.setColor(this.colorBgActive);
/* 102 */     } else if (mouseOver) {
/* 103 */       g2d.setColor(this.colorBgMouseOver);
/*     */     } else {
/* 105 */       g2d.setColor(this.colorBgPassive);
/*     */     }
/* 107 */     g2d.fillOval(0, 0, size, size);
/* 108 */     if ((!isSelected()) && (!mouseOver)) {
/* 109 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.8F));
/*     */     }
/* 111 */     g2d.drawImage(this.icon, this.border + 1, this.border + 1, null);
/* 112 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ImageToggleButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */