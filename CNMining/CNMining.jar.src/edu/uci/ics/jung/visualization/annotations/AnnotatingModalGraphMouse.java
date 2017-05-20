/*     */ package edu.uci.ics.jung.visualization.annotations;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*     */ import edu.uci.ics.jung.visualization.RenderContext;
/*     */ import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
/*     */ import edu.uci.ics.jung.visualization.control.AnimatedPickingGraphMousePlugin;
/*     */ import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
/*     */ import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
/*     */ import edu.uci.ics.jung.visualization.control.ModalGraphMouse.Mode;
/*     */ import edu.uci.ics.jung.visualization.control.PickingGraphMousePlugin;
/*     */ import edu.uci.ics.jung.visualization.control.RotatingGraphMousePlugin;
/*     */ import edu.uci.ics.jung.visualization.control.ScalingGraphMousePlugin;
/*     */ import edu.uci.ics.jung.visualization.control.ShearingGraphMousePlugin;
/*     */ import edu.uci.ics.jung.visualization.control.TranslatingGraphMousePlugin;
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
/*     */ public class AnnotatingModalGraphMouse<V, E>
/*     */   extends AbstractModalGraphMouse
/*     */   implements ModalGraphMouse, ItemSelectable
/*     */ {
/*     */   protected AnnotatingGraphMousePlugin<V, E> annotatingPlugin;
/*     */   protected MultiLayerTransformer basicTransformer;
/*     */   protected RenderContext<V, E> rc;
/*     */   
/*     */   public AnnotatingModalGraphMouse(RenderContext<V, E> rc, AnnotatingGraphMousePlugin<V, E> annotatingPlugin)
/*     */   {
/*  62 */     this(rc, annotatingPlugin, 1.1F, 0.9090909F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public AnnotatingModalGraphMouse(RenderContext<V, E> rc, AnnotatingGraphMousePlugin<V, E> annotatingPlugin, float in, float out)
/*     */   {
/*  73 */     super(in, out);
/*  74 */     this.rc = rc;
/*  75 */     this.basicTransformer = rc.getMultiLayerTransformer();
/*  76 */     this.annotatingPlugin = annotatingPlugin;
/*  77 */     loadPlugins();
/*  78 */     setModeKeyListener(new ModeKeyAdapter(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void loadPlugins()
/*     */   {
/*  87 */     this.pickingPlugin = new PickingGraphMousePlugin();
/*  88 */     this.animatedPickingPlugin = new AnimatedPickingGraphMousePlugin();
/*  89 */     this.translatingPlugin = new TranslatingGraphMousePlugin(16);
/*  90 */     this.scalingPlugin = new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, this.in, this.out);
/*  91 */     this.rotatingPlugin = new RotatingGraphMousePlugin();
/*  92 */     this.shearingPlugin = new ShearingGraphMousePlugin();
/*  93 */     add(this.scalingPlugin);
/*  94 */     setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMode(ModalGraphMouse.Mode mode)
/*     */   {
/* 102 */     if (this.mode != mode) {
/* 103 */       fireItemStateChanged(new ItemEvent(this, 701, this.mode, 2));
/*     */       
/* 105 */       this.mode = mode;
/* 106 */       if (mode == ModalGraphMouse.Mode.TRANSFORMING) {
/* 107 */         setTransformingMode();
/* 108 */       } else if (mode == ModalGraphMouse.Mode.PICKING) {
/* 109 */         setPickingMode();
/* 110 */       } else if (mode == ModalGraphMouse.Mode.ANNOTATING) {
/* 111 */         setAnnotatingMode();
/*     */       }
/* 113 */       if (this.modeBox != null) {
/* 114 */         this.modeBox.setSelectedItem(mode);
/*     */       }
/* 116 */       fireItemStateChanged(new ItemEvent(this, 701, mode, 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setPickingMode()
/*     */   {
/* 125 */     remove(this.translatingPlugin);
/* 126 */     remove(this.rotatingPlugin);
/* 127 */     remove(this.shearingPlugin);
/* 128 */     remove(this.annotatingPlugin);
/* 129 */     add(this.pickingPlugin);
/* 130 */     add(this.animatedPickingPlugin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void setTransformingMode()
/*     */   {
/* 138 */     remove(this.pickingPlugin);
/* 139 */     remove(this.animatedPickingPlugin);
/* 140 */     remove(this.annotatingPlugin);
/* 141 */     add(this.translatingPlugin);
/* 142 */     add(this.rotatingPlugin);
/* 143 */     add(this.shearingPlugin);
/*     */   }
/*     */   
/*     */   protected void setEditingMode() {
/* 147 */     remove(this.pickingPlugin);
/* 148 */     remove(this.animatedPickingPlugin);
/* 149 */     remove(this.translatingPlugin);
/* 150 */     remove(this.rotatingPlugin);
/* 151 */     remove(this.shearingPlugin);
/* 152 */     remove(this.annotatingPlugin);
/*     */   }
/*     */   
/*     */   protected void setAnnotatingMode() {
/* 156 */     remove(this.pickingPlugin);
/* 157 */     remove(this.animatedPickingPlugin);
/* 158 */     remove(this.translatingPlugin);
/* 159 */     remove(this.rotatingPlugin);
/* 160 */     remove(this.shearingPlugin);
/* 161 */     add(this.annotatingPlugin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JComboBox getModeComboBox()
/*     */   {
/* 170 */     if (this.modeBox == null) {
/* 171 */       this.modeBox = new JComboBox(new ModalGraphMouse.Mode[] { ModalGraphMouse.Mode.TRANSFORMING, ModalGraphMouse.Mode.PICKING, ModalGraphMouse.Mode.ANNOTATING });
/* 172 */       this.modeBox.addItemListener(getModeListener());
/*     */     }
/* 174 */     this.modeBox.setSelectedItem(this.mode);
/* 175 */     return this.modeBox;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JMenu getModeMenu()
/*     */   {
/* 185 */     if (this.modeMenu == null) {
/* 186 */       this.modeMenu = new JMenu();
/* 187 */       Icon icon = BasicIconFactory.getMenuArrowIcon();
/* 188 */       this.modeMenu.setIcon(BasicIconFactory.getMenuArrowIcon());
/* 189 */       this.modeMenu.setPreferredSize(new Dimension(icon.getIconWidth() + 10, icon.getIconHeight() + 10));
/*     */       
/*     */ 
/* 192 */       final JRadioButtonMenuItem transformingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.TRANSFORMING.toString());
/*     */       
/* 194 */       transformingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 196 */           if (e.getStateChange() == 1) {
/* 197 */             AnnotatingModalGraphMouse.this.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*     */           }
/*     */         }
/* 200 */       });
/* 201 */       final JRadioButtonMenuItem pickingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.PICKING.toString());
/*     */       
/* 203 */       pickingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 205 */           if (e.getStateChange() == 1) {
/* 206 */             AnnotatingModalGraphMouse.this.setMode(ModalGraphMouse.Mode.PICKING);
/*     */           }
/*     */         }
/* 209 */       });
/* 210 */       ButtonGroup radio = new ButtonGroup();
/* 211 */       radio.add(transformingButton);
/* 212 */       radio.add(pickingButton);
/* 213 */       transformingButton.setSelected(true);
/* 214 */       this.modeMenu.add(transformingButton);
/* 215 */       this.modeMenu.add(pickingButton);
/* 216 */       this.modeMenu.setToolTipText("Menu for setting Mouse Mode");
/* 217 */       addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 219 */           if (e.getStateChange() == 1) {
/* 220 */             if (e.getItem() == ModalGraphMouse.Mode.TRANSFORMING) {
/* 221 */               transformingButton.setSelected(true);
/* 222 */             } else if (e.getItem() == ModalGraphMouse.Mode.PICKING)
/* 223 */               pickingButton.setSelected(true);
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 228 */     return this.modeMenu;
/*     */   }
/*     */   
/*     */   public static class ModeKeyAdapter extends KeyAdapter {
/* 232 */     private char t = 't';
/* 233 */     private char p = 'p';
/* 234 */     private char a = 'a';
/*     */     protected ModalGraphMouse graphMouse;
/*     */     
/*     */     public ModeKeyAdapter(ModalGraphMouse graphMouse) {
/* 238 */       this.graphMouse = graphMouse;
/*     */     }
/*     */     
/*     */     public ModeKeyAdapter(char t, char p, char a, ModalGraphMouse graphMouse) {
/* 242 */       this.t = t;
/* 243 */       this.p = p;
/* 244 */       this.a = a;
/* 245 */       this.graphMouse = graphMouse;
/*     */     }
/*     */     
/*     */     public void keyTyped(KeyEvent event)
/*     */     {
/* 250 */       char keyChar = event.getKeyChar();
/* 251 */       if (keyChar == this.t) {
/* 252 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(0));
/* 253 */         this.graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/* 254 */       } else if (keyChar == this.p) {
/* 255 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(12));
/* 256 */         this.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
/* 257 */       } else if (keyChar == this.a) {
/* 258 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(1));
/* 259 */         this.graphMouse.setMode(ModalGraphMouse.Mode.ANNOTATING);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/annotations/AnnotatingModalGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */