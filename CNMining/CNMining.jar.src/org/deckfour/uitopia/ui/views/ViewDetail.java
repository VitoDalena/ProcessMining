/*     */ package org.deckfour.uitopia.ui.views;
/*     */ 
/*     */ import com.fluxicon.slickerbox.factory.SlickerFactory;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.Timer;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ViewManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.View;
/*     */ import org.deckfour.uitopia.api.model.ViewType;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.ImageButton;
/*     */ import org.deckfour.uitopia.ui.components.ImageToggleButton;
/*     */ import org.deckfour.uitopia.ui.components.ViewHeaderBar;
/*     */ import org.deckfour.uitopia.ui.main.MainView;
/*     */ import org.deckfour.uitopia.ui.util.ImageLoader;
/*     */ import org.deckfour.uitopia.ui.util.PrintUtils;
/*     */ import org.deckfour.uitopia.ui.workspace.WorkspaceView;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ViewDetail
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 300142446308646603L;
/*  66 */   private static final Color COLOR_BUTTON_ACTIVE = new Color(40, 140, 40);
/*  67 */   private static final Color COLOR_BUTTON_PASSIVE = new Color(80, 80, 80);
/*     */   private final UITopiaController controller;
/*     */   private final ViewsView parent;
/*     */   private final View view;
/*     */   
/*     */   public ViewDetail(ViewsView parent, View view)
/*     */   {
/*  74 */     this.controller = parent.getController();
/*  75 */     this.parent = parent;
/*  76 */     this.view = view;
/*  77 */     setupUI();
/*     */   }
/*     */   
/*     */   private void setupUI() {
/*  81 */     setLayout(new BorderLayout());
/*  82 */     setOpaque(true);
/*  83 */     setBorder(BorderFactory.createEmptyBorder());
/*  84 */     ViewHeaderBar header = new ViewHeaderBar(this.view.getCustomName());
/*  85 */     List<ViewType> types = this.controller.getFrameworkHub().getViewManager().getViewTypes(this.view.getResource());
/*     */     
/*  87 */     Object[] values = new Object[types.size() + 1];
/*  88 */     values[0] = "Create new...";
/*  89 */     int index = 1;
/*  90 */     for (ViewType type : types) {
/*  91 */       values[index] = type;
/*  92 */       index++;
/*     */     }
/*  94 */     final JComboBox viewTypeBox = SlickerFactory.instance().createComboBox(values);
/*     */     
/*  96 */     viewTypeBox.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/*  98 */         ViewDetail.this.createView(viewTypeBox.getSelectedItem());
/*     */       }
/* 100 */     });
/* 101 */     viewTypeBox.setPreferredSize(new Dimension(250, 25));
/* 102 */     viewTypeBox.setMinimumSize(new Dimension(250, 25));
/* 103 */     viewTypeBox.setSize(new Dimension(250, 25));
/* 104 */     viewTypeBox.setToolTipText("Select viewer");
/* 105 */     ImageToggleButton favoriteButton = new ImageToggleButton(ImageLoader.load("favorite_white_30x30.png"), new Color(140, 140, 20), COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 1);
/*     */     
/*     */ 
/* 108 */     favoriteButton.setSelected(this.view.getResource().isFavorite());
/* 109 */     favoriteButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 111 */         ViewDetail.this.view.getResource().setFavorite(!ViewDetail.this.view.getResource().isFavorite());
/*     */         
/* 113 */         ViewDetail.this.controller.getMainView().getWorkspaceView().updateResources();
/*     */       }
/* 115 */     });
/* 116 */     favoriteButton.setToolTipText("Toggle resource favorite flag");
/* 117 */     ImageButton refreshButton = new ImageButton(ImageLoader.load("refresh_white_30x30.png"), COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 1);
/*     */     
/*     */ 
/* 120 */     refreshButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 122 */         ViewDetail.this.view.refresh();
/* 123 */         ViewDetail.this.revalidate();
/*     */       }
/* 125 */     });
/* 126 */     refreshButton.setToolTipText("Refresh view");
/* 127 */     ImageButton printButton = new ImageButton(ImageLoader.load("print_white_30x30.png"), COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 1);
/*     */     
/*     */ 
/* 130 */     printButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 132 */         PrintUtils.printView(ViewDetail.this.view);
/*     */       }
/* 134 */     });
/* 135 */     printButton.setToolTipText("Print view");
/* 136 */     ImageButton actionButton = new ImageButton(ImageLoader.load("action_white_30x30.png"), COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 1);
/*     */     
/*     */ 
/* 139 */     actionButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 141 */         ViewDetail.this.controller.getMainView().showActionsView(ViewDetail.this.view.getResource());
/*     */       }
/* 143 */     });
/* 144 */     actionButton.setToolTipText("Use resource");
/* 145 */     ImageButton workspaceButton = new ImageButton(ImageLoader.load("workspace_white_30x30.png"), COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 1);
/*     */     
/*     */ 
/* 148 */     workspaceButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 150 */         ViewDetail.this.controller.getMainView().showWorkspaceView(ViewDetail.this.view.getResource());
/*     */       }
/* 152 */     });
/* 153 */     workspaceButton.setToolTipText("View resource in workspace");
/* 154 */     ImageButton overviewButton = new ImageButton(ImageLoader.load("overview_white_30x30.png"), COLOR_BUTTON_PASSIVE, COLOR_BUTTON_ACTIVE, 1);
/*     */     
/*     */ 
/* 157 */     overviewButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 159 */         ViewDetail.this.view.captureNow();
/* 160 */         ViewDetail.this.parent.showOverview();
/*     */       }
/* 162 */     });
/* 163 */     overviewButton.setToolTipText("View overview");
/* 164 */     header.addComponent(viewTypeBox);
/* 165 */     header.addComponent(refreshButton);
/* 166 */     header.addComponent(printButton);
/* 167 */     header.addComponent(favoriteButton);
/* 168 */     header.addComponent(actionButton);
/* 169 */     header.addComponent(workspaceButton);
/* 170 */     header.addComponent(overviewButton);
/* 171 */     add(header, "North");
/* 172 */     add(this.view.getViewComponent(), "Center");
/*     */   }
/*     */   
/*     */   public void createView(Object obj) {
/* 176 */     if ((obj instanceof ViewType)) {
/* 177 */       ViewType type = (ViewType)obj;
/*     */       
/* 179 */       final View view = type.createView(this.view.getResource());
/* 180 */       Timer viewFinishedTimer = new Timer(100, new ActionListener()
/*     */       {
/*     */         public void actionPerformed(ActionEvent e) {
/* 183 */           if (!view.isReady()) {
/* 184 */             return;
/*     */           }
/* 186 */           Timer timer = (Timer)e.getSource();
/* 187 */           timer.stop();
/*     */           
/* 189 */           ViewDetail.this.controller.getFrameworkHub().getViewManager().addView(view);
/* 190 */           ViewDetail.this.parent.updateViews();
/* 191 */           ViewDetail.this.parent.showFullScreen(view);
/*     */         }
/* 193 */       });
/* 194 */       viewFinishedTimer.start();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/views/ViewDetail.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */