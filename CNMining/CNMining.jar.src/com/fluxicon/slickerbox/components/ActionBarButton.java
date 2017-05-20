/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Icon;
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
/*     */ 
/*     */ public class ActionBarButton
/*     */   extends JButton
/*     */   implements MouseListener
/*     */ {
/*  65 */   protected static Color bgBezelColor = new Color(160, 160, 160, 220);
/*  66 */   protected static Color bgBezelActiveColor = new Color(200, 10, 10, 220);
/*     */   protected boolean isMouseOver;
/*     */   protected boolean isMousePressed;
/*     */   
/*     */   public ActionBarButton(Action action) {
/*  71 */     super(action);
/*  72 */     this.isMouseOver = false;
/*  73 */     this.isMousePressed = false;
/*  74 */     addMouseListener(this);
/*  75 */     setMaximumSize(new Dimension(32, 32));
/*  76 */     setMinimumSize(new Dimension(32, 32));
/*  77 */     setPreferredSize(new Dimension(32, 32));
/*  78 */     setBorder(BorderFactory.createEmptyBorder());
/*  79 */     setOpaque(false);
/*     */   }
/*     */   
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/*  85 */     int width = getWidth();
/*  86 */     int height = getHeight();
/*  87 */     Graphics2D g2d = (Graphics2D)g;
/*  88 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*  89 */     if (this.isMouseOver)
/*     */     {
/*  91 */       if (this.isMousePressed) {
/*  92 */         g2d.setColor(bgBezelActiveColor);
/*     */       } else {
/*  94 */         g2d.setColor(bgBezelColor);
/*     */       }
/*  96 */       g2d.fillRoundRect(0, 0, width - 1, height - 1, 7, 7);
/*     */     }
/*  98 */     Icon icon = getIcon();
/*  99 */     icon.paintIcon(this, g2d, 
/* 100 */       (width - icon.getIconWidth()) / 2, 
/* 101 */       (height - icon.getIconHeight()) / 2);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent arg0) {}
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent arg0)
/*     */   {
/* 112 */     this.isMouseOver = true;
/* 113 */     repaint();
/*     */   }
/*     */   
/*     */   public void mouseExited(MouseEvent arg0) {
/* 117 */     this.isMouseOver = false;
/* 118 */     repaint();
/*     */   }
/*     */   
/*     */   public void mousePressed(MouseEvent arg0) {
/* 122 */     this.isMousePressed = true;
/*     */   }
/*     */   
/*     */   public void mouseReleased(MouseEvent arg0) {
/* 126 */     this.isMousePressed = false;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/ActionBarButton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */