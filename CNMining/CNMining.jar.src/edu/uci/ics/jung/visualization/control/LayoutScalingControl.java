/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationServer;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.geom.Point2D;
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
/*    */ 
/*    */ public class LayoutScalingControl
/*    */   implements ScalingControl
/*    */ {
/*    */   public void scale(VisualizationServer vv, float amount, Point2D from)
/*    */   {
/* 35 */     Point2D ivtfrom = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, from);
/* 36 */     MutableTransformer modelTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 37 */     modelTransformer.scale(amount, amount, ivtfrom);
/* 38 */     vv.repaint();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/LayoutScalingControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */