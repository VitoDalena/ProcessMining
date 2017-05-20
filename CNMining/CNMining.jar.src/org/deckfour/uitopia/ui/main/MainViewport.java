/*     */ package org.deckfour.uitopia.ui.main;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.GraphicsUtilities;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.OverlayLayout;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MainViewport
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -798381268357065577L;
/*     */   private JComponent view;
/*     */   private JComponent overlay;
/*     */   private BufferedImage backdrop;
/*     */   
/*     */   public MainViewport()
/*     */   {
/*  61 */     setOpaque(true);
/*  62 */     setBorder(BorderFactory.createEmptyBorder());
/*  63 */     setLayout(new OverlayLayout(this));
/*     */   }
/*     */   
/*     */   public synchronized void setView(JComponent view) {
/*  67 */     if (this.view != null) {
/*  68 */       remove(this.view);
/*     */     }
/*  70 */     if (this.overlay != null) {
/*  71 */       remove(this.overlay);
/*  72 */       this.overlay = null;
/*  73 */       this.backdrop = null;
/*     */     }
/*  75 */     this.view = view;
/*  76 */     add(view);
/*  77 */     revalidate();
/*  78 */     repaint();
/*     */   }
/*     */   
/*     */   public synchronized void showOverlay(JComponent overlay) {
/*  82 */     if (this.overlay != null) {
/*  83 */       remove(this.overlay);
/*  84 */       this.overlay = null;
/*  85 */       this.backdrop = null;
/*     */     }
/*  87 */     createBackdrop();
/*  88 */     if (this.view != null) {
/*  89 */       this.view.setVisible(false);
/*     */     }
/*  91 */     this.overlay = overlay;
/*  92 */     add(overlay);
/*  93 */     revalidate();
/*  94 */     repaint();
/*     */   }
/*     */   
/*     */   public synchronized void hideOverlay() {
/*  98 */     if (this.overlay != null) {
/*  99 */       remove(this.overlay);
/* 100 */       this.overlay = null;
/*     */     }
/* 102 */     this.backdrop = null;
/* 103 */     if (this.view != null) {
/* 104 */       this.view.setVisible(true);
/* 105 */       revalidate();
/* 106 */       repaint();
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized boolean hideOverlay(JComponent overlay) {
/* 111 */     for (Component c : this.overlay.getComponents()) {
/* 112 */       if (c == overlay) {
/* 113 */         hideOverlay();
/* 114 */         return true;
/*     */       }
/*     */     }
/* 117 */     return false;
/*     */   }
/*     */   
/*     */   private synchronized void createBackdrop() {
/* 121 */     int width = getWidth();
/* 122 */     int height = getHeight();
/* 123 */     if ((this.backdrop == null) || (this.backdrop.getWidth() != width) || (this.backdrop.getHeight() != height))
/*     */     {
/* 125 */       this.backdrop = GraphicsUtilities.createCompatibleImage(getWidth(), getHeight());
/*     */     }
/*     */     
/* 128 */     Graphics2D g2d = (Graphics2D)this.backdrop.getGraphics();
/* 129 */     this.view.paint(g2d);
/* 130 */     g2d.setColor(new Color(10, 10, 10, 125));
/* 131 */     g2d.fillRect(0, 0, getWidth(), getHeight());
/* 132 */     g2d.dispose();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 142 */     super.paintComponent(g);
/* 143 */     Graphics2D g2d = (Graphics2D)g.create();
/* 144 */     if (this.overlay != null) {
/* 145 */       g2d.drawImage(this.backdrop, 0, 0, getWidth(), getHeight(), null);
/*     */     }
/*     */     
/* 148 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/main/MainViewport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */