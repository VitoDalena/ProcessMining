/*     */ package org.deckfour.uitopia.ui.views;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.ui.components.ImageButton;
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
/*     */ public class ViewItem
/*     */   extends JComponent
/*     */ {
/*     */   private static final long serialVersionUID = -3380025203180010319L;
/*  67 */   private static final Color COLOR_TOP = new Color(255, 255, 255);
/*  68 */   private static final Color COLOR_BOTTOM = new Color(180, 180, 180);
/*  69 */   private static final Color COLOR_SHADOW = new Color(40, 40, 40, 120);
/*  70 */   private static final Color COLOR_TEXT = new Color(20, 20, 20);
/*  71 */   private static final Color COLOR_BUTTON_PASSIVE = new Color(0, 0, 0, 0);
/*  72 */   private static final Color COLOR_BUTTON_ACTIVE = new Color(60, 180, 60);
/*     */   
/*     */   private static final int WIDTH = 160;
/*     */   
/*     */   private static final int HEIGHT = 160;
/*     */   private static final int BORDER_OUT = 10;
/*     */   private static final int BORDER_IN = 8;
/*     */   private static final int SHADOW_X = 4;
/*     */   private static final int SHADOW_Y = 5;
/*  81 */   private static final Image ICON_VIEW = ImageLoader.load("view_black_20x20.png");
/*  82 */   private static final Image ICON_REMOVE = ImageLoader.load("remove_black_20x20.png");
/*     */   
/*     */   private ViewsView parent;
/*     */   private View view;
/*     */   private ImageButton viewButton;
/*     */   private ImageButton removeButton;
/*     */   private JLabel thumbnail;
/*     */   private JLabel label;
/*     */   
/*     */   public ViewItem(ViewsView parent, View view)
/*     */   {
/*  93 */     this.parent = parent;
/*  94 */     this.view = view;
/*  95 */     setupUI();
/*     */   }
/*     */   
/*     */   public void updateSize(int width, int height) {
/*  99 */     Dimension dim = new Dimension(width + 8 + 10 + 4, height + 8 + 10 + 5);
/*     */     
/* 101 */     this.thumbnail.setIcon(new ImageIcon(this.view.getPreview(width, width)));
/* 102 */     setMinimumSize(dim);
/* 103 */     setMaximumSize(dim);
/* 104 */     setPreferredSize(dim);
/* 105 */     setSize(dim);
/* 106 */     revalidate();
/* 107 */     repaint();
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 111 */     setOpaque(false);
/* 112 */     setLayout(new BoxLayout(this, 1));
/* 113 */     setBorder(BorderFactory.createEmptyBorder(18, 18, 23, 22));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 118 */     JPanel header = new JPanel();
/* 119 */     header.setOpaque(false);
/* 120 */     header.setLayout(new BoxLayout(header, 0));
/* 121 */     header.setMinimumSize(new Dimension(30, 30));
/* 122 */     header.setMaximumSize(new Dimension(1000, 30));
/* 123 */     header.setPreferredSize(new Dimension(200, 30));
/* 124 */     this.viewButton = new ImageButton(ICON_VIEW, COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 2);
/* 125 */     this.viewButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 127 */         ViewItem.this.parent.showFullScreen(ViewItem.this.view);
/*     */       }
/* 129 */     });
/* 130 */     this.viewButton.setToolTipText("Open view");
/* 131 */     this.removeButton = new ImageButton(ICON_REMOVE, COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 2);
/* 132 */     this.removeButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 134 */         ViewItem.this.parent.removeView(ViewItem.this.view);
/*     */       }
/* 136 */     });
/* 137 */     this.removeButton.setToolTipText("Remove view");
/* 138 */     header.add(Box.createHorizontalGlue());
/* 139 */     header.add(this.viewButton);
/* 140 */     header.add(Box.createHorizontalStrut(5));
/* 141 */     header.add(this.removeButton);
/* 142 */     this.thumbnail = new JLabel(new ImageIcon(this.view.getPreview(160, 160)));
/* 143 */     this.thumbnail.setAlignmentX(0.5F);
/* 144 */     this.thumbnail.addMouseListener(new MouseAdapter() {
/*     */       public void mouseClicked(MouseEvent e) {
/* 146 */         if ((e.getButton() == 1) && (e.getClickCount() > 1))
/*     */         {
/* 148 */           ViewItem.this.parent.showFullScreen(ViewItem.this.view);
/*     */         }
/*     */       }
/* 151 */     });
/* 152 */     this.label = new JLabel(this.view.getCustomName());
/* 153 */     this.label.setAlignmentX(0.5F);
/* 154 */     this.label.setOpaque(false);
/* 155 */     this.label.setForeground(COLOR_TEXT);
/* 156 */     this.label.setFont(this.label.getFont().deriveFont(12.0F));
/*     */     
/* 158 */     add(header);
/* 159 */     add(this.thumbnail);
/* 160 */     add(Box.createVerticalStrut(6));
/* 161 */     add(this.label);
/* 162 */     add(Box.createVerticalGlue());
/* 163 */     updateSize(160, 160);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 171 */     int width = getWidth();
/* 172 */     int height = getHeight();
/* 173 */     int boxWidth = width - 10 - 10 - 4;
/* 174 */     int boxHeight = height - 10 - 10 - 5;
/* 175 */     Graphics2D g2d = (Graphics2D)g.create();
/* 176 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 178 */     g2d.setColor(COLOR_SHADOW);
/* 179 */     g2d.fillRect(14, 15, boxWidth, boxHeight);
/*     */     
/* 181 */     GradientPaint gradient = new GradientPaint(0.0F, 10.0F, COLOR_TOP, 0.0F, 10 + boxHeight, COLOR_BOTTOM);
/*     */     
/*     */ 
/* 184 */     g2d.setPaint(gradient);
/* 185 */     g2d.fillRect(10, 10, boxWidth, boxHeight);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/views/ViewItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */