/*     */ package com.fluxicon.slickerbox.components.glasspane;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.RuntimeUtils;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.PrintStream;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComponent;
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
/*     */ 
/*     */ 
/*     */ public class GlassCenteredPane
/*     */   extends JPanel
/*     */ {
/*     */   protected ActionListener closeListener;
/*     */   protected GraphicsConfiguration gc;
/*  74 */   protected BufferedImage parentBuffer = null;
/*  75 */   protected BufferedImage childrenBuffer = null;
/*     */   protected JComponent dialogPane;
/*  77 */   protected float alpha = 0.0F;
/*  78 */   protected Thread alphaThread = null;
/*     */   
/*     */   public GlassCenteredPane(JComponent dialogPane, ActionListener closeListener) {
/*  81 */     this.closeListener = closeListener;
/*  82 */     this.dialogPane = dialogPane;
/*  83 */     this.alpha = 0.0F;
/*  84 */     setLayout(new BoxLayout(this, 0));
/*  85 */     setBorder(BorderFactory.createEmptyBorder());
/*  86 */     JPanel innerPanel = new JPanel();
/*  87 */     innerPanel.setLayout(new BoxLayout(innerPanel, 1));
/*  88 */     innerPanel.setOpaque(false);
/*  89 */     innerPanel.setBorder(BorderFactory.createEmptyBorder());
/*  90 */     innerPanel.add(Box.createVerticalGlue());
/*  91 */     innerPanel.add(this.dialogPane);
/*  92 */     innerPanel.add(Box.createVerticalGlue());
/*  93 */     add(Box.createHorizontalGlue());
/*  94 */     add(innerPanel);
/*  95 */     add(Box.createHorizontalGlue());
/*     */   }
/*     */   
/*     */   public void fadeIn() {
/*  99 */     while (this.alphaThread != null) {
/*     */       try {
/* 101 */         Thread.sleep(100L);
/*     */       }
/*     */       catch (InterruptedException e) {
/* 104 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 107 */     setVisible(false);
/* 108 */     this.alpha = 0.0F;
/* 109 */     Container parent = getParent();
/* 110 */     this.parentBuffer = createCompatibleImage(parent.getWidth(), parent.getHeight());
/* 111 */     getParent().paint(this.parentBuffer.getGraphics());
/* 112 */     setOpaque(true);
/* 113 */     setVisible(true);
/* 114 */     this.childrenBuffer = null;
/* 115 */     this.alphaThread = new Thread() {
/*     */       public void run() {
/* 117 */         while (GlassCenteredPane.this.alpha < 1.0F) {
/* 118 */           GlassCenteredPane.this.alpha += 0.08F;
/* 119 */           if (GlassCenteredPane.this.alpha > 1.0F) {
/* 120 */             GlassCenteredPane.this.alpha = 1.0F;
/* 121 */             GlassCenteredPane.this.childrenBuffer = null;
/*     */           }
/* 123 */           GlassCenteredPane.this.repaint();
/*     */           try {
/* 125 */             Thread.sleep(40L);
/*     */           }
/*     */           catch (InterruptedException e) {
/* 128 */             e.printStackTrace();
/*     */           }
/*     */         }
/* 131 */         GlassCenteredPane.this.alphaThread = null;
/*     */       }
/* 133 */     };
/* 134 */     this.alphaThread.start();
/*     */   }
/*     */   
/*     */   public void fadeOut() {
/* 138 */     while (this.alphaThread != null) {
/*     */       try {
/* 140 */         Thread.sleep(100L);
/*     */       }
/*     */       catch (InterruptedException e) {
/* 143 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 146 */     this.parentBuffer = null;
/* 147 */     setOpaque(false);
/* 148 */     this.alphaThread = new Thread() {
/*     */       public void run() {
/* 150 */         while (GlassCenteredPane.this.alpha > 0.0F) {
/* 151 */           GlassCenteredPane.this.alpha -= 0.1F;
/* 152 */           if (GlassCenteredPane.this.alpha < 0.0F) {
/* 153 */             GlassCenteredPane.this.alpha = 0.0F;
/* 154 */             GlassCenteredPane.this.childrenBuffer = null;
/*     */           }
/* 156 */           GlassCenteredPane.this.repaint();
/*     */           try {
/* 158 */             Thread.sleep(40L);
/*     */           }
/*     */           catch (InterruptedException e) {
/* 161 */             e.printStackTrace();
/*     */           }
/*     */         }
/* 164 */         GlassCenteredPane.this.setVisible(false);
/* 165 */         if (GlassCenteredPane.this.closeListener != null) {
/* 166 */           GlassCenteredPane.this.closeListener.actionPerformed(new ActionEvent(this, 0, "Glass centered pane closed"));
/*     */         } else {
/* 168 */           System.err.println("no close listener in GlassCenteredPane (slickerbox)");
/*     */         }
/* 170 */         GlassCenteredPane.this.alphaThread = null;
/*     */       }
/* 172 */     };
/* 173 */     this.alphaThread.start();
/*     */   }
/*     */   
/*     */   protected BufferedImage createCompatibleImage(int width, int height) {
/* 177 */     if (this.gc == null) {
/* 178 */       this.gc = getGraphicsConfiguration();
/* 179 */       if (this.gc == null) {
/* 180 */         this.gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
/*     */       }
/*     */     }
/* 183 */     return this.gc.createCompatibleImage(width, height, 3);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 191 */     if ((!isOpaque()) && (!isVisible())) {
/* 192 */       return;
/*     */     }
/* 194 */     int width = getWidth();
/* 195 */     int height = getHeight();
/* 196 */     Graphics2D g2d = (Graphics2D)g.create();
/* 197 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/* 198 */     if (isOpaque())
/*     */     {
/* 200 */       g2d.drawImage(this.parentBuffer, 0, 0, width, height, this);
/*     */     }
/* 202 */     if (this.alpha < 1.0F) {
/* 203 */       g2d.setComposite(AlphaComposite.getInstance(3, this.alpha));
/* 204 */       g2d.setColor(new Color(0, 0, 0, 160));
/* 205 */       g2d.fillRect(0, 0, width, height);
/*     */     } else {
/* 207 */       g2d.setComposite(AlphaComposite.SrcOver);
/* 208 */       g2d.setColor(new Color(0, 0, 0, 160));
/* 209 */       g2d.fillRect(0, 0, width, height);
/*     */     }
/* 211 */     g2d.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintBorder(Graphics g) {}
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintChildren(Graphics g)
/*     */   {
/* 229 */     Graphics2D g2d = (Graphics2D)g.create();
/* 230 */     g2d.setComposite(AlphaComposite.getInstance(3, this.alpha));
/* 231 */     if (this.alpha < 1.0F) {
/* 232 */       if (this.childrenBuffer == null) { BufferedImage cBuf;
/*     */         BufferedImage cBuf;
/* 234 */         if (RuntimeUtils.isRunningMacOsX())
/*     */         {
/* 236 */           cBuf = createCompatibleImage(getParent().getWidth(), getParent().getHeight());
/*     */         }
/*     */         else {
/* 239 */           cBuf = new BufferedImage(getParent().getWidth(), getParent().getHeight(), 2);
/*     */         }
/* 241 */         Graphics2D g2db = cBuf.createGraphics();
/* 242 */         g2db.setClip(g.getClip());
/* 243 */         g2db.setComposite(AlphaComposite.Clear);
/* 244 */         g2db.setColor(new Color(255, 0, 0, 255));
/* 245 */         g2db.fillRect(0, 0, cBuf.getWidth(), cBuf.getHeight());
/* 246 */         g2db.setComposite(AlphaComposite.SrcOver);
/* 247 */         super.paintChildren(g2db);
/* 248 */         this.childrenBuffer = cBuf;
/*     */       }
/* 250 */       g2d.drawImage(this.childrenBuffer, 0, 0, null);
/*     */     } else {
/* 252 */       this.childrenBuffer = null;
/* 253 */       super.paintChildren(g2d);
/*     */     }
/* 255 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/glasspane/GlassCenteredPane.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */