/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.awt.event.MouseListener;
/*     */ import java.awt.event.MouseMotionListener;
/*     */ import java.awt.event.MouseWheelEvent;
/*     */ import java.awt.event.MouseWheelListener;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.Set;
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
/*     */ public class PluggableGraphMouse
/*     */   implements VisualizationViewer.GraphMouse
/*     */ {
/*     */   MouseListener[] mouseListeners;
/*     */   MouseMotionListener[] mouseMotionListeners;
/*     */   MouseWheelListener[] mouseWheelListeners;
/*  35 */   Set<GraphMousePlugin> mousePluginList = new LinkedHashSet();
/*  36 */   Set<MouseMotionListener> mouseMotionPluginList = new LinkedHashSet();
/*  37 */   Set<MouseWheelListener> mouseWheelPluginList = new LinkedHashSet();
/*     */   
/*     */   public void add(GraphMousePlugin plugin) {
/*  40 */     if ((plugin instanceof MouseListener)) {
/*  41 */       this.mousePluginList.add(plugin);
/*  42 */       this.mouseListeners = null;
/*     */     }
/*  44 */     if ((plugin instanceof MouseMotionListener)) {
/*  45 */       this.mouseMotionPluginList.add((MouseMotionListener)plugin);
/*  46 */       this.mouseMotionListeners = null;
/*     */     }
/*  48 */     if ((plugin instanceof MouseWheelListener)) {
/*  49 */       this.mouseWheelPluginList.add((MouseWheelListener)plugin);
/*  50 */       this.mouseWheelListeners = null;
/*     */     }
/*     */   }
/*     */   
/*     */   public void remove(GraphMousePlugin plugin) {
/*  55 */     if ((plugin instanceof MouseListener)) {
/*  56 */       boolean wasThere = this.mousePluginList.remove(plugin);
/*  57 */       if (wasThere) this.mouseListeners = null;
/*     */     }
/*  59 */     if ((plugin instanceof MouseMotionListener)) {
/*  60 */       boolean wasThere = this.mouseMotionPluginList.remove(plugin);
/*  61 */       if (wasThere) this.mouseMotionListeners = null;
/*     */     }
/*  63 */     if ((plugin instanceof MouseWheelListener)) {
/*  64 */       boolean wasThere = this.mouseWheelPluginList.remove(plugin);
/*  65 */       if (wasThere) this.mouseWheelListeners = null;
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkMouseListeners() {
/*  70 */     if (this.mouseListeners == null) {
/*  71 */       this.mouseListeners = ((MouseListener[])this.mousePluginList.toArray(new MouseListener[this.mousePluginList.size()]));
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkMouseMotionListeners()
/*     */   {
/*  77 */     if (this.mouseMotionListeners == null) {
/*  78 */       this.mouseMotionListeners = ((MouseMotionListener[])this.mouseMotionPluginList.toArray(new MouseMotionListener[this.mouseMotionPluginList.size()]));
/*     */     }
/*     */   }
/*     */   
/*     */   private void checkMouseWheelListeners()
/*     */   {
/*  84 */     if (this.mouseWheelListeners == null) {
/*  85 */       this.mouseWheelListeners = ((MouseWheelListener[])this.mouseWheelPluginList.toArray(new MouseWheelListener[this.mouseWheelPluginList.size()]));
/*     */     }
/*     */   }
/*     */   
/*     */   public void mouseClicked(MouseEvent e)
/*     */   {
/*  91 */     checkMouseListeners();
/*  92 */     for (int i = 0; i < this.mouseListeners.length; i++) {
/*  93 */       this.mouseListeners[i].mouseClicked(e);
/*  94 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/*  99 */   public void mousePressed(MouseEvent e) { checkMouseListeners();
/* 100 */     for (int i = 0; i < this.mouseListeners.length; i++) {
/* 101 */       this.mouseListeners[i].mousePressed(e);
/* 102 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 107 */   public void mouseReleased(MouseEvent e) { checkMouseListeners();
/* 108 */     for (int i = 0; i < this.mouseListeners.length; i++) {
/* 109 */       this.mouseListeners[i].mouseReleased(e);
/* 110 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 115 */   public void mouseEntered(MouseEvent e) { checkMouseListeners();
/* 116 */     for (int i = 0; i < this.mouseListeners.length; i++) {
/* 117 */       this.mouseListeners[i].mouseEntered(e);
/* 118 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 123 */   public void mouseExited(MouseEvent e) { checkMouseListeners();
/* 124 */     for (int i = 0; i < this.mouseListeners.length; i++) {
/* 125 */       this.mouseListeners[i].mouseExited(e);
/* 126 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 131 */   public void mouseDragged(MouseEvent e) { checkMouseMotionListeners();
/* 132 */     for (int i = 0; i < this.mouseMotionListeners.length; i++) {
/* 133 */       this.mouseMotionListeners[i].mouseDragged(e);
/* 134 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 139 */   public void mouseMoved(MouseEvent e) { checkMouseMotionListeners();
/* 140 */     for (int i = 0; i < this.mouseMotionListeners.length; i++) {
/* 141 */       this.mouseMotionListeners[i].mouseMoved(e);
/* 142 */       if (e.isConsumed())
/*     */         break;
/*     */     }
/*     */   }
/*     */   
/* 147 */   public void mouseWheelMoved(MouseWheelEvent e) { checkMouseWheelListeners();
/* 148 */     for (int i = 0; i < this.mouseWheelListeners.length; i++) {
/* 149 */       this.mouseWheelListeners[i].mouseWheelMoved(e);
/* 150 */       if (e.isConsumed()) {
/*     */         break;
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/PluggableGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */