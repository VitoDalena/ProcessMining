/*     */ package com.fluxicon.slickerbox.components;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.geom.Ellipse2D.Float;
/*     */ import java.util.ArrayList;
/*     */ import javax.swing.JComponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PlayButton
/*     */   extends JComponent
/*     */   implements MouseListener, MouseMotionListener
/*     */ {
/*  67 */   protected int size = 40;
/*  68 */   protected int border = 3;
/*  69 */   protected int iconBorder = 10;
/*  70 */   protected boolean isPlay = true;
/*  71 */   protected boolean mouseOver = false;
/*  72 */   protected boolean mouseDown = false;
/*     */   
/*  74 */   protected ArrayList<ActionListener> listeners = new ArrayList();
/*     */   
/*     */   public PlayButton() {
/*  77 */     Dimension dim = new Dimension(this.size, this.size);
/*  78 */     setMinimumSize(dim);
/*  79 */     setMaximumSize(dim);
/*  80 */     setPreferredSize(dim);
/*  81 */     addMouseListener(this);
/*  82 */     addMouseMotionListener(this);
/*     */   }
/*     */   
/*     */   public void setPlay(boolean showPlay) {
/*  86 */     this.isPlay = showPlay;
/*  87 */     repaint();
/*     */   }
/*     */   
/*     */   public boolean isPlay() {
/*  91 */     return this.isPlay;
/*     */   }
/*     */   
/*     */   public void addActionListener(ActionListener l) {
/*  95 */     this.listeners.add(l);
/*     */   }
/*     */   
/*     */   protected void paintComponent(Graphics g) {
/*  99 */     Graphics2D g2d = (Graphics2D)g;
/* 100 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 101 */     int width = getWidth();
/* 102 */     int height = getHeight();
/*     */     
/* 104 */     Ellipse2D.Float clip = new Ellipse2D.Float(this.border, this.border, 
/* 105 */       width - this.border - this.border, height - this.border - this.border);
/* 106 */     GradientPaint gradient = new GradientPaint(0.0F, height / 2, new Color(20, 20, 20), 
/* 107 */       0.0F, height, new Color(10, 10, 10), false);
/* 108 */     g2d.setPaint(gradient);
/* 109 */     g2d.fill(clip);
/* 110 */     Shape oldClip = g2d.getClip();
/* 111 */     g2d.setClip(clip);
/*     */     Color darkened;
/*     */     Color hilight;
/* 114 */     Color darkened; if (this.mouseDown) {
/* 115 */       Color hilight = new Color(220, 0, 0, 220);
/* 116 */       darkened = new Color(220, 0, 0, 140); } else { Color darkened;
/* 117 */       if (this.mouseOver) {
/* 118 */         Color hilight = new Color(200, 200, 200, 200);
/* 119 */         darkened = new Color(200, 200, 200, 110);
/*     */       } else {
/* 121 */         hilight = new Color(200, 200, 200, 130);
/* 122 */         darkened = new Color(200, 200, 200, 80);
/*     */       } }
/* 124 */     gradient = new GradientPaint(0.0F, height / 2, hilight, 
/* 125 */       0.0F, height, darkened, false);
/* 126 */     g2d.setPaint(gradient);
/* 127 */     int innerBorder = this.iconBorder / 2;
/* 128 */     if (this.isPlay)
/*     */     {
/* 130 */       int[] x = { this.iconBorder + innerBorder, width - this.iconBorder, this.iconBorder + innerBorder };
/* 131 */       int[] y = { this.iconBorder, height / 2, height - this.iconBorder };
/* 132 */       g2d.fillPolygon(x, y, 3);
/*     */     }
/*     */     else {
/* 135 */       int barWidth = (width - this.iconBorder - this.iconBorder - 4) / 3;
/* 136 */       g2d.fillRect(this.iconBorder + 2, this.iconBorder + 2, barWidth, height - this.iconBorder - this.iconBorder - 4);
/* 137 */       g2d.fillRect(width - this.iconBorder - barWidth - 2, this.iconBorder + 2, barWidth, height - this.iconBorder - this.iconBorder - 4);
/*     */     }
/*     */     
/* 140 */     gradient = new GradientPaint(0.0F, this.border, new Color(200, 200, 200, 10), 
/* 141 */       0.0F, height / 2, new Color(200, 200, 200, 40), false);
/* 142 */     g2d.setPaint(gradient);
/* 143 */     g2d.fillOval(-(width / 2), -(3 * (height / 2)), width * 2, height * 2);
/*     */     
/* 145 */     g2d.setClip(oldClip);
/* 146 */     g2d.setColor(new Color(0, 0, 0, 200));
/* 147 */     g2d.draw(new Ellipse2D.Float(this.border - 1, this.border - 1, width - this.border - this.border + 2, height - this.border - this.border + 2));
/* 148 */     g2d.setColor(new Color(0, 0, 0, 60));
/* 149 */     g2d.draw(new Ellipse2D.Float(this.border - 2, this.border - 2, width - this.border - this.border + 4, height - this.border - this.border + 4));
/* 150 */     g2d.draw(new Ellipse2D.Float(this.border + 1, this.border + 1, width - this.border - this.border - 2, height - this.border - this.border - 2));
/* 151 */     g2d.setColor(new Color(0, 0, 0, 220));
/* 152 */     g2d.draw(new Ellipse2D.Float(this.border, this.border, width - this.border - this.border, height - this.border - this.border));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/* 159 */     this.isPlay = (!this.isPlay);
/* 160 */     repaint();
/* 161 */     ActionEvent evt = new ActionEvent(this, 0, "click");
/* 162 */     for (ActionListener listener : this.listeners) {
/* 163 */       listener.actionPerformed(evt);
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseEntered(MouseEvent e)
/*     */   {
/* 171 */     this.mouseOver = true;
/* 172 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseExited(MouseEvent e)
/*     */   {
/* 179 */     this.mouseOver = false;
/* 180 */     this.mouseDown = false;
/* 181 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mousePressed(MouseEvent e)
/*     */   {
/* 188 */     this.mouseDown = true;
/* 189 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void mouseReleased(MouseEvent e)
/*     */   {
/* 196 */     this.mouseDown = false;
/* 197 */     repaint();
/*     */   }
/*     */   
/*     */   public void mouseDragged(MouseEvent e) {}
/*     */   
/*     */   public void mouseMoved(MouseEvent e) {}
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/PlayButton.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */