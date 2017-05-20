/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
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
/*    */ public class AbsoluteCrossoverScalingControl
/*    */   extends CrossoverScalingControl
/*    */   implements ScalingControl
/*    */ {
/*    */   public void scale(VisualizationViewer<?, ?> vv, float amount, Point2D at)
/*    */   {
/* 34 */     MutableTransformer layoutTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 35 */     MutableTransformer viewTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 36 */     double modelScale = layoutTransformer.getScale();
/* 37 */     double viewScale = viewTransformer.getScale();
/* 38 */     double inverseModelScale = Math.sqrt(this.crossover) / modelScale;
/* 39 */     double inverseViewScale = Math.sqrt(this.crossover) / viewScale;
/*    */     
/* 41 */     Point2D transformedAt = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, at);
/*    */     
/*    */ 
/* 44 */     layoutTransformer.scale(inverseModelScale, inverseModelScale, transformedAt);
/* 45 */     viewTransformer.scale(inverseViewScale, inverseViewScale, at);
/*    */     
/* 47 */     super.scale(vv, amount, at);
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/AbsoluteCrossoverScalingControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */