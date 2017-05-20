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
/*     */ import org.deckfour.uitopia.api.model.Author;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceTypeListCellRenderer
/*     */   extends JComponent
/*     */   implements ListCellRenderer
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  62 */   private static final Color COLOR_PASSIVE_TOP = new Color(160, 160, 160);
/*  63 */   private static final Color COLOR_PASSIVE_BOTTOM = new Color(150, 150, 150);
/*  64 */   private static final Color COLOR_PASSIVE_TEXT = new Color(30, 30, 30);
/*  65 */   private static final Color COLOR_ACTIVE_TOP = new Color(80, 160, 80);
/*  66 */   private static final Color COLOR_ACTIVE_BOTTOM = new Color(40, 120, 40);
/*  67 */   private static final Color COLOR_ACTIVE_TEXT = new Color(0, 0, 0);
/*  68 */   private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
/*     */   
/*     */   private static final int HEIGHT = 60;
/*     */   
/*     */   private ResourceType type;
/*     */   
/*     */   private boolean selected;
/*  75 */   private boolean paintArrow = true;
/*     */   
/*     */   public ResourceTypeListCellRenderer() {
/*  78 */     this(true);
/*     */   }
/*     */   
/*     */   public ResourceTypeListCellRenderer(boolean paintArrow) {
/*  82 */     this.paintArrow = paintArrow;
/*  83 */     setOpaque(true);
/*  84 */     setBorder(BorderFactory.createEmptyBorder());
/*  85 */     setMinimumSize(new Dimension(100, 60));
/*  86 */     setMaximumSize(new Dimension(1000, 60));
/*  87 */     setPreferredSize(new Dimension(250, 60));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  97 */     int height = getHeight();
/*  98 */     int width = getWidth();
/*  99 */     Graphics2D g2d = (Graphics2D)g.create();
/* 100 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 102 */     if (this.selected) {
/* 103 */       g2d.setPaint(new GradientPaint(0.0F, 0.0F, COLOR_ACTIVE_TOP, 0.0F, height, COLOR_ACTIVE_BOTTOM));
/*     */     } else {
/* 105 */       g2d.setPaint(new GradientPaint(0.0F, 0.0F, COLOR_PASSIVE_TOP, 0.0F, height, COLOR_PASSIVE_BOTTOM));
/*     */     }
/* 107 */     g2d.fillRect(0, 0, width, height);
/*     */     
/* 109 */     Image icon = this.type.getTypeIcon();
/* 110 */     int iconX = 10;
/* 111 */     int iconY = (height - icon.getHeight(null)) / 2;
/* 112 */     g2d.drawImage(icon, iconX, iconY, null);
/*     */     
/* 114 */     if (this.selected) {
/* 115 */       g2d.setPaint(new GradientPaint(width - 20, 0.0F, COLOR_ACTIVE_TEXT, width, 0.0F, COLOR_TRANSPARENT));
/*     */     } else {
/* 117 */       g2d.setPaint(new GradientPaint(width - 20, 0.0F, COLOR_PASSIVE_TEXT, width, 0.0F, COLOR_TRANSPARENT));
/*     */     }
/* 119 */     g2d.setFont(g2d.getFont().deriveFont(13.0F));
/* 120 */     int textX = 25 + icon.getWidth(null);
/* 121 */     int textY = 30;
/* 122 */     g2d.drawString(this.type.getTypeName(), textX, textY - 8);
/* 123 */     g2d.setFont(g2d.getFont().deriveFont(10.0F));
/* 124 */     g2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
/* 125 */     Author author = this.type.getTypeAuthor();
/* 126 */     g2d.drawString(author.getName() + " (" + author.getEmail() + ")", textX, textY + 10);
/* 127 */     g2d.setComposite(AlphaComposite.getInstance(3, 1.0F));
/* 128 */     if ((this.selected) && (this.paintArrow)) {
/* 129 */       g2d.setColor(new Color(10, 10, 10, 200));
/* 130 */       int yMid = height / 2;
/* 131 */       int[] x = { width - 30, width - 10, width - 30, width - 28 };
/* 132 */       int[] y = { yMid - 10, yMid, yMid + 10, yMid };
/* 133 */       g2d.fillPolygon(x, y, 4);
/*     */     }
/* 135 */     g2d.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */   {
/* 145 */     this.type = ((ResourceType)value);
/* 146 */     this.selected = isSelected;
/* 147 */     return this;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/ResourceTypeListCellRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */