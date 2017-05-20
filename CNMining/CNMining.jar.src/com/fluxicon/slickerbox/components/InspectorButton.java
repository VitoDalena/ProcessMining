/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JToolTip;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class InspectorButton
/*     */   extends JButton
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*  64 */   protected Color colorIconActive = new Color(220, 220, 220);
/*  65 */   protected Color colorIconOver = new Color(160, 160, 160);
/*  66 */   protected Color colorIconPassive = new Color(120, 120, 120);
/*  67 */   protected Color colorButtonActive = new Color(220, 0, 0);
/*  68 */   protected Color colorButtonOver = new Color(30, 30, 30);
/*  69 */   protected Color colorButtonPassive = new Color(60, 60, 60);
/*     */   protected boolean mouseOver;
/*     */   protected boolean buttonDown;
/*     */   
/*     */   public InspectorButton()
/*     */   {
/*  75 */     setOpaque(false);
/*  76 */     setBorder(BorderFactory.createEmptyBorder());
/*  77 */     addMouseListener(this);
/*  78 */     addMouseMotionListener(this);
/*  79 */     this.mouseOver = false;
/*  80 */     this.buttonDown = false;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setColors(Color iconActive, Color iconOver, Color iconPassive, Color buttonActive, Color buttonOver, Color buttonPassive)
/*     */   {
/*  90 */     this.colorIconActive = iconActive;
/*  91 */     this.colorIconOver = iconOver;
/*  92 */     this.colorIconPassive = iconPassive;
/*  93 */     this.colorButtonActive = buttonActive;
/*  94 */     this.colorButtonOver = buttonOver;
/*  95 */     this.colorButtonPassive = buttonPassive;
/*  96 */     if (isVisible()) {
/*  97 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 106 */     int width = getWidth();
/* 107 */     int height = getHeight();
/* 108 */     int size = Math.min(width, height);
/* 109 */     int horizPadding = (width - size) / 2;
/* 110 */     int vertPadding = (height - size) / 2;
/* 111 */     Graphics2D g2d = (Graphics2D)g;
/* 112 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 113 */     if (isOpaque())
/*     */     {
/* 115 */       g2d.clearRect(0, 0, width, height); }
/*     */     Color iconColor;
/*     */     Color bgColor;
/*     */     Color iconColor;
/* 119 */     if (this.buttonDown) {
/* 120 */       Color bgColor = this.colorButtonActive;
/* 121 */       iconColor = this.colorIconActive; } else { Color iconColor;
/* 122 */       if (this.mouseOver) {
/* 123 */         Color bgColor = this.colorButtonOver;
/* 124 */         iconColor = this.colorIconOver;
/*     */       } else {
/* 126 */         bgColor = this.colorButtonPassive;
/* 127 */         iconColor = this.colorIconPassive;
/*     */       }
/*     */     }
/* 130 */     g2d.setColor(bgColor);
/* 131 */     g2d.fillOval(horizPadding, vertPadding, size - 1, size - 1);
/*     */     
/* 133 */     int innerBorder = (int)(size * 0.22D);
/* 134 */     int iconSize = size - innerBorder - innerBorder;
/* 135 */     int ovalSize = (int)(iconSize * 0.75D);
/* 136 */     Graphics2D gCopy = (Graphics2D)g2d.create();
/* 137 */     AffineTransform trans = gCopy.getTransform();
/* 138 */     trans.rotate(Math.toRadians(40.0D), width / 2 - 1, height / 2 - 1);
/* 139 */     gCopy.setTransform(trans);
/* 140 */     if (ovalSize > 5) {
/* 141 */       gCopy.setStroke(new BasicStroke(2.0F, 1, 1));
/*     */     }
/* 143 */     gCopy.setColor(iconColor);
/* 144 */     gCopy.drawOval(horizPadding + innerBorder, 
/* 145 */       height / 2 - ovalSize / 2, 
/* 146 */       ovalSize - 1, 
/* 147 */       ovalSize - 1);
/* 148 */     gCopy.drawLine(horizPadding + innerBorder + ovalSize, 
/* 149 */       height / 2, 
/* 150 */       width - horizPadding - innerBorder, 
/* 151 */       height / 2);
/* 152 */     gCopy.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseDragged(MouseEvent evt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseMoved(MouseEvent evt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent evt) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent evt)
/*     */   {
/* 180 */     this.mouseOver = true;
/* 181 */     if (isDisplayable()) {
/* 182 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent evt)
/*     */   {
/* 190 */     this.mouseOver = false;
/* 191 */     if (isDisplayable()) {
/* 192 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent evt)
/*     */   {
/* 200 */     this.buttonDown = true;
/* 201 */     if (isDisplayable()) {
/* 202 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent evt)
/*     */   {
/* 210 */     this.buttonDown = false;
/* 211 */     if (isDisplayable()) {
/* 212 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public JToolTip createToolTip()
/*     */   {
/* 221 */     return new PToolTip();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/InspectorButton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */