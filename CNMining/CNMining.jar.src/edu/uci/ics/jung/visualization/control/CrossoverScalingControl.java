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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CrossoverScalingControl
/*    */   implements ScalingControl
/*    */ {
/* 38 */   protected double crossover = 1.0D;
/*    */   
/*    */ 
/*    */ 
/*    */   public void setCrossover(double crossover)
/*    */   {
/* 44 */     this.crossover = crossover;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public double getCrossover()
/*    */   {
/* 51 */     return this.crossover;
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */ 
/*    */   public void scale(VisualizationServer<?, ?> vv, float amount, Point2D at)
/*    */   {
/* 59 */     MutableTransformer layoutTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/* 60 */     MutableTransformer viewTransformer = vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW);
/* 61 */     double modelScale = layoutTransformer.getScale();
/* 62 */     double viewScale = viewTransformer.getScale();
/* 63 */     double inverseModelScale = Math.sqrt(this.crossover) / modelScale;
/* 64 */     double inverseViewScale = Math.sqrt(this.crossover) / viewScale;
/* 65 */     double scale = modelScale * viewScale;
/*    */     
/* 67 */     Point2D transformedAt = vv.getRenderContext().getMultiLayerTransformer().inverseTransform(Layer.VIEW, at);
/*    */     
/* 69 */     if ((scale * amount - this.crossover) * (scale * amount - this.crossover) < 0.001D)
/*    */     {
/* 71 */       layoutTransformer.scale(inverseModelScale, inverseModelScale, transformedAt);
/* 72 */       viewTransformer.scale(inverseViewScale, inverseViewScale, at);
/* 73 */     } else if (scale * amount < this.crossover)
/*    */     {
/* 75 */       viewTransformer.scale(amount, amount, at);
/* 76 */       layoutTransformer.scale(inverseModelScale, inverseModelScale, transformedAt);
/*    */     }
/*    */     else {
/* 79 */       layoutTransformer.scale(amount, amount, transformedAt);
/* 80 */       viewTransformer.scale(inverseViewScale, inverseViewScale, at);
/*    */     }
/* 82 */     vv.repaint();
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/CrossoverScalingControl.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */