/*     */ package com.fluxicon.slickerbox.util;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.FullScreenHeaderBar;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.GraphicsDevice;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.image.BufferedImage;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JFrame;
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
/*     */ public class FullScreenView
/*     */ {
/*     */   public static void enterFullScreen(final JComponent view, String title, final JComponent parent, final ActionListener closeCallback, BufferedImage icon)
/*     */   {
/*  65 */     if (parent != null) {
/*  66 */       parent.remove(view);
/*     */     }
/*  68 */     final GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
/*  69 */     JFrame fullScreenFrame = new JFrame();
/*  70 */     fullScreenFrame.setResizable(false);
/*  71 */     if (!fullScreenFrame.isDisplayable()) {
/*  72 */       fullScreenFrame.setUndecorated(true);
/*     */     }
/*     */     
/*  75 */     JPanel content = new JPanel();
/*  76 */     content.setBorder(BorderFactory.createEmptyBorder());
/*  77 */     content.setLayout(new BorderLayout());
/*  78 */     FullScreenHeaderBar header = new FullScreenHeaderBar(title, icon);
/*  79 */     header.setCloseActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/*  81 */         FullScreenView.this.setVisible(false);
/*  82 */         gd.setFullScreenWindow(null);
/*  83 */         if (closeCallback != null) {
/*  84 */           closeCallback.actionPerformed(new ActionEvent(this, 1001, "fullscreen exited."));
/*     */         }
/*  86 */         if (parent != null) {
/*  87 */           parent.invalidate();
/*  88 */           parent.revalidate();
/*  89 */           parent.repaint();
/*     */         }
/*  91 */         view.invalidate();
/*  92 */         view.revalidate();
/*  93 */         view.repaint();
/*     */         try {
/*  95 */           Thread.sleep(150L);
/*     */         } catch (InterruptedException e) {
/*  97 */           e.printStackTrace();
/*     */         }
/*     */       }
/* 100 */     });
/* 101 */     content.add(header, "North");
/* 102 */     content.add(view, "Center");
/* 103 */     fullScreenFrame.setContentPane(content);
/* 104 */     gd.setFullScreenWindow(fullScreenFrame);
/* 105 */     content.invalidate();
/* 106 */     content.revalidate();
/* 107 */     content.repaint();
/*     */     try {
/* 109 */       Thread.sleep(150L);
/*     */     } catch (InterruptedException e) {
/* 111 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/util/FullScreenView.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */