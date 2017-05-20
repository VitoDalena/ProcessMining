/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import javax.swing.Action;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JPanel;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ActionBar
/*     */   extends JPanel
/*     */   implements MouseListener
/*     */ {
/*  71 */   protected static Color bgColorBright = new Color(220, 220, 220);
/*  72 */   protected static Color bgColorDark = new Color(150, 150, 150);
/*     */   
/*  74 */   protected static int[] xOpen = { 5, 10, 15 };
/*  75 */   protected static int[] yOpen = { 5, 15, 5 };
/*  76 */   protected static int[] xClose = { 5, 15, 5 };
/*  77 */   protected static int[] yClose = { 5, 10, 15 };
/*     */   protected boolean isExpanded;
/*     */   protected JPanel contents;
/*     */   protected JPanel buttons;
/*     */   protected ImageIcon logo;
/*     */   protected ActionListener logoListener;
/*     */   
/*     */   public ActionBar(ImageIcon logo, ActionListener logoListener)
/*     */   {
/*  86 */     this.logo = logo;
/*  87 */     this.logoListener = logoListener;
/*  88 */     setupUI();
/*  89 */     setExpanded(true);
/*  90 */     addMouseListener(this);
/*     */   }
/*     */   
/*     */   protected void setupUI() {
/*  94 */     setBorder(BorderFactory.createEmptyBorder(2, 5, 2, this.logo.getIconWidth() + 15));
/*  95 */     setLayout(new BorderLayout());
/*  96 */     this.contents = new JPanel();
/*  97 */     this.contents.setLayout(new BoxLayout(this.contents, 0));
/*  98 */     this.contents.setBorder(BorderFactory.createEmptyBorder(2, 10, 2, 10));
/*  99 */     this.contents.setOpaque(false);
/* 100 */     this.buttons = new JPanel();
/* 101 */     this.buttons.setLayout(new BoxLayout(this.buttons, 0));
/* 102 */     this.buttons.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 10));
/* 103 */     this.buttons.setOpaque(false);
/* 104 */     this.buttons.add(Box.createHorizontalStrut(15));
/*     */   }
/*     */   
/*     */   public JButton add(Action action) {
/* 108 */     ActionBarButton button = new ActionBarButton(action);
/* 109 */     this.buttons.add(button);
/* 110 */     return button;
/*     */   }
/*     */   
/*     */   public void addSeperator(int width) {
/* 114 */     this.buttons.add(Box.createRigidArea(new Dimension(width, 0)));
/*     */   }
/*     */   
/*     */   protected void setExpanded(boolean expanded) {
/* 118 */     this.isExpanded = expanded;
/* 119 */     if (expanded) {
/* 120 */       setMaximumSize(new Dimension(5000, 40));
/* 121 */       setMinimumSize(new Dimension(20, 40));
/* 122 */       setPreferredSize(new Dimension(1000, 40));
/* 123 */       add(this.buttons, "West");
/* 124 */       add(this.contents, "Center");
/*     */     } else {
/* 126 */       removeAll();
/* 127 */       setMaximumSize(new Dimension(5000, 20));
/* 128 */       setMinimumSize(new Dimension(20, 20));
/* 129 */       setPreferredSize(new Dimension(1000, 20));
/*     */     }
/* 131 */     revalidate();
/* 132 */     repaint();
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 137 */     int width = getWidth();
/* 138 */     int height = getHeight();
/* 139 */     Graphics2D g2d = (Graphics2D)g.create();
/* 140 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 142 */     GradientPaint gradient = new GradientPaint(20.0F, height / 3, bgColorBright, 20.0F, height, bgColorDark, false);
/* 143 */     g2d.setPaint(gradient);
/* 144 */     g2d.fillRect(0, 0, width, height);
/*     */     
/* 146 */     g2d.setColor(new Color(60, 60, 60));
/* 147 */     if (this.isExpanded) {
/* 148 */       g2d.fillPolygon(xOpen, yOpen, 3);
/*     */     } else {
/* 150 */       g2d.fillPolygon(xClose, yClose, 3);
/* 151 */       g2d.setFont(g2d.getFont().deriveFont(11.0F));
/* 152 */       g2d.drawString("click to expand", 20, height - 5);
/*     */     }
/*     */     
/* 155 */     if (this.isExpanded) {
/* 156 */       g2d.drawImage(this.logo.getImage(), width - this.logo.getIconWidth() - 5, (height - this.logo.getIconHeight()) / 2, this);
/*     */     }
/* 158 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e) {
/* 162 */     int x = e.getX();
/* 163 */     int y = e.getY();
/* 164 */     if ((x >= 5) && (x <= 15) && (y >= 5) && (y <= 15))
/*     */     {
/* 166 */       setExpanded(!this.isExpanded);
/* 167 */     } else if ((x > getWidth() - this.logo.getIconWidth() - 5) && 
/* 168 */       (this.logoListener != null)) {
/* 169 */       this.logoListener.actionPerformed(new ActionEvent(this, 0, "Logo clicked"));
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseEntered(MouseEvent e) {}
/*     */   
/*     */   public void mouseExited(MouseEvent e) {}
/*     */   
/*     */   public void mousePressed(MouseEvent e) {}
/*     */   
/*     */   public void mouseReleased(MouseEvent e) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/ActionBar.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */