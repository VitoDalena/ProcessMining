/*     */ package org.deckfour.uitopia.ui.workspace;
/*     */ 
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JRadioButton;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.ResourceManager;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.ImageRadioButton;
/*     */ import org.deckfour.uitopia.ui.util.ArrangementHelper;
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
/*     */ public class ResourceListBrowser
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = -5795110941541903523L;
/*     */   private static final int VIEWPORT_WIDTH = 500;
/*     */   private UITopiaController controller;
/*     */   
/*     */   public static enum Type
/*     */   {
/*  77 */     ALL,  FAVORITES,  IMPORTED,  SELECTION;
/*     */     
/*     */ 
/*     */     private Type() {}
/*     */   }
/*     */   
/*  83 */   private List<? extends Resource> resources = new ArrayList();
/*     */   
/*     */   private Type type;
/*     */   
/*     */   private ResourceListModel listModel;
/*     */   private JList resourceList;
/*     */   private JComponent viewport;
/*     */   private JLabel header;
/*     */   private JRadioButton sortByAlpha;
/*     */   private JRadioButton sortByAccess;
/*     */   private JRadioButton sortByCreation;
/*     */   
/*     */   public ResourceListBrowser(UITopiaController controller, Type type)
/*     */   {
/*  97 */     this.controller = controller;
/*  98 */     this.type = type;
/*  99 */     setupUI();
/* 100 */     updateData();
/*     */   }
/*     */   
/*     */   public void setSelectionContent(Resource reference, boolean showParents) {
/* 104 */     ResourceManager<? extends Resource> resManager = this.controller.getFrameworkHub().getResourceManager();
/*     */     
/* 106 */     if (showParents) {
/* 107 */       this.resources = resManager.getParentsOf(reference);
/*     */     } else {
/* 109 */       this.resources = resManager.getChildrenOf(reference);
/*     */     }
/* 111 */     String rel = showParents ? "Parents of " : "Children of ";
/* 112 */     this.header.setText(rel + reference.getName());
/*     */   }
/*     */   
/*     */   public void showResource(Resource res) {
/* 116 */     if (res == null) {
/* 117 */       this.resourceList.setSelectedIndex(0);
/* 118 */       this.resourceList.ensureIndexIsVisible(0);
/* 119 */       return;
/*     */     }
/* 121 */     for (int i = 0; i < this.listModel.getSize(); i++) {
/* 122 */       if (this.listModel.getElementAt(i).equals(res)) {
/* 123 */         this.resourceList.setSelectedIndex(i);
/* 124 */         this.resourceList.ensureIndexIsVisible(i);
/* 125 */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 131 */     setOpaque(true);
/* 132 */     setBackground(new Color(180, 180, 180));
/* 133 */     setBorder(BorderFactory.createEmptyBorder());
/* 134 */     JPanel browser = new JPanel();
/* 135 */     browser.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
/* 136 */     browser.setLayout(new BorderLayout());
/* 137 */     browser.setOpaque(true);
/* 138 */     browser.setBackground(new Color(180, 180, 180));
/* 139 */     this.listModel = new ResourceListModel(this.resources);
/* 140 */     this.resourceList = new JList(this.listModel);
/* 141 */     this.resourceList.setBackground(new Color(160, 160, 160));
/* 142 */     this.resourceList.setCellRenderer(new ResourceListCellRenderer());
/* 143 */     this.resourceList.setSelectionMode(0);
/* 144 */     this.resourceList.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent e) {
/* 146 */         ResourceListBrowser.this.updateViewport();
/*     */       }
/* 148 */     });
/* 149 */     this.sortByAccess = new ImageRadioButton(ImageLoader.load("sortByExec_27x20_white.png"));
/*     */     
/* 151 */     this.sortByCreation = new ImageRadioButton(ImageLoader.load("sortByImport_27x20_white.png"));
/*     */     
/* 153 */     this.sortByAlpha = new ImageRadioButton(ImageLoader.load("sortByAlpha_27x20_white.png"));
/*     */     
/* 155 */     ButtonGroup sortGroup = new ButtonGroup();
/* 156 */     sortGroup.add(this.sortByAccess);
/* 157 */     sortGroup.add(this.sortByCreation);
/* 158 */     sortGroup.add(this.sortByAlpha);
/* 159 */     this.sortByAccess.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 161 */         SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() {
/* 163 */             ResourceListBrowser.this.sortList(true);
/*     */           }
/*     */         });
/*     */       }
/* 167 */     });
/* 168 */     this.sortByAccess.setToolTipText("Sort resources on access time");
/* 169 */     this.sortByCreation.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 171 */         SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() {
/* 173 */             ResourceListBrowser.this.sortList(true);
/*     */           }
/*     */         });
/*     */       }
/* 177 */     });
/* 178 */     this.sortByCreation.setToolTipText("Sort resources on creation time");
/* 179 */     this.sortByAlpha.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 181 */         SwingUtilities.invokeLater(new Runnable() {
/*     */           public void run() {
/* 183 */             ResourceListBrowser.this.sortList(true);
/*     */           }
/*     */         });
/*     */       }
/* 187 */     });
/* 188 */     this.sortByAlpha.setToolTipText("Sort resources on author name");
/* 189 */     this.sortByCreation.setSelected(true);
/*     */     
/* 191 */     this.header = new JLabel("No selection to show");
/* 192 */     this.header.setOpaque(false);
/* 193 */     this.header.setFont(this.header.getFont().deriveFont(16.0F));
/* 194 */     this.header.setForeground(new Color(60, 60, 60));
/*     */     
/* 196 */     JPanel sortPanel = new JPanel();
/* 197 */     sortPanel.setMinimumSize(new Dimension(180, 40));
/* 198 */     sortPanel.setMaximumSize(new Dimension(20000, 40));
/* 199 */     sortPanel.setPreferredSize(new Dimension(200, 40));
/* 200 */     sortPanel.setOpaque(false);
/* 201 */     sortPanel.setBorder(BorderFactory.createEmptyBorder());
/* 202 */     sortPanel.setLayout(new BoxLayout(sortPanel, 0));
/* 203 */     JLabel sortLabel = new JLabel("sort by");
/* 204 */     sortLabel.setFont(sortLabel.getFont().deriveFont(10.0F));
/* 205 */     sortLabel.setForeground(new Color(80, 80, 80));
/* 206 */     sortPanel.add(sortLabel);
/* 207 */     sortPanel.add(Box.createHorizontalStrut(10));
/* 208 */     sortPanel.add(this.sortByCreation);
/* 209 */     sortPanel.add(Box.createHorizontalStrut(5));
/* 210 */     sortPanel.add(this.sortByAccess);
/* 211 */     sortPanel.add(Box.createHorizontalStrut(5));
/* 212 */     sortPanel.add(this.sortByAlpha);
/* 213 */     sortPanel.add(Box.createHorizontalGlue());
/*     */     
/* 215 */     JScrollPane listScrollPane = new JScrollPane(this.resourceList);
/* 216 */     listScrollPane.setBorder(BorderFactory.createEmptyBorder());
/* 217 */     SlickerDecorator.instance().decorate(listScrollPane, new Color(180, 180, 180), new Color(40, 40, 40), new Color(100, 100, 100));
/*     */     
/*     */ 
/* 220 */     listScrollPane.setHorizontalScrollBarPolicy(31);
/*     */     
/* 222 */     listScrollPane.setVerticalScrollBarPolicy(20);
/*     */     
/* 224 */     listScrollPane.setViewportBorder(BorderFactory.createLineBorder(new Color(100, 100, 100), 2));
/*     */     
/*     */ 
/* 227 */     JPanel topPanel = new JPanel();
/* 228 */     topPanel.setMinimumSize(new Dimension(180, 60));
/* 229 */     topPanel.setMaximumSize(new Dimension(20000, 60));
/* 230 */     topPanel.setPreferredSize(new Dimension(200, 60));
/* 231 */     topPanel.setOpaque(false);
/* 232 */     topPanel.setBorder(BorderFactory.createEmptyBorder());
/* 233 */     topPanel.setLayout(new BoxLayout(topPanel, 1));
/*     */     
/* 235 */     if (this.type.equals(Type.SELECTION)) {
/* 236 */       topPanel.add(ArrangementHelper.pushLeft(this.header));
/* 237 */       topPanel.add(sortPanel);
/* 238 */       browser.add(topPanel, "North");
/*     */     } else {
/* 240 */       browser.add(sortPanel, "North");
/*     */     }
/* 242 */     browser.add(listScrollPane, "Center");
/*     */     
/* 244 */     this.viewport = new JPanel();
/* 245 */     this.viewport.setOpaque(false);
/* 246 */     this.viewport.setLayout(new BorderLayout());
/* 247 */     this.viewport.setBorder(BorderFactory.createEmptyBorder(40, 10, 5, 5));
/* 248 */     this.viewport.setMinimumSize(new Dimension(500, 10));
/* 249 */     this.viewport.setMaximumSize(new Dimension(500, 10000));
/* 250 */     this.viewport.setPreferredSize(new Dimension(500, 400));
/* 251 */     setLayout(new BorderLayout());
/* 252 */     add(browser, "Center");
/* 253 */     add(this.viewport, "East");
/*     */   }
/*     */   
/*     */   private void sortList(boolean keepSelection)
/*     */   {
/* 258 */     Object selected = this.resourceList.getSelectedValue();
/* 259 */     if (this.sortByAlpha.isSelected()) {
/* 260 */       this.listModel.sortByAlphabetical();
/* 261 */     } else if (this.sortByCreation.isSelected()) {
/* 262 */       this.listModel.sortByCreationTime();
/*     */     } else {
/* 264 */       this.listModel.sortByAccessTime();
/*     */     }
/* 266 */     if (keepSelection) {
/* 267 */       for (int i = 0; i < this.listModel.getSize(); i++) {
/* 268 */         if (this.listModel.getElementAt(i).equals(selected)) {
/* 269 */           this.resourceList.setSelectedIndex(i);
/* 270 */           this.resourceList.ensureIndexIsVisible(i);
/* 271 */           break;
/*     */         }
/*     */       }
/*     */     }
/* 275 */     revalidate();
/* 276 */     repaint();
/*     */   }
/*     */   
/*     */   public void updateData() {
/* 280 */     if (this.type.equals(Type.ALL)) {
/* 281 */       this.resources = this.controller.getFrameworkHub().getResourceManager().getAllResources();
/*     */     }
/* 283 */     else if (this.type.equals(Type.FAVORITES)) {
/* 284 */       this.resources = this.controller.getFrameworkHub().getResourceManager().getFavoriteResources();
/*     */     }
/* 286 */     else if (this.type.equals(Type.IMPORTED)) {
/* 287 */       this.resources = this.controller.getFrameworkHub().getResourceManager().getImportedResources();
/*     */     }
/*     */     
/* 290 */     this.listModel = new ResourceListModel(this.resources);
/* 291 */     Object selected = this.resourceList.getSelectedValue();
/* 292 */     int index = this.resourceList.getSelectedIndex();
/*     */     
/* 294 */     this.resourceList.setModel(this.listModel);
/* 295 */     sortList(false);
/* 296 */     this.resourceList.setSelectedValue(selected, true);
/* 297 */     if (this.resourceList.isSelectionEmpty()) {
/* 298 */       int i = Math.max(0, Math.min(index, this.resources.size() - 1));
/*     */       
/* 300 */       this.resourceList.setSelectedIndex(i);
/* 301 */       this.resourceList.ensureIndexIsVisible(i);
/*     */     }
/*     */   }
/*     */   
/*     */   private void updateViewport()
/*     */   {
/* 307 */     this.viewport.removeAll();
/* 308 */     Resource selected = (Resource)this.resourceList.getSelectedValue();
/* 309 */     if (selected != null) {
/* 310 */       ResourceView view = new ResourceView(selected, this.controller);
/* 311 */       this.viewport.add(view, "Center");
/*     */     }
/* 313 */     this.viewport.revalidate();
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/workspace/ResourceListBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */