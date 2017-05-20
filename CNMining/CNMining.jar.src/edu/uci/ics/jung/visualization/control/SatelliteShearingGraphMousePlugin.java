/*    */ package edu.uci.ics.jung.visualization.control;
/*    */ 
/*    */ import edu.uci.ics.jung.visualization.Layer;
/*    */ import edu.uci.ics.jung.visualization.MultiLayerTransformer;
/*    */ import edu.uci.ics.jung.visualization.RenderContext;
/*    */ import edu.uci.ics.jung.visualization.VisualizationViewer;
/*    */ import edu.uci.ics.jung.visualization.transform.MutableTransformer;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.MouseEvent;
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
/*    */ public class SatelliteShearingGraphMousePlugin
/*    */   extends ShearingGraphMousePlugin
/*    */ {
/*    */   public SatelliteShearingGraphMousePlugin() {}
/*    */   
/*    */   public SatelliteShearingGraphMousePlugin(int modifiers)
/*    */   {
/* 36 */     super(modifiers);
/*    */   }
/*    */   
/*    */ 
/*    */ 
/*    */   public void mouseDragged(MouseEvent e)
/*    */   {
/* 43 */     if (this.down == null) return;
/* 44 */     VisualizationViewer vv = (VisualizationViewer)e.getSource();
/* 45 */     boolean accepted = checkModifiers(e);
/* 46 */     if (accepted) {
/* 47 */       if ((vv instanceof SatelliteVisualizationViewer)) {
/* 48 */         VisualizationViewer vvMaster = ((SatelliteVisualizationViewer)vv).getMaster();
/*    */         
/*    */ 
/* 51 */         MutableTransformer modelTransformerMaster = vvMaster.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT);
/*    */         
/*    */ 
/* 54 */         vv.setCursor(this.cursor);
/* 55 */         Point2D q = this.down;
/* 56 */         Point2D p = e.getPoint();
/* 57 */         float dx = (float)(p.getX() - q.getX());
/* 58 */         float dy = (float)(p.getY() - q.getY());
/*    */         
/* 60 */         Dimension d = vv.getSize();
/* 61 */         float shx = 2.0F * dx / d.height;
/* 62 */         float shy = 2.0F * dy / d.width;
/*    */         
/*    */ 
/*    */ 
/*    */ 
/* 67 */         Point2D center = vv.getRenderContext().getMultiLayerTransformer().transform(vvMaster.getRenderContext().getMultiLayerTransformer().inverseTransform(vvMaster.getCenter()));
/* 68 */         if (p.getX() < center.getX()) {
/* 69 */           shy = -shy;
/*    */         }
/* 71 */         if (p.getY() < center.getY()) {
/* 72 */           shx = -shx;
/*    */         }
/* 74 */         modelTransformerMaster.shear(-shx, -shy, vvMaster.getCenter());
/*    */         
/* 76 */         this.down.x = e.getX();
/* 77 */         this.down.y = e.getY();
/*    */       }
/* 79 */       e.consume();
/*    */     }
/*    */   }
/*    */ }


/* Location:              /home/menick/Scrivania/ProcessMining/CNMining/CNMining.jar!/edu/uci/ics/jung/visualization/control/SatelliteShearingGraphMousePlugin.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       0.7.1
 */