/*     */ package org.deckfour.uitopia.ui.actions;
/*     */ 
/*     */ import java.awt.BasicStroke;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Image;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D;
/*     */ import java.awt.geom.RoundRectangle2D.Float;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.Box;
/*     */ import javax.swing.BoxLayout;
/*     */ import javax.swing.ImageIcon;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import org.deckfour.uitopia.api.hub.FrameworkHub;
/*     */ import org.deckfour.uitopia.api.hub.TaskManager;
/*     */ import org.deckfour.uitopia.api.model.Parameter;
/*     */ import org.deckfour.uitopia.api.model.Resource;
/*     */ import org.deckfour.uitopia.api.model.ResourceFilter;
/*     */ import org.deckfour.uitopia.api.model.ResourceType;
/*     */ import org.deckfour.uitopia.ui.UITopiaController;
/*     */ import org.deckfour.uitopia.ui.components.ImageButton;
/*     */ import org.deckfour.uitopia.ui.main.MainView;
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
/*     */ public class ParameterField
/*     */   extends JComponent
/*     */   implements ParameterFieldListener
/*     */ {
/*     */   private static final long serialVersionUID = 6548175820355717889L;
/*     */   
/*     */   public static enum Type
/*     */   {
/*  83 */     INPUT,  OUTPUT;
/*     */     
/*     */     private Type() {} }
/*  86 */   private static final Color COLOR_BG_BOUND = new Color(80, 80, 80);
/*  87 */   private static final Color COLOR_BG_UNBOUND = new Color(120, 120, 120);
/*  88 */   private static final Color COLOR_BORDER = new Color(200, 200, 200);
/*  89 */   private static final Color COLOR_TEXT = new Color(220, 220, 220);
/*  90 */   private static final Color COLOR_TEXT_DARK = new Color(20, 20, 20);
/*     */   
/*     */   private Resource resource;
/*     */   private ResourceType type;
/*     */   private Parameter parameter;
/*     */   private Type fieldType;
/*  96 */   private List<ParameterField> children = new ArrayList(0);
/*     */   
/*     */   private ParameterFieldListener listener;
/*     */   
/*     */   private UITopiaController controller;
/*     */   private MouseListener mouseListener;
/* 102 */   private ParameterField parentField = null;
/*     */   
/*     */   public ParameterField(Type type, Parameter param, UITopiaController controller, ParameterFieldListener listener)
/*     */   {
/* 106 */     this.fieldType = type;
/* 107 */     this.parameter = param;
/* 108 */     this.controller = controller;
/* 109 */     this.listener = listener;
/* 110 */     setupUI();
/*     */   }
/*     */   
/*     */   private ParameterField(ParameterField parent) {
/* 114 */     this.parentField = parent;
/* 115 */     this.fieldType = parent.fieldType;
/* 116 */     this.parameter = parent.parameter;
/* 117 */     this.controller = parent.controller;
/* 118 */     this.listener = parent;
/*     */     
/* 120 */     setupUI();
/*     */   }
/*     */   
/*     */   public ParameterField(Type type, Resource value, UITopiaController controller, ParameterFieldListener listener)
/*     */   {
/* 125 */     this.fieldType = type;
/* 126 */     this.resource = value;
/* 127 */     this.controller = controller;
/* 128 */     this.listener = listener;
/* 129 */     setupUI();
/*     */   }
/*     */   
/*     */   public ParameterField(Type type, UITopiaController controller, ParameterFieldListener listener)
/*     */   {
/* 134 */     this.fieldType = type;
/* 135 */     this.controller = controller;
/* 136 */     this.listener = listener;
/* 137 */     setupUI();
/*     */   }
/*     */   
/*     */   public List<Resource> getResources() {
/* 141 */     List<Resource> list = new ArrayList(1 + this.children.size());
/* 142 */     if (this.resource != null) {
/* 143 */       list.add(this.resource);
/*     */     }
/* 145 */     for (ParameterField f : this.children) {
/* 146 */       if (f.resource != null) {
/* 147 */         list.add(f.resource);
/*     */       }
/*     */     }
/* 150 */     return list;
/*     */   }
/*     */   
/*     */   public ResourceType getResourceType() {
/* 154 */     return this.type;
/*     */   }
/*     */   
/*     */   public void setResource(Resource resource) {
/* 158 */     this.resource = resource;
/* 159 */     adjustState();
/*     */   }
/*     */   
/*     */   public void setResourceType(ResourceType type) {
/* 163 */     this.type = type;
/* 164 */     adjustState();
/*     */   }
/*     */   
/*     */   private void setupUI() {
/* 168 */     setMinimumSize(new Dimension(200, 50));
/* 169 */     setMaximumSize(new Dimension(500, 50));
/* 170 */     setPreferredSize(new Dimension(250, 50));
/* 171 */     setOpaque(false);
/* 172 */     setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
/* 173 */     setLayout(new BoxLayout(this, 0));
/*     */     
/* 175 */     this.mouseListener = new MouseAdapter() {
/*     */       public void mouseClicked(MouseEvent e) {
/* 177 */         if (e.getButton() == 1) {
/* 178 */           ParameterField.this.handleClick();
/*     */         }
/*     */       }
/* 181 */     };
/* 182 */     addMouseListener(this.mouseListener);
/*     */     
/* 184 */     addComponentListener(new ComponentListener()
/*     */     {
/*     */       public void componentShown(ComponentEvent e)
/*     */       {
/* 188 */         ParameterField.this.adjustState();
/*     */       }
/*     */       
/*     */       public void componentResized(ComponentEvent e)
/*     */       {
/* 193 */         ParameterField.this.adjustState();
/*     */       }
/*     */       
/*     */       public void componentMoved(ComponentEvent e)
/*     */       {
/* 198 */         ParameterField.this.adjustState();
/*     */       }
/*     */       
/*     */       public void componentHidden(ComponentEvent e)
/*     */       {
/* 203 */         ParameterField.this.adjustState();
/*     */       }
/*     */     });
/*     */   }
/*     */   
/*     */   private void adjustState() {
/* 209 */     removeAll();
/* 210 */     if (this.resource != null)
/*     */     {
/* 212 */       JLabel icon = new JLabel(new ImageIcon(this.resource.getType().getTypeIcon()));
/*     */       
/* 214 */       icon.addMouseListener(this.mouseListener);
/* 215 */       icon.setOpaque(false);
/* 216 */       JLabel name = new JLabel(this.resource.getName());
/* 217 */       name.addMouseListener(this.mouseListener);
/* 218 */       name.setOpaque(false);
/* 219 */       name.setForeground(COLOR_TEXT);
/* 220 */       name.setFont(name.getFont().deriveFont(13.0F));
/* 221 */       JLabel type = new JLabel(this.resource.getType().getTypeName());
/* 222 */       type.addMouseListener(this.mouseListener);
/* 223 */       type.setOpaque(false);
/* 224 */       type.setForeground(COLOR_TEXT);
/* 225 */       type.setFont(type.getFont().deriveFont(10.0F));
/*     */       
/* 227 */       ImageButton removeButton = new ImageButton(ImageLoader.load("remove_30x30_black.png"), new Color(140, 140, 140), new Color(240, 240, 240), 0);
/*     */       
/*     */ 
/* 230 */       removeButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 232 */           ParameterField.this.remove();
/*     */         }
/* 234 */       });
/* 235 */       removeButton.setToolTipText("Remove resource from inputs");
/*     */       
/* 237 */       ImageButton workspaceButton = new ImageButton(ImageLoader.load("workspace_30x30_black.png"), new Color(140, 140, 140), new Color(240, 240, 240), 0);
/*     */       
/*     */ 
/* 240 */       workspaceButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 242 */           ParameterField.this.controller.getMainView().showWorkspaceView(ParameterField.this.resource);
/*     */         }
/* 244 */       });
/* 245 */       workspaceButton.setToolTipText("View resource in workspace");
/*     */       
/* 247 */       ImageButton addButton = null;
/*     */       
/* 249 */       int w = getWidth();
/* 250 */       w -= 140;
/* 251 */       if ((this.parameter != null) && (this.parameter.getMaxCardinality() > 1) && (this.fieldType == Type.INPUT) && (this.parentField == null))
/*     */       {
/*     */ 
/*     */ 
/*     */ 
/* 256 */         addButton = new ImageButton(ImageLoader.load("children_30x30_black.png"), new Color(140, 140, 140), new Color(240, 240, 240), 0);
/*     */         
/*     */ 
/* 259 */         addButton.addActionListener(new ActionListener() {
/*     */           public void actionPerformed(ActionEvent e) {
/* 261 */             ParameterField.this.addChild();
/*     */           }
/* 263 */         });
/* 264 */         addButton.setToolTipText("Add additional resource");
/* 265 */         w -= 40;
/*     */       }
/*     */       
/* 268 */       JPanel infoPanel = new JPanel();
/* 269 */       infoPanel.setOpaque(false);
/* 270 */       infoPanel.addMouseListener(this.mouseListener);
/* 271 */       infoPanel.setLayout(new BoxLayout(infoPanel, 1));
/* 272 */       infoPanel.add(Box.createVerticalGlue());
/* 273 */       name.setText(fitLabelToWidth(name, name.getText(), w));
/* 274 */       infoPanel.add(ArrangementHelper.pushLeft(name));
/* 275 */       infoPanel.add(Box.createVerticalStrut(1));
/* 276 */       type.setText(fitLabelToWidth(type, type.getText(), w));
/* 277 */       infoPanel.add(ArrangementHelper.pushLeft(type));
/* 278 */       infoPanel.add(Box.createVerticalGlue());
/* 279 */       add(Box.createHorizontalStrut(5));
/* 280 */       if (this.parentField == null) {
/* 281 */         add(ArrangementHelper.centerVertically(icon));
/*     */       } else {
/* 283 */         add(Box.createHorizontalStrut(icon.getPreferredSize().width));
/*     */       }
/* 285 */       add(Box.createHorizontalStrut(10));
/* 286 */       add(infoPanel);
/* 287 */       add(Box.createHorizontalGlue());
/* 288 */       if (addButton != null) {
/* 289 */         add(ArrangementHelper.centerVertically(addButton));
/* 290 */         add(Box.createHorizontalStrut(10));
/* 291 */         addButton.setEnabled(getFreeParameterCount() < this.parameter.getMaxCardinality());
/*     */       }
/*     */       
/* 294 */       add(ArrangementHelper.centerVertically(workspaceButton));
/* 295 */       add(Box.createHorizontalStrut(10));
/* 296 */       add(ArrangementHelper.centerVertically(removeButton));
/* 297 */     } else if (this.parameter != null)
/*     */     {
/* 299 */       Image img = this.parameter.getType().getTypeIcon();
/* 300 */       JLabel icon = new JLabel(new ImageIcon(img));
/* 301 */       icon.addMouseListener(this.mouseListener);
/* 302 */       icon.setOpaque(false);
/* 303 */       JLabel name = new JLabel(this.parameter.getName());
/* 304 */       name.addMouseListener(this.mouseListener);
/* 305 */       name.setOpaque(false);
/* 306 */       name.setForeground(COLOR_TEXT_DARK);
/* 307 */       name.setFont(name.getFont().deriveFont(13.0F));
/* 308 */       JLabel type = new JLabel(this.parameter.getType().getTypeName());
/* 309 */       type.addMouseListener(this.mouseListener);
/* 310 */       type.setOpaque(false);
/* 311 */       type.setForeground(COLOR_TEXT_DARK);
/* 312 */       type.setFont(type.getFont().deriveFont(10.0F));
/* 313 */       ImageButton removeButton = new ImageButton(ImageLoader.load("remove_30x30_black.png"), new Color(140, 140, 140), new Color(240, 240, 240), 0);
/*     */       
/*     */ 
/* 316 */       removeButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 318 */           ParameterField.this.remove();
/*     */         }
/* 320 */       });
/* 321 */       removeButton.setToolTipText("Remove parameter from inputs");
/* 322 */       JPanel infoPanel = new JPanel();
/* 323 */       infoPanel.setOpaque(false);
/* 324 */       infoPanel.addMouseListener(this.mouseListener);
/* 325 */       infoPanel.setLayout(new BoxLayout(infoPanel, 1));
/* 326 */       infoPanel.add(Box.createVerticalGlue());
/* 327 */       infoPanel.add(ArrangementHelper.pushLeft(name));
/* 328 */       infoPanel.add(Box.createVerticalStrut(1));
/* 329 */       infoPanel.add(ArrangementHelper.pushLeft(type));
/* 330 */       infoPanel.add(Box.createVerticalGlue());
/* 331 */       add(Box.createHorizontalStrut(5));
/* 332 */       if (this.parentField == null) {
/* 333 */         add(ArrangementHelper.centerVertically(icon));
/*     */       } else {
/* 335 */         add(Box.createHorizontalStrut(icon.getPreferredSize().width));
/*     */       }
/* 337 */       add(Box.createHorizontalStrut(10));
/* 338 */       add(infoPanel);
/* 339 */       add(Box.createHorizontalGlue());
/* 340 */       if (this.resource != null) {
/* 341 */         add(ArrangementHelper.centerVertically(removeButton));
/*     */       }
/* 343 */     } else if (this.type != null)
/*     */     {
/* 345 */       Image img = this.type.getTypeIcon();
/* 346 */       JLabel icon = new JLabel(new ImageIcon(img));
/* 347 */       icon.addMouseListener(this.mouseListener);
/* 348 */       icon.setOpaque(false);
/* 349 */       JLabel name = new JLabel(this.type.getTypeName());
/* 350 */       name.addMouseListener(this.mouseListener);
/* 351 */       name.setOpaque(false);
/* 352 */       name.setForeground(COLOR_TEXT_DARK);
/* 353 */       name.setFont(name.getFont().deriveFont(13.0F));
/* 354 */       ImageButton removeButton = new ImageButton(ImageLoader.load("remove_30x30_black.png"), new Color(160, 160, 160), new Color(240, 240, 240), 0);
/*     */       
/*     */ 
/* 357 */       removeButton.addActionListener(new ActionListener() {
/*     */         public void actionPerformed(ActionEvent e) {
/* 359 */           ParameterField.this.remove();
/*     */         }
/* 361 */       });
/* 362 */       removeButton.setToolTipText("Remove type from outputs");
/* 363 */       add(Box.createHorizontalStrut(5));
/* 364 */       add(ArrangementHelper.centerVertically(icon));
/* 365 */       add(Box.createHorizontalStrut(10));
/* 366 */       add(ArrangementHelper.centerVertically(name));
/* 367 */       add(Box.createHorizontalGlue());
/* 368 */       add(ArrangementHelper.centerVertically(removeButton));
/*     */     } else {
/*     */       String text;
/*     */       String text;
/* 372 */       if (this.fieldType.equals(Type.INPUT)) {
/* 373 */         text = "Click to add input object";
/*     */       } else {
/* 375 */         text = "Click to add output object";
/*     */       }
/* 377 */       JLabel label = new JLabel(text);
/* 378 */       label.addMouseListener(this.mouseListener);
/* 379 */       label.setOpaque(false);
/* 380 */       label.setForeground(COLOR_TEXT);
/* 381 */       label.setFont(label.getFont().deriveFont(14.0F));
/* 382 */       add(ArrangementHelper.centerHorizontally(ArrangementHelper.centerVertically(label)));
/*     */     }
/*     */     
/* 385 */     revalidate();
/* 386 */     repaint();
/*     */   }
/*     */   
/*     */   public int getFreeParameterCount() {
/* 390 */     return this.parameter.getMaxCardinality() - this.children.size() - (this.resource == null ? 0 : 1);
/*     */   }
/*     */   
/*     */   private void handleClick()
/*     */   {
/* 395 */     if (this.resource != null)
/*     */     {
/* 397 */       selectNewResource();
/* 398 */     } else if (this.parameter != null)
/*     */     {
/* 400 */       if (this.fieldType.equals(Type.INPUT)) {
/* 401 */         selectNewResource();
/*     */       }
/*     */       
/*     */ 
/*     */     }
/* 406 */     else if (this.fieldType.equals(Type.INPUT)) {
/* 407 */       selectNewResource();
/*     */     } else {
/* 409 */       selectNewResourceType();
/*     */     }
/*     */   }
/*     */   
/*     */   private void selectNewResource()
/*     */   {
/* 415 */     final ResourceFilter filter = new ResourceFilter() {
/*     */       public boolean accept(Resource resource) {
/* 417 */         if (!ParameterField.this.controller.getFrameworkHub().getTaskManager().isActionableResource(resource))
/*     */         {
/* 419 */           return false;
/*     */         }
/* 421 */         if (ParameterField.this.parameter != null) {
/* 422 */           return ParameterField.this.parameter.getType().isAssignableFrom(resource.getType());
/*     */         }
/*     */         
/* 425 */         return resource.isFavorite();
/*     */       }
/*     */       
/* 428 */     };
/* 429 */     final Comparator<Resource> comparator = new Comparator() {
/*     */       public int compare(Resource o1, Resource o2) {
/* 431 */         return o1.getName().compareTo(o2.getName());
/*     */       }
/* 433 */     };
/* 434 */     Thread dialogThread = new Thread() {
/*     */       public void run() {
/* 436 */         Resource res = ParameterField.this.controller.getMainView().showResourcePickerDialog("Select resource", filter, comparator);
/*     */         
/*     */ 
/* 439 */         if (res != null) {
/* 440 */           ParameterField.this.resource = res;
/* 441 */           ParameterField.this.adjustState();
/* 442 */           ParameterField.this.listener.changed(ParameterField.this);
/*     */         }
/*     */       }
/* 445 */     };
/* 446 */     dialogThread.start();
/*     */   }
/*     */   
/*     */   private void selectNewResourceType() {
/* 450 */     final String title = "Select " + (this.fieldType.equals(Type.INPUT) ? "input" : "output") + " type";
/*     */     
/* 452 */     Thread dialogThread = new Thread() {
/*     */       public void run() {
/* 454 */         ResourceType resType = ParameterField.this.controller.getMainView().showResourceTypePickerDialog(title);
/*     */         
/* 456 */         if (resType != null) {
/* 457 */           ParameterField.this.type = resType;
/* 458 */           ParameterField.this.adjustState();
/* 459 */           ParameterField.this.listener.changed(ParameterField.this);
/*     */         }
/*     */       }
/* 462 */     };
/* 463 */     dialogThread.start();
/*     */   }
/*     */   
/*     */   public void remove() {
/* 467 */     for (ParameterField child : new ArrayList(this.children)) {
/* 468 */       child.remove();
/*     */     }
/* 470 */     if (this.resource != null) {
/* 471 */       this.resource = null;
/* 472 */     } else if (this.parameter != null) {
/* 473 */       this.parameter = null;
/* 474 */     } else if (this.type != null) {
/* 475 */       this.type = null;
/*     */     }
/* 477 */     adjustState();
/* 478 */     this.listener.removed(this);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void paintComponent(Graphics g)
/*     */   {
/* 488 */     Graphics2D g2d = (Graphics2D)g.create();
/* 489 */     RoundRectangle2D round = new RoundRectangle2D.Float(2.0F, 2.0F, getWidth() - 4, getHeight() - 4, 15.0F, 15.0F);
/*     */     
/* 491 */     if (this.resource != null) {
/* 492 */       round = new RoundRectangle2D.Float(1.0F, 1.0F, getWidth() - 2, getHeight() - 2, 15.0F, 15.0F);
/*     */       
/* 494 */       g2d.setColor(COLOR_BG_BOUND);
/* 495 */       g2d.fill(round);
/* 496 */     } else if (this.parameter != null) {
/* 497 */       g2d.setColor(COLOR_BG_UNBOUND);
/* 498 */       g2d.fill(round);
/* 499 */       if (this.parameter.getMinCardinality() == 0) {
/* 500 */         g2d.setStroke(new BasicStroke(2.0F, 1, 1, 10.0F, new float[] { 6.0F, 4.0F }, 0.0F));
/*     */       }
/*     */       else {
/* 503 */         g2d.setStroke(new BasicStroke(2.0F));
/*     */       }
/* 505 */       g2d.setColor(COLOR_BORDER);
/* 506 */       g2d.draw(round);
/*     */     } else {
/* 508 */       g2d.setStroke(new BasicStroke(2.0F, 1, 1, 10.0F, new float[] { 6.0F, 4.0F }, 0.0F));
/*     */       
/* 510 */       g2d.setColor(COLOR_BORDER);
/* 511 */       g2d.draw(round);
/*     */     }
/* 513 */     g2d.dispose();
/*     */   }
/*     */   
/*     */   public void removed(ParameterField source)
/*     */   {
/* 518 */     this.children.remove(source);
/* 519 */     this.listener.changed(this);
/*     */   }
/*     */   
/*     */   public void changed(ParameterField source)
/*     */   {
/* 524 */     this.listener.changed(this);
/*     */   }
/*     */   
/*     */   public List<ParameterField> getChildren() {
/* 528 */     return this.children;
/*     */   }
/*     */   
/*     */   public static String fitLabelToWidth(JComponent c, String label, int maxWidth)
/*     */   {
/* 533 */     FontMetrics metrics = c.getFontMetrics(c.getFont());
/* 534 */     Graphics g = c.getGraphics();
/* 535 */     boolean abbreviated = false;
/* 536 */     int width = Integer.MAX_VALUE;
/*     */     
/* 538 */     while (label.length() >= 2)
/*     */     {
/*     */ 
/* 541 */       String test = label;
/* 542 */       if (abbreviated) {
/* 543 */         test = test + "...";
/*     */       }
/* 545 */       Rectangle2D stringBounds = metrics.getStringBounds(test, g);
/* 546 */       width = (int)stringBounds.getWidth();
/* 547 */       if (width <= maxWidth) break;
/* 548 */       label = label.substring(0, label.length() - 1);
/* 549 */       if (!abbreviated) {
/* 550 */         abbreviated = true;
/*     */       }
/*     */     }
/*     */     
/*     */ 
/*     */ 
/* 556 */     if (abbreviated) {
/* 557 */       label = label + "...";
/*     */     }
/* 559 */     return label;
/*     */   }
/*     */   
/*     */   public ParameterField addChild() {
/* 563 */     ParameterField child = new ParameterField(this);
/* 564 */     this.children.add(child);
/* 565 */     this.listener.changed(this);
/* 566 */     assert (getFreeParameterCount() >= 0);
/* 567 */     return child;
/*     */   }
/*     */   
/*     */   public Resource getResource() {
/* 571 */     return this.resource;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/org/deckfour/uitopia/ui/actions/ParameterField.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */