/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer.GraphMouse;
/*    */ import java.awt.event.ItemListener;
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
/*    */ public abstract interface ModalGraphMouse
/*    */   extends VisualizationViewer.GraphMouse
/*    */ {
/*    */   public abstract void setMode(Mode paramMode);
/*    */   
/*    */   public abstract ItemListener getModeListener();
/*    */   
/*    */   public static enum Mode
/*    */   {
/* 34 */     TRANSFORMING,  PICKING,  ANNOTATING,  EDITING;
/*    */     
/*    */     private Mode() {}
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/ModalGraphMouse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */