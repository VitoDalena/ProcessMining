/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import java.awt.event.MouseWheelEvent;
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
/*    */ public class SatelliteScalingGraphMousePlugin
/*    */   extends ScalingGraphMousePlugin
/*    */ {
/*    */   public SatelliteScalingGraphMousePlugin(ScalingControl scaler, int modifiers)
/*    */   {
/* 28 */     super(scaler, modifiers);
/*    */   }
/*    */   
/*    */   public SatelliteScalingGraphMousePlugin(ScalingControl scaler, int modifiers, float in, float out) {
/* 32 */     super(scaler, modifiers, in, out);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void mouseWheelMoved(MouseWheelEvent e)
/*    */   {
/* 40 */     boolean accepted = checkModifiers(e);
/* 41 */     if (accepted == true) {
/* 42 */       VisualizationViewer vv = (VisualizationViewer)e.getSource();
/*    */       
/* 44 */       if ((vv instanceof SatelliteVisualizationViewer)) {
/* 45 */         VisualizationViewer vvMaster = ((SatelliteVisualizationViewer)vv).getMaster();
/*    */         
/*    */ 
/* 48 */         int amount = e.getWheelRotation();
/*    */         
/* 50 */         if (amount > 0) {
/* 51 */           this.scaler.scale(vvMaster, this.in, vvMaster.getCenter());
/*    */         }
/* 53 */         else if (amount < 0) {
/* 54 */           this.scaler.scale(vvMaster, this.out, vvMaster.getCenter());
/*    */         }
/* 56 */         e.consume();
/* 57 */         vv.repaint();
/*    */       }
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/SatelliteScalingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */