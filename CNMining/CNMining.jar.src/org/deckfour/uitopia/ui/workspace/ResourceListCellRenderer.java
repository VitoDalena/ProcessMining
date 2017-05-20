/*     */ package org.deckfour.uitopia.ui.workspace;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceListCellRenderer
/*     */   extends JComponent
/*     */   implements ListCellRenderer
/*     */ {
/*     */   private static final long serialVersionUID = 595027368316969895L;
/*  62 */   private static final Color COLOR_PASSIVE_TOP = new Color(160, 160, 160);
/*  63 */   private static final Color COLOR_PASSIVE_BOTTOM = new Color(150, 150, 150);
/*  64 */   private static final Color COLOR_PASSIVE_TEXT = new Color(30, 30, 30);
/*  65 */   private static final Color COLOR_ACTIVE_TOP = new Color(80, 160, 80);
/*  66 */   private static final Color COLOR_ACTIVE_BOTTOM = new Color(40, 120, 40);
/*  67 */   private static final Color COLOR_ACTIVE_TEXT = new Color(0, 0, 0);
/*  68 */   private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
/*     */   
/*  70 */   private static final Image favoriteIcon = ImageLoader.load("favorite_30x30.png");
/*     */   
/*     */   private static final int HEIGHT = 60;
/*     */   
/*     */   private Resource resource;
/*     */   
/*     */   private boolean selected;
/*  77 */   private boolean paintArrow = true;
/*     */   
/*     */   public ResourceListCellRenderer() {
/*  80 */     this(true);
/*     */   }
/*     */   
/*     */   public ResourceListCellRenderer(boolean paintArrow) {
/*  84 */     this.paintArrow = paintArrow;
/*  85 */     setOpaque(true);
/*  86 */     setBorder(BorderFactory.createEmptyBorder());
/*  87 */     setMinimumSize(new Dimension(100, 60));
/*  88 */     setMaximumSize(new Dimension(1000, 60));
/*  89 */     setPreferredSize(new Dimension(250, 60));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  99 */     int height = getHeight();
/* 100 */     int width = getWidth();
/* 101 */     Graphics2D g2d = (Graphics2D)g.create();
/* 102 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 104 */     if (this.selected) {
/* 105 */       g2d.setPaint(new GradientPaint(0.0F, 0.0F, COLOR_ACTIVE_TOP, 0.0F, height, COLOR_ACTIVE_BOTTOM));
/*     */     } else {
/* 107 */       g2d.setPaint(new GradientPaint(0.0F, 0.0F, COLOR_PASSIVE_TOP, 0.0F, height, COLOR_PASSIVE_BOTTOM));
/*     */     }
/* 109 */     g2d.fillRect(0, 0, width, height);
/*     */     
/* 111 */     Image icon = this.resource.getType().getTypeIcon();
/* 112 */     int iconX = 10;
/* 113 */     int iconY = (height - icon.getHeight(null)) / 2;
/* 114 */     g2d.drawImage(icon, iconX, iconY, null);
/*     */     
/* 116 */     if (this.selected) {
/* 117 */       g2d.setPaint(new GradientPaint(width - 20, 0.0F, COLOR_ACTIVE_TEXT, width, 0.0F, COLOR_TRANSPARENT));
/*     */     } else {
/* 119 */       g2d.setPaint(new GradientPaint(width - 20, 0.0F, COLOR_PASSIVE_TEXT, width, 0.0F, COLOR_TRANSPARENT));
/*     */     }
/* 121 */     g2d.setFont(g2d.getFont().deriveFont(13.0F));
/* 122 */     int textX = 20 + icon.getWidth(null);
/* 123 */     int textY = 30;
/* 124 */     g2d.drawString(this.resource.getName(), textX, textY - 6);
/* 125 */     g2d.setFont(g2d.getFont().deriveFont(10.0F));
/* 126 */     g2d.drawString(this.resource.getType().getTypeName(), textX, textY + 10);
/* 127 */     if (this.resource.isFavorite()) {
/* 128 */       int fwidth = favoriteIcon.getWidth(null);
/* 129 */       int fheight = favoriteIcon.getHeight(null);
/* 130 */       if (!this.selected) {
/* 131 */         g2d.setComposite(AlphaComposite.getInstance(3, 0.6F));
/*     */       } else {
/* 133 */         g2d.setComposite(AlphaComposite.getInstance(3, 0.8F));
/*     */       }
/* 135 */       int posX = width - fwidth - 20;
/* 136 */       if (this.paintArrow) {
/* 137 */         posX -= 20;
/*     */       }
/* 139 */       g2d.drawImage(favoriteIcon, posX, (height - fheight) / 2, null);
/*     */     }
/* 141 */     if ((this.selected) && (this.paintArrow)) {
/* 142 */       g2d.setColor(new Color(10, 10, 10, 200));
/* 143 */       int yMid = height / 2;
/* 144 */       int[] x = { width - 30, width - 10, width - 30, width - 28 };
/* 145 */       int[] y = { yMid - 10, yMid, yMid + 10, yMid };
/* 146 */       g2d.fillPolygon(x, y, 4);
/*     */     }
/* 148 */     g2d.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */   {
/* 158 */     this.resource = ((Resource)value);
/* 159 */     this.selected = isSelected;
/* 160 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/ResourceListCellRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */