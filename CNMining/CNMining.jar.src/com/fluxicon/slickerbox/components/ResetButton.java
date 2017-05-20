/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.BorderFactory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResetButton
/*     */   extends JButton
/*     */   implements MouseListener
/*     */ {
/*  67 */   protected Color COLOR_PASSIVE = new Color(100, 100, 100);
/*  68 */   protected Color COLOR_MOUSEOVER = new Color(40, 40, 40);
/*  69 */   protected Color COLOR_ACTIVE = new Color(100, 0, 0);
/*     */   
/*     */   protected int size;
/*     */   protected BufferedImage closePassive;
/*     */   protected BufferedImage closeMouseover;
/*     */   protected BufferedImage closeActive;
/*  75 */   protected boolean mouseOver = false;
/*  76 */   protected boolean buttonDown = false;
/*  77 */   protected boolean visible = true;
/*     */   
/*     */   public ResetButton(int size) {
/*  80 */     setBorder(BorderFactory.createEmptyBorder());
/*  81 */     addMouseListener(this);
/*  82 */     this.size = size;
/*  83 */     setOpaque(false);
/*  84 */     Dimension dim = new Dimension(size, size);
/*  85 */     setMinimumSize(dim);
/*  86 */     setMaximumSize(dim);
/*  87 */     setPreferredSize(dim);
/*  88 */     this.closePassive = createCloseImage(this.COLOR_PASSIVE, size);
/*  89 */     this.closeMouseover = createCloseImage(this.COLOR_MOUSEOVER, size);
/*  90 */     this.closeActive = createCloseImage(this.COLOR_ACTIVE, size);
/*     */   }
/*     */   
/*     */   public void setVisible(boolean visible) {
/*  94 */     this.visible = visible;
/*  95 */     repaint();
/*     */   }
/*     */   
/*     */   public void setPassive(Color color) {
/*  99 */     this.COLOR_PASSIVE = color;
/* 100 */     this.closePassive = createCloseImage(color, this.size);
/*     */   }
/*     */   
/*     */   public void setMouseOver(Color color) {
/* 104 */     this.COLOR_MOUSEOVER = color;
/* 105 */     this.closeMouseover = createCloseImage(color, this.size);
/*     */   }
/*     */   
/*     */   public void setActive(Color color) {
/* 109 */     this.COLOR_ACTIVE = color;
/* 110 */     this.closeActive = createCloseImage(color, this.size);
/*     */   }
/*     */   
/*     */   public static BufferedImage createCloseImage(Color color, int size) {
/* 114 */     BufferedImage closeImage = GraphicsUtilities.createTranslucentCompatibleImage(size, size);
/* 115 */     Graphics2D g2d = (Graphics2D)closeImage.getGraphics();
/* 116 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 117 */     g2d.setColor(color);
/* 118 */     g2d.fillOval(0, 0, size - 1, size - 1);
/* 119 */     AlphaComposite alphaComp = AlphaComposite.getInstance(8, 1.0F);
/* 120 */     g2d.setComposite(alphaComp);
/* 121 */     g2d.setColor(new Color(0, 0, 0, 255));
/* 122 */     double rotCenter = size / 2.0D;
/* 123 */     float stroke = size / 6.0F;
/* 124 */     int border = (int)stroke + 2;
/* 125 */     g2d.setStroke(new BasicStroke(stroke));
/* 126 */     Graphics2D g2dc = (Graphics2D)g2d.create();
/* 127 */     AffineTransform mod = g2dc.getTransform();
/* 128 */     mod.rotate(Math.toRadians(45.0D), rotCenter - 0.5D, rotCenter - 0.5D);
/* 129 */     g2dc.setTransform(mod);
/* 130 */     g2dc.drawLine(border, (int)rotCenter, size - border - 1, (int)rotCenter);
/* 131 */     g2dc.dispose();
/* 132 */     g2dc = (Graphics2D)g2d.create();
/* 133 */     mod = g2dc.getTransform();
/* 134 */     mod.rotate(Math.toRadians(135.0D), rotCenter - 0.5D, rotCenter - 0.5D);
/* 135 */     g2dc.setTransform(mod);
/* 136 */     g2dc.drawLine(border, (int)rotCenter, size - border - 1, (int)rotCenter);
/* 137 */     g2dc.dispose();
/* 138 */     g2d.dispose();
/* 139 */     return closeImage;
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/* 143 */     if (!this.visible) {
/* 144 */       return;
/*     */     }
/* 146 */     Graphics2D g2d = (Graphics2D)g;
/* 147 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 148 */     BufferedImage image = null;
/* 149 */     if (this.buttonDown) {
/* 150 */       image = this.closeActive;
/* 151 */     } else if (this.mouseOver) {
/* 152 */       image = this.closeMouseover;
/*     */     } else {
/* 154 */       image = this.closePassive;
/*     */     }
/* 156 */     g2d.drawImage(image, 0, 0, this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent arg0) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent arg0)
/*     */   {
/* 170 */     this.mouseOver = true;
/* 171 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent arg0)
/*     */   {
/* 178 */     this.mouseOver = false;
/* 179 */     this.buttonDown = false;
/* 180 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent arg0)
/*     */   {
/* 187 */     this.buttonDown = true;
/* 188 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent arg0)
/*     */   {
/* 195 */     this.buttonDown = false;
/* 196 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/ResetButton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */