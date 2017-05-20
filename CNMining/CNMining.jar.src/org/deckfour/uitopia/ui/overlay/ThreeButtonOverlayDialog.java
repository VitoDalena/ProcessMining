/*     */ package org.deckfour.uitopia.ui.overlay;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.ui.components.ImageLozengeButton;
/*     */ import org.deckfour.uitopia.ui.main.MainView;
/*     */ 
/*     */ public class ThreeButtonOverlayDialog extends AbstractOverlayDialog
/*     */ {
/*     */   private static final long serialVersionUID = -7327894009448217680L;
/*     */   protected MainView mainView;
/*     */   private JPanel content;
/*     */   private Result result;
/*     */   
/*     */   public static enum Result
/*     */   {
/*  24 */     CANCEL,  PREVIOUS,  CONTINUE;
/*     */     
/*     */ 
/*     */     private Result() {}
/*     */   }
/*     */   
/*  30 */   protected boolean closed = false;
/*     */   
/*     */   protected ImageLozengeButton cancelButton;
/*     */   protected ImageLozengeButton prevButton;
/*     */   protected ImageLozengeButton contButton;
/*     */   
/*     */   public ThreeButtonOverlayDialog(MainView mainView, String title, String cancelString, String prevString, String continueString, JComponent payload)
/*     */   {
/*  38 */     super(title);
/*  39 */     this.mainView = mainView;
/*  40 */     setOpaque(false);
/*  41 */     setLayout(new BorderLayout());
/*  42 */     setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
/*  43 */     JPanel header = new JPanel();
/*  44 */     header.setOpaque(false);
/*  45 */     header.setMinimumSize(new Dimension(400, 40));
/*  46 */     header.setMaximumSize(new Dimension(1000, 40));
/*  47 */     header.setPreferredSize(new Dimension(800, 40));
/*  48 */     header.setLayout(new BorderLayout());
/*  49 */     header.setBorder(BorderFactory.createEmptyBorder());
/*     */     
/*  51 */     this.content = new JPanel();
/*  52 */     this.content.setOpaque(false);
/*  53 */     this.content.setLayout(new BorderLayout());
/*  54 */     this.content.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
/*  55 */     if (payload != null) {
/*  56 */       this.content.add(payload, "Center");
/*     */     }
/*  58 */     JPanel footer = new JPanel();
/*  59 */     footer.setOpaque(false);
/*  60 */     footer.setMinimumSize(new Dimension(400, 40));
/*  61 */     footer.setMaximumSize(new Dimension(1000, 40));
/*  62 */     footer.setPreferredSize(new Dimension(800, 40));
/*  63 */     footer.setLayout(new javax.swing.BoxLayout(footer, 0));
/*  64 */     footer.setBorder(BorderFactory.createEmptyBorder());
/*  65 */     this.cancelButton = new ImageLozengeButton(org.deckfour.uitopia.ui.util.ImageLoader.load("cancel_white_30x30.png"), cancelString, new Color(90, 0, 0), new Color(160, 0, 0), 4);
/*     */     
/*     */ 
/*  68 */     this.cancelButton.setLabelColor(Color.white);
/*  69 */     this.cancelButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  71 */         ThreeButtonOverlayDialog.this.close(ThreeButtonOverlayDialog.Result.CANCEL);
/*     */       }
/*     */       
/*  74 */     });
/*  75 */     this.prevButton = new ImageLozengeButton(org.deckfour.uitopia.ui.util.ImageLoader.load("prev_white_30x30.png"), prevString, new Color(0, 90, 0), new Color(0, 140, 0), 4);
/*     */     
/*     */ 
/*  78 */     this.prevButton.setLabelColor(Color.white);
/*  79 */     this.prevButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  81 */         ThreeButtonOverlayDialog.this.close(ThreeButtonOverlayDialog.Result.PREVIOUS);
/*     */       }
/*  83 */     });
/*  84 */     this.contButton = new ImageLozengeButton(org.deckfour.uitopia.ui.util.ImageLoader.load("next_white_30x30.png"), continueString, new Color(0, 90, 0), new Color(0, 140, 0), 4);
/*     */     
/*     */ 
/*  87 */     this.contButton.setLabelColor(Color.white);
/*  88 */     this.contButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  90 */         ThreeButtonOverlayDialog.this.close(ThreeButtonOverlayDialog.Result.CONTINUE);
/*     */       }
/*  92 */     });
/*  93 */     footer.add(Box.createHorizontalStrut(10));
/*  94 */     footer.add(this.cancelButton);
/*  95 */     footer.add(Box.createHorizontalGlue());
/*  96 */     footer.add(this.prevButton);
/*  97 */     footer.add(Box.createHorizontalStrut(10));
/*  98 */     footer.add(this.contButton);
/*  99 */     footer.add(Box.createHorizontalStrut(10));
/* 100 */     add(this.content, "Center");
/* 101 */     add(header, "North");
/* 102 */     add(footer, "South");
/*     */   }
/*     */   
/*     */   public void setPayload(JComponent payload) {
/* 106 */     this.content.removeAll();
/* 107 */     this.content.add(payload, "Center");
/* 108 */     this.content.revalidate();
/* 109 */     this.content.repaint();
/*     */   }
/*     */   
/*     */   public void setMainView(MainView parent) {
/* 113 */     this.mainView = parent;
/*     */   }
/*     */   
/*     */   private void close(Result result) {
/* 117 */     synchronized (this) {
/* 118 */       this.result = result;
/* 119 */       this.closed = true;
/* 120 */       notify();
/*     */     }
/*     */   }
/*     */   
/*     */   protected Result getResult() {
/* 125 */     return this.result;
/*     */   }
/*     */   
/*     */   public Result getResultBlocking() {
/* 129 */     while (!this.closed) {
/*     */       try {
/* 131 */         synchronized (this) {
/* 132 */           wait();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException e) {
/* 136 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 139 */     this.mainView.hideOverlay();
/* 140 */     return getResult();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/ThreeButtonOverlayDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */