/*     */ package org.deckfour.uitopia.ui.overlay;
/*     */ 
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.ui.components.ImageLozengeButton;
/*     */ import org.deckfour.uitopia.ui.main.MainView;
/*     */ import org.deckfour.uitopia.ui.main.Overlayable;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TwoButtonOverlayDialog
/*     */   extends AbstractOverlayDialog
/*     */ {
/*     */   private static final long serialVersionUID = 7882987384090142342L;
/*     */   private Overlayable mainView;
/*     */   private JPanel content;
/*  63 */   private boolean closed = false;
/*  64 */   private boolean okayed = false;
/*     */   
/*     */   private ImageLozengeButton cancelButton;
/*     */   
/*     */   private ImageLozengeButton okButton;
/*     */   
/*     */   private final JComponent payload;
/*     */   
/*     */   public TwoButtonOverlayDialog(Overlayable mainView, String title, String cancelString, String okayString, JComponent payload)
/*     */   {
/*  74 */     super(title);
/*  75 */     this.mainView = mainView;
/*  76 */     this.payload = payload;
/*  77 */     setOpaque(false);
/*  78 */     setLayout(new BorderLayout());
/*  79 */     setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
/*  80 */     JPanel header = new JPanel();
/*  81 */     header.setOpaque(false);
/*  82 */     header.setMinimumSize(new Dimension(400, 40));
/*  83 */     header.setMaximumSize(new Dimension(1000, 40));
/*  84 */     header.setPreferredSize(new Dimension(800, 40));
/*  85 */     header.setLayout(new BorderLayout());
/*  86 */     header.setBorder(BorderFactory.createEmptyBorder());
/*     */     
/*  88 */     this.content = new JPanel();
/*  89 */     this.content.setOpaque(false);
/*  90 */     this.content.setLayout(new BorderLayout());
/*  91 */     this.content.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
/*  92 */     if (payload != null) {
/*  93 */       this.content.add(payload, "Center");
/*     */     }
/*  95 */     JPanel footer = new JPanel();
/*  96 */     footer.setOpaque(false);
/*  97 */     footer.setMinimumSize(new Dimension(400, 40));
/*  98 */     footer.setMaximumSize(new Dimension(1000, 40));
/*  99 */     footer.setPreferredSize(new Dimension(800, 40));
/* 100 */     footer.setLayout(new BoxLayout(footer, 0));
/* 101 */     footer.setBorder(BorderFactory.createEmptyBorder());
/* 102 */     this.cancelButton = new ImageLozengeButton(ImageLoader.load("cancel_white_30x30.png"), cancelString, new Color(90, 0, 0), new Color(160, 0, 0), 4);
/*     */     
/*     */ 
/* 105 */     this.cancelButton.setLabelColor(Color.white);
/* 106 */     this.cancelButton.setMinimumSize(new Dimension(100, 25));
/* 107 */     this.cancelButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 109 */         TwoButtonOverlayDialog.this.close(false);
/*     */       }
/* 111 */     });
/* 112 */     this.okButton = new ImageLozengeButton(ImageLoader.load("ok_white_30x30.png"), okayString, new Color(0, 90, 0), new Color(0, 140, 0), 4);
/*     */     
/*     */ 
/* 115 */     this.okButton.setLabelColor(Color.white);
/* 116 */     this.okButton.setMinimumSize(new Dimension(100, 25));
/* 117 */     this.okButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 119 */         TwoButtonOverlayDialog.this.close(true);
/*     */       }
/* 121 */     });
/* 122 */     footer.add(Box.createHorizontalStrut(10));
/* 123 */     footer.add(this.cancelButton);
/* 124 */     footer.add(Box.createHorizontalGlue());
/* 125 */     footer.add(this.okButton);
/* 126 */     footer.add(Box.createHorizontalStrut(10));
/* 127 */     add(this.content, "Center");
/* 128 */     add(header, "North");
/* 129 */     add(footer, "South");
/*     */   }
/*     */   
/*     */   protected JComponent getPayload() {
/* 133 */     return this.payload;
/*     */   }
/*     */   
/*     */   protected JButton getOKButton() {
/* 137 */     return this.okButton;
/*     */   }
/*     */   
/*     */   protected JButton getCancelButton() {
/* 141 */     return this.cancelButton;
/*     */   }
/*     */   
/*     */   public void setPayload(JComponent payload) {
/* 145 */     this.content.removeAll();
/* 146 */     this.content.add(payload, "Center");
/* 147 */     this.content.revalidate();
/* 148 */     this.content.repaint();
/*     */   }
/*     */   
/*     */   public void setMainView(MainView parent) {
/* 152 */     this.mainView = parent;
/*     */   }
/*     */   
/*     */   public void close(boolean okayed) {
/* 156 */     synchronized (this) {
/* 157 */       this.okayed = okayed;
/* 158 */       this.closed = true;
/* 159 */       notify();
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean getResultBlocking() {
/* 164 */     while (!this.closed) {
/*     */       try {
/* 166 */         synchronized (this) {
/* 167 */           wait();
/*     */         }
/*     */       }
/*     */       catch (InterruptedException e) {
/* 171 */         e.printStackTrace();
/*     */       }
/*     */     }
/* 174 */     this.mainView.hideOverlay();
/* 175 */     return this.okayed;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/overlay/TwoButtonOverlayDialog.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */