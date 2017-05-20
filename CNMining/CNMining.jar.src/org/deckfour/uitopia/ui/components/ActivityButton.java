/*     */ package org.deckfour.uitopia.ui.components;
/*     */ 
/*     */ import com.fluxicon.slickerbox.ui.SlickerRoundProgressBarUI;
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.HierarchyEvent;
/*     */ import java.awt.event.HierarchyListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JProgressBar;
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
/*     */ public class ActivityButton
/*     */   extends JComponent
/*     */ {
/*     */   private static final long serialVersionUID = 6541201293757561659L;
/*  67 */   private static final Color COLOR_BG_PASSIVE = new Color(90, 90, 90, 100);
/*  68 */   private static final Color COLOR_BG_ACTIVE = new Color(10, 140, 10, 180);
/*  69 */   private static final Color COLOR_TEXT_PASSIVE = new Color(180, 180, 180);
/*  70 */   private static final Color COLOR_TEXT_ACTIVE = new Color(255, 255, 255);
/*     */   
/*  72 */   private static final Image ICON = ImageLoader.load("activity_white_30x30.png");
/*     */   
/*     */   private static final int HEIGHT = 40;
/*     */   
/*     */   private static final int WIDTH = 120;
/*     */   private final ActionListener listener;
/*     */   private JProgressBar progressBar;
/*     */   private boolean mouseOver;
/*     */   
/*     */   public ActivityButton(ActionListener actionListener)
/*     */   {
/*  83 */     this.listener = actionListener;
/*  84 */     Dimension dim = new Dimension(120, 40);
/*  85 */     setMinimumSize(dim);
/*  86 */     setMaximumSize(dim);
/*  87 */     setPreferredSize(dim);
/*  88 */     setBorder(BorderFactory.createEmptyBorder());
/*  89 */     setOpaque(false);
/*  90 */     this.mouseOver = false;
/*  91 */     addMouseListener(new MouseAdapter() {
/*     */       public void mouseClicked(MouseEvent e) {
/*  93 */         if (e.getButton() == 1) {
/*  94 */           ActivityButton.this.listener.actionPerformed(new ActionEvent(ActivityButton.this, 1001, "clicked"));
/*     */         }
/*     */       }
/*     */       
/*     */ 
/*     */       public void mouseEntered(MouseEvent e)
/*     */       {
/* 101 */         ActivityButton.this.mouseOver = true;
/* 102 */         ActivityButton.this.repaint();
/*     */       }
/*     */       
/*     */       public void mouseExited(MouseEvent e) {
/* 106 */         ActivityButton.this.mouseOver = false;
/* 107 */         ActivityButton.this.repaint();
/*     */       }
/* 109 */     });
/* 110 */     addHierarchyListener(new HierarchyListener() {
/*     */       public void hierarchyChanged(HierarchyEvent e) {
/* 112 */         ActivityButton.this.mouseOver = false;
/* 113 */         ActivityButton.this.repaint();
/*     */       }
/* 115 */     });
/* 116 */     this.progressBar = new JProgressBar(0, 100);
/* 117 */     this.progressBar.setUI(new SlickerRoundProgressBarUI(20, new Color(180, 180, 180)));
/*     */     
/* 119 */     this.progressBar.setIndeterminate(true);
/* 120 */     this.progressBar.addMouseListener(new MouseAdapter() {
/*     */       public void mouseClicked(MouseEvent e) {
/* 122 */         if (e.getButton() == 1) {
/* 123 */           ActivityButton.this.listener.actionPerformed(new ActionEvent(ActivityButton.this, 1001, "clicked"));
/*     */         }
/*     */       }
/*     */       
/*     */       public void mouseEntered(MouseEvent e)
/*     */       {
/* 129 */         ActivityButton.this.mouseOver = true;
/* 130 */         ActivityButton.this.repaint();
/*     */       }
/*     */       
/*     */       public void mouseExited(MouseEvent e) {
/* 134 */         ActivityButton.this.mouseOver = false;
/* 135 */         ActivityButton.this.repaint();
/*     */       }
/* 137 */     });
/* 138 */     setLayout(new BoxLayout(this, 0));
/* 139 */     add(Box.createHorizontalGlue());
/* 140 */     add(this.progressBar);
/* 141 */     add(Box.createHorizontalStrut(10));
/* 142 */     setAlignmentX(0.5F);
/* 143 */     setAlignmentY(0.5F);
/*     */   }
/*     */   
/*     */   public void setSpinning(boolean spin) {
/* 147 */     this.progressBar.setVisible(spin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 157 */     Graphics2D g2d = (Graphics2D)g.create();
/* 158 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 160 */     int width = getWidth();
/* 161 */     int height = getHeight();
/* 162 */     if (this.mouseOver) {
/* 163 */       g2d.setColor(COLOR_BG_ACTIVE);
/*     */     } else {
/* 165 */       g2d.setColor(COLOR_BG_PASSIVE);
/*     */     }
/* 167 */     g2d.fillRoundRect(0, 3, width - 1, height - 6, 15, 15);
/* 168 */     if (this.mouseOver) {
/* 169 */       g2d.setColor(COLOR_TEXT_ACTIVE);
/*     */     } else {
/* 171 */       g2d.setColor(COLOR_TEXT_PASSIVE);
/*     */     }
/* 173 */     g2d.setFont(getFont().deriveFont(13.0F));
/* 174 */     g2d.drawString("Activity...", 15, 25);
/* 175 */     if (!this.progressBar.isVisible()) {
/* 176 */       if (!this.mouseOver) {
/* 177 */         g2d.setComposite(AlphaComposite.getInstance(3, 0.7F));
/*     */       }
/*     */       
/* 180 */       int imgY = (40 - ICON.getHeight(null)) / 2;
/* 181 */       g2d.drawImage(ICON, width - 40, imgY, null);
/*     */     }
/* 183 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/components/ActivityButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */