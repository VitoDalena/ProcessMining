/*    */ package edu.uci.ics.jung.visualization.control;
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
/*    */ public class ModalSatelliteGraphMouse
/*    */   extends DefaultModalGraphMouse
/*    */   implements ModalGraphMouse
/*    */ {
/*    */   public ModalSatelliteGraphMouse()
/*    */   {
/* 23 */     this(1.1F, 0.9090909F);
/*    */   }
/*    */   
/*    */   public ModalSatelliteGraphMouse(float in, float out) {
/* 27 */     super(in, out);
/*    */   }
/*    */   
/*    */   protected void loadPlugins() {
/* 31 */     this.pickingPlugin = new PickingGraphMousePlugin();
/* 32 */     this.animatedPickingPlugin = new SatelliteAnimatedPickingGraphMousePlugin();
/* 33 */     this.translatingPlugin = new SatelliteTranslatingGraphMousePlugin(16);
/* 34 */     this.scalingPlugin = new SatelliteScalingGraphMousePlugin(new CrossoverScalingControl(), 0);
/* 35 */     this.rotatingPlugin = new SatelliteRotatingGraphMousePlugin();
/* 36 */     this.shearingPlugin = new SatelliteShearingGraphMousePlugin();
/*    */     
/* 38 */     add(this.scalingPlugin);
/*    */     
/* 40 */     setMode(ModalGraphMouse.Mode.TRANSFORMING);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ModalSatelliteGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */