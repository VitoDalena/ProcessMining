/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.annotations.AnnotatingGraphMousePlugin;
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.ItemSelectable;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ import javax.swing.plaf.basic.BasicIconFactory;
/*     */ import org.apache.commons.collections15.Factory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EditingModalGraphMouse<V, E>
/*     */   extends AbstractModalGraphMouse
/*     */   implements ModalGraphMouse, ItemSelectable
/*     */ {
/*     */   protected Factory<V> vertexFactory;
/*     */   protected Factory<E> edgeFactory;
/*     */   protected EditingGraphMousePlugin<V, E> editingPlugin;
/*     */   protected LabelEditingGraphMousePlugin<V, E> labelEditingPlugin;
/*     */   protected EditingPopupGraphMousePlugin<V, E> popupEditingPlugin;
/*     */   protected AnnotatingGraphMousePlugin<V, E> annotatingPlugin;
/*     */   protected MultiLayerTransformer basicTransformer;
/*     */   protected RenderContext<V, E> rc;
/*     */   
/*     */   public EditingModalGraphMouse(RenderContext<V, E> rc, Factory<V> vertexFactory, Factory<E> edgeFactory)
/*     */   {
/*  44 */     this(rc, vertexFactory, edgeFactory, 1.1F, 0.9090909F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public EditingModalGraphMouse(RenderContext<V, E> rc, Factory<V> vertexFactory, Factory<E> edgeFactory, float in, float out)
/*     */   {
/*  54 */     super(in, out);
/*  55 */     this.vertexFactory = vertexFactory;
/*  56 */     this.edgeFactory = edgeFactory;
/*  57 */     this.rc = rc;
/*  58 */     this.basicTransformer = rc.getMultiLayerTransformer();
/*  59 */     loadPlugins();
/*  60 */     setModeKeyListener(new ModeKeyAdapter(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void loadPlugins()
/*     */   {
/*  69 */     this.pickingPlugin = new PickingGraphMousePlugin();
/*  70 */     this.animatedPickingPlugin = new AnimatedPickingGraphMousePlugin();
/*  71 */     this.translatingPlugin = new TranslatingGraphMousePlugin(16);
/*  72 */     this.scalingPlugin = new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, this.in, this.out);
/*  73 */     this.rotatingPlugin = new RotatingGraphMousePlugin();
/*  74 */     this.shearingPlugin = new ShearingGraphMousePlugin();
/*  75 */     this.editingPlugin = new EditingGraphMousePlugin(this.vertexFactory, this.edgeFactory);
/*  76 */     this.labelEditingPlugin = new LabelEditingGraphMousePlugin();
/*  77 */     this.annotatingPlugin = new AnnotatingGraphMousePlugin(this.rc);
/*  78 */     this.popupEditingPlugin = new EditingPopupGraphMousePlugin(this.vertexFactory, this.edgeFactory);
/*  79 */     add(this.scalingPlugin);
/*  80 */     setMode(ModalGraphMouse.Mode.EDITING);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMode(ModalGraphMouse.Mode mode)
/*     */   {
/*  88 */     if (this.mode != mode) {
/*  89 */       fireItemStateChanged(new ItemEvent(this, 701, this.mode, 2));
/*     */       
/*  91 */       this.mode = mode;
/*  92 */       if (mode == ModalGraphMouse.Mode.TRANSFORMING) {
/*  93 */         setTransformingMode();
/*  94 */       } else if (mode == ModalGraphMouse.Mode.PICKING) {
/*  95 */         setPickingMode();
/*  96 */       } else if (mode == ModalGraphMouse.Mode.EDITING) {
/*  97 */         setEditingMode();
/*  98 */       } else if (mode == ModalGraphMouse.Mode.ANNOTATING) {
/*  99 */         setAnnotatingMode();
/*     */       }
/* 101 */       if (this.modeBox != null) {
/* 102 */         this.modeBox.setSelectedItem(mode);
/*     */       }
/* 104 */       fireItemStateChanged(new ItemEvent(this, 701, mode, 1));
/*     */     }
/*     */   }
/*     */   
/*     */   protected void setPickingMode()
/*     */   {
/* 110 */     remove(this.translatingPlugin);
/* 111 */     remove(this.rotatingPlugin);
/* 112 */     remove(this.shearingPlugin);
/* 113 */     remove(this.editingPlugin);
/* 114 */     remove(this.annotatingPlugin);
/* 115 */     add(this.pickingPlugin);
/* 116 */     add(this.animatedPickingPlugin);
/* 117 */     add(this.labelEditingPlugin);
/* 118 */     add(this.popupEditingPlugin);
/*     */   }
/*     */   
/*     */   protected void setTransformingMode()
/*     */   {
/* 123 */     remove(this.pickingPlugin);
/* 124 */     remove(this.animatedPickingPlugin);
/* 125 */     remove(this.editingPlugin);
/* 126 */     remove(this.annotatingPlugin);
/* 127 */     add(this.translatingPlugin);
/* 128 */     add(this.rotatingPlugin);
/* 129 */     add(this.shearingPlugin);
/* 130 */     add(this.labelEditingPlugin);
/* 131 */     add(this.popupEditingPlugin);
/*     */   }
/*     */   
/*     */   protected void setEditingMode() {
/* 135 */     remove(this.pickingPlugin);
/* 136 */     remove(this.animatedPickingPlugin);
/* 137 */     remove(this.translatingPlugin);
/* 138 */     remove(this.rotatingPlugin);
/* 139 */     remove(this.shearingPlugin);
/* 140 */     remove(this.labelEditingPlugin);
/* 141 */     remove(this.annotatingPlugin);
/* 142 */     add(this.editingPlugin);
/* 143 */     add(this.popupEditingPlugin);
/*     */   }
/*     */   
/*     */   protected void setAnnotatingMode() {
/* 147 */     remove(this.pickingPlugin);
/* 148 */     remove(this.animatedPickingPlugin);
/* 149 */     remove(this.translatingPlugin);
/* 150 */     remove(this.rotatingPlugin);
/* 151 */     remove(this.shearingPlugin);
/* 152 */     remove(this.labelEditingPlugin);
/* 153 */     remove(this.editingPlugin);
/* 154 */     remove(this.popupEditingPlugin);
/* 155 */     add(this.annotatingPlugin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JComboBox getModeComboBox()
/*     */   {
/* 164 */     if (this.modeBox == null) {
/* 165 */       this.modeBox = new JComboBox(new ModalGraphMouse.Mode[] { ModalGraphMouse.Mode.TRANSFORMING, ModalGraphMouse.Mode.PICKING, ModalGraphMouse.Mode.EDITING, ModalGraphMouse.Mode.ANNOTATING });
/* 166 */       this.modeBox.addItemListener(getModeListener());
/*     */     }
/* 168 */     this.modeBox.setSelectedItem(this.mode);
/* 169 */     return this.modeBox;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JMenu getModeMenu()
/*     */   {
/* 179 */     if (this.modeMenu == null) {
/* 180 */       this.modeMenu = new JMenu();
/* 181 */       Icon icon = BasicIconFactory.getMenuArrowIcon();
/* 182 */       this.modeMenu.setIcon(BasicIconFactory.getMenuArrowIcon());
/* 183 */       this.modeMenu.setPreferredSize(new Dimension(icon.getIconWidth() + 10, icon.getIconHeight() + 10));
/*     */       
/*     */ 
/* 186 */       final JRadioButtonMenuItem transformingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.TRANSFORMING.toString());
/*     */       
/* 188 */       transformingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 190 */           if (e.getStateChange() == 1) {
/* 191 */             EditingModalGraphMouse.this.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*     */           }
/*     */         }
/* 194 */       });
/* 195 */       final JRadioButtonMenuItem pickingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.PICKING.toString());
/*     */       
/* 197 */       pickingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 199 */           if (e.getStateChange() == 1) {
/* 200 */             EditingModalGraphMouse.this.setMode(ModalGraphMouse.Mode.PICKING);
/*     */           }
/*     */         }
/* 203 */       });
/* 204 */       final JRadioButtonMenuItem editingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.EDITING.toString());
/*     */       
/* 206 */       editingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 208 */           if (e.getStateChange() == 1) {
/* 209 */             EditingModalGraphMouse.this.setMode(ModalGraphMouse.Mode.EDITING);
/*     */           }
/*     */         }
/* 212 */       });
/* 213 */       ButtonGroup radio = new ButtonGroup();
/* 214 */       radio.add(transformingButton);
/* 215 */       radio.add(pickingButton);
/* 216 */       radio.add(editingButton);
/* 217 */       transformingButton.setSelected(true);
/* 218 */       this.modeMenu.add(transformingButton);
/* 219 */       this.modeMenu.add(pickingButton);
/* 220 */       this.modeMenu.add(editingButton);
/* 221 */       this.modeMenu.setToolTipText("Menu for setting Mouse Mode");
/* 222 */       addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 224 */           if (e.getStateChange() == 1) {
/* 225 */             if (e.getItem() == ModalGraphMouse.Mode.TRANSFORMING) {
/* 226 */               transformingButton.setSelected(true);
/* 227 */             } else if (e.getItem() == ModalGraphMouse.Mode.PICKING) {
/* 228 */               pickingButton.setSelected(true);
/* 229 */             } else if (e.getItem() == ModalGraphMouse.Mode.EDITING)
/* 230 */               editingButton.setSelected(true);
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 235 */     return this.modeMenu;
/*     */   }
/*     */   
/*     */   public static class ModeKeyAdapter extends KeyAdapter {
/* 239 */     private char t = 't';
/* 240 */     private char p = 'p';
/* 241 */     private char e = 'e';
/* 242 */     private char a = 'a';
/*     */     protected ModalGraphMouse graphMouse;
/*     */     
/*     */     public ModeKeyAdapter(ModalGraphMouse graphMouse) {
/* 246 */       this.graphMouse = graphMouse;
/*     */     }
/*     */     
/*     */     public ModeKeyAdapter(char t, char p, char e, char a, ModalGraphMouse graphMouse) {
/* 250 */       this.t = t;
/* 251 */       this.p = p;
/* 252 */       this.e = e;
/* 253 */       this.a = a;
/* 254 */       this.graphMouse = graphMouse;
/*     */     }
/*     */     
/*     */     public void keyTyped(KeyEvent event)
/*     */     {
/* 259 */       char keyChar = event.getKeyChar();
/* 260 */       if (keyChar == this.t) {
/* 261 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(0));
/* 262 */         this.graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/* 263 */       } else if (keyChar == this.p) {
/* 264 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(12));
/* 265 */         this.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
/* 266 */       } else if (keyChar == this.e) {
/* 267 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(1));
/* 268 */         this.graphMouse.setMode(ModalGraphMouse.Mode.EDITING);
/* 269 */       } else if (keyChar == this.a) {
/* 270 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(1));
/* 271 */         this.graphMouse.setMode(ModalGraphMouse.Mode.ANNOTATING);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public AnnotatingGraphMousePlugin<V, E> getAnnotatingPlugin()
/*     */   {
/* 280 */     return this.annotatingPlugin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EditingGraphMousePlugin<V, E> getEditingPlugin()
/*     */   {
/* 287 */     return this.editingPlugin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public LabelEditingGraphMousePlugin<V, E> getLabelEditingPlugin()
/*     */   {
/* 294 */     return this.labelEditingPlugin;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public EditingPopupGraphMousePlugin<V, E> getPopupEditingPlugin()
/*     */   {
/* 301 */     return this.popupEditingPlugin;
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/EditingModalGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */