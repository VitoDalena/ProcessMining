/*     */ package org.deckfour.uitopia.ui.workspace;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.RoundedPanel;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Image;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.swing.AbstractButton;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.ImageButton;
/*     */ import org.deckfour.uitopia.ui.components.ImageLozengeButton;
/*     */ import org.deckfour.uitopia.ui.components.ImageToggleButton;
/*     */ import org.deckfour.uitopia.ui.main.MainView;
/*     */ import org.deckfour.uitopia.ui.util.ArrangementHelper;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ import org.deckfour.uitopia.ui.util.TimeUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ResourceView
/*     */   extends RoundedPanel
/*     */ {
/*     */   private static final long serialVersionUID = 7429267198567749852L;
/*     */   private final Resource resource;
/*     */   private final UITopiaController controller;
/*     */   private AbstractButton favoriteButton;
/*     */   private AbstractButton viewButton;
/*     */   private AbstractButton actionButton;
/*     */   private AbstractButton removeButton;
/*     */   private AbstractButton parentButton;
/*     */   private AbstractButton childrenButton;
/*     */   private AbstractButton exportButton;
/*     */   
/*     */   public ResourceView(Resource resource, UITopiaController controller)
/*     */   {
/*  87 */     super(20, 5, 15);
/*  88 */     this.resource = resource;
/*  89 */     this.controller = controller;
/*  90 */     setupUI();
/*     */   }
/*     */   
/*     */   private void setupUI() {
/*  94 */     setBackground(new Color(160, 160, 160));
/*  95 */     setLayout(new BoxLayout(this, 1));
/*     */     
/*  97 */     JPanel infoPanel = new JPanel();
/*  98 */     infoPanel.setMaximumSize(new Dimension(500, 180));
/*  99 */     infoPanel.setOpaque(false);
/* 100 */     infoPanel.setLayout(new BorderLayout());
/* 101 */     Image icon = this.resource.getPreview(150, 150);
/* 102 */     JLabel preview = new JLabel(new ImageIcon(icon));
/* 103 */     preview.setSize(150, 150);
/* 104 */     preview.setOpaque(false);
/* 105 */     JPanel detailsPanel = new JPanel();
/* 106 */     detailsPanel.setOpaque(false);
/* 107 */     detailsPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 15, 0));
/* 108 */     detailsPanel.setLayout(new BoxLayout(detailsPanel, 1));
/* 109 */     detailsPanel.add(styleLabel(this.resource.getName(), new Color(10, 10, 10), 18.0F));
/*     */     
/* 111 */     detailsPanel.add(Box.createVerticalStrut(3));
/* 112 */     detailsPanel.add(styleLabel(this.resource.getType().getTypeName(), new Color(30, 30, 30), 14.0F));
/*     */     
/* 114 */     detailsPanel.add(Box.createVerticalStrut(12));
/* 115 */     long age = System.currentTimeMillis() - this.resource.getCreationTime().getTime();
/*     */     
/* 117 */     detailsPanel.add(styleLabel(TimeUtils.ageToString(age), new Color(60, 60, 60), 12.0F));
/*     */     
/* 119 */     detailsPanel.add(Box.createVerticalStrut(5));
/* 120 */     String text = "<html><i>";
/* 121 */     if (this.resource.getSourceAction() == null) {
/* 122 */       text = text + "imported";
/*     */     } else {
/* 124 */       text = text + "by " + this.resource.getSourceAction().getName();
/*     */     }
/* 126 */     text = text + "</i></html>";
/* 127 */     detailsPanel.add(styleLabel(text, new Color(60, 60, 60), 12.0F));
/* 128 */     detailsPanel.add(Box.createVerticalGlue());
/* 129 */     infoPanel.add(preview, "West");
/* 130 */     infoPanel.add(detailsPanel, "Center");
/*     */     
/* 132 */     RoundedPanel actionsPanel = new RoundedPanel(50, 0, 0);
/* 133 */     actionsPanel.setBackground(new Color(80, 80, 80));
/* 134 */     actionsPanel.setLayout(new BoxLayout(actionsPanel, 0));
/* 135 */     actionsPanel.setMinimumSize(new Dimension(180, 50));
/* 136 */     actionsPanel.setMaximumSize(new Dimension(180, 50));
/* 137 */     actionsPanel.setPreferredSize(new Dimension(180, 50));
/* 138 */     actionsPanel.setBorder(BorderFactory.createEmptyBorder());
/*     */     
/* 140 */     boolean isActionable = this.controller.getFrameworkHub().getTaskManager().isActionableResource(this.resource);
/*     */     
/*     */ 
/* 143 */     this.favoriteButton = new ImageToggleButton(ImageLoader.load("favorite_30x30_black.png"));
/*     */     
/* 145 */     this.favoriteButton.setSelected(this.resource.isFavorite());
/* 146 */     this.favoriteButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 148 */         ResourceView.this.toggleFavorite();
/*     */       }
/*     */     });
/* 151 */     if (isActionable) {
/* 152 */       this.favoriteButton.setToolTipText("Toggle resource favorite flag");
/*     */     } else {
/* 154 */       this.favoriteButton.setToolTipText("This resource cannot be made favorite");
/*     */       
/* 156 */       this.favoriteButton.setEnabled(false);
/*     */     }
/* 158 */     this.viewButton = new ImageButton(ImageLoader.load("view_30x30_black.png"));
/* 159 */     this.viewButton.setEnabled(!this.controller.getFrameworkHub().getViewManager().getViewTypes(this.resource).isEmpty());
/*     */     
/* 161 */     this.viewButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 163 */         ResourceView.this.view();
/*     */       }
/*     */     });
/* 166 */     if (isActionable) {
/* 167 */       this.viewButton.setToolTipText("View resource");
/*     */     } else {
/* 169 */       this.viewButton.setToolTipText("This resource cannot be viewed");
/* 170 */       this.viewButton.setEnabled(false);
/*     */     }
/* 172 */     this.actionButton = new ImageButton(ImageLoader.load("action_30x30_black.png"));
/*     */     
/* 174 */     this.actionButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 176 */         ResourceView.this.action();
/*     */       }
/*     */     });
/* 179 */     if (isActionable) {
/* 180 */       this.actionButton.setToolTipText("Use resource");
/*     */     } else {
/* 182 */       this.actionButton.setToolTipText("This resource cannot be used");
/* 183 */       this.actionButton.setEnabled(false);
/*     */     }
/* 185 */     this.removeButton = new ImageButton(ImageLoader.load("remove_30x30_black.png"), new Color(140, 140, 140), new Color(180, 20, 20), 2);
/*     */     
/*     */ 
/* 188 */     this.removeButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 190 */         ResourceView.this.remove();
/*     */       }
/* 192 */     });
/* 193 */     this.removeButton.setToolTipText("Remove resource");
/* 194 */     actionsPanel.add(Box.createHorizontalGlue());
/* 195 */     actionsPanel.add(this.favoriteButton);
/* 196 */     actionsPanel.add(Box.createHorizontalStrut(5));
/* 197 */     actionsPanel.add(this.viewButton);
/* 198 */     actionsPanel.add(Box.createHorizontalStrut(5));
/* 199 */     actionsPanel.add(this.actionButton);
/* 200 */     actionsPanel.add(Box.createHorizontalStrut(5));
/* 201 */     actionsPanel.add(this.removeButton);
/* 202 */     actionsPanel.add(Box.createHorizontalGlue());
/*     */     
/* 204 */     RoundedPanel familyPanel = new RoundedPanel(50, 0, 0)
/*     */     {
/*     */       private static final long serialVersionUID = 6739005088069438989L;
/*     */       
/*     */       protected void paintComponent(Graphics g) {
/* 209 */         super.paintComponent(g);
/*     */         
/* 211 */         int yMid = getHeight() / 2;
/* 212 */         int[] x = { 15, 45, 42, 45 };
/* 213 */         int[] y = { yMid, yMid - 15, yMid, yMid + 15 };
/* 214 */         g.setColor(new Color(120, 120, 120));
/* 215 */         g.fillPolygon(x, y, 4);
/*     */       }
/* 217 */     };
/* 218 */     familyPanel.setBackground(new Color(80, 80, 80));
/* 219 */     familyPanel.setLayout(new BoxLayout(familyPanel, 1));
/* 220 */     familyPanel.setMinimumSize(new Dimension(220, 100));
/* 221 */     familyPanel.setMaximumSize(new Dimension(220, 100));
/* 222 */     familyPanel.setPreferredSize(new Dimension(220, 100));
/* 223 */     familyPanel.setBorder(BorderFactory.createEmptyBorder(5, 55, 5, 15));
/* 224 */     this.parentButton = new ImageLozengeButton(ImageLoader.load("parent_30x30_black.png"), "Show parents");
/*     */     
/* 226 */     this.parentButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 228 */         ResourceView.this.showParents();
/*     */       }
/* 230 */     });
/* 231 */     this.parentButton.setToolTipText("Show resources used to create this resource");
/* 232 */     this.childrenButton = new ImageLozengeButton(ImageLoader.load("children_30x30_black.png"), "Show children");
/*     */     
/* 234 */     this.childrenButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 236 */         ResourceView.this.showChildren();
/*     */       }
/* 238 */     });
/* 239 */     this.childrenButton.setToolTipText("Show resources created using this resource");
/* 240 */     familyPanel.add(Box.createVerticalGlue());
/* 241 */     familyPanel.add(this.parentButton);
/* 242 */     familyPanel.add(Box.createVerticalStrut(5));
/* 243 */     familyPanel.add(this.childrenButton);
/* 244 */     familyPanel.add(Box.createVerticalGlue());
/* 245 */     this.exportButton = new ImageLozengeButton(ImageLoader.load("export_30x30_black.png"), "Export to disk", new Color(120, 120, 120), new Color(0, 120, 0), 2);
/*     */     
/*     */ 
/* 248 */     this.exportButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 250 */         ResourceView.this.export();
/*     */       }
/* 252 */     });
/* 253 */     this.exportButton.setToolTipText("Export resource to file");
/* 254 */     add(infoPanel);
/* 255 */     add(Box.createVerticalStrut(25));
/* 256 */     add(ArrangementHelper.pushLeft(actionsPanel));
/* 257 */     add(Box.createVerticalStrut(25));
/* 258 */     add(ArrangementHelper.pushLeft(familyPanel));
/* 259 */     add(Box.createVerticalStrut(25));
/* 260 */     add(ArrangementHelper.pushLeft(this.exportButton));
/* 261 */     add(Box.createVerticalGlue());
/*     */   }
/*     */   
/*     */   private void toggleFavorite() {
/* 265 */     this.resource.setFavorite(this.favoriteButton.isSelected());
/* 266 */     this.controller.getMainView().getWorkspaceView().updateResources();
/*     */   }
/*     */   
/*     */   private void view() {
/* 270 */     this.controller.getMainView().showViewsView(this.resource);
/*     */   }
/*     */   
/*     */   private void action() {
/* 274 */     this.controller.getMainView().showActionsView(this.resource);
/*     */   }
/*     */   
/*     */   private void remove() {
/* 278 */     List<View> views = new ArrayList(this.controller.getFrameworkHub().getViewManager().getViews());
/*     */     
/* 280 */     for (View view : views) {
/* 281 */       if (view.getResource().equals(this.resource)) {
/* 282 */         this.controller.getFrameworkHub().getViewManager().removeView(view);
/*     */       }
/*     */     }
/*     */     
/* 286 */     this.resource.destroy();
/*     */   }
/*     */   
/*     */   private void showParents() {
/* 290 */     this.controller.getMainView().getWorkspaceView().showParentsOf(this.resource);
/*     */   }
/*     */   
/*     */   private void showChildren()
/*     */   {
/* 295 */     this.controller.getMainView().getWorkspaceView().showChildrenOf(this.resource);
/*     */   }
/*     */   
/*     */   private void export()
/*     */   {
/*     */     try {
/* 301 */       this.controller.getFrameworkHub().getResourceManager().exportResource(this.resource);
/*     */     }
/*     */     catch (IOException e)
/*     */     {
/* 305 */       e.printStackTrace();
/*     */     }
/*     */   }
/*     */   
/*     */   private JLabel styleLabel(String text, Color color, float size) {
/* 310 */     JLabel label = new JLabel(text);
/* 311 */     label.setOpaque(false);
/* 312 */     label.setForeground(color);
/* 313 */     label.setFont(label.getFont().deriveFont(size));
/* 314 */     return label;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/ResourceView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */