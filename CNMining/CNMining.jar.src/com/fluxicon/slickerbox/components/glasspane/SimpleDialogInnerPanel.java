/*     */ package com.fluxicon.slickerbox.components.glasspane;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.ResetButton;
/*     */ import com.fluxicon.slickerbox.components.RoundedPanel;
/*     */ import com.fluxicon.slickerbox.util.RuntimeUtils;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JLabel;
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
/*     */ public class SimpleDialogInnerPanel
/*     */   extends RoundedPanel
/*     */ {
/*     */   protected GlassCenteredPane parent;
/*     */   protected JLabel headerLabel;
/*     */   protected JLabel messageLabel;
/*     */   protected RoundedPanel innerPanel;
/*     */   
/*     */   public SimpleDialogInnerPanel(String title, String message)
/*     */   {
/*  73 */     super(24, 5, 0);
/*  74 */     this.innerPanel = new RoundedPanel(20, 0, 5);
/*  75 */     this.innerPanel.setBackground(new Color(240, 240, 240, 120));
/*  76 */     this.innerPanel.setLayout(new BorderLayout());
/*  77 */     this.parent = null;
/*  78 */     setBackground(new Color(0, 0, 0, 180));
/*  79 */     setLayout(new BorderLayout());
/*  80 */     add(this.innerPanel, "Center");
/*  81 */     setMinimumSize(new Dimension(500, 170));
/*  82 */     setMaximumSize(new Dimension(500, 500));
/*  83 */     setPreferredSize(new Dimension(500, 220));
/*  84 */     ResetButton closeButton = new ResetButton(25);
/*  85 */     closeButton.setPassive(new Color(120, 120, 120));
/*  86 */     closeButton.setMouseOver(new Color(180, 180, 180));
/*  87 */     closeButton.setActive(new Color(220, 20, 20));
/*  88 */     closeButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent arg0) {
/*  90 */         SimpleDialogInnerPanel.this.parent.fadeOut();
/*     */       }
/*  92 */     });
/*  93 */     JPanel headerPanel = new JPanel();
/*  94 */     headerPanel.setOpaque(false);
/*  95 */     headerPanel.setLayout(new BoxLayout(headerPanel, 0));
/*  96 */     headerPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
/*  97 */     this.headerLabel = new JLabel(title);
/*  98 */     this.headerLabel.setOpaque(false);
/*  99 */     if (!RuntimeUtils.isRunningMacOsX()) {
/* 100 */       this.headerLabel.setFont(this.headerLabel.getFont().deriveFont(16.0F).deriveFont(1));
/*     */     } else {
/* 102 */       this.headerLabel.setFont(this.headerLabel.getFont().deriveFont(16.0F));
/*     */     }
/* 104 */     this.headerLabel.setForeground(new Color(240, 240, 240));
/* 105 */     headerPanel.add(this.headerLabel);
/* 106 */     headerPanel.add(Box.createHorizontalGlue());
/* 107 */     headerPanel.add(closeButton);
/* 108 */     add(headerPanel, "North");
/* 109 */     add(this.innerPanel, "Center");
/* 110 */     message = message.replaceAll("\\n", "<br>");
/* 111 */     message = "<html>" + message + "</html>";
/* 112 */     this.messageLabel = new JLabel(message);
/* 113 */     this.messageLabel.setFont(this.messageLabel.getFont().deriveFont(14.0F));
/* 114 */     this.messageLabel.setOpaque(false);
/* 115 */     this.messageLabel.setForeground(new Color(10, 10, 10));
/* 116 */     this.innerPanel.add(this.messageLabel, "Center");
/*     */   }
/*     */   
/*     */   public void setParent(GlassCenteredPane parent) {
/* 120 */     this.parent = parent;
/*     */   }
/*     */   
/*     */   public void setTitle(String title) {
/* 124 */     this.headerLabel.setText(title);
/* 125 */     revalidate();
/* 126 */     repaint();
/*     */   }
/*     */   
/*     */   public void setMessage(String message) {
/* 130 */     message = message.replaceAll("\\n", "<br>");
/* 131 */     message = "<html>" + message + "</html>";
/* 132 */     this.messageLabel.setText(message);
/*     */     
/*     */ 
/*     */ 
/* 136 */     repaint();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/com/fluxicon/slickerbox/components/glasspane/SimpleDialogInnerPanel.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */