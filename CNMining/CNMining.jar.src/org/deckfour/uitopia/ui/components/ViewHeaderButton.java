/*     */ package org.deckfour.uitopia.ui.components;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.ButtonModel;
/*     */ import javax.swing.JButton;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewHeaderButton
/*     */   extends JButton
/*     */ {
/*     */   private static final long serialVersionUID = 9061243826339370335L;
/*     */   private static final int HEIGHT = 40;
/*  54 */   private static final Color COLOR_BG_PASSIVE = new Color(90, 90, 90, 100);
/*  55 */   private static final Color COLOR_BG_ACTIVE = new Color(10, 140, 10, 180);
/*  56 */   private static final Color COLOR_TEXT_PASSIVE = new Color(180, 180, 180);
/*  57 */   private static final Color COLOR_TEXT_ACTIVE = new Color(255, 255, 255);
/*     */   private Image icon;
/*     */   private String text;
/*     */   
/*     */   public ViewHeaderButton(Image icon, String text)
/*     */   {
/*  63 */     this.icon = icon;
/*  64 */     this.text = text;
/*  65 */     setOpaque(false);
/*  66 */     setRolloverEnabled(true);
/*  67 */     setFont(getFont().deriveFont(13.0F));
/*  68 */     setBorder(BorderFactory.createEmptyBorder());
/*  69 */     int width = icon.getWidth(null) + 10;
/*  70 */     if (text != null) {
/*  71 */       width += text.length() * 7 + 5;
/*     */     }
/*  73 */     Dimension dim = new Dimension(width, 40);
/*  74 */     setMinimumSize(dim);
/*  75 */     setMaximumSize(dim);
/*  76 */     setPreferredSize(dim);
/*  77 */     setSize(dim);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  85 */     Graphics2D g2d = (Graphics2D)g.create();
/*  86 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  87 */     boolean rollOver = getModel().isRollover();
/*  88 */     int width = getWidth();
/*  89 */     int height = getHeight();
/*  90 */     if (rollOver) {
/*  91 */       g2d.setColor(COLOR_BG_ACTIVE);
/*     */     } else {
/*  93 */       g2d.setColor(COLOR_BG_PASSIVE);
/*     */     }
/*  95 */     g2d.fillRoundRect(0, 3, width - 1, height - 6, 15, 15);
/*  96 */     if (this.text != null) {
/*  97 */       if (rollOver) {
/*  98 */         g2d.setColor(COLOR_TEXT_ACTIVE);
/*     */       } else {
/* 100 */         g2d.setColor(COLOR_TEXT_PASSIVE);
/*     */       }
/* 102 */       g2d.setFont(getFont());
/* 103 */       g2d.drawString(this.text, this.icon.getWidth(null) + 10, 25);
/*     */     }
/* 105 */     if (!rollOver) {
/* 106 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
/*     */     }
/* 108 */     int imgY = (40 - this.icon.getHeight(null)) / 2;
/* 109 */     g2d.drawImage(this.icon, 5, imgY, null);
/* 110 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ViewHeaderButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */