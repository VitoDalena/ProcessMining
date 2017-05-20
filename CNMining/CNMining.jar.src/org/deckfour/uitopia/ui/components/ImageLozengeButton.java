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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ImageLozengeButton
/*     */   extends ImageButton
/*     */ {
/*     */   private static final long serialVersionUID = -3411551781629196531L;
/*     */   private final String label;
/*  53 */   private Color labelColor = new Color(10, 10, 10);
/*     */   
/*     */ 
/*     */ 
/*     */   public ImageLozengeButton(Image icon, String label)
/*     */   {
/*  59 */     super(icon);
/*  60 */     this.label = label;
/*  61 */     setBorder(BorderFactory.createEmptyBorder());
/*  62 */     adjustSize();
/*     */   }
/*     */   
/*     */ 
/*     */   public ImageLozengeButton(Image icon, String label, Color colorBgPassive, Color colorBgMouseOver, int border)
/*     */   {
/*  68 */     super(icon, colorBgPassive, colorBgMouseOver, border);
/*  69 */     this.label = label;
/*  70 */     setBorder(BorderFactory.createEmptyBorder());
/*  71 */     adjustSize();
/*     */   }
/*     */   
/*     */   public void setLabelColor(Color labelColor) {
/*  75 */     this.labelColor = labelColor;
/*     */   }
/*     */   
/*     */   private void adjustSize() {
/*  79 */     setFont(getFont().deriveFont(13.0F));
/*  80 */     int height = this.icon.getHeight(null) + this.border + this.border + 2;
/*  81 */     int width = this.icon.getWidth(null) + this.border + this.border + 2;
/*  82 */     if (this.label != null) {
/*  83 */       width += this.label.length() * 8 + 10;
/*     */     }
/*  85 */     Dimension dim = new Dimension(width, height);
/*  86 */     setMinimumSize(dim);
/*  87 */     setMaximumSize(dim);
/*  88 */     setPreferredSize(dim);
/*  89 */     setSize(dim);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  97 */     Graphics2D g2d = (Graphics2D)g.create();
/*  98 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  99 */     boolean mouseOver = getModel().isRollover();
/* 100 */     int width = getWidth();
/* 101 */     int height = getHeight();
/* 102 */     if (!isEnabled()) {
/* 103 */       g2d.setColor(this.colorBgPassive);
/* 104 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.3F));
/* 105 */     } else if (mouseOver) {
/* 106 */       g2d.setColor(this.colorBgMouseOver);
/*     */     } else {
/* 108 */       g2d.setColor(this.colorBgPassive);
/*     */     }
/* 110 */     g2d.fillRoundRect(0, 0, width - 1, height - 1, height, height);
/* 111 */     if (!isEnabled()) {
/* 112 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.3F));
/* 113 */     } else if (!mouseOver) {
/* 114 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.8F));
/*     */     }
/* 116 */     int iconX = this.border + 5;
/* 117 */     g2d.drawImage(this.icon, iconX, this.border + 1, null);
/* 118 */     g2d.setFont(getFont());
/* 119 */     g2d.setColor(this.labelColor);
/* 120 */     g2d.drawString(this.label, iconX + 5 + this.icon.getWidth(null), height / 2 + 5);
/* 121 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ImageLozengeButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */