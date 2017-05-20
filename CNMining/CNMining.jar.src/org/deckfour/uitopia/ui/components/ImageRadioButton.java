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
/*     */ import javax.swing.JRadioButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageRadioButton
/*     */   extends JRadioButton
/*     */ {
/*     */   private static final long serialVersionUID = 1291125257809126270L;
/*     */   private final Image icon;
/*     */   private final Color colorBgActive;
/*     */   private final Color colorBgPassive;
/*     */   private final Color colorBgMouseOver;
/*     */   private final int borderX;
/*     */   private final int borderY;
/*     */   
/*     */   public ImageRadioButton(Image icon)
/*     */   {
/*  62 */     this(icon, new Color(20, 80, 20), new Color(110, 110, 110), new Color(60, 80, 60), 7, 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ImageRadioButton(Image icon, Color colorBgActive, Color colorBgPassive, Color colorBgMouseOver, int borderX, int borderY)
/*     */   {
/*  73 */     this.icon = icon;
/*  74 */     this.colorBgActive = colorBgActive;
/*  75 */     this.colorBgPassive = colorBgPassive;
/*  76 */     this.colorBgMouseOver = colorBgMouseOver;
/*  77 */     this.borderX = borderX;
/*  78 */     this.borderY = borderY;
/*  79 */     setBorder(BorderFactory.createEmptyBorder());
/*  80 */     int width = icon.getWidth(null) + borderX + borderX;
/*  81 */     int height = icon.getHeight(null) + borderY + borderY;
/*  82 */     Dimension dim = new Dimension(width, height);
/*  83 */     setMinimumSize(dim);
/*  84 */     setMaximumSize(dim);
/*  85 */     setPreferredSize(dim);
/*  86 */     setSize(dim);
/*  87 */     setOpaque(false);
/*  88 */     setRolloverEnabled(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  96 */     Graphics2D g2d = (Graphics2D)g.create();
/*  97 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  98 */     boolean mouseOver = getModel().isRollover();
/*  99 */     int width = getWidth();
/* 100 */     int height = getHeight();
/* 101 */     if (isSelected()) {
/* 102 */       g2d.setColor(this.colorBgActive);
/* 103 */     } else if (mouseOver) {
/* 104 */       g2d.setColor(this.colorBgMouseOver);
/*     */     } else {
/* 106 */       g2d.setColor(this.colorBgPassive);
/*     */     }
/* 108 */     g2d.fillRoundRect(0, 0, width - 1, height - 1, this.borderY * 2, this.borderY * 2);
/*     */     
/* 110 */     if ((!isSelected()) && (!mouseOver)) {
/* 111 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.8F));
/*     */     }
/* 113 */     g2d.drawImage(this.icon, this.borderX, this.borderY, null);
/* 114 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ImageRadioButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */