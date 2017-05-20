/*     */ package edu.uci.ics.jung.visualization.control;
/*     */ 
/*     */ import java.awt.Component;
/*     */ import java.awt.Cursor;
/*     */ import java.awt.ItemSelectable;
/*     */ import java.awt.event.KeyAdapter;
/*     */ import java.awt.event.KeyEvent;
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
/*     */ public class DefaultModalGraphMouse<V, E>
/*     */   extends AbstractModalGraphMouse
/*     */   implements ModalGraphMouse, ItemSelectable
/*     */ {
/*     */   public DefaultModalGraphMouse()
/*     */   {
/*  51 */     this(1.1F, 0.9090909F);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public DefaultModalGraphMouse(float in, float out)
/*     */   {
/*  60 */     super(in, out);
/*  61 */     loadPlugins();
/*  62 */     setModeKeyListener(new ModeKeyAdapter(this));
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   protected void loadPlugins()
/*     */   {
/*  71 */     this.pickingPlugin = new PickingGraphMousePlugin();
/*  72 */     this.animatedPickingPlugin = new AnimatedPickingGraphMousePlugin();
/*  73 */     this.translatingPlugin = new TranslatingGraphMousePlugin(16);
/*  74 */     this.scalingPlugin = new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, this.in, this.out);
/*  75 */     this.rotatingPlugin = new RotatingGraphMousePlugin();
/*  76 */     this.shearingPlugin = new ShearingGraphMousePlugin();
/*     */     
/*  78 */     add(this.scalingPlugin);
/*  79 */     setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*     */   }
/*     */   
/*     */   public static class ModeKeyAdapter extends KeyAdapter {
/*  83 */     private char t = 't';
/*  84 */     private char p = 'p';
/*     */     protected ModalGraphMouse graphMouse;
/*     */     
/*     */     public ModeKeyAdapter(ModalGraphMouse graphMouse) {
/*  88 */       this.graphMouse = graphMouse;
/*     */     }
/*     */     
/*     */     public ModeKeyAdapter(char t, char p, ModalGraphMouse graphMouse) {
/*  92 */       this.t = t;
/*  93 */       this.p = p;
/*  94 */       this.graphMouse = graphMouse;
/*     */     }
/*     */     
/*     */     public void keyTyped(KeyEvent event)
/*     */     {
/*  99 */       char keyChar = event.getKeyChar();
/* 100 */       if (keyChar == this.t) {
/* 101 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(0));
/* 102 */         this.graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/* 103 */       } else if (keyChar == this.p) {
/* 104 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(12));
/* 105 */         this.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
/*     */       }
/*     */     }
/*     */   }
/*     */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/DefaultModalGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */