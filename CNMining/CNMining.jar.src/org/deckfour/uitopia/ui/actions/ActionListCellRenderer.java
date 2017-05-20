/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import com.fluxicon.slickerbox.util.ColorUtils;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.GradientPaint;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.RenderingHints;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.ListCellRenderer;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.ActionStatus;
/*     */ import org.deckfour.uitopia.api.model.ActionType;
/*     */ import org.deckfour.uitopia.api.model.Author;
/*     */ import org.deckfour.uitopia.api.model.Resource;
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
/*     */ public class ActionListCellRenderer
/*     */   extends JComponent
/*     */   implements ListCellRenderer
/*     */ {
/*     */   private static final long serialVersionUID = -176928184229024823L;
/*     */   private static final int HEIGHT = 60;
/*  71 */   private static final Image ICON_INTERACTIVE = ImageLoader.load("action_interactive_40x40.png");
/*     */   
/*  73 */   private static final Image ICON_BATCH = ImageLoader.load("action_batch_40x40.png");
/*     */   
/*     */ 
/*  76 */   private static final Color COLOR_TRANSPARENT = new Color(0, 0, 0, 0);
/*     */   
/*  78 */   private static final Color COLOR_NAME_ACTIVE = new Color(255, 255, 255);
/*  79 */   private static final Color COLOR_NAME_PASSIVE = new Color(10, 10, 10);
/*  80 */   private static final Color COLOR_AUTHOR_ACTIVE = new Color(220, 220, 220, 160);
/*     */   
/*  82 */   private static final Color COLOR_AUTHOR_PASSIVE = new Color(20, 20, 20, 160);
/*     */   
/*  84 */   private static final Color COLOR_BG_ENABLED_PASSIVE = new Color(80, 160, 80);
/*  85 */   private static final Color COLOR_BG_ENABLED_ACTIVE = new Color(0, 160, 0);
/*     */   
/*  87 */   private static final Color COLOR_BG_PARTIAL_PASSIVE = new Color(160, 160, 80);
/*     */   
/*  89 */   private static final Color COLOR_BG_PARTIAL_ACTIVE = new Color(160, 160, 0);
/*     */   
/*  91 */   private static final Color COLOR_BG_DISABLED_PASSIVE = new Color(160, 80, 80);
/*     */   
/*  93 */   private static final Color COLOR_BG_DISABLED_ACTIVE = new Color(160, 0, 0);
/*     */   private Action action;
/*     */   private boolean selected;
/*     */   private List<Collection<? extends Resource>> parameters;
/*     */   
/*     */   public ActionListCellRenderer()
/*     */   {
/* 100 */     setOpaque(false);
/* 101 */     setBorder(BorderFactory.createEmptyBorder());
/* 102 */     setMinimumSize(new Dimension(100, 60));
/* 103 */     setMaximumSize(new Dimension(1000, 60));
/* 104 */     setPreferredSize(new Dimension(250, 60));
/* 105 */     this.parameters = new ArrayList();
/*     */   }
/*     */   
/*     */   public void setParameters(List<Collection<? extends Resource>> parameters) {
/* 109 */     this.parameters = parameters;
/* 110 */     repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
/*     */   {
/* 122 */     this.action = ((Action)value);
/* 123 */     this.selected = isSelected;
/* 124 */     return this;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 134 */     int height = getHeight();
/* 135 */     int width = getWidth();
/* 136 */     Graphics2D g2d = (Graphics2D)g.create();
/* 137 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/*     */ 
/* 140 */     ActionStatus status = this.action.getStatus(this.parameters);
/*     */     Color bgTop;
/* 142 */     Color bgTop; if (status.equals(ActionStatus.EXECUTABLE)) { Color bgTop;
/* 143 */       if (this.selected) {
/* 144 */         bgTop = COLOR_BG_ENABLED_ACTIVE;
/*     */       } else
/* 146 */         bgTop = COLOR_BG_ENABLED_PASSIVE;
/*     */     } else { Color bgTop;
/* 148 */       if (status.equals(ActionStatus.INCOMPLETE)) { Color bgTop;
/* 149 */         if (this.selected) {
/* 150 */           bgTop = COLOR_BG_PARTIAL_ACTIVE;
/*     */         } else
/* 152 */           bgTop = COLOR_BG_PARTIAL_PASSIVE;
/*     */       } else {
/*     */         Color bgTop;
/* 155 */         if (this.selected) {
/* 156 */           bgTop = COLOR_BG_DISABLED_ACTIVE;
/*     */         } else
/* 158 */           bgTop = COLOR_BG_DISABLED_PASSIVE;
/*     */       }
/*     */     }
/* 161 */     GradientPaint gp = new GradientPaint(0.0F, 0.0F, bgTop, 0.0F, height, ColorUtils.darken(bgTop, 20));
/*     */     
/* 163 */     g2d.setPaint(gp);
/* 164 */     g2d.fillRect(0, 0, width, height);
/*     */     
/* 166 */     Image icon = ICON_INTERACTIVE;
/* 167 */     if (this.action.getType().equals(ActionType.HEADLESS)) {
/* 168 */       icon = ICON_BATCH;
/*     */     }
/* 170 */     int iconY = (height - icon.getHeight(null)) / 2;
/* 171 */     g2d.drawImage(icon, 5, iconY, null);
/*     */     
/* 173 */     Color nameColor = COLOR_NAME_PASSIVE;
/* 174 */     Color authorColor = COLOR_AUTHOR_PASSIVE;
/* 175 */     if (this.selected) {
/* 176 */       nameColor = COLOR_NAME_ACTIVE;
/* 177 */       authorColor = COLOR_AUTHOR_ACTIVE;
/*     */     }
/* 179 */     int textX = icon.getWidth(null) + 15;
/*     */     
/* 181 */     gp = new GradientPaint(width - 20, 0.0F, nameColor, width - 5, 0.0F, COLOR_TRANSPARENT);
/*     */     
/* 183 */     g2d.setPaint(gp);
/* 184 */     g2d.setFont(g2d.getFont().deriveFont(14.0F));
/* 185 */     g2d.drawString(this.action.getName(), textX, height / 2 - 12);
/*     */     
/* 187 */     String author = this.action.getAuthor().getName() + " (" + this.action.getAuthor().getEmail() + ")";
/*     */     
/* 189 */     gp = new GradientPaint(width - 20, 0.0F, authorColor, width - 5, 0.0F, COLOR_TRANSPARENT);
/*     */     
/* 191 */     g2d.setPaint(gp);
/* 192 */     g2d.setFont(g2d.getFont().deriveFont(11.0F));
/* 193 */     g2d.drawString(author, textX, height / 2 + 4);
/*     */     
/*     */ 
/*     */ 
/* 197 */     String pack = this.action.getPackage();
/* 198 */     gp = new GradientPaint(width - 20, 0.0F, authorColor, width - 5, 0.0F, COLOR_TRANSPARENT);
/*     */     
/* 200 */     g2d.setPaint(gp);
/* 201 */     g2d.setFont(g2d.getFont().deriveFont(11.0F));
/*     */     
/* 203 */     g2d.drawString(pack, textX, height / 2 + 20);
/*     */     
/* 205 */     g2d.dispose();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ActionListCellRenderer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */