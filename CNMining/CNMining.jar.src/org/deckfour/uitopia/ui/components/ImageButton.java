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
/*     */ public class ImageButton
/*     */   extends JButton
/*     */ {
/*     */   private static final long serialVersionUID = -3471403169411666592L;
/*     */   protected Image icon;
/*     */   protected final Color colorBgPassive;
/*     */   protected final Color colorBgMouseOver;
/*     */   protected final int border;
/*     */   
/*     */   public ImageButton(Image icon)
/*     */   {
/*  59 */     this(icon, new Color(140, 140, 140), new Color(40, 140, 40), 2);
/*     */   }
/*     */   
/*     */   public ImageButton(Image icon, Color colorBgPassive, Color colorBgMouseOver, int border)
/*     */   {
/*  64 */     this.icon = icon;
/*  65 */     this.colorBgPassive = colorBgPassive;
/*  66 */     this.colorBgMouseOver = colorBgMouseOver;
/*  67 */     this.border = border;
/*  68 */     setBorder(BorderFactory.createEmptyBorder());
/*  69 */     int width = icon.getWidth(null) + border + border + 2;
/*  70 */     int height = icon.getHeight(null) + border + border + 2;
/*  71 */     Dimension dim = new Dimension(width, height);
/*  72 */     setMinimumSize(dim);
/*  73 */     setMaximumSize(dim);
/*  74 */     setPreferredSize(dim);
/*  75 */     setSize(dim);
/*  76 */     setOpaque(false);
/*  77 */     setRolloverEnabled(true);
/*     */   }
/*     */   
/*     */   public void setIcon(Image icon) {
/*  81 */     this.icon = icon;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  91 */     Graphics2D g2d = (Graphics2D)g.create();
/*  92 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*  94 */     boolean mouseOver = getModel().isRollover();
/*  95 */     int width = getWidth();
/*  96 */     int height = getHeight();
/*  97 */     int size = (width > height ? height : width) - 1;
/*  98 */     if (mouseOver) {
/*  99 */       g2d.setColor(this.colorBgMouseOver);
/*     */     } else {
/* 101 */       g2d.setColor(this.colorBgPassive);
/*     */     }
/* 103 */     g2d.fillOval(0, 0, size, size);
/* 104 */     if (!mouseOver) {
/* 105 */       g2d.setComposite(AlphaComposite.getInstance(3, 0.8F));
/*     */     }
/*     */     
/* 108 */     g2d.drawImage(this.icon, this.border + 1, this.border + 1, null);
/* 109 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ImageButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */