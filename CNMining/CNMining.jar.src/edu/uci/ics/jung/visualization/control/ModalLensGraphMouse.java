/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.Cursor;
/*    */ import java.awt.event.KeyAdapter;
/*    */ import java.awt.event.KeyEvent;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ModalLensGraphMouse
/*    */   extends AbstractModalGraphMouse
/*    */   implements ModalGraphMouse
/*    */ {
/*    */   protected LensMagnificationGraphMousePlugin magnificationPlugin;
/*    */   
/*    */   public ModalLensGraphMouse()
/*    */   {
/* 35 */     this(1.1F, 0.9090909F);
/*    */   }
/*    */   
/*    */   public ModalLensGraphMouse(float in, float out) {
/* 39 */     this(in, out, new LensMagnificationGraphMousePlugin());
/*    */   }
/*    */   
/*    */   public ModalLensGraphMouse(LensMagnificationGraphMousePlugin magnificationPlugin) {
/* 43 */     this(1.1F, 0.9090909F, magnificationPlugin);
/*    */   }
/*    */   
/*    */   public ModalLensGraphMouse(float in, float out, LensMagnificationGraphMousePlugin magnificationPlugin) {
/* 47 */     super(in, out);
/* 48 */     this.in = in;
/* 49 */     this.out = out;
/* 50 */     this.magnificationPlugin = magnificationPlugin;
/* 51 */     loadPlugins();
/* 52 */     setModeKeyListener(new ModeKeyAdapter(this));
/*    */   }
/*    */   
/*    */   protected void loadPlugins() {
/* 56 */     this.pickingPlugin = new PickingGraphMousePlugin();
/* 57 */     this.animatedPickingPlugin = new AnimatedPickingGraphMousePlugin();
/* 58 */     this.translatingPlugin = new LensTranslatingGraphMousePlugin(16);
/* 59 */     this.scalingPlugin = new ScalingGraphMousePlugin(new CrossoverScalingControl(), 0, this.in, this.out);
/* 60 */     this.rotatingPlugin = new RotatingGraphMousePlugin();
/* 61 */     this.shearingPlugin = new ShearingGraphMousePlugin();
/*    */     
/* 63 */     add(this.magnificationPlugin);
/* 64 */     add(this.scalingPlugin);
/*    */     
/* 66 */     setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*    */   }
/*    */   
/* 69 */   public static class ModeKeyAdapter extends KeyAdapter { private char t = 't';
/* 70 */     private char p = 'p';
/*    */     protected ModalGraphMouse graphMouse;
/*    */     
/*    */     public ModeKeyAdapter(ModalGraphMouse graphMouse) {
/* 74 */       this.graphMouse = graphMouse;
/*    */     }
/*    */     
/*    */     public ModeKeyAdapter(char t, char p, ModalGraphMouse graphMouse) {
/* 78 */       this.t = t;
/* 79 */       this.p = p;
/* 80 */       this.graphMouse = graphMouse;
/*    */     }
/*    */     
/*    */     public void keyTyped(KeyEvent event) {
/* 84 */       char keyChar = event.getKeyChar();
/* 85 */       if (keyChar == this.t) {
/* 86 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(0));
/* 87 */         this.graphMouse.setMode(ModalGraphMouse.Mode.TRANSFORMING);
/* 88 */       } else if (keyChar == this.p) {
/* 89 */         ((Component)event.getSource()).setCursor(Cursor.getPredefinedCursor(12));
/* 90 */         this.graphMouse.setMode(ModalGraphMouse.Mode.PICKING);
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ModalLensGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */