/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import com.fluxicon.slickerbox.components.SlickerSearchField;
/*     */ import com.fluxicon.slickerbox.factory.SlickerDecorator;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.io.PrintStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.regex.PatternSyntaxException;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JList;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollBar;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JToggleButton;
/*     */ import javax.swing.JViewport;
/*     */ import javax.swing.event.ListSelectionEvent;
/*     */ import javax.swing.event.ListSelectionListener;
/*     */ import org.deckfour.uitopia.api.hub.ActionManager;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.model.Action;
/*     */ import org.deckfour.uitopia.api.model.ActionStatus;
/*     */ import org.deckfour.uitopia.api.model.ActionType;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.ImageLozengeButton;
/*     */ import org.deckfour.uitopia.ui.components.ImageToggleButton;
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
/*     */ public class ActionsBrowser
/*     */   extends JPanel
/*     */ {
/*     */   private static final long serialVersionUID = 1529560986982194413L;
/*  85 */   private static final Color BG = new Color(20, 20, 20);
/*  86 */   private static final Color COLOR_TEXT = new Color(160, 160, 160);
/*     */   
/*     */   private ActionsView parent;
/*     */   
/*     */   private JToggleButton filterButtonInteractive;
/*     */   private JToggleButton filterButtonBatch;
/*     */   private SlickerSearchField filterSearch;
/*     */   private ImageLozengeButton okButton;
/*     */   private JList actionList;
/*     */   private ActionListCellRenderer cellRenderer;
/*     */   
/*     */   public ActionsBrowser(ActionsView parent)
/*     */   {
/*  99 */     this.parent = parent;
/* 100 */     setupUI();
/*     */   }
/*     */   
/*     */   public void setFocus() {
/* 104 */     System.out.println("[ActionsBrowser] setFocus");
/* 105 */     this.filterSearch.requestFocusInWindow();
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 109 */     setLayout(new BoxLayout(this, 1));
/* 110 */     setOpaque(false);
/* 111 */     setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
/* 112 */     setMinimumSize(new Dimension(400, 500));
/* 113 */     setMaximumSize(new Dimension(600, 2000));
/* 114 */     setPreferredSize(new Dimension(500, 1000));
/*     */     
/* 116 */     JLabel header = new JLabel("Actions");
/* 117 */     header.setOpaque(false);
/* 118 */     header.setForeground(new Color(200, 200, 200));
/* 119 */     header.setFont(header.getFont().deriveFont(18.0F).deriveFont(1));
/* 120 */     header.setAlignmentX(0.5F);
/*     */     
/* 122 */     JPanel filterPanel = new JPanel();
/* 123 */     filterPanel.setMinimumSize(new Dimension(350, 50));
/* 124 */     filterPanel.setMaximumSize(new Dimension(450, 50));
/* 125 */     filterPanel.setPreferredSize(new Dimension(450, 50));
/* 126 */     filterPanel.setOpaque(false);
/* 127 */     filterPanel.setBorder(BorderFactory.createEmptyBorder());
/* 128 */     filterPanel.setLayout(new BoxLayout(filterPanel, 0));
/* 129 */     JLabel filterLabel = new JLabel("Filter:");
/* 130 */     filterLabel.setOpaque(false);
/* 131 */     filterLabel.setForeground(COLOR_TEXT);
/* 132 */     filterLabel.setFont(filterLabel.getFont().deriveFont(11.0F));
/* 133 */     this.filterButtonInteractive = new ImageToggleButton(ImageLoader.load("action_interactive_20x20.png"), new Color(0, 120, 0), new Color(80, 80, 80), new Color(120, 120, 0), 3);
/*     */     
/*     */ 
/*     */ 
/* 137 */     this.filterButtonInteractive.setSelected(true);
/* 138 */     this.filterButtonInteractive.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 140 */         ActionsBrowser.this.updateFilter();
/*     */       }
/* 142 */     });
/* 143 */     this.filterButtonInteractive.setToolTipText("Toggle interactive filter");
/*     */     
/* 145 */     this.filterButtonBatch = new ImageToggleButton(ImageLoader.load("action_batch_20x20.png"), new Color(0, 120, 0), new Color(80, 80, 80), new Color(120, 120, 0), 3);
/*     */     
/*     */ 
/*     */ 
/* 149 */     this.filterButtonBatch.setSelected(true);
/* 150 */     this.filterButtonBatch.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 152 */         ActionsBrowser.this.updateFilter();
/*     */       }
/* 154 */     });
/* 155 */     this.filterButtonBatch.setToolTipText("Toggle batch filter");
/* 156 */     this.filterSearch = new SlickerSearchField(220, 23, new Color(140, 140, 140), new Color(80, 80, 80), new Color(40, 40, 40), new Color(20, 20, 20));
/*     */     
/*     */ 
/* 159 */     this.filterSearch.addSearchListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 161 */         ActionsBrowser.this.updateFilter();
/*     */       }
/* 163 */     });
/* 164 */     this.filterSearch.setToolTipText("Filter text");
/* 165 */     filterPanel.add(filterLabel);
/* 166 */     filterPanel.add(Box.createHorizontalGlue());
/* 167 */     filterPanel.add(this.filterButtonInteractive);
/* 168 */     filterPanel.add(Box.createHorizontalStrut(5));
/* 169 */     filterPanel.add(this.filterButtonBatch);
/* 170 */     filterPanel.add(Box.createHorizontalGlue());
/* 171 */     filterPanel.add(this.filterSearch);
/*     */     
/* 173 */     this.cellRenderer = new ActionListCellRenderer();
/* 174 */     this.actionList = new JList(this.parent.getActionManager().getActions().toArray());
/* 175 */     this.actionList.setCellRenderer(this.cellRenderer);
/* 176 */     this.actionList.setBackground(new Color(60, 60, 60));
/* 177 */     this.actionList.addListSelectionListener(new ListSelectionListener() {
/*     */       public void valueChanged(ListSelectionEvent e) {
/* 179 */         ActionsBrowser.this.parent.actionSelected((Action)ActionsBrowser.this.actionList.getSelectedValue());
/* 180 */         ActionsBrowser.this.checkExecutability();
/*     */       }
/* 182 */     });
/* 183 */     JScrollPane scrollpane = new JScrollPane(this.actionList);
/* 184 */     scrollpane.setOpaque(false);
/* 185 */     scrollpane.getViewport().setOpaque(false);
/* 186 */     scrollpane.setBorder(BorderFactory.createEmptyBorder());
/* 187 */     scrollpane.setViewportBorder(BorderFactory.createLineBorder(new Color(10, 10, 10), 2));
/*     */     
/* 189 */     scrollpane.setHorizontalScrollBarPolicy(31);
/*     */     
/* 191 */     scrollpane.setVerticalScrollBarPolicy(20);
/*     */     
/* 193 */     SlickerDecorator.instance().decorate(scrollpane.getVerticalScrollBar(), new Color(0, 0, 0, 0), new Color(140, 140, 140), new Color(80, 80, 80));
/*     */     
/*     */ 
/* 196 */     scrollpane.getVerticalScrollBar().setOpaque(false);
/*     */     
/* 198 */     JPanel footer = new JPanel();
/* 199 */     footer.setMinimumSize(new Dimension(350, 70));
/* 200 */     footer.setMaximumSize(new Dimension(400, 70));
/* 201 */     footer.setPreferredSize(new Dimension(400, 70));
/* 202 */     footer.setOpaque(false);
/* 203 */     footer.setBorder(BorderFactory.createEmptyBorder());
/* 204 */     footer.setLayout(new BoxLayout(footer, 0));
/* 205 */     ImageLozengeButton cancelButton = new ImageLozengeButton(ImageLoader.load("cancel_white_30x30.png"), "Reset", new Color(90, 0, 0), new Color(160, 0, 0), 4);
/*     */     
/*     */ 
/* 208 */     cancelButton.setLabelColor(Color.white);
/* 209 */     cancelButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 211 */         ActionsBrowser.this.parent.reset();
/*     */       }
/* 213 */     });
/* 214 */     cancelButton.setToolTipText("Reset action view");
/* 215 */     this.okButton = new ImageLozengeButton(ImageLoader.load("ok_white_30x30.png"), "Start", new Color(0, 90, 0), new Color(0, 140, 0), 4);
/*     */     
/*     */ 
/* 218 */     this.okButton.setLabelColor(Color.white);
/* 219 */     this.okButton.addActionListener(new ActionListener() {
/*     */       public void actionPerformed(ActionEvent e) {
/* 221 */         ActionsBrowser.this.trigger();
/*     */       }
/* 223 */     });
/* 224 */     this.okButton.setEnabled(false);
/* 225 */     this.okButton.setToolTipText("Start selected action");
/* 226 */     footer.add(Box.createHorizontalStrut(10));
/* 227 */     footer.add(cancelButton);
/* 228 */     footer.add(Box.createHorizontalGlue());
/* 229 */     footer.add(this.okButton);
/* 230 */     footer.add(Box.createHorizontalStrut(10));
/*     */     
/* 232 */     add(header);
/* 233 */     add(Box.createVerticalStrut(10));
/* 234 */     add(filterPanel);
/* 235 */     add(Box.createVerticalStrut(5));
/* 236 */     add(scrollpane);
/* 237 */     add(Box.createVerticalStrut(10));
/* 238 */     add(footer);
/*     */   }
/*     */   
/*     */   public Action getSelectedAction() {
/* 242 */     return (Action)this.actionList.getSelectedValue();
/*     */   }
/*     */   
/*     */   public void checkExecutability() {
/* 246 */     List<Collection<? extends Resource>> parameters = assembleParameterValues();
/* 247 */     this.cellRenderer.setParameters(parameters);
/* 248 */     Action action = (Action)this.actionList.getSelectedValue();
/* 249 */     if (action != null) {
/* 250 */       ActionStatus status = action.getStatus(parameters);
/* 251 */       if (status.equals(ActionStatus.EXECUTABLE)) {
/* 252 */         this.okButton.setEnabled(true);
/*     */       } else {
/* 254 */         this.okButton.setEnabled(false);
/*     */       }
/*     */     } else {
/* 257 */       this.okButton.setEnabled(false);
/*     */     }
/*     */   }
/*     */   
/*     */   private List<Collection<? extends Resource>> assembleParameterValues() {
/* 262 */     List<Collection<? extends Resource>> parameters = new ArrayList();
/* 263 */     List<List<Resource>> inputValues = this.parent.getInputBrowser().getCurrentValues();
/*     */     
/* 265 */     for (List<Resource> res : inputValues) {
/* 266 */       ArrayList<Resource> wrap = new ArrayList(res.size());
/* 267 */       wrap.addAll(res);
/* 268 */       parameters.add(wrap);
/*     */     }
/* 270 */     return parameters;
/*     */   }
/*     */   
/*     */   public void reset() {
/* 274 */     this.filterButtonBatch.setEnabled(true);
/* 275 */     this.filterButtonInteractive.setEnabled(true);
/* 276 */     this.filterSearch.reset();
/* 277 */     this.actionList.clearSelection();
/* 278 */     updateFilter();
/* 279 */     checkExecutability();
/*     */   }
/*     */   
/*     */   public void updateFilter() {
/* 283 */     Action selected = (Action)this.actionList.getSelectedValue();
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 288 */     if (!this.parent.getOutputBrowser().isConstraintsMode()) {
/* 289 */       this.parent.getOutputBrowser().reset();
/*     */     }
/*     */     
/* 292 */     String search = this.filterSearch.getSearchText().toLowerCase().trim();
/* 293 */     if (search.length() > 0) {
/* 294 */       search.replaceAll("\\w", "(.*)");
/* 295 */       search = "(.*)" + search + "(.*)";
/*     */     } else {
/* 297 */       search = "(.*)";
/*     */     }
/*     */     
/* 300 */     List<ResourceType> inputConstraints = this.parent.getInputBrowser().getCurrentParameters();
/*     */     
/* 302 */     List<ResourceType> outputConstraints = this.parent.getOutputBrowser().getCurrentTypes();
/*     */     
/*     */ 
/* 305 */     ActionManager<? extends Action> manager = this.parent.getActionManager();
/* 306 */     List<Action> actions = new ArrayList();
/*     */     
/* 308 */     if (this.filterButtonInteractive.isSelected()) {
/* 309 */       actions.addAll(manager.getActions(inputConstraints, outputConstraints, ActionType.INTERACTIVE));
/*     */     }
/*     */     
/*     */ 
/* 313 */     if (this.filterButtonBatch.isSelected()) {
/* 314 */       actions.addAll(manager.getActions(inputConstraints, outputConstraints, ActionType.HEADLESS));
/*     */     }
/*     */     
/*     */ 
/* 318 */     List<Action> filteredActions = new ArrayList();
/* 319 */     for (Action action : actions) {
/*     */       try {
/* 321 */         if (action.getName().toLowerCase().matches(search)) {
/* 322 */           filteredActions.add(action);
/*     */         }
/*     */       }
/*     */       catch (PatternSyntaxException e)
/*     */       {
/* 327 */         if (action.getName().toLowerCase().contains(search)) {
/* 328 */           filteredActions.add(action);
/*     */         }
/*     */       }
/*     */     }
/*     */     
/* 333 */     Collections.sort(filteredActions, new Comparator() {
/*     */       public int compare(Action o1, Action o2) {
/* 335 */         if ((o1.getType().equals(ActionType.INTERACTIVE)) && (o2.getType().equals(ActionType.HEADLESS)))
/*     */         {
/* 337 */           return -1; }
/* 338 */         if ((o1.getType().equals(ActionType.HEADLESS)) && (o2.getType().equals(ActionType.INTERACTIVE)))
/*     */         {
/* 340 */           return 1;
/*     */         }
/* 342 */         return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
/*     */       }
/*     */       
/*     */ 
/* 346 */     });
/* 347 */     this.actionList.setListData(filteredActions.toArray());
/* 348 */     if (selected != null) {
/* 349 */       this.actionList.setSelectedValue(selected, true);
/*     */     }
/* 351 */     checkExecutability();
/*     */   }
/*     */   
/*     */   private void trigger() {
/* 355 */     Action action = (Action)this.actionList.getSelectedValue();
/* 356 */     if (action != null) {
/* 357 */       List<Collection<? extends Resource>> parameterValues = assembleParameterValues();
/* 358 */       if (action.getStatus(parameterValues).equals(ActionStatus.EXECUTABLE))
/*     */       {
/* 360 */         DefaultTaskListener listener = new DefaultTaskListener(this.parent);
/*     */         try {
/* 362 */           List<Collection<? extends Resource>> params = new ArrayList();
/* 363 */           params.addAll(parameterValues);
/* 364 */           listener.setTask(this.parent.getController().getFrameworkHub().getTaskManager().execute(action, params, listener));
/*     */         }
/*     */         catch (Exception e)
/*     */         {
/* 368 */           e.printStackTrace();
/*     */         }
/* 370 */         this.parent.showActivityView();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 382 */     Graphics2D g2d = (Graphics2D)g.create();
/* 383 */     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
/*     */     
/* 385 */     g2d.setColor(BG);
/* 386 */     g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ActionsBrowser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */