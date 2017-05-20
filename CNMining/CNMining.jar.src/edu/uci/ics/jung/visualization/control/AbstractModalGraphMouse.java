/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import java.awt.Dimension;
/*     */ import java.awt.ItemSelectable;
/*     */ import java.awt.event.ItemEvent;
/*     */ import java.awt.event.ItemListener;
/*     */ import java.awt.event.KeyListener;
/*     */ import javax.swing.ButtonGroup;
/*     */ import javax.swing.Icon;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JRadioButtonMenuItem;
/*     */ import javax.swing.event.EventListenerList;
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
/*     */ public abstract class AbstractModalGraphMouse
/*     */   extends PluggableGraphMouse
/*     */   implements ModalGraphMouse, ItemSelectable
/*     */ {
/*     */   protected float in;
/*     */   protected float out;
/*     */   protected ItemListener modeListener;
/*     */   protected JComboBox modeBox;
/*     */   protected JMenu modeMenu;
/*     */   protected ModalGraphMouse.Mode mode;
/*  85 */   protected EventListenerList listenerList = new EventListenerList();
/*     */   
/*     */   protected GraphMousePlugin pickingPlugin;
/*     */   protected GraphMousePlugin translatingPlugin;
/*     */   protected GraphMousePlugin animatedPickingPlugin;
/*     */   protected GraphMousePlugin scalingPlugin;
/*     */   protected GraphMousePlugin rotatingPlugin;
/*     */   protected GraphMousePlugin shearingPlugin;
/*     */   protected KeyListener modeKeyListener;
/*     */   
/*     */   protected AbstractModalGraphMouse(float in, float out)
/*     */   {
/*  97 */     this.in = in;
/*  98 */     this.out = out;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   protected abstract void loadPlugins();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */   public void setMode(ModalGraphMouse.Mode mode)
/*     */   {
/* 111 */     if (this.mode != mode) {
/* 112 */       fireItemStateChanged(new ItemEvent(this, 701, this.mode, 2));
/*     */       
/* 114 */       this.mode = mode;
/* 115 */       if (mode == ModalGraphMouse.Mode.TRANSFORMING) {
/* 116 */         setTransformingMode();
/* 117 */       } else if (mode == ModalGraphMouse.Mode.PICKING) {
/* 118 */         setPickingMode();
/*     */       }
/* 120 */       if (this.modeBox != null) {
/* 121 */         this.modeBox.setSelectedItem(mode);
/*     */       }
/* 123 */       fireItemStateChanged(new ItemEvent(this, 701, mode, 1));
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */   protected void setPickingMode()
/*     */   {
/* 130 */     remove(this.translatingPlugin);
/* 131 */     remove(this.rotatingPlugin);
/* 132 */     remove(this.shearingPlugin);
/* 133 */     add(this.pickingPlugin);
/* 134 */     add(this.animatedPickingPlugin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   protected void setTransformingMode()
/*     */   {
/* 141 */     remove(this.pickingPlugin);
/* 142 */     remove(this.animatedPickingPlugin);
/* 143 */     add(this.translatingPlugin);
/* 144 */     add(this.rotatingPlugin);
/* 145 */     add(this.shearingPlugin);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setZoomAtMouse(boolean zoomAtMouse)
/*     */   {
/* 152 */     ((ScalingGraphMousePlugin)this.scalingPlugin).setZoomAtMouse(zoomAtMouse);
/*     */   }
/*     */   
/*     */   class ModeListener implements ItemListener
/*     */   {
/*     */     ModeListener() {}
/*     */     
/*     */     public void itemStateChanged(ItemEvent e) {
/* 160 */       AbstractModalGraphMouse.this.setMode((ModalGraphMouse.Mode)e.getItem());
/*     */     }
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public ItemListener getModeListener()
/*     */   {
/* 168 */     if (this.modeListener == null) {
/* 169 */       this.modeListener = new ModeListener();
/*     */     }
/* 171 */     return this.modeListener;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public KeyListener getModeKeyListener()
/*     */   {
/* 178 */     return this.modeKeyListener;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void setModeKeyListener(KeyListener modeKeyListener)
/*     */   {
/* 185 */     this.modeKeyListener = modeKeyListener;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public JComboBox getModeComboBox()
/*     */   {
/* 192 */     if (this.modeBox == null) {
/* 193 */       this.modeBox = new JComboBox(new ModalGraphMouse.Mode[] { ModalGraphMouse.Mode.TRANSFORMING, ModalGraphMouse.Mode.PICKING });
/* 194 */       this.modeBox.addItemListener(getModeListener());
/*     */     }
/* 196 */     this.modeBox.setSelectedItem(this.mode);
/* 197 */     return this.modeBox;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public JMenu getModeMenu()
/*     */   {
/* 206 */     if (this.modeMenu == null) {
/* 207 */       this.modeMenu = new JMenu();
/* 208 */       Icon icon = BasicIconFactory.getMenuArrowIcon();
/* 209 */       this.modeMenu.setIcon(BasicIconFactory.getMenuArrowIcon());
/* 210 */       this.modeMenu.setPreferredSize(new Dimension(icon.getIconWidth() + 10, icon.getIconHeight() + 10));
/*     */       
/*     */ 
/* 213 */       final JRadioButtonMenuItem transformingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.TRANSFORMING.toString());
/*     */       
/* 215 */       transformingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 217 */           if (e.getStateChange() == 1) {
/* 218 */             AbstractModalGraphMouse.this.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*     */           }
/*     */         }
/* 221 */       });
/* 222 */       final JRadioButtonMenuItem pickingButton = new JRadioButtonMenuItem(ModalGraphMouse.Mode.PICKING.toString());
/*     */       
/* 224 */       pickingButton.addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 226 */           if (e.getStateChange() == 1)
/* 227 */             AbstractModalGraphMouse.this.setMode(ModalGraphMouse.Mode.PICKING);
/*     */         }
/* 229 */       });
/* 230 */       ButtonGroup radio = new ButtonGroup();
/* 231 */       radio.add(transformingButton);
/* 232 */       radio.add(pickingButton);
/* 233 */       transformingButton.setSelected(true);
/* 234 */       this.modeMenu.add(transformingButton);
/* 235 */       this.modeMenu.add(pickingButton);
/* 236 */       this.modeMenu.setToolTipText("Menu for setting Mouse Mode");
/* 237 */       addItemListener(new ItemListener() {
/*     */         public void itemStateChanged(ItemEvent e) {
/* 239 */           if (e.getStateChange() == 1) {
/* 240 */             if (e.getItem() == ModalGraphMouse.Mode.TRANSFORMING) {
/* 241 */               transformingButton.setSelected(true);
/* 242 */             } else if (e.getItem() == ModalGraphMouse.Mode.PICKING)
/* 243 */               pickingButton.setSelected(true);
/*     */           }
/*     */         }
/*     */       });
/*     */     }
/* 248 */     return this.modeMenu;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void addItemListener(ItemListener aListener)
/*     */   {
/* 255 */     this.listenerList.add(ItemListener.class, aListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */   public void removeItemListener(ItemListener aListener)
/*     */   {
/* 262 */     this.listenerList.remove(ItemListener.class, aListener);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public ItemListener[] getItemListeners()
/*     */   {
/* 274 */     return (ItemListener[])this.listenerList.getListeners(ItemListener.class);
/*     */   }
/*     */   
/*     */   public Object[] getSelectedObjects() {
/* 278 */     if (this.mode == null) {
/* 279 */       return new Object[0];
/*     */     }
/* 281 */     Object[] result = new Object[1];
/* 282 */     result[0] = this.mode;
/* 283 */     return result;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void fireItemStateChanged(ItemEvent e)
/*     */   {
/* 296 */     Object[] listeners = this.listenerList.getListenerList();
/*     */     
/*     */ 
/* 299 */     for (int i = listeners.length - 2; i >= 0; i -= 2) {
/* 300 */       if (listeners[i] == ItemListener.class) {
/* 301 */         ((ItemListener)listeners[(i + 1)]).itemStateChanged(e);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/AbstractModalGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */